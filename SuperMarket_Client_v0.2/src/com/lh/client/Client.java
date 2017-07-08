package com.lh.client;

import java.util.List;
import java.util.Scanner;

import com.lh.clientBiz.ClientBiz;
import com.lh.entity.Goods;
import com.lh.entity.Order;
import com.lh.entity.User;
/**
 * ������ʾ����͵��÷���
 * @author H
 *
 */
public class Client {
	private Scanner input = new Scanner(System.in);
	private ClientBiz clientBiz = new ClientBiz();
	private User loginUser = null;
	
	public Client(){
		System.out.println("���������������������������������");
		System.out.println("       ��ӭ������ȷ����Ʒ���۹���ϵͳ");
		System.out.println("\t1����¼    |\t2���˳�");
		System.out.println("----------------------------------");
		System.out.println("���������ѡ��");
		String choice = input.next();
		if("1".equals(choice)){
			showLoginView();
		}
		System.out.println("ϵͳ���˳���");
	}
	
	public void showLoginView(){
		while(true){
			System.out.print("�������û�����");
			String uName = input.next();
			System.out.print("���������룺");
			String uPass = input.next();
			//����ҵ�������֤�û��Ƿ�Ϸ�
			loginUser = clientBiz.login(new User(uName, uPass));
			if(null == loginUser){
				System.out.println("�û�����������󣬵�¼ʧ�ܣ�");
				continue;
			}
			//��֤�Ϸ��û��󣬻���Ҫ��֤�û���Ȩ��/��ݣ����������ʾ��ͬ�Ĳ����˵�
			String roleName = loginUser.getRole().getName();
			System.out.println("----------------------------------");
			System.out.println("��ǰ��¼�û���" + loginUser.getUserName() + "\t\t��ݣ�" + roleName);
			if("���".equals(roleName)){
				//ִ�п�ܵĲ���
				showStoreManagerView();
			}else if("����Ա".equals(roleName)){
				//ִ������Ա�Ĳ���
				showCashierView();
			}
		}
	}
	
	public void showStoreManagerView(){
		System.out.println("��ܲ���̨\n1����� | 2������| 3��������Ʒ| 4���鿴������Ʒ| 5������Ʒ��Ų�ѯ| 6���˳�����̨");
		System.out.print("��ѡ��");
		String choice = input.next();
		switch (choice) {
		case "1":
			this.inStore();
			break;
		case "2":
			this.outStore();
			break;
		case "3":
			this.insertNewCommodity();
			break;
		case "4":
			break;
		case "5":
			this.queryById();
			break;
		case "6":
			return;
		default:
			System.out.println("���������ѡ��1-6���֣�");
			break;
		}
		this.query();
		showStoreManagerView();
		
	}
	
	public void showCashierView(){
		System.out.println("����Ա����̨\n1��ɨ����Ʒ| 2���޸���Ʒ����| 3����ӡСƱ| 4���˳�����̨");
		String choice = input.next();
		switch (choice) {
		case "1":
			scanCommodity();
			break;
		case "2":
			updateCommodityNum();
			break;
		case "3":
			printOrder();
			break;
		case "4":
			return;
		default:
			System.out.println("���������ѡ��1-6���֣�");
			break;
		}
		query();
		showCashierView();
	}
	
	private void scanCommodity() {
		System.out.println("������Ҫɨ�����Ʒ��ţ�");
		String commodityId = input.next();
		System.out.println(clientBiz.scanCommodity(commodityId));
		
		
	}

	/**
	 * ��Ʒ���
	 * @return ����Ƿ�ɹ�
	 */
	private void inStore(){
		System.out.println("������Ҫ������Ʒ��ţ�");
		String commodityId = input.next();
		Goods commodity = clientBiz.queryById(commodityId);
		if(null == commodity){
			System.out.println("�ֿ���û�д˱�ŵ���Ʒ������ѡ��������Ʒ���ܡ�");
			return;
		}
		System.out.println("���������������");
		int num = input.nextInt();
		commodity.setNum(num);//setNum�����ڲ���֤���ֵĺϷ��ԣ�Ĭ��0
		//����ҵ�񷽷�����������		
		if(clientBiz.inStore(commodity)){
			System.out.println("�������ɹ�������鿴�������ѡ��鿴���л��ѯ��Ʒ��ţ�" + commodityId);
		}else{
			System.out.println("������ʧ�ܣ�������������ԡ�");
		}
	}
	
	private void outStore(){
		System.out.println("�����������Ʒ��ţ�");
		Goods commodity = clientBiz.queryById(input.next());
		if(null == commodity){
			System.out.println("û�д���Ʒ���޷�ִ�г��������");
			return;
		}
		
		int num = 0;
		//num��Ҫ��֤
		while(true){
			System.out.println("���������������");
			num = input.nextInt();
			if(num <= 0)
				System.out.println("���������������0�������ԣ�");
			else
				break;
		}
		commodity.setNum(num);
		if(clientBiz.outStore(commodity)){
			System.out.println("��������ɹ�������鿴�������ѡ��鿴���л��ѯ��Ʒ��ţ�" + commodity.getId());
		}else{
			System.out.println("�������ʧ�ܣ�������������ԡ�");
		}
	}
	
	private void query(){
		List<Goods> commodityList = clientBiz.query();
		System.out.println("��Ʒ���\t��Ʒ����\t\t��Ʒ����\t���\t��λ");
		for(Goods commodity: commodityList){
			System.out.println(commodity);
		}
	}
	
	private void queryById(){
		System.out.print("������Ҫ�鿴����Ʒ��ţ�");
		String commodityId = input.next();
		Goods commodity = clientBiz.queryById(commodityId);
		System.out.println("��Ʒ���\t��Ʒ����\t\t��Ʒ����\t���\t��λ");
		System.out.println(commodity);
	}
	
	private void insertNewCommodity(){
		System.out.print("��������������Ʒ��ţ�");
		String newId = input.next();
		if(clientBiz.queryById(newId) != null){
			System.out.println("�������Ʒ����Ѵ��ڣ��뷵�أ�");
			return;
		}
		System.out.print("��Ʒ���ƣ�");
		String newName = input.next();
		System.out.print("��Ʒ���ۣ�");
		double newPrice = input.nextDouble();
		System.out.print("������λ��");
		String newUnit = input.next();
		Goods commodity = new Goods();
		commodity.setId(newId);
		commodity.setName(newName);
		commodity.setUnit(newUnit);
		commodity.setPrice(newPrice);
		commodity.setNum(0);
		
		if(clientBiz.insert(commodity)){
			System.out.println("�����ɹ���");
		}else{
			System.out.println("����ʧ�ܣ�");
		}
	}
	
	private void printOrder(){
		Order order = clientBiz.getOrder();
		if(null == order){
			System.out.println("��û��ɨ����Ʒ������Ϊ�գ�");
		}else{
			System.out.println(order);
		}
	}
	
	private void updateCommodityNum(){
		System.out.print("������Ҫ�޸ĵ���Ʒ��ţ�");
		String id = input.next();
		Goods orderCommodity = clientBiz.queryFromOrderById(id);
		if(null == orderCommodity){
			System.out.println("��������δ��Ӵ���Ʒ���뷵��ɨ����Ʒ��");
			return;
		}
		System.out.print("�������µĶ�����Ʒ������");
		int newNum = input.nextInt();
		orderCommodity.setNum(newNum);
		if(clientBiz.updateOrderItem(orderCommodity)){
			System.out.println("�޸ĳɹ���");
		}else{
			System.out.println("�޸�ʧ�ܣ�");
		}
		
		printOrder();
	}
	
}











