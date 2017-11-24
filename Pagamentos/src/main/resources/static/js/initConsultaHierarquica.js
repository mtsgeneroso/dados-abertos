function initCamposBusca(tiposOrgaos){
	chips = {}
	tiposOrgaos.forEach(function(tipoOrgao, index){
		var urlAPI = "/api/"+tipoOrgao+"/";
		chips[tipoOrgao] = {};

		$.getJSON(urlAPI, function(orgaos){
			var dataSugestions = {};

			orgaos.forEach(function(orgao, index){
				chips[tipoOrgao][orgao.nome] = {
						"tipo":orgao.tipo,
						"id":orgao.id,
						"nome":orgao.nome
				};
				dataSugestions[orgao.nome] = null;
			});

			$("#chips-"+tipoOrgao).material_chip({
				autocompleteOptions: {
					data: dataSugestions,
					limit: 7,
					minLength: 1
				},
				secondaryPlaceholder: "Consulte aqui",
				placeholder: "+ Órgão"
			});
		});

	});

	return chips;
}

function assistirCamposBusca(tiposOrgaos, chips){
	tiposOrgaos.forEach(function(tipoOrgao, index){
		$("#chips-"+tipoOrgao).on("chip.add", function(e, chip){
			if(chips[tipoOrgao][chip.tag] === undefined)
				$("#modal-orgaos-inexistentes").modal('open');
		});
	});
}

function pegarValoresBusca(hierarquia, chips){
	orgaosConsulta = [];
	hierarquia.forEach(function(tipoOrgao, index){
		$.each($("#chips-"+tipoOrgao).material_chip('data'), function(index, elem){
			if(chips[tipoOrgao][elem.tag] != undefined)
				orgaosConsulta.push(chips[tipoOrgao][elem.tag]);
		});

	});

	return orgaosConsulta;
}

function gerarTabela(orgaos, hierarquia){
	tags = "<table id='tableResultado' class='responsive-table bordered'>";
	tags += "<thead><tr class='tablesorter-headerRow'>";
	for(var i = 0; i < hierarquia.length; i++){
		tags += "<th class='main-color-black-text'>" + hierarquia[i] + "</th>";
	}
	tags += "<th>Valor (R$)</th>";
	tags += "</tr></thead>";

	tags += "<tfoot><tr class='tablesorter-ignoreRow'>";
	for(var i = 0; i < hierarquia.length; i++){
		tags += "<th class='main-color-black-text'>" + hierarquia[i] + "</th>";
	}
	tags += "<th>Valor (R$)</th></tr>";
	tags += `<tr class="tablesorter-ignoreRow">
      <th colspan="` + parseInt(hierarquia.length + 1) + `" class="ts-pager form-horizontal">
        <button type="button" class="btn first main-color-black"><i class="small material-icons">first_page</i></button>
        <button type="button" class="btn prev main-color-black"><i class="small material-icons">navigate_before</i></button>
        <span class="pagedisplay"></span>
        <!-- this can be any element, including an input -->
        <button type="button" class="btn next main-color-black"><i class="small material-icons">navigate_next</i></button>
        <button type="button" class="btn last main-color-black"><i class="small material-icons">last_page</i></button>
		<select class="pagesize browser-default" title="Selecione número de linhas">
          <option selected="selected" value="10">10</option>
          <option value="20">20</option>
          <option value="30">30</option>
          <option value="40">40</option>
        </select>
        <select class="pagenum browser-default" title="Número de páginas"></select>
	 </th>
    </tr>`;

	tags += "</tfoot>";

	tags += "<tbody>";
	for(var i = 0; i < orgaos.length; i++){
		tags += "<tr>";
		for(var j = 0; j < orgaos[i].length; j++){
			if(j == orgaos[i].length -1){
				tags += "<td class='celula'>" + orgaos[i][j] + "</td>";
			}
			else{
				tags += "<td>" + orgaos[i][j] + "</td>";
			}
		}
		tags += "</tr>";
	}
	tags += "</tbody>";
	tags += "</table>"
	return tags;
}

