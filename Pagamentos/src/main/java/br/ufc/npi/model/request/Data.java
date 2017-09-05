package br.ufc.npi.model.request;

public class Data {

	private String[] labels;
	private Dataset[] datasets;
	
	public Data(String[] labels, Dataset[] datasets) {
		super();
		this.labels = labels;
		this.datasets = datasets;
	}
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	public Dataset[] getDatasets() {
		return datasets;
	}
	public void setDatasets(Dataset[] datasets) {
		this.datasets = datasets;
	}
	
}
