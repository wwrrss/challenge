package me.willermo.challenge.presenters;

import io.realm.Realm;
import io.realm.RealmResults;
import me.willermo.challenge.models.App;

/**
 * Created by william on 3/1/17.
 */

public class AppListPresenter implements BaseViewContract {

    Realm realm;
    private AppListPresenterContract view;
    private Integer categoryId;

    public AppListPresenter(AppListPresenterContract view,Integer categoryId){
        this.view=view;
        this.categoryId = categoryId;

    }

    private void queryData(){
        RealmResults<App> realmResults= realm.where(App.class).equalTo("category.id",categoryId).findAll();
        view.getApps(realmResults);

    }
    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
        queryData();
    }

    @Override
    public void onDestroy() {
        realm.close();
    }
}