$(document).ready(function(){
	$('.modal').modal();
	$("#loadChart").hide();
	$("#itens-hierarquia, #hierarquia").sortable({
		connectWith: ".conexaoHierarquia",
		placeholder: "item-placeholder"//,
		//forcePlaceholderSize: false
	});
	$("#itens-hierarquia, #hierarquia").disableSelection();
	$(".item-content").hide();
	$(".filtro-orgaos").hide();
	$(".resultado-container").hide();
	$(".btn-steps").hide();
	$("#btn-prev").addClass("disabled");
});

$('#btn-prosseguir').click(function(){
	hierarquia = $("#hierarquia").sortable("toArray");
	hierarquiaUI = [];
	hierarquia.forEach(function(tipoOrgao, index){
		hierarquiaUI.push(tipoOrgaoGovernamental[tipoOrgao]);
	});

	if(hierarquia.length == 0){
		$("#modal-hierarquia-vazia").modal("open");
	}
	else{
		if(hierarquia.length == 1){
			$("#btn-next").addClass("disabled");
		}
		$(".hierarquia-container").hide();
		$(".btn-steps").show();

		step = 0;
		$(".passos-consulta").show();
		$("#"+hierarquia[step]).show();

		chips = initCamposBusca(hierarquia);
		assistirCamposBusca(hierarquia, chips);

		ProgressBar.singleStepAnimation = 0;
		ProgressBar.init(hierarquiaUI,
				hierarquiaUI[step],
				"progress-bar-wrapper"
		);

		$("#" + hierarquia[step] + "-filtro").show();

	}

});

$("#btn-next").click(function(){
	$("#btn-prev").removeClass("disabled");
	if(step < hierarquia.length){
		step++;
		//Limpa a barra atual
		$(".progress-bar-wrapper").empty();
		//Constroi nova barra no passo seguinte
		ProgressBar.init(hierarquiaUI,
				hierarquiaUI[step],
				"progress-bar-wrapper"
		);
		if(step >= hierarquia.length-1){
			$(this).addClass("disabled");
		}
		else{
			$(this).removeClass("disabled");
		}
	}
	$(".filtro-orgaos").hide();
	$("#" + hierarquia[step] + "-filtro").show();
});

$("#btn-prev").click(function(){
	$("#btn-next").removeClass("disabled");
	if(step >= 0){
		step--;
		//Limpa a barra atual
		$(".progress-bar-wrapper").empty();
		//Constroi nova barra no passo seguinte
		ProgressBar.init(hierarquiaUI,
				hierarquiaUI[step],
				"progress-bar-wrapper"
		);
		if(step <= 0){
			$(this).addClass("disabled");
		}
		else{
			$(this).removeClass("disabled");
		}
	}
	$(".filtro-orgaos").hide();
	$("#" + hierarquia[step] + "-filtro").show();
});

$("#btn-consultar").click(function(){
	$(".passos-consulta").hide();
	$("#loadChart").show();
	orgaosConsulta = pegarValoresBusca(hierarquia, chips);
	var consulta = {
		"hierarquia" : hierarquia,
		"orgaosConsulta" : orgaosConsulta
	}
	$.ajax({
		type: 'POST',
		url: '/api/consulta-hierarquica',
		data: JSON.stringify(consulta),
		dataType: 'json',
		contentType: 'application/json',
		success: function(orgaos){
			$(".resultado-container").show();
			$(".consulta-container").hide();
			$("#resultados").append(gerarTabela(orgaos, hierarquiaUI));
			$("table").tablesorter({
				theme : "materialize",
				widthFixed: true,
				widgets : ["filter", "zebra"],
				widgetOptions : {
					zebra : ["even", "odd"],
					filter_reset : ".reset"
				}
			}).tablesorterPager({
				container: $(".ts-pager"),
				cssGoto  : ".pagenum",
				removeRows: false,
				output: '{startRow} - {endRow} / {filteredRows} ({totalRows})'
			});
			$(".celula").formatCurrency();
			$("#loadChart").hide();
		}
	});
});
