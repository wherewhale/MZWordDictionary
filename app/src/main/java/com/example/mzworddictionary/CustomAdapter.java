package com.example.mzworddictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Word> arrayList;
    private Context context;


    public CustomAdapter(ArrayList<Word> arrayList, Context context) {
        this.arrayList = arrayList;
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
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.wordText.setText(arrayList.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0); //리스트 사이즈가 비었는지 확인하기 위한 삼항연산자
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView wordText;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wordText = itemView.findViewById(R.id.wordText);
        }
    }
}
