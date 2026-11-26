package com.navi.tool.utils;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.File;
import java.util.Locale;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class AudioPlayerHelper {
    private final Handler audioHandler = new Handler(Looper.getMainLooper());
    private final AudioManager audioManager;
    private Runnable audioUpdateRunnable;
    private final Context context;
    private AudioManager.OnAudioFocusChangeListener focusChangeListener;
    private MediaPlayer mediaPlayer;

    public AudioPlayerHelper(Context context) {
        this.context = context;
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatTime(int i7) {
        int i8 = i7 / 1000;
        return String.format(Locale.getDefault(), "%02d:%02d", Integer.valueOf(i8 / 60), Integer.valueOf(i8 % 60));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$playAudio$0(DialogInterface dialogInterface, int i7) {
        stopAudio();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$playAudio$1(DialogInterface dialogInterface) {
        stopAudio();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$playAudio$2(ImageButton imageButton, View view) {
        if (this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            imageButton.setImageResource(R.drawable.ic_media_play);
            this.audioManager.abandonAudioFocus(this.focusChangeListener);
        } else if (this.audioManager.requestAudioFocus(this.focusChangeListener, 3, 1) != 1) {
            ToastHelper.show(this.context, "无法获取音频焦点");
        } else {
            this.mediaPlayer.start();
            imageButton.setImageResource(R.drawable.ic_media_pause);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$playAudio$3(ImageButton imageButton, SeekBar seekBar, TextView textView, MediaPlayer mediaPlayer) {
        imageButton.setImageResource(R.drawable.ic_media_play);
        seekBar.setProgress(0);
        textView.setText("00:00");
        this.audioManager.abandonAudioFocus(this.focusChangeListener);
    }

    public void playAudio(File file) {
        stopAudio();
        this.focusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.navi.tool.utils.AudioPlayerHelper.1
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i7) {
                if (i7 == -1) {
                    if (AudioPlayerHelper.this.mediaPlayer == null || !AudioPlayerHelper.this.mediaPlayer.isPlaying()) {
                        return;
                    }
                    AudioPlayerHelper.this.mediaPlayer.pause();
                    return;
                }
                if (i7 == -2) {
                    if (AudioPlayerHelper.this.mediaPlayer == null || !AudioPlayerHelper.this.mediaPlayer.isPlaying()) {
                        return;
                    }
                    AudioPlayerHelper.this.mediaPlayer.pause();
                    return;
                }
                if (i7 != 1 || AudioPlayerHelper.this.mediaPlayer == null || AudioPlayerHelper.this.mediaPlayer.isPlaying()) {
                    return;
                }
                AudioPlayerHelper.this.mediaPlayer.start();
            }
        };
        View inflate = LayoutInflater.from(this.context).inflate(g4.q.dialog_audio_player, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(g4.p.tv_title);
        final TextView textView2 = (TextView) inflate.findViewById(g4.p.tv_current_time);
        TextView textView3 = (TextView) inflate.findViewById(g4.p.tv_total_time);
        final SeekBar seekBar = (SeekBar) inflate.findViewById(g4.p.seek_bar);
        final ImageButton imageButton = (ImageButton) inflate.findViewById(g4.p.btn_play_pause);
        textView.setText("正在播放: " + file.getName());
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.context);
        nVar.u(inflate);
        nVar.o("关闭", new e(2, this));
        ((androidx.appcompat.app.j) nVar.f831c).f758n = new DialogInterface.OnDismissListener() { // from class: com.navi.tool.utils.b
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                AudioPlayerHelper.this.lambda$playAudio$1(dialogInterface);
            }
        };
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mediaPlayer = mediaPlayer;
            mediaPlayer.setDataSource(file.getAbsolutePath());
            this.mediaPlayer.prepare();
            if (this.audioManager.requestAudioFocus(this.focusChangeListener, 3, 1) == 1) {
                this.mediaPlayer.start();
                imageButton.setImageResource(R.drawable.ic_media_pause);
            } else {
                ToastHelper.show(this.context, "无法获取音频焦点，播放失败");
            }
            textView3.setText(formatTime(this.mediaPlayer.getDuration()));
            seekBar.setMax(this.mediaPlayer.getDuration());
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.utils.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudioPlayerHelper.this.lambda$playAudio$2(imageButton, view);
                }
            });
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.utils.AudioPlayerHelper.2
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar2, int i7, boolean z6) {
                    if (z6) {
                        AudioPlayerHelper.this.mediaPlayer.seekTo(i7);
                        textView2.setText(AudioPlayerHelper.this.formatTime(i7));
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar2) {
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar2) {
                }
            });
            Runnable runnable = new Runnable() { // from class: com.navi.tool.utils.AudioPlayerHelper.3
                @Override // java.lang.Runnable
                public void run() {
                    if (AudioPlayerHelper.this.mediaPlayer != null && AudioPlayerHelper.this.mediaPlayer.isPlaying()) {
                        int currentPosition = AudioPlayerHelper.this.mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        textView2.setText(AudioPlayerHelper.this.formatTime(currentPosition));
                    }
                    AudioPlayerHelper.this.audioHandler.postDelayed(this, 1000L);
                }
            };
            this.audioUpdateRunnable = runnable;
            this.audioHandler.post(runnable);
            this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.navi.tool.utils.d
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    AudioPlayerHelper.this.lambda$playAudio$3(imageButton, seekBar, textView2, mediaPlayer2);
                }
            });
        } catch (Exception e8) {
            e8.printStackTrace();
            ToastHelper.show(this.context, "播放失败: " + e8.getMessage());
            e7.dismiss();
        }
    }

    public void stopAudio() {
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
        Runnable runnable;
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer = null;
        }
        Handler handler = this.audioHandler;
        if (handler != null && (runnable = this.audioUpdateRunnable) != null) {
            handler.removeCallbacks(runnable);
        }
        AudioManager audioManager = this.audioManager;
        if (audioManager == null || (onAudioFocusChangeListener = this.focusChangeListener) == null) {
            return;
        }
        audioManager.abandonAudioFocus(onAudioFocusChangeListener);
    }
}
