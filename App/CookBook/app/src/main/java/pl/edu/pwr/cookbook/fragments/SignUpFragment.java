package pl.edu.pwr.cookbook.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;

import org.androidannotations.annotations.EFragment;

import pl.edu.pwr.cookbook.app.R;

/**
 * Created by johniak8 on 5/3/2014.
 */
@EFragment(R.layout.sign_up)
public class SignUpFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        ActionBar actionBar = activity.getActionBar();
        actionBar.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.ab_background_light));
        activity.setTitle("Sign up");
        super.onAttach(activity);
    }
}
