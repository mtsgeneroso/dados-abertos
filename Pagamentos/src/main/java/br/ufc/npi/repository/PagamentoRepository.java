package br.ufc.npi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufc.npi.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

	@Query(value="SELECT dt.mes, avg(pg.valor) FROM pagamento AS pg "
			+ "JOIN data AS dt ON pg.id_data = dt.id_data "
			+ "WHERE dt.mes BETWEEN :m1 and :m2 "
			+ "GROUP BY dt.mes "
			+ "ORDER BY dt.mes ASC;", 
			nativeQuery=true)
	public List<Object[]> listByDataBetween(@Param("m1")int m1, @Param("m2")int m2);
	
	
}
