package me.willermo.challenge.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import me.willermo.challenge.R;
import me.willermo.challenge.messages.RecyclerViewClickMessage;
import me.willermo.challenge.models.App;

/**
 * Created by william on 3/1/17.
 */

public class AppListAdapter extends RealmRecyclerViewAdapter<App,AppListAdapter.ViewHolder> {

    private Context context;
    public AppListAdapter(Context context, OrderedRealmCollection<App> orderedRealmCollection){
        super(orderedRealmCollection,true);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final App app = super.getItem(position);
        holder.textViewAppName.setText(app.getTitle());
        Picasso.with(context).load(app.getImage()).fit().into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new RecyclerViewClickMessage(app.getId(),1));
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_app_list,parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cardAppText)
        TextView textViewAppName;

        @BindView(R.id.cardAppImage)
        ImageView imageView;

        @BindView(R.id.card_view)
        CardView cardView;

        public  ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
