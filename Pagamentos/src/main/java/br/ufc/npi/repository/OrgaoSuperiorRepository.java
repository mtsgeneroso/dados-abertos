package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;

@Repository
public interface OrgaoSuperiorRepository extends JpaRepository<OrgaoSuperior, Integer>{

	public List<OrgaoSuperior> findByOrderByNomeOrgaoSuperiorAsc();
	
}
