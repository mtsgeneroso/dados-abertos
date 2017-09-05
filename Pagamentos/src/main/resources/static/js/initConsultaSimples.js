$(document).ready(function(){
	var chips = {};
	
	$.getJSON("/api/orgaos", function(orgaos){
		var dataSugestions = {};		
		for(var i = 0; i < orgaos.length; i++){
			chips[orgaos[i].nome] = {
					"tipo":orgaos[i].tipo, 
					"id":orgaos[i].id
			};
			dataSugestions[orgaos[i].nome] = null;
		}
		
		$('.chips-autocomplete').material_chip({
		    autocompleteOptions: {
		    	data: dataSugestions,
		      limit: 5,
		      minLength: 1
		    }
		  });
	});
	
	$('.chips').on('chip.add', function(e, chip){
		if(chips[chip.tag] === undefined)
			alert("Esse elemento não existe e será ignorado na consulta.");	
	});
	
	
	$('#btn-consultar').click(function(){
		orgaosConsulta = []
		$.each($('.chips-autocomplete').material_chip('data'), function(index, elem){
			orgaosConsulta.push(chips[elem.tag]);
		});
		
		if(orgaosConsulta.length == 1){
			window.location = "/" + orgaosConsulta[0].tipo + "/" + orgaosConsulta[0].id;
		}
		
		console.log(orgaosConsulta);
	});
	
});