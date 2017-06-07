package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="favorecido")
public class Favorecido {
	
	@Id
	@Column(nullable=false, name="id_favorecido")
	private int idFavorecido;
	@Column(name="cod_favorecido")
	private int codFavorecido; 
	@Column(name="nome_favorecido")
	private String nomeFavorecido;
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=Favorecido.class)
	private List<Pagamento> pagamentos;
	
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	public int getIdFavorecido() {
		return idFavorecido;
	}
	public void setIdFavorecido(int idFavorecido) {
		this.idFavorecido = idFavorecido;
	}
	public int getCodFavorecido() {
		return codFavorecido;
	}
	public void setCodFavorecido(int codFavorecido) {
		this.codFavorecido = codFavorecido;
	}
	public String getNomeFavorecido() {
		return nomeFavorecido;
	}
	public void setNomeFavorecido(String nomeFavorecido) {
		this.nomeFavorecido = nomeFavorecido;
	}
	

}
