package com.cookandroid.android_project1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> {
    private Context context;
    private List<ApiResponse.DrugItem> items;

    public DrugAdapter(Context context, List<ApiResponse.DrugItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drug, parent, false);
        return new DrugViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {
        ApiResponse.DrugItem item = items.get(position);
        holder.itemName.setText(item.getItemName());
        holder.entpName.setText(item.getEntpName());
        Glide.with(context).load(item.getItemImage()).into(holder.itemImage);

        // 별 버튼 상태 변경
        if (item.isFavorite()) {
            holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
        }

        // 별 버튼 클릭 처리
        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 즐겨찾기 상태 토글
                item.setFavorite(!item.isFavorite());
                if (item.isFavorite()) {
                    holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
                } else {
                    holder.favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DrugDetailActivity.class);
                intent.putExtra("entpName", item.getEntpName());
                intent.putExtra("itemName", item.getItemName());
                intent.putExtra("efcyQesitm", item.getEfcyQesitm());
                intent.putExtra("useMethodQesitm", item.getUseMethodQesitm());
                intent.putExtra("atpnQesitm", item.getAtpnQesitm());
                intent.putExtra("depositMethodQesitm", item.getDepositMethodQesitm());
                intent.putExtra("itemImage", item.getItemImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class DrugViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, entpName;
        ImageView itemImage;
        ImageButton favoriteButton;


        public DrugViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            entpName = itemView.findViewById(R.id.entp_name);
            itemImage = itemView.findViewById(R.id.item_image);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
        }
    }
}
