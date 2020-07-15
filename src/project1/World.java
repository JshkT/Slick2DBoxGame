/* Base code by by Eleanor McMurtry
 * Modified by Joshua Tan [838922]
 */

package project1;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * The Class World.
 */
public class World {
	
	/** The sprites. */
	private static ArrayList<Sprite> sprites;
	
//	/** The world state stack. */
//	private static ArrayList<ArrayList<Sprite>> worldStateStack = new ArrayList<>();
	
	/** The input. */
	public static Input input;
	
	/** The dir. */
	public static int dir;
	
	/** The time. */
	public static int time = 0;
	
	/** Player Dead Status */
	private static boolean dead = false;
	
	/** The path. */
	private String path = "res/levels/";
	
	/** The extention. */
	private String extention = ".lvl";
	
	/** The curr level. */
	private static Integer currLevel = 0;
	
	/** The player moved. */
	private static boolean playerMoved = false;
	
	/** The target goal. */
	private static int targetGoal = 0;
	
	/** The target count. */
	private static int targetCount = 0;
	
	/** door open status */
	public static boolean doorIsOpen;
	
	/** The door sprite */
	private static Sprite door;
	
	/** explosion sprite */
	public static Sprite explode = null;
	
	/** The explode condition. */
	public static int explodeCondition = 0;
	
	/** The explosion start time. */
	public static int explosionStartTime;
	
	/** The tnt sprite.  */
	public static Sprite tnt = null;
	
	/** The cracked sprite */
	public static Sprite cracked;
	
	/** determines if the tnt should be removed.  */
	public static int removeTnt = 0;
	
	
	/**
	 * Builds the level path.
	 *
	 * @return the string
	 */
	private String buildLevelPath(){
		return path + currLevel.toString() + extention;
	}
	
	/**
	 * Instantiates a new world.
	 */
	public World() {
		sprites = Loader.loadSprites(buildLevelPath());		
		
	}
	
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name	the name of the sprite
	 * @param x		the x position
	 * @param y		the y position
	 * @return		the sprite object
	 */
	public static Sprite createSprite(String name, float x, float y) {
		switch (name) {
			case "cracked":
				Sprite.addTag("cracked");
				return new Cracked(x,y);
			
			case "tnt":
				Sprite.addTag("tnt");
				Movable tempSprite = new Tnt(x,y);
				return tempSprite;
			
			case "switch":
				Sprite.addTag("switch");
				return new Switch(x,y);
			
			case "door":
				Sprite.addTag("door");
				setDoorIsOpen(false);
				return new Door(x,y);
			
			case "floor":
				Sprite.addTag("floor");
				return new Floor(x, y);
			case "wall":
				Sprite.addTag("wall");
				return new Wall(x, y);
			
			case "ice":
				Sprite.addTag("ice");
				return new Ice(x,y);
			
			case "stone":
				Sprite.addTag("stone");
				return new Stone(x, y);
			
			case "target":
				targetGoal++;
				Sprite.addTag("target");
				return new Target(x, y);
			
			case "player":
				Sprite.addTag("player");
				Player tempPlayer = new Player(x,y);
				return  tempPlayer;
			
			case "skeleton":
				Sprite.addTag("skeleton");
				return new Skeleton(x,y);
				
			case "mage":
				Sprite.addTag("mage");
				return new Mage(x,y);
				
			case "rogue":
				Sprite.addTag("rogue");
				return new Rogue(x,y);
				
			case "explosion":
				Sprite.addTag("explosion");
				return new Explosion(x,y);
		}
		return null;
	}
	
	/**
	 * Destroy sprite.
	 *
	 * @param sprite to be destroyed. 
	 */
	public static void destroySprite(Sprite sprite){
		getSprites().remove(sprite);
	}
	
	/**
	 * Adds sprite to sprite list.
	 *
	 * @param  sprite to be added
	 */
	public static void addToSprites(Sprite sprite){
		getSprites().add(sprite);
	}
	
//	/**
//	 * Save world state.
//	 */
//	public static void saveWorldState(){
//		if(sprites != null){
//			System.out.println("SPRITES NOT NULL");
//			System.out.println(sprites.size());
//			ArrayList<Sprite> temp = new ArrayList<Sprite>(sprites);
//			System.out.println(temp.size());
//			System.out.println(temp.size());
//			worldStateStack.add(temp);
//			}
//		}
	
