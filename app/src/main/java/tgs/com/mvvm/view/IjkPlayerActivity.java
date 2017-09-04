package tgs.com.mvvm.view;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import tgs.com.mvvm.R;
import tgs.com.mvvm.bean.VideoPlayUrlInfo;
import tgs.com.mvvm.bis.MediaController;
import tgs.com.mvvm.callback.DanmukuSwitchListener;
import tgs.com.mvvm.callback.VideoBackListener;
import tgs.com.mvvm.core.retrofit.BiliDanmukuDownloadUtil;
import tgs.com.mvvm.core.retrofit.MyObserver;
import tgs.com.mvvm.core.retrofit.RetrofitUtil;
import tgs.com.mvvm.core.retrofit.RxHelper;
import tgs.com.mvvm.utils.BilibiliSignUtil;
import tgs.com.mvvm.weight.player.VideoPlayerView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by 田桂森 on 2017/8/31.
 */

public class IjkPlayerActivity extends AutoLayoutActivity implements DanmukuSwitchListener, VideoBackListener {
    public static final String VIDEO_CID = "video_cid";
    public static final String VIDEO_TITLE = "video_title";
    private String startText = "初始化播放器...";
    private TextView videoStartInfo;
    private ImageView biliAnim;
    private AnimationDrawable mLoadingAnim;
    private String title;
    private VideoPlayerView mPlayerView;
    private View mBufferingIndicator;
    private IDanmakuView mDanmakuView;
    private DanmakuContext danmakuContext;
    private int LastPosition = 0;
    private int cid;
    private View videoStart;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ijkplayer);
        cid = getIntent().getIntExtra(VIDEO_CID, 0);
        title = getIntent().getStringExtra(VIDEO_TITLE);
        initView();
        initMediaPlayer();
        initData();
        
    }
    
    private void initData() {
        RetrofitUtil.getJson().getVideoUrl("https://interface.bilibili.com//playurl?appkey=f3bb208b3d081dc8&from=local&otype=json&player=1&cid=" + cid + "&quality=3&type=mp4&sign=" + getSign(cid, 3, "mp4"))
                .compose(RxHelper.ioMain2())
                .subscribe(new MyObserver<VideoPlayUrlInfo>(false, null) {
                    @Override
                    protected void next(VideoPlayUrlInfo videoPlayUrlInfo) {
                        List<VideoPlayUrlInfo.DurlBean> durl = videoPlayUrlInfo.getDurl();
                        ArrayList<String> urls=new ArrayList<String>();
                        urls.add(durl.get(0).getUrl());
                        urls.add(durl.get(0).getBackup_url().get(0));
    
                        try {
                            urls.add(durl.get(0).getBackup_url().get(1));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
    
                        BiliDanmukuDownloadUtil.downloadXML("http://comment.bilibili.com/" + cid + ".xml")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(baseDanmakuParser -> {
                                    mDanmakuView.prepare(baseDanmakuParser, danmakuContext);
                                    mDanmakuView.showFPS(false);
                                    mDanmakuView.enableDanmakuDrawingCache(false);
                                    mDanmakuView.setCallback(new DrawHandler.Callback() {
                                        @Override
                                        public void prepared() {
                                            mDanmakuView.start();
                                        }
                    
                                        @Override
                                        public void updateTimer(DanmakuTimer danmakuTimer) {
                                        }
                    
                                        @Override
                                        public void danmakuShown(BaseDanmaku danmaku) {
                                        }
                    
                                        @Override
                                        public void drawingFinished() {
                                        }
                                    });
                                }, throwable -> {
                                    startText = startText + "【失败】\n视频缓冲中...";
                                    videoStartInfo.setText(startText);
                                    startText = startText + "【失败】\n" + throwable.getMessage();
                                    videoStartInfo.setText(startText);
                                });
                        HashMap<String, String> header = new HashMap<>();
                        header.put("Referer", "https://www.bilibili.com/");
                        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
    
                        mPlayerView.setHeader(header);
                        Uri uri = Uri.parse(urls.get(0));
                        mPlayerView.setVideoURI(uri);
                        
                        //                        mPlayerView.setVideoURI(uri);
                        mPlayerView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(IMediaPlayer iMediaPlayer) {
                                mLoadingAnim.stop();
                                startText = startText + "【完成】\n视频缓冲中...";
                                videoStartInfo.setText(startText);
                                videoStart.setVisibility(View.GONE);
                            }
                        });
                        mPlayerView.start();
                    }
                });
        
    }
    
    private void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawable(null);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        videoStartInfo = (TextView) findViewById(R.id.video_start_info);
        biliAnim = (ImageView) findViewById(R.id.bili_anim);
        mPlayerView = (VideoPlayerView) findViewById(R.id.playerView);
        mBufferingIndicator = findViewById(R.id.buffering_indicator);
        mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
        videoStart = findViewById(R.id.video_start);
        
        startText = startText + "【完成】\n解析视频地址...【完成】\n全舰弹幕填装...";
        videoStartInfo.setText(startText);
        mLoadingAnim = (AnimationDrawable) biliAnim.getBackground();
        mLoadingAnim.start();
        
    }
    
    @SuppressLint("UseSparseArrays")
    private void initMediaPlayer() {
        //配置播放器
        MediaController mMediaController = new MediaController(this);

        mMediaController.setTitle(title);
       
        mPlayerView.setMediaController(mMediaController);
        mPlayerView.setMediaBufferingIndicator(mBufferingIndicator);
        mPlayerView.requestFocus();
        mPlayerView.setOnInfoListener(onInfoListener);
        mPlayerView.setOnSeekCompleteListener(onSeekCompleteListener);
        mPlayerView.setOnCompletionListener(onCompletionListener);
        mPlayerView.setOnControllerEventsListener(onControllerEventsListener);
        //设置弹幕开关监听
        mMediaController.setDanmakuSwitchListener(this);
        //设置返回键监听
        mMediaController.setVideoBackEvent(this);
        //配置弹幕库
        mDanmakuView.enableDanmakuDrawingCache(true);
        //设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        //滚动弹幕最大显示5行
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5);
        //设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
        //设置弹幕样式
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(0.8f)
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
        
    }
    private String getSign(int cid, int quality, String type) {
        HashMap<String, String> encryptMap = new HashMap<>();
        encryptMap.put("appkey", "f3bb208b3d081dc8");
        encryptMap.put("cid", cid + "");
        encryptMap.put("from", "local");
        encryptMap.put("otype", "json");
        encryptMap.put("player", "1");
        encryptMap.put("quality", quality + "");
        encryptMap.put("type", type);
        return BilibiliSignUtil.getSign(encryptMap, "1c15888dc316e05a15fdd0a02ed6584f");
    }
    
    
    /**
     * 视频缓冲事件回调
     */
    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(IMediaPlayer mp, int what, int extra) {
            if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_START) {
                if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                    mDanmakuView.pause();
                    if (mBufferingIndicator != null) {
                        mBufferingIndicator.setVisibility(View.VISIBLE);
                    }
                }
            } else if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_END) {
                if (mDanmakuView != null && mDanmakuView.isPaused()) {
                    mDanmakuView.resume();
                }
                if (mBufferingIndicator != null) {
                    mBufferingIndicator.setVisibility(View.GONE);
                }
            }
            return true;
        }
    };
    
    /**
     * 视频跳转事件回调
     */
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {
        
        @Override
        public void onSeekComplete(IMediaPlayer mp) {
            if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                mDanmakuView.seekTo(mp.getCurrentPosition());
            }
        }
    };
    
    /**
     * 视频播放完成事件回调
     */
    private IMediaPlayer.OnCompletionListener onCompletionListener = new IMediaPlayer.OnCompletionListener() {
        
        @Override
        public void onCompletion(IMediaPlayer mp) {
            if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                mDanmakuView.seekTo((long) 0);
                mDanmakuView.pause();
            }
            mPlayerView.pause();
        }
    };
    
    /**
     * 控制条控制状态事件回调
     */
    private VideoPlayerView.OnControllerEventsListener onControllerEventsListener = new VideoPlayerView.OnControllerEventsListener() {
        
        @Override
        public void onVideoPause() {
            if (mDanmakuView != null && mDanmakuView.isPrepared()) {
                mDanmakuView.pause();
            }
        }
        
        
        @Override
        public void OnVideoResume() {
            if (mDanmakuView != null && mDanmakuView.isPaused()) {
                mDanmakuView.resume();
            }
        }
    };
    
    
    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.seekTo((long) LastPosition);
        }
        if (mPlayerView != null && !mPlayerView.isPlaying()) {
            mPlayerView.seekTo(LastPosition);
        }
    }
    
    
    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayerView != null) {
            LastPosition = mPlayerView.getCurrentPosition();
            mPlayerView.pause();
        }
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }
    
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
        if (mLoadingAnim != null) {
            mLoadingAnim.stop();
            mLoadingAnim = null;
        }
    }
    
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayerView != null && mPlayerView.isDrawingCacheEnabled()) {
            mPlayerView.destroyDrawingCache();
        }
        if (mDanmakuView != null && mDanmakuView.isPaused()) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
        if (mLoadingAnim != null) {
            mLoadingAnim.stop();
            mLoadingAnim = null;
        }
    }
    
    
    /**
     * 弹幕开关回调
     */
    @Override
    public void setDanmakushow(boolean isShow) {
        if (mDanmakuView != null) {
            if (isShow) {
                mDanmakuView.show();
            } else {
                mDanmakuView.hide();
            }
        }
    }
    
    
    /**
     * 退出界面回调
     */
    @Override
    public void back() {
        onBackPressed();
    }
}
