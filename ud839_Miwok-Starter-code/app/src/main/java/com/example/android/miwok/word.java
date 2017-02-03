package com.example.android.miwok;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Puja on 05/12/16.
 */

public class word {
    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mImageResourceId=-1;

    private int mAudioResourceId;


    public word(String defaultTranslation, String miwokTranslation,int defaultAudioId){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mAudioResourceId=defaultAudioId;

    }

    public word(String defaultTranslation, String miwokTranslation, int defaultResourceId,int defaultAudioId ){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mImageResourceId=defaultResourceId;
        mAudioResourceId=defaultAudioId;
    }

    public String getMiwoktranslation(){
        return mMiwokTranslation;
    }
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public int getDefaultResourceId(){return mImageResourceId;}
    public int getDefaultAudioId(){return mAudioResourceId;}
    public boolean hasImage(){
        if(getDefaultResourceId()==-1)
            return false;
        else
            return true;
    }
}
