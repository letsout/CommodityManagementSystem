package com.lh.client;

import java.util.List;
import java.util.Scanner;

import com.lh.clientBiz.ClientBiz;
import com.lh.entity.Goods;
import com.lh.entity.Order;
import com.lh.entity.User;
/**
 * 用来显示界面和调用方法
 * @author H
 *
 */
public class Client {
	private Scanner input = new Scanner(System.in);
	private ClientBiz clientBiz = new ClientBiz();
	private User loginUser = null;
	
	public Client(){
		System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
		System.out.println("       欢迎进入五谷丰登商品零售管理系统");
		System.out.println("\t1、登录    |\t2、退出");
		System.out.println("----------------------------------");
		System.out.println("请输入你的选择：");
		String choice = input.next();
		if("1".equals(choice)){
			showLoginView();
		}
		System.out.println("系统已退出。");
	}
	
	public void showLoginView(){
		while(true){
			System.out.print("请输入用户名：");
			String uName = input.next();
			System.out.print("请输入密码：");
			String uPass = input.next();
			//调用业务代码验证用户是否合法
			loginUser = clientBiz.login(new User(uName, uPass));
			if(null == loginUser){
				System.out.println("用户名或密码错误，登录失败！");
				continue;
			}
			//验证合法用户后，还需要验证用户的权限/身份，根据身份显示不同的操作菜单
			String roleName = loginUser.getRole().getName();
			System.out.println("----------------------------------");
			System.out.println("当前登录用户：" + loginUser.getUserName() + "\t\t身份：" + roleName);
			if("库管".equals(roleName)){
				//执行库管的操作
				showStoreManagerView();
			}else if("收银员".equals(roleName)){
				//执行收银员的操作
				showCashierView();
			}
		}
	}
	
	public void showStoreManagerView(){
		System.out.println("库管操作台\n1、入库 | 2、出库| 3、新增商品| 4、查看所有商品| 5、按商品编号查询| 6、退出操作台");
		System.out.print("请选择：");
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
			System.out.println("输入错误，请选择1-6数字！");
			break;
		}
		this.query();
		showStoreManagerView();
		
	}
	
	public void showCashierView(){
		System.out.println("收银员操作台\n1、扫描商品| 2、修改商品数量| 3、打印小票| 4、退出操作台");
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
			System.out.println("输入错误，请选择1-6数字！");
			break;
		}
		query();
		showCashierView();
	}
	
	private void scanCommodity() {
		System.out.println("请输入要扫描的商品编号：");
		String commodityId = input.next();
		System.out.println(clientBiz.scanCommodity(commodityId));
		
		
	}

	/**
	 * 商品入库
	 * @return 入库是否成功
	 */
	private void inStore(){
		System.out.println("请输入要入库的商品编号：");
		String commodityId = input.next();
		Goods commodity = clientBiz.queryById(commodityId);
		if(null == commodity){
			System.out.println("仓库中没有此编号的商品，建议选择新增商品功能。");
			return;
		}
		System.out.println("请输入入库数量：");
		int num = input.nextInt();
		commodity.setNum(num);//setNum方法内部验证数字的合法性，默认0
		//调用业务方法进行入库操作		
		if(clientBiz.inStore(commodity)){
			System.out.println("入库操作成功，如需查看结果，请选择查看所有或查询商品编号：" + commodityId);
		}else{
			System.out.println("入库操作失败，请检查输入后重试。");
		}
	}
	
	private void outStore(){
		System.out.println("请输入出库商品编号：");
		Goods commodity = clientBiz.queryById(input.next());
		if(null == commodity){
			System.out.println("没有此商品，无法执行出库操作！");
			return;
		}
		
		int num = 0;
		//num需要验证
		while(true){
			System.out.println("请输入出库数量：");
			num = input.nextInt();
			if(num <= 0)
				System.out.println("出库数量必须大于0，请重试！");
			else
				break;
		}
		commodity.setNum(num);
		if(clientBiz.outStore(commodity)){
			System.out.println("出库操作成功，如需查看结果，请选择查看所有或查询商品编号：" + commodity.getId());
		}else{
			System.out.println("出库操作失败，请检查输入后重试。");
		}
	}
	
	private void query(){
		List<Goods> commodityList = clientBiz.query();
		System.out.println("商品编号\t商品名称\t\t商品单价\t库存\t单位");
		for(Goods commodity: commodityList){
			System.out.println(commodity);
		}
	}
	
	private void queryById(){
		System.out.print("请输入要查看的商品编号：");
		String commodityId = input.next();
		Goods commodity = clientBiz.queryById(commodityId);
		System.out.println("商品编号\t商品名称\t\t商品单价\t库存\t单位");
		System.out.println(commodity);
	}
	
	private void insertNewCommodity(){
		System.out.print("请输入新增的商品编号：");
		String newId = input.next();
		if(clientBiz.queryById(newId) != null){
			System.out.println("输入的商品编号已存在，请返回！");
			return;
		}
		System.out.print("商品名称：");
		String newName = input.next();
		System.out.print("商品单价：");
		double newPrice = input.nextDouble();
		System.out.print("计量单位：");
		String newUnit = input.next();
		Goods commodity = new Goods();
		commodity.setId(newId);
		commodity.setName(newName);
		commodity.setUnit(newUnit);
		commodity.setPrice(newPrice);
		commodity.setNum(0);
		
		if(clientBiz.insert(commodity)){
			System.out.println("新增成功！");
		}else{
			System.out.println("新增失败！");
		}
	}
	
	private void printOrder(){
		Order order = clientBiz.getOrder();
		if(null == order){
			System.out.println("还没有扫描商品，订单为空！");
		}else{
			System.out.println(order);
		}
	}
	
	private void updateCommodityNum(){
		System.out.print("请输入要修改的商品编号：");
		String id = input.next();
		Goods orderCommodity = clientBiz.queryFromOrderById(id);
		if(null == orderCommodity){
			System.out.println("订单中尚未添加此商品，请返回扫描商品！");
			return;
		}
		System.out.print("请输入新的订单商品数量：");
		int newNum = input.nextInt();
		orderCommodity.setNum(newNum);
		if(clientBiz.updateOrderItem(orderCommodity)){
			System.out.println("修改成功！");
		}else{
			System.out.println("修改失败！");
		}
		
		printOrder();
	}
	
}











