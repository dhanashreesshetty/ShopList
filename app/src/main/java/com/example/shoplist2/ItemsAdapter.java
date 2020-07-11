package com.example.shoplist2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    List<ItemCard> items;

    private Context context;
    private static RecyclerViewClickListener itemListener;

    public ItemsAdapter(List<ItemCard> items) {
        this.items=items;
    }

    public ItemsAdapter(Context context, RecyclerViewClickListener itemListener) {
        this.context=context;
        this.itemListener=itemListener;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View ItemsView=inflater.inflate(R.layout.list_item, parent, false);
        return new ItemsViewHolder(ItemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).itemName);
        holder.itemPrice.setText(String.valueOf(items.get(position).itemPrice));
        holder.itemQuantity.setText(String.valueOf(items.get(position).itemQuantity));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemName, itemPrice, itemQuantity;
        ImageView delete;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.itemName);
            itemPrice=itemView.findViewById(R.id.itemPrice);
            itemQuantity=itemView.findViewById(R.id.itemQuantity);
            delete=itemView.findViewById(R.id.iconDelete);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
            Toast.makeText(context, String.valueOf(this.getLayoutPosition()), Toast.LENGTH_SHORT).show();
        }
    }

}
