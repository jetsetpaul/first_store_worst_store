package jetsetpaul.e_commerce_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by pauljoiner on 6/25/16.
 */
public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {
    List<Product> list;
    ArrayList<Product> results;
    Context context;
    View_Holder.IMyViewHolderClicks listener;

    public Recycler_View_Adapter(List<Product> product, View_Holder.IMyViewHolderClicks listener){
        list = product;
        this.listener = listener;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v, listener);
        return holder;
    }
    @Override
    public void onBindViewHolder(View_Holder holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.title.setText(list.get(position).title);
        holder.artist.setText(list.get(position).artist);
        holder.description.setText(list.get(position).description);
        holder.price.setText(list.get(position).price);
        holder.imageView.setImageResource(list.get(position).imageId);
        holder.bindData(list.get(position));

        //animate(holder);
    }
    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Product product) {
        list.add(position, product);
        notifyItemInserted(position);
    }
    // Remove a RecyclerView item containing a specified Product object
    public void remove(Product product) {
        int position = list.indexOf(product);
        list.remove(position);
        notifyItemRemoved(position);
    }
    public void switchList(List<Product> newList){
        this.list = newList;
        notifyDataSetChanged();
    }

}

