package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.Acao;
import br.ufc.npi.model.Favorecido;
import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.Programa;
import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.request.Chart;
import br.ufc.npi.model.request.ConsultaHierarquicaObj;
import br.ufc.npi.model.request.Data;
import br.ufc.npi.model.request.Dataset;
import br.ufc.npi.model.request.Dado;
import br.ufc.npi.service.AcaoService;
import br.ufc.npi.service.FavorecidoService;
import br.ufc.npi.service.OrgaoSubordinadoService;
import br.ufc.npi.service.OrgaoSuperiorService;
import br.ufc.npi.service.PagamentoService;
import br.ufc.npi.service.ProgramaService;
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
	@Autowired
	private AcaoService acaoService;
	@Autowired
	private ProgramaService programaService;
	@Autowired
	private FavorecidoService favorecidoService;
	
	private String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
	
	@RequestMapping(path="/orgaos", method=RequestMethod.GET)
	public List<Dado> listarTodosOrgaos(){
		
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		List<UnidadeGestora> unidadesGestoras = unidadeGestoraService.findAll();
		
		ArrayList<Dado> orgaos = new ArrayList<Dado>();
		
		for(OrgaoSuperior os : orgaosSuperiores){
			orgaos.add(new Dado(os.getNomeOrgaoSuperior(), Dado.ORGAO_SUPERIOR, String.valueOf(os.getCodOrgaoSuperior())));
		}
		
		for(OrgaoSubordinado os : orgaosSubordinados){
			orgaos.add(new Dado(os.getNomeOrgaoSubordinado(), Dado.ORGAO_SUBORDINADO, String.valueOf(os.getCodOrgaoSubordinado())));
		}
		
		for(UnidadeGestora ug : unidadesGestoras){
			orgaos.add(new Dado(ug.getNomeUnidadeGestora(), Dado.UNIDADE_GESTORA, String.valueOf(ug.getCodUnidadeGestora())));
		}
		
		return orgaos;
		
	}
	
	@RequestMapping(path="/"+Dado.ORGAO_SUPERIOR)
	public List<Dado> listarOrgaosSuperiores(){
		ArrayList<Dado> orgaos = new ArrayList<Dado>();
		
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		for(OrgaoSuperior os : orgaosSuperiores){
			orgaos.add(new Dado(os.getNomeOrgaoSuperior(), Dado.ORGAO_SUPERIOR, String.valueOf(os.getCodOrgaoSuperior())));
		}
		
		return orgaos;
	}
	
	@RequestMapping(path="/"+Dado.ORGAO_SUBORDINADO)
	public List<Dado> listarOrgaosSubordinados(){
		ArrayList<Dado> orgaos = new ArrayList<Dado>();
		
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		for(OrgaoSubordinado os : orgaosSubordinados){
			orgaos.add(new Dado(os.getNomeOrgaoSubordinado(), Dado.ORGAO_SUBORDINADO, String.valueOf(os.getCodOrgaoSubordinado())));
		}
		
		return orgaos;
	}
	
	@RequestMapping(path="/"+Dado.UNIDADE_GESTORA)
	public List<Dado> listarUnidadesGestoras(){
		ArrayList<Dado> orgaos = new ArrayList<Dado>();
		
		List<UnidadeGestora> unidadeGestoras = unidadeGestoraService.findAll();
		for(UnidadeGestora u : unidadeGestoras){
			orgaos.add(new Dado(u.getNomeUnidadeGestora(), Dado.UNIDADE_GESTORA, String.valueOf(u.getCodUnidadeGestora())));
		}
		
		return orgaos;
	}
	
	@RequestMapping(path="/"+Dado.ACAO)
	public List<Dado> listarAcoes(){
		ArrayList<Dado> orgaos = new ArrayList<Dado>();
		
		List<Acao> acoes = acaoService.findAll();
		for(Acao a : acoes){
			orgaos.add(new Dado(a.getNomeAcao(), Dado.ACAO, a.getCodAcao()));
		}
		
		return orgaos;
	}
	
	@RequestMapping(path="/"+Dado.PROGRAMA)
	public List<Dado> listarProgramas(){
		ArrayList<Dado> orgaos = new ArrayList<Dado>();
		
		List<Programa> programas = programaService.findAll();
		for(Programa p : programas){
			orgaos.add(new Dado(p.getNomePrograma(), Dado.PROGRAMA, String.valueOf(p.getCodPrograma())));
		}
		
		return orgaos;
	}
	
	@RequestMapping(path="/"+Dado.FAVORECIDO)
	public List<Dado> listarFavorecidos(){
		ArrayList<Dado> orgaos = new ArrayList<Dado>();
		
		List<Favorecido> favorecidos = favorecidoService.findAll();
		for(Favorecido f : favorecidos){
			orgaos.add(new Dado(f.getNomeFavorecido(), Dado.FAVORECIDO, f.getCodFavorecido()));
		}
		
		return orgaos;
	}
	
	@RequestMapping(path="/consulta-simples", method=RequestMethod.POST)
	public Chart consultarOrgaos(@RequestBody List<Dado> orgaos){
		
		List<Object[]> pagamentos;
		
		Dataset[] datasets = new Dataset[orgaos.size()];
		
		int k = 0;
		for(Dado o : orgaos){
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

	@RequestMapping(path="/pagamentos/"+Dado.ORGAO_SUPERIOR, method=RequestMethod.GET)
	public Chart pagamentosOrgaoSuperior(@RequestParam("id")Long id){
		
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

	@RequestMapping(path="/pagamentos/"+Dado.ORGAO_SUBORDINADO, method=RequestMethod.GET)
	public Chart pagamentosOrgaoSubordinado(@RequestParam("id")Long id){

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
	
	@RequestMapping(path="/pagamentos/"+Dado.UNIDADE_GESTORA, method=RequestMethod.GET)
	public Chart pagamentosUnidadeGestora(@RequestParam("id")Long id){

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
			valores[(int)pagamentos.get(i)[0]-1] = (Double)pagamentos.get(i)[1];
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
