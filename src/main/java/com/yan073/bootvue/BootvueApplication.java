package com.yan073.bootvue;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yan073.listener.HelloListener;


@SpringBootApplication
public class BootvueApplication {

	private String queuename ;

	@Bean
	Queue queue() {
		Queue que =new AnonymousQueue(); 
		this.queuename = que.getName();
		return que;
	}
	
	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("example", false, false);
	}
	
	@Bean
	Binding binding(FanoutExchange fanout, Queue queue) {
		return BindingBuilder.bind(queue).to(fanout);
	}	
	
	@Bean
	SimpleMessageListenerContainer container(	ConnectionFactory connectionFactory,
												MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(this.queuename);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(HelloListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BootvueApplication.class, args);
	}
}
