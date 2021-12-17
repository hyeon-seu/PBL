package com.random.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RandomFood extends AppCompatActivity {
    // 기본 view단 변수 생성
    TextView randomTxt;
    TextView foodName;
    TextView foodList;
    TextView title;

    ImageView foodImg;

    Button randomBtn;
    Button listBtn;
    Button backBtn;
    Button urlBtn;
    Button urlBtn2;

    ScrollView foodListScroll;

    // 이하 데이터 처리용 변수
    String[] data;

    int[] dataNumbers;

    String category;
    String changeURL;

    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radom_food);

        title = (TextView)findViewById(R.id.title);
        randomTxt = (TextView)findViewById(R.id.randomTxt);
        foodName = (TextView)findViewById(R.id.foodName);
        foodImg = (ImageView)findViewById(R.id.foodImg);
        foodListScroll = (ScrollView)findViewById(R.id.foodListScroll);
        foodList = (TextView)findViewById(R.id.foodList);
        randomBtn = (Button)findViewById(R.id.randomBtn);
        listBtn = (Button)findViewById(R.id.listBtn);
        backBtn = (Button)findViewById(R.id.backBtn);
        urlBtn = (Button)findViewById(R.id.urlBtn);
        urlBtn2 = (Button)findViewById(R.id.urlBtn2);

        Intent intent = getIntent();
        category = intent.getExtras().getString("category");

        switch(category) {
            case "KoreanFood" :
                data = FoodData.getKoreanFood();
                break;
            case "ChineseFood" :
                data = FoodData.getChineseFood();
                break;
            case "JapaneseFood" :
                data = FoodData.getJapaneseFood();
                break;
            case "food" :
                data = FoodData.getFood();
                break;
            case "AllFood" :
                data = FoodData.getAllFood();
                break;
        }

        if(dataNumbers == null)
            dataNumbers = dataRandom(data);
    }

    //후기보기&위치 버튼 클릭시
    public void urlBtn(View view){
        Intent myIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse(changeURL));
        startActivity(myIntent);
    }

    //리스트 검색창 버튼 클릭시
    public void urlBtn2(View view){
        //아래 Uri.parse 뒤 주소 변경하면 됩니다.
        Intent myIntent =  new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naver.com"));
        startActivity(myIntent);
    }


    // 랜덤 버튼을 클릭 했을 때
    public void randomBtn(View view) {
        dataExposure();// 절대 지우면 안됨
    }

    // 리스트 버튼을 클릭 했을 때
    public void listBtn(View view) {
        listExposure();
    }

    //리스트 뒤로가기 버튼을 클릭 했을 때
    public void backBtn(View view){
        title.setVisibility(View.VISIBLE);

        title.setText("아래를 눌러주세요");

        randomTxt.setVisibility(View.VISIBLE);
        foodImg.setVisibility(View.VISIBLE);
        foodListScroll.setVisibility(View.GONE);
        foodList.setVisibility(View.GONE);
        listBtn.setVisibility(View.VISIBLE);
        randomBtn.setVisibility(View.VISIBLE);
        backBtn.setVisibility(View.GONE);
        urlBtn.setVisibility(View.GONE);
        urlBtn2.setVisibility(View.GONE);

        ImageView imageView = findViewById(R.id.foodImg);
        imageView.setImageResource(R.drawable.logo);
    }

    /**
     * 중복 제거를 하고 랜덤으로 리스트 뽑기
     * @param data
     * @return
     */
    private int[] dataRandom(String[] data) {
        System.out.println("데이터 출력 부분이다.");
        for(int i = 0; i < data.length; i++) {
            System.out.println("데이터    :  " + data[i]);
        }

        int[] numbers = new int[data.length];

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random() * data.length);

            for(int j = 0; j < i; j++) {
                if(numbers[i] == numbers[j]) {
                    i--;
                    break;
                }
            }
        }
        return numbers;
    }

    //랜덤 리스트 행동 함수
    private void listExposure(){
        title.setVisibility(View.GONE);
        randomTxt.setVisibility(View.GONE);
        foodName.setVisibility(View.GONE);
        foodImg.setVisibility(View.GONE);
        foodList.setVisibility(View.VISIBLE);
        foodListScroll.setVisibility(View.VISIBLE);
        listBtn.setVisibility(View.GONE);
        randomBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
        urlBtn.setVisibility(View.GONE);
        urlBtn2.setVisibility(View.VISIBLE);


        for(int i=0; i<dataNumbers.length; i++)
            foodList.append(data[dataNumbers[i]] + "\n");
    }

    // click 버튼을 누를 시 데이터 랜덤으로 출력
    private void dataExposure() {
        title.setText("아래를 눌러주세요");

        if(dataNumbers.length > counter) {
            randomTxt.setVisibility(View.GONE);
            foodName.setVisibility(View.VISIBLE);
            urlBtn.setVisibility(View.VISIBLE);

            foodName.setText(data[dataNumbers[counter]]);

            // ------------- 음식 데이터 추가 방법 ---------------
            // 선작업 : 이미지를 res -> drawable에 넣는다.
            // 1. 이미지를 아이디로 검색해서 하나의 변수에 담아둔다.
            // 2. 가져온 랜덤 값이 if문 또는 switch문의 조건에 일치할 시 해당 이미지 변수에 리소스를 갈아치운다.
            // 3. changeURL 변수에 해당 음식 url을 string형으로 넣는다.

            ImageView imageView = findViewById(R.id.foodImg);

            switch (data[dataNumbers[counter]]) {
                //  --------------------------------------   한식 시작  --------------------------------------

                case "본죽" :
                {
                    imageView.setImageResource(R.drawable.k_bonjuk_1);
                    changeURL = "https://m.place.naver.com/restaurant/441768346/home?entry=pll";
                    break;
                }
                case "한솥도시락" :
                {
                    imageView.setImageResource(R.drawable.k_hansot_2);
                    changeURL = "https://m.search.naver.com/search.naver?sm=mtb_hty.top&where=m&oquery=%ED%95%9C%EC%86%A5+%EC%A7%84%EC%A3%BC%EA%B0%80%EC%A2%8C%EC%A0%90&tqi=hkNXPdp0JW0ssvTNvclssssssXG-135163&query=%EA%B2%BD%EC%83%81%EB%8C%80+%ED%95%9C%EC%86%A5";
                    break;
                }
                case "울엄마 부대찌개" :
                {
                    imageView.setImageResource(R.drawable.k_mymom_3);
                    changeURL = "https://m.search.naver.com/search.naver?sm=mtb_hty.top&where=m&oquery=%EA%B2%BD%EC%83%81%EB%8C%80+%ED%95%9C%EC%86%A5&tqi=hkNXlsp0JxCssc%2FklsosssssswR-191503&query=%EA%B2%BD%EC%83%81%EB%8C%80+%EC%9A%B8%EC%97%84%EB%A7%88+%EB%B6%80%EB%8C%80%EC%B0%8C%EA%B0%9C";
                    break;
                }
                case "신전떡볶이" :
                {
                    imageView.setImageResource(R.drawable.k_sinjeon_4);
                    changeURL = "https://m.place.naver.com/restaurant/37917945/home?entry=pll";
                    break;
                }
                case "바우네나주곰탕" :
                {
                    imageView.setImageResource(R.drawable.k_baune_5);
                    changeURL = "https://m.place.naver.com/restaurant/1889364434/home?entry=pll";
                    break;
                }
                case "통묵은지감자탕" :
                {
                    imageView.setImageResource(R.drawable.k_gamjatang_6);
                    changeURL = "https://m.place.naver.com/restaurant/32114688/home";
                    break;
                }
                case "부산돼지국밥" :
                {
                    imageView.setImageResource(R.drawable.k_busangugbab_7);
                    changeURL = "https://m.place.naver.com/restaurant/1737361404/home?entry=pll";
                    break;
                }
                case "밥장인 돼지찌개" :
                {
                    imageView.setImageResource(R.drawable.k_babjangin_8);
                    changeURL = "https://m.place.naver.com/restaurant/1434080299/home?entry=plt";
                    break;
                }
                case "밀리오레" :
                {
                    imageView.setImageResource(R.drawable.k_milliole_9);
                    changeURL = "https://m.place.naver.com/restaurant/21657355/home";
                    break;
                }
                case "통큰돼지국밥" :
                {
                    imageView.setImageResource(R.drawable.k_tonggugbab_10);
                    changeURL = "https://m.place.naver.com/restaurant/38276492/home";
                    break;
                }
                case "오봉도시락" :
                {
                    imageView.setImageResource(R.drawable.k_obong_11);
                    changeURL = "https://m.place.naver.com/restaurant/36859630/home";
                    break;
                }

                case "진미" :
                {
                    imageView.setImageResource(R.drawable.k_jinmi_14);
                    changeURL = "https://m.place.naver.com/restaurant/38595661/home?entry=plt";
                    break;
                }
                case "경아" :
                {
                    imageView.setImageResource(R.drawable.k_gyeonga_15);
                    changeURL = "https://m.place.naver.com/restaurant/13398435/home?entry=plt";
                    break;
                }
                case "땡초김밥" :
                {
                    imageView.setImageResource(R.drawable.k_ttaengcho_16);
                    changeURL = "https://m.place.naver.com/restaurant/32110852/home";
                    break;
                }
                case "오늘김해뒷고기" :
                {
                    imageView.setImageResource(R.drawable.k_gimhaegogi_17);
                    changeURL = "https://m.place.naver.com/restaurant/1941731894/home?entry=pll";
                    break;
                }
                case "동궁찜닭" :
                {
                    imageView.setImageResource(R.drawable.k_donggung_18);
                    changeURL = "https://m.place.naver.com/restaurant/854263171/home?entry=pll";
                    break;
                }
                case "국수나무" :
                {
                    imageView.setImageResource(R.drawable.k_gugsu_19);
                    changeURL = "https://m.place.naver.com/restaurant/20810208/home?entry=pll";
                    break;
                }
                case "남매컵밥" :
                {
                    imageView.setImageResource(R.drawable.k_nammae_20);
                    changeURL = "https://m.place.naver.com/restaurant/35258196/home";
                    break;
                }
                case "돼지랑 순대랑" :
                {
                    imageView.setImageResource(R.drawable.k_pig_21);
                    changeURL = "https://m.place.naver.com/restaurant/11884253/home";
                    break;
                }
                //  --------------------------------------   한식 끝  --------------------------------------
                //  --------------------------------------   중식 시작  --------------------------------------
                case "북경양꼬치" :
                {
                    imageView.setImageResource(R.drawable.c_bukgyeongyangkkochichina_1);
                    changeURL = "https://m.place.naver.com/restaurant/1127102372/home?entry=plt";
                    break;
                }
                case "짬뽕공장" :
                {
                    imageView.setImageResource(R.drawable.c_jjampponggongjangchina_2);
                    changeURL = "https://m.place.naver.com/restaurant/35031995/home?entry=plt";
                    break;
                }
                case "홍콩반점" :
                {
                    imageView.setImageResource(R.drawable.c_hongkongbanjeomchina_3);
                    changeURL = "https://m.place.naver.com/restaurant/1181753509/home?entry=pll";
                    break;
                }
                case "수해복마라탕" :
                {
                    imageView.setImageResource(R.drawable.c_suhaebokmaratangchina_4);
                    changeURL = "https://m.place.naver.com/restaurant/1789521327/home?entry=pll";
                    break;
                }
                case "천안문" :
                {
                    imageView.setImageResource(R.drawable.c_cheonanmunchina_5);
                    changeURL = "https://m.place.naver.com/restaurant/19449562/home?entry=pll";
                    break;
                }
                case "미향루" :
                {
                    imageView.setImageResource(R.drawable.c_mihyangruchina_6);
                    changeURL = "https://m.place.naver.com/restaurant/38552494/home";
                    break;
                }
                case "이비가짬뽕" :
                {
                    imageView.setImageResource(R.drawable.c_ibigachina_7);
                    changeURL = "https://m.place.naver.com/restaurant/36260830/home?entry=pll";
                    break;
                }
                case "라화쿵부" :
                {
                    imageView.setImageResource(R.drawable.c_rahwakungbuchina_8);
                    changeURL = "https://m.place.naver.com/restaurant/1072217722/home?entry=pll";
                    break;
                }
                case "라맛쿵부" :
                {
                    imageView.setImageResource(R.drawable.c_ramaskungbuchina_9);
                    changeURL = "https://m.place.naver.com/restaurant/1252740780/home?entry=pll";
                    break;
                }
                case "만성각" :
                {
                    imageView.setImageResource(R.drawable.c_manseonggakchina_10);
                    changeURL = "https://m.place.naver.com/restaurant/15524783/home?entry=plt";
                    break;
                }
                case "소림마라" :
                {
                    imageView.setImageResource(R.drawable.c_sorimmarachina_11);
                    changeURL = "https://m.place.naver.com/restaurant/1953922825/home?entry=pll";
                    break;
                }
                case "서울손짜장" :
                {
                    imageView.setImageResource(R.drawable.c_seoulsonjjajangchina_12);
                    changeURL = "https://m.place.naver.com/restaurant/37990855/home?entry=pll";
                    break;
                }
                case "삼양콩짜장" :
                {
                    imageView.setImageResource(R.drawable.c_samyangkongjjajangchina_13);
                    changeURL = "https://m.place.naver.com/restaurant/33121297/home";
                    break;
                }
                case "탕슈탕슈" :
                {
                    imageView.setImageResource(R.drawable.c_tangsyutangsyuchina_14);
                    changeURL = "https://m.place.naver.com/restaurant/1040484772/home?entry=pll";
                    break;
                }
                case "이서방짬뽕" :
                {
                    imageView.setImageResource(R.drawable.c_iseobangjjamppongchina_15);
                    changeURL = "https://m.place.naver.com/restaurant/37683638/home?entry=plt";
                    break;
                }
                case "동향" :
                {
                    imageView.setImageResource(R.drawable.c_donghyangchina_16);
                    changeURL = "https://m.place.naver.com/restaurant/1581730250/home?entry=pll";
                    break;
                }
                case "갈비짬뽕엔탕수육" :
                {
                    imageView.setImageResource(R.drawable.c_galbijjamppongandtangsuyukchina_17);
                    changeURL = "https://m.place.naver.com/restaurant/1731820844/home?entry=pll";
                    break;
                }
                case "고량진미" :
                {
                    imageView.setImageResource(R.drawable.c_goryangjinmichina_19);
                    changeURL = "https://m.place.naver.com/restaurant/1696754463/home?entry=pll";
                    break;
                }
                case "착한쭝식" :
                {
                    imageView.setImageResource(R.drawable.c_chakhanjjungsikchina_20);
                    changeURL = "https://m.place.naver.com/restaurant/1390159939/home?entry=pll";
                    break;
                }
                case "역전반점" :
                {
                    imageView.setImageResource(R.drawable.c_yeokjeonbanjeomchina_21);
                    changeURL = "https://m.place.naver.com/restaurant/1629548804/home?entry=pll";
                    break;
                }
                case "9찌짬뽕" :
                {
                    imageView.setImageResource(R.drawable.c_guccijjamppongchina_22);
                    changeURL = "https://m.place.naver.com/restaurant/1866467787/home?entry=plt";
                    break;
                }
                //  --------------------------------------   중식 끝  --------------------------------------
                //  --------------------------------------   양식 시작 -------------------------------------
                case "델브리또" :
                {
                    imageView.setImageResource(R.drawable.w_delburiddoewestern_8);
                    changeURL = "https://m.place.naver.com/restaurant/36451807/home?entry=pll";
                    break;
                }
                case "에그드랍" :
                {
                    imageView.setImageResource(R.drawable.w_eggdropwestern_11);
                    changeURL = "https://m.place.naver.com/restaurant/1374450446/home?entry=pll";
                    break;
                }
                case "도스마스" :
                {
                    imageView.setImageResource(R.drawable.w_dosmaswestern_10);
                    changeURL = "https://m.place.naver.com/restaurant/37905009/home?entry=pll";
                    break;
                }
                case "버거킹" :
                {
                    imageView.setImageResource(R.drawable.w_burgerkingwestern_6);
                    changeURL = "https://m.place.naver.com/restaurant/1234925806/home?entry=pll";
                    break;
                }
                case "필로돈테리아" :
                {
                    imageView.setImageResource(R.drawable.w_philadonteriawestern_25);
                    changeURL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=필로돈테리아";
                    break;
                }
                case "롯데리아" :
                {
                    imageView.setImageResource(R.drawable.w_lotteriawestern_17);
                    changeURL = "https://m.place.naver.com/restaurant/11824674/home?entry=pll";
                    break;
                }
                case "피자헛" :
                {
                    imageView.setImageResource(R.drawable.w_pizzahutwestern_27);
                    changeURL = "https://m.place.naver.com/restaurant/11624984/home?entry=pll";
                    break;
                }
                case "미스터피자" :
                {
                    imageView.setImageResource(R.drawable.w_mrpizzawestern_22);
                    changeURL = "https://m.place.naver.com/restaurant/11826718/home?entry=pll";
                    break;
                }
                case "아웃닭" :
                {
                    imageView.setImageResource(R.drawable.w_outdakwestern_24);
                    changeURL = "https://m.place.naver.com/restaurant/35051596/home?entry=pll";
                    break;
                }
                case "맥도날드" :
                {
                    imageView.setImageResource(R.drawable.w_mcdonaldswestern_18);
                    changeURL = "https://m.place.naver.com/restaurant/1658905917/home?entry=pll";
                    break;
                }
                case "맘스터치" :
                {
                    imageView.setImageResource(R.drawable.w_momstouchwestern_21);
                    changeURL = "https://m.place.naver.com/restaurant/1064629613/home?entry=pll";
                    break;
                }
                case "도미노피자" :
                {
                    imageView.setImageResource(R.drawable.w_dominopizzawestern_9);
                    changeURL = "https://m.place.naver.com/restaurant/33771044/home?entry=pll";
                    break;
                }
                case "임실치즈피자" :
                {
                    imageView.setImageResource(R.drawable.w_imsilcheesepizzawestern_15);
                    changeURL = "https://m.place.naver.com/restaurant/20737476/home?entry=pll";
                    break;
                }
                case "고피자" :
                {
                    imageView.setImageResource(R.drawable.w_gopizzawestern_14);
                    changeURL = "https://m.place.naver.com/restaurant/1433165233/home?entry=pll";
                    break;
                }
                case "멘탈비히클" :
                {
                    imageView.setImageResource(R.drawable.w_mentalvehiclewestern_19);
                    changeURL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=멘탈비히클";
                    break;
                }
                case "피나치공" :
                {
                    imageView.setImageResource(R.drawable.w_pinachigongwestern_26);
                    changeURL = "https://m.place.naver.com/restaurant/1528654696/home?entry=pll";
                    break;
                }
                case "어니언스" :
                {
                    imageView.setImageResource(R.drawable.w_onionswestern_23);
                    changeURL = "https://m.place.naver.com/restaurant/1536568094/home?entry=pll";
                    break;
                }
                case "굽네치킨" :
                {
                    imageView.setImageResource(R.drawable.w_goobnechickenwestern_13);
                    changeURL = "https://m.place.naver.com/restaurant/12804573/home?entry=pll";
                    break;
                }
                case "BHC" :
                {
                    imageView.setImageResource(R.drawable.w_bhcwestern_3);
                    changeURL = "https://m.place.naver.com/restaurant/37393602/home?entry=pll";
                    break;
                }
                case "BBQ" :
                {
                    imageView.setImageResource(R.drawable.w_bbqwestern_2);
                    changeURL = "https://m.place.naver.com/restaurant/1437497668/home?entry=pll";
                    break;
                }
                case "꼬꼬아찌" :
                {
                    imageView.setImageResource(R.drawable.w_ggoggoazziwestern_12);
                    changeURL = "https://m.place.naver.com/restaurant/620109876/home?entry=pll";
                    break;
                }
                case "멕시카나" :
                {
                    imageView.setImageResource(R.drawable.w_mexicanawestern_20);
                    changeURL = "https://m.place.naver.com/restaurant/32839379/home?entry=pll";
                    break;
                }
                case "조커닭" :
                {
                    imageView.setImageResource(R.drawable.w_jokerdakwestern_16);
                    changeURL = "https://m.place.naver.com/restaurant/1160263950/home?entry=pll";
                    break;
                }
                case "닭제이" :
                {
                    imageView.setImageResource(R.drawable.w_dakjaeewestern_7);
                    changeURL = "https://m.place.naver.com/restaurant/15523780/home?entry=pll";
                    break;
                }
                case "바쿠스" :
                {
                    imageView.setImageResource(R.drawable.w_bacchuswestern_1);
                    changeURL = "https://m.place.naver.com/restaurant/32672235/home?entry=pll";
                    break;
                }
                case "버거앤프라이즈" :
                {
                    imageView.setImageResource(R.drawable.w_burgerandprisewestern_4);
                    changeURL = "https://m.place.naver.com/restaurant/1691638551/home?entry=pll";
                    break;
                }
                //  --------------------------------------   양식 끝 -------------------------------------
                //  --------------------------------------   일식 시작 -------------------------------------
                case "비샤몬텐" :
                {
                    imageView.setImageResource(R.drawable.j_bishamonten_1);
                    changeURL = "https://m.place.naver.com/restaurant/1273472045/home?entry=pll";
                    break;
                }
                case "스시하루" :
                {
                    imageView.setImageResource(R.drawable.j_sushiharu_2);
                    changeURL = "https://m.place.naver.com/restaurant/15522890/home?entry=plt";
                    break;
                }
                case "카메" :
                {
                    imageView.setImageResource(R.drawable.j_kame_3);
                    changeURL = "https://kamekame.modoo.at/?link=5oih1fu8";
                    break;
                }
                case "사누끼우동" :
                {
                    imageView.setImageResource(R.drawable.j_sanukkigyudong_4);
                    changeURL = "https://m.place.naver.com/restaurant/32425794/home";
                    break;
                }
                case "정스시" :
                {
                    imageView.setImageResource(R.drawable.j_jeongsushi_5);
                    changeURL = "https://m.place.naver.com/restaurant/1109974571/home?entry=plt";
                    break;
                }
                case "쇼우도우" :
                {
                    imageView.setImageResource(R.drawable.j_shoudou_6);
                    changeURL = "https://m.place.naver.com/restaurant/1175671833/home?entry=plt";
                    break;
                }
                case "이에케이 라멘" :
                {
                    imageView.setImageResource(R.drawable.j_iekeiramen_7);
                    changeURL = "https://m.place.naver.com/restaurant/1126815888/home";
                    break;
                }
                case "개척초밥" :
                {
                    imageView.setImageResource(R.drawable.j_gaecheoksushi_8);
                    changeURL = "https://m.place.naver.com/restaurant/1290668411/home?entry=plt";
                    break;
                }
                case "쇼우다이" :
                {
                    imageView.setImageResource(R.drawable.j_shoudai_9);
                    changeURL = "https://m.place.naver.com/restaurant/38412373/home";
                    break;
                }
                case "시마다" :
                {
                    imageView.setImageResource(R.drawable.j_simada_10);
                    changeURL = "https://m.place.naver.com/restaurant/37921648/home?entry=plt";
                    break;
                }
                case "스시도" :
                {
                    imageView.setImageResource(R.drawable.j_sushido_11);
                    changeURL = "https://m.place.naver.com/restaurant/32362833/home?entry=pll";
                    break;
                }
                case "엔동" :
                {
                    imageView.setImageResource(R.drawable.j_endong_12);
                    changeURL = "https://m.search.naver.com/search.naver?sm=mtb_hty.top&where=m&oquery=%EC%8A%A4%EC%8B%9C%EB%8F%84&tqi=hkPa5dp0JWVssFhL6iRssssstdC-163186&query=%EC%97%94%EB%8F%99";
                    break;
                }
                case "겐코" :
                {
                    imageView.setImageResource(R.drawable.j_genko_13);
                    changeURL = "https://m.place.naver.com/restaurant/1564926228/home?entry=pll";
                    break;
                }
                case "돈브로" :
                {
                    imageView.setImageResource(R.drawable.j_donbro_14);
                    changeURL = "https://m.place.naver.com/restaurant/1181245636/home?entry=pll";
                    break;
                }
            }

            counter++;
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.toastNotDataMsg), Toast.LENGTH_SHORT).show();
        }
    }
}