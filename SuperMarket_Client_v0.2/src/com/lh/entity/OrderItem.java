package com.lh.entity;

import java.io.Serializable;

/**
 * ��������
 * @author 
 *
 */
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 3879648689811270598L;
	/** ������Ʒ */
	private Goods commodity;
	/** ��Ʒ�۸� */
	private double price;
	/** ��Ʒ��λ */
	private String unit;
	/** �������� */
	private int num;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(commodity.getId() + "\t" + commodity.getName() + "\t" + price + "\t" + num);
		return str.toString();
	}
	
	/**
	 * @return the commodity
	 */
	public Goods getCommodity() {
		return commodity;
	}
	/**
	 * @param commodity the commodity to set
	 */
	public void setCommodity(Goods commodity) {
		this.commodity = commodity;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
}
