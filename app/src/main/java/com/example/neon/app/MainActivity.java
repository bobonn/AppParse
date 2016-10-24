package com.example.neon.app;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.neon.app.Newthr;

public class MainActivity extends ListActivity {

    public class Val {

        private String name; // название слота
        private String game;  // название игры
        private int image; // картинка
        private String price; // стоимость
        private String num;// кол-во

        public Val(int image, String name, String game, String price, String num){

            this.name=name;
            this.game=game;
            this.price=price;
            this.num=num;
            this.image=image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGame() {
            return game;
        }
        public void setGame(String game) {
            this.game = game;
        }

        public String getPrice() {
            return price;
        }
        public void setPrice(String price) {
            this.price = price;
        }
        public String getNum() {
            return num;
        }
        public void setNum(String num) {
            this.num = num;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }


    public Val[] vals = {
        new Val (image, titleList, gameList, numList, priceList)
    };


    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Newthr newthr= new Newthr();
        setListAdapter(new ValAdapter(vals));
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // Здесь будет реализовано всплывающее окно с описанием предмета
                Val selectedState = (vals) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getName(),
//                        Toast.LENGTH_SHORT).show();
            }
        };
        getListView().setOnItemClickListener(itemListener);

    }

    private Val getModel(int position) {
        return (((ValAdapter) getListAdapter()).getItem(position));
    }

    public class ValAdapter extends ArrayAdapter<Val> {

        private LayoutInflater mInflater;



        ValAdapter(Val[] list) {
            super(MainActivity.this, R.layout.activity_main, list);
            mInflater = LayoutInflater.from(MainActivity.this);
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder;
            View row = convertView;
            if (row == null) {

                row = mInflater.inflate(R.layout.activity_main, parent, false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) row.findViewById(R.id.img);
                holder.nameView = (TextView) row.findViewById(R.id.name);
                holder.gameView = (TextView) row.findViewById(R.id.game);
                holder.numView = (TextView) row.findViewById(R.id.num);
                holder.priceView = (TextView) row.findViewById(R.id.price);
                row.setTag(holder);
            } else {

                holder = (ViewHolder) row.getTag();
            }

            MainActivity.Val val = getModel(position);

            holder.imageView.setImageResource((val.getImage()));
            holder.nameView.setText(val.getName());
            holder.gameView.setText(val.getGame());
            holder.numView.setText(val.getNum());
            holder.priceView.setText(val.getPrice());
            return row;
        }



        class ViewHolder {
            public ImageView imageView;
            public TextView nameView, gameView;
            public TextView numView;
            public TextView priceView;
        }
    }


}
}
