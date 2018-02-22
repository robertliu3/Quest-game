package group52.comp3004;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/*DESCRIPTION: Storage class for of all Images and ImagePatterns we need
 * 			   Can reference this for each
 *FORM: Image-> thingImg
 *		ImagePattern(turns image into a paint for fill)-> thing
 *Author: Sandy
 */
public class ResourceManager {//ISSSUE: multiple resource managers are being made?
	//Resources
	private String url = "image/Cards/";
	private Image        adventureBackImg;
	private ImagePattern adventureBack;
	private Image        storyBackImg;
	private ImagePattern storyBack;
	
	private HashMap<String, Image> frontsImg;
	private HashMap<String, ImagePattern> fronts;
	
	public ResourceManager() {
		adventureBackImg = new Image(url+"/Backs/adventure_back.jpg");
		adventureBack = new ImagePattern(adventureBackImg);
		
		storyBackImg = new Image(url+"/Backs/story_back.jpg");
		storyBack = new ImagePattern(storyBackImg);
		
		frontsImg = new HashMap<String, Image>();
		fronts = new HashMap<String, ImagePattern>();
		//System.out.println("Resource manager loaded");
	}
	
	public ImagePattern getAdventureBack() {
		return adventureBack;
	}
	
	public ImagePattern getStoryBack() {
		//System.out.println("sending story back");
		return storyBack;
	}
	
	//returns the image pattern of the name
	//if the image pattern doesnt yet exist then it loads it and returns the pattern
	//type values: STORY->s ADVENTURE->a
	public ImagePattern getFront(String name, char type) {//need to find out how names are found
		//System.out.println("Looking for face: " + name);
		if(fronts.containsKey(name)) {	
			//System.out.println("Pattern found: "+ name);
			return fronts.get(name);
		}
		else {
			//System.out.println("Pattern not found");
			Image img = new Image("image/linen-texture.jpg");
			if(type == 'a' || type == 'A') {
				//System.out.println(url+"Adventure/"+name+".jpg");
				img = new Image(url+"Adventure/"+name+".jpg");
			}
			else if(type == 's' || type == 'S') {
				img = new Image(url+"Story/"+name+".jpg");
			}
			ImagePattern pattern = new ImagePattern(img);
			fronts.put(name, pattern);
			return pattern;
		}
	}
}
