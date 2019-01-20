package dal;

import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOJPA<T, ID> implements IDAO<T, ID> {
	private static final String P_UNIT = "dcma-jpa";
	EntityManagerFactory emf;
	public DAOJPA() {
		emf = Persistence.createEntityManagerFactory( P_UNIT );
	}

	@Override
	public void create( T object ) throws SQLException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist( object);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update( T object ) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		object = em.merge( object);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Set<T> findAll() throws SQLException {
		return null;
	}

	@Override
	public T findById( ID id ) {
		EntityManager em = emf.createEntityManager();
		///T entity = em.find( , id );
		return null;
	}
}
