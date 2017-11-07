package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.Programa;

public interface ProgramaRepository extends JpaRepository<Programa, Long>{

	public List<Programa> findByOrderByNomeProgramaAsc();
	
}
