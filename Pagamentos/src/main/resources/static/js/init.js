(function($){
  $(function(){

    $('.button-collapse').sideNav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

var slider1 = document.getElementById('range1');
noUiSlider.create(slider1, {
  start: [30,70],
  range: {
    'min': 0,
    'max': 100
  },
  connect: true,
  format: wNumb({
    decimals: 0,
    prefix: ''
  })
});

var ctx = $('#myChart');
$.getJSON("/teste", function(chart) {
	var chart = new Chart(ctx, chart);
});
