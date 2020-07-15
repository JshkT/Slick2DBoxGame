/* by Joshua Tan [838922]
 * 
 */
package project1;

// TODO: Auto-generated Javadoc
/**
 * The Class Mage.
 */
public class Mage extends Movable {
	
	/** The dir X. */
	private int dirX;
	
	/** The dir Y. */
	private int dirY;
	
	/** The dist X. */
	private float distX;
	
	/** The dist Y. */
	private float distY;

	/**
	 * Instantiates a new mage.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Mage(float x, float y) {
		super("res/mage.png", x, y);
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Undo.
	 */
	public void undo(){
		
		
	}
	
	/* Mage's update function
	 * implements it's special movement.
	 */
	@Override
	public void update(int delta){
		if(World.getPlayerMoved()){
//			System.out.println("Mage Knows");
			for(Sprite sprite : World.getSprites()){
				if(sprite.tag.equals("player")){
					distX = this.x - sprite.x;
					distY = this.y - sprite.y;
				}
			}
			//Implementing sign.
//			System.out.println(distX + " " + distY);
			if(Math.abs(distX) > Math.abs(distY)){
				dirX = Sprite.DIR_LEFT;
				if(distX < 0){
					dirX = Sprite.DIR_RIGHT;
				}
				moveToDest(dirX);
			} else {
				dirY = Sprite.DIR_UP;
				if(distY < 0){
					dirY = Sprite.DIR_DOWN;
				}
				moveToDest(dirY);
			}	
		}	
	}

}
