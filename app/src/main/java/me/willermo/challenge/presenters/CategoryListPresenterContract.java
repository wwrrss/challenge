package me.willermo.challenge.presenters;

import io.realm.OrderedRealmCollection;
import me.willermo.challenge.models.Category;

/**
 * Created by william on 3/1/17.
 */

public interface CategoryListPresenterContract {
    void getCategories(OrderedRealmCollection<Category> categoryOrderedRealmCollection);

}
