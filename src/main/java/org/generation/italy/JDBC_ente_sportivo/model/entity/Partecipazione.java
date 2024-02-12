package org.generation.italy.JDBC_ente_sportivo.model.entity;

public class Partecipazione {

	private String codiceFiscale;

	private Long idGara;

	private Float tempo;

	public Partecipazione(String codiceFiscale, Long idGara, Float tempo) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.idGara = idGara;
		this.tempo = tempo;
	}

	public Partecipazione(String codiceFiscale, Float tempo) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.idGara = idGara;
		this.tempo = tempo;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public Long getIdGara() {
		return idGara;
	}

	public Float getTempo() {
		return tempo;
	}

	@Override
	public String toString() {
		return "Partecipazione [codiceFiscale=" + codiceFiscale + ", idGara=" + idGara + ", tempo=" + tempo + "]";
	}

}
