package br.ufc.npi.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PagamentoRepositoryImpl implements PagamentoRepositoryCustom{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Object[]> consultaHierarquica(String sqlQuery) {
		
		return manager.createNativeQuery(sqlQuery).getResultList();
		
	}

	
}
