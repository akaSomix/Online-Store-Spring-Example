package com.capgemini.online_store_spring_example.repositories.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.SortedSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.capgemini.online_store_spring_example.config.AppConfig;
import com.capgemini.online_store_spring_example.entities.CategoriaEntity;
import com.capgemini.online_store_spring_example.entities.ProdottoEntity;
import com.capgemini.online_store_spring_example.repositories.CategoriaRepository;
import com.capgemini.online_store_spring_example.repositories.ProdottoRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@TestPropertySource(locations = "classpath:test.properties")
@ContextConfiguration(classes = {AppConfig.class})
public class ProdottoRepositoryTest {
	
	@Autowired
	private ProdottoRepository prodottoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	final static String INIT_DATASET = "/db-unit/prodotto_repository_is.xml";
	
	@Test
	@DatabaseSetup(INIT_DATASET)
	public void findByIdZero() {
		final Optional<ProdottoEntity> entity = prodottoRepository.findById(0l);
		assertFalse(entity.isPresent());
	}
	
	@Test
	@DatabaseSetup(INIT_DATASET)
	public void saveEntitySuccessfullyWithCategoriaIdOne() {
		ProdottoEntity entityTest = new ProdottoEntity();
		
		entityTest.setCodiceProdotto("prd10");
		entityTest.setNome("Prodotto TEST");
		entityTest.setPrezzo(120.3);
		entityTest.setDescrizione("Empty");
		
		Optional<CategoriaEntity> categoria = categoriaRepository.findById(1l);
		assertTrue(categoria.isPresent());
		entityTest.setCategoria(categoria.get());
		
		long sizeBeforeSaving = prodottoRepository.count(); 
				
		prodottoRepository.saveAndFlush(entityTest);
		
		assertTrue(sizeBeforeSaving < prodottoRepository.count() );	
		
		log.warn("ID DEL NUOVO PRODOTTO " + prodottoRepository.findById(1l).get().getProdottoId());
	}
	
	@Test
	@DatabaseSetup(INIT_DATASET)
	public void findAllNegozioCodiceTest() {
		SortedSet<String> founds = prodottoRepository.findAllProdottoCodice();
		
		assertTrue(founds.size() > 0);
		assertTrue(founds.contains("prd1"));
	}
}
