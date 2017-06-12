package br.ufc.npi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.npi.model.chart.Chart;
import br.ufc.npi.model.chart.Data;
import br.ufc.npi.model.chart.Dataset;

@RestController
public class TesteChartContoller {

	@RequestMapping(path="/teste")
	public Chart teste(){
		
		Dataset dataset = new Dataset(
				"Tetando o Chart via Java", 
				new Double[]{0., 10., 5., 2., 20., 30., 45.});
		
		Data data = new Data(
				new String[]{"January", "February", "March", "April", "May", "June", "July"}, 
				new Dataset[]{dataset});
		
		Chart chart = new Chart("line", data);
		
		return chart;
		
	}
	
}
