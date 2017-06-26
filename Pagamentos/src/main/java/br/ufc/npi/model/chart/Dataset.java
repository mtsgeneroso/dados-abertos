package br.ufc.npi.model.chart;

public class Dataset {

	private String label;
	private String backgroundColor = "rgba(50,50,50,.8)";
	private String borderColor = "rgb(0,0,0)";
	private Double[] data;
	private boolean fill = true;
	
	public Dataset(String label, Double[] data) {
		super();
		this.label = label;
		this.data = data;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public Double[] getData() {
		return data;
	}
	public void setData(Double[] data) {
		this.data = data;
	}
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
}
