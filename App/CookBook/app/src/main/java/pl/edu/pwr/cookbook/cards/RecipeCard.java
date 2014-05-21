package pl.edu.pwr.cookbook.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import it.gmariotti.cardslib.library.internal.Card;
import pl.edu.pwr.cookbook.app.R;
import pl.edu.pwr.cookbook.model.RecipeResult;

/**
 * Created by johniak8 on 5/2/14.
 */
public class RecipeCard extends Card {
    RecipeResult recipe;

    public RecipeCard(Context context, RecipeResult recipe) {
        super(context, R.layout.recipe_list_item_card);
        this.recipe = recipe;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        TextView titleTextView = (TextView) parent.findViewById(R.id.RecipeTitleTextView);
        if (titleTextView != null)
            titleTextView.setText(recipe.getName());

        RatingBar myRatingBar = (RatingBar) parent.findViewById(R.id.myRatingBar);
        if (myRatingBar != null)
            myRatingBar.setRating(recipe.getRating());

        ImageView imageView = (ImageView) parent.findViewById(R.id.imageView);
        if (imageView != null) {
            // reset image to default
            imageView.setImageResource(R.drawable.empty_food_icon);
            ImageLoader.getInstance().displayImage(recipe.getIcon(), imageView);
        }
    }
}
