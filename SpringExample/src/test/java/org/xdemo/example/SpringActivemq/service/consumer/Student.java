package org.xdemo.example.SpringActivemq.service.consumer;

import java.io.Serializable;

public class Student implements Serializable{

	private String name;
	// transient static
	private int age;
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}

	public int getAge() {
		return age;
	}

//	public void setAge(int age) {
//		this.age = age;
//	}
	
	//jdk自动调用，扩展序列化规则的方法。
//	private void writeObject(ObjectOutputStream out) throws IOException{
//		out.defaultWriteObject();
//		out.writeInt(age);
//	}
//	
//	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
//		in.defaultReadObject();
//		age = in.readInt();
//	}
}
