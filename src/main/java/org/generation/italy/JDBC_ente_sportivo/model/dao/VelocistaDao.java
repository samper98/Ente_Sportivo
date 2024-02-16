package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.JDBC_ente_sportivo.model.EnteSportivoModelException;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Partecipazione;
import org.generation.italy.JDBC_ente_sportivo.model.entity.Velocista;

public class VelocistaDao extends ADao {

	public VelocistaDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
		// TODO Auto-generated constructor stub
	}

	private List<Velocista> loadVelocistaByQuery(PreparedStatement preparedstatement) throws EnteSportivoModelException {

		List<Velocista> elencoVelocista = new ArrayList<Velocista>();

		try {

			ResultSet rsSelect = preparedstatement.executeQuery();

			while (rsSelect.next()) {

				String nominativo = rsSelect.getString("nominativo");
				if (rsSelect.wasNull()) {
					nominativo = "";
				}

				Integer eta = rsSelect.getInt("eta");
				if (rsSelect.wasNull()) {
					eta = 0;
				}

				String codiceFiscale = rsSelect.getString("codice_fiscale");
				if (rsSelect.wasNull()) {
					codiceFiscale = "";
				}

				Integer altezza = rsSelect.getInt("altezza");
				if (rsSelect.wasNull()) {
					altezza = 0;
				}

				Float peso = rsSelect.getFloat("peso");
				if (rsSelect.wasNull()) {
					peso = 0f;
				}

				Velocista velocista = new Velocista(codiceFiscale, nominativo, eta, altezza, peso);
				elencoVelocista.add(velocista);
        
			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("VelocistaDao ===> loadVelocista" + sqlException.getMessage());
			// normalizzazione dell'eccezione SQLException

		}

		return elencoVelocista;

	}

	public Velocista loadVelocistaByPrimaryKey(String codiceFiscale) throws EnteSportivoModelException {

		Velocista velocista = null;

		try {

			List<Velocista> elencoVelocista = new ArrayList<Velocista>();

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromVelocistaByPrimaryKey);

			preparedStatement.setString(1, codiceFiscale);

			elencoVelocista = loadVelocistaByQuery(preparedStatement);

			if (elencoVelocista.size() == 1) {
				velocista = elencoVelocista.get(0);
			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException(
					"VelocistaDao -> loadVelocistaByPrimaryKey -> " + sqlException.getMessage());
		}

		return velocista;
	}

	
	
	
	
	
	public void addVelocista(Velocista velocista) throws EnteSportivoModelException {

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.insertVelocista);

			preparedStatement.setString(1, velocista.getNominativo());
			preparedStatement.setInt(2, velocista.getEta());
			preparedStatement.setString(3, velocista.getCodiceFiscale());
			preparedStatement.setInt(4, velocista.getAltezza());
			preparedStatement.setFloat(5, velocista.getPeso());

			preparedStatement.executeUpdate();

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("VelocistaDao -> addVelocista  -> " + sqlException.getMessage());

		}
	}
}
