package br.ufc.npi.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity(name="elemento_despesa")
public class ElementoDespesa {

	private String nome_elemento_despesa; 
	@Id
	@Column(nullable=false)
	private int cod_elemento_despesa;
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=ElementoDespesa.class)
	List<Pagamento> pagamentos;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="elemento_despesa_grupo_despesa",
		joinColumns=@JoinColumn(name="cod_elemento_despesa",
								referencedColumnName="cod_elemento_despesa"),
		inverseJoinColumns=@JoinColumn(name="cod_grupo_despesa",
									   referencedColumnName="cod_grupo_despesa")
			  )
	private List<GrupoDespesa> grupos;
	
	public String getNome_elemento_despesa() {
		return nome_elemento_despesa;
	}
	public void setNome_elemento_despesa(String nome_elemento_despesa) {
		this.nome_elemento_despesa = nome_elemento_despesa;
	}
	public int getCod_elemento_despesa() {
		return cod_elemento_despesa;
	}
	public void setCod_elemento_despesa(int cod_elemento_despesa) {
		this.cod_elemento_despesa = cod_elemento_despesa;
	}	
	

}
