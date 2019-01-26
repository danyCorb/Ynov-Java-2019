package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Dany CORBINEAU / Mathis AUBRY
 * use to store player informations 
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	/**
	 * Is pseudo (player cant have a same pseudo)
	 */ 
	private String pseudo;
	
	/**
	 * The player max score to find it easely
	 */
	private int maxScore;
	
	/**
	 * All player parties (Lazy loading beacause the player is load after is pseudo is enter so instead of load many-many data it load at call)
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Partie> parties;

	
	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	
	
	public User() {
		super();
		this.maxScore = 0;
		this.parties = new HashSet<Partie>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Collection<Partie> getParties() {
		return parties;
	}

	public void setParties(Collection<Partie> parties) {
		this.parties = parties;
	}
	
   
}
