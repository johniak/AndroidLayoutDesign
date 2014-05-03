package pl.edu.pwr.cookbook.model;

import java.util.ArrayList;

public class Recipe {
	String name;
	Flavors flavors;
	ArrayList<String> ingredientLines;
	Integer rating;
	Source source;
	Double totalTimeInSeconds;
}
