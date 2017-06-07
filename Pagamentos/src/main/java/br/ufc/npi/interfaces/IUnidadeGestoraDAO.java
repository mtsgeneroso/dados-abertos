package br.ufc.npi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.UnidadeGestora;

public interface IUnidadeGestoraDAO extends JpaRepository<UnidadeGestora, Integer>{

}
