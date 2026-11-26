package com.navi.tool.ui.extra;

import android.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.navi.tool.DesktopAssistantStubActivity;
import com.navi.tool.amapflying.util.FloatingWindowService;
import com.navi.tool.amapflying.util.kugou.KugouMusicManager;
import com.navi.tool.amapflying.util.kuwo.KuwoMusicManager;
import com.navi.tool.amapflying.util.kuwo.NewKuwoMusicManager;
import com.navi.tool.amapflying.util.netease.NeteaseMusicManager;
import com.navi.tool.amapflying.util.qq.QQMusicManager;
import com.navi.tool.model.AppInfo;
import com.navi.tool.service.GestureActionService;
import com.navi.tool.service.MusicIslandService;
import com.navi.tool.ui.adapter.AppAdapter;
import com.navi.tool.ui.adapter.PanelAppSettingsAdapter;
import com.navi.tool.ui.adapter.SelectAppAdapter;
import com.navi.tool.ui.common.DialogListDividers;
import com.navi.tool.utils.AppDetectionUtils;
import com.navi.tool.utils.DialogImeNavBarHelper;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.ToastHelper;
import com.navi.tool.view.WeatherAmbientView;
import com.yalantis.ucrop.UCropActivity;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class DesktopAssistantFragment extends androidx.fragment.app.z {
    private static final String CAR_MODEL_TYPE_CUSTOM = "custom";
    private static final String KEY_FLOATING_MAP_DISPLAY_MODE = "desktop_map_display_mode";
    private static final String KEY_MAP_ACTIVE_INDEX = "desktop_map_active_index";
    private static final int MAP_DISPLAY_MODE_CALENDAR = 1;
    private static final int MAP_DISPLAY_MODE_MAP = 0;
    private static final String PKG_ADB_SWITCH = "com.navi.tool.action.adbswitch";
    private static final String PKG_THEME_MODE = "com.navi.tool.thememode";
    private static final String PREF_CAR_CONTROL_CUSTOM_MODEL_PATH = "car_control_custom_model_path";
    private static final String PREF_CAR_CONTROL_MODEL_TYPE = "car_control_model_type";
    private static final String PREF_KEY_MAP_PACKAGE = "desktop_map_package";
    private static final String PREF_KEY_MAP_PACKAGE_2 = "desktop_map_package_2";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 3101;
    private Button btnAdjustTopInfo;
    private Button btnBrowseCustomCarModel;
    private Button btnClearFloatingMap;
    private Button btnClearFloatingMap2;
    private Button btnClearFloatingMusic;
    private Button btnClearVideoWallpaper;
    private Button btnClearWallpaper;
    private Button btnClearWeekly;
    private Button btnClearWeeklyVideo;
    private Button btnSelectDesktopApps;
    private Button btnSelectTopInfo;
    private Button btnSetDesktopApps;
    private Button btnSetDockApps;
    private Button btnSetFloatingMap;
    private Button btnSetFloatingMap2;
    private Button btnSetFloatingMusic;
    private Button btnSetVideoWallpaper;
    private Button btnSetWallpaper;
    private Button btnVideoWallFri;
    private Button btnVideoWallMon;
    private Button btnVideoWallSat;
    private Button btnVideoWallSun;
    private Button btnVideoWallThu;
    private Button btnVideoWallTue;
    private Button btnVideoWallWed;
    private Button btnWallFri;
    private Button btnWallMon;
    private Button btnWallSat;
    private Button btnWallSun;
    private Button btnWallThu;
    private Button btnWallTue;
    private Button btnWallWed;
    private d.b chooseCarModelImageLauncher;
    private d.b chooseIconLauncher;
    private d.b cropWallpaperLauncher;
    private PanelAppSettingsAdapter desktopAppSettingsAdapter;
    private View dividerAlbumArtOptions;
    private View dividerAutoLaunchFloating;
    private View dividerAutoStart;
    private View dividerCardMargin;
    private View dividerDesktopSeatCompatBottom;
    private View dividerDesktopSeatCompatTop;
    private View dividerDoorWindowDynamicEffectTop;
    private View dividerDynamicLaneTop;
    private View dividerHideFrontHeat;
    private View dividerHideFrontVent;
    private View dividerHideRearHeat;
    private View dividerMusicSpectrumColorful;
    private View dividerMusicSpectrumReal;
    private View dividerNightModeAlpha;
    private View dividerRoundView;
    private View dividerSeatPanelLegacyAfterMassage;
    private View dividerSeatPanelLegacyBeforeMassage;
    private View dividerSeatPanelLegacyBeforeMemory;
    private View dividerShowFragranceButton;
    private View dividerShowSteeringHeatButton;
    private View dividerVisualCarModelBottom;
    private View dividerVisualCarModelTop;
    private View dividerWallpaper;
    private LinearLayout layoutAlbumArtAnimation;
    private LinearLayout layoutAlbumArtOptions;
    private View layoutAmbientScale;
    private LinearLayout layoutAppSelection;
    private LinearLayout layoutAutoLaunchFloating;
    private LinearLayout layoutAutoStart;
    private LinearLayout layoutCarControlSettings;
    private LinearLayout layoutCarModelSelection;
    private LinearLayout layoutCardMargin;
    private View layoutCoverGradientIntensity;
    private LinearLayout layoutDesktopSeatCompat;
    private LinearLayout layoutDoorWindowDynamicEffect;
    private LinearLayout layoutDynamicLaneSettings;
    private LinearLayout layoutHideDriverMassage;
    private LinearLayout layoutHideFrontHeat;
    private LinearLayout layoutHideFrontVent;
    private LinearLayout layoutHideHvacFunctions;
    private LinearLayout layoutHideRearHeat;
    private LinearLayout layoutMapCalendarSettings;
    private LinearLayout layoutMusicClockWeather;
    private LinearLayout layoutMusicClockWeatherLocationMode;
    private LinearLayout layoutMusicIsland;
    private LinearLayout layoutMusicSettings;
    private LinearLayout layoutMusicSpectrumColorful;
    private LinearLayout layoutMusicSpectrumReal;
    private LinearLayout layoutNightModeAlpha;
    private LinearLayout layoutRoundCorner;
    private LinearLayout layoutSeatMassageCard;
    private LinearLayout layoutSeatMemoryCard;
    private LinearLayout layoutSeatMemorySaveCard;
    private LinearLayout layoutSettings;
    private LinearLayout layoutShowFragranceButton;
    private LinearLayout layoutShowSteeringHeatButton;
    private RelativeLayout layoutTopInfoSettings;
    private LinearLayout layoutWallpaperCarousel;
    private HorizontalScrollView layoutWeeklySettings;
    private LinearLayout layoutWeeklyVideoSection;
    private HorizontalScrollView layoutWeeklyVideoSettings;
    private BroadcastReceiver musicSourceUiReceiver;
    private CompoundButton.OnCheckedChangeListener musicSpectrumCheckedChangeListener;
    private String pendingIconChangePackageName;
    private MaterialCardView permissionStatusContainer;
    private d.b pickVideoWallpaperLauncher;
    private d.b pickWallpaperLauncher;
    private SharedPreferences prefs;
    private RadioGroup radioGroupMode;
    private RadioGroup radioGroupMusicClockWeatherLocationMode;
    private RadioGroup radioGroupMusicSource;
    private CompoundButton.OnCheckedChangeListener realMusicSpectrumCheckedChangeListener;
    private RadioGroup rgAlbumArtMode;
    private RadioGroup rgCarControlPropertyTextColor;
    private RadioGroup rgCarControlTextColor;
    private RadioGroup rgCarModelSelection;
    private RadioGroup rgLyricsColor;
    private int screenHeight;
    private int screenWidth;
    private SeekBar seekAmbientScale;
    private SeekBar seekBlurRadius;
    private SeekBar seekCarControlAlpha;
    private SeekBar seekCarControlFontSize;
    private SeekBar seekCardMargin;
    private SeekBar seekCarouselInterval;
    private SeekBar seekCoverGradientIntensity;
    private SeekBar seekLyricsFontSize;
    private SeekBar seekLyricsLines;
    private SeekBar seekNightModeAlpha;
    private SwitchMaterial switchAlbumArtAnimation;
    private SwitchMaterial switchAutoLaunchFloating;
    private SwitchMaterial switchCarControlAc;
    private SwitchMaterial switchCarControlCard;
    private SwitchMaterial switchCarControlDoor;
    private SwitchMaterial switchCarControlDrive;
    private SwitchMaterial switchCarControlEv;
    private SwitchMaterial switchCarControlSeat;
    private SwitchMaterial switchCarControlStatus;
    private SwitchMaterial switchColorfulMusicSpectrum;
    private SwitchMaterial switchDesktopAmbientFx;
    private SwitchMaterial switchDesktopAssistant;
    private SwitchMaterial switchDesktopAssistantAutoStart;
    private SwitchMaterial switchDesktopCoverGradient;
    private SwitchMaterial switchDesktopSeatCompat;
    private SwitchMaterial switchDesktopWeatherFx;
    private SwitchMaterial switchDoorWindowDynamicEffect;
    private SwitchMaterial switchDoubleClickSwap;
    private SwitchMaterial switchDynamicLane;
    private SwitchMaterial switchFloatingMapCard;
    private SwitchMaterial switchFloatingMusic;
    private SwitchMaterial switchFuelCard;
    private SwitchMaterial switchHideDock;
    private SwitchMaterial switchHideDriverMassage;
    private SwitchMaterial switchHideFrontHeat;
    private SwitchMaterial switchHideFrontVent;
    private SwitchMaterial switchHideHvacFunctions;
    private SwitchMaterial switchHideRearHeat;
    private SwitchMaterial switchMapCalendarCard;
    private SwitchMaterial switchMixedWallpaper;
    private SwitchMaterial switchMusicClockWeather;
    private SwitchMaterial switchMusicIsland;
    private SwitchMaterial switchMusicSpectrumEffect;
    private SwitchMaterial switchMusicWeatherAlert;
    private SwitchMaterial switchRandomWallpaper;
    private SwitchMaterial switchRealMusicSpectrum;
    private SwitchMaterial switchRoundCorner;
    private SwitchMaterial switchSeatComfortCard;
    private SwitchMaterial switchSeatMassageCard;
    private SwitchMaterial switchSeatMemoryCard;
    private SwitchMaterial switchSeatMemorySaveCard;
    private SwitchMaterial switchShowAlbumArt;
    private SwitchMaterial switchShowFragranceButton;
    private SwitchMaterial switchShowSteeringHeatButton;
    private SwitchMaterial switchTopInfoArea;
    private SwitchMaterial switchTransparentBg;
    private SwitchMaterial switchVisualCarModel;
    private SwitchMaterial switchWallpaperMode;
    private SwitchMaterial switchWeeklyVideoWallpaper;
    private SwitchMaterial switchWeeklyWallpaper;
    private TextView textAmbientScale;
    private TextView textBlurRadius;
    private TextView textCarControlAlpha;
    private TextView textCarControlFontSize;
    private TextView textCardMargin;
    private TextView textCarouselInterval;
    private TextView textCoverGradientIntensity;
    private TextView textFloatingMap2Current;
    private TextView textFloatingMapCurrent;
    private TextView textFloatingMusicCurrent;
    private TextView textLyricsFontSize;
    private TextView textLyricsLines;
    private TextView textMixedWallpaperDesc;
    private TextView textNightModeAlpha;
    private TextView textOverlayPermissionStatus;
    private TextView textStoragePermissionStatus;
    private TextView textTopInfoCount;
    private TextView textVideoWallpaperStatus;
    private TextView textWallpaperCount;
    private LruCache<String, Bitmap> videoManageThumbnailCache;
    private static final String PKG_FLOATING_MAP_MODE = "com.navi.tool.floatingmapmode";
    private static final String PKG_WALLPAPER_MODE = "com.navi.tool.wallpapermode";
    private static final Set<String> DEFAULT_ENABLED_APPS = new HashSet(Arrays.asList("com.navi.tool.originalhome", "com.navi.tool.desktopassistant", PKG_FLOATING_MAP_MODE, PKG_WALLPAPER_MODE));
    private static final String[] WEEK_DAYS = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
    private static final String[] CAR_INFO_IDS = {"speed", "gear", "fuel", "range", "odo", "temp", "outside_temp", "voltage", "engine_status", "power_mode", "trip_mileage", "drive_time", "door_fl", "door_fr", "door_rl", "door_rr", "trunk", "hood", "child_lock", "sunroof", "sunshade", "window_fl", "window_fr", "window_rl", "window_rr", "light_head", "light_high", "light_fog", "light_parking", "light_hazard", "hvac_ac", "hvac_fan", "hvac_circ", "hvac_auto", "hvac_power", "hvac_mode", "hvac_dual", "hvac_front_defrost", "hvac_rear_defrost", "driving_wiper", "driving_handbrake", "driving_brake", "driving_brake_link", "driving_suspension_mode", "driving_brake_feel_mode", "wiper_sensitivity", "seat_heat_fl", "seat_heat_fr", "seat_heat_rl", "seat_heat_rr", "seat_vent_fl", "seat_vent_fr", "seat_msg_driver", "seat_msg_pass", "ev_soc", "ev_soh", "battery_power", "battery_health", "avg_fuel_recently_50km", "avg_fuel_after_clear", "gnss_altitude", "gnss_accuracy", "gnss_satellites", "cdm_power", "phone_battery"};
    private static final String[] CAR_INFO_NAMES = {"车速", "档位", "剩余油量", "续航里程", "总里程", "空调温度", "车外温度", "电瓶电压", "引擎状态", "电源状态", "小计里程", "驾驶时长", "左前门", "右前门", "左后门", "右后门", "后备箱", "引擎盖", "儿童锁", "天窗", "遮阳帘", "左前窗", "右前窗", "左后窗", "右后窗", "近光灯", "远光灯", "雾灯", "示宽灯", "双闪灯", "空调AC", "风量", "循环模式", "空调AUTO", "空调系统", "吹风模式", "双区空调", "前除霜", "后除霜", "雨刮", "手刹", "刹车踏板", "制动关联", "悬架", "制动模式", "雨感灵敏", "主驾加热", "副驾加热", "左后加热", "右后加热", "主驾通风", "副驾通风", "主驾按摩", "副驾按摩", "剩余电量", "电池健康", "电瓶电量", "电瓶健康", "即时油耗", "总体油耗", "海拔", "精度", "卫星", "CDM动力", "手机电量"};
    private boolean isMusicSourceUiReceiverRegistered = false;
    private boolean suppressMusicSourceRadioCallback = false;
    private String currentEditingDay = null;
    private boolean pendingEnableRealMusicSpectrum = false;
    private final ExecutorService videoManageThumbDiskExecutor = Executors.newFixedThreadPool(2);
    private final ExecutorService videoManageThumbDecodeExecutor = Executors.newFixedThreadPool(2);
    private final AtomicInteger videoManageThumbSessionToken = new AtomicInteger(0);
    private final List<Future<?>> videoManageThumbDiskTasks = Collections.synchronizedList(new ArrayList());
    private final List<Future<?>> videoManageThumbDecodeTasks = Collections.synchronizedList(new ArrayList());
    private Queue<Uri> pendingWallpapers = new LinkedList();
    private int processedWallpaperCount = 0;
    private Queue<Uri> pendingVideoWallpapers = new LinkedList();
    private int processedVideoWallpaperCount = 0;
    private int failedVideoWallpaperCount = 0;
    private int nextVideoWallpaperIndex = 0;
    private boolean isAppendWallpaperMode = false;
    private boolean isAppendVideoWallpaperMode = false;
    private boolean isAutoCropAll = false;
    private String currentEditingVideoDay = null;

    /* renamed from: com.navi.tool.ui.extra.DesktopAssistantFragment$12, reason: invalid class name */
    public class AnonymousClass12 extends androidx.recyclerview.widget.h1 {
        final /* synthetic */ androidx.appcompat.app.o val$dialog;
        final /* synthetic */ List val$fileList;
        final /* synthetic */ boolean[] val$isOrderChanged;
        final /* synthetic */ int val$sessionToken;

        public AnonymousClass12(List list, int i7, boolean[] zArr, androidx.appcompat.app.o oVar) {
            this.val$fileList = list;
            this.val$sessionToken = i7;
            this.val$isOrderChanged = zArr;
            this.val$dialog = oVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(File file, androidx.recyclerview.widget.j2 j2Var, List list, boolean[] zArr, androidx.appcompat.app.o oVar, DialogInterface dialogInterface, int i7) {
            if (!file.delete()) {
                ToastHelper.show(DesktopAssistantFragment.this.requireContext(), "删除失败");
                return;
            }
            int adapterPosition = j2Var.getAdapterPosition();
            if (adapterPosition != -1) {
                list.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, list.size());
                zArr[0] = true;
                ToastHelper.show(DesktopAssistantFragment.this.requireContext(), "已删除，关闭后自动生效");
                if (list.isEmpty()) {
                    oVar.dismiss();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$1(File file, androidx.recyclerview.widget.j2 j2Var, List list, boolean[] zArr, androidx.appcompat.app.o oVar, View view) {
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(DesktopAssistantFragment.this.requireContext());
            nVar.t("确认删除");
            nVar.m("确定要删除这个动态壁纸吗？");
            nVar.r("删除", new u2(this, file, j2Var, list, zArr, oVar, 0));
            nVar.o("取消", null);
            nVar.v();
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.val$fileList.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(androidx.recyclerview.widget.j2 j2Var, int i7) {
            File file = (File) this.val$fileList.get(i7);
            FrameLayout frameLayout = (FrameLayout) j2Var.itemView;
            ImageView imageView = (ImageView) ((CardView) frameLayout.getChildAt(0)).getChildAt(0);
            ImageView imageView2 = (ImageView) frameLayout.getChildAt(1);
            imageView.setTag(file.getAbsolutePath());
            imageView.setImageResource(g4.o.ic_file_video);
            DesktopAssistantFragment.this.loadManagedVideoThumbnail(imageView, file, this.val$sessionToken);
            imageView2.setOnClickListener(new v2(this, file, j2Var, this.val$fileList, this.val$isOrderChanged, this.val$dialog, 0));
        }

        @Override // androidx.recyclerview.widget.h1
        public androidx.recyclerview.widget.j2 onCreateViewHolder(ViewGroup viewGroup, int i7) {
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            float f7 = DesktopAssistantFragment.this.getResources().getDisplayMetrics().density;
            frameLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, -2));
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams();
            float f8 = 6.0f * f7;
            int i8 = (int) f8;
            marginLayoutParams.setMargins(i8, i8, i8, i8);
            frameLayout.setLayoutParams(marginLayoutParams);
            CardView cardView = new CardView(viewGroup.getContext());
            cardView.setRadius(f8);
            cardView.setCardElevation(CropImageView.DEFAULT_ASPECT_RATIO);
            frameLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, (int) ((((int) ((DesktopAssistantFragment.this.screenWidth * 0.7f) / 3.0f)) * 9.0f) / 16.0f)));
            ((ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams()).setMargins(i8, i8, i8, i8);
            ImageView imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(g4.o.ic_file_video);
            cardView.addView(imageView, new ViewGroup.LayoutParams(-1, -1));
            frameLayout.addView(cardView, new FrameLayout.LayoutParams(-1, -1));
            ImageView imageView2 = new ImageView(viewGroup.getContext());
            imageView2.setImageResource(R.drawable.ic_menu_close_clear_cancel);
            imageView2.setBackgroundColor(Integer.MIN_VALUE);
            imageView2.setColorFilter(k0.a.CATEGORY_MASK);
            int i9 = (int) (f7 * 24.0f);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i9, i9);
            layoutParams.gravity = 8388661;
            layoutParams.setMargins(8, 8, 8, 8);
            frameLayout.addView(imageView2, layoutParams);
            return new androidx.recyclerview.widget.j2(frameLayout) { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.12.1
            };
        }
    }

    /* renamed from: com.navi.tool.ui.extra.DesktopAssistantFragment$21, reason: invalid class name */
    public class AnonymousClass21 extends androidx.recyclerview.widget.h1 {
        final /* synthetic */ androidx.appcompat.app.o val$dialog;
        final /* synthetic */ List val$fileList;
        final /* synthetic */ boolean[] val$isOrderChanged;

        public AnonymousClass21(List list, boolean[] zArr, androidx.appcompat.app.o oVar) {
            this.val$fileList = list;
            this.val$isOrderChanged = zArr;
            this.val$dialog = oVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onBindViewHolder$0(ImageView imageView, Bitmap bitmap) {
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$1(File file, ImageView imageView) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int i7 = 1;
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                options.inSampleSize = 4;
                options.inJustDecodeBounds = false;
                Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                if (DesktopAssistantFragment.this.getActivity() != null) {
                    DesktopAssistantFragment.this.getActivity().runOnUiThread(new z0(i7, imageView, decodeFile));
                }
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$2(File file, androidx.recyclerview.widget.j2 j2Var, List list, boolean[] zArr, androidx.appcompat.app.o oVar, DialogInterface dialogInterface, int i7) {
            if (!file.delete()) {
                ToastHelper.show(DesktopAssistantFragment.this.requireContext(), "删除失败");
                return;
            }
            int adapterPosition = j2Var.getAdapterPosition();
            if (adapterPosition != -1) {
                list.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, list.size());
                zArr[0] = true;
                ToastHelper.show(DesktopAssistantFragment.this.requireContext(), "已删除，关闭后自动生效");
                if (list.isEmpty()) {
                    oVar.dismiss();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$3(File file, androidx.recyclerview.widget.j2 j2Var, List list, boolean[] zArr, androidx.appcompat.app.o oVar, View view) {
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(DesktopAssistantFragment.this.requireContext());
            nVar.t("确认删除");
            nVar.m("确定要删除这张壁纸吗？");
            nVar.r("删除", new u2(this, file, j2Var, list, zArr, oVar, 1));
            nVar.o("取消", null);
            nVar.v();
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.val$fileList.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(androidx.recyclerview.widget.j2 j2Var, int i7) {
            File file = (File) this.val$fileList.get(i7);
            FrameLayout frameLayout = (FrameLayout) j2Var.itemView;
            ImageView imageView = (ImageView) ((CardView) frameLayout.getChildAt(0)).getChildAt(0);
            ImageView imageView2 = (ImageView) frameLayout.getChildAt(1);
            new Thread(new w2(this, file, imageView, 0)).start();
            imageView2.setOnClickListener(new v2(this, file, j2Var, this.val$fileList, this.val$isOrderChanged, this.val$dialog, 1));
        }

        @Override // androidx.recyclerview.widget.h1
        public androidx.recyclerview.widget.j2 onCreateViewHolder(ViewGroup viewGroup, int i7) {
            FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
            float f7 = DesktopAssistantFragment.this.getResources().getDisplayMetrics().density;
            frameLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, -2));
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams();
            float f8 = f7 * 6.0f;
            int i8 = (int) f8;
            marginLayoutParams.setMargins(i8, i8, i8, i8);
            frameLayout.setLayoutParams(marginLayoutParams);
            CardView cardView = new CardView(viewGroup.getContext());
            cardView.setRadius(f8);
            cardView.setCardElevation(CropImageView.DEFAULT_ASPECT_RATIO);
            frameLayout.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, (int) ((((int) ((DesktopAssistantFragment.this.screenWidth * 0.7f) / 3.0f)) * 9.0f) / 16.0f)));
            frameLayout.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) frameLayout.getLayoutParams()).setMargins(i8, i8, i8, i8);
            ImageView imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            cardView.addView(imageView, new ViewGroup.LayoutParams(-1, -1));
            frameLayout.addView(cardView, new FrameLayout.LayoutParams(-1, -1));
            ImageView imageView2 = new ImageView(viewGroup.getContext());
            imageView2.setImageResource(R.drawable.ic_menu_close_clear_cancel);
            imageView2.setBackgroundColor(Integer.MIN_VALUE);
            imageView2.setColorFilter(k0.a.CATEGORY_MASK);
            int i9 = (int) (DesktopAssistantFragment.this.getResources().getDisplayMetrics().density * 24.0f);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i9, i9);
            layoutParams.gravity = 8388661;
            layoutParams.setMargins(8, 8, 8, 8);
            frameLayout.addView(imageView2, layoutParams);
            return new androidx.recyclerview.widget.j2(frameLayout) { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.21.1
            };
        }
    }

    private void addAppToAutoStart(String str) {
        if (str == null || str.isEmpty() || SharedPreferencesUtils.isAutoStartAppEnabled(requireContext(), str)) {
            return;
        }
        int nextAutoStartDelay = SharedPreferencesUtils.getNextAutoStartDelay(requireContext(), str);
        SharedPreferencesUtils.setAutoStartAppEnabled(requireContext(), str, true);
        SharedPreferencesUtils.setAutoStartAppDelay(requireContext(), str, nextAutoStartDelay);
        ToastHelper.show(requireContext(), "已将 " + getAppName(str) + " 加入自启列表，延时" + nextAutoStartDelay + "秒");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String ambientScaleText(float f7) {
        return String.format(Locale.ROOT, "调整氛围物体的显示尺寸 (范围: 1.5x - 3.0x，当前 %.1fx)", Float.valueOf(f7));
    }

    private void autoCropAndSave(Uri uri) {
        new Thread(new z0(10, this, uri)).start();
    }

    private void autoUpdateMusicSource(String str) {
        int resolveMusicSourceFromPackage = resolveMusicSourceFromPackage(str);
        this.prefs.edit().putInt("desktop_music_source", resolveMusicSourceFromPackage).putInt(FloatingWindowService.KEY_MUSIC_SOURCE, resolveMusicSourceFromPackage).apply();
        int musicSourceRadioId = getMusicSourceRadioId(resolveMusicSourceFromPackage);
        RadioGroup radioGroup = this.radioGroupMusicSource;
        if (radioGroup != null) {
            if (radioGroup.getCheckedRadioButtonId() != musicSourceRadioId) {
                this.radioGroupMusicSource.check(musicSourceRadioId);
            } else if (this.prefs.getBoolean("desktop_music_enabled", false)) {
                checkAndInitMusicService();
            }
        } else if (this.prefs.getBoolean("desktop_music_enabled", false)) {
            checkAndInitMusicService();
        }
        ToastHelper.show(requireContext(), "已自动设置音乐源为：" + getMusicSourceDisplayName(resolveMusicSourceFromPackage));
    }

    private String buildManagedVideoThumbnailCacheKey(File file) {
        return md5(file.getAbsolutePath() + "_" + file.lastModified());
    }

    private String buildVideoWallpaperFileName(int i7) {
        if (i7 == 0) {
            return "desktop_wallpaper.mp4";
        }
        return "desktop_wallpaper" + (i7 + 1) + ".mp4";
    }

    private int calculateManagedThumbnailInSampleSize(BitmapFactory.Options options, int i7, int i8) {
        int i9;
        int i10 = options.outHeight;
        int i11 = options.outWidth;
        if (i10 > i8 || i11 > i7) {
            int i12 = i10 / 2;
            int i13 = i11 / 2;
            i9 = 1;
            while (i12 / i9 >= i8 && i13 / i9 >= i7) {
                i9 *= 2;
            }
        } else {
            i9 = 1;
        }
        return Math.max(1, i9);
    }

    private void cancelManagedVideoThumbnailTasks() {
        cancelManagedVideoThumbnailTasks(this.videoManageThumbDiskTasks);
        cancelManagedVideoThumbnailTasks(this.videoManageThumbDecodeTasks);
    }

    private void cancelManagedVideoThumbnailTasks(List<Future<?>> list) {
        synchronized (list) {
            try {
                ArrayList arrayList = new ArrayList(list);
                list.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Future) it.next()).cancel(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void checkAndInitMusicService() {
        int i7 = SharedPreferencesUtils.getAppPrefs(requireContext()).getInt("desktop_music_source", 0);
        DesktopAssistantMusicUiHelper.dispatchMusicControl(requireContext(), SharedPreferencesUtils.getAppPrefs(requireContext()), true, 0);
        stopMusicServices();
        switch (i7) {
            case 0:
                QQMusicManager.getInstance(requireContext()).start();
                break;
            case 1:
                KuwoMusicManager.getInstance(requireContext()).start();
                break;
            case 2:
                KugouMusicManager.getInstance(requireContext()).start();
                break;
            case 3:
                NeteaseMusicManager.getInstance(requireContext()).start();
                break;
            case 4:
                NewKuwoMusicManager.getInstance(requireContext()).start();
                break;
            case 5:
                k4.a.c(requireContext()).g();
                break;
            case 6:
                l4.e.b(requireContext()).d();
                break;
        }
    }

    private void checkAndSyncAutoLaunchFloatingApps() {
        String string = this.prefs.getString(PREF_KEY_MAP_PACKAGE, null);
        String string2 = this.prefs.getString(PREF_KEY_MAP_PACKAGE_2, null);
        String string3 = this.prefs.getString("desktop_music_package", null);
        if ((string == null || string.isEmpty()) && ((string2 == null || string2.isEmpty()) && (string3 == null || string3.isEmpty()))) {
            View view = this.dividerAutoLaunchFloating;
            if (view != null) {
                view.setVisibility(8);
            }
            LinearLayout linearLayout = this.layoutAutoLaunchFloating;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
                return;
            }
            return;
        }
        View view2 = this.dividerAutoLaunchFloating;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        LinearLayout linearLayout2 = this.layoutAutoLaunchFloating;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(0);
        }
    }

    private void checkResolutionAndProcess(final Uri uri) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            final int i7 = 1;
            options.inJustDecodeBounds = true;
            InputStream openInputStream = requireContext().getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(openInputStream, null, options);
            if (openInputStream != null) {
                openInputStream.close();
            }
            int i8 = options.outWidth;
            int i9 = options.outHeight;
            if (i8 == this.screenWidth && i9 == this.screenHeight) {
                saveSingleWallpaper(uri);
                processNextWallpaper();
                return;
            }
            if (this.isAutoCropAll) {
                autoCropAndSave(uri);
                return;
            }
            LinearLayout linearLayout = new LinearLayout(requireContext());
            linearLayout.setOrientation(1);
            int i10 = (int) (getResources().getDisplayMetrics().density * 24.0f);
            linearLayout.setPadding(i10, i10 / 2, i10, i10 / 2);
            TextView textView = new TextView(requireContext());
            textView.setText("图片分辨率 (" + i8 + "x" + i9 + ") 与车机屏幕 (" + this.screenWidth + "x" + this.screenHeight + ") 不一致，建议裁剪以获得最佳效果。");
            textView.setTextSize(16.0f);
            linearLayout.addView(textView);
            LinearLayout linearLayout2 = new LinearLayout(requireContext());
            final int i11 = 0;
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(8388613);
            linearLayout2.setPadding(0, i10, 0, 0);
            Button button = new Button(requireContext());
            button.setText("直接使用");
            button.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.e2

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ DesktopAssistantFragment f5130b;

                {
                    this.f5130b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i12 = i11;
                    DesktopAssistantFragment desktopAssistantFragment = this.f5130b;
                    Uri uri2 = uri;
                    androidx.appcompat.app.o[] oVarArr = r2;
                    switch (i12) {
                        case 0:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$140(oVarArr, uri2, view);
                            break;
                        case 1:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$141(oVarArr, uri2, view);
                            break;
                        default:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$142(oVarArr, uri2, view);
                            break;
                    }
                }
            });
            linearLayout2.addView(button);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins((int) (getResources().getDisplayMetrics().density * 8.0f), 0, 0, 0);
            Button button2 = new Button(requireContext());
            button2.setText("手动裁剪");
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.e2

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ DesktopAssistantFragment f5130b;

                {
                    this.f5130b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i12 = i7;
                    DesktopAssistantFragment desktopAssistantFragment = this.f5130b;
                    Uri uri2 = uri;
                    androidx.appcompat.app.o[] oVarArr = r2;
                    switch (i12) {
                        case 0:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$140(oVarArr, uri2, view);
                            break;
                        case 1:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$141(oVarArr, uri2, view);
                            break;
                        default:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$142(oVarArr, uri2, view);
                            break;
                    }
                }
            });
            linearLayout2.addView(button2, layoutParams);
            Button button3 = new Button(requireContext());
            button3.setText("自动裁剪(剩余全部)");
            final int i12 = 2;
            button3.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.e2

                /* renamed from: b, reason: collision with root package name */
                public final /* synthetic */ DesktopAssistantFragment f5130b;

                {
                    this.f5130b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i122 = i12;
                    DesktopAssistantFragment desktopAssistantFragment = this.f5130b;
                    Uri uri2 = uri;
                    androidx.appcompat.app.o[] oVarArr = r2;
                    switch (i122) {
                        case 0:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$140(oVarArr, uri2, view);
                            break;
                        case 1:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$141(oVarArr, uri2, view);
                            break;
                        default:
                            desktopAssistantFragment.lambda$checkResolutionAndProcess$142(oVarArr, uri2, view);
                            break;
                    }
                }
            });
            linearLayout2.addView(button3, layoutParams);
            linearLayout.addView(linearLayout2);
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
            nVar.t("分辨率不匹配");
            nVar.u(linearLayout);
            nVar.k();
            androidx.appcompat.app.o e7 = nVar.e();
            final androidx.appcompat.app.o[] oVarArr = {e7};
            e7.show();
        } catch (Exception e8) {
            e8.printStackTrace();
            ToastHelper.show(requireContext(), "读取图片失败，跳过");
            processNextWallpaper();
        }
    }

    private void checkWallpaperCount() {
        File[] listFiles;
        File[] listFiles2;
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper");
        int length = (!file.exists() || (listFiles2 = file.listFiles(new s0(9))) == null) ? 0 : listFiles2.length;
        if (length > 0) {
            this.textWallpaperCount.setVisibility(0);
            a.a.v("静态壁纸：", length, "张", this.textWallpaperCount);
            this.btnClearWallpaper.setVisibility(0);
        } else {
            this.textWallpaperCount.setVisibility(8);
            this.btnClearWallpaper.setVisibility(8);
        }
        String[] strArr = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        int i7 = 0;
        for (int i8 = 0; i8 < 7; i8++) {
            File file2 = new File(file, strArr[i8]);
            if (file2.exists() && (listFiles = file2.listFiles(new s0(10))) != null) {
                i7 = Math.max(i7, listFiles.length);
            }
        }
        boolean z6 = this.prefs.getBoolean("wallpaper_weekly_enabled", false);
        if (length > 1 || (z6 && i7 > 1)) {
            this.layoutWallpaperCarousel.setVisibility(0);
        } else {
            this.layoutWallpaperCarousel.setVisibility(8);
        }
        updateMixedWallpaperAvailability();
    }

    private boolean clearVideoWallpaperFiles(String str) {
        File[] listFiles;
        Iterator<File> it = getVideoWallpaperFiles(str).iterator();
        boolean z6 = false;
        while (it.hasNext()) {
            if (it.next().delete()) {
                z6 = true;
            }
        }
        if (str != null) {
            File videoWallpaperDir = getVideoWallpaperDir(str);
            if (videoWallpaperDir.exists() && ((listFiles = videoWallpaperDir.listFiles()) == null || listFiles.length == 0)) {
                videoWallpaperDir.delete();
            }
        }
        return z6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String coverGradientIntensityText(int i7) {
        return String.format(Locale.ROOT, "调整封面渐变背景的亮度 (范围: 0%% - 100%%，当前 %d%%)", Integer.valueOf(i7));
    }

    private Bitmap decodeManagedThumbnailFile(File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                    options.inSampleSize = calculateManagedThumbnailInSampleSize(options, z1.c.AND_LONG, z1.c.INVOKE_INTERFACE_RANGE);
                    options.inJustDecodeBounds = false;
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private String describePackage(String str) {
        try {
            PackageManager packageManager = requireContext().getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString() + "\n" + str;
        } catch (Exception unused) {
            return str;
        }
    }

    private void ensureCarLogoFolderExists() {
        File file = new File(new File(Environment.getExternalStorageDirectory(), "navitool"), "carlogo");
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private void ensureIconFolderExists() {
        File file = new File(new File(Environment.getExternalStorageDirectory(), "navitool"), "icon");
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private void forceDisableRoundCornerForVideo() {
        if (this.prefs.getBoolean("desktop_round_corner_enabled", false)) {
            a.a.m(this.prefs, "desktop_round_corner_enabled", false);
            this.switchRoundCorner.setChecked(false);
            ToastHelper.show(requireContext(), "动态壁纸模式下桌面圆角视图已自动关闭");
            sendRoundCornerUpdatedBroadcast();
        }
    }

    private void generateManagedVideoThumbnail(ImageView imageView, File file, File file2, String str, String str2, int i7) {
        submitManagedVideoThumbnailTask(this.videoManageThumbDecodeExecutor, this.videoManageThumbDecodeTasks, new y1(this, i7, file, str2, file2, str, imageView));
    }

    private List<AppInfo> getAllLaunchableApps() {
        return getContext() != null ? getAllLaunchableApps(getContext()) : new ArrayList();
    }

    private List<AppInfo> getAllLaunchableApps(Context context) {
        String str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            String str2 = activityInfo.packageName;
            String str3 = activityInfo.name;
            if (!"com.navi.tool.FileAssistantActivity".equals(str3) && !"com.navi.tool.PermissionAssistantActivity".equals(str3)) {
                if (!linkedHashMap.containsKey("com.navi.tool".equals(str2) ? str2 + "/" + str3 : str2)) {
                    try {
                        str = resolveInfo.loadLabel(packageManager).toString();
                    } catch (Exception unused) {
                        str = str2;
                    }
                    if (!linkedHashMap.containsKey(str2)) {
                        linkedHashMap.put(str2, new AppInfo(str2, str, false));
                    }
                }
            }
        }
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(context);
        if (appPrefs.getBoolean("file_assistant_enabled", false)) {
            linkedHashMap.put("com.navi.tool.fileassistant", new AppInfo("com.navi.tool.fileassistant", "文件助手", true));
        }
        if (appPrefs.getBoolean("permission_assistant_enabled", false)) {
            linkedHashMap.put("com.navi.tool.permissionassistant", new AppInfo("com.navi.tool.permissionassistant", "权限助手", true));
        }
        linkedHashMap.put("com.navi.tool.originalhome", new AppInfo("com.navi.tool.originalhome", "原车桌面", true));
        linkedHashMap.put(PKG_ADB_SWITCH, new AppInfo(PKG_ADB_SWITCH, "ADB界面", true));
        linkedHashMap.put("com.navi.tool.desktopassistant", new AppInfo("com.navi.tool.desktopassistant", "桌面设置", true));
        linkedHashMap.put("com.navi.tool.action.restart", new AppInfo("com.navi.tool.action.restart", "重启桌面", true));
        linkedHashMap.put(PKG_THEME_MODE, new AppInfo(PKG_THEME_MODE, "主题模式", true));
        linkedHashMap.put(PKG_FLOATING_MAP_MODE, new AppInfo(PKG_FLOATING_MAP_MODE, getFloatingMapModeLabel(), true));
        linkedHashMap.put(PKG_WALLPAPER_MODE, new AppInfo(PKG_WALLPAPER_MODE, "动态壁纸", true));
        ArrayList arrayList = new ArrayList(linkedHashMap.values());
        arrayList.sort(new x(13));
        return arrayList;
    }

    private String getAppName(String str) {
        try {
            PackageManager packageManager = requireContext().getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
        } catch (Exception unused) {
            return str;
        }
    }

    private String getCarInfoNameById(String str) {
        int i7 = 0;
        while (true) {
            String[] strArr = CAR_INFO_IDS;
            if (i7 >= strArr.length) {
                return null;
            }
            if (strArr[i7].equals(str)) {
                return CAR_INFO_NAMES[i7];
            }
            i7++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0047, code lost:
    
        r0 = r9.lastIndexOf(47);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004d, code lost:
    
        if (r0 < 0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0055, code lost:
    
        return r9.substring(r0 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
    
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003d, code lost:
    
        if (r1 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x002d, code lost:
    
        if (r1 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002f, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0040, code lost:
    
        r9 = r9.getPath();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0044, code lost:
    
        if (r9 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0046, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getDisplayName(android.net.Uri r9) {
        /*
            r8 = this;
            r0 = 0
            android.content.Context r1 = r8.requireContext()     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L33 java.lang.Exception -> L35
            if (r1 == 0) goto L2d
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L3d
            if (r2 == 0) goto L2d
            java.lang.String r2 = "_display_name"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L3d
            if (r2 < 0) goto L2d
            java.lang.String r9 = r1.getString(r2)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L3d
            r1.close()
            return r9
        L2a:
            r9 = move-exception
            r0 = r1
            goto L37
        L2d:
            if (r1 == 0) goto L40
        L2f:
            r1.close()
            goto L40
        L33:
            r9 = move-exception
            goto L37
        L35:
            r1 = r0
            goto L3d
        L37:
            if (r0 == 0) goto L3c
            r0.close()
        L3c:
            throw r9
        L3d:
            if (r1 == 0) goto L40
            goto L2f
        L40:
            java.lang.String r9 = r9.getPath()
            if (r9 != 0) goto L47
            return r0
        L47:
            r0 = 47
            int r0 = r9.lastIndexOf(r0)
            if (r0 < 0) goto L55
            int r0 = r0 + 1
            java.lang.String r9 = r9.substring(r0)
        L55:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantFragment.getDisplayName(android.net.Uri):java.lang.String");
    }

    private List<AppInfo> getEnabledDesktopAppsInOrder() {
        return getContext() != null ? getEnabledDesktopAppsInOrder(getContext(), "desktop_app_order") : new ArrayList();
    }

    private List<AppInfo> getEnabledDesktopAppsInOrder(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        String string = this.prefs.getString(str, s5.h0.FRAGMENT_ENCODE_SET);
        HashSet hashSet = new HashSet();
        String str2 = str.contains("dock") ? "dock_app_enabled_" : "desktop_app_enabled_";
        boolean contains = str.contains("dock");
        if (string != null && !string.isEmpty()) {
            for (String str3 : string.split(",")) {
                String trim = str3.trim();
                if (!trim.isEmpty()) {
                    if ("com.navi.tool.desktopassistant".equals(trim)) {
                        a.a.x(trim, "桌面设置", this.prefs.getBoolean(str2.concat(trim), contains && DEFAULT_ENABLED_APPS.contains(trim)), arrayList);
                        hashSet.add(trim);
                    } else if ("com.navi.tool.action.restart".equals(trim)) {
                        a.a.x(trim, "重启桌面", this.prefs.getBoolean(str2.concat(trim), false), arrayList);
                        hashSet.add(trim);
                    } else if ("com.navi.tool.originalhome".equals(trim)) {
                        a.a.x(trim, "原车桌面", this.prefs.getBoolean(str2.concat(trim), contains && DEFAULT_ENABLED_APPS.contains(trim)), arrayList);
                        hashSet.add(trim);
                    } else if (PKG_ADB_SWITCH.equals(trim)) {
                        a.a.x(trim, "ADB界面", this.prefs.getBoolean(str2.concat(trim), contains && DEFAULT_ENABLED_APPS.contains(trim)), arrayList);
                        hashSet.add(trim);
                    } else if ("com.navi.tool.fileassistant".equals(trim)) {
                        if (this.prefs.getBoolean("file_assistant_enabled", false)) {
                            a.a.x(trim, "文件助手", this.prefs.getBoolean(str2.concat(trim), false), arrayList);
                            hashSet.add(trim);
                        }
                    } else if ("com.navi.tool.permissionassistant".equals(trim)) {
                        if (this.prefs.getBoolean("permission_assistant_enabled", false)) {
                            a.a.x(trim, "权限助手", this.prefs.getBoolean(str2.concat(trim), false), arrayList);
                            hashSet.add(trim);
                        }
                    } else if (PKG_THEME_MODE.equals(trim)) {
                        a.a.x(trim, "主题模式", this.prefs.getBoolean(str2.concat(trim), false), arrayList);
                        hashSet.add(trim);
                    } else if (PKG_FLOATING_MAP_MODE.equals(trim)) {
                        arrayList.add(new AppInfo(trim, getFloatingMapModeLabel(), this.prefs.getBoolean(str2.concat(trim), contains && DEFAULT_ENABLED_APPS.contains(trim))));
                        hashSet.add(trim);
                    } else if (PKG_WALLPAPER_MODE.equals(trim)) {
                        a.a.x(trim, "动态壁纸", this.prefs.getBoolean(str2.concat(trim), contains && DEFAULT_ENABLED_APPS.contains(trim)), arrayList);
                        hashSet.add(trim);
                    } else if (!isSystemApp(context, trim) || this.prefs.getBoolean("desktop_app_enabled_".concat(trim), false)) {
                        try {
                            arrayList.add(new AppInfo(trim, packageManager.getApplicationLabel(packageManager.getApplicationInfo(trim, 0)).toString(), this.prefs.getBoolean(str2 + trim, false)));
                            hashSet.add(trim);
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
        for (AppInfo appInfo : getAllLaunchableApps(context)) {
            String packageName = appInfo.getPackageName();
            if (!hashSet.contains(packageName)) {
                if (isSystemApp(context, packageName)) {
                    if (!this.prefs.getBoolean("desktop_app_enabled_" + packageName, false)) {
                    }
                }
                appInfo.setEnabled(this.prefs.getBoolean(str2 + packageName, false));
                arrayList.add(appInfo);
                hashSet.add(packageName);
            }
        }
        return arrayList;
    }

    private List<AppInfo> getEnabledTopInfoItemsInOrder() {
        String carInfoNameById;
        ArrayList arrayList = new ArrayList();
        String string = this.prefs.getString("top_info_item_order", s5.h0.FRAGMENT_ENCODE_SET);
        HashSet hashSet = new HashSet();
        if (string != null && !string.isEmpty()) {
            for (String str : string.split(",")) {
                String trim = str.trim();
                if (!trim.isEmpty() && isTopInfoSupportedInCurrentMode(trim) && (carInfoNameById = getCarInfoNameById(trim)) != null && this.prefs.getBoolean("top_info_item_enabled_".concat(trim), false)) {
                    String string2 = this.prefs.getString("top_info_item_name_".concat(trim), null);
                    if (string2 != null) {
                        carInfoNameById = string2;
                    }
                    arrayList.add(new AppInfo(trim, carInfoNameById, true));
                    hashSet.add(trim);
                }
            }
        }
        int i7 = 0;
        while (true) {
            String[] strArr = CAR_INFO_IDS;
            if (i7 >= strArr.length) {
                return arrayList;
            }
            String str2 = strArr[i7];
            if (!hashSet.contains(str2) && isTopInfoSupportedInCurrentMode(str2)) {
                if (this.prefs.getBoolean("top_info_item_enabled_" + str2, false)) {
                    String str3 = CAR_INFO_NAMES[i7];
                    String string3 = this.prefs.getString("top_info_item_name_" + str2, null);
                    if (string3 != null) {
                        str3 = string3;
                    }
                    arrayList.add(new AppInfo(str2, str3, true));
                    hashSet.add(str2);
                }
            }
            i7++;
        }
    }

    private int getFloatingMapDisplayMode() {
        SharedPreferences sharedPreferences = this.prefs;
        if (sharedPreferences == null) {
            return 0;
        }
        if (sharedPreferences.contains(KEY_FLOATING_MAP_DISPLAY_MODE)) {
            return this.prefs.getInt(KEY_FLOATING_MAP_DISPLAY_MODE, 0);
        }
        String string = this.prefs.getString(PREF_KEY_MAP_PACKAGE, null);
        return ((string == null || string.isEmpty()) && this.prefs.getBoolean("desktop_map_calendar_enabled", false)) ? 1 : 0;
    }

    private String getFloatingMapModeLabel() {
        return AppDetectionUtils.getFloatingMapModeLabel(requireContext(), this.prefs);
    }

    private File getManagedVideoThumbnailCacheFile(File file) {
        File file2 = new File(requireContext().getCacheDir(), "desktop_video_manage_thumbs");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return new File(file2, a.a.g(buildManagedVideoThumbnailCacheKey(file), ".jpg"));
    }

    private String getMusicSourceDisplayName(int i7) {
        switch (i7) {
            case 1:
                return "酷我音乐车机版";
            case 2:
                return "酷狗音乐车机版";
            case 3:
                return "网易云音乐车机版";
            case 4:
                return "新酷我音乐车机版";
            case 5:
                return "汽水音乐车机版";
            case 6:
                return "通用音乐";
            default:
                return "QQ音乐车机版";
        }
    }

    private int getMusicSourceRadioId(int i7) {
        switch (i7) {
            case 1:
                return g4.p.radio_source_kuwo;
            case 2:
                return g4.p.radio_source_kugou;
            case 3:
                return g4.p.radio_source_netease;
            case 4:
                return g4.p.radio_source_new_kuwo;
            case 5:
                return g4.p.radio_source_luna;
            case 6:
                return g4.p.radio_source_universal;
            default:
                return g4.p.radio_source_qq;
        }
    }

    private String getPackageVersionName(String str) {
        if (!isAdded()) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
        try {
            return requireContext().getPackageManager().getPackageInfo(str, 0).versionName == null ? s5.h0.FRAGMENT_ENCODE_SET : requireContext().getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception unused) {
            return s5.h0.FRAGMENT_ENCODE_SET;
        }
    }

    private File getSingleVideoWallpaperFile() {
        return new File(getVideoWallpaperRootDir(), "desktop_wallpaper.mp4");
    }

    private int getVideoWallpaperCount(String str) {
        return getVideoWallpaperFiles(str).size();
    }

    private File getVideoWallpaperDir(String str) {
        return str == null ? getVideoWallpaperRootDir() : new File(getVideoWallpaperRootDir(), str);
    }

    private List<File> getVideoWallpaperFiles(String str) {
        File[] listFiles = getVideoWallpaperDir(str).listFiles(new s0(11));
        ArrayList arrayList = new ArrayList();
        if (listFiles != null) {
            Collections.addAll(arrayList, listFiles);
            Collections.sort(arrayList, new x(14));
        }
        return arrayList;
    }

    private int getVideoWallpaperMaxIndex(File file) {
        File[] listFiles = file.listFiles(new s0(6));
        if (listFiles == null) {
            return 0;
        }
        int i7 = 0;
        for (File file2 : listFiles) {
            try {
                String replace = file2.getName().replace("desktop_wallpaper", s5.h0.FRAGMENT_ENCODE_SET).replace(".mp4", s5.h0.FRAGMENT_ENCODE_SET);
                i7 = replace.isEmpty() ? Math.max(i7, 1) : Math.max(i7, Integer.parseInt(replace));
            } catch (Exception unused) {
            }
        }
        return i7;
    }

    private File getVideoWallpaperRootDir() {
        return new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper_video");
    }

    private File getWeeklyVideoWallpaperFile(String str) {
        return new File(new File(getVideoWallpaperRootDir(), str), "desktop_wallpaper.mp4");
    }

    private int getWeeklyVideoWallpaperTotalCount() {
        int i7 = 0;
        for (String str : WEEK_DAYS) {
            i7 += getVideoWallpaperCount(str);
        }
        return i7;
    }

    private void handleCustomCarModelChosen(Uri uri) {
        int i7;
        int lastIndexOf;
        ensureCarLogoFolderExists();
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/carlogo");
        if (!file.exists() && !file.mkdirs()) {
            ToastHelper.show(requireContext(), "无法创建车模目录");
            return;
        }
        String queryDisplayName = queryDisplayName(uri);
        String substring = (queryDisplayName == null || (lastIndexOf = queryDisplayName.lastIndexOf(46)) < 0 || lastIndexOf >= queryDisplayName.length() + (-1)) ? ".png" : queryDisplayName.substring(lastIndexOf);
        File file2 = new File(file, "custom_car_model_tmp_" + System.currentTimeMillis() + substring);
        File file3 = new File(file, a.a.B("custom_car_model", substring));
        try {
            InputStream openInputStream = requireContext().getContentResolver().openInputStream(uri);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    if (openInputStream == null) {
                        ToastHelper.show(requireContext(), "无法读取选中的文件");
                        fileOutputStream.close();
                        if (openInputStream != null) {
                            openInputStream.close();
                            return;
                        }
                        return;
                    }
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = openInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, read);
                        }
                    }
                    fileOutputStream.flush();
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        for (File file4 : listFiles) {
                            if (file4 != null && file4.isFile() && file4.getName().startsWith("custom_car_model") && !file4.equals(file2)) {
                                file4.delete();
                            }
                        }
                    }
                    if (!file2.renameTo(file3)) {
                        throw new IllegalStateException("rename temp car model failed");
                    }
                    this.prefs.edit().putString(PREF_CAR_CONTROL_MODEL_TYPE, CAR_MODEL_TYPE_CUSTOM).putString(PREF_CAR_CONTROL_CUSTOM_MODEL_PATH, file3.getAbsolutePath()).apply();
                    RadioGroup radioGroup = this.rgCarModelSelection;
                    if (radioGroup != null) {
                        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                        int i8 = g4.p.rb_car_custom;
                        if (checkedRadioButtonId != i8) {
                            this.rgCarModelSelection.check(i8);
                        }
                    }
                    updateCustomCarModelBrowseVisibility(CAR_MODEL_TYPE_CUSTOM);
                    requireContext().sendBroadcast(new Intent("com.navi.tool.CAR_CONTROL_CARD_UPDATED"));
                    ToastHelper.show(requireContext(), "自定义车模已更新");
                    fileOutputStream.close();
                    openInputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Exception unused) {
            file2.delete();
            ToastHelper.show(requireContext(), "保存车模图失败");
        }
    }

    private void handleIconChosen(String str, Uri uri) {
        int lastIndexOf;
        ensureIconFolderExists();
        File file = new File(new File(Environment.getExternalStorageDirectory(), "navitool"), "icon");
        if (!file.exists() && !file.mkdirs()) {
            ToastHelper.show(requireContext(), "无法创建图标目录");
            return;
        }
        String queryDisplayName = queryDisplayName(uri);
        File file2 = new File(file, a.a.g(str, (queryDisplayName == null || (lastIndexOf = queryDisplayName.lastIndexOf(46)) < 0 || lastIndexOf >= queryDisplayName.length() + (-1)) ? ".png" : queryDisplayName.substring(lastIndexOf)));
        try {
            InputStream openInputStream = requireContext().getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                ToastHelper.show(requireContext(), "无法读取选中的文件");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read <= 0) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, read);
                }
            }
            fileOutputStream.flush();
            openInputStream.close();
            fileOutputStream.close();
            this.prefs.edit().putString("gesture_panel_app_icon_" + str, file2.getAbsolutePath()).apply();
            PanelAppSettingsAdapter panelAppSettingsAdapter = this.desktopAppSettingsAdapter;
            if (panelAppSettingsAdapter != null) {
                panelAppSettingsAdapter.notifyDataSetChanged();
            }
            requireContext().sendBroadcast(new Intent("com.navi.tool.DESKTOP_APPS_UPDATED"));
            requireContext().sendBroadcast(new Intent("com.navi.tool.DOCK_APPS_UPDATED"));
            ToastHelper.show(requireContext(), "图标已更新");
        } catch (Exception unused) {
            ToastHelper.show(requireContext(), "保存图标失败");
        }
    }

    private boolean hasAnyVideoWallpaperConfigured() {
        return hasSingleVideoWallpaper() || hasAnyWeeklyVideoWallpaper();
    }

    private boolean hasAnyWeeklyVideoWallpaper() {
        for (String str : WEEK_DAYS) {
            if (getVideoWallpaperCount(str) > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean hasRecordAudioPermission() {
        return requireContext().checkSelfPermission("android.permission.RECORD_AUDIO") == 0;
    }

    private boolean hasSingleVideoWallpaper() {
        return getVideoWallpaperCount(null) > 0;
    }

    private boolean hasStoragePermission() {
        boolean isExternalStorageManager;
        if (Build.VERSION.SDK_INT < 30) {
            return g0.g.a(requireContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        }
        isExternalStorageManager = Environment.isExternalStorageManager();
        return isExternalStorageManager;
    }

    private void hideSeatPanelAdvancedSettings() {
        LinearLayout linearLayout = this.layoutHideFrontHeat;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        View view = this.dividerHideFrontHeat;
        if (view != null) {
            view.setVisibility(8);
        }
        LinearLayout linearLayout2 = this.layoutHideRearHeat;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        View view2 = this.dividerHideRearHeat;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        LinearLayout linearLayout3 = this.layoutHideFrontVent;
        if (linearLayout3 != null) {
            linearLayout3.setVisibility(8);
        }
        View view3 = this.dividerHideFrontVent;
        if (view3 != null) {
            view3.setVisibility(8);
        }
        LinearLayout linearLayout4 = this.layoutHideHvacFunctions;
        if (linearLayout4 != null) {
            linearLayout4.setVisibility(8);
        }
        LinearLayout linearLayout5 = this.layoutShowSteeringHeatButton;
        if (linearLayout5 != null) {
            linearLayout5.setVisibility(8);
        }
        View view4 = this.dividerShowSteeringHeatButton;
        if (view4 != null) {
            view4.setVisibility(8);
        }
        LinearLayout linearLayout6 = this.layoutShowFragranceButton;
        if (linearLayout6 != null) {
            linearLayout6.setVisibility(8);
        }
        View view5 = this.dividerShowFragranceButton;
        if (view5 != null) {
            view5.setVisibility(8);
        }
        SwitchMaterial switchMaterial = this.switchSeatComfortCard;
        boolean z6 = switchMaterial != null && switchMaterial.isChecked();
        LinearLayout linearLayout7 = this.layoutDesktopSeatCompat;
        if (linearLayout7 != null) {
            linearLayout7.setVisibility(z6 ? 0 : 8);
        }
        View view6 = this.dividerDesktopSeatCompatTop;
        if (view6 != null) {
            view6.setVisibility(8);
        }
        View view7 = this.dividerDesktopSeatCompatBottom;
        if (view7 != null) {
            view7.setVisibility(8);
        }
        LinearLayout linearLayout8 = this.layoutSeatMemoryCard;
        if (linearLayout8 != null) {
            linearLayout8.setVisibility(8);
        }
        LinearLayout linearLayout9 = this.layoutSeatMemorySaveCard;
        if (linearLayout9 != null) {
            linearLayout9.setVisibility(8);
        }
        LinearLayout linearLayout10 = this.layoutSeatMassageCard;
        if (linearLayout10 != null) {
            linearLayout10.setVisibility(8);
        }
        LinearLayout linearLayout11 = this.layoutHideDriverMassage;
        if (linearLayout11 != null) {
            linearLayout11.setVisibility(8);
        }
        View view8 = this.dividerSeatPanelLegacyBeforeMemory;
        if (view8 != null) {
            view8.setVisibility(8);
        }
        View view9 = this.dividerSeatPanelLegacyBeforeMassage;
        if (view9 != null) {
            view9.setVisibility(8);
        }
        View view10 = this.dividerSeatPanelLegacyAfterMassage;
        if (view10 != null) {
            view10.setVisibility(8);
        }
    }

    private void initAmbientScaleUi() {
        if (this.seekAmbientScale == null || this.textAmbientScale == null) {
            return;
        }
        float f7 = this.prefs.getFloat("desktop_ambient_scale", 2.0f);
        int max = Math.max(0, Math.min(15, Math.round((f7 - 1.5f) * 10.0f)));
        this.seekAmbientScale.setMax(15);
        this.seekAmbientScale.setProgress(max);
        this.textAmbientScale.setText(ambientScaleText(f7));
        this.seekAmbientScale.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
                float f8 = (i7 * 0.1f) + 1.5f;
                DesktopAssistantFragment.this.textAmbientScale.setText(DesktopAssistantFragment.ambientScaleText(f8));
                if (z6) {
                    DesktopAssistantFragment.this.prefs.edit().putFloat("desktop_ambient_scale", f8).apply();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initCoverGradientIntensityUi() {
        if (this.seekCoverGradientIntensity == null || this.textCoverGradientIntensity == null) {
            return;
        }
        int max = Math.max(0, Math.min(100, this.prefs.getInt("desktop_cover_gradient_intensity", 60)));
        this.seekCoverGradientIntensity.setMax(100);
        this.seekCoverGradientIntensity.setProgress(max);
        this.textCoverGradientIntensity.setText(coverGradientIntensityText(max));
        this.seekCoverGradientIntensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.3
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
                DesktopAssistantFragment.this.textCoverGradientIntensity.setText(DesktopAssistantFragment.coverGradientIntensityText(i7));
                if (z6) {
                    DesktopAssistantFragment.this.prefs.edit().putInt("desktop_cover_gradient_intensity", i7).apply();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initIconChooser() {
        this.chooseIconLauncher = registerForActivityResult(new e.f(), new x1(this, 4));
    }

    private void initLogic() {
        int i7 = 0;
        boolean z6 = this.prefs.getBoolean("desktop_assistant_enabled", false);
        this.switchDesktopAssistant.setChecked(z6);
        this.switchHideDock.setChecked(this.prefs.getBoolean("desktop_hide_dock", false));
        a.a.t(this, 27, this.switchHideDock);
        boolean z7 = this.prefs.getBoolean("top_info_area_enabled", false);
        this.switchTopInfoArea.setChecked(z7);
        this.layoutTopInfoSettings.setVisibility(z7 ? 0 : 8);
        this.switchTopInfoArea.setOnCheckedChangeListener(new f2(this, 1));
        a.a.s(this, 11, this.btnSelectTopInfo);
        a.a.s(this, 12, this.btnAdjustTopInfo);
        updateTopInfoCount();
        updateState(z6);
        int i8 = z6 ? 0 : 8;
        this.layoutAutoStart.setVisibility(i8);
        this.layoutRoundCorner.setVisibility(i8);
        this.dividerRoundView.setVisibility(i8);
        this.layoutSettings.setVisibility(i8);
        toggleDesktopIcon(z6);
        updatePermissionStatus();
        this.switchDesktopAssistant.setOnCheckedChangeListener(new f2(this, 4));
        int i9 = this.prefs.getInt("desktop_mode_selection", 0);
        boolean z8 = this.prefs.getBoolean("desktop_clean_mode", false);
        if (i9 == 0) {
            this.radioGroupMode.check(g4.p.radio_mode_floating);
        } else if (z8) {
            this.radioGroupMode.check(g4.p.radio_mode_clean);
        } else {
            this.radioGroupMode.check(g4.p.radio_mode_wallpaper);
        }
        this.dividerWallpaper.setVisibility(0);
        this.btnSelectDesktopApps.setVisibility(0);
        this.btnSetDesktopApps.setVisibility(0);
        this.btnSetDockApps.setVisibility(0);
        this.btnSetWallpaper.setVisibility(0);
        this.btnSetVideoWallpaper.setVisibility(0);
        updateFloatingMapText();
        this.radioGroupMode.setOnCheckedChangeListener(new r2(this, 7));
        a.a.s(this, 13, this.btnSelectDesktopApps);
        a.a.s(this, 14, this.btnSetDesktopApps);
        a.a.s(this, 15, this.btnSetDockApps);
        a.a.s(this, 16, this.btnClearWallpaper);
        int i10 = 3;
        a.a.s(this, 3, this.btnSetVideoWallpaper);
        a.a.s(this, 4, this.btnClearVideoWallpaper);
        this.switchAutoLaunchFloating.setChecked(this.prefs.getBoolean("auto_launch_floating_apps_enabled", false));
        a.a.t(this, 28, this.switchAutoLaunchFloating);
        checkAndSyncAutoLaunchFloatingApps();
        a.a.s(this, 5, this.btnSetFloatingMap);
        a.a.s(this, 6, this.btnSetFloatingMap2);
        a.a.s(this, 7, this.btnClearFloatingMap);
        a.a.s(this, 8, this.btnClearFloatingMap2);
        a.a.s(this, 9, this.btnClearFloatingMusic);
        int i11 = this.prefs.getInt("wallpaper_carousel_interval", 5);
        int i12 = 2;
        this.seekCarouselInterval.setProgress(Math.max(2, Math.min(10, i11)) - 2);
        a.a.v("轮播间隔: ", i11, "秒", this.textCarouselInterval);
        this.seekCarouselInterval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.11
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i13, boolean z9) {
                int i14 = i13 + 2;
                a.a.v("轮播间隔: ", i14, "秒", DesktopAssistantFragment.this.textCarouselInterval);
                DesktopAssistantFragment.this.prefs.edit().putInt("wallpaper_carousel_interval", i14).apply();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        checkWallpaperCount();
        boolean z9 = this.prefs.getBoolean("wallpaper_weekly_enabled", false);
        this.switchWeeklyWallpaper.setChecked(z9);
        this.layoutWeeklySettings.setVisibility(z9 ? 0 : 8);
        a.a.t(this, 29, this.switchWeeklyWallpaper);
        this.switchDesktopWeatherFx.setChecked(this.prefs.getBoolean("desktop_weather_fx_enabled", false));
        this.switchDesktopWeatherFx.setOnCheckedChangeListener(new f2(this, i7));
        boolean z10 = this.prefs.getBoolean("desktop_ambient_fx_enabled", false);
        this.switchDesktopAmbientFx.setChecked(z10);
        updateAmbientScaleVisibility(z10);
        this.switchDesktopAmbientFx.setOnCheckedChangeListener(new f2(this, i12));
        this.switchDesktopCoverGradient.setChecked(this.prefs.getBoolean("desktop_cover_gradient_enabled", false));
        updateCoverGradientIntensityVisibility(this.switchDesktopCoverGradient.isChecked());
        this.switchDesktopCoverGradient.setOnCheckedChangeListener(new f2(this, i10));
        refreshWeeklyButtons();
        initWeeklyVideoWallpaper();
        a.a.s(this, 10, this.btnSetWallpaper);
        initMapCardLogic();
        initWallpaperModeLogic();
        initRandomAndMixedWallpaperLogic();
        initMusicLogic();
        updateFloatingMapText();
        updateFloatingMusicText();
    }

    private void initMapCardLogic() {
        boolean z6 = this.prefs.getBoolean("desktop_map_enabled", false);
        boolean z7 = getFloatingMapDisplayMode() == 1;
        this.switchFloatingMapCard.setChecked(z6);
        this.switchMapCalendarCard.setChecked(z7);
        updateMapCalendarSettingVisibility(z6);
        this.switchFloatingMapCard.setOnCheckedChangeListener(new f2(this, 6));
        this.switchMapCalendarCard.setOnCheckedChangeListener(new f2(this, 7));
        a.a.s(this, 19, this.btnSetFloatingMap);
        a.a.s(this, 20, this.btnSetFloatingMap2);
        a.a.s(this, 21, this.btnSetFloatingMusic);
    }

    private void initMusicLogic() {
        int i7 = 0;
        boolean z6 = this.prefs.getBoolean("desktop_music_enabled", false);
        this.switchFloatingMusic.setChecked(z6);
        this.layoutMusicSettings.setVisibility(z6 ? 0 : 8);
        this.layoutMusicClockWeather.setVisibility(z6 ? 0 : 8);
        a.a.t(this, 0, this.switchFloatingMusic);
        a.a.s(this, 0, this.btnSetFloatingMusic);
        this.switchMusicIsland.setChecked(this.prefs.getBoolean("desktop_music_island_enabled", false));
        a.a.t(this, 19, this.switchMusicIsland);
        boolean z7 = this.prefs.getBoolean("desktop_music_clock_weather_enabled", false);
        int i8 = 1;
        boolean z8 = this.prefs.getBoolean(DesktopAssistantClockWeatherController.PREF_CLOCK_WEATHER_ALERT_ENABLED, true);
        int i9 = this.prefs.getInt(DesktopAssistantClockWeatherController.PREF_CLOCK_WEATHER_LOCATION_MODE, 1);
        this.switchMusicClockWeather.setChecked(z7);
        this.switchMusicWeatherAlert.setChecked(z8);
        updateMusicClockWeatherLocationModeVisibility(z7);
        int i10 = 2;
        if (i9 == 0) {
            this.radioGroupMusicClockWeatherLocationMode.check(g4.p.radio_music_clock_weather_location_county);
        } else if (i9 == 2) {
            this.radioGroupMusicClockWeatherLocationMode.check(g4.p.radio_music_clock_weather_location_town);
        } else {
            this.radioGroupMusicClockWeatherLocationMode.check(g4.p.radio_music_clock_weather_location_county_town);
        }
        a.a.t(this, 21, this.switchMusicClockWeather);
        a.a.t(this, 22, this.switchMusicWeatherAlert);
        this.radioGroupMusicClockWeatherLocationMode.setOnCheckedChangeListener(new r2(this, 5));
        boolean z9 = this.prefs.getBoolean("desktop_music_spectrum_enabled", true);
        this.switchMusicSpectrumEffect.setChecked(z9);
        updateMusicSpectrumSettingVisibility(z9);
        q2 q2Var = new q2(this, 23);
        this.musicSpectrumCheckedChangeListener = q2Var;
        this.switchMusicSpectrumEffect.setOnCheckedChangeListener(q2Var);
        boolean z10 = this.prefs.getBoolean("desktop_music_real_spectrum_enabled", false);
        if (z10 && !hasRecordAudioPermission()) {
            a.a.m(this.prefs, "desktop_music_real_spectrum_enabled", false);
            z10 = false;
        }
        this.switchRealMusicSpectrum.setChecked(z10);
        q2 q2Var2 = new q2(this, 24);
        this.realMusicSpectrumCheckedChangeListener = q2Var2;
        this.switchRealMusicSpectrum.setOnCheckedChangeListener(q2Var2);
        this.switchColorfulMusicSpectrum.setChecked(this.prefs.getBoolean("desktop_music_spectrum_colorful_enabled", true));
        a.a.t(this, 25, this.switchColorfulMusicSpectrum);
        switch (this.prefs.getInt("desktop_music_source", 0)) {
            case 0:
                this.radioGroupMusicSource.check(g4.p.radio_source_qq);
                break;
            case 1:
                this.radioGroupMusicSource.check(g4.p.radio_source_kuwo);
                break;
            case 2:
                this.radioGroupMusicSource.check(g4.p.radio_source_kugou);
                break;
            case 3:
                this.radioGroupMusicSource.check(g4.p.radio_source_netease);
                break;
            case 4:
                this.radioGroupMusicSource.check(g4.p.radio_source_new_kuwo);
                break;
            case 5:
                this.radioGroupMusicSource.check(g4.p.radio_source_luna);
                break;
            case 6:
                this.radioGroupMusicSource.check(g4.p.radio_source_universal);
                break;
        }
        this.radioGroupMusicSource.setOnCheckedChangeListener(new r2(this, 6));
        int i11 = this.prefs.getInt("desktop_lyrics_font_size", 25);
        if (i11 < 20) {
            i11 = 20;
        }
        if (i11 > 30) {
            i11 = 30;
        }
        this.seekLyricsFontSize.setMax(10);
        this.seekLyricsFontSize.setProgress(Math.max(0, Math.min(10, i11 - 20)));
        a.a.v("歌词大小: ", i11, "sp", this.textLyricsFontSize);
        this.seekLyricsFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.14
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i12, boolean z11) {
                int i13 = i12 + 20;
                a.a.v("歌词大小: ", i13, "sp", DesktopAssistantFragment.this.textLyricsFontSize);
                DesktopAssistantFragment.this.prefs.edit().putInt("desktop_lyrics_font_size", i13).apply();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        int i12 = this.prefs.getInt("desktop_lyrics_lines", 5);
        this.seekLyricsLines.setProgress(Math.max(0, Math.min(5, i12 - 3)));
        TextView textView = this.textLyricsLines;
        StringBuilder sb = new StringBuilder("显示行数: ");
        int i13 = 3;
        sb.append(Math.min(8, Math.max(3, i12)));
        sb.append("行");
        textView.setText(sb.toString());
        this.seekLyricsLines.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.15
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i14, boolean z11) {
                int i15 = i14 + 3;
                a.a.v("显示行数: ", i15, "行", DesktopAssistantFragment.this.textLyricsLines);
                DesktopAssistantFragment.this.prefs.edit().putInt("desktop_lyrics_lines", i15).apply();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        int i14 = this.prefs.getInt("desktop_lyrics_color_index", 0);
        if (i14 == 0) {
            this.rgLyricsColor.check(g4.p.rb_color_white);
        } else if (i14 == 1) {
            this.rgLyricsColor.check(g4.p.rb_color_yellow);
        } else if (i14 == 2) {
            this.rgLyricsColor.check(g4.p.rb_color_green);
        } else if (i14 == 3) {
            this.rgLyricsColor.check(g4.p.rb_color_blue);
        } else if (i14 == 4) {
            this.rgLyricsColor.check(g4.p.rb_color_orange);
        } else if (i14 == 5) {
            this.rgLyricsColor.check(g4.p.rb_color_black);
        }
        this.rgLyricsColor.setOnCheckedChangeListener(new r2(this, i7));
        this.switchTransparentBg.setChecked(this.prefs.getBoolean("desktop_transparent_bg", false));
        a.a.t(this, 1, this.switchTransparentBg);
        boolean z11 = this.prefs.getBoolean("desktop_music_show_album_art", false);
        this.switchShowAlbumArt.setChecked(z11);
        this.layoutAlbumArtOptions.setVisibility(z11 ? 0 : 8);
        View view = this.dividerAlbumArtOptions;
        if (view != null) {
            view.setVisibility(z11 ? 0 : 8);
        }
        a.a.t(this, 2, this.switchShowAlbumArt);
        int i15 = this.prefs.getInt("desktop_music_album_art_mode", 0);
        if (i15 == 1) {
            this.rgAlbumArtMode.check(g4.p.rb_album_art_circle);
        } else {
            this.rgAlbumArtMode.check(g4.p.rb_album_art_square);
        }
        this.layoutAlbumArtAnimation.setVisibility(i15 == 1 ? 0 : 8);
        this.rgAlbumArtMode.setOnCheckedChangeListener(new r2(this, i8));
        this.switchAlbumArtAnimation.setChecked(this.prefs.getBoolean("desktop_music_album_art_anim", false));
        a.a.t(this, 3, this.switchAlbumArtAnimation);
        int i16 = this.prefs.getInt("desktop_blur_radius", 10);
        this.seekBlurRadius.setProgress(Math.max(0, Math.min(25, i16)));
        this.textBlurRadius.setText("磨砂强度: " + i16);
        this.seekBlurRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.16
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i17, boolean z12) {
                DesktopAssistantFragment.this.textBlurRadius.setText("磨砂强度: " + i17);
                DesktopAssistantFragment.this.prefs.edit().putInt("desktop_blur_radius", i17).apply();
                Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
                intent.setPackage(DesktopAssistantFragment.this.requireContext().getPackageName());
                DesktopAssistantFragment.this.requireContext().sendBroadcast(intent);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.switchDesktopAssistantAutoStart.setChecked(SharedPreferencesUtils.isDesktopAssistantAutoStartEnabled(requireContext()));
        a.a.t(this, 4, this.switchDesktopAssistantAutoStart);
        boolean z12 = this.prefs.getBoolean("desktop_round_corner_enabled", false);
        if (this.prefs.getBoolean("desktop_cover_gradient_enabled", false)) {
            if (z12) {
                a.a.m(this.prefs, "desktop_round_corner_enabled", false);
            }
            this.switchRoundCorner.setEnabled(false);
            this.switchRoundCorner.setAlpha(0.5f);
            z12 = false;
        }
        this.switchRoundCorner.setChecked(z12);
        a.a.t(this, 5, this.switchRoundCorner);
        this.switchDoubleClickSwap.setChecked(this.prefs.getBoolean("desktop_double_click_swap", false));
        a.a.t(this, 6, this.switchDoubleClickSwap);
        int i17 = this.prefs.getInt("desktop_card_margin", 15);
        if (i17 < 0) {
            i17 = 0;
        }
        if (i17 > 20) {
            i17 = 20;
        }
        a.a.v("卡片间距: ", i17, "dp", this.textCardMargin);
        this.seekCardMargin.setProgress(i17);
        this.seekCardMargin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.17
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i18, boolean z13) {
                a.a.v("卡片间距: ", i18, "dp", DesktopAssistantFragment.this.textCardMargin);
                DesktopAssistantFragment.this.prefs.edit().putInt("desktop_card_margin", i18).apply();
                Intent intent = new Intent("com.navi.tool.DESKTOP_ROUND_CORNER_UPDATED");
                intent.setPackage(DesktopAssistantFragment.this.requireContext().getPackageName());
                DesktopAssistantFragment.this.requireContext().sendBroadcast(intent);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        int i18 = this.prefs.getInt("desktop_night_mode_alpha", 70);
        if (i18 < 0) {
            i18 = 0;
        }
        if (i18 > 100) {
            i18 = 100;
        }
        a.a.v("夜间模式卡片变暗程度: ", i18, "%", this.textNightModeAlpha);
        this.seekNightModeAlpha.setMax(100);
        this.seekNightModeAlpha.setProgress(i18);
        this.seekNightModeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.18
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i19, boolean z13) {
                a.a.v("夜间模式卡片变暗程度: ", i19, "%", DesktopAssistantFragment.this.textNightModeAlpha);
                DesktopAssistantFragment.this.prefs.edit().putInt("desktop_night_mode_alpha", i19).apply();
                Intent intent = new Intent("com.navi.tool.DESKTOP_NIGHT_MODE_ALPHA_UPDATED");
                intent.setPackage(DesktopAssistantFragment.this.requireContext().getPackageName());
                DesktopAssistantFragment.this.requireContext().sendBroadcast(intent);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        boolean z13 = this.prefs.getBoolean("car_control_card_enabled", false);
        this.switchCarControlCard.setChecked(z13);
        this.layoutCarControlSettings.setVisibility(z13 ? 0 : 8);
        View view2 = this.dividerVisualCarModelTop;
        if (view2 != null) {
            view2.setVisibility(z13 ? 0 : 8);
        }
        View view3 = this.dividerVisualCarModelBottom;
        if (view3 != null) {
            view3.setVisibility(z13 ? 0 : 8);
        }
        a.a.t(this, 7, this.switchCarControlCard);
        this.switchSeatComfortCard.setChecked(this.prefs.getBoolean("seat_comfort_panel_enabled", false));
        hideSeatPanelAdvancedSettings();
        a.a.t(this, 8, this.switchSeatComfortCard);
        this.switchDesktopSeatCompat.setChecked(this.prefs.getBoolean("desktop_seat_compat_mode", false));
        a.a.t(this, 9, this.switchDesktopSeatCompat);
        if (this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            this.prefs.edit().putBoolean("fuel_card_enabled", false).putBoolean("car_control_drive_enabled", false).putBoolean("car_control_status_enabled", false).putBoolean("car_control_ev_enabled", false).putBoolean("seat_memory_card_enabled", false).putBoolean("seat_memory_save_card_enabled", false).putBoolean("seat_massage_card_enabled", false).putBoolean("hide_driver_massage_enabled", false).putBoolean("desktop_seat_compat_mode", false).apply();
            SwitchMaterial switchMaterial = this.switchSeatMassageCard;
            if (switchMaterial != null) {
                switchMaterial.setChecked(false);
            }
            SwitchMaterial switchMaterial2 = this.switchHideDriverMassage;
            if (switchMaterial2 != null) {
                switchMaterial2.setChecked(false);
            }
            SwitchMaterial switchMaterial3 = this.switchDesktopSeatCompat;
            if (switchMaterial3 != null) {
                switchMaterial3.setChecked(false);
            }
            LinearLayout linearLayout = this.layoutHideDriverMassage;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
        }
        this.switchFuelCard.setChecked(this.prefs.getBoolean("fuel_card_enabled", false));
        a.a.t(this, 10, this.switchFuelCard);
        this.switchCarControlAc.setChecked(this.prefs.getBoolean("car_control_ac_enabled", false));
        a.a.t(this, 11, this.switchCarControlAc);
        this.switchCarControlSeat.setChecked(this.prefs.getBoolean("seat_comfort_card_enabled", false));
        a.a.t(this, 12, this.switchCarControlSeat);
        this.switchCarControlDoor.setChecked(this.prefs.getBoolean("car_control_door_enabled", false));
        a.a.t(this, 13, this.switchCarControlDoor);
        this.switchCarControlDrive.setChecked(this.prefs.getBoolean("car_control_drive_enabled", false));
        a.a.t(this, 14, this.switchCarControlDrive);
        this.switchCarControlStatus.setChecked(this.prefs.getBoolean("car_control_status_enabled", false));
        a.a.t(this, 15, this.switchCarControlStatus);
        this.switchCarControlEv.setChecked(this.prefs.getBoolean("car_control_ev_enabled", false));
        a.a.t(this, 16, this.switchCarControlEv);
        this.switchVisualCarModel.setChecked(this.prefs.getBoolean("car_control_visual_model_enabled", false));
        this.switchDoorWindowDynamicEffect.setChecked(this.prefs.getBoolean("car_control_door_window_dynamic_effect_enabled", true));
        this.switchDynamicLane.setChecked(this.prefs.getBoolean("car_control_dynamic_lane_enabled", true));
        updateVisualCarModelChildVisibility(this.switchVisualCarModel.isChecked());
        this.layoutCarModelSelection.setVisibility(this.switchVisualCarModel.isChecked() ? 0 : 8);
        a.a.t(this, 17, this.switchVisualCarModel);
        a.a.t(this, 18, this.switchDoorWindowDynamicEffect);
        a.a.t(this, 20, this.switchDynamicLane);
        String string = this.prefs.getString(PREF_CAR_CONTROL_MODEL_TYPE, "yaoguang");
        if ("yaoguang".equals(string)) {
            this.rgCarModelSelection.check(g4.p.rb_car_yaoguang);
        } else if ("tiggo9".equals(string)) {
            this.rgCarModelSelection.check(g4.p.rb_car_tiggo9);
        } else if ("arrizo8".equals(string)) {
            this.rgCarModelSelection.check(g4.p.rb_car_arrizo8);
        } else if ("fengyun_a8".equals(string)) {
            this.rgCarModelSelection.check(g4.p.rb_car_fengyun_a8);
        } else if ("tiggo8pro".equals(string)) {
            this.rgCarModelSelection.check(g4.p.rb_car_tiggo8pro);
        } else if (CAR_MODEL_TYPE_CUSTOM.equals(string)) {
            this.rgCarModelSelection.check(g4.p.rb_car_custom);
        }
        updateCustomCarModelBrowseVisibility(string);
        Button button = this.btnBrowseCustomCarModel;
        if (button != null) {
            a.a.s(this, 1, button);
        }
        this.rgCarModelSelection.setOnCheckedChangeListener(new r2(this, i10));
        int i19 = this.prefs.getInt("car_control_property_text_color_index", 0);
        if (i19 == 0) {
            this.rgCarControlPropertyTextColor.check(g4.p.rb_cc_property_color_white);
        } else if (i19 == 1) {
            this.rgCarControlPropertyTextColor.check(g4.p.rb_cc_property_color_yellow);
        } else if (i19 == 2) {
            this.rgCarControlPropertyTextColor.check(g4.p.rb_cc_property_color_green);
        } else if (i19 == 3) {
            this.rgCarControlPropertyTextColor.check(g4.p.rb_cc_property_color_blue);
        } else if (i19 == 4) {
            this.rgCarControlPropertyTextColor.check(g4.p.rb_cc_property_color_orange);
        } else if (i19 == 5) {
            this.rgCarControlPropertyTextColor.check(g4.p.rb_cc_property_color_black);
        }
        this.rgCarControlPropertyTextColor.setOnCheckedChangeListener(new r2(this, i13));
        int i20 = this.prefs.getInt("car_control_text_color_index", 0);
        if (i20 == 0) {
            this.rgCarControlTextColor.check(g4.p.rb_cc_color_white);
        } else if (i20 == 1) {
            this.rgCarControlTextColor.check(g4.p.rb_cc_color_yellow);
        } else if (i20 == 2) {
            this.rgCarControlTextColor.check(g4.p.rb_cc_color_green);
        } else if (i20 == 3) {
            this.rgCarControlTextColor.check(g4.p.rb_cc_color_blue);
        } else if (i20 == 4) {
            this.rgCarControlTextColor.check(g4.p.rb_cc_color_orange);
        } else if (i20 == 5) {
            this.rgCarControlTextColor.check(g4.p.rb_cc_color_black);
        }
        this.rgCarControlTextColor.setOnCheckedChangeListener(new r2(this, 4));
        int i21 = this.prefs.getInt("car_control_text_size", 24);
        this.seekCarControlFontSize.setProgress(Math.max(0, Math.min(10, i21 - 20)));
        a.a.v("车控信息字体大小: ", i21, "sp", this.textCarControlFontSize);
        this.seekCarControlFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.19
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i22, boolean z14) {
                int i23 = i22 + 20;
                a.a.v("车控信息字体大小: ", i23, "sp", DesktopAssistantFragment.this.textCarControlFontSize);
                DesktopAssistantFragment.this.prefs.edit().putInt("car_control_text_size", i23).apply();
                a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", DesktopAssistantFragment.this.requireContext());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        int i22 = this.prefs.getInt("car_control_card_alpha", 90);
        this.seekCarControlAlpha.setProgress(i22);
        a.a.v("车控信息卡片背景透明度: ", i22, "%", this.textCarControlAlpha);
        this.seekCarControlAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.20
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i23, boolean z14) {
                a.a.v("车控信息卡片背景透明度: ", i23, "%", DesktopAssistantFragment.this.textCarControlAlpha);
                DesktopAssistantFragment.this.prefs.edit().putInt("car_control_card_alpha", i23).apply();
                a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", DesktopAssistantFragment.this.requireContext());
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initRandomAndMixedWallpaperLogic() {
        SwitchMaterial switchMaterial = this.switchRandomWallpaper;
        if (switchMaterial != null) {
            switchMaterial.setChecked(this.prefs.getBoolean("wallpaper_random_order", false));
            this.switchRandomWallpaper.setOnCheckedChangeListener(new f2(this, 8));
        }
        SwitchMaterial switchMaterial2 = this.switchMixedWallpaper;
        if (switchMaterial2 != null) {
            switchMaterial2.setChecked(this.prefs.getBoolean("wallpaper_mixed_play", false));
            this.switchMixedWallpaper.setOnCheckedChangeListener(new f2(this, 9));
        }
        updateMixedWallpaperAvailability();
    }

    private void initScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        int i7 = displayMetrics.widthPixels;
        this.screenWidth = i7;
        int i8 = displayMetrics.heightPixels;
        this.screenHeight = i8;
        if (i7 < i8) {
            this.screenWidth = i8;
            this.screenHeight = i7;
        }
    }

    private void initViews(View view) {
        this.switchDesktopAssistant = (SwitchMaterial) view.findViewById(g4.p.switch_desktop_assistant);
        this.permissionStatusContainer = (MaterialCardView) view.findViewById(g4.p.permission_status_container);
        this.textOverlayPermissionStatus = (TextView) view.findViewById(g4.p.text_overlay_permission_status);
        this.textStoragePermissionStatus = (TextView) view.findViewById(g4.p.text_storage_permission_status);
        this.layoutSettings = (LinearLayout) view.findViewById(g4.p.layout_settings);
        this.radioGroupMode = (RadioGroup) view.findViewById(g4.p.radio_group_mode);
        this.dividerWallpaper = view.findViewById(g4.p.divider_wallpaper);
        this.btnSelectDesktopApps = (Button) view.findViewById(g4.p.btn_select_desktop_apps);
        this.btnSetDesktopApps = (Button) view.findViewById(g4.p.btn_set_desktop_apps);
        this.btnSetDockApps = (Button) view.findViewById(g4.p.btn_set_dock_apps);
        this.btnSetWallpaper = (Button) view.findViewById(g4.p.btn_set_wallpaper);
        this.btnClearWallpaper = (Button) view.findViewById(g4.p.btn_clear_wallpaper);
        this.textWallpaperCount = (TextView) view.findViewById(g4.p.text_wallpaper_count);
        this.btnSetVideoWallpaper = (Button) view.findViewById(g4.p.btn_set_video_wallpaper);
        this.btnClearVideoWallpaper = (Button) view.findViewById(g4.p.btn_clear_video_wallpaper);
        this.textVideoWallpaperStatus = (TextView) view.findViewById(g4.p.text_video_wallpaper_status);
        this.layoutWeeklyVideoSection = (LinearLayout) view.findViewById(g4.p.layout_weekly_video_section);
        this.layoutAppSelection = (LinearLayout) view.findViewById(g4.p.layout_app_selection);
        this.btnSetFloatingMap = (Button) view.findViewById(g4.p.btn_set_floating_map);
        this.btnClearFloatingMap = (Button) view.findViewById(g4.p.btn_clear_floating_map);
        this.textFloatingMapCurrent = (TextView) view.findViewById(g4.p.text_floating_map_current);
        this.btnSetFloatingMap2 = (Button) view.findViewById(g4.p.btn_set_floating_map_2);
        this.btnClearFloatingMap2 = (Button) view.findViewById(g4.p.btn_clear_floating_map_2);
        this.textFloatingMap2Current = (TextView) view.findViewById(g4.p.text_floating_map_2_current);
        this.btnSetFloatingMusic = (Button) view.findViewById(g4.p.btn_set_floating_music);
        this.btnClearFloatingMusic = (Button) view.findViewById(g4.p.btn_clear_floating_music);
        this.textFloatingMusicCurrent = (TextView) view.findViewById(g4.p.text_floating_music_current);
        this.layoutWallpaperCarousel = (LinearLayout) view.findViewById(g4.p.layout_wallpaper_carousel);
        this.textCarouselInterval = (TextView) view.findViewById(g4.p.text_carousel_interval);
        this.seekCarouselInterval = (SeekBar) view.findViewById(g4.p.seek_carousel_interval);
        this.switchWeeklyWallpaper = (SwitchMaterial) view.findViewById(g4.p.switch_weekly_wallpaper);
        this.layoutWeeklySettings = (HorizontalScrollView) view.findViewById(g4.p.layout_weekly_settings);
        this.switchDesktopWeatherFx = (SwitchMaterial) view.findViewById(g4.p.switch_desktop_weather_fx);
        this.switchDesktopAmbientFx = (SwitchMaterial) view.findViewById(g4.p.switch_desktop_ambient_fx);
        this.switchDesktopCoverGradient = (SwitchMaterial) view.findViewById(g4.p.switch_desktop_cover_gradient);
        this.layoutCoverGradientIntensity = view.findViewById(g4.p.layout_cover_gradient_intensity);
        this.textCoverGradientIntensity = (TextView) view.findViewById(g4.p.text_cover_gradient_intensity);
        this.seekCoverGradientIntensity = (SeekBar) view.findViewById(g4.p.seek_cover_gradient_intensity);
        initCoverGradientIntensityUi();
        this.layoutAmbientScale = view.findViewById(g4.p.layout_ambient_scale);
        this.seekAmbientScale = (SeekBar) view.findViewById(g4.p.seek_ambient_scale);
        this.textAmbientScale = (TextView) view.findViewById(g4.p.text_ambient_scale);
        initAmbientScaleUi();
        View findViewById = view.findViewById(g4.p.layout_ambient_fx_text);
        if (findViewById == null) {
            findViewById = view.findViewById(g4.p.text_ambient_fx_label);
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new s2(this, 18));
            findViewById.setOnLongClickListener(new g0(3, this));
        }
        this.btnWallMon = (Button) view.findViewById(g4.p.btn_wall_mon);
        this.btnWallTue = (Button) view.findViewById(g4.p.btn_wall_tue);
        this.btnWallWed = (Button) view.findViewById(g4.p.btn_wall_wed);
        this.btnWallThu = (Button) view.findViewById(g4.p.btn_wall_thu);
        this.btnWallFri = (Button) view.findViewById(g4.p.btn_wall_fri);
        this.btnWallSat = (Button) view.findViewById(g4.p.btn_wall_sat);
        this.btnWallSun = (Button) view.findViewById(g4.p.btn_wall_sun);
        this.btnClearWeekly = (Button) view.findViewById(g4.p.btn_clear_weekly);
        this.switchWeeklyVideoWallpaper = (SwitchMaterial) view.findViewById(g4.p.switch_weekly_video_wallpaper);
        this.layoutWeeklyVideoSettings = (HorizontalScrollView) view.findViewById(g4.p.layout_weekly_video_settings);
        this.btnVideoWallMon = (Button) view.findViewById(g4.p.btn_video_wall_mon);
        this.btnVideoWallTue = (Button) view.findViewById(g4.p.btn_video_wall_tue);
        this.btnVideoWallWed = (Button) view.findViewById(g4.p.btn_video_wall_wed);
        this.btnVideoWallThu = (Button) view.findViewById(g4.p.btn_video_wall_thu);
        this.btnVideoWallFri = (Button) view.findViewById(g4.p.btn_video_wall_fri);
        this.btnVideoWallSat = (Button) view.findViewById(g4.p.btn_video_wall_sat);
        this.btnVideoWallSun = (Button) view.findViewById(g4.p.btn_video_wall_sun);
        this.btnClearWeeklyVideo = (Button) view.findViewById(g4.p.btn_clear_weekly_video);
        this.switchFloatingMapCard = (SwitchMaterial) view.findViewById(g4.p.switch_floating_map_card);
        this.layoutMapCalendarSettings = (LinearLayout) view.findViewById(g4.p.layout_map_calendar_settings);
        this.switchMapCalendarCard = (SwitchMaterial) view.findViewById(g4.p.switch_map_calendar_card);
        this.switchWallpaperMode = (SwitchMaterial) view.findViewById(g4.p.switch_wallpaper_mode);
        this.switchRandomWallpaper = (SwitchMaterial) view.findViewById(g4.p.switch_random_wallpaper);
        this.switchMixedWallpaper = (SwitchMaterial) view.findViewById(g4.p.switch_mixed_wallpaper);
        this.textMixedWallpaperDesc = (TextView) view.findViewById(g4.p.text_mixed_wallpaper_desc);
        this.dividerAutoLaunchFloating = view.findViewById(g4.p.divider_auto_launch_floating);
        this.layoutAutoLaunchFloating = (LinearLayout) view.findViewById(g4.p.layout_auto_launch_floating);
        this.switchAutoLaunchFloating = (SwitchMaterial) view.findViewById(g4.p.switch_auto_launch_floating);
        this.switchFloatingMusic = (SwitchMaterial) view.findViewById(g4.p.switch_floating_music);
        this.layoutMusicSettings = (LinearLayout) view.findViewById(g4.p.layout_music_settings);
        this.radioGroupMusicSource = (RadioGroup) view.findViewById(g4.p.radio_group_music_source);
        this.textLyricsFontSize = (TextView) view.findViewById(g4.p.text_lyrics_font_size);
        this.seekLyricsFontSize = (SeekBar) view.findViewById(g4.p.seek_lyrics_font_size);
        this.textLyricsLines = (TextView) view.findViewById(g4.p.text_lyrics_lines);
        this.seekLyricsLines = (SeekBar) view.findViewById(g4.p.seek_lyrics_lines);
        this.rgLyricsColor = (RadioGroup) view.findViewById(g4.p.rg_lyrics_color);
        this.switchTransparentBg = (SwitchMaterial) view.findViewById(g4.p.switch_transparent_bg);
        this.textBlurRadius = (TextView) view.findViewById(g4.p.text_blur_radius);
        this.seekBlurRadius = (SeekBar) view.findViewById(g4.p.seek_blur_radius);
        this.switchMusicSpectrumEffect = (SwitchMaterial) view.findViewById(g4.p.switch_music_spectrum_effect);
        this.layoutMusicIsland = (LinearLayout) view.findViewById(g4.p.layout_music_island);
        this.switchMusicIsland = (SwitchMaterial) view.findViewById(g4.p.switch_music_island);
        this.layoutMusicClockWeather = (LinearLayout) view.findViewById(g4.p.layout_music_clock_weather);
        this.switchMusicClockWeather = (SwitchMaterial) view.findViewById(g4.p.switch_music_clock_weather);
        this.switchMusicWeatherAlert = (SwitchMaterial) view.findViewById(g4.p.switch_music_weather_alert);
        this.layoutMusicClockWeatherLocationMode = (LinearLayout) view.findViewById(g4.p.layout_music_clock_weather_location_mode);
        this.radioGroupMusicClockWeatherLocationMode = (RadioGroup) view.findViewById(g4.p.radio_group_music_clock_weather_location_mode);
        this.dividerMusicSpectrumReal = view.findViewById(g4.p.divider_music_spectrum_real);
        this.layoutMusicSpectrumReal = (LinearLayout) view.findViewById(g4.p.layout_music_spectrum_real);
        this.switchRealMusicSpectrum = (SwitchMaterial) view.findViewById(g4.p.switch_real_music_spectrum);
        this.dividerMusicSpectrumColorful = view.findViewById(g4.p.divider_music_spectrum_colorful);
        this.layoutMusicSpectrumColorful = (LinearLayout) view.findViewById(g4.p.layout_music_spectrum_colorful);
        this.switchColorfulMusicSpectrum = (SwitchMaterial) view.findViewById(g4.p.switch_colorful_music_spectrum);
        this.switchShowAlbumArt = (SwitchMaterial) view.findViewById(g4.p.switch_show_album_art);
        this.dividerAlbumArtOptions = view.findViewById(g4.p.divider_album_art_options);
        this.layoutAlbumArtOptions = (LinearLayout) view.findViewById(g4.p.layout_album_art_options);
        this.rgAlbumArtMode = (RadioGroup) view.findViewById(g4.p.rg_album_art_mode);
        this.layoutAlbumArtAnimation = (LinearLayout) view.findViewById(g4.p.layout_album_art_animation);
        this.switchAlbumArtAnimation = (SwitchMaterial) view.findViewById(g4.p.switch_album_art_animation);
        this.switchDesktopAssistantAutoStart = (SwitchMaterial) view.findViewById(g4.p.switch_desktop_assistant_auto_start);
        this.switchHideDock = (SwitchMaterial) view.findViewById(g4.p.switch_hide_dock);
        this.layoutAutoStart = (LinearLayout) view.findViewById(g4.p.layout_auto_start);
        this.dividerAutoStart = view.findViewById(g4.p.divider_auto_start);
        this.dividerRoundView = view.findViewById(g4.p.divider_round_view);
        this.layoutRoundCorner = (LinearLayout) view.findViewById(g4.p.layout_round_corner);
        this.switchRoundCorner = (SwitchMaterial) view.findViewById(g4.p.switch_round_corner);
        this.switchDoubleClickSwap = (SwitchMaterial) view.findViewById(g4.p.switch_double_click_swap);
        this.dividerCardMargin = view.findViewById(g4.p.divider_card_margin);
        this.layoutCardMargin = (LinearLayout) view.findViewById(g4.p.layout_card_margin);
        this.textCardMargin = (TextView) view.findViewById(g4.p.text_card_margin);
        this.seekCardMargin = (SeekBar) view.findViewById(g4.p.seek_card_margin);
        this.dividerNightModeAlpha = view.findViewById(g4.p.divider_night_mode_alpha);
        this.layoutNightModeAlpha = (LinearLayout) view.findViewById(g4.p.layout_night_mode_alpha);
        this.textNightModeAlpha = (TextView) view.findViewById(g4.p.text_night_mode_alpha);
        this.seekNightModeAlpha = (SeekBar) view.findViewById(g4.p.seek_night_mode_alpha);
        this.switchCarControlCard = (SwitchMaterial) view.findViewById(g4.p.switch_car_control_card);
        this.dividerVisualCarModelTop = view.findViewById(g4.p.divider_visual_car_model_top);
        this.dividerVisualCarModelBottom = view.findViewById(g4.p.divider_visual_car_model_bottom);
        this.switchSeatComfortCard = (SwitchMaterial) view.findViewById(g4.p.switch_seat_comfort_card);
        this.dividerDesktopSeatCompatTop = view.findViewById(g4.p.divider_desktop_seat_compat_top);
        this.switchDesktopSeatCompat = (SwitchMaterial) view.findViewById(g4.p.switch_desktop_seat_compat);
        this.layoutDesktopSeatCompat = (LinearLayout) view.findViewById(g4.p.layout_desktop_seat_compat);
        this.dividerDesktopSeatCompatBottom = view.findViewById(g4.p.divider_desktop_seat_compat_bottom);
        this.switchSeatMemoryCard = (SwitchMaterial) view.findViewById(g4.p.switch_seat_memory_card);
        this.layoutSeatMemoryCard = (LinearLayout) view.findViewById(g4.p.layout_seat_memory_card);
        this.switchSeatMemorySaveCard = (SwitchMaterial) view.findViewById(g4.p.switch_seat_memory_save_card);
        this.layoutSeatMemorySaveCard = (LinearLayout) view.findViewById(g4.p.layout_seat_memory_save_card);
        this.switchSeatMassageCard = (SwitchMaterial) view.findViewById(g4.p.switch_seat_massage_card);
        this.layoutSeatMassageCard = (LinearLayout) view.findViewById(g4.p.layout_seat_massage_card);
        this.switchHideDriverMassage = (SwitchMaterial) view.findViewById(g4.p.switch_hide_driver_massage);
        this.layoutHideDriverMassage = (LinearLayout) view.findViewById(g4.p.layout_hide_driver_massage);
        this.switchHideFrontHeat = (SwitchMaterial) view.findViewById(g4.p.switch_hide_front_heat);
        this.layoutHideFrontHeat = (LinearLayout) view.findViewById(g4.p.layout_hide_front_heat);
        this.dividerHideFrontHeat = view.findViewById(g4.p.divider_hide_front_heat);
        this.switchHideRearHeat = (SwitchMaterial) view.findViewById(g4.p.switch_hide_rear_heat);
        this.layoutHideRearHeat = (LinearLayout) view.findViewById(g4.p.layout_hide_rear_heat);
        this.dividerHideRearHeat = view.findViewById(g4.p.divider_hide_rear_heat);
        this.switchHideFrontVent = (SwitchMaterial) view.findViewById(g4.p.switch_hide_front_vent);
        this.layoutHideFrontVent = (LinearLayout) view.findViewById(g4.p.layout_hide_front_vent);
        this.dividerHideFrontVent = view.findViewById(g4.p.divider_hide_front_vent);
        this.switchHideHvacFunctions = (SwitchMaterial) view.findViewById(g4.p.switch_hide_hvac_functions);
        this.layoutHideHvacFunctions = (LinearLayout) view.findViewById(g4.p.layout_hide_hvac_functions);
        this.dividerShowSteeringHeatButton = view.findViewById(g4.p.divider_show_steering_heat_button);
        this.switchShowSteeringHeatButton = (SwitchMaterial) view.findViewById(g4.p.switch_show_steering_heat_button);
        this.layoutShowSteeringHeatButton = (LinearLayout) view.findViewById(g4.p.layout_show_steering_heat_button);
        this.dividerShowFragranceButton = view.findViewById(g4.p.divider_show_fragrance_button);
        this.switchShowFragranceButton = (SwitchMaterial) view.findViewById(g4.p.switch_show_fragrance_button);
        this.layoutShowFragranceButton = (LinearLayout) view.findViewById(g4.p.layout_show_fragrance_button);
        this.dividerSeatPanelLegacyBeforeMemory = view.findViewById(g4.p.divider_seat_panel_legacy_before_memory);
        this.dividerSeatPanelLegacyBeforeMassage = view.findViewById(g4.p.divider_seat_panel_legacy_before_massage);
        this.dividerSeatPanelLegacyAfterMassage = view.findViewById(g4.p.divider_seat_panel_legacy_after_massage);
        this.switchFuelCard = (SwitchMaterial) view.findViewById(g4.p.switch_fuel_card);
        this.layoutCarControlSettings = (LinearLayout) view.findViewById(g4.p.layout_car_control_settings);
        this.switchCarControlAc = (SwitchMaterial) view.findViewById(g4.p.switch_car_control_ac);
        this.switchCarControlSeat = (SwitchMaterial) view.findViewById(g4.p.switch_car_control_seat);
        this.switchCarControlDoor = (SwitchMaterial) view.findViewById(g4.p.switch_car_control_door);
        this.switchCarControlDrive = (SwitchMaterial) view.findViewById(g4.p.switch_car_control_drive);
        this.switchCarControlStatus = (SwitchMaterial) view.findViewById(g4.p.switch_car_control_status);
        this.switchCarControlEv = (SwitchMaterial) view.findViewById(g4.p.switch_car_control_ev);
        this.switchVisualCarModel = (SwitchMaterial) view.findViewById(g4.p.switch_visual_car_model);
        this.switchDoorWindowDynamicEffect = (SwitchMaterial) view.findViewById(g4.p.switch_door_window_dynamic_effect);
        this.switchDynamicLane = (SwitchMaterial) view.findViewById(g4.p.switch_dynamic_lane);
        this.dividerDynamicLaneTop = view.findViewById(g4.p.divider_dynamic_lane_top);
        this.dividerDoorWindowDynamicEffectTop = view.findViewById(g4.p.divider_door_window_dynamic_effect_top);
        this.layoutDynamicLaneSettings = (LinearLayout) view.findViewById(g4.p.layout_dynamic_lane_settings);
        this.layoutDoorWindowDynamicEffect = (LinearLayout) view.findViewById(g4.p.layout_door_window_dynamic_effect);
        this.layoutCarModelSelection = (LinearLayout) view.findViewById(g4.p.layout_car_model_selection);
        this.rgCarModelSelection = (RadioGroup) view.findViewById(g4.p.rg_car_model_selection);
        this.btnBrowseCustomCarModel = (Button) view.findViewById(g4.p.btn_browse_custom_car_model);
        this.rgCarControlPropertyTextColor = (RadioGroup) view.findViewById(g4.p.rg_car_control_property_text_color);
        this.rgCarControlTextColor = (RadioGroup) view.findViewById(g4.p.rg_car_control_text_color);
        this.seekCarControlAlpha = (SeekBar) view.findViewById(g4.p.seek_car_control_alpha);
        this.textCarControlAlpha = (TextView) view.findViewById(g4.p.text_car_control_alpha);
        this.seekCarControlFontSize = (SeekBar) view.findViewById(g4.p.seek_car_control_font_size);
        this.textCarControlFontSize = (TextView) view.findViewById(g4.p.text_car_control_font_size);
        this.switchTopInfoArea = (SwitchMaterial) view.findViewById(g4.p.switch_top_info_area);
        this.layoutTopInfoSettings = (RelativeLayout) view.findViewById(g4.p.layout_top_info_settings);
        this.btnSelectTopInfo = (Button) view.findViewById(g4.p.btn_select_top_info);
        this.btnAdjustTopInfo = (Button) view.findViewById(g4.p.btn_adjust_top_info);
        this.textTopInfoCount = (TextView) view.findViewById(g4.p.text_top_info_count);
    }

    private void initWallpaperModeLogic() {
        this.switchWallpaperMode.setChecked(this.prefs.getInt("desktop_wallpaper_display_mode", 0) == 1);
        a.a.t(this, 26, this.switchWallpaperMode);
    }

    private void initWeeklyVideoWallpaper() {
        boolean z6 = this.prefs.getBoolean("wallpaper_weekly_video_enabled", false);
        this.switchWeeklyVideoWallpaper.setChecked(z6);
        this.layoutWeeklyVideoSettings.setVisibility(z6 ? 0 : 8);
        this.switchWeeklyVideoWallpaper.setOnCheckedChangeListener(new f2(this, 5));
        updateVideoWallpaperUi();
        refreshWeeklyVideoButtons();
    }

    private boolean isSupportedVideoUri(Uri uri) {
        String type = requireContext().getContentResolver().getType(uri);
        if ("video/mp4".equalsIgnoreCase(type) || "application/mp4".equalsIgnoreCase(type)) {
            return true;
        }
        String displayName = getDisplayName(uri);
        if (displayName == null) {
            return false;
        }
        return displayName.toLowerCase(Locale.ROOT).endsWith(".mp4");
    }

    private boolean isSystemApp(Context context, String str) {
        try {
            int i7 = context.getPackageManager().getApplicationInfo(str, 0).flags;
            return ((i7 & 1) == 0 && (i7 & 128) == 0) ? false : true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isSystemApp(String str) {
        if (getContext() != null) {
            return isSystemApp(getContext(), str);
        }
        return false;
    }

    private boolean isTopInfoSupportedInCurrentMode(String str) {
        return DesktopAssistantCarControlHelper.isTopInfoSupportedInCurrentMode(this.prefs, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$autoCropAndSave$143(Exception exc) {
        ToastHelper.show(requireContext(), "自动裁剪失败，跳过: " + exc.getMessage());
        processNextWallpaper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$autoCropAndSave$144(Uri uri) {
        File file;
        String str;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i7 = 1;
            options.inJustDecodeBounds = true;
            InputStream openInputStream = requireContext().getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(openInputStream, null, options);
            if (openInputStream != null) {
                openInputStream.close();
            }
            int i8 = options.outWidth;
            int i9 = options.outHeight;
            Math.max(this.screenWidth / i8, this.screenHeight / i9);
            options.inJustDecodeBounds = false;
            options.inSampleSize = 1;
            while (true) {
                int i10 = options.inSampleSize;
                if (i8 / i10 <= this.screenWidth * 2 || i9 / i10 <= this.screenHeight * 2) {
                    break;
                } else {
                    options.inSampleSize = i10 * 2;
                }
            }
            InputStream openInputStream2 = requireContext().getContentResolver().openInputStream(uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream2, null, options);
            if (openInputStream2 != null) {
                openInputStream2.close();
            }
            if (decodeStream == null) {
                throw new Exception("图片解码失败");
            }
            Matrix matrix = new Matrix();
            float max = Math.max(this.screenWidth / decodeStream.getWidth(), this.screenHeight / decodeStream.getHeight());
            matrix.postScale(max, max);
            Bitmap createBitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, true);
            if (decodeStream != createBitmap) {
                decodeStream.recycle();
            }
            Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, Math.max(0, (createBitmap.getWidth() - this.screenWidth) / 2), Math.max(0, (createBitmap.getHeight() - this.screenHeight) / 2), this.screenWidth, this.screenHeight);
            if (createBitmap != createBitmap2) {
                createBitmap.recycle();
            }
            if (this.currentEditingDay != null) {
                file = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper/" + this.currentEditingDay);
            } else {
                file = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper");
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            if (this.processedWallpaperCount == 0) {
                str = "desktop_wallpaper.png";
            } else {
                str = "desktop_wallpaper" + (this.processedWallpaperCount + 1) + ".png";
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, str));
            createBitmap2.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            createBitmap2.recycle();
            this.processedWallpaperCount++;
            if (getActivity() != null) {
                getActivity().runOnUiThread(new g2(this, i7));
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            if (getActivity() != null) {
                getActivity().runOnUiThread(new z0(11, this, e7));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkResolutionAndProcess$140(androidx.appcompat.app.o[] oVarArr, Uri uri, View view) {
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
        saveSingleWallpaper(uri);
        processNextWallpaper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkResolutionAndProcess$141(androidx.appcompat.app.o[] oVarArr, Uri uri, View view) {
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
        startCrop(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkResolutionAndProcess$142(androidx.appcompat.app.o[] oVarArr, Uri uri, View view) {
        androidx.appcompat.app.o oVar = oVarArr[0];
        if (oVar != null) {
            oVar.dismiss();
        }
        this.isAutoCropAll = true;
        autoCropAndSave(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$checkWallpaperCount$136(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$checkWallpaperCount$137(File file, String str) {
        return str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$generateManagedVideoThumbnail$76(int i7, String str, ImageView imageView, Bitmap bitmap) {
        if (i7 == this.videoManageThumbSessionToken.get() && str.equals(imageView.getTag())) {
            imageView.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$generateManagedVideoThumbnail$77(int i7, File file, String str, File file2, String str2, ImageView imageView) {
        if (i7 == this.videoManageThumbSessionToken.get() && !Thread.currentThread().isInterrupted()) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
            } catch (Exception unused) {
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception unused2) {
                }
                throw th;
            }
            if (Thread.currentThread().isInterrupted()) {
                try {
                    mediaMetadataRetriever.release();
                    return;
                } catch (Exception unused3) {
                    return;
                }
            }
            Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(0L, 2);
            if (frameAtTime == null) {
                try {
                    mediaMetadataRetriever.release();
                    return;
                } catch (Exception unused4) {
                    return;
                }
            }
            if (Thread.currentThread().isInterrupted()) {
                frameAtTime.recycle();
                try {
                    mediaMetadataRetriever.release();
                    return;
                } catch (Exception unused5) {
                    return;
                }
            }
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(frameAtTime, z1.c.AND_LONG, z1.c.INVOKE_INTERFACE_RANGE, true);
            if (createScaledBitmap != frameAtTime) {
                frameAtTime.recycle();
            }
            if (!Thread.currentThread().isInterrupted() && i7 == this.videoManageThumbSessionToken.get()) {
                this.videoManageThumbnailCache.put(str, createScaledBitmap);
                if (Thread.currentThread().isInterrupted()) {
                    this.videoManageThumbnailCache.remove(str);
                    createScaledBitmap.recycle();
                    try {
                        mediaMetadataRetriever.release();
                        return;
                    } catch (Exception unused6) {
                        return;
                    }
                }
                saveManagedThumbnailFile(file2, createScaledBitmap);
                androidx.fragment.app.d0 activity = getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new t2(this, i7, str2, imageView, createScaledBitmap, 0));
                }
                try {
                    mediaMetadataRetriever.release();
                    return;
                } catch (Exception unused7) {
                    return;
                }
            }
            createScaledBitmap.recycle();
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getAllLaunchableApps$11(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getVideoWallpaperFiles$69(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".mp4");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getVideoWallpaperFiles$70(File file, File file2) {
        String name = file.getName();
        String name2 = file2.getName();
        return name.length() != name2.length() ? name.length() - name2.length() : name.compareTo(name2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getVideoWallpaperMaxIndex$71(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".mp4");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$initIconChooser$6(ActivityResult activityResult) {
        if (activityResult.f648a != -1) {
            this.pendingIconChangePackageName = null;
            return;
        }
        Intent intent = activityResult.f649b;
        if (intent == null) {
            this.pendingIconChangePackageName = null;
            return;
        }
        Uri data = intent.getData();
        String str = this.pendingIconChangePackageName;
        this.pendingIconChangePackageName = null;
        if (data == null || str == null) {
            return;
        }
        handleIconChosen(str, data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$32(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("desktop_hide_dock", z6).apply();
        a.a.w("com.navi.tool.DESKTOP_DOCK_VISIBILITY_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$33(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "top_info_area_enabled", z6);
        this.layoutTopInfoSettings.setVisibility(z6 ? 0 : 8);
        a.a.w("com.navi.tool.TOP_INFO_AREA_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$34(View view) {
        showSelectTopInfoDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$35(View view) {
        showAdjustTopInfoDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$36(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_assistant_enabled", z6);
        updateState(z6);
        if (!z6 && this.switchDesktopAssistantAutoStart.isChecked()) {
            this.switchDesktopAssistantAutoStart.setChecked(false);
        }
        toggleDesktopIcon(z6);
        int i7 = z6 ? 0 : 8;
        this.layoutAutoStart.setVisibility(i7);
        this.layoutRoundCorner.setVisibility(i7);
        this.dividerRoundView.setVisibility(i7);
        LinearLayout linearLayout = this.layoutCardMargin;
        if (linearLayout != null) {
            linearLayout.setVisibility(i7);
        }
        View view = this.dividerCardMargin;
        if (view != null) {
            view.setVisibility(i7);
        }
        this.layoutSettings.setVisibility(i7);
        updatePermissionStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$37(RadioGroup radioGroup, int i7) {
        int i8;
        boolean z6;
        if (i7 != g4.p.radio_mode_floating) {
            i8 = 1;
            if (i7 == g4.p.radio_mode_wallpaper) {
                z6 = false;
            } else if (i7 == g4.p.radio_mode_clean) {
                z6 = true;
            }
            this.prefs.edit().putInt("desktop_mode_selection", i8).putBoolean("desktop_clean_mode", z6).apply();
            this.dividerWallpaper.setVisibility(0);
            this.btnSelectDesktopApps.setVisibility(0);
            this.btnSetDesktopApps.setVisibility(0);
            this.btnSetDockApps.setVisibility(0);
            this.btnSetWallpaper.setVisibility(0);
            this.btnSetVideoWallpaper.setVisibility(0);
            checkWallpaperCount();
        }
        z6 = false;
        i8 = 0;
        this.prefs.edit().putInt("desktop_mode_selection", i8).putBoolean("desktop_clean_mode", z6).apply();
        this.dividerWallpaper.setVisibility(0);
        this.btnSelectDesktopApps.setVisibility(0);
        this.btnSetDesktopApps.setVisibility(0);
        this.btnSetDockApps.setVisibility(0);
        this.btnSetWallpaper.setVisibility(0);
        this.btnSetVideoWallpaper.setVisibility(0);
        checkWallpaperCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$38(View view) {
        showSelectSystemAppsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$39(View view) {
        showSetDesktopAppsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$40(View view) {
        showSetDockAppsDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initLogic$41(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$42(File[] fileArr, DialogInterface dialogInterface, int i7) {
        boolean z6 = false;
        for (File file : fileArr) {
            if (file.delete()) {
                z6 = true;
            }
        }
        if (!z6) {
            ToastHelper.show(requireContext(), "清除失败");
            return;
        }
        ToastHelper.show(requireContext(), "壁纸已清除，恢复默认");
        Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
        checkWallpaperCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$43(View view) {
        File[] listFiles = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper").listFiles(new s0(7));
        if (listFiles == null || listFiles.length <= 0) {
            ToastHelper.show(requireContext(), "当前没有设置壁纸");
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("确认清除");
        nVar.m("确定要清除所有普通壁纸吗？");
        nVar.r("清除", new c1(4, this, listFiles));
        nVar.o("取消", null);
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$44(View view) {
        this.currentEditingVideoDay = null;
        this.prefs.edit().remove("temp_video_wallpaper_editing_day").apply();
        int videoWallpaperCount = getVideoWallpaperCount(null);
        if (videoWallpaperCount > 0) {
            showVideoWallpaperMenuDialog(videoWallpaperCount, null);
        } else {
            this.isAppendVideoWallpaperMode = false;
            startPickVideoWallpaper();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$45(List list, DialogInterface dialogInterface, int i7) {
        Iterator it = list.iterator();
        boolean z6 = false;
        while (it.hasNext()) {
            if (((File) it.next()).delete()) {
                z6 = true;
            }
        }
        if (!z6) {
            ToastHelper.show(requireContext(), "清除失败");
            return;
        }
        ToastHelper.show(requireContext(), "动态壁纸已清除");
        sendWallpaperUpdatedBroadcast();
        refreshWeeklyVideoButtons();
        updateVideoWallpaperUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$46(View view) {
        List<File> videoWallpaperFiles = getVideoWallpaperFiles(null);
        if (videoWallpaperFiles.isEmpty()) {
            ToastHelper.show(requireContext(), "当前没有设置动态壁纸");
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("确认清除");
        nVar.m("确定要清除所有普通动态壁纸吗？");
        nVar.r("清除", new c1(3, this, videoWallpaperFiles));
        nVar.o("取消", null);
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$47(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "auto_launch_floating_apps_enabled", z6);
        String[] strArr = {this.prefs.getString(PREF_KEY_MAP_PACKAGE, null), this.prefs.getString(PREF_KEY_MAP_PACKAGE_2, null), this.prefs.getString("desktop_music_package", null)};
        for (int i7 = 0; i7 < 3; i7++) {
            String str = strArr[i7];
            if (str != null && !str.isEmpty()) {
                if (z6) {
                    addAppToAutoStart(str);
                } else {
                    removeAppFromAutoStart(str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$48(View view) {
        showAppSelectionDialog(false, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$49(View view) {
        showAppSelectionDialog(false, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$50(View view) {
        String string = this.prefs.getString(PREF_KEY_MAP_PACKAGE, null);
        if (string != null) {
            removeAppFromAutoStart(string);
        }
        String string2 = this.prefs.getString(PREF_KEY_MAP_PACKAGE_2, null);
        boolean z6 = (string2 == null || string2.isEmpty()) ? false : true;
        SharedPreferences.Editor remove = this.prefs.edit().remove(PREF_KEY_MAP_PACKAGE);
        if (z6) {
            remove.putInt(KEY_MAP_ACTIVE_INDEX, 1).putInt(KEY_FLOATING_MAP_DISPLAY_MODE, 0).putBoolean("desktop_map_calendar_enabled", false);
        } else {
            remove.putInt(KEY_MAP_ACTIVE_INDEX, 0).putInt(KEY_FLOATING_MAP_DISPLAY_MODE, 1).putBoolean("desktop_map_calendar_enabled", true);
        }
        remove.apply();
        updateFloatingMapText();
        updateMapCalendarSettingVisibility(this.prefs.getBoolean("desktop_map_enabled", false));
        if (!z6) {
            this.switchMapCalendarCard.setChecked(true);
        }
        checkAndSyncAutoLaunchFloatingApps();
        ToastHelper.show(requireContext(), "悬浮地图设置已清除");
        a.a.w("com.navi.tool.ACTION_MAP_APP_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$51(View view) {
        String string = this.prefs.getString(PREF_KEY_MAP_PACKAGE_2, null);
        if (string != null) {
            removeAppFromAutoStart(string);
        }
        this.prefs.edit().remove(PREF_KEY_MAP_PACKAGE_2).putInt(KEY_MAP_ACTIVE_INDEX, 0).apply();
        updateFloatingMapText();
        updateMapCalendarSettingVisibility(this.prefs.getBoolean("desktop_map_enabled", false));
        checkAndSyncAutoLaunchFloatingApps();
        ToastHelper.show(requireContext(), "悬浮地图二设置已清除");
        a.a.w("com.navi.tool.ACTION_MAP_APP_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$52(View view) {
        String string = this.prefs.getString("desktop_music_package", null);
        if (string != null) {
            removeAppFromAutoStart(string);
        }
        this.prefs.edit().remove("desktop_music_package").apply();
        updateFloatingMusicText();
        checkAndSyncAutoLaunchFloatingApps();
        ToastHelper.show(requireContext(), "悬浮音乐设置已清除");
        a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_APP_UPDATED, requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$53(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "wallpaper_weekly_enabled", z6);
        this.layoutWeeklySettings.setVisibility(z6 ? 0 : 8);
        sendWallpaperUpdatedBroadcast();
        checkWallpaperCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$54(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_weather_fx_enabled", z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$55(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_ambient_fx_enabled", z6);
        updateAmbientScaleVisibility(z6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$56(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_cover_gradient_enabled", z6);
        updateCoverGradientIntensityVisibility(z6);
        if (this.switchRoundCorner != null) {
            if (z6) {
                a.a.m(this.prefs, "desktop_round_corner_enabled", false);
                this.switchRoundCorner.setChecked(false);
            }
            this.switchRoundCorner.setEnabled(!z6);
            this.switchRoundCorner.setAlpha(z6 ? 0.5f : 1.0f);
        }
        if (z6) {
            sendRoundCornerUpdatedBroadcast();
            ToastHelper.show(requireContext(), "封面渐变已开启，桌面圆角自动关闭");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$initLogic$57(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initLogic$58(View view) {
        this.currentEditingDay = null;
        this.prefs.edit().remove("temp_wallpaper_editing_day").apply();
        File[] listFiles = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper").listFiles(new s0(14));
        int length = listFiles != null ? listFiles.length : 0;
        if (length > 0) {
            showWallpaperMenuDialog(length);
        } else {
            this.isAppendWallpaperMode = false;
            startPickWallpaper();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMapCardLogic$82(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_map_enabled", z6);
        updateMapCalendarSettingVisibility(z6);
        a.a.w("com.navi.tool.ACTION_MAP_APP_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMapCardLogic$83(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("desktop_map_calendar_enabled", z6).putInt(KEY_FLOATING_MAP_DISPLAY_MODE, z6 ? 1 : 0).apply();
        a.a.w("com.navi.tool.ACTION_MAP_APP_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMapCardLogic$84(View view) {
        showAppSelectionDialog(false, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMapCardLogic$85(View view) {
        showAppSelectionDialog(false, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMapCardLogic$86(View view) {
        showAppSelectionDialog(true, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$100(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean(DesktopAssistantClockWeatherController.PREF_CLOCK_WEATHER_ALERT_ENABLED, z6).apply();
        Intent intent = new Intent(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED);
        intent.putExtra("clock_weather_alert_setting_changed", true);
        intent.putExtra("clock_weather_alert_enabled", z6);
        requireContext().sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$101(RadioGroup radioGroup, int i7) {
        this.prefs.edit().putInt(DesktopAssistantClockWeatherController.PREF_CLOCK_WEATHER_LOCATION_MODE, i7 == g4.p.radio_music_clock_weather_location_county ? 0 : i7 == g4.p.radio_music_clock_weather_location_town ? 2 : 1).apply();
        Intent intent = new Intent(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED);
        intent.putExtra("clock_weather_location_mode_changed", true);
        requireContext().sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$102(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_music_spectrum_enabled", z6);
        updateMusicSpectrumSettingVisibility(z6);
        a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED, requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$103(CompoundButton compoundButton, boolean z6) {
        if (!z6 || hasRecordAudioPermission()) {
            this.pendingEnableRealMusicSpectrum = false;
            this.prefs.edit().putBoolean("desktop_music_real_spectrum_enabled", z6).apply();
            a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED, requireContext());
        } else {
            this.pendingEnableRealMusicSpectrum = true;
            setRealMusicSpectrumCheckedSilently(false);
            requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, REQUEST_RECORD_AUDIO_PERMISSION);
            ToastHelper.show(requireContext(), "真实频谱需要麦克风权限");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$104(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("desktop_music_spectrum_colorful_enabled", z6).apply();
        a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED, requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$105(RadioGroup radioGroup, int i7) {
        if (this.suppressMusicSourceRadioCallback) {
            return;
        }
        int i8 = i7 == g4.p.radio_source_kuwo ? 1 : i7 == g4.p.radio_source_kugou ? 2 : i7 == g4.p.radio_source_netease ? 3 : i7 == g4.p.radio_source_new_kuwo ? 4 : i7 == g4.p.radio_source_luna ? 5 : i7 == g4.p.radio_source_universal ? 6 : 0;
        this.prefs.edit().putInt("desktop_music_source", i8).putInt(FloatingWindowService.KEY_MUSIC_SOURCE, i8).apply();
        checkAndInitMusicService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$106(RadioGroup radioGroup, int i7) {
        int i8 = 0;
        if (i7 != g4.p.rb_color_white) {
            if (i7 == g4.p.rb_color_yellow) {
                i8 = 1;
            } else if (i7 == g4.p.rb_color_green) {
                i8 = 2;
            } else if (i7 == g4.p.rb_color_blue) {
                i8 = 3;
            } else if (i7 == g4.p.rb_color_orange) {
                i8 = 4;
            } else if (i7 == g4.p.rb_color_black) {
                i8 = 5;
            }
        }
        a.a.l(this.prefs, "desktop_lyrics_color_index", i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$107(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("desktop_transparent_bg", z6).apply();
        Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$108(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_music_show_album_art", z6);
        this.layoutAlbumArtOptions.setVisibility(z6 ? 0 : 8);
        View view = this.dividerAlbumArtOptions;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED, requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$109(RadioGroup radioGroup, int i7) {
        int i8 = i7 == g4.p.rb_album_art_circle ? 1 : 0;
        a.a.l(this.prefs, "desktop_music_album_art_mode", i8);
        this.layoutAlbumArtAnimation.setVisibility(i8 != 1 ? 8 : 0);
        a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED, requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$110(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("desktop_music_album_art_anim", z6).apply();
        a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED, requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$111(CompoundButton compoundButton, boolean z6) {
        SharedPreferencesUtils.setDesktopAssistantAutoStartEnabled(requireContext(), z6);
        if (!z6) {
            if (SharedPreferencesUtils.getAutoStartAppCount(requireContext()) > 0 || !SharedPreferencesUtils.isAutoStartEnabled(requireContext())) {
                return;
            }
            SharedPreferencesUtils.setAutoStartEnabled(requireContext(), false);
            ToastHelper.showLong(requireContext(), "没有其他自启应用，自启管理总开关已自动关闭");
            return;
        }
        if (!SharedPreferencesUtils.isAutoStartEnabled(requireContext())) {
            SharedPreferencesUtils.setAutoStartEnabled(requireContext(), true);
        }
        int autoStartAppDelay = SharedPreferencesUtils.getAutoStartAppDelay(requireContext(), "com.navi.tool.desktopassistant");
        ToastHelper.show(requireContext(), "已设置桌面助手自启，延时" + autoStartAppDelay + "秒");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$112(CompoundButton compoundButton, boolean z6) {
        if (!z6 || (!hasAnyVideoWallpaperConfigured() && !this.prefs.getBoolean("desktop_cover_gradient_enabled", false))) {
            this.prefs.edit().putBoolean("desktop_round_corner_enabled", z6).apply();
            ToastHelper.show(requireContext(), "桌面圆角视图已".concat(z6 ? "开启" : "关闭"));
            sendRoundCornerUpdatedBroadcast();
        } else {
            this.switchRoundCorner.setChecked(false);
            this.prefs.edit().putBoolean("desktop_round_corner_enabled", false).apply();
            ToastHelper.show(requireContext(), this.prefs.getBoolean("desktop_cover_gradient_enabled", false) ? "封面渐变模式下暂不支持桌面圆角视图" : "动态壁纸模式下暂不支持桌面圆角视图");
            sendRoundCornerUpdatedBroadcast();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$113(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("desktop_double_click_swap", z6).apply();
        ToastHelper.show(requireContext(), "双击切换音乐车控卡片已".concat(z6 ? "开启" : "关闭"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$114(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "car_control_card_enabled", z6);
        this.layoutCarControlSettings.setVisibility(z6 ? 0 : 8);
        View view = this.dividerVisualCarModelTop;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
        View view2 = this.dividerVisualCarModelBottom;
        if (view2 != null) {
            view2.setVisibility(z6 ? 0 : 8);
        }
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$115(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "seat_comfort_panel_enabled", z6);
        hideSeatPanelAdvancedSettings();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$116(CompoundButton compoundButton, boolean z6) {
        if (!z6 || !this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            this.prefs.edit().putBoolean("desktop_seat_compat_mode", z6).apply();
            a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
        } else {
            compoundButton.setChecked(false);
            this.prefs.edit().putBoolean("desktop_seat_compat_mode", false).apply();
            ToastHelper.show(requireContext(), "当前车联服务模式不支持此选项");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$117(CompoundButton compoundButton, boolean z6) {
        if (!z6 || !this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            this.prefs.edit().putBoolean("fuel_card_enabled", z6).apply();
            a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
        } else {
            compoundButton.setChecked(false);
            this.prefs.edit().putBoolean("fuel_card_enabled", false).apply();
            ToastHelper.show(requireContext(), "当前车联服务模式不支持此选项");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$118(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("car_control_ac_enabled", z6).apply();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$119(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("seat_comfort_card_enabled", z6).apply();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$120(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("car_control_door_enabled", z6).apply();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$121(CompoundButton compoundButton, boolean z6) {
        if (!z6 || !this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            this.prefs.edit().putBoolean("car_control_drive_enabled", z6).apply();
            a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
        } else {
            compoundButton.setChecked(false);
            this.prefs.edit().putBoolean("car_control_drive_enabled", false).apply();
            ToastHelper.show(requireContext(), "当前车联服务模式不支持此选项");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$122(CompoundButton compoundButton, boolean z6) {
        if (!z6 || !this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            this.prefs.edit().putBoolean("car_control_status_enabled", z6).apply();
            a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
        } else {
            compoundButton.setChecked(false);
            this.prefs.edit().putBoolean("car_control_status_enabled", false).apply();
            ToastHelper.show(requireContext(), "当前车联服务模式不支持此选项");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$123(CompoundButton compoundButton, boolean z6) {
        if (!z6 || !this.prefs.getBoolean("lion_sync_service_enabled", false)) {
            this.prefs.edit().putBoolean("car_control_ev_enabled", z6).apply();
            a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
        } else {
            compoundButton.setChecked(false);
            this.prefs.edit().putBoolean("car_control_ev_enabled", false).apply();
            ToastHelper.show(requireContext(), "当前车联服务模式不支持此选项");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$124(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "car_control_visual_model_enabled", z6);
        updateVisualCarModelChildVisibility(z6);
        this.layoutCarModelSelection.setVisibility(z6 ? 0 : 8);
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$125(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("car_control_door_window_dynamic_effect_enabled", z6).apply();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$126(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putBoolean("car_control_dynamic_lane_enabled", z6).apply();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$127(View view) {
        startChooseCustomCarModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$128(RadioGroup radioGroup, int i7) {
        String str = i7 == g4.p.rb_car_tiggo9 ? "tiggo9" : i7 == g4.p.rb_car_arrizo8 ? "arrizo8" : i7 == g4.p.rb_car_fengyun_a8 ? "fengyun_a8" : i7 == g4.p.rb_car_tiggo8pro ? "tiggo8pro" : i7 == g4.p.rb_car_custom ? CAR_MODEL_TYPE_CUSTOM : "yaoguang";
        this.prefs.edit().putString(PREF_CAR_CONTROL_MODEL_TYPE, str).apply();
        updateCustomCarModelBrowseVisibility(str);
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$129(RadioGroup radioGroup, int i7) {
        int i8 = 0;
        if (i7 != g4.p.rb_cc_property_color_white) {
            if (i7 == g4.p.rb_cc_property_color_yellow) {
                i8 = 1;
            } else if (i7 == g4.p.rb_cc_property_color_green) {
                i8 = 2;
            } else if (i7 == g4.p.rb_cc_property_color_blue) {
                i8 = 3;
            } else if (i7 == g4.p.rb_cc_property_color_orange) {
                i8 = 4;
            } else if (i7 == g4.p.rb_cc_property_color_black) {
                i8 = 5;
            }
        }
        this.prefs.edit().putInt("car_control_property_text_color_index", i8).apply();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$130(RadioGroup radioGroup, int i7) {
        int i8 = 0;
        if (i7 != g4.p.rb_cc_color_white) {
            if (i7 == g4.p.rb_cc_color_yellow) {
                i8 = 1;
            } else if (i7 == g4.p.rb_cc_color_green) {
                i8 = 2;
            } else if (i7 == g4.p.rb_cc_color_blue) {
                i8 = 3;
            } else if (i7 == g4.p.rb_cc_color_orange) {
                i8 = 4;
            } else if (i7 == g4.p.rb_cc_color_black) {
                i8 = 5;
            }
        }
        this.prefs.edit().putInt("car_control_text_color_index", i8).apply();
        a.a.w("com.navi.tool.CAR_CONTROL_CARD_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$96(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_music_enabled", z6);
        this.layoutMusicSettings.setVisibility(z6 ? 0 : 8);
        this.layoutMusicClockWeather.setVisibility(z6 ? 0 : 8);
        if (!z6) {
            stopMusicServices();
            MusicIslandService.stop(requireContext());
        } else {
            checkAndInitMusicService();
            if (this.prefs.getBoolean("desktop_music_island_enabled", false)) {
                MusicIslandService.startOrRefresh(requireContext());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$97(View view) {
        showAppSelectionDialog(true, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$98(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_music_island_enabled", z6);
        if (z6 && this.prefs.getBoolean("desktop_music_enabled", false)) {
            MusicIslandService.startOrRefresh(requireContext());
        } else {
            MusicIslandService.stop(requireContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initMusicLogic$99(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "desktop_music_clock_weather_enabled", z6);
        updateMusicClockWeatherLocationModeVisibility(z6);
        a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED, requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRandomAndMixedWallpaperLogic$88(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "wallpaper_random_order", z6);
        sendWallpaperUpdatedBroadcast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initRandomAndMixedWallpaperLogic$89(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "wallpaper_mixed_play", z6);
        sendWallpaperUpdatedBroadcast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$4(View view) {
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(view.getContext());
        if (appPrefs.getInt("desktop_ambient_festival", 0) == 0) {
            ToastHelper.show(requireContext(), "长按可切换节日预览");
            return;
        }
        appPrefs.edit().putInt("desktop_ambient_festival", 0).remove("desktop_ambient_festival_at").apply();
        updateAmbientFxLabel();
        ToastHelper.show(requireContext(), "已退出节日调试，恢复跟随系统日期");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initViews$5(View view) {
        SharedPreferences appPrefs = SharedPreferencesUtils.getAppPrefs(view.getContext());
        int i7 = WeatherAmbientView.WEATHER_NONE;
        int i8 = (appPrefs.getInt("desktop_ambient_festival", 0) + 1) % 21;
        appPrefs.edit().putInt("desktop_ambient_festival", i8).putLong("desktop_ambient_festival_at", System.currentTimeMillis()).apply();
        updateAmbientFxLabel();
        boolean z6 = appPrefs.getBoolean("desktop_ambient_fx_enabled", false);
        String g7 = i8 == 0 ? "自动（跟随系统日期）" : WeatherAmbientView.g(i8);
        if (!z6) {
            ToastHelper.show(requireContext(), "已切到「" + g7 + "」，但氛围物体开关还关着");
        } else if (i8 == 0) {
            ToastHelper.show(requireContext(), "节日氛围：自动（跟随系统日期）");
        } else {
            ToastHelper.show(requireContext(), "节日氛围：" + g7 + "（单击标题可恢复自动）");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initWallpaperModeLogic$87(CompoundButton compoundButton, boolean z6) {
        this.prefs.edit().putInt("desktop_wallpaper_display_mode", z6 ? 1 : 0).apply();
        a.a.w("com.navi.tool.WALLPAPER_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initWeeklyVideoWallpaper$61(CompoundButton compoundButton, boolean z6) {
        a.a.m(this.prefs, "wallpaper_weekly_video_enabled", z6);
        this.layoutWeeklyVideoSettings.setVisibility(z6 ? 0 : 8);
        sendWallpaperUpdatedBroadcast();
        updateVideoWallpaperUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadManagedVideoThumbnail$74(int i7, String str, ImageView imageView, Bitmap bitmap) {
        if (i7 == this.videoManageThumbSessionToken.get() && str.equals(imageView.getTag())) {
            imageView.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadManagedVideoThumbnail$75(int i7, File file, String str, String str2, ImageView imageView, File file2) {
        if (i7 == this.videoManageThumbSessionToken.get() && !Thread.currentThread().isInterrupted()) {
            Bitmap decodeManagedThumbnailFile = decodeManagedThumbnailFile(file);
            if (Thread.currentThread().isInterrupted()) {
                if (decodeManagedThumbnailFile == null || decodeManagedThumbnailFile.isRecycled()) {
                    return;
                }
                decodeManagedThumbnailFile.recycle();
                return;
            }
            if (decodeManagedThumbnailFile == null) {
                generateManagedVideoThumbnail(imageView, file2, file, str2, str, i7);
                return;
            }
            this.videoManageThumbnailCache.put(str, decodeManagedThumbnailFile);
            androidx.fragment.app.d0 activity = getActivity();
            if (activity != null) {
                activity.runOnUiThread(new t2(this, i7, str2, imageView, decodeManagedThumbnailFile, 1));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onViewCreated$0(ActivityResult activityResult) {
        Intent intent;
        if (activityResult.f648a != -1 || (intent = activityResult.f649b) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            for (int i7 = 0; i7 < itemCount; i7++) {
                arrayList.add(intent.getClipData().getItemAt(i7).getUri());
            }
        } else if (intent.getData() != null) {
            arrayList.add(intent.getData());
        }
        startWallpaperProcessing(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onViewCreated$1(ActivityResult activityResult) {
        Intent intent;
        if (activityResult.f648a != -1 || (intent = activityResult.f649b) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            for (int i7 = 0; i7 < itemCount; i7++) {
                Uri uri = intent.getClipData().getItemAt(i7).getUri();
                if (uri != null) {
                    arrayList.add(uri);
                }
            }
        } else if (intent.getData() != null) {
            arrayList.add(intent.getData());
        }
        if (arrayList.isEmpty()) {
            ToastHelper.show(requireContext(), "未选择动态壁纸");
        } else {
            startVideoWallpaperProcessing(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onViewCreated$2(ActivityResult activityResult) {
        Intent intent;
        if (activityResult.f648a != -1 || (intent = activityResult.f649b) == null || intent.getData() == null) {
            return;
        }
        handleCustomCarModelChosen(intent.getData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$onViewCreated$3(ActivityResult activityResult) {
        int i7 = activityResult.f648a;
        Intent intent = activityResult.f649b;
        if (i7 == -1 && intent != null) {
            Uri uri = (Uri) intent.getParcelableExtra("com.yalantis.ucrop.OutputUri");
            if (uri != null) {
                saveSingleWallpaper(uri);
            } else {
                ToastHelper.show(requireContext(), "裁剪失败");
            }
        } else if (i7 == 96) {
            Throwable th = (Throwable) intent.getSerializableExtra("com.yalantis.ucrop.Error");
            Context requireContext = requireContext();
            StringBuilder sb = new StringBuilder("裁剪出错: ");
            sb.append(th != null ? th.getMessage() : "未知错误");
            ToastHelper.show(requireContext, sb.toString());
        }
        processNextWallpaper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postToast$68(Context context, String str) {
        if (!isAdded() || getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        ToastHelper.show(context, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postVideoWallpaperSaveResult$67(boolean z6, String str, Context context) {
        if (!isAdded() || getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        if (z6) {
            forceDisableRoundCornerForVideo();
            processNextVideoWallpaper();
        } else {
            this.failedVideoWallpaperCount++;
            if (str != null) {
                ToastHelper.show(context, str);
            }
            processNextVideoWallpaper();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshWeeklyButtons$59(DialogInterface dialogInterface, int i7) {
        String[] strArr = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper");
        for (int i8 = 0; i8 < 7; i8++) {
            File file2 = new File(file, strArr[i8]);
            if (file2.exists()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null) {
                    for (File file3 : listFiles) {
                        file3.delete();
                    }
                }
                file2.delete();
            }
        }
        ToastHelper.show(requireContext(), "已清除所有周期壁纸");
        refreshWeeklyButtons();
        Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshWeeklyButtons$60(View view) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("确认清除");
        nVar.m("确定要删除所有周期壁纸吗？");
        nVar.r("删除", new z1(this, 1));
        nVar.o("取消", null);
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshWeeklyVideoButtons$62(DialogInterface dialogInterface, int i7) {
        boolean z6 = false;
        for (String str : WEEK_DAYS) {
            if (clearVideoWallpaperFiles(str)) {
                z6 = true;
            }
        }
        if (!z6) {
            ToastHelper.show(requireContext(), "清除失败");
            return;
        }
        ToastHelper.show(requireContext(), "已清除所有周期动态壁纸");
        refreshWeeklyVideoButtons();
        updateVideoWallpaperUi();
        sendWallpaperUpdatedBroadcast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshWeeklyVideoButtons$63(View view) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("确认清除");
        nVar.m("确定要删除所有周期动态壁纸吗？");
        nVar.r("删除", new z1(this, 2));
        nVar.o("取消", null);
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveVideoWallpaper$66(Context context, Uri uri, Activity activity, File file, String str, ContentResolver contentResolver) {
        String validateVideoMetadataMessage = validateVideoMetadataMessage(context, uri);
        int i7 = 0;
        if (validateVideoMetadataMessage != null && !validateVideoMetadataMessage.startsWith("动态壁纸分辨率为 ")) {
            this.failedVideoWallpaperCount++;
            postToast(context, validateVideoMetadataMessage);
            activity.runOnUiThread(new g2(this, i7));
            return;
        }
        if (validateVideoMetadataMessage != null) {
            postToast(context, validateVideoMetadataMessage);
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            postVideoWallpaperSaveResult(activity, context, false, str, "创建动态壁纸目录失败");
            return;
        }
        try {
            InputStream openInputStream = contentResolver.openInputStream(uri);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                try {
                    if (openInputStream == null) {
                        postVideoWallpaperSaveResult(activity, context, false, str, "读取动态壁纸失败");
                        fileOutputStream.close();
                        if (openInputStream != null) {
                            openInputStream.close();
                            return;
                        }
                        return;
                    }
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = openInputStream.read(bArr);
                        if (read == -1) {
                            fileOutputStream.flush();
                            this.nextVideoWallpaperIndex++;
                            this.processedVideoWallpaperCount++;
                            postVideoWallpaperSaveResult(activity, context, true, str, null);
                            fileOutputStream.close();
                            openInputStream.close();
                            return;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                } finally {
                }
            } finally {
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            postVideoWallpaperSaveResult(activity, context, false, str, "保存动态壁纸失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setupWeeklyButton$79(File file, String str) {
        return str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupWeeklyButton$80(String str, File[] fileArr, String str2, DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            this.isAppendWallpaperMode = true;
            startPickWallpaper();
            return;
        }
        if (i7 == 1) {
            showManageWallpapersDialog(str);
            return;
        }
        if (fileArr != null) {
            for (File file : fileArr) {
                file.delete();
            }
        }
        ToastHelper.show(requireContext(), "已清空" + str2 + "壁纸");
        refreshWeeklyButtons();
        Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupWeeklyButton$81(String str, int i7, String str2, File[] fileArr, View view) {
        this.currentEditingDay = str;
        this.prefs.edit().putString("temp_wallpaper_editing_day", str).apply();
        if (i7 <= 0) {
            this.isAppendWallpaperMode = false;
            startPickWallpaper();
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t(str2 + "壁纸设置");
        nVar.l(new String[]{"添加壁纸", "管理壁纸", "清空壁纸"}, new a(this, str, fileArr, str2, 1));
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupWeeklyVideoButton$64(String str, String str2, DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            this.isAppendVideoWallpaperMode = true;
            startPickVideoWallpaper();
        } else {
            if (i7 == 1) {
                showManageVideoWallpapersDialog(str);
                return;
            }
            ToastHelper.show(requireContext(), clearVideoWallpaperFiles(str) ? a.a.h("已清空", str2, "动态壁纸") : "清除失败");
            refreshWeeklyVideoButtons();
            updateVideoWallpaperUi();
            sendWallpaperUpdatedBroadcast();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupWeeklyVideoButton$65(String str, boolean z6, String str2, View view) {
        this.currentEditingVideoDay = str;
        this.prefs.edit().putString("temp_video_wallpaper_editing_day", str).apply();
        if (!z6) {
            this.isAppendVideoWallpaperMode = false;
            startPickVideoWallpaper();
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t(str2 + "动态壁纸设置");
        nVar.l(new String[]{"添加动态壁纸", "管理动态壁纸", "清空动态壁纸"}, new b(this, str, str2, 1));
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAdjustTopInfoDialog$26(DialogInterface dialogInterface) {
        updateTopInfoCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAdjustTopInfoDialog$27(List list, final RecyclerView recyclerView) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        final PanelAppSettingsAdapter panelAppSettingsAdapter = new PanelAppSettingsAdapter(requireContext(), list, new PanelAppSettingsAdapter.OnIconChangeRequestListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.9
            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeIcon(AppInfo appInfo) {
                ToastHelper.show(DesktopAssistantFragment.this.requireContext(), "暂不支持修改图标");
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeName(AppInfo appInfo) {
                DesktopAssistantFragment.this.showChangeTopInfoNameDialog(appInfo, (PanelAppSettingsAdapter) recyclerView.getAdapter());
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestRestoreIcon(AppInfo appInfo) {
            }
        });
        panelAppSettingsAdapter.setShowDockSwitch(false);
        recyclerView.setAdapter(panelAppSettingsAdapter);
        new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.n0(3, 0) { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.10
            @Override // androidx.recyclerview.widget.l0
            public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                int adapterPosition = j2Var.getAdapterPosition();
                int adapterPosition2 = j2Var2.getAdapterPosition();
                if (adapterPosition == -1 || adapterPosition2 == -1 || !panelAppSettingsAdapter.onItemMove(adapterPosition, adapterPosition2)) {
                    return false;
                }
                DesktopAssistantFragment.this.saveTopInfoItemOrder(panelAppSettingsAdapter.getCurrentPackageOrder());
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i7) {
            }
        }).f(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAdjustTopInfoDialog$28(RecyclerView recyclerView) {
        List<AppInfo> enabledTopInfoItemsInOrder = getEnabledTopInfoItemsInOrder();
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new c2(this, enabledTopInfoItemsInOrder, recyclerView, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$showAppSelectionDialog$92(AppInfo appInfo, AppInfo appInfo2) {
        return appInfo.getAppName().compareToIgnoreCase(appInfo2.getAppName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAppSelectionDialog$93(boolean z6, boolean z7, androidx.appcompat.app.o oVar, AppInfo appInfo) {
        String str = z6 ? "desktop_music_package" : z7 ? PREF_KEY_MAP_PACKAGE_2 : PREF_KEY_MAP_PACKAGE;
        String string = this.prefs.getString(str, null);
        String packageName = appInfo.getPackageName();
        if (this.switchAutoLaunchFloating.isChecked() && string != null && !string.isEmpty() && !string.equals(packageName)) {
            removeAppFromAutoStart(string, false);
        }
        this.prefs.edit().putString(str, packageName).apply();
        if (z6) {
            autoUpdateMusicSource(packageName);
            updateFloatingMusicText();
            a.a.w(DesktopAssistantMusicUiHelper.ACTION_MUSIC_APP_UPDATED, requireContext());
        } else {
            this.prefs.edit().putInt(KEY_FLOATING_MAP_DISPLAY_MODE, 0).putBoolean("desktop_map_calendar_enabled", false).putInt(KEY_MAP_ACTIVE_INDEX, 0).apply();
            updateFloatingMapText();
            updateMapCalendarSettingVisibility(this.prefs.getBoolean("desktop_map_enabled", false));
            a.a.w("com.navi.tool.ACTION_MAP_APP_UPDATED", requireContext());
        }
        checkAndSyncAutoLaunchFloatingApps();
        if (this.switchAutoLaunchFloating.isChecked()) {
            addAppToAutoStart(packageName);
        }
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showAppSelectionDialog$94(ProgressDialog progressDialog, final boolean z6, final boolean z7, List list) {
        progressDialog.dismiss();
        if (isAdded()) {
            Context requireContext = requireContext();
            RecyclerView recyclerView = new RecyclerView(requireContext);
            recyclerView.setLayoutManager(new LinearLayoutManager(1));
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext);
            nVar.t(z6 ? "选择悬浮音乐应用" : z7 ? "选择悬浮地图二" : "选择悬浮地图应用");
            nVar.u(recyclerView);
            nVar.o("取消", null);
            final androidx.appcompat.app.o e7 = nVar.e();
            recyclerView.setAdapter(new SelectAppAdapter(requireContext, list, new SelectAppAdapter.OnAppClickListener() { // from class: com.navi.tool.ui.extra.d2
                @Override // com.navi.tool.ui.adapter.SelectAppAdapter.OnAppClickListener, com.navi.tool.ui.adapter.DockAppAdapter.OnAppClickListener
                public final void onAppClick(AppInfo appInfo) {
                    DesktopAssistantFragment.this.lambda$showAppSelectionDialog$93(z6, z7, e7, appInfo);
                }
            }));
            e7.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAppSelectionDialog$95(Context context, String str, final ProgressDialog progressDialog, final boolean z6, final boolean z7) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(128);
        final ArrayList arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            int i7 = applicationInfo.flags;
            if ((i7 & 1) == 0 && (i7 & 128) == 0 && (str == null || !str.equals(applicationInfo.packageName))) {
                if (packageManager.getLaunchIntentForPackage(applicationInfo.packageName) != null && !applicationInfo.packageName.equals(context.getPackageName())) {
                    arrayList.add(new AppInfo(applicationInfo.packageName, packageManager.getApplicationLabel(applicationInfo).toString(), false));
                }
            }
        }
        Collections.sort(arrayList, new x(12));
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.navi.tool.ui.extra.w1
                @Override // java.lang.Runnable
                public final void run() {
                    DesktopAssistantFragment.this.lambda$showAppSelectionDialog$94(progressDialog, z6, z7, arrayList);
                }
            });
        } else {
            progressDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showChangeNameDialog$10(EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showChangeNameDialog$8(EditText editText, AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, DialogInterface dialogInterface, int i7) {
        String c7 = a.a.c(editText);
        if (c7.isEmpty()) {
            this.prefs.edit().remove("app_custom_name_" + appInfo.getPackageName()).commit();
            ToastHelper.show(requireContext(), "已恢复默认名称");
        } else {
            this.prefs.edit().putString("app_custom_name_" + appInfo.getPackageName(), c7).commit();
            ToastHelper.show(requireContext(), "名称已修改");
        }
        if (panelAppSettingsAdapter != null) {
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        requireContext().sendBroadcast(new Intent("com.navi.tool.DESKTOP_APPS_UPDATED"));
        Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
        GestureActionService.updatePanelConfig(requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showChangeNameDialog$9(AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, DialogInterface dialogInterface, int i7) {
        this.prefs.edit().remove("app_custom_name_" + appInfo.getPackageName()).commit();
        if (panelAppSettingsAdapter != null) {
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        requireContext().sendBroadcast(new Intent("com.navi.tool.DESKTOP_APPS_UPDATED"));
        Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
        GestureActionService.updatePanelConfig(requireContext());
        ToastHelper.show(requireContext(), "已恢复默认名称");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showChangeTopInfoNameDialog$29(EditText editText, AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, DialogInterface dialogInterface, int i7) {
        String c7 = a.a.c(editText);
        if (c7.isEmpty()) {
            this.prefs.edit().remove("top_info_item_name_" + appInfo.getPackageName()).commit();
            ToastHelper.show(requireContext(), "已恢复默认名称");
        } else {
            this.prefs.edit().putString("top_info_item_name_" + appInfo.getPackageName(), c7).commit();
            ToastHelper.show(requireContext(), "名称已修改");
        }
        if (panelAppSettingsAdapter != null) {
            if (c7.isEmpty()) {
                c7 = getCarInfoNameById(appInfo.getPackageName());
            }
            appInfo.setAppName(c7);
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        a.a.w("com.navi.tool.TOP_INFO_AREA_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showChangeTopInfoNameDialog$30(AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter, DialogInterface dialogInterface, int i7) {
        this.prefs.edit().remove("top_info_item_name_" + appInfo.getPackageName()).commit();
        if (panelAppSettingsAdapter != null) {
            appInfo.setAppName(getCarInfoNameById(appInfo.getPackageName()));
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        requireContext().sendBroadcast(new Intent("com.navi.tool.TOP_INFO_AREA_UPDATED"));
        ToastHelper.show(requireContext(), "已恢复默认名称");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showChangeTopInfoNameDialog$31(EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showManageVideoWallpapersDialog$73(boolean[] zArr, List list, String str, DialogInterface dialogInterface) {
        this.videoManageThumbSessionToken.incrementAndGet();
        cancelManagedVideoThumbnailTasks();
        if (zArr[0]) {
            saveVideoWallpaperOrder(list, str);
        } else {
            refreshWeeklyVideoButtons();
            updateVideoWallpaperUi();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$showManageWallpapersDialog$132(File file, String str) {
        return str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$showManageWallpapersDialog$133(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$showManageWallpapersDialog$134(File file, File file2) {
        String name = file.getName();
        String name2 = file2.getName();
        return name.length() != name2.length() ? name.length() - name2.length() : name.compareTo(name2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showManageWallpapersDialog$135(boolean[] zArr, List list, String str, DialogInterface dialogInterface) {
        if (zArr[0]) {
            saveWallpaperOrder(list, str);
        } else if (str != null) {
            refreshWeeklyButtons();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectSystemAppsDialog$12(RecyclerView recyclerView, AppInfo appInfo) {
        boolean z6 = !appInfo.isEnabled();
        this.prefs.edit().putBoolean("desktop_app_enabled_" + appInfo.getPackageName(), z6).apply();
        appInfo.setEnabled(z6);
        androidx.recyclerview.widget.h1 adapter = recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectSystemAppsDialog$13(RecyclerView recyclerView, List list) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        AppAdapter appAdapter = new AppAdapter(new l2(this, recyclerView, 1));
        appAdapter.submitList(list);
        recyclerView.setAdapter(appAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectSystemAppsDialog$14(Context context, RecyclerView recyclerView) {
        List<AppInfo> allLaunchableApps = getAllLaunchableApps(context);
        ArrayList arrayList = new ArrayList();
        for (AppInfo appInfo : allLaunchableApps) {
            if (isSystemApp(context, appInfo.getPackageName())) {
                appInfo.setEnabled(this.prefs.getBoolean("desktop_app_enabled_" + appInfo.getPackageName(), false));
                arrayList.add(appInfo);
            }
        }
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new c2(this, recyclerView, arrayList, 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectTopInfoDialog$22(DialogInterface dialogInterface) {
        updateTopInfoCount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectTopInfoDialog$23(RecyclerView recyclerView, AppInfo appInfo) {
        boolean z6 = !appInfo.isEnabled();
        this.prefs.edit().putBoolean("top_info_item_enabled_" + appInfo.getPackageName(), z6).apply();
        appInfo.setEnabled(z6);
        requireContext().sendBroadcast(new Intent("com.navi.tool.TOP_INFO_AREA_UPDATED"));
        androidx.recyclerview.widget.h1 adapter = recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSelectTopInfoDialog$24(RecyclerView recyclerView, List list) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        AppAdapter appAdapter = new AppAdapter(new l2(this, recyclerView, 0));
        appAdapter.submitList(list);
        recyclerView.setAdapter(appAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showSelectTopInfoDialog$25(RecyclerView recyclerView) {
        ArrayList arrayList = new ArrayList();
        Context requireContext = requireContext();
        Object obj = g0.g.f6423a;
        g0.c.b(requireContext, R.drawable.ic_menu_info_details);
        int i7 = 0;
        int i8 = 0;
        while (true) {
            String[] strArr = CAR_INFO_IDS;
            if (i8 >= strArr.length) {
                break;
            }
            String str = strArr[i8];
            if (isTopInfoSupportedInCurrentMode(str)) {
                a.a.x(str, CAR_INFO_NAMES[i8], this.prefs.getBoolean("top_info_item_enabled_" + str, false), arrayList);
            }
            i8++;
        }
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new c2(this, recyclerView, arrayList, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSetDesktopAppsDialog$18(RadioButton radioButton, RadioGroup radioGroup, int i7) {
        this.prefs.edit().putInt("desktop_app_icon_size", i7 == radioButton.getId() ? 60 : 76).apply();
        a.a.w("com.navi.tool.DESKTOP_APPS_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSetDesktopAppsDialog$19(RadioButton radioButton, RadioGroup radioGroup, int i7) {
        this.prefs.edit().putInt("desktop_app_text_color", i7 == radioButton.getId() ? 1 : 0).apply();
        a.a.w("com.navi.tool.DESKTOP_APPS_UPDATED", requireContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSetDesktopAppsDialog$20(List list, RecyclerView recyclerView) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        PanelAppSettingsAdapter panelAppSettingsAdapter = new PanelAppSettingsAdapter(requireContext(), list, new PanelAppSettingsAdapter.OnIconChangeRequestListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.7
            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeIcon(AppInfo appInfo) {
                DesktopAssistantFragment.this.pendingIconChangePackageName = appInfo.getPackageName();
                DesktopAssistantFragment.this.startChooseIcon();
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeName(AppInfo appInfo) {
                DesktopAssistantFragment desktopAssistantFragment = DesktopAssistantFragment.this;
                desktopAssistantFragment.showChangeNameDialog(appInfo, desktopAssistantFragment.desktopAppSettingsAdapter);
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestRestoreIcon(AppInfo appInfo) {
                DesktopAssistantFragment.this.restoreAppIcon(appInfo);
            }
        });
        this.desktopAppSettingsAdapter = panelAppSettingsAdapter;
        recyclerView.setAdapter(panelAppSettingsAdapter);
        new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.n0(3, 0) { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.8
            @Override // androidx.recyclerview.widget.l0
            public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                int adapterPosition = j2Var.getAdapterPosition();
                int adapterPosition2 = j2Var2.getAdapterPosition();
                if (adapterPosition == -1 || adapterPosition2 == -1 || !DesktopAssistantFragment.this.desktopAppSettingsAdapter.onItemMove(adapterPosition, adapterPosition2)) {
                    return false;
                }
                DesktopAssistantFragment desktopAssistantFragment = DesktopAssistantFragment.this;
                desktopAssistantFragment.saveDesktopAppOrder(desktopAssistantFragment.desktopAppSettingsAdapter.getCurrentPackageOrder(), "desktop_app_order");
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i7) {
            }
        }).f(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSetDesktopAppsDialog$21(Context context, RecyclerView recyclerView) {
        List<AppInfo> enabledDesktopAppsInOrder = getEnabledDesktopAppsInOrder(context, "desktop_app_order");
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new c2(this, enabledDesktopAppsInOrder, recyclerView, 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSetDockAppsDialog$15(RadioButton radioButton, RadioGroup radioGroup, int i7) {
        this.prefs.edit().putBoolean("dock_app_show_name", i7 == radioButton.getId()).apply();
        Intent intent = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSetDockAppsDialog$16(List list, RecyclerView recyclerView) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        PanelAppSettingsAdapter panelAppSettingsAdapter = new PanelAppSettingsAdapter(requireContext(), list, new PanelAppSettingsAdapter.OnIconChangeRequestListener() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.5
            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeIcon(AppInfo appInfo) {
                DesktopAssistantFragment.this.pendingIconChangePackageName = appInfo.getPackageName();
                DesktopAssistantFragment.this.startChooseIcon();
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestChangeName(AppInfo appInfo) {
                DesktopAssistantFragment desktopAssistantFragment = DesktopAssistantFragment.this;
                desktopAssistantFragment.showChangeNameDialog(appInfo, desktopAssistantFragment.desktopAppSettingsAdapter);
            }

            @Override // com.navi.tool.ui.adapter.PanelAppSettingsAdapter.OnIconChangeRequestListener
            public void onRequestRestoreIcon(AppInfo appInfo) {
                DesktopAssistantFragment.this.restoreAppIcon(appInfo);
            }
        });
        this.desktopAppSettingsAdapter = panelAppSettingsAdapter;
        panelAppSettingsAdapter.setShowDockSwitch(true);
        recyclerView.setAdapter(this.desktopAppSettingsAdapter);
        new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.n0(3, 0) { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.6
            @Override // androidx.recyclerview.widget.l0
            public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                int adapterPosition = j2Var.getAdapterPosition();
                int adapterPosition2 = j2Var2.getAdapterPosition();
                if (adapterPosition == -1 || adapterPosition2 == -1 || !DesktopAssistantFragment.this.desktopAppSettingsAdapter.onItemMove(adapterPosition, adapterPosition2)) {
                    return false;
                }
                DesktopAssistantFragment desktopAssistantFragment = DesktopAssistantFragment.this;
                desktopAssistantFragment.saveDesktopAppOrder(desktopAssistantFragment.desktopAppSettingsAdapter.getCurrentPackageOrder(), "dock_app_order");
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i7) {
            }
        }).f(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSetDockAppsDialog$17(Context context, RecyclerView recyclerView) {
        List<AppInfo> enabledDesktopAppsInOrder = getEnabledDesktopAppsInOrder(context, "dock_app_order");
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new c2(this, enabledDesktopAppsInOrder, recyclerView, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showVideoWallpaperMenuDialog$72(String str, DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            this.isAppendVideoWallpaperMode = true;
            startPickVideoWallpaper();
        } else if (i7 == 1) {
            this.isAppendVideoWallpaperMode = false;
            startPickVideoWallpaper();
        } else {
            if (i7 != 2) {
                return;
            }
            showManageVideoWallpapersDialog(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showWallpaperMenuDialog$131(DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            this.isAppendWallpaperMode = true;
            startPickWallpaper();
        } else if (i7 == 1) {
            this.isAppendWallpaperMode = false;
            startPickWallpaper();
        } else {
            if (i7 != 2) {
                return;
            }
            showManageWallpapersDialog(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$startWallpaperProcessing$138(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$startWallpaperProcessing$139(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$submitManagedVideoThumbnailTask$78(Runnable runnable, Future[] futureArr, List list) {
        try {
            if (!Thread.currentThread().isInterrupted()) {
                runnable.run();
            }
        } finally {
            Future future = futureArr[0];
            if (future != null) {
                list.remove(future);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateMixedWallpaperAvailability$90(File file, String str) {
        return str.startsWith("desktop_wallpaper") && str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateMixedWallpaperAvailability$91(File file, String str) {
        return str.endsWith(".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePermissionStatus$7(boolean z6) {
        this.permissionStatusContainer.setVisibility(z6 ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadManagedVideoThumbnail(ImageView imageView, File file, int i7) {
        String absolutePath = file.getAbsolutePath();
        String buildManagedVideoThumbnailCacheKey = buildManagedVideoThumbnailCacheKey(file);
        Bitmap bitmap = this.videoManageThumbnailCache.get(buildManagedVideoThumbnailCacheKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        File managedVideoThumbnailCacheFile = getManagedVideoThumbnailCacheFile(file);
        if (managedVideoThumbnailCacheFile.exists()) {
            submitManagedVideoThumbnailTask(this.videoManageThumbDiskExecutor, this.videoManageThumbDiskTasks, new y1(this, i7, managedVideoThumbnailCacheFile, buildManagedVideoThumbnailCacheKey, absolutePath, imageView, file));
        } else {
            generateManagedVideoThumbnail(imageView, file, managedVideoThumbnailCacheFile, absolutePath, buildManagedVideoThumbnailCacheKey, i7);
        }
    }

    private String md5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b7 : digest) {
                sb.append(String.format(Locale.US, "%02x", Byte.valueOf(b7)));
            }
            return sb.toString();
        } catch (Exception unused) {
            return String.valueOf(str.hashCode());
        }
    }

    private void postToast(Context context, String str) {
        androidx.fragment.app.d0 activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new w2(this, context, str, 8));
    }

    private void postVideoWallpaperSaveResult(Activity activity, Context context, boolean z6, String str, String str2) {
        activity.runOnUiThread(new j2(this, z6, str2, context, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processNextVideoWallpaper() {
        if (!this.pendingVideoWallpapers.isEmpty()) {
            Uri poll = this.pendingVideoWallpapers.poll();
            if (poll == null) {
                processNextVideoWallpaper();
                return;
            } else {
                saveVideoWallpaper(poll);
                return;
            }
        }
        int i7 = this.processedVideoWallpaperCount;
        if (this.failedVideoWallpaperCount + i7 == 0) {
            ToastHelper.show(requireContext(), "未设置任何动态壁纸");
            return;
        }
        if (i7 > 0) {
            sendWallpaperUpdatedBroadcast();
        }
        refreshWeeklyVideoButtons();
        updateVideoWallpaperUi();
        String str = this.currentEditingVideoDay == null ? "动态壁纸设置完成" : "按周动态壁纸设置完成";
        ToastHelper.showLong(requireContext(), str + "，成功 " + this.processedVideoWallpaperCount + " 个，失败 " + this.failedVideoWallpaperCount + " 个");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processNextWallpaper() {
        if (!this.pendingWallpapers.isEmpty()) {
            Uri poll = this.pendingWallpapers.poll();
            if (poll == null) {
                processNextWallpaper();
                return;
            } else {
                checkResolutionAndProcess(poll);
                return;
            }
        }
        if (this.processedWallpaperCount > 0) {
            ToastHelper.show(requireContext(), "壁纸设置成功 (" + this.processedWallpaperCount + "张)");
            Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
            intent.setPackage(requireContext().getPackageName());
            requireContext().sendBroadcast(intent);
            if (this.currentEditingDay != null) {
                refreshWeeklyButtons();
            }
        } else {
            ToastHelper.show(requireContext(), "未设置任何壁纸");
        }
        checkWallpaperCount();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        if (r10 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0032, code lost:
    
        if (r10 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0034, code lost:
    
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0045, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String queryDisplayName(android.net.Uri r10) {
        /*
            r9 = this;
            java.lang.String r0 = "_display_name"
            r1 = 0
            android.content.Context r2 = r9.requireContext()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r2 = 1
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r2 = 0
            r5[r2] = r0     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            r6 = 0
            r7 = 0
            r8 = 0
            r4 = r10
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3a
            if (r10 == 0) goto L32
            boolean r2 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L42
            if (r2 == 0) goto L32
            int r0 = r10.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L42
            if (r0 < 0) goto L32
            java.lang.String r0 = r10.getString(r0)     // Catch: java.lang.Throwable -> L2f java.lang.Exception -> L42
            r10.close()
            return r0
        L2f:
            r0 = move-exception
            r1 = r10
            goto L3c
        L32:
            if (r10 == 0) goto L45
        L34:
            r10.close()
            goto L45
        L38:
            r0 = move-exception
            goto L3c
        L3a:
            r10 = r1
            goto L42
        L3c:
            if (r1 == 0) goto L41
            r1.close()
        L41:
            throw r0
        L42:
            if (r10 == 0) goto L45
            goto L34
        L45:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.DesktopAssistantFragment.queryDisplayName(android.net.Uri):java.lang.String");
    }

    private void refreshWeeklyButtons() {
        if (setupWeeklyButton(this.btnWallMon, "monday", "周一") + setupWeeklyButton(this.btnWallTue, "tuesday", "周二") + setupWeeklyButton(this.btnWallWed, "wednesday", "周三") + setupWeeklyButton(this.btnWallThu, "thursday", "周四") + setupWeeklyButton(this.btnWallFri, "friday", "周五") + setupWeeklyButton(this.btnWallSat, "saturday", "周六") + setupWeeklyButton(this.btnWallSun, "sunday", "周日") > 0) {
            this.btnClearWeekly.setVisibility(0);
            a.a.s(this, 17, this.btnClearWeekly);
        } else {
            this.btnClearWeekly.setVisibility(8);
        }
        checkWallpaperCount();
    }

    private void refreshWeeklyVideoButtons() {
        if (setupWeeklyVideoButton(this.btnVideoWallMon, "monday", "周一") + setupWeeklyVideoButton(this.btnVideoWallTue, "tuesday", "周二") + setupWeeklyVideoButton(this.btnVideoWallWed, "wednesday", "周三") + setupWeeklyVideoButton(this.btnVideoWallThu, "thursday", "周四") + setupWeeklyVideoButton(this.btnVideoWallFri, "friday", "周五") + setupWeeklyVideoButton(this.btnVideoWallSat, "saturday", "周六") + setupWeeklyVideoButton(this.btnVideoWallSun, "sunday", "周日") > 0) {
            this.btnClearWeeklyVideo.setVisibility(0);
            a.a.s(this, 2, this.btnClearWeeklyVideo);
        } else {
            this.btnClearWeeklyVideo.setVisibility(8);
        }
        updateVideoWallpaperUi();
    }

    private void registerMusicSourceUiReceiverIfNeeded() {
        if (!isAdded() || this.isMusicSourceUiReceiverRegistered) {
            return;
        }
        if (this.musicSourceUiReceiver == null) {
            this.musicSourceUiReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    DesktopAssistantFragment.this.syncMusicSourceUiFromPrefs();
                }
            };
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DesktopAssistantMusicUiHelper.ACTION_MUSIC_APP_UPDATED);
        intentFilter.addAction(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED);
        if (Build.VERSION.SDK_INT >= 33) {
            requireContext().registerReceiver(this.musicSourceUiReceiver, intentFilter, 4);
        } else {
            requireContext().registerReceiver(this.musicSourceUiReceiver, intentFilter);
        }
        this.isMusicSourceUiReceiverRegistered = true;
    }

    private void removeAppFromAutoStart(String str) {
        removeAppFromAutoStart(str, true);
    }

    private void removeAppFromAutoStart(String str, boolean z6) {
        if (str == null || str.isEmpty() || !SharedPreferencesUtils.isAutoStartAppEnabled(requireContext(), str)) {
            return;
        }
        SharedPreferencesUtils.removeAutoStartApp(requireContext(), str);
        if (z6) {
            ToastHelper.show(requireContext(), "已取消 " + getAppName(str) + " 的自启设置");
        }
    }

    private int resolveMusicSourceFromPackage(String str) {
        if ("com.tencent.qqmusiccar".equals(str)) {
            return 0;
        }
        if (cn.kuwo.autosdk.m.PREFERENCE_PACKAGE.equals(str)) {
            String packageVersionName = getPackageVersionName(str);
            if (packageVersionName.startsWith("6")) {
                return 1;
            }
            return packageVersionName.startsWith("7") ? 4 : 6;
        }
        if ("com.kugou.android.auto".equals(str)) {
            return 2;
        }
        if ("com.netease.cloudmusic.iot".equals(str)) {
            return 3;
        }
        return "com.luna.music.car".equals(str) ? 5 : 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreAppIcon(AppInfo appInfo) {
        this.prefs.edit().remove("gesture_panel_app_icon_" + appInfo.getPackageName()).apply();
        PanelAppSettingsAdapter panelAppSettingsAdapter = this.desktopAppSettingsAdapter;
        if (panelAppSettingsAdapter != null) {
            panelAppSettingsAdapter.notifyDataSetChanged();
        }
        requireContext().sendBroadcast(new Intent("com.navi.tool.DESKTOP_APPS_UPDATED"));
        requireContext().sendBroadcast(new Intent("com.navi.tool.DOCK_APPS_UPDATED"));
        ToastHelper.show(requireContext(), "图标已恢复");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDesktopAppOrder(List<String> list, String str) {
        if (list == null || list.isEmpty()) {
            this.prefs.edit().remove(str).apply();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i7 = 0; i7 < list.size(); i7++) {
            if (i7 > 0) {
                sb.append(',');
            }
            sb.append(list.get(i7));
        }
        this.prefs.edit().putString(str, sb.toString()).apply();
        Intent intent = new Intent("com.navi.tool.DESKTOP_APPS_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
        if (str.contains("dock")) {
            Intent intent2 = new Intent("com.navi.tool.DOCK_APPS_UPDATED");
            intent2.setPackage(requireContext().getPackageName());
            requireContext().sendBroadcast(intent2);
        }
    }

    private void saveManagedThumbnailFile(File file, Bitmap bitmap) {
        try {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } finally {
            }
        } catch (Exception unused) {
        }
    }

    private void saveSingleWallpaper(Uri uri) {
        File file;
        String str;
        if (this.currentEditingDay == null) {
            this.currentEditingDay = this.prefs.getString("temp_wallpaper_editing_day", null);
        }
        if (this.currentEditingDay != null) {
            file = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper/" + this.currentEditingDay);
        } else {
            file = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper");
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            InputStream openInputStream = requireContext().getContentResolver().openInputStream(uri);
            if (this.processedWallpaperCount == 0) {
                str = "desktop_wallpaper.png";
            } else {
                str = "desktop_wallpaper" + (this.processedWallpaperCount + 1) + ".png";
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, str));
            byte[] bArr = new byte[8192];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.close();
                    openInputStream.close();
                    this.processedWallpaperCount++;
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            ToastHelper.show(requireContext(), "保存壁纸失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveTopInfoItemOrder(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String str : list) {
                if (isTopInfoSupportedInCurrentMode(str)) {
                    arrayList.add(str);
                }
            }
        }
        if (arrayList.isEmpty()) {
            this.prefs.edit().remove("top_info_item_order").apply();
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                if (i7 > 0) {
                    sb.append(',');
                }
                sb.append((String) arrayList.get(i7));
            }
            this.prefs.edit().putString("top_info_item_order", sb.toString()).apply();
        }
        a.a.w("com.navi.tool.TOP_INFO_AREA_UPDATED", requireContext());
    }

    private void saveVideoWallpaper(Uri uri) {
        Context context = getContext();
        androidx.fragment.app.d0 activity = getActivity();
        if (context == null || activity == null) {
            processNextVideoWallpaper();
            return;
        }
        String string = this.prefs.getString("temp_video_wallpaper_editing_day", this.currentEditingVideoDay);
        this.currentEditingVideoDay = string;
        if (isSupportedVideoUri(uri)) {
            new Thread(new d(this, context, uri, activity, new File(getVideoWallpaperDir(string), buildVideoWallpaperFileName(this.nextVideoWallpaperIndex)), string, context.getContentResolver(), 1), "save-video-wallpaper").start();
        } else {
            this.failedVideoWallpaperCount++;
            postToast(context, "仅支持 MP4 格式的动态壁纸");
            processNextVideoWallpaper();
        }
    }

    private void saveVideoWallpaperOrder(List<File> list, String str) {
        if (list == null || list.isEmpty()) {
            refreshWeeklyVideoButtons();
            updateVideoWallpaperUi();
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            File parentFile = list.get(0).getParentFile();
            if (parentFile == null) {
                return;
            }
            for (int i7 = 0; i7 < list.size(); i7++) {
                File file = list.get(i7);
                File file2 = new File(parentFile, "temp_video_reorder_" + System.currentTimeMillis() + "_" + i7 + ".mp4");
                if (file.renameTo(file2)) {
                    arrayList.add(file2);
                } else {
                    arrayList.add(file);
                }
            }
            boolean z6 = true;
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                if (!((File) arrayList.get(i8)).renameTo(new File(parentFile, buildVideoWallpaperFileName(i8)))) {
                    z6 = false;
                }
            }
            if (!z6) {
                ToastHelper.show(requireContext(), "部分动态壁纸排序保存失败");
                return;
            }
            sendWallpaperUpdatedBroadcast();
            refreshWeeklyVideoButtons();
            updateVideoWallpaperUi();
        } catch (Exception e7) {
            e7.printStackTrace();
            ToastHelper.show(requireContext(), "保存动态壁纸顺序出错: " + e7.getMessage());
        }
    }

    private void saveWallpaperOrder(List<File> list, String str) {
        if (list == null || list.isEmpty()) {
            if (str != null) {
                refreshWeeklyButtons();
                return;
            } else {
                checkWallpaperCount();
                return;
            }
        }
        try {
            ArrayList arrayList = new ArrayList();
            File parentFile = list.get(0).getParentFile();
            if (parentFile == null) {
                return;
            }
            for (int i7 = 0; i7 < list.size(); i7++) {
                File file = list.get(i7);
                File file2 = new File(parentFile, "temp_reorder_" + System.currentTimeMillis() + "_" + i7 + ".png");
                if (file.renameTo(file2)) {
                    arrayList.add(file2);
                } else {
                    arrayList.add(file);
                }
            }
            boolean z6 = true;
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                if (!((File) arrayList.get(i8)).renameTo(new File(parentFile, i8 == 0 ? "desktop_wallpaper.png" : "desktop_wallpaper" + (i8 + 1) + ".png"))) {
                    z6 = false;
                }
            }
            if (!z6) {
                ToastHelper.show(requireContext(), "部分壁纸排序保存失败");
                return;
            }
            Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
            intent.setPackage(requireContext().getPackageName());
            requireContext().sendBroadcast(intent);
            if (str != null) {
                refreshWeeklyButtons();
            } else {
                checkWallpaperCount();
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            ToastHelper.show(requireContext(), "保存壁纸顺序出错: " + e7.getMessage());
        }
    }

    private void saveWallpapers(List<Uri> list) {
        startWallpaperProcessing(list);
    }

    private void sendRoundCornerUpdatedBroadcast() {
        Intent intent = new Intent("com.navi.tool.DESKTOP_ROUND_CORNER_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
    }

    private void sendWallpaperUpdatedBroadcast() {
        Intent intent = new Intent("com.navi.tool.WALLPAPER_UPDATED");
        intent.setPackage(requireContext().getPackageName());
        requireContext().sendBroadcast(intent);
    }

    private void setMusicSpectrumCheckedSilently(boolean z6) {
        SwitchMaterial switchMaterial = this.switchMusicSpectrumEffect;
        if (switchMaterial == null) {
            return;
        }
        switchMaterial.setOnCheckedChangeListener(null);
        this.switchMusicSpectrumEffect.setChecked(z6);
        this.switchMusicSpectrumEffect.setOnCheckedChangeListener(this.musicSpectrumCheckedChangeListener);
    }

    private void setRealMusicSpectrumCheckedSilently(boolean z6) {
        SwitchMaterial switchMaterial = this.switchRealMusicSpectrum;
        if (switchMaterial == null) {
            return;
        }
        switchMaterial.setOnCheckedChangeListener(null);
        this.switchRealMusicSpectrum.setChecked(z6);
        this.switchRealMusicSpectrum.setOnCheckedChangeListener(this.realMusicSpectrumCheckedChangeListener);
    }

    private int setupWeeklyButton(Button button, final String str, final String str2) {
        File file = new File(Environment.getExternalStorageDirectory(), a.a.B("navitool/wallpaper/", str));
        final File[] listFiles = file.exists() ? file.listFiles(new s0(8)) : null;
        int length = listFiles != null ? listFiles.length : 0;
        StringBuilder j7 = a.a.j(str2);
        j7.append(length > 0 ? a.a.e("(", length, ")") : s5.h0.FRAGMENT_ENCODE_SET);
        button.setText(j7.toString());
        final int i7 = length;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.extra.i2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DesktopAssistantFragment.this.lambda$setupWeeklyButton$81(str, i7, str2, listFiles, view);
            }
        });
        return length;
    }

    private int setupWeeklyVideoButton(Button button, String str, String str2) {
        int videoWallpaperCount = getVideoWallpaperCount(str);
        boolean z6 = videoWallpaperCount > 0;
        StringBuilder j7 = a.a.j(str2);
        j7.append(z6 ? a.a.e("(", videoWallpaperCount, ")") : s5.h0.FRAGMENT_ENCODE_SET);
        button.setText(j7.toString());
        button.setOnClickListener(new o5(this, str, z6, str2, 2));
        return videoWallpaperCount;
    }

    private void showAdjustTopInfoDialog() {
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(1);
        int i7 = (int) (getResources().getDisplayMetrics().density * 8.0f);
        linearLayout.setPadding(i7, i7, i7, i7);
        TextView textView = new TextView(requireContext());
        textView.setText("长按拖动排序，点击名称可修改");
        textView.setTextSize(2, 13.0f);
        textView.setTextColor(-7829368);
        int i8 = 0;
        textView.setPadding(0, i7, 0, i7);
        linearLayout.addView(textView);
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        if (recyclerView.getItemDecorationCount() == 0) {
            DialogListDividers.applyAppList(recyclerView);
        }
        linearLayout.addView(recyclerView, new LinearLayout.LayoutParams(-1, -2));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("调整展示信息");
        nVar.u(linearLayout);
        nVar.o("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnDismissListener(new o2(this, i8));
        e7.show();
        new Thread(new p2(this, recyclerView, i8)).start();
    }

    @Deprecated
    private void showAppSelectionDialog() {
        showAppSelectionDialog(false, 1);
    }

    private void showAppSelectionDialog(final boolean z6, int i7) {
        final boolean z7 = !z6 && i7 == 2;
        final ProgressDialog progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("正在加载应用列表...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final Context applicationContext = requireContext().getApplicationContext();
        final String string = z7 ? this.prefs.getString(PREF_KEY_MAP_PACKAGE, null) : null;
        new Thread(new Runnable() { // from class: com.navi.tool.ui.extra.h2
            @Override // java.lang.Runnable
            public final void run() {
                DesktopAssistantFragment.this.lambda$showAppSelectionDialog$95(applicationContext, string, progressDialog, z6, z7);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChangeNameDialog(AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter) {
        EditText editText = new EditText(requireContext());
        editText.setSingleLine(true);
        editText.setText(this.prefs.getString("app_custom_name_" + appInfo.getPackageName(), appInfo.getAppName()));
        editText.setSelectAllOnFocus(true);
        FrameLayout frameLayout = new FrameLayout(requireContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 20.0f);
        layoutParams.rightMargin = (int) (getResources().getDisplayMetrics().density * 20.0f);
        frameLayout.addView(editText, layoutParams);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("修改应用名称");
        nVar.u(frameLayout);
        nVar.r("确定", new a2(this, editText, appInfo, panelAppSettingsAdapter, 1));
        nVar.o("取消", null);
        nVar.p("恢复默认", new b2(this, appInfo, panelAppSettingsAdapter, 1));
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        editText.requestFocus();
        if (e7.getWindow() != null) {
            e7.getWindow().setSoftInputMode(5);
        }
        editText.post(new c(editText, 2));
        DialogImeNavBarHelper.install(e7, editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChangeTopInfoNameDialog(AppInfo appInfo, PanelAppSettingsAdapter panelAppSettingsAdapter) {
        EditText editText = new EditText(requireContext());
        int i7 = 1;
        editText.setSingleLine(true);
        editText.setText(this.prefs.getString("top_info_item_name_" + appInfo.getPackageName(), appInfo.getAppName()));
        editText.setSelectAllOnFocus(true);
        FrameLayout frameLayout = new FrameLayout(requireContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 20.0f);
        layoutParams.rightMargin = (int) (getResources().getDisplayMetrics().density * 20.0f);
        frameLayout.addView(editText, layoutParams);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("修改显示名称");
        nVar.u(frameLayout);
        nVar.r("确定", new a2(this, editText, appInfo, panelAppSettingsAdapter, 0));
        nVar.o("取消", null);
        nVar.p("恢复默认", new b2(this, appInfo, panelAppSettingsAdapter, 0));
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        editText.requestFocus();
        if (e7.getWindow() != null) {
            e7.getWindow().setSoftInputMode(5);
        }
        editText.post(new c(editText, i7));
        DialogImeNavBarHelper.install(e7, editText);
    }

    private void showManageVideoWallpapersDialog(String str) {
        List<File> videoWallpaperFiles = getVideoWallpaperFiles(str);
        if (videoWallpaperFiles.isEmpty()) {
            ToastHelper.show(requireContext(), "当前没有动态壁纸");
            return;
        }
        final ArrayList arrayList = new ArrayList(videoWallpaperFiles);
        final boolean[] zArr = {false};
        cancelManagedVideoThumbnailTasks();
        int incrementAndGet = this.videoManageThumbSessionToken.incrementAndGet();
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new GridLayoutManager(3));
        int i7 = (int) (getResources().getDisplayMetrics().density * 8.0f);
        recyclerView.setPadding(i7, i7, i7, i7);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("管理动态壁纸 (长按拖动排序)");
        nVar.u(recyclerView);
        nVar.r("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnDismissListener(new k2(this, zArr, arrayList, str, 0));
        final AnonymousClass12 anonymousClass12 = new AnonymousClass12(arrayList, incrementAndGet, zArr, e7);
        new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.l0() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.13
            @Override // androidx.recyclerview.widget.l0
            public int getMovementFlags(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var) {
                return androidx.recyclerview.widget.l0.makeMovementFlags(15, 0);
            }

            @Override // androidx.recyclerview.widget.l0
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                int adapterPosition = j2Var.getAdapterPosition();
                int adapterPosition2 = j2Var2.getAdapterPosition();
                if (adapterPosition < adapterPosition2) {
                    int i8 = adapterPosition;
                    while (i8 < adapterPosition2) {
                        int i9 = i8 + 1;
                        Collections.swap(arrayList, i8, i9);
                        i8 = i9;
                    }
                } else {
                    for (int i10 = adapterPosition; i10 > adapterPosition2; i10--) {
                        Collections.swap(arrayList, i10, i10 - 1);
                    }
                }
                anonymousClass12.notifyItemMoved(adapterPosition, adapterPosition2);
                zArr[0] = true;
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i8) {
            }
        }).f(recyclerView);
        recyclerView.setAdapter(anonymousClass12);
        e7.show();
    }

    private void showManageWallpapersDialog(String str) {
        File file = str != null ? new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper/".concat(str)) : new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper");
        int i7 = 15;
        File[] listFiles = str != null ? file.listFiles(new s0(i7)) : file.listFiles(new s0(16));
        if (listFiles == null || listFiles.length == 0) {
            ToastHelper.show(requireContext(), "当前没有壁纸");
            return;
        }
        Arrays.sort(listFiles, new x(i7));
        final ArrayList arrayList = new ArrayList(Arrays.asList(listFiles));
        final boolean[] zArr = {false};
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new GridLayoutManager(3));
        int i8 = (int) (getResources().getDisplayMetrics().density * 8.0f);
        recyclerView.setPadding(i8, i8, i8, i8);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("管理壁纸 (长按拖动排序)");
        nVar.u(recyclerView);
        nVar.r("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnDismissListener(new k2(this, zArr, arrayList, str, 1));
        final AnonymousClass21 anonymousClass21 = new AnonymousClass21(arrayList, zArr, e7);
        new androidx.recyclerview.widget.p0(new androidx.recyclerview.widget.l0() { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.22
            @Override // androidx.recyclerview.widget.l0
            public void clearView(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var) {
                super.clearView(recyclerView2, j2Var);
                j2Var.itemView.setAlpha(1.0f);
                j2Var.itemView.setScaleX(1.0f);
                j2Var.itemView.setScaleY(1.0f);
            }

            @Override // androidx.recyclerview.widget.l0
            public int getMovementFlags(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var) {
                return androidx.recyclerview.widget.l0.makeMovementFlags(15, 0);
            }

            @Override // androidx.recyclerview.widget.l0
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public boolean onMove(RecyclerView recyclerView2, androidx.recyclerview.widget.j2 j2Var, androidx.recyclerview.widget.j2 j2Var2) {
                int adapterPosition = j2Var.getAdapterPosition();
                int adapterPosition2 = j2Var2.getAdapterPosition();
                if (adapterPosition < adapterPosition2) {
                    int i9 = adapterPosition;
                    while (i9 < adapterPosition2) {
                        int i10 = i9 + 1;
                        Collections.swap(arrayList, i9, i10);
                        i9 = i10;
                    }
                } else {
                    for (int i11 = adapterPosition; i11 > adapterPosition2; i11--) {
                        Collections.swap(arrayList, i11, i11 - 1);
                    }
                }
                anonymousClass21.notifyItemMoved(adapterPosition, adapterPosition2);
                zArr[0] = true;
                return true;
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSelectedChanged(androidx.recyclerview.widget.j2 j2Var, int i9) {
                super.onSelectedChanged(j2Var, i9);
                if (i9 != 2 || j2Var == null) {
                    return;
                }
                j2Var.itemView.setAlpha(0.7f);
                j2Var.itemView.setScaleX(1.1f);
                j2Var.itemView.setScaleY(1.1f);
            }

            @Override // androidx.recyclerview.widget.l0
            public void onSwiped(androidx.recyclerview.widget.j2 j2Var, int i9) {
            }
        }).f(recyclerView);
        recyclerView.setAdapter(anonymousClass21);
        e7.show();
    }

    private void showSelectSystemAppsDialog() {
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(1);
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        linearLayout.addView(recyclerView, new LinearLayout.LayoutParams(-1, -2));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("选择要在桌面显示的系统应用");
        nVar.u(linearLayout);
        nVar.o("关闭", null);
        nVar.e().show();
        new Thread(new n2(this, requireContext().getApplicationContext(), recyclerView, 1)).start();
    }

    private void showSelectTopInfoDialog() {
        LinearLayout linearLayout = new LinearLayout(requireContext());
        int i7 = 1;
        linearLayout.setOrientation(1);
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        if (recyclerView.getItemDecorationCount() == 0) {
            DialogListDividers.applyAppList(recyclerView);
        }
        linearLayout.addView(recyclerView, new LinearLayout.LayoutParams(-1, -2));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("选择展示信息");
        nVar.u(linearLayout);
        nVar.o("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnDismissListener(new o2(this, i7));
        e7.show();
        new Thread(new p2(this, recyclerView, i7)).start();
    }

    private void showSetDesktopAppsDialog() {
        LinearLayout linearLayout = new LinearLayout(requireContext());
        int i7 = 1;
        linearLayout.setOrientation(1);
        int i8 = (int) (getResources().getDisplayMetrics().density * 8.0f);
        linearLayout.setPadding(i8, i8, i8, i8);
        LinearLayout linearLayout2 = new LinearLayout(requireContext());
        linearLayout2.setOrientation(1);
        LinearLayout linearLayout3 = new LinearLayout(requireContext());
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        TextView textView = new TextView(requireContext());
        textView.setText("图标大小:");
        linearLayout3.addView(textView);
        RadioGroup radioGroup = new RadioGroup(requireContext());
        radioGroup.setOrientation(0);
        RadioButton radioButton = new RadioButton(requireContext());
        radioButton.setText("76px");
        RadioButton radioButton2 = new RadioButton(requireContext());
        radioButton2.setText("60px");
        radioGroup.addView(radioButton);
        radioGroup.addView(radioButton2);
        linearLayout3.addView(radioGroup);
        linearLayout2.addView(linearLayout3);
        LinearLayout linearLayout4 = new LinearLayout(requireContext());
        linearLayout4.setOrientation(0);
        linearLayout4.setGravity(16);
        TextView textView2 = new TextView(requireContext());
        textView2.setText("文字颜色:");
        linearLayout4.addView(textView2);
        RadioGroup radioGroup2 = new RadioGroup(requireContext());
        radioGroup2.setOrientation(0);
        RadioButton radioButton3 = new RadioButton(requireContext());
        radioButton3.setText("白色");
        RadioButton radioButton4 = new RadioButton(requireContext());
        radioButton4.setText("黑色");
        radioGroup2.addView(radioButton3);
        radioGroup2.addView(radioButton4);
        linearLayout4.addView(radioGroup2);
        linearLayout2.addView(linearLayout4);
        linearLayout.addView(linearLayout2);
        if (this.prefs.getInt("desktop_app_icon_size", 76) == 60) {
            radioButton2.setChecked(true);
        } else {
            radioButton.setChecked(true);
        }
        if (this.prefs.getInt("desktop_app_text_color", 0) == 1) {
            radioButton4.setChecked(true);
        } else {
            radioButton3.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new m2(this, radioButton2, i7));
        int i9 = 2;
        radioGroup2.setOnCheckedChangeListener(new m2(this, radioButton4, i9));
        TextView textView3 = new TextView(requireContext());
        textView3.setText("长按应用所在行，拖动可排序");
        textView3.setTextSize(2, 13.0f);
        textView3.setTextColor(-7829368);
        textView3.setPadding(0, i8, 0, i8);
        linearLayout.addView(textView3);
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        linearLayout.addView(recyclerView, new LinearLayout.LayoutParams(-1, -2));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("设置桌面应用");
        nVar.u(linearLayout);
        nVar.o("关闭", null);
        nVar.e().show();
        new Thread(new n2(this, requireContext().getApplicationContext(), recyclerView, i9)).start();
    }

    private void showSetDockAppsDialog() {
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(1);
        int i7 = (int) (getResources().getDisplayMetrics().density * 8.0f);
        linearLayout.setPadding(i7, i7, i7, i7);
        LinearLayout linearLayout2 = new LinearLayout(requireContext());
        linearLayout2.setOrientation(1);
        LinearLayout linearLayout3 = new LinearLayout(requireContext());
        int i8 = 0;
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        TextView textView = new TextView(requireContext());
        textView.setText("应用名称:");
        linearLayout3.addView(textView);
        RadioGroup radioGroup = new RadioGroup(requireContext());
        radioGroup.setOrientation(0);
        RadioButton radioButton = new RadioButton(requireContext());
        radioButton.setText("显示");
        RadioButton radioButton2 = new RadioButton(requireContext());
        radioButton2.setText("不显示");
        radioGroup.addView(radioButton);
        radioGroup.addView(radioButton2);
        linearLayout3.addView(radioGroup);
        linearLayout2.addView(linearLayout3);
        linearLayout.addView(linearLayout2);
        if (this.prefs.getBoolean("dock_app_show_name", true)) {
            radioButton.setChecked(true);
        } else {
            radioButton2.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new m2(this, radioButton, i8));
        TextView textView2 = new TextView(requireContext());
        textView2.setText("开启开关的应用将在DOCK栏显示，长按拖动排序");
        textView2.setTextSize(2, 13.0f);
        textView2.setTextColor(-7829368);
        textView2.setPadding(0, i7, 0, i7);
        linearLayout.addView(textView2);
        RecyclerView recyclerView = new RecyclerView(requireContext());
        requireContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(1));
        DialogListDividers.applyAppList(recyclerView);
        linearLayout.addView(recyclerView, new LinearLayout.LayoutParams(-1, -2));
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("设置DOCK应用");
        nVar.u(linearLayout);
        nVar.o("关闭", null);
        nVar.e().show();
        new Thread(new n2(this, requireContext().getApplicationContext(), recyclerView, i8)).start();
    }

    private void showVideoWallpaperMenuDialog(int i7, String str) {
        String str2 = str == null ? "动态壁纸设置" : "按周动态壁纸设置";
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t(str2 + " (当前" + i7 + "个)");
        nVar.l(new String[]{"➕ 追加壁纸", "🔄 重新设置", "🗑️ 管理壁纸"}, new c1(5, this, str));
        nVar.v();
    }

    private void showWallpaperMenuDialog(int i7) {
        String[] strArr = i7 > 1 ? new String[]{"➕ 追加壁纸", "🔄 重新设置", "🗑️ 管理壁纸"} : new String[]{"➕ 追加壁纸", "🔄 重新设置", "🗑️ 管理壁纸"};
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(requireContext());
        nVar.t("壁纸设置 (当前" + i7 + "张)");
        nVar.l(strArr, new z1(this, 0));
        nVar.v();
    }

    private void startChooseCustomCarModel() {
        ensureCarLogoFolderExists();
        Intent intent = new Intent(requireContext(), (Class<?>) FileManagerActivity.class);
        intent.setAction(FileManagerActivity.ACTION_PICK_FILE);
        this.chooseCarModelImageLauncher.a(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startChooseIcon() {
        ensureIconFolderExists();
        Intent intent = new Intent(requireContext(), (Class<?>) FileManagerActivity.class);
        intent.setAction(FileManagerActivity.ACTION_PICK_FILE);
        this.chooseIconLauncher.a(intent);
    }

    private void startCrop(Uri uri) {
        try {
            Uri fromFile = Uri.fromFile(new File(requireContext().getCacheDir(), "crop_temp_" + System.currentTimeMillis() + ".png"));
            Bundle bundle = new Bundle();
            bundle.putString("com.yalantis.ucrop.CompressionFormatName", Bitmap.CompressFormat.PNG.name());
            bundle.putBoolean("com.yalantis.ucrop.HideBottomControls", false);
            bundle.putBoolean("com.yalantis.ucrop.FreeStyleCrop", false);
            Intent intent = new Intent();
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("com.yalantis.ucrop.InputUri", uri);
            bundle2.putParcelable("com.yalantis.ucrop.OutputUri", fromFile);
            float f7 = this.screenWidth;
            float f8 = this.screenHeight;
            bundle2.putFloat("com.yalantis.ucrop.AspectRatioX", f7);
            bundle2.putFloat("com.yalantis.ucrop.AspectRatioY", f8);
            int i7 = this.screenWidth;
            int i8 = this.screenHeight;
            if (i7 < 10) {
                i7 = 10;
            }
            if (i8 < 10) {
                i8 = 10;
            }
            bundle2.putInt("com.yalantis.ucrop.MaxSizeX", i7);
            bundle2.putInt("com.yalantis.ucrop.MaxSizeY", i8);
            bundle2.putAll(bundle);
            intent.setClass(requireContext(), UCropActivity.class);
            intent.putExtras(bundle2);
            this.cropWallpaperLauncher.a(intent);
        } catch (Exception e7) {
            e7.printStackTrace();
            ToastHelper.show(requireContext(), "启动裁剪失败: " + e7.getMessage());
            processNextWallpaper();
        }
    }

    private void startPickVideoWallpaper() {
        Intent intent = new Intent(requireContext(), (Class<?>) FileManagerActivity.class);
        intent.setAction(FileManagerActivity.ACTION_PICK_FILE);
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        this.pickVideoWallpaperLauncher.a(intent);
    }

    private void startPickWallpaper() {
        Intent intent = new Intent(requireContext(), (Class<?>) FileManagerActivity.class);
        intent.setAction(FileManagerActivity.ACTION_PICK_FILE);
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        this.pickWallpaperLauncher.a(intent);
    }

    private void startVideoWallpaperProcessing(List<Uri> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        String string = this.prefs.getString("temp_video_wallpaper_editing_day", this.currentEditingVideoDay);
        this.currentEditingVideoDay = string;
        File videoWallpaperDir = getVideoWallpaperDir(string);
        if (!videoWallpaperDir.exists() && !videoWallpaperDir.mkdirs()) {
            ToastHelper.show(requireContext(), "创建动态壁纸目录失败");
            return;
        }
        if (this.isAppendVideoWallpaperMode) {
            this.nextVideoWallpaperIndex = getVideoWallpaperMaxIndex(videoWallpaperDir);
        } else {
            Iterator<File> it = getVideoWallpaperFiles(this.currentEditingVideoDay).iterator();
            while (it.hasNext()) {
                it.next().delete();
            }
            this.nextVideoWallpaperIndex = 0;
        }
        this.processedVideoWallpaperCount = 0;
        this.failedVideoWallpaperCount = 0;
        this.pendingVideoWallpapers.clear();
        this.pendingVideoWallpapers.addAll(list);
        processNextVideoWallpaper();
    }

    private void startWallpaperProcessing(List<Uri> list) {
        int i7;
        if (list == null || list.isEmpty()) {
            return;
        }
        String string = this.prefs.getString("temp_wallpaper_editing_day", null);
        this.currentEditingDay = string;
        this.isAutoCropAll = false;
        File file = string != null ? new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper/" + this.currentEditingDay) : new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (this.isAppendWallpaperMode) {
            File[] listFiles = file.listFiles(new s0(5));
            if (listFiles != null) {
                i7 = 0;
                for (File file2 : listFiles) {
                    try {
                        String replace = file2.getName().replace("desktop_wallpaper", s5.h0.FRAGMENT_ENCODE_SET).replace(".png", s5.h0.FRAGMENT_ENCODE_SET);
                        i7 = replace.isEmpty() ? Math.max(i7, 1) : Math.max(i7, Integer.parseInt(replace));
                    } catch (Exception unused) {
                    }
                }
            } else {
                i7 = 0;
            }
            this.processedWallpaperCount = i7;
        } else {
            File[] listFiles2 = file.listFiles(new s0(4));
            if (listFiles2 != null) {
                for (File file3 : listFiles2) {
                    file3.delete();
                }
            }
            this.processedWallpaperCount = 0;
        }
        this.pendingWallpapers.clear();
        this.pendingWallpapers.addAll(list);
        this.isAutoCropAll = false;
        processNextWallpaper();
    }

    private void stopMusicServices() {
        if (getContext() == null) {
            return;
        }
        QQMusicManager.getInstance(requireContext()).stop();
        KuwoMusicManager.getInstance(requireContext()).stop();
        KugouMusicManager.getInstance(requireContext()).stop();
        NeteaseMusicManager.getInstance(requireContext()).stop();
        NewKuwoMusicManager.getInstance(requireContext()).stop();
        k4.a.c(requireContext()).h();
        l4.e.b(requireContext()).e();
    }

    private void submitManagedVideoThumbnailTask(ExecutorService executorService, List<Future<?>> list, Runnable runnable) {
        Future<?> submit = executorService.submit(new w2(runnable, r0, list, 7));
        Future[] futureArr = {submit};
        list.add(submit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncMusicSourceUiFromPrefs() {
        int musicSourceRadioId;
        if (!isAdded() || this.prefs == null) {
            return;
        }
        updateFloatingMusicText();
        checkAndSyncAutoLaunchFloatingApps();
        if (this.radioGroupMusicSource == null || this.radioGroupMusicSource.getCheckedRadioButtonId() == (musicSourceRadioId = getMusicSourceRadioId(this.prefs.getInt("desktop_music_source", 0)))) {
            return;
        }
        this.suppressMusicSourceRadioCallback = true;
        this.radioGroupMusicSource.check(musicSourceRadioId);
        this.suppressMusicSourceRadioCallback = false;
    }

    private void syncRoundCornerSwitchFromPrefs() {
        SharedPreferences sharedPreferences;
        if (this.switchRoundCorner == null || (sharedPreferences = this.prefs) == null) {
            return;
        }
        boolean z6 = sharedPreferences.getBoolean("desktop_round_corner_enabled", false);
        boolean z7 = this.prefs.getBoolean("desktop_cover_gradient_enabled", false);
        if (this.switchRoundCorner.isChecked() != z6) {
            this.switchRoundCorner.setChecked(z6);
        }
        this.switchRoundCorner.setEnabled(!z7);
        this.switchRoundCorner.setAlpha(z7 ? 0.5f : 1.0f);
    }

    private void toggleDesktopIcon(boolean z6) {
        try {
            PackageManager packageManager = requireContext().getPackageManager();
            ComponentName componentName = new ComponentName(requireContext(), (Class<?>) DesktopAssistantStubActivity.class);
            int i7 = z6 ? 1 : 2;
            if (packageManager.getComponentEnabledSetting(componentName) != i7) {
                packageManager.setComponentEnabledSetting(componentName, i7, 1);
                if (z6) {
                    ToastHelper.showLong(requireContext(), "桌面助手图标已启用，可能需要稍等片刻或重启启动器才能看到");
                } else {
                    ToastHelper.show(requireContext(), "桌面助手图标已隐藏");
                }
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void unregisterMusicSourceUiReceiver() {
        if (!this.isMusicSourceUiReceiverRegistered || this.musicSourceUiReceiver == null || !isAdded()) {
            this.isMusicSourceUiReceiverRegistered = false;
        } else {
            try {
                requireContext().unregisterReceiver(this.musicSourceUiReceiver);
            } catch (Exception unused) {
            }
            this.isMusicSourceUiReceiverRegistered = false;
        }
    }

    private void updateAmbientFxLabel() {
        TextView textView;
        View view = getView();
        if (view == null || this.prefs == null || (textView = (TextView) view.findViewById(g4.p.text_ambient_fx_label)) == null) {
            return;
        }
        int i7 = this.prefs.getInt("desktop_ambient_festival", 0);
        if (i7 == 0) {
            textView.setText("氛围物体");
        } else {
            textView.setText("氛围物体 · 调试 ".concat(WeatherAmbientView.g(i7)));
        }
    }

    private void updateAmbientScaleVisibility(boolean z6) {
        View view = this.layoutAmbientScale;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateCoverGradientIntensityVisibility(boolean z6) {
        View view = this.layoutCoverGradientIntensity;
        if (view != null) {
            view.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateCustomCarModelBrowseVisibility(String str) {
        Button button = this.btnBrowseCustomCarModel;
        if (button == null) {
            return;
        }
        button.setVisibility(CAR_MODEL_TYPE_CUSTOM.equals(str) ? 0 : 8);
    }

    private void updateFloatingMapText() {
        String str;
        String string = this.prefs.getString(PREF_KEY_MAP_PACKAGE, null);
        boolean z6 = (string == null || string.isEmpty()) ? false : true;
        if (z6) {
            this.btnClearFloatingMap.setVisibility(0);
            this.textFloatingMapCurrent.setText(describePackage(string));
        } else {
            this.textFloatingMapCurrent.setText("当前未设置");
            this.btnClearFloatingMap.setVisibility(8);
        }
        String string2 = this.prefs.getString(PREF_KEY_MAP_PACKAGE_2, null);
        boolean z7 = (string2 == null || string2.isEmpty()) ? false : true;
        Button button = this.btnSetFloatingMap2;
        if (button != null) {
            button.setVisibility((z6 || z7) ? 0 : 8);
        }
        Button button2 = this.btnClearFloatingMap2;
        if (button2 != null) {
            button2.setVisibility(z7 ? 0 : 8);
        }
        TextView textView = this.textFloatingMap2Current;
        if (textView != null) {
            textView.setVisibility(z7 ? 0 : 8);
            TextView textView2 = this.textFloatingMap2Current;
            if (z7) {
                str = "地图二：" + describePackage(string2);
            } else {
                str = "地图二：当前未设置";
            }
            textView2.setText(str);
        }
    }

    private void updateFloatingMusicText() {
        String string = this.prefs.getString("desktop_music_package", null);
        if (string == null) {
            this.textFloatingMusicCurrent.setText("当前未设置");
            this.btnClearFloatingMusic.setVisibility(8);
            return;
        }
        this.btnClearFloatingMusic.setVisibility(0);
        try {
            PackageManager packageManager = requireContext().getPackageManager();
            String charSequence = packageManager.getApplicationLabel(packageManager.getApplicationInfo(string, 0)).toString();
            this.textFloatingMusicCurrent.setText(charSequence + "\n" + string);
        } catch (Exception unused) {
            this.textFloatingMusicCurrent.setText(string);
        }
    }

    private void updateMapCalendarSettingVisibility(boolean z6) {
        LinearLayout linearLayout = this.layoutMapCalendarSettings;
        if (linearLayout != null) {
            linearLayout.setVisibility(z6 ? 0 : 8);
        }
        String string = this.prefs.getString(PREF_KEY_MAP_PACKAGE_2, null);
        boolean z7 = (string == null || string.isEmpty()) ? false : true;
        SwitchMaterial switchMaterial = this.switchMapCalendarCard;
        if (switchMaterial != null) {
            if (z7) {
                switchMaterial.setChecked(false);
            }
            this.switchMapCalendarCard.setEnabled(!z7);
            this.switchMapCalendarCard.setAlpha(z7 ? 0.5f : 1.0f);
        }
        if (this.switchRoundCorner != null) {
            if (z7) {
                a.a.m(this.prefs, "desktop_round_corner_enabled", false);
                this.switchRoundCorner.setChecked(false);
            }
            this.switchRoundCorner.setEnabled(!z7);
            this.switchRoundCorner.setAlpha(z7 ? 0.5f : 1.0f);
        }
    }

    private void updateMixedWallpaperAvailability() {
        File[] listFiles;
        if (this.switchMixedWallpaper == null) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/wallpaper");
        File[] listFiles2 = file.exists() ? file.listFiles(new s0(12)) : null;
        boolean z6 = false;
        int length = listFiles2 == null ? 0 : listFiles2.length;
        boolean z7 = this.prefs.getBoolean("wallpaper_weekly_enabled", false);
        String[] strArr = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        int i7 = 0;
        for (int i8 = 0; i8 < 7; i8++) {
            File file2 = new File(file, strArr[i8]);
            if (file2.exists() && (listFiles = file2.listFiles(new s0(13))) != null) {
                i7 = Math.max(i7, listFiles.length);
            }
        }
        boolean z8 = length > 0 || (z7 && i7 > 0);
        boolean z9 = getVideoWallpaperCount(null) > 0 || hasAnyWeeklyVideoWallpaper();
        if (z8 && z9) {
            z6 = true;
        }
        this.switchMixedWallpaper.setEnabled(z6);
        this.switchMixedWallpaper.setAlpha(z6 ? 1.0f : 0.5f);
        TextView textView = this.textMixedWallpaperDesc;
        if (textView != null) {
            textView.setText(z6 ? "关闭：只播放「壁纸类型」选中的那一种（图片或视频）。开启：图片和视频都播放——先把优先的那种播完，再播另一种，然后循环。默认关闭" : "当前只有图片或只有视频，无需混合，此项暂不可用");
        }
    }

    private void updateMusicClockWeatherLocationModeVisibility(boolean z6) {
        LinearLayout linearLayout = this.layoutMusicClockWeatherLocationMode;
        if (linearLayout != null) {
            linearLayout.setVisibility(z6 ? 0 : 8);
        }
    }

    private void updateMusicSpectrumSettingVisibility(boolean z6) {
        int i7 = z6 ? 0 : 8;
        View view = this.dividerMusicSpectrumReal;
        if (view != null) {
            view.setVisibility(i7);
        }
        LinearLayout linearLayout = this.layoutMusicSpectrumReal;
        if (linearLayout != null) {
            linearLayout.setVisibility(i7);
        }
        View view2 = this.dividerMusicSpectrumColorful;
        if (view2 != null) {
            view2.setVisibility(i7);
        }
        LinearLayout linearLayout2 = this.layoutMusicSpectrumColorful;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(i7);
        }
    }

    private void updatePermissionStatus() {
        boolean canDrawOverlays = Settings.canDrawOverlays(requireContext());
        boolean hasStoragePermission = hasStoragePermission();
        TextView textView = this.textOverlayPermissionStatus;
        if (textView != null) {
            textView.setText(canDrawOverlays ? "悬浮窗权限：已授予" : "悬浮窗权限：未授予");
            Context requireContext = requireContext();
            int i7 = canDrawOverlays ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj = g0.g.f6423a;
            this.textOverlayPermissionStatus.setTextColor(g0.d.a(requireContext, i7));
        }
        TextView textView2 = this.textStoragePermissionStatus;
        if (textView2 != null) {
            textView2.setText(hasStoragePermission ? "存储权限：已授予" : "存储权限：未授予");
            Context requireContext2 = requireContext();
            int i8 = hasStoragePermission ? g4.m.permission_granted : g4.m.permission_denied;
            Object obj2 = g0.g.f6423a;
            this.textStoragePermissionStatus.setTextColor(g0.d.a(requireContext2, i8));
        }
        int i9 = 1;
        boolean z6 = canDrawOverlays && hasStoragePermission;
        SwitchMaterial switchMaterial = this.switchDesktopAssistant;
        if (switchMaterial != null) {
            boolean isChecked = switchMaterial.isChecked();
            MaterialCardView materialCardView = this.permissionStatusContainer;
            if (materialCardView != null) {
                materialCardView.post(new a1(this, isChecked, i9));
            }
            if (isChecked) {
                this.switchDesktopAssistant.setEnabled(true);
            } else {
                this.switchDesktopAssistant.setEnabled(z6);
            }
        }
    }

    private void updateState(boolean z6) {
        this.layoutSettings.setVisibility(z6 ? 0 : 8);
        View view = this.dividerRoundView;
        if (view != null && this.layoutRoundCorner != null) {
            view.setVisibility(z6 ? 0 : 8);
            this.layoutRoundCorner.setVisibility(z6 ? 0 : 8);
        }
        View view2 = this.dividerCardMargin;
        if (view2 != null && this.layoutCardMargin != null) {
            view2.setVisibility(z6 ? 0 : 8);
            this.layoutCardMargin.setVisibility(z6 ? 0 : 8);
        }
        View view3 = this.dividerNightModeAlpha;
        if (view3 == null || this.layoutNightModeAlpha == null) {
            return;
        }
        view3.setVisibility(z6 ? 0 : 8);
        this.layoutNightModeAlpha.setVisibility(z6 ? 0 : 8);
    }

    private void updateTopInfoCount() {
        int i7;
        if (this.textTopInfoCount == null) {
            return;
        }
        String[] strArr = CAR_INFO_IDS;
        if (strArr != null) {
            i7 = 0;
            for (String str : strArr) {
                if (isTopInfoSupportedInCurrentMode(str)) {
                    if (this.prefs.getBoolean("top_info_item_enabled_" + str, false)) {
                        i7++;
                    }
                }
            }
        } else {
            i7 = 0;
        }
        if (i7 <= 0) {
            this.textTopInfoCount.setVisibility(8);
        } else {
            a.a.v("已选择: ", i7, "个", this.textTopInfoCount);
            this.textTopInfoCount.setVisibility(0);
        }
    }

    private void updateVideoWallpaperUi() {
        int videoWallpaperCount = getVideoWallpaperCount(null);
        int weeklyVideoWallpaperTotalCount = getWeeklyVideoWallpaperTotalCount();
        boolean z6 = true;
        boolean z7 = videoWallpaperCount > 0;
        boolean hasAnyWeeklyVideoWallpaper = hasAnyWeeklyVideoWallpaper();
        boolean z8 = this.prefs.getBoolean("wallpaper_weekly_video_enabled", false);
        if (!z7 && !hasAnyWeeklyVideoWallpaper) {
            z6 = false;
        }
        this.btnClearVideoWallpaper.setVisibility(z7 ? 0 : 8);
        this.layoutWeeklyVideoSection.setVisibility(0);
        this.layoutWeeklyVideoSettings.setVisibility(z8 ? 0 : 8);
        this.textVideoWallpaperStatus.setVisibility(z6 ? 0 : 8);
        if (z7) {
            a.a.v("动态壁纸：", videoWallpaperCount, "个", this.textVideoWallpaperStatus);
        } else if (hasAnyWeeklyVideoWallpaper) {
            a.a.v("动态壁纸：按周", weeklyVideoWallpaperTotalCount, "个", this.textVideoWallpaperStatus);
        }
    }

    private void updateVisualCarModelChildVisibility(boolean z6) {
        int i7 = z6 ? 0 : 8;
        View view = this.dividerDynamicLaneTop;
        if (view != null) {
            view.setVisibility(i7);
        }
        LinearLayout linearLayout = this.layoutDynamicLaneSettings;
        if (linearLayout != null) {
            linearLayout.setVisibility(i7);
        }
        View view2 = this.dividerDoorWindowDynamicEffectTop;
        if (view2 != null) {
            view2.setVisibility(i7);
        }
        LinearLayout linearLayout2 = this.layoutDoorWindowDynamicEffect;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(i7);
        }
    }

    private String validateVideoMetadataMessage(Context context, Uri uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(context, uri);
            String extractMetadata = mediaMetadataRetriever.extractMetadata(18);
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
            String extractMetadata3 = mediaMetadataRetriever.extractMetadata(24);
            int parseInt = extractMetadata != null ? Integer.parseInt(extractMetadata) : 0;
            int parseInt2 = extractMetadata2 != null ? Integer.parseInt(extractMetadata2) : 0;
            int parseInt3 = extractMetadata3 != null ? Integer.parseInt(extractMetadata3) : 0;
            if (parseInt3 == 90 || parseInt3 == 270) {
                int i7 = parseInt2;
                parseInt2 = parseInt;
                parseInt = i7;
            }
            if (parseInt > 0 && parseInt2 > 0) {
                if (parseInt == this.screenWidth && parseInt2 == this.screenHeight) {
                    try {
                        mediaMetadataRetriever.release();
                        return null;
                    } catch (Exception unused) {
                        return null;
                    }
                }
                String str = "动态壁纸分辨率为 " + parseInt + "x" + parseInt2 + "，与车机屏幕 " + this.screenWidth + "x" + this.screenHeight + " 不一致";
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception unused2) {
                }
                return str;
            }
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused3) {
            }
            return "无法读取视频分辨率";
        } catch (Exception unused4) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused5) {
            }
            return "读取动态壁纸信息失败";
        } catch (Throwable th) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused6) {
            }
            throw th;
        }
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(g4.q.fragment_desktop_assistant, viewGroup, false);
    }

    @Override // androidx.fragment.app.z
    public void onDestroy() {
        super.onDestroy();
        this.videoManageThumbSessionToken.incrementAndGet();
        cancelManagedVideoThumbnailTasks();
        this.videoManageThumbDiskExecutor.shutdownNow();
        this.videoManageThumbDecodeExecutor.shutdownNow();
        LruCache<String, Bitmap> lruCache = this.videoManageThumbnailCache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }

    @Override // androidx.fragment.app.z
    public void onDestroyView() {
        unregisterMusicSourceUiReceiver();
        super.onDestroyView();
        this.desktopAppSettingsAdapter = null;
    }

    @Override // androidx.fragment.app.z
    public void onPause() {
        super.onPause();
        unregisterMusicSourceUiReceiver();
    }

    @Override // androidx.fragment.app.z
    public void onRequestPermissionsResult(int i7, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i7, strArr, iArr);
        if (i7 != REQUEST_RECORD_AUDIO_PERMISSION) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0 || !this.pendingEnableRealMusicSpectrum) {
            this.pendingEnableRealMusicSpectrum = false;
            a.a.m(this.prefs, "desktop_music_real_spectrum_enabled", false);
            setRealMusicSpectrumCheckedSilently(false);
            ToastHelper.show(requireContext(), "未授予麦克风权限，无法启用真实频谱");
            return;
        }
        this.pendingEnableRealMusicSpectrum = false;
        setRealMusicSpectrumCheckedSilently(true);
        this.prefs.edit().putBoolean("desktop_music_real_spectrum_enabled", true).apply();
        requireContext().sendBroadcast(new Intent(DesktopAssistantMusicUiHelper.ACTION_MUSIC_UI_UPDATED));
        ToastHelper.show(requireContext(), "已启用真实频谱");
    }

    @Override // androidx.fragment.app.z
    public void onResume() {
        super.onResume();
        updatePermissionStatus();
        updateTopInfoCount();
        registerMusicSourceUiReceiverIfNeeded();
        syncMusicSourceUiFromPrefs();
        updateFloatingMapText();
        updateMapCalendarSettingVisibility(this.prefs.getBoolean("desktop_map_enabled", false));
        updateAmbientFxLabel();
        syncRoundCornerSwitchFromPrefs();
    }

    @Override // androidx.fragment.app.z
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 256) == 0) {
            view.setVisibility(8);
            return;
        }
        this.prefs = SharedPreferencesUtils.getAppPrefs(requireContext());
        this.videoManageThumbnailCache = new LruCache<String, Bitmap>(Math.max(1024, ((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 16)) { // from class: com.navi.tool.ui.extra.DesktopAssistantFragment.1
            @Override // android.util.LruCache
            public int sizeOf(String str, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
        initViews(view);
        initIconChooser();
        initScreenSize();
        initLogic();
        this.pickWallpaperLauncher = registerForActivityResult(new e.f(), new x1(this, 0));
        this.pickVideoWallpaperLauncher = registerForActivityResult(new e.f(), new x1(this, 1));
        this.chooseCarModelImageLauncher = registerForActivityResult(new e.f(), new x1(this, 2));
        this.cropWallpaperLauncher = registerForActivityResult(new e.f(), new x1(this, 3));
    }
}
