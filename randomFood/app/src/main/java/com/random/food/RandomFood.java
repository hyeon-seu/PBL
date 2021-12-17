package com.random.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class RandomFood extends AppCompatActivity {
    TextView randomTxt;
    TextView foodName;

    ImageView foodImg;

    String[] data;

    int[] dataNumbers;

    String category;

    int counter = 0;

    final String TAG = "FreeFood";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radom_food);

        randomTxt = (TextView)findViewById(R.id.randomTxt);
        foodName = (TextView)findViewById(R.id.foodName);
        foodImg = (ImageView)findViewById(R.id.foodImg);

        Intent intent = getIntent();
        category = intent.getExtras().getString("category");
    }

    // 랜덤 버튼을 클릭 했을 때
    public void randomBtn(View view) {
        Log.d(TAG, "category ===> " + category);

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

        if(dataNumbers == null) {
            dataNumbers = dataRandom(data);
        }

        dataExposure();
    }

    /**
     * 중복 제거를 하고 랜덤으로 리스트 뽑기
     * @param data
     * @return
     */
    private int[] dataRandom(String[] data) {
        Log.d(TAG, "data_length ===> " + data.length);
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

    // click 버튼을 누를 시 데이터 랜덤으로 출력
    private void dataExposure() {
        if(dataNumbers.length > counter) {
            // 이미지로 같이 사용하고 싶은 경우 아래 주석 해제
            /*String[] foodData = data[dataNumbers[counter]].split(",");
            String strImgName = foodData[0]; // 음식 이미지
            String strFoodName = foodData[1]; // 음식 이름

            int lid = this.getResources().getIdentifier(strImgName, "drawable", this.getPackageName());*/

            randomTxt.setVisibility(View.GONE);

            // 이미지로 같이 사용하고 싶은 경우 아래 주석 해제
            /*foodImg.setVisibility(View.VISIBLE);
            foodImg.setImageResource(lid);*/
            foodName.setVisibility(View.VISIBLE);
            // 이미지로 같이 사용하고 싶은 경우 아래 주석 해제
            // foodName.setText(strFoodName);
            foodName.setText(data[dataNumbers[counter]]);

            counter++;
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.toastNotDataMsg), Toast.LENGTH_SHORT).show();
        }
    }
}