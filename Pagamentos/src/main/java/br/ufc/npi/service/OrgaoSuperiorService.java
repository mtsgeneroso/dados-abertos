package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.repository.OrgaoSuperiorRepository;

@Service
public class OrgaoSuperiorService {

	@Autowired
	private OrgaoSuperiorRepository orgaoSuperiorRepository;
	
	public List<OrgaoSuperior> findAll(){
		return orgaoSuperiorRepository.findAll();
	}
	
}
