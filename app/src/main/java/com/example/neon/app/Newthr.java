package com.example.neon.app;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Neon on 24.10.2016.
 */
public class Newthr extends AsyncTask<String, Void, String> {

    public ArrayList<String> titleList = new ArrayList<String>();
    public ArrayList<String> gameList = new ArrayList<String>();
    public ArrayList<String> numList = new ArrayList<String>();
    public ArrayList<String> priceList = new ArrayList<String>();
    public ArrayList<String> imgList = new ArrayList<String>();
    public Elements title;
    public Elements game;
    public Elements num;
    public Elements price;
    public Elements image;

    @Override
    protected String doInBackground(String... arg) {


        Document doc;


        try {
            // Подключение
            doc = Jsoup.connect("http://steamcommunity.com/market/search?q=").get();


            //Передача данных в переменные

            title = doc.select(".market_listing_item_name");
            game = doc.select(".market_listing_game_name");
            num = doc.select(".market_listing_num_listings_qty");
            price = doc.select(".normal_price");
//            img = doc.select(".market_listing_item_img");

            //List clear
            titleList.clear();
            gameList.clear();
            numList.clear();
            priceList.clear();
//            imgList.clear();

            for (Element titles : title) {
                titleList.add(titles.text());
            }

            for (Element games : game) {
                gameList.add(games.text());
            }
            for (Element nums : num) {
                numList.add(nums.text());
            }
            for (Element prices : price) {
                priceList.add(prices.text());
            }
//                for (Element imgs: img){
//                    imgList.add(imgs.);
//                }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }




}
