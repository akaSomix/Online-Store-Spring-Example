package com.capgemini.online_store_spring_example.entities;

import java.io.Serializable;

import javax.persistence.Table;

import com.capgemini.online_store_spring_example.commons.EntityConstants;
import com.capgemini.online_store_spring_example.entities.composite_keys.DisponibilitaCompositeKey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(DisponibilitaCompositeKey.class)
@Table(name = "Disponibilita")
@EqualsAndHashCode
public class DisponibilitaEntity implements Serializable, IEntity {

	private static final long serialVersionUID = 476929501606889268L;

	@Getter
	@Setter
	@Column(name = "Quantita", nullable = false, precision = EntityConstants.QUANTITA_PRECISION)
	private Integer quantita;
	
	@Getter
	@Setter
	@Id
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = NegozioEntity.class)
	@JoinColumn(name = "Negozio_Id", referencedColumnName = "Negozio_Id", nullable = false)
	private NegozioEntity negozio;
	
	@Getter
	@Setter
	@Id
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ProdottoEntity.class)
	@JoinColumn(name = "Prodotto_Id", referencedColumnName = "Prodotto_Id", nullable = false)
	private ProdottoEntity prodotto;

}
