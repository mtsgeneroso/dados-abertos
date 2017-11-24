package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.OrgaoSuperior;
import br.ufc.npi.model.api.Chart;
import br.ufc.npi.model.api.Data;
import br.ufc.npi.model.api.Dataset;
import br.ufc.npi.model.api.MensagemJSON;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.OrgaoSuperiorService;
import br.ufc.npi.service.PagamentoService;

@RestController
@RequestMapping(path="/api/orgao_superior")
public class OrgaoSuperiorApiController extends PagamentoApiController<Long> implements IOrgaoApiControler<Long>{
	
	@Autowired
	private OrgaoSuperiorService orgaoSuperiorService;
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public List<OrgaoGovernamental> list(){
		ArrayList<OrgaoGovernamental> orgaos = new ArrayList<OrgaoGovernamental>();
		List<OrgaoSuperior> orgaosSuperiores = orgaoSuperiorService.findAll();
		for(OrgaoSuperior os : orgaosSuperiores){
			orgaos.add(new OrgaoGovernamental(os.getNomeOrgaoSuperior(), TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela(), String.valueOf(os.getCodOrgaoSuperior())));
		}
		return orgaos;
	}
	
	@RequestMapping(path="/{codigo}", method=RequestMethod.GET)
	public Object get(@PathVariable("codigo") Long codigo){
		OrgaoSuperior orgaoSuperior = orgaoSuperiorService.getById(codigo);
		if(orgaoSuperior != null){
			OrgaoGovernamental orgao = new OrgaoGovernamental();
			orgao.setId(String.valueOf(codigo));
			orgao.setNome(orgaoSuperior.getNomeOrgaoSuperior());
			orgao.setTipo(TipoOrgaoGovernamental.ORGAO_SUPERIOR.getNomeTabela());
			return orgao;
		}else{
			return new MensagemJSON("Órgão não encontrado.");
		}	
	}
	
	@RequestMapping(path="/find", method=RequestMethod.POST)
	public List<OrgaoGovernamental> find(@RequestParam OrgaoGovernamental orgaoConsulta){
		return null;
	}

	@RequestMapping("/pagamentos/{codigo}")
	@Override
	public Chart pagamentos(@PathVariable("codigo")Long codigo) {
		List<Object[]> pagamentos;
		if(codigo == 0){
			pagamentos = pagamentoService.findByMonths();
		}else{
			pagamentos = pagamentoService.findPagamentosOrgSuperiorByMonths(codigo);
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
	
}
