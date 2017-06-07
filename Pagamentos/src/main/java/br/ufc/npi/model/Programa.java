package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="programa")
public class Programa {
	
	@Id
	@Column(nullable=false, name="cod_programa")
	private int codPrograma;
	@Column(name="nome_programa")
	private String nomePrograma;
	
	@ManyToMany(mappedBy="programas")
	private List<Acao> acoes;
	

}
