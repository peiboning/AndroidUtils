package com.android.video;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sohu.util.demo.R;


public class VideoActivity extends Activity implements TextureView.SurfaceTextureListener, SeekBar.OnSeekBarChangeListener, Runnable, View.OnClickListener {
    private static final String VIDEO_SOURCE = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";

    private TextureView mVideoView;
    private ImageView mBack;
    private ImageView mFullScreenBtn;
    private SeekBar mSeekBar;
    private TextView time;
    private RelativeLayout progressBar;

    private RelativeLayout controll;
    private Surface mSurface;
    private int mVideoLength;
    private Handler H;
    private int defWidth;

    private int defHeight;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.e("VideoActivity", "onCreate ");
        setContentView(R.layout.activity_video);

        initData();
        initView();
        initPlayer();
        controll.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        H = new Handler(Looper.getMainLooper());
    }

    private void initPlayer() {

    }

    private void initView() {
        mVideoView = (TextureView) findViewById(R.id.video_view);
        mBack = (ImageView) findViewById(R.id.btn_back);
        mFullScreenBtn = (ImageView) findViewById(R.id.btn_full_screen);
        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        controll = (RelativeLayout) findViewById(R.id.controll);
        time = (TextView) findViewById(R.id.time);
        String str = TimeUtils.getTimeStr(0) + "/"+TimeUtils.getTimeStr(MediaPlayerManager.getInstance().getDuration1());
        Log.e("VideoActivity", "init time str: " + str);
        time.setText(str);
        progressBar = (RelativeLayout) findViewById(R.id.progress);

        mSeekBar.setOnSeekBarChangeListener(this);
        mVideoView.setSurfaceTextureListener(this);
        mFullScreenBtn.setOnClickListener(this);
        mBack.setOnClickListener(this);

        if(MediaPlayerManager.getInstance().isPlaying()){
            int ori = this.getResources().getConfiguration().orientation ; //获取屏幕方向
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) ((ViewGroup)mVideoView.getParent()).getLayoutParams();
            FrameLayout.LayoutParams lp1 = (FrameLayout.LayoutParams) controll.getLayoutParams();
            if(ori == Configuration.ORIENTATION_LANDSCAPE){

                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

                lp.height = screenHeight;
                lp.width = screenWidth;
                lp1.height = screenHeight;
                lp1.width = screenWidth;

                lp1.bottomMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
                //横屏
            }else if(ori == Configuration.ORIENTATION_PORTRAIT){
//                lp.height = MediaPlayerManager.getInstance().getDefHeight();
//                lp.width = MediaPlayerManager.getInstance().getDefWidth();
//                lp1.height = MediaPlayerManager.getInstance().getDefHeight();
//                lp1.width = MediaPlayerManager.getInstance().getDefWidth();
                //竖屏
            }
            ((ViewGroup)mVideoView.getParent()).setLayoutParams(lp);
            controll.setLayoutParams(lp1);
            H.post(VideoActivity.this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("VideoActivity", "onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("VideoActivity", "onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("VideoActivity", "onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("VideoActivity", "onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("VideoActivity", "onDestroy ");
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.e("VideoActivity", "onSurfaceTextureAvailable width:" + width + "  height:" + height);
        mSurface = new Surface(surface);
        MediaPlayerManager.getInstance().setSurface(mSurface);
        if(!MediaPlayerManager.getInstance().isPlaying()){
            startPlayer();
        }
    }

    private void startPlayer() {
        MediaPlayerManager.getInstance().reset();
        try {
            MediaPlayerManager.getInstance().setDataSource(VIDEO_SOURCE);
            MediaPlayerManager.getInstance().setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.e("VideoActivity", " play onBufferingUpdate: " + percent);
                }
            });
            MediaPlayerManager.getInstance().prepareAsync();
            MediaPlayerManager.getInstance().setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mVideoLength = MediaPlayerManager.getInstance().getDuration();
                    MediaPlayerManager.getInstance().setDuration(mVideoLength);
                    String str = TimeUtils.getTimeStr(0) + "/"+TimeUtils.getTimeStr(MediaPlayerManager.getInstance().getDuration1());
                    time.setText(str);
                    MediaPlayerManager.getInstance().start();
                    H.postDelayed(VideoActivity.this, 1000);
                    startHiddenContorll();
                    progressBar.setVisibility(View.GONE);
                    controll.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startHiddenContorll(){
        H.postDelayed(new Runnable() {
            @Override
            public void run() {
                controll.setVisibility(View.INVISIBLE);
                AnimalUtils.alphGone(controll);
            }
        }, 10000);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.e("VideoActivity", "onSurfaceTextureSizeChanged width:" + width + "  height:" + height);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if(MediaPlayerManager.getInstance().isPlaying()){
//            MediaPlayerManager.getInstance().stop();
//            MediaPlayerManager.getInstance().release();
            H.removeCallbacks(this);
        }
        Log.e("VideoActivity", "surface onDestory");
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(fromUser){
            int lenght = (int) (progress*1.0/100*MediaPlayerManager.getInstance().getDuration1());
            if(lenght > MediaPlayerManager.getInstance().getDuration1()){
                lenght = MediaPlayerManager.getInstance().getDuration1();
            }
            if(MediaPlayerManager.getInstance().isPlaying()){
                MediaPlayerManager.getInstance().seekTo(lenght);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void run() {
        if(MediaPlayerManager.getInstance().isPlaying()){
            int playPos = MediaPlayerManager.getInstance().getCurrentPosition();
            int progress = (int) (playPos*1.0f/MediaPlayerManager.getInstance().getDuration1()*100);
            mSeekBar.setProgress(progress);
            String str = TimeUtils.getTimeStr(playPos) + "/"+TimeUtils.getTimeStr(MediaPlayerManager.getInstance().getDuration1());
            time.setText(str);
            H.postDelayed(this, 1000);
        }else{
            H.removeCallbacks(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_full_screen){
            switch2Fullscreen();
        }else if(id == R.id.btn_back){
            backAction();
        }
    }

    private void backAction() {
        int ori = this.getResources().getConfiguration().orientation ; //获取屏幕方向
        if(ori == Configuration.ORIENTATION_LANDSCAPE){
            switch2Fullscreen();
        }else{
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            backAction();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    boolean isflag = true;
    private void switch2Fullscreen() {
        if(isflag){
            defHeight = ((ViewGroup)mVideoView.getParent()).getHeight();
            defWidth = ((ViewGroup)mVideoView.getParent()).getWidth();
            MediaPlayerManager.getInstance().setDef(defWidth, defHeight);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            getWindow().addFlags(WindowManager.LayoutParams.SCREEN_ORIENTATION_CHANGED);

        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().clearFlags(WindowManager.LayoutParams.SCREEN_ORIENTATION_CHANGED);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }

        isflag = !isflag;
    }

    private long lastViewTime = 0L;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(System.currentTimeMillis() - lastViewTime > 10 * 1000){
            controll.setVisibility(View.VISIBLE);
            AnimalUtils.alphView(controll);
            startHiddenContorll();
            lastViewTime = System.currentTimeMillis();
        }
        return super.onTouchEvent(event);
    }
}
