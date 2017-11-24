package br.ufc.npi.controller.api;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufc.npi.model.api.Chart;
import br.ufc.npi.service.PagamentoService;

public abstract class PagamentoApiController<T>{

	@Autowired
	protected PagamentoService pagamentoService;
	
	protected static String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
	public abstract Chart pagamentos(T codigo);
	
}
