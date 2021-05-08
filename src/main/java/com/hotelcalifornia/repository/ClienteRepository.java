package com.hotelcalifornia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelcalifornia.model.Cliente;

//Implementando à interface ClienteRepository alguns métodos genéricos do JPA que usaremos no CRUD:
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
