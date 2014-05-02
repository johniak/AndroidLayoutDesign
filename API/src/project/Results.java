package project;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Results {
	
	@SerializedName("matches")
	public ArrayList<RecipeResult> recipes;
}
