package pl.edu.pwr.cookbook.model;

import com.google.gson.annotations.SerializedName;

public class Flavors {
    @SerializedName("Meaty")
    Double meaty;
    @SerializedName("Salty")
	Double salty;
    @SerializedName("Sour")
	Double sour;
    @SerializedName("Sweet")
	Double sweet;
    @SerializedName("Bitter")
	Double bitter;
    @SerializedName("Piquant")
	Double piquant;

    public Flavors() {
        meaty = 0.0;
        salty = 0.0;
        sour = 0.0;
        sweet = 0.0;
        bitter = 0.0;
        piquant = 0.0;
    }

    public Integer getMeaty() {
        return (int) (meaty*100);
    }

    public Integer getSalty() {
        return (int) (salty*100);
    }

    public Integer getSour() {
        return (int) (sour*100);
    }

    public Integer getSweet() {
        return (int) (sweet*100);
    }

    public Integer getBitter() {
        return (int) (bitter*100);
    }

    public Integer getPiquant() {
        return (int) (piquant*100);
    }
}
