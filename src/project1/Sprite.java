/* Skeleton code by by Eleanor McMurtry
 * Modified by Joshua Tan [838922]
 * 
 */
package project1;

import org.newdawn.slick.Image;
//import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

/**
 * The Class Sprite.
 */
public class Sprite {
	// Used to decide what direction an object is moving
	/** The Constant DIR_NONE. */
	// Look up enums to find a more elegant solution!
	public static final int DIR_NONE = 0;
	
	/** The Constant DIR_LEFT. */
	public static final int DIR_LEFT = 1;
	
	/** The Constant DIR_RIGHT. */
	public static final int DIR_RIGHT = 2;
	
	/** The Constant DIR_UP. */
	public static final int DIR_UP = 3;
	
	/** The Constant DIR_DOWN. */
	public static final int DIR_DOWN = 4;
	
	/** The tag list. */
	public static ArrayList<String> tagList = new ArrayList<>();

	

	/** The image. */
	private Image image = null;
	
	/** The tag. */
	public String tag;
	
	/** The x. */
	public float x;
	
	/** The y. */
	public float y;
	
	/**
	 * Instantiates a new sprite.
	 *
	 * @param image_src the image src
	 * @param x the x
	 * @param y the y
	 */
	public Sprite(String image_src, float x, float y) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
//		System.out.println(image_src);
		image_src = image_src.toLowerCase();
		image_src = image_src.split("/")[1];
		image_src = image_src.split("\\.")[0];
		image_src = image_src.split("_")[0];
//		System.out.println(image_src);

		
		this.tag = image_src;
		this.x = x;
		this.y = y;
		snapToGrid();
	}
	
	/**
	 * Adds the tag.
	 *
	 * @param the tag to add
	 */
	public static void addTag(String tag){
		tagList.add(tag);
		
	}
	
	/**
	 * Removes the tag.
	 *
	 * @param the tag to be removed
	 */
	public void removeTag(String tag){
		tagList.remove(tag);
		
	}
	
	/**
	 * Compare tag.
	 *
	 * @param String to be compared
	 * @return true, if matched
	 */
	public boolean compareTag(String compare){
		compare.toLowerCase();
		if(this.tag.contains(compare)){
			return true;
		}else{
			return false; 
		}
	}
	
	/**
	 * Update.
	 *
	 * @param delta
	 */
	public void update(int delta) {
		
	}
	
	/**
	 * Render.
	 *
	 * @param g
	 */
	public void render(Graphics g) {
		image.draw(x, y);
	}
	
	
	
	/**
	 * Snap to grid.
	 */
	// Forces this sprite to align to the grid
	public void snapToGrid() {
		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		x = Math.round(x);
		y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}
	
	
}
