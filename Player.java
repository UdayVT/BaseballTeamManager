/**
 * An abstract data type meant to serve as a representation of a baseball
 * player, containing the player's name, number of hits, number of errors
 * and with methods to manipulate these values. 
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #1 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 5th, 2023
 */
public class Player implements Cloneable
{
	private String name; //A string that stores player's name
	private int numHits; //An integer that stores the number of hits the player has
	private int numErrors;//An integer that stores the number of errors the player has
		
	public Player(){}
	
	/**
	 * Sets a new name of player
	 * 
	 * @param name
	 *     the new name of the player
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * Returns the current name of the player
	 * 
	 * @return
	 *     the name of the player
	 */
	public String getName() 
	{
		return this.name;
	}
	
	/**
	 * Sets a new number for numHits
	 * 
	 * @param numHits
	 *     the number thats going to be new numHits
	 *     
	 * @custom.Precondition
	 *     new numHits must not be less than 0 
	 */
	public void setNumHits(int numHits) throws IllegalArgumentException
	{
		if(numHits < 0) 
			throw new IllegalArgumentException("Invalid input: Number of Hits cannot be less than 0");

		this.numHits = numHits;
	}
	
	/**
	 * Returns the current number of hits
	 * 
	 * @return
	 *     number of hits 
	 */
	public int getNumHits() 
	{
		return this.numHits;
	}
		
	/**
	 * Sets a new number for numErrors
	 * 
	 * @param numErrors
	 *     the number thats going to be new numErros
	 *     
	 * @custom.Precondition
	 *     new numErrors must not be less than 0 
	 */
	public void setNumErrors(int numErrors) throws IllegalArgumentException
	{
		if(numErrors < 0)
			throw new IllegalArgumentException("Invalid input: Number of Errors cannot be less than 0");
		
		this.numErrors = numErrors;
	}
	
	/**
	 * Returns the current number of errors
	 * 
	 * @return
	 *     number of errors
	 */
	public int getNumErrors() 
	{
		return this.numErrors;
	}
	
	/**
	 * returns a printable representation of the player and his statistics (hits and errors)
	 * 
	 * @return
	 *     the player stats in a string format
	 */
	public String toString() 
	{
		return name+" - "+numHits+" hits, "+numErrors+" errors";
	}
	
	/**
	 * creates a copy of the player
	 * 
	 * @return
	 *     the player with the same stats as a new object
	 */
	public Object clone() 
	{
		Player p = new Player();
		p.setName(name);
		p.setNumHits(numHits);
		p.setNumErrors(numErrors);
		return p;
	}
}
