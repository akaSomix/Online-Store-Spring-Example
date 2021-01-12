package com.capgemini.online_store_spring_example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.online_store_spring_example.entities.DisponibilitaEntity;
import com.capgemini.online_store_spring_example.entities.composite_keys.DisponibilitaCompositeKey;

@Repository
public interface DisponibilitaRepository extends JpaRepository<DisponibilitaEntity, DisponibilitaCompositeKey>{

}
