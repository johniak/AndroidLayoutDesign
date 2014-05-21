package project;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
 
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ApiParser {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Gson gson = new Gson();
        
        // list call
        Results result;
		try {
			result = gson.fromJson(IOUtils.toString(new URL("http://isweb.eu/web/jd/fakeAPI/list.json")), Results.class);
	        for (RecipeResult recipe : result.recipes) {
	        	System.out.println(recipe.getName());
	        	System.out.format("\t" + recipe.getIcon() + "\n");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// details call
		Recipe recipe;
		try {
			recipe = gson.fromJson(IOUtils.toString(new URL("http://isweb.eu/web/jd/fakeAPI/more.json")), Recipe.class);
			System.out.println(recipe.name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
