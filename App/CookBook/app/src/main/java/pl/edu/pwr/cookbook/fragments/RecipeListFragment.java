package pl.edu.pwr.cookbook.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardGridView;
import pl.edu.pwr.cookbook.activities.MainActivity;
import pl.edu.pwr.cookbook.app.R;
import pl.edu.pwr.cookbook.cards.RecipeCard;
import pl.edu.pwr.cookbook.model.Recipe;
import pl.edu.pwr.cookbook.model.RecipeResult;
import pl.edu.pwr.cookbook.model.Results;
import pl.edu.pwr.cookbook.network.APIClient;
import pl.edu.pwr.cookbook.network.APIErrorException;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by johniak8 on 5/2/14.
 */
public class RecipeListFragment extends Fragment {
    PullToRefreshLayout pullToRefreshLayout;
    CardArrayAdapter mCardArrayAdapter;
    public static final String FRAMGENT_TITLE="fragment_title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_list, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPullToRefresh();
        initCards();
    }

    @Override
    public void onAttach(Activity activity) {
        ActionBar actionBar = activity.getActionBar();
        actionBar.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.ab_background_light));
        Bundle args= getArguments();
        if(args!=null)
            activity.setTitle(args.getString(FRAMGENT_TITLE,"Popular Now"));
        else
            activity.setTitle("Popular Now");
        super.onAttach(activity);
    }

    void initPullToRefresh(){
        pullToRefreshLayout = (PullToRefreshLayout) getActivity().findViewById(R.id.carddemo_extra_ptr_layout);
        ActionBarPullToRefresh.from(this.getActivity())
                .allChildrenArePullable()
                .listener(onSignalisedRefresh)
                .setup(pullToRefreshLayout);
    }

    Card.OnCardClickListener onCardClickListener = new Card.OnCardClickListener() {
        @Override
        public void onClick(Card card, View view) {
            final ProgressDialog mDialog = new ProgressDialog(getActivity());
            final AsyncTask<Void, Void, Recipe> at = new AsyncTask<Void, Void, Recipe>() {

                @Override
                protected Recipe doInBackground(Void... params) {
                    APIClient api = APIClient.getInstance();

                    try {
                        Recipe recipe = api.getRecipe(getActivity());
                        if (isCancelled()) return null;
                        return recipe;
                    }catch(APIErrorException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Recipe recipe) {
                    super.onPostExecute(recipe);
                    mDialog.dismiss();

                    if(recipe != null) {
                        ((MainActivity) getActivity()).showRecipeDetails(recipe);
                    }
                }
            };

            mDialog.setMessage("Please wait...");
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mDialog.dismiss();
                    at.cancel(true);
                }
            });
            mDialog.show();
            at.execute();
        }
    };

    OnRefreshListener onSignalisedRefresh = new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {
            new AsyncTask<Void, Void, Results>() {

                @Override
                protected Results doInBackground(Void... params) {
                    APIClient api = APIClient.getInstance();

                    try {
                        return api.getResults(getActivity());
                    }catch(APIErrorException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Results results) {
                    super.onPostExecute(results);

                    mCardArrayAdapter.clear();
                    for(RecipeResult result : results.recipes) {
                        RecipeCard recipeCard = new RecipeCard(getActivity(), result);
                        recipeCard.setOnClickListener(onCardClickListener);
                        mCardArrayAdapter.add(recipeCard);
                    }
                    mCardArrayAdapter.notifyDataSetChanged();
                    pullToRefreshLayout.setRefreshComplete();
                }
            }.execute();
        }
    };

    void initCards(){

        ArrayList<Card> cards = new ArrayList<Card>();

        mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
        onSignalisedRefresh.onRefreshStarted(null);

        CardGridView gridView = (CardGridView) getActivity().findViewById(R.id.RecipeCardsListView);
        if (gridView != null) {
            gridView.setAdapter(mCardArrayAdapter);
            gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {

        super.onHiddenChanged(hidden);
    }
}
