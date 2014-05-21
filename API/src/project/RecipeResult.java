package project;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class RecipeResult {
	@SerializedName("recipeName")
	String name;
	Flavors flavors;
	ArrayList<String> ingredients;
	Integer rating;
	ArrayList<String> smallImageUrls;
	Float totalTimeInSeconds;
	
	public String getName() {
		return name;
	}
	public Flavors getFlavors() {
		return flavors;
	}
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	public Integer getRating() {
		return rating;
	}
	public String getIcon() {
		if(smallImageUrls.isEmpty())
			return "http://placehold.it/350x150";
		return smallImageUrls.get(0);
	}
	public Float getTotalTimeInSeconds() {
		return totalTimeInSeconds;
	}
}
