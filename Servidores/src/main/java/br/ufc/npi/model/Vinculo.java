package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vinculo")
public class Vinculo {

	@Id
	@Column(name="cod_vinculo")
	private int codVinculo;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="situacao")
	private String situacao;
}
