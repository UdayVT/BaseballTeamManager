/**
 * An driver program used to test out the <CODE>Player</CODE> and
 * <CODE>Team</CODE> abstract data type written as a part of this
 * assignment. The user can use the commands below
 * to perform operations on <CODE>TEAM</CODE> objects.
 * <dt><b>Commands:</b><dd>
 *  	A) Add Player. (Name) (Hits) (Errors) (Position)
 *  	G) Get Player stats. (Position)
 *  	L) Get leader in a stat. (Stat)
 *  	R) Remove a player. (Position)
 *  	P) Print all players. (Team)
 *  	S) Size of team. (Team)
 *  	T) Select team (Index)
 *  	C) Clone team (From) (To)
 *  	E) Team equals (Team1) (Team2)
 *  	U) Update stat. (Name) (Stat) (numHits)
 *  	Q) Quit
 *  
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * 
 * @custom.Assignment
 * 		Homework #1 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * 
 * @custom.Date
 *    February 5th, 2023
 */

import java.util.*;
public class TeamManager 
{
	public static final int MAX_TEAMS = 5; //Maximum number of team the team class could have
	public Team[] teams = new Team[MAX_TEAMS]; //an array to store all the teams
	
	/**
	 * Default Constructor of Team Manager
	 * 
	 * @custom.Postcondition
	 * 		initializes all the Team classes in teams array
	 */
	TeamManager() 
	{
		for(int i = 0; i<MAX_TEAMS;i++) 
		{
			teams[i] = new Team();
		}
	}
	
	/**
	 * Accessor for the Team class in array teams
	 * @param index
	 * 		the index of the team according to the loop
	 * @return
	 * 		the team from the array
	 */
	public Team getTeam(int index) 
	{
		return teams[index];
	}
	
	/**
	 * Mutator for the Team in array teams
	 * @param t
	 * 		the new team that replaces old team
	 * @param index
	 * 		the index of the Team that need to be mutated
	 */
	public void setTeam(Team t,int index) 
	{
		teams[index] = t;
	}
	
