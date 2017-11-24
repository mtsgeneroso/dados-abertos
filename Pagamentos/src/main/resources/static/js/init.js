$(document).ready(function(){
	$("#filter").keypress(function(){
		console.log($("#filter").val());
	});
});

function initInterface(tipoOrgao, id){
	
	$("#rangeMeses").hide();
	$("#chart").hide();
	$("#mensagemChart").hide();

	$.getJSON("/api/" + tipoOrgao + "/pagamentos/" + id, function(chart){
		if(chart.data.datasets[0].data.length === 0){
			$("#loadChart").hide();
			$("#mensagemChart").show();
		}
		else{
			labels = chart.data.labels;
			console.log(labels);
			dados = [];
			for(var i=0; i<chart.data.datasets.length; i++){
				dados.push(chart.data.datasets[i].data);
			}

			$("#loadChart").hide();
			$("#rangeMeses").show();
			$("#chart").show();

			function number2month(value){
				return labels[value];
			}

			function month2number(month){
				return labels.indexOf(month);
			}
			
			var ctx = $('#chart');
			chart.options =  {
			        scales: {
			            yAxes: [{
			                ticks: {
			                    // Include a dollar sign in the ticks
			                    callback: function(value, index, values) {
			                        return 'R' + numeral(value).format('($ 0.0 a)');
			                    }
			                }
			            }]
			        }
			    };
			if(labels.length == 1){
				chart.type = 'bar';
			}
			var grafico = new Chart(ctx, chart);
			
			if(labels.length != 1){
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
						stepped: true,
						filter: function(value){
							return 1;
						},
						format: {
							to: number2month,
							from: month2number
						}
					},
					connect: true,
					format: wNumb({
						decimals: 0,
						encoder: function(value){
							return (parseInt(value)+1);
						}
					})
				});
			
				//Método chamado ao atualizar o slider
				slider.noUiSlider.on('update', function(values, handle){
					var mesIni = parseInt(values[0])-1;
					var mesFim = parseInt(values[1])-1;
	
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
			}
		}
		
	});
}