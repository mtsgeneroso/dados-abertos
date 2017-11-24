package br.ufc.npi.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.api.Chart;
import br.ufc.npi.model.api.ConsultaHierarquicaObj;
import br.ufc.npi.model.api.Data;
import br.ufc.npi.model.api.Dataset;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.service.PagamentoService;
import br.ufc.npi.util.RGBStringColor;

@RestController
@RequestMapping(path="/api")
public class ApiController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
	private String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
	
	@RequestMapping(path="/consulta-simples", method=RequestMethod.POST)
	public Chart consultarOrgaos(@RequestBody List<OrgaoGovernamental> orgaos){
		
		List<Object[]> pagamentos;
		
		Dataset[] datasets = new Dataset[orgaos.size()];
		
		int k = 0;
		for(OrgaoGovernamental o : orgaos){
			pagamentos = pagamentoService.findPagamentosByMonths(o.getTipo(), Long.valueOf(o.getId()));				
			
			Double valores[] = new Double[12];
			for(int i = 0; i<valores.length; i++){
				valores[i] = 0.0;
			}
			
			for (int i = 0; i<pagamentos.size(); i++){
				valores[(int)pagamentos.get(i)[0]-1] = (Double)(pagamentos.get(i)[1]);
			}
			
			datasets[k] = new Dataset(
					o.getNome(),
					valores);
			datasets[k].setBorderColor(RGBStringColor.getColor(k));
			k++;
		}
		
		Data data = new Data(
				meses,
				datasets);

		Chart chart = new Chart("line", data);

		return chart;

	}
	
	@RequestMapping(path="/consulta-hierarquica", method=RequestMethod.POST)
	public List<Object[]> consultaHierarquica(@RequestBody ConsultaHierarquicaObj objConsulta){
		return pagamentoService.pagamentosConsultaHierarquica(objConsulta);
	}
	
	@RequestMapping(path="/pagamentos")
	public Chart getData(){

		List<Object[]> pagamentos;
		
		pagamentos = pagamentoService.findByMonths();
		
		Double valores[] = new Double[12];
		for(int i = 0; i<valores.length; i++){
			valores[i] = 0.0;
		}
		
		for (int i = 0; i<pagamentos.size(); i++){
			valores[(int)pagamentos.get(i)[0]-1] = (Double)(pagamentos.get(i)[1]);
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
