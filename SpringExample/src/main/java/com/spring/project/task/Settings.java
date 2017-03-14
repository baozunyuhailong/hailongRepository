package com.spring.project.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Settings {
    private static Settings instance;
    public static Settings getInstance() {
        return instance;
    }
    public Settings() {
        instance = this;
    }

    @Value("#{configProperties['amq_queueName']}")
    private String amqQueueName;

	public String getAmqQueueName() {
		return amqQueueName;
	}
	public void setAmqQueueName(String amqQueueName) {
		this.amqQueueName = amqQueueName;
	}
}
