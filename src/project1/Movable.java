/* by Joshua Tan [838922]
 * 
 */
package project1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Movable.
 */
public class Movable extends Sprite {

	
	/**
	 * Instantiates a new movable.
	 *
	 * @param image_src the image src
	 * @param x the x
	 * @param y the y
	 */
	public Movable(String image_src, float x, float y) {
		super(image_src, x, y);
		
	}
	
	
	/**
	 * Move to dest.
	 *
	 * @param dir the dir
	 * @return true, if successful
	 */
	public boolean moveToDest(int dir) {
//		System.out.println(history.xStack.get(history.xStack.size()-1));
		float speed = 32;
		// Translate the direction to an x and y displacement
		float delta_x = 0,
			  delta_y = 0;
//		System.out.println(dir);
		switch (dir) {
			case DIR_LEFT:
				delta_x = -speed;
				break;
			case DIR_RIGHT:
				delta_x = speed;
				break;
			case DIR_UP:
				delta_y = -speed;
				break;
			case DIR_DOWN:
				delta_y = speed;
				break;
		}
		
		// Make sure the position isn't occupied!
		if (!Loader.isBlocked(x + delta_x, y + delta_y)) {
			
			// Coordinates to try
			float testX = x + delta_x;
			float testY = y + delta_y;			
			
			ArrayList<Sprite> typesAt = World.getSpritesAt(testX,testY);
			for(Sprite j : typesAt){
				if(j.tag.equals("cracked") || j.tag.equals("door")
						|| j.tag.equals("wall")){
					return false;
				}
				
				if(j.tag.equals("ice")){
					if(((Ice) j).isPushed()){
						return false;
					}
				}
				
			}

			// if the next position is occupied by a Pushable,
			if(Loader.isPushable(testX,testY) 
					&& (this.tag.equals("player") || this.tag.equals("rogue"))){
				Sprite tempPushable = null;
				
				for (Sprite i : typesAt){
//					System.out.println(i.tag);
					if(i.tag.equals("stone") || i.tag.equals("ice") || i.tag.equals("tnt")){
						tempPushable = i;
					} 
				}
				// if the space in front of the pushable isn't blocked, push.
				if(!Loader.isBlocked(testX + delta_x, testY + delta_y)){
					if(((Pushable)tempPushable).push(dir) == 1){
						this.x += delta_x;
						this.y += delta_y;
					} else {
//						System.out.println("CANT PUSH");
					}
				} else {
					return false;
				}
				
//				
			} else {
			this.x += delta_x;
			this.y += delta_y;
			}			
			
			return true;
		} else {
			return false;
		}
	}

}
