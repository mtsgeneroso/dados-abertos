package br.ufc.npi.controller.api;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		
		String mesesFinal[] = null;
		
		int k = 0;
		for(Orgao o : orgaos){
			pagamentos = pagamentoService.findPagamentosByMonths(o.getTipo(), o.getId());				
			Double valores[] = new Double[pagamentos.size()];
			String meses[] = new String[pagamentos.size()];
			DateFormatSymbols dfs = new DateFormatSymbols();
			String[] months = dfs.getMonths();
			for (int i = 0; i<pagamentos.size(); i++){
				valores[i] = (Double)(pagamentos.get(i)[2]);
				meses[i] = months[(int)(pagamentos.get(i)[1])-1].substring(0, 3);
			}
			datasets[k] = new Dataset(
					o.getNome(),
					valores);
			datasets[k].setBorderColor(RGBStringColor.getColor(k));
			mesesFinal = meses;
			k++;
		}
		
		Data data = new Data(
				mesesFinal,
				datasets);

		Chart chart = new Chart("line", data);

		return chart;

	}
	
	
}
