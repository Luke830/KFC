package kg.korea.kfc.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

/**
 * Created by itsm02 on 2017. 3. 28..
 */

public class UtilComponent {

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), null);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), null);
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Object object) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), (object != null ? object.getClass().getName() : null));
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, String object) {
        replaceFragement(activity, layoutId, fragment, null, fragment.getClass().getName(), (object != null ? object : null));
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, Object object) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), (object != null ? object.getClass().getName() : null));
    }

    public static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, String object) {
        replaceFragement(activity, layoutId, fragment, bundle, fragment.getClass().getName(), (object != null ? object : null));
    }

    private static void replaceFragement(FragmentActivity activity, int layoutId, Fragment fragment, Bundle bundle, String tag, String addToBackStack) {
//        Kog.e("activity = " + activity + " layoutId = " + layoutId + " bundle = " + bundle + " tag = " + tag + " addToBackStack = " + addToBackStack);

//        Kog.e(" layoutId = " + layoutId + " bundle = " + bundle + " tag = " + tag + " addToBackStack = " + addToBackStack);

        try {
            if (activity != null) {
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                if (transaction != null) {
                    if (bundle != null) {
                        fragment.setArguments(bundle);
                    }
                    transaction.replace(layoutId, fragment, tag);
                    if (addToBackStack != null) {
                        transaction.addToBackStack(addToBackStack);
                    }
                    transaction.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideAddFragment(FragmentActivity activity, Fragment hideFragment, int layoutId, Fragment addFragment, Bundle bundle, Object object) {
        hideAddFragment(activity, hideFragment, layoutId, addFragment, bundle, addFragment.getClass().getName(), (object != null ? object.getClass().getName() : null));
    }

    public static void hideAddFragment(FragmentActivity activity, Fragment hideFragment, int layoutId, Fragment addFragment, Bundle bundle, String tag, String addToBackStack) {
        if (activity != null) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            if (transaction != null) {
                if (bundle != null) {
                    addFragment.setArguments(bundle);
                }
                transaction.hide(hideFragment);
                transaction.add(layoutId, addFragment, tag);
                if (addToBackStack != null) {
                    transaction.addToBackStack(addToBackStack);
                }
                transaction.commit();
            }
        }
    }

    public static boolean checkCameraPermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

}
