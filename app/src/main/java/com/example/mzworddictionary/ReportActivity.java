package com.example.mzworddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity {
    EditText editText;
    Button btn_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        editText = (EditText) findViewById(R.id.report);
        btn_report = (Button) findViewById(R.id.btn_report);

        btn_report.setOnClickListener(new View.OnClickListener() { //button_ok 에 onClickListener 부착
            public void onClick(View v) { //클릭 이벤트 생성
                Toast.makeText(getApplicationContext(), editText.getText().toString()+"의 분석요청이 완료되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }
}