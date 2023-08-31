/**
 * An abstract data type meant to serve as a representation of a baseball
 * team, containing an array of players and number of players in the team
 * and with methods to manipulate these team and their players. 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #1 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 5th, 2023
 */

public class Team implements Cloneable
{
	public static final int MAX_PLAYERS = 40; //Stores Max number of players a team can have
	private Player[] players; //an array holding all the players in the team
	private int numOfPlayers;//stores the number of players the team has
	
	/**
	 * Construct an instance of the Team class with no Player objects in it
	 * @custom.Postcondition
	 *     This Team has been initialized to an empty list of Players.
	 */
	public Team()
	{
		players = new Player[MAX_PLAYERS];
		numOfPlayers = 0;
	}
	
	
	/**
	 * Generate a clone of this Team.WW
	 * @return
	 *     The return value is a clone of this Team. Subsequent changes to the clone will not affect the original, nor vice versa.
	 */
	public Object clone()
	{
		//creates a new team object and clones every player from team to new object and than returns the new object
		//it also assign the numOfPlayers in the new team object
		Team newTeam = new Team(); //new team object
		for(int i = 0; i<numOfPlayers;i++) 
		{
			newTeam.players[i] = (Player) this.players[i].clone();
		}
		newTeam.numOfPlayers = this.numOfPlayers;
		return newTeam;
	}
	
	
	/**
	 * Compare this Team to another object for equality.
	 * 
	 * @param obj
	 *     an object to which this Team is compared
	 * 
	 * @return
	 *   a value that determines if the team object is equal to another object
	 */
	public boolean equals(Object obj) 
	{
		//checks if obj is a team object
		if(!(obj instanceof Team)) 
			return false;
		
		//checks if they have same number of players
		if(((Team)obj).numOfPlayers != this.numOfPlayers) 
			return false;
		
		//checks if all players have same toString and have same position
		//used toString since it checks all stats (name,hits and errors)
		//if anyone does not match, it will break the function and return false
		// if all of them matches, it will return true after loop completes
		for(int i = 0; i<numOfPlayers;i++) 
		{
			if(!(((Team)obj).players[i].toString().equals(players[i].toString()))) 
				return false; 
		}

		return true;
	}
	
	
	/**
	 * Determines the number of Players currently in this Team.
	 * 
	 * @custom.Precondition
	 *     This Team object has been instantiated
	 *   
	 * @return
	 *     The number of Players in this Team
	 */
	public int size() 
	{
		return numOfPlayers;
	}
	
	
	/**
	 * Adds a Player to the team at the indicated position in the lineup
	 * 
	 * @param p
	 *     the new Player object to add to this Team
	 * 
	 * @param position
	 *     the position in the lineup where the Player will be inserted 
	 *     
	 * @custom.Precondition
	 *     Team object has been instantiated
	 *     1 ≤ position ≤ players_currently_in_team + 1.
	 *     The number of Player objects in this Team is less than MAX_PLAYERS
	 *     
	 * @custom.Postcondition
	 *     The new Player is now stored at the desired position in the Team
	 *     All Players that were originally in positions greater than or equal to position are moved back one position    
	 *     
	 * @throws IllegalArgumentException
	 *     Indicates that position is not within the valid range
	 * 
	 * @throws FullTeamException
	 *     Indicates that there is no more room inside of the Team to store the new Player object
	 */
	public void addPlayer(Player p, int position) throws IllegalArgumentException, FullTeamException
	{
		//throws exception if position is not within the valid range
		if(!(position >= 1 && position <= (numOfPlayers+1))) 
			throw new IllegalArgumentException("Invalid position for adding the new player");
		
		//throws exception if team is full.
		if(numOfPlayers==40) 
			throw new FullTeamException("Maximum players reached.");
		
		//creates a clone of player to the right of the array till position
		for(int i = numOfPlayers; i>=position;i--) 
		{
			players[i] = (Player) players[i-1];
		}
		
		//changes the player at position given to new the player
		players[position-1] = (Player) p.clone();
		numOfPlayers++;
	}
	
