package com.example.mzworddictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    EditText edit; //EditText 형태의 edit 선언
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle barDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView=findViewById(R.id.nav);
        drawerLayout=findViewById(R.id.layout_drawer);
        //item icon색조를 적용하지 않도록.. 설정 안하면 회색 색조
        navigationView.setItemIconTintList(null);
        edit = (EditText) findViewById(R.id.edit); //edit 은 activity_sub 에 존재하는 edit 으로 할당

        Button button = (Button) findViewById((R.id.search_button)); //button 은 activity_main 의 search_button 임을 알림
        button.setOnClickListener(new View.OnClickListener() { //button_ok 에 onClickListener 부착
            public void onClick(View v) { //클릭 이벤트 생성
                Intent intent = new Intent(MainActivity.this, WordListActivity.class); //인텐트 생성
                intent.putExtra("INPUT_TEXT", edit.getText().toString()); //인텐트에 INPUT_TEXT 라는 이름을 가진 edit 의 글자 데이터 첨부
                startActivity(intent); //Activity 실행 및 intent 와 결과코드 부착
            }
        });


        //네비게이션뷰의 아이템을 클릭했을때
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_word:
                        Intent dic = new Intent(MainActivity.this, WordListActivity.class); //intent 생성 및 목적지 선언 (WordListActivity)
                        startActivity(dic); //인텐트와 결과코드 공유
                        break;
                    case R.id.menu_report:
                        Intent report = new Intent(MainActivity.this, ReportActivity.class); //intent 생성 및 목적지 선언 (ReportActivity)
                        startActivity(report); //인텐트 공유
                        break;
                    case R.id.menu_make:
                        Intent make = new Intent(MainActivity.this, WordMakeActivity.class); //intent 생성 및 목적지 선언 (WordMakeActivity)
                        startActivity(make); //인텐트 공유
                        break;
                    //메뉴 목록 추가가능
                }

                //Drawer를 닫기...
                drawerLayout.closeDrawer(navigationView);

                return false;
            }
        });

        //Drawer 조절용 토글 버튼 객체 생성
        barDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,R.string.app_name);
        //ActionBar(제목줄)의 홈or업버튼의 위치에 토글아이콘이 보이게끔...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //삼선아이콘 모양으로 보이도록
        //토글버튼의 동기를 맞추기
        barDrawerToggle.syncState();

        //삼선 아이콘과 화살표아이콘이 자동으로 변환하도록
        drawerLayout.addDrawerListener(barDrawerToggle);

    }//onCreate method..

    //액션바의 메뉴를 클릭하는 이벤트를 듣는
    //메소드를 통해서 클릭 상황을 전달하도록..
    //토글 버튼이 클릭 상황을 인지하도록..
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}