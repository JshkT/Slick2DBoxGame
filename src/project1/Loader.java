/* Skeleton code by by Eleanor McMurtry
 * Modified by Joshua Tan [838922]
 * 
 */
package project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * The Class Loader.
 */
public class Loader {
	
	/** The types. */
	public static String[][] types;
	
	/** The world width. */
	private static int world_width;
	
	/** The world height. */
	private static int world_height;
	
	/** The offset x. */
	private static int offset_x;
	
	/** The offset y. */
	private static int offset_y;

	
	
	/**
	 * Converts X from world to tile coords.
	 *
	 * @param x
	 * @return the tile X
	 */
	//
	public static float getTileX(float x){
		float tempX = x - offset_x;
		tempX /= App.TILE_SIZE;
		return tempX;
	}
	
	/**
	 * Converts Y from world to tile coords.
	 *
	 * @param y
	 * @return the tile Y
	 */
	public static float getTileY(float y){
		float tempY = y - offset_y;
		tempY /= App.TILE_SIZE;
		return tempY;
		
	}
	
	/**
	 * Checks if is blocked.
	 * works for immovable tiles.
	 * @param x 
	 * @param y 
	 * @return true, if is blocked
	 */
	// and returns if that location is a blocked tile
	public static boolean isBlocked(float x, float y) {
		String temp = getTileType(x,y);
		if(temp.equals("wall") || temp.equals("cracked")){
			return true;
		} 
		return false;
			
	}
	
	/**
	 * Gets the tile type.
	 * works for immovable tiles.
	 * @param x the x
	 * @param y the y
	 * @return the tile type
	 */
	public static String getTileType(float x, float y){
		x = Loader.getTileX(x);
		y = Loader.getTileY(y);
		
		String temp = "floor";
		
		if (x >= 0 && x < world_width && y >= 0 && y < world_height) {
			temp = types[(int)x][(int)y];
		}
//		System.out.println("temp is: " + temp);
		return temp;
	}
	
	/**
	 * Gets the sprites in the tile ahead.
	 *
	 * @param dir the direction
	 * @param x 
	 * @param y 
	 * @return the next sprites
	 */
	public static ArrayList<Sprite> getNextSprites(int dir, float x, float y){
		
		float speed = 32;
		// Translate the direction to an x and y displacement
		float delta_x = 0,
			  delta_y = 0;

		switch (dir) {
			case Movable.DIR_LEFT:
				delta_x = -speed;
				break;
			case Movable.DIR_RIGHT:
				delta_x = speed;
				break;
			case Movable.DIR_UP:
				delta_y = -speed;
				break;
			case Movable.DIR_DOWN:
				delta_y = speed;
				break;
		}
		return World.getSpritesAt(x + delta_x, y + delta_y);
		
	}
	
	/**
	 * Checks if is pushable.
	 *
	 * @param x
	 * @param y 
	 * @return true, if is pushable
	 */
	public static boolean isPushable(float x, float y) {
		ArrayList<Sprite> spriteList = World.getSpritesAt(x,y);
		for(Sprite temp : spriteList){
			if (temp.tag.contains("stone") || temp.tag.contains("ice") || temp.tag.contains("tnt")){
				return true;
			}
		}
		return false;
		
	}
		
	/**
	 * Loads the sprites from a given file.
	 *
	 * @param filename the filename
	 * @return the array list
	 */
	public static ArrayList<Sprite> loadSprites(String filename) {
		ArrayList<Sprite> list = new ArrayList<>();
		
		// Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			// Find the world size
			line = reader.readLine();
			String[] parts = line.split(",");
			world_width = Integer.parseInt(parts[0]);
			world_height = Integer.parseInt(parts[1]);
			
			// Create the array of object types
			types = new String[world_width][world_height];
			
			// Calculate the top left of the tiles so that the level is
			// centred
			offset_x = (App.SCREEN_WIDTH - world_width * App.TILE_SIZE) / 2;
			offset_y = (App.SCREEN_HEIGHT - world_height * App.TILE_SIZE) / 2;

			// Loop over every line of the file
			while ((line = reader.readLine()) != null) {
				String name;
				float x, y;
			
				// Split the line into parts
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
			
				if(name.equals("wall") || name.equals("floor") 
						|| name.equals("target") || name.equals("switch")
						|| name.equals("door") || name.equals("switch")){
					types[(int)x][(int)y] = name;
				}
					// Adjust for the grid
					x = offset_x + x * App.TILE_SIZE;
					y = offset_y + y * App.TILE_SIZE;
				
				// Create the sprite
				list.add(World.createSprite(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
