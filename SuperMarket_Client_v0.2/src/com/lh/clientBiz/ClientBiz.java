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
		//�������ݶ���
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.��¼);
		dto.setUserName(loginUser.getUserName());
		dto.setPassword(loginUser.getPassword());
		
		try {
			//�����˷��ʹ������
			outStream.writeObject(dto);
			//��÷���˷��صĴ������
			dto = (SysDTO)inStream.readObject();
			if(null != dto.getRoleName()){//��Ϊ��֤����֤�ɹ�
				//Ϊ��¼�û�����Ȩ��
				loginUser.setRole(new Role(dto.getRoleName()));
				new Sounds().playMusic("F:\\sounds\\ʤ����Ц.wav", 4000);
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
	 * ������Ʒid���Ҷ�Ӧ����Ʒ����
	 * @param id
	 * @return ���û�ҵ�������null
	 */
	public Goods queryById(String id){
		initSocket();
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.����Ų�ѯ);
		dto.setCommodityId(id);
		try {
			outStream.writeObject(dto);
			//��ȡ�������
			dto = (SysDTO)inStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			closeSocket();
		}		
		return dto.getCommodity();
	}
	/**
	 * ���
	 * @param commodity
	 * @return
	 */
	public boolean inStore(Goods commodity){
		initSocket();
		SysDTO dto = new SysDTO();
		dto.setFlag(FlagType.���);
		dto.setCommodity(commodity);
		try {
			outStream.writeObject(dto);
			dto = (SysDTO)inStream.readObject();
			//���ݷ���˴��ص�flag��ʶ�ж�����Ƿ�ɹ�
			return FlagType.�ɹ� == dto.getFlag();
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
		dto.setFlag(FlagType.����);
		dto.setCommodity(commodity);
		try {
			outStream.writeObject(dto);
			dto = (SysDTO)inStream.readObject();
			return FlagType.�ɹ� == dto.getFlag();
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
		dto.setFlag(FlagType.��ѯ����);;
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
		dto.setFlag(FlagType.����);
		try {
			outStream.writeObject(dto);
			dto = (SysDTO)inStream.readObject();
			return dto.getFlag() == FlagType.�ɹ�;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 
	 * @param id
	 * @return ����ɨ������Ϣ������뵽�����ɹ�����治�����޴���Ʒ��
	 */
	public String scanCommodity(String id){
		if(null == order){
			order = new Order();
		}
		Goods commodity = this.queryById(id);
		if(null == commodity){
			return "�ֿ���û�е�ǰ����Ʒ����������ԣ�";
		}
		//1���ж���Ʒ���
		if(commodity.getNum() < 1){
			return "��Ǹ����治�����޷�����";
		}
		//2���ж϶���������û�е�ǰɨ�����Ʒ
		Map<String, OrderItem> items = order.getItems();
		OrderItem item = null;
		if(items.containsKey(commodity.getId())){//������Ķ�������+1
			item = items.get(commodity.getId());
			item.setNum(item.getNum() + 1);		
			
		}else{//�����¶�����
			item = new OrderItem();
			item.setCommodity(commodity);
			item.setPrice(commodity.getPrice());
			item.setUnit(commodity.getUnit());
			item.setNum(1);//ÿ��ɨ��һ��			
		}
		Goods outCommodity = new Goods();
		outCommodity.setId(commodity.getId());
		outCommodity.setNum(1);
		outStore(outCommodity);//����һ����Ʒ
		//������Ʒ����item����
		commodity = queryById(commodity.getId());
		item.setCommodity(commodity);
		items.put(commodity.getId(), item);		
		order.setItems(items);
		order.setUserId(loginUser.getUserName());
		System.out.println(order);
		return "ɨ��ɹ�����Ʒ����ӵ�������";
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
		//�õ��������е�ԭ��Ʒ����
		int oldNum = order.getItems().get(commodity.getId()).getNum();
		//ÿ���˻ص��������ԭ�������е�����
		if(newNum > oldNum){
			commodity.setNum(commodity.getNum() - oldNum);;
			isUpdate = outStore(commodity);
		}else if(newNum < oldNum){//�˻�
			commodity.setNum(oldNum - commodity.getNum());
			isUpdate = inStore(commodity);
		}
		//������Ʒ����item����
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
