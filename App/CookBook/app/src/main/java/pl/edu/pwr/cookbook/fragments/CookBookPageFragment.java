package pl.edu.pwr.cookbook.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

import pl.edu.pwr.cookbook.app.R;

/**
 * Created by johniak8 on 5/2/14.
 */
public class CookBookPageFragment extends Fragment{

    private FadingActionBarHelper fadingHelper;
    private Bundle arguments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = fadingHelper.createView(getActivity());
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        arguments = getArguments();
        fadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.ab_background_light)
                .headerLayout(R.layout.header_light)
                .contentLayout(R.layout.activity_main)
                .lightActionBar(false);
        fadingHelper.initActionBar(activity);
    }
}
