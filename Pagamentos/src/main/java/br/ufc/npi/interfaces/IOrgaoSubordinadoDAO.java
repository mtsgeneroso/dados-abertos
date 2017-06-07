package br.ufc.npi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.OrgaoSubordinado;

public interface IOrgaoSubordinadoDAO extends JpaRepository<OrgaoSubordinado, Integer> {

	
	
}
