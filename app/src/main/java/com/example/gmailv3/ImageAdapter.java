package com.example.gmailv3;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ContactModel> items;
    List<ContactModel> items2;
    public ImageAdapter(List<ContactModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_1, parent, false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailViewHolder viewHolder = (EmailViewHolder) holder;
        ContactModel item = items.get(position);

        viewHolder.textLetter.setText(item.getName().substring(0, 1));

        Drawable background = viewHolder.textLetter.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(item.getColor(), PorterDuff.Mode.SRC_ATOP));

        viewHolder.textName.setText(item.getName());
        viewHolder.textSubject.setText(item.getSubject());
        viewHolder.textContent.setText(item.getContent());
        viewHolder.textTime.setText(item.getTime());
        if (item.isFavorite())
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star);

        else
            viewHolder.imageFavorite.setImageResource(R.drawable.ic_star_border);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class EmailViewHolder extends RecyclerView.ViewHolder {
        TextView textLetter;
        TextView textName;
        TextView textSubject;
        TextView textContent;
        TextView textTime;
        ImageView imageFavorite;


        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);



            textLetter = itemView.findViewById(R.id.text_letter);
            textName = itemView.findViewById(R.id.text_name);
            textSubject = itemView.findViewById(R.id.text_subject);
            textContent = itemView.findViewById(R.id.text_content);
            textTime = itemView.findViewById(R.id.text_time);
            imageFavorite = itemView.findViewById(R.id.image_favorite);

            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isFavorite = items.get(getAdapterPosition()).isFavorite();
                    items.get(getAdapterPosition()).setFavorite(!isFavorite);
                    notifyDataSetChanged();
                }
            });
        }
    }
}

