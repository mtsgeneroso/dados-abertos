package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="data")
public class Data {
	
	@Id
	@Column(name="cod_data",
	nullable=false)
	private Long codData;
	
	private int dia;
	private int mes; 
	private int ano;
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=Data.class)
	private List<Pagamento> pagamentos;

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Long getCodData() {
		return codData;
	}

	public void setCodData(Long codData) {
		this.codData = codData;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
}
