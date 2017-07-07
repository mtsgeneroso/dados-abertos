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
@Table(name="fato_remuneracao")
@IdClass(FatoRemuneracaoId.class)
public class FatoRemuneracao {

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
	
	@Column(name="remuneracao_basica_bruta_dolar")
	private Double remuneracaoBasicaBrutaDolar;
	
	@Column(name="abate_teto_real")
	private Double abateTetoReal;
	
	@Column(name="abate_teto_dolar")
	private Double abateTetoDolar;
	
	@Column(name="gratificacao_natalina_dolar")
	private Double gratificacaoNatalinaDolar;
	
	@Column(name="gratificacao_natalina_real")
	private Double gratificacaoNatalinaReal;
	
	@Column(name="abate_teto_gratificacao_natal_dolar")
	private Double abateTetoGratificacaoNatalDolar;
	
	@Column(name="abate_teto_gratificacao_natal_real")
	private Double abateTetoGratificacaoNatalReal;
	
	@Column(name="ferias_dolar")
	private Double feriasDolar;
	
	@Column(name="ferias_real")
	private Double feriasReal;
	
	@Column(name="outras_remuneracoes_eventuais_dolar")
	private Double outrasRemuneracoesEventuaisDolar;
	
	@Column(name="outras_remumeracoes_eventuais_real")
	private Double outrasRemuneracoesEventuaisReal;
	
	@Column(name="irrf_dolar")
	private Double irrfDolar;
	
	@Column(name="irrf_real")
	private Double irrfReal;
	
	@Column(name="pss_rpgs_dolar")
	private Double pssRpgsDolar;
	
	@Column(name="pss_rpgs_real")
	private Double pssRpgsReal;
	
	@Column(name="pensao_militar_dolar")
	private Double pensaoMilitarDolar;
	
	@Column(name="pensao_militar_real")
	private Double pensaoMilitarReal;
	
	@Column(name="fundo_de_saude_dolar")
	private Double fundoDeSaudeDolar;
	
	@Column(name="fundo_de_saude_real")
	private Double fundoDeSaudeReal;
	
	@Column(name="demais_deducoes_dolar")
	private Double demaisDeducoesDolar;
	
	@Column(name="demais_deducoes_real")
	private Double demaisDeducoesReal;
	
	@Column(name="remuneracao_apos_deducoes_obrigatorias_dolar")
	private Double remuneracaoAposDeducoesObrigatoriasDolar;
	
	@Column(name="remuneracao_apos_deducoes_obrigatorias_real")
	private Double remuneracaoAposDeducoesObrigatoriasReal;
	
	@Column(name="verbas_indenizatorias_de_pessoal_civil_dolar")
	private Double verbasIndenizatoriasDePessoalCivilDolar;
	
	@Column(name="verbas_indenizatorias_de_pessoal_civil_real")
	private Double verbasIndenizatoriasDePessoalCivilReal;
	
	@Column(name="verbas_indenizatorias_de_pessoal_militar_dolar")
	private Double verbasIndenizatoriasDePessoalMilitarDolar;
	
	@Column(name="verbas_indenizatorias_de_pessoal_militar_real")
	private Double verbasIndenizatoriasDePessoalMilitarReal;
	
	@Column(name="total_de_honorarios_jetons")
	private Double totalDeHonorariosJetons;
	
	@Column(name="remuneracao_basica_bruta_real")
	private Double remuneracaoBasicaBrutaReal;
}
