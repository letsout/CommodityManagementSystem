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
		//ȷ����ʼ������ֻ�ᱻ����һ��
		initData();
	}
	public ServerBiz(){
		
	}
	
	public SysDTO dealWithDTO(SysDTO dto){
		switch (dto.getFlag()) {
		case ��¼:
			System.out.println("server:��¼");
			this.doLogin(dto);
			break;
		case ���:
			System.out.println("server:���");
			this.doInStore(dto);
			break;
		case ����:
			System.out.println("server:����");
			this.doOutStore(dto);
			break;
		case ����:
			System.out.println("server:����");
			this.doInsert(dto);
			break;
		case ��ѯ����:
			System.out.println("server:��ѯ����");
			this.doQuery(dto);
			break;
		case ����Ų�ѯ:
			System.out.println("server:����Ų�ѯ");
			this.doQueryById(dto);
			break;
		default:
			break;
		}
		return dto;
	}
	
	private void doLogin(SysDTO dto){
		//�����û�Map���Ա��û���������
		if(!userMap.containsKey(dto.getUserName())){
			return;
		}
		ServerUser serverUser = userMap.get(dto.getUserName());
		String password = serverUser.getPassword();
		if(password.equals(dto.getPassword())){
			//����ǺϷ��û����������ǰ��½�û������
			dto.setRoleName(serverUser.getRole());
			return;
		}
		//���Ϸ���û�����
		dto.setRoleName(null);
	}
	
	private void doInStore(SysDTO dto){
		//����ֿ����Ѿ�����������Ʒ����ԭ��Ʒ������Ӽ���
		Goods commodity = dto.getCommodity();
		if(commodity == null){
			dto.setFlag(FlagType.ʧ��);
			return;
		}
		if(commodityMap.containsKey(commodity.getId())){
			Goods originalCommodity = commodityMap.get(commodity.getId());
			originalCommodity.setNum(commodity.getNum() + originalCommodity.getNum());
			commodityMap.put(commodity.getId(), originalCommodity);
			
		}else{//������Ʒ
			commodityMap.put(commodity.getId(), commodity);
		}
		dto.setFlag(FlagType.�ɹ�);
	}
	
	private void doOutStore(SysDTO dto){
		Goods commodity = dto.getCommodity();
		if(null == commodity){
			dto.setFlag(FlagType.ʧ��);
			return;
		}
		if(commodityMap.containsKey(commodity.getId())){
			Goods originalCommodity = commodityMap.get(commodity.getId());
			if(originalCommodity.getNum() - commodity.getNum() < 0){
				//˵����治��
				dto.setFlag(FlagType.ʧ��);
				return;
			}
			originalCommodity.setNum(originalCommodity.getNum() - commodity.getNum());
			commodityMap.put(commodity.getId(), originalCommodity);
		}
		dto.setFlag(FlagType.�ɹ�);
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
			dto.setFlag(FlagType.�ɹ�);
		}else{
			dto.setCommodity(null);
			dto.setFlag(FlagType.ʧ��);
		}
	}
	
	private void doInsert(SysDTO dto){
		Goods commodity = dto.getCommodity();
		if(null == commodity){
			dto.setFlag(FlagType.ʧ��);
			return;
		}
		commodityMap.put(commodity.getId(), commodity);
		dto.setFlag(FlagType.�ɹ�);
	}
	
	//��ʼ�� ����
	public static void initData(){
		commodityMap = new HashMap<String, Goods>();
		Goods commodity1 = new Goods("001", "����", 	2.5,	"500ml", 		202);
		Goods commodity2 = new Goods("002", "���", 	3.5, 	"��", 	100);
		Goods commodity3 = new Goods("003", "�л�", 	50, 	"��", 	60);
		Goods commodity4 = new Goods("004", "��ţ",	5,	"250ml*24��", 	170);
		Goods commodity5 = new Goods("005", "������", 5,	"Ͱ", 	100);
		Goods commodity6 = new Goods("006", "����ӡ��ֽ", 	67.9, 	"120��*18��", 	100);
		Goods commodity7 = new Goods("007", "ˮ��",	10.9, 	"��", 		150);
		Goods commodity8 = new Goods("008", "±��", 	2, 	"��", 			150);

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
		userMap.put("001", new ServerUser("001", "123", "���"));
		userMap.put("002", new ServerUser("002", "000", "����Ա"));
		userMap.put("003", new ServerUser("003", "000", "����Ա"));
	}
}
