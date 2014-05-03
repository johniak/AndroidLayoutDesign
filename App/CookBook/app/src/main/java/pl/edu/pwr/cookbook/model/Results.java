package pl.edu.pwr.cookbook.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Results {
	
	@SerializedName("matches")
	public ArrayList<RecipeResult> recipes;
}
