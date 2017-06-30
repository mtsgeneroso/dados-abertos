package br.ufc.npi.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

public class TempoId implements Serializable {

	private Tempo ano;
	private Tempo mes;
	
	public TempoId(){
		
	}

	public Tempo getAno() {
		return ano;
	}

	public void setAno(Tempo ano) {
		this.ano = ano;
	}

	public Tempo getMes() {
		return mes;
	}

	public void setMes(Tempo mes) {
		this.mes = mes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
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
		TempoId other = (TempoId) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		return true;
	}
	
	
}
