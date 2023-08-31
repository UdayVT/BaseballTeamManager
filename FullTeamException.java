/**
 * An exception thrown in the <CODE>Team</CODE> class to indicate that the
 * maximum number of players a team can have has been reached.
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #1 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 5th, 2023
 */
public class FullTeamException extends Exception 
{	
	/**
     * Default constructor for an FullTeamException that
     * passes a default string to the Exception class constructor.
     *
     * @custom.Postcondition:
     *    The object is created and contains the default message.
     */
	public FullTeamException() 
	{
		//Default Message
		super("Maximum players reached.");
	}
	
	/**
     * Second constructor for the FullTeamException that
     * passes a provided string to the Exception class constructor.
     *
     * @param errorMessage
     *    the message that the object is to contain
     *    
     * @custom.Postcondition:
     *    The object is created and contains the provided message.
     */
	public FullTeamException(String errorMessage) 
	{
		//Passed Message
		super(errorMessage);
	}
}
