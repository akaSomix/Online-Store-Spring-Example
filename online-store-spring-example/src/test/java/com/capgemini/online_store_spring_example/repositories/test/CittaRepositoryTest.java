package com.capgemini.online_store_spring_example.repositories.test;

import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.capgemini.online_store_spring_example.config.AppConfig;
import com.capgemini.online_store_spring_example.entities.CittaEntity;
import com.capgemini.online_store_spring_example.repositories.CittaRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@TestPropertySource(locations = "classpath:test.properties")
@ContextConfiguration(classes = {AppConfig.class})
public class CittaRepositoryTest {

	@Autowired
	CittaRepository cittaRepository;
	
	final static String INIT_DATASET = "/db-unit/citta_repository_is.xml";
	
	@Test
	@DatabaseSetup(INIT_DATASET)
	public void findByIdZero() {
		final Optional<CittaEntity> cittaEntity = cittaRepository.findById(0l);
		assertFalse(cittaEntity.isPresent());
	}
}
