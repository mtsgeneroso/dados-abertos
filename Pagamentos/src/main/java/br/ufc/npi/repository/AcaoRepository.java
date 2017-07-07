package br.ufc.npi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.model.Acao;

@Repository
@Transactional
public interface AcaoRepository extends JpaRepository<Acao, Long>{
	
}
