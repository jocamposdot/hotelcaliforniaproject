package com.hotelcalifornia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Construtor com todos argumentos:
@AllArgsConstructor
//Construtor vazio, sem argumentos:
@NoArgsConstructor
//Cria implicitamente os Getters e Setters, ToString, EqualsAndHashCode, e o RequiredArgsConstructor:
@Data
//Annotation do JPA que faz a classe espelhar/persistir com a entidade do banco de dados que faz referência:
@Entity
public class Hotelcalifornia {
	
	//Estou dizendo ao JPA que este é o ID da tabela
	@Id
	//Aqui eu informo que a geração do valor do ID será gerenciado pelo provedor de persistência, ou seja, pelo auto incremento do banco de dados:
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricula;

	private String nome;
	private String valorDiaria;
	private	String cidade;
	private float estrelas;
	

	//Mapeando com cliente -> Um hotel para vários clientes (1 para N):
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy = "hotelcalifornia")
	//Aqui ele recebe uma lista por que podem vir muitos objetos de cliente
	//Utilizei o JasonManagedReference para evitar recursividade infinita
	@JsonManagedReference
	private List<Cliente> clientes;  
	
}
