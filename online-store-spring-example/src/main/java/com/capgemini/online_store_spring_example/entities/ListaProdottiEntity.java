package com.capgemini.online_store_spring_example.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capgemini.online_store_spring_example.commons.EntityConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Lista_Prodotti")
@EqualsAndHashCode
public class ListaProdottiEntity implements Serializable, IEntity{

	private static final long serialVersionUID = 3616192934408775195L;

	@Getter
	@Setter
	@Id
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = OrdineEntity.class)
	@JoinColumn(name = "Ordine_Id", referencedColumnName = "Ordine_Id", nullable = false)
	private OrdineEntity ordine;
	
	@Getter
	@Setter
	@Id
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProdottoEntity.class)
	@JoinColumn(name = "Prodotto_Id", referencedColumnName = "Prodotto_Id", nullable = false)
	private ProdottoEntity prodotto;
	
	@Getter
	@Setter
	@Column(name = "Quantita", nullable = false, precision = EntityConstants.QUANTITA_PRECISION)
	private Integer quantita;
	
}
