package com.example.mzworddictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class WordListActivity extends AppCompatActivity implements TextWatcher {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Word> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    EditText editsearch;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);


        recyclerView = findViewById(R.id.recyclerView); //리사이클러뷰 ID 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //Word 객체를 담을 ArrayList (어뎁터 쪽으로)
        editsearch =(EditText)findViewById(R.id.et_search);
        editsearch.addTextChangedListener(this);

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("id_list");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); //기존 배열리스트가 존재하지 않도록 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){ //반복문으로 데이터 list를 추출함
                    Word word = snapshot.getValue(Word.class); //만들어둔 Word 객체에 데이터를 담음.
                    arrayList.add(word); //담은 데이터를 배열 리스트에 넣고 리사이클러 뷰로 보낼 준비
                    Log.d("database", "word 값 :" + word.getWord());
                    Log.d("database", "detail 값 :" + word.getDetail());
                    Log.d("database", "origin 값 :" + word.getOrigin());
                    Log.d("database", "example 값 :" + word.getEx());
                    Log.d("database", "likes " + word.getLikes());
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //데이터베이스 데이터 가져오던 중 에러 발생 경우 처리
                Log.e("WordListActivity", String.valueOf(error.toException())); //에러문 출력
            }
        });


        adapter = new CustomAdapter(arrayList, this); //Adapter 와 연결
        recyclerView.setAdapter(adapter); //리사이클러 뷰에 어댑터 연결

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        ((CustomAdapter) adapter).getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}