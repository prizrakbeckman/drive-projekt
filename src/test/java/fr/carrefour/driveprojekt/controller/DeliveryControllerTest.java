package fr.carrefour.driveprojekt.controller;

import fr.carrefour.driveprojekt.dto.DeliveryRequest;
import fr.carrefour.driveprojekt.entities.Delivery;
import fr.carrefour.driveprojekt.mapper.DeliveryDtoToEntityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.carrefour.driveprojekt.controller.DeliveryController;
import fr.carrefour.driveprojekt.service.DeliveryService;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DeliveryService deliveryService;

	@Autowired
	private DeliveryDtoToEntityMapper deliveryDtoToEntityMapper;

	@Test
	void testBookDeliveryEndpoint() throws Exception {
		//given
		var request = new DeliveryRequest(1, "123 Main St", "PENDING");
		var actualDelivery = deliveryDtoToEntityMapper.dtoToEntity(request);

		//when
		when(deliveryService.bookDelivery(any(Delivery.class))).thenReturn(actualDelivery);

		//then
		mockMvc.perform(post("/deliveries")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"timeslotId\": 1, \"deliveryAddress\": \"123 Main St\", \"status\": \"PENDING\"}"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":null,\"timeslotId\":null,\"deliveryAddress\":null,\"status\":null}"));
	}
}