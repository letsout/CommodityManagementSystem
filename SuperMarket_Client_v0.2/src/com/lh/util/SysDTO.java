package com.lh.util;

import java.io.Serializable;
import java.util.List;

import com.lh.entity.Goods;
import com.lh.entity.User;

/**
 * ϵͳ��Ҫһ���ڿͻ��˺ͷ�������֮���Ч����ȫ�ؽ������ݴ���ļ�����<br/>
 * DTO(Data Transfer Object�����ݴ��Ͷ���)�ǽ���������ıȽϺõķ�ʽ��<br/>
 * DTO��һ����ͨ��Java�࣬����װ��Ҫ���͵����������ݡ�<br/>
 * ���ͻ�����Ҫ��ȡ�������˵����ݵ�ʱ�򣬷������˽����ݷ�װ��DTO�У������ͻ��˾Ϳ�����һ����������л������Ҫ���������ݡ�<br/>
 * http://blog.csdn.net/caiwenfeng_for_23/article/details/12850399
 * @author �Ͼž� ��ͷ
 *
 */
public class SysDTO implements Serializable{
	//Ϊ��ʵ�ִ�������ӵ�ʱ������ͳһ�ԣ�����Ҫʵ�����л�
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
