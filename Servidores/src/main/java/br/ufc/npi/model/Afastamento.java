package br.ufc.npi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="afastamento")
@IdClass(AfastamentoId.class)
public class Afastamento {
	
	@Id
	@Column(insertable=false, updatable=false,nullable=false, name="id_servidor_portal")
	private int idServidorPortal;

	@ManyToOne(optional=false)
	@JoinColumn(name="id_servidor_portal", referencedColumnName="id_servidor_portal")
	private Servidor servidor;
	
	@Id
	@Column(insertable=false, updatable=false, nullable=false, name="ano")
	private int ano;
	
	@Id
	@Column(insertable=false, updatable=false, nullable=false, name="mes")
	private int mes;
	
	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="ano", referencedColumnName="ano"),
		@JoinColumn(name="mes", referencedColumnName="mes")
	})
	private Tempo tempoAnoMes;
	
	@Column(name="data_inicio")
	Date dataInicio;
	
	@Column(name="data_fim")
	Date dataFim;

	public int getIdServidorPortal() {
		return idServidorPortal;
	}

	public void setIdServidorPortal(int idServidorPortal) {
		this.idServidorPortal = idServidorPortal;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public Tempo getTempoAnoMes() {
		return tempoAnoMes;
	}

	public void setTempoAnoMes(Tempo tempoAnoMes) {
		this.tempoAnoMes = tempoAnoMes;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + idServidorPortal;
		result = prime * result + mes;
		result = prime * result + ((servidor == null) ? 0 : servidor.hashCode());
		result = prime * result + ((tempoAnoMes == null) ? 0 : tempoAnoMes.hashCode());
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
		Afastamento other = (Afastamento) obj;
		if (ano != other.ano)
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (idServidorPortal != other.idServidorPortal)
			return false;
		if (mes != other.mes)
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		if (tempoAnoMes == null) {
			if (other.tempoAnoMes != null)
				return false;
		} else if (!tempoAnoMes.equals(other.tempoAnoMes))
			return false;
		return true;
	}
	
	
}
