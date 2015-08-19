package com.jerry.bullshit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jerry.bullshit.R;

/**
 * 首页
 *
 * Created by Administrator on 2015/8/19.
 */
public class HomeFragment extends BaseFragment{

    private static final String TAG = "HomeFragment";

    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
    }

    private void initView(View view) {
        mRefresh = $(view, R.id.refresh);
        mRecyclerView = $(view, R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(manager);

//        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
    }

    private void initEvent() {
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "onRefresh...'");
            }
        });
    }

    public <T> T $(View view, @IdRes int viewID) {
        return (T) view.findViewById(viewID);
    }
}
