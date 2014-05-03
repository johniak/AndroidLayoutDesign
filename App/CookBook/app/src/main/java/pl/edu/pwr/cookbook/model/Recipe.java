package pl.edu.pwr.cookbook.model;

import java.util.ArrayList;

public class Recipe {
	String name;
	Flavors flavors;
	ArrayList<String> ingredientLines;
    Integer rating;
	Source source;
	Double totalTimeInSeconds;
    ArrayList<Image> images;

    public Image getImages() {
        if (images.size() == 0)
            return new Image();
        return images.get(0);
    }

    public String getName() {
        return name;
    }

    public Flavors getFlavors() {
        return flavors;
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public Integer getRating() {
        return rating;
    }

    public Source getSource() {
        return source;
    }

    public Double getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public String getTime() {
        return String.format("%d min", (int) (totalTimeInSeconds / 60));
    }
}
