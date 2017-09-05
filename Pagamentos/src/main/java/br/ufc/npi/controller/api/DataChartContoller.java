package br.ufc.npi.controller.api;

import java.text.DateFormatSymbols;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.Pagamento;
import br.ufc.npi.model.request.Chart;
import br.ufc.npi.model.request.Data;
import br.ufc.npi.model.request.Dataset;
import br.ufc.npi.service.PagamentoService;

@RestController
public class DataChartContoller {

	@Autowired
	private PagamentoService pagamentoService;

	@RequestMapping(path="/pagamentos")
	public Chart getData(){

		List<Object[]> pagamentos;
		
		pagamentos = pagamentoService.findByMonths();
		
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

		Data data = new Data(
				meses, 
				new Dataset[]{datasetPagamentos});

		Chart chart = new Chart("line", data);

		return chart;
		
	}

	@RequestMapping(path="/pagamentos/orgaoSuperior/{id}")
	public Chart paagamentosOrgaoSuperior(@PathVariable("id")Long id){

		List<Object[]> pagamentos;
		
		pagamentos = pagamentoService.findPagamentosOrgSuperiorByMonths(id);
		
		Double valores[] = new Double[pagamentos.size()];

		String meses[] = new String[pagamentos.size()];

		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		for (int i = 0; i<pagamentos.size(); i++){
			valores[i] = (Double)(pagamentos.get(i)[2]);
			meses[i] = months[(int)(pagamentos.get(i)[1])-1].substring(0, 3);
		}


		Dataset datasetPagamentos = new Dataset(
				"Pagamentos",
				valores);


		Data data = new Data(
				meses, 
				new Dataset[]{datasetPagamentos});

		Chart chart = new Chart("line", data);

		return chart;

	}

	@RequestMapping(path="/pagamentos/orgaoSubordinado/{id}")
	public Chart pagamentosOrgaoSubordinado(@PathVariable("id")Long id){

		List<Object[]> pagamentos;
		
		pagamentos = pagamentoService.findPagamentosOrgSubordinadoByMonths(id);
		
		Double valores[] = new Double[pagamentos.size()];

		String meses[] = new String[pagamentos.size()];

		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		for (int i = 0; i<pagamentos.size(); i++){
			valores[i] = (Double)(pagamentos.get(i)[2]);
			meses[i] = months[(int)(pagamentos.get(i)[1])-1].substring(0, 3);
		}


		Dataset datasetPagamentos = new Dataset(
				"Pagamentos",
				valores);

		Data data = new Data(
				meses, 
				new Dataset[]{datasetPagamentos});

		Chart chart = new Chart("line", data);

		return chart;

	}
	
	@RequestMapping(path="/pagamentos/unidadeGestora/{id}")
	public Chart pagamentosUnidadeGestora(@PathVariable("id")Long id){

		List<Object[]> pagamentos;
		
		pagamentos = pagamentoService.findPagamentosUnidadeGestoraByMonths(id);
		
		Double valores[] = new Double[pagamentos.size()];

		String meses[] = new String[pagamentos.size()];

		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		for (int i = 0; i<pagamentos.size(); i++){
			valores[i] = (Double)(pagamentos.get(i)[2]);
			meses[i] = months[(int)(pagamentos.get(i)[1])-1].substring(0, 3);
		}


		Dataset datasetPagamentos = new Dataset(
				"Pagamentos",
				valores);

		Data data = new Data(
				meses, 
				new Dataset[]{datasetPagamentos});

		Chart chart = new Chart("line", data);

		return chart;

	}
	
}