	/**
	 * The main operating method for the program where data is taken from the user
	 * and different players and teams has been created. 
	 */
	public static void main(String[]args) 
	{
		TeamManager m = new TeamManager(); //creating object of team manager
		
		Scanner input = new Scanner(System.in);//input scanner
		int teamSelected = 1; // default selected team
		
		System.out.println("Welcome to TeamManager!\n");
		System.out.println("Team 1 is currently selected.");
		while(true) 
		{
			//Printing menu and options
			System.out.println("\nPlease select an option:\r\n"
					+ "A)  Add Player.\r\n"
					+ "G)  Get Player stats. \r\n"
					+ "L)  Get leader in a stat. \r\n"
					+ "R)  Remove a player. \r\n"
					+ "P)  Print all players.\r\n"
					+ "S)  Size of team.\r\n"
					+ "T)  Select team \r\n"
					+ "C)  Clone team\r\n"
					+ "E)  Team equals\r\n"
					+ "U)  Update stat.\r\n"
					+ "Q)  Quit.\n");
			System.out.print("Select a menu option: ");
			String option = input.nextLine();
			option = option.toUpperCase();
			System.out.println();
			
			//if nothing is entered
			if(option.length() == 0) 
			{
				System.out.println("\nInvalid option selected, please try again.");
			}
			//calling add player function
			else if(option.charAt(0) == 'A') 
			{
				try 
				{
					//creating player p
					Player p = new Player();
					System.out.print("Enter the player's name: ");
					p.setName(input.nextLine());
					System.out.print("Enter the number of hits: ");
					p.setNumHits(input.nextInt());
					System.out.print("Enter the number of errors: ");
					p.setNumErrors(input.nextInt());
					//adding player at selected position
					System.out.print("Enter the position: ");
					int position = input.nextInt();
					m.getTeam(teamSelected-1).addPlayer(p, position);
					System.out.println("Player added: "+p);
				}
				catch(IllegalArgumentException | FullTeamException e)
				{
					System.out.println("\n"+e.getMessage());
				}
				finally 
				{
					//since last input was integer if there was exception or not
					input.nextLine();
				}
			}
			//calling get player function
			else if(option.charAt(0) == 'G')
			{
				try 
				{
					//getting position and calling exception
					System.out.print("Enter the position: ");
					int position = input.nextInt();
					input.nextLine();
					System.out.println(m.getTeam(teamSelected-1).getPlayer(position));
				}
				catch(IllegalArgumentException e) 
				{
					System.out.println("\n"+e.getMessage());
				}				
			}
			//calling getLeader function
			else if(option.charAt(0) == 'L')
			{
				try 
				{
					System.out.print("Enter the stat: ");
					String stat = input.nextLine();
					Player p = m.getTeam(teamSelected-1).getLeader(stat);
					System.out.println("Leader in "+stat+" : "+p);
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("\n"+e.getMessage());
				}
			}
			//calling remove player
			else if(option.charAt(0) == 'R')
			{
				System.out.print("Enter the position: ");
				int position = input.nextInt();
				input.nextLine();
				try 
				{
					//try to get player, if null than do nothing or else print
					Player p = m.getTeam(teamSelected-1).getPlayer(position);
					if(p != null) 
						System.out.println("Player Removed at position "+position+ "\n");
						System.out.println(p.getName()+" has been removed from the team.");
					m.getTeam(teamSelected-1).removePlayer(position);
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("\nNo player at position "+position+" to remove.");
				}
			}
			// calling print function
			else if(option.charAt(0) == 'P') 
			{
				System.out.print("Select team index: ");
				m.getTeam(input.nextInt()-1).printAllPlayers();
				input.nextLine();
			}
			// calling size function
			else if(option.charAt(0) == 'S') 
			{
				System.out.println("Select team index: ");
				System.out.println("There are "+m.getTeam(teamSelected-1).size()+" players(s) in the current team");
			}
			//selecting different team
			else if(option.charAt(0) == 'T') 
			{
				System.out.print("Enter the team index to select: ");
				int newTeamSelected = input.nextInt();
				if(newTeamSelected <= MAX_TEAMS) 
				{
					System.out.println("\nTeam "+newTeamSelected+" has been selected.");
					teamSelected = newTeamSelected;
				}
				else 
				{
					System.out.println("\nInvalid index for team.");
				}
				input.nextLine();
			}
			//calling clone function
			else if(option.charAt(0) == 'C') 
			{
				System.out.print("Select team to clone from: ");
				int from = input.nextInt();
				System.out.print("Select team to clone to: ");
				int to = input.nextInt();
				input.nextLine();
				
				////checking if valid from and to number were given as input
				if(from<1 || from>MAX_TEAMS ) 
				{
					System.out.println("\nInvalid team number to clone from.");
					continue;
				}
				
				if(to<1||to>MAX_TEAMS) 
				{
					System.out.println("\nInvalid team number to clone to.");
					continue;
				}
				
				m.setTeam((Team) m.getTeam(from-1).clone(),to-1);
				System.out.println("\nTeam "+from+" has been copied to team "+to);				
			}
			//calling equal function
			else if(option.charAt(0) == 'E') 
			{
				System.out.print("Select first team index: ");
				int first = input.nextInt();
				System.out.print("Select second team index: ");
				int second = input.nextInt();
				input.nextLine();
				
				//checking if valid first and second number were given as input
				if(first<1 || first>MAX_TEAMS || second<1 || second>MAX_TEAMS) 
				{
					System.out.println("\nInvalid team number.");
					continue;
				}
				
				if(m.getTeam(first-1).equals(m.getTeam(second-1))) 
				{
					System.out.println("\nThese teams are equal.");
				}
				else 
				{
					System.out.println("\nThese teams are not equal.");
				}
			}
			//caling update function
			else if(option.charAt(0) == 'U') 
			{
				System.out.print("Enter the player to update: ");
				String name = input.nextLine();
				System.out.print("Enter stat to update: ");
				String stat = input.nextLine();
				try 
				{
					Player p = m.getTeam(teamSelected-1).getPlayer(name);
					if(stat.equals("hits")) 
					{
						System.out.print("Enter the new number of hits: ");
						p.setNumHits(input.nextInt());
						System.out.println("\nUpdated "+name+" hits");
						input.nextLine();
					}
					else if(stat.equals("errors")) 
					{
						System.out.print("Enter the new number of errors: ");
						p.setNumErrors(input.nextInt());
						System.out.println("\nUpdated "+name+" errors");
						input.nextLine();
					}
					else 
					{
						System.out.println("\nNo such statistic.");
					}
				}
				catch(IllegalArgumentException e) 
				{
					System.out.println("\nInvalid input: cannot update "+stat+" to a negative number.");
					input.nextLine();
				}
				catch(NullPointerException e) 
				{
					System.out.println("\nPlayer not found.");
					input.nextLine();
				}
			}
			//Quiting the function
			else if(option.charAt(0) == 'Q') 
			{
				System.out.println("Program terminating normally...");
				break;
			}
			//if anything else was given as input instead of the options
			else 
			{
				System.out.println("Invalid option selected, please try again.\n");
			}
		}
	}
}
