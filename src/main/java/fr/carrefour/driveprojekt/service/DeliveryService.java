package fr.carrefour.driveprojekt.service;

import fr.carrefour.driveprojekt.entities.Delivery;
import fr.carrefour.driveprojekt.repository.DeliveryRepository;
import fr.carrefour.driveprojekt.repository.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;
	private final TimeslotRepository timeslotRepository;

	@Autowired
	public DeliveryService(DeliveryRepository deliveryRepository, TimeslotRepository timeslotRepository) {
		this.deliveryRepository = deliveryRepository;
		this.timeslotRepository = timeslotRepository;
	}


	@Transactional
	public Delivery bookDelivery(Delivery delivery) {
		validateTimeslotNotbooked(delivery);
		return deliveryRepository.save(delivery);
	}

	private void validateTimeslotNotbooked(Delivery delivery) {
		if (timeslotRepository.existsByTimeslotId(delivery.getTimeslotId())) {
			throw new IllegalStateException("Timeslot already booked");
		}
	}
}
