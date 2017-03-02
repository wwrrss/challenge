package me.willermo.challenge.presenters;

import io.realm.OrderedRealmCollection;
import me.willermo.challenge.models.App;

/**
 * Created by william on 3/1/17.
 */

public interface AppListPresenterContract {
    void getApps(OrderedRealmCollection<App> orderedRealmCollection);
}
