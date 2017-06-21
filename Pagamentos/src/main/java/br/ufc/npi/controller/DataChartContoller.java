package br.ufc.npi.controller;

import java.text.DateFormatSymbols;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.Pagamento;
import br.ufc.npi.model.chart.Chart;
import br.ufc.npi.model.chart.Data;
import br.ufc.npi.model.chart.Dataset;
import br.ufc.npi.service.PagamentoService;

@RestController
public class DataChartContoller {

	@Autowired
	private PagamentoService pagamentoService;

	@RequestMapping(path="/getData/{id}")
	public Object getData(@PathVariable("id")Long id){

		List<Object[]> pagamentos;
		
		if(id == 0){
			pagamentos = pagamentoService.findByMonths(1, 12);
		}else{
			pagamentos = pagamentoService.findPagamentosOrgSuperiorByMonths(1, 12, id);
		}
		

		Double valores[] = new Double[pagamentos.size()];

		String meses[] = new String[pagamentos.size()];

		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		for (int i = 0; i<pagamentos.size(); i++){
			valores[i] = (Double)(pagamentos.get(i)[1]);
			meses[i] = months[(int)(pagamentos.get(i)[0])-1].substring(0, 3);
		}


		Dataset datasetPagamentos = new Dataset(
				"Pagamentos",
				valores);

		datasetPagamentos.setBackgroundColor("rgba(10, 50, 100,.6)");

		Data data = new Data(
				meses, 
				new Dataset[]{datasetPagamentos});

		Chart chart = new Chart("line", data);

		return chart;
		
	}

	@RequestMapping(path="/pagamentos")
	public List<Pagamento> testePagamento(){

		return pagamentoService.findAll();

	}

}
