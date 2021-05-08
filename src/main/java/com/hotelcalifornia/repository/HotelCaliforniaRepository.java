package com.hotelcalifornia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelcalifornia.model.Hotelcalifornia;

//Implementando à interface HotelCaliforniaRepository alguns métodos genéricos do JPA que usaremos no CRUD:
@Repository
public interface HotelCaliforniaRepository extends JpaRepository<Hotelcalifornia, Long>{

}
