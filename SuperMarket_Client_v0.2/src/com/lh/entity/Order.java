package com.lh.entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Order implements Serializable{
	private static final long serialVersionUID = 395717566110767952L;
	/** 订单编号 */
	private String orderId;
	/** 操作人id */
	private String userId;
	/** 订单商品明细,键：商品id，值：订单项 */
	private Map<String , OrderItem> items;
	
	public Order(){
		items = new HashMap<String, OrderItem>();
	}
	/**
	 * 更新订单状态，去掉数字为0的项
	 */
	public void updateState(){
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			OrderItem item = items.get(key);
			if(item.getNum() == 0){
				it.remove();
			}
		}
	
	}
	
	/**
	 * 得到订单总额
	 * @return
	 */
	public double getTotal(){
		if(null == items){
			return 0;
		}
		double total = 0;
		for(OrderItem item : items.values()){
			total += item.getPrice() * item.getNum();
		}
		return total;
	}
	
	@Override
	public String toString() {
		updateState();
		StringBuilder str = new StringBuilder();
		str.append("*********************\n");
		str.append("\t系统小票\n");
		str.append("操作员：\t" + userId + "\n");
		str.append("商品编号\t商品名称\t商品单价\t购买数量\n");
		for(OrderItem item : items.values()){
			str.append(item.toString() + "\n");
		}
		str.append("订单总额：" + getTotal() + "\n");
		str.append("**********************\n");
		return str.toString();
	}
	
	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the items
	 */
	public Map<String, OrderItem> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(Map<String, OrderItem> items) {
		this.items = items;
	}
}
