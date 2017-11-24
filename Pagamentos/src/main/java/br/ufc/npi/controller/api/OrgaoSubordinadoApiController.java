package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.OrgaoSubordinado;
import br.ufc.npi.model.api.Chart;
import br.ufc.npi.model.api.Data;
import br.ufc.npi.model.api.Dataset;
import br.ufc.npi.model.api.MensagemJSON;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.OrgaoSubordinadoService;

@RestController
@RequestMapping(path="/api/orgao_subordinado")
public class OrgaoSubordinadoApiController extends PagamentoApiController<Long> implements IOrgaoApiControler<Long>{
	
	@Autowired
	private OrgaoSubordinadoService orgaoSubordinadoService;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@Override
	public List<OrgaoGovernamental> list(){
		ArrayList<OrgaoGovernamental> orgaos = new ArrayList<OrgaoGovernamental>();
		List<OrgaoSubordinado> orgaosSubordinados = orgaoSubordinadoService.findAll();
		for(OrgaoSubordinado os : orgaosSubordinados){
			orgaos.add(new OrgaoGovernamental(os.getNomeOrgaoSubordinado(), TipoOrgaoGovernamental.ORGAO_SUBORDINADO.getNomeTabela(), String.valueOf(os.getCodOrgaoSubordinado())));
		}
		return orgaos;
	}
	
	@RequestMapping(path="/{codigo}", method=RequestMethod.GET)
	@Override
	public Object get(@PathVariable("codigo") Long codigo){
		OrgaoSubordinado orgaoSubordinado = orgaoSubordinadoService.getById(codigo);
		if(orgaoSubordinado != null){
			OrgaoGovernamental orgao = new OrgaoGovernamental();
			orgao.setId(String.valueOf(codigo));
			orgao.setNome(orgaoSubordinado.getNomeOrgaoSubordinado());
			orgao.setTipo(TipoOrgaoGovernamental.ORGAO_SUBORDINADO.getNomeTabela());
			return orgao;
		}else{
			return new MensagemJSON("Órgão não encontrado.");
		}	
	}

	@RequestMapping(path="/find", method=RequestMethod.POST)
	@Override
	public List<OrgaoGovernamental> find(OrgaoGovernamental orgaoConsulta) {
		return null;
	}

	@RequestMapping(path="/pagamentos/{codigo}")
	@Override
	public Chart pagamentos(@PathVariable("codigo")Long codigo) {
		List<Object[]> pagamentos;
		
		if(codigo == 0){
			pagamentos = pagamentoService.findByMonths();
		}else{
			pagamentos = pagamentoService.findPagamentosOrgSubordinadoByMonths(codigo);
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
