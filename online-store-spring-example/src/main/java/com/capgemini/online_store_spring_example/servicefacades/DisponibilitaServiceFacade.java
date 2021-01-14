package com.capgemini.online_store_spring_example.servicefacades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.assemblers.impl.DisponibilitaAssembler;
import com.capgemini.online_store_spring_example.assemblers.impl.NegozioAssembler;
import com.capgemini.online_store_spring_example.assemblers.impl.ProdottoAssembler;
import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.services.IDisponibilitaService;
import com.capgemini.online_store_spring_example.viewmodels.DisponibilitaVm;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;
import com.capgemini.online_store_spring_example.viewmodels.ProdottoVm;

@Service
public class DisponibilitaServiceFacade {

	@Autowired
	private IDisponibilitaService disponibilitaService;
	
	@Autowired 
	private DisponibilitaAssembler disponibilitaAssembler;
	
	@Autowired
	private NegozioAssembler negozioAssembler;
	
	@Autowired
	private ProdottoAssembler prodottoAssembler;
	
	public void save(final DisponibilitaVm disponibilitaVm)throws DataRelatedException{
		DisponibilitaEntity disponibilitaEntity = disponibilitaAssembler.toEntity(disponibilitaVm);
		disponibilitaService.save(disponibilitaEntity);
	}
	
	public void deleteByNegozio(final NegozioVm negozioVm)throws DataRelatedException{
		NegozioEntity negozio = negozioAssembler.toEntity(negozioVm);
		disponibilitaService.deleteByNegozio(negozio);
	}
	
	public void deleteByProdotto(final ProdottoVm prodottoVm)throws DataRelatedException{
		ProdottoEntity prodotto = prodottoAssembler.toEntity(prodottoVm);
		disponibilitaService.deleteByProdotto(prodotto);
	}
	
}
