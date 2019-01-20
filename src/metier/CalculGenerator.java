package metier;

import java.util.Random;

public class CalculGenerator {
	private static CalculGenerator cg=null;
	private CalculGenerator() {
	}	
	public static CalculGenerator getCalculGenerator()
	{
		if(CalculGenerator.cg != null){
			return CalculGenerator.cg;
		}
		else {
			CalculGenerator.cg = new CalculGenerator();
			return CalculGenerator.cg;
		}
	}
	
	public String createCalc()
	{
		int nbNumber = (new Random()).nextInt(3)+2;
		String finalString="";
		
		for(int j=0;j<nbNumber;++j)
		{
			finalString += (new Random()).nextInt(20)-10;
			if(nbNumber-1>j){
				finalString += " "+this.getRandOperande()+" ";
			}
		}
		return finalString;
	}
	
	private String getRandOperande()
	{
		String[] operande = {"+", "-", "*", "/"};
		return operande[(new Random()).nextInt(operande.length-1)];
	}
}
