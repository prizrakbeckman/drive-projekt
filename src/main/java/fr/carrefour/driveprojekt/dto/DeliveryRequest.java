package fr.carrefour.driveprojekt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryRequest {
	private Integer timeslotId;
	private String deliveryAddress;
	private String status;
}