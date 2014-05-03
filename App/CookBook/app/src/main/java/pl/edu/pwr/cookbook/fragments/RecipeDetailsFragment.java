package pl.edu.pwr.cookbook.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardView;
import pl.edu.pwr.cookbook.Utils.ViewsUtils;
import pl.edu.pwr.cookbook.app.R;

/**
 * Created by johniak8 on 5/2/14.
 */
@EFragment
public class RecipeDetailsFragment extends Fragment {

    private FadingActionBarHelper fadingHelper;
    private Bundle arguments;

    @ViewById
    ImageView headerImageView;
    @ViewById
    CardView tastesCardView;
    @ViewById
    CardView ingredientsCardView;
    @ViewById
    CardView detailsButtonCardView;

    @ViewById
    RatingBar recipeRatingBar;
    @ViewById
    TextView totalTimeTextView;

    @ViewById
    ListView ingredientsListView;

    @ViewById
    ProgressBar saltyProgressBar;
    @ViewById
    ProgressBar savoryProgressBar;
    @ViewById
    ProgressBar sourProgressBar;
    @ViewById
    ProgressBar bitterProgressBar;
    @ViewById
    ProgressBar sweetProgressBar;
    @ViewById
    ProgressBar spicyProgressBar;


    @ViewById
    Button emailIngredientsButton;
    @ViewById
    Button readFullDirectionsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = fadingHelper.createView(getActivity());
        return view;
    }

    @AfterViews
    void initCardsViews() {
        headerImageView.setImageResource(R.drawable.ny);
        initOverlay();
        initIngredientsCard();
        initTastesCard();
        initDeatilButtons();
    }

    void initOverlay(){
        recipeRatingBar.setRating(1.5f);
        totalTimeTextView.setText("15 min");
    }

    void initIngredientsCard() {
        Card card = new Card(getActivity());
        List<String> ingredientsList= new ArrayList<String>();
        ingredientsCardView.setCard(card);
        ingredientsList.add("Butter");
        ingredientsList.add("Rice");
        ingredientsList.add("Meat");
        ingredientsList.add("Milk");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ingredientsList);
        ingredientsListView.setAdapter(arrayAdapter);
        ViewsUtils.setListViewHeightBasedOnChildren(ingredientsListView);
    }

    void initTastesCard() {
        Card card = new Card(getActivity());
        tastesCardView.setCard(card);
        saltyProgressBar.setProgress(10);
        savoryProgressBar.setProgress(15);
        sourProgressBar.setProgress(40);
        bitterProgressBar.setProgress(20);
        sweetProgressBar.setProgress(70);
        spicyProgressBar.setProgress(30);
    }
    void initDeatilButtons(){
        Card card = new Card(getActivity());
        detailsButtonCardView.setCard(card);
        emailIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        readFullDirectionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        arguments = getArguments();
        fadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background_light)
                .headerLayout(R.layout.header)
                .contentLayout(R.layout.recipe_details)
                .lightActionBar(false);
        fadingHelper.headerOverlayLayout(R.layout.recipe_overlay);
        fadingHelper.initActionBar(activity);
    }
}
