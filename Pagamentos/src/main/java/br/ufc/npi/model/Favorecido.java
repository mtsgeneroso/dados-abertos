package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="favorecido")
public class Favorecido {
	
	@Id
	@Column(nullable=false)
	private int id_favorecido;
	private int cod_favorecido; 
	private String nome_favorecido;
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=Favorecido.class)
	List<Pagamento> pagamentos;
	
	public int getId_favorecido() {
		return id_favorecido;
	}
	public void setId_favorecido(int id_favorecido) {
		this.id_favorecido = id_favorecido;
	}
	public int getCod_favorecido() {
		return cod_favorecido;
	}
	public void setCod_favorecido(int cod_favorecido) {
		this.cod_favorecido = cod_favorecido;
	}
	public String getNome_favorecido() {
		return nome_favorecido;
	}
	public void setNome_favorecido(String nome_favorecido) {
		this.nome_favorecido = nome_favorecido;
	} 
	

}
