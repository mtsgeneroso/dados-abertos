package br.ufc.npi.util;

public class RGBStringColor {

	private static final String[] colors = {
			"rgb(255,0,0)",
			"rgb(0,0,255)",
			"rgb(0,250,0)",
			"rgb(200,200,0)",
			"rgb(51,204,255)",
			"rgb(255,102,0)",
			"rgb(0,0,0)",
			"rgb(153,255,153)",
			"rgb(51,204,204)",
			"rgb(102,51,0)"
	};
	
	public static String getColor(int index){
		return colors[(index%colors.length)];
	}
	
}
