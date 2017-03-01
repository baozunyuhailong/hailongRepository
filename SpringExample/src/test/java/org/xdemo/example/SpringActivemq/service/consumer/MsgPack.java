package org.xdemo.example.SpringActivemq.service.consumer;

import org.msgpack.annotation.Message;

//redis2.6也支持MsgPack
@Message
public class MsgPack {

	public String name;
	
	public int age;
}
