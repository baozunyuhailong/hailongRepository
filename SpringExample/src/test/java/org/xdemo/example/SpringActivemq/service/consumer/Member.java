package org.xdemo.example.SpringActivemq.service.consumer;

import java.io.Serializable;

public class Member implements Serializable{

	private String name;
	
	private int age;
	
	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	private static class InstanceHolder {
		private static final Member instance = new Member("Tom老师", 20);
	}

	public static Member getInstance() {
		return InstanceHolder.instance;
	}

//	private Object readResolve() throws ObjectStreamException {
//		return InstanceHolder.instance;
//	}
}
