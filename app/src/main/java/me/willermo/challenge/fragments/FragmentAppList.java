package me.willermo.challenge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import me.willermo.challenge.R;
import me.willermo.challenge.adapters.AppListAdapter;
import me.willermo.challenge.models.App;
import me.willermo.challenge.presenters.AppListPresenter;
import me.willermo.challenge.presenters.AppListPresenterContract;
import me.willermo.challenge.presenters.BaseViewContract;

/**
 * Created by william on 3/1/17.
 */

public class FragmentAppList extends Fragment implements AppListPresenterContract {
    private AppListAdapter adapter;
    @BindView(R.id.fragmentRecyclerView)
    RecyclerView recyclerView;
    BaseViewContract appListPresenter;
    private int categoryId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        categoryId = bundle.getInt("id");
        appListPresenter = new AppListPresenter(this,categoryId);
        appListPresenter.onCreate();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void getApps(OrderedRealmCollection<App> orderedRealmCollection) {
        adapter = new AppListAdapter(getActivity(),orderedRealmCollection);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        appListPresenter.onDestroy();
        super.onDestroy();
    }
}
