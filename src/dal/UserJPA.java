package dal;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import model.User;
/**
 * 
 * @author Dany CORBINEAU / Mathis AUBRY
 * User JAp is use to find a player by is name and get all player ordered by max score
 */
public class UserJPA extends DAOJPA<User,Long>{
	
	public UserJPA() {
		super();
	}
	
	/**
	 * find a player by is name. if not : return null
	 * @param u the user object with a name set
	 * @return a user if is find
	 */
	public User findByName(User u)
	{
		EntityManager em = emf.createEntityManager();
		Session sess = em.unwrap(Session.class);
		User userToReturn = u;
		
		Criteria crit = sess.createCriteria(User.class);
		crit.add(Restrictions.eq("pseudo", u.getPseudo()));
		List<User> users = crit.list();
		if(users.size()>0) {
			this.update(users.get(0));
			userToReturn= users.get(0);
		} else {
			try {
				this.create(u);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		sess.close();
		em.close();
		return userToReturn;
	}
	
	/**
	 * get all player but order by max score
	 * @return
	 */
	public List<User> getClassement()
	{
		String hql = "FROM User u ORDER BY u.maxScore DESC";
		EntityManager em = emf.createEntityManager();
		Session sess = em.unwrap(Session.class);
		Query<User> query = sess.createQuery(hql);
		
		return query.getResultList();
	}
}
