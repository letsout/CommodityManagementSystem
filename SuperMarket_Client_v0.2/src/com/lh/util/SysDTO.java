package com.lh.util;

import java.io.Serializable;
import java.util.List;

import com.lh.entity.Goods;
import com.lh.entity.User;

/**
 * 系统需要一种在客户端和服务器端之间高效、安全地进行数据传输的技术。<br/>
 * DTO(Data Transfer Object，数据传送对象)是解决这个问题的比较好的方式。<br/>
 * DTO是一个普通的Java类，它封装了要传送的批量的数据。<br/>
 * 当客户端需要读取服务器端的数据的时候，服务器端将数据封装在DTO中，这样客户端就可以在一个网络调用中获得它需要的所有数据。<br/>
 * http://blog.csdn.net/caiwenfeng_for_23/article/details/12850399
 * @author 老九君 窖头
 *
 */
public class SysDTO implements Serializable{
	//为了实现传输和链接的时候数据统一性，所以要实现序列化
	private static final long serialVersionUID = 2242584568954495462L;
	
	private String userName;
	private String password;
	private String commodityId;
	private FlagType flag;
	private User user;
	private String roleName;
	private Goods commodity;
	private List<Goods> commodityList;	
	
	
	/**
	 * @return the commodityId
	 */
	public String getCommodityId() {
		return commodityId;
	}
	/**
	 * @param commodityId the commodityId to set
	 */
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the flag
	 */
	public FlagType getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(FlagType flag) {
		this.flag = flag;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @return the commodityList
	 */
	public List<Goods> getCommodityList() {
		return commodityList;
	}
	/**
	 * @param commodityList the commodityList to set
	 */
	public void setCommodityList(List<Goods> commodityList) {
		this.commodityList = commodityList;
	}
	
	
}
