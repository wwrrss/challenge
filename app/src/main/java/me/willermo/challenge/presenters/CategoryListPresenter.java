package me.willermo.challenge.presenters;

import io.realm.Realm;
import io.realm.RealmResults;
import me.willermo.challenge.models.Category;

/**
 * Created by william on 3/1/17.
 */

public class CategoryListPresenter implements BaseViewContract {
    private CategoryListPresenterContract view;
    private Realm realm;

    public CategoryListPresenter(CategoryListPresenterContract view){
        this.view = view;

    }

    private void queryData(){
        realm = Realm.getDefaultInstance();
        RealmResults<Category> realmResults= realm.where(Category.class).distinct("id");
        view.getCategories(realmResults);
    }


    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
        queryData();
    }

    @Override
    public void onDestroy() {
        realm.close();
        view=null;
    }
}
