package com.ksm.recyclerviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ListAdapter adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        for (int i =0; i < 200; i++) {
            adapter.addTitle("Added: " + Integer.toString(i));
        }
    }

    private class ListAdapter extends RecyclerView.Adapter<NameViewListHolder> {
        private final ArrayList<String> titles;

        private ListAdapter() {
            titles = new ArrayList<>();
        }

        public void addTitle(String title) {
            titles.add(title);
            notifyItemInserted(titles.size() - 1);

        }

        public void removeTitle(String title) {
            int position = titles.indexOf(title);
            titles.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public int getItemCount() {
            return titles.size();
        }

        @Override
        public NameViewListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.list_item_container, parent, false);
            return new NameViewListHolder(view);
        }

        @Override
        public void onBindViewHolder(NameViewListHolder holder, int position) {
            String title = titles.get(position);
            holder.tv.setText(title);
        }

    }

    /**
     * ViewHolder is object that is responsible for holding references
     * for the individuals items that appear in RecyclerView
     */
    private class NameViewListHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public NameViewListHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.list_item_title);
        }
    }
}
