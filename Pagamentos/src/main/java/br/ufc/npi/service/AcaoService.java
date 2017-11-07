package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.Acao;
import br.ufc.npi.repository.AcaoRepository;

@Service
public class AcaoService {

	@Autowired
	private AcaoRepository acaoRepository;
	
	public List<Acao> findAll(){
		return acaoRepository.findByOrderByNomeAcaoAsc();
	}
	
}
