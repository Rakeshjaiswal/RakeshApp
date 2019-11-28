package com.rakesh.in.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dueeeke.tablayout.SegmentTabLayout;
import com.rakesh.in.R;

import java.util.ArrayList;


public class MallFragment extends Fragment {

    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

    SegmentTabLayout tabLayout_4;
    private String[] mTitles_2 = {"New Offers", "My Offers", "My Vouchers"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mall, container, false);

        for (String title : mTitles_2) {
            mFragments2.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
        }

        tabLayout_4 = view.findViewById(R.id.tl_4);

        tabLayout_4.setTabData(mTitles_2, getActivity(), R.id.fl_change, mFragments2);

        return view;
    }
}
