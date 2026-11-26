package com.navi.tool.service;

import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.view.SpectrumView;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class MusicIslandService extends Service {
    private static final String ACTION_MUSIC_APP_UPDATED = "com.navi.tool.ACTION_MUSIC_APP_UPDATED";
    private static final String ACTION_MUSIC_UI_UPDATED = "com.navi.tool.ACTION_MUSIC_UI_UPDATED";
    public static final String ACTION_SET_DESKTOP_ASSISTANT_VISIBLE = "com.navi.tool.action.MUSIC_ISLAND_SET_DESKTOP_VISIBLE";
    public static final String ACTION_SYNC = "com.navi.tool.action.MUSIC_ISLAND_SYNC";
    private static final String CACHE_DIR_NAME = "navitool/.music";
    private static final String CACHE_FILE_COVER = "cover.png";
    private static final String CACHE_FILE_LYRICS = "lyrics.json";
    private static final String CACHE_FILE_META = "meta.json";
    private static final long DEFERRED_REFRESH_DELAY_MS = 250;
    public static final String EXTRA_DESKTOP_ASSISTANT_VISIBLE = "desktop_assistant_visible";
    private static final String PREF_ENABLE = "desktop_music_island_enabled";
    private static final String PREF_POS_X = "desktop_music_island_pos_x";
    private static final String PREF_POS_Y = "desktop_music_island_pos_y";
    private TextView artistLargeView;
    private LinearLayout capsuleView;
    private CardView coverCard;
    private CardView coverLargeCard;
    private ObjectAnimator coverLargeRotationAnimator;
    private ImageView coverLargeView;
    private ObjectAnimator coverRotationAnimator;
    private ImageView coverView;
    private TextView currentDurationLargeView;
    private TextView currentLyricLargeView;
    private int dragTouchSlop;
    private LinearLayout expandedCard;
    private TextView lyricView;
    private Handler mainHandler;
    private ImageButton nextButtonView;
    private TextView nextLyricView;
    private WindowManager.LayoutParams overlayParams;
    private View overlayView;
    private ImageButton playPauseLargeView;
    private ImageButton playPauseView;
    private SharedPreferences prefs;
    private ImageButton prevButtonView;
    private TextView prevLyricView;
    private View progressContainerView;
    private View progressLargeView;
    private View progressTrackLargeView;
    private ImageView sourceIconView;
    private ImageView sourceLargeIconView;
    private TextView sourceNameView;
    private SpectrumView spectrumView;
    private TextView titleLargeView;
    private TextView titleView;
    private TextView totalDurationLargeView;
    private float touchStartRawX;
    private float touchStartRawY;
    private BroadcastReceiver updateReceiver;
    private int viewStartX;
    private int viewStartY;
    private WindowManager windowManager;
    private boolean overlayAttached = false;
    private boolean assistantVisible = false;
    private final Runnable deferredRefreshRunnable = new g0(this, 3);
    private boolean isPlaying = false;
    private boolean isExpanded = false;
    private boolean dragEnabled = false;
    private boolean dragging = false;
    private boolean isDraggingProgress = false;
    private int currentSource = 0;
    private int startedMusicSource = -1;
    private long currentPositionMs = 0;
    private long totalDurationMs = 0;
    private String currentTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private String currentArtist = s5.h0.FRAGMENT_ENCODE_SET;
    private String currentLyric = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastKnownTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastLoadingTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private String lastProgressTitle = s5.h0.FRAGMENT_ENCODE_SET;
    private long lastKnownTotalDuration = 0;
    private TreeMap<Long, String> lyricsMap = new TreeMap<>();

    public static class MusicMeta {
        String artist;
        String title;

        private MusicMeta() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyAlbumArtUi() {
        float f7 = getResources().getDisplayMetrics().density;
        this.coverCard.setVisibility(0);
        this.coverLargeCard.setVisibility(0);
        this.coverCard.setRadius(23.0f * f7);
        this.coverLargeCard.setRadius(f7 * 31.0f);
        this.coverCard.setClipToOutline(true);
        this.coverLargeCard.setClipToOutline(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: applyCachedSnapshot, reason: merged with bridge method [inline-methods] */
    public void lambda$loadCachedMusicSnapshot$7(boolean z6, MusicMeta musicMeta, TreeMap<Long, String> treeMap, Bitmap bitmap) {
        Map.Entry<Long, String> firstEntry;
        boolean isCachedLyricsMatchCurrentTrack = isCachedLyricsMatchCurrentTrack(musicMeta);
        if (z6 && musicMeta != null && isCachedLyricsMatchCurrentTrack) {
            if (TextUtils.isEmpty(this.currentTitle) && !TextUtils.isEmpty(musicMeta.title)) {
                this.currentTitle = musicMeta.title;
            }
            if (TextUtils.isEmpty(this.currentArtist) && !TextUtils.isEmpty(musicMeta.artist)) {
                this.currentArtist = musicMeta.artist;
            }
        }
        TreeMap<Long, String> treeMap2 = this.lyricsMap;
        if ((treeMap2 == null || treeMap2.isEmpty()) && isCachedLyricsMatchCurrentTrack && treeMap != null && !treeMap.isEmpty()) {
            this.lyricsMap.clear();
            this.lyricsMap.putAll(treeMap);
            if (TextUtils.isEmpty(this.currentLyric) && (firstEntry = this.lyricsMap.firstEntry()) != null) {
                this.currentLyric = safeLyricLine(firstEntry.getValue());
            }
        }
        if (bitmap != null) {
            lambda$onAlbumArtUpdateEvent$9(bitmap);
        }
        updateTexts();
        updateProgressUi();
        updateExpandedLyrics();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: applyCoverBitmap, reason: merged with bridge method [inline-methods] */
    public void lambda$onAlbumArtUpdateEvent$9(Bitmap bitmap) {
        DesktopAssistantMusicUiHelper.applyCoverBitmap(bitmap, this.coverView);
        DesktopAssistantMusicUiHelper.applyCoverBitmap(bitmap, this.coverLargeView);
    }

    private void applyPlaybackState(boolean z6) {
        this.isPlaying = z6;
        this.playPauseView.setImageResource(z6 ? g4.o.ic_pause : g4.o.ic_play);
        this.playPauseLargeView.setImageResource(z6 ? g4.o.ic_pause : g4.o.ic_play);
        if (!this.prefs.getBoolean("desktop_music_spectrum_enabled", true)) {
            this.spectrumView.f();
        } else if (z6) {
            this.spectrumView.e();
        } else {
            this.spectrumView.f();
        }
        if (z6) {
            startCoverRotation();
        } else {
            stopCoverRotation();
        }
    }

    private void applyProgressPreview(long j7) {
        View view = this.progressLargeView;
        TextView textView = this.currentDurationLargeView;
        TextView textView2 = this.totalDurationLargeView;
        long j8 = this.lastKnownTotalDuration;
        DesktopAssistantMusicUiHelper.updateProgressBarAndDuration(view, textView, textView2, j8 > 0 ? j8 : this.totalDurationMs, j7, this.currentTitle, this.lastProgressTitle, j8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applySpectrumPreference() {
        boolean z6 = this.prefs.getBoolean("desktop_music_spectrum_enabled", true);
        boolean z7 = this.prefs.getBoolean("desktop_music_spectrum_colorful_enabled", true);
        this.spectrumView.setVisibility(z6 ? 0 : 8);
        this.spectrumView.setUseGradient(z7);
        if (!z6) {
            this.spectrumView.f();
        } else if (this.isPlaying) {
            this.spectrumView.e();
        } else {
            this.spectrumView.f();
        }
    }

    private ObjectAnimator buildRotationAnimator(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ROTATION, CropImageView.DEFAULT_ASPECT_RATIO, 360.0f);
        ofFloat.setDuration(20000L);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(new LinearInterpolator());
        return ofFloat;
    }

    private boolean canDragProgress() {
        return this.isExpanded && DesktopAssistantMusicUiHelper.isSeekSupportedSource(this.currentSource) && (this.lastKnownTotalDuration > 0 || this.totalDurationMs > 0);
    }

    private void clampOverlayPosition() {
        int i7 = getResources().getDisplayMetrics().widthPixels;
        int i8 = getResources().getDisplayMetrics().heightPixels;
        View view = this.overlayView;
        int dp = (view == null || view.getWidth() <= 0) ? dp(340) : this.overlayView.getWidth();
        View view2 = this.overlayView;
        int dp2 = (view2 == null || view2.getHeight() <= 0) ? dp(64) : this.overlayView.getHeight();
        WindowManager.LayoutParams layoutParams = this.overlayParams;
        layoutParams.x = clampX(layoutParams.x, i7, dp);
        WindowManager.LayoutParams layoutParams2 = this.overlayParams;
        layoutParams2.y = clampY(layoutParams2.y, i8, dp2);
    }

    private int clampX(int i7, int i8, int i9) {
        return Math.max(0, Math.min(i7, i8 - i9));
    }

    private int clampY(int i7, int i8, int i9) {
        return Math.max(0, Math.min(i7, i8 - i9));
    }

    private void dispatchMusicControl(int i7) {
        DesktopAssistantMusicUiHelper.dispatchMusicControl(this, this.prefs, this.isPlaying, i7);
    }

    private int dp(int i7) {
        return Math.round(i7 * getResources().getDisplayMetrics().density);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureMusicSourceStarted() {
        if (this.prefs.getBoolean(PREF_ENABLE, false) && this.prefs.getBoolean("desktop_music_enabled", false) && this.startedMusicSource != this.currentSource) {
            DesktopAssistantMusicUiHelper.stopMusicManagers(this);
            DesktopAssistantMusicUiHelper.startMusicManagerBySource(this, this.currentSource, null);
            int i7 = this.currentSource;
            this.startedMusicSource = i7;
            DesktopAssistantMusicUiHelper.refreshMusicStateBySource(this, i7);
        }
    }

    private void ensureOverlayAttached() {
        View view;
        WindowManager windowManager;
        syncOverlayAttachmentState();
        if (this.overlayAttached || (view = this.overlayView) == null || (windowManager = this.windowManager) == null) {
            return;
        }
        try {
            windowManager.addView(view, this.overlayParams);
            this.overlayAttached = true;
        } catch (Exception unused) {
            this.overlayAttached = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void evaluateOverlayState() {
        syncOverlayAttachmentState();
        boolean z6 = this.prefs.getBoolean(PREF_ENABLE, false);
        boolean z7 = this.prefs.getBoolean("desktop_music_enabled", false);
        if (!z6 || !z7) {
            removeOverlay();
            stopSelf();
            return;
        }
        if (!Settings.canDrawOverlays(this) || this.assistantVisible) {
            removeOverlay();
            return;
        }
        ensureOverlayAttached();
        applySpectrumPreference();
        applyAlbumArtUi();
        updateTexts();
        updateProgressUi();
        updateExpandedLyrics();
        updateSourceIcon();
    }

    private void handleMusicControlClick(int i7) {
        refreshOverlayForInteraction();
        dispatchMusicControl(i7);
        scheduleDeferredRefresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleProgressTouch(View view, MotionEvent motionEvent) {
        if (motionEvent == null || !canDragProgress()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.isDraggingProgress = true;
            view.getParent().requestDisallowInterceptTouchEvent(true);
            previewProgressSeek(motionEvent.getX(), view.getWidth());
            return true;
        }
        if (actionMasked == 1) {
            long previewProgressSeek = previewProgressSeek(motionEvent.getX(), view.getWidth());
            this.isDraggingProgress = false;
            DesktopAssistantMusicUiHelper.dispatchMusicSeek(this, this.prefs, this.currentPositionMs, previewProgressSeek);
            this.currentPositionMs = previewProgressSeek;
            return true;
        }
        if (actionMasked == 2) {
            previewProgressSeek(motionEvent.getX(), view.getWidth());
            return true;
        }
        if (actionMasked != 3) {
            return false;
        }
        this.isDraggingProgress = false;
        applyProgressPreview(this.currentPositionMs);
        return true;
    }

    private void initOverlay() {
        View inflate = LayoutInflater.from(this).inflate(g4.q.view_music_island_overlay, (ViewGroup) null);
        this.overlayView = inflate;
        this.capsuleView = (LinearLayout) inflate.findViewById(g4.p.music_island_capsule);
        this.expandedCard = (LinearLayout) this.overlayView.findViewById(g4.p.music_island_expanded_card);
        this.coverCard = (CardView) this.overlayView.findViewById(g4.p.card_music_island_cover);
        this.coverLargeCard = (CardView) this.overlayView.findViewById(g4.p.card_music_island_cover_large);
        this.coverView = (ImageView) this.overlayView.findViewById(g4.p.img_music_island_cover);
        this.coverLargeView = (ImageView) this.overlayView.findViewById(g4.p.img_music_island_cover_large);
        this.sourceIconView = (ImageView) this.overlayView.findViewById(g4.p.img_music_island_source);
        this.sourceLargeIconView = (ImageView) this.overlayView.findViewById(g4.p.img_music_island_source_large);
        this.titleView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_title);
        this.lyricView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_lyric);
        this.titleLargeView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_title_large);
        this.artistLargeView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_artist_large);
        this.sourceNameView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_source_name);
        this.currentDurationLargeView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_current_duration);
        this.totalDurationLargeView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_total_duration);
        this.prevLyricView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_prev_lyric);
        this.currentLyricLargeView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_current_lyric_large);
        this.nextLyricView = (TextView) this.overlayView.findViewById(g4.p.text_music_island_next_lyric);
        this.playPauseView = (ImageButton) this.overlayView.findViewById(g4.p.btn_music_island_play_pause);
        this.playPauseLargeView = (ImageButton) this.overlayView.findViewById(g4.p.btn_music_island_play_pause_large);
        this.prevButtonView = (ImageButton) this.overlayView.findViewById(g4.p.btn_music_island_prev);
        this.nextButtonView = (ImageButton) this.overlayView.findViewById(g4.p.btn_music_island_next);
        this.progressLargeView = this.overlayView.findViewById(g4.p.view_music_island_progress);
        this.progressTrackLargeView = this.overlayView.findViewById(g4.p.view_music_island_progress_track);
        this.progressContainerView = this.overlayView.findViewById(g4.p.layout_music_island_progress_container);
        this.spectrumView = (SpectrumView) this.overlayView.findViewById(g4.p.view_music_island_spectrum);
        setupWindowParams();
        setupGestureAndTouch();
        setupControls();
        applySpectrumPreference();
        applyAlbumArtUi();
        updateSourceIcon();
        applyPlaybackState(false);
        updateTexts();
        updateProgressUi();
        updateExpandedLyrics();
        this.titleView.setSelected(true);
        this.lyricView.setSelected(true);
        this.titleLargeView.setSelected(true);
    }

    private boolean isCachedLyricsMatchCurrentTrack(MusicMeta musicMeta) {
        return musicMeta != null && TextUtils.equals(normalizeMusicText(musicMeta.title), normalizeMusicText(this.currentTitle)) && TextUtils.equals(normalizeMusicText(musicMeta.artist), normalizeMusicText(this.currentArtist));
    }

    private boolean isTouchInsideView(MotionEvent motionEvent, View view, int i7) {
        if (motionEvent == null || view == null || view.getVisibility() != 0) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        int i8 = iArr[0];
        if (rawX < i8 - i7 || rawX > view.getWidth() + i8 + i7) {
            return false;
        }
        int i9 = iArr[1];
        return rawY >= ((float) (i9 - i7)) && rawY <= ((float) ((view.getHeight() + i9) + i7));
    }

    private boolean isTouchOnInteractiveCapsuleChild(MotionEvent motionEvent) {
        int dp = dp(10);
        return isTouchInsideView(motionEvent, this.playPauseView, dp) || isTouchInsideView(motionEvent, this.sourceIconView, dp) || isTouchInsideView(motionEvent, this.coverView, dp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCachedMusicSnapshot$8(final boolean z6) {
        final MusicMeta musicMeta;
        final TreeMap treeMap;
        BufferedReader bufferedReader;
        try {
            File file = new File(Environment.getExternalStorageDirectory(), CACHE_DIR_NAME);
            if (file.exists()) {
                File file2 = new File(file, CACHE_FILE_META);
                if (file2.exists()) {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                    try {
                        MusicMeta musicMeta2 = (MusicMeta) new Gson().fromJson((Reader) bufferedReader, MusicMeta.class);
                        bufferedReader.close();
                        musicMeta = musicMeta2;
                    } finally {
                    }
                } else {
                    musicMeta = null;
                }
                File file3 = new File(file, CACHE_FILE_LYRICS);
                if (file3.exists()) {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
                    try {
                        TreeMap treeMap2 = (TreeMap) new Gson().fromJson(bufferedReader, new TypeToken<TreeMap<Long, String>>() { // from class: com.navi.tool.service.MusicIslandService.2
                        }.getType());
                        bufferedReader.close();
                        treeMap = treeMap2;
                    } finally {
                    }
                } else {
                    treeMap = null;
                }
                File file4 = new File(file, CACHE_FILE_COVER);
                final Bitmap decodeFile = file4.exists() ? BitmapFactory.decodeFile(file4.getAbsolutePath()) : null;
                this.mainHandler.post(new Runnable() { // from class: com.navi.tool.service.h0
                    @Override // java.lang.Runnable
                    public final void run() {
                        MusicIslandService.this.lambda$loadCachedMusicSnapshot$7(z6, musicMeta, treeMap, decodeFile);
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAlbumArtUpdateEvent$10(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.mainHandler.post(new l0(6, this, bitmap));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupControls$1(View view) {
        handleMusicControlClick(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupControls$2(View view) {
        handleMusicControlClick(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupControls$3(View view) {
        handleMusicControlClick(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupControls$4(View view) {
        handleMusicControlClick(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupControls$5(View view) {
        refreshOverlayForInteraction();
        launchCurrentMusicApp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0015, code lost:
    
        if (r7 != 3) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ boolean lambda$setupGestureAndTouch$0(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            boolean r7 = r6.isTouchOnInteractiveCapsuleChild(r8)
            r0 = 0
            if (r7 == 0) goto L8
            return r0
        L8:
            int r7 = r8.getActionMasked()
            r1 = 1
            if (r7 == 0) goto Lce
            if (r7 == r1) goto L90
            r2 = 2
            if (r7 == r2) goto L19
            r2 = 3
            if (r7 == r2) goto L90
            goto Leb
        L19:
            boolean r7 = r6.dragEnabled
            if (r7 == 0) goto Leb
            boolean r7 = r6.overlayAttached
            if (r7 == 0) goto Leb
            float r7 = r8.getRawX()
            float r0 = r6.touchStartRawX
            float r7 = r7 - r0
            float r0 = r8.getRawY()
            float r2 = r6.touchStartRawY
            float r0 = r0 - r2
            boolean r2 = r6.dragging
            if (r2 != 0) goto L65
            double r2 = (double) r7
            double r4 = (double) r0
            double r2 = java.lang.Math.hypot(r2, r4)
            int r7 = r6.dragTouchSlop
            double r4 = (double) r7
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 >= 0) goto L42
            goto Leb
        L42:
            r6.dragging = r1
            android.widget.LinearLayout r7 = r6.capsuleView
            android.view.ViewPropertyAnimator r7 = r7.animate()
            r0 = 1065604874(0x3f83d70a, float:1.03)
            android.view.ViewPropertyAnimator r7 = r7.scaleX(r0)
            android.view.ViewPropertyAnimator r7 = r7.scaleY(r0)
            r0 = 1064682127(0x3f75c28f, float:0.96)
            android.view.ViewPropertyAnimator r7 = r7.alpha(r0)
            r2 = 120(0x78, double:5.93E-322)
            android.view.ViewPropertyAnimator r7 = r7.setDuration(r2)
            r7.start()
        L65:
            android.view.WindowManager$LayoutParams r7 = r6.overlayParams
            int r0 = r6.viewStartX
            float r2 = r8.getRawX()
            float r3 = r6.touchStartRawX
            float r2 = r2 - r3
            int r2 = java.lang.Math.round(r2)
            int r2 = r2 + r0
            r7.x = r2
            android.view.WindowManager$LayoutParams r7 = r6.overlayParams
            int r0 = r6.viewStartY
            float r8 = r8.getRawY()
            float r2 = r6.touchStartRawY
            float r8 = r8 - r2
            int r8 = java.lang.Math.round(r8)
            int r8 = r8 + r0
            r7.y = r8
            r6.clampOverlayPosition()
            r6.updateOverlayLayout()
            goto Leb
        L90:
            boolean r7 = r6.dragging
            boolean r2 = r6.dragEnabled
            if (r2 == 0) goto L9b
            if (r7 == 0) goto L9b
            r6.persistPosition()
        L9b:
            r6.dragEnabled = r0
            r6.dragging = r0
            android.widget.LinearLayout r0 = r6.capsuleView
            android.view.ViewPropertyAnimator r0 = r0.animate()
            r2 = 1065353216(0x3f800000, float:1.0)
            android.view.ViewPropertyAnimator r0 = r0.scaleX(r2)
            android.view.ViewPropertyAnimator r0 = r0.scaleY(r2)
            android.view.ViewPropertyAnimator r0 = r0.alpha(r2)
            r2 = 140(0x8c, double:6.9E-322)
            android.view.ViewPropertyAnimator r0 = r0.setDuration(r2)
            r0.start()
            int r8 = r8.getActionMasked()
            if (r8 != r1) goto Leb
            if (r7 != 0) goto Leb
            r6.refreshOverlayForInteraction()
            boolean r7 = r6.isExpanded
            r7 = r7 ^ r1
            r6.showExpandedCard(r7)
            goto Leb
        Lce:
            r6.refreshOverlayForInteraction()
            float r7 = r8.getRawX()
            r6.touchStartRawX = r7
            float r7 = r8.getRawY()
            r6.touchStartRawY = r7
            r6.dragEnabled = r1
            android.view.WindowManager$LayoutParams r7 = r6.overlayParams
            int r8 = r7.x
            r6.viewStartX = r8
            int r7 = r7.y
            r6.viewStartY = r7
            r6.dragging = r0
        Leb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.service.MusicIslandService.lambda$setupGestureAndTouch$0(android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showExpandedCard$6() {
        this.expandedCard.setVisibility(8);
        this.expandedCard.setAlpha(1.0f);
        this.expandedCard.setScaleX(1.0f);
        this.expandedCard.setScaleY(1.0f);
        this.expandedCard.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
    }

    private void launchCurrentMusicApp() {
        String resolveCurrentMusicPackage = resolveCurrentMusicPackage();
        if (TextUtils.isEmpty(resolveCurrentMusicPackage)) {
            return;
        }
        try {
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(resolveCurrentMusicPackage);
            if (launchIntentForPackage != null) {
                launchIntentForPackage.addFlags(268435456);
                startActivity(launchIntentForPackage);
            }
        } catch (Exception unused) {
        }
    }

    private void loadCachedMusicSnapshot(boolean z6) {
        new Thread(new com.google.android.material.internal.g0(this, z6, 4)).start();
    }

    private String normalizeMusicText(String str) {
        return str == null ? s5.h0.FRAGMENT_ENCODE_SET : str.trim();
    }

    public static void notifyDesktopAssistantVisibility(Context context, boolean z6) {
        Intent intent = new Intent(context, (Class<?>) MusicIslandService.class);
        intent.setAction(ACTION_SET_DESKTOP_ASSISTANT_VISIBLE);
        intent.putExtra(EXTRA_DESKTOP_ASSISTANT_VISIBLE, z6);
        startServiceCompat(context, intent);
    }

    private void persistPosition() {
        this.prefs.edit().putInt(PREF_POS_X, this.overlayParams.x).putInt(PREF_POS_Y, this.overlayParams.y).apply();
    }

    private long previewProgressSeek(float f7, int i7) {
        long j7 = this.lastKnownTotalDuration;
        if (j7 <= 0) {
            j7 = this.totalDurationMs;
        }
        if (j7 <= 0 || i7 <= 0) {
            return 0L;
        }
        long max = (long) (j7 * Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(1.0f, f7 / i7)));
        applyProgressPreview(max);
        return max;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshOverlayForInteraction() {
        refreshSourceState();
        ensureMusicSourceStarted();
        syncOverlayAttachmentState();
        if (this.prefs.getBoolean(PREF_ENABLE, false) && this.prefs.getBoolean("desktop_music_enabled", false) && !this.assistantVisible && Settings.canDrawOverlays(this)) {
            ensureOverlayAttached();
            clampOverlayPosition();
            applySpectrumPreference();
            applyAlbumArtUi();
            updateTexts();
            updateProgressUi();
            updateExpandedLyrics();
            updateSourceIcon();
            updateOverlayLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSourceState() {
        this.currentSource = this.prefs.getInt("desktop_music_source", 0);
    }

    private void registerUpdateReceiver() {
        this.updateReceiver = new BroadcastReceiver() { // from class: com.navi.tool.service.MusicIslandService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                MusicIslandService.this.refreshSourceState();
                MusicIslandService.this.ensureMusicSourceStarted();
                MusicIslandService.this.applySpectrumPreference();
                MusicIslandService.this.applyAlbumArtUi();
                MusicIslandService.this.updateSourceIcon();
                MusicIslandService.this.evaluateOverlayState();
                MusicIslandService.this.scheduleDeferredRefresh();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.navi.tool.ACTION_MUSIC_UI_UPDATED");
        intentFilter.addAction("com.navi.tool.ACTION_MUSIC_APP_UPDATED");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.updateReceiver, intentFilter, 4);
        } else {
            registerReceiver(this.updateReceiver, intentFilter);
        }
    }

    private void removeOverlay() {
        View view;
        WindowManager windowManager;
        SpectrumView spectrumView = this.spectrumView;
        if (spectrumView != null) {
            spectrumView.f();
        }
        syncOverlayAttachmentState();
        if (!this.overlayAttached || (view = this.overlayView) == null || (windowManager = this.windowManager) == null) {
            return;
        }
        try {
            windowManager.removeView(view);
        } catch (Exception unused) {
        }
        this.overlayAttached = false;
    }

    private String resolveCurrentMusicPackage() {
        String resolveMusicPackageForSource = DesktopAssistantMusicUiHelper.resolveMusicPackageForSource(this.currentSource, this.prefs);
        if (this.currentSource != 6) {
            return resolveMusicPackageForSource;
        }
        String string = this.prefs.getString("universal_music_current_app", null);
        return !TextUtils.isEmpty(string) ? string : resolveMusicPackageForSource;
    }

    private int resolveSavedX() {
        int i7 = getResources().getDisplayMetrics().widthPixels;
        int dp = dp(340);
        return this.prefs.contains(PREF_POS_X) ? clampX(this.prefs.getInt(PREF_POS_X, 0), i7, dp) : Math.max(0, (i7 - dp) / 2);
    }

    private int resolveSavedY() {
        return this.prefs.contains(PREF_POS_Y) ? clampY(this.prefs.getInt(PREF_POS_Y, dp(26)), getResources().getDisplayMetrics().heightPixels, dp(64)) : dp(26);
    }

    private String safeLyricLine(String str) {
        return TextUtils.isEmpty(str) ? s5.h0.FRAGMENT_ENCODE_SET : str.trim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleDeferredRefresh() {
        Handler handler = this.mainHandler;
        if (handler == null) {
            return;
        }
        handler.removeCallbacks(this.deferredRefreshRunnable);
        this.mainHandler.postDelayed(this.deferredRefreshRunnable, DEFERRED_REFRESH_DELAY_MS);
    }

    private void setupControls() {
        final int i7 = 0;
        this.playPauseView.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.service.i0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MusicIslandService f4879b;

            {
                this.f4879b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i8 = i7;
                MusicIslandService musicIslandService = this.f4879b;
                switch (i8) {
                    case 0:
                        musicIslandService.lambda$setupControls$1(view);
                        break;
                    case 1:
                        musicIslandService.lambda$setupControls$2(view);
                        break;
                    case 2:
                        musicIslandService.lambda$setupControls$3(view);
                        break;
                    case 3:
                        musicIslandService.lambda$setupControls$4(view);
                        break;
                    default:
                        musicIslandService.lambda$setupControls$5(view);
                        break;
                }
            }
        });
        final int i8 = 1;
        this.playPauseLargeView.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.service.i0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MusicIslandService f4879b;

            {
                this.f4879b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i82 = i8;
                MusicIslandService musicIslandService = this.f4879b;
                switch (i82) {
                    case 0:
                        musicIslandService.lambda$setupControls$1(view);
                        break;
                    case 1:
                        musicIslandService.lambda$setupControls$2(view);
                        break;
                    case 2:
                        musicIslandService.lambda$setupControls$3(view);
                        break;
                    case 3:
                        musicIslandService.lambda$setupControls$4(view);
                        break;
                    default:
                        musicIslandService.lambda$setupControls$5(view);
                        break;
                }
            }
        });
        final int i9 = 2;
        this.prevButtonView.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.service.i0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MusicIslandService f4879b;

            {
                this.f4879b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i82 = i9;
                MusicIslandService musicIslandService = this.f4879b;
                switch (i82) {
                    case 0:
                        musicIslandService.lambda$setupControls$1(view);
                        break;
                    case 1:
                        musicIslandService.lambda$setupControls$2(view);
                        break;
                    case 2:
                        musicIslandService.lambda$setupControls$3(view);
                        break;
                    case 3:
                        musicIslandService.lambda$setupControls$4(view);
                        break;
                    default:
                        musicIslandService.lambda$setupControls$5(view);
                        break;
                }
            }
        });
        final int i10 = 3;
        this.nextButtonView.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.service.i0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MusicIslandService f4879b;

            {
                this.f4879b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i82 = i10;
                MusicIslandService musicIslandService = this.f4879b;
                switch (i82) {
                    case 0:
                        musicIslandService.lambda$setupControls$1(view);
                        break;
                    case 1:
                        musicIslandService.lambda$setupControls$2(view);
                        break;
                    case 2:
                        musicIslandService.lambda$setupControls$3(view);
                        break;
                    case 3:
                        musicIslandService.lambda$setupControls$4(view);
                        break;
                    default:
                        musicIslandService.lambda$setupControls$5(view);
                        break;
                }
            }
        });
        final int i11 = 4;
        View.OnClickListener onClickListener = new View.OnClickListener(this) { // from class: com.navi.tool.service.i0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MusicIslandService f4879b;

            {
                this.f4879b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i82 = i11;
                MusicIslandService musicIslandService = this.f4879b;
                switch (i82) {
                    case 0:
                        musicIslandService.lambda$setupControls$1(view);
                        break;
                    case 1:
                        musicIslandService.lambda$setupControls$2(view);
                        break;
                    case 2:
                        musicIslandService.lambda$setupControls$3(view);
                        break;
                    case 3:
                        musicIslandService.lambda$setupControls$4(view);
                        break;
                    default:
                        musicIslandService.lambda$setupControls$5(view);
                        break;
                }
            }
        };
        this.sourceIconView.setOnClickListener(onClickListener);
        this.sourceLargeIconView.setOnClickListener(onClickListener);
        this.coverView.setOnClickListener(onClickListener);
        this.coverLargeView.setOnClickListener(onClickListener);
    }

    private void setupGestureAndTouch() {
        this.dragTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        final int i7 = 0;
        this.capsuleView.setOnTouchListener(new View.OnTouchListener(this) { // from class: com.navi.tool.service.j0

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MusicIslandService f4883b;

            {
                this.f4883b = this;
            }

            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean lambda$setupGestureAndTouch$0;
                boolean handleProgressTouch;
                int i8 = i7;
                MusicIslandService musicIslandService = this.f4883b;
                switch (i8) {
                    case 0:
                        lambda$setupGestureAndTouch$0 = musicIslandService.lambda$setupGestureAndTouch$0(view, motionEvent);
                        return lambda$setupGestureAndTouch$0;
                    default:
                        handleProgressTouch = musicIslandService.handleProgressTouch(view, motionEvent);
                        return handleProgressTouch;
                }
            }
        });
        View view = this.progressContainerView;
        if (view != null) {
            final int i8 = 1;
            view.setOnTouchListener(new View.OnTouchListener(this) { // from class: com.navi.tool.service.j0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ MusicIslandService f4883b;

                {
                    this.f4883b = this;
                }

                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    boolean lambda$setupGestureAndTouch$0;
                    boolean handleProgressTouch;
                    int i82 = i8;
                    MusicIslandService musicIslandService = this.f4883b;
                    switch (i82) {
                        case 0:
                            lambda$setupGestureAndTouch$0 = musicIslandService.lambda$setupGestureAndTouch$0(view2, motionEvent);
                            return lambda$setupGestureAndTouch$0;
                        default:
                            handleProgressTouch = musicIslandService.handleProgressTouch(view2, motionEvent);
                            return handleProgressTouch;
                    }
                }
            });
        }
    }

    private void setupWindowParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2038, 776, -3);
        this.overlayParams = layoutParams;
        layoutParams.gravity = 8388659;
        layoutParams.x = resolveSavedX();
        this.overlayParams.y = resolveSavedY();
    }

    private boolean shouldConsumeEvent(int i7) {
        return i7 == this.currentSource || i7 == this.prefs.getInt("desktop_music_source", 0);
    }

    private void showExpandedCard(boolean z6) {
        LinearLayout linearLayout = this.expandedCard;
        if (linearLayout == null || z6 == this.isExpanded) {
            return;
        }
        linearLayout.animate().cancel();
        this.capsuleView.animate().cancel();
        this.isExpanded = z6;
        int i7 = 1;
        if (!z6) {
            this.capsuleView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(220L).setInterpolator(new OvershootInterpolator(0.5f)).start();
            this.expandedCard.animate().alpha(CropImageView.DEFAULT_ASPECT_RATIO).scaleX(0.96f).scaleY(0.9f).translationY(-10.0f).setDuration(210L).setInterpolator(new AccelerateInterpolator(1.15f)).withEndAction(new g0(this, i7)).start();
            this.overlayView.post(new g0(this, 2));
            return;
        }
        this.expandedCard.setVisibility(0);
        this.titleLargeView.setSelected(true);
        this.expandedCard.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        this.expandedCard.setScaleX(0.92f);
        this.expandedCard.setScaleY(0.84f);
        this.expandedCard.setTranslationY(-14.0f);
        this.capsuleView.animate().scaleX(0.985f).scaleY(0.94f).alpha(0.985f).setDuration(260L).setInterpolator(new OvershootInterpolator(0.45f)).start();
        this.expandedCard.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).translationY(CropImageView.DEFAULT_ASPECT_RATIO).setDuration(320L).setInterpolator(new OvershootInterpolator(0.72f)).start();
        this.overlayView.post(new g0(this, 0));
    }

    private void startCoverRotation() {
        if (this.coverRotationAnimator == null) {
            this.coverRotationAnimator = buildRotationAnimator(this.coverCard);
        }
        if (this.coverLargeRotationAnimator == null) {
            this.coverLargeRotationAnimator = buildRotationAnimator(this.coverLargeCard);
        }
        if (!this.coverRotationAnimator.isStarted()) {
            this.coverRotationAnimator.start();
        } else if (this.coverRotationAnimator.isPaused()) {
            this.coverRotationAnimator.resume();
        }
        if (!this.coverLargeRotationAnimator.isStarted()) {
            this.coverLargeRotationAnimator.start();
        } else if (this.coverLargeRotationAnimator.isPaused()) {
            this.coverLargeRotationAnimator.resume();
        }
    }

    public static void startOrRefresh(Context context) {
        Intent intent = new Intent(context, (Class<?>) MusicIslandService.class);
        intent.setAction(ACTION_SYNC);
        startServiceCompat(context, intent);
    }

    private static void startServiceCompat(Context context, Intent intent) {
        if (context == null) {
            return;
        }
        context.startService(intent);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, (Class<?>) MusicIslandService.class));
    }

    private void stopAllMusicSources() {
        DesktopAssistantMusicUiHelper.stopMusicManagers(this);
        this.startedMusicSource = -1;
    }

    private void stopCoverRotation() {
        ObjectAnimator objectAnimator = this.coverRotationAnimator;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.coverRotationAnimator.pause();
        }
        ObjectAnimator objectAnimator2 = this.coverLargeRotationAnimator;
        if (objectAnimator2 == null || !objectAnimator2.isRunning()) {
            return;
        }
        this.coverLargeRotationAnimator.pause();
    }

    private void syncOverlayAttachmentState() {
        View view = this.overlayView;
        if (view == null) {
            this.overlayAttached = false;
        } else {
            this.overlayAttached = view.getParent() != null;
        }
    }

    private void unregisterUpdateReceiver() {
        BroadcastReceiver broadcastReceiver = this.updateReceiver;
        if (broadcastReceiver == null) {
            return;
        }
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (Exception unused) {
        }
        this.updateReceiver = null;
    }

    private void updateExpandedLyrics() {
        Map.Entry<Long, String> entry;
        Map.Entry<Long, String> entry2;
        Map.Entry<Long, String> entry3 = null;
        if (this.lyricsMap.isEmpty()) {
            entry = null;
            entry2 = null;
        } else {
            Map.Entry<Long, String> floorEntry = this.lyricsMap.floorEntry(Long.valueOf(Math.max(0L, this.currentPositionMs)));
            if (floorEntry == null) {
                floorEntry = this.lyricsMap.firstEntry();
            }
            if (floorEntry != null) {
                Map.Entry<Long, String> lowerEntry = this.lyricsMap.lowerEntry(floorEntry.getKey());
                entry2 = this.lyricsMap.higherEntry(floorEntry.getKey());
                entry3 = floorEntry;
                entry = lowerEntry;
            } else {
                entry2 = null;
                entry3 = floorEntry;
                entry = null;
            }
        }
        String str = this.currentLyric;
        if (entry3 != null && !TextUtils.isEmpty(entry3.getValue())) {
            str = entry3.getValue();
        }
        if (TextUtils.isEmpty(str)) {
            str = TextUtils.isEmpty(this.currentArtist) ? "等待歌词" : this.currentArtist;
        }
        String str2 = s5.h0.FRAGMENT_ENCODE_SET;
        String safeLyricLine = entry == null ? s5.h0.FRAGMENT_ENCODE_SET : safeLyricLine(entry.getValue());
        String safeLyricLine2 = safeLyricLine(str);
        if (entry2 != null) {
            str2 = safeLyricLine(entry2.getValue());
        }
        if (!TextUtils.equals(String.valueOf(this.prevLyricView.getText()), safeLyricLine)) {
            this.prevLyricView.setText(safeLyricLine);
        }
        if (!TextUtils.equals(String.valueOf(this.currentLyricLargeView.getText()), safeLyricLine2)) {
            this.currentLyricLargeView.setText(safeLyricLine2);
        }
        if (TextUtils.equals(String.valueOf(this.nextLyricView.getText()), str2)) {
            return;
        }
        this.nextLyricView.setText(str2);
    }

    private void updateOverlayLayout() {
        View view;
        WindowManager windowManager;
        syncOverlayAttachmentState();
        if (!this.overlayAttached || (view = this.overlayView) == null || (windowManager = this.windowManager) == null) {
            return;
        }
        try {
            windowManager.updateViewLayout(view, this.overlayParams);
        } catch (Exception unused) {
            this.overlayAttached = false;
        }
    }

    private void updateProgressUi() {
        if (this.isDraggingProgress) {
            return;
        }
        View view = this.progressTrackLargeView;
        if (view != null) {
            view.setAlpha(0.18f);
        }
        this.lastKnownTotalDuration = DesktopAssistantMusicUiHelper.updateProgressBarAndDuration(this.progressLargeView, this.currentDurationLargeView, this.totalDurationLargeView, this.totalDurationMs, this.currentPositionMs, this.currentTitle, this.lastProgressTitle, this.lastKnownTotalDuration);
        if (TextUtils.isEmpty(this.currentTitle)) {
            return;
        }
        this.lastProgressTitle = this.currentTitle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSourceIcon() {
        Drawable resolveAppIcon = DesktopAssistantMusicUiHelper.resolveAppIcon(this, resolveCurrentMusicPackage());
        if (resolveAppIcon == null) {
            this.sourceIconView.setImageResource(g4.o.ic_music_note);
            this.sourceLargeIconView.setImageResource(g4.o.ic_music_note);
        } else {
            this.sourceIconView.setImageDrawable(resolveAppIcon);
            this.sourceLargeIconView.setImageDrawable(resolveAppIcon);
        }
        String resolveMusicSourceDisplayName = DesktopAssistantMusicUiHelper.resolveMusicSourceDisplayName(this.currentSource);
        if (TextUtils.equals(String.valueOf(this.sourceNameView.getText()), resolveMusicSourceDisplayName)) {
            return;
        }
        this.sourceNameView.setText(resolveMusicSourceDisplayName);
    }

    private void updateTexts() {
        String str = TextUtils.isEmpty(this.currentTitle) ? "暂无音乐播放" : this.currentTitle;
        String str2 = TextUtils.isEmpty(this.currentArtist) ? "等待音乐播放" : this.currentArtist;
        String str3 = TextUtils.isEmpty(this.currentLyric) ? str2 : this.currentLyric;
        if (!TextUtils.equals(String.valueOf(this.titleView.getText()), str)) {
            this.titleView.setText(str);
        }
        if (!TextUtils.equals(String.valueOf(this.lyricView.getText()), str3)) {
            this.lyricView.setText(str3);
        }
        if (!TextUtils.equals(String.valueOf(this.titleLargeView.getText()), str)) {
            this.titleLargeView.setText(str);
        }
        if (TextUtils.equals(String.valueOf(this.artistLargeView.getText()), str2)) {
            return;
        }
        this.artistLargeView.setText(str2);
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onAlbumArtUpdateEvent(h4.a aVar) {
        if (shouldConsumeEvent(aVar.f6725c)) {
            Bitmap bitmap = aVar.f6724b;
            if (bitmap != null) {
                lambda$onAlbumArtUpdateEvent$9(bitmap);
                return;
            }
            String str = aVar.f6723a;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            DesktopAssistantMusicUiHelper.downloadCoverAsync(str, new e(2, this));
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.prefs = SharedPreferencesUtils.getAppPrefs(this);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.windowManager = (WindowManager) getSystemService("window");
        initOverlay();
        registerUpdateReceiver();
        if (!i6.e.b().e(this)) {
            i6.e.b().j(this);
        }
        refreshSourceState();
        loadCachedMusicSnapshot(true);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mainHandler.removeCallbacks(this.deferredRefreshRunnable);
        unregisterUpdateReceiver();
        if (i6.e.b().e(this)) {
            i6.e.b().l(this);
        }
        stopCoverRotation();
        removeOverlay();
        if (this.assistantVisible) {
            return;
        }
        stopAllMusicSources();
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLyricUpdateEvent(h4.k kVar) {
        if (shouldConsumeEvent(kVar.f6754b)) {
            this.currentLyric = safeLyricLine(kVar.f6753a);
            updateTexts();
            updateExpandedLyrics();
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLyricsDataEvent(h4.l lVar) {
        if (shouldConsumeEvent(lVar.f6756b)) {
            this.lyricsMap.clear();
            TreeMap treeMap = lVar.f6755a;
            if (treeMap != null) {
                this.lyricsMap.putAll(treeMap);
            }
            updateExpandedLyrics();
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onMusicStatusEvent(h4.m mVar) {
        if (shouldConsumeEvent(mVar.f6768l)) {
            this.currentSource = mVar.f6768l;
            this.currentTitle = mVar.f6758b;
            this.currentArtist = mVar.f6759c;
            this.currentPositionMs = Math.max(0L, mVar.f6760d);
            long j7 = mVar.f6761e;
            this.totalDurationMs = Math.max(0L, j7);
            if (j7 > 0) {
                this.lastKnownTotalDuration = j7;
            }
            applyPlaybackState(mVar.f6757a);
            updateTexts();
            updateProgressUi();
            updateExpandedLyrics();
            updateSourceIcon();
            DesktopAssistantMusicUiHelper.CoverRequest buildCoverRequest = DesktopAssistantMusicUiHelper.buildCoverRequest(mVar, this.prefs, this.lastKnownTitle, this.lastLoadingTitle);
            if (buildCoverRequest != null) {
                String str = buildCoverRequest.title;
                this.lastKnownTitle = str;
                this.lastLoadingTitle = str;
                DesktopAssistantMusicUiHelper.executeCoverRequest(buildCoverRequest, new DesktopAssistantMusicUiHelper.CoverRequestCallback() { // from class: com.navi.tool.service.MusicIslandService.3
                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void applyCoverBitmap(Bitmap bitmap, boolean z6) {
                        MusicIslandService.this.lambda$onAlbumArtUpdateEvent$9(bitmap);
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void applyDefaultCover() {
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public boolean isCurrentTitle(String str2) {
                        return TextUtils.equals(str2, MusicIslandService.this.lastKnownTitle);
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void logDebug(String str2) {
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void logError(String str2) {
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void runOnUiThread(Runnable runnable) {
                        MusicIslandService.this.mainHandler.post(runnable);
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void saveMusicCover(Bitmap bitmap) {
                    }
                });
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        if (intent != null && ACTION_SET_DESKTOP_ASSISTANT_VISIBLE.equals(intent.getAction())) {
            this.assistantVisible = intent.getBooleanExtra(EXTRA_DESKTOP_ASSISTANT_VISIBLE, false);
        }
        refreshSourceState();
        ensureMusicSourceStarted();
        evaluateOverlayState();
        scheduleDeferredRefresh();
        return 1;
    }
}
