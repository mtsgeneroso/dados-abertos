package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.model.OrgaoSubordinado;

@Repository
public interface OrgaoSubordinadoRepository extends JpaRepository<OrgaoSubordinado, Integer> {

	public List<OrgaoSubordinado> findByCodOrgaoSuperiorLike(Long codOrgaoSuperior);
	
}
