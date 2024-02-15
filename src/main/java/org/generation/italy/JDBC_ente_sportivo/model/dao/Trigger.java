package org.generation.italy.JDBC_ente_sportivo.model.dao;

import org.generation.italy.JDBC_ente_sportivo.model.entity.Iscrizione;

public class Trigger {
 
	public static GaraDao garaDao;
	public static VelocistaDao velocistaDao;
	public static IscrizioneDao iscrizioneDao;
	public static PartecipazioneDao partecipazioneDao;
	
	public static void checkLimiteIscrizioni(Iscrizione iscrizione) {
		
		int quantita =  Integer.parseInt(iscrizione.getCodiceFiscale());
		quantita++;
		if (quantita<=6)
		{
			
		}
		
	}
	
	
	
}
