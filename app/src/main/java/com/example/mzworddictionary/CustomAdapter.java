package com.example.mzworddictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements Filterable {

    private ArrayList<Word> arrayList;
    private ArrayList<Word> filteredArrayList;
    private Context context;


    public CustomAdapter(ArrayList<Word> arrayList, Context context) {
        this.arrayList = arrayList;
        this.filteredArrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.wordText.setText(arrayList.get(position).getWord());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, WordDetailActivity.class);
                intent.putExtra("WORD", arrayList.get(position).getWord());
                intent.putExtra("DETAIL", arrayList.get(position).getDetail());
                intent.putExtra("ORIGIN", arrayList.get(position).getOrigin());
                intent.putExtra("EXAMPLE", arrayList.get(position).getEx());
                intent.putExtra("LIKES", arrayList.get(position).getLikes());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0); //리스트 사이즈가 비었는지 확인하기 위한 삼항연산자
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    arrayList = filteredArrayList;
                } else {
                    ArrayList<Word> filteringList = new ArrayList<>();
                    for (Word name : filteredArrayList) {
                        if (name.getWord().toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(name);
                        }
                    }
                    arrayList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayList = (ArrayList<Word>) results.values;
                notifyDataSetChanged();
            }

        };
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView wordText;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wordText = itemView.findViewById(R.id.wordText);
        }
    }


}
