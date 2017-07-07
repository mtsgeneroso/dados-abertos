package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ufc.npi.model.OrgaoSubordinado;

@Repository
@Transactional
public interface OrgaoSubordinadoRepository extends JpaRepository<OrgaoSubordinado, Long> {
	
	public List<OrgaoSubordinado> findByCodOrgaoSuperiorOrderByNomeOrgaoSubordinadoAsc(Long codOrgaoSuperior);
	
}
