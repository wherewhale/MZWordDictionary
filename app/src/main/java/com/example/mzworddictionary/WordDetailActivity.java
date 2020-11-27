package com.example.mzworddictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WordDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView detail;
    private TextView origin;
    private TextView example;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);

        title = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        origin = findViewById(R.id.origin);
        example = findViewById(R.id.example);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("WORD")); //title 의 글자에 WORD 라는 이름의 데이터를 삽입한다.
        detail.setText(intent.getStringExtra("DETAIL")); //detail 에 DETAIL 이라는 이름으로 들어온 데이터 삽입.
        origin.setText(intent.getStringExtra("ORIGIN")); //origin 에 ORIGIN 이라는 이름으로 들어온 데이터 삽입.
        example.setText(intent.getStringExtra("EXAMPLE")); //example 에 EXAMPLE 이라는 이름으로 들어온 데이터 삽입.
        //TODO: 인텐트로 들어온 값을 text 값으로 설정
//        switch (intent.getStringExtra("DETAIL_TEXT")) {
//            case "꾸안꾸": {
//                detail.setText("'꾸민 듯 안 꾸민 듯'의 줄임말. 2019년 후반부터 인터넷 유행어로 쓰이기 시작했다. 일상 패션, 캐주얼 룩과 거의 동의어라고 보면 된다. 화려한 패션, 포멀 룩과는 대척점에 있는 심플하고 단순한 스타일이 주를 이룬다. 미니멀 라이프의 일종이라고 볼 수 있다. 반대말로는 '꾸꾸(꾸민 듯 꾸민)'가 있다.");
//                origin.setText("실은 패션계의 단골 표현인 무심한 듯 시크하다는 말과 별 다를 바 없으나, SNS의 성행으로 더욱 퍼져나갔다는 주장이다. 비슷한 사례로 소확행이 있다.");
//                example.setText("너 이번에 소개팅 나간다며?! 꾸안꾸 알지?");
//                break;
//            }
//            case "알잘딱깔센": {
//                detail.setText("알아서 잘 딱 깔끔하고 센스있게의 준말.");
//                origin.setText("사실은 우왁굳 방송의 밈 '알잘딱깔센(알아서 잘 딱 깔끔하고 센스있게)'에서 유래된 가상의 인물이다. 원래는 우왁굳과 팬들끼리 알잘딱깔센을 마치 실제로 존재하는 인명처럼 알자르 타카르센으로 변형시켜서 농담 삼아서 쓰던 일종의 밈이었다.");
//                example.setText("김대리! 이번 일도 알잘딱깔센 알지?");
//                break;
//            }
//            case "슬세권": {
//                detail.setText("슬세권이란 슬리퍼 차림과 같은 편한 복장으로 카페나 편의점, 도서관, 쇼핑몰 같은 편의시설을 사용할 수 있는 주거 권역을 뜻하는 신조어이다. ");
//                origin.setText("2020년도 중반 sns 에서 유행한 신조어로 1~2인 가구가 증가하면서 생긴 신조어로 자신의 생활반경에 얼마나 많은 여가시설이 있는지를 나타내는 지표이기도 하다.");
//                example.setText("와 너 진짜 슬리퍼 신고 지하철타러 왔어!? 너네 집 슬세권이구나?");
//                break;
//            }
//            case "졌잘싸": {
//                detail.setText("결과적으로 패배했지만, 과정만 보면 명경기를 보여주거나, 아니면 어느 정도 실력은 보장되어 있었으나, 초반부터 우승 후보를 만나는 악운에 걸려 실력을 제대로 보여주지 못하고 떨어진 사람이나 단체 등에게 위로용으로 주로 쓰이는 말.");
//                origin.setText("2006 독일 월드컵 스위스전 패배 이후 공중파 방송에서 처음 사용했던 것으로 추정되며, 원래는 세계적으로 상술한 대로의 의미가 주로 쓰이나, 인터넷 등지에서는 반대 의미로 비꼬는 용도로 쓰이고 있다.");
//                example.setText("이번 국가대표 리얼 졌잘싸. 인정?");
//                break;
//            }
//            case "레게노": {
//                detail.setText("레전드를 뜻하는 인터넷 신조어. 우왁굳 방송내에서는 감탄사 한정으로 레게노 말고도 ㄹㄱㄴ라고 쓰인다.");
//                origin.setText("트위치 스트리머 우왁굳의 마인크래프트 왁트모르즈비 방송 중 피곤했던 우왁굳이 아내 김수현에게 방송을 잠시 넘겼는데 건축물에 쓰여진 '레전드(LEGEND)'를 '레제노(LEGENO)'로 잘못 읽었던 것에서 유래되었다. 정확히는 레전드를 재플리시처럼 레잔도로 써대는 우왁굳 때문에 버릇이 되어서 레전드에 O가 포함되는줄 혼동한거에서 비롯되었다. 이후 레전드의 이음동의어로써 우왁굳 스트리밍에서만 사용되었다가, 특유의 어감 덕에 여러 트위치 스트리머 방에서 사용되기 시작하면서 \"레전드\" → \"레게노\"와 같은 이음동의어가 인터넷에 퍼지게 되었다.");
//                example.setText("이번에 학점 2.0 못 넘었다고?! 너 인생 진짜 레게노다...");
//            }
//
//        }
    }
}