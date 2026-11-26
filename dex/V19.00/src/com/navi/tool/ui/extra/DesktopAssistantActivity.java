package com.navi.tool.ui.extra;

import android.R;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.DisplayMetrics;
import android.view.Choreographer;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.navi.tool.MainActivity;
import com.navi.tool.NaviApplication;
import com.navi.tool.amapflying.receiver.LunaMusicLyricReceiver;
import com.navi.tool.amapflying.receiver.QQMusicLyricReceiver;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.carinfo.CarInfoService;
import com.navi.tool.carinfo.LionCarInfoService;
import com.navi.tool.model.AppInfo;
import com.navi.tool.service.GestureAccessibilityService;
import com.navi.tool.service.GestureActionService;
import com.navi.tool.service.MusicIslandService;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.ui.adapter.DockAppAdapter;
import com.navi.tool.ui.adapter.SelectAppAdapter;
import com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper;
import com.navi.tool.utils.AccessibilityGuard;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.ConfigSyncManager;
import com.navi.tool.utils.CoverPaletteExtractor;
import com.navi.tool.utils.DensityUtil;
import com.navi.tool.utils.LunarCalendarUtils;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import com.navi.tool.view.CoverGradientBackgroundView;
import com.navi.tool.view.DesktopMapCalendarCardView;
import com.navi.tool.view.LyricsFxView;
import com.navi.tool.view.RealtimeBlurView;
import com.navi.tool.view.SmartCornerView;
import com.navi.tool.view.SpectrumView;
import com.navi.tool.view.WeatherAmbientView;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopAssistantActivity extends androidx.appcompat.app.r {
    private static final String CACHE_DIR_NAME = "navitool/.music";
    private static final String CACHE_FILE_COVER = "cover.png";
    private static final String CACHE_FILE_LYRICS = "lyrics.json";
    private static final String CACHE_FILE_META = "meta.json";
    private static final String CUSTOM_MUSIC_SOURCE_PACKAGE = "__custom_music_source__";
    private static final long EMPTY_LYRIC_HINT_DELAY_MS = 1600;
    private static final String KEY_FLOATING_MAP_DISPLAY_MODE = "desktop_map_display_mode";
    private static final String KEY_MAP_ACTIVE_INDEX = "desktop_map_active_index";
    private static final String KEY_THEME_MODE = "theme_mode";
    private static final String KEY_WALLPAPER_DISPLAY_MODE = "desktop_wallpaper_display_mode";
    private static final long KUGOU_RESUME_LYRICS_GUARD_MS = 2500;
    private static final long LYRICS_IMMEDIATE_SCROLL_THRESHOLD_MS = 5000;
    private static final int MAP_DISPLAY_MODE_CALENDAR = 1;
    private static final int MAP_DISPLAY_MODE_MAP = 0;
    private static final int MODE_SPLIT = 0;
    private static final int MODE_WALLPAPER = 1;
    private static final String PKG_ADB_SWITCH = "com.navi.tool.action.adbswitch";
    private static final String PKG_THEME_MODE = "com.navi.tool.thememode";
    private static final String PREF_KEY_CENTER_WEIGHT = "desktop_center_weight";
    private static final String PREF_KEY_CLEAN_MODE = "desktop_clean_mode";
    private static final String PREF_KEY_LEFT_WEIGHT = "desktop_left_weight";
    private static final String PREF_KEY_MAP_PACKAGE = "desktop_map_package";
    private static final String PREF_KEY_MAP_PACKAGE_2 = "desktop_map_package_2";
    private static final String PREF_KEY_MUSIC_PACKAGE = "desktop_music_package";
    private static final String PREF_KEY_SWAP_POS = "desktop_swap_pos";
    private static final String PREF_KEY_VIEW_MODE = "desktop_view_mode";
    private static final String PREF_KEY_VIEW_ORDER = "desktop_view_order";
    private static final String PREF_KEY_VIEW_WEIGHTS = "desktop_view_weights";
    private static final long SWITCH_CLOSE_OLD_DELAY_MS = 400;
    private static final int THEME_DARK = 1;
    private static final int THEME_LIGHT = 0;
    private static final int THEME_SYSTEM = 2;
    private static final String VIDEO_WALLPAPER_FILE_NAME = "desktop_wallpaper.mp4";
    private static final long VIDEO_WATCHDOG_PREPARE_TIMEOUT_MS = 20000;
    private static final long VIDEO_WATCHDOG_SLACK_MS = 5000;
    private static final int VIEW_TYPE_CAR = 2;
    private static final int VIEW_TYPE_MAP = 0;
    private static final int VIEW_TYPE_MUSIC = 1;
    private static final String WALLPAPER_FILE_NAME = "desktop_wallpaper.png";
    private static final int WALLPAPER_MODE_DYNAMIC = 0;
    private static final int WALLPAPER_MODE_STATIC = 1;
    private ObjectAnimator albumArtAnimator;
    private DesktopAppAdapter appListAdapter;
    private RecyclerView appListRecyclerView;
    private androidx.appcompat.app.o appSelectionLoadingDialog;
    private ImageView btnControlHandle;
    private ImageButton btnNext;
    private ImageButton btnPlayPause;
    private ImageButton btnPrev;
    private ImageView btnSwitchMode;
    private ImageButton btnSwitchToCar;
    private CarControlAdapter carControlAdapter;
    private View carControlContainer;
    private DesktopAssistantCarControlHelper carControlHelper;
    private BroadcastReceiver carControlReceiver;
    private RecyclerView carControlRecyclerView;
    private CardView cardAlbumArt;
    private CardView cardSeatControl;
    private Runnable carouselRunnable;
    private RealtimeBlurView centerContainer;
    private View centerDivider;
    private DesktopAssistantClockWeatherController clockWeatherController;
    private ConfigSyncManager configSyncManager;
    private FrameLayout containerDisc;
    private ViewGroup contentContainer;
    private LinearLayout controlHandleContainer;
    private FrameLayout controlOverlay;
    private CoverGradientBackgroundView coverGradientBackground;
    private String currentVideoWallpaperPath;
    private m4.a desktopSpectrumAnalyzer;
    private DockAppAdapter dockAdapter;
    private RealtimeBlurView dockContainer;
    private BroadcastReceiver dockReceiver;
    private GestureDetector gestureDetector;
    private Runnable hideControlRunnable;
    private View hvacAutoContainer;
    private View hvacModeContainer;
    private ImageView imgAlbumArtFull;
    private ImageView imgFavHeart;
    private ImageView imgLoopMode;
    private ImageView imgMusicAppIcon;
    private ImageView imgSignalIcon;
    private ImageView imgWeatherIcon;
    private ImageView imgWifiIcon;
    private ImageView ivHvacAutoIcon;
    private ImageView ivHvacModeIcon;
    private ImageView ivSeatHeatFlIcon;
    private ImageView ivSeatHeatFrIcon;
    private ImageView ivSeatHeatRlIcon;
    private ImageView ivSeatHeatRrIcon;
    private ImageView ivSeatVentFlIcon;
    private ImageView ivSeatVentFrIcon;
    private View layoutClockWeatherHeader;
    private View layoutProgressContainer;
    private View layoutSongTitleRow;
    private LinearLayout layoutStatusIcons;
    private RealtimeBlurView leftContainer;
    private LyricsAdapter lyricsAdapter;
    private View lyricsContentView;
    private LyricsFxView lyricsFxView;
    private RecyclerView lyricsRecyclerView;
    private View mFloatingCenterHandle;
    private View mFloatingSplitHandle;
    private WindowManager mWindowManager;
    private DesktopMapCalendarCardView mapCalendarCardView;
    private boolean mapClosedByLyricsFx;
    private FrameLayout mapContainer;
    private BroadcastReceiver mapUiReceiver;
    private View mapViewContent;
    private BroadcastReceiver musicUiReceiver;
    private BroadcastReceiver networkReceiver;
    private BroadcastReceiver oilRecordReceiver;
    private Runnable oneSecondCornerRetryRunnable;
    private BroadcastReceiver packageReceiver;
    private SharedPreferences prefs;
    private RecyclerView recyclerDockApps;
    private RecyclerView recyclerTopInfo;
    private RealtimeBlurView rightContainer;
    private ViewGroup rootLayout;
    private BroadcastReceiver roundCornerReceiver;
    private View seatComfortContainer;
    private View seatHeatFlContainer;
    private View seatHeatFrContainer;
    private View seatHeatRlContainer;
    private View seatHeatRrContainer;
    private View seatVentFlContainer;
    private View seatVentFrContainer;
    private androidx.recyclerview.widget.x0 smoothScroller;
    private LinearLayout splitContainer;
    private View splitDivider;
    private final PlayOrder staticOrder;
    private TextView textArtist;
    private TextClock textClock;
    private TextView textControlGuide;
    private TextView textCurrentDuration;
    private TextView textCurrentLyric;
    private TextView textSongTitle;
    private TextView textTotalDuration;
    private TextView textWeatherLocation;
    private TextView textWeatherLunar;
    private TextView textWeatherSummary;
    private BroadcastReceiver timeReceiver;
    private ImageView topImgSignal;
    private ImageView topImgWifi;
    private DesktopAssistantTopInfoAdapter topInfoAdapter;
    private RealtimeBlurView topInfoBarContainer;
    private View topInfoBarSurface;
    private View topInfoLeftContainer;
    private BroadcastReceiver topInfoReceiver;
    private TextClock topTextDate;
    private TextView tvHvacAuto;
    private TextView tvHvacMode;
    private TextView tvNoCards;
    private TextView tvSeatHeatFL;
    private TextView tvSeatHeatFR;
    private TextView tvSeatHeatFlLevel;
    private TextView tvSeatHeatFrLevel;
    private TextView tvSeatHeatRL;
    private TextView tvSeatHeatRR;
    private TextView tvSeatHeatRlLevel;
    private TextView tvSeatHeatRrLevel;
    private TextView tvSeatMsgDriver;
    private TextView tvSeatMsgPass;
    private TextView tvSeatVentFL;
    private TextView tvSeatVentFR;
    private TextView tvSeatVentFlLevel;
    private TextView tvSeatVentFrLevel;
    private final PlayOrder videoOrder;
    private Runnable videoStallWatchdog;
    private View videoWallpaperMask;
    private MediaPlayer videoWallpaperPlayer;
    private Surface videoWallpaperSurface;
    private TextureView videoWallpaperView;
    private View viewMusicSeekTouchArea;
    private View viewProgress;
    private SpectrumView viewSpectrum;
    private BroadcastReceiver viewSwitchReceiver;
    private WeatherAmbientView viewWeatherAmbient;
    private BroadcastReceiver wallpaperReceiver;
    private ImageView wallpaperView;
    private static final String PKG_FLOATING_MAP_MODE = "com.navi.tool.floatingmapmode";
    private static final String PKG_WALLPAPER_MODE = "com.navi.tool.wallpapermode";
    private static final Set<String> DEFAULT_DOCK_APPS = new HashSet(Arrays.asList("com.navi.tool.originalhome", "com.navi.tool.desktopassistant", PKG_FLOATING_MAP_MODE, PKG_WALLPAPER_MODE));
    private static final String[][] WEATHER_PREVIEW = {new String[]{null, null, "真实天气"}, new String[]{"305", "小雨", "小雨"}, new String[]{"306", "中雨", "中雨"}, new String[]{"307", "大雨", "大雨"}, new String[]{"310", "暴雨", "暴雨"}, new String[]{"311", "大暴雨", "大暴雨"}, new String[]{"312", "特大暴雨", "特大暴雨"}, new String[]{"302", "雷阵雨", "雷阵雨"}, new String[]{"309", "毛毛雨", "毛毛雨"}, new String[]{"300", "阵雨", "阵雨"}, new String[]{"400", "小雪", "小雪"}, new String[]{"401", "中雪", "中雪"}, new String[]{"402", "大雪", "大雪"}, new String[]{"403", "暴雪", "暴雪"}, new String[]{"404", "雨夹雪", "雨夹雪"}, new String[]{"304", "雷阵雨伴有冰雹", "冰雹"}, new String[]{"501", "雾", "雾"}, new String[]{"502", "霾", "霾"}, new String[]{"503", "扬沙", "沙尘"}, new String[]{"101", "多云", "多云"}, new String[]{"104", "阴", "阴"}};
    private volatile List<AppItem> cachedAppList = null;
    private final ExecutorService appListExecutor = Executors.newSingleThreadExecutor();
    private final AtomicInteger appListGen = new AtomicInteger();
    private volatile boolean appPreloadInProgress = false;
    private boolean isCleanMode = false;
    private boolean isLongPress = false;
    private boolean isMusicAdjusting = false;
    private boolean isCarCardDragging = false;
    private boolean isGestureHandling = false;
    private SmartCornerView[] mCornerViews = new SmartCornerView[4];
    private SmartCornerView[] mTransitionCornerViews = new SmartCornerView[4];
    private boolean mIsCornerMaskShown = false;
    private boolean coverGradientApplied = false;
    private int coverGradientRequestToken = 0;
    private String lastGradientCoverUrl = s5.h0.FRAGMENT_ENCODE_SET;
    private boolean isVideoWallpaperActive = false;
    private final List<String> videoWallpaperPaths = new ArrayList();
    private int currentVideoWallpaperIndex = 0;
    private final Handler videoWallpaperHandler = new Handler(Looper.getMainLooper());
    private int videoStallWatchdogToken = 0;
    private boolean mixedSegmentIsVideo = false;
    private boolean mixedForceSegment = false;
    private int staticShownCount = 0;
    private int videoShownCount = 0;
    private final Handler controlHandler = new Handler(Looper.getMainLooper());
    private long lastClockWeatherSwapClick = 0;
    private boolean isPlaying = false;
    private boolean mIsDraggingMusicProgress = false;
    private long mLastKnownCurrentPosition = 0;
    private boolean mPendingLyricsImmediateSync = false;
    private boolean mPreferDirectLyricsLayoutScroll = false;
    private long mKugouResumeLyricsGuardUntilMs = 0;
    private int desktopSpectrumBarCount = 0;
    private String mCurrentLyricLine = s5.h0.FRAGMENT_ENCODE_SET;
    private boolean mEmptyLyricHintPending = false;
    private final Runnable mShowEmptyLyricHintRunnable = new Runnable() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.1
        @Override // java.lang.Runnable
        public void run() {
            DesktopAssistantActivity.this.mEmptyLyricHintPending = false;
            if (DesktopAssistantActivity.this.textCurrentLyric == null) {
                return;
            }
            boolean z6 = DesktopAssistantActivity.this.lyricsAdapter != null && DesktopAssistantActivity.this.lyricsAdapter.getItemCount() > 0;
            String str = DesktopAssistantActivity.this.mCurrentLyricLine == null ? s5.h0.FRAGMENT_ENCODE_SET : DesktopAssistantActivity.this.mCurrentLyricLine;
            if (z6 || !TextUtils.isEmpty(str)) {
                return;
            }
            DesktopAssistantActivity.this.textCurrentLyric.setText("暂无歌词，请欣赏");
            DesktopAssistantActivity.this.textCurrentLyric.setVisibility(0);
            if (DesktopAssistantActivity.this.lyricsRecyclerView != null) {
                DesktopAssistantActivity.this.lyricsRecyclerView.setVisibility(8);
            }
        }
    };
    private int mode = 0;
    private boolean isSwapped = false;
    private int viewPositionMode = 0;
    private boolean isVisualCarModelEnabled = false;
    private int[] viewOrder = {0, 1, 2};
    private float[] viewWeights = {6.0f, 4.0f, 4.0f};
    private final TreeMap<Long, String> mCurrentLyrics = new TreeMap<>();
    private boolean isCarControlEnabled = false;
    private boolean showCarControl = false;
    private boolean isSeatComfortEnabled = false;
    private boolean isMusicEnabled = false;
    private int lyricsLines = 5;
    private int lyricsFontSize = 25;
    private final List<String> wallpaperPaths = new ArrayList();
    private int currentWallpaperIndex = 0;
    private final Handler carouselHandler = new Handler(Looper.getMainLooper());
    private final AtomicInteger wallpaperTokenGenerator = new AtomicInteger(0);
    private volatile int wallpaperToken = 0;
    private final AtomicInteger wallpaperLoadRequestGenerator = new AtomicInteger(0);
    private volatile int wallpaperLoadRequestId = 0;
    private volatile boolean hasResolvedWallpaperOnce = false;
    private boolean gnssObserverRegistered = false;
    private String mLastKnownTitle = null;
    private String mLastKnownArtist = null;
    private String mLastLoadingTitle = null;
    private long mLastKnownTotalDuration = 0;
    private int mPlayMode = -1;
    private int mLoopCycleIndex = 0;
    private boolean mIsFavorite = false;
    private Boolean mPendingFavToggle = null;
    private final SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.navi.tool.ui.extra.t0
        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            DesktopAssistantActivity.this.lambda$new$2(sharedPreferences, str);
        }
    };
    private int mLastFavSource = -1;
    private boolean appSelectionLoading = false;

    /* renamed from: com.navi.tool.ui.extra.DesktopAssistantActivity$12, reason: invalid class name */
    public class AnonymousClass12 implements DesktopAssistantMusicUiHelper.FavoriteCallback {
        public AnonymousClass12() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onError$1(String str) {
            DesktopAssistantActivity.this.imgFavHeart.setEnabled(true);
            DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
            if (str == null) {
                str = "操作失败";
            }
            ToastHelper.show(desktopAssistantActivity, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$0(boolean z6) {
            DesktopAssistantActivity.this.mIsFavorite = z6;
            DesktopAssistantActivity.this.setFavHeartIcon(z6);
            DesktopAssistantActivity.this.imgFavHeart.setEnabled(true);
            DesktopAssistantActivity.this.mPendingFavToggle = z6 ? Boolean.TRUE : Boolean.FALSE;
            ToastHelper.show(DesktopAssistantActivity.this, z6 ? "已收藏" : "已取消收藏");
        }

        @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.FavoriteCallback
        public void onError(String str) {
            DesktopAssistantActivity.this.runOnUiThread(new z0(0, (Object) this, (Object) str));
        }

        @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.FavoriteCallback
        public void onSuccess(boolean z6) {
            DesktopAssistantActivity.this.runOnUiThread(new a1(this, z6, 0));
        }
    }

    /* renamed from: com.navi.tool.ui.extra.DesktopAssistantActivity$29, reason: invalid class name */
    public class AnonymousClass29 implements View.OnTouchListener {
        float startLeftWeight;
        float startRightWeight;
        float startX;
        final /* synthetic */ boolean val$isSplitHandle;
        boolean isDragging = false;
        final float DRAG_THRESHOLD = 10.0f;

        public AnonymousClass29(boolean z6) {
            this.val$isSplitHandle = z6;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            RealtimeBlurView realtimeBlurView;
            RealtimeBlurView realtimeBlurView2;
            int i7;
            int i8;
            float f7;
            int i9 = 0;
            boolean z6 = DesktopAssistantActivity.this.centerContainer != null && DesktopAssistantActivity.this.centerContainer.getVisibility() == 0;
            if (this.val$isSplitHandle) {
                realtimeBlurView = DesktopAssistantActivity.this.leftContainer;
                DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
                realtimeBlurView2 = z6 ? desktopAssistantActivity.centerContainer : desktopAssistantActivity.rightContainer;
                i8 = z6 ? 1 : 2;
                i7 = 0;
            } else {
                realtimeBlurView = DesktopAssistantActivity.this.centerContainer;
                realtimeBlurView2 = DesktopAssistantActivity.this.rightContainer;
                i7 = 1;
                i8 = 2;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                this.startX = motionEvent.getRawX();
                this.isDragging = false;
                if (realtimeBlurView != null) {
                    this.startLeftWeight = ((LinearLayout.LayoutParams) realtimeBlurView.getLayoutParams()).weight;
                }
                if (realtimeBlurView2 != null) {
                    this.startRightWeight = ((LinearLayout.LayoutParams) realtimeBlurView2.getLayoutParams()).weight;
                }
                view.setPressed(true);
                return true;
            }
            if (action != 1) {
                if (action == 2) {
                    float rawX = motionEvent.getRawX();
                    float f8 = rawX - this.startX;
                    if (!this.isDragging && Math.abs(f8) > 10.0f) {
                        this.isDragging = true;
                    }
                    if (this.isDragging) {
                        float f9 = 2.0f;
                        if (view.getLayoutParams() instanceof WindowManager.LayoutParams) {
                            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
                            layoutParams.x = (int) (rawX - (view.getWidth() / 2));
                            try {
                                DesktopAssistantActivity.this.mWindowManager.updateViewLayout(view, layoutParams);
                            } catch (Exception unused) {
                            }
                        } else if (DesktopAssistantActivity.this.rootLayout != null) {
                            DesktopAssistantActivity.this.rootLayout.getLocationOnScreen(new int[2]);
                            view.setX((rawX - r4[0]) - (view.getWidth() / 2.0f));
                        }
                        if (realtimeBlurView != null && realtimeBlurView2 != null) {
                            float width = (f8 / DesktopAssistantActivity.this.splitContainer.getWidth()) * (z6 ? DesktopAssistantActivity.this.getViewWeightsSum() : 10.0f);
                            float f10 = this.startLeftWeight;
                            float f11 = f10 + width;
                            float f12 = this.startRightWeight;
                            float f13 = f12 - width;
                            if (f11 < 2.0f) {
                                f7 = (f10 + f12) - 2.0f;
                            } else if (f13 < 2.0f) {
                                f9 = (f10 + f12) - 2.0f;
                                f7 = 2.0f;
                            } else {
                                f9 = f11;
                                f7 = f13;
                            }
                            DesktopAssistantActivity.this.updateViewWeight(realtimeBlurView, f9);
                            DesktopAssistantActivity.this.updateViewWeight(realtimeBlurView2, f7);
                            if (i7 < DesktopAssistantActivity.this.viewWeights.length) {
                                DesktopAssistantActivity.this.viewWeights[i7] = f9;
                            }
                            if (i8 < DesktopAssistantActivity.this.viewWeights.length) {
                                DesktopAssistantActivity.this.viewWeights[i8] = f7;
                            }
                        }
                    }
                    return true;
                }
                if (action != 3) {
                    return false;
                }
            }
            view.setPressed(false);
            if (this.isDragging) {
                if (DesktopAssistantActivity.this.isCarControlEnabled) {
                    DesktopAssistantActivity.this.saveLayoutState();
                } else if (realtimeBlurView != null) {
                    DesktopAssistantActivity.this.prefs.edit().putFloat(DesktopAssistantActivity.PREF_KEY_LEFT_WEIGHT, ((LinearLayout.LayoutParams) realtimeBlurView.getLayoutParams()).weight).apply();
                }
                ToastHelper.show(DesktopAssistantActivity.this, "布局已调整");
                if (DesktopAssistantActivity.this.mapContainer != null) {
                    DesktopAssistantActivity.this.mapContainer.postDelayed(new b1(DesktopAssistantActivity.this, i9), 200L);
                }
            } else if (motionEvent.getAction() == 1) {
                view.performClick();
            }
            return true;
        }
    }

    /* renamed from: com.navi.tool.ui.extra.DesktopAssistantActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends BroadcastReceiver {
        public AnonymousClass9() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.navi.tool.DESKTOP_NIGHT_MODE_ALPHA_UPDATED".equals(intent.getAction())) {
                DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
                desktopAssistantActivity.updateNightMode(desktopAssistantActivity.isCurrentNightMode());
                return;
            }
            DesktopAssistantActivity.this.applyRoundCorner(DesktopAssistantActivity.this.prefs.getBoolean("desktop_round_corner_enabled", false));
            DesktopAssistantActivity.this.updateTopInfoBarVisibility();
            if (DesktopAssistantActivity.this.mapContainer != null) {
                DesktopAssistantActivity.this.mapContainer.post(new b1(DesktopAssistantActivity.this, 1));
            }
        }
    }

    public static class AppItem {
        Drawable icon;
        ApplicationInfo info;
        String label;
        String packageName;

        private AppItem() {
        }
    }

    public static class DesktopAppAdapter extends androidx.recyclerview.widget.h1 {
        private static final Set<String> NON_UNINSTALLABLE_PACKAGES = new HashSet(Arrays.asList("com.navi.tool.desktopassistant", "com.navi.tool.action.restart", "com.navi.tool.fileassistant", "com.navi.tool.originalhome", "com.navi.tool.permissionassistant", DesktopAssistantActivity.PKG_ADB_SWITCH, DesktopAssistantActivity.PKG_THEME_MODE));
        private List<AppItem> appList;
        private Context context;
        private OnAppClickListener listener;
        private boolean showPackageName = true;
        private Integer appNameColor = null;
        private int iconSize = 0;
        private int fontSizeSp = 14;
        private boolean isNightMode = false;

        public interface OnAppClickListener {
            void onAppClick(AppItem appItem);
        }

        public static class ViewHolder extends androidx.recyclerview.widget.j2 {
            ImageView appIcon;
            TextView appName;
            TextView appPackage;

            public ViewHolder(View view) {
                super(view);
                this.appIcon = (ImageView) view.findViewById(g4.p.app_icon);
                this.appName = (TextView) view.findViewById(g4.p.app_name);
                this.appPackage = (TextView) view.findViewById(g4.p.app_package);
            }
        }

        public DesktopAppAdapter(Context context, List<AppItem> list, OnAppClickListener onAppClickListener) {
            this.context = context;
            this.appList = list;
            this.listener = onAppClickListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(AppItem appItem, View view) {
            OnAppClickListener onAppClickListener = this.listener;
            if (onAppClickListener != null) {
                onAppClickListener.onAppClick(appItem);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$1(AppItem appItem, DialogInterface dialogInterface, int i7) {
            try {
                Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + appItem.packageName));
                intent.addFlags(268435456);
                this.context.startActivity(intent);
            } catch (Exception e7) {
                ToastHelper.show(this.context, "无法调起卸载: " + e7.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$onBindViewHolder$2(AppItem appItem, View view) {
            String str;
            if (appItem != null && (str = appItem.packageName) != null && !str.isEmpty()) {
                if (NON_UNINSTALLABLE_PACKAGES.contains(appItem.packageName)) {
                    ToastHelper.show(this.context, "该项目不支持卸载");
                    return true;
                }
                ApplicationInfo applicationInfo = appItem.info;
                if (applicationInfo != null) {
                    int i7 = applicationInfo.flags;
                    if ((i7 & 1) != 0 || (i7 & 128) != 0) {
                        ToastHelper.show(this.context, "系统应用不支持卸载");
                        return true;
                    }
                }
                androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this.context);
                nVar.t("卸载应用");
                StringBuilder sb = new StringBuilder("确定卸载 ");
                String str2 = appItem.label;
                if (str2 == null) {
                    str2 = appItem.packageName;
                }
                sb.append(str2);
                sb.append("？");
                nVar.m(sb.toString());
                nVar.o("取消", null);
                nVar.r("卸载", new c1(0, this, appItem));
                nVar.v();
            }
            return true;
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.appList.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(ViewHolder viewHolder, int i7) {
            AppItem appItem = this.appList.get(i7);
            viewHolder.appName.setText(appItem.label);
            viewHolder.appName.setTextSize(2, this.fontSizeSp);
            viewHolder.appIcon.setImageDrawable(appItem.icon);
            if (AppDetectionUtils.hasCustomAppIcon(this.context, appItem.packageName) || !("com.navi.tool.desktopassistant".equals(appItem.packageName) || "com.navi.tool.action.restart".equals(appItem.packageName) || "com.navi.tool.originalhome".equals(appItem.packageName) || DesktopAssistantActivity.PKG_ADB_SWITCH.equals(appItem.packageName) || DesktopAssistantActivity.PKG_THEME_MODE.equals(appItem.packageName))) {
                viewHolder.appIcon.clearColorFilter();
            } else {
                viewHolder.appIcon.setColorFilter(-1);
            }
            if (this.isNightMode) {
                viewHolder.appName.setTextColor(-1711276033);
                viewHolder.appName.setBackgroundResource(g4.o.bg_app_name_pill);
            } else {
                Integer num = this.appNameColor;
                if (num != null) {
                    viewHolder.appName.setTextColor(num.intValue());
                    if (this.appNameColor.intValue() == -16777216) {
                        viewHolder.appName.setBackgroundResource(g4.o.bg_app_name_pill_light);
                    } else {
                        viewHolder.appName.setBackgroundResource(g4.o.bg_app_name_pill);
                    }
                } else {
                    viewHolder.appName.setBackgroundResource(g4.o.bg_app_name_pill);
                }
            }
            viewHolder.appIcon.setAlpha(this.isNightMode ? 0.6f : 1.0f);
            int i8 = 0;
            if (this.showPackageName) {
                viewHolder.appPackage.setVisibility(0);
                viewHolder.appPackage.setText(appItem.packageName);
            } else {
                viewHolder.appPackage.setVisibility(8);
            }
            if (this.iconSize > 0) {
                ViewGroup.LayoutParams layoutParams = viewHolder.appIcon.getLayoutParams();
                int i9 = (int) (this.iconSize * this.context.getResources().getDisplayMetrics().density);
                layoutParams.width = i9;
                layoutParams.height = i9;
                viewHolder.appIcon.setLayoutParams(layoutParams);
            }
            d1 d1Var = new d1(i8, this, appItem);
            viewHolder.appIcon.setOnClickListener(d1Var);
            viewHolder.appName.setOnClickListener(d1Var);
            e1 e1Var = new e1(this, appItem, i8);
            viewHolder.appIcon.setOnLongClickListener(e1Var);
            viewHolder.appName.setOnLongClickListener(e1Var);
            viewHolder.itemView.setOnClickListener(null);
            viewHolder.itemView.setClickable(false);
            viewHolder.itemView.setOnLongClickListener(null);
        }

        @Override // androidx.recyclerview.widget.h1
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(g4.q.item_installed_app, viewGroup, false));
        }

        public void setAppNameColor(Integer num) {
            this.appNameColor = num;
            notifyDataSetChanged();
        }

        public void setIconSize(int i7) {
            this.iconSize = i7;
            if (i7 >= 76) {
                this.fontSizeSp = 18;
            } else if (i7 <= 60) {
                this.fontSizeSp = 14;
            } else {
                this.fontSizeSp = i7 <= 68 ? 14 : 18;
            }
            notifyDataSetChanged();
        }

        public void setNightMode(boolean z6) {
            this.isNightMode = z6;
            notifyDataSetChanged();
        }

        public void setShowPackageName(boolean z6) {
            this.showPackageName = z6;
            notifyDataSetChanged();
        }
    }

    public static class LabeledApp {
        final ApplicationInfo info;
        final String label;

        public LabeledApp(ApplicationInfo applicationInfo, String str) {
            this.info = applicationInfo;
            this.label = str;
        }
    }

    public static class MusicMeta {
        String artist;
        String title;

        public MusicMeta(String str, String str2) {
            this.title = str;
            this.artist = str2;
        }
    }

    public static final class PlayOrder {
        private int cursor;
        private int lastPicked;
        private final List<Integer> queue;
        private boolean random;

        private PlayOrder() {
            this.queue = new ArrayList();
            this.cursor = 0;
            this.random = false;
            this.lastPicked = -1;
        }

        public int next(int i7, int i8) {
            if (i8 <= 1) {
                reset();
                return 0;
            }
            if (!this.random) {
                if (i7 < 0) {
                    return 0;
                }
                return (i7 + 1) % i8;
            }
            if (this.queue.size() != i8) {
                this.queue.clear();
                for (int i9 = 0; i9 < i8; i9++) {
                    this.queue.add(Integer.valueOf(i9));
                }
                Collections.shuffle(this.queue);
                this.cursor = 0;
            }
            if (this.cursor >= this.queue.size()) {
                Collections.shuffle(this.queue);
                if (this.queue.size() > 1 && this.queue.get(0).intValue() == this.lastPicked) {
                    Collections.swap(this.queue, 0, 1);
                }
                this.cursor = 0;
            }
            List<Integer> list = this.queue;
            int i10 = this.cursor;
            this.cursor = i10 + 1;
            int intValue = list.get(i10).intValue();
            this.lastPicked = intValue;
            return intValue;
        }

        public void reset() {
            this.queue.clear();
            this.cursor = 0;
            this.lastPicked = -1;
        }

        public void setRandom(boolean z6) {
            if (this.random != z6) {
                this.random = z6;
                reset();
            }
        }
    }

    public DesktopAssistantActivity() {
        this.staticOrder = new PlayOrder();
        this.videoOrder = new PlayOrder();
    }

    public static /* synthetic */ void access$4300(DesktopAssistantActivity desktopAssistantActivity) {
        desktopAssistantActivity.lambda$setupMode$71();
    }

    public static /* synthetic */ int access$6908(DesktopAssistantActivity desktopAssistantActivity) {
        int i7 = desktopAssistantActivity.staticShownCount;
        desktopAssistantActivity.staticShownCount = i7 + 1;
        return i7;
    }

    private void addFloatingHandle(View view, View view2) {
        view2.getLocationOnScreen(new int[2]);
        view.measure(0, 0);
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (measuredWidth == 0) {
            measuredWidth = DensityUtil.dip2px(this, 120.0f);
        }
        if (measuredHeight == 0) {
            measuredHeight = DensityUtil.dip2px(this, 40.0f);
        }
        float width = ((view2.getWidth() / 2.0f) + r0[0]) - (measuredWidth / 2.0f);
        float height = ((view2.getHeight() / 2.0f) + r0[1]) - (measuredHeight / 2.0f);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.flags = 262664;
        layoutParams.format = -3;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 51;
        layoutParams.x = (int) width;
        layoutParams.y = (int) height;
        try {
            if (view.getParent() != null) {
                if (view.getParent() instanceof ViewGroup) {
                    ((ViewGroup) view.getParent()).removeView(view);
                } else {
                    this.mWindowManager.removeView(view);
                }
            }
            this.mWindowManager.addView(view, layoutParams);
            view.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
            view.animate().alpha(1.0f).setDuration(200L).start();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void addTransitionCornerMasks(int r29, int r30, int r31, int r32) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.addTransitionCornerMasks(int, int, int, int):void");
    }

    private void applyCoverGradientFromEvent(h4.a aVar) {
        SharedPreferences sharedPreferences;
        if (this.coverGradientBackground == null || (sharedPreferences = this.prefs) == null || aVar == null || !sharedPreferences.getBoolean("desktop_cover_gradient_enabled", false)) {
            return;
        }
        Bitmap bitmap = aVar.f6724b;
        if (bitmap != null && !bitmap.isRecycled()) {
            updateCoverGradientFromBitmap(bitmap);
            return;
        }
        String str = aVar.f6723a;
        if (TextUtils.isEmpty(str) || str.equals(this.lastGradientCoverUrl)) {
            return;
        }
        this.lastGradientCoverUrl = str;
        DesktopAssistantMusicUiHelper.downloadCoverAsync(str, new z(this, 4));
    }

    private void applyCoverGradientMode() {
        MediaPlayer mediaPlayer;
        if (this.coverGradientBackground == null || this.prefs == null || isFinishing() || isDestroyed()) {
            return;
        }
        boolean z6 = false;
        if (!this.prefs.getBoolean("desktop_cover_gradient_enabled", false)) {
            this.coverGradientBackground.setVisibility(8);
            this.coverGradientApplied = false;
            View view = this.viewProgress;
            if (view instanceof GlowProgressView) {
                ((GlowProgressView) view).resetDefaultColors();
            }
            TextureView textureView = this.videoWallpaperView;
            if (textureView != null && textureView.getVisibility() == 0) {
                z6 = true;
            }
            if (this.isVideoWallpaperActive && this.videoWallpaperPlayer != null && z6) {
                return;
            }
            loadWallpaper();
            return;
        }
        this.coverGradientBackground.setVisibility(0);
        this.coverGradientBackground.setBrightness(gradientIntensityToBrightness(this.prefs.getInt("desktop_cover_gradient_intensity", 60)));
        ImageView imageView = this.wallpaperView;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        TextureView textureView2 = this.videoWallpaperView;
        if (textureView2 != null) {
            textureView2.setVisibility(8);
        }
        View view2 = this.videoWallpaperMask;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        if (this.isVideoWallpaperActive && (mediaPlayer = this.videoWallpaperPlayer) != null) {
            try {
                mediaPlayer.pause();
            } catch (Exception unused) {
            }
        }
        if (this.coverGradientApplied) {
            return;
        }
        this.coverGradientApplied = true;
        stopCarousel();
        loadCachedCoverForGradient();
    }

    private void applyMusicProgressPreview(long j7) {
        TextView textView = this.textSongTitle;
        String valueOf = textView == null ? s5.h0.FRAGMENT_ENCODE_SET : String.valueOf(textView.getText());
        View view = this.viewProgress;
        TextView textView2 = this.textCurrentDuration;
        TextView textView3 = this.textTotalDuration;
        long j8 = this.mLastKnownTotalDuration;
        DesktopAssistantMusicUiHelper.updateProgressBarAndDuration(view, textView2, textView3, j8, j7, valueOf, this.mLastKnownTitle, j8, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyRoundCorner(boolean z6) {
        FrameLayout frameLayout;
        RealtimeBlurView realtimeBlurView;
        ViewGroup.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2;
        if (this.isVideoWallpaperActive) {
            z6 = false;
        }
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences != null && sharedPreferences.getBoolean("desktop_cover_gradient_enabled", false)) {
            z6 = false;
        }
        float f7 = getResources().getDisplayMetrics().density;
        final float f8 = z6 ? 20.0f * f7 : CropImageView.DEFAULT_ASPECT_RATIO;
        int i7 = this.prefs.getInt("desktop_card_margin", 15);
        if (i7 < 0) {
            i7 = 0;
        }
        if (i7 > 20) {
            i7 = 20;
        }
        float f9 = 1.5f * f7;
        int round = Math.round(i7 * f7);
        int round2 = i7 == 0 ? 0 : Math.round(f7 * 1.0f);
        int max = Math.max(0, round - round2);
        int i8 = max / 2;
        int i9 = max - i8;
        View view = this.splitDivider;
        if (view != null && (layoutParams2 = view.getLayoutParams()) != null && layoutParams2.width != round2) {
            layoutParams2.width = round2;
            this.splitDivider.setLayoutParams(layoutParams2);
        }
        View view2 = this.centerDivider;
        if (view2 != null && (layoutParams = view2.getLayoutParams()) != null && layoutParams.width != round2) {
            layoutParams.width = round2;
            this.centerDivider.setLayoutParams(layoutParams);
        }
        RealtimeBlurView realtimeBlurView2 = this.topInfoBarContainer;
        int i10 = (realtimeBlurView2 == null || realtimeBlurView2.getVisibility() != 0) ? 0 : round;
        if (this.dockContainer != null) {
            this.dockContainer.setVisibility(this.prefs.getBoolean("desktop_hide_dock", false) ? 8 : 0);
            ViewGroup.LayoutParams layoutParams3 = this.dockContainer.getLayoutParams();
            if (layoutParams3 instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams3;
                marginLayoutParams.setMargins(0, i10, 0, 0);
                this.dockContainer.setLayoutParams(marginLayoutParams);
            }
            this.dockContainer.setCornerRadius(f8);
            this.dockContainer.setBorder(f9, 452984831);
            if (z6) {
                this.dockContainer.setOutlineProvider(new ViewOutlineProvider() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.20
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view3, Outline outline) {
                        outline.setRoundRect(0, 0, view3.getWidth(), view3.getHeight(), f8);
                    }
                });
                this.dockContainer.setClipToOutline(true);
            } else {
                this.dockContainer.setClipToOutline(false);
            }
        }
        ViewGroup viewGroup = this.contentContainer;
        if (viewGroup != null) {
            ViewGroup.LayoutParams layoutParams4 = viewGroup.getLayoutParams();
            if (layoutParams4 instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams4;
                marginLayoutParams2.setMargins(round, 0, 0, 0);
                this.contentContainer.setLayoutParams(marginLayoutParams2);
            }
        }
        RealtimeBlurView realtimeBlurView3 = this.leftContainer;
        if (realtimeBlurView3 != null) {
            LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) realtimeBlurView3.getLayoutParams();
            if (layoutParams5 != null) {
                RealtimeBlurView realtimeBlurView4 = this.centerContainer;
                layoutParams5.setMargins(0, round, ((realtimeBlurView4 == null || realtimeBlurView4.getVisibility() != 0) && ((realtimeBlurView = this.rightContainer) == null || realtimeBlurView.getVisibility() != 0)) ? round : i8, round);
                this.leftContainer.setLayoutParams(layoutParams5);
            }
            this.leftContainer.setCornerRadius(f8);
            this.leftContainer.setBorder(f9, 452984831);
            if (z6) {
                this.leftContainer.setOutlineProvider(new ViewOutlineProvider() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.21
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view3, Outline outline) {
                        outline.setRoundRect(0, 0, view3.getWidth(), view3.getHeight(), f8);
                    }
                });
                this.leftContainer.setClipToOutline(true);
            } else {
                this.leftContainer.setClipToOutline(false);
            }
        }
        RealtimeBlurView realtimeBlurView5 = this.centerContainer;
        if (realtimeBlurView5 != null) {
            LinearLayout.LayoutParams layoutParams6 = (LinearLayout.LayoutParams) realtimeBlurView5.getLayoutParams();
            if (layoutParams6 != null) {
                layoutParams6.setMargins(i9, round, i8, round);
                this.centerContainer.setLayoutParams(layoutParams6);
            }
            this.centerContainer.setCornerRadius(f8);
            this.centerContainer.setBorder(f9, 452984831);
            if (z6) {
                this.centerContainer.setOutlineProvider(new ViewOutlineProvider() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.22
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view3, Outline outline) {
                        outline.setRoundRect(0, 0, view3.getWidth(), view3.getHeight(), f8);
                    }
                });
                this.centerContainer.setClipToOutline(true);
            } else {
                this.centerContainer.setClipToOutline(false);
            }
        }
        RealtimeBlurView realtimeBlurView6 = this.rightContainer;
        if (realtimeBlurView6 != null) {
            LinearLayout.LayoutParams layoutParams7 = (LinearLayout.LayoutParams) realtimeBlurView6.getLayoutParams();
            if (layoutParams7 != null) {
                layoutParams7.setMargins(i9, round, round, round);
                this.rightContainer.setLayoutParams(layoutParams7);
            }
            this.rightContainer.setCornerRadius(f8);
            this.rightContainer.setBorder(f9, 452984831);
            if (z6) {
                this.rightContainer.setOutlineProvider(new ViewOutlineProvider() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.23
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view3, Outline outline) {
                        outline.setRoundRect(0, 0, view3.getWidth(), view3.getHeight(), f8);
                    }
                });
                this.rightContainer.setClipToOutline(true);
            } else {
                this.rightContainer.setClipToOutline(false);
            }
        }
        if (this.mode != 0 || (frameLayout = this.mapContainer) == null) {
            return;
        }
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.24
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                DesktopAssistantActivity.this.mapContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                DesktopAssistantActivity.this.lambda$setupMode$71();
            }
        });
        this.mapContainer.requestLayout();
    }

    private void applyTopInfoBarStyle() {
        SharedPreferences sharedPreferences;
        if (this.topInfoBarContainer == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        int i7 = sharedPreferences.getInt("desktop_blur_radius", 10);
        boolean z6 = this.prefs.getBoolean("desktop_transparent_bg", false);
        boolean isCurrentNightMode = isCurrentNightMode();
        int i8 = this.prefs.getInt("car_control_card_alpha", 90);
        int argb = isCurrentNightMode ? Color.argb((int) ((this.prefs.getInt("desktop_night_mode_alpha", 70) * 255) / 100.0f), 0, 0, 0) : z6 ? 0 : 872415231;
        int argb2 = Color.argb((int) (((100 - i8) / 100.0f) * 255.0f), 0, 0, 0);
        this.topInfoBarContainer.setBackgroundColor(0);
        this.topInfoBarContainer.setBlurEnabled(true);
        this.topInfoBarContainer.setBlurRadius(i7);
        this.topInfoBarContainer.setOverlayColor(argb);
        View view = this.topInfoBarSurface;
        if (view != null) {
            view.setBackgroundColor(argb2);
        }
    }

    private void applyWeatherEffects() {
        SharedPreferences sharedPreferences;
        if (this.viewWeatherAmbient == null) {
            this.viewWeatherAmbient = (WeatherAmbientView) findViewById(g4.p.view_weather_ambient);
        }
        if (this.viewWeatherAmbient == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        boolean z6 = sharedPreferences.getBoolean("desktop_weather_fx_enabled", false);
        boolean z7 = this.prefs.getBoolean("desktop_ambient_fx_enabled", false);
        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = this.clockWeatherController;
        if (desktopAssistantClockWeatherController != null) {
            desktopAssistantClockWeatherController.setWeatherObserver(new z(this, 10));
        }
        long j7 = (z6 || z7) ? 120L : 33L;
        RealtimeBlurView realtimeBlurView = this.leftContainer;
        if (realtimeBlurView != null) {
            realtimeBlurView.setMinUpdateIntervalMs(j7);
        }
        RealtimeBlurView realtimeBlurView2 = this.rightContainer;
        if (realtimeBlurView2 != null) {
            realtimeBlurView2.setMinUpdateIntervalMs(j7);
        }
        RealtimeBlurView realtimeBlurView3 = this.centerContainer;
        if (realtimeBlurView3 != null) {
            realtimeBlurView3.setMinUpdateIntervalMs(j7);
        }
        RealtimeBlurView realtimeBlurView4 = this.dockContainer;
        if (realtimeBlurView4 != null) {
            realtimeBlurView4.setMinUpdateIntervalMs(j7);
        }
        RealtimeBlurView realtimeBlurView5 = this.topInfoBarContainer;
        if (realtimeBlurView5 != null) {
            realtimeBlurView5.setMinUpdateIntervalMs(j7);
        }
        this.viewWeatherAmbient.setEffectsEnabled(z6, z7);
        this.viewWeatherAmbient.setNightMode(isCurrentNightMode());
        int i7 = this.prefs.getInt("desktop_ambient_festival", 0);
        if (i7 != 0) {
            long j8 = this.prefs.getLong("desktop_ambient_festival_at", 0L);
            if (j8 == 0) {
                this.prefs.edit().putLong("desktop_ambient_festival_at", System.currentTimeMillis()).apply();
            } else if (System.currentTimeMillis() - j8 > t1.f.T_MS_DAY) {
                this.prefs.edit().putInt("desktop_ambient_festival", 0).remove("desktop_ambient_festival_at").apply();
                i7 = 0;
            }
        }
        this.viewWeatherAmbient.setFestivalOption(i7);
        this.viewWeatherAmbient.setAmbientScale(this.prefs.getFloat("desktop_ambient_scale", 2.0f));
        this.viewWeatherAmbient.getFestivalLabel();
        int i8 = this.prefs.getInt("desktop_weather_fx_preview", 0);
        if (i8 > 0) {
            String[][] strArr = WEATHER_PREVIEW;
            if (i8 < strArr.length) {
                WeatherAmbientView weatherAmbientView = this.viewWeatherAmbient;
                String[] strArr2 = strArr[i8];
                weatherAmbientView.setWeather(strArr2[0], strArr2[1]);
                if (!z6 || z7) {
                    this.viewWeatherAmbient.l();
                }
                WeatherAmbientView weatherAmbientView2 = this.viewWeatherAmbient;
                weatherAmbientView2.f6065i0 = false;
                Choreographer.getInstance().removeFrameCallback(weatherAmbientView2.f6075n0);
                return;
            }
        }
        TextView textView = this.textWeatherSummary;
        if (textView != null) {
            this.viewWeatherAmbient.setWeather(null, String.valueOf(textView.getText()));
        }
        if (z6) {
        }
        this.viewWeatherAmbient.l();
    }

    private void assignViewToContainer(ViewGroup viewGroup, int i7, float f7, boolean z6) {
        if (viewGroup == null) {
            return;
        }
        if (!z6) {
            viewGroup.setVisibility(8);
            return;
        }
        viewGroup.setVisibility(0);
        updateViewWeight(viewGroup, f7);
        View viewByType = getViewByType(i7);
        if (viewByType != null) {
            viewGroup.addView(viewByType, matchParentParams());
        }
    }

    private boolean canDragMusicProgress() {
        SharedPreferences sharedPreferences = this.prefs;
        return this.isMusicEnabled && DesktopAssistantMusicUiHelper.isSeekSupportedSource(sharedPreferences != null ? sharedPreferences.getInt("desktop_music_source", 0) : 0) && this.mLastKnownTotalDuration > 0;
    }

    private boolean canShowFloatingMap() {
        if (this.prefs == null) {
            return false;
        }
        LyricsFxView lyricsFxView = this.lyricsFxView;
        return (lyricsFxView == null || !lyricsFxView.c0()) && ((androidx.lifecycle.a0) getLifecycle()).f2136c.a(androidx.lifecycle.r.RESUMED) && this.mode == 0 && this.prefs.getBoolean("desktop_map_enabled", false) && isFloatingMapMode();
    }

    private void cancelVideoWatchdog() {
        this.videoStallWatchdogToken++;
        Runnable runnable = this.videoStallWatchdog;
        if (runnable != null) {
            this.videoWallpaperHandler.removeCallbacks(runnable);
            this.videoStallWatchdog = null;
        }
    }

    private List<String> collectSortedVideoWallpaperPaths(File file) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        if (file != null && file.exists() && file.isDirectory() && (listFiles = file.listFiles(new s0(3))) != null && listFiles.length != 0) {
            Arrays.sort(listFiles, new x(10));
            for (File file2 : listFiles) {
                arrayList.add(file2.getAbsolutePath());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cycleFloatingMapDisplayMode() {
        if (this.prefs == null) {
            return;
        }
        if (hasSecondFloatingMap()) {
            switchFloatingMap(this.prefs.getInt(KEY_MAP_ACTIVE_INDEX, 0) != 0 ? 0 : 1);
            return;
        }
        boolean isFloatingMapMode = isFloatingMapMode();
        this.prefs.edit().putInt(KEY_FLOATING_MAP_DISPLAY_MODE, isFloatingMapMode ? 1 : 0).putBoolean("desktop_map_calendar_enabled", isFloatingMapMode).apply();
        notifyFloatingMapModeChanged();
    }

    private void cycleThemeMode() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            return;
        }
        int i7 = sharedPreferences.getInt(KEY_THEME_MODE, 2);
        int i8 = i7 != 0 ? i7 != 2 ? 2 : 0 : 1;
        a.a.l(this.prefs, KEY_THEME_MODE, i8);
        if (i8 == 0) {
            androidx.appcompat.app.w.j(1);
        } else if (i8 != 1) {
            androidx.appcompat.app.w.j(-1);
        } else {
            androidx.appcompat.app.w.j(2);
        }
        recreate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cycleWallpaperMode() {
        if (this.prefs == null) {
            return;
        }
        a.a.l(this.prefs, KEY_WALLPAPER_DISPLAY_MODE, !isWallpaperStaticMode() ? 1 : 0);
        notifyWallpaperModeChanged();
    }

    private void cycleWeatherFxPreview() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            return;
        }
        int i7 = sharedPreferences.getInt("desktop_weather_fx_preview", 0) + 1;
        String[][] strArr = WEATHER_PREVIEW;
        int length = i7 % strArr.length;
        a.a.l(this.prefs, "desktop_weather_fx_preview", length);
        ToastHelper.show(this, length == 0 ? "天气特效：真实天气" : a.a.i(new StringBuilder("天气特效："), strArr[length][2], "（单击恢复真实天气）"));
        applyWeatherEffects();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decodeWallpaperAsync(String str, int i7, boolean z6) {
        new Thread(new p0(this, i7, str, z6, 1)).start();
    }

    private void detachView(View view) {
        if (view == null || view.getParent() == null) {
            return;
        }
        ((ViewGroup) view.getParent()).removeView(view);
    }

    private int dpToPx(int i7) {
        return Math.round(i7 * getResources().getDisplayMetrics().density);
    }

    private void ensureDesktopSpectrumAnalyzer() {
        if (this.desktopSpectrumAnalyzer != null) {
            return;
        }
        m4.a aVar = new m4.a(this);
        this.desktopSpectrumAnalyzer = aVar;
        z zVar = new z(this, 8);
        synchronized (aVar.f7552b) {
            aVar.f7554d = zVar;
        }
        int i7 = this.desktopSpectrumBarCount;
        if (i7 > 0) {
            this.desktopSpectrumAnalyzer.c(i7);
        }
    }

    private void ensureRoundCornerDisabledForVideo() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null || !sharedPreferences.getBoolean("desktop_round_corner_enabled", false)) {
            return;
        }
        this.prefs.edit().putBoolean("desktop_round_corner_enabled", false).apply();
        sendBroadcast(new Intent("com.navi.tool.DESKTOP_ROUND_CORNER_UPDATED"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0287 A[Catch: Exception -> 0x028e, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x028e, blocks: (B:113:0x0233, B:115:0x023b, B:116:0x0242, B:118:0x025c, B:126:0x0287, B:132:0x0240), top: B:112:0x0233 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0297 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.navi.tool.ui.extra.DesktopAssistantActivity.AppItem> fetchAppList() {
        /*
            Method dump skipped, instructions count: 926
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.fetchAppList():java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getActiveMapPackage() {
        return AppDetectionUtils.getActiveFloatingMapPackage(this.prefs);
    }

    private int getEffectiveLyricsLines() {
        SharedPreferences sharedPreferences;
        int max = Math.max(3, Math.min(8, this.lyricsLines));
        if (shouldShowClockWeatherHeader()) {
            return Math.min(max, (this.isMusicEnabled && (sharedPreferences = this.prefs) != null && sharedPreferences.getBoolean("desktop_music_show_album_art", false)) ? 3 : 5);
        }
        return max;
    }

    private int getFloatingMapDisplayMode() {
        if (this.prefs == null || hasSecondFloatingMap()) {
            return 0;
        }
        if (this.prefs.contains(KEY_FLOATING_MAP_DISPLAY_MODE)) {
            return this.prefs.getInt(KEY_FLOATING_MAP_DISPLAY_MODE, 0);
        }
        String string = this.prefs.getString(PREF_KEY_MAP_PACKAGE, null);
        return ((string == null || string.isEmpty()) && this.prefs.getBoolean("desktop_map_calendar_enabled", false)) ? 1 : 0;
    }

    private Drawable getFloatingMapModeIcon() {
        return AppDetectionUtils.getFloatingMapModeIcon(this, this.prefs);
    }

    private String getFloatingMapModeLabel() {
        return AppDetectionUtils.getFloatingMapModeLabel(this, this.prefs);
    }

    private String getMapPackageAt(int i7) {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString(i7 == 2 ? PREF_KEY_MAP_PACKAGE_2 : PREF_KEY_MAP_PACKAGE, null);
    }

    private int getThemeModeIconRes() {
        SharedPreferences sharedPreferences = this.prefs;
        int i7 = sharedPreferences != null ? sharedPreferences.getInt(KEY_THEME_MODE, 2) : 2;
        return i7 != 0 ? i7 != 1 ? g4.o.ic_theme_auto : g4.o.ic_theme_dark : g4.o.ic_theme_light;
    }

    private String getTodayWallpaperSubFolder() {
        switch (Calendar.getInstance().get(7)) {
            case 1:
                return "sunday";
            case 2:
                return "monday";
            case 3:
                return "tuesday";
            case 4:
                return "wednesday";
            case 5:
                return "thursday";
            case 6:
                return "friday";
            case 7:
                return "saturday";
            default:
                return null;
        }
    }

    private View getViewByType(int i7) {
        if (i7 == 0) {
            return this.mapViewContent;
        }
        if (i7 == 1) {
            return this.lyricsContentView;
        }
        if (i7 != 2) {
            return null;
        }
        return this.carControlContainer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getViewWeightsSum() {
        float[] fArr = this.viewWeights;
        float f7 = CropImageView.DEFAULT_ASPECT_RATIO;
        for (float f8 : fArr) {
            f7 += f8;
        }
        return f7;
    }

    private int getWallpaperModeIconRes() {
        return isWallpaperStaticMode() ? g4.o.ic_wallpaper_static_mode : g4.o.ic_wallpaper_dynamic_mode;
    }

    private String getWallpaperModeLabel() {
        return isWallpaperStaticMode() ? "静态壁纸" : "动态壁纸";
    }

    private static float gradientIntensityToBrightness(int i7) {
        return ((Math.max(0, Math.min(100, i7)) / 100.0f) * 0.5f) + 0.58f;
    }

    private void handleHvacAutoClick() {
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
        if (desktopAssistantCarControlHelper != null) {
            desktopAssistantCarControlHelper.handleHvacAutoClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMusicProgressTouch(View view, MotionEvent motionEvent) {
        if (motionEvent == null || !canDragMusicProgress()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mIsDraggingMusicProgress = true;
            view.getParent().requestDisallowInterceptTouchEvent(true);
            previewMusicSeek(motionEvent.getX(), view.getWidth());
            return true;
        }
        if (actionMasked == 1) {
            long previewMusicSeek = previewMusicSeek(motionEvent.getX(), view.getWidth());
            this.mIsDraggingMusicProgress = false;
            DesktopAssistantMusicUiHelper.dispatchMusicSeek(this, this.prefs, this.mLastKnownCurrentPosition, previewMusicSeek);
            this.mLastKnownCurrentPosition = previewMusicSeek;
            DesktopAssistantMusicUiHelper.updateLyricsProgress(this.lyricsAdapter, this.lyricsRecyclerView, this.smoothScroller, previewMusicSeek, true);
            return true;
        }
        if (actionMasked == 2) {
            previewMusicSeek(motionEvent.getX(), view.getWidth());
            return true;
        }
        if (actionMasked != 3) {
            return false;
        }
        this.mIsDraggingMusicProgress = false;
        applyMusicProgressPreview(this.mLastKnownCurrentPosition);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0072, code lost:
    
        if (r4 != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0081, code lost:
    
        if (r4 != false) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0088 A[ADDED_TO_REGION] */
    /* renamed from: handleSwap, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void lambda$setupFloatingHandleInteraction$105(android.view.View r8) {
        /*
            r7 = this;
            boolean r0 = r7.isCarControlEnabled
            r1 = 1
            if (r0 != 0) goto L37
            boolean r8 = r7.isSwapped
            r8 = r8 ^ r1
            r7.isSwapped = r8
            android.content.SharedPreferences r8 = r7.prefs
            android.content.SharedPreferences$Editor r8 = r8.edit()
            java.lang.String r0 = "desktop_swap_pos"
            boolean r1 = r7.isSwapped
            android.content.SharedPreferences$Editor r8 = r8.putBoolean(r0, r1)
            r8.apply()
            android.content.SharedPreferences r8 = r7.prefs
            r0 = 1082130432(0x40800000, float:4.0)
            java.lang.String r1 = "desktop_left_weight"
            float r8 = r8.getFloat(r1, r0)
            android.content.SharedPreferences r0 = r7.prefs
            android.content.SharedPreferences$Editor r0 = r0.edit()
            r2 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 - r8
            android.content.SharedPreferences$Editor r8 = r0.putFloat(r1, r2)
            r8.apply()
            goto La3
        L37:
            com.navi.tool.view.RealtimeBlurView r0 = r7.leftContainer
            r2 = 0
            if (r0 == 0) goto L44
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L44
            r0 = r1
            goto L45
        L44:
            r0 = r2
        L45:
            com.navi.tool.view.RealtimeBlurView r3 = r7.centerContainer
            if (r3 == 0) goto L51
            int r3 = r3.getVisibility()
            if (r3 != 0) goto L51
            r3 = r1
            goto L52
        L51:
            r3 = r2
        L52:
            com.navi.tool.view.RealtimeBlurView r4 = r7.rightContainer
            if (r4 == 0) goto L5e
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L5e
            r4 = r1
            goto L5f
        L5e:
            r4 = r2
        L5f:
            android.view.View r5 = r7.mFloatingSplitHandle
            r6 = 2
            if (r8 != r5) goto L75
            if (r0 == 0) goto L6b
            if (r3 == 0) goto L6b
        L68:
            r6 = r1
        L69:
            r1 = r2
            goto L86
        L6b:
            if (r0 == 0) goto L70
            if (r4 == 0) goto L70
        L6f:
            goto L69
        L70:
            if (r3 == 0) goto L84
            if (r4 == 0) goto L84
            goto L86
        L75:
            if (r3 == 0) goto L7a
            if (r4 == 0) goto L7a
            goto L86
        L7a:
            if (r0 == 0) goto L7f
            if (r3 == 0) goto L7f
            goto L68
        L7f:
            if (r0 == 0) goto L84
            if (r4 == 0) goto L84
            goto L6f
        L84:
            r1 = -1
            r6 = r1
        L86:
            if (r1 < 0) goto La3
            if (r6 < 0) goto La3
            if (r1 == r6) goto La3
            int[] r8 = r7.viewOrder
            r0 = r8[r1]
            r2 = r8[r6]
            r8[r1] = r2
            r8[r6] = r0
            float[] r8 = r7.viewWeights
            r0 = r8[r1]
            r2 = r8[r6]
            r8[r1] = r2
            r8[r6] = r0
            r7.saveLayoutState()
        La3:
            r7.hideHandles()
            r7.setupContent()
            java.lang.String r8 = "位置已交换"
            com.navi.tool.utils.ToastHelper.show(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.lambda$setupFloatingHandleInteraction$105(android.view.View):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleVideoWallpaperError, reason: merged with bridge method [inline-methods] */
    public void lambda$startVideoWallpaper$90(String str) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        releaseVideoWallpaperPlayer();
        if (tryPlayNextVideoWallpaperAfterError(str)) {
            return;
        }
        this.isVideoWallpaperActive = false;
        this.videoWallpaperPaths.clear();
        this.currentVideoWallpaperIndex = 0;
        this.currentVideoWallpaperPath = null;
        TextureView textureView = this.videoWallpaperView;
        if (textureView != null) {
            textureView.setVisibility(8);
        }
        View view = this.videoWallpaperMask;
        if (view != null) {
            view.setVisibility(8);
        }
        loadWallpaper();
    }

    private boolean hasRecordAudioPermission() {
        return checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
    }

    private boolean hasSecondFloatingMap() {
        String mapPackageAt = getMapPackageAt(2);
        return (mapPackageAt == null || mapPackageAt.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideHandles() {
        FrameLayout frameLayout;
        this.isMusicAdjusting = false;
        removeHandleSafely(this.mFloatingSplitHandle);
        removeHandleSafely(this.mFloatingCenterHandle);
        if (this.mode != 0 || (frameLayout = this.mapContainer) == null) {
            return;
        }
        frameLayout.postDelayed(new b1(this, 18), 100L);
    }

    private void hideMapFloat() {
        Runnable runnable;
        FrameLayout frameLayout = this.mapContainer;
        if (frameLayout != null && (runnable = this.oneSecondCornerRetryRunnable) != null) {
            frameLayout.removeCallbacks(runnable);
        }
        removeCornerMasks();
        FrameLayout frameLayout2 = this.mapContainer;
        if (frameLayout2 != null && (frameLayout2.getParent() instanceof RealtimeBlurView)) {
            ((RealtimeBlurView) this.mapContainer.getParent()).setBlurRadius(20.0f);
        }
        lambda$switchFloatingMap$0(getMapPackageAt(1));
        lambda$switchFloatingMap$0(getMapPackageAt(2));
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void initAlbumArtAnimation() {
        CardView cardView = this.cardAlbumArt;
        if (cardView == null) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(cardView, y.c.ROTATION, CropImageView.DEFAULT_ASPECT_RATIO, 360.0f);
        this.albumArtAnimator = ofFloat;
        ofFloat.setDuration(VIDEO_WATCHDOG_PREPARE_TIMEOUT_MS);
        this.albumArtAnimator.setInterpolator(new LinearInterpolator());
        this.albumArtAnimator.setRepeatCount(-1);
        this.albumArtAnimator.setRepeatMode(1);
    }

    private void initAppDisplay() {
        final int i7 = 0;
        this.mapViewContent.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.navi.tool.ui.extra.y
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean lambda$initAppDisplay$62;
                boolean lambda$initAppDisplay$63;
                switch (i7) {
                    case 0:
                        lambda$initAppDisplay$62 = DesktopAssistantActivity.lambda$initAppDisplay$62(view);
                        return lambda$initAppDisplay$62;
                    default:
                        lambda$initAppDisplay$63 = DesktopAssistantActivity.lambda$initAppDisplay$63(view);
                        return lambda$initAppDisplay$63;
                }
            }
        });
        final int i8 = 1;
        this.mapContainer.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.navi.tool.ui.extra.y
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                boolean lambda$initAppDisplay$62;
                boolean lambda$initAppDisplay$63;
                switch (i8) {
                    case 0:
                        lambda$initAppDisplay$62 = DesktopAssistantActivity.lambda$initAppDisplay$62(view);
                        return lambda$initAppDisplay$62;
                    default:
                        lambda$initAppDisplay$63 = DesktopAssistantActivity.lambda$initAppDisplay$63(view);
                        return lambda$initAppDisplay$63;
                }
            }
        });
        this.mapContainer.postDelayed(new b1(this, 7), 1000L);
    }

    private void initMusicService() {
        int i7 = this.prefs.getInt("desktop_music_source", 0);
        DesktopAssistantMusicUiHelper.startMusicManagerBySource(this, i7, this.lyricsAdapter);
        if (i7 == 0) {
            QQMusicLyricReceiver.requestRefresh(this);
        }
        Intent intent = new Intent(this, (Class<?>) FloatingWindowService.class);
        intent.setAction("update_lyric_config");
        intent.putExtra(FloatingWindowService.KEY_MUSIC_SOURCE, i7);
        startForegroundService(intent);
        loadCachedMusicInfo();
    }

    private void initTopInfoBar() {
        this.topInfoBarContainer = (RealtimeBlurView) findViewById(g4.p.top_info_bar_container);
        this.topInfoBarSurface = findViewById(g4.p.top_info_bar_surface);
        this.topInfoLeftContainer = findViewById(g4.p.top_info_left_container);
        this.topTextDate = (TextClock) findViewById(g4.p.text_top_date);
        this.topImgWifi = (ImageView) findViewById(g4.p.img_top_wifi);
        this.topImgSignal = (ImageView) findViewById(g4.p.img_top_signal);
        this.recyclerTopInfo = (RecyclerView) findViewById(g4.p.recycler_top_info);
        RealtimeBlurView realtimeBlurView = this.topInfoBarContainer;
        if (realtimeBlurView != null) {
            ViewGroup viewGroup = this.rootLayout;
            if (viewGroup instanceof ViewGroup) {
                realtimeBlurView.setupWith(viewGroup);
            }
        }
        if (this.recyclerTopInfo != null) {
            this.recyclerTopInfo.setLayoutManager(new LinearLayoutManager(0));
            DesktopAssistantTopInfoAdapter desktopAssistantTopInfoAdapter = new DesktopAssistantTopInfoAdapter(this.prefs, this.carControlHelper);
            this.topInfoAdapter = desktopAssistantTopInfoAdapter;
            this.recyclerTopInfo.setAdapter(desktopAssistantTopInfoAdapter);
        }
        updateTopInfoBarVisibility();
        updateAuxiliaryClockVisibility();
    }

    private void initViews() {
        ViewGroup viewGroup = (ViewGroup) findViewById(g4.p.root_layout);
        this.rootLayout = viewGroup;
        int i7 = 1;
        viewGroup.setOnTouchListener(new x0(this, i7));
        this.contentContainer = (ViewGroup) findViewById(g4.p.content_container);
        this.splitContainer = (LinearLayout) findViewById(g4.p.split_container);
        this.leftContainer = (RealtimeBlurView) findViewById(g4.p.left_container);
        this.rightContainer = (RealtimeBlurView) findViewById(g4.p.right_container);
        this.centerContainer = (RealtimeBlurView) findViewById(g4.p.center_container);
        this.dockContainer = (RealtimeBlurView) findViewById(g4.p.dock_container);
        RecyclerView recyclerView = (RecyclerView) findViewById(g4.p.recycler_dock_apps);
        this.recyclerDockApps = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        this.btnSwitchMode = (ImageView) findViewById(g4.p.btn_switch_mode);
        this.textClock = (TextClock) findViewById(g4.p.text_clock);
        this.layoutStatusIcons = (LinearLayout) findViewById(g4.p.layout_status_icons);
        this.imgSignalIcon = (ImageView) findViewById(g4.p.img_signal_icon);
        this.imgWifiIcon = (ImageView) findViewById(g4.p.img_wifi_icon);
        this.leftContainer.setupWith(this.rootLayout);
        this.leftContainer.setBlurRadius(20.0f);
        this.rightContainer.setupWith(this.rootLayout);
        this.rightContainer.setBlurRadius(20.0f);
        RealtimeBlurView realtimeBlurView = this.centerContainer;
        if (realtimeBlurView != null) {
            realtimeBlurView.setupWith(this.rootLayout);
            this.centerContainer.setBlurRadius(20.0f);
        }
        RealtimeBlurView realtimeBlurView2 = this.dockContainer;
        if (realtimeBlurView2 != null) {
            realtimeBlurView2.setupWith(this.rootLayout);
            this.dockContainer.setBlurRadius(20.0f);
        }
        this.splitDivider = findViewById(g4.p.split_divider);
        this.centerDivider = findViewById(g4.p.center_divider);
        this.lyricsContentView = findViewById(g4.p.lyrics_view_content);
        this.mapViewContent = findViewById(g4.p.map_view_content);
        this.mapContainer = (FrameLayout) findViewById(g4.p.map_container);
        this.mapCalendarCardView = (DesktopMapCalendarCardView) findViewById(g4.p.view_map_calendar_card);
        this.wallpaperView = (ImageView) findViewById(g4.p.wallpaper_view);
        this.coverGradientBackground = (CoverGradientBackgroundView) findViewById(g4.p.cover_gradient_background);
        this.videoWallpaperView = (TextureView) findViewById(g4.p.video_wallpaper_view);
        this.videoWallpaperMask = findViewById(g4.p.video_wallpaper_mask);
        TextureView textureView = this.videoWallpaperView;
        if (textureView != null) {
            textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.15
                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i8, int i9) {
                    DesktopAssistantActivity.this.videoWallpaperSurface = new Surface(surfaceTexture);
                    if (!DesktopAssistantActivity.this.isVideoWallpaperActive || DesktopAssistantActivity.this.currentVideoWallpaperPath == null) {
                        return;
                    }
                    DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
                    desktopAssistantActivity.startVideoWallpaper(desktopAssistantActivity.currentVideoWallpaperPath);
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    DesktopAssistantActivity.this.releaseVideoWallpaperPlayer();
                    if (DesktopAssistantActivity.this.videoWallpaperSurface == null) {
                        return true;
                    }
                    DesktopAssistantActivity.this.videoWallpaperSurface.release();
                    DesktopAssistantActivity.this.videoWallpaperSurface = null;
                    return true;
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i8, int i9) {
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }
            });
        }
        this.controlOverlay = (FrameLayout) findViewById(g4.p.control_overlay);
        this.controlHandleContainer = (LinearLayout) findViewById(g4.p.control_handle_container);
        this.btnControlHandle = (ImageView) findViewById(g4.p.btn_control_handle);
        this.textControlGuide = (TextView) findViewById(g4.p.text_control_guide);
        this.lyricsRecyclerView = (RecyclerView) findViewById(g4.p.recycler_lyrics);
        LyricsFxView lyricsFxView = (LyricsFxView) findViewById(g4.p.lyrics_fx_view);
        this.lyricsFxView = lyricsFxView;
        if (lyricsFxView != null) {
            lyricsFxView.setOnExitListener(new b1(this, 22));
        }
        RecyclerView recyclerView2 = this.lyricsRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setLayoutManager(new LinearLayoutManager(1));
            this.lyricsRecyclerView.setItemAnimator(null);
            LyricsAdapter lyricsAdapter = new LyricsAdapter(this);
            this.lyricsAdapter = lyricsAdapter;
            lyricsAdapter.setFontSize(this.lyricsFontSize);
            this.lyricsRecyclerView.setAdapter(this.lyricsAdapter);
            this.lyricsAdapter.setOnLineClickListener(new z(this, 7));
            this.lyricsRecyclerView.post(new b1(this, 23));
            updateLyricsColor();
        }
        this.carControlContainer = this.carControlHelper.initCarControlViews();
        this.smoothScroller = new androidx.recyclerview.widget.x0(this) { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.16
            @Override // androidx.recyclerview.widget.x0
            public int calculateDtToFit(int i8, int i9, int i10, int i11, int i12) {
                return (((i11 - i10) / 2) + i10) - (((i9 - i8) / 2) + i8);
            }

            @Override // androidx.recyclerview.widget.x0
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 100.0f / displayMetrics.densityDpi;
            }
        };
        this.btnPrev = (ImageButton) findViewById(g4.p.btn_prev);
        this.btnPlayPause = (ImageButton) findViewById(g4.p.btn_play_pause);
        this.btnNext = (ImageButton) findViewById(g4.p.btn_next);
        this.layoutClockWeatherHeader = findViewById(g4.p.layout_clock_weather_header);
        TextView textView = (TextView) findViewById(g4.p.text_weather_location);
        this.textWeatherLocation = textView;
        if (textView != null) {
            textView.setSelected(true);
        }
        TextView textView2 = (TextView) findViewById(g4.p.text_weather_summary);
        this.textWeatherSummary = textView2;
        if (textView2 != null) {
            textView2.setOnClickListener(new n0(this, i7));
            this.textWeatherSummary.setOnLongClickListener(new g0(i7, this));
        }
        TextView textView3 = (TextView) findViewById(g4.p.text_weather_clock);
        int i8 = 2;
        if (textView3 != null) {
            textView3.setOnLongClickListener(new g0(i8, this));
        }
        this.textWeatherLunar = (TextView) findViewById(g4.p.text_weather_lunar);
        this.imgWeatherIcon = (ImageView) findViewById(g4.p.img_weather_icon);
        this.imgMusicAppIcon = (ImageView) findViewById(g4.p.img_music_app_icon);
        TextView textView4 = (TextView) findViewById(g4.p.text_song_title);
        this.textSongTitle = textView4;
        int i9 = 0;
        if (textView4 != null) {
            textView4.setSelected(true);
            this.textSongTitle.setClickable(true);
            this.textSongTitle.setFocusable(false);
            this.textSongTitle.setOnClickListener(new n0(this, i8));
        }
        this.textArtist = (TextView) findViewById(g4.p.text_artist);
        this.textCurrentLyric = (TextView) findViewById(g4.p.text_current_lyric);
        this.viewProgress = findViewById(g4.p.view_progress);
        this.layoutProgressContainer = findViewById(g4.p.layout_progress_container);
        this.viewMusicSeekTouchArea = findViewById(g4.p.view_music_seek_touch_area);
        this.viewWeatherAmbient = (WeatherAmbientView) findViewById(g4.p.view_weather_ambient);
        this.textCurrentDuration = (TextView) findViewById(g4.p.text_current_duration);
        this.textTotalDuration = (TextView) findViewById(g4.p.text_total_duration);
        this.clockWeatherController = new DesktopAssistantClockWeatherController(this, this.textWeatherLocation, this.textWeatherSummary, this.imgWeatherIcon);
        updateClockWeatherHeaderVisibility();
        View view = this.layoutClockWeatherHeader;
        if (view != null) {
            view.setOnClickListener(new n0(this, 3));
        }
        updateFloatingMusicAppIcon();
        updateWeatherLunarText();
        this.containerDisc = (FrameLayout) findViewById(g4.p.container_disc);
        this.cardAlbumArt = (CardView) findViewById(g4.p.card_album_art);
        this.imgAlbumArtFull = (ImageView) findViewById(g4.p.img_album_art_full);
        this.layoutSongTitleRow = findViewById(g4.p.layout_song_title_row);
        ImageView imageView = (ImageView) findViewById(g4.p.img_fav_heart);
        this.imgFavHeart = imageView;
        int i10 = 4;
        if (imageView != null) {
            imageView.setOnClickListener(new n0(this, i10));
        }
        ImageView imageView2 = (ImageView) findViewById(g4.p.img_loop_mode);
        this.imgLoopMode = imageView2;
        if (imageView2 != null) {
            imageView2.setOnClickListener(new n0(this, i9));
        }
        SpectrumView spectrumView = (SpectrumView) findViewById(g4.p.view_spectrum);
        this.viewSpectrum = spectrumView;
        spectrumView.setOnBarCountReadyListener(new z(this, 6));
        initAlbumArtAnimation();
        this.appListRecyclerView = (RecyclerView) findViewById(g4.p.recycler_app_list);
        setupMusicControls();
        initAppDisplay();
        initTopInfoBar();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DesktopAssistantMusicUiHelper.ACTION_MUSIC_APP_UPDATED);
        intentFilter.addAction(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.17
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DesktopAssistantActivity.this.updateMusicUI();
                if (intent != null && intent.getBooleanExtra("clock_weather_location_mode_changed", false) && DesktopAssistantActivity.this.clockWeatherController != null && DesktopAssistantActivity.this.shouldShowClockWeatherHeader()) {
                    DesktopAssistantActivity.this.clockWeatherController.onLocationDisplayModeChanged();
                }
                if (intent == null || !intent.getBooleanExtra("clock_weather_alert_setting_changed", false) || DesktopAssistantActivity.this.clockWeatherController == null) {
                    return;
                }
                DesktopAssistantActivity.this.clockWeatherController.onWeatherAlertPreferenceChanged(intent.getBooleanExtra("clock_weather_alert_enabled", true));
            }
        };
        this.musicUiReceiver = broadcastReceiver;
        registerReceiver(broadcastReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter("com.navi.tool.ACTION_MAP_APP_UPDATED");
        BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.18
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DesktopAssistantActivity.this.updateMapPlaceholderState();
                DesktopAssistantActivity.this.updateMapCalendarVisibility();
                DesktopAssistantActivity.this.setupContent();
                if (DesktopAssistantActivity.this.prefs == null || DesktopAssistantActivity.this.getActiveMapPackage() == null) {
                    return;
                }
                DesktopAssistantActivity.this.lambda$setupMode$71();
            }
        };
        this.mapUiReceiver = broadcastReceiver2;
        registerReceiver(broadcastReceiver2, intentFilter2);
        this.wallpaperReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.19
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if ("com.navi.tool.WALLPAPER_UPDATED".equals(intent.getAction())) {
                    DesktopAssistantActivity.this.loadWallpaper();
                    DesktopAssistantActivity.this.setupMode();
                }
            }
        };
        IntentFilter intentFilter3 = new IntentFilter("com.navi.tool.WALLPAPER_UPDATED");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.wallpaperReceiver, intentFilter3, 4);
        } else {
            registerReceiver(this.wallpaperReceiver, intentFilter3);
        }
    }

    private boolean isCachedLyricsMatchCurrentTrack(MusicMeta musicMeta) {
        return musicMeta != null && TextUtils.equals(normalizeMusicText(musicMeta.title), normalizeMusicText(this.mLastKnownTitle)) && TextUtils.equals(normalizeMusicText(musicMeta.artist), normalizeMusicText(this.mLastKnownArtist));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCurrentNightMode() {
        return (getResources().getConfiguration().uiMode & 48) == 32;
    }

    private boolean isCustomMusicSourceCandidate(DesktopAssistantMusicUiHelper.MusicSourceCandidate musicSourceCandidate) {
        return musicSourceCandidate != null && TextUtils.equals(CUSTOM_MUSIC_SOURCE_PACKAGE, musicSourceCandidate.packageName);
    }

    private static boolean isDockAppDefaultEnabled(String str) {
        return DEFAULT_DOCK_APPS.contains(str);
    }

    private boolean isFloatingMapMode() {
        return getFloatingMapDisplayMode() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMixedWallpaperEnabled() {
        SharedPreferences sharedPreferences = this.prefs;
        return (sharedPreferences == null || !sharedPreferences.getBoolean("wallpaper_mixed_play", false) || this.prefs.getBoolean("desktop_cover_gradient_enabled", false)) ? false : true;
    }

    private boolean isRandomWallpaperOrder() {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences != null && sharedPreferences.getBoolean("wallpaper_random_order", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isViewUnder(View view, float f7, float f8) {
        if (view != null && view.getVisibility() == 0) {
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                return rect.contains((int) f7, (int) f8);
            }
        }
        return false;
    }

    private boolean isWallpaperStaticMode() {
        SharedPreferences sharedPreferences = this.prefs;
        return sharedPreferences != null && sharedPreferences.getInt(KEY_WALLPAPER_DISPLAY_MODE, 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyCoverGradientFromEvent$44(Bitmap bitmap) {
        updateCoverGradientFromBitmap(bitmap);
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyWeatherEffects$36(String str, String str2) {
        WeatherAmbientView weatherAmbientView;
        SharedPreferences sharedPreferences = this.prefs;
        if ((sharedPreferences == null || sharedPreferences.getInt("desktop_weather_fx_preview", 0) == 0) && (weatherAmbientView = this.viewWeatherAmbient) != null) {
            weatherAmbientView.post(new w2(weatherAmbientView, str, str2, 6));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$collectSortedVideoWallpaperPaths$86(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".mp4");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$collectSortedVideoWallpaperPaths$87(File file, File file2) {
        String name = file.getName();
        String name2 = file2.getName();
        return name.length() != name2.length() ? name.length() - name2.length() : name.compareTo(name2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$decodeWallpaperAsync$95(int i7, Bitmap bitmap, boolean z6) {
        if (i7 != this.wallpaperToken || isFinishing() || isDestroyed() || bitmap == null) {
            return;
        }
        if (z6) {
            setWallpaperWithAnimation(bitmap);
        } else {
            this.wallpaperView.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$decodeWallpaperAsync$96(int i7, String str, boolean z6) {
        if (i7 != this.wallpaperToken || isFinishing() || isDestroyed()) {
            return;
        }
        runOnUiThread(new p0(this, i7, BitmapFactory.decodeFile(str), z6, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureDesktopSpectrumAnalyzer$40(float[] fArr) {
        if (this.viewSpectrum == null || isFinishing() || !this.isPlaying) {
            return;
        }
        this.viewSpectrum.setSpectrumLevels(fArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$ensureDesktopSpectrumAnalyzer$41(float[] fArr) {
        runOnUiThread(new z0(6, this, fArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$fetchAppList$77(List list, AppItem appItem, AppItem appItem2) {
        int indexOf = list.indexOf(appItem.packageName);
        int indexOf2 = list.indexOf(appItem2.packageName);
        if (indexOf != -1 && indexOf2 != -1) {
            return Integer.compare(indexOf, indexOf2);
        }
        if (indexOf != -1) {
            return -1;
        }
        if (indexOf2 != -1) {
            return 1;
        }
        String str = appItem.label;
        String str2 = s5.h0.FRAGMENT_ENCODE_SET;
        if (str == null) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        String str3 = appItem2.label;
        if (str3 != null) {
            str2 = str3;
        }
        return str.compareToIgnoreCase(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$fetchAppList$78(AppItem appItem, AppItem appItem2) {
        String str = appItem.label;
        String str2 = s5.h0.FRAGMENT_ENCODE_SET;
        if (str == null) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        String str3 = appItem2.label;
        if (str3 != null) {
            str2 = str3;
        }
        return str.compareToIgnoreCase(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initAppDisplay$62(View view) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initAppDisplay$63(View view) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initAppDisplay$64() {
        if (this.prefs.getString(PREF_KEY_MAP_PACKAGE, null) != null) {
            lambda$setupMode$71();
        }
        updateMapPlaceholderState();
        updateMapCalendarVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViews$52(View view, MotionEvent motionEvent) {
        if (this.gestureDetector == null || motionEvent.getPointerCount() != 1) {
            return false;
        }
        this.gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$53(int i7) {
        openLyricsFxPage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$54(View view) {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            return;
        }
        if (sharedPreferences.getInt("desktop_weather_fx_preview", 0) == 0) {
            ToastHelper.show(this, "长按可切换天气预览");
            return;
        }
        a.a.l(this.prefs, "desktop_weather_fx_preview", 0);
        applyWeatherEffects();
        ToastHelper.show(this, "已恢复真实天气");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViews$55(View view) {
        cycleWeatherFxPreview();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean lambda$initViews$56(View view) {
        WeatherAmbientView weatherAmbientView = this.viewWeatherAmbient;
        if (weatherAmbientView != null) {
            boolean z6 = false;
            if (weatherAmbientView.O && weatherAmbientView.H > 0 && weatherAmbientView.I > 0) {
                weatherAmbientView.S.clear();
                n1.z.b0();
                weatherAmbientView.f6057e0 = true;
                for (int i7 = 0; i7 < 70; i7++) {
                    float max = ((((i7 % 7) * 0.84f) / Math.max(1, 6)) + 0.08f) * weatherAmbientView.H;
                    float max2 = ((((i7 / 7) * 0.8f) / Math.max(1, 9)) + 0.1f) * weatherAmbientView.I;
                    r4.j b7 = weatherAmbientView.b(i7, weatherAmbientView.H, weatherAmbientView.I);
                    if (b7 != null) {
                        b7.f8571b = max;
                        b7.f8572c = max2;
                        b7.f8573d = CropImageView.DEFAULT_ASPECT_RATIO;
                        b7.f8574e = CropImageView.DEFAULT_ASPECT_RATIO;
                        b7.f8577h = 9999.0f;
                        b7.f8578i = 9999.0f;
                        if (b7.f8589t != null && b7.f8570a == 49) {
                            float f7 = max - max;
                            float f8 = max2 - max2;
                            for (int i8 = 0; i8 < b7.f8590u; i8++) {
                                float[] fArr = b7.f8589t;
                                int i9 = i8 * 2;
                                fArr[i9] = fArr[i9] + f7;
                                int i10 = i9 + 1;
                                fArr[i10] = fArr[i10] + f8;
                            }
                        }
                        weatherAmbientView.S.add(b7);
                    }
                }
                weatherAmbientView.f6057e0 = false;
                weatherAmbientView.U = 8.0f;
                weatherAmbientView.invalidate();
                if (n1.z.f7690c != null) {
                    z6 = true;
                }
            }
            String festivalLabel = this.viewWeatherAmbient.getFestivalLabel();
            if (z6) {
                ToastHelper.show(this, festivalLabel.isEmpty() ? "已生成全部 70 种氛围物体预览" : "已生成全部 70 种氛围物体预览 · 今日".concat(festivalLabel));
            } else {
                ToastHelper.show(this, "氛围图集生成中，请稍后再长按一次");
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$57(View view) {
        openLyricsFxPage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$58(View view) {
        if (this.isCarControlEnabled) {
            if (!this.prefs.getBoolean("desktop_double_click_swap", false)) {
                swapCarControlAndMusic();
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.lastClockWeatherSwapClick >= SWITCH_CLOSE_OLD_DELAY_MS) {
                this.lastClockWeatherSwapClick = elapsedRealtime;
            } else {
                this.lastClockWeatherSwapClick = 0L;
                swapCarControlAndMusic();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$59(View view) {
        onFavHeartClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$60(View view) {
        onLoopModeClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$61(int i7) {
        SharedPreferences sharedPreferences;
        this.desktopSpectrumBarCount = i7;
        ensureDesktopSpectrumAnalyzer();
        m4.a aVar = this.desktopSpectrumAnalyzer;
        if (aVar != null) {
            aVar.c(i7);
        }
        if (this.isPlaying && this.isMusicEnabled && (sharedPreferences = this.prefs) != null && sharedPreferences.getBoolean("desktop_music_spectrum_enabled", true)) {
            updateSpectrumPlaybackState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadAppList$73(List list) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        updateAppListUI(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0046, code lost:
    
        r7.run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0049, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ void lambda$loadAppList$74(long r3, int r5, boolean r6, java.lang.Runnable r7) {
        /*
            r2 = this;
            r0 = 0
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L12
            java.lang.Thread.sleep(r3)     // Catch: java.lang.Throwable -> La java.lang.InterruptedException -> Lc
            goto L12
        La:
            r3 = move-exception
            goto L4a
        Lc:
            if (r7 == 0) goto L11
            r7.run()
        L11:
            return
        L12:
            boolean r3 = r2.isFinishing()     // Catch: java.lang.Throwable -> La
            if (r3 != 0) goto L44
            boolean r3 = r2.isDestroyed()     // Catch: java.lang.Throwable -> La
            if (r3 == 0) goto L1f
            goto L44
        L1f:
            java.util.List r3 = r2.fetchAppList()     // Catch: java.lang.Throwable -> La
            java.util.concurrent.atomic.AtomicInteger r4 = r2.appListGen     // Catch: java.lang.Throwable -> La
            int r4 = r4.get()     // Catch: java.lang.Throwable -> La
            if (r5 == r4) goto L31
            if (r7 == 0) goto L30
            r7.run()
        L30:
            return
        L31:
            r2.cachedAppList = r3     // Catch: java.lang.Throwable -> La
            if (r6 == 0) goto L3e
            com.navi.tool.ui.extra.a0 r4 = new com.navi.tool.ui.extra.a0     // Catch: java.lang.Throwable -> La
            r5 = 1
            r4.<init>(r2, r3, r5)     // Catch: java.lang.Throwable -> La
            r2.runOnUiThread(r4)     // Catch: java.lang.Throwable -> La
        L3e:
            if (r7 == 0) goto L43
            r7.run()
        L43:
            return
        L44:
            if (r7 == 0) goto L49
            r7.run()
        L49:
            return
        L4a:
            if (r7 == 0) goto L4f
            r7.run()
        L4f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.lambda$loadAppList$74(long, int, boolean, java.lang.Runnable):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCachedCoverForGradient$47() {
        Bitmap decodeFile;
        try {
            File file = new File(new File(Environment.getExternalStorageDirectory(), CACHE_DIR_NAME), CACHE_FILE_COVER);
            if (file.exists() && (decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath())) != null) {
                updateCoverGradientFromBitmap(decodeFile);
                decodeFile.recycle();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCachedMusicInfo$32(TreeMap treeMap) {
        LyricsAdapter lyricsAdapter = this.lyricsAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setLyrics(treeMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCachedMusicInfo$33(Bitmap bitmap) {
        ImageView imageView = this.imgAlbumArtFull;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCachedMusicInfo$34() {
        MusicMeta musicMeta;
        Bitmap decodeFile;
        BufferedReader bufferedReader;
        try {
            File file = new File(Environment.getExternalStorageDirectory(), CACHE_DIR_NAME);
            if (file.exists()) {
                File file2 = new File(file, CACHE_FILE_META);
                if (file2.exists()) {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
                    try {
                        musicMeta = (MusicMeta) new Gson().fromJson((Reader) bufferedReader, MusicMeta.class);
                        bufferedReader.close();
                    } finally {
                    }
                } else {
                    musicMeta = null;
                }
                File file3 = new File(file, CACHE_FILE_LYRICS);
                if (file3.exists() && isCachedLyricsMatchCurrentTrack(musicMeta)) {
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
                    try {
                        TreeMap treeMap = (TreeMap) new Gson().fromJson(bufferedReader, new TypeToken<TreeMap<Long, String>>() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.11
                        }.getType());
                        if (treeMap != null) {
                            runOnUiThread(new z0(5, this, treeMap));
                        }
                        bufferedReader.close();
                    } finally {
                    }
                }
                File file4 = new File(file, CACHE_FILE_COVER);
                if (!file4.exists() || (decodeFile = BitmapFactory.decodeFile(file4.getAbsolutePath())) == null) {
                    return;
                }
                updateCoverGradientFromBitmap(decodeFile);
                runOnUiThread(new v0(this, decodeFile, 0));
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadCustomMusicApps$39(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadDockApps$49(PackageManager packageManager, AppInfo appInfo) {
        try {
            if ("com.navi.tool.fileassistant".equals(appInfo.getPackageName())) {
                startActivity(new Intent(this, (Class<?>) FileManagerActivity.class));
            } else if ("com.navi.tool.permissionassistant".equals(appInfo.getPackageName())) {
                startActivity(new Intent(this, (Class<?>) PermissionManagerActivity.class));
            } else if ("com.navi.tool.originalhome".equals(appInfo.getPackageName())) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                intent.addFlags(268435456);
                startActivity(intent);
            } else if (PKG_ADB_SWITCH.equals(appInfo.getPackageName())) {
                launchAdbSwitch();
            } else if ("com.navi.tool.desktopassistant".equals(appInfo.getPackageName())) {
                Intent intent2 = new Intent(this, (Class<?>) MainActivity.class);
                intent2.addFlags(335544320);
                intent2.putExtra("target_tab", "desktop_assistant");
                startActivity(intent2);
            } else if (PKG_THEME_MODE.equals(appInfo.getPackageName())) {
                cycleThemeMode();
            } else if (PKG_FLOATING_MAP_MODE.equals(appInfo.getPackageName())) {
                cycleFloatingMapDisplayMode();
            } else if (PKG_WALLPAPER_MODE.equals(appInfo.getPackageName())) {
                cycleWallpaperMode();
            } else {
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(appInfo.getPackageName());
                if (launchIntentForPackage != null) {
                    startActivity(launchIntentForPackage);
                } else {
                    ToastHelper.show(this, "无法启动应用");
                }
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            ToastHelper.show(this, "启动失败: " + e7.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadDockApps$50(List list, PackageManager packageManager) {
        DockAppAdapter dockAppAdapter = new DockAppAdapter(this, list, new c0(this, packageManager, 2));
        this.dockAdapter = dockAppAdapter;
        dockAppAdapter.setNightMode(isCurrentNightMode());
        this.recyclerDockApps.setAdapter(this.dockAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadDockApps$51() {
        String str;
        String[] strArr;
        int i7;
        String str2;
        String str3;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = getPackageManager();
        String string = this.prefs.getString("dock_app_order", s5.h0.FRAGMENT_ENCODE_SET);
        HashSet hashSet = new HashSet();
        String str4 = "原车桌面";
        String str5 = "权限助手";
        if (string == null || string.isEmpty()) {
            str = "ADB界面";
        } else {
            String[] split = string.split(",");
            int length = split.length;
            str = "ADB界面";
            int i8 = 0;
            while (i8 < length) {
                String[] strArr2 = split;
                String trim = split[i8].trim();
                if (trim.isEmpty()) {
                    i7 = length;
                    str2 = str4;
                    str3 = str5;
                } else {
                    i7 = length;
                    str2 = str4;
                    str3 = str5;
                    if (this.prefs.getBoolean("dock_app_enabled_".concat(trim), isDockAppDefaultEnabled(trim))) {
                        String string2 = this.prefs.getString("app_custom_name_".concat(trim), null);
                        if ("com.navi.tool.fileassistant".equals(trim)) {
                            if (string2 == null) {
                                string2 = "文件助手";
                            }
                            arrayList.add(new AppInfo(trim, string2, true));
                            hashSet.add(trim);
                        } else if ("com.navi.tool.permissionassistant".equals(trim)) {
                            if (string2 == null) {
                                string2 = str3;
                            }
                            arrayList.add(new AppInfo(trim, string2, true));
                            hashSet.add(trim);
                        } else if ("com.navi.tool.originalhome".equals(trim)) {
                            if (string2 == null) {
                                string2 = str2;
                            }
                            arrayList.add(new AppInfo(trim, string2, true));
                            hashSet.add(trim);
                        } else if (PKG_ADB_SWITCH.equals(trim)) {
                            if (string2 == null) {
                                string2 = str;
                            }
                            arrayList.add(new AppInfo(trim, string2, true));
                            hashSet.add(trim);
                        } else if ("com.navi.tool.desktopassistant".equals(trim)) {
                            if (string2 == null) {
                                string2 = "桌面设置";
                            }
                            arrayList.add(new AppInfo(trim, string2, true));
                            hashSet.add(trim);
                        } else if (PKG_THEME_MODE.equals(trim)) {
                            if (string2 == null) {
                                string2 = "主题模式";
                            }
                            arrayList.add(new AppInfo(trim, string2, true));
                            hashSet.add(trim);
                        } else if (PKG_FLOATING_MAP_MODE.equals(trim)) {
                            arrayList.add(new AppInfo(trim, getFloatingMapModeLabel(), true));
                            hashSet.add(trim);
                        } else if (PKG_WALLPAPER_MODE.equals(trim)) {
                            arrayList.add(new AppInfo(trim, getWallpaperModeLabel(), true));
                            hashSet.add(trim);
                        } else {
                            try {
                                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(trim, 0);
                                if (string2 == null) {
                                    string2 = packageManager.getApplicationLabel(applicationInfo).toString();
                                }
                                arrayList.add(new AppInfo(trim, string2, true));
                                hashSet.add(trim);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
                i8++;
                length = i7;
                split = strArr2;
                str4 = str2;
                str5 = str3;
            }
        }
        String str6 = str4;
        String str7 = str5;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 0).iterator();
        while (it.hasNext()) {
            ResolveInfo next = it.next();
            String str8 = next.activityInfo.packageName;
            if (!hashSet.contains(str8)) {
                Iterator<ResolveInfo> it2 = it;
                if (this.prefs.getBoolean(a.a.B("dock_app_enabled_", str8), isDockAppDefaultEnabled(str8))) {
                    String string3 = this.prefs.getString("app_custom_name_" + str8, null);
                    if (string3 == null) {
                        string3 = next.loadLabel(packageManager).toString();
                    }
                    a.a.x(str8, string3, true, arrayList);
                    hashSet.add(str8);
                }
                it = it2;
            }
        }
        String[] strArr3 = {"com.navi.tool.fileassistant", "com.navi.tool.permissionassistant", "com.navi.tool.originalhome", PKG_ADB_SWITCH, "com.navi.tool.desktopassistant", PKG_THEME_MODE, PKG_FLOATING_MAP_MODE, PKG_WALLPAPER_MODE};
        int i9 = 0;
        while (i9 < 8) {
            String str9 = strArr3[i9];
            if (hashSet.contains(str9)) {
                strArr = strArr3;
            } else {
                strArr = strArr3;
                if (this.prefs.getBoolean(a.a.B("dock_app_enabled_", str9), isDockAppDefaultEnabled(str9))) {
                    String string4 = this.prefs.getString("app_custom_name_" + str9, null);
                    if (PKG_FLOATING_MAP_MODE.equals(str9)) {
                        string4 = getFloatingMapModeLabel();
                    } else if (PKG_WALLPAPER_MODE.equals(str9)) {
                        string4 = getWallpaperModeLabel();
                    } else if (string4 == null) {
                        string4 = str9.contains("file") ? "文件助手" : str9.contains("permission") ? str7 : str9.contains("originalhome") ? str6 : PKG_ADB_SWITCH.equals(str9) ? str : str9.contains("desktop") ? "桌面设置" : PKG_THEME_MODE.equals(str9) ? "主题模式" : str9;
                    }
                    a.a.x(str9, string4, true, arrayList);
                    hashSet.add(str9);
                    i9++;
                    strArr3 = strArr;
                }
            }
            i9++;
            strArr3 = strArr;
        }
        runOnUiThread(new w2(this, arrayList, packageManager, 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadLaunchableAppsOnWorker$68(LabeledApp labeledApp, LabeledApp labeledApp2) {
        return labeledApp.label.compareToIgnoreCase(labeledApp2.label);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$loadWallpaper$80(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$loadWallpaper$81(File file, String str) {
        return str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$loadWallpaper$82(File file, File file2) {
        String name = file.getName();
        String name2 = file2.getName();
        return name.length() != name2.length() ? name.length() - name2.length() : name.compareTo(name2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadWallpaper$83(int i7, boolean z6, List list, boolean z7, List list2, Bitmap bitmap) {
        if (isFinishing() || isDestroyed() || i7 != this.wallpaperLoadRequestId) {
            return;
        }
        this.hasResolvedWallpaperOnce = true;
        if (z6) {
            this.isVideoWallpaperActive = true;
            this.videoWallpaperPaths.clear();
            this.videoWallpaperPaths.addAll(list);
            this.wallpaperPaths.clear();
            if (z7) {
                this.wallpaperPaths.addAll(list2);
            }
            ensureRoundCornerDisabledForVideo();
            TextureView textureView = this.videoWallpaperView;
            if (textureView != null) {
                textureView.setVisibility(0);
            }
            View view = this.videoWallpaperMask;
            if (view != null) {
                view.setVisibility(0);
            }
            ImageView imageView = this.wallpaperView;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            startVideoSegmentInternal();
            applyRoundCorner(false);
            updateBackground();
            return;
        }
        this.isVideoWallpaperActive = false;
        this.videoWallpaperPaths.clear();
        if (z7) {
            this.videoWallpaperPaths.addAll(list);
        }
        this.currentVideoWallpaperIndex = 0;
        this.currentVideoWallpaperPath = null;
        releaseVideoWallpaperPlayer();
        TextureView textureView2 = this.videoWallpaperView;
        if (textureView2 != null) {
            textureView2.setVisibility(8);
        }
        View view2 = this.videoWallpaperMask;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        applyRoundCorner(this.prefs.getBoolean("desktop_round_corner_enabled", false));
        ImageView imageView2 = this.wallpaperView;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
        }
        this.wallpaperPaths.clear();
        this.wallpaperPaths.addAll(list2);
        if (this.wallpaperPaths.isEmpty()) {
            this.wallpaperView.setBackground(null);
            this.wallpaperView.setImageResource(g4.o.desktop_default_wallpaper);
            return;
        }
        if (z7) {
            startStaticSegmentInternal();
            return;
        }
        if (this.wallpaperPaths.size() > 1) {
            startCarousel();
        } else if (bitmap != null) {
            this.wallpaperView.setImageBitmap(bitmap);
        } else {
            this.wallpaperView.setBackground(null);
            this.wallpaperView.setImageResource(g4.o.desktop_default_wallpaper);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadWallpaper$84(final int i7) {
        boolean z6;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        List<String> resolveActiveVideoWallpaperPaths = resolveActiveVideoWallpaperPaths();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File resolveStaticWallpaperDirectory = resolveStaticWallpaperDirectory(externalStorageDirectory);
        File[] listFiles = resolveStaticWallpaperDirectory.getAbsolutePath().equals(new File(externalStorageDirectory, "navitool/wallpaper").getAbsolutePath()) ? resolveStaticWallpaperDirectory.listFiles(new s0(1)) : resolveStaticWallpaperDirectory.listFiles(new s0(2));
        final ArrayList arrayList = new ArrayList();
        if (listFiles != null && listFiles.length > 0) {
            Arrays.sort(listFiles, new x(8));
            for (File file : listFiles) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        final ArrayList arrayList2 = new ArrayList(resolveActiveVideoWallpaperPaths);
        boolean isWallpaperStaticMode = isWallpaperStaticMode();
        final boolean z7 = (!isMixedWallpaperEnabled() || arrayList2.isEmpty() || arrayList.isEmpty()) ? false : true;
        if (z7) {
            if (!this.mixedForceSegment) {
                this.mixedSegmentIsVideo = !isWallpaperStaticMode;
            }
            this.mixedForceSegment = false;
            z6 = this.mixedSegmentIsVideo;
        } else {
            this.mixedForceSegment = false;
            z6 = (isWallpaperStaticMode || arrayList2.isEmpty()) ? false : true;
        }
        final boolean z8 = z6;
        final Bitmap decodeFile = (z7 || z8 || arrayList.size() != 1) ? null : BitmapFactory.decodeFile((String) arrayList.get(0));
        runOnUiThread(new Runnable() { // from class: com.navi.tool.ui.extra.d0
            @Override // java.lang.Runnable
            public final void run() {
                DesktopAssistantActivity.this.lambda$loadWallpaper$83(i7, z8, arrayList2, z7, arrayList, decodeFile);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(int i7) {
        DesktopAssistantMusicUiHelper.refreshMusicStateBySource(this, i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(SharedPreferences sharedPreferences, String str) {
        if ("desktop_ambient_scale".equals(str)) {
            WeatherAmbientView weatherAmbientView = this.viewWeatherAmbient;
            if (weatherAmbientView != null) {
                weatherAmbientView.setAmbientScale(sharedPreferences.getFloat(str, 2.0f));
                return;
            }
            return;
        }
        if ("desktop_lyrics_font_size".equals(str)) {
            int i7 = sharedPreferences.getInt(str, 25);
            this.lyricsFontSize = i7;
            LyricsAdapter lyricsAdapter = this.lyricsAdapter;
            if (lyricsAdapter != null) {
                lyricsAdapter.setFontSize(i7);
                this.lyricsAdapter.notifyDataSetChanged();
                updateLyricsHeight();
                return;
            }
            return;
        }
        if ("desktop_lyrics_lines".equals(str)) {
            this.lyricsLines = Math.max(3, Math.min(8, sharedPreferences.getInt(str, 5)));
            updateLyricsHeight();
            return;
        }
        int i8 = 1;
        if ("wallpaper_carousel_interval".equals(str)) {
            if (this.wallpaperPaths.size() <= 1 || this.carouselRunnable == null) {
                return;
            }
            stopCarousel();
            startCarousel();
            return;
        }
        if ("desktop_lyrics_color_index".equals(str)) {
            updateLyricsColor();
            return;
        }
        if ("car_control_visual_model_enabled".equals(str)) {
            boolean z6 = sharedPreferences.getBoolean(str, false);
            this.isVisualCarModelEnabled = z6;
            DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
            if (desktopAssistantCarControlHelper != null) {
                desktopAssistantCarControlHelper.setState(this.isCarControlEnabled, z6, this.isSeatComfortEnabled);
            }
            updateVisualCarModelVisibility();
            return;
        }
        if ("car_control_dynamic_lane_enabled".equals(str)) {
            updateVisualCarModelVisibility();
            return;
        }
        if ("car_control_card_alpha".equals(str)) {
            updateCarControlCards();
            applyTopInfoBarStyle();
            return;
        }
        if ("car_control_text_size".equals(str)) {
            updateCarControlCards();
            return;
        }
        if ("desktop_blur_radius".equals(str)) {
            updateBackground();
            return;
        }
        if ("desktop_cover_gradient_intensity".equals(str)) {
            CoverGradientBackgroundView coverGradientBackgroundView = this.coverGradientBackground;
            if (coverGradientBackgroundView != null) {
                coverGradientBackgroundView.setBrightness(gradientIntensityToBrightness(sharedPreferences.getInt(str, 60)));
                return;
            }
            return;
        }
        if ("desktop_cover_gradient_enabled".equals(str)) {
            applyCoverGradientMode();
            applyRoundCorner(sharedPreferences.getBoolean("desktop_round_corner_enabled", false));
            return;
        }
        if ("desktop_music_enabled".equals(str)) {
            boolean z7 = sharedPreferences.getBoolean(str, false);
            this.isMusicEnabled = z7;
            if (z7) {
                initMusicService();
                updateMusicUI();
                updateLoopModeState();
                updateFavHeartState();
            } else {
                stopAllMusicServices();
            }
            updateClockWeatherHeaderVisibility();
            setupContent();
            syncMusicIslandVisibility(true);
            return;
        }
        if ("desktop_music_show_album_art".equals(str) || "desktop_music_spectrum_enabled".equals(str) || "desktop_music_spectrum_colorful_enabled".equals(str) || "desktop_music_album_art_mode".equals(str) || "desktop_music_album_art_anim".equals(str) || "desktop_music_clock_weather_enabled".equals(str) || DesktopAssistantClockWeatherController.PREF_CLOCK_WEATHER_ALERT_ENABLED.equals(str)) {
            updateMusicUI();
            return;
        }
        if (PREF_KEY_MUSIC_PACKAGE.equals(str)) {
            updateFloatingMusicAppIcon();
            return;
        }
        if ("desktop_map_enabled".equals(str)) {
            setupContent();
            return;
        }
        if ("desktop_map_calendar_enabled".equals(str) || KEY_FLOATING_MAP_DISPLAY_MODE.equals(str)) {
            updateMapCalendarVisibility();
            return;
        }
        if (o4.r.PREF_OIL_RECORD_CALENDAR_ENABLED.equals(str)) {
            DesktopMapCalendarCardView desktopMapCalendarCardView = this.mapCalendarCardView;
            if (desktopMapCalendarCardView != null) {
                desktopMapCalendarCardView.d();
                return;
            }
            return;
        }
        if (PREF_KEY_MAP_PACKAGE.equals(str) || PREF_KEY_MAP_PACKAGE_2.equals(str)) {
            updateMapPlaceholderState();
            updateMapCalendarVisibility();
            if (getActiveMapPackage() != null) {
                lambda$setupMode$71();
                return;
            }
            return;
        }
        if ("desktop_music_source".equals(str)) {
            if (this.isMusicEnabled) {
                stopAllMusicServices();
                initMusicService();
                updateLoopModeState();
                updateFavHeartState();
                new Handler(Looper.getMainLooper()).postDelayed(new u0(this, sharedPreferences.getInt(str, 0), i8), 500L);
                return;
            }
            return;
        }
        if ("desktop_round_corner_enabled".equals(str) || "desktop_card_margin".equals(str)) {
            applyRoundCorner(sharedPreferences.getBoolean("desktop_round_corner_enabled", false));
            FrameLayout frameLayout = this.mapContainer;
            if (frameLayout != null) {
                frameLayout.post(new b1(this, 6));
                return;
            }
            return;
        }
        if ("desktop_app_text_color".equals(str)) {
            if (this.appListAdapter != null) {
                this.appListAdapter.setAppNameColor(Integer.valueOf(sharedPreferences.getInt(str, 0) == 1 ? p0.d1.MEASURED_STATE_MASK : -1));
                return;
            }
            return;
        }
        if ("desktop_app_icon_size".equals(str)) {
            if (this.cachedAppList != null) {
                updateAppListUI(this.cachedAppList);
                return;
            } else {
                loadInstalledApps();
                return;
            }
        }
        if ("desktop_mode_selection".equals(str)) {
            int i9 = sharedPreferences.getInt("desktop_mode_selection", 0);
            if (this.mode != i9) {
                this.mode = i9;
                setupMode();
                return;
            }
            return;
        }
        if ("desktop_night_mode_alpha".equals(str)) {
            updateNightMode(isCurrentNightMode());
            return;
        }
        if (PREF_KEY_CLEAN_MODE.equals(str)) {
            boolean z8 = sharedPreferences.getBoolean(PREF_KEY_CLEAN_MODE, false);
            if (this.isCleanMode != z8) {
                this.isCleanMode = z8;
                setupMode();
                return;
            }
            return;
        }
        if ("desktop_app_order".equals(str) || ((str != null && str.startsWith("desktop_app_enabled_")) || ((str != null && str.startsWith("app_custom_name_")) || ((str != null && str.startsWith("gesture_panel_app_icon_")) || "file_assistant_enabled".equals(str) || "permission_assistant_enabled".equals(str) || "desktop_assistant_enabled".equals(str))))) {
            this.cachedAppList = null;
            loadInstalledApps();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAlbumArtUpdateEvent$42(Bitmap bitmap) {
        DesktopAssistantMusicUiHelper.applyCoverBitmap(bitmap, this.imgAlbumArtFull);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAlbumArtUpdateEvent$43(Bitmap bitmap) {
        saveMusicCover(bitmap);
        runOnUiThread(new v0(this, bitmap, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$23(Map map) {
        updateSeatComfort();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$onCreate$28() {
        LionCarInfoService lionCarInfoService = LionCarInfoService.f4712m;
        if (lionCarInfoService != null) {
            new Thread(new o4.k(lionCarInfoService, 0)).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3() {
        DesktopAssistantTopInfoAdapter desktopAssistantTopInfoAdapter = this.topInfoAdapter;
        if (desktopAssistantTopInfoAdapter != null) {
            desktopAssistantTopInfoAdapter.updateData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(boolean z6) {
        updateNightMode(isCurrentNightMode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$5() {
        this.appPreloadInProgress = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$6(Object obj) {
        updateCarControlCards();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$openLyricsFxPage$112() {
        TreeMap<Long, String> snapshotLyrics = QQMusicLyricReceiver.snapshotLyrics();
        if (snapshotLyrics.isEmpty() || !this.lyricsFxView.c0()) {
            return;
        }
        snapshotLyrics.size();
        this.lyricsFxView.i0(snapshotLyrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshAppListSilently$75(List list) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        updateAppListUI(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshAppListSilently$76(int i7) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        List<AppItem> fetchAppList = fetchAppList();
        if (i7 == this.appListGen.get() && !samePackageList(this.cachedAppList, fetchAppList)) {
            this.cachedAppList = fetchAppList;
            runOnUiThread(new a0(this, fetchAppList, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$resolveStaticWallpaperDirectory$85(File file, String str) {
        return str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveLyrics$30(TreeMap treeMap) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), CACHE_DIR_NAME);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, CACHE_FILE_LYRICS);
            String json = new Gson().toJson(treeMap);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                fileOutputStream.write(json.getBytes());
                fileOutputStream.close();
            } finally {
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveMusicCover$31(Bitmap bitmap) {
        try {
            try {
                File file = new File(Environment.getExternalStorageDirectory(), CACHE_DIR_NAME);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file, CACHE_FILE_COVER));
                try {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                bitmap.recycle();
                throw th3;
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        bitmap.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveMusicMeta$29(String str, String str2) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), CACHE_DIR_NAME);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, CACHE_FILE_META);
            String json = new Gson().toJson(new MusicMeta(str, str2));
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                fileOutputStream.write(json.getBytes());
                fileOutputStream.close();
            } finally {
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scheduleVideoWatchdog$92(int i7) {
        if (i7 == this.videoStallWatchdogToken && !isFinishing() && !isDestroyed() && this.isVideoWallpaperActive) {
            if (this.videoWallpaperPaths.size() > 1 || isMixedWallpaperEnabled()) {
                playNextVideoWallpaper();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendMapBroadcast$109(boolean z6, final int i7, final int i8, final int i9, final int i10) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (z6) {
            final int i11 = 0;
            this.mapContainer.postDelayed(new Runnable(this) { // from class: com.navi.tool.ui.extra.o0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ DesktopAssistantActivity f5362b;

                {
                    this.f5362b = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i12 = i11;
                    int i13 = i8;
                    int i14 = i7;
                    DesktopAssistantActivity desktopAssistantActivity = this.f5362b;
                    int i15 = i10;
                    int i16 = i9;
                    switch (i12) {
                        case 0:
                            desktopAssistantActivity.lambda$sendMapBroadcast$107(i14, i13, i16, i15);
                            break;
                        default:
                            desktopAssistantActivity.lambda$sendMapBroadcast$108(i14, i13, i16, i15);
                            break;
                    }
                }
            }, 100L);
            final int i12 = 1;
            this.mapContainer.postDelayed(new Runnable(this) { // from class: com.navi.tool.ui.extra.o0

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ DesktopAssistantActivity f5362b;

                {
                    this.f5362b = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i122 = i12;
                    int i13 = i8;
                    int i14 = i7;
                    DesktopAssistantActivity desktopAssistantActivity = this.f5362b;
                    int i15 = i10;
                    int i16 = i9;
                    switch (i122) {
                        case 0:
                            desktopAssistantActivity.lambda$sendMapBroadcast$107(i14, i13, i16, i15);
                            break;
                        default:
                            desktopAssistantActivity.lambda$sendMapBroadcast$108(i14, i13, i16, i15);
                            break;
                    }
                }
            }, 500L);
        } else {
            updateCornerMasks(i7, i8, i9, i10);
        }
        final int[] iArr = {8};
        this.mapContainer.postOnAnimation(new Runnable() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.30
            @Override // java.lang.Runnable
            public void run() {
                if (DesktopAssistantActivity.this.isFinishing() || DesktopAssistantActivity.this.isDestroyed() || iArr[0] <= 0 || !((androidx.lifecycle.a0) DesktopAssistantActivity.this.getLifecycle()).f2136c.a(androidx.lifecycle.r.RESUMED)) {
                    return;
                }
                int[] iArr2 = new int[2];
                DesktopAssistantActivity.this.mapContainer.getLocationOnScreen(iArr2);
                int i13 = iArr2[0];
                int i14 = iArr2[1];
                int width = DesktopAssistantActivity.this.mapContainer.getWidth();
                int height = DesktopAssistantActivity.this.mapContainer.getHeight();
                if (width > 0 && height > 0) {
                    DesktopAssistantActivity.this.updateCornerMasks(i13, i14, width, height, true);
                }
                int[] iArr3 = iArr;
                int i15 = iArr3[0] - 1;
                iArr3[0] = i15;
                if (i15 > 0) {
                    DesktopAssistantActivity.this.mapContainer.postOnAnimation(this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$sendMapBroadcast$110() {
        if (isFinishing() || isDestroyed() || !((androidx.lifecycle.a0) getLifecycle()).f2136c.a(androidx.lifecycle.r.RESUMED) || this.mode != 0) {
            return;
        }
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences != null && !sharedPreferences.getBoolean("desktop_round_corner_enabled", false)) {
            removeCornerMasks();
            return;
        }
        int[] iArr = new int[2];
        this.mapContainer.getLocationOnScreen(iArr);
        int width = this.mapContainer.getWidth();
        int height = this.mapContainer.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        lambda$sendMapBroadcast$108(iArr[0], iArr[1], width, height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$sendMapBroadcast$111(String str, final boolean z6) {
        if (((androidx.lifecycle.a0) getLifecycle()).f2136c.a(androidx.lifecycle.r.RESUMED)) {
            int[] iArr = new int[2];
            this.mapContainer.getLocationOnScreen(iArr);
            final int i7 = iArr[0];
            final int i8 = iArr[1];
            final int width = this.mapContainer.getWidth();
            final int height = this.mapContainer.getHeight();
            if (width <= 0 || height <= 0) {
                return;
            }
            Intent intent = new Intent("com.autonavi.plus.showmap");
            intent.setPackage(str);
            intent.putExtra("x", i7);
            intent.putExtra("y", i8);
            intent.putExtra("w", i7 + width);
            intent.putExtra("h", i8 + height);
            try {
                sendBroadcast(intent);
                if (this.mapContainer.getParent() instanceof RealtimeBlurView) {
                    ((RealtimeBlurView) this.mapContainer.getParent()).setBlurRadius(CropImageView.DEFAULT_ASPECT_RATIO);
                }
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            this.mapContainer.postDelayed(new Runnable() { // from class: com.navi.tool.ui.extra.b0
                @Override // java.lang.Runnable
                public final void run() {
                    DesktopAssistantActivity.this.lambda$sendMapBroadcast$109(z6, i7, i8, width, height);
                }
            }, 50L);
            Runnable runnable = this.oneSecondCornerRetryRunnable;
            if (runnable != null) {
                this.mapContainer.removeCallbacks(runnable);
            }
            b1 b1Var = new b1(this, 10);
            this.oneSecondCornerRetryRunnable = b1Var;
            this.mapContainer.postDelayed(b1Var, 888L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setWallpaperWithAnimation$93(TransitionDrawable transitionDrawable, Bitmap bitmap) {
        if (this.wallpaperView.getDrawable() == transitionDrawable) {
            this.wallpaperView.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setWallpaperWithAnimation$94(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        for (int i7 = 0; i7 < 4; i7++) {
            SmartCornerView smartCornerView = this.mCornerViews[i7];
            if (smartCornerView != null) {
                smartCornerView.setTransitionProgress(floatValue);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupDockLogic$48(View view) {
        if (this.mode == 0) {
            this.mode = 1;
        } else {
            this.mode = 0;
        }
        setupMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMode$70() {
        if (isFinishing() || isDestroyed() || this.mode == 1) {
            return;
        }
        RealtimeBlurView realtimeBlurView = this.leftContainer;
        if (realtimeBlurView != null) {
            realtimeBlurView.setBlurEnabled(true);
        }
        RealtimeBlurView realtimeBlurView2 = this.centerContainer;
        if (realtimeBlurView2 != null) {
            realtimeBlurView2.setBlurEnabled(true);
        }
        RealtimeBlurView realtimeBlurView3 = this.rightContainer;
        if (realtimeBlurView3 != null) {
            realtimeBlurView3.setBlurEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMode$72() {
        if (isFinishing() || isDestroyed() || this.mode == 1) {
            return;
        }
        Fade fade = new Fade();
        fade.setDuration(300L);
        TransitionManager.beginDelayedTransition(this.contentContainer, fade);
        updateBackground();
        RealtimeBlurView realtimeBlurView = this.leftContainer;
        if (realtimeBlurView != null) {
            realtimeBlurView.setBlurEnabled(false);
        }
        RealtimeBlurView realtimeBlurView2 = this.centerContainer;
        if (realtimeBlurView2 != null) {
            realtimeBlurView2.setBlurEnabled(false);
        }
        RealtimeBlurView realtimeBlurView3 = this.rightContainer;
        if (realtimeBlurView3 != null) {
            realtimeBlurView3.setBlurEnabled(false);
        }
        this.splitContainer.setVisibility(0);
        this.contentContainer.postDelayed(new b1(this, 2), 300L);
        FrameLayout frameLayout = this.mapContainer;
        if (frameLayout != null) {
            if (frameLayout != null && (frameLayout.getParent() instanceof RealtimeBlurView)) {
                ((RealtimeBlurView) this.mapContainer.getParent()).setBlurRadius(CropImageView.DEFAULT_ASPECT_RATIO);
            }
            this.mapContainer.post(new b1(this, 3));
        }
        updateTopInfoBarVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMusicControls$100(View view) {
        DesktopAssistantMusicUiHelper.dispatchMusicControl(this, this.prefs, this.isPlaying, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMusicControls$101(View view) {
        DesktopAssistantMusicUiHelper.dispatchMusicControl(this, this.prefs, this.isPlaying, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMusicControls$97(View view) {
        launchMusicApp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMusicControls$98(View view) {
        showMusicSourcePickerDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupMusicControls$99(View view) {
        DesktopAssistantMusicUiHelper.dispatchMusicControl(this, this.prefs, this.isPlaying, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setupMusicPluginInteraction$104(GestureDetector gestureDetector, View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupTwoViewInteraction$102(View view) {
        if (this.mode == 1) {
            return false;
        }
        ToastHelper.show(this, "长按之后挪动可以互换视图位置");
        view.startDragAndDrop(ClipData.newPlainText("Swap", "Swap"), new View.DragShadowBuilder(view), null, 0);
        view.performHapticFeedback(0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setupTwoViewInteraction$103(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        if (action != 1) {
            if (action != 3) {
                if (action == 4) {
                    view.setBackgroundColor(0);
                    return true;
                }
                if (action == 5) {
                    view.setBackgroundColor(872415231);
                    return true;
                }
                if (action != 6) {
                    return false;
                }
                view.setBackgroundColor(0);
                return true;
            }
            view.setBackgroundColor(0);
            this.isSwapped = !this.isSwapped;
            this.prefs.edit().putBoolean(PREF_KEY_SWAP_POS, this.isSwapped).apply();
            setupContent();
            ToastHelper.show(this, "位置已交换");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAppSelectionDialog$66(List list) {
        this.appSelectionLoading = false;
        androidx.appcompat.app.o oVar = this.appSelectionLoadingDialog;
        if (oVar != null) {
            try {
                oVar.dismiss();
            } catch (Exception unused) {
            }
            this.appSelectionLoadingDialog = null;
        }
        if (isFinishing() || isDestroyed()) {
            return;
        }
        showAppSelectionDialogWith(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAppSelectionDialog$67() {
        runOnUiThread(new a0(this, loadLaunchableAppsOnWorker(), 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAppSelectionDialogWith$69(androidx.appcompat.app.o oVar, ApplicationInfo applicationInfo) {
        this.prefs.edit().putString(PREF_KEY_MAP_PACKAGE, applicationInfo.packageName).apply();
        lambda$setupMode$71();
        updateMapPlaceholderState();
        ToastHelper.show(this, "已关联: " + ((Object) getPackageManager().getApplicationLabel(applicationInfo)));
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCustomMusicAppSelectionDialog$38(androidx.appcompat.app.o[] oVarArr, AppInfo appInfo) {
        if (appInfo == null) {
            return;
        }
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
        switchDesktopMusicSource(new DesktopAssistantMusicUiHelper.MusicSourceCandidate(6, appInfo.getPackageName(), appInfo.getAppName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$showHandles$106(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            hideHandles();
        }
        GestureDetector gestureDetector = this.gestureDetector;
        if (gestureDetector == null) {
            return false;
        }
        gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showMusicSourcePickerDialog$37(DesktopAssistantMusicUiHelper.MusicSourceCandidate musicSourceCandidate, androidx.appcompat.app.o oVar, View view) {
        if (isCustomMusicSourceCandidate(musicSourceCandidate)) {
            oVar.dismiss();
            showCustomMusicAppSelectionDialog();
        } else {
            switchDesktopMusicSource(musicSourceCandidate);
            oVar.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startVideoWallpaper$88(String str, MediaPlayer mediaPlayer) {
        long j7;
        if (isFinishing() || isDestroyed() || !str.equals(this.currentVideoWallpaperPath)) {
            return;
        }
        try {
            mediaPlayer.start();
        } catch (Exception unused) {
        }
        try {
            j7 = mediaPlayer.getDuration();
        } catch (Exception unused2) {
            j7 = 0;
        }
        scheduleVideoWatchdog(j7 > 0 ? j7 + 5000 : VIDEO_WATCHDOG_PREPARE_TIMEOUT_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startVideoWallpaper$89(MediaPlayer mediaPlayer) {
        this.videoWallpaperPaths.size();
        cancelVideoWatchdog();
        TextureView textureView = this.videoWallpaperView;
        if (textureView != null) {
            textureView.post(new b1(this, 27));
        } else {
            playNextVideoWallpaper();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$startVideoWallpaper$91(String str, MediaPlayer mediaPlayer, int i7, int i8) {
        TextureView textureView = this.videoWallpaperView;
        w0 w0Var = new w0(this, str, 0);
        if (textureView != null) {
            textureView.post(w0Var);
            return true;
        }
        w0Var.run();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$syncGnssDataSource$65(String str) {
        DesktopAssistantTopInfoAdapter desktopAssistantTopInfoAdapter = this.topInfoAdapter;
        if (desktopAssistantTopInfoAdapter != null) {
            desktopAssistantTopInfoAdapter.updateData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAppListUI$79(AppItem appItem) {
        String str = appItem.packageName;
        if ("com.navi.tool.action.restart".equals(str)) {
            recreate();
            return;
        }
        if ("com.navi.tool.desktopassistant".equals(str)) {
            Intent intent = new Intent(this, (Class<?>) MainActivity.class);
            intent.addFlags(335544320);
            intent.putExtra("target_tab", "desktop_assistant");
            startActivity(intent);
            return;
        }
        if ("com.navi.tool.fileassistant".equals(str)) {
            Intent intent2 = new Intent(this, (Class<?>) FileManagerActivity.class);
            intent2.addFlags(268435456);
            startActivity(intent2);
            return;
        }
        if ("com.navi.tool.originalhome".equals(str)) {
            Intent intent3 = new Intent("android.intent.action.MAIN");
            intent3.addCategory("android.intent.category.HOME");
            intent3.addFlags(268435456);
            startActivity(intent3);
            return;
        }
        if (PKG_ADB_SWITCH.equals(str)) {
            launchAdbSwitch();
            return;
        }
        if ("com.navi.tool.permissionassistant".equals(str)) {
            Intent intent4 = new Intent(this, (Class<?>) PermissionManagerActivity.class);
            intent4.addFlags(268435456);
            startActivity(intent4);
            return;
        }
        if (PKG_THEME_MODE.equals(str)) {
            cycleThemeMode();
            return;
        }
        if (PKG_FLOATING_MAP_MODE.equals(str)) {
            cycleFloatingMapDisplayMode();
            return;
        }
        if (PKG_WALLPAPER_MODE.equals(str)) {
            cycleWallpaperMode();
            return;
        }
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(appItem.packageName);
        if (launchIntentForPackage == null) {
            ToastHelper.show(this, "无法启动应用");
        } else {
            launchIntentForPackage.addFlags(268435456);
            startActivity(launchIntentForPackage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$updateCoverGradientFromBitmap$45(int i7, int[] iArr) {
        SharedPreferences sharedPreferences;
        if (i7 != this.coverGradientRequestToken || isFinishing() || isDestroyed() || this.coverGradientBackground == null || (sharedPreferences = this.prefs) == null || !sharedPreferences.getBoolean("desktop_cover_gradient_enabled", false)) {
            return;
        }
        this.coverGradientBackground.setColors(iArr, true);
        if (iArr != null && iArr.length >= 3) {
            View view = this.viewProgress;
            if (view instanceof GlowProgressView) {
                ((GlowProgressView) view).setCoverAccent(iArr[2]);
            }
        }
        LyricsFxView lyricsFxView = this.lyricsFxView;
        if (lyricsFxView == null || !lyricsFxView.c0()) {
            return;
        }
        LyricsFxView lyricsFxView2 = this.lyricsFxView;
        lyricsFxView2.getClass();
        if (iArr == null || iArr.length < 3) {
            return;
        }
        int i8 = iArr[0];
        int i9 = iArr[1];
        int i10 = iArr[2];
        if ((((i9 << 3) ^ i8) ^ (i10 << 7)) == lyricsFxView2.Q) {
            return;
        }
        lyricsFxView2.U = new int[]{i8, i9, i10};
        lyricsFxView2.f5929b0 = LyricsFxView.d0(i10);
        lyricsFxView2.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCoverGradientFromBitmap$46(Bitmap bitmap, boolean z6, boolean z7, int i7) {
        int[] extract = CoverPaletteExtractor.extract(bitmap, z6);
        if (z7) {
            bitmap.recycle();
        }
        runOnUiThread(new g4(this, i7, extract, 1));
    }

    private void launchAdbSwitch() {
        AppDetectionUtils.launchAdbSwitch(this);
    }

    private void launchMusicApp() {
        String string = this.prefs.getString(PREF_KEY_MUSIC_PACKAGE, null);
        if (string == null) {
            ToastHelper.show(this, "未关联音乐应用，请在设置中选择");
            return;
        }
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(string);
        if (launchIntentForPackage == null) {
            ToastHelper.show(this, "无法启动关联的音乐应用");
        } else {
            launchIntentForPackage.addFlags(268435456);
            startActivity(launchIntentForPackage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAppList(final boolean z6, final long j7, final Runnable runnable) {
        final int incrementAndGet = this.appListGen.incrementAndGet();
        try {
            this.appListExecutor.execute(new Runnable() { // from class: com.navi.tool.ui.extra.e0
                @Override // java.lang.Runnable
                public final void run() {
                    DesktopAssistantActivity.this.lambda$loadAppList$74(j7, incrementAndGet, z6, runnable);
                }
            });
        } catch (RejectedExecutionException unused) {
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    private void loadCachedCoverForGradient() {
        new Thread(new b1(this, 24), "CoverGradientCache").start();
    }

    private void loadCachedMusicInfo() {
        new Thread(new b1(this, 4)).start();
    }

    private List<AppInfo> loadCustomMusicApps() {
        String str;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(128);
        HashSet hashSet = new HashSet();
        for (DesktopAssistantMusicUiHelper.MusicSourceCandidate musicSourceCandidate : DesktopAssistantMusicUiHelper.getInstalledMusicSourceCandidates(this)) {
            if (!TextUtils.isEmpty(musicSourceCandidate.packageName)) {
                hashSet.add(musicSourceCandidate.packageName);
            }
        }
        hashSet.add(getPackageName());
        for (ApplicationInfo applicationInfo : installedApplications) {
            if (applicationInfo != null && !TextUtils.isEmpty(applicationInfo.packageName)) {
                int i7 = applicationInfo.flags;
                if ((i7 & 1) == 0 && (i7 & 128) == 0 && !hashSet.contains(applicationInfo.packageName) && packageManager.getLaunchIntentForPackage(applicationInfo.packageName) != null) {
                    try {
                        CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfo);
                        str = applicationLabel == null ? applicationInfo.packageName : applicationLabel.toString();
                    } catch (Exception unused) {
                        str = applicationInfo.packageName;
                    }
                    arrayList.add(new AppInfo(applicationInfo.packageName, str, false));
                }
            }
        }
        Collections.sort(arrayList, new x(9));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadDockApps() {
        new Thread(new b1(this, 25)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadInstalledApps() {
        loadAppList(true, 0L, null);
    }

    private List<ApplicationInfo> loadLaunchableAppsOnWorker() {
        String str;
        PackageManager packageManager = getPackageManager();
        HashSet hashSet = new HashSet();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        Iterator<ResolveInfo> it = packageManager.queryIntentActivities(intent, 65536).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null) {
                hashSet.add(activityInfo.packageName);
            }
        }
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.INFO");
        Iterator<ResolveInfo> it2 = packageManager.queryIntentActivities(intent2, 65536).iterator();
        while (it2.hasNext()) {
            ActivityInfo activityInfo2 = it2.next().activityInfo;
            if (activityInfo2 != null) {
                hashSet.add(activityInfo2.packageName);
            }
        }
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        ArrayList arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if (hashSet.contains(applicationInfo.packageName) && !applicationInfo.packageName.equals(getPackageName())) {
                try {
                    str = packageManager.getApplicationLabel(applicationInfo).toString();
                } catch (Exception unused) {
                    str = applicationInfo.packageName;
                }
                arrayList.add(new LabeledApp(applicationInfo, str));
            }
        }
        arrayList.sort(new x(1));
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            arrayList2.add(((LabeledApp) it3.next()).info);
        }
        return arrayList2;
    }

    private void loadLayoutState() {
        String string = this.prefs.getString(PREF_KEY_VIEW_ORDER, null);
        if (string != null) {
            try {
                String[] split = string.split(",");
                if (split.length == 3) {
                    for (int i7 = 0; i7 < 3; i7++) {
                        this.viewOrder[i7] = Integer.parseInt(split[i7]);
                    }
                }
            } catch (Exception e7) {
                e7.printStackTrace();
            }
        }
        String string2 = this.prefs.getString(PREF_KEY_VIEW_WEIGHTS, null);
        if (string2 != null) {
            try {
                String[] split2 = string2.split(",");
                if (split2.length == 3) {
                    for (int i8 = 0; i8 < 3; i8++) {
                        this.viewWeights[i8] = Float.parseFloat(split2[i8]);
                    }
                }
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadWallpaper() {
        stopCarousel();
        int incrementAndGet = this.wallpaperLoadRequestGenerator.incrementAndGet();
        this.wallpaperLoadRequestId = incrementAndGet;
        new Thread(new u0(this, incrementAndGet, 3)).start();
    }

    private ViewGroup.LayoutParams matchParentParams() {
        return new ViewGroup.LayoutParams(-1, -1);
    }

    private String normalizeMusicText(String str) {
        return str == null ? s5.h0.FRAGMENT_ENCODE_SET : str.trim();
    }

    private void notifyFloatingMapModeChanged() {
        updateMapCalendarVisibility();
        Intent intent = new Intent("com.navi.tool.ACTION_MAP_APP_UPDATED");
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
        Intent intent2 = new Intent("com.navi.tool.DESKTOP_APPS_UPDATED");
        intent2.setPackage(getPackageName());
        sendBroadcast(intent2);
        Intent intent3 = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent3.setPackage(getPackageName());
        sendBroadcast(intent3);
    }

    private void notifyWallpaperModeChanged() {
        loadWallpaper();
        Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
        Intent intent2 = new Intent("com.navi.tool.DESKTOP_APPS_UPDATED");
        intent2.setPackage(getPackageName());
        sendBroadcast(intent2);
    }

    private void onFavHeartClick() {
        ImageView imageView = this.imgFavHeart;
        if (imageView == null) {
            return;
        }
        imageView.setEnabled(false);
        try {
            DesktopAssistantMusicUiHelper.toggleFavorite(this, !this.mIsFavorite, this.prefs, new AnonymousClass12());
        } catch (Exception e7) {
            this.imgFavHeart.setEnabled(true);
            ToastHelper.show(this, "操作异常: " + e7.getMessage());
        }
    }

    private void onLoopModeClick() {
        int i7;
        if (this.imgLoopMode == null) {
            return;
        }
        int i8 = this.prefs.getInt("desktop_music_source", 0);
        if (i8 == 0) {
            i7 = (this.mPlayMode + 1) % 3;
        } else if (i8 == 1 || i8 == 4) {
            i7 = (this.mLoopCycleIndex + 1) % 4;
            this.mLoopCycleIndex = i7;
            this.mPlayMode = i7;
            setLoopModeIcon(i7);
        } else {
            i7 = (this.mLoopCycleIndex + 1) % 3;
            this.mLoopCycleIndex = i7;
        }
        DesktopAssistantMusicUiHelper.dispatchLoopMode(this, i8, i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openLyricsFxPage() {
        TreeMap<Long, String> treeMap;
        MediaPlayer mediaPlayer;
        if (this.lyricsFxView == null) {
            return;
        }
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences != null && sharedPreferences.getBoolean("desktop_round_corner_enabled", false)) {
            a.a.m(this.prefs, "desktop_round_corner_enabled", false);
            applyRoundCorner(false);
            Intent intent = new Intent("com.navi.tool.DESKTOP_ROUND_CORNER_UPDATED");
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        }
        this.mapClosedByLyricsFx = false;
        if (isFloatingMapMode() && canShowFloatingMap()) {
            String mapPackageAt = getMapPackageAt(1);
            String mapPackageAt2 = getMapPackageAt(2);
            lambda$switchFloatingMap$0(mapPackageAt);
            if (mapPackageAt2 != null && !mapPackageAt2.equals(mapPackageAt)) {
                lambda$switchFloatingMap$0(mapPackageAt2);
            }
            String activeMapPackage = getActiveMapPackage();
            if (activeMapPackage != null && !activeMapPackage.isEmpty()) {
                this.mapClosedByLyricsFx = true;
            }
        }
        CoverGradientBackgroundView coverGradientBackgroundView = this.coverGradientBackground;
        int[] colors = coverGradientBackgroundView != null ? coverGradientBackgroundView.getColors() : null;
        this.lyricsFxView.setNightMode(isCurrentNightMode());
        synchronized (this.mCurrentLyrics) {
            treeMap = new TreeMap<>((SortedMap<Long, ? extends String>) this.mCurrentLyrics);
        }
        SharedPreferences sharedPreferences2 = this.prefs;
        int i7 = sharedPreferences2 != null ? sharedPreferences2.getInt("desktop_music_source", 0) : 0;
        int i8 = 5;
        if (treeMap.isEmpty()) {
            if (i7 == 0) {
                treeMap = QQMusicLyricReceiver.snapshotLyrics();
            } else if (i7 == 5) {
                TreeMap treeMap2 = LunaMusicLyricReceiver.f4508e;
                synchronized (treeMap2) {
                    treeMap = new TreeMap<>((SortedMap<Long, ? extends String>) treeMap2);
                }
            }
        }
        boolean z6 = treeMap.isEmpty() && i7 == 0;
        treeMap.size();
        this.mCurrentLyrics.size();
        LyricsFxView lyricsFxView = this.lyricsFxView;
        long j7 = this.mLastKnownCurrentPosition;
        boolean z7 = this.isPlaying;
        TreeMap treeMap3 = lyricsFxView.R;
        treeMap3.clear();
        treeMap3.putAll(treeMap);
        lyricsFxView.f0();
        if (colors != null && colors.length >= 3) {
            int i9 = colors[0];
            int i10 = colors[1];
            int i11 = colors[2];
            lyricsFxView.U = new int[]{i9, i10, i11};
            lyricsFxView.f5929b0 = LyricsFxView.d0(i11);
            lyricsFxView.f5938g = null;
        }
        lyricsFxView.f5931c0 = Math.max(0L, j7);
        long uptimeMillis = SystemClock.uptimeMillis();
        lyricsFxView.f5933d0 = uptimeMillis;
        lyricsFxView.L0 = uptimeMillis;
        lyricsFxView.f5935e0 = z7;
        lyricsFxView.f5937f0 = -1;
        lyricsFxView.f5945j0 = 49;
        lyricsFxView.f5947k0.clear();
        lyricsFxView.K0 = 0;
        lyricsFxView.S0 = 0;
        lyricsFxView.C0 = null;
        lyricsFxView.H = 0;
        lyricsFxView.J = false;
        lyricsFxView.I = lyricsFxView.T.isEmpty() ? SystemClock.uptimeMillis() : 0L;
        lyricsFxView.setVisibility(0);
        lyricsFxView.T0 = true;
        Choreographer choreographer = Choreographer.getInstance();
        x0.b bVar = lyricsFxView.U0;
        choreographer.removeFrameCallback(bVar);
        Choreographer.getInstance().postFrameCallback(bVar);
        lyricsFxView.invalidate();
        String str = this.mCurrentLyricLine;
        if (str != null && !str.isEmpty()) {
            this.lyricsFxView.h0(this.mCurrentLyricLine);
        }
        if (z6) {
            QQMusicLyricReceiver.requestRefresh(this);
            this.lyricsFxView.postDelayed(new b1(this, i8), 800L);
        }
        WeatherAmbientView weatherAmbientView = this.viewWeatherAmbient;
        if (weatherAmbientView != null) {
            weatherAmbientView.f6065i0 = false;
            Choreographer.getInstance().removeFrameCallback(weatherAmbientView.f6075n0);
        }
        stopDesktopSpectrum();
        RealtimeBlurView.setGlobalPaused(true);
        if (this.isVideoWallpaperActive && (mediaPlayer = this.videoWallpaperPlayer) != null) {
            try {
                mediaPlayer.pause();
            } catch (Exception unused) {
            }
        }
        hideSystemUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playNextVideoWallpaper() {
        if (isFinishing() || isDestroyed() || this.videoWallpaperPaths.isEmpty()) {
            return;
        }
        if (isMixedWallpaperEnabled() && !this.wallpaperPaths.isEmpty() && this.videoShownCount >= this.videoWallpaperPaths.size()) {
            this.mixedSegmentIsVideo = false;
            this.mixedForceSegment = true;
            startStaticSegmentInternal();
        } else {
            if (this.videoWallpaperPaths.size() == 1) {
                this.videoShownCount = 1;
                startVideoWallpaper(this.videoWallpaperPaths.get(0));
                return;
            }
            this.videoOrder.setRandom(isRandomWallpaperOrder());
            int next = this.videoOrder.next(this.currentVideoWallpaperIndex, this.videoWallpaperPaths.size());
            this.currentVideoWallpaperIndex = next;
            this.videoShownCount++;
            startVideoWallpaper(this.videoWallpaperPaths.get(next));
        }
    }

    private long previewMusicSeek(float f7, int i7) {
        long max = Math.max(0L, this.mLastKnownTotalDuration);
        if (max <= 0 || i7 <= 0) {
            return 0L;
        }
        long max2 = (long) (max * Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(1.0f, f7 / i7)));
        applyMusicProgressPreview(max2);
        return max2;
    }

    private void promoteTransitionToPrimaryMasks() {
        for (int i7 = 0; i7 < 4; i7++) {
            SmartCornerView[] smartCornerViewArr = this.mCornerViews;
            SmartCornerView[] smartCornerViewArr2 = this.mTransitionCornerViews;
            smartCornerViewArr[i7] = smartCornerViewArr2[i7];
            smartCornerViewArr2[i7] = null;
        }
        this.mIsCornerMaskShown = true;
    }

    private void refreshAppListSilently() {
        try {
            this.appListExecutor.execute(new u0(this, this.appListGen.incrementAndGet(), 2));
        } catch (RejectedExecutionException unused) {
        }
    }

    private void registerNetworkReceiver() {
        this.networkReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DesktopAssistantActivity.this.updateNetworkStatus();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        registerReceiver(this.networkReceiver, intentFilter);
        updateNetworkStatus();
    }

    private void registerOilRecordReceiver() {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (DesktopAssistantActivity.this.mapCalendarCardView != null) {
                    DesktopAssistantActivity.this.mapCalendarCardView.d();
                }
            }
        };
        this.oilRecordReceiver = broadcastReceiver;
        registerReceiver(broadcastReceiver, new IntentFilter(o4.r.ACTION_OIL_RECORD_UPDATED));
    }

    private void registerPackageReceiver() {
        this.packageReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DesktopAssistantActivity.this.cachedAppList = null;
                DesktopAssistantActivity.this.loadDockApps();
                DesktopAssistantActivity.this.loadAppList(true, 500L, null);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("com.navi.tool.DESKTOP_APPS_UPDATED");
        registerReceiver(this.packageReceiver, intentFilter);
    }

    private void registerTimeReceiver() {
        this.timeReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DesktopAssistantActivity.this.updateWeatherLunarText();
                if (DesktopAssistantActivity.this.mapCalendarCardView != null) {
                    DesktopAssistantActivity.this.mapCalendarCardView.d();
                }
                if (DesktopAssistantActivity.this.mode == 1) {
                    DesktopAssistantActivity.this.loadWallpaper();
                    DesktopAssistantActivity.this.updateBackground();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        registerReceiver(this.timeReceiver, intentFilter);
    }

    private void registerViewSwitchReceiver() {
        this.viewSwitchReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.8
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if ("com.navi.tool.ACTION_SWITCH_DESKTOP_VIEW".equals(intent.getAction())) {
                    if (DesktopAssistantActivity.this.mode == 1) {
                        DesktopAssistantActivity.this.mode = 0;
                    } else {
                        DesktopAssistantActivity.this.mode = 1;
                    }
                    DesktopAssistantActivity.this.setupMode();
                    return;
                }
                if ("com.navi.tool.ACTION_SWITCH_FLOATING_APP".equals(intent.getAction())) {
                    DesktopAssistantActivity.this.cycleFloatingMapDisplayMode();
                    return;
                }
                if ("com.navi.tool.ACTION_TOGGLE_LYRICS_FX".equals(intent.getAction())) {
                    Intent intent2 = new Intent(DesktopAssistantActivity.this, (Class<?>) DesktopAssistantActivity.class);
                    intent2.addFlags(268566528);
                    DesktopAssistantActivity.this.startActivity(intent2);
                    if (DesktopAssistantActivity.this.lyricsFxView == null || !DesktopAssistantActivity.this.lyricsFxView.c0()) {
                        DesktopAssistantActivity.this.openLyricsFxPage();
                        return;
                    } else {
                        DesktopAssistantActivity.this.lyricsFxView.b0();
                        DesktopAssistantActivity.this.restoreDesktopAfterLyricsFx();
                        return;
                    }
                }
                if ("com.navi.tool.ACTION_SWITCH_CLEAN_MODE".equals(intent.getAction())) {
                    DesktopAssistantActivity.this.toggleCleanMode();
                } else if ("com.navi.tool.ACTION_SWITCH_WALLPAPER_MODE".equals(intent.getAction())) {
                    DesktopAssistantActivity.this.cycleWallpaperMode();
                } else if ("com.navi.tool.ACTION_REPAIR_ACCESSIBILITY".equals(intent.getAction())) {
                    AccessibilityGuard.repairIfNeeded(DesktopAssistantActivity.this);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("com.navi.tool.ACTION_SWITCH_DESKTOP_VIEW");
        intentFilter.addAction("com.navi.tool.ACTION_SWITCH_FLOATING_APP");
        intentFilter.addAction("com.navi.tool.ACTION_TOGGLE_LYRICS_FX");
        intentFilter.addAction("com.navi.tool.ACTION_SWITCH_CLEAN_MODE");
        intentFilter.addAction("com.navi.tool.ACTION_SWITCH_WALLPAPER_MODE");
        intentFilter.addAction("com.navi.tool.ACTION_REPAIR_ACCESSIBILITY");
        registerReceiver(this.viewSwitchReceiver, intentFilter);
        this.roundCornerReceiver = new AnonymousClass9();
        IntentFilter intentFilter2 = new IntentFilter("com.navi.tool.DESKTOP_ROUND_CORNER_UPDATED");
        intentFilter2.addAction("com.navi.tool.DESKTOP_DOCK_VISIBILITY_UPDATED");
        intentFilter2.addAction("com.navi.tool.DESKTOP_NIGHT_MODE_ALPHA_UPDATED");
        registerReceiver(this.roundCornerReceiver, intentFilter2);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.10
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DesktopAssistantActivity.this.updateTopInfoBarVisibility();
                DesktopAssistantActivity.this.syncGnssDataSource();
                DesktopAssistantActivity.this.applyRoundCorner(DesktopAssistantActivity.this.prefs.getBoolean("desktop_round_corner_enabled", false));
            }
        };
        this.topInfoReceiver = broadcastReceiver;
        registerReceiver(broadcastReceiver, new IntentFilter("com.navi.tool.TOP_INFO_AREA_UPDATED"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseVideoWallpaperPlayer() {
        cancelVideoWatchdog();
        MediaPlayer mediaPlayer = this.videoWallpaperPlayer;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setOnPreparedListener(null);
                this.videoWallpaperPlayer.setOnCompletionListener(null);
                this.videoWallpaperPlayer.setOnErrorListener(null);
            } catch (Exception unused) {
            }
            try {
                this.videoWallpaperPlayer.stop();
            } catch (Exception unused2) {
            }
            try {
                this.videoWallpaperPlayer.reset();
            } catch (Exception unused3) {
            }
            try {
                this.videoWallpaperPlayer.release();
            } catch (Exception unused4) {
            }
            this.videoWallpaperPlayer = null;
        }
    }

    private void removeCornerMasks() {
        removePrimaryCornerMasks();
        removeTransitionCornerMasks();
    }

    private void removeHandleSafely(View view) {
        WindowManager windowManager;
        if (view == null) {
            return;
        }
        try {
            if (view.getParent() instanceof ViewGroup) {
                ((ViewGroup) view.getParent()).removeView(view);
            } else if (view.getParent() != null && (windowManager = this.mWindowManager) != null) {
                windowManager.removeView(view);
            }
        } catch (Exception unused) {
        }
    }

    private void removePrimaryCornerMasks() {
        if (this.mWindowManager == null) {
            return;
        }
        for (int i7 = 0; i7 < 4; i7++) {
            SmartCornerView smartCornerView = this.mCornerViews[i7];
            if (smartCornerView != null) {
                try {
                    this.mWindowManager.removeView(smartCornerView);
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                this.mCornerViews[i7] = null;
            }
        }
        this.mIsCornerMaskShown = false;
    }

    private void removeTransitionCornerMasks() {
        if (this.mWindowManager == null) {
            return;
        }
        for (int i7 = 0; i7 < 4; i7++) {
            SmartCornerView smartCornerView = this.mTransitionCornerViews[i7];
            if (smartCornerView != null) {
                try {
                    this.mWindowManager.removeView(smartCornerView);
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                this.mTransitionCornerViews[i7] = null;
            }
        }
    }

    private List<String> resolveActiveVideoWallpaperPaths() {
        String todayWallpaperSubFolder;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (this.prefs.getBoolean("wallpaper_weekly_video_enabled", false) && (todayWallpaperSubFolder = getTodayWallpaperSubFolder()) != null) {
            List<String> collectSortedVideoWallpaperPaths = collectSortedVideoWallpaperPaths(new File(externalStorageDirectory, "navitool/wallpaper_video/".concat(todayWallpaperSubFolder)));
            if (!collectSortedVideoWallpaperPaths.isEmpty()) {
                return collectSortedVideoWallpaperPaths;
            }
        }
        List<String> collectSortedVideoWallpaperPaths2 = collectSortedVideoWallpaperPaths(new File(externalStorageDirectory, "navitool/wallpaper_video"));
        return !collectSortedVideoWallpaperPaths2.isEmpty() ? collectSortedVideoWallpaperPaths2 : new ArrayList();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0034, code lost:
    
        if (r0.length > 0) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.io.File resolveStaticWallpaperDirectory(java.io.File r5) {
        /*
            r4 = this;
            android.content.SharedPreferences r0 = r4.prefs
            java.lang.String r1 = "wallpaper_weekly_enabled"
            r2 = 0
            boolean r0 = r0.getBoolean(r1, r2)
            if (r0 == 0) goto L37
            java.lang.String r0 = r4.getTodayWallpaperSubFolder()
            if (r0 == 0) goto L37
            java.io.File r1 = new java.io.File
            java.lang.String r3 = "navitool/wallpaper/"
            java.lang.String r0 = r3.concat(r0)
            r1.<init>(r5, r0)
            boolean r0 = r1.exists()
            if (r0 == 0) goto L37
            boolean r0 = r1.isDirectory()
            if (r0 == 0) goto L37
            com.navi.tool.ui.extra.s0 r0 = new com.navi.tool.ui.extra.s0
            r0.<init>(r2)
            java.io.File[] r0 = r1.listFiles(r0)
            if (r0 == 0) goto L37
            int r0 = r0.length
            if (r0 <= 0) goto L37
            goto L38
        L37:
            r1 = 0
        L38:
            if (r1 != 0) goto L41
            java.io.File r1 = new java.io.File
            java.lang.String r0 = "navitool/wallpaper"
            r1.<init>(r5, r0)
        L41:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.resolveStaticWallpaperDirectory(java.io.File):java.io.File");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: restackCornerMasks, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$sendMapBroadcast$108(int i7, int i8, int i9, int i10) {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences != null && !sharedPreferences.getBoolean("desktop_round_corner_enabled", false)) {
            removeCornerMasks();
            return;
        }
        addTransitionCornerMasks(i7, i8, i9, i10);
        removePrimaryCornerMasks();
        promoteTransitionToPrimaryMasks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreDesktopAfterLyricsFx() {
        SharedPreferences sharedPreferences;
        SharedPreferences sharedPreferences2;
        RealtimeBlurView.setGlobalPaused(false);
        if (this.viewWeatherAmbient != null && (sharedPreferences2 = this.prefs) != null) {
            boolean z6 = sharedPreferences2.getBoolean("desktop_weather_fx_enabled", false);
            boolean z7 = this.prefs.getBoolean("desktop_ambient_fx_enabled", false);
            if (z6 || z7) {
                this.viewWeatherAmbient.l();
            }
        }
        updateSpectrumAppearance(isCurrentNightMode());
        hideSystemUI();
        if (this.isVideoWallpaperActive && this.videoWallpaperPlayer != null && (sharedPreferences = this.prefs) != null && !sharedPreferences.getBoolean("desktop_cover_gradient_enabled", false)) {
            try {
                this.videoWallpaperPlayer.start();
            } catch (Exception unused) {
            }
        }
        if (this.mapClosedByLyricsFx) {
            this.mapClosedByLyricsFx = false;
            if (isFloatingMapMode() && canShowFloatingMap()) {
                sendMapBroadcast(getActiveMapPackage(), true);
            }
        }
    }

    private boolean samePackageList(List<AppItem> list, List<AppItem> list2) {
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            if (!list.get(i7).packageName.equals(list2.get(i7).packageName)) {
                return false;
            }
        }
        return true;
    }

    private void saveCarCardOrder() {
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
        if (desktopAssistantCarControlHelper != null) {
            desktopAssistantCarControlHelper.saveCarCardOrder();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveLayoutState() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i7 = 0;
        while (i7 < 3) {
            sb.append(this.viewOrder[i7]);
            String str = s5.h0.FRAGMENT_ENCODE_SET;
            sb.append(i7 < 2 ? "," : s5.h0.FRAGMENT_ENCODE_SET);
            sb2.append(this.viewWeights[i7]);
            if (i7 < 2) {
                str = ",";
            }
            sb2.append(str);
            i7++;
        }
        this.prefs.edit().putString(PREF_KEY_VIEW_ORDER, sb.toString()).putString(PREF_KEY_VIEW_WEIGHTS, sb2.toString()).apply();
    }

    private void saveLyrics(TreeMap<Long, String> treeMap) {
        new Thread(new f4(5, treeMap)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveMusicCover(Bitmap bitmap) {
        Bitmap copy;
        if (bitmap == null || (copy = bitmap.copy(Bitmap.Config.ARGB_8888, true)) == null) {
            return;
        }
        new Thread(new f4(4, copy)).start();
    }

    private void saveMusicMeta(String str, String str2) {
        new Thread(new z0(7, (Object) str2, str)).start();
    }

    private void scheduleVideoWatchdog(long j7) {
        cancelVideoWatchdog();
        if (j7 <= 0) {
            j7 = VIDEO_WATCHDOG_PREPARE_TIMEOUT_MS;
        }
        int i7 = this.videoStallWatchdogToken + 1;
        this.videoStallWatchdogToken = i7;
        u0 u0Var = new u0(this, i7, 0);
        this.videoStallWatchdog = u0Var;
        this.videoWallpaperHandler.postDelayed(u0Var, j7);
    }

    private void scheduleVideoWatchdogForPlayer(MediaPlayer mediaPlayer) {
        long j7 = VIDEO_WATCHDOG_PREPARE_TIMEOUT_MS;
        try {
            int duration = mediaPlayer.getDuration();
            int currentPosition = mediaPlayer.getCurrentPosition();
            if (duration > 0 && currentPosition >= 0) {
                j7 = duration - currentPosition;
            }
        } catch (Exception unused) {
        }
        if (j7 < 5000) {
            j7 = 5000;
        }
        scheduleVideoWatchdog(j7 + 5000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendCloseMap, reason: merged with bridge method [inline-methods] */
    public void lambda$switchFloatingMap$0(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        Intent intent = new Intent("com.autonavi.plus.closemap");
        intent.setPackage(str);
        intent.addFlags(32);
        sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: sendMapBroadcast, reason: merged with bridge method [inline-methods] */
    public void lambda$setupMode$71() {
        LyricsFxView lyricsFxView = this.lyricsFxView;
        if (lyricsFxView == null || !lyricsFxView.c0()) {
            sendMapBroadcast(getActiveMapPackage(), false);
        }
    }

    private void sendMapBroadcast(String str, boolean z6) {
        if (((androidx.lifecycle.a0) getLifecycle()).f2136c.a(androidx.lifecycle.r.RESUMED)) {
            LyricsFxView lyricsFxView = this.lyricsFxView;
            if ((lyricsFxView == null || !lyricsFxView.c0()) && this.mode == 0 && this.prefs.getBoolean("desktop_map_enabled", false) && isFloatingMapMode() && str != null) {
                this.mapContainer.post(new y0(this, str, z6));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFavHeartIcon(boolean z6) {
        ImageView imageView = this.imgFavHeart;
        if (imageView == null) {
            return;
        }
        if (!z6) {
            imageView.setImageResource(g4.o.ic_fav_heart_outline);
            this.imgFavHeart.setImageTintList(ColorStateList.valueOf(isCurrentNightMode() ? -1711276033 : -1));
        } else {
            imageView.setImageResource(g4.o.ic_fav_heart_solid);
            this.imgFavHeart.clearColorFilter();
            this.imgFavHeart.setImageTintList(null);
        }
    }

    private void setLoopModeIcon(int i7) {
        if (this.imgLoopMode == null) {
            return;
        }
        int i8 = isCurrentNightMode() ? -1711276033 : -1;
        if (i7 == 0) {
            this.imgLoopMode.setImageResource(g4.o.ic_loop_list);
        } else if (i7 == 1) {
            this.imgLoopMode.setImageResource(g4.o.ic_loop_single);
        } else if (i7 == 2) {
            this.imgLoopMode.setImageResource(g4.o.ic_loop_shuffle);
        } else if (i7 != 3) {
            this.imgLoopMode.setImageResource(g4.o.ic_loop_list);
        } else {
            this.imgLoopMode.setImageResource(g4.o.ic_loop_order);
        }
        this.imgLoopMode.setImageTintList(ColorStateList.valueOf(i8));
    }

    private void setSongTitleRowMarginEnd(int i7) {
        View view = this.layoutSongTitleRow;
        if (view == null) {
            return;
        }
        a0.f fVar = (a0.f) view.getLayoutParams();
        int dpToPx = dpToPx(i7);
        if (fVar.getMarginEnd() != dpToPx) {
            fVar.setMarginEnd(dpToPx);
            this.layoutSongTitleRow.setLayoutParams(fVar);
        }
    }

    private void setWallpaperWithAnimation(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        Drawable drawable = this.wallpaperView.getDrawable();
        if (drawable instanceof TransitionDrawable) {
            drawable = ((TransitionDrawable) drawable).getDrawable(1);
        }
        int i7 = 0;
        if (this.mIsCornerMaskShown) {
            this.mWindowManager.getDefaultDisplay().getRealMetrics(new DisplayMetrics());
            for (int i8 = 0; i8 < 4; i8++) {
                SmartCornerView smartCornerView = this.mCornerViews[i8];
                if (smartCornerView != null) {
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) smartCornerView.getLayoutParams();
                    this.mCornerViews[i8].setWallpaperConfig(bitmap, r1.widthPixels, r1.heightPixels, layoutParams.x, layoutParams.y);
                }
            }
        }
        if (drawable == null) {
            this.wallpaperView.setImageBitmap(bitmap);
            return;
        }
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{drawable, new BitmapDrawable(getResources(), bitmap)});
        transitionDrawable.setCrossFadeEnabled(false);
        this.wallpaperView.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(1000);
        this.wallpaperView.postDelayed(new w2(this, transitionDrawable, bitmap, 5), 1000L);
        if (this.mIsCornerMaskShown) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
            ofFloat.setDuration(1000L);
            ofFloat.addUpdateListener(new j0(this, i7));
            ofFloat.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupContent() {
        boolean z6;
        detachView(this.lyricsContentView);
        detachView(this.mapViewContent);
        View view = this.carControlContainer;
        if (view != null) {
            detachView(view);
        }
        boolean z7 = this.isMusicEnabled;
        boolean z8 = this.prefs.getBoolean("desktop_map_enabled", false);
        this.lyricsContentView.setVisibility(z7 ? 0 : 8);
        this.mapViewContent.setVisibility(z8 ? 0 : 8);
        View view2 = this.carControlContainer;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        if (!this.isCarControlEnabled || this.centerContainer == null) {
            DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
            if (desktopAssistantCarControlHelper != null) {
                desktopAssistantCarControlHelper.setCarModelClickListener(null);
            }
            this.splitContainer.setWeightSum(10.0f);
            RealtimeBlurView realtimeBlurView = this.centerContainer;
            if (realtimeBlurView != null) {
                realtimeBlurView.setVisibility(8);
            }
            View view3 = this.centerDivider;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            if (!z7 && z8) {
                View view4 = this.splitDivider;
                if (view4 != null) {
                    view4.setVisibility(8);
                }
                RealtimeBlurView realtimeBlurView2 = this.leftContainer;
                if (realtimeBlurView2 != null) {
                    realtimeBlurView2.setVisibility(0);
                    updateViewWeight(this.leftContainer, 10.0f);
                    RealtimeBlurView realtimeBlurView3 = this.rightContainer;
                    if (realtimeBlurView3 != null) {
                        realtimeBlurView3.setVisibility(8);
                    }
                    this.leftContainer.addView(this.mapViewContent, matchParentParams());
                }
            } else if (z7 && !z8) {
                View view5 = this.splitDivider;
                if (view5 != null) {
                    view5.setVisibility(8);
                }
                RealtimeBlurView realtimeBlurView4 = this.leftContainer;
                if (realtimeBlurView4 != null) {
                    realtimeBlurView4.setVisibility(0);
                    updateViewWeight(this.leftContainer, 10.0f);
                    RealtimeBlurView realtimeBlurView5 = this.rightContainer;
                    if (realtimeBlurView5 != null) {
                        realtimeBlurView5.setVisibility(8);
                    }
                    this.leftContainer.addView(this.lyricsContentView, matchParentParams());
                }
            } else if (z7 && z8) {
                View view6 = this.splitDivider;
                if (view6 != null) {
                    view6.setVisibility(0);
                }
                RealtimeBlurView realtimeBlurView6 = this.leftContainer;
                if (realtimeBlurView6 != null) {
                    realtimeBlurView6.setVisibility(0);
                }
                RealtimeBlurView realtimeBlurView7 = this.rightContainer;
                if (realtimeBlurView7 != null) {
                    realtimeBlurView7.setVisibility(0);
                }
                float f7 = this.prefs.getFloat(PREF_KEY_LEFT_WEIGHT, 4.0f);
                updateViewWeight(this.leftContainer, f7);
                updateViewWeight(this.rightContainer, 10.0f - f7);
                View view7 = this.lyricsContentView;
                if (this.isSwapped) {
                    this.leftContainer.addView(view7, matchParentParams());
                    this.rightContainer.addView(this.mapViewContent, matchParentParams());
                } else {
                    this.leftContainer.addView(this.mapViewContent, matchParentParams());
                    this.rightContainer.addView(view7, matchParentParams());
                }
            } else {
                View view8 = this.splitDivider;
                if (view8 != null) {
                    view8.setVisibility(8);
                }
                RealtimeBlurView realtimeBlurView8 = this.leftContainer;
                if (realtimeBlurView8 != null) {
                    realtimeBlurView8.setVisibility(8);
                }
                RealtimeBlurView realtimeBlurView9 = this.rightContainer;
                if (realtimeBlurView9 != null) {
                    realtimeBlurView9.setVisibility(8);
                }
            }
        } else {
            DesktopAssistantCarControlHelper desktopAssistantCarControlHelper2 = this.carControlHelper;
            if (desktopAssistantCarControlHelper2 != null) {
                desktopAssistantCarControlHelper2.setCarModelClickListener(new b1(this, 13));
            }
            boolean[] zArr = new boolean[3];
            int i7 = 0;
            float f8 = 0.0f;
            while (true) {
                if (i7 >= 3) {
                    break;
                }
                int i8 = this.viewOrder[i7];
                if (i8 == 1 && !z7) {
                    z6 = false;
                }
                if (i8 == 0 && !z8) {
                    z6 = false;
                }
                zArr[i7] = z6;
                if (z6) {
                    f8 += this.viewWeights[i7];
                }
                i7++;
            }
            LinearLayout linearLayout = this.splitContainer;
            if (f8 <= CropImageView.DEFAULT_ASPECT_RATIO) {
                f8 = 1.0f;
            }
            linearLayout.setWeightSum(f8);
            assignViewToContainer(this.leftContainer, this.viewOrder[0], this.viewWeights[0], zArr[0]);
            assignViewToContainer(this.centerContainer, this.viewOrder[1], this.viewWeights[1], zArr[1]);
            assignViewToContainer(this.rightContainer, this.viewOrder[2], this.viewWeights[2], zArr[2]);
            boolean z9 = zArr[0] && (zArr[1] || zArr[2]);
            z6 = zArr[1] && zArr[2];
            View view9 = this.splitDivider;
            if (view9 != null) {
                view9.setVisibility(z9 ? 0 : 8);
            }
            View view10 = this.centerDivider;
            if (view10 != null) {
                view10.setVisibility(z6 ? 0 : 8);
            }
        }
        applyRoundCorner(this.prefs.getBoolean("desktop_round_corner_enabled", false));
    }

    private void setupDockLogic() {
        this.btnSwitchMode.setOnClickListener(new n0(this, 10));
        loadDockApps();
        this.dockReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.14
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if ("com.navi.tool.DOCK_APPS_UPDATED".equals(intent.getAction())) {
                    DesktopAssistantActivity.this.loadDockApps();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("com.navi.tool.DOCK_APPS_UPDATED");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.dockReceiver, intentFilter, 4);
        } else {
            registerReceiver(this.dockReceiver, intentFilter);
        }
    }

    private void setupFloatingHandleInteraction(View view, boolean z6) {
        n0 n0Var = new n0(this, 11);
        AnonymousClass29 anonymousClass29 = new AnonymousClass29(z6);
        view.setOnClickListener(n0Var);
        view.setOnTouchListener(anonymousClass29);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupMode() {
        TransitionSet transitionSet = new TransitionSet();
        Slide slide = new Slide(80);
        RecyclerView recyclerView = this.appListRecyclerView;
        if (recyclerView != null) {
            slide.addTarget(recyclerView);
        }
        Fade fade = new Fade();
        transitionSet.addTransition(slide);
        transitionSet.addTransition(fade);
        transitionSet.setDuration(250L);
        transitionSet.setInterpolator((TimeInterpolator) new DecelerateInterpolator());
        RealtimeBlurView realtimeBlurView = this.dockContainer;
        if (realtimeBlurView != null) {
            slide.excludeTarget((View) realtimeBlurView, true);
            fade.excludeTarget((View) this.dockContainer, true);
        }
        FrameLayout frameLayout = this.controlOverlay;
        if (frameLayout != null) {
            slide.excludeTarget((View) frameLayout, true);
            fade.excludeTarget((View) this.controlOverlay, true);
        }
        ImageView imageView = this.btnSwitchMode;
        if (imageView != null) {
            imageView.setImageResource(this.mode == 1 ? g4.o.ic_view_split : g4.o.ic_view_apps);
        }
        int i7 = 8;
        if (this.mode == 1) {
            this.splitContainer.setVisibility(8);
            updateBackground();
            TransitionManager.beginDelayedTransition(this.contentContainer, transitionSet);
            if (this.isCleanMode) {
                RecyclerView recyclerView2 = this.appListRecyclerView;
                if (recyclerView2 != null) {
                    recyclerView2.setVisibility(8);
                }
                RealtimeBlurView realtimeBlurView2 = this.dockContainer;
                if (realtimeBlurView2 != null) {
                    realtimeBlurView2.setVisibility(8);
                }
            } else {
                RecyclerView recyclerView3 = this.appListRecyclerView;
                if (recyclerView3 != null) {
                    recyclerView3.setVisibility(0);
                    updateAppListLayoutHeight();
                    if (this.appListAdapter != null && this.cachedAppList != null) {
                        refreshAppListSilently();
                    } else if (this.cachedAppList != null) {
                        updateAppListUI(this.cachedAppList);
                    } else if (!this.appPreloadInProgress) {
                        this.contentContainer.postDelayed(new b1(this, i7), 300L);
                    }
                }
                if (this.dockContainer != null) {
                    this.dockContainer.setVisibility(this.prefs.getBoolean("desktop_hide_dock", false) ? 8 : 0);
                }
            }
            hideMapFloat();
        } else {
            TransitionManager.beginDelayedTransition(this.contentContainer, transitionSet);
            RecyclerView recyclerView4 = this.appListRecyclerView;
            if (recyclerView4 != null) {
                recyclerView4.setVisibility(8);
            }
            if (this.dockContainer != null) {
                this.dockContainer.setVisibility(this.prefs.getBoolean("desktop_hide_dock", false) ? 8 : 0);
            }
            this.contentContainer.postDelayed(new b1(this, 9), 160L);
        }
        updateTopInfoBarVisibility();
        syncMusicIslandVisibility(true);
    }

    private void setupMusicControls() {
        this.btnPrev = (ImageButton) findViewById(g4.p.btn_prev);
        this.btnPlayPause = (ImageButton) findViewById(g4.p.btn_play_pause);
        this.btnNext = (ImageButton) findViewById(g4.p.btn_next);
        this.textSongTitle = (TextView) findViewById(g4.p.text_song_title);
        this.textArtist = (TextView) findViewById(g4.p.text_artist);
        this.textCurrentDuration = (TextView) findViewById(g4.p.text_current_duration);
        this.textTotalDuration = (TextView) findViewById(g4.p.text_total_duration);
        this.viewProgress = findViewById(g4.p.view_progress);
        this.layoutProgressContainer = findViewById(g4.p.layout_progress_container);
        this.viewWeatherAmbient = (WeatherAmbientView) findViewById(g4.p.view_weather_ambient);
        FrameLayout frameLayout = this.containerDisc;
        if (frameLayout != null) {
            frameLayout.setOnClickListener(new n0(this, 5));
        }
        ImageView imageView = this.imgMusicAppIcon;
        if (imageView != null) {
            imageView.setOnClickListener(new n0(this, 6));
        }
        View view = this.layoutProgressContainer;
        if (view != null) {
            view.setOnTouchListener(new x0(this, 2));
        }
        View view2 = this.viewMusicSeekTouchArea;
        if (view2 != null) {
            view2.setOnTouchListener(new x0(this, 3));
        }
        this.btnPlayPause.setOnClickListener(new n0(this, 7));
        this.btnPrev.setOnClickListener(new n0(this, 8));
        this.btnNext.setOnClickListener(new n0(this, 9));
        updateMusicSeekUiState();
    }

    private void setupMusicPluginInteraction() {
        q0 q0Var = new q0(0, new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.28
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                DesktopAssistantActivity.this.isMusicAdjusting = true;
                DesktopAssistantActivity.this.showHandles();
                ToastHelper.show(DesktopAssistantActivity.this, "已进入布局编辑模式");
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                DesktopAssistantActivity.this.hideHandles();
                return false;
            }
        }));
        View view = this.lyricsContentView;
        if (view != null) {
            view.setOnTouchListener(q0Var);
            this.lyricsContentView.setClickable(true);
            View view2 = this.lyricsContentView;
            if ((view2 instanceof ViewGroup) && ((ViewGroup) view2).getChildCount() > 0) {
                View childAt = ((ViewGroup) this.lyricsContentView).getChildAt(0);
                childAt.setOnTouchListener(q0Var);
                childAt.setClickable(true);
            }
        }
        RecyclerView recyclerView = this.lyricsRecyclerView;
        if (recyclerView != null) {
            recyclerView.setOnTouchListener(q0Var);
        }
    }

    private void setupTwoViewInteraction() {
        View view = this.splitDivider;
        int i7 = 0;
        if (view != null) {
            view.setVisibility(0);
            final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.26
                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public void onLongPress(MotionEvent motionEvent) {
                    ToastHelper.show(DesktopAssistantActivity.this, "左右拖动可调整视图大小");
                    if (DesktopAssistantActivity.this.splitDivider != null) {
                        DesktopAssistantActivity.this.splitDivider.performHapticFeedback(0);
                    }
                }
            });
            this.splitDivider.setOnTouchListener(new View.OnTouchListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.27
                float startLeftWeight;
                float startX;
                boolean isDragging = false;
                final float DRAG_THRESHOLD = 10.0f;

                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    gestureDetector.onTouchEvent(motionEvent);
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        this.startX = motionEvent.getRawX();
                        this.startLeftWeight = ((LinearLayout.LayoutParams) DesktopAssistantActivity.this.leftContainer.getLayoutParams()).weight;
                        this.isDragging = false;
                        view2.setPressed(true);
                        return true;
                    }
                    if (action != 1) {
                        if (action == 2) {
                            float rawX = motionEvent.getRawX() - this.startX;
                            if (!this.isDragging && Math.abs(rawX) > 10.0f) {
                                this.isDragging = true;
                            }
                            if (this.isDragging) {
                                float max = Math.max(2.0f, Math.min(8.0f, this.startLeftWeight + ((rawX / DesktopAssistantActivity.this.splitContainer.getWidth()) * 10.0f)));
                                DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
                                desktopAssistantActivity.updateViewWeight(desktopAssistantActivity.leftContainer, max);
                                DesktopAssistantActivity desktopAssistantActivity2 = DesktopAssistantActivity.this;
                                desktopAssistantActivity2.updateViewWeight(desktopAssistantActivity2.rightContainer, 10.0f - max);
                            }
                            return true;
                        }
                        if (action != 3) {
                            return false;
                        }
                    }
                    view2.setPressed(false);
                    if (this.isDragging) {
                        DesktopAssistantActivity.this.prefs.edit().putFloat(DesktopAssistantActivity.PREF_KEY_LEFT_WEIGHT, ((LinearLayout.LayoutParams) DesktopAssistantActivity.this.leftContainer.getLayoutParams()).weight).apply();
                        ToastHelper.show(DesktopAssistantActivity.this, "布局已调整");
                    }
                    return true;
                }
            });
        }
        g0 g0Var = new g0(i7, this);
        View view2 = this.mapViewContent;
        if (view2 != null) {
            view2.setOnLongClickListener(g0Var);
        }
        View view3 = this.lyricsContentView;
        if (view3 != null) {
            view3.setOnLongClickListener(g0Var);
        }
        RecyclerView recyclerView = this.lyricsRecyclerView;
        if (recyclerView != null) {
            recyclerView.setOnLongClickListener(g0Var);
        }
        View.OnDragListener onDragListener = new View.OnDragListener() { // from class: com.navi.tool.ui.extra.r0
            @Override // android.view.View.OnDragListener
            public final boolean onDrag(View view4, DragEvent dragEvent) {
                boolean lambda$setupTwoViewInteraction$103;
                lambda$setupTwoViewInteraction$103 = DesktopAssistantActivity.this.lambda$setupTwoViewInteraction$103(view4, dragEvent);
                return lambda$setupTwoViewInteraction$103;
            }
        };
        RealtimeBlurView realtimeBlurView = this.leftContainer;
        if (realtimeBlurView != null) {
            realtimeBlurView.setOnDragListener(onDragListener);
        }
        RealtimeBlurView realtimeBlurView2 = this.rightContainer;
        if (realtimeBlurView2 != null) {
            realtimeBlurView2.setOnDragListener(onDragListener);
        }
    }

    private boolean shouldKeepKugouResumeDirectSync(int i7) {
        return i7 == 2 && this.mKugouResumeLyricsGuardUntilMs > 0 && SystemClock.elapsedRealtime() <= this.mKugouResumeLyricsGuardUntilMs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowClockWeatherHeader() {
        SharedPreferences sharedPreferences;
        return this.isMusicEnabled && (sharedPreferences = this.prefs) != null && sharedPreferences.getBoolean("desktop_music_clock_weather_enabled", false);
    }

    private boolean shouldUseRealSpectrum() {
        return this.prefs.getBoolean("desktop_music_real_spectrum_enabled", false) && hasRecordAudioPermission();
    }

    private void showAppSelectionDialog() {
        if (this.appSelectionLoading) {
            return;
        }
        this.appSelectionLoading = true;
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.m("正在加载应用列表…");
        nVar.k();
        androidx.appcompat.app.o e7 = nVar.e();
        this.appSelectionLoadingDialog = e7;
        e7.show();
        new Thread(new b1(this, 14), "app-selection-loader").start();
    }

    private void showAppSelectionDialogWith(List<ApplicationInfo> list) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("选择要在该区域显示的应用");
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new GridLayoutManager(4));
        nVar.u(recyclerView);
        androidx.appcompat.app.o e7 = nVar.e();
        recyclerView.setAdapter(new InstalledAppAdapter(this, list, new c0(this, e7, 0)));
        e7.show();
    }

    private void showCustomMusicAppSelectionDialog() {
        List<AppInfo> loadCustomMusicApps = loadCustomMusicApps();
        if (loadCustomMusicApps.isEmpty()) {
            ToastHelper.show(this, "没有可用的自定义应用");
            return;
        }
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        recyclerView.setAdapter(new SelectAppAdapter(this, loadCustomMusicApps, new c0(this, r2, 1)));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("选择自定义应用");
        nVar.u(recyclerView);
        nVar.o("取消", null);
        androidx.appcompat.app.o e7 = nVar.e();
        androidx.appcompat.app.o[] oVarArr = {e7};
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHandles() {
        View view = this.splitDivider;
        if (view != null && view.getVisibility() == 0) {
            if (this.mFloatingSplitHandle == null) {
                View inflate = LayoutInflater.from(this).inflate(g4.q.layout_floating_handle, (ViewGroup) null);
                this.mFloatingSplitHandle = inflate;
                setupFloatingHandleInteraction(inflate, true);
            }
            if (this.mFloatingSplitHandle.getParent() == null) {
                addFloatingHandle(this.mFloatingSplitHandle, this.splitDivider);
            }
        }
        View view2 = this.centerDivider;
        int i7 = 0;
        if (view2 != null && view2.getVisibility() == 0) {
            if (this.mFloatingCenterHandle == null) {
                View inflate2 = LayoutInflater.from(this).inflate(g4.q.layout_floating_handle, (ViewGroup) null);
                this.mFloatingCenterHandle = inflate2;
                setupFloatingHandleInteraction(inflate2, false);
            }
            if (this.mFloatingCenterHandle.getParent() == null) {
                addFloatingHandle(this.mFloatingCenterHandle, this.centerDivider);
            }
        }
        try {
            getWindow().getDecorView().performHapticFeedback(0);
        } catch (Exception unused) {
        }
        ViewGroup viewGroup = this.rootLayout;
        if (viewGroup != null) {
            viewGroup.setOnTouchListener(new x0(this, i7));
        }
    }

    private void showHvacModeGrid() {
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
        if (desktopAssistantCarControlHelper != null) {
            desktopAssistantCarControlHelper.showHvacModeGrid();
        }
    }

    private void showMusicSourcePickerDialog() {
        ArrayList arrayList = new ArrayList(DesktopAssistantMusicUiHelper.getInstalledMusicSourceCandidates(this));
        arrayList.add(new DesktopAssistantMusicUiHelper.MusicSourceCandidate(6, getPackageName(), "通用音乐"));
        arrayList.add(new DesktopAssistantMusicUiHelper.MusicSourceCandidate(6, CUSTOM_MUSIC_SOURCE_PACKAGE, "自定义应用"));
        if (arrayList.isEmpty()) {
            ToastHelper.show(this, "未找到可切换的已安装音源");
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(g4.q.dialog_music_source_picker, (ViewGroup) null, false);
        GridLayout gridLayout = (GridLayout) inflate.findViewById(g4.p.grid_music_source_picker);
        if (gridLayout == null) {
            ToastHelper.show(this, "音源面板加载失败");
            return;
        }
        SharedPreferences sharedPreferences = this.prefs;
        String string = sharedPreferences == null ? null : sharedPreferences.getString(PREF_KEY_MUSIC_PACKAGE, null);
        LayoutInflater from = LayoutInflater.from(this);
        Iterator it = arrayList.iterator();
        while (true) {
            boolean z6 = true;
            if (!it.hasNext()) {
                break;
            }
            DesktopAssistantMusicUiHelper.MusicSourceCandidate musicSourceCandidate = (DesktopAssistantMusicUiHelper.MusicSourceCandidate) it.next();
            View inflate2 = from.inflate(g4.q.item_music_source_picker, (ViewGroup) gridLayout, false);
            ImageView imageView = (ImageView) inflate2.findViewById(g4.p.img_music_source_picker_icon);
            TextView textView = (TextView) inflate2.findViewById(g4.p.text_music_source_picker_name);
            Drawable resolveAppIcon = isCustomMusicSourceCandidate(musicSourceCandidate) ? null : DesktopAssistantMusicUiHelper.resolveAppIcon(this, musicSourceCandidate.packageName);
            if (resolveAppIcon != null) {
                imageView.setImageDrawable(resolveAppIcon);
            } else if (isCustomMusicSourceCandidate(musicSourceCandidate)) {
                imageView.setImageResource(R.drawable.ic_input_add);
            } else {
                imageView.setImageResource(g4.o.ic_music_note);
            }
            textView.setText(musicSourceCandidate.displayName);
            if (isCustomMusicSourceCandidate(musicSourceCandidate) || musicSourceCandidate.source != this.prefs.getInt("desktop_music_source", 0) || !TextUtils.equals(musicSourceCandidate.packageName, string)) {
                z6 = false;
            }
            inflate2.setActivated(z6);
            inflate2.setSelected(z6);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = 0;
            layoutParams.height = -2;
            layoutParams.columnSpec = GridLayout.spec(Integer.MIN_VALUE, 1.0f);
            inflate2.setLayoutParams(layoutParams);
            gridLayout.addView(inflate2);
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.u(inflate);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setCanceledOnTouchOutside(true);
        int childCount = gridLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            gridLayout.getChildAt(i7).setOnClickListener(new k4(this, (DesktopAssistantMusicUiHelper.MusicSourceCandidate) arrayList.get(i7), e7, 2));
        }
        if (e7.getWindow() != null) {
            e7.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            e7.getWindow().setType(2038);
        }
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAlbumArtAnimation() {
        ObjectAnimator objectAnimator = this.albumArtAnimator;
        if (objectAnimator == null) {
            return;
        }
        if (objectAnimator.isPaused()) {
            this.albumArtAnimator.resume();
        } else {
            if (this.albumArtAnimator.isRunning()) {
                return;
            }
            this.albumArtAnimator.start();
        }
    }

    private void startCarousel() {
        if (this.wallpaperPaths.isEmpty()) {
            return;
        }
        this.wallpaperToken = this.wallpaperTokenGenerator.incrementAndGet();
        final int i7 = this.wallpaperToken;
        this.staticOrder.setRandom(isRandomWallpaperOrder());
        this.staticOrder.reset();
        int next = this.staticOrder.next(-1, this.wallpaperPaths.size());
        this.currentWallpaperIndex = next;
        this.staticShownCount = 1;
        decodeWallpaperAsync(this.wallpaperPaths.get(next), i7, false);
        final long j7 = this.prefs.getInt("wallpaper_carousel_interval", 5) * 1000;
        Runnable runnable = new Runnable() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.25
            @Override // java.lang.Runnable
            public void run() {
                if (DesktopAssistantActivity.this.wallpaperPaths.isEmpty() || i7 != DesktopAssistantActivity.this.wallpaperToken) {
                    return;
                }
                boolean z6 = DesktopAssistantActivity.this.isMixedWallpaperEnabled() && !DesktopAssistantActivity.this.videoWallpaperPaths.isEmpty();
                if (DesktopAssistantActivity.this.staticShownCount >= DesktopAssistantActivity.this.wallpaperPaths.size()) {
                    if (z6) {
                        DesktopAssistantActivity.this.mixedSegmentIsVideo = true;
                        DesktopAssistantActivity.this.mixedForceSegment = true;
                        DesktopAssistantActivity.this.startVideoSegmentInternal();
                        return;
                    }
                    DesktopAssistantActivity.this.staticShownCount = 0;
                }
                DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
                desktopAssistantActivity.currentWallpaperIndex = desktopAssistantActivity.staticOrder.next(DesktopAssistantActivity.this.currentWallpaperIndex, DesktopAssistantActivity.this.wallpaperPaths.size());
                DesktopAssistantActivity.access$6908(DesktopAssistantActivity.this);
                DesktopAssistantActivity desktopAssistantActivity2 = DesktopAssistantActivity.this;
                desktopAssistantActivity2.decodeWallpaperAsync((String) desktopAssistantActivity2.wallpaperPaths.get(DesktopAssistantActivity.this.currentWallpaperIndex), i7, true);
                DesktopAssistantActivity.this.carouselHandler.postDelayed(this, j7);
            }
        };
        this.carouselRunnable = runnable;
        this.carouselHandler.postDelayed(runnable, j7);
    }

    private boolean startDesktopRealtimeSpectrumIfPossible() {
        boolean z6 = false;
        if (this.viewSpectrum == null || isFinishing()) {
            return false;
        }
        ensureDesktopSpectrumAnalyzer();
        if (this.desktopSpectrumAnalyzer == null) {
            return false;
        }
        if (this.desktopSpectrumBarCount <= 0 && this.viewSpectrum.getBarCount() > 0) {
            int barCount = this.viewSpectrum.getBarCount();
            this.desktopSpectrumBarCount = barCount;
            this.desktopSpectrumAnalyzer.c(barCount);
        }
        m4.a aVar = this.desktopSpectrumAnalyzer;
        synchronized (aVar.f7552b) {
            try {
                if (!aVar.f7558h) {
                    boolean z7 = aVar.f7551a.checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
                    if (aVar.f7557g > 0 && z7) {
                        Visualizer visualizer = new Visualizer(0);
                        aVar.f7553c = visualizer;
                        visualizer.setEnabled(false);
                        aVar.f7553c.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
                        aVar.f7553c.setDataCaptureListener(aVar, Visualizer.getMaxCaptureRate(), false, true);
                        aVar.f7553c.setEnabled(true);
                        aVar.b();
                        aVar.f7558h = true;
                    }
                }
                z6 = true;
            } catch (Throwable unused) {
                Visualizer visualizer2 = aVar.f7553c;
                if (visualizer2 != null) {
                    try {
                        visualizer2.setEnabled(false);
                    } catch (Throwable unused2) {
                    }
                    try {
                        aVar.f7553c.release();
                    } catch (Throwable unused3) {
                    }
                    aVar.f7553c = null;
                }
                aVar.b();
                aVar.f7558h = false;
            } finally {
            }
        }
        if (z6) {
            this.viewSpectrum.setUseExternalLevels(true);
            this.viewSpectrum.e();
        }
        return z6;
    }

    private void startStaticSegmentInternal() {
        if (this.wallpaperPaths.isEmpty()) {
            return;
        }
        stopCarousel();
        this.isVideoWallpaperActive = false;
        cancelVideoWatchdog();
        releaseVideoWallpaperPlayer();
        TextureView textureView = this.videoWallpaperView;
        if (textureView != null) {
            textureView.setVisibility(8);
        }
        View view = this.videoWallpaperMask;
        if (view != null) {
            view.setVisibility(8);
        }
        ImageView imageView = this.wallpaperView;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        applyRoundCorner(this.prefs.getBoolean("desktop_round_corner_enabled", false));
        startCarousel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVideoSegmentInternal() {
        if (this.videoWallpaperPaths.isEmpty()) {
            return;
        }
        this.isVideoWallpaperActive = true;
        this.videoOrder.setRandom(isRandomWallpaperOrder());
        this.videoOrder.reset();
        int next = this.videoOrder.next(-1, this.videoWallpaperPaths.size());
        this.currentVideoWallpaperIndex = next;
        this.currentVideoWallpaperPath = this.videoWallpaperPaths.get(next);
        this.videoShownCount = 1;
        ensureRoundCornerDisabledForVideo();
        TextureView textureView = this.videoWallpaperView;
        if (textureView != null) {
            textureView.setVisibility(0);
        }
        View view = this.videoWallpaperMask;
        if (view != null) {
            view.setVisibility(0);
        }
        startVideoWallpaper(this.currentVideoWallpaperPath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVideoWallpaper(final String str) {
        SurfaceTexture surfaceTexture;
        if (this.videoWallpaperView == null) {
            return;
        }
        SharedPreferences sharedPreferences = this.prefs;
        int i7 = 0;
        if (sharedPreferences != null && sharedPreferences.getBoolean("desktop_cover_gradient_enabled", false)) {
            this.currentVideoWallpaperPath = str;
            this.isVideoWallpaperActive = false;
            return;
        }
        this.currentVideoWallpaperPath = str;
        boolean z6 = true;
        this.isVideoWallpaperActive = true;
        if (this.videoWallpaperSurface == null && this.videoWallpaperView.isAvailable() && (surfaceTexture = this.videoWallpaperView.getSurfaceTexture()) != null) {
            this.videoWallpaperSurface = new Surface(surfaceTexture);
        }
        if (this.videoWallpaperSurface == null) {
            return;
        }
        releaseVideoWallpaperPlayer();
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(str);
            mediaPlayer.setSurface(this.videoWallpaperSurface);
            if (this.videoWallpaperPaths.size() > 1 || isMixedWallpaperEnabled()) {
                z6 = false;
            }
            mediaPlayer.setLooping(z6);
            mediaPlayer.setVolume(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.navi.tool.ui.extra.f0
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    DesktopAssistantActivity.this.lambda$startVideoWallpaper$88(str, mediaPlayer2);
                }
            });
            mediaPlayer.setOnCompletionListener(new h0(this, i7));
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.navi.tool.ui.extra.i0
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer2, int i8, int i9) {
                    boolean lambda$startVideoWallpaper$91;
                    lambda$startVideoWallpaper$91 = DesktopAssistantActivity.this.lambda$startVideoWallpaper$91(str, mediaPlayer2, i8, i9);
                    return lambda$startVideoWallpaper$91;
                }
            });
            this.videoWallpaperPlayer = mediaPlayer;
            mediaPlayer.prepareAsync();
            scheduleVideoWatchdog(VIDEO_WATCHDOG_PREPARE_TIMEOUT_MS);
        } catch (Exception unused) {
            releaseVideoWallpaperPlayer();
            if (tryPlayNextVideoWallpaperAfterError(str)) {
                return;
            }
            this.isVideoWallpaperActive = false;
            this.videoWallpaperPaths.clear();
            this.currentVideoWallpaperIndex = 0;
            this.currentVideoWallpaperPath = null;
            TextureView textureView = this.videoWallpaperView;
            if (textureView != null) {
                textureView.setVisibility(8);
            }
            View view = this.videoWallpaperMask;
            if (view != null) {
                view.setVisibility(8);
            }
            if (isFinishing() || isDestroyed()) {
                return;
            }
            loadWallpaper();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAlbumArtAnimation() {
        ObjectAnimator objectAnimator = this.albumArtAnimator;
        if (objectAnimator == null || !objectAnimator.isRunning()) {
            return;
        }
        this.albumArtAnimator.pause();
    }

    private void stopAllMusicServices() {
        DesktopAssistantMusicUiHelper.stopAllMusicServices(this, this.textSongTitle, this.textArtist, this.textCurrentDuration, this.textTotalDuration, this.lyricsAdapter, this.btnPlayPause, this.viewSpectrum);
        this.isPlaying = false;
        this.mIsDraggingMusicProgress = false;
        this.mLastKnownCurrentPosition = 0L;
        this.mLastKnownTotalDuration = 0L;
        updateSingleLineLyricsUi();
        updateMusicSeekUiState();
    }

    private void stopCarousel() {
        this.wallpaperToken = this.wallpaperTokenGenerator.incrementAndGet();
        Runnable runnable = this.carouselRunnable;
        if (runnable != null) {
            this.carouselHandler.removeCallbacks(runnable);
            this.carouselRunnable = null;
        }
    }

    private void stopDesktopRealtimeSpectrumAnalyzer() {
        m4.a aVar = this.desktopSpectrumAnalyzer;
        if (aVar != null) {
            synchronized (aVar.f7552b) {
                Visualizer visualizer = aVar.f7553c;
                if (visualizer != null) {
                    try {
                        visualizer.setEnabled(false);
                    } catch (Throwable unused) {
                    }
                    try {
                        aVar.f7553c.release();
                    } catch (Throwable unused2) {
                    }
                    aVar.f7553c = null;
                }
                aVar.b();
                aVar.f7558h = false;
            }
        }
    }

    private void stopDesktopSpectrum() {
        stopDesktopRealtimeSpectrumAnalyzer();
        SpectrumView spectrumView = this.viewSpectrum;
        if (spectrumView != null) {
            spectrumView.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void swapCarControlAndMusic() {
        int i7 = -1;
        int i8 = -1;
        for (int i9 = 0; i9 < 3; i9++) {
            int i10 = this.viewOrder[i9];
            if (i10 == 2) {
                i7 = i9;
            }
            if (i10 == 1) {
                i8 = i9;
            }
        }
        if (i7 < 0 || i8 < 0 || i7 == i8) {
            return;
        }
        int[] iArr = this.viewOrder;
        int i11 = iArr[i7];
        iArr[i7] = iArr[i8];
        iArr[i8] = i11;
        float[] fArr = this.viewWeights;
        float f7 = fArr[i7];
        fArr[i7] = fArr[i8];
        fArr[i8] = f7;
        saveLayoutState();
        setupContent();
    }

    private void switchDesktopMusicSource(DesktopAssistantMusicUiHelper.MusicSourceCandidate musicSourceCandidate) {
        SharedPreferences sharedPreferences;
        if (musicSourceCandidate == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        if (this.isPlaying) {
            DesktopAssistantMusicUiHelper.dispatchMusicControl(this, sharedPreferences, true, 0);
        }
        String string = this.prefs.getString(PREF_KEY_MUSIC_PACKAGE, null);
        String str = musicSourceCandidate.packageName;
        if (TextUtils.isEmpty(str)) {
            str = DesktopAssistantMusicUiHelper.resolveMusicPackageForSource(musicSourceCandidate.source, this.prefs);
        }
        if (!DesktopAssistantMusicUiHelper.switchMusicSource(this, this.prefs, musicSourceCandidate.source, musicSourceCandidate.packageName)) {
            ToastHelper.show(this, "切换音源失败");
            return;
        }
        boolean z6 = this.prefs.getBoolean("auto_launch_floating_apps_enabled", false);
        if (z6 && !TextUtils.isEmpty(string) && !string.equals(str) && SharedPreferencesUtils.isAutoStartAppEnabled(this, string)) {
            SharedPreferencesUtils.removeAutoStartApp(this, string);
        }
        if (z6 && !TextUtils.isEmpty(str) && !SharedPreferencesUtils.isAutoStartAppEnabled(this, str)) {
            int nextAutoStartDelay = SharedPreferencesUtils.getNextAutoStartDelay(this, str);
            SharedPreferencesUtils.setAutoStartAppEnabled(this, str, true);
            SharedPreferencesUtils.setAutoStartAppDelay(this, str, nextAutoStartDelay);
        }
        DesktopAssistantMusicUiHelper.stopMusicManagers(this);
        this.isPlaying = false;
        this.mPlayMode = -1;
        this.mLoopCycleIndex = -1;
        this.mLastKnownCurrentPosition = 0L;
        this.mLastKnownTotalDuration = 0L;
        this.mLastKnownTitle = s5.h0.FRAGMENT_ENCODE_SET;
        this.mLastKnownArtist = s5.h0.FRAGMENT_ENCODE_SET;
        this.mLastLoadingTitle = s5.h0.FRAGMENT_ENCODE_SET;
        this.mCurrentLyricLine = s5.h0.FRAGMENT_ENCODE_SET;
        TextView textView = this.textCurrentLyric;
        if (textView != null) {
            textView.setText(s5.h0.FRAGMENT_ENCODE_SET);
        }
        LyricsAdapter lyricsAdapter = this.lyricsAdapter;
        if (lyricsAdapter != null) {
            lyricsAdapter.setLyrics(new TreeMap<>());
            this.lyricsAdapter.notifyDataSetChanged();
        }
        DesktopAssistantMusicUiHelper.applyDefaultCover(this.imgAlbumArtFull);
        initMusicService();
        updateMusicUI();
        updateLoopModeState();
        updateFavHeartState();
        ToastHelper.show(this, "已切换到 " + musicSourceCandidate.displayName);
    }

    private void switchFloatingMap(int i7) {
        if (this.prefs == null) {
            return;
        }
        String activeMapPackage = getActiveMapPackage();
        String mapPackageAt = getMapPackageAt(i7);
        if (mapPackageAt == null || mapPackageAt.isEmpty()) {
            ToastHelper.show(this, "仅设置了一个悬浮地图");
            return;
        }
        this.prefs.edit().putInt(KEY_MAP_ACTIVE_INDEX, i7).apply();
        if (mapPackageAt.equals(activeMapPackage) || !canShowFloatingMap()) {
            notifyFloatingMapModeChanged();
            return;
        }
        int i8 = 1;
        sendMapBroadcast(mapPackageAt, true);
        FrameLayout frameLayout = this.mapContainer;
        if (frameLayout != null) {
            frameLayout.postDelayed(new w0(this, activeMapPackage, i8), SWITCH_CLOSE_OLD_DELAY_MS);
        } else {
            lambda$switchFloatingMap$0(activeMapPackage);
        }
        notifyFloatingMapModeChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncGnssDataSource() {
        GnssInfoProvider gnssInfoProvider = GnssInfoProvider.getInstance();
        String[] strArr = {"gnss_altitude", "gnss_accuracy", "gnss_satellites"};
        for (int i7 = 0; i7 < 3; i7++) {
            String str = strArr[i7];
            if (this.prefs.getBoolean("top_info_item_enabled_" + str, false)) {
                gnssInfoProvider.start(getApplicationContext());
                if (this.gnssObserverRegistered) {
                    return;
                }
                this.gnssObserverRegistered = true;
                z zVar = new z(this, 9);
                gnssInfoProvider.getAltitude().e(this, zVar);
                gnssInfoProvider.getAccuracy().e(this, zVar);
                gnssInfoProvider.getSatellites().e(this, zVar);
                return;
            }
        }
        gnssInfoProvider.stop(getApplicationContext());
    }

    private void syncMusicIslandVisibility(boolean z6) {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            return;
        }
        boolean z7 = false;
        boolean z8 = sharedPreferences.getBoolean("desktop_music_enabled", false);
        boolean z9 = this.prefs.getBoolean("desktop_music_island_enabled", false);
        if (!z8 || !z9) {
            MusicIslandService.stop(this);
            return;
        }
        if (z6 && this.mode == 0) {
            z7 = true;
        }
        MusicIslandService.notifyDesktopAssistantVisibility(this, z7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleCleanMode() {
        this.isCleanMode = !this.isCleanMode;
        this.prefs.edit().putBoolean(PREF_KEY_CLEAN_MODE, this.isCleanMode).apply();
        updateTopInfoBarVisibility();
        setupMode();
        if (this.isCleanMode) {
            ToastHelper.show(this, "已进入清屏模式");
        } else {
            ToastHelper.show(this, "已进入壁纸视图");
        }
    }

    private boolean tryPlayNextVideoWallpaperAfterError(String str) {
        if (this.videoWallpaperPaths.isEmpty()) {
            return false;
        }
        int indexOf = this.videoWallpaperPaths.indexOf(str);
        if (indexOf >= 0) {
            this.videoWallpaperPaths.remove(indexOf);
            this.videoOrder.reset();
        }
        if (this.videoWallpaperPaths.isEmpty()) {
            return false;
        }
        if (this.videoWallpaperPaths.size() == 1) {
            this.currentVideoWallpaperIndex = 0;
        } else {
            this.videoOrder.setRandom(isRandomWallpaperOrder());
            this.currentVideoWallpaperIndex = this.videoOrder.next(-1, this.videoWallpaperPaths.size());
        }
        this.videoShownCount = 1;
        String str2 = this.videoWallpaperPaths.get(this.currentVideoWallpaperIndex);
        this.currentVideoWallpaperPath = str2;
        startVideoWallpaper(str2);
        return true;
    }

    private void updateAppListLayoutHeight() {
        if (this.appListRecyclerView == null) {
            return;
        }
        int i7 = this.prefs.getInt("desktop_app_icon_size", 76);
        int i8 = 14;
        int i9 = 4;
        if (i7 > 60 && (i7 >= 76 || i7 > 68)) {
            i8 = 18;
            i9 = 3;
        }
        int i10 = (int) ((i7 + 92 + i8 + 6) * i9 * getResources().getDisplayMetrics().density);
        ViewGroup.LayoutParams layoutParams = this.appListRecyclerView.getLayoutParams();
        if (layoutParams.height == i10) {
            this.appListRecyclerView.requestLayout();
        } else {
            layoutParams.height = i10;
            this.appListRecyclerView.setLayoutParams(layoutParams);
        }
    }

    private void updateAppListUI(List<AppItem> list) {
        DesktopAppAdapter desktopAppAdapter = new DesktopAppAdapter(this, list, new z(this, 5));
        this.appListAdapter = desktopAppAdapter;
        desktopAppAdapter.setShowPackageName(false);
        this.appListAdapter.setAppNameColor(Integer.valueOf(this.prefs.getInt("desktop_app_text_color", 0) == 1 ? p0.d1.MEASURED_STATE_MASK : -1));
        int i7 = this.prefs.getInt("desktop_app_icon_size", 76);
        this.appListAdapter.setIconSize(i7);
        int i8 = 14;
        int i9 = 4;
        if (i7 > 60 && (i7 >= 76 || i7 > 68)) {
            i8 = 18;
            i9 = 3;
        }
        int i10 = (int) ((i7 + 92 + i8 + 6) * i9 * getResources().getDisplayMetrics().density);
        RecyclerView recyclerView = this.appListRecyclerView;
        if (recyclerView != null) {
            androidx.recyclerview.widget.r1 layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                if (gridLayoutManager.f2280p != 1) {
                    gridLayoutManager.g1(1);
                    gridLayoutManager.s1(6);
                }
            } else {
                this.appListRecyclerView.setLayoutManager(new GridLayoutManager(6));
            }
            ViewGroup.LayoutParams layoutParams = this.appListRecyclerView.getLayoutParams();
            if (layoutParams.height != i10) {
                layoutParams.height = i10;
                this.appListRecyclerView.setLayoutParams(layoutParams);
            }
            this.appListRecyclerView.setAdapter(this.appListAdapter);
        }
        this.appListAdapter.setNightMode(isCurrentNightMode());
    }

    private void updateAuxiliaryClockVisibility() {
        boolean shouldShowClockWeatherHeader = shouldShowClockWeatherHeader();
        TextClock textClock = this.textClock;
        if (textClock != null) {
            textClock.setVisibility(shouldShowClockWeatherHeader ? 8 : 0);
        }
        TextClock textClock2 = this.topTextDate;
        if (textClock2 != null) {
            textClock2.setVisibility(shouldShowClockWeatherHeader ? 8 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBackground() {
        int i7;
        this.wallpaperView.setVisibility(0);
        boolean z6 = this.prefs.getBoolean("desktop_transparent_bg", false);
        int i8 = this.prefs.getInt("desktop_blur_radius", 10);
        boolean isCurrentNightMode = isCurrentNightMode();
        if (isCurrentNightMode) {
            i7 = Color.argb((int) (((this.prefs != null ? r0.getInt("desktop_night_mode_alpha", 70) : 70) * 255) / 100.0f), 0, 0, 0);
        } else {
            i7 = z6 ? 0 : 872415231;
        }
        ImageView imageView = this.wallpaperView;
        if (imageView != null) {
            if (isCurrentNightMode) {
                this.wallpaperView.setColorFilter(Color.argb((int) (((this.prefs != null ? r7.getInt("desktop_night_mode_alpha", 70) : 70) * 255) / 100.0f), 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
            } else {
                imageView.clearColorFilter();
            }
        }
        updateVideoWallpaperMask(i8, isCurrentNightMode);
        float f7 = i8;
        this.leftContainer.setBlurEnabled(true);
        this.rightContainer.setBlurEnabled(true);
        RealtimeBlurView realtimeBlurView = this.centerContainer;
        if (realtimeBlurView != null) {
            realtimeBlurView.setBlurEnabled(true);
        }
        RealtimeBlurView realtimeBlurView2 = this.dockContainer;
        if (realtimeBlurView2 != null) {
            realtimeBlurView2.setBlurEnabled(true);
        }
        this.leftContainer.setBlurRadius(f7);
        this.rightContainer.setBlurRadius(f7);
        this.leftContainer.setOverlayColor(i7);
        this.rightContainer.setOverlayColor(i7);
        RealtimeBlurView realtimeBlurView3 = this.centerContainer;
        if (realtimeBlurView3 != null) {
            realtimeBlurView3.setBlurRadius(f7);
            this.centerContainer.setOverlayColor(i7);
        }
        RealtimeBlurView realtimeBlurView4 = this.dockContainer;
        if (realtimeBlurView4 != null) {
            realtimeBlurView4.setBlurRadius(f7);
            this.dockContainer.setOverlayColor(i7);
        }
        applyTopInfoBarStyle();
        applyRoundCorner(this.prefs.getBoolean("desktop_round_corner_enabled", false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCarControlCards() {
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
        if (desktopAssistantCarControlHelper != null) {
            desktopAssistantCarControlHelper.setState(this.isCarControlEnabled, this.isVisualCarModelEnabled, this.isSeatComfortEnabled);
            this.carControlHelper.updateCarControlCards();
        }
    }

    private void updateClockWeatherHeaderVisibility() {
        boolean shouldShowClockWeatherHeader = shouldShowClockWeatherHeader();
        View view = this.layoutClockWeatherHeader;
        if (view != null) {
            view.setVisibility(shouldShowClockWeatherHeader ? 0 : 8);
        }
        updateMusicCardSpacing(shouldShowClockWeatherHeader);
        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = this.clockWeatherController;
        if (desktopAssistantClockWeatherController != null) {
            desktopAssistantClockWeatherController.setEnabled(shouldShowClockWeatherHeader);
        }
        updateAuxiliaryClockVisibility();
        updateSingleLineLyricsUi();
    }

    private void updateCornerMasks(int i7, int i8, int i9, int i10) {
        updateCornerMasks(i7, i8, i9, i10, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0139, code lost:
    
        if (r35 != false) goto L62;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateCornerMasks(int r31, int r32, int r33, int r34, boolean r35) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.updateCornerMasks(int, int, int, int, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCoverGradientFromBitmap(Bitmap bitmap) {
        SharedPreferences sharedPreferences;
        if (bitmap == null || bitmap.isRecycled() || this.coverGradientBackground == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        if (sharedPreferences.getBoolean("desktop_cover_gradient_enabled", false)) {
            final int i7 = this.coverGradientRequestToken + 1;
            this.coverGradientRequestToken = i7;
            final boolean isCurrentNightMode = isCurrentNightMode();
            try {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 64, 64, true);
                if (createScaledBitmap == bitmap) {
                    createScaledBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                    if (createScaledBitmap == null) {
                        return;
                    }
                }
                boolean z6 = createScaledBitmap != bitmap;
                try {
                    final Bitmap bitmap2 = createScaledBitmap;
                    final boolean z7 = z6;
                    new Thread(new Runnable() { // from class: com.navi.tool.ui.extra.k0
                        @Override // java.lang.Runnable
                        public final void run() {
                            DesktopAssistantActivity.this.lambda$updateCoverGradientFromBitmap$46(bitmap2, isCurrentNightMode, z7, i7);
                        }
                    }, "CoverGradientPalette").start();
                } catch (Throwable unused) {
                    if (z6) {
                        createScaledBitmap.recycle();
                    }
                }
            } catch (Throwable unused2) {
            }
        }
    }

    private void updateFavHeartState() {
        ImageView imageView = this.imgFavHeart;
        if (imageView == null) {
            return;
        }
        if (!this.isMusicEnabled) {
            imageView.setVisibility(8);
            setSongTitleRowMarginEnd(10);
            return;
        }
        int i7 = this.prefs.getInt("desktop_music_source", 0);
        boolean z6 = i7 == 0 || i7 == 5;
        if (this.mLastFavSource != i7) {
            this.mLastFavSource = i7;
            setSongTitleRowMarginEnd(z6 ? 60 : 10);
        }
        if (!z6) {
            this.imgFavHeart.setVisibility(8);
        } else {
            this.imgFavHeart.setVisibility(0);
            setFavHeartIcon(this.mIsFavorite);
        }
    }

    private void updateFloatingMusicAppIcon() {
        SharedPreferences sharedPreferences;
        ImageView imageView = this.imgMusicAppIcon;
        if (imageView == null || (sharedPreferences = this.prefs) == null || !this.isMusicEnabled) {
            if (imageView != null) {
                imageView.setVisibility(8);
                return;
            }
            return;
        }
        String string = sharedPreferences.getString(PREF_KEY_MUSIC_PACKAGE, null);
        if (this.prefs.getInt("desktop_music_source", 0) == 6) {
            String string2 = this.prefs.getString("universal_music_current_app", null);
            if (!TextUtils.isEmpty(string2)) {
                string = string2;
            }
        }
        if (TextUtils.isEmpty(string)) {
            this.imgMusicAppIcon.setImageDrawable(null);
            this.imgMusicAppIcon.setVisibility(8);
            return;
        }
        try {
            Drawable applicationIcon = getPackageManager().getApplicationIcon(string);
            if (applicationIcon != null) {
                this.imgMusicAppIcon.setImageDrawable(applicationIcon);
                this.imgMusicAppIcon.setVisibility(0);
            } else {
                this.imgMusicAppIcon.setImageDrawable(null);
                this.imgMusicAppIcon.setVisibility(8);
            }
        } catch (Exception unused) {
            this.imgMusicAppIcon.setImageDrawable(null);
            this.imgMusicAppIcon.setVisibility(8);
        }
    }

    private void updateLoopModeState() {
        ImageView imageView = this.imgLoopMode;
        if (imageView == null) {
            return;
        }
        if (!this.isMusicEnabled) {
            imageView.setVisibility(8);
            return;
        }
        int i7 = this.prefs.getInt("desktop_music_source", 0);
        if (i7 != 0 && i7 != 5 && i7 != 1 && i7 != 4) {
            this.imgLoopMode.setVisibility(8);
        } else {
            this.imgLoopMode.setVisibility(0);
            setLoopModeIcon(this.mPlayMode);
        }
    }

    private void updateLyricsColor() {
        DesktopAssistantMusicUiHelper.updateLyricsColor(this.prefs, this.lyricsAdapter);
        updateSingleLineLyricsUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004c, code lost:
    
        if (r0 != false) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateLyricsHeight() {
        /*
            r6 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            if (r0 != 0) goto L5
            return
        L5:
            com.navi.tool.ui.extra.LyricsAdapter r0 = r6.lyricsAdapter
            if (r0 == 0) goto Le
            int r0 = r0.getItemHeight()
            goto L24
        Le:
            android.content.res.Resources r0 = r6.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.scaledDensity
            int r1 = r6.lyricsFontSize
            int r1 = r1 + 4
            float r1 = (float) r1
            float r1 = r1 * r0
            r0 = 1069547520(0x3fc00000, float:1.5)
            float r1 = r1 * r0
            int r0 = (int) r1
            int r0 = r0 + 32
        L24:
            int r1 = r6.getEffectiveLyricsLines()
            int r0 = r0 * r1
            androidx.recyclerview.widget.RecyclerView r1 = r6.lyricsRecyclerView
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            r2 = 0
            if (r1 == 0) goto L68
            int r3 = r1.height
            if (r3 == r0) goto L3a
            r1.height = r0
            r0 = 1
            goto L3b
        L3a:
            r0 = r2
        L3b:
            boolean r3 = r1 instanceof android.widget.LinearLayout.LayoutParams
            if (r3 == 0) goto L4c
            r3 = r1
            android.widget.LinearLayout$LayoutParams r3 = (android.widget.LinearLayout.LayoutParams) r3
            float r4 = r3.weight
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 == 0) goto L4c
            r3.weight = r5
            goto L4e
        L4c:
            if (r0 == 0) goto L68
        L4e:
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            r0.setLayoutParams(r1)
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            android.view.ViewParent r0 = r0.getParent()
            boolean r0 = r0 instanceof android.view.View
            if (r0 == 0) goto L68
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            r0.requestLayout()
        L68:
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            r0.setPadding(r2, r2, r2, r2)
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            r0.setClipToPadding(r2)
            com.navi.tool.ui.extra.LyricsAdapter r0 = r6.lyricsAdapter
            if (r0 == 0) goto Ld2
            androidx.recyclerview.widget.x0 r0 = r6.smoothScroller
            if (r0 == 0) goto Ld2
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            androidx.recyclerview.widget.r1 r0 = r0.getLayoutManager()
            if (r0 == 0) goto Ld2
            com.navi.tool.ui.extra.LyricsAdapter r0 = r6.lyricsAdapter
            int r0 = r0.getCurrentHighlightIndex()
            r1 = -1
            if (r0 == r1) goto Ld2
            boolean r1 = r6.mPreferDirectLyricsLayoutScroll
            if (r1 == 0) goto Lc2
            androidx.recyclerview.widget.RecyclerView r1 = r6.lyricsRecyclerView
            androidx.recyclerview.widget.r1 r1 = r1.getLayoutManager()
            boolean r1 = r1 instanceof androidx.recyclerview.widget.LinearLayoutManager
            if (r1 == 0) goto Lc2
            androidx.recyclerview.widget.RecyclerView r1 = r6.lyricsRecyclerView
            androidx.recyclerview.widget.r1 r1 = r1.getLayoutManager()
            androidx.recyclerview.widget.LinearLayoutManager r1 = (androidx.recyclerview.widget.LinearLayoutManager) r1
            androidx.recyclerview.widget.RecyclerView r3 = r6.lyricsRecyclerView
            android.view.View r3 = r3.getChildAt(r2)
            if (r3 == 0) goto Lae
            int r3 = r3.getHeight()
            goto Laf
        Lae:
            r3 = r2
        Laf:
            androidx.recyclerview.widget.RecyclerView r4 = r6.lyricsRecyclerView
            int r4 = r4.getHeight()
            int r4 = r4 - r3
            int r4 = r4 / 2
            int r3 = java.lang.Math.max(r2, r4)
            r1.f1(r0, r3)
            r6.mPreferDirectLyricsLayoutScroll = r2
            goto Ld2
        Lc2:
            androidx.recyclerview.widget.x0 r1 = r6.smoothScroller
            r1.setTargetPosition(r0)
            androidx.recyclerview.widget.RecyclerView r0 = r6.lyricsRecyclerView
            androidx.recyclerview.widget.r1 r0 = r0.getLayoutManager()
            androidx.recyclerview.widget.x0 r1 = r6.smoothScroller
            r0.D0(r1)
        Ld2:
            r6.updateTopInfoBarVisibility()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.updateLyricsHeight():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMapCalendarVisibility() {
        SharedPreferences sharedPreferences;
        if (this.mapCalendarCardView == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        boolean z6 = sharedPreferences.getBoolean("desktop_map_enabled", false) && getFloatingMapDisplayMode() == 1;
        this.mapCalendarCardView.setVisibility(z6 ? 0 : 8);
        FrameLayout frameLayout = this.mapContainer;
        if (frameLayout != null) {
            frameLayout.setVisibility(z6 ? 8 : 0);
            this.mapContainer.setEnabled(!z6);
            this.mapContainer.setClickable(!z6);
        }
        if (z6) {
            hideMapFloat();
            this.mapCalendarCardView.setEnabled(true);
            this.mapCalendarCardView.setClickable(true);
            this.mapCalendarCardView.bringToFront();
            this.mapCalendarCardView.d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMapPlaceholderState() {
        TextView textView = (TextView) findViewById(g4.p.text_map_placeholder);
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    private void updateMusicCardSpacing(boolean z6) {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            return;
        }
        boolean z7 = sharedPreferences.getBoolean("desktop_music_show_album_art", false);
        FrameLayout frameLayout = this.containerDisc;
        if (frameLayout != null) {
            a0.f fVar = (a0.f) frameLayout.getLayoutParams();
            int i7 = z7 ? 0 : 8;
            int i8 = z7 ? z6 ? 10 : 6 : z6 ? 2 : 0;
            int i9 = z7 ? 6 : 0;
            int dpToPx = dpToPx(i8);
            int dpToPx2 = dpToPx(i9);
            if (((ViewGroup.MarginLayoutParams) fVar).topMargin != dpToPx || fVar.f51x != dpToPx2) {
                ((ViewGroup.MarginLayoutParams) fVar).topMargin = dpToPx;
                fVar.f51x = dpToPx2;
                this.containerDisc.setLayoutParams(fVar);
            }
            if (this.containerDisc.getVisibility() != i7) {
                this.containerDisc.setVisibility(i7);
            }
        }
        View view = this.layoutSongTitleRow;
        if (view != null) {
            a0.f fVar2 = (a0.f) view.getLayoutParams();
            int i10 = z7 ? g4.p.container_disc : g4.p.layout_clock_weather_header;
            int i11 = z7 ? 18 : 8;
            int i12 = z6 ? 8 : 0;
            int dpToPx3 = dpToPx(i11);
            int dpToPx4 = dpToPx(i12);
            if (fVar2.f29j == i10 && ((ViewGroup.MarginLayoutParams) fVar2).topMargin == dpToPx3 && fVar2.f51x == dpToPx4) {
                return;
            }
            fVar2.f29j = i10;
            fVar2.f27i = -1;
            ((ViewGroup.MarginLayoutParams) fVar2).topMargin = dpToPx3;
            fVar2.f51x = dpToPx4;
            this.layoutSongTitleRow.setLayoutParams(fVar2);
        }
    }

    private void updateMusicSeekUiState() {
        boolean canDragMusicProgress = canDragMusicProgress();
        View view = this.layoutProgressContainer;
        if (view != null) {
            view.setEnabled(canDragMusicProgress);
            this.layoutProgressContainer.setClickable(canDragMusicProgress);
            this.layoutProgressContainer.setAlpha(canDragMusicProgress ? 1.0f : 0.55f);
        }
        View view2 = this.viewMusicSeekTouchArea;
        if (view2 != null) {
            view2.setEnabled(canDragMusicProgress);
            this.viewMusicSeekTouchArea.setClickable(canDragMusicProgress);
        }
        View view3 = this.viewProgress;
        if (view3 != null) {
            view3.setAlpha(canDragMusicProgress ? 1.0f : 0.45f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMusicUI() {
        DesktopAssistantMusicUiHelper.updateMusicUI(this, this.prefs, this.isMusicEnabled, this.isPlaying, this.containerDisc, this.cardAlbumArt, new b1(this, 11), new b1(this, 12));
        updateClockWeatherHeaderVisibility();
        updateFloatingMusicAppIcon();
        updateSpectrumAppearance(isCurrentNightMode());
        updateSpectrumPlaybackState();
        updateMusicSeekUiState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateNetworkStatus() {
        /*
            r4 = this;
            android.widget.ImageView r0 = r4.imgWifiIcon
            if (r0 != 0) goto L11
            android.widget.ImageView r0 = r4.imgSignalIcon
            if (r0 != 0) goto L11
            android.widget.ImageView r0 = r4.topImgWifi
            if (r0 != 0) goto L11
            android.widget.ImageView r0 = r4.topImgSignal
            if (r0 != 0) goto L11
            return
        L11:
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r4.getSystemService(r0)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            if (r0 != 0) goto L1c
            return
        L1c:
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L2c
            boolean r3 = r0.isConnectedOrConnecting()
            if (r3 == 0) goto L2c
            r3 = r1
            goto L2d
        L2c:
            r3 = r2
        L2d:
            if (r3 == 0) goto L3e
            if (r0 == 0) goto L3e
            int r0 = r0.getType()
            if (r0 != r1) goto L39
            r0 = r2
            goto L40
        L39:
            if (r0 != 0) goto L3e
            r0 = r1
            r1 = r2
            goto L40
        L3e:
            r0 = r2
            r1 = r0
        L40:
            r3 = 8
            if (r1 == 0) goto L61
            android.widget.ImageView r0 = r4.imgWifiIcon
            if (r0 == 0) goto L4b
            r0.setVisibility(r2)
        L4b:
            android.widget.ImageView r0 = r4.topImgWifi
            if (r0 == 0) goto L52
            r0.setVisibility(r2)
        L52:
            android.widget.ImageView r0 = r4.imgSignalIcon
            if (r0 == 0) goto L59
            r0.setVisibility(r3)
        L59:
            android.widget.ImageView r0 = r4.topImgSignal
            if (r0 == 0) goto L9c
            r0.setVisibility(r3)
            goto L9c
        L61:
            if (r0 == 0) goto L80
            android.widget.ImageView r0 = r4.imgSignalIcon
            if (r0 == 0) goto L6a
            r0.setVisibility(r2)
        L6a:
            android.widget.ImageView r0 = r4.topImgSignal
            if (r0 == 0) goto L71
            r0.setVisibility(r2)
        L71:
            android.widget.ImageView r0 = r4.imgWifiIcon
            if (r0 == 0) goto L78
            r0.setVisibility(r3)
        L78:
            android.widget.ImageView r0 = r4.topImgWifi
            if (r0 == 0) goto L9c
            r0.setVisibility(r3)
            goto L9c
        L80:
            android.widget.ImageView r0 = r4.imgWifiIcon
            if (r0 == 0) goto L87
            r0.setVisibility(r3)
        L87:
            android.widget.ImageView r0 = r4.topImgWifi
            if (r0 == 0) goto L8e
            r0.setVisibility(r3)
        L8e:
            android.widget.ImageView r0 = r4.imgSignalIcon
            if (r0 == 0) goto L95
            r0.setVisibility(r3)
        L95:
            android.widget.ImageView r0 = r4.topImgSignal
            if (r0 == 0) goto L9c
            r0.setVisibility(r3)
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantActivity.updateNetworkStatus():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNightMode(boolean z6) {
        if (this.viewWeatherAmbient == null) {
            this.viewWeatherAmbient = (WeatherAmbientView) findViewById(g4.p.view_weather_ambient);
        }
        WeatherAmbientView weatherAmbientView = this.viewWeatherAmbient;
        if (weatherAmbientView != null) {
            weatherAmbientView.setNightMode(z6);
        }
        int i7 = (int) (((this.prefs != null ? r0.getInt("desktop_night_mode_alpha", 70) : 70) * 255) / 100.0f);
        int argb = z6 ? Color.argb(i7, 0, 0, 0) : 0;
        RealtimeBlurView realtimeBlurView = this.dockContainer;
        if (realtimeBlurView != null) {
            realtimeBlurView.setOverlayColor(argb);
        }
        applyTopInfoBarStyle();
        SharedPreferences sharedPreferences = this.prefs;
        int argb2 = z6 ? Color.argb(i7, 0, 0, 0) : sharedPreferences != null && sharedPreferences.getBoolean("desktop_transparent_bg", false) ? 0 : 872415231;
        RealtimeBlurView realtimeBlurView2 = this.leftContainer;
        if (realtimeBlurView2 != null) {
            realtimeBlurView2.setOverlayColor(argb2);
        }
        RealtimeBlurView realtimeBlurView3 = this.rightContainer;
        if (realtimeBlurView3 != null) {
            realtimeBlurView3.setOverlayColor(argb2);
        }
        RealtimeBlurView realtimeBlurView4 = this.centerContainer;
        if (realtimeBlurView4 != null) {
            realtimeBlurView4.setOverlayColor(argb2);
        }
        SmartCornerView[] smartCornerViewArr = this.mCornerViews;
        if (smartCornerViewArr != null) {
            for (SmartCornerView smartCornerView : smartCornerViewArr) {
                if (smartCornerView != null) {
                    smartCornerView.setOverlayColor(argb);
                }
            }
        }
        DockAppAdapter dockAppAdapter = this.dockAdapter;
        if (dockAppAdapter != null) {
            dockAppAdapter.setNightMode(z6);
        }
        DesktopAppAdapter desktopAppAdapter = this.appListAdapter;
        if (desktopAppAdapter != null) {
            desktopAppAdapter.setNightMode(z6);
        }
        updateLyricsColor();
        TextView textView = this.textSongTitle;
        if (textView != null && this.textArtist != null) {
            int i8 = z6 ? -1711276033 : -1;
            int i9 = z6 ? 1728053247 : -1275068417;
            textView.setTextColor(i8);
            this.textArtist.setTextColor(i9);
        }
        TextView textView2 = this.textCurrentDuration;
        if (textView2 != null) {
            textView2.setTextColor(z6 ? -1711276033 : -1);
        }
        TextView textView3 = this.textTotalDuration;
        if (textView3 != null) {
            textView3.setTextColor(z6 ? -1711276033 : -1);
        }
        TextClock textClock = this.textClock;
        if (textClock != null) {
            textClock.setTextColor(z6 ? -1711276033 : -1);
        }
        if (this.layoutStatusIcons != null) {
            int i10 = z6 ? -1711276033 : -1;
            for (int i11 = 0; i11 < this.layoutStatusIcons.getChildCount(); i11++) {
                View childAt = this.layoutStatusIcons.getChildAt(i11);
                if (childAt instanceof ImageView) {
                    ((ImageView) childAt).setColorFilter(i10);
                }
            }
        }
        ImageView imageView = this.btnSwitchMode;
        if (imageView != null) {
            imageView.setImageTintList(ColorStateList.valueOf(z6 ? -1711276033 : -1));
        }
        ImageButton imageButton = this.btnPrev;
        if (imageButton != null && this.btnPlayPause != null && this.btnNext != null) {
            int i12 = z6 ? -1711276033 : -1;
            imageButton.setImageTintList(ColorStateList.valueOf(i12));
            this.btnPlayPause.setImageTintList(ColorStateList.valueOf(i12));
            this.btnNext.setImageTintList(ColorStateList.valueOf(i12));
        }
        ImageView imageView2 = this.imgLoopMode;
        if (imageView2 != null) {
            imageView2.setImageTintList(ColorStateList.valueOf(z6 ? -1711276033 : -1));
        }
        ImageView imageView3 = this.imgFavHeart;
        if (imageView3 != null && !this.mIsFavorite) {
            imageView3.setImageTintList(ColorStateList.valueOf(z6 ? -1711276033 : -1));
        }
        updateBackground();
        updateSpectrumAppearance(z6);
    }

    private void updateNoCardsVisibility() {
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
        if (desktopAssistantCarControlHelper != null) {
            desktopAssistantCarControlHelper.updateNoCardsVisibility();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSeatComfort() {
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
        if (desktopAssistantCarControlHelper != null) {
            desktopAssistantCarControlHelper.setState(this.isCarControlEnabled, this.isVisualCarModelEnabled, this.isSeatComfortEnabled);
            this.carControlHelper.updateSeatComfort();
        }
    }

    private void updateSingleLineLyricsUi() {
        LyricsAdapter lyricsAdapter;
        if (this.textCurrentLyric == null) {
            return;
        }
        LyricsAdapter lyricsAdapter2 = this.lyricsAdapter;
        boolean z6 = lyricsAdapter2 != null && lyricsAdapter2.getItemCount() > 0;
        String str = this.mCurrentLyricLine;
        if (str == null) {
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        if (z6) {
            if (this.mPendingLyricsImmediateSync && (lyricsAdapter = this.lyricsAdapter) != null && lyricsAdapter.getCurrentHighlightIndex() == -1 && !TextUtils.isEmpty(str)) {
                this.mEmptyLyricHintPending = false;
                this.controlHandler.removeCallbacks(this.mShowEmptyLyricHintRunnable);
                this.textCurrentLyric.setText(str);
                this.textCurrentLyric.setVisibility(0);
                RecyclerView recyclerView = this.lyricsRecyclerView;
                if (recyclerView != null) {
                    recyclerView.setVisibility(8);
                    return;
                }
                return;
            }
            this.mEmptyLyricHintPending = false;
            this.controlHandler.removeCallbacks(this.mShowEmptyLyricHintRunnable);
            this.textCurrentLyric.setText(s5.h0.FRAGMENT_ENCODE_SET);
            this.textCurrentLyric.setVisibility(8);
            RecyclerView recyclerView2 = this.lyricsRecyclerView;
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(0);
                this.lyricsRecyclerView.post(new b1(this, 21));
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.mEmptyLyricHintPending = false;
            this.controlHandler.removeCallbacks(this.mShowEmptyLyricHintRunnable);
            this.textCurrentLyric.setText(str);
            this.textCurrentLyric.setVisibility(0);
            RecyclerView recyclerView3 = this.lyricsRecyclerView;
            if (recyclerView3 != null) {
                recyclerView3.setVisibility(8);
                return;
            }
            return;
        }
        RecyclerView recyclerView4 = this.lyricsRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.setVisibility(8);
        }
        if (TextUtils.equals(this.textCurrentLyric.getText(), "暂无歌词，请欣赏")) {
            this.textCurrentLyric.setVisibility(0);
        } else {
            if (this.mEmptyLyricHintPending) {
                return;
            }
            this.mEmptyLyricHintPending = true;
            this.textCurrentLyric.setText(s5.h0.FRAGMENT_ENCODE_SET);
            this.textCurrentLyric.setVisibility(8);
            this.controlHandler.postDelayed(this.mShowEmptyLyricHintRunnable, EMPTY_LYRIC_HINT_DELAY_MS);
        }
    }

    private void updateSpectrumAppearance(boolean z6) {
        SharedPreferences sharedPreferences;
        if (this.viewSpectrum == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        boolean z7 = this.isMusicEnabled && sharedPreferences.getBoolean("desktop_music_spectrum_enabled", true);
        this.viewSpectrum.setVisibility(z7 ? 0 : 4);
        if (z7) {
            if (this.prefs.getBoolean("desktop_music_spectrum_colorful_enabled", true)) {
                this.viewSpectrum.setUseGradient(true);
            } else {
                this.viewSpectrum.setColor(z6 ? 1308622847 : -1275068417);
            }
        }
    }

    private void updateSpectrumPlaybackState() {
        SharedPreferences sharedPreferences;
        if (this.viewSpectrum == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        if (!this.isMusicEnabled || !sharedPreferences.getBoolean("desktop_music_spectrum_enabled", true)) {
            stopDesktopSpectrum();
            return;
        }
        boolean shouldUseRealSpectrum = shouldUseRealSpectrum();
        this.viewSpectrum.setUseExternalLevels(shouldUseRealSpectrum);
        if (!this.isPlaying) {
            stopDesktopSpectrum();
        } else {
            if (shouldUseRealSpectrum && startDesktopRealtimeSpectrumIfPossible()) {
                return;
            }
            stopDesktopRealtimeSpectrumAnalyzer();
            this.viewSpectrum.setUseExternalLevels(false);
            this.viewSpectrum.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTopInfoBarVisibility() {
        if (this.topInfoBarContainer == null) {
            return;
        }
        this.prefs.getBoolean(PREF_KEY_CLEAN_MODE, false);
        if (this.mode != 0) {
            this.topInfoBarContainer.setVisibility(8);
            return;
        }
        boolean z6 = this.prefs.getBoolean("top_info_area_enabled", false);
        this.topInfoBarContainer.setVisibility(z6 ? 0 : 8);
        if (z6) {
            if (this.topTextDate != null) {
                this.topTextDate.setTextSize(2, this.prefs.getInt("car_control_text_size", 24));
            }
            applyTopInfoBarStyle();
            boolean z7 = this.prefs.getBoolean("desktop_hide_dock", false);
            View view = this.topInfoLeftContainer;
            if (view != null) {
                view.setVisibility(z7 ? 0 : 8);
            }
            updateAuxiliaryClockVisibility();
            updateNetworkStatus();
            DesktopAssistantTopInfoAdapter desktopAssistantTopInfoAdapter = this.topInfoAdapter;
            if (desktopAssistantTopInfoAdapter != null) {
                desktopAssistantTopInfoAdapter.refreshStyle();
                this.topInfoAdapter.updateData();
            }
        }
    }

    private void updateVideoWallpaperMask(int i7, boolean z6) {
        View view = this.videoWallpaperMask;
        if (view == null) {
            return;
        }
        if (!this.isVideoWallpaperActive) {
            view.setBackgroundColor(0);
            return;
        }
        int max = Math.max(0, Math.min(25, i7));
        if (max == 0) {
            this.videoWallpaperMask.setBackgroundColor(0);
        } else if (z6) {
            this.videoWallpaperMask.setBackgroundColor(Color.argb(Math.min(z1.c.REM_INT_LIT8, (max * 4) + 70), 0, 0, 0));
        } else {
            this.videoWallpaperMask.setBackgroundColor(Color.argb(Math.min(z1.c.OR_INT, (max * 4) + 8), 255, 255, 255));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViewWeight(View view, float f7) {
        if (view == null) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = f7;
        view.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVisualCarModelVisibility() {
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = this.carControlHelper;
        if (desktopAssistantCarControlHelper != null) {
            desktopAssistantCarControlHelper.setState(this.isCarControlEnabled, this.isVisualCarModelEnabled, this.isSeatComfortEnabled);
            this.carControlHelper.updateVisualCarModelVisibility();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWeatherLunarText() {
        if (this.textWeatherLunar == null) {
            return;
        }
        String lunarText = LunarCalendarUtils.getLunarText();
        this.textWeatherLunar.setText(lunarText);
        this.textWeatherLunar.setVisibility(lunarText.isEmpty() ? 8 : 0);
    }

    public void cycleAmbientFestival() {
        if (this.prefs == null) {
            this.prefs = SharedPreferencesUtils.getAppPrefs(this);
        }
        int i7 = WeatherAmbientView.WEATHER_NONE;
        int i8 = (this.prefs.getInt("desktop_ambient_festival", 0) + 1) % 21;
        a.a.l(this.prefs, "desktop_ambient_festival", i8);
        if (this.viewWeatherAmbient == null) {
            this.viewWeatherAmbient = (WeatherAmbientView) findViewById(g4.p.view_weather_ambient);
        }
        WeatherAmbientView weatherAmbientView = this.viewWeatherAmbient;
        if (weatherAmbientView != null) {
            weatherAmbientView.setFestivalOption(i8);
        }
        ToastHelper.show(this, "节日氛围：".concat(WeatherAmbientView.g(i8)));
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        GestureDetector gestureDetector;
        if (motionEvent.getPointerCount() > 1) {
            this.isGestureHandling = false;
        } else if (motionEvent.getAction() == 0) {
            this.isGestureHandling = true;
            RealtimeBlurView realtimeBlurView = this.topInfoBarContainer;
            if (realtimeBlurView != null && realtimeBlurView.getVisibility() == 0) {
                int[] iArr = new int[2];
                this.topInfoBarContainer.getLocationOnScreen(iArr);
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                if (rawX >= iArr[0] && rawX <= this.topInfoBarContainer.getWidth() + r5) {
                    if (rawY >= iArr[1] && rawY <= this.topInfoBarContainer.getHeight() + r0) {
                        this.isGestureHandling = false;
                    }
                }
            }
        }
        if (this.isGestureHandling && (gestureDetector = this.gestureDetector) != null) {
            gestureDetector.onTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onAlbumArtUpdateEvent(h4.a aVar) {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null || aVar == null) {
            return;
        }
        int i7 = 0;
        int i8 = sharedPreferences.getInt("desktop_music_source", 0);
        int i9 = aVar.f6725c;
        if (i9 != i8) {
            if (i9 != 1 && i9 != 4) {
                return;
            }
            if (i8 != 1 && i8 != 4) {
                return;
            }
        }
        applyCoverGradientFromEvent(aVar);
        if (this.isMusicEnabled && this.prefs.getBoolean("desktop_music_show_album_art", false)) {
            Bitmap bitmap = aVar.f6724b;
            if (bitmap != null) {
                saveMusicCover(bitmap);
                DesktopAssistantMusicUiHelper.applyCoverBitmap(bitmap, this.imgAlbumArtFull);
            } else {
                String str = aVar.f6723a;
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                DesktopAssistantMusicUiHelper.downloadCoverAsync(str, new z(this, i7));
            }
        }
    }

    @Override // androidx.activity.o, android.app.Activity
    @SuppressLint({"MissingSuperCall"})
    public void onBackPressed() {
        LyricsFxView lyricsFxView = this.lyricsFxView;
        if (lyricsFxView == null || !lyricsFxView.c0()) {
            moveTaskToBack(true);
        } else {
            this.lyricsFxView.b0();
            restoreDesktopAfterLyricsFx();
        }
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, androidx.activity.o, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateNightMode(isCurrentNightMode());
        LyricsFxView lyricsFxView = this.lyricsFxView;
        if (lyricsFxView != null && lyricsFxView.c0()) {
            this.lyricsFxView.setNightMode(isCurrentNightMode());
        }
        updateBackground();
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i7 = 0;
        if (!SharedPreferencesUtils.getAppPrefs(this).getBoolean("desktop_assistant_enabled", false)) {
            finish();
            return;
        }
        hideSystemUI();
        setContentView(g4.q.activity_desktop_assistant);
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(this);
        this.prefs = appPrefs;
        appPrefs.registerOnSharedPreferenceChangeListener(this.prefListener);
        this.mWindowManager = (WindowManager) getSystemService("window");
        this.mode = this.prefs.getInt("desktop_mode_selection", 0);
        this.isCleanMode = this.prefs.getBoolean(PREF_KEY_CLEAN_MODE, false);
        boolean z6 = this.prefs.getBoolean(PREF_KEY_SWAP_POS, false);
        this.isSwapped = z6;
        this.viewPositionMode = this.prefs.getInt(PREF_KEY_VIEW_MODE, z6 ? 1 : 0);
        loadLayoutState();
        this.gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (DesktopAssistantActivity.this.mode != 1) {
                    return false;
                }
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                if (DesktopAssistantActivity.this.dockContainer != null && DesktopAssistantActivity.this.dockContainer.getVisibility() == 0) {
                    DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
                    if (desktopAssistantActivity.isViewUnder(desktopAssistantActivity.dockContainer, rawX, rawY)) {
                        return false;
                    }
                }
                if (!DesktopAssistantActivity.this.isCleanMode && DesktopAssistantActivity.this.appListRecyclerView != null && DesktopAssistantActivity.this.appListRecyclerView.getVisibility() == 0) {
                    for (int i8 = 0; i8 < DesktopAssistantActivity.this.appListRecyclerView.getChildCount(); i8++) {
                        View childAt = DesktopAssistantActivity.this.appListRecyclerView.getChildAt(i8);
                        View findViewById = childAt.findViewById(g4.p.app_icon);
                        View findViewById2 = childAt.findViewById(g4.p.app_name);
                        if (DesktopAssistantActivity.this.isViewUnder(findViewById, rawX, rawY) || DesktopAssistantActivity.this.isViewUnder(findViewById2, rawX, rawY)) {
                            return false;
                        }
                    }
                }
                DesktopAssistantActivity.this.toggleCleanMode();
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                DesktopAssistantActivity.this.isLongPress = false;
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f7, float f8) {
                if (motionEvent != null && motionEvent2 != null && motionEvent.getPointerCount() == 1 && motionEvent2.getPointerCount() == 1 && !DesktopAssistantActivity.this.isLongPress && !DesktopAssistantActivity.this.isMusicAdjusting) {
                    float x6 = motionEvent2.getX() - motionEvent.getX();
                    float abs = Math.abs(x6);
                    if (abs > Math.abs(motionEvent2.getY() - motionEvent.getY()) && abs > 100.0f && Math.abs(f7) > 100.0f) {
                        if (x6 > CropImageView.DEFAULT_ASPECT_RATIO) {
                            if (DesktopAssistantActivity.this.mode == 1) {
                                DesktopAssistantActivity.this.mode = 0;
                                DesktopAssistantActivity.this.setupMode();
                                return true;
                            }
                        } else if (DesktopAssistantActivity.this.mode == 0) {
                            DesktopAssistantActivity.this.mode = 1;
                            DesktopAssistantActivity.this.setupMode();
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                DesktopAssistantActivity.this.isLongPress = true;
            }
        });
        this.isMusicEnabled = this.prefs.getBoolean("desktop_music_enabled", false);
        int i8 = 3;
        this.lyricsLines = Math.max(3, Math.min(8, this.prefs.getInt("desktop_lyrics_lines", 5)));
        this.lyricsFontSize = this.prefs.getInt("desktop_lyrics_font_size", 25);
        this.isCarControlEnabled = this.prefs.getBoolean("car_control_card_enabled", false);
        this.isVisualCarModelEnabled = this.prefs.getBoolean("car_control_visual_model_enabled", false);
        this.isSeatComfortEnabled = this.prefs.getBoolean("seat_comfort_panel_enabled", false);
        DesktopAssistantCarControlHelper desktopAssistantCarControlHelper = new DesktopAssistantCarControlHelper(this, this.prefs, new b1(this, 16));
        this.carControlHelper = desktopAssistantCarControlHelper;
        desktopAssistantCarControlHelper.setState(this.isCarControlEnabled, this.isVisualCarModelEnabled, this.isSeatComfortEnabled);
        initViews();
        loadWallpaper();
        this.appPreloadInProgress = true;
        setupMode();
        setupMusicPluginInteraction();
        setupContent();
        setupDockLogic();
        NaviApplication.getInstance().setGlobalThemeChangeListener(new z(this, 1));
        updateNightMode(isCurrentNightMode());
        loadAppList(true, 0L, new b1(this, 17));
        i6.e.b().j(this);
        if (this.isMusicEnabled) {
            initMusicService();
            updateMusicUI();
        }
        registerNetworkReceiver();
        registerPackageReceiver();
        registerTimeReceiver();
        registerViewSwitchReceiver();
        AccessibilityGuard.start(this);
        registerOilRecordReceiver();
        ConfigSyncManager configSyncManager = ConfigSyncManager.getInstance(this);
        this.configSyncManager = configSyncManager;
        configSyncManager.register();
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DesktopAssistantActivity desktopAssistantActivity = DesktopAssistantActivity.this;
                desktopAssistantActivity.isCarControlEnabled = desktopAssistantActivity.prefs.getBoolean("car_control_card_enabled", false);
                DesktopAssistantActivity desktopAssistantActivity2 = DesktopAssistantActivity.this;
                desktopAssistantActivity2.isVisualCarModelEnabled = desktopAssistantActivity2.prefs.getBoolean("car_control_visual_model_enabled", false);
                DesktopAssistantActivity desktopAssistantActivity3 = DesktopAssistantActivity.this;
                desktopAssistantActivity3.isSeatComfortEnabled = desktopAssistantActivity3.prefs.getBoolean("seat_comfort_panel_enabled", false);
                if (DesktopAssistantActivity.this.carControlHelper != null) {
                    DesktopAssistantActivity.this.carControlHelper.setState(DesktopAssistantActivity.this.isCarControlEnabled, DesktopAssistantActivity.this.isVisualCarModelEnabled, DesktopAssistantActivity.this.isSeatComfortEnabled);
                }
                DesktopAssistantActivity.this.updateCarControlCards();
                DesktopAssistantActivity.this.updateVisualCarModelVisibility();
                DesktopAssistantActivity.this.updateSeatComfort();
                DesktopAssistantActivity.this.setupContent();
                if (DesktopAssistantActivity.this.topInfoAdapter != null) {
                    DesktopAssistantActivity.this.topInfoAdapter.refreshStyle();
                    DesktopAssistantActivity.this.topInfoAdapter.updateData();
                }
                DesktopAssistantActivity.this.updateTopInfoBarVisibility();
            }
        };
        this.carControlReceiver = broadcastReceiver;
        registerReceiver(broadcastReceiver, new IntentFilter("com.navi.tool.CAR_CONTROL_CARD_UPDATED"));
        o4.c c7 = o4.c.c();
        z zVar = new z(this, i8);
        a.a.q(zVar, 14, c7.B, this);
        a.a.q(zVar, 15, c7.f7956x, this);
        a.a.q(zVar, 16, c7.A, this);
        a.a.q(zVar, 17, c7.f7938f, this);
        a.a.q(zVar, 18, c7.f7940h, this);
        a.a.q(zVar, 19, c7.f7941i, this);
        a.a.q(zVar, 0, c7.f7942j, this);
        a.a.q(zVar, 1, c7.f7943k, this);
        a.a.q(zVar, 2, c7.f7944l, this);
        a.a.q(zVar, 3, c7.f7949q, this);
        a.a.q(zVar, 4, c7.f7950r, this);
        a.a.q(zVar, 5, c7.f7957y, this);
        a.a.q(zVar, 6, c7.f7939g, this);
        a.a.q(zVar, 7, c7.f7958z, this);
        a.a.q(zVar, 8, c7.C, this);
        a.a.q(zVar, 9, c7.F, this);
        c7.G.e(this, new z(this, 2));
        a.a.q(zVar, 10, c7.D, this);
        a.a.q(zVar, 11, c7.f7945m, this);
        a.a.q(zVar, 12, c7.f7946n, this);
        a.a.q(zVar, 13, c7.f7947o, this);
        syncGnssDataSource();
        updateCarControlCards();
        updateSeatComfort();
        updateVisualCarModelVisibility();
        boolean z7 = this.prefs.getBoolean("car_link_service_enabled", false);
        boolean z8 = this.prefs.getBoolean("lion_sync_service_enabled", false);
        if (z7) {
            startForegroundService(new Intent(this, (Class<?>) CarInfoService.class));
        } else if (z8) {
            startForegroundService(new Intent(this, (Class<?>) LionCarInfoService.class));
            new Handler(Looper.getMainLooper()).postDelayed(new m0(i7), 800L);
        }
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public void onDestroy() {
        this.appListExecutor.shutdownNow();
        this.appPreloadInProgress = false;
        androidx.appcompat.app.o oVar = this.appSelectionLoadingDialog;
        if (oVar != null) {
            try {
                oVar.dismiss();
            } catch (Exception unused) {
            }
            this.appSelectionLoadingDialog = null;
        }
        this.appSelectionLoading = false;
        stopDesktopSpectrum();
        RealtimeBlurView.setGlobalPaused(false);
        if (this.mapClosedByLyricsFx) {
            this.mapClosedByLyricsFx = false;
            String activeMapPackage = getActiveMapPackage();
            if (activeMapPackage != null && !activeMapPackage.isEmpty()) {
                sendMapBroadcast(activeMapPackage, true);
            }
        }
        super.onDestroy();
        this.mEmptyLyricHintPending = false;
        this.controlHandler.removeCallbacks(this.mShowEmptyLyricHintRunnable);
        ConfigSyncManager configSyncManager = this.configSyncManager;
        if (configSyncManager != null) {
            configSyncManager.unregister();
        }
        BroadcastReceiver broadcastReceiver = this.topInfoReceiver;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        GnssInfoProvider.getInstance().stop(getApplicationContext());
        NaviApplication.getInstance().setGlobalThemeChangeListener(null);
        BroadcastReceiver broadcastReceiver2 = this.packageReceiver;
        if (broadcastReceiver2 != null) {
            unregisterReceiver(broadcastReceiver2);
        }
        hideMapFloat();
        i6.e.b().l(this);
        stopCarousel();
        cancelVideoWatchdog();
        this.videoWallpaperHandler.removeCallbacksAndMessages(null);
        releaseVideoWallpaperPlayer();
        Surface surface = this.videoWallpaperSurface;
        if (surface != null) {
            surface.release();
            this.videoWallpaperSurface = null;
        }
        removeCornerMasks();
        BroadcastReceiver broadcastReceiver3 = this.dockReceiver;
        if (broadcastReceiver3 != null) {
            unregisterReceiver(broadcastReceiver3);
        }
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences != null) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(this.prefListener);
        }
        if (this.isMusicEnabled) {
            stopAllMusicServices();
        }
        BroadcastReceiver broadcastReceiver4 = this.wallpaperReceiver;
        if (broadcastReceiver4 != null) {
            unregisterReceiver(broadcastReceiver4);
        }
        BroadcastReceiver broadcastReceiver5 = this.musicUiReceiver;
        if (broadcastReceiver5 != null) {
            unregisterReceiver(broadcastReceiver5);
        }
        BroadcastReceiver broadcastReceiver6 = this.mapUiReceiver;
        if (broadcastReceiver6 != null) {
            unregisterReceiver(broadcastReceiver6);
        }
        BroadcastReceiver broadcastReceiver7 = this.networkReceiver;
        if (broadcastReceiver7 != null) {
            unregisterReceiver(broadcastReceiver7);
        }
        BroadcastReceiver broadcastReceiver8 = this.timeReceiver;
        if (broadcastReceiver8 != null) {
            unregisterReceiver(broadcastReceiver8);
        }
        BroadcastReceiver broadcastReceiver9 = this.oilRecordReceiver;
        if (broadcastReceiver9 != null) {
            unregisterReceiver(broadcastReceiver9);
        }
        BroadcastReceiver broadcastReceiver10 = this.viewSwitchReceiver;
        if (broadcastReceiver10 != null) {
            unregisterReceiver(broadcastReceiver10);
        }
        BroadcastReceiver broadcastReceiver11 = this.roundCornerReceiver;
        if (broadcastReceiver11 != null) {
            unregisterReceiver(broadcastReceiver11);
        }
        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = this.clockWeatherController;
        if (desktopAssistantClockWeatherController != null) {
            desktopAssistantClockWeatherController.onDestroy();
        }
        BroadcastReceiver broadcastReceiver12 = this.carControlReceiver;
        if (broadcastReceiver12 != null) {
            unregisterReceiver(broadcastReceiver12);
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLyricUpdateEvent(h4.k kVar) {
        if (this.isMusicEnabled && kVar.f6754b == this.prefs.getInt("desktop_music_source", 0)) {
            String str = kVar.f6753a;
            if (str == null) {
                str = s5.h0.FRAGMENT_ENCODE_SET;
            }
            this.mCurrentLyricLine = str;
            LyricsFxView lyricsFxView = this.lyricsFxView;
            if (lyricsFxView != null && lyricsFxView.c0()) {
                this.lyricsFxView.h0(this.mCurrentLyricLine);
            }
            updateSingleLineLyricsUi();
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onLyricsDataEvent(h4.l lVar) {
        if (this.isMusicEnabled && lVar.f6756b == this.prefs.getInt("desktop_music_source", 0)) {
            TreeMap treeMap = lVar.f6755a;
            if (treeMap == null || treeMap.isEmpty()) {
                this.mLastKnownCurrentPosition = 0L;
            }
            TreeMap treeMap2 = lVar.f6755a;
            if (treeMap2 != null) {
                treeMap2.size();
            }
            LyricsFxView lyricsFxView = this.lyricsFxView;
            if (lyricsFxView != null) {
                lyricsFxView.c0();
            }
            synchronized (this.mCurrentLyrics) {
                try {
                    this.mCurrentLyrics.clear();
                    TreeMap treeMap3 = lVar.f6755a;
                    if (treeMap3 != null) {
                        this.mCurrentLyrics.putAll(treeMap3);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            LyricsFxView lyricsFxView2 = this.lyricsFxView;
            if (lyricsFxView2 != null && lyricsFxView2.c0()) {
                this.lyricsFxView.i0(lVar.f6755a);
            }
            LyricsAdapter lyricsAdapter = this.lyricsAdapter;
            if (lyricsAdapter == null) {
                return;
            }
            lyricsAdapter.setLyrics(lVar.f6755a);
            RecyclerView recyclerView = this.lyricsRecyclerView;
            if (recyclerView != null) {
                recyclerView.j0();
                boolean shouldKeepKugouResumeDirectSync = shouldKeepKugouResumeDirectSync(lVar.f6756b);
                if (this.mPendingLyricsImmediateSync || shouldKeepKugouResumeDirectSync) {
                    this.mPreferDirectLyricsLayoutScroll = true;
                    DesktopAssistantMusicUiHelper.updateLyricsProgress(this.lyricsAdapter, this.lyricsRecyclerView, this.smoothScroller, Math.max(0L, this.mLastKnownCurrentPosition), true);
                    if (this.lyricsAdapter.getCurrentHighlightIndex() != -1) {
                        this.mPendingLyricsImmediateSync = false;
                    }
                } else if (this.lyricsRecyclerView.getLayoutManager() != null) {
                    long j7 = this.mLastKnownCurrentPosition;
                    if (j7 > 0) {
                        this.mPreferDirectLyricsLayoutScroll = true;
                        DesktopAssistantMusicUiHelper.updateLyricsProgress(this.lyricsAdapter, this.lyricsRecyclerView, this.smoothScroller, j7, true);
                    } else {
                        this.lyricsRecyclerView.getLayoutManager().s0(0);
                    }
                }
            }
            updateSingleLineLyricsUi();
            TreeMap treeMap4 = lVar.f6755a;
            if (treeMap4 != null && !treeMap4.isEmpty()) {
                saveLyrics(lVar.f6755a);
            }
            this.lyricsRecyclerView.post(new b1(this, 26));
        }
    }

    @i6.k(threadMode = ThreadMode.MAIN)
    public void onMusicStatusEvent(h4.m mVar) {
        int i7;
        long j7;
        LyricsAdapter lyricsAdapter;
        m4.a aVar;
        if (this.isMusicEnabled) {
            int i8 = this.prefs.getInt("desktop_music_source", 0);
            int i9 = mVar.f6768l;
            if (i9 != i8) {
                if (i9 != 1 && i9 != 4) {
                    return;
                }
                if (i8 != 1 && i8 != 4) {
                    return;
                }
            }
            boolean z6 = this.isPlaying;
            String str = mVar.f6758b;
            String str2 = mVar.f6759c;
            TextView textView = this.textSongTitle;
            if (textView != null && !TextUtils.equals(textView.getText(), str)) {
                this.textSongTitle.setText(str);
            }
            TextView textView2 = this.textArtist;
            if (textView2 != null && !TextUtils.equals(textView2.getText(), str2)) {
                this.textArtist.setText(str2);
            }
            this.mLastKnownArtist = str2;
            int i10 = this.prefs.getInt("desktop_music_source", 0);
            if (i10 == 0 || i10 == 5) {
                Boolean bool = this.mPendingFavToggle;
                if (bool == null) {
                    this.mIsFavorite = mVar.f6766j;
                } else if (mVar.f6766j == bool.booleanValue()) {
                    this.mIsFavorite = mVar.f6766j;
                    this.mPendingFavToggle = null;
                }
            }
            updateFavHeartState();
            if ((i10 == 0 || i10 == 5 || i10 == 1 || i10 == 4) && (i7 = mVar.f6767k) >= 0) {
                this.mPlayMode = i7;
                if (this.mLoopCycleIndex < 0) {
                    this.mLoopCycleIndex = i7;
                }
            }
            updateLoopModeState();
            boolean z7 = !TextUtils.equals(str, this.mLastKnownTitle);
            if (z7) {
                this.mPendingFavToggle = null;
            }
            long j8 = this.mLastKnownCurrentPosition;
            long max = Math.max(0L, mVar.f6760d);
            if (str != null && !str.isEmpty()) {
                saveMusicMeta(str, str2);
            }
            this.isPlaying = mVar.f6757a;
            if (shouldUseRealSpectrum() && (aVar = this.desktopSpectrumAnalyzer) != null && (z7 || (!z6 && this.isPlaying))) {
                synchronized (aVar.f7552b) {
                    aVar.b();
                }
            }
            this.mLastKnownCurrentPosition = max;
            LyricsFxView lyricsFxView = this.lyricsFxView;
            if (lyricsFxView == null || !lyricsFxView.c0()) {
                j7 = j8;
            } else {
                LyricsFxView lyricsFxView2 = this.lyricsFxView;
                boolean z8 = this.isPlaying;
                lyricsFxView2.getClass();
                j7 = j8;
                lyricsFxView2.f5931c0 = Math.max(0L, max);
                lyricsFxView2.f5933d0 = SystemClock.uptimeMillis();
                lyricsFxView2.f5935e0 = z8;
            }
            long j9 = mVar.f6761e;
            if (j9 > 0) {
                this.mLastKnownTotalDuration = j9;
            }
            DesktopAssistantMusicUiHelper.CoverRequest buildCoverRequest = DesktopAssistantMusicUiHelper.buildCoverRequest(mVar, this.prefs, this.mLastKnownTitle, this.mLastLoadingTitle);
            if (buildCoverRequest != null) {
                String str3 = buildCoverRequest.title;
                this.mLastKnownTitle = str3;
                this.mLastLoadingTitle = str3;
                DesktopAssistantMusicUiHelper.executeCoverRequest(buildCoverRequest, new DesktopAssistantMusicUiHelper.CoverRequestCallback() { // from class: com.navi.tool.ui.extra.DesktopAssistantActivity.13
                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void applyCoverBitmap(Bitmap bitmap, boolean z9) {
                        DesktopAssistantActivity.this.updateCoverGradientFromBitmap(bitmap);
                        DesktopAssistantMusicUiHelper.applyCoverBitmap(bitmap, DesktopAssistantActivity.this.imgAlbumArtFull);
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void applyDefaultCover() {
                        DesktopAssistantMusicUiHelper.applyDefaultCover(DesktopAssistantActivity.this.imgAlbumArtFull);
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public boolean isCurrentTitle(String str4) {
                        return str4.equals(DesktopAssistantActivity.this.mLastKnownTitle);
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void logDebug(String str4) {
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void logError(String str4) {
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void runOnUiThread(Runnable runnable) {
                        DesktopAssistantActivity.this.runOnUiThread(runnable);
                    }

                    @Override // com.navi.tool.ui.extra.DesktopAssistantMusicUiHelper.CoverRequestCallback
                    public void saveMusicCover(Bitmap bitmap) {
                        DesktopAssistantActivity.this.saveMusicCover(bitmap);
                    }
                });
            }
            DesktopAssistantMusicUiHelper.updatePlaybackVisualState(this.prefs, this.isPlaying, this.btnPlayPause, this.viewSpectrum, new b1(this, 19), new b1(this, 20));
            updateSpectrumAppearance(isCurrentNightMode());
            updateSpectrumPlaybackState();
            if (!this.mIsDraggingMusicProgress) {
                DesktopAssistantMusicUiHelper.updateLyricsProgress(this.lyricsAdapter, this.lyricsRecyclerView, this.smoothScroller, max, this.mPendingLyricsImmediateSync || z7 || j7 <= 0 || Math.abs(max - j7) >= 5000);
                if (this.mPendingLyricsImmediateSync && (lyricsAdapter = this.lyricsAdapter) != null && lyricsAdapter.getCurrentHighlightIndex() != -1) {
                    this.mPendingLyricsImmediateSync = false;
                }
                this.mLastKnownTotalDuration = DesktopAssistantMusicUiHelper.updateProgressBarAndDuration(this.viewProgress, this.textCurrentDuration, this.textTotalDuration, mVar.f6761e, mVar.f6760d, str, this.mLastKnownTitle, this.mLastKnownTotalDuration);
            }
            updateSingleLineLyricsUi();
            updateMusicSeekUiState();
        }
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onPause() {
        super.onPause();
        stopDesktopSpectrum();
        syncMusicIslandVisibility(false);
        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = this.clockWeatherController;
        if (desktopAssistantClockWeatherController != null) {
            desktopAssistantClockWeatherController.onPause();
        }
        WeatherAmbientView weatherAmbientView = this.viewWeatherAmbient;
        if (weatherAmbientView != null) {
            weatherAmbientView.f6065i0 = false;
            Choreographer.getInstance().removeFrameCallback(weatherAmbientView.f6075n0);
        }
        if (this.videoWallpaperPlayer != null) {
            cancelVideoWatchdog();
            try {
                this.videoWallpaperPlayer.pause();
            } catch (Exception unused) {
            }
        }
        hideMapFloat();
        hideHandles();
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, android.app.Activity
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = this.clockWeatherController;
        if (desktopAssistantClockWeatherController == null || !desktopAssistantClockWeatherController.onRequestPermissionsResult(i7, iArr)) {
            super.onRequestPermissionsResult(i7, strArr, iArr);
        }
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onResume() {
        SharedPreferences sharedPreferences;
        super.onResume();
        try {
            if (SharedPreferencesUtils.getAppPrefs(this).getBoolean("gesture_actions_enabled", false) && !GestureActionService.isServiceRunning() && Settings.canDrawOverlays(this) && GestureAccessibilityService.isEnabled(this)) {
                GestureActionService.start(this);
            }
        } catch (Throwable unused) {
        }
        loadDockApps();
        syncMusicIslandVisibility(true);
        PhoneNotificationRelayService.syncService(this);
        hideSystemUI();
        updateWeatherLunarText();
        if (this.isVideoWallpaperActive && this.videoWallpaperPlayer != null && (sharedPreferences = this.prefs) != null && !sharedPreferences.getBoolean("desktop_cover_gradient_enabled", false)) {
            try {
                this.videoWallpaperPlayer.start();
                scheduleVideoWatchdogForPlayer(this.videoWallpaperPlayer);
            } catch (Exception unused2) {
            }
        } else if (this.mode == 1 && !this.hasResolvedWallpaperOnce && this.wallpaperLoadRequestId == 0) {
            loadWallpaper();
        }
        SharedPreferences sharedPreferences2 = this.prefs;
        if (sharedPreferences2 != null) {
            int i7 = sharedPreferences2.getInt("desktop_mode_selection", 0);
            boolean z6 = this.prefs.getBoolean(PREF_KEY_CLEAN_MODE, false);
            if (this.mode != i7 || this.isCleanMode != z6) {
                this.mode = i7;
                this.isCleanMode = z6;
                setupMode();
            }
        }
        updateSpectrumPlaybackState();
        applyCoverGradientMode();
        if (this.mode == 1 && !this.isCleanMode) {
            if (this.appListAdapter != null && this.cachedAppList != null) {
                refreshAppListSilently();
            } else if (this.cachedAppList != null) {
                updateAppListUI(this.cachedAppList);
            } else if (!this.appPreloadInProgress) {
                loadInstalledApps();
            }
        }
        FrameLayout frameLayout = this.mapContainer;
        if (frameLayout != null) {
            frameLayout.post(new b1(this, 15));
        }
        if (this.isMusicEnabled) {
            this.mPendingLyricsImmediateSync = true;
            this.mPreferDirectLyricsLayoutScroll = true;
            int i8 = this.prefs.getInt("desktop_music_source", 0);
            if (i8 == 2) {
                this.mKugouResumeLyricsGuardUntilMs = SystemClock.elapsedRealtime() + KUGOU_RESUME_LYRICS_GUARD_MS;
            } else {
                this.mKugouResumeLyricsGuardUntilMs = 0L;
            }
            DesktopAssistantMusicUiHelper.refreshMusicStateBySource(this, i8);
        } else {
            this.mKugouResumeLyricsGuardUntilMs = 0L;
        }
        DesktopAssistantClockWeatherController desktopAssistantClockWeatherController = this.clockWeatherController;
        if (desktopAssistantClockWeatherController != null) {
            desktopAssistantClockWeatherController.onResume();
        }
        applyWeatherEffects();
        updateCarControlCards();
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public void onStop() {
        super.onStop();
        hideMapFloat();
        hideHandles();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        if (z6) {
            hideSystemUI();
            if (this.mode == 0) {
                lambda$setupMode$71();
            }
        }
    }
}
