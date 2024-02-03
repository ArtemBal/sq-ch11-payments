package com.example.sqch11payments;

import com.example.sqch11payments.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testPaymentEndpoint() throws Exception {
		Payment requestBody = new Payment();
		requestBody.setAmount(1500);

		ObjectMapper objectMapper = new ObjectMapper();

		mockMvc.perform(post("/payment")
					.header("requestId", "1")
					.content(objectMapper.writeValueAsString(requestBody))
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value(1500));
	}

}
