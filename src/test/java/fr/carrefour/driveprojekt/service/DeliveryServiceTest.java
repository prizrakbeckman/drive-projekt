package fr.carrefour.driveprojekt.service;

import fr.carrefour.driveprojekt.dto.DeliveryRequest;
import fr.carrefour.driveprojekt.entities.Delivery;
import fr.carrefour.driveprojekt.mapper.DeliveryDtoToEntityMapper;
import fr.carrefour.driveprojekt.repository.DeliveryRepository;
import fr.carrefour.driveprojekt.repository.TimeslotRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DeliveryServiceTest {

	@Mock
	private DeliveryRepository deliveryRepository;
	@Mock
	private TimeslotRepository timeslotRepository;

	@InjectMocks
	private DeliveryService deliveryService;

	@InjectMocks
	private DeliveryDtoToEntityMapper deliveryDtoToEntityMapper;
	@Test
	void testBookDeliverySuccess() {
		//given
		var request = new DeliveryRequest(1, "123 Main St", "PENDING");
		var expectedDelivery = new Delivery();
		var actualDelivery = deliveryDtoToEntityMapper.dtoToEntity(request);

		//when
		when(deliveryRepository.save(any(Delivery.class))).thenReturn(expectedDelivery);
		when(timeslotRepository.existsByTimeslotId(request.getTimeslotId())).thenReturn(true);
		Delivery result = deliveryService.bookDelivery(actualDelivery);

		//then
		assertEquals(expectedDelivery.getTimeslotId(), result.getTimeslotId());
	}
}
