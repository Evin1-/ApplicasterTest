package com.example.applicastertest.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.applicastertest.data.entities.TweetSearch;

import java.util.List;

/**
 * Created by user on 3/29/17.
 */

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<TweetSearch> tweets;

    public SearchAdapter(List<TweetSearch> tweets) {
        this.tweets = tweets;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        holder.bind(tweets.get(position));
    }

    @Override
    public int getItemCount() {
        return tweets != null
                ? tweets.size()
                : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }

        void bind(TweetSearch tweetSearch) {
            textView.setText(tweetSearch.getText());
        }
    }
}
