package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer player;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener=new
            AudioManager.OnAudioFocusChangeListener(){
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT|
                            focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        player.pause();
                        player.seekTo(0);
                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_GAIN_TRANSIENT){
                        player.start();
                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                        ReleaseMediaPlayer();
                    }
                }
            };
    MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            ReleaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        ReleaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<word> words =new ArrayList<word>();

        words.add(new word("father", "әpә", R.drawable.family_father,R.raw.family_father));
        words.add(new word("mother", "әṭa", R.drawable.family_mother,R.raw.family_mother));
        words.add(new word("son", "angsi", R.drawable.family_son,R.raw.family_son));
        words.add(new word("daughter", "tune", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new word("older brother", "taachi", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new word("younger brother", "chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new word("older sister", "teṭe", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new word("younger sister", "kolliti", R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new word("grandmother", "ama", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new word("grandfather", "paapa", R.drawable.family_grandfather,R.raw.family_grandfather));


        WordAdapter adapter=new WordAdapter(this, words,R.color.category_family);

        ListView listView=(ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word w=words.get(position);
                ReleaseMediaPlayer();
                int result=mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    player=MediaPlayer.create(FamilyActivity.this,w.getDefaultAudioId());
                    player.start();
                    player.setOnCompletionListener(mCompletionListener);
                }

            }
        });
    }

    private void ReleaseMediaPlayer(){
        if(player!=null){
            player.release();
            player=null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
