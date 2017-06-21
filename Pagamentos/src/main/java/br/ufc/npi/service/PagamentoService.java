package br.ufc.npi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.npi.model.Pagamento;
import br.ufc.npi.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;	
	
	public List<Pagamento> findAll(){
		return pagamentoRepository.findAll();
	}
	
	public List<Object[]> findByMonths(int m1, int m2){
		return pagamentoRepository.listByDataBetween(m1, m2);
	}
	
	public List<Object[]> findPagamentosOrgSuperiorByMonths(int m1, int m2, Long codigoOrgSuperior){
		return pagamentoRepository.listPagamentosOrgSuperiorByDataBetween(m1, m2, codigoOrgSuperior);
	}
	
}
