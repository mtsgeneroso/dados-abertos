package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufc.npi.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

	@Query(value="SELECT dt.mes, sum(pg.valor) FROM pagamento AS pg "
			+ "JOIN data AS dt ON pg.id_data = dt.id_data "
			+ "JOIN acao AS ac ON pg.id_acao = ac.id_acao "
			+ "JOIN acao_unidade_gestora AS acug ON ac.id_acao = acug.id_acao "
			+ "JOIN unidade_gestora AS ug ON acug.cod_unidade_gestora = ug.cod_unidade_gestora "
			+ "JOIN orgao_subordinado AS orgsub ON ug.cod_orgao_subordinado = orgsub.cod_orgao_subordinado "
			+ "JOIN orgao_superior AS orgsup ON orgsub.cod_orgao_superior = orgsup.cod_orgao_superior "
			+ "GROUP BY dt.mes "
			+ "ORDER BY dt.mes ASC;", 
			nativeQuery=true)
	public List<Object[]> listByDataBetween();
	
	
	@Query(value="SELECT dt.mes, sum(pg.valor) FROM pagamento AS pg "
			+ "JOIN data AS dt ON pg.id_data = dt.id_data "
			+ "JOIN acao AS ac ON pg.id_acao = ac.id_acao "
			+ "JOIN acao_unidade_gestora AS acug ON ac.id_acao = acug.id_acao "
			+ "JOIN unidade_gestora AS ug ON acug.cod_unidade_gestora = ug.cod_unidade_gestora "
			+ "JOIN orgao_subordinado AS orgsub ON ug.cod_orgao_subordinado = orgsub.cod_orgao_subordinado "
			+ "JOIN orgao_superior AS orgsup ON orgsub.cod_orgao_superior = orgsup.cod_orgao_superior "
			+ "WHERE orgsup.cod_orgao_superior = :cod "
			+ "GROUP BY dt.mes "
			+ "ORDER BY dt.mes ASC;", 
			nativeQuery=true)
	public List<Object[]> listPagamentosOrgSuperiorByDataBetween(@Param("cod")Long codOrgSuperior);
	
	@Query(value="SELECT dt.mes, sum(pg.valor) FROM pagamento AS pg "
			+ "JOIN data AS dt ON pg.id_data = dt.id_data "
			+ "JOIN acao AS ac ON pg.id_acao = ac.id_acao "
			+ "JOIN acao_unidade_gestora AS acug ON ac.id_acao = acug.id_acao "
			+ "JOIN unidade_gestora AS ug ON acug.cod_unidade_gestora = ug.cod_unidade_gestora "
			+ "JOIN orgao_subordinado AS orgsub ON ug.cod_orgao_subordinado = orgsub.cod_orgao_subordinado "
			+ "JOIN orgao_superior AS orgsup ON orgsub.cod_orgao_superior = orgsup.cod_orgao_superior "
			+ "WHERE orgsub.cod_orgao_subordinado = :cod "
			+ "GROUP BY dt.mes "
			+ "ORDER BY dt.mes ASC;", 
			nativeQuery=true)
	public List<Object[]> listPagamentosOrgSubordinadoByDataBetween(@Param("cod")Long codOrgSuperior);
	
	@Query(value="SELECT dt.mes, sum(pg.valor) FROM pagamento AS pg "
			+ "JOIN data AS dt ON pg.id_data = dt.id_data "
			+ "JOIN acao AS ac ON pg.id_acao = ac.id_acao "
			+ "JOIN acao_unidade_gestora AS acug ON ac.id_acao = acug.id_acao "
			+ "JOIN unidade_gestora AS ug ON acug.cod_unidade_gestora = ug.cod_unidade_gestora "
			+ "JOIN orgao_subordinado AS orgsub ON ug.cod_orgao_subordinado = orgsub.cod_orgao_subordinado "
			+ "JOIN orgao_superior AS orgsup ON orgsub.cod_orgao_superior = orgsup.cod_orgao_superior "
			+ "WHERE dt.mes BETWEEN :m1 and :m2 "
			+ "AND ug.cod_unidade_gestora = :cod "
			+ "GROUP BY dt.mes "
			+ "ORDER BY dt.mes ASC;", 
			nativeQuery=true)
	public List<Object[]> listPagamentosUnidadeGestoraByDataBetween(@Param("cod")Long codUnidadeGestora);
	
}
