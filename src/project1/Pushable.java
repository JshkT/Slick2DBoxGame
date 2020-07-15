/* by Joshua Tan [838922]
 * 
 */
package project1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Pushable.
 */
public class Pushable extends Movable{
	
	/** The Constant EXPLODE. */
	public static final int EXPLODE = -1;
	
	/** The Constant PUSHED. */
	public static final int PUSHED = 1;
	
	/** The Constant BLOCKED. */
	public static final int BLOCKED = 0;

	/**
	 * Instantiates a new pushable.
	 *
	 * @param image_src the image src
	 * @param x the x
	 * @param y the y
	 */
	public Pushable(String image_src, float x, float y) {
		super(image_src, x, y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Push.
	 *
	 * @param dir the dir
	 * @return the int
	 */
	public int push(int dir){
		System.out.println(Loader.getNextSprites(dir,x,y));
		ArrayList<Sprite> nextSprites = Loader.getNextSprites(dir, x, y);
		
		//If the object being pushed is Tnt,
		//Check for Cracked walls and Explode as needed;
		if(this.tag.equals("tnt")){
			if(((Tnt)this).onMove(dir, x,y)){
				return EXPLODE;
			}
		}
		
		for(Sprite i : nextSprites){
			if(i.tag.contains("wall") || i.tag.contains("stone") 
					|| i.tag.contains("ice") || i.tag.contains("tnt") 
					|| i.tag.contains("cracked") || i.tag.contains("door")){
//				System.out.println("BLOCKED BY "+i.tag);
				
				return BLOCKED;
			}
		}
		if(this.moveToDest(dir)){
//			System.out.println("Pushed");
			return PUSHED;
		}
		return BLOCKED;
		
		
		
	}
	
	/**
	 * Active.
	 *
	 * @return true, if successful
	 */
	public boolean active(){
		//if stone isn't on a target, True
		//if Tnt has not exploded, True
		return true;
	}
	

}
