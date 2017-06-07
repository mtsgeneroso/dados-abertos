package br.ufc.npi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.Data;

public interface IDataDAO extends JpaRepository<Data, Integer> {

	
	
}
