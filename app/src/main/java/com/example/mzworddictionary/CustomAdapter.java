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

    private ArrayList<Word> arrayList; //필터링되지 않은 arrayList  생성
    private ArrayList<Word> filteredArrayList; //필터링 이후 보여지는 filteredArrayList 생성
    private Context context;


    public CustomAdapter(ArrayList<Word> arrayList, Context context) {
        this.arrayList = arrayList;
        this.filteredArrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //viewHolder 에 담을 view 선언 및 담기
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) { //position 을 통해 리스트의 정보 공유
        holder.wordText.setText(arrayList.get(position).getWord()); //holder 의 아이템 요소에 값 할당

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //listView 에 클릭 리스너 부착
                Context context = view.getContext();
                Intent intent = new Intent(context, WordDetailActivity.class); //액티비티 전환을 위한 인텐트 생성
                intent.putExtra("WORD", arrayList.get(position).getWord());
                intent.putExtra("DETAIL", arrayList.get(position).getDetail());
                intent.putExtra("ORIGIN", arrayList.get(position).getOrigin());
                intent.putExtra("EXAMPLE", arrayList.get(position).getEx());
                intent.putExtra("LIKES", arrayList.get(position).getLikes()); //인텐트에 각 이름을 가진 객체 데이터 첨부
                context.startActivity(intent); //액티비티 전환
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0); //리스트 사이즈가 비었는지 확인하기 위한 삼항연산자
    }

    @Override
    public Filter getFilter() {
        return new Filter() { //Filter 모듈을 사용하여 리스트를 확인하고 검색어 결과를 정리하여 사용자에게 보여줌
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) { //만약 넘겨받은 값(검색어)이 비어있는 경우, 현재 값 유지
                    arrayList = filteredArrayList;
                } else { //아니라면, 넘겨받은 값과 관련된 모든 리스트 출력
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
                return filterResults; //결과 공유
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayList = (ArrayList<Word>) results.values;
                notifyDataSetChanged();
            }

        };
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder { //리싸이클러 뷰에 존재하는 데이터 값 설정
        TextView wordText; //텍스트뷰 선언

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.wordText = itemView.findViewById(R.id.wordText); //레이아웃의 요소와 연동
        }
    }


}
