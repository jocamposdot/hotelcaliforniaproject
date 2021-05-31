package com.hotelcalifornia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelcalifornia.model.Hotelcalifornia;
import com.hotelcalifornia.repository.HotelCaliforniaRepository;

//Definindo este controller com características REST:
@RestController
//Definindo minha rota principal:
@RequestMapping({"/hotelcalifornia"})
public class HotelCaliforniaController {
	
	//Injeção de dependência. Estou delegando ao Spring a inicialização do objeto repository:
	@Autowired
	private HotelCaliforniaRepository repository;
	
	//Lista todos os hoteis = http://localhost:8090/hotelcalifornia
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	//Pesquisar pelo ID = http://localhost:8090/hotelcalifornia/{id} 
	@GetMapping(value = "{id}")
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Criar novo hotel = http://localhost:8090/hotelcalifornia
	@PostMapping
	public Hotelcalifornia create(@RequestBody Hotelcalifornia hc) {
		return repository.save(hc);
	}
	
	//Atualiza determinado hotel a partir de seu ID = http://localhost:8090/hotelcalifornia/{id}
	@PutMapping(value = "{id}")
	public ResponseEntity update(@PathVariable long id, @RequestBody Hotelcalifornia hc) {
		return repository.findById(id)
				.map(record -> {
					record.setNome(hc.getNome());
					record.setValorDiaria(hc.getValorDiaria());
					record.setCidade(hc.getCidade());
					record.setEstrelas(hc.getEstrelas());
					record.setClientes(hc.getClientes());
					Hotelcalifornia update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	//Deletar hotel específico - http://localhost:8090/hotelcalifornia/{id}
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity delete(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("Deletado com Sucesso!");
				}).orElse(ResponseEntity.notFound().build());	
	}
	
	
}
