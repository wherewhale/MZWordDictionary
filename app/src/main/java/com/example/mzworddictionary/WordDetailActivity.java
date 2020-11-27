package com.example.mzworddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class WordDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView detail;
    private TextView origin;
    private TextView example;
    private TextView likes;
    private LottieAnimationView imgIconLike;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        title = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        origin = findViewById(R.id.origin);
        example = findViewById(R.id.example);
        likes = findViewById(R.id.likes);
        imgIconLike = findViewById(R.id.lottie_animation);

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("id_list"); //id_list 라는 주소 확인



        final Intent intent = getIntent();
        title.setText(intent.getStringExtra("WORD")); //title 의 글자에 WORD 라는 이름의 데이터를 삽입한다.
        detail.setText(intent.getStringExtra("DETAIL")); //detail 에 DETAIL 이라는 이름으로 들어온 데이터 삽입.
        origin.setText(intent.getStringExtra("ORIGIN")); //origin 에 ORIGIN 이라는 이름으로 들어온 데이터 삽입.
        example.setText(intent.getStringExtra("EXAMPLE")); //example 에 EXAMPLE 이라는 이름으로 들어온 데이터 삽입.
        likes.setText("" + intent.getIntExtra("LIKES", 0)); //likes 에 LIKES 라는 이름의 숫자 데이터 삽입




        imgIconLike.setOnClickListener(new View.OnClickListener() {
            boolean isAnimated = false; //애니메이션 여부를 확인하기 위한 boolean 변수 선언

            @Override
            public void onClick(View v) {
                if (!isAnimated) {
                    int afterLike = intent.getIntExtra("LIKES", 0) + 1; //좋아요를 누른 경우, 값을 1 늘림
                    databaseReference.child(intent.getStringExtra("WORD")).child("likes").setValue(afterLike); //데이터베이스 좋아요 숫자 변경
                    imgIconLike.playAnimation(); //로티 애니메이션 실행
                    imgIconLike.setSpeed(3f); //속도 설정
                    isAnimated = true; //애니메이션 된 것을 알리기 위해 true 로 설정
                    likes.setText("" + afterLike); //afterLike 정수값을 넣어 사용자에게 알림
                } else {
                    imgIconLike.setSpeed(-2F); //로티 애니메이션 속도를 마이너스로 설정하여 역실행
                    isAnimated=false; //false 로 재변경
                    imgIconLike.playAnimation(); //로티 애니메이션 실행
                    likes.setText("" + intent.getIntExtra("LIKES", 0)); //값을 다시 기존으로 변경
                    databaseReference.child(intent.getStringExtra("WORD")).child("likes").setValue(intent.getIntExtra("LIKES", 0));
                    //데이터베이스의 좋아요 숫자를 기존으로 변경
                }
            }
        });
    }
}
