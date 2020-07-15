/* by Joshua Tan [838922]
 * 
 */
package project1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Ice.
 */
public class Ice extends Pushable{
	
	/** The dir momentum. */
	private int dirMomentum;
	
	/** The last X. */
	private float lastX;
	
	/** The last Y. */
	private float lastY;
	
	/** The is pushed. */
	private boolean isPushed = false;

	/** The Constant ICE_DELAY. */
	private static final int ICE_DELAY = 250;
	
	/** The ice start time. */
	private static int iceStartTime = 0;

	/**
	 * Instantiates a new ice.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
		// TODO Auto-generated constructor stub
	}
	
	
	/* Checks if the ice is moving
	 * returns true if it is moving.
	 */
	public boolean active(){
		return isPushed();
	}
	
	/* Ice's own push method.
	 * Has to account for sliding.
	 */
	public int push(int dir){
		this.dirMomentum = dir;
		isPushed = true;
		lastX = new Float(this.x);
		lastY = new Float(this.y);
		
		ArrayList<Sprite> nextSprites = Loader.getNextSprites(dir, x, y);
		for(Sprite i : nextSprites){
			if(i.tag.contains("wall") || i.tag.contains("stone") 
					|| i.tag.contains("ice") || i.tag.contains("tnt") 
					|| i.tag.contains("cracked")){
				dirMomentum = Movable.DIR_NONE;
				isPushed = false;
				return BLOCKED;
			}
		}
		if(this.moveToDest(World.dir)){
//			System.out.println("Pushed");
			return PUSHED;
		}
		return BLOCKED;
		
		
		
	}
	
	/* Ice's own update
	 * 
	 */
	public void update(int delta){
		if(isPushed()){
			iceStartTime += delta;
			ArrayList<Sprite> nextSprites = Loader.getNextSprites(dirMomentum, x, y);
			for(Sprite i : nextSprites){
				if(i.tag.contains("wall") || i.tag.contains("stone") 
						|| i.tag.contains("ice") || i.tag.contains("tnt") 
						|| i.tag.contains("cracked")){
					dirMomentum = Movable.DIR_NONE;
					isPushed = false;
					iceStartTime = 0;
					return;
				}
			}
			if(iceStartTime >= ICE_DELAY){
				if(this.moveToDest(dirMomentum)){
					iceStartTime = 0;
					return;
				}
			}
			
			
			
		}
		
	}

	/**
	 * Checks if is pushed.
	 *
	 * @return true, if is pushed
	 */
	public boolean isPushed() {
		return isPushed;
	}
	

}
