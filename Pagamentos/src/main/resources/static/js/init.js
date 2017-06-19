(function($){
	$(function(){
		$('.button-collapse').sideNav();
	}); // end of document ready
})(jQuery); // end of jQuery name space

$.getJSON("/teste", function(chart){
	var labels = chart.data.labels;
	var dados = []
	for(var i=0; i<chart.data.datasets.length; i++){
		dados.push(chart.data.datasets[i].data);
	}
	
	//Cria slider	
	var slider1 = document.getElementById('range1');
	noUiSlider.create(slider1, {
		start: [0, labels.length-1],
		step: 1,
		range: {
			'min': 0,
			'max': labels.length-1
		},
		connect: true,
		format: {
			to: function(value){
				return labels[value];
			},
			from: function(value){
				return labels.indexOf(value);
			}
		}
	});
	
	//Cria gráfico inicial
	var ctx = $('#myChart');
	var grafico = new Chart(ctx, chart);
	
	//Método chamado ao atualizar o slider
	slider1.noUiSlider.on('update', function(values, handle){
		var mesIni = labels.indexOf(values[0]); 
		var mesFim = labels.indexOf(values[1]);
		
		console.log([mesIni, mesFim]);
		
		newChart = chart;
		newChart.data.labels = labels.slice(mesIni, mesFim+1);
				
		for(var i=0; i<newChart.data.datasets.length; i++){
			newChart.data.datasets[i].data = dados[i].slice(mesIni, mesFim+1);
		}
		
		//Se o intervalo é composto por apenas um único mês
		if(mesIni == mesFim){
			newChart.type = 'bar'; //Altera o tipo para gráfico de barras ('bar')
		}else{
			newChart.type = 'line'; //Caso contrário, altera para o tipo 'line'
		}
		grafico.destroy();
		grafico = new Chart(ctx, newChart);
		
	});
			
});