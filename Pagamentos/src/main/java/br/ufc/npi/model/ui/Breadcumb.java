package br.ufc.npi.model.ui;

public class Breadcumb {
	
	public static Breadcumb inicio = new Breadcumb("0", "INÍCIO", "/");
	
	private String id;
	private String titulo;
	private String url;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Breadcumb(String id, String titulo, String url) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Breadcumb){
			Breadcumb objB = (Breadcumb)obj;
			return objB.id == this.id;
		}
		return false;
	}
	
	
}
