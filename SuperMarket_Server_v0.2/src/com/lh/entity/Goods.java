package com.lh.entity;

import java.io.Serializable;

import com.lh.util.StringUtil;

/**
 * ��Ʒʵ����
 * @author 
 *
 */
public class Goods implements Serializable{
	private static final long serialVersionUID = -8369779871956075575L;
	/** ��Ʒ��� */
	private String id;
	/** ��Ʒ���� */
	private String name;
	/** ��Ʒ���� */
	private double price;
	/** ��λ */
	private String unit;
	/** ��Ʒ��� */
	private int num;
	/**
	 * Ĭ�Ϲ���
	 */
	public Goods() {
		super();
	}
	
	/**
	 * @param id	��Ʒ���
	 * @param name	��Ʒ����
	 * @param price	��Ʒ����
	 * @param unit	��Ʒ��λ
	 * @param num	��Ʒ���
	 */
	public Goods(String id, String name, double price, String unit, int num) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.num = num;
	}
	
	@Override
	public String toString() {
		//��ӡ˳����Ʒ��š����ơ����ۡ���桢��λ
		String str = String.format("%s\t%s\t\t%.2f\t%d\t%s", id, name, price, num, unit);
		return str;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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








