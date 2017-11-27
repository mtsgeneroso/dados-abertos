package br.ufc.npi.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.UnidadeGestora;
import br.ufc.npi.model.api.Chart;
import br.ufc.npi.model.api.Data;
import br.ufc.npi.model.api.Dataset;
import br.ufc.npi.model.api.OrgaoGovernamental;
import br.ufc.npi.model.ui.TipoOrgaoGovernamental;
import br.ufc.npi.service.UnidadeGestoraService;

@RestController
@RequestMapping(path="/api/unidade_gestora")
public class UnidadeGestoraApiController extends PagamentoApiController<Long> implements IOrgaoApiControler<Long>{

	@Autowired
	private UnidadeGestoraService unidadeGestoraService;

	@RequestMapping(path="/", method=RequestMethod.GET)
	@Override
	public List<OrgaoGovernamental> list() {
		ArrayList<OrgaoGovernamental> orgaos = new ArrayList<OrgaoGovernamental>();
		List<UnidadeGestora> unidadeGestoras = unidadeGestoraService.findAll();
		for(UnidadeGestora u : unidadeGestoras){
			orgaos.add(new OrgaoGovernamental(u.getNomeUnidadeGestora(), TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela(), String.valueOf(u.getCodUnidadeGestora())));
		}
		return orgaos;
	}

	@RequestMapping(path="/{codigo}", method=RequestMethod.GET)
	@Override
	public OrgaoGovernamental get(@PathVariable("codigo")Long codigo) {
		UnidadeGestora unidadeGestoa = unidadeGestoraService.getById(codigo);
		OrgaoGovernamental orgao = new OrgaoGovernamental();
		orgao.setId(String.valueOf(codigo));
		orgao.setNome(unidadeGestoa.getNomeUnidadeGestora());
		orgao.setTipo(TipoOrgaoGovernamental.UNIDADE_GESTORA.getNomeTabela());
		return orgao;
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
			System.out.println((Double)pagamentos.get(i)[1]);
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
