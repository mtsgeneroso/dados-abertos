package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.repository.OrgaoSubordinadoRepository;

@Service
public class OrgaoSubordinadoService {

	@Autowired
	OrgaoSubordinadoRepository orgaoSubordinadoRepository;
	
	public OrgaoSubordinado getById(Long id){
		return orgaoSubordinadoRepository.findOne(id);
	}
	
	public List<OrgaoSubordinado> listByCodOrgaoSuperior(Long codOrgaoSuperior){
		return orgaoSubordinadoRepository.findByCodOrgaoSuperiorOrderByNomeOrgaoSubordinadoAsc(codOrgaoSuperior); 
	}
	
	
}
