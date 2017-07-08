package com.lh.entity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Order implements Serializable{
	private static final long serialVersionUID = 395717566110767952L;
	/** ������� */
	private String orderId;
	/** ������id */
	private String userId;
	/** ������Ʒ��ϸ,������Ʒid��ֵ�������� */
	private Map<String , OrderItem> items;
	
	public Order(){
		items = new HashMap<String, OrderItem>();
	}
	/**
	 * ���¶���״̬��ȥ������Ϊ0����
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
	 * �õ������ܶ�
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
		str.append("\tϵͳСƱ\n");
		str.append("����Ա��\t" + userId + "\n");
		str.append("��Ʒ���\t��Ʒ����\t��Ʒ����\t��������\n");
		for(OrderItem item : items.values()){
			str.append(item.toString() + "\n");
		}
		str.append("�����ܶ" + getTotal() + "\n");
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
