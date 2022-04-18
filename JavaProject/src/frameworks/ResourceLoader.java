package frameworks;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
/**
 * 
 * Resource loader designed to remove the hassle of exceptions and the hassle of operating system file pointers.
 * 
 * @author Joshua Ounalom(TheJodes)
 *
 */
public class ResourceLoader {

	
	private static final char file_seperate = File.separatorChar;
	
	private static HashMap<String, CachedContent> cachedContents = new HashMap<String, CachedContent>();
	
	static class CachedContent{
		Object content = null;
		String fileLocation = "";
		int requested = 0;
		long lastRequested = 0;
	}
	
	private static String convertStylesToOSDefault(String fileLocation) {
		if (file_seperate == '\\') {
			return convertStylesToWindow(fileLocation);
		}else
			return convertStylesToUnix(fileLocation);
	}
	private static String convertStylesToWindow(String fileLocation) {
		return fileLocation.replaceAll("/", "\\\\");
	}
	private static String convertStylesToUnix(String fileLocation) {
		return fileLocation.replaceAll("\\\\", "/");
	}
	
	private static void saveContentInCache(String fileLocation, Object contents) {
		CachedContent content = new CachedContent();
		content.content = contents;
		content.fileLocation = fileLocation;
		content.lastRequested = System.currentTimeMillis();
		content.requested += 1;
		cachedContents.put(fileLocation, content);
	}
	
	private static Object getContentFromCache(String fileLocation) {
		CachedContent content = cachedContents.get(fileLocation);
		content.requested += 1;
		content.lastRequested = System.currentTimeMillis();
		return cachedContents.get(fileLocation).content;
	}
	
	/**
	 * Attempt to clean the cache by setting a filter that selects which content it should unreference and garbage collect to free up memory.
	 * @param minimumRequestLevel (the amount of requests an asset should need to be preserved)
	 * @param maximumTimeLevel (the amount of time an asset should remain idle and unused)
	 */
	public static void clearUnusedContents(int minimumRequestLevel, long maximumTimeLevel) {
		class Class_1 implements Consumer<Map.Entry<String, CachedContent>>{

			@Override
			public void accept(Entry<String, CachedContent> entry) {
				// TODO Auto-generated method stub
				CachedContent content = entry.getValue();
				if (content.requested < minimumRequestLevel || System.currentTimeMillis()-content.lastRequested > maximumTimeLevel) {
					cachedContents.remove(entry.getKey());
				}
			}
			
		}
		cachedContents.entrySet().forEach(new Class_1());
	}
	
	/**
	 * Clears the cache.
	 */
	public static void clearCache() {
		cachedContents.clear();
	}
	/**
	 * 
	 * @param fileLocation (Unix styled, screw windows and their dumb file pointers)
	 * @return Image content
	 */
	public static Image readImage(String fileLocation) {
		fileLocation = convertStylesToOSDefault(fileLocation);
		Exception[] errors = new Exception[2];
		try {
			Image img = ImageIO.read(new File(fileLocation));
			saveContentInCache(fileLocation, img);
			return img;
		}
		catch(Exception er) {
			errors[0] = er;
		}try {
			System.out.println(ResourceLoader.class.getResource(fileLocation));
			Image img = ImageIO.read(ResourceLoader.class.getResource(fileLocation));
			saveContentInCache(fileLocation, img);
			return img;
		}
		catch(Exception er) {
			errors[1] = er;
		}
		ConsoleScript.error("Failed to retrieve the asset \"" + fileLocation + "\"from two locations: System File, Jar Archive");
		ConsoleScript.error("First error: " + errors[0]);
		ConsoleScript.error("First error: " + errors[1]);
		return null;
	}
}
