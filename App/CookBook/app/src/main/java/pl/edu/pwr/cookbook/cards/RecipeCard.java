package pl.edu.pwr.cookbook.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.Card;
import pl.edu.pwr.cookbook.app.R;

/**
 * Created by johniak8 on 5/2/14.
 */
public class RecipeCard extends Card {
    String title;


    public RecipeCard(Context context, String title) {
        super(context, R.layout.recipe_list_item_card);
        this.title = title;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        TextView titleTextView = (TextView) parent.findViewById(R.id.RecipeTitleTextView);
        if (titleTextView != null)
            titleTextView.setText(this.title);
    }
}
