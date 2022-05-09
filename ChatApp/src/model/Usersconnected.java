package model;

import java.io.Serializable;

public class Usersconnected implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name,ip,color;
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public String toString() {
		
		return name+" "+ip;
	}

}
