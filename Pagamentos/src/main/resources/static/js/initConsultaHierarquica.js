$(document).ready(function(){
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
	
	
	$('#btn-consultar').click(function(){
		console.log(orgaosConsulta);
	});
	
	
});