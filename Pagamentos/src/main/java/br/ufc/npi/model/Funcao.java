package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="funcao")
public class Funcao {
	
		private String nome_funcao; 
		@Id
		@Column(nullable=false)
		private int cod_funcao;
		
		@ManyToMany
		@JoinTable(name="funcao_subfuncao",
			joinColumns=@JoinColumn(name="cod_funcao",
									referencedColumnName="cod_funcao"),
			inverseJoinColumns=@JoinColumn(name="cod_subfuncao",
										   referencedColumnName="cod_subfuncao")
				  )
		private List<Subfuncao> subfuncoes;

		
		public String getNome_funcao() {
			return nome_funcao;
		}
		public void setNome_funcao(String nome_funcao) {
			this.nome_funcao = nome_funcao;
		}
		public int getCod_funcao() {
			return cod_funcao;
		}
		public void setCod_funcao(int cod_funcao) {
			this.cod_funcao = cod_funcao;
		}


}
