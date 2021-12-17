package com.random.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CategoryStage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_stage);
    }

    // 한식 메뉴를 선택 했을 때
    public void KoreanFood(View view) {
        Intent intent = new Intent(CategoryStage.this, RandomFood.class);
        intent.putExtra("category", "KoreanFood");
        startActivity(intent);
    }

    // 중식 메뉴를 선택 했을 때
    public void ChineseFood(View view) {
        Intent intent = new Intent(CategoryStage.this, RandomFood.class);
        intent.putExtra("category", "ChineseFood");
        startActivity(intent);
    }

    // 일식 메뉴를 선택 했을 때
    public void JapaneseFood(View view) {
        Intent intent = new Intent(CategoryStage.this, RandomFood.class);
        intent.putExtra("category", "JapaneseFood");
        startActivity(intent);
    }

    // 양식 메뉴를 선택 했을 때
    public void food(View view) {
        Intent intent = new Intent(CategoryStage.this, RandomFood.class);
        intent.putExtra("category", "food");
        startActivity(intent);
    }

    // 모두선택 메뉴를 선택 했을 때
    public void AllFood(View view) {
        Intent intent = new Intent(CategoryStage.this, RandomFood.class);
        intent.putExtra("category", "AllFood");
        startActivity(intent);
    }
}