	/**
	 * Removes a Player from the team at the indicated position in the lineup
	 * 
	 * @param position
	 *     the position in the lineup from which the Player is to be removed.
	 * 
	 * @custom.Precondition
	 *     This Team object has been instantiated.
	 *     1 ≤ position ≤ players_currently_in_team.
	 *     
	 * @throws IllegalArgumentException
	 *     Indicates that position is not within the valid range
	 */
	public void removePlayer(int position) throws IllegalArgumentException
	{
		//throws exception if illegal position entered
		if(!(position >= 1 && position <= numOfPlayers)) 
			throw new IllegalArgumentException("No player at position "+position+" to remove.");
		
		//creates a clone of player to the left, overwriting the player to be removed
		for(int i = position;i<numOfPlayers;i++) 
		{
			players[i-1] = (Player) players[i];
		}
		
		//deletes the duplicate in the end
		players[--numOfPlayers] = null;
	}
	
	/**
	 * Returns a reference to a player in the lineup at the indicated position
	 * 
	 * @param position
	 * 		the position in the lineup from which the Player is to be retrieved.
	 * 
	 * @custom.Precondition
	 * 		This Team object has been instantiated.
	 * 
	 * @return
	 * 		The Player from the given index
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates that position is not within the valid range
	 */
	public Player getPlayer(int position) throws IllegalArgumentException
	{
		//throws exception if position is illegal
		if(!(position >= 1 && position <= numOfPlayers)) 
			throw new IllegalArgumentException("invalid position to get player stats.");
		
		return players[position-1];
	}
	
	/**
	 * Return the Player with the best value in the given statistic ("hits" or "errors").
	 * 
	 * @param stat
	 * 		either "hits" or "errors".
	 * 
	 * @custom.Precondition
	 * 		This Team object has been instantiated.
	 * 
	 * @return
	 * 		The Player with the best stat.
	 * 
	 * @throws IllegalArgumentException
	 * 		Indicates that indicated stat was neither "hits" nor "errors".
	 */
	public Player getLeader(String stat) throws IllegalArgumentException
	{
		//throws exception if stat is not "hits" or "errors"
		if(!(stat.equals("hits") || stat.equals("errors")))
			throw new IllegalArgumentException("No such statistic.");
		
		//checks if its hit or errors
		//loops through all players and stores the position of max hits or min errors
		//return the player from LeaderPosition
		int storeLeaderPosition = 0;
		if(stat.equals("hits"))
		{
			int maxHits = 0;
			for(int i = 0; i<numOfPlayers;i++) 
			{
				if(players[i].getNumHits() > maxHits) 
				{
					storeLeaderPosition = i;
					maxHits = players[i].getNumHits();
				}
			}
		}
		else
		{
			int minError = 999999999;
			for(int i = 0; i<numOfPlayers;i++) 
			{
				if(players[i].getNumErrors() < minError) 
				{
					storeLeaderPosition = i;
					minError = players[i].getNumErrors();
				}
			}
		}

		return players[storeLeaderPosition];
	}
	
	/**
	 * Prints a neatly formatted table of each Player in the Team
	 * 
	 * @custom.Precondition
	 * 		This Team object has been instantiated
	 * 
	 * @custom.Postcondition
	 * 		A neatly formatted table of each Player in the Team on its own line with its position number has been displayed to the use
	 */
	public void printAllPlayers() 
	{
		//uses toString method
		System.out.println(this);
	}
	
	/**
	 * Gets the String representation of this Team object, which is a neatly formatted table of each Player in the Team
	 * 
	 * @return
	 * 		The String representation of this Team object.
	 */
	public String toString() 
	{
		String answer = String.format("\n%s%9s%15s%15s\n","Player#","Name","Hits","Errors");
		answer += "----------------------------------------------\n";
		for(int i = 0; i<numOfPlayers;i++) 
		{
			answer += String.format("%d%20s%9d%13d\n", i+1,players[i].getName(),players[i].getNumHits(),players[i].getNumErrors());
		}
		return answer;
	}
	
	/**
	 * gets player from player's name
	 * @param name
	 * 		the name of the player
	 * @return
	 * 		the player if found, else it will return null
	 */
	public Player getPlayer(String name) 
	{
		for(int i = 0; i<numOfPlayers;i++) 
		{
			if(players[i].getName().equals(name)) 
			{
				return players[i];
			}
		}
		return null;
	}
}
