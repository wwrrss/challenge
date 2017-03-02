package me.willermo.challenge.presenters;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import me.willermo.challenge.models.App;

/**
 * Created by william on 3/2/17.
 */

public class AppDetailPresenter implements BaseViewContract {
    private AppDetailPresenterContract view;
    private int appId;
    private Realm realm;
    private App app;
    public AppDetailPresenter(AppDetailPresenterContract view, int appId){
        this.view = view;
        this.appId =appId;
    }

    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
        queryData();
    }

    @Override
    public void onDestroy() {
        realm.close();
        view = null;
    }
    private void queryData(){
        app= realm.where(App.class).equalTo("id",appId).findFirst();
        view.getApp(app);
    }
}
