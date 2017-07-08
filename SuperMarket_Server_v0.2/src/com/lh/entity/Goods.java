package com.lh.entity;

import java.io.Serializable;

import com.lh.util.StringUtil;

/**
 * 商品实体类
 * @author 
 *
 */
public class Goods implements Serializable{
	private static final long serialVersionUID = -8369779871956075575L;
	/** 商品编号 */
	private String id;
	/** 商品名称 */
	private String name;
	/** 商品单价 */
	private double price;
	/** 单位 */
	private String unit;
	/** 商品库存 */
	private int num;
	/**
	 * 默认构造
	 */
	public Goods() {
		super();
	}
	
	/**
	 * @param id	商品编号
	 * @param name	商品名称
	 * @param price	商品单价
	 * @param unit	商品单位
	 * @param num	商品库存
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
		//打印顺序：商品编号、名称、单价、库存、单位
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








