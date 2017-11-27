function filter(classFilter){
	var texto = $("#filter").val().toUpperCase();
	if(texto == ''){
		$(classFilter).css("height", "120px");
		$(classFilter).show();		
		$(".card-panel").parent().parent().addClass("col s12 m3 l2");
	}
	else{
		$(classFilter).hide();
		$(".card-panel").parent().parent().removeClass("col s12 m3 l2");
		$(classFilter+':contains("'+texto+'")').show();
		$(classFilter).css("height", "50px");
	}
}
