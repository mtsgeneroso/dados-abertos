package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.Programa;
import br.ufc.npi.repository.ProgramaRepository;

@Service
public class ProgramaService {

	@Autowired
	private ProgramaRepository programaRepository;
	
	public List<Programa> findAll(){
		return programaRepository.findByOrderByNomeProgramaAsc();
	}
	
}
