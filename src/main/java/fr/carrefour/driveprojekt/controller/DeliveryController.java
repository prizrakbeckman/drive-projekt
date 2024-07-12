package fr.carrefour.driveprojekt.controller;

import fr.carrefour.driveprojekt.dto.DeliveryRequest;
import fr.carrefour.driveprojekt.entities.Delivery;
import fr.carrefour.driveprojekt.mapper.DeliveryDtoToEntityMapper;
import fr.carrefour.driveprojekt.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	private final DeliveryService deliveryService;
	private final DeliveryDtoToEntityMapper deliveryDtoToEntityMapper;

	public DeliveryController(DeliveryService deliveryService, DeliveryDtoToEntityMapper deliveryDtoToEntityMapper) {
		this.deliveryService = deliveryService;
		this.deliveryDtoToEntityMapper = deliveryDtoToEntityMapper;
	}

	@PostMapping
	public ResponseEntity<Delivery> bookDelivery(@RequestBody DeliveryRequest deliveryRequest) {
		var delivery = deliveryService.bookDelivery(deliveryDtoToEntityMapper.dtoToEntity(deliveryRequest));
		return ResponseEntity.status(201).body(delivery);
	}
}