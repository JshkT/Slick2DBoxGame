/* by Joshua Tan [838922]
 * 
 */
package project1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Target.
 */
public class Target extends Sprite {
	
	/** The Constant INACTIVE. */
	public static final int INACTIVE = 0;
	
	/** The Constant ACTIVE. */
	public static final int ACTIVE = 1;
	
	/** The activated. */
	private int activated = INACTIVE;
	
	/**
	 * Instantiates a new target.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Target(float x, float y) {
		super("res/Target.png", x, y);
	}
	
	/* (non-Javadoc)
	 * @see project1.Sprite#update(int)
	 */
	@Override
	public void update(int delta){

		ArrayList<Sprite> spritesHere = World.getSpritesAt(x,y);
		for(Sprite i : spritesHere){
			if(i.tag.equals("stone") || i.tag.equals("ice") || i.tag.equals("tnt")){
				this.activated = ACTIVE;
			} else {
				this.activated = INACTIVE;
			}
		}
	
	}

	/**
	 * Checks if is active.
	 *
	 * @return the int
	 */
	public int isActive(){
		return activated;
	}
}
