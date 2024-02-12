package org.generation.italy.JDBC_ente_sportivo.model.entity;

public class Velocista {

	private String codiceFiscale;

	private String nominativo;

	private Integer eta;

	private Integer altezza;

	private Float peso;

	public Velocista(String codiceFiscale, String nominativo, Integer eta, Integer altezza, Float peso) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.nominativo = nominativo;
		this.eta = eta;
		this.altezza = altezza;
		this.peso = peso;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getNominativo() {
		return nominativo;
	}

	public Integer getEta() {
		return eta;
	}

	public Integer getAltezza() {
		return altezza;
	}

	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Velocista [codiceFiscale=" + codiceFiscale + ", nominativo=" + nominativo + ", eta=" + eta
				+ ", altezza=" + altezza + ", peso=" + peso + "]";
	}

}