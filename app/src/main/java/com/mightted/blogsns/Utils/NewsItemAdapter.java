package com.mightted.blogsns.Utils;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.mightted.blogsns.Bean.NewsItem;
import com.mightted.blogsns.R;
import com.mightted.blogsns.View.ShowActivity;

import java.util.List;

/**
 * Created by 晓深 on 2017/4/17.
 */

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {

    List<NewsItem> newsList;

    public NewsItemAdapter(List<NewsItem> list) {
        newsList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText,userText,scanText,commentText;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);
            titleText  = (TextView)view.findViewById(R.id.txt_new_item_title);
            userText = (TextView)view.findViewById(R.id.txt_news_item_user);
            scanText = (TextView)view.findViewById(R.id.txt_news_item_scan);
            commentText = (TextView)view.findViewById(R.id.txt_news_item_comment);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_news_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsItem item = newsList.get(holder.getAdapterPosition());
                String id = item.getId();
                Intent intent = new Intent(parent.getContext(), ShowActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("title",item.getTitle());
                parent.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem item = newsList.get(position);
        holder.titleText.setText(item.getTitle());
        holder.userText.setText(item.getUser());
        holder.scanText.setText(item.getScan());
        holder.commentText.setText(item.getComment());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
