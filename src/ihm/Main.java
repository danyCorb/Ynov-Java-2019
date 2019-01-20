package ihm;

import java.util.Scanner;

import dal.UserJPA;
import model.Calcul;
import model.Partie;
import model.Response;
import model.User;

public class Main {

	private User player = new User();
	private Scanner sc =new Scanner(System.in);
	
	public static void main(String [] args)
	{
		Main m = new Main();
		m.loadPlayer();
		m.playGame();
	}
	
	public void loadPlayer()
	{
		System.out.println("Votre pseudo ?");
		String pseudo = sc.nextLine();
		User u =new User();
		u.setPseudo(pseudo);
		UserJPA daoUser = new UserJPA();
		this.player = daoUser.findByName(u); 
	}
	
	public void playGame()
	{
		Partie p = new Partie();
		int nbQuestion = 10;
		for(int j=0;j<10;++j) {
			Calcul c =new Calcul();
			System.out.println(c.toString()+" ?");
			String response = sc.nextLine();
			try {
				p.getCalculs().add(c);
				Response r = new Response(Integer.valueOf(response));
				p.getResponses().add(r);
			}
			catch(Exception e) {
				
			}
		}
		System.out.println("Score : "+p.getScore());
		this.player.getParties().add(p);
	}
   
}
