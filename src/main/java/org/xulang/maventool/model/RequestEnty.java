package org.xulang.maventool.model;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

public interface RequestEnty {
	String CHAR_SET = "UTF-8";

	/**
	 * post 请求时实现
	 * 
	 * @return
	 */
	HttpEntity toHttpEntity();

	/**
	 * get请求时实现
	 * 
	 * @return
	 */
	List<NameValuePair> toNameValuePairs();
}
