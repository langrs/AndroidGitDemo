package lzm.com.androidgitdemo.activity.tabHost;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lzm.com.androidgitdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabHostFragmentTwo extends Fragment {


    public TabHostFragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_host_fragment_two, container, false);
    }

}
