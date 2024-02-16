package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.util.List;

import org.generation.italy.JDBC_ente_sportivo.model.EnteSportivoModelException;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Iscrizione;



public class Trigger {
 
	public static GaraDao garaDao;
	public static VelocistaDao velocistaDao;
	public static IscrizioneDao iscrizioneDao;
	public static PartecipazioneDao partecipazioneDao;
	
	public static void checkLimiteIscrizioni(Iscrizione iscrizione) throws EnteSportivoModelException {
		
		List<Iscrizione> elencoIscrizioni =  iscrizioneDao.loadIscrizioneByIdGara(iscrizione.getIdGara());
		
		if (elencoIscrizioni.size() >= EnteSportivoConstaints.limiteNumeroIscizioni )
{
    		throw new EnteSportivoModelException ("Trigger -> checkBeforeInsertMovimento -> hai effettuato già " + EnteSportivoConstaints.limiteNumeroIscizioni + " iscrizione!");

}
		  
		
	}
	
	
	
}
