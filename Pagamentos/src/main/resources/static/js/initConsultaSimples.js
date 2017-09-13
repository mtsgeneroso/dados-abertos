$(document).ready(function(){
	
	$('.modal').modal();
	
	$(".grafico-container").hide();
	$(".detalhes-container").hide();
	
	var chips = {};

	//Lista todos os órgãos para o campo de pesquisa
	$.getJSON("/api/orgaos", function(orgaos){
		var dataSugestions = {};		
		for(var i = 0; i < orgaos.length; i++){
			chips[orgaos[i].nome] = {
					"tipo":orgaos[i].tipo, 
					"id":orgaos[i].id,
					"nome":orgaos[i].nome
			};
			dataSugestions[orgaos[i].nome] = null;
		}

		$('.chips-autocomplete').material_chip({
			autocompleteOptions: {
				data: dataSugestions,
				limit: 7,
				minLength: 1
			},
			secondaryPlaceholder: 'Consulte aqui',
			placeholder: '+Órgão'
		});
	});

	//Alerta para quando uma tag inválida é adicionada
	$('.chips').on('chip.add', function(e, chip){
		if(chips[chip.tag] === undefined)
			$("#modal-orgaos-inexistentes").modal('open');
	});

	//Ação de click no botão
	$('#btn-consultar').click(function(){
		$(".grafico-container").show();
		$(".grafico-container").empty();
		
		$(".detalhes-container").show();
		$(".detalhes-container").empty();
		
		$(".grafico-container").append("<p><div id='rangeMeses'></div>" +
				"</p><br/><br/>" +
				"<canvas id='chart'></canvas>" +
				"<br/>");
		
		//Coleta as tags que foram adicionadas
		var orgaosConsulta = []
		$.each($('.chips-autocomplete').material_chip('data'), function(index, elem){
			if(chips[elem.tag] != undefined)
				orgaosConsulta.push(chips[elem.tag]);
		});
		//console.log(orgaosConsulta);
		
		//Se apenas uma uma tag foi selecionada, redireciona para consulta antiga
		if (orgaosConsulta.length == 0){
			$("#modal-consulta").modal('open');
		}

		//Caso contrário
		else{
			//Requisição ao controlado de cosulta para vários órgãos
			$.ajax({
				type: 'POST',
				url: '/api/consulta-simples',
				data: JSON.stringify(orgaosConsulta),
				dataType: 'json',
				contentType: 'application/json',
				success: function(chart){
					labels = chart.data.labels;
					console.log(labels);
					dados = [];
					for(var i=0; i<chart.data.datasets.length; i++){
						dados.push(chart.data.datasets[i].data);
					}

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

					var grafico = new Chart(ctx, chart);

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
							for(var i=0; i<newChart.data.datasets.length; i++){
								newChart.data.datasets[i].fill = true;
								newChart.data.datasets[i].backgroundColor = newChart.data.datasets[i].borderColor;
							}
						}else{
							newChart.type = 'line'; //Caso contrário, altera para o tipo 'line'
							for(var i=0; i<newChart.data.datasets.length; i++){
								newChart.data.datasets[i].fill = false;
								//newChart.data.datasets[i].backgroundColor = newChart.data.datasets[i].borderColor;
							}
						}

						grafico.destroy();
						grafico = new Chart(ctx, newChart);

					});
				}
			});
			
			$(".detalhes-container").append("<p>Clique para ver detalhes:</p>");
			for(var i = 0; i<orgaosConsulta.length; i++){
				$(".detalhes-container").append("<a target='_blank' class='waves-effect waves-light btn red' href='/"+orgaosConsulta[i].tipo+"/"+orgaosConsulta[i].id+"'><i class='material-icons right'>open_in_new</i>" + orgaosConsulta[i].nome + "</a>")
			}
			
			
		}
		
		
		
		
	});
});