package br.ufc.npi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.ElementoDespesa;

public interface IElementoDespesaDAO extends JpaRepository<ElementoDespesa, Integer>{

	
	
}
