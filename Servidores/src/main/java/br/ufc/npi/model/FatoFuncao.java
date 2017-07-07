package br.ufc.npi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fato_funcao")
public class FatoFuncao {

	@Id
	@Column(name="id_servidor_portal")
	private int idServidorPortal;
	
	@Column(name="cod_atividade")
	private int codAtividade;
	
	@Column(name="mes")
	private int mes;
	
	@Column(name="ano")
	private int ano;
	
	@Column(name="cod_uorg_exercicio")
	private int codUorgExercicio;
	
	@Column(name="cod_org_exercicio")
	private int codOrgExercicio;
	
	@Column(name="cod_org_sup_exercicio")
	private int codOrgSupExercicio;
	
	@Column(name="cod_uorg_lotacao")
	private int codUorgLotacao;
	
	@Column(name="cod_org_lotacao")
	private int codOrgLotacao;
	
	@Column(name="cod_org_sup_lotacao")
	private int codOrgSupLotacao;
	
	@Column(name="regime_juridico")
	private String regimeJuridico;
	
	@Column(name="uf_exercicio")
	private String ufExercicio;
	
	@Column(name="jornada_de_trabalho")
	private String jornadaDeTrabalho;
}
