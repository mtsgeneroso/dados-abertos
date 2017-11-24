package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.Pagamento;
import br.ufc.npi.model.api.ConsultaHierarquicaObj;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;	

	public List<Pagamento> findAll(){
		return pagamentoRepository.findAll();
	}

	public List<Object[]> findByMonths(){
		return pagamentoRepository.list();
	}

	public List<Object[]> findPagamentosOrgSuperiorByMonths(Long codigoOrgSuperior){
		return pagamentoRepository.listPagamentosOrgSuperior(codigoOrgSuperior);
	}

	public List<Object[]> findPagamentosOrgSubordinadoByMonths(Long codigoOrgSubordinado){
		return pagamentoRepository.listPagamentosOrgSubordinado(codigoOrgSubordinado);
	}

	public List<Object[]> findPagamentosUnidadeGestoraByMonths(Long codigoUnidadeGestora){
		return pagamentoRepository.listPagamentosUnidadeGestora(codigoUnidadeGestora);
	}	

	public List<Object[]> findPagamentosAcaoByMonths(String codigoAcao){
		return pagamentoRepository.listPagamentosAcoes(codigoAcao);
	}
	
	public List<Object[]> findPagamentosProgramaByMonths(Long codigoPrograma){
		return pagamentoRepository.listPagamentosProgramas(codigoPrograma);
	}
	
	public List<Object[]> findPagamentosFavorecidoByMonths(String codigoFavorecido){
		return pagamentoRepository.listPagamentosFavorecido(codigoFavorecido);
	}
	
	public List<Object[]> findPagamentosByMonths(String orgaoTipo, Long codOrgao){
		if(orgaoTipo.equals(TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela())){
			return this.findPagamentosOrgSuperiorByMonths(codOrgao);
		}
		else if(orgaoTipo.equals(TipoOrgaoGovernamental.ORGAO_SUBORDINADO.getNomeTabela())){
			return this.findPagamentosOrgSubordinadoByMonths(codOrgao);
		}
		else if(orgaoTipo.equals(TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela())){
			return this.findPagamentosUnidadeGestoraByMonths(codOrgao);
		}
		return null;
	}

	public List<Object[]> pagamentosConsultaHierarquica(ConsultaHierarquicaObj obj){

		String sqlQuery = "SELECT ";

		for(String h : obj.getHierarquia()){
			sqlQuery += String.format(" %s.nome_%s, ", h, h);
		}

		sqlQuery += " CAST(SUM(pg.valor) AS FLOAT) ";
		sqlQuery += " FROM pagamento AS pg ";

		for(String h : obj.getHierarquia()){
			sqlQuery += String.format(" JOIN %s ON pg.cod_%s = %s.cod_%s ", h, h, h, h);
		}

		int contOrg = 0;
		if(obj.getOrgaosConsulta().size() > 0){
			sqlQuery += " WHERE ";
			for(int h = 0; h < obj.getHierarquia().size(); h++){
				sqlQuery += " ( ";
				for(int i = 0; i < obj.getOrgaosConsulta().size(); i++){
					if(obj.getOrgaosConsulta().get(i).getTipo().equals(obj.getHierarquia().get(h))){
						//Ação e Favorecido têm chaves primárias de tipo String, por isso esse IF é necessário
						if(obj.getOrgaosConsulta().get(i).getTipo().equals(TipoOrgaoGovernamental.ACAO.getNomeTabela()) ||
								obj.getOrgaosConsulta().get(i).getTipo().equals(TipoOrgaoGovernamental.FAVORECIDO.getNomeTabela())){
							sqlQuery += String.format(" %s.cod_%s = '%s' ", obj.getOrgaosConsulta().get(i).getTipo(), obj.getOrgaosConsulta().get(i).getTipo(), obj.getOrgaosConsulta().get(i).getId());
						}else{
							sqlQuery += String.format(" %s.cod_%s = %s ", obj.getOrgaosConsulta().get(i).getTipo(), obj.getOrgaosConsulta().get(i).getTipo(), obj.getOrgaosConsulta().get(i).getId());
						}
						if(i < obj.getOrgaosConsulta().size()-1){
							if(obj.getOrgaosConsulta().get(i+1).getTipo().equals(obj.getOrgaosConsulta().get(i).getTipo())){
								sqlQuery += " OR ";
							}
						}
						contOrg++;
					}

				}
				sqlQuery += " ) ";
				if(contOrg == obj.getOrgaosConsulta().size()){
					break;
				}
				else if(h < obj.getHierarquia().size()-1){
					sqlQuery += " AND ";
				}
			}
		}

		sqlQuery += " GROUP BY ";

		for(int i = 0; i < obj.getHierarquia().size(); i++){
			sqlQuery += String.format(" %s.cod_%s ", obj.getHierarquia().get(i), obj.getHierarquia().get(i));
			if(i < obj.getHierarquia().size()-1){
				sqlQuery += " , ";
			}
		}

		sqlQuery += " ORDER BY ";

		for(int i = 0; i < obj.getHierarquia().size(); i++){
			sqlQuery += String.format(" %s.nome_%s ", obj.getHierarquia().get(i), obj.getHierarquia().get(i));
			if(i < obj.getHierarquia().size()-1){
				sqlQuery += " , ";
			}
		}
		
		//Removendo eventuais erros na construção da String
		sqlQuery = sqlQuery.replace("AND  (  )", "");
		sqlQuery = sqlQuery.replace("OR  (  )", "");

		return pagamentoRepository.consultaHierarquica(sqlQuery);

	}

}
