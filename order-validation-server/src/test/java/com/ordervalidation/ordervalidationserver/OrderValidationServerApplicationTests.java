package com.ordervalidation.ordervalidationserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderValidationServerApplicationTests {
	@Autowired
	private ServiceEndpoint serviceEndpoint;
	private ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet;
	private DefaultWsdl11Definition defaultWsdl11Definition;

	@Test
	void contextLoads() {
			assertThat(serviceEndpoint).isNotNull();
			//assertThat(messageDispatcherServlet).isNot()
		//	assertThat(defaultWsdl11Definition).isNotNull();


	}

}
