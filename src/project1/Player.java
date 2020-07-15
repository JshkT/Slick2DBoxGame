/* Skeleton code by by Eleanor McMurtry
 * Modified by Joshua Tan [838922]
 * 
 */
package project1;

import java.util.ArrayList;

/**
 * The Class Player.
 */
public class Player extends Movable {
	
	/** The dir. */
	public int dir;
	
	/** The move count. */
	private static int moveCount = 0;
	
	/** The death confirmation. 
	 * checks the number of frames
	 * the player is sharing a tile with
	 * an enemy.
	 * */
	private int deathConfirmation = 0;
	
	
	/**
	 * Instantiates a new player.
	 *
	 * @param x
	 * @param y
	 */
	public Player(float x, float y) {
		super("res/player_left.png", x, y);

	}

	/* (non-Javadoc)
	 * @see project1.Sprite#update(int)
	 */
	@Override
	public void update(int delta) {
		//Check if Player is dead.
		ArrayList<Sprite> standingOn = World.getSpritesAt(this.x, this.y);
		//If there's more than the player and some form of floor,
		//There's a good chance it's dead.
		if(standingOn.size()>2){
			for(Sprite sprite : standingOn){
				if(sprite.tag.equals("rogue") || sprite.tag.equals("skull")
						|| sprite.tag.equals("mage")){
					//Need this condition to persist for more than
					//one frame to account for update order.
					if(deathConfirmation > 1){
						World.setDead(true);
						deathConfirmation = 0;
					} else {
						deathConfirmation ++;
					}
				}
			}
		} else {
			deathConfirmation = 0;
		}
		
		if(World.dir != Sprite.DIR_NONE){
		// Move to our destination
			if(this.moveToDest(World.dir)){
				moveCount++;
				World.setPlayerMoved();
			}
		}
		
		
	}
	
	/**
	 * Gets the move count.
	 *
	 * @return the move count
	 */
	public static int getMoveCount(){
		return moveCount;
	}
	
	/**
	 * Sets the move count.
	 *
	 * @param newCount the new move count
	 */
	public static void setMoveCount(int newCount){
		moveCount = newCount;
	}
}
