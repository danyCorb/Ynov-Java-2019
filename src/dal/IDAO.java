package dal;
import java.sql.SQLException;
import java.util.Set;

public interface IDAO<T, ID> {
	
	void create( T object ) throws SQLException;
	
	void update( T object );
	
	Set<T> findAll() throws SQLException;
	
	T findById( ID id );
}