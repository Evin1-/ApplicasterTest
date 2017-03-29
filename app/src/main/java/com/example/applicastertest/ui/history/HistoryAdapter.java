package com.example.applicastertest.ui.history;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.applicastertest.R;
import com.example.applicastertest.data.entities.SearchTerm;

import java.util.List;

/**
 * Created by user on 3/29/17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<SearchTerm> searchTerms;

    public HistoryAdapter(List<SearchTerm> searchTerms) {
        this.searchTerms = searchTerms;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        holder.bind(searchTerms.get(position));
    }

    @Override
    public int getItemCount() {
        return searchTerms != null ? searchTerms.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.r_history_text);
        }

        public void bind(SearchTerm searchTerm) {
            textView.setText(searchTerm.getTerm());
        }
    }
}
