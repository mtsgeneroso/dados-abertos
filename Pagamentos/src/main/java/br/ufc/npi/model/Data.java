package br.ufc.npi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="data")
public class Data {
	
	private int dia;
	private int mes; 
	private int ano;
	@Id
	@Column(nullable=false)
	private int id_data;
	
	@OneToMany(mappedBy="pagamentos",
			   targetEntity=Data.class)
	private List<Pagamento> pagamentos;
	
	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
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
	public int getId_data() {
		return id_data;
	}
	public void setId_data(int id_data) {
		this.id_data = id_data;
} 

}
