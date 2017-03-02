package me.willermo.challenge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.willermo.challenge.R;
import me.willermo.challenge.models.App;
import me.willermo.challenge.presenters.AppDetailPresenter;
import me.willermo.challenge.presenters.AppDetailPresenterContract;
import me.willermo.challenge.presenters.BaseViewContract;

/**
 * Created by william on 3/2/17.
 */

public class FragmentAppDetail extends Fragment implements AppDetailPresenterContract {

    @BindView(R.id.fragmentAppDetailImage)
    ImageView imageView;
    @BindView(R.id.fragmentAppDetailTitle)
    TextView textViewTitle;
    @BindView(R.id.fragmentAppDetailSummary) TextView textViewSummary;

    private BaseViewContract presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_detail,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        int appId = bundle.getInt("id");
        presenter = new AppDetailPresenter(this,appId);
        presenter.onCreate();
        return view;
    }

    @Override
    public void getApp(App app) {
        Picasso.with(getActivity()).load(app.getImage()).fit().into(imageView);
        textViewTitle.setText(app.getTitle());
        textViewSummary.setText(app.getSummary());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        presenter=null;
    }
}
