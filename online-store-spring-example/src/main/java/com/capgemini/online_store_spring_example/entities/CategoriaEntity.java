package com.capgemini.online_store_spring_example.entities;

import javax.persistence.Table;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "Categoria")
public class CategoriaEntity implements Serializable, IEntity{

	private static final long serialVersionUID = -3020776082880391298L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "CategoriaEntityIdGenerator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "CategoriaEntityIdGenerator", sequenceName = "SQ_ID_CATEGORIA", initialValue = EntityConstants.SEQUENCE_INIT_VALUE, allocationSize = EntityConstants.SEQUENCE_ALLOCATION_SIZE)	
	@Column(name = "Categoria_Id", precision = EntityConstants.CATEGORIA_ENTITY_ID_PRECISION)
	private Long categoriaId;
	
	@Getter
	@Setter
	@Column(name = "Nome", unique = true, nullable = false, length = EntityConstants.CATEGORIA_ENTITY_NOME_LENGTH)
	private String nome;

	@Getter
	@Setter
	@Column(name = "Descrizione", nullable = false, length = EntityConstants.CATEGORIA_ENTITY_DESCRIZIONE_LENGTH)
	private String descrizione;
	
}
