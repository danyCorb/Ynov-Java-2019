package model;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import metier.CalculGenerator;

/**
 * Entity implementation class for Entity: Calcul
 *
 */
@Entity
@Table(name = "calcul")
public class Calcul implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	protected String calculString;

	public Calcul() {
		super();
		this.calculString = CalculGenerator.getCalculGenerator().createCalc();
	}
	
	public String toString()
	{
		return this.calculString;
	}
	
	public boolean isValid(int result)
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			Object result1 = engine.eval(this.calculString);
			if(result1.toString().compareTo(String.valueOf(result))==0)
			{
				return true;
			}
		} catch (ScriptException e) {
			return false;
		}
		return false;
	}
	public String getValue()
	{
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			Object result1 = engine.eval(this.calculString);
			return result1.toString();
		} catch (ScriptException e) {
			return "";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalculString() {
		return calculString;
	}

	public void setCalculString(String calculString) {
		this.calculString = calculString;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
   
}
