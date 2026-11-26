package com.navi.tool.ui.extra;

import android.R;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.navi.tool.utils.ToastHelper;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class FileVideoPreviewActivity extends androidx.appcompat.app.r {
    private static final long CONTROL_AUTO_HIDE_DELAY_MS = 3000;
    public static final String EXTRA_VIDEO_PATH = "video_path";
    private AudioFocusRequest audioFocusRequest;
    private AudioManager audioManager;
    private ImageButton btnMute;
    private ImageButton btnPlayPause;
    private View controlsBar;
    private GestureDetector gestureDetector;
    private Runnable hideControlsRunnable;
    private MediaPlayer mediaPlayer;
    private Runnable progressRunnable;
    private FrameLayout rootLayout;
    private SeekBar seekVideoProgress;
    private TextView textCurrentTime;
    private TextView textTotalTime;
    private TextView textVideoTitle;
    private TextureView textureView;
    private View topBar;
    private int videoHeight;
    private String videoPath;
    private Surface videoSurface;
    private int videoWidth;
    private final Handler progressHandler = new Handler(Looper.getMainLooper());
    private boolean isPrepared = false;
    private boolean isSeeking = false;
    private boolean isMuted = false;
    private boolean controlsVisible = true;

    private void abandonAudioFocus() {
        AudioManager audioManager = this.audioManager;
        if (audioManager == null) {
            return;
        }
        AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
        if (audioFocusRequest != null) {
            audioManager.abandonAudioFocusRequest(audioFocusRequest);
        } else {
            audioManager.abandonAudioFocus(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyCenterCrop() {
        if (this.textureView == null) {
            return;
        }
        this.textureView.setTransform(new Matrix());
        this.textureView.invalidate();
    }

    private void applyMuteState() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            float f7 = this.isMuted ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f;
            mediaPlayer.setVolume(f7, f7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatTime(int i7) {
        int max = Math.max(0, i7 / 1000);
        return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(max / 60), Integer.valueOf(max % 60));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideControls() {
        this.controlsVisible = false;
        View view = this.topBar;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.controlsBar;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        this.progressHandler.removeCallbacks(this.hideControlsRunnable);
    }

    private void hideSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void initViews() {
        this.textureView = (TextureView) findViewById(g4.p.texture_video_preview);
        this.rootLayout = (FrameLayout) findViewById(g4.p.layout_video_preview_root);
        this.topBar = findViewById(g4.p.layout_video_top_bar);
        this.controlsBar = findViewById(g4.p.layout_video_controls);
        this.btnPlayPause = (ImageButton) findViewById(g4.p.btn_video_play_pause);
        this.btnMute = (ImageButton) findViewById(g4.p.btn_video_mute);
        this.seekVideoProgress = (SeekBar) findViewById(g4.p.seek_video_progress);
        this.textCurrentTime = (TextView) findViewById(g4.p.text_video_current_time);
        this.textTotalTime = (TextView) findViewById(g4.p.text_video_total_time);
        this.textVideoTitle = (TextView) findViewById(g4.p.text_video_title);
        final int i7 = 0;
        ((ImageButton) findViewById(g4.p.btn_close_video_preview)).setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.m4

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ FileVideoPreviewActivity f5338b;

            {
                this.f5338b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i8 = i7;
                FileVideoPreviewActivity fileVideoPreviewActivity = this.f5338b;
                switch (i8) {
                    case 0:
                        fileVideoPreviewActivity.lambda$initViews$0(view);
                        break;
                    case 1:
                        fileVideoPreviewActivity.lambda$initViews$1(view);
                        break;
                    default:
                        fileVideoPreviewActivity.lambda$initViews$2(view);
                        break;
                }
            }
        });
        final int i8 = 1;
        this.btnPlayPause.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.m4

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ FileVideoPreviewActivity f5338b;

            {
                this.f5338b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i82 = i8;
                FileVideoPreviewActivity fileVideoPreviewActivity = this.f5338b;
                switch (i82) {
                    case 0:
                        fileVideoPreviewActivity.lambda$initViews$0(view);
                        break;
                    case 1:
                        fileVideoPreviewActivity.lambda$initViews$1(view);
                        break;
                    default:
                        fileVideoPreviewActivity.lambda$initViews$2(view);
                        break;
                }
            }
        });
        final int i9 = 2;
        this.btnMute.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.m4

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ FileVideoPreviewActivity f5338b;

            {
                this.f5338b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i82 = i9;
                FileVideoPreviewActivity fileVideoPreviewActivity = this.f5338b;
                switch (i82) {
                    case 0:
                        fileVideoPreviewActivity.lambda$initViews$0(view);
                        break;
                    case 1:
                        fileVideoPreviewActivity.lambda$initViews$1(view);
                        break;
                    default:
                        fileVideoPreviewActivity.lambda$initViews$2(view);
                        break;
                }
            }
        });
        q0 q0Var = new q0(3, this);
        this.textureView.setOnTouchListener(q0Var);
        this.rootLayout.setOnTouchListener(q0Var);
        this.seekVideoProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.FileVideoPreviewActivity.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i10, boolean z6) {
                if (z6) {
                    FileVideoPreviewActivity.this.textCurrentTime.setText(FileVideoPreviewActivity.this.formatTime(i10));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                FileVideoPreviewActivity.this.isSeeking = true;
                FileVideoPreviewActivity.this.keepControlsVisible();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                FileVideoPreviewActivity.this.isSeeking = false;
                if (FileVideoPreviewActivity.this.isPrepared && FileVideoPreviewActivity.this.mediaPlayer != null) {
                    FileVideoPreviewActivity.this.mediaPlayer.seekTo(seekBar.getProgress());
                }
                FileVideoPreviewActivity.this.scheduleAutoHideControls();
            }
        });
        updateMuteIcon();
        showControls();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keepControlsVisible() {
        showControls();
        scheduleAutoHideControls();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$1(View view) {
        togglePlayback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$2(View view) {
        toggleMute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViews$3(View view, MotionEvent motionEvent) {
        GestureDetector gestureDetector = this.gestureDetector;
        return gestureDetector != null && gestureDetector.onTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$preparePlayer$4(MediaPlayer mediaPlayer) {
        this.isPrepared = true;
        this.videoWidth = mediaPlayer.getVideoWidth();
        this.videoHeight = mediaPlayer.getVideoHeight();
        applyCenterCrop();
        applyMuteState();
        this.seekVideoProgress.setMax(mediaPlayer.getDuration());
        this.textTotalTime.setText(formatTime(mediaPlayer.getDuration()));
        mediaPlayer.start();
        updatePlayPauseIcon();
        startProgressUpdates();
        scheduleAutoHideControls();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$preparePlayer$5(MediaPlayer mediaPlayer, int i7, int i8) {
        this.videoWidth = i7;
        this.videoHeight = i8;
        applyCenterCrop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$preparePlayer$6(MediaPlayer mediaPlayer) {
        updatePlayPauseIcon();
        stopProgressUpdates();
        SeekBar seekBar = this.seekVideoProgress;
        seekBar.setProgress(seekBar.getMax());
        this.textCurrentTime.setText(formatTime(mediaPlayer.getDuration()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$preparePlayer$7(MediaPlayer mediaPlayer, int i7, int i8) {
        ToastHelper.show(this, "视频播放失败");
        finish();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$requestAudioFocus$8(int i7) {
        MediaPlayer mediaPlayer;
        if (i7 > 0 || (mediaPlayer = this.mediaPlayer) == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mediaPlayer.pause();
        updatePlayPauseIcon();
        stopProgressUpdates();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preparePlayer() {
        String str;
        if (this.videoSurface == null || (str = this.videoPath) == null || str.trim().isEmpty()) {
            return;
        }
        releasePlayer();
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mediaPlayer = mediaPlayer;
            mediaPlayer.setDataSource(this.videoPath);
            this.mediaPlayer.setSurface(this.videoSurface);
            this.mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.navi.tool.ui.extra.n4
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    FileVideoPreviewActivity.this.lambda$preparePlayer$4(mediaPlayer2);
                }
            });
            this.mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.navi.tool.ui.extra.o4
                @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                public final void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i7, int i8) {
                    FileVideoPreviewActivity.this.lambda$preparePlayer$5(mediaPlayer2, i7, i8);
                }
            });
            this.mediaPlayer.setOnCompletionListener(new h0(this, 1));
            this.mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.navi.tool.ui.extra.p4
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer2, int i7, int i8) {
                    boolean lambda$preparePlayer$7;
                    lambda$preparePlayer$7 = FileVideoPreviewActivity.this.lambda$preparePlayer$7(mediaPlayer2, i7, i8);
                    return lambda$preparePlayer$7;
                }
            });
            this.mediaPlayer.prepareAsync();
        } catch (Exception unused) {
            ToastHelper.show(this, "视频播放失败");
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releasePlayer() {
        this.isPrepared = false;
        stopProgressUpdates();
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
            } catch (Exception unused) {
            }
            try {
                this.mediaPlayer.release();
            } catch (Exception unused2) {
            }
            this.mediaPlayer = null;
        }
    }

    private void requestAudioFocus() {
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.audioManager = audioManager;
        if (audioManager == null) {
            return;
        }
        AudioFocusRequest build = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build()).setAcceptsDelayedFocusGain(false).setOnAudioFocusChangeListener(new AudioManager.OnAudioFocusChangeListener() { // from class: com.navi.tool.ui.extra.q4
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i7) {
                FileVideoPreviewActivity.this.lambda$requestAudioFocus$8(i7);
            }
        }).build();
        this.audioFocusRequest = build;
        this.audioManager.requestAudioFocus(build);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleAutoHideControls() {
        this.progressHandler.removeCallbacks(this.hideControlsRunnable);
        this.progressHandler.postDelayed(this.hideControlsRunnable, 3000L);
    }

    private void showControls() {
        this.controlsVisible = true;
        View view = this.topBar;
        if (view != null) {
            view.setVisibility(0);
        }
        View view2 = this.controlsBar;
        if (view2 != null) {
            view2.setVisibility(0);
        }
    }

    private void startProgressUpdates() {
        stopProgressUpdates();
        Runnable runnable = new Runnable() { // from class: com.navi.tool.ui.extra.FileVideoPreviewActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (!FileVideoPreviewActivity.this.isPrepared || FileVideoPreviewActivity.this.mediaPlayer == null) {
                    return;
                }
                int currentPosition = FileVideoPreviewActivity.this.mediaPlayer.getCurrentPosition();
                if (!FileVideoPreviewActivity.this.isSeeking) {
                    FileVideoPreviewActivity.this.seekVideoProgress.setProgress(currentPosition);
                    FileVideoPreviewActivity.this.textCurrentTime.setText(FileVideoPreviewActivity.this.formatTime(currentPosition));
                }
                FileVideoPreviewActivity.this.progressHandler.postDelayed(this, 500L);
            }
        };
        this.progressRunnable = runnable;
        this.progressHandler.post(runnable);
    }

    private void stopProgressUpdates() {
        Runnable runnable = this.progressRunnable;
        if (runnable != null) {
            this.progressHandler.removeCallbacks(runnable);
            this.progressRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleControlsVisibility() {
        if (this.controlsVisible) {
            hideControls();
        } else {
            showControls();
            scheduleAutoHideControls();
        }
    }

    private void toggleMute() {
        this.isMuted = !this.isMuted;
        applyMuteState();
        updateMuteIcon();
        keepControlsVisible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void togglePlayback() {
        MediaPlayer mediaPlayer;
        if (!this.isPrepared || (mediaPlayer = this.mediaPlayer) == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            stopProgressUpdates();
        } else {
            this.mediaPlayer.start();
            startProgressUpdates();
        }
        updatePlayPauseIcon();
        keepControlsVisible();
    }

    private void updateMuteIcon() {
        this.btnMute.setImageResource(this.isMuted ? R.drawable.ic_lock_silent_mode : R.drawable.ic_lock_silent_mode_off);
    }

    private void updatePlayPauseIcon() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        this.btnPlayPause.setImageResource(mediaPlayer != null && mediaPlayer.isPlaying() ? R.drawable.ic_media_pause : R.drawable.ic_media_play);
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(g4.q.activity_file_video_preview);
        hideSystemUi();
        if (getSupportActionBar() != null) {
            getSupportActionBar().f();
        }
        this.hideControlsRunnable = new f4(8, this);
        this.gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.extra.FileVideoPreviewActivity.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                FileVideoPreviewActivity.this.togglePlayback();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                FileVideoPreviewActivity.this.toggleControlsVisibility();
                return true;
            }
        });
        String stringExtra = getIntent().getStringExtra(EXTRA_VIDEO_PATH);
        this.videoPath = stringExtra;
        if (stringExtra == null || stringExtra.trim().isEmpty()) {
            finish();
            return;
        }
        File file = new File(this.videoPath);
        if (!file.exists() || !file.isFile()) {
            ToastHelper.show(this, "视频文件不存在");
            finish();
        } else {
            initViews();
            this.textVideoTitle.setText(file.getName());
            requestAudioFocus();
            this.textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.navi.tool.ui.extra.FileVideoPreviewActivity.2
                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i7, int i8) {
                    FileVideoPreviewActivity.this.videoSurface = new Surface(surfaceTexture);
                    FileVideoPreviewActivity.this.preparePlayer();
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    FileVideoPreviewActivity.this.releasePlayer();
                    if (FileVideoPreviewActivity.this.videoSurface == null) {
                        return true;
                    }
                    FileVideoPreviewActivity.this.videoSurface.release();
                    FileVideoPreviewActivity.this.videoSurface = null;
                    return true;
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i7, int i8) {
                    FileVideoPreviewActivity.this.applyCenterCrop();
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }
            });
        }
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        stopProgressUpdates();
        releasePlayer();
        Surface surface = this.videoSurface;
        if (surface != null) {
            surface.release();
            this.videoSurface = null;
        }
        abandonAudioFocus();
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onPause() {
        super.onPause();
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
        }
        updatePlayPauseIcon();
        stopProgressUpdates();
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onResume() {
        MediaPlayer mediaPlayer;
        super.onResume();
        hideSystemUi();
        if (!this.isPrepared || (mediaPlayer = this.mediaPlayer) == null || mediaPlayer.isPlaying()) {
            return;
        }
        this.mediaPlayer.start();
        updatePlayPauseIcon();
        startProgressUpdates();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        if (z6) {
            hideSystemUi();
        }
    }
}
