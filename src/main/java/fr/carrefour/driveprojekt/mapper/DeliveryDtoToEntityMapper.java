package fr.carrefour.driveprojekt.mapper;

import fr.carrefour.driveprojekt.dto.DeliveryRequest;
import fr.carrefour.driveprojekt.entities.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryDtoToEntityMapper {
	public Delivery dtoToEntity(DeliveryRequest deliveryRequest){
		Delivery delivery = new Delivery();
		delivery.setTimeslotId(deliveryRequest.getTimeslotId());
		delivery.setDeliveryAddress(deliveryRequest.getDeliveryAddress());
		delivery.setStatus(deliveryRequest.getStatus());
		return delivery;
	}
}
