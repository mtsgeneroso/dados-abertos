package br.ufc.npi.model;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="elemento_despesa")
public class ElementoDespesa {
	
	@Column(name="nome_elemento_despesa")
	private String nomeElementoDespesa; 
	@Id
	@Column(nullable=false,name="cod_elemento_despesa")
	private int codElementoDespesa;

	@OneToMany(mappedBy="pagamentos",
			   targetEntity=ElementoDespesa.class)
	private List<Pagamento> pagamentos;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="elemento_despesa_grupo_despesa",
		joinColumns=@JoinColumn(name="cod_elemento_despesa",
								referencedColumnName="cod_elemento_despesa"),
		inverseJoinColumns=@JoinColumn(name="cod_grupo_despesa",
									   referencedColumnName="cod_grupo_despesa")
			  )
	private List<GrupoDespesa> grupos;
	
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	public List<GrupoDespesa> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<GrupoDespesa> grupos) {
		this.grupos = grupos;
	}
	public String getNomeElementoDespesa() {
		return nomeElementoDespesa;
	}
	public void setNomeElementoDespesa(String nomeElementoDespesa) {
		this.nomeElementoDespesa = nomeElementoDespesa;
	}
	public int getCodElementoDespesa() {
		return codElementoDespesa;
	}
	public void setCodElementoDespesa(int codElementoDespesa) {
		this.codElementoDespesa = codElementoDespesa;
	}
	

}
