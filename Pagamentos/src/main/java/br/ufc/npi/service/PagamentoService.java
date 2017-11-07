package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.Pagamento;
import br.ufc.npi.model.request.ConsultaHierarquicaObj;
import br.ufc.npi.model.request.Dado;
import br.ufc.npi.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;	
	
	public List<Pagamento> findAll(){
		return pagamentoRepository.findAll();
	}
	
	public List<Object[]> findByMonths(){
		return pagamentoRepository.listByDataBetween();
	}
	
	public List<Object[]> findPagamentosOrgSuperiorByMonths(Long codigoOrgSuperior){
		return pagamentoRepository.listPagamentosOrgSuperiorByDataBetween(codigoOrgSuperior);
	}
	
	public List<Object[]> findPagamentosOrgSubordinadoByMonths(Long codigoOrgSubordinado){
		return pagamentoRepository.listPagamentosOrgSubordinadoByDataBetween(codigoOrgSubordinado);
	}
	
	public List<Object[]> findPagamentosUnidadeGestoraByMonths(Long codigoUnidadeGestora){
		return pagamentoRepository.listPagamentosUnidadeGestoraByDataBetween(codigoUnidadeGestora);
	}	
	
	public List<Object[]> findPagamentosByMonths(String orgaoTipo, Long codOrgao){
		if(orgaoTipo.equals(Dado.ORGAO_SUPERIOR)){
			return this.findPagamentosOrgSuperiorByMonths(codOrgao);
		}
		else if(orgaoTipo.equals(Dado.ORGAO_SUBORDINADO)){
			return this.findPagamentosOrgSubordinadoByMonths(codOrgao);
		}
		else if(orgaoTipo.equals(Dado.UNIDADE_GESTORA)){
			return this.findPagamentosUnidadeGestoraByMonths(codOrgao);
		}
		return null;
	}
	
	public List<Object[]> pagamentosConsultaHierarquica(ConsultaHierarquicaObj obj){
		
		String sqlQuery = "SELECT ";
		
		for(String h : obj.getHierarquia()){
			sqlQuery += String.format("%s.nome_%s, ", h, h);
		}
		
		sqlQuery += " CAST(SUM(pg.valor) AS FLOAT) ";
		sqlQuery += " FROM pagamento AS pg ";
		
		for(String h : obj.getHierarquia()){
			sqlQuery += String.format("JOIN %s ON pg.cod_%s = %s.cod_%s ", h, h, h, h);
		}
		
		if(obj.getOrgaosConsulta().size() > 0){
			sqlQuery += " WHERE ";
			for(int h = 0; h < obj.getHierarquia().size(); h++){
				sqlQuery += " ( ";
				for(int i = 0; i < obj.getOrgaosConsulta().size(); i++){
					if(obj.getOrgaosConsulta().get(i).getTipo().equals(obj.getHierarquia().get(h))){
						sqlQuery += String.format(" %s.cod_%s = %s ", obj.getOrgaosConsulta().get(i).getTipo(), obj.getOrgaosConsulta().get(i).getTipo(), obj.getOrgaosConsulta().get(i).getId());
						if(i < obj.getOrgaosConsulta().size()-1){
							if(obj.getOrgaosConsulta().get(i+1).getTipo().equals(obj.getOrgaosConsulta().get(i).getTipo())){
								sqlQuery += " OR ";
							}
						}
					}
				}
				sqlQuery += " ) ";
				if(h < obj.getHierarquia().size()-1){
					sqlQuery += " AND ";
				}
			}
		}
		
		sqlQuery += " GROUP BY ";
		
		for(int i = 0; i < obj.getHierarquia().size(); i++){
			sqlQuery += String.format("%s.cod_%s", obj.getHierarquia().get(i), obj.getHierarquia().get(i));
			if(i < obj.getHierarquia().size()-1){
				sqlQuery += ", ";
			}
		}
		
		sqlQuery += " ORDER BY ";
		
		for(int i = 0; i < obj.getHierarquia().size(); i++){
			sqlQuery += String.format("%s.nome_%s", obj.getHierarquia().get(i), obj.getHierarquia().get(i));
			if(i < obj.getHierarquia().size()-1){
				sqlQuery += ", ";
			}
		}
		
		return pagamentoRepository.consultaHierarquica(sqlQuery);
	
	}
	
}
