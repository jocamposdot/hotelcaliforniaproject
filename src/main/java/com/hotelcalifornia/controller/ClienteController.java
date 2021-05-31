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

import com.hotelcalifornia.model.Cliente;
import com.hotelcalifornia.repository.ClienteRepository;

//Definindo este controller com características REST:
@RestController
//Definindo minha rota principal:
@RequestMapping({"/cliente"})
public class ClienteController {
	
	//Injeção de dependência. Estou delegando ao Spring a inicialização do objeto repository:
	@Autowired
	private ClienteRepository repository;
	
	//Lista todos os clientes = http://localhost:8090/cliente
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	//Pesquisar pelo ID = http://localhost:8090/cliente/{cpf} 
	@GetMapping(value = "{cpf}")
	public ResponseEntity findById(@PathVariable String cpf) {
		return repository.findById(cpf)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Criar novo cliente = http://localhost:8090/cliente
	@PostMapping
	public Cliente create(@RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	//Atualiza determinado cliente a partir de seu ID = http://localhost:8090/cliente/{cpf}
	@PutMapping(value = "{cpf}")
	public ResponseEntity update(@PathVariable String cpf, @RequestBody Cliente cliente) {
		return repository.findById(cpf)
				.map(record -> {
					record.setNome(cliente.getNome());
					record.setIdade(cliente.getIdade());
					record.setFone(cliente.getFone());
					record.setEmail(cliente.getEmail());
					record.setHotelcalifornia(cliente.getHotelcalifornia());
					Cliente update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	//Deletar cliente específico - http://localhost:8090/cliente/{cpf}
	@DeleteMapping(path = {"/{cpf}"})
	public ResponseEntity delete(@PathVariable String cpf) {
		return repository.findById(cpf)
				.map(record -> {
					repository.deleteById(cpf);
					return ResponseEntity.ok().body("Deletado com Sucesso!");
				}).orElse(ResponseEntity.notFound().build());	
	}	

}
