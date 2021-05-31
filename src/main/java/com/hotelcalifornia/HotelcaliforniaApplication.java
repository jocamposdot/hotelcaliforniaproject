package com.hotelcalifornia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Annotation que por si só contém as seguintes anotações no Spring: @Configuration, @EnableAutoConfiguration e @ComponentScan. 
Essas duas últimas, basicamente, dizem ao inicializador do Spring: “Busque e instancie todo bean anotado deste pacote para frente”. */
@SpringBootApplication
public class HotelcaliforniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelcaliforniaApplication.class, args);
	}

}
