package me.willermo.challenge.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

import me.willermo.challenge.R;
import me.willermo.challenge.messages.RecyclerViewClickMessage;
import me.willermo.challenge.models.Category;

/**
 * Created by william on 3/1/17.
 */

public class CategoryListAdapter extends RealmRecyclerViewAdapter<Category,CategoryListAdapter.ViewHolder> {
    private Context context;

    public CategoryListAdapter(android.content.Context context, OrderedRealmCollection<Category> categories){
        super(categories,true);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_caterogy_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category category = getData().get(position);
        holder.textViewCategoryName.setText(category.getTerm());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RecyclerViewClickMessage(category.getId(),0));
            }
        });

    }

    public void updateData(OrderedRealmCollection<Category> categories){
        super.updateData(categories);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

         @BindView(R.id.cardCategoryText)
        TextView textViewCategoryName;

        @BindView(R.id.card_view)
        CardView cardView;

        public  ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
