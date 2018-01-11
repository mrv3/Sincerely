package com.example.victory.sincerely.RSSFeed.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.example.victory.sincerely.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Victory on 11/27/2017.
 */

public class PicassoClient {

    public static void downloadImage(Context c, String imageUrl, ImageView img){

        if (imageUrl != null && imageUrl.length()>0){

            Picasso.with(c).load(imageUrl)
                    .placeholder(R.color.cardview_dark_background)
                    .resize(300,300)
                    //.onlyScaleDown()
                    .centerInside()
                    .into(img);
        } else {
            Picasso.with(c)
                    .load(R.color.cardview_dark_background)
                    .resize(50,50)
                    .centerInside()
                   // .onlyScaleDown()
                   // .fit()
                    .into(img);
        }
    }
}
