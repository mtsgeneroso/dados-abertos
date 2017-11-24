package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.Favorecido;

public interface FavorecidoRepository extends JpaRepository<Favorecido, String>{

	public List<Favorecido> findByOrderByNomeFavorecidoAsc();
	
}
