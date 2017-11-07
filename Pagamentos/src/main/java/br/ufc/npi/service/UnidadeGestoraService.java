package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.repository.UnidadeGestoraRepository;

@Service
public class UnidadeGestoraService {

	@Autowired
	private UnidadeGestoraRepository unidadeGestoraRepository;

	public List<UnidadeGestora> findAll(){
		return unidadeGestoraRepository.findByOrderByNomeUnidadeGestoraAsc();
	}

	public UnidadeGestora getById(Long id){
		return unidadeGestoraRepository.findOne(id);
	}
	
	public List<UnidadeGestora> findByCodOrgaoSubordinado(Long codOrgaoSubordinado){
		return unidadeGestoraRepository.findByCodOrgaoSubordinadoOrderByNomeUnidadeGestoraAsc(codOrgaoSubordinado);
	}
	
}
