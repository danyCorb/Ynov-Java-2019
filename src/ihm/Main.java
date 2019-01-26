package ihm;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dal.DAOJPA;
import dal.UserJPA;
import model.Calcul;
import model.Partie;
import model.Response;
import model.User;

/**
 * 
 * @author Dany CORBINEAU / Mathis AUBRY
 * This class is the main point of the application
 * It use user input and JPA to do the program
 * It may use spring but after many hours, we can't make it work
 */
public class Main {
	
	/**
	 * Number of Calcul to generate in one partie
	 */
	private final static int NB_QUESTION = 10;

	/**
	 * The main user and it Input interface
	 */
	private User player = new User();
	private Scanner sc =new Scanner(System.in);
	
	/**
	 * All DAO class use
	 */
	private UserJPA daoUser;
	private DAOJPA<Calcul, Long> daoCalcul;
	private DAOJPA<Response, Long> daoResponse;
	
	/**
	 * Entry point of the program
	 * @param args list of arguments send by executor
	 */
	public static void main(String [] args)
	{
		/**
		 * Init main object and his attributs
		 */
		Main m = new Main();
		m.daoUser = new UserJPA();
		m.daoCalcul = new DAOJPA<>();
		m.daoResponse = new DAOJPA<>();
		
		/**
		 * Game loop until player dont quit
		 */
		try {
			boolean userIsPlaying = true;
			m.loadPlayer();
			m.playGame();
			m.printAllMaxScore();
			
			while(userIsPlaying) {
				System.out.println("\n-----------\nMenu : \n-----------");
				System.out.println("1 : Changer le Pseudo");
				System.out.println("2 : Rejouer");
				System.out.println("3 : Quitter");
				int choix = Integer.valueOf(m.sc.nextLine());
				switch(choix) {
					case 1: m.loadPlayer(); break;
					case 2: m.playGame(); m.printAllMaxScore(); break;
					case 3: userIsPlaying=false; System.out.println("Au revoir"); break;
				}
			}
		} catch (SQLException e) {
			System.out.println("Cant save player into DB");
		}
	}
	
	/**
	 * Create player and search if he exist in the DB. If not, it create and save it
	 * @throws SQLException
	 */
	public void loadPlayer() throws SQLException
	{
		System.out.println("Votre pseudo ?");
		String pseudo = sc.nextLine();
		User u =new User();
		u.setPseudo(pseudo);
		
		this.player = daoUser.findByName(u); 
		if(this.player == null)
		{
			daoUser.create(this.player);
			this.player = daoUser.findByName(u); 
		}
	}
	
	/**
	 * Create a partie and loop to have player calcul-response. At the end, print his score
	 * @throws SQLException
	 */
	public void playGame() throws SQLException
	{
		Partie p = new Partie();
		for(int j=0;j<NB_QUESTION;++j) {
			Calcul c =new Calcul();
			System.out.println(c.toString()+" ?");
			String response = sc.nextLine();
			p.getCalculs().add(c);
			try {
				Response r = new Response(Integer.valueOf(response));
				c.setResponse(r);
				System.out.println("R = "+c.getValue());
				this.daoResponse.create(r);
			}
			catch(Exception e) {
				Response r = new Response(0);
				c.setResponse(r);
				System.out.println("R = "+c.getValue());
				this.daoResponse.create(r);
			}
			this.daoCalcul.create(c);
		}
		int score = p.getScore();
		System.out.println("Score : "+score);
		if(this.player.getMaxScore() < score) {
			this.player.setMaxScore(score);
		}
		
		this.player.getParties().add(p);
		this.daoUser.update(this.player);
	}
	
	/**
	 * Get all player in the DB and print there score
	 */
	public void printAllMaxScore()
	{
		List<User> userList = this.daoUser.getClassement();
		for(int j=0; j<userList.size(); ++j) {
			System.out.println((j+1)+" : "+userList.get(j).getPseudo()+" ("+userList.get(j).getMaxScore()+")");
		}
	}
   
}
