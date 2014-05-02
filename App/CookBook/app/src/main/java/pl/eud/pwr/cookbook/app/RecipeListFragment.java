package pl.eud.pwr.cookbook.app;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardGridView;
import it.gmariotti.cardslib.library.view.CardListView;
import pl.eud.pwr.cookbook.app.cards.RecipeCard;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by johniak8 on 5/2/14.
 */
public class RecipeListFragment extends Fragment {
    PullToRefreshLayout pullToRefreshLayout;
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
    void initPullToRefresh(){
        pullToRefreshLayout = (PullToRefreshLayout) getActivity().findViewById(R.id.carddemo_extra_ptr_layout);
        ActionBarPullToRefresh.from(this.getActivity())
                .allChildrenArePullable()
                .listener(onSingnalisedRefresh)
                .setup(pullToRefreshLayout);
    }
    OnRefreshListener onSingnalisedRefresh=new OnRefreshListener() {
        @Override
        public void onRefreshStarted(View view) {
            new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    pullToRefreshLayout.setRefreshComplete();
                }
            }.execute();
        }
    };
    void initCards(){

        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < 500; i++) {
            RecipeCard recipeCard = new RecipeCard(getActivity(),"Title "+i);
            recipeCard.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    ((MainActivity)getActivity()).showDetails();
                }
            });
            cards.add(recipeCard);
        }
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

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
}
