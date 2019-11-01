package com.example.project.model.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sw on 2017/9/6.
 */
public class DTO {

	/**
	 * 请求成功
	 */
	public static final String SUCCESS = "success";
	/**
	 * 请求失败
	 */
	public static final String FAILURE = "failure";
	/**
	 * 请求发生错误
	 */
	public static final String ERROR = "error";
	/**
	 * 请求已超时
	 */
	public static final String TIMEOUT = "timeout";
	/**
	 * 未认证
	 */
	public static final String NOTAUTH = "notauth";
	/**
	 * 未登录
	 */
	public static final String NOTLOGIN = "notlogin";
	/**
	 * 重复请求
	 */
	public static final String EXIST = "exist";
	/**
	 * 默认状态成功
	 */
	protected String status = SUCCESS;
	/**
	 * 返回消息
	 */
	protected String msg = "";
	/**
	 * 返回url
	 */
	protected String url;
	/**
	 * 返回集合
	 */
	protected List<?> dataRows;
	/**
	 * 返回集合的条数
	 */
	protected Integer total;
	/**
	 * 返回一个对象
	 */
	protected Object data;
	/**
	 * 返回一个map
	 */
	protected Map<String, Object> map = new HashMap<String, Object>();
	
	public Object getData() {return data;}
	public void setData(Object data) {
		this.data = data;
	}
	public List<?> getDataRows() {
		return dataRows;
	}
	public void setDataRows(List<?> dataRows) {
		this.dataRows = dataRows;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Object getMap(String key) {
		return map.get(key);
	}
	public void addMap(String key, Object value) {
		map.put(key, value);
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
}
