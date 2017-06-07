package br.ufc.npi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.Favorecido;

public interface IFavorecidoDAO extends JpaRepository<Favorecido, Integer>{

	
	
}
