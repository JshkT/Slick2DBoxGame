/* by Joshua Tan [838922]
 * 
 */
package project1;

// TODO: Auto-generated Javadoc
/**
 * The Class Rogue.
 */
public class Rogue extends Movable {
	
	/** The dir. */
	private int dir = Sprite.DIR_LEFT;
	
	/** The opp dir. */
	private int oppDir = Sprite.DIR_RIGHT;
	
	/**
	 * Instantiates a new rogue.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Rogue(float x, float y) {
		super("Res/rogue.png", x, y);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see project1.Sprite#update(int)
	 */
	public void update(int delta){
		if(World.getPlayerMoved()){
//			System.out.println("Rogue Knows");
			if(moveToDest(dir)){
			} else {
				int tempDir = new Integer(dir);
				int tempOppDir = new Integer(oppDir);
				dir = tempOppDir;
				oppDir = tempDir;
				moveToDest(dir);
			}
		}
	}
	

}
