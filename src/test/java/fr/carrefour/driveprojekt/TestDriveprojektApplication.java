package fr.carrefour.driveprojekt;

import org.springframework.boot.SpringApplication;

public class TestDriveprojektApplication {

	public static void main(String[] args) {
		SpringApplication.from(DriveprojektApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
