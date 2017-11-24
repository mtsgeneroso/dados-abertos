package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.Acao;
import br.ufc.npi.model.api.Chart;
import br.ufc.npi.model.api.Data;
import br.ufc.npi.model.api.Dataset;
import br.ufc.npi.model.api.MensagemJSON;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.AcaoService;

@RestController
@RequestMapping(path="/api/acao")
public class AcaoApiController extends PagamentoApiController<String> implements IOrgaoApiControler<String>{
	
	@Autowired
	private AcaoService acaoService;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	@Override
	public List<OrgaoGovernamental> list() {
		ArrayList<OrgaoGovernamental> orgaos = new ArrayList<OrgaoGovernamental>();
		List<Acao> acoes = acaoService.findAll();
		for(Acao a : acoes){
			orgaos.add(new OrgaoGovernamental(a.getNomeAcao(), TipoOrgaoGovernamental.ACAO.getNomeTabela(), a.getCodAcao()));
		}
		return orgaos;
	}

	@RequestMapping(path="/{codigo}", method=RequestMethod.GET)
	@Override
	public Object get(@PathVariable("codigo")String codigo) {
		Acao acao = acaoService.getById(codigo);
		if(acao != null){
			OrgaoGovernamental orgao = new OrgaoGovernamental();
			orgao.setId(String.valueOf(codigo));
			orgao.setNome(acao.getCodAcao());
			orgao.setTipo(TipoOrgaoGovernamental.ACAO.getNomeTabela());
			return orgao;
		}else{
			return new MensagemJSON("Órgão não encontrado.");
		}
	}
	
	@RequestMapping(path="/find", method=RequestMethod.POST)
	@Override
	public List<OrgaoGovernamental> find(OrgaoGovernamental orgaoConsulta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(path="/pagamentos/{codigo}")
	@Override
	public Chart pagamentos(@PathVariable("codigo")String codigo) {
		List<Object[]> pagamentos;
		if(codigo.equals("0")){
			pagamentos = pagamentoService.findByMonths();
		}else{
			pagamentos = pagamentoService.findPagamentosAcaoByMonths(codigo);
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
