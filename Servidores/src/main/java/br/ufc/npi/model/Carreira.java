package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carreira")
public class Carreira {

	@Id
	@Column(nullable=false, name="cod_carreira")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codCarreira;
	
	@Column(name="classe")
	private String classe;
	
	@Column(name="referencia")
	private String referencia;
	
	@Column(name="nivel")
	private String nivel;
	
	@Column(nullable=false, name="cod_cargo")
	private int codCargo;
}
