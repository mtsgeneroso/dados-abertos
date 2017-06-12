package br.ufc.npi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.Acao;

public interface AcaoRepository extends JpaRepository<Acao, Integer>{
	
	
}
