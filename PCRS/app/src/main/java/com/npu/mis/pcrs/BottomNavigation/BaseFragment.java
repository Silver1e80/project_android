package com.npu.mis.pcrs.BottomNavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.npu.mis.pcrs.R;

import java.util.Arrays;
import java.util.List;

public class BaseFragment extends Fragment implements RecyclerViewAdapter.OnItemClickHandler {
    //coordinatorLayout_toolbar_scrollView
    //private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    //private LinearLayoutManager linearLayoutManager;
    //private Button button;
    private List<String> dataList;

    public static BaseFragment newInstance(String info) {
        BaseFragment fragment = new BaseFragment();
        Bundle args = new Bundle();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        //coordinatorLayout_toolbar_scrollView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter = new RecyclerViewAdapter(initData(), this);
        recyclerView.setAdapter(recyclerViewAdapter);
        return view;
    }

    private List<String> initData() {
        dataList = Arrays.asList(getResources().getStringArray(R.array.languages));
        return dataList;
    }

    @Override
    public void OnItemClick(String text) {
        Toast.makeText(getContext(), "你點擊了" + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnItemRemove(final int position, final String text) {
        Snackbar.make(getView(), "刪除" + text, Snackbar.LENGTH_LONG).setAction("確定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "你刪除了" + text, Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}