package org.xulang.maventool.model;

public class MavenServer {
	private String userName;
	private String password;
	private String host;
	private String port;

	public MavenServer(String userName, String password, String host, String port) {
		super();
		this.userName = userName;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
