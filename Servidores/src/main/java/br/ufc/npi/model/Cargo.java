package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cargo")
public class Cargo {

	@Id
	@Column(nullable=false, name="cod_cargo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codCargo;
	
	@Column(name="descricao_cargo")
	private String descricaoCargo;
}
