package me.willermo.challenge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import me.willermo.challenge.R;
import me.willermo.challenge.adapters.CategoryListAdapter;
import me.willermo.challenge.messages.RecyclerViewClickMessage;
import me.willermo.challenge.messages.StartAppListMessage;
import me.willermo.challenge.models.Category;
import me.willermo.challenge.presenters.BaseViewContract;
import me.willermo.challenge.presenters.CategoryListPresenter;
import me.willermo.challenge.presenters.CategoryListPresenterContract;
import me.willermo.challenge.utils.ItemClickSupport;


/**
 * Created by william on 3/1/17.
 */

public class FragmentCategories extends Fragment implements CategoryListPresenterContract {

    @BindView(R.id.fragmentRecyclerView)
    RecyclerView recyclerView;

    CategoryListAdapter categoryListAdapter;
    BaseViewContract categoryListPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler,container,false);
        ButterKnife.bind(this,view);
        categoryListPresenter = new CategoryListPresenter(this);
        categoryListPresenter.onCreate();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }


    @Override
    public void getCategories(OrderedRealmCollection<Category> categoryOrderedRealmCollection) {
            categoryListAdapter = new CategoryListAdapter(getActivity(),categoryOrderedRealmCollection);
            recyclerView.setAdapter(categoryListAdapter);
    }

    @Override
    public void onDestroyView() {
        categoryListPresenter.onDestroy();
        categoryListPresenter=null;
        super.onDestroyView();
    }
}
