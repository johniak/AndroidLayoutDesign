package pl.edu.pwr.cookbook.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.view.CardView;
import pl.edu.pwr.cookbook.activities.MainActivity;
import pl.edu.pwr.cookbook.cards.RecipeCard;
import pl.edu.pwr.cookbook.model.Recipe;
import pl.edu.pwr.cookbook.model.RecipeResult;
import pl.edu.pwr.cookbook.model.Results;
import pl.edu.pwr.cookbook.network.APIClient;
import pl.edu.pwr.cookbook.network.APIErrorException;
import pl.edu.pwr.cookbook.utils.ViewsUtils;
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
        final ProgressDialog mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Please wait...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                getActivity().onBackPressed();
            }
        });
        mDialog.show();

        // load details
        new AsyncTask<Void, Void, Recipe>() {

            @Override
            protected Recipe doInBackground(Void... params) {
                APIClient api = APIClient.getInstance();

                try {
                    return api.getRecipe(getActivity());
                }catch(APIErrorException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Recipe recipe) {
                super.onPostExecute(recipe);
                getActivity().getActionBar().setTitle(recipe.getName());
                recipeRatingBar.setRating(recipe.getRating());
                totalTimeTextView.setText(recipe.getTime());
                ImageLoader.getInstance().displayImage(recipe.getImages().getHostedLargeUrl(), headerImageView);

                // set ingredients
                Card card = new Card(getActivity());
                List<String> ingredientsList= new ArrayList<String>();
                ingredientsCardView.setCard(card);
                ingredientsList.addAll(recipe.getIngredientLines());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ingredientsList);
                ingredientsListView.setAdapter(arrayAdapter);
                ViewsUtils.setListViewHeightBasedOnChildren(ingredientsListView);

                // tastes
                Card card2 = new Card(getActivity());
                tastesCardView.setCard(card2);
                saltyProgressBar.setProgress(recipe.getFlavors().getSalty());
                savoryProgressBar.setProgress(56);
                sourProgressBar.setProgress(recipe.getFlavors().getSour());
                bitterProgressBar.setProgress(recipe.getFlavors().getBitter());
                sweetProgressBar.setProgress(recipe.getFlavors().getSweet());
                spicyProgressBar.setProgress(30);

                // close dialog
                mDialog.dismiss();
            }
        }.execute();

        initDeatilButtons();
    }


    void initDeatilButtons(){

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
