$(document).ready(function(){
	
	$(".resultado-container").hide();
	
	var orgaosConsulta = [];
	
	$(".btn-tag").click(
			function(){
				if($(this).hasClass("green")) {
					$(this).removeClass("green");
					$("#" + $(this).find("p").text().replace(/ /g, '_').trim()).remove();
					index = orgaosConsulta.indexOf($(this).find("p").text().replace(/ /g, '_').trim());
					if (index > -1) {
					    orgaosConsulta.splice(index, 1);
					}
				} else {
					$(this).addClass("green");
					$("#panel-tags").append("<div class='chip' id='"+ $(this).find("p").text().replace(/ /g, '_').trim() + "'>" + $(this).text() + "</div>");
					orgaosConsulta.push($(this).find("p").text().replace(/ /g, '_').trim());
				}
			}
	);
	
	
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
	
	$('#btn-consultar').click(function(){
		
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
