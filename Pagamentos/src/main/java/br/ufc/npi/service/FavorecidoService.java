package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.Favorecido;
import br.ufc.npi.repository.FavorecidoRepository;

@Service
public class FavorecidoService {

	@Autowired
	private FavorecidoRepository favorecidoRepository;
	
	public List<Favorecido> findAll(){
		return favorecidoRepository.findByOrderByNomeFavorecidoAsc();
	}
	
}
