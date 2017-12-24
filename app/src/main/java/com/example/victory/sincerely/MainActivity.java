package com.example.victory.sincerely;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);


        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Title", "Description", R.drawable.ic_launcher_background);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setTitleColor(R.color.white);
        ahoyOnboarderCard1.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(8, this));

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Hello", "Victory", R.drawable.ic_launcher_background);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setTitleColor(R.color.white);
        ahoyOnboarderCard2.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(10, this));
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(8, this));
  //      ahoyOnboarderCard1.setIconLayoutParams(iconWidth, iconHeight, marginTop, marginLeft, marginRight, marginBottom);
        ahoyOnboarderCard1.setIconLayoutParams(50, 50, 50, 50, 50, 50);

        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        //pages.add(ahoyOnboarderCard3);

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
