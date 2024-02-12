package org.generation.italy.JDBC_ente_sportivo.model.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Gara {

	private Long idGara;

	private LocalDateTime dataGara;

	private String luogo;

	public Gara(Long idGara, LocalDateTime dataGara, String luogo) {
		super();
		this.idGara = idGara;
		this.dataGara = dataGara;
		this.luogo = luogo;
	}

	public Gara(LocalDateTime dataGara, String luogo) {
		super();
		this.dataGara = dataGara;
		this.luogo = luogo;
	}

	public LocalDateTime getDataGara() {
		return dataGara;
	}

	public void setDataGara(LocalDateTime dataGara) {
		this.dataGara = dataGara;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public Long getIdGara() {
		return idGara;
	}

	@Override
	public String toString() {
		return "Gara [idGara=" + idGara + ", dataGara=" + dataGara + ", luogo=" + luogo + "]";
	}

}
