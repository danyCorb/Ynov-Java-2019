package dal;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.User;

public class UserJPA extends DAOJPA<User,Long>{
	
	public UserJPA() {
		super();
	}
	
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
}
