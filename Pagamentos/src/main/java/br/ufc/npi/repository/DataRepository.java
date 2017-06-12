package br.ufc.npi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.Data;

public interface DataRepository extends JpaRepository<Data, Integer> {

	
	
}
