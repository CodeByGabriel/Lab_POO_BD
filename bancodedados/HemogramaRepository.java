import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;

public class HemogramaRepository {
    private static Database database;
    private static Dao<Hemograma, Integer> dao;

    public HemogramaRepository(Database database) {
        setDatabase(database);
    }

    public static void setDatabase(Database database) {
        HemogramaRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Hemograma.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Hemograma.class);
        } catch(SQLException e) {
            System.out.println(e);
        }            
    }

    public Hemograma create(Hemograma hemograma) {
        try {
            dao.create(hemograma);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return hemograma;
    }

    public void update(Hemograma hemograma) {
        try {
            dao.update(hemograma);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Hemograma hemograma) {
        try {
            dao.delete(hemograma);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Hemograma loadFromId(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Hemograma> loadAll() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
