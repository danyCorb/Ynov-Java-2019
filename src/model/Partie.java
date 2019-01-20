package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Partie
 *
 */
@Entity
public class Partie implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	@OneToMany
	private Collection<Calcul> calculs;
	
	@OneToMany
	private Collection<Response> responses;

	public Partie() {
		super();
		this.calculs = new HashSet();
		this.responses = new HashSet();
	}
	
	public int getScore()
	{
		int score = 0;
		System.out.println(this.calculs.toArray().length+" "+ this.responses.toArray().length);
		for (int j=0; j<this.calculs.size(); ++j) {
		    score += ((Calcul) this.calculs.toArray()[j]).isValid( ((Response)this.responses.toArray()[j]).getValue() )? 1 : 0;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Collection<Response> getResponses() {
		return responses;
	}

	public void setResponses(Collection<Response> responses) {
		this.responses = responses;
	}
	
	
   
}
