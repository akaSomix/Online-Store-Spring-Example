package com.capgemini.online_store_spring_example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Citta", uniqueConstraints = @UniqueConstraint(columnNames = {"Nome", "CAP"}))
public class CittaEntity implements Serializable, IEntity{

	private static final long serialVersionUID = -2915105249512643154L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "CittaEntityIdGenerator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "CittaEntityIdGenerator", sequenceName = "SQ_ID_CITTA", initialValue = EntityConstants.SEQUENCE_INIT_VALUE, allocationSize = EntityConstants.SEQUENCE_ALLOCATION_SIZE)
	@Column(name = "citta_id", nullable = false, precision = EntityConstants.CITTA_ENTITY_ID_PRECISION)
	private Long cittaId;
	
	@Getter
	@Setter
	@Column(name = "Nome", nullable= false, length = EntityConstants.CITTA_ENTITY_NOME_LENGTH)
	private String nome;
	
	@Getter
	@Setter
	@Column(name= "CAP", nullable = false, length = EntityConstants.CITTA_ENTITY_CAP_LENGTH)
	private String cap;
	
	@Getter
	@Setter
	@Column(name = "Stato", nullable = false, length = EntityConstants.CITTA_ENTITY_STATO_LENGTH)
	private String stato;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CittaEntity.class)
	@JoinColumn(name = "Provincia_Id", nullable = true, referencedColumnName = "Citta_Id")
	private CittaEntity provincia;
}
