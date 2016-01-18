package com.example.josefranco.tutorial.reproductor2;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.widget.AdapterView.OnItemClickListener;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private ImageButton btn;
    private ImageButton btnForward;
    private ImageButton btnBackward;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private boolean intialStage = true;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    private ImageButton playListButon;
    private String urlCurrent = "http://educacionweb.mx/Audacia/Viaje/Viaje%20al%20centro%20de%20la%20tierra_capitulo%204.mp3";


    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler() {
        @Override
        public void close() {

        }

        @Override
        public void flush() {

        }

        @Override
        public void publish(LogRecord record) {

        }
    };
    private Utilities utils;
    private int seekForwardTime = 30000; // 30_000 milliseconds
    private int seekBackwardTime = 30000; // 30_000 milliseconds
    Boolean Reproduce=false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        //All player buttons
        btn = (ImageButton) findViewById(R.id.btnPlay);
        btnForward = (ImageButton) findViewById(R.id.btnForward);
        btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        songTitleLabel = (TextView) findViewById(R.id.songTitle);
        songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
        playListButon = (ImageButton) findViewById(R.id.btnPlaylist);


        //Media Player
        utils = new Utilities();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        // Listeners
        playListButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),List_Chapters_Activity.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(pausePlay);
        btnForward.setOnClickListener(btnForwardd);
        btnBackward.setOnClickListener(btnBackwardd);
        songProgressBar.setOnSeekBarChangeListener(this); // Important
        mediaPlayer.setOnCompletionListener(this); // Important

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            urlCurrent = extras.getString("URL");
        }
        Intent intent2 = getIntent();
        Bundle extras2 = intent2.getExtras();
        if(extras2!=null){
            Reproduce = extras2.getBoolean("Reproduce");
        }

        if(Reproduce==true){
            if (!playPause) {
                btn.setBackgroundResource(R.drawable.pause_button);
                if (intialStage)
                    new Player().execute(urlCurrent);
                else {
                    if (!mediaPlayer.isPlaying())
                        mediaPlayer.start();
                }
                playPause = true;
            } else {
                btn.setBackgroundResource(R.drawable.play_button);

                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                playPause = false;
            }
            Reproduce=false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //prueba-------------------------------------------------------------------------------




    private View.OnClickListener pausePlay = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // TODO Auto-generated method stub

            if (!playPause) {
                btn.setBackgroundResource(R.drawable.pause_button);
                if (intialStage)
                    new Player().execute(urlCurrent);
                else {
                    if (!mediaPlayer.isPlaying())
                        mediaPlayer.start();
                }
                playPause = true;
            } else {
                btn.setBackgroundResource(R.drawable.play_button);

                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                playPause = false;
            }
        }
    };

    /**
     * Forward button click event
     * Forwards song specified seconds
     */
    private View.OnClickListener btnForwardd = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // get current song position
            int currentPosition = mediaPlayer.getCurrentPosition();
            // check if seekForward time is lesser than song duration
            if (currentPosition + seekForwardTime <= mediaPlayer.getDuration()) {
                // forward song
                mediaPlayer.seekTo(currentPosition + seekForwardTime);
            } else {
                // forward to end position
                mediaPlayer.seekTo(mediaPlayer.getDuration());
            }
        }
    };
    /**
     * Backward button click event
     * Backwards song specified seconds
     */
    private View.OnClickListener btnBackwardd = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // get current song position
            int currentPosition = mediaPlayer.getCurrentPosition();
            // check if seekForward time is lesser than song duration
            if (currentPosition - seekBackwardTime >= 1) {
                // backward song
                mediaPlayer.seekTo(currentPosition - seekBackwardTime);
            } else {
                // backward to end position
                mediaPlayer.seekTo(0);
            }
        }
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        updateProgressBar();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //remove message Handler from updating progress bar
        songProgressBar.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        songProgressBar.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mediaPlayer.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mediaPlayer.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }

    /**
     * preparing mediaplayer will take sometime to buffer the content so prepare it inside the background thread and starting it on UI thread.
     */

    class Player extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progress;

        @Override
        protected Boolean doInBackground(String... params) {
            // TODO Auto-generated method stub
            Boolean prepared;
            try {

                mediaPlayer.setDataSource(params[0]);

                mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        intialStage = true;
                        playPause = false;
                        btn.setBackgroundResource(R.drawable.play_button);
                        mediaPlayer.stop();
                        //mediaPlayer.reset();

                        //TEST CODE
                        // set Progress bar values
                        songProgressBar.setProgress(0);
                        songProgressBar.setMax(100);
                        // Updating progress bar
                       // updateProgressBar();
                    }
                });
                mediaPlayer.prepare();
                // Updating progress bar the first time
                prepared = true;
                updateProgressBar();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                Log.d("IllegarArgument", e.getMessage());
                prepared = false;
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                prepared = false;
                e.printStackTrace();
            }
            return prepared;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (progress.isShowing()) {
                progress.cancel();
            }
            Log.d("Prepared", "//" + result);
            mediaPlayer.start();

            intialStage = false;
        }

        public Player() {
            progress = new ProgressDialog(MainActivity.this);
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            this.progress.setMessage("Cargando...");
            this.progress.show();

        }
    }
    /**
     * Update timer on seekbar
     * */
    public void updateProgressBar() {
        songProgressBar.postDelayed(mUpdateTimeTask, 100);
    }
    /**
     * Background Runnable thread
     * */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mediaPlayer.getDuration();
            long currentDuration = mediaPlayer.getCurrentPosition();

            // Displaying Total Duration time
            songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            songProgressBar.postDelayed(this, 100);
        }
    };



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

}
