package com.testyantra.datatransfer.service;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Service;
import org.apache.camel.component.jms.JmsComponent;

@Service
public class ActiveMqServiceimpl implements ActiveMqService {

	private CamelContext contex;

	@Override
	public String postActiveMq(String data) {
		try {
			
			System.out.println(data);
			contex = new DefaultCamelContext();

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			contex.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
			
			ProducerTemplate producertemplate = contex.createProducerTemplate();
			producertemplate.sendBody("direct:start",data);
			contex.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
					// TODO Auto-generated method stub
					from("direct:start").process(new Processor() {

						@Override
						public void process(Exchange exchange) throws Exception {
							Exception e =exchange.getException();
							System.out.println(e);
							
						}
						
					})
					.to("seda:end");
				}
				
			});
			
			ConsumerTemplate consumerTemple = contex.createConsumerTemplate();
			String mess = consumerTemple.receiveBody("seda:end",String.class);
			System.err.println(mess);
			while(true)
			contex.start();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
