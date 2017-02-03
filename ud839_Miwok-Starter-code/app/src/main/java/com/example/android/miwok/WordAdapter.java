package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Puja on 05/12/16.
 */

public class WordAdapter extends ArrayAdapter<word>{


    private int colourResourceId;
    public WordAdapter(Activity context, ArrayList<word> currentWord,int mColourResourceId){
        super(context,0,currentWord);
        colourResourceId=mColourResourceId;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View listView=convertView;
        if(listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        word currentWord= getItem(position);
        int color= ContextCompat.getColor(getContext(),colourResourceId);

        TextView nView= (TextView) listView.findViewById(R.id.miwok_text_view);
        nView.setText(currentWord.getMiwoktranslation());
        nView.setBackgroundColor(color);

        TextView nView1=(TextView)listView.findViewById(R.id.default_text_view);
        nView1.setText(currentWord.getDefaultTranslation());
        nView1.setBackgroundColor(color);

        if(currentWord.hasImage()){
            ImageView iView=(ImageView)listView.findViewById(R.id.image);
            iView.setImageResource(currentWord.getDefaultResourceId());
            iView.setVisibility(View.VISIBLE);

        }
        else{
            ImageView iView=(ImageView)listView.findViewById(R.id.image);
            iView.setVisibility(View.GONE);
         }
        return listView;
    }
}
