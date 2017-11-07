function initCamposBusca(tiposOrgaos){
	chips = {}
	console.log(tiposOrgaos);
	tiposOrgaos.forEach(function(tipoOrgao, index){
		var urlAPI = "/api/"+tipoOrgao;
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

function gerarCollapsible(orgaos){
	tags_collapsible = "";
	for(var i = 0; i<orgaos.length; i++){
		tags_collapsible += "<li>" +
		"<div class='collapsible-header'>" +
		"<i class='material-icons'>chevron_right</i>" + orgaos[i].nome + " - R$ " + orgaos[i].valorPagamentos +
		"</div>" +
		"<div class='collapsible-body'>";

		if(orgaos[i].subordinados != null){
			tags_collapsible +=
				"<ul class='collapsible' data-collapsible='expandable'>" +
				gerarCollapsible(orgaos[i].subordinados) +
				"</ul>";
		}

		else{
			tags_collapsible += "<p>Sem dados</p>";
		}

		tags_collapsible += "</div></li>";
	}
	return tags_collapsible;
}

$(document).ready(function(){
	$('.modal').modal();
	$("#itens-hierarquia, #hierarquia").sortable({
		connectWith: ".conexaoHierarquia",
		placeholder: "item-placeholder",
		forcePlaceholderSize: false
	}).disableSelection();
	$(".item-content").hide();
	$(".filtro-orgaos").hide();
	$(".resultado-container").hide();
	$(".btn-steps").hide();
	$("#btn-prev").addClass("disabled");

});

$('#btn-prosseguir').click(function(){
	hierarquia = $("#hierarquia").sortable("toArray");

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
		ProgressBar.init(hierarquia,
			hierarquia[step],
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
			ProgressBar.init(hierarquia,
				hierarquia[step],
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
		ProgressBar.init(hierarquia,
			hierarquia[step],
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

	orgaosConsulta = pegarValoresBusca(hierarquia, chips);
	var consulta = {
		"hierarquia" : hierarquia,
		"orgaosConsulta": orgaosConsulta
	};

	$.ajax({
		type: 'POST',
		url: '/api/consulta-hierarquica',
		data: JSON.stringify(consulta),
		dataType: 'json',
		contentType: 'application/json',
		success: function(orgaos){
			$(".resultado-container").show();
			$(".consulta-container").hide();
			$(".collapsible").append(gerarCollapsible(orgaos));
			$('.collapsible').collapsible();
		}
	});

});

function expandAll(){
	$(".collapsible-header").addClass("active");
	$(".collapsible").collapsible({accordion: false});
}

function collapseAll(){
	$(".collapsible-header").removeClass(function(){
		return "active";
	});
	$(".collapsible").collapsible({accordion: true});
	$(".collapsible").collapsible({accordion: false});
}
