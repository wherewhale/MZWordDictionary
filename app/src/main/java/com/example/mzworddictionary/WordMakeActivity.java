package com.example.mzworddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WordMakeActivity extends AppCompatActivity {
    private EditText edit_word;
    private EditText edit_detail;
    private EditText edit_origin;
    private EditText edit_example;
    private Button btn_send;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_make);

        edit_word = findViewById(R.id.edit_word);
        edit_detail = findViewById(R.id.edit_detail);
        edit_origin = findViewById(R.id.edit_origin);
        edit_example = findViewById(R.id.edit_example);
        btn_send = findViewById(R.id.btn_send);

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("id_list");

        btn_send.setOnClickListener(new View.OnClickListener() { //button_send 에 onClickListener 부착
            public void onClick(View v) { //클릭 이벤트 생성
                if(edit_word.getText().toString() == null || edit_detail.getText().toString() == null || edit_origin.getText().toString() == null || edit_example.getText().toString() == null){
                    Toast.makeText(getApplicationContext(), "용어 설명 중 빈 부분이 존재합니다!", Toast.LENGTH_LONG).show();
                } //용어 설명 중 빈 부분이 있다면 확인하여 서버로 보내지 않도록 예외설정
                else{
                    writeNewWord(edit_word.getText().toString(), edit_detail.getText().toString(), edit_origin.getText().toString(), edit_example.getText().toString());
                    Toast.makeText(getApplicationContext(), "용어 등록이 완료되었습니다!", Toast.LENGTH_LONG).show();
                    onBackPressed(); //이전 화면으로 돌아가기
                }

            }

            private void writeNewWord(String word, String detail, String origin, String example) {
                Word wordObject = new Word(word, detail, origin, example, 0);
                databaseReference.child(word).setValue(wordObject);
                //새로운 언어를 만들어 서버에 데이터 저장
            }
        });




    }
}