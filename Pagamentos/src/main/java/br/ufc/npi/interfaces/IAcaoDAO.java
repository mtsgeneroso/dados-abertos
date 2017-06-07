package br.ufc.npi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.Acao;

public interface IAcaoDAO extends JpaRepository<Acao, Integer>{
	
	
}
