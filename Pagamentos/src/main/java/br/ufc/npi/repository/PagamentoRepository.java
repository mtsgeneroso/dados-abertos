package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufc.npi.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

	@Query(value="SELECT mes, sum FROM view_pagamentos_gerais;", 
			nativeQuery=true)
	public List<Object[]> listByDataBetween();
	
	
	@Query(value="SELECT mes,sum FROM view_pagamentos_orgaosuperior WHERE cod_org_sup = :cod", 
			nativeQuery=true)
	public List<Object[]> listPagamentosOrgSuperiorByDataBetween(@Param("cod")Long codOrgSuperior);
	
	@Query(value="SELECT mes,sum FROM view_pagamentos_orgsubordinado WHERE cod_org_sub = :cod", 
			nativeQuery=true)
	public List<Object[]> listPagamentosOrgSubordinadoByDataBetween(@Param("cod")Long codOrgSuperior);
	
	@Query(value="SELECT mes,sum FROM view_pagamentos_unidadegestora WHERE cod_unid_gest = :cod", 
			nativeQuery=true)
	public List<Object[]> listPagamentosUnidadeGestoraByDataBetween(@Param("cod")Long codUnidadeGestora);
	
}
