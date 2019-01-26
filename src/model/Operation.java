package model;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Dany CORBINEAU / Mathis AUBRY
 * Use to store a operation with 2 number
 */
@Entity
@Table(name = "operation")
public class Operation implements Serializable{
	
	/**
	 * All operation constant
	 * operator and number range
	 */
	private final static String operators[] = {"+", "-", "*", "/"};
	private final static int max_number = 10;
	private final static int min_number = -10;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	/**
	 * 2 numbers and the operator
	 */
	int first_value;
	int second_value;
	String opertor;
	
	
	public Operation() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFirst_value() {
		return first_value;
	}

	public void setFirst_value(int first_value) {
		this.first_value = first_value;
	}

	public int getSecond_value() {
		return second_value;
	}

	public void setSecond_value(int second_value) {
		this.second_value = second_value;
	}

	public String getOpertor() {
		return opertor;
	}

	public void setOpertor(String opertor) {
		this.opertor = opertor;
	}

	/**
	 * Generate a operation with a result between minValue and maxValue
	 * @param minValue min result value
	 * @param maxValue max result value
	 */
	public void generate(int minValue, int maxValue)
	{
		int value;
		Random r= new Random();
		do{
			this.first_value=(int) (r.nextFloat()*(max_number - min_number)+min_number);
			this.second_value=(int) (r.nextFloat()*(max_number - min_number)+min_number);
			this.opertor = this.generateOperator();
			try {
				value = calculate();
			}
			catch(Exception e) {
				value = maxValue+1;
			}
			
		}while(value<minValue || value>maxValue);
		
	}
	
	/**
	 * get a random operator
	 * @return operator in array to string
	 */
	private String generateOperator() {
		int index = (int) ((new Random()).nextFloat()*operators.length);
		return operators[index];
	}
	
	/**
	 * get the operation to print it
	 */
	public String toString() {
		if(this.opertor.compareTo("+")==0) {
			return this.first_value +" + "+ this.second_value;
		} else if(this.opertor.compareTo("-")==0) {
			return this.first_value +" - " +this.second_value;
		} else if(this.opertor.compareTo("*")==0) {
			return this.first_value+" * "+this.second_value;
		} else if(this.opertor.compareTo("/")==0) {
			return "FLOOR("+this.first_value+" / " +this.second_value+")";
		}
		return "0";
	}
	
	/**
	 * Calculate the result of the operator 
	 * @return operator result
	 */
	public int calculate() {
		if(this.opertor.compareTo("+")==0) {
			return this.first_value + this.second_value;
		} else if(this.opertor.compareTo("-")==0) {
			return this.first_value - this.second_value;
		} else if(this.opertor.compareTo("*")==0) {
			return this.first_value * this.second_value;
		} else if(this.opertor.compareTo("/")==0) {
			return (int) Math.floor(this.first_value / this.second_value);
		}
		return 0;
	}
	
	
	
}
