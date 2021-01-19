package com.capgemini.online_store_spring_example.entities;

import java.io.Serializable;

import javax.persistence.Table;

import com.capgemini.online_store_spring_example.entities.composite_keys.DisponibilitaCompositeKey;

import lombok.Data;

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
@Data
public class DisponibilitaEntity implements Serializable, IEntity {

	private static final long serialVersionUID = -5421390300545853603L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = NegozioEntity.class)
	@JoinColumn(name = "Negozio_Id", referencedColumnName = "Negozio_Id", nullable = false)
	private NegozioEntity negozio;

	@Id
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = ProdottoEntity.class)
	@JoinColumn(name = "Prodotto_Id", referencedColumnName = "Prodotto_Id", nullable = false)
	private ProdottoEntity prodotto;

	@Column(name = "disponibile", nullable = false)
	private Boolean disponibile;

}
