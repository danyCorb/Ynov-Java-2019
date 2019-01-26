package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @author Dany CORBINEAU / Mathis AUBRY
 * A calcul is composed by responses and operations
 */
@Entity
@Table(name = "calcul")
public class Calcul implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	/**
	 * Player response
	 */
	@OneToOne
	protected Response response;
	
	/**
	 * Calcul operations
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Operation> operations;

	
	/**
	 * Init all Collection and generate new Operations
	 */
	public Calcul() {
		super();
		this.operations = new HashSet<>();
		
		int nbOperation = (new Random()).nextInt(2)+2;
		for(int j=0;j<nbOperation;++j) {
			Operation op = new Operation();
			op.generate(-20, 50);
			operations.add(op);
		}
	}
	
	/**
	 * Get all operations string and concat it to make a calul string
	 */
	public String toString()
	{
		String returnString ="";
		for(int j=0 ; j<this.operations.size();++j) {
			returnString+=" ( "+((Operation)this.operations.toArray()[j]).toString()+" ) ";
			if(j<this.operations.size()-1) {
				returnString+=" + ";
			}
		}
		return returnString;
	}
	
	/**
	 * Test if the response is correct
	 * @return false or true according to the test
	 */
	public boolean isValid()
	{
		if(this.calculate() == response.getValue())
		{
			return true;
		}
		return false;
	}

	/**
	 * Send the calcul value
	 * @return final value
	 */
	public int getValue()
	{
		return this.calculate();
	}
	
	/**
	 * Add all operator value to calculate the final result
	 * @return int , the final result
	 */
	private int calculate()
	{
		int value = 0;
		for(int j=0 ; j<this.operations.size();++j) {
			value+=((Operation)this.operations.toArray()[j]).calculate();
		}
		return value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	
	
   
}
