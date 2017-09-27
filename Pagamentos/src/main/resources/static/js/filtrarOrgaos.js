$("#filter").keyup(function(){
	var texto = $("#filter").val().toUpperCase();
	if(texto == ''){
		$(".card-panel").show();
		$(".card-panel").parent().parent().addClass("col s12 m3 l2");
	}else{
		$(".card-panel").hide();
		$(".card-panel").parent().parent().removeClass("col s12 m3 l2");
		$('.card-panel:contains("'+texto+'")').show();
	}
});