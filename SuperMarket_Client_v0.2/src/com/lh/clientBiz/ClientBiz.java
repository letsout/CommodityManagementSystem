package com.lh.clientBiz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lh.entity.Goods;
import com.lh.entity.Order;
import com.lh.entity.OrderItem;
import com.lh.entity.Role;
import com.lh.entity.User;
import com.lh.util.FlagType;
import com.lh.util.SysDTO;


public class ClientBiz {
	Socket socket = null;
	ObjectInputStream inStream = null;
	ObjectOutputStream outStream = null;
	private Order order = null;
	private User loginUser = null;
	
	private void initSocket(){
		try {
			socket = new Socket("localhost", 8888);
			inStream = new ObjectInputStream(socket.getInputStream());
			outStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void closeSocket(){
		try {
			outStream.close();
			inStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User login(User loginUser){
		initSocket();
		//创建数据对象
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.登录);
		dto.setUserName(loginUser.getUserName());
		dto.setPassword(loginUser.getPassword());
		
		try {
			//向服务端发送传输对象
			outStream.writeObject(dto);
			//获得服务端返回的传输对象
			dto = (SysDTO)inStream.readObject();
			if(null != dto.getRoleName()){//不为空证明验证成功
				//为登录用户对象赋权限
				loginUser.setRole(new Role(dto.getRoleName()));
				new Sounds().playMusic("F:\\sounds\\胜利狂笑.wav", 4000);
			}else{
				return null;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeSocket();
		}	
		this.loginUser = loginUser;
		return loginUser;
	}
	/**
	 * 根据商品id查找对应的商品对象
	 * @param id
	 * @return 如果没找到，返回null
	 */
	public Goods queryById(String id){
		initSocket();
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.按编号查询);
		dto.setCommodityId(id);
		try {
			outStream.writeObject(dto);
			//获取传输对象
			dto = (SysDTO)inStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			closeSocket();
		}		
		return dto.getCommodity();
	}
	/**
	 * 入库
	 * @param commodity
	 * @return
	 */
	public boolean inStore(Goods commodity){
		initSocket();
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.入库);
		dto.setCommodity(commodity);
		try {
			outStream.writeObject(dto);
			dto = (SysDTO)inStream.readObject();
			//根据服务端传回的flag标识判断入库是否成功
			return FlagType.成功 == dto.getFlag();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally{
			closeSocket();
		}
	}
	
	public boolean outStore(Goods commodity){
		initSocket();
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.出库);
		dto.setCommodity(commodity);
		try {
			outStream.writeObject(dto);
			dto = (SysDTO)inStream.readObject();
			return FlagType.成功 == dto.getFlag();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeSocket();
		}
	}
	
	public List<Goods> query(){
		List<Goods> commodityList = new ArrayList<Goods>();
		initSocket();
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.查询所有);;
		try {
			outStream.writeObject(dto);
			dto = (SysDTO)inStream.readObject();
			commodityList = dto.getCommodityList();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeSocket();
		}		
		return commodityList;
	}
	
	public boolean insert(Goods commodity){
		initSocket();
		SysDTO dto = new SysDTO();
		dto.setCommodity(commodity);
		dto.setFlag(FlagType.新增);
		try {
			outStream.writeObject(dto);
			dto = (SysDTO)inStream.readObject();
			return dto.getFlag() == FlagType.成功;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 
	 * @param id
	 * @return 返回扫描后的消息，如加入到订单成功、库存不够、无此商品等
	 */
	public String scanCommodity(String id){
		if(null == order){
			order = new Order();
		}
		Goods commodity = this.queryById(id);
		if(null == commodity){
			return "仓库中没有当前的商品，请检查后重试！";
		}
		//1、判断商品库存
		if(commodity.getNum() < 1){
			return "抱歉，库存不够，无法购买！";
		}
		//2、判断订单项里有没有当前扫描的商品
		Map<String, OrderItem> items = order.getItems();
		OrderItem item = null;
		if(items.containsKey(commodity.getId())){//订单项的订单数量+1
			item = items.get(commodity.getId());
			item.setNum(item.getNum() + 1);		
			
		}else{//创建新订单项
			item = new OrderItem();
			item.setCommodity(commodity);
			item.setPrice(commodity.getPrice());
			item.setUnit(commodity.getUnit());
			item.setNum(1);//每次扫描一个			
		}
		Goods outCommodity = new Goods();
		outCommodity.setId(commodity.getId());
		outCommodity.setNum(1);
		outStore(outCommodity);//出库一个商品
		//更新商品对象及item对象
		commodity = queryById(commodity.getId());
		item.setCommodity(commodity);
		items.put(commodity.getId(), item);		
		order.setItems(items);
		order.setUserId(loginUser.getUserName());
		System.out.println(order);
		return "扫描成功，商品已添加到订单。";
	}
	
	public Goods queryFromOrderById(String id){
		for(OrderItem item : order.getItems().values()){
			if(id.equals(item.getCommodity().getId())){
				return item.getCommodity();
			}
		}
		return null;
	}
	
	public boolean updateOrderItem(Goods commodity){
		if(commodity.getNum() < 0) return false;
		boolean isUpdate = false;
		int newNum = commodity.getNum();
		//得到订单项中的原商品对象
		int oldNum = order.getItems().get(commodity.getId()).getNum();
		//每次退回的最大数是原订单项中的数量
		if(newNum > oldNum){
			commodity.setNum(commodity.getNum() - oldNum);;
			isUpdate = outStore(commodity);
		}else if(newNum < oldNum){//退货
			commodity.setNum(oldNum - commodity.getNum());
			isUpdate = inStore(commodity);
		}
		//更新商品对象及item对象
		if(isUpdate){
			commodity = queryById(commodity.getId());
			OrderItem item = new OrderItem();
			item.setCommodity(commodity);
			item.setNum(newNum);
			item.setPrice(commodity.getPrice());
			item.setUnit(commodity.getUnit());
			Map<String, OrderItem> items = order.getItems();
			items.put(commodity.getId(), item);		
			order.setItems(items);
			order.setUserId(loginUser.getUserName());
		}
		return isUpdate;
		
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
}
