package org.generation.italy.JDBC_ente_sportivo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.JDBC_ente_sportivo.model.entity.Gara;
import org.generation.italy.JDBC_ente_sportivo.model1.EnteSportivoModelException;

public class GaraDao extends ADao {

	public GaraDao(Connection jdbcConnectionToDatabase) {
		super(jdbcConnectionToDatabase);
		// TODO Auto-generated constructor stub
	}

	private List<Gara> loadGaraByQuery(PreparedStatement preparedstatement) throws EnteSportivoModelException {

		List<Gara> elencoGara = new ArrayList<Gara>();

		try {

			ResultSet rsSelect = preparedstatement.executeQuery();

			while (rsSelect.next()) {

				Long idGara = rsSelect.getLong("id_gara");
				if (rsSelect.wasNull()) {
					idGara = (long) 0;
				}

				LocalDateTime dataOraGara = rsSelect.getTimestamp("data_ora_gara").toLocalDateTime();
				if (rsSelect.wasNull()) {
					dataOraGara = LocalDateTime.of(LocalDate.of(0, 0, 0), LocalTime.of(0, 0, 0));
				}

				String luogo = rsSelect.getString("luogo");
				if (rsSelect.wasNull()) {
					luogo = "";

				}
				Gara gara = new Gara(idGara, dataOraGara, luogo);
				elencoGara.add(gara);

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("GaraDao ===> loadGara" + sqlException.getMessage());
			// normalizzazione dell'eccezione SQLException

		}

		return elencoGara;

	}

	public Gara loadGaraByPrimaryKey(Long idGara) throws EnteSportivoModelException {

		Gara Gara = null;

		try {

			List<Gara> elencoGara = new ArrayList<Gara>();

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromGaraByPrimaryKey);

			preparedStatement.setLong(1, idGara);

			elencoGara = loadGaraByQuery(preparedStatement);

			if (elencoGara.size() == 1) {
				Gara = elencoGara.get(0);
			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("GaraDao -> loadGaraByPrimaryKey -> " + sqlException.getMessage());
		}

		return Gara;
	}

	public List<Gara> loadGara() throws EnteSportivoModelException {

		Gara Gara = null;

		List<Gara> elencoGara = new ArrayList<Gara>();

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromGara);

			elencoGara = loadGaraByQuery(preparedStatement);

			if (elencoGara.size() == 1) {
				Gara = elencoGara.get(0);

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("GaraDao -> loadElencoGara -> " + sqlException.getMessage());
		}

		return elencoGara;
	}

	
	
	public List<Gara> loadGaraOrderByLuogo() throws EnteSportivoModelException {

		Gara Gara = null;

		List<Gara> elencoGara = new ArrayList<Gara>();

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.selectFromGaraOrderByLuogo);

			elencoGara = loadGaraByQuery(preparedStatement);

			if (elencoGara.size() == 1) {
				Gara = elencoGara.get(0);

			}

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("GaraDao -> loadElencoGara -> " + sqlException.getMessage());
		}

		return elencoGara;
	}
	
	
	public void addGara(Gara gara) throws EnteSportivoModelException {

		try {

			PreparedStatement preparedStatement = this.jdbcConnectionToDatabase
					.prepareStatement(QueryCatalog.insertGara);

			preparedStatement.setTimestamp(1, Timestamp.valueOf(gara.getDataGara()));
			preparedStatement.setString(2, gara.getLuogo());

			preparedStatement.executeUpdate();

		} catch (SQLException sqlException) {

			throw new EnteSportivoModelException("GaraDao -> addGara  -> " + sqlException.getMessage());

		}
	}
}
