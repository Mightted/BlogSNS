package com.mightted.blogsns.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mightted.blogsns.Bean.NewsItem;
import com.mightted.blogsns.R;

import java.util.List;

/**
 * Created by 晓深 on 2017/4/12.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsItem> newsList;

    public NewsAdapter(List<NewsItem> newsList) {
        this.newsList = newsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText,userText,scanText,commentText;

        public ViewHolder(View view) {
            super(view);
            titleText = (TextView) view.findViewById(R.id.txt_new_item_title);
            userText = (TextView) view.findViewById(R.id.txt_news_item_user);
            scanText = (TextView) view.findViewById(R.id.txt_news_item_scan);
            commentText = (TextView) view.findViewById(R.id.txt_news_item_comment);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_news_item,parent,false);
        return new ViewHolder(view);
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
