package com.lh.serverBiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lh.entity.Goods;
import com.lh.entity.ServerUser;
import com.lh.util.FlagType;
import com.lh.util.SysDTO;

/**
 * 
 * @author 
 *
 */
public class ServerBiz {
	private static Map<String, Goods> commodityMap;
	private static Map<String, ServerUser> userMap;
	static{
		//确保初始化方法只会被调用一次
		initData();
	}
	public ServerBiz(){
		
	}
	
	public SysDTO dealWithDTO(SysDTO dto){
		switch (dto.getFlag()) {
		case 登录:
			System.out.println("server:登录");
			this.doLogin(dto);
			break;
		case 入库:
			System.out.println("server:入库");
			this.doInStore(dto);
			break;
		case 出库:
			System.out.println("server:出库");
			this.doOutStore(dto);
			break;
		case 新增:
			System.out.println("server:新增");
			this.doInsert(dto);
			break;
		case 查询所有:
			System.out.println("server:查询所有");
			this.doQuery(dto);
			break;
		case 按编号查询:
			System.out.println("server:按编号查询");
			this.doQueryById(dto);
			break;
		default:
			break;
		}
		return dto;
	}
	
	private void doLogin(SysDTO dto){
		//遍历用户Map，对比用户名和密码
		if(!userMap.containsKey(dto.getUserName())){
			return;
		}
		ServerUser serverUser = userMap.get(dto.getUserName());
		String password = serverUser.getPassword();
		if(password.equals(dto.getPassword())){
			//如果是合法用户，则给出当前登陆用户的身份
			dto.setRoleName(serverUser.getRole());
			return;
		}
		//不合法就没有身份
		dto.setRoleName(null);
	}
	
	private void doInStore(SysDTO dto){
		//如果仓库中已经存在入库的商品，则原商品库存增加即可
		Goods commodity = dto.getCommodity();
		if(commodity == null){
			dto.setFlag(FlagType.失败);
			return;
		}
		if(commodityMap.containsKey(commodity.getId())){
			Goods originalCommodity = commodityMap.get(commodity.getId());
			originalCommodity.setNum(commodity.getNum() + originalCommodity.getNum());
			commodityMap.put(commodity.getId(), originalCommodity);
			
		}else{//新增商品
			commodityMap.put(commodity.getId(), commodity);
		}
		dto.setFlag(FlagType.成功);
	}
	
	private void doOutStore(SysDTO dto){
		Goods commodity = dto.getCommodity();
		if(null == commodity){
			dto.setFlag(FlagType.失败);
			return;
		}
		if(commodityMap.containsKey(commodity.getId())){
			Goods originalCommodity = commodityMap.get(commodity.getId());
			if(originalCommodity.getNum() - commodity.getNum() < 0){
				//说明库存不足
				dto.setFlag(FlagType.失败);
				return;
			}
			originalCommodity.setNum(originalCommodity.getNum() - commodity.getNum());
			commodityMap.put(commodity.getId(), originalCommodity);
		}
		dto.setFlag(FlagType.成功);
	}
	
	private void doQuery(SysDTO dto){
		List<Goods> commodityList = new ArrayList<Goods>();
		for(Goods commodity : commodityMap.values()){
			commodityList.add(commodity);
		}
		dto.setCommodityList(commodityList);
	}
	
	private void doQueryById(SysDTO dto){
		if(commodityMap.containsKey(dto.getCommodityId())){
			dto.setCommodity(commodityMap.get(dto.getCommodityId()));
			dto.setFlag(FlagType.成功);
		}else{
			dto.setCommodity(null);
			dto.setFlag(FlagType.失败);
		}
	}
	
	private void doInsert(SysDTO dto){
		Goods commodity = dto.getCommodity();
		if(null == commodity){
			dto.setFlag(FlagType.失败);
			return;
		}
		commodityMap.put(commodity.getId(), commodity);
		dto.setFlag(FlagType.成功);
	}
	
	//初始化 数据
	public static void initData(){
		commodityMap = new HashMap<String, Goods>();
		Goods commodity1 = new Goods("001", "可乐", 	2.5,	"500ml", 		202);
		Goods commodity2 = new Goods("002", "面包", 	3.5, 	"袋", 	100);
		Goods commodity3 = new Goods("003", "中华", 	50, 	"盒", 	60);
		Goods commodity4 = new Goods("004", "红牛",	5,	"250ml*24罐", 	170);
		Goods commodity5 = new Goods("005", "方便面", 5,	"桶", 	100);
		Goods commodity6 = new Goods("006", "心相印抽纸", 	67.9, 	"120抽*18包", 	100);
		Goods commodity7 = new Goods("007", "水杯",	10.9, 	"个", 		150);
		Goods commodity8 = new Goods("008", "卤蛋", 	2, 	"个", 			150);

		commodityMap.put(commodity1.getId(), commodity1);
		commodityMap.put(commodity2.getId(), commodity2);
		commodityMap.put(commodity3.getId(), commodity3);
		commodityMap.put(commodity4.getId(), commodity4);
		commodityMap.put(commodity5.getId(), commodity5);
		commodityMap.put(commodity6.getId(), commodity6);
		commodityMap.put(commodity7.getId(), commodity7);
		commodityMap.put(commodity8.getId(), commodity8);
		
		for(String key : commodityMap.keySet()){
			System.out.println(commodityMap.get(key));
		}

		userMap = new HashMap<String, ServerUser>();
		userMap.put("001", new ServerUser("001", "123", "库管"));
		userMap.put("002", new ServerUser("002", "000", "收银员"));
		userMap.put("003", new ServerUser("003", "000", "收银员"));
	}
}
