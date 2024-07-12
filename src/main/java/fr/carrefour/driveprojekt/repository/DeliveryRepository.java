package fr.carrefour.driveprojekt.repository;

import fr.carrefour.driveprojekt.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
	boolean existsByTimeslotId(Integer timeslotId);
}
