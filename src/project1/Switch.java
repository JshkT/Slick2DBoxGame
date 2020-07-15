/* by Joshua Tan [838922]
 * 
 */
package project1;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Switch.
 */
public class Switch extends Sprite{
	
	/** The switch status */
	private boolean switchIsPressed = false;
	
	/**
	 * Instantiates a new switch.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Switch(float x, float y) {
		super("res/switch.png",x, y);
	}
	
	/* 
	 * Switch's own update function.
	 */
	@Override
	public void update(int delta) {
		ArrayList<Sprite> spritesHere = World.getSpritesAt(x,y);
		for(Sprite i : spritesHere){
			if(i.tag.equals("stone") || i.tag.equals("ice") || i.tag.equals("tnt")){
				this.switchIsPressed = true;
			} else {
				this.switchIsPressed = false;
			}
		}
	
	}

	/**
	 * Checks if is pressed.
	 *
	 * @return true, if is pressed
	 */
	public boolean isPressed(){
		return switchIsPressed;
	}
	
	/**
	 * Switch system.
	 */
	public static void switchSystem(){
//		System.out.println(World.doorIsOpen);
		int doorExists = 0;
		if(World.doorIsOpen == true){
			Sprite doorToBeRemoved = null;
			for(Sprite i : World.getSprites()){
				if(i.tag.equals("door")){
					doorToBeRemoved = i;
					World.setDoor(new Door(i.x,i.y));
				}
			}
			World.destroySprite(doorToBeRemoved);
		} else {
			for(Sprite i : World.getSprites()) {
				if(i.tag.equals("door")){
					//Door is where it should be.
					// do nothing.
					doorExists = 1;
				}
			}
		}
//		System.out.println(doorExists);
		if(doorExists == 0 && World.doorIsOpen == false && World.getDoor() != null){
			World.addToSprites(World.getDoor());
		}
		
		
	}
}
