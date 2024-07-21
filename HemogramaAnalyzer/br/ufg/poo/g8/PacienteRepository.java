package br.ufg.poo.g8;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;

public class PacienteRepository {
    private static Database database;
    private static Dao<Paciente, Integer> dao;

    public PacienteRepository(Database database) {
        setDatabase(database);
    }

    public static void setDatabase(Database database) {
        PacienteRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Paciente.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Paciente.class);
        } catch(SQLException e) {
            System.out.println(e);
        }            
    }

    public Paciente create(Paciente paciente) {
        try {
            dao.create(paciente);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return paciente;
    }

    public void update(Paciente paciente) {
        try {
            dao.update(paciente);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Paciente paciente) {
        try {
            dao.delete(paciente);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Paciente loadFromId(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Paciente> loadAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
