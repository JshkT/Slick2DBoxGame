/* by Joshua Tan [838922]
 * 
 */
package project1;

// TODO: Auto-generated Javadoc
/**
 * The Class Skeleton.
 */
public class Skeleton extends Movable {
	
	/** The dir. */
	private int dir = Sprite.DIR_UP;
	
	/** The opp dir. */
	private int oppDir = Sprite.DIR_DOWN;
	
	/** The time. */
	private int time = 0;
	
	/** The Constant TARGET_TIME. */
	private static final int TARGET_TIME = 1000;

	/**
	 * Instantiates a new skeleton.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y);
	}
	
	/**
	 * Undo.
	 */
	public void undo(){
		
	}
	
	/* (non-Javadoc)
	 * @see project1.Sprite#update(int)
	 */
	public void update(int delta){
		time += delta;
		if(time >= TARGET_TIME){
			//Act.
			if(moveToDest(dir)){
				//Continue in this direction.
			} else {
				int tempDir = new Integer(dir);
				int tempOppDir = new Integer(oppDir);
				dir = tempOppDir;
				oppDir = tempDir;
				moveToDest(dir);
			}
			time = 0;
		}
		
		
	}
	
	/**
	 * On move.
	 *
	 * @param dir the dir
	 * @param testX the test X
	 * @param testY the test Y
	 */
	public void onMove(int dir, float testX, float testY){
		
	}

}
