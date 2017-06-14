package br.ufc.npi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.Pagamento;
import br.ufc.npi.model.chart.Chart;
import br.ufc.npi.model.chart.Data;
import br.ufc.npi.model.chart.Dataset;
import br.ufc.npi.repository.PagamentoRepository;
import br.ufc.npi.service.PagamentoService;

@RestController
public class TesteChartContoller {

	private PagamentoService pagamentoService;
	
	@RequestMapping(path="/teste")
	public Chart teste(){
		
		/*List<Pagamento> pagamentos = pagamentoService.findByMonths(1, 12);
		
		Double valores[] = new Double[pagamentos.size()];
		
		for(int i = 0; i<pagamentos.size(); i++){
			valores[i] = (double) pagamentos.get(i).getValor();
		}*/
		
		Dataset datasetPagamentos = new Dataset(
				"Pagamentos",
				new Double[]{10., 2., 1., 20., 30., 7., 20.});
		
		datasetPagamentos.setBackgroundColor("rgba(100,100,100,.6)");
		
		Dataset datasetServidores = new Dataset(
				"Servidores",
				new Double[]{10., 20., 10., 5., 13., 21., 16.});
		
		datasetServidores.setBackgroundColor("rgb(100, 100, 255)");
		datasetServidores.setBorderColor("rgb(50, 50, 150)");
		
		Data data = new Data(
				new String[]{"January", "February", "March", "April", "May", "June", "July"}, 
				new Dataset[]{datasetPagamentos, datasetServidores});
		
		Chart chart = new Chart("line", data);
		
		return chart;
		
	}
	
	@RequestMapping(path="/pagamentos")
	public List<Pagamento> testePagamento(){
		
		return pagamentoService.findAll();
		
	}
	
}
