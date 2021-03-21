package com.exchangeconnectivity.exchangeserver;

import com.exchangeconnectivity.exchangeserver.resourceclasses.OrderStatus;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;

class ExchangeServerApplicationTests {
	@Autowired
	OrderController orderController;

	@Test
	void contextLoads() {
		assertThat(orderController).isNotNull();
	}

}
