package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

/**
 * 
 * @author Dany CORBINEAU / Mathis AUBRY
 * Is use to store player partie composed by many calcul
 */
@Entity
@Table(name = "partie")
public class Partie implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	/**
	 * All calculs
	 */
	@OneToMany
	private Collection<Calcul> calculs;

	/**
	 * Init partie object
	 */
	public Partie() {
		super();
		this.calculs = new HashSet<Calcul>();
	}
	
	
	/**
	 * check all calculs to calculate player score and send it 
	 * @return player score
	 */
	public int getScore()
	{
		int score = 0;
		for (int j=0; j<this.calculs.size(); ++j) {
		    score += ((Calcul) this.calculs.toArray()[j]).isValid()? 1 : 0;
		}
		return score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Calcul> getCalculs() {
		return calculs;
	}

	public void setCalculs(Collection<Calcul> calculs) {
		this.calculs = calculs;
	}

}
