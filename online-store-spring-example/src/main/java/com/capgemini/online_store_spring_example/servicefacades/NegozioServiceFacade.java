package com.capgemini.online_store_spring_example.servicefacades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.online_store_spring_example.assemblers.impl.NegozioAssembler;
import com.capgemini.online_store_spring_example.assemblers.impl.ProdottoAssembler;
import com.capgemini.online_store_spring_example.commons.DataRelatedException;
import com.capgemini.online_store_spring_example.entities.NegozioEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.services.INegozioService;
import com.capgemini.online_store_spring_example.viewmodels.NegozioVm;
import com.capgemini.online_store_spring_example.viewmodels.ProdottoVm;

@Service
public class NegozioServiceFacade {

	@Autowired
	private NegozioAssembler negozioAssembler;
	
	@Autowired
	private ProdottoAssembler prodottoAssembler;
	
	@Autowired
	private INegozioService negozioService;
	
	public List<NegozioVm> findAll(){
		List<NegozioEntity> entities = negozioService.findAll();
		List<NegozioVm> vms = negozioAssembler.toViewModel(entities);
		return vms;
	}
	
	public NegozioVm findById(final Long id) {
		NegozioEntity entity = negozioService.findById(id);
		NegozioVm vm = negozioAssembler.toViewModel(entity);
		
		return vm;
	}
	
	public List<NegozioVm> findByCittaNomeOrCap(final String cittaNome, final String cittaCap){
		List<NegozioEntity> entities = negozioService.findByCittaNomeOrCap(cittaNome, cittaCap);
		List<NegozioVm> vms = negozioAssembler.toViewModel(entities);
		
		return vms;
	}
	
	public List<ProdottoVm> findProdottiDisponibiliByNegozioId(final Long id){
		List<ProdottoEntity> entities = negozioService.findProdottiDisponibiliByNegozioId(id);
		List<ProdottoVm> vms = prodottoAssembler.toViewModel(entities);
	
		return vms;
	}
	
	public void save(final NegozioVm negozio) throws DataRelatedException{
		NegozioEntity negozioEntity = negozioAssembler.toEntity(negozio); 
		negozioService.save(negozioEntity);
	}
	
}
