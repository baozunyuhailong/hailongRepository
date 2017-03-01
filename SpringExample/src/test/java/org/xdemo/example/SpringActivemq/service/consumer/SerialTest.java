package org.xdemo.example.SpringActivemq.service.consumer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.msgpack.MessagePack;

import com.alibaba.fastjson.JSON;


public class SerialTest {

	public static void main(String[] args) throws ClassNotFoundException {
		
//		String stuFileName = "student.out";
//		
//		Student serialStu = new Student("海龙", 11);
//		serialize(serialStu, stuFileName);
//		
//		Student student = (Student)deserialize(stuFileName);
//		System.out.println("name:" + student.getName() + ",age:"+ student.getAge());
		
//		System.out.println("========================================================");
//		
//		String perFileName = "person.out";
//		Person serialPer = new Person("天宫", 99);
//		serialize(serialPer, perFileName);
//		
//		Person person = (Person)deserialize(perFileName);
//		System.out.println("name:" + person.getName() + ",age:"+ person.getAge());
//		
//		== 比 引用地址是否相同
//		equals 比值是否相同
//		System.out.println(serialPer == person);
		
		System.out.println("========================================================");

//		String memFileName = "member.out";
//		Member serialMem = Member.getInstance();
//		serialize(serialMem, memFileName);
//		
//		Member member = (Member)deserialize(memFileName);
//		System.out.println("name:" + member.getName() + ",age:"+ member.getAge() + "_member");
//		System.out.println(serialMem == member);
		
		long msgpackStart = System.currentTimeMillis();
		
		
		MsgPack msgPack = new MsgPack();
		msgPack.name = "海龙";
	    msgPack.age= 8;

	    try {
	    	// 序列化
			byte[] arr = MessagePack.pack(msgPack);
			// 反序列化
			MsgPack msgPack2 =  MessagePack.unpack(arr, MsgPack.class);
	        System.out.println(msgPack2);
			System.out.println("name:" + msgPack2.name + ",age:"+ msgPack2.age + "_msgPack");
			System.out.println(msgPack == msgPack2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("========================================================");
		String json = JSON.toJSONString(msgPack);
		System.out.println(json);
		// 反序列化
		MsgPack msgPack3 =  JSON.parseObject(json, MsgPack.class);
		System.out.println("name:" + msgPack3.name + ",age:"+ msgPack3.age + "_json");
		
		
		long msgpackEnd = System.currentTimeMillis();
		System.out.println(msgpackEnd - msgpackStart);
	}

	/**
	 * 序列化
	 * @param obj
	 * @param outFile
	 */
	public static void serialize(Object obj, String outFile) {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outFile));
			
			//向磁盘中写入当前内存中对象的状态。
			oos.writeObject(obj);
			//清空
			oos.flush();
			//关闭
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 反序列化
	 * @param obj
	 * @param outFile
	 * @throws ClassNotFoundException 
	 */
	public static Object deserialize(String readFile) throws ClassNotFoundException {
		
		ObjectInputStream ois;
		Object object;
		try {
			ois = new ObjectInputStream(new FileInputStream(readFile));
			object = ois.readObject();
			ois.close();
			return object;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
