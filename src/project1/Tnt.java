/* by Joshua Tan [838922]
 * 
 */
package project1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Tnt.
 */
public class Tnt extends Pushable{
	
	/** The cracked. */
	private static Sprite cracked;

	/**
	 * Instantiates a new tnt.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Tnt(float x, float y) {
		super("res/tnt.png", x, y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * On move.
	 *
	 * @param dir the dir
	 * @param testX the test X
	 * @param testY the test Y
	 * @return true, if successful
	 */
	public boolean onMove(int dir, float testX, float testY) {
		//Something to do with checking if 
		// it's moved into the cracked Wall.
		ArrayList<Sprite> nextSprites = Loader.getNextSprites(dir,testX,testY);
		for(Sprite i : nextSprites){
			if(i.tag.equals("cracked")){
				World.explode = World.createSprite("explosion", 
						i.x-App.TILE_SIZE, i.y-App.TILE_SIZE);
				// exception offset for huge sprite.
				World.explodeCondition = 1;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Explosion system.
	 *
	 * @param explodeCondition the explode condition
	 * @return the int
	 */
	public static int explosionSystem(int explodeCondition){
		
		if(explodeCondition >= 1){
			World.addToSprites(World.explode);
			World.explosionStartTime = World.time;
			for(Sprite i : World.getSprites()){
				if(i.tag.equals("tnt")){
					World.tnt = i;
					World.removeTnt = 1;
					explodeCondition = -1;
				} else if (i.tag.equals("cracked")){
					cracked = i;
				}
			}
		}
		if(explodeCondition <= -1){
			World.destroySprite(World.tnt);
			World.destroySprite(cracked);
			if(World.time - World.explosionStartTime >= 400){
				World.destroySprite(World.explode);
			}
		}
		return explodeCondition;
	}
	

}
