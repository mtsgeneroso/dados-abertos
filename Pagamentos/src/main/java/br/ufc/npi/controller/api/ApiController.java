package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.request.Chart;
import br.ufc.npi.model.request.Data;
import br.ufc.npi.model.request.Dataset;
import br.ufc.npi.model.request.Orgao;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.OrgaoSuperiorService;
import br.ufc.npi.service.PagamentoService;
import br.ufc.npi.service.UnidadeGestoraService;
import br.ufc.npi.util.RGBStringColor;

@RestController
@RequestMapping(path="/api")
public class ApiController {
	
	@Autowired
	private OrgaoSuperiorService orgaoSuperiorService;
	@Autowired
	private OrgaoSubordinadoService orgaoSubordinadoService;
	@Autowired
	private UnidadeGestoraService unidadeGestoraService;
	@Autowired
	private PagamentoService pagamentoService;
	
	private String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
	
	@RequestMapping(path="/orgaos", method=RequestMethod.GET)
	public List<Orgao> listarTodosOrgaos(){
		
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findAll();
		
		ArrayList<Orgao> orgaos = new ArrayList<Orgao>();
		
		for(OrgaoSuperior os : orgaosSuperiores){
			orgaos.add(new Orgao(os.getNomeOrgaoSuperior(), Orgao.ORGAO_SUPERIOR, os.getCodOrgaoSuperior()));
		}
		
		for(OrgaoSubordinado os : orgaosSubordinados){
			orgaos.add(new Orgao(os.getNomeOrgaoSubordinado(), Orgao.ORGAO_SUBORDINADO, os.getCodOrgaoSubordinado()));
		}
		
		for(UnidadeGestora ug : unidadesGestoras){
			orgaos.add(new Orgao(ug.getNomeUnidadeGestora(), Orgao.UNIDADE_GESTORA, ug.getCodUnidadeGestora()));
		}
		
		return orgaos;
		
	}
	
	@RequestMapping(path="/consulta-simples", method=RequestMethod.POST)
	public Chart consultarOrgaos(@RequestBody List<Orgao> orgaos){
		
		List<Object[]> pagamentos;
		
		Dataset[] datasets = new Dataset[orgaos.size()];
		
		int k = 0;
		for(Orgao o : orgaos){
			pagamentos = pagamentoService.findPagamentosByMonths(o.getTipo(), o.getId());				
			
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
	public List<Orgao> consultaHierarquica(@RequestBody List<String> hierarquia){
		
		ArrayList<Orgao> orgaos = new ArrayList<Orgao>();
		
		List<Orgao> subOs1 = new ArrayList<Orgao>();
		
		List<Orgao> subsubOs1 = new ArrayList<Orgao>();
		subsubOs1.add(new Orgao("Sub Órgão 1", "orgaoSubordinado", 12L, null, 1000));
		subsubOs1.add(new Orgao("Sub Órgão 2", "orgaoSubordinado", 13L, null, 1000));
		
		subOs1.add(new Orgao("Sub Órgão 1", "orgaoSubordinado", 12L, subsubOs1, 1000));
		subOs1.add(new Orgao("Sub Órgão 2", "orgaoSubordinado", 13L, null, 1000));
		
		Orgao os1 = new Orgao("Órgão Superior 1", "orgaoSuperior", 1L, subOs1, 2000);
		
		List<Orgao> subOs2 = new ArrayList<Orgao>();
		subOs2.add(new Orgao("Sub Órgão 1", "orgaoSubordinado", 12L, null, 1000));
		subOs2.add(new Orgao("Sub Órgão 2", "orgaoSubordinado", 13L, null, 1000));
		subOs2.add(new Orgao("Sub Órgão 3", "orgaoSubordinado", 14L, null, 3000));
		
		Orgao os2 = new Orgao("Órgão Superior 2", "orgaoSuperior", 2L, subOs2, 5000);
		
		orgaos.add(os1);
		orgaos.add(os2);
		
		return orgaos;
		
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

	@RequestMapping(path="/pagamentos/"+Orgao.ORGAO_SUPERIOR+"/{id}")
	public Chart pagamentosOrgaoSuperior(@PathVariable("id")Long id){
		
		List<Object[]> pagamentos;
		
		if(id == 0){
			pagamentos = pagamentoService.findByMonths();
		}else{
			pagamentos = pagamentoService.findPagamentosOrgSuperiorByMonths(id);
		}
		
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

	@RequestMapping(path="/pagamentos/"+Orgao.ORGAO_SUBORDINADO+"/{id}")
	public Chart pagamentosOrgaoSubordinado(@PathVariable("id")Long id){

		List<Object[]> pagamentos;
		
		if(id == 0){
			pagamentos = pagamentoService.findByMonths();
		}else{
			pagamentos = pagamentoService.findPagamentosOrgSubordinadoByMonths(id);
		}
		
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
	
	@RequestMapping(path="/pagamentos/"+Orgao.UNIDADE_GESTORA+"/{id}")
	public Chart pagamentosUnidadeGestora(@PathVariable("id")Long id){

		List<Object[]> pagamentos;
		
		if(id == 0){
			pagamentos = pagamentoService.findByMonths();
		}else{
			pagamentos = pagamentoService.findPagamentosOrgSubordinadoByMonths(id);
		}
		
		Double valores[] = new Double[12];
		for(int i = 0; i<valores.length; i++){
			valores[i] = 0.0;
		}

		for (int i = 0; i<pagamentos.size(); i++){
			valores[(int)pagamentos.get(i)[0]-1] = (Double) pagamentos.get(i)[1];
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
