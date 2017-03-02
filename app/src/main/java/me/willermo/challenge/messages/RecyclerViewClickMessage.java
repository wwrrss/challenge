package me.willermo.challenge.messages;

import android.support.v7.widget.RecyclerView;

/**
 * Created by william on 3/2/17.
 */

public class RecyclerViewClickMessage {
    public int id,listType;
    // listType 0==1 Categories 1==Apps
    public RecyclerViewClickMessage(Integer id, Integer listType){
        this.id = id;
        this.listType = listType;
    }
}
