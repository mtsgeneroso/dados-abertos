package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orgao")
public class Orgao {

	@Id
	@Column(name="cod_orgao")
	private int codOrgao;
	
	@Column(name="descricao")
	private String descricao;
}
