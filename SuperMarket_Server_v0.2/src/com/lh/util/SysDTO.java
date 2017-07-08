package com.lh.util;

import java.io.Serializable;
import java.util.List;

import com.lh.entity.Goods;
import com.lh.entity.ServerUser;

/**
 * ϵͳ��Ҫһ���ڿͻ��˺ͷ�������֮���Ч����ȫ�ؽ������ݴ���ļ�����<br/>
 * DTO(Data Transfer Object�����ݴ��Ͷ���)�ǽ���������ıȽϺõķ�ʽ��<br/>
 * DTO��һ����ͨ��Java�࣬����װ��Ҫ���͵����������ݡ�<br/>
 * ���ͻ�����Ҫ��ȡ�������˵����ݵ�ʱ�򣬷������˽����ݷ�װ��DTO�У������ͻ��˾Ϳ�����һ����������л������Ҫ���������ݡ�<br/>
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
