package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="servidor")
public class Servidor {
	
	@Id
	@Column(nullable=false, name="id_servidor_portal")
	private int idServidorPortal;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="matricula")
	private String matricula;

	@OneToMany(mappedBy="afastamentos", targetEntity=Servidor.class)
	private List<Afastamento> afastamentos;
	
	@OneToMany(mappedBy="observacoes", targetEntity=Servidor.class)
	private List<Observacao> observacoes;
	
	@OneToMany(mappedBy="honorarios", targetEntity=Servidor.class)
	private List<Honorario> honorarios;
	
	@OneToMany(mappedBy="remuneracoes", targetEntity=Servidor.class)
	private List<FatoRemuneracao> remuneracoes;
	
	public List<Observacao> getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(List<Observacao> observacoes) {
		this.observacoes = observacoes;
	}

	public List<Afastamento> getAfastamentos() {
		return afastamentos;
	}

	public void setAfastamentos(List<Afastamento> afastamentos) {
		this.afastamentos = afastamentos;
	}

	public int getIdServidorPortal() {
		return idServidorPortal;
	}

	public void setIdServidorPortal(int idServidorPortal) {
		this.idServidorPortal = idServidorPortal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + idServidorPortal;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servidor other = (Servidor) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (idServidorPortal != other.idServidorPortal)
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
