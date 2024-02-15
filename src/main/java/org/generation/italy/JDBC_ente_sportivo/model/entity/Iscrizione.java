package org.generation.italy.JDBC_ente_sportivo.model.entity;


import java.time.LocalDateTime;

public class Iscrizione {
  
	
	private String codiceFiscale;

	private Long idGara;

	private LocalDateTime dataOraIscrizione;

	public Iscrizione(String codiceFiscale, Long idGara, LocalDateTime dataOraIscrizione) {
		super();
		
		this.codiceFiscale = codiceFiscale;
		this.idGara = idGara;
		this.dataOraIscrizione = dataOraIscrizione;
		
	}

	public Iscrizione(String codiceFiscale, LocalDateTime dataIscrizione) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.dataOraIscrizione = dataIscrizione;
	}
 
	public Iscrizione(String codiceFiscale, Long idGara) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.idGara = idGara;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public Long getIdGara() {
		return idGara;
	}

	public LocalDateTime getDataOraIscrizione() {
		return dataOraIscrizione;
	}

	@Override
	public String toString() {
		return "Iscrizione [codiceFiscale=" + codiceFiscale + ", idGara=" + idGara + ", dataIscrizione="
				+ dataOraIscrizione + "]";
	}


}
