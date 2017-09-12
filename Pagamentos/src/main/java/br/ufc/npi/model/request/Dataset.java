package br.ufc.npi.model.request;

import br.ufc.npi.util.RGBStringColor;

public class Dataset {
	
	private String label;
	private String borderColor = RGBStringColor.getColor(0);
	private Double[] data;
	private boolean fill = false;
	
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
