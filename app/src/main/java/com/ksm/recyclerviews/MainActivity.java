package com.ksm.recyclerviews;

import android.graphics.Color;
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

        // Vælge hvilke slags layout recyclerView skal have
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Adapter
        CustomDataAdapter adapter = new CustomDataAdapter();
        recyclerView.setAdapter(adapter);

        // Generer noget data
        for (int i =0; i < 200; i++) {
            adapter.addTitle("Title: " + Integer.toString(i));
        }
    }

    private class CustomDataAdapter extends RecyclerView.Adapter<DataViewListHolder> {
        // Variabler der skal indgå i RecyclerView
        private final ArrayList<String> titles;

        private CustomDataAdapter() {
            titles = new ArrayList<>();
        }

        public void addTitle(String title) {
            titles.add(title);
            // Sidste element af Array
            notifyItemInserted(titles.size() - 1);

        }

        public void removeTitle(String title) {
            int position = titles.indexOf(title);
            if (position == -1) {
                return;
            }

            titles.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, titles.size() );
        }

        @Override
        public int getItemCount() {
            // Antal items
            return titles.size();
        }

        @Override
        public DataViewListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.list_item_container, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Remove item onClick
                    String title = (String) view.getTag();
                    removeTitle(title);
                }
            });

            return new DataViewListHolder(view);
        }

        @Override
        public void onBindViewHolder(DataViewListHolder holder, int position) {
            String title = titles.get(position);
            holder.tv.setText(title);

            // Tag bruges ovenover til onClick
            holder.itemView.setTag(title);

            if (position % 2 == 0) {
                holder.tv.setBackgroundColor(Color.parseColor("#22000000"));
            }
            else {
                holder.tv.setBackground(null);
            }
        }

    }

    /**
     * ViewHolder er et object som er ansvarlig for indeholder referencer
     * til de enkelte items som vises i RecyclerView
     */
    private class DataViewListHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public DataViewListHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.list_item_title);
        }
    }
}
