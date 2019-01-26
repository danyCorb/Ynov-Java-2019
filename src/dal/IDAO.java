package dal;
import java.sql.SQLException;
import java.util.Set;

/**
 * 
 * @author Dany CORBINEAU / Mathis AUBRY
 * Interface Dao to implements creation and update model methods
 *
 * @param <T>
 * @param <ID>
 */
public interface IDAO<T, ID> {
	
	void create( T object ) throws SQLException;
	
	void update( T object );
	
}