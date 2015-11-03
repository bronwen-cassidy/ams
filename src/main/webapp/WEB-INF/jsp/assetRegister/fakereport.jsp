<div class="container">

	<div class="row">
	
		<div class="col-lg-4">
	
			<div class="line-chart" style="height:300px;width:100%"></div>	
		
		</div>
	
		<div class="col-lg-4">
		
			<div class="pie-chart" style="height:300px;width:100%"></div>	
		
		</div>
	
		<div class="col-lg-4">
		
			<div class="pie-chart2" style="height:300px;width:100%"></div>
		
		</div>
	
	</div>	

</div>

<script>
$(document).ready(function() {
	
	var line_chart = $('.line-chart');
	
	var data = [ [1,1.5], [2,3.4], [3,2] ];
	
	$.plot(line_chart, [ {data: data, lines:{show:true} } ]);
	
	var pie_chart = $('.pie-chart')
	  var pie_data = [
	    { label: "Forklift",  data: 19.5, color: "#4572A7"},
	    { label: "Jackhammer",  data: 4.5, color: "#80699B"},
	    { label: "Digger",  data: 36.6, color: "#AA4643"},
	    { label: "Trailer",  data: 2.3, color: "#3D96AE"},
	    { label: "Spanner",  data: 36.3, color: "#89A54E"},
	    { label: "Welder",  data: 0.8, color: "#3D96AE"}

	  ];

	  $.plot(pie_chart, pie_data, {
	    series: {
	        pie: {
	            show: true
	        }
	    },
	         legend: {
	            labelBoxBorderColor: "none"
	         }
	    });
	  
	  var pie_chart2 = $('.pie-chart2')
	  var pie_data2 = [
	    { label: "personal",  data: 19.5, color: "#FF0000"},
	    { label: "small business",  data: 4.5, color: "#0000FF"},
	    { label: "large business",  data: 36.6, color: "#FFFF00"},

	  ];

	  $.plot(pie_chart2, pie_data2, {
	    series: {
	        pie: {
	            show: true
	        }
	    },
	         legend: {
	            labelBoxBorderColor: "none"
	         }
	    });
});
</script>