	/**
	 * Pop world state.
	 *
	 * @return the array list
	 */
//	public static ArrayList<Sprite> popWorldState(){
//		if(worldStateStack.size() > 0){
//			System.out.println("WorldStack not empty");
//			ArrayList<Sprite> tempSprites = new ArrayList<Sprite>(worldStateStack.get(0));
//			//Just to see if it was actually doing anything.
//			worldStateStack.remove(0);
//			if(tempSprites.equals(sprites)){
//				System.out.println("SAME");
//			} else {
//				System.out.println("DIFF");
//			}
//			return tempSprites;
//		}
//		return sprites;
//		
//	}
//	
//	/**
//	 * Load last world state.
//	 */
//	public static void loadLastWorldState() {
//		sprites = popWorldState();
//	}
	
	
	/**
	 * Gets the sprite of type.
	 *
	 * @param tag the tag
	 * @return the sprite of type
	 */
	public static Sprite getSpriteOfType(String tag) {
		// This is going through sprites but it needs to compare
		// tags in tagList (probably).
		for(int i = 0; i < Sprite.tagList.size(); i++){
			String temp = Sprite.tagList.get(i);
//			System.out.println(temp);
			if (temp.equals(tag)){
//				System.out.println(sprites.get(i));
				return getSprites().get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets the sprite of type.
	 *
	 * @param tag the tag
	 * @param x the x
	 * @param y the y
	 * @return the sprite of type
	 */
	public static Sprite getSpriteOfType(String tag, float x, float y) {
//		System.out.println(x);
		for (Sprite temp : getSprites()){
//			System.out.println(temp.tag);
			if (temp.x == x && temp.y == y && temp.compareTag(tag)){
				return temp;
			}
		}
		
		return null;
	}
	
	/**
	 * Gets the sprites at.
	 *
	 * @param x
	 * @param y
	 * @return the sprites at x,y
	 */
	public static ArrayList<Sprite> getSpritesAt(float x, float y){
		ArrayList<Sprite> tilesHere = new ArrayList<>();
		for (Sprite temp : getSprites()){
			if (temp.x == x && temp.y == y) {
				tilesHere.add(temp);
			}
		}
		return tilesHere;
	}
	
	
	/**
	 * Updates the world every frame.
	 * 
	 * @param the input from the user.
	 * @param delta. Time in ms since last update.
	 */
	public void update(Input input, int delta) {
		time += delta;
		targetCount = 0;
		for(Sprite i : getSprites()){
			if(i.tag.equals("target")){
				targetCount += ((Target) i).isActive();
			} else if(i.tag.equals("switch")){
				doorIsOpen = ((Switch) i).isPressed();
			}
		}
		//Lose conditions
		if(dead == true){
			restart();
		}
		
		//Win conditions 
		if(targetGoal > 0 && targetCount >= targetGoal){
			currLevel++;
			restart();
			return;
		}
		
		
		//Door Switch System
		Switch.switchSystem();
		

		//TNT and Explosion System
		explodeCondition = Tnt.explosionSystem(explodeCondition);
		
		// Inputs
		dir = Sprite.DIR_NONE;
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			System.exit(0);
		}
		
		if(input.isKeyPressed(Input.KEY_N)){
			currLevel++;
			restart();
		}
		
		if(input.isKeyPressed(Input.KEY_R)){
			restart();
		}
		
		if(input.isKeyPressed(Input.KEY_Z)){
//			loadLastWorldState();
			
		}
		
		if(input.isKeyPressed(Input.KEY_T)){
			//Test Key.
//			saveWorldState();
		}

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = Sprite.DIR_LEFT;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = Sprite.DIR_RIGHT;
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = Sprite.DIR_UP;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = Sprite.DIR_DOWN;
		}
		
//		System.out.println(dir);
		if(dir != Sprite.DIR_NONE){
			playerMoved = true;
		} else {
			playerMoved = false;
		}
		
		for(Sprite sprite : getSprites()){
			if(sprite.tag.equals("player")){
				sprite.update(delta);
			}
		}
		
		for (Sprite sprite : getSprites()) {
			if (!sprite.tag.equals("player")) {
				sprite.update(delta);
			}
		}
	}
	
	/**
	 * Render.
	 *
	 * @param graphics g
	 */
	public void render(Graphics g) {
		g.drawString("Moves = " + Player.getMoveCount(),0,0);
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}
	}
	
	/**
	 * Restarts the level.
	 */
	public void restart() {
		getSprites().removeAll(getSprites()); 
		
		time = 0;
		Player.setMoveCount(0);
		targetGoal = 0;
		targetCount = 0;
		dead = false;
		
		
		explode = null;
		explodeCondition = 0;
		tnt = null;
		removeTnt = 0;
		sprites = Loader.loadSprites(buildLevelPath());

		
		
	}
	
	/**
	 * Gets the dir.
	 *
	 * @return the dir
	 */
	public static int getDir(){
		return dir;
	}
	
	/**
	 * Sets the player moved to true.
	 */
	public static void setPlayerMoved(){
		playerMoved = true;
	}
	
	/**
	 * Gets whether the player has moved.
	 *
	 * @return playerMoved status.
	 */
	public static boolean getPlayerMoved(){
		return playerMoved;
	}

	/**
	 * Gets the target count.
	 *
	 * @return the target count
	 */
	public static int getTargetCount() {
		return targetCount;
	}

	/**
	 * Sets the target count.
	 *
	 * @param targetCount the new target count
	 */
	public static void setTargetCount(int targetCount) {
		World.targetCount = targetCount;
	}

	/**
	 * Checks if is door is open.
	 *
	 * @return true, if is door is open
	 */
	public static boolean isDoorIsOpen() {
		return doorIsOpen;
	}

	/**
	 * Sets the door is open.
	 *
	 * @param doorIsOpen the new door is open
	 */
	public static void setDoorIsOpen(boolean doorIsOpen) {
		World.doorIsOpen = doorIsOpen;
	}

	/**
	 * Gets the door.
	 *
	 * @return the door
	 */
	public static Sprite getDoor() {
		return door;
	}

	/**
	 * Sets the door.
	 *
	 * @param door the new door
	 */
	public static void setDoor(Sprite door) {
		World.door = door;
	}

	/**
	 * Gets the sprites.
	 *
	 * @return the sprites
	 */
	public static ArrayList<Sprite> getSprites() {
		return sprites;
	}

	/**
	 * Sets player dead status.
	 *
	 * @param sets whether the player is dead.
	 */
	public static void setDead(boolean status){
		dead = status;
	}
}
