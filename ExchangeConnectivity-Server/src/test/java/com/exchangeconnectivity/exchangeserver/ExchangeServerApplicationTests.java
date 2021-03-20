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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ExchangeServerApplication.class})
@AutoConfigureMockMvc
class ExchangeServerApplicationTests {
	@Autowired
	OrderController orderController;

	@Autowired
	public MockMvc mockMvc;


	@Mock
	APIInterfaces.GetOrder orderInterface;
	Call<OrderStatus> response;
	Response res;
	OrderStatus data;

	@Before
	public void init() throws IOException {
		when(orderInterface.getOrderStatus(Mockito.anyString())).thenReturn(response);
		when(response.execute()).thenReturn(res);
		when(res.body()).thenReturn(data);
	}

	@Test
	void contextLoads() {
		assertThat(orderController).isNotNull();
	}

	@Test
	public void testOrder() throws Exception {
		this.mockMvc.perform( MockMvcRequestBuilders
				.get("/api/getorder?ID=ac5f1f242-d894-4c13-a1c")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

}
