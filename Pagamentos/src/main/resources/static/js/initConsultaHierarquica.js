function initCamposBusca(tiposOrgaos){
	chips = {}
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
	orgaosConsulta = {};
	hierarquia.forEach(function(tipoOrgao, index){
		orgaosConsulta[tipoOrgao] = [];
		$.each($("#chips-"+tipoOrgao).material_chip('data'), function(index, elem){
			if(chips[tipoOrgao][elem.tag] != undefined)
				orgaosConsulta[tipoOrgao].push(chips[tipoOrgao][elem.tag]);
		});
		
	});
	
	return orgaosConsulta;
}

$(document).ready(function(){

	$('.modal').modal();

	$("#itens-hierarquia, #hierarquia").sortable({
		connectWith: ".conexaoHierarquia",
		placeholder: "item-placeholder",
		forcePlaceholderSize: false
	}).disableSelection();

	$(".item-content").hide();
	
	tiposOrgaos = ["orgaos-superiores", "orgaos-subordinados", "unidades-gestoras", "acoes", "programas", "favorecidos"];

	chips = initCamposBusca(tiposOrgaos);
	assistirCamposBusca(tiposOrgaos, chips);	

	$(".oculto").hide();
	$(".resultado-container").hide();

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

	var orgaosConsulta = [];

	$('#btn-consultar').click(function(){
		$(".consulta-container").hide();

		var hierarquia = $("#hierarquia").sortable("toArray");
		orgaosConsulta = pegarValoresBusca(hierarquia, chips);
		
		console.log(hierarquia);
		console.log(orgaosConsulta);

		$.ajax({
			type: 'POST',
			url: '/api/consulta-hierarquica',
			data: JSON.stringify(orgaosConsulta),
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

	$(".item-config").click(function(){
		tagI = $(this).closest(".item").find(".item-config").find("i");
		$(".item-content").not($(this).closest(".item").find(".item-content")).hide("fast");
		$(".item-config").find("i").not(tagI).text("arrow_drop_down");
		$(this).closest(".item").find(".item-content").toggle("fast");
		if(tagI.text() === "arrow_drop_down"){
			tagI.text("arrow_drop_up");
		}else{
			tagI.text("arrow_drop_down");
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
