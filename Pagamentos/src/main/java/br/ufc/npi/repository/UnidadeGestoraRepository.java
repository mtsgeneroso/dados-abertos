package br.ufc.npi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.model.UnidadeGestora;

@Repository
@Transactional
public interface UnidadeGestoraRepository extends JpaRepository<UnidadeGestora, Long>{
	
	public List<UnidadeGestora> findByCodOrgaoSubordinado(Long codOrgaoSubordinado);
	
}
