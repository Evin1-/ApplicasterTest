package com.example.applicastertest.ui.search;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.applicastertest.R;
import com.example.applicastertest.data.entities.TweetSearch;

import java.util.List;

/**
 * Created by user on 3/29/17.
 */

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private static final String TAG = "SearchAdapterTAG_";

    private List<TweetSearch> tweets;

    public SearchAdapter(List<TweetSearch> tweets) {
        this.tweets = tweets;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_search, parent, false);
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

        private final ImageView imageView;
        private final TextView textViewName;
        private final TextView textViewFollowers;
        private final TextView textViewText;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.r_search_pic);
            textViewName = (TextView) itemView.findViewById(R.id.r_search_name);
            textViewFollowers = (TextView) itemView.findViewById(R.id.r_search_followers);
            textViewText = (TextView) itemView.findViewById(R.id.r_search_text);
        }

        void bind(TweetSearch tweetSearch) {
            Glide.with(itemView.getContext())
                    .load(tweetSearch.getUserPicture())
                    .centerCrop()
                    .into(imageView);

            textViewName.setText(tweetSearch.getUserName());
            textViewFollowers.setText(String.format("%d", tweetSearch.getUserFollowers()));
            textViewText.setText(tweetSearch.getText());
        }
    }
}
