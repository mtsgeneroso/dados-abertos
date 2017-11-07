package br.ufc.npi.repository;

import java.util.List;

public interface PagamentoRepositoryCustom {

	public List<Object[]> consultaHierarquica(String sqlQuery);
	
}
