package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="grupo_despesa")
public class GrupoDespesa {
	
	@Id
	@Column(nullable=false, name="cod_grupo_despesa")
	private Long codGrupoDespesa;
	@Column(name="nome_grupo_despesa")
	private String nomeGrupoDespesa;
	
	@ManyToMany(mappedBy="grupos", fetch=FetchType.LAZY)
	private List<ElementoDespesa> despesas;
	
	@OneToMany(mappedBy="pagamentos",
			targetEntity=GrupoDespesa.class)
	private List<Pagamento> pagamentos;

	public Long getCodGrupoDespesa() {
		return codGrupoDespesa;
	}

	public void setCodGrupoDespesa(Long codGrupoDespesa) {
		this.codGrupoDespesa = codGrupoDespesa;
	}

	public String getNomeGrupoDespesa() {
		return nomeGrupoDespesa;
	}

	public void setNomeGrupoDespesa(String nomeGrupoDespesa) {
		this.nomeGrupoDespesa = nomeGrupoDespesa;
	}

	public List<ElementoDespesa> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<ElementoDespesa> despesas) {
		this.despesas = despesas;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
}
