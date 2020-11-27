package com.example.mzworddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.w3c.dom.Text;

public class WordDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView detail;
    private TextView origin;
    private TextView example;
    private TextView likes;
    private LottieAnimationView imgIconLike;


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

        final Intent intent = getIntent();
        title.setText(intent.getStringExtra("WORD")); //title 의 글자에 WORD 라는 이름의 데이터를 삽입한다.
        detail.setText(intent.getStringExtra("DETAIL")); //detail 에 DETAIL 이라는 이름으로 들어온 데이터 삽입.
        origin.setText(intent.getStringExtra("ORIGIN")); //origin 에 ORIGIN 이라는 이름으로 들어온 데이터 삽입.
        example.setText(intent.getStringExtra("EXAMPLE")); //example 에 EXAMPLE 이라는 이름으로 들어온 데이터 삽입.
        likes.setText("" + intent.getIntExtra("LIKES", 0)); //likes 에 LIKES 라는 이름의 숫자 데이터 삽입


        imgIconLike.setOnClickListener(new View.OnClickListener() {
            boolean isAnimated = false;

            @Override
            public void onClick(View v) {
                if (!isAnimated) {
                    int afterLike = intent.getIntExtra("LIKES", 0) + 1;
                    imgIconLike.playAnimation();
                    imgIconLike.setSpeed(3f);
                    isAnimated = true;
                    likes.setText("" + afterLike);
                } else {
                    imgIconLike.setSpeed(-2F);
                    isAnimated=false;
                    imgIconLike.playAnimation();
                    likes.setText("" + intent.getIntExtra("LIKES", 0));
                }
            }
        });
    }
}
