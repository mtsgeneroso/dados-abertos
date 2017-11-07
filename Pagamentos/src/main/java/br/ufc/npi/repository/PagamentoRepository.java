package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufc.npi.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>, PagamentoRepositoryCustom{

	@Query(value="SELECT dt.mes, CAST(sum(pg.valor) as FLOAT) FROM pagamento AS pg, data AS dt "
			+ "WHERE pg.cod_data = dt.cod_data "
			+ "GROUP BY dt.mes "
			+ "ORDER BY dt.mes ASC", 
			nativeQuery=true)
	public List<Object[]> listByDataBetween();
	
	
	@Query(value="SELECT dt.mes, CAST(sum(pg.valor) as FLOAT) "
			+ "FROM pagamento AS pg, data dt "
			+ "WHERE pg.cod_data = dt.cod_data AND pg.cod_orgao_superior = :cod "
			+ "GROUP BY pg.cod_orgao_superior, dt.mes "
			+ "ORDER BY dt.mes ASC", 
			nativeQuery=true)
	public List<Object[]> listPagamentosOrgSuperiorByDataBetween(@Param("cod")Long codOrgSuperior);
	
	@Query(value="SELECT dt.mes, CAST(sum(pg.valor) as FLOAT) "
			+ "FROM pagamento AS pg, data dt "
			+ "WHERE pg.cod_data = dt.cod_data AND pg.cod_orgao_subordinado = :cod "
			+ "GROUP BY pg.cod_orgao_subordinado, dt.mes "
			+ "ORDER BY dt.mes ASC", 
			nativeQuery=true)
	public List<Object[]> listPagamentosOrgSubordinadoByDataBetween(@Param("cod")Long codOrgSuperior);
	
	@Query(value="SELECT dt.mes, CAST(sum(pg.valor) as FLOAT) "
			+ "from data dt, pagamento pg "
			+ "WHERE pg.cod_data = dt.cod_data AND pg.cod_unidade_gestora = :cod "
			+ "GROUP BY pg.cod_unidade_gestora, dt.mes "
			+ "ORDER BY dt.mes ASC", 
			nativeQuery=true)
	public List<Object[]> listPagamentosUnidadeGestoraByDataBetween(@Param("cod")Long codUnidadeGestora);
	
}
