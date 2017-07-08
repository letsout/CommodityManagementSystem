package com.lh.util;

import java.io.Serializable;
import java.util.List;

import com.lh.entity.Goods;
import com.lh.entity.ServerUser;

/**
 * 系统需要一种在客户端和服务器端之间高效、安全地进行数据传输的技术。<br/>
 * DTO(Data Transfer Object，数据传送对象)是解决这个问题的比较好的方式。<br/>
 * DTO是一个普通的Java类，它封装了要传送的批量的数据。<br/>
 * 当客户端需要读取服务器端的数据的时候，服务器端将数据封装在DTO中，这样客户端就可以在一个网络调用中获得它需要的所有数据。<br/>
 * 
 * @author H
 *
 */
public class SysDTO implements Serializable {
	private static final long serialVersionUID = 2242584568954495462L;

	private String userName;
	private String password;
	private String commodityId;
	private FlagType flag;
	private ServerUser user;
	private String roleName;
	private Goods commodity;
	private List<Goods> commodityList;

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public FlagType getFlag() {
		return flag;
	}

	public void setFlag(FlagType flag) {
		this.flag = flag;
	}

	public ServerUser getUser() {
		return user;
	}

	public void setUser(ServerUser user) {
		this.user = user;
	}

	public Goods getCommodity() {
		return commodity;
	}

	public void setCommodity(Goods commodity) {
		this.commodity = commodity;
	}

	public List<Goods> getCommodityList() {
		return commodityList;
	}

	public void setCommodityList(List<Goods> commodityList) {
		this.commodityList = commodityList;
	}

}
