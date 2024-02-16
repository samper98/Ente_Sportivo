package org.generation.italy.JDBC_ente_sportivo.model.entity;

public class VelocistaPartecipanteGara {

	
	private Long idGara;
	

	private String nominativo;
	
	private Integer eta;

	
	private Float tempo;


	public VelocistaPartecipanteGara(Long idGara, String nominativo, Integer eta, Float tempo) {
		super();
		this.idGara = idGara;
		this.nominativo = nominativo;
		this.eta = eta;
		this.tempo = tempo;
	}

	
	public String getNominativo() {
		return nominativo;
	}

	public Float getTempo() {
		return tempo;
	}

	public Long getIdGara() {
		return idGara;
	}


	public Integer getEta() {
		return eta;
	}

	
}
