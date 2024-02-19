package org.generation.italy.JDBC_ente_sportivo.model.entity;

public class VelocistaIscrizioneGara {
   private String nominativo;
   
   private int eta;

    private long idGara;
public VelocistaIscrizioneGara(String nominativo, int eta, Long idGara) {
	super();
	this.nominativo = nominativo;
	this.eta = eta;
	this.idGara =idGara;
}





public VelocistaIscrizioneGara(String nominativo, int eta) {
	super();
	this.nominativo = nominativo;
	this.eta=eta;

}





public String getNominativo() {
	return nominativo;
}

public int getEta() {
	return eta;
}

public long getIdGara() {
	return idGara;
}

public void setIdGara(long idGara) {
	this.idGara = idGara;
}
   
   
}
