(function($){
	$(function(){
		$('.button-collapse').sideNav();
	}); // end of document ready
	
	
	$("#rangeMeses").hide();
	$("#chart").hide();
	$("#botoes").hide();

	var id = $("input[name=currentId]").val();

	$.getJSON("/getData/" + id, function(chart){
		var labels = chart.data.labels;
		var dados = [];
		for(var i=0; i<chart.data.datasets.length; i++){
			dados.push(chart.data.datasets[i].data);
		}
		
		$("#loadChart").hide();
		$("#rangeMeses").show();
		$("#chart").show();
		$("#botoes").show();
		
		function number2month(value){
			return labels[value];
		}
		
		function month2number(month){
			return labels.indexOf(month);
		}
		
		//Cria slider
		var slider = document.getElementById('rangeMeses');
		noUiSlider.create(slider, {
			start: [0, labels.length-1],
			step: 1,
			range: {
				'min': 0,
				'max': labels.length-1
			},
			pips: {
				mode: 'steps',
//				stepped: true,
				density: 1,
				filter: function(value){
					return 1;
				},
				format: {
					to: number2month,
					from: month2number
				}
			},
			connect: true,
			format: {
				to: number2month,
				from: month2number
			}
		});
		
		//Cria gráfico inicial
		var ctx = $('#chart');
		var grafico = new Chart(ctx, chart);
		
		//Método chamado ao atualizar o slider
		slider.noUiSlider.on('update', function(values){
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
	
	
})(jQuery); // end of jQuery name space