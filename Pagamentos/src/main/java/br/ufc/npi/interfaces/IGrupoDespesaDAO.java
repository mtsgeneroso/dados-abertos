package br.ufc.npi.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.npi.model.GrupoDespesa;

public interface IGrupoDespesaDAO extends JpaRepository<GrupoDespesa, Integer>{

}
