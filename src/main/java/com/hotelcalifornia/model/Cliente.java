package com.hotelcalifornia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class Cliente {
	
	//Estou dizendo ao JPA que este é o ID da tabela
	@Id
	private String cpf;
	
	private String nome;
	private int idade;
	private String fone;  
	private String email;
	
	//Sinalizando para o JPA que vários clientes vão estar relacionados com um hotel (muitos-para-1):
	@ManyToOne	
	@JsonIgnore
	//Sinalizando ao JPA que a coluna FK de cliente (matricula_id) será a responsável pelo mapeamento entre Cliente e Hotelcalifornia:
	@JoinColumn(name="matricula_id", nullable = true)
	private Hotelcalifornia hotelcali;  

}
