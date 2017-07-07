package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="observacao")
@IdClass(ObservacaoId.class)
public class Observacao {

	@Id
	@Column(insertable=false, updatable=false,nullable=false, name="id_servidor_portal")
	private int idServidorPortal;

	@ManyToOne(optional=false)
	@JoinColumn(name="id_servidor_portal", referencedColumnName="id_servidor_portal")
	private Servidor servidor;
	
	@Id
	@Column(insertable=false, updatable=false, nullable=false, name="ano")
	private int ano;
	
	@Id
	@Column(insertable=false, updatable=false, nullable=false, name="mes")
	private int mes;
	
	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="ano", referencedColumnName="ano"),
		@JoinColumn(name="mes", referencedColumnName="mes")
	})
	private Tempo tempoAnoMes;
	
	@Column(name="descricao")
	private String descricao;
	
	
}
