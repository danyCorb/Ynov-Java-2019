package model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	private String pseudo;
	
	@OneToMany
	private Collection<Partie> parties;

	public User() {
		super();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
   
}
