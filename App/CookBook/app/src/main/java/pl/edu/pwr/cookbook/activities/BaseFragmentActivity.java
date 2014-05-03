package pl.edu.pwr.cookbook.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.cookbook.app.R;

/**
 * Created by johniak8 on 5/3/14.
 */
public class BaseFragmentActivity extends Activity {
    private List<Fragment> fragmentStack= new ArrayList<Fragment>();
    /**
     * Create new fragment and add it to fragment stack
     */
    public final static int FLAG_FRAGMENT_NORMAL=1;
    /**
     * Remove old fragments from stack and create new
     */
    public final static int FLAG_FRAGMENT_SINGLE_TOP=FLAG_FRAGMENT_NORMAL|2;
    /**
     * Bring to front existing activity or start new
     */
    public final static int FLAG_FRAGMENT_BROUGHT_TO_FRONT=4;
    /**
     * Clear fragment stack, and leavs one last fragment you can connect it with
     * FLAG_FRAGMENT_NORMAL
     * FLAG_FRAGMENT_SINGLE_TOP
     * FLAG_FRAGMENT_BROUGHT_TO_FRONT
     */
    public final static int FLAG_FRAGMENT_CLEAR_TOP=8;


    public void startFragment(Class<?> fragmentClass){
        startFragment(fragmentClass,FLAG_FRAGMENT_NORMAL,null);
    }
    public void startFragment(Class<?> fragmentClass,int flags){
        startFragment(fragmentClass,flags,null);
    }
    public void startFragment(Class<?> fragmentClass,int flags,Bundle args){
        if((flags&FLAG_FRAGMENT_SINGLE_TOP)==FLAG_FRAGMENT_SINGLE_TOP){
            int fragmentIndex=indexOfFragmentStack(fragmentClass);
            if(fragmentIndex>=0){
                fragmentStack.remove(fragmentIndex);
            }
        }
        if((flags&FLAG_FRAGMENT_BROUGHT_TO_FRONT)==FLAG_FRAGMENT_BROUGHT_TO_FRONT){
            int fragmentIndex=indexOfFragmentStack(fragmentClass);
            if(fragmentIndex>=0){
                Fragment fragment= fragmentStack.get(fragmentIndex);
                if(args!=null){
                    fragment.setArguments(args);
                }
                fragmentStack.remove(fragmentIndex);
                fragmentStack.add(0,fragment);
                setFragment(fragment);
            }else{
                flags|=FLAG_FRAGMENT_NORMAL;
            }
        }
        if((flags&FLAG_FRAGMENT_NORMAL)==FLAG_FRAGMENT_NORMAL){
            try {
                Constructor<?> ctor = null;
                ctor = fragmentClass.getConstructor();
                Fragment fragment = (Fragment)ctor.newInstance();
                if(args!=null){
                    fragment.setArguments(args);
                }
                setFragment(fragment);
                fragmentStack.add(0,fragment);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if((flags&FLAG_FRAGMENT_CLEAR_TOP)==FLAG_FRAGMENT_CLEAR_TOP){
            for(int i=1;i<fragmentStack.size();i++){
                fragmentStack.remove(i);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(fragmentStack.size()==1||fragmentStack.size()==0){
            super.onBackPressed();
            return;
        }
        fragmentStack.remove(0);
        Fragment fragment=fragmentStack.get(0);
        setFragment(fragment);
    }

    public void setFragment(Fragment fragment){
        setFragment(fragment,-1,-1);
    }
    public void setFragment(Fragment fragment,int animation1,int animation2){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        if(animation1>=0&&animation2>=0){
            fragmentTransaction.setCustomAnimations(animation1,animation2);
        }
        fragmentTransaction.replace(R.id.content_frame, fragment)
                .commit();
    }
    private int indexOfFragmentStack(Class<?> fragmentClass){
        for(int i=0;i<fragmentStack.size();i++){
            if(fragmentStack.get(i).getClass().equals(fragmentClass))
                return i;
        }
        return -1;
    }
}
