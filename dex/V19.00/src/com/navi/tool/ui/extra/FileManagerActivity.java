package com.navi.tool.ui.extra;

import android.R;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.LruCache;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.model.CloudDiskCredential;
import com.navi.tool.service.ConfigBackupService;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.navi.tool.ui.extra.FileManagerActivity;
import com.navi.tool.utils.AudioPlayerHelper;
import com.navi.tool.utils.Pan123Api;
import com.navi.tool.utils.ToastHelper;
import com.navi.tool.utils.ZipHelper;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class FileManagerActivity extends androidx.appcompat.app.r {
    public static final String ACTION_PICK_FILE = "com.navi.tool.ACTION_PICK_FILE";
    public static final String ACTION_PICK_FOLDER = "com.navi.tool.ACTION_PICK_FOLDER";
    private static final int CONFLICT_ACTION_OVERWRITE = 0;
    private static final int CONFLICT_ACTION_PROMPT = -1;
    private static final int CONFLICT_ACTION_RENAME = 1;
    private static final int CONFLICT_ACTION_SKIP = 2;
    private static final int FAST_PREVIEW_COUNT = 180;
    private static final int FAST_PREVIEW_THRESHOLD = 300;
    private static final int HTTP_PORT = 8080;
    private static final int HTTP_PORT_MAX_ATTEMPTS = 4;
    private static final long NETWORK_NEIGHBOR_OFFLINE_TIMEOUT_MS = 6000;
    private static final int REQUEST_CODE_PICK_FOLDER = 1001;
    private static final int SORT_BY_DATE = 2;
    private static final int SORT_BY_NAME = 0;
    private static final int SORT_BY_SIZE = 1;
    private static final int STREAM_BATCH_SIZE = 220;
    private static final long UI_UPDATE_INTERVAL = 1000;
    private View actionCancel;
    private View actionCompress;
    private View actionConfirmSelection;
    private View actionCopy;
    private View actionCut;
    private View actionDelete;
    private View actionInfo;
    private MenuItem actionNetworkNeighbor;
    private View actionRename;
    private View actionSelectAll;
    private FileAdapter adapter;
    private LruCache<String, Drawable> apkIconCache;
    private InstalledAppAdapter appAdapter;
    private AudioPlayerHelper audioPlayerHelper;
    private LinearLayout breadcrumbContainer;
    private Button btnCancelDownload;
    private Button btnCancelPaste;
    private Button btnPaste;
    private Button btnShowQrCode;
    private androidx.appcompat.app.o cloudBackupDialog;
    private TextView cloudBackupPercentText;
    private ProgressBar cloudBackupProgressBar;
    private TextView cloudBackupProgressText;
    private CloudDiskCredential currentCloudCredential;
    private File currentDir;
    private File currentZipFile;
    private File customUnzipTargetDir;
    private GridSpacingItemDecoration gridDivider;
    private q4.a httpServer;
    private boolean isNetworkNeighborVisible;
    private View layoutCatApk;
    private View layoutCatApps;
    private View layoutCatImage;
    private View layoutCatMusic;
    private View layoutCatTransfer;
    private View layoutCatVideo;
    private View layoutCatZip;
    private LinearLayout layoutCloudDisks;
    private View layoutDownloadProgress;
    private View layoutPasteActions;
    private View layoutSelectionActions;
    private LinearLayout layoutStorageVolumes;
    private androidx.recyclerview.widget.y listDivider;
    private ZipFile openZipFile;
    private File pendingUnzipFile;
    private ProgressBar progressDownload;
    private ProgressBar progressScan;
    private ProgressBar progressStorage;
    private androidx.appcompat.app.o qrDialog;
    private RecyclerView recyclerView;
    private Switch switchHttpServer;
    private Switch switchNetworkTransfer;
    private TextView textDownloadName;
    private TextView textDownloadPercent;
    private TextView textEmpty;
    private TextView textHttpServerAddress;
    private TextView textLocalIp;
    private TextView textPasteInfo;
    private TextView textStorageInfo;
    private LruCache<String, Bitmap> thumbnailCache;
    private q4.h transferManager;
    private ProgressBar transferProgressBar;
    private androidx.appcompat.app.o transferProgressDialog;
    private TextView transferProgressText;
    private boolean isCloudDiskMode = false;
    private String currentCloudParentId = "0";
    private Stack<String> cloudPathStack = new Stack<>();
    private Stack<String> cloudNameStack = new Stack<>();
    private List<Pan123Api.CloudFileInfo> cloudFiles = new ArrayList();
    private List<CloudDiskCredential> savedCloudCredentials = new ArrayList();
    private boolean cloudBackupAvailable = false;
    private int cloudBackupCredentialIndex = -1;
    private long cloudBackupCheckedAt = 0;
    private boolean cloudBackupReceiverRegistered = false;
    private final BroadcastReceiver cloudBackupReceiver = new BroadcastReceiver() { // from class: com.navi.tool.ui.extra.FileManagerActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !ConfigBackupService.ACTION_PROGRESS.equals(intent.getAction())) {
                return;
            }
            String stringExtra = intent.getStringExtra(ConfigBackupService.EXTRA_STAGE);
            int intExtra = intent.getIntExtra(ConfigBackupService.EXTRA_PERCENT, 0);
            String stringExtra2 = intent.getStringExtra(ConfigBackupService.EXTRA_MESSAGE);
            String stringExtra3 = intent.getStringExtra(ConfigBackupService.EXTRA_FILE_NAME);
            FileManagerActivity.this.updateCloudBackupProgress(stringExtra, intExtra, stringExtra2);
            if (ConfigBackupService.STAGE_DONE.equals(stringExtra)) {
                FileManagerActivity.this.dismissCloudBackupDialog();
                ToastHelper.showLong(FileManagerActivity.this, "备份完成：" + stringExtra3);
                if (FileManagerActivity.this.isCloudDiskMode) {
                    FileManagerActivity fileManagerActivity = FileManagerActivity.this;
                    fileManagerActivity.loadCloudFiles(fileManagerActivity.currentCloudParentId);
                }
                FileManagerActivity.this.invalidateOptionsMenu();
                return;
            }
            if (!ConfigBackupService.STAGE_ERROR.equals(stringExtra)) {
                if (ConfigBackupService.STAGE_CANCELLED.equals(stringExtra)) {
                    FileManagerActivity.this.dismissCloudBackupDialog();
                    ToastHelper.showLong(FileManagerActivity.this, "备份已取消");
                    FileManagerActivity.this.invalidateOptionsMenu();
                    return;
                }
                return;
            }
            FileManagerActivity.this.dismissCloudBackupDialog();
            FileManagerActivity fileManagerActivity2 = FileManagerActivity.this;
            if (stringExtra2 == null) {
                stringExtra2 = "未知错误";
            }
            ToastHelper.showLong(fileManagerActivity2, "备份失败：".concat(stringExtra2));
            FileManagerActivity.this.invalidateOptionsMenu();
        }
    };
    private boolean isZipMode = false;
    private String currentZipPath = s5.h0.FRAGMENT_ENCODE_SET;
    private Map<String, List<FileItem>> zipCache = new HashMap();
    private volatile boolean isDownloadCancelled = false;
    private final Handler networkNeighborHandler = new Handler();
    private final Runnable hideNetworkNeighborRunnable = new c4(this, 2);
    private int activeHttpServerPort = HTTP_PORT;
    private Handler transferAutoDismissHandler = new Handler();
    private File lastReceivedFile = null;
    private Runnable transferAutoDismissRunnable = new AnonymousClass2();
    private boolean isAppMode = false;
    private boolean isGridMode = false;
    private boolean isSelectionMode = false;
    private List<File> selectedFiles = new ArrayList();
    private List<File> clipboardFiles = new ArrayList();
    private boolean isCutOperation = false;
    private boolean isSearchMode = false;
    private boolean isPickFolderMode = false;
    private boolean isPickFileMode = false;
    private boolean isMultiSelectAllowed = false;
    private volatile boolean isDeletingSelected = false;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private ExecutorService downloadExecutor = Executors.newSingleThreadExecutor();
    private ExecutorService directoryLoadExecutor = Executors.newSingleThreadExecutor();
    private ExecutorService metadataExecutor = Executors.newFixedThreadPool(2);
    private ExecutorService imageExecutor = Executors.newFixedThreadPool(4);
    private final AtomicInteger directoryLoadVersion = new AtomicInteger(0);
    private final Map<String, Integer> directoryChildCountCache = new ConcurrentHashMap();
    private final Set<String> pendingDirectoryCountPaths = Collections.newSetFromMap(new ConcurrentHashMap());
    private int currentSortType = 0;
    private AtomicBoolean isScanning = new AtomicBoolean(false);
    private Map<String, List<FileItem>> globalCategoryCache = new ConcurrentHashMap();
    private String currentCategoryView = null;
    private String[] mLastScanExtensions = null;
    private long lastUiUpdateTime = 0;
    private boolean isSortAscending = true;
    private volatile int globalConflictAction = -1;
    private volatile boolean isPasteCancelled = false;

    /* renamed from: com.navi.tool.ui.extra.FileManagerActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        public AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$0(View view) {
            if (FileManagerActivity.this.lastReceivedFile != null) {
                FileManagerActivity fileManagerActivity = FileManagerActivity.this;
                fileManagerActivity.loadFiles(fileManagerActivity.lastReceivedFile.getParentFile());
            }
            FileManagerActivity.this.transferProgressDialog.dismiss();
            FileManagerActivity.this.transferProgressDialog = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$1(View view) {
            FileManagerActivity.this.transferProgressDialog.dismiss();
            FileManagerActivity.this.transferProgressDialog = null;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FileManagerActivity.this.transferProgressDialog == null || !FileManagerActivity.this.transferProgressDialog.isShowing()) {
                return;
            }
            if (FileManagerActivity.this.transferProgressText != null) {
                FileManagerActivity.this.transferProgressText.setText("所有文件接收完成");
            }
            if (FileManagerActivity.this.transferProgressBar != null) {
                FileManagerActivity.this.transferProgressBar.setProgress(100);
            }
            Button e7 = FileManagerActivity.this.transferProgressDialog.e(-1);
            Button e8 = FileManagerActivity.this.transferProgressDialog.e(-2);
            if (e7 != null) {
                e7.setText("查看");
                final int i7 = 0;
                e7.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.d4

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ FileManagerActivity.AnonymousClass2 f5110b;

                    {
                        this.f5110b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i8 = i7;
                        FileManagerActivity.AnonymousClass2 anonymousClass2 = this.f5110b;
                        switch (i8) {
                            case 0:
                                anonymousClass2.lambda$run$0(view);
                                break;
                            default:
                                anonymousClass2.lambda$run$1(view);
                                break;
                        }
                    }
                });
            }
            if (e8 != null) {
                e8.setText("关闭");
                final int i8 = 1;
                e8.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.d4

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ FileManagerActivity.AnonymousClass2 f5110b;

                    {
                        this.f5110b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i82 = i8;
                        FileManagerActivity.AnonymousClass2 anonymousClass2 = this.f5110b;
                        switch (i82) {
                            case 0:
                                anonymousClass2.lambda$run$0(view);
                                break;
                            default:
                                anonymousClass2.lambda$run$1(view);
                                break;
                        }
                    }
                });
            }
        }
    }

    /* renamed from: com.navi.tool.ui.extra.FileManagerActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements q4.g {
        public AnonymousClass4() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onConnected$1(String str) {
            FileManagerActivity.this.refreshNetworkNeighborIndicator();
            ToastHelper.showLong(FileManagerActivity.this, "已连接: " + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onDeviceAlive$0() {
            FileManagerActivity.this.refreshNetworkNeighborIndicator();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onError$6(String str) {
            ToastHelper.show(FileManagerActivity.this, "互传错误: " + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFileProgress$4(String str, int i7) {
            if (FileManagerActivity.this.isFinishing() || FileManagerActivity.this.isDestroyed()) {
                return;
            }
            FileManagerActivity.this.transferAutoDismissHandler.removeCallbacks(FileManagerActivity.this.transferAutoDismissRunnable);
            if (FileManagerActivity.this.transferProgressDialog == null || !FileManagerActivity.this.transferProgressDialog.isShowing()) {
                FileManagerActivity.this.showReceiveProgressDialog(str);
            }
            if (FileManagerActivity.this.transferProgressBar != null) {
                FileManagerActivity.this.transferProgressBar.setProgress(i7);
            }
            if (FileManagerActivity.this.transferProgressText != null) {
                FileManagerActivity.this.transferProgressText.setText("正在接收: " + str + " (" + i7 + "%)");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onFileReceived$5(File file, String str) {
            if (FileManagerActivity.this.isFinishing() || FileManagerActivity.this.isDestroyed()) {
                return;
            }
            FileManagerActivity.this.lastReceivedFile = file;
            if (FileManagerActivity.this.currentDir != null && FileManagerActivity.this.currentDir.equals(file.getParentFile())) {
                FileManagerActivity fileManagerActivity = FileManagerActivity.this;
                fileManagerActivity.loadFiles(fileManagerActivity.currentDir);
            }
            if (FileManagerActivity.this.transferProgressDialog == null || !FileManagerActivity.this.transferProgressDialog.isShowing()) {
                ToastHelper.show(FileManagerActivity.this, "已接收: " + str);
                return;
            }
            if (FileManagerActivity.this.transferProgressText != null) {
                FileManagerActivity.this.transferProgressText.setText("接收完成: " + str + "\n等待下一个文件(3秒后汇总)...");
            }
            if (FileManagerActivity.this.transferProgressBar != null) {
                FileManagerActivity.this.transferProgressBar.setProgress(100);
            }
            FileManagerActivity.this.transferAutoDismissHandler.removeCallbacks(FileManagerActivity.this.transferAutoDismissRunnable);
            FileManagerActivity.this.transferAutoDismissHandler.postDelayed(FileManagerActivity.this.transferAutoDismissRunnable, PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onMessageReceived$2(String str, DialogInterface dialogInterface, int i7) {
            ((ClipboardManager) FileManagerActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", str));
            ToastHelper.show(FileManagerActivity.this, "已复制到剪贴板");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onMessageReceived$3(String str) {
            if (FileManagerActivity.this.isFinishing() || FileManagerActivity.this.isDestroyed()) {
                return;
            }
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(FileManagerActivity.this);
            nVar.t("收到消息");
            nVar.m(str);
            nVar.r("复制", new c1(1, this, str));
            nVar.o("关闭", null);
            nVar.v();
        }

        @Override // q4.g
        public void onConnected(String str, String str2) {
            FileManagerActivity.this.runOnUiThread(new e4(this, str, 1));
        }

        @Override // q4.g
        public void onDeviceAlive(String str, String str2) {
            FileManagerActivity.this.runOnUiThread(new f4(0, this));
        }

        @Override // q4.g
        public void onDeviceFound(String str, String str2) {
        }

        @Override // q4.g
        public void onError(String str) {
            if (FileManagerActivity.this.isFinishing() || FileManagerActivity.this.isDestroyed()) {
                return;
            }
            FileManagerActivity.this.runOnUiThread(new e4(this, str, 2));
        }

        @Override // q4.g
        public void onFileProgress(String str, int i7) {
            if (FileManagerActivity.this.isFinishing() || FileManagerActivity.this.isDestroyed()) {
                return;
            }
            FileManagerActivity.this.runOnUiThread(new g4(this, str, i7, 0));
        }

        @Override // q4.g
        public void onFileReceived(String str, File file) {
            if (FileManagerActivity.this.isFinishing() || FileManagerActivity.this.isDestroyed()) {
                return;
            }
            FileManagerActivity.this.runOnUiThread(new w2(this, file, str, 1));
        }

        @Override // q4.g
        public void onMessageReceived(String str) {
            if (FileManagerActivity.this.isFinishing() || FileManagerActivity.this.isDestroyed()) {
                return;
            }
            FileManagerActivity.this.runOnUiThread(new e4(this, str, 0));
        }
    }

    /* renamed from: com.navi.tool.ui.extra.FileManagerActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements ZipHelper.ZipCallback {
        public AnonymousClass6() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onError$1(String str) {
            FileManagerActivity.this.progressScan.setVisibility(8);
            ToastHelper.showLong(FileManagerActivity.this, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onSuccess$0() {
            FileManagerActivity.this.progressScan.setVisibility(8);
            ToastHelper.show(FileManagerActivity.this, "解压成功");
            if (FileManagerActivity.this.currentDir.equals(FileManagerActivity.this.customUnzipTargetDir)) {
                FileManagerActivity fileManagerActivity = FileManagerActivity.this;
                fileManagerActivity.loadFiles(fileManagerActivity.currentDir);
            }
        }

        @Override // com.navi.tool.utils.ZipHelper.ZipCallback
        public void onError(String str) {
            FileManagerActivity.this.runOnUiThread(new z0(2, (Object) this, (Object) str));
        }

        @Override // com.navi.tool.utils.ZipHelper.ZipCallback
        public void onStart() {
            FileManagerActivity.this.progressScan.setVisibility(0);
        }

        @Override // com.navi.tool.utils.ZipHelper.ZipCallback
        public void onSuccess() {
            FileManagerActivity.this.runOnUiThread(new f4(1, this));
        }
    }

    public interface CopyProgressCallback {
        boolean onProgress(long j7);
    }

    public class FileAdapter extends androidx.recyclerview.widget.h1 {
        private SimpleDateFormat dateFormat;
        private List<FileItem> items;

        public class ViewHolder extends androidx.recyclerview.widget.j2 {
            CheckBox checkbox;
            ImageView icon;
            TextView textApkInfo;
            TextView textDate;
            TextView textName;
            TextView textSize;

            public ViewHolder(View view) {
                super(view);
                this.icon = (ImageView) view.findViewById(g4.p.file_icon);
                this.textName = (TextView) view.findViewById(g4.p.file_name);
                this.textDate = (TextView) view.findViewById(g4.p.file_date);
                this.textSize = (TextView) view.findViewById(g4.p.file_size);
                this.textApkInfo = (TextView) view.findViewById(g4.p.file_apk_info);
                this.checkbox = (CheckBox) view.findViewById(g4.p.checkbox_select);
            }
        }

        private FileAdapter() {
            this.items = new ArrayList();
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        }

        private int calculateInSampleSize(BitmapFactory.Options options, int i7, int i8) {
            int i9 = options.outHeight;
            int i10 = options.outWidth;
            int i11 = 1;
            if (i9 > i8 || i10 > i7) {
                int i12 = i9 / 2;
                int i13 = i10 / 2;
                while (i12 / i11 >= i8 && i13 / i11 >= i7) {
                    i11 *= 2;
                }
            }
            return i11;
        }

        private Bitmap decodeThumbnailFile(File file) {
            if (file != null) {
                try {
                    if (file.exists()) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                        options.inSampleSize = calculateInSampleSize(options, z1.c.AND_LONG, z1.c.INVOKE_INTERFACE_RANGE);
                        options.inJustDecodeBounds = false;
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                    }
                } catch (Exception unused) {
                }
            }
            return null;
        }

        private File getVideoThumbnailCacheFile(File file) {
            File file2 = new File(FileManagerActivity.this.getCacheDir(), "video_thumbs");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            return new File(file2, a.a.g(md5(file.getAbsolutePath() + "_" + file.lastModified()), ".jpg"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadApkInfo$10(File file, String str, ViewHolder viewHolder) {
            try {
                PackageManager packageManager = FileManagerActivity.this.getPackageManager();
                PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), 0);
                if (packageArchiveInfo != null) {
                    packageArchiveInfo.applicationInfo.sourceDir = file.getAbsolutePath();
                    packageArchiveInfo.applicationInfo.publicSourceDir = file.getAbsolutePath();
                    Drawable loadIcon = packageArchiveInfo.applicationInfo.loadIcon(packageManager);
                    String str2 = packageArchiveInfo.versionName;
                    String str3 = packageArchiveInfo.packageName;
                    if (loadIcon != null) {
                        FileManagerActivity.this.apkIconCache.put(str, loadIcon);
                    }
                    FileManagerActivity.this.runOnUiThread(new j4(viewHolder, file, loadIcon, str2, str3));
                }
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$loadApkInfo$9(ViewHolder viewHolder, File file, Drawable drawable, String str, String str2) {
            if (viewHolder.getAdapterPosition() == -1 || !viewHolder.textName.getText().equals(file.getName())) {
                return;
            }
            viewHolder.icon.setImageDrawable(drawable);
            viewHolder.textApkInfo.setVisibility(0);
            viewHolder.textApkInfo.setText(a2.m.PREFIX + str + " " + str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$loadImageThumbnail$3(ViewHolder viewHolder, File file, Bitmap bitmap) {
            if (viewHolder.getAdapterPosition() == -1 || !viewHolder.textName.getText().equals(file.getName())) {
                return;
            }
            viewHolder.icon.setImageBitmap(bitmap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadImageThumbnail$4(File file, String str, ViewHolder viewHolder) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                options.inSampleSize = calculateInSampleSize(options, 100, 100);
                options.inJustDecodeBounds = false;
                Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                if (decodeFile != null) {
                    FileManagerActivity.this.thumbnailCache.put(str, decodeFile);
                    FileManagerActivity.this.runOnUiThread(new w2(viewHolder, file, decodeFile, 2));
                }
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$loadVideoThumbnail$5(ViewHolder viewHolder, String str, Bitmap bitmap) {
            if (viewHolder.getAdapterPosition() == -1 || !str.equals(viewHolder.icon.getTag())) {
                return;
            }
            viewHolder.icon.setImageBitmap(bitmap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadVideoThumbnail$6(File file, String str, ViewHolder viewHolder) {
            Bitmap decodeThumbnailFile = decodeThumbnailFile(file);
            if (decodeThumbnailFile != null) {
                FileManagerActivity.this.thumbnailCache.put(str, decodeThumbnailFile);
                FileManagerActivity.this.runOnUiThread(new i4(viewHolder, str, decodeThumbnailFile, 0));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$loadVideoThumbnail$7(ViewHolder viewHolder, String str, Bitmap bitmap) {
            if (viewHolder.getAdapterPosition() == -1 || !str.equals(viewHolder.icon.getTag())) {
                return;
            }
            viewHolder.icon.setImageBitmap(bitmap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadVideoThumbnail$8(File file, String str, File file2, ViewHolder viewHolder) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(file.getAbsolutePath());
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(0L, 2);
                if (frameAtTime != null) {
                    int i7 = 1;
                    Bitmap createScaledBitmap = Bitmap.createScaledBitmap(frameAtTime, z1.c.AND_LONG, z1.c.INVOKE_INTERFACE_RANGE, true);
                    if (createScaledBitmap != frameAtTime) {
                        frameAtTime.recycle();
                    }
                    FileManagerActivity.this.thumbnailCache.put(str, createScaledBitmap);
                    saveThumbnailFile(file2, createScaledBitmap);
                    FileManagerActivity.this.runOnUiThread(new i4(viewHolder, str, createScaledBitmap, i7));
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (Exception unused2) {
                }
                throw th;
            }
            try {
                mediaMetadataRetriever.release();
            } catch (Exception unused3) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(ViewHolder viewHolder, FileItem fileItem, View view) {
            File file;
            Pan123Api.CloudFileInfo cloudFileInfo;
            if (FileManagerActivity.this.isCloudDiskMode) {
                int adapterPosition = viewHolder.getAdapterPosition();
                if (adapterPosition < 0 || adapterPosition >= this.items.size() || (cloudFileInfo = this.items.get(adapterPosition).cloudInfo) == null) {
                    return;
                }
                if (cloudFileInfo.isDir) {
                    FileManagerActivity.this.cloudPathStack.push(FileManagerActivity.this.currentCloudParentId);
                    FileManagerActivity.this.cloudNameStack.push(cloudFileInfo.name);
                    FileManagerActivity.this.currentCloudParentId = cloudFileInfo.fileId;
                    FileManagerActivity.this.loadCloudFiles(cloudFileInfo.fileId);
                    FileManagerActivity.this.updateCloudBreadcrumbs();
                    return;
                }
                ToastHelper.show(FileManagerActivity.this, "开始下载: " + cloudFileInfo.name);
                FileManagerActivity.this.downloadCloudFile(cloudFileInfo);
                return;
            }
            if (FileManagerActivity.this.isPickFolderMode) {
                boolean z6 = fileItem.isDirectory;
                if (z6 && !fileItem.isZipEntry && (file = fileItem.file) != null) {
                    FileManagerActivity.this.loadFiles(file);
                    return;
                } else if (!fileItem.isZipEntry) {
                    ToastHelper.show(FileManagerActivity.this, "请选择文件夹");
                    return;
                } else {
                    if (z6) {
                        FileManagerActivity.this.loadZipFolder(fileItem.path);
                        return;
                    }
                    return;
                }
            }
            if (FileManagerActivity.this.isSelectionMode && !fileItem.isZipEntry && fileItem.file != null) {
                FileManagerActivity.this.toggleSelection(viewHolder.getAdapterPosition());
                return;
            }
            if (fileItem.isDirectory) {
                if (fileItem.isZipEntry) {
                    FileManagerActivity.this.loadZipFolder(fileItem.path);
                    return;
                }
                File file2 = fileItem.file;
                if (file2 != null) {
                    FileManagerActivity.this.loadFiles(file2);
                    return;
                }
                return;
            }
            if (!FileManagerActivity.this.isPickFileMode || fileItem.isZipEntry || fileItem.file == null) {
                if (fileItem.isZipEntry) {
                    ToastHelper.show(FileManagerActivity.this, "暂不支持预览压缩包内文件");
                    return;
                }
                File file3 = fileItem.file;
                if (file3 != null) {
                    FileManagerActivity.this.openFile(file3);
                    return;
                }
                return;
            }
            Intent intent = new Intent();
            Uri fileUri = FileManagerActivity.this.getFileUri(fileItem.file);
            intent.setData(fileUri);
            intent.addFlags(1);
            intent.setClipData(ClipData.newRawUri(null, fileUri));
            FileManagerActivity.this.setResult(-1, intent);
            FileManagerActivity.this.finish();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$1(Pan123Api.CloudFileInfo cloudFileInfo, DialogInterface dialogInterface, int i7) {
            if (!cloudFileInfo.isDir || i7 != 0) {
                ToastHelper.show(FileManagerActivity.this, "开始下载: " + cloudFileInfo.name);
                FileManagerActivity.this.downloadCloudFile(cloudFileInfo);
                return;
            }
            FileManagerActivity.this.cloudPathStack.push(FileManagerActivity.this.currentCloudParentId);
            FileManagerActivity.this.cloudNameStack.push(cloudFileInfo.name);
            FileManagerActivity.this.currentCloudParentId = cloudFileInfo.fileId;
            FileManagerActivity.this.loadCloudFiles(cloudFileInfo.fileId);
            FileManagerActivity.this.updateCloudBreadcrumbs();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$onBindViewHolder$2(ViewHolder viewHolder, FileItem fileItem, View view) {
            if (!FileManagerActivity.this.isCloudDiskMode) {
                if (fileItem.isZipEntry || fileItem.file == null) {
                    return false;
                }
                if (FileManagerActivity.this.isSelectionMode) {
                    FileManagerActivity.this.toggleSelection(viewHolder.getAdapterPosition());
                } else {
                    FileManagerActivity.this.enterSelectionMode(fileItem.file);
                }
                return true;
            }
            int adapterPosition = viewHolder.getAdapterPosition();
            if (adapterPosition < 0 || adapterPosition >= FileManagerActivity.this.cloudFiles.size()) {
                return false;
            }
            Pan123Api.CloudFileInfo cloudFileInfo = (Pan123Api.CloudFileInfo) FileManagerActivity.this.cloudFiles.get(adapterPosition);
            String[] strArr = cloudFileInfo.isDir ? new String[]{"打开", "下载整个文件夹"} : new String[]{"下载"};
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(FileManagerActivity.this);
            nVar.t(cloudFileInfo.name);
            nVar.l(strArr, new c1(2, this, cloudFileInfo));
            nVar.o("取消", null);
            nVar.v();
            return true;
        }

        private void loadApkInfo(ViewHolder viewHolder, File file) {
            String absolutePath = file.getAbsolutePath();
            Drawable drawable = (Drawable) FileManagerActivity.this.apkIconCache.get(absolutePath);
            if (drawable != null) {
                viewHolder.icon.setImageDrawable(drawable);
                viewHolder.textApkInfo.setVisibility(0);
            }
            FileManagerActivity.this.executor.execute(new h4(this, file, absolutePath, viewHolder, 0));
        }

        private void loadImageThumbnail(ViewHolder viewHolder, File file) {
            String absolutePath = file.getAbsolutePath();
            Bitmap bitmap = (Bitmap) FileManagerActivity.this.thumbnailCache.get(absolutePath);
            if (bitmap != null) {
                viewHolder.icon.setImageBitmap(bitmap);
            } else {
                FileManagerActivity.this.imageExecutor.execute(new h4(this, file, absolutePath, viewHolder, 1));
            }
        }

        private void loadVideoThumbnail(ViewHolder viewHolder, File file) {
            String absolutePath = file.getAbsolutePath();
            Bitmap bitmap = (Bitmap) FileManagerActivity.this.thumbnailCache.get(absolutePath);
            if (bitmap != null) {
                viewHolder.icon.setImageBitmap(bitmap);
                return;
            }
            viewHolder.icon.setTag(absolutePath);
            File videoThumbnailCacheFile = getVideoThumbnailCacheFile(file);
            if (videoThumbnailCacheFile.exists()) {
                FileManagerActivity.this.imageExecutor.execute(new h4(this, videoThumbnailCacheFile, absolutePath, viewHolder, 2));
            } else {
                FileManagerActivity.this.metadataExecutor.execute(new j4(this, file, absolutePath, videoThumbnailCacheFile, viewHolder));
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

        private void saveThumbnailFile(File file, Bitmap bitmap) {
            if (file == null || bitmap == null) {
                return;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } finally {
                }
            } catch (Exception unused) {
            }
        }

        public void appendItems(List<FileItem> list) {
            if (list == null || list.isEmpty()) {
                return;
            }
            int size = this.items.size();
            this.items.addAll(list);
            notifyItemRangeInserted(size, list.size());
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.items.size();
        }

        public List<FileItem> getItems() {
            return this.items;
        }

        @Override // androidx.recyclerview.widget.h1
        public /* bridge */ /* synthetic */ void onBindViewHolder(androidx.recyclerview.widget.j2 j2Var, int i7, List list) {
            onBindViewHolder((ViewHolder) j2Var, i7, (List<Object>) list);
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(final ViewHolder viewHolder, int i7) {
            File file;
            File file2;
            File file3;
            final FileItem fileItem = this.items.get(i7);
            viewHolder.textName.setText(fileItem.name);
            if (fileItem.date > 0) {
                viewHolder.textDate.setText(this.dateFormat.format(new Date(fileItem.date)));
            } else {
                viewHolder.textDate.setText(s5.h0.FRAGMENT_ENCODE_SET);
            }
            int i8 = 0;
            if (!FileManagerActivity.this.isSelectionMode || fileItem.isZipEntry || fileItem.file == null) {
                viewHolder.checkbox.setVisibility(8);
                TypedValue typedValue = new TypedValue();
                FileManagerActivity.this.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
                viewHolder.itemView.setBackgroundResource(typedValue.resourceId);
            } else {
                viewHolder.checkbox.setVisibility(0);
                viewHolder.checkbox.setChecked(FileManagerActivity.this.selectedFiles.contains(fileItem.file));
                viewHolder.itemView.setBackgroundColor(FileManagerActivity.this.selectedFiles.contains(fileItem.file) ? 539072243 : 0);
            }
            viewHolder.textApkInfo.setVisibility(8);
            if (fileItem.isDirectory) {
                viewHolder.icon.setImageResource(g4.o.ic_folder_yellow);
                if (fileItem.isZipEntry) {
                    viewHolder.textSize.setText(s5.h0.FRAGMENT_ENCODE_SET);
                } else {
                    File file4 = fileItem.file;
                    if (file4 != null) {
                        Integer num = (Integer) FileManagerActivity.this.directoryChildCountCache.get(file4.getAbsolutePath());
                        if (num != null) {
                            viewHolder.textSize.setText(num + " 项");
                        } else {
                            viewHolder.textSize.setText("-- 项");
                            FileManagerActivity.this.requestDirectoryItemCount(fileItem.file);
                        }
                    }
                }
                TypedValue typedValue2 = new TypedValue();
                viewHolder.itemView.getContext().getTheme().resolveAttribute(R.attr.textColorPrimary, typedValue2, true);
                viewHolder.textName.setTextColor(typedValue2.data);
            } else {
                String lowerCase = fileItem.name.toLowerCase();
                viewHolder.textSize.setText(FileManagerActivity.this.formatSize(fileItem.size));
                TypedValue typedValue3 = new TypedValue();
                viewHolder.itemView.getContext().getTheme().resolveAttribute(R.attr.textColorPrimary, typedValue3, true);
                viewHolder.textName.setTextColor(typedValue3.data);
                if (lowerCase.endsWith(".apk")) {
                    viewHolder.icon.setImageResource(g4.o.ic_file_apk);
                    if (!fileItem.isZipEntry && (file3 = fileItem.file) != null) {
                        loadApkInfo(viewHolder, file3);
                    }
                } else if (lowerCase.endsWith(".zip") || lowerCase.endsWith(".rar") || lowerCase.endsWith(".7z")) {
                    viewHolder.icon.setImageResource(g4.o.ic_file_zip);
                } else if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".gif")) {
                    viewHolder.icon.setImageResource(g4.o.ic_file_image);
                    if (!fileItem.isZipEntry && (file = fileItem.file) != null) {
                        loadImageThumbnail(viewHolder, file);
                    }
                } else if (FileManagerActivity.this.isVideoFileName(lowerCase)) {
                    viewHolder.icon.setImageResource(g4.o.ic_file_video);
                    if (!fileItem.isZipEntry && (file2 = fileItem.file) != null) {
                        loadVideoThumbnail(viewHolder, file2);
                    }
                } else {
                    viewHolder.icon.setImageResource(g4.o.ic_file_default);
                }
            }
            viewHolder.itemView.setOnClickListener(new k4(this, viewHolder, fileItem, i8));
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.navi.tool.ui.extra.l4
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    boolean lambda$onBindViewHolder$2;
                    lambda$onBindViewHolder$2 = FileManagerActivity.FileAdapter.this.lambda$onBindViewHolder$2(viewHolder, fileItem, view);
                    return lambda$onBindViewHolder$2;
                }
            });
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i7, List<Object> list) {
            if (list.isEmpty()) {
                onBindViewHolder(viewHolder, i7);
                return;
            }
            Iterator<Object> it = list.iterator();
            while (it.hasNext()) {
                if ("selection".equals(it.next())) {
                    FileItem fileItem = this.items.get(i7);
                    if (!FileManagerActivity.this.isSelectionMode || fileItem.isZipEntry || fileItem.file == null) {
                        viewHolder.checkbox.setVisibility(8);
                        TypedValue typedValue = new TypedValue();
                        FileManagerActivity.this.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
                        viewHolder.itemView.setBackgroundResource(typedValue.resourceId);
                    } else {
                        viewHolder.checkbox.setVisibility(0);
                        viewHolder.checkbox.setChecked(FileManagerActivity.this.selectedFiles.contains(fileItem.file));
                        viewHolder.itemView.setBackgroundColor(FileManagerActivity.this.selectedFiles.contains(fileItem.file) ? 539072243 : 0);
                    }
                }
            }
        }

        @Override // androidx.recyclerview.widget.h1
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return new ViewHolder(FileManagerActivity.this.getLayoutInflater().inflate(g4.q.item_file, viewGroup, false));
        }

        public void setFiles(List<File> list) {
            ArrayList arrayList = new ArrayList();
            Iterator<File> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new FileItem(it.next()));
            }
            setItems(arrayList);
        }

        public void setItems(List<FileItem> list) {
            this.items = list;
            notifyDataSetChanged();
        }
    }

    public static class FileItem {
        public Pan123Api.CloudFileInfo cloudInfo;
        public long date;
        public File file;
        public boolean isDirectory;
        public boolean isZipEntry;
        public String name;
        public String path;
        public long size;

        public FileItem(File file) {
            this.name = file.getName();
            this.size = file.length();
            this.date = file.lastModified();
            this.isDirectory = file.isDirectory();
            this.file = file;
            this.path = file.getAbsolutePath();
            this.isZipEntry = false;
        }

        public FileItem(String str, String str2, boolean z6, long j7, long j8) {
            this.name = str;
            this.path = str2;
            this.isDirectory = z6;
            this.size = j7;
            this.date = j8;
            this.file = null;
            this.isZipEntry = true;
        }
    }

    public class GridSpacingItemDecoration extends androidx.recyclerview.widget.o1 {
        private boolean includeEdge;
        private int spacing;
        private int spanCount;

        public GridSpacingItemDecoration(int i7, int i8, boolean z6) {
            this.spanCount = i7;
            this.spacing = i8;
            this.includeEdge = z6;
        }

        @Override // androidx.recyclerview.widget.o1
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, androidx.recyclerview.widget.g2 g2Var) {
            recyclerView.getClass();
            int I = RecyclerView.I(view);
            int i7 = this.spanCount;
            int i8 = I % i7;
            if (this.includeEdge) {
                int i9 = this.spacing;
                rect.left = i9 - ((i8 * i9) / i7);
                rect.right = ((i8 + 1) * i9) / i7;
                if (I < i7) {
                    rect.top = i9;
                }
                rect.bottom = i9;
                return;
            }
            int i10 = this.spacing;
            rect.left = (i8 * i10) / i7;
            rect.right = i10 - (((i8 + 1) * i10) / i7);
            if (I >= i7) {
                rect.top = i10;
            }
        }
    }

    public class InstalledAppAdapter extends androidx.recyclerview.widget.h1 {
        private List<PackageInfo> apps;

        public class AppViewHolder extends androidx.recyclerview.widget.j2 {
            CheckBox checkbox;
            ImageView icon;
            TextView textApkInfo;
            TextView textDate;
            TextView textName;
            TextView textSize;

            public AppViewHolder(View view) {
                super(view);
                this.icon = (ImageView) view.findViewById(g4.p.file_icon);
                this.textName = (TextView) view.findViewById(g4.p.file_name);
                this.textDate = (TextView) view.findViewById(g4.p.file_date);
                this.textSize = (TextView) view.findViewById(g4.p.file_size);
                this.textApkInfo = (TextView) view.findViewById(g4.p.file_apk_info);
                this.checkbox = (CheckBox) view.findViewById(g4.p.checkbox_select);
            }
        }

        private InstalledAppAdapter() {
            this.apps = new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(PackageInfo packageInfo, View view) {
            FileManagerActivity.this.showAppActionDialog(packageInfo);
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.apps.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(AppViewHolder appViewHolder, int i7) {
            Drawable drawable;
            PackageInfo packageInfo = this.apps.get(i7);
            PackageManager packageManager = FileManagerActivity.this.getPackageManager();
            String str = packageInfo.packageName;
            try {
                CharSequence loadLabel = packageInfo.applicationInfo.loadLabel(packageManager);
                if (loadLabel != null) {
                    str = loadLabel.toString();
                }
                drawable = packageInfo.applicationInfo.loadIcon(packageManager);
            } catch (Exception e7) {
                e7.printStackTrace();
                drawable = null;
            }
            appViewHolder.textName.setText(str);
            if (drawable != null) {
                appViewHolder.icon.setImageDrawable(drawable);
            } else {
                appViewHolder.icon.setImageResource(R.drawable.sym_def_app_icon);
            }
            appViewHolder.textDate.setText(a2.m.PREFIX + packageInfo.versionName);
            appViewHolder.textSize.setVisibility(8);
            appViewHolder.textApkInfo.setVisibility(0);
            appViewHolder.textApkInfo.setText(packageInfo.packageName);
            appViewHolder.checkbox.setVisibility(8);
            appViewHolder.itemView.setOnClickListener(new d1(1, this, packageInfo));
        }

        @Override // androidx.recyclerview.widget.h1
        public AppViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return new AppViewHolder(FileManagerActivity.this.getLayoutInflater().inflate(g4.q.item_file, viewGroup, false));
        }

        public void setApps(List<PackageInfo> list) {
            this.apps = list;
            notifyDataSetChanged();
        }
    }

    private void addBreadcrumbSeparator() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(g4.o.ic_right_arrow_small);
        imageView.setColorFilter(getBreadcrumbColor());
        this.breadcrumbContainer.addView(imageView);
    }

    private void addCloudBreadcrumbItem(String str, String str2, int i7) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setPadding(4, 4, 4, 4);
        linearLayout.setClickable(true);
        linearLayout.setFocusable(true);
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        linearLayout.setBackgroundResource(typedValue.resourceId);
        if (i7 > 0) {
            TextView textView = new TextView(this);
            textView.setText(" > ");
            textView.setTextSize(14.0f);
            textView.setTextColor(getResources().getColor(R.color.darker_gray, getTheme()));
            linearLayout.addView(textView);
        }
        TextView textView2 = new TextView(this);
        textView2.setText(str);
        textView2.setTextColor(getResources().getColor(R.color.holo_blue_dark, getTheme()));
        textView2.setTextSize(14.0f);
        textView2.setPadding(4, 2, 4, 2);
        linearLayout.addView(textView2);
        linearLayout.setOnClickListener(new l1(this, i7, str2, 2));
        this.breadcrumbContainer.addView(linearLayout);
    }

    private void applySortedItemsIncrementally(int i7, File file, List<FileItem> list) {
        int min = Math.min(220, list.size());
        this.adapter.setItems(new ArrayList(list.subList(0, min)));
        if (min >= list.size()) {
            return;
        }
        streamNextBatch(i7, file, list, min);
    }

    private void beginConfigBackup() {
        if (ConfigBackupService.running) {
            ToastHelper.show(this, "备份正在进行");
            return;
        }
        Intent intent = new Intent(this, (Class<?>) ConfigBackupService.class);
        intent.setAction(ConfigBackupService.ACTION_START);
        intent.putExtra(ConfigBackupService.EXTRA_CREDENTIAL_INDEX, this.cloudBackupCredentialIndex);
        Object obj = g0.g.f6423a;
        g0.e.b(this, intent);
        showCloudBackupDialog();
    }

    private void cancelConfigBackup() {
        if (!ConfigBackupService.running) {
            dismissCloudBackupDialog();
            return;
        }
        TextView textView = this.cloudBackupProgressText;
        if (textView != null) {
            textView.setText("正在取消...");
        }
        Intent intent = new Intent(this, (Class<?>) ConfigBackupService.class);
        intent.setAction(ConfigBackupService.ACTION_CANCEL);
        try {
            startService(intent);
        } catch (Exception unused) {
        }
    }

    private void checkNotifyUi(String str) {
    }

    private void compressSelection() {
        if (this.selectedFiles.isEmpty()) {
            return;
        }
        String name = this.selectedFiles.get(0).getName();
        if (this.selectedFiles.size() > 1) {
            name = name + "_等" + this.selectedFiles.size() + "项";
        }
        if (!name.toLowerCase().endsWith(".zip")) {
            name = name.concat(".zip");
        }
        File file = new File(this.currentDir, name);
        int i7 = 1;
        while (file.exists()) {
            String substring = name.substring(0, name.length() - 4);
            i7++;
            file = new File(this.currentDir, substring + "(" + i7 + ").zip");
        }
        ArrayList arrayList = new ArrayList(this.selectedFiles);
        ToastHelper.show(this, "开始压缩...");
        exitSelectionMode();
        this.executor.execute(new w2(this, file, arrayList, 13));
    }

    private void copyAndSend(Uri uri) {
        this.executor.execute(new z0(14, this, uri));
    }

    private boolean copyRecursiveWithProgress(File file, File file2, CopyProgressCallback copyProgressCallback, boolean z6) {
        if (this.isPasteCancelled) {
            return false;
        }
        boolean z7 = true;
        if (file.isDirectory()) {
            if (file2.exists()) {
                if (!file2.isDirectory()) {
                    int resolveConflict = resolveConflict(file2);
                    if (this.isPasteCancelled) {
                        return false;
                    }
                    if (resolveConflict == 2) {
                        if (copyProgressCallback != null) {
                            copyProgressCallback.onProgress(countRecursiveForCopy(file)[1]);
                        }
                        return true;
                    }
                    if (resolveConflict == 0) {
                        deleteRecursive(file2);
                        file2.mkdirs();
                    } else if (resolveConflict == 1) {
                        file2 = getAutoRenameFile(file2);
                        file2.mkdirs();
                    }
                }
            } else if (!file2.mkdirs() && file2.exists() && !file2.isDirectory()) {
                int resolveConflict2 = resolveConflict(file2);
                if (this.isPasteCancelled) {
                    return false;
                }
                if (resolveConflict2 == 2) {
                    if (copyProgressCallback != null) {
                        copyProgressCallback.onProgress(countRecursiveForCopy(file)[1]);
                    }
                    return true;
                }
                if (resolveConflict2 == 0) {
                    deleteRecursive(file2);
                    file2.mkdirs();
                } else if (resolveConflict2 == 1) {
                    file2 = getAutoRenameFile(file2);
                    file2.mkdirs();
                }
            }
            if (!file2.exists() || !file2.isDirectory()) {
                return false;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file3 : listFiles) {
                    if (this.isPasteCancelled) {
                        return false;
                    }
                    if (!copyRecursiveWithProgress(file3, new File(file2, file3.getName()), copyProgressCallback, z6)) {
                        z7 = false;
                    }
                }
            }
            if (z6 && z7) {
                file.delete();
            }
            return z7;
        }
        if (file2.exists()) {
            int resolveConflict3 = resolveConflict(file2);
            if (this.isPasteCancelled) {
                return false;
            }
            if (resolveConflict3 == 2) {
                if (copyProgressCallback != null) {
                    copyProgressCallback.onProgress(file.length());
                }
                return true;
            }
            if (resolveConflict3 == 0) {
                if (file2.isDirectory()) {
                    deleteRecursive(file2);
                } else {
                    file2.delete();
                }
            } else if (resolveConflict3 == 1) {
                file2 = getAutoRenameFile(file2);
            }
        }
        if (z6 && !file2.exists()) {
            long length = file.length();
            if (file.renameTo(file2)) {
                if (copyProgressCallback != null) {
                    copyProgressCallback.onProgress(length);
                }
                return true;
            }
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        fileOutputStream.close();
                        fileInputStream.close();
                        if (z6) {
                            file.delete();
                        }
                        return true;
                    }
                    if (this.isPasteCancelled) {
                        fileOutputStream.close();
                        fileInputStream.close();
                        return false;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    if (copyProgressCallback != null) {
                        copyProgressCallback.onProgress(read);
                    }
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void copySelection() {
        this.clipboardFiles.clear();
        this.clipboardFiles.addAll(this.selectedFiles);
        this.isCutOperation = false;
        showPasteLayout();
        exitSelectionMode();
    }

    private long[] countRecursive(File file) {
        long j7;
        long j8;
        File[] listFiles = file.listFiles();
        long j9 = 0;
        if (listFiles != null) {
            j7 = 0;
            j8 = 0;
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    long[] countRecursive = countRecursive(file2);
                    j9 += countRecursive[0];
                    j7 += countRecursive[1];
                    j8 = j8 + 1 + countRecursive[2];
                } else {
                    j7++;
                    j9 = file2.length() + j9;
                }
            }
        } else {
            j7 = 0;
            j8 = 0;
        }
        return new long[]{j9, j7, j8};
    }

    private long[] countRecursiveForCopy(File file) {
        long[] jArr = {0, 0};
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    long[] countRecursiveForCopy = countRecursiveForCopy(file2);
                    jArr[0] = jArr[0] + countRecursiveForCopy[0];
                    jArr[1] = jArr[1] + countRecursiveForCopy[1];
                }
            }
        } else {
            jArr[0] = 1;
            jArr[1] = file.length();
        }
        return jArr;
    }

    private void createFileOrFolder(String str, boolean z6) {
        if (this.isCloudDiskMode) {
            if (!z6) {
                ToastHelper.show(this, "云盘模式下暂不支持创建文件");
                return;
            } else {
                this.progressScan.setVisibility(0);
                this.executor.execute(new z3(this, str, 3));
                return;
            }
        }
        File file = this.currentDir;
        if (file == null || !file.exists()) {
            ToastHelper.show(this, "当前目录无效");
            return;
        }
        File file2 = new File(this.currentDir, str);
        if (file2.exists()) {
            ToastHelper.show(this, (z6 ? "文件夹" : "文件").concat("已存在"));
            return;
        }
        try {
            if (!(z6 ? file2.mkdirs() : file2.createNewFile())) {
                ToastHelper.show(this, "创建失败");
            } else {
                ToastHelper.show(this, "创建成功");
                loadFiles(this.currentDir);
            }
        } catch (Exception e7) {
            ToastHelper.show(this, "创建出错: " + e7.getMessage());
        }
    }

    private void cutSelection() {
        this.clipboardFiles.clear();
        this.clipboardFiles.addAll(this.selectedFiles);
        this.isCutOperation = true;
        showPasteLayout();
        exitSelectionMode();
    }

    private boolean deleteRecursive(File file) {
        File[] listFiles;
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                deleteRecursive(file2);
            }
        }
        return file.delete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissCloudBackupDialog() {
        try {
            androidx.appcompat.app.o oVar = this.cloudBackupDialog;
            if (oVar != null && oVar.isShowing()) {
                this.cloudBackupDialog.dismiss();
            }
        } catch (Exception unused) {
        }
        this.cloudBackupDialog = null;
        this.cloudBackupProgressBar = null;
        this.cloudBackupProgressText = null;
        this.cloudBackupPercentText = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadCloudFile(Pan123Api.CloudFileInfo cloudFileInfo) {
        if (cloudFileInfo.isDir) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/filemanager/云盘下载");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, cloudFileInfo.name);
        if (file2.exists()) {
            int lastIndexOf = cloudFileInfo.name.lastIndexOf(46);
            String str = cloudFileInfo.name;
            if (lastIndexOf > 0) {
                str = str.substring(0, lastIndexOf);
            }
            String substring = lastIndexOf > 0 ? cloudFileInfo.name.substring(lastIndexOf) : s5.h0.FRAGMENT_ENCODE_SET;
            int i7 = 1;
            while (file2.exists()) {
                file2 = new File(file, str + "(" + i7 + ")" + substring);
                i7++;
            }
        }
        this.isDownloadCancelled = false;
        runOnUiThread(new z0(16, this, cloudFileInfo));
        this.downloadExecutor.execute(new w2(this, cloudFileInfo, file2));
    }

    private void enterCloudDisk(CloudDiskCredential cloudDiskCredential) {
        this.isCloudDiskMode = true;
        this.isAppMode = false;
        this.isZipMode = false;
        this.isSearchMode = false;
        this.isSelectionMode = false;
        this.selectedFiles.clear();
        this.currentCloudCredential = cloudDiskCredential;
        this.currentCloudParentId = "0";
        this.cloudPathStack.clear();
        this.cloudNameStack.clear();
        this.cloudFiles.clear();
        if (getSupportActionBar() != null) {
            androidx.appcompat.app.b supportActionBar = getSupportActionBar();
            String str = cloudDiskCredential.name;
            if (str == null) {
                str = "123网盘";
            }
            supportActionBar.t(str);
        }
        updateCloudBreadcrumbs();
        loadCloudFiles("0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterSelectionMode(File file) {
        this.isSelectionMode = true;
        this.selectedFiles.clear();
        this.selectedFiles.add(file);
        FileAdapter fileAdapter = this.adapter;
        fileAdapter.notifyItemRangeChanged(0, fileAdapter.getItemCount(), "selection");
        updateSelectionTitle();
        this.layoutSelectionActions.setVisibility(0);
        if (this.isPickFileMode && this.isMultiSelectAllowed) {
            this.actionCopy.setVisibility(8);
            this.actionCut.setVisibility(8);
            this.actionDelete.setVisibility(8);
            this.actionRename.setVisibility(8);
            this.actionCompress.setVisibility(8);
            this.actionInfo.setVisibility(8);
            this.actionConfirmSelection.setVisibility(0);
            return;
        }
        this.actionCopy.setVisibility(0);
        this.actionCut.setVisibility(0);
        this.actionDelete.setVisibility(0);
        this.actionRename.setVisibility(0);
        this.actionCompress.setVisibility(0);
        this.actionInfo.setVisibility(0);
        this.actionConfirmSelection.setVisibility(8);
    }

    private void enterZipMode(File file) {
        this.progressScan.setVisibility(0);
        new Thread(new w3(this, file, 2)).start();
    }

    private void executePaste() {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t(this.isCutOperation ? "正在移动..." : "正在复制...");
        nVar.u(LayoutInflater.from(this).inflate(g4.q.dialog_progress, (ViewGroup) null));
        nVar.k();
        androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        ProgressBar progressBar = (ProgressBar) e7.findViewById(g4.p.progress_bar);
        TextView textView = (TextView) e7.findViewById(g4.p.progress_text);
        TextView textView2 = (TextView) e7.findViewById(g4.p.progress_percent);
        Button button = (Button) e7.findViewById(g4.p.btn_cancel);
        this.isPasteCancelled = false;
        if (button != null) {
            button.setOnClickListener(new d1(5, this, button));
        }
        this.executor.execute(new l5(this, textView, progressBar, textView2, this.currentDir, e7));
    }

    private void exitCloudDisk() {
        this.isCloudDiskMode = false;
        this.currentCloudCredential = null;
        this.currentCloudParentId = "0";
        this.cloudPathStack.clear();
        this.cloudNameStack.clear();
        this.cloudFiles.clear();
        if (getSupportActionBar() != null) {
            getSupportActionBar().t("文件助手");
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        this.currentDir = externalStorageDirectory;
        loadFiles(externalStorageDirectory);
    }

    private void exitSelectionMode() {
        this.isSelectionMode = false;
        this.selectedFiles.clear();
        FileAdapter fileAdapter = this.adapter;
        fileAdapter.notifyItemRangeChanged(0, fileAdapter.getItemCount(), "selection");
        if (getSupportActionBar() != null) {
            getSupportActionBar().t("文件助手");
        }
        this.layoutSelectionActions.setVisibility(8);
    }

    private void exitZipMode() {
        this.isZipMode = false;
        this.currentZipFile = null;
        ZipFile zipFile = this.openZipFile;
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            this.openZipFile = null;
        }
        this.zipCache.clear();
        switchLayout(this.isGridMode);
        loadFiles(this.currentDir);
    }

    private FileItem findZipItem(List<FileItem> list, String str) {
        for (FileItem fileItem : list) {
            if (fileItem.name.equals(str)) {
                return fileItem;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatSize(long j7) {
        if (j7 >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return j7 < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? String.format(Locale.US, "%.1f KB", Double.valueOf(j7 / 1024.0d)) : j7 < 1073741824 ? String.format(Locale.US, "%.1f MB", Double.valueOf(j7 / 1048576.0d)) : String.format(Locale.US, "%.1f GB", Double.valueOf(j7 / 1.073741824E9d));
        }
        return j7 + " B";
    }

    private String formatTime(long j7) {
        if (j7 < 60) {
            return j7 + "秒";
        }
        return (j7 / 60) + "分" + (j7 % 60) + "秒";
    }

    private File getAutoRenameFile(File file) {
        File parentFile = file.getParentFile();
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        String substring = lastIndexOf > 0 ? name.substring(0, lastIndexOf) : name;
        String substring2 = lastIndexOf > 0 ? name.substring(lastIndexOf) : s5.h0.FRAGMENT_ENCODE_SET;
        int i7 = 1;
        while (file.exists()) {
            file = new File(parentFile, substring + "(" + i7 + ")" + substring2);
            i7++;
        }
        return file;
    }

    private int getBreadcrumbColor() {
        if ((getResources().getConfiguration().uiMode & 48) == 32) {
            return -1;
        }
        int i7 = g4.m.primary;
        Object obj = g0.g.f6423a;
        return g0.d.a(this, i7);
    }

    private String getExtension(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf <= 0) {
            return "文件";
        }
        return str.substring(lastIndexOf + 1).toUpperCase() + " 文件";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getFileUri(File file) {
        return FileProvider.b(this, getPackageName() + ".fileprovider", file);
    }

    private String getFriendlyName(File file) {
        String description;
        if (file.equals(Environment.getExternalStorageDirectory())) {
            return "内部存储";
        }
        StorageManager storageManager = (StorageManager) getSystemService("storage");
        if (storageManager != null) {
            for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
                File volumePath = getVolumePath(storageVolume);
                if (volumePath != null && volumePath.equals(file) && (description = storageVolume.getDescription(this)) != null && !description.isEmpty()) {
                    return description;
                }
            }
        }
        return file.getName();
    }

    private String getPathFromUri(Uri uri) {
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private List<File> getStorageRoots() {
        File file;
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) getSystemService("storage");
        if (storageManager == null) {
            return arrayList;
        }
        for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
            if (Build.VERSION.SDK_INT >= 30) {
                file = storageVolume.getDirectory();
            } else {
                try {
                    String str = (String) storageVolume.getClass().getMethod("getPath", new Class[0]).invoke(storageVolume, new Object[0]);
                    if (str != null) {
                        file = new File(str);
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                file = null;
            }
            if (file != null && file.exists() && file.canRead()) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    private int getThemeColor(int i7) {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(i7, typedValue, true);
        return typedValue.data;
    }

    private File getVolumePath(StorageVolume storageVolume) {
        File directory;
        if (Build.VERSION.SDK_INT >= 30) {
            directory = storageVolume.getDirectory();
            return directory;
        }
        try {
            String str = (String) storageVolume.getClass().getMethod("getPath", new Class[0]).invoke(storageVolume, new Object[0]);
            if (str != null) {
                return new File(str);
            }
            return null;
        } catch (Exception e7) {
            e7.printStackTrace();
            return null;
        }
    }

    private void initTransferLogic() {
        if (this.isPickFolderMode || this.isPickFileMode) {
            return;
        }
        q4.h g7 = q4.h.g(this);
        this.transferManager = g7;
        g7.f8481q = new AnonymousClass4();
        this.switchNetworkTransfer.setOnCheckedChangeListener(new o3(this, 0));
    }

    private void initViews() {
        this.breadcrumbContainer = (LinearLayout) findViewById(g4.p.container_breadcrumb);
        this.textEmpty = (TextView) findViewById(g4.p.text_empty);
        RecyclerView recyclerView = (RecyclerView) findViewById(g4.p.recycler_files);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(4));
        this.progressStorage = (ProgressBar) findViewById(g4.p.progress_storage);
        this.textStorageInfo = (TextView) findViewById(g4.p.text_storage_info);
        this.layoutStorageVolumes = (LinearLayout) findViewById(g4.p.layout_storage_volumes);
        this.layoutCloudDisks = (LinearLayout) findViewById(g4.p.layout_cloud_disks);
        this.layoutCatApps = findViewById(g4.p.layout_cat_apps);
        this.layoutCatTransfer = findViewById(g4.p.layout_cat_transfer);
        this.layoutCatApk = findViewById(g4.p.layout_cat_apk);
        this.layoutCatZip = findViewById(g4.p.layout_cat_zip);
        this.layoutCatImage = findViewById(g4.p.layout_cat_image);
        this.layoutCatVideo = findViewById(g4.p.layout_cat_video);
        this.layoutCatMusic = findViewById(g4.p.layout_cat_music);
        this.progressScan = (ProgressBar) findViewById(g4.p.progress_scan);
        this.layoutDownloadProgress = findViewById(g4.p.layout_download_progress);
        this.progressDownload = (ProgressBar) findViewById(g4.p.progress_download);
        this.textDownloadName = (TextView) findViewById(g4.p.text_download_name);
        this.textDownloadPercent = (TextView) findViewById(g4.p.text_download_percent);
        Button button = (Button) findViewById(g4.p.btn_cancel_download);
        this.btnCancelDownload = button;
        button.setOnClickListener(new a4(this, 25));
        this.switchNetworkTransfer = (Switch) findViewById(g4.p.switch_network_transfer);
        this.textLocalIp = (TextView) findViewById(g4.p.text_local_ip);
        this.switchHttpServer = (Switch) findViewById(g4.p.switch_http_server);
        this.textHttpServerAddress = (TextView) findViewById(g4.p.text_http_server_address);
        this.btnShowQrCode = (Button) findViewById(g4.p.btn_show_qr_code);
        this.layoutSelectionActions = findViewById(g4.p.layout_selection_actions);
        this.actionCancel = findViewById(g4.p.action_cancel);
        this.actionConfirmSelection = findViewById(g4.p.action_confirm_selection);
        this.actionSelectAll = findViewById(g4.p.action_select_all);
        this.actionCopy = findViewById(g4.p.action_copy);
        this.actionConfirmSelection.setOnClickListener(new a4(this, 26));
        this.actionCut = findViewById(g4.p.action_cut);
        this.actionDelete = findViewById(g4.p.action_delete);
        this.actionRename = findViewById(g4.p.action_rename);
        this.actionCompress = findViewById(g4.p.action_compress);
        this.actionInfo = findViewById(g4.p.action_info);
        this.layoutPasteActions = findViewById(g4.p.layout_paste_actions);
        this.textPasteInfo = (TextView) findViewById(g4.p.text_paste_info);
        this.btnCancelPaste = (Button) findViewById(g4.p.btn_cancel_paste);
        this.btnPaste = (Button) findViewById(g4.p.btn_paste);
        initTransferLogic();
    }

    private void installApk(File file) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Uri fileUri = getFileUri(file);
            intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            intent.addFlags(268435456);
            intent.addFlags(1);
            intent.setClipData(ClipData.newRawUri(s5.h0.FRAGMENT_ENCODE_SET, fileUri));
            String str = null;
            for (ResolveInfo resolveInfo : getPackageManager().queryIntentActivities(intent, 0)) {
                String str2 = resolveInfo.activityInfo.packageName;
                if (!str2.equals("com.android.packageinstaller") && !str2.equals("com.google.android.packageinstaller")) {
                    if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                        str = str2;
                    }
                }
                str = str2;
            }
            if (str != null) {
                intent.setPackage(str);
            }
            startActivity(intent);
        } catch (Exception e7) {
            e7.printStackTrace();
            ToastHelper.show(this, "无法安装APK: " + e7.getMessage());
        }
    }

    private boolean isCloudCredentialValid(CloudDiskCredential cloudDiskCredential) {
        try {
            Pan123Api.listFiles(cloudDiskCredential, "0", 1);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isDirectoryLoadValid(int i7, File file) {
        return (this.directoryLoadVersion.get() != i7 || this.isSearchMode || this.isAppMode || this.isZipMode || !isSameDirectory(this.currentDir, file)) ? false : true;
    }

    private boolean isImageFileName(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".png") || lowerCase.endsWith(".gif") || lowerCase.endsWith(".bmp") || lowerCase.endsWith(".webp");
    }

    private boolean isSameDirectory(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        return file.getAbsolutePath().equals(file2.getAbsolutePath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVideoFileName(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.endsWith(".mp4") || lowerCase.endsWith(".avi") || lowerCase.endsWith(".mkv") || lowerCase.endsWith(".mov") || lowerCase.endsWith(".webm") || lowerCase.endsWith(".m4v") || lowerCase.endsWith(".3gp");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addCloudBreadcrumbItem$14(int i7, String str, View view) {
        if (this.isCloudDiskMode) {
            while (this.cloudPathStack.size() > i7) {
                this.cloudPathStack.pop();
                this.cloudNameStack.pop();
            }
            this.currentCloudParentId = str;
            loadCloudFiles(str);
            updateCloudBreadcrumbs();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$compressSelection$47(File file) {
        ToastHelper.showLong(this, "压缩完成: " + file.getName());
        File file2 = this.currentDir;
        if (file2 == null || !file2.equals(file.getParentFile())) {
            return;
        }
        loadFiles(this.currentDir);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$compressSelection$48(Exception exc) {
        ToastHelper.showLong(this, "压缩失败: " + exc.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$compressSelection$49(File file, List list) {
        try {
            byte[] bArr = new byte[8192];
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            try {
                zipOutputStream.setLevel(9);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    zipFileRecursive((File) it.next(), this.currentDir.getAbsolutePath(), zipOutputStream, bArr);
                }
                zipOutputStream.close();
                runOnUiThread(new w3(this, file, 1));
            } finally {
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            runOnUiThread(new x3(this, e7, 2));
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$copyAndSend$29() {
        ToastHelper.show(this, "开始发送文件...");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$copyAndSend$30(Uri uri) {
        String str;
        int i7;
        try {
            str = "unknown_file";
            Cursor query = getContentResolver().query(uri, null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int columnIndex = query.getColumnIndex("_display_name");
                str = columnIndex >= 0 ? query.getString(columnIndex) : "unknown_file";
                query.close();
            }
            File file = new File(getCacheDir(), str);
            InputStream openInputStream = getContentResolver().openInputStream(uri);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = openInputStream.read(bArr);
                        i7 = 0;
                        if (read == -1) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, read);
                        }
                    }
                    fileOutputStream.close();
                    openInputStream.close();
                    q4.h hVar = this.transferManager;
                    String str2 = hVar.f8482r;
                    if (str2 != null) {
                        hVar.f8467c.execute(new q4.c(hVar, str2, file, i7));
                    }
                    runOnUiThread(new c4(this, 1));
                } finally {
                }
            } finally {
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createFileOrFolder$116() {
        this.progressScan.setVisibility(8);
        ToastHelper.show(this, "创建成功");
        loadCloudFiles(this.currentCloudParentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createFileOrFolder$117(Exception exc) {
        this.progressScan.setVisibility(8);
        ToastHelper.show(this, "创建失败: " + exc.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createFileOrFolder$118(String str) {
        try {
            Pan123Api.createFolder(this.currentCloudCredential, this.currentCloudParentId, str);
            runOnUiThread(new c4(this, 0));
        } catch (Exception e7) {
            runOnUiThread(new x3(this, e7, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$downloadCloudFile$18(Pan123Api.CloudFileInfo cloudFileInfo) {
        this.textDownloadName.setText(cloudFileInfo.name);
        this.textDownloadPercent.setText("0%");
        this.progressDownload.setProgress(0);
        this.layoutDownloadProgress.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$downloadCloudFile$19(int i7) {
        this.progressDownload.setProgress(i7);
        this.textDownloadPercent.setText(i7 + "%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$downloadCloudFile$20(File file) {
        this.layoutDownloadProgress.setVisibility(8);
        ToastHelper.showLong(this, "下载完成：已保存到内部存储/navitool/云盘下载/" + file.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$downloadCloudFile$21(Exception exc) {
        if (this.isDownloadCancelled) {
            return;
        }
        this.layoutDownloadProgress.setVisibility(8);
        ToastHelper.show(this, "下载失败: " + exc.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$downloadCloudFile$22(Pan123Api.CloudFileInfo cloudFileInfo, File file) {
        s5.a1 a1Var;
        int i7 = 0;
        try {
            s5.x0 downloadFileStream = Pan123Api.downloadFileStream(Pan123Api.getDownloadUrl(this.currentCloudCredential, cloudFileInfo, this.currentCloudParentId), this.currentCloudCredential.domain + "/");
            if (!downloadFileStream.B() || (a1Var = downloadFileStream.f8919g) == null) {
                throw new Exception("下载失败");
            }
            g6.g x6 = ((s5.y0) a1Var).f8928c.x();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[q0.i.ACTION_COLLAPSE];
            long[] jArr = {0};
            long j7 = 0;
            while (true) {
                int read = x6.read(bArr);
                if (read == -1) {
                    fileOutputStream.close();
                    x6.close();
                    downloadFileStream.close();
                    runOnUiThread(new w3(this, file, i7));
                    return;
                }
                if (this.isDownloadCancelled) {
                    fileOutputStream.close();
                    x6.close();
                    downloadFileStream.close();
                    file.delete();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
                j7 += read;
                if (System.currentTimeMillis() - jArr[0] > 300) {
                    jArr[0] = System.currentTimeMillis();
                    long j8 = cloudFileInfo.size;
                    runOnUiThread(new u3(this, j8 > 0 ? (int) ((100 * j7) / j8) : 0, i7));
                }
            }
        } catch (Exception e7) {
            runOnUiThread(new x3(this, e7, i7));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$enterZipMode$84(boolean z6, File file) {
        this.progressScan.setVisibility(8);
        if (!z6) {
            ToastHelper.show(this, "无法读取压缩文件");
            return;
        }
        this.isZipMode = true;
        this.currentZipFile = file;
        if (this.isGridMode) {
            switchLayout(false);
        }
        loadZipFolder(s5.h0.FRAGMENT_ENCODE_SET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$enterZipMode$85(File file) {
        boolean z6 = true;
        try {
            try {
                parseZip(file, StandardCharsets.UTF_8);
            } catch (Exception unused) {
                parseZip(file, Charset.forName("GBK"));
            }
        } catch (Exception e7) {
            e7.printStackTrace();
            z6 = false;
        }
        runOnUiThread(new y0(this, z6, file));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$executePaste$100(TextView textView, ProgressBar progressBar, TextView textView2) {
        if (textView != null) {
            textView.setText("正在计算文件大小...");
        }
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        if (textView2 != null) {
            textView2.setText("0%");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$executePaste$101(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setIndeterminate(false);
            progressBar.setMax(100);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executePaste$102(TextView textView, String str, String str2, ProgressBar progressBar, int i7, TextView textView2) {
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.isCutOperation ? "正在移动: " : "正在复制: ");
            sb.append("\n");
            sb.append(str);
            sb.append(" - 剩余: ");
            sb.append(str2);
            textView.setText(sb.toString());
        }
        if (progressBar != null) {
            progressBar.setProgress(i7);
        }
        if (textView2 != null) {
            textView2.setText(i7 + "%");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$executePaste$103(AtomicLong atomicLong, long j7, long j8, TextView textView, ProgressBar progressBar, TextView textView2, long j9) {
        if (this.isPasteCancelled) {
            return false;
        }
        long addAndGet = atomicLong.addAndGet(j9);
        long currentTimeMillis = System.currentTimeMillis() - j7;
        long j10 = currentTimeMillis > 0 ? (1000 * addAndGet) / currentTimeMillis : 0L;
        runOnUiThread(new p3(this, textView, a.a.i(new StringBuilder(), formatSize(j10), "/s"), formatTime(j10 > 0 ? (j8 - addAndGet) / j10 : 0L), progressBar, j8 > 0 ? (int) ((addAndGet * 100) / j8) : 0, textView2));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executePaste$104(androidx.appcompat.app.o oVar) {
        oVar.dismiss();
        if (this.isPasteCancelled) {
            ToastHelper.show(this, "操作已取消");
        } else {
            ToastHelper.show(this, (this.isCutOperation ? "移动" : "复制").concat("完成"));
        }
        this.clipboardFiles.clear();
        this.layoutPasteActions.setVisibility(8);
        if (this.isAppMode) {
            return;
        }
        loadFiles(this.currentDir);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executePaste$105(final TextView textView, final ProgressBar progressBar, final TextView textView2, File file, androidx.appcompat.app.o oVar) {
        int i7;
        runOnUiThread(new w2(textView, progressBar, textView2, 10));
        Iterator<File> it = this.clipboardFiles.iterator();
        final long j7 = 0;
        while (true) {
            i7 = 0;
            if (!it.hasNext()) {
                break;
            }
            File next = it.next();
            if (this.isPasteCancelled) {
                break;
            }
            long[] countRecursive = countRecursive(next);
            j7 += countRecursive[0];
            long j8 = countRecursive[1];
        }
        runOnUiThread(new f4(7, progressBar));
        final AtomicLong atomicLong = new AtomicLong(0L);
        final long currentTimeMillis = System.currentTimeMillis();
        CopyProgressCallback copyProgressCallback = new CopyProgressCallback() { // from class: com.navi.tool.ui.extra.a3
            @Override // com.navi.tool.ui.extra.FileManagerActivity.CopyProgressCallback
            public final boolean onProgress(long j9) {
                boolean lambda$executePaste$103;
                lambda$executePaste$103 = FileManagerActivity.this.lambda$executePaste$103(atomicLong, currentTimeMillis, j7, textView, progressBar, textView2, j9);
                return lambda$executePaste$103;
            }
        };
        for (File file2 : this.clipboardFiles) {
            if (this.isPasteCancelled) {
                break;
            }
            File file3 = new File(file, file2.getName());
            if (!file3.getAbsolutePath().startsWith(file2.getAbsolutePath())) {
                try {
                    copyRecursiveWithProgress(file2, file3, copyProgressCallback, this.isCutOperation);
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
        }
        runOnUiThread(new b3(this, oVar, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$executePaste$99(Button button, View view) {
        this.isPasteCancelled = true;
        button.setText("正在取消...");
        button.setEnabled(false);
        synchronized (this) {
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$initTransferLogic$25(CompoundButton compoundButton, boolean z6) {
        if (!z6) {
            this.transferManager.m(transferOwner());
            this.textLocalIp.setVisibility(8);
            this.networkNeighborHandler.removeCallbacks(this.hideNetworkNeighborRunnable);
            setNetworkNeighborVisible(false);
            return;
        }
        this.transferManager.a(transferOwner());
        this.transferManager.getClass();
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.getName().contains("wlan")) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress() && (nextElement2 instanceof Inet4Address)) {
                            if (sb.length() > 0) {
                                sb.append(" / ");
                            }
                            sb.append(nextElement2.getHostAddress());
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        String sb2 = sb.length() > 0 ? sb.toString() : "先开启或连接热点";
        TextView textView = this.textLocalIp;
        if (sb2 == null) {
            sb2 = "未知";
        }
        textView.setText("本机IP: ".concat(sb2));
        this.textLocalIp.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$23(View view) {
        this.isDownloadCancelled = true;
        this.layoutDownloadProgress.setVisibility(8);
        ToastHelper.show(this, "已取消下载");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViews$24(View view) {
        if (this.selectedFiles.isEmpty()) {
            ToastHelper.show(this, "未选择任何文件");
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(1);
        Iterator<File> it = this.selectedFiles.iterator();
        ClipData clipData = null;
        while (it.hasNext()) {
            Uri fileUri = getFileUri(it.next());
            if (clipData == null) {
                clipData = ClipData.newRawUri(null, fileUri);
            } else {
                clipData.addItem(new ClipData.Item(fileUri));
            }
        }
        intent.setClipData(clipData);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCloudFiles$15(List list) {
        if (this.isCloudDiskMode) {
            this.progressScan.setVisibility(8);
            if (list.isEmpty()) {
                this.adapter.setItems(list);
                this.textEmpty.setText("文件夹为空");
                this.textEmpty.setVisibility(0);
                this.recyclerView.setVisibility(8);
                return;
            }
            this.textEmpty.setVisibility(8);
            this.recyclerView.setVisibility(0);
            this.adapter.setItems(list);
            switchLayout(this.isGridMode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCloudFiles$16(Exception exc) {
        if (this.isCloudDiskMode) {
            this.progressScan.setVisibility(8);
            if (exc.getMessage() == null || !exc.getMessage().contains("登录失效")) {
                ToastHelper.show(this, "加载失败: " + exc.getMessage());
            } else {
                ToastHelper.show(this, "登录失效，请重新绑定123网盘");
                exitCloudDisk();
                refreshCloudDisks();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadCloudFiles$17(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            int i7 = 1;
            int i8 = 1;
            do {
                List<Pan123Api.CloudFileInfo> listFiles = Pan123Api.listFiles(this.currentCloudCredential, str, i8);
                arrayList.addAll(listFiles);
                i8++;
                if (listFiles.size() < 100) {
                    break;
                }
            } while (i8 <= 5);
            this.cloudFiles = arrayList;
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Pan123Api.CloudFileInfo cloudFileInfo = (Pan123Api.CloudFileInfo) it.next();
                FileItem fileItem = new FileItem(cloudFileInfo.name, cloudFileInfo.fileId, cloudFileInfo.isDir, cloudFileInfo.size, parseCloudTime(cloudFileInfo.createTime));
                fileItem.cloudInfo = cloudFileInfo;
                arrayList2.add(fileItem);
            }
            sortFileItems(arrayList2);
            runOnUiThread(new j3(i7, this, arrayList2));
        } catch (Exception e7) {
            runOnUiThread(new x3(this, e7, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFiles$75(int i7, File file, List list) {
        if (isDirectoryLoadValid(i7, file)) {
            this.progressScan.setVisibility(8);
            this.adapter.setItems(list);
            this.textEmpty.setVisibility(8);
            this.recyclerView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFiles$76(int i7, File file, List list) {
        if (isDirectoryLoadValid(i7, file)) {
            this.progressScan.setVisibility(8);
            if (!list.isEmpty()) {
                this.textEmpty.setVisibility(8);
                this.recyclerView.setVisibility(0);
                applySortedItemsIncrementally(i7, file, list);
            } else {
                this.adapter.setItems(list);
                this.textEmpty.setText("文件夹为空");
                this.textEmpty.setVisibility(0);
                this.recyclerView.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadFiles$77(final File file, final int i7) {
        File[] listFiles = file.listFiles();
        ArrayList arrayList = new ArrayList();
        if (listFiles != null) {
            arrayList = new ArrayList(listFiles.length);
            for (File file2 : listFiles) {
                arrayList.add(new FileItem(file2));
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.size() > 300) {
            final ArrayList arrayList3 = new ArrayList(arrayList2.subList(0, Math.min(180, arrayList2.size())));
            final int i8 = 0;
            runOnUiThread(new Runnable() { // from class: com.navi.tool.ui.extra.t3
                @Override // java.lang.Runnable
                public final void run() {
                    int i9 = i8;
                    int i10 = i7;
                    FileManagerActivity fileManagerActivity = this;
                    List list = arrayList3;
                    File file3 = file;
                    switch (i9) {
                        case 0:
                            fileManagerActivity.lambda$loadFiles$75(i10, file3, list);
                            break;
                        default:
                            fileManagerActivity.lambda$loadFiles$76(i10, file3, list);
                            break;
                    }
                }
            });
        }
        final ArrayList arrayList4 = new ArrayList(arrayList2);
        sortFileItems(arrayList4);
        final int i9 = 1;
        runOnUiThread(new Runnable() { // from class: com.navi.tool.ui.extra.t3
            @Override // java.lang.Runnable
            public final void run() {
                int i92 = i9;
                int i10 = i7;
                FileManagerActivity fileManagerActivity = this;
                List list = arrayList4;
                File file3 = file;
                switch (i92) {
                    case 0:
                        fileManagerActivity.lambda$loadFiles$75(i10, file3, list);
                        break;
                    default:
                        fileManagerActivity.lambda$loadFiles$76(i10, file3, list);
                        break;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        setNetworkNeighborVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$promptForConflict$106(androidx.appcompat.app.o oVar, int[] iArr, CheckBox checkBox, boolean[] zArr, View view) {
        oVar.dismiss();
        synchronized (this) {
            try {
                iArr[0] = 0;
                if (checkBox.isChecked()) {
                    this.globalConflictAction = 0;
                }
                zArr[0] = true;
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$promptForConflict$107(androidx.appcompat.app.o oVar, int[] iArr, CheckBox checkBox, boolean[] zArr, View view) {
        oVar.dismiss();
        synchronized (this) {
            try {
                iArr[0] = 1;
                if (checkBox.isChecked()) {
                    this.globalConflictAction = 1;
                }
                zArr[0] = true;
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$promptForConflict$108(androidx.appcompat.app.o oVar, int[] iArr, CheckBox checkBox, boolean[] zArr, View view) {
        oVar.dismiss();
        synchronized (this) {
            try {
                iArr[0] = 2;
                if (checkBox.isChecked()) {
                    this.globalConflictAction = 2;
                }
                zArr[0] = true;
                notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$promptForConflict$109(androidx.appcompat.app.o oVar, boolean[] zArr, View view) {
        this.isPasteCancelled = true;
        oVar.dismiss();
        synchronized (this) {
            zArr[0] = true;
            notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$promptForConflict$110(String str, int[] iArr, boolean[] zArr) {
        View inflate = LayoutInflater.from(this).inflate(g4.q.dialog_conflict, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(g4.p.conflict_file_name);
        CheckBox checkBox = (CheckBox) inflate.findViewById(g4.p.checkbox_apply_all);
        textView.setText(str);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("文件冲突");
        nVar.u(inflate);
        nVar.k();
        androidx.appcompat.app.o e7 = nVar.e();
        Button button = (Button) inflate.findViewById(g4.p.btn_overwrite_conflict);
        Button button2 = (Button) inflate.findViewById(g4.p.btn_rename_conflict);
        Button button3 = (Button) inflate.findViewById(g4.p.btn_skip_conflict);
        Button button4 = (Button) inflate.findViewById(g4.p.btn_cancel_conflict);
        if (button != null) {
            button.setOnClickListener(new r3(this, e7, iArr, checkBox, zArr, 0));
        }
        if (button2 != null) {
            button2.setOnClickListener(new r3(this, e7, iArr, checkBox, zArr, 1));
        }
        if (button3 != null) {
            button3.setOnClickListener(new r3(this, e7, iArr, checkBox, zArr, 2));
        }
        if (button4 != null) {
            button4.setOnClickListener(new k3(this, e7, zArr));
        }
        e7.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCloudBackupAvailability$6(int i7) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        this.cloudBackupAvailable = i7 >= 0;
        this.cloudBackupCredentialIndex = i7;
        invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCloudBackupAvailability$7(int i7, List list) {
        int i8 = (i7 < 0 || !isCloudCredentialValid((CloudDiskCredential) list.get(i7))) ? -1 : i7;
        if (i8 < 0) {
            int i9 = 0;
            while (true) {
                if (i9 >= list.size()) {
                    break;
                }
                if (i9 != i7 && isCloudCredentialValid((CloudDiskCredential) list.get(i9))) {
                    i8 = i9;
                    break;
                }
                i9++;
            }
        }
        runOnUiThread(new u3(this, i8, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCloudDisks$10(CloudDiskCredential cloudDiskCredential, View view) {
        exitSelectionMode();
        enterCloudDisk(cloudDiskCredential);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCloudDisks$11(int i7, CloudDiskCredential cloudDiskCredential, DialogInterface dialogInterface, int i8) {
        this.savedCloudCredentials.remove(i7);
        CloudDiskCredential.saveAll(this, this.savedCloudCredentials);
        this.cloudBackupCheckedAt = 0L;
        if (this.isCloudDiskMode && this.currentCloudCredential == cloudDiskCredential) {
            exitCloudDisk();
        }
        refreshCloudDisks();
        refreshCloudBackupAvailability();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$refreshCloudDisks$12(CloudDiskCredential cloudDiskCredential, int i7, View view) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("管理网盘");
        nVar.m("确定要移除" + cloudDiskCredential.name + "吗？");
        nVar.r("移除", new u5(i7, cloudDiskCredential, this));
        nVar.o("取消", null);
        nVar.v();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshCloudDisks$13(View view) {
        showCloudDiskLoginDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshStorageVolumes$1(File file, View view) {
        this.isAppMode = false;
        this.isZipMode = false;
        this.isSearchMode = false;
        this.isCloudDiskMode = false;
        this.currentCloudCredential = null;
        this.isSelectionMode = false;
        this.selectedFiles.clear();
        if (getSupportActionBar() != null) {
            getSupportActionBar().t("文件助手");
        }
        loadFiles(file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDirectoryItemCount$80(File file, String str) {
        File[] listFiles = file.listFiles();
        int i7 = 0;
        this.directoryChildCountCache.put(str, Integer.valueOf(listFiles != null ? listFiles.length : 0));
        this.pendingDirectoryCountPaths.remove(str);
        runOnUiThread(new z3(this, str, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scanCategoryFiles$58(View view) {
        loadFiles(Environment.getExternalStorageDirectory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scanCategoryFiles$59(List list) {
        this.progressScan.setVisibility(8);
        this.recyclerView.setVisibility(0);
        switchLayout(this.isGridMode);
        this.adapter.setFiles(list);
        if (list.isEmpty()) {
            this.textEmpty.setText("未找到相关文件");
            this.textEmpty.setVisibility(0);
            this.recyclerView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scanCategoryFiles$60(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = new ArrayList();
        int i7 = 0;
        for (int i8 = 0; i8 < strArr.length; i8++) {
            if (i8 > 0) {
                sb.append(" OR ");
            }
            sb.append("_data LIKE ?");
            arrayList2.add("%" + strArr[i8]);
        }
        arrayList.addAll(queryMediaStore(MediaStore.Files.getContentUri("external"), sb.toString(), (String[]) arrayList2.toArray(new String[0])));
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (externalStoragePublicDirectory.exists() && externalStoragePublicDirectory.isDirectory()) {
            scanDirRecursive(externalStoragePublicDirectory, arrayList, strArr);
        }
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/filemanager");
        if (file.exists() && file.isDirectory()) {
            scanDirRecursive(file, arrayList, strArr);
        }
        ArrayList arrayList3 = new ArrayList();
        HashSet hashSet = new HashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File file2 = (File) it.next();
            if (hashSet.add(file2.getAbsolutePath())) {
                arrayList3.add(file2);
            }
        }
        sortFiles(arrayList3);
        runOnUiThread(new j3(i7, this, arrayList3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scanInstalledApps$61(View view) {
        loadFiles(Environment.getExternalStorageDirectory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ int lambda$scanInstalledApps$62(android.content.pm.PackageManager r3, android.content.pm.PackageInfo r4, android.content.pm.PackageInfo r5) {
        /*
            r0 = 0
            android.content.pm.ApplicationInfo r1 = r4.applicationInfo     // Catch: java.lang.Exception -> L10
            java.lang.CharSequence r1 = r1.loadLabel(r3)     // Catch: java.lang.Exception -> L10
            android.content.pm.ApplicationInfo r2 = r5.applicationInfo     // Catch: java.lang.Exception -> Le
            java.lang.CharSequence r0 = r2.loadLabel(r3)     // Catch: java.lang.Exception -> Le
            goto L15
        Le:
            r3 = move-exception
            goto L12
        L10:
            r3 = move-exception
            r1 = r0
        L12:
            r3.printStackTrace()
        L15:
            if (r1 == 0) goto L1c
            java.lang.String r3 = r1.toString()
            goto L1e
        L1c:
            java.lang.String r3 = r4.packageName
        L1e:
            if (r0 == 0) goto L25
            java.lang.String r4 = r0.toString()
            goto L27
        L25:
            java.lang.String r4 = r5.packageName
        L27:
            int r3 = r3.compareToIgnoreCase(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.FileManagerActivity.lambda$scanInstalledApps$62(android.content.pm.PackageManager, android.content.pm.PackageInfo, android.content.pm.PackageInfo):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scanInstalledApps$63(List list) {
        this.progressScan.setVisibility(8);
        this.recyclerView.setVisibility(0);
        if (this.appAdapter == null) {
            this.appAdapter = new InstalledAppAdapter();
        }
        this.appAdapter.setApps(list);
        switchLayout(this.isGridMode);
        if (list.isEmpty()) {
            this.textEmpty.setText("没有找到用户应用");
            this.textEmpty.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scanInstalledApps$64() {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = getPackageManager();
        for (PackageInfo packageInfo : packageManager.getInstalledPackages(0)) {
            int i7 = packageInfo.applicationInfo.flags;
            if ((i7 & 1) == 0 && (i7 & 128) == 0) {
                arrayList.add(packageInfo);
            }
        }
        Collections.sort(arrayList, new w(2, packageManager));
        runOnUiThread(new j3(3, this, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$searchFiles$65(View view) {
        loadFiles(Environment.getExternalStorageDirectory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$searchFiles$66(List list) {
        this.progressScan.setVisibility(8);
        this.recyclerView.setVisibility(0);
        switchLayout(this.isGridMode);
        this.adapter.setFiles(list);
        if (list.isEmpty()) {
            this.textEmpty.setText("未找到相关文件");
            this.textEmpty.setVisibility(0);
            this.recyclerView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$searchFiles$67(String str) {
        ArrayList arrayList = new ArrayList();
        List<File> storageRoots = getStorageRoots();
        if (storageRoots.isEmpty()) {
            storageRoots.add(Environment.getExternalStorageDirectory());
        }
        Iterator<File> it = storageRoots.iterator();
        while (it.hasNext()) {
            searchDirRecursive(it.next(), arrayList, str.toLowerCase());
        }
        sortFiles(arrayList);
        runOnUiThread(new j3(4, this, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupActions$31(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupActions$32(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$33(View view) {
        exitSelectionMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$34(View view) {
        toggleSelectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$35(View view) {
        showDeleteSelectionDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$36(View view) {
        copySelection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$37(View view) {
        cutSelection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$38(View view) {
        if (this.selectedFiles.size() == 1) {
            showRenameDialog(this.selectedFiles.get(0));
        } else {
            ToastHelper.show(this, "只能重命名单个文件");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$39(View view) {
        compressSelection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$40(View view) {
        showPropertiesDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$41(View view) {
        this.clipboardFiles.clear();
        this.layoutPasteActions.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupActions$42(View view) {
        pasteFiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupCategories$51(View view) {
        scanInstalledApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupCategories$52(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/filemanager");
        if (!file.exists()) {
            file.mkdirs();
        }
        loadFiles(file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupCategories$53(View view) {
        scanCategoryFiles(".apk");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupCategories$54(View view) {
        scanCategoryFiles(".zip", ".rar", ".7z");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupCategories$55(View view) {
        scanCategoryFiles(".jpg", ".jpeg", ".png", ".gif", ".bmp");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupCategories$56(View view) {
        scanCategoryFiles(".mp4", ".mkv", ".avi", ".mov");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupCategories$57(View view) {
        scanCategoryFiles(".mp3", ".wav", ".aac", ".flac", ".wma", ".m4a");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupHttpServer$119(CompoundButton compoundButton, boolean z6) {
        if (z6) {
            startHttpServer();
            return;
        }
        stopHttpServer();
        this.textHttpServerAddress.setVisibility(8);
        this.btnShowQrCode.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupStorageInfo$50(View view) {
        startActivity(new Intent(this, (Class<?>) StorageAnalysisActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showAppActionDialog$112(PackageInfo packageInfo, DialogInterface dialogInterface, int i7) {
        Intent intent = new Intent("android.intent.action.DELETE", Uri.parse("package:" + packageInfo.packageName));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCloudBackupDialog$9(View view) {
        cancelConfigBackup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCloudDiskLoginDialog$2(CloudDiskCredential cloudDiskCredential) {
        refreshCloudDisks();
        invalidateOptionsMenu();
        enterCloudDisk(cloudDiskCredential);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCloudDiskLoginDialog$3(CloudDiskCredential cloudDiskCredential) {
        int addOrUpdate = CloudDiskCredential.addOrUpdate(this, cloudDiskCredential, this.savedCloudCredentials.size());
        this.cloudBackupAvailable = true;
        this.cloudBackupCredentialIndex = addOrUpdate;
        this.cloudBackupCheckedAt = System.currentTimeMillis();
        runOnUiThread(new z0(15, this, cloudDiskCredential));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCloudDiskLoginDialog$4(String str) {
        ToastHelper.show(this, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCloudDiskLoginDialog$5(String str) {
        runOnUiThread(new z3(this, str, 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCreateDialog$114(EditText editText, boolean z6, DialogInterface dialogInterface, int i7) {
        String c7 = a.a.c(editText);
        if (c7.isEmpty()) {
            ToastHelper.show(this, "名称不能为空");
        } else {
            createFileOrFolder(c7, z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCreateDialog$115(EditText editText, DialogInterface dialogInterface) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCreateOptions$113(DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            showCreateDialog(true);
        } else {
            showCreateDialog(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showDeleteSelectionDialog$86(DialogInterface dialogInterface, int i7) {
        startDeleteSelected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$showMultiFileProperties$45(androidx.appcompat.app.o oVar, StringBuilder sb) {
        String sb2 = sb.toString();
        androidx.appcompat.app.m mVar = oVar.f840f;
        mVar.f804f = sb2;
        TextView textView = mVar.B;
        if (textView != null) {
            textView.setText(sb2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showMultiFileProperties$46(androidx.appcompat.app.o oVar) {
        Iterator<File> it = this.selectedFiles.iterator();
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        while (true) {
            int i7 = 0;
            if (!it.hasNext()) {
                StringBuilder j10 = a.a.j("选中项目: ");
                j10.append(this.selectedFiles.size());
                j10.append(" 个\n总大小: ");
                j10.append(formatSize(j7));
                j10.append("\n包含总计: ");
                j10.append(j8);
                j10.append(" 个文件, ");
                j10.append(j9);
                j10.append(" 个文件夹");
                runOnUiThread(new n3(oVar, j10, i7));
                return;
            }
            File next = it.next();
            if (next.isDirectory()) {
                long[] countRecursive = countRecursive(next);
                j7 += countRecursive[0];
                j8 += countRecursive[1];
                j9 = countRecursive[2] + 1 + j9;
            } else {
                j8++;
                j7 = next.length() + j7;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showReceiveProgressDialog$26(DialogInterface dialogInterface, int i7) {
        androidx.appcompat.app.o oVar = this.transferProgressDialog;
        if (oVar != null) {
            oVar.dismiss();
            this.transferProgressDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showRenameDialog$111(EditText editText, File file, DialogInterface dialogInterface, int i7) {
        String c7 = a.a.c(editText);
        if (c7.isEmpty() || c7.equals(file.getName())) {
            return;
        }
        if (!file.renameTo(new File(file.getParentFile(), c7))) {
            ToastHelper.show(this, "重命名失败");
            return;
        }
        ToastHelper.show(this, "重命名成功");
        exitSelectionMode();
        loadFiles(this.currentDir);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$showSendDialog$27(EditText editText, View view) {
        String obj = editText.getText().toString();
        if (obj.isEmpty()) {
            return;
        }
        q4.h hVar = this.transferManager;
        String str = "general";
        String str2 = hVar.f8482r;
        if (str2 != null) {
            hVar.f8467c.execute(new o4.w(hVar, str, str2, obj, 4));
        }
        editText.setText(s5.h0.FRAGMENT_ENCODE_SET);
        ToastHelper.show(this, "发送成功");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSendDialog$28(androidx.appcompat.app.o oVar, View view) {
        Intent intent = new Intent(this, (Class<?>) FileManagerActivity.class);
        intent.setAction(ACTION_PICK_FILE);
        startActivityForResult(intent, 2001);
        oVar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$showSingleFileProperties$43(androidx.appcompat.app.o oVar, StringBuilder sb) {
        String sb2 = sb.toString();
        androidx.appcompat.app.m mVar = oVar.f840f;
        mVar.f804f = sb2;
        TextView textView = mVar.B;
        if (textView != null) {
            textView.setText(sb2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showSingleFileProperties$44(File file, androidx.appcompat.app.o oVar) {
        String formatSize;
        String str;
        String name = file.getName();
        String absolutePath = file.getAbsolutePath();
        String extension = file.isDirectory() ? "文件夹" : getExtension(name);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(file.lastModified()));
        int i7 = 1;
        if (file.isDirectory()) {
            long[] countRecursive = countRecursive(file);
            formatSize = formatSize(countRecursive[0]);
            str = String.format(Locale.getDefault(), "\n包含: %d 个文件, %d 个文件夹", Long.valueOf(countRecursive[1]), Long.valueOf(countRecursive[2]));
        } else {
            formatSize = formatSize(file.length());
            str = s5.h0.FRAGMENT_ENCODE_SET;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("名称: ");
        sb.append(name);
        sb.append("\n类型: ");
        sb.append(extension);
        sb.append("\n位置: ");
        sb.append(absolutePath);
        sb.append("\n大小: ");
        sb.append(formatSize);
        sb.append("\n");
        if (!str.isEmpty()) {
            sb.append(str);
            sb.append("\n");
        }
        sb.append("修改时间: ");
        sb.append(format);
        sb.append("\n读写权限: ");
        sb.append(file.canRead() ? "读" : "-");
        sb.append("/");
        sb.append(file.canWrite() ? "写" : "-");
        runOnUiThread(new n3(oVar, sb, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUnzipDialog$81(File file, DialogInterface dialogInterface, int i7) {
        if (i7 == 0) {
            enterZipMode(file);
        } else {
            showUnzipOptionsDialog(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUnzipOptionsDialog$82(File file, DialogInterface dialogInterface, int i7) {
        AnonymousClass6 anonymousClass6 = new AnonymousClass6();
        if (file.getName().toLowerCase().endsWith(".rar")) {
            ZipHelper.unrar(this, file, this.customUnzipTargetDir, anonymousClass6);
        } else {
            ZipHelper.unzip(this, file, this.customUnzipTargetDir, anonymousClass6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showUnzipOptionsDialog$83(File file, androidx.appcompat.app.o oVar, View view) {
        this.pendingUnzipFile = file;
        oVar.dismiss();
        Intent intent = new Intent(this, (Class<?>) FileManagerActivity.class);
        intent.setAction(ACTION_PICK_FOLDER);
        startActivityForResult(intent, REQUEST_CODE_PICK_FOLDER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ int lambda$sortFileItems$73(FileItem fileItem, FileItem fileItem2) {
        boolean z6 = fileItem.isDirectory;
        if (z6 && !fileItem2.isDirectory) {
            return -1;
        }
        if (!z6 && fileItem2.isDirectory) {
            return 1;
        }
        int i7 = this.currentSortType;
        int compareToIgnoreCase = i7 != 1 ? i7 != 2 ? fileItem.name.compareToIgnoreCase(fileItem2.name) : Long.compare(fileItem.date, fileItem2.date) : Long.compare(fileItem.size, fileItem2.size);
        return this.isSortAscending ? compareToIgnoreCase : -compareToIgnoreCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ int lambda$sortFileItemsByName$74(FileItem fileItem, FileItem fileItem2) {
        boolean z6 = fileItem.isDirectory;
        if (z6 && !fileItem2.isDirectory) {
            return -1;
        }
        if (!z6 && fileItem2.isDirectory) {
            return 1;
        }
        int compareToIgnoreCase = fileItem.name.compareToIgnoreCase(fileItem2.name);
        return this.isSortAscending ? compareToIgnoreCase : -compareToIgnoreCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ int lambda$sortFiles$72(File file, File file2) {
        if (file.isDirectory() && !file2.isDirectory()) {
            return -1;
        }
        if (!file.isDirectory() && file2.isDirectory()) {
            return 1;
        }
        int i7 = this.currentSortType;
        int compareToIgnoreCase = i7 != 1 ? i7 != 2 ? file.getName().compareToIgnoreCase(file2.getName()) : Long.compare(file.lastModified(), file2.lastModified()) : Long.compare(file.length(), file2.length());
        return this.isSortAscending ? compareToIgnoreCase : -compareToIgnoreCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startConfigBackup$8(DialogInterface dialogInterface, int i7) {
        beginConfigBackup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startDeleteSelected$87(int i7) {
        String[] strArr;
        this.isDeletingSelected = false;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        ToastHelper.show(this, "成功删除 " + i7 + " 个项目");
        if (this.isSearchMode && (strArr = this.mLastScanExtensions) != null) {
            scanCategoryFiles(strArr);
        } else if (this.isAppMode) {
            scanInstalledApps();
        } else {
            loadFiles(this.currentDir);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startDeleteSelected$88(List list) {
        int i7 = 0;
        try {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (deleteRecursive((File) it.next())) {
                    i7++;
                }
            }
        } catch (Throwable unused) {
        }
        runOnUiThread(new u3(this, i7, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startHttpServer$120() {
        androidx.appcompat.app.o oVar = this.qrDialog;
        if (oVar == null || !oVar.isShowing()) {
            return;
        }
        this.qrDialog.dismiss();
        this.qrDialog = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startHttpServer$121() {
        runOnUiThread(new c4(this, 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startHttpServer$122(String str, View view) {
        showQrCodeDialog(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$streamNextBatch$78(int i7, File file, int i8, List list) {
        if (isDirectoryLoadValid(i7, file) && i8 < list.size()) {
            int min = Math.min(i8 + 220, list.size());
            this.adapter.appendItems(new ArrayList(list.subList(i8, min)));
            streamNextBatch(i7, file, list, min);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBreadcrumbs$68(View view) {
        exitZipMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBreadcrumbs$69(View view) {
        loadZipFolder(s5.h0.FRAGMENT_ENCODE_SET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBreadcrumbs$70(String str, View view) {
        loadZipFolder(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateBreadcrumbs$71(File file, View view) {
        loadFiles(file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uploadToCloudDisk$89(boolean[] zArr, androidx.appcompat.app.o oVar, View view) {
        zArr[0] = true;
        oVar.dismiss();
        ToastHelper.show(this, "已取消上传");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$uploadToCloudDisk$90(TextView textView, File file, TextView textView2, int i7, int i8) {
        if (textView != null) {
            textView.setText("计算文件指纹: " + file.getName());
        }
        if (textView2 != null) {
            textView2.setText((i7 + 1) + " / " + i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$uploadToCloudDisk$91(TextView textView, File file) {
        if (textView != null) {
            textView.setText("获取上传地址: " + file.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$uploadToCloudDisk$92(TextView textView, File file, TextView textView2) {
        if (textView != null) {
            textView.setText("上传中: " + file.getName());
        }
        if (textView2 != null) {
            textView2.setText("0%");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$uploadToCloudDisk$93(ProgressBar progressBar, int i7, TextView textView) {
        if (progressBar != null) {
            progressBar.setProgress(i7);
        }
        if (textView != null) {
            textView.setText(i7 + "%");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uploadToCloudDisk$94(long j7, AtomicLong atomicLong, File file, int[] iArr, ProgressBar progressBar, TextView textView, int i7) {
        if (j7 <= 0) {
            return;
        }
        int min = (int) Math.min(100L, ((((file.length() * Math.max(0, Math.min(100, i7))) / 100) + atomicLong.get()) * 100) / j7);
        if (min == iArr[0]) {
            return;
        }
        iArr[0] = min;
        runOnUiThread(new g4(progressBar, min, textView, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$uploadToCloudDisk$95(AtomicLong atomicLong, ProgressBar progressBar, long j7, TextView textView) {
        long j8 = atomicLong.get();
        if (progressBar != null && j7 > 0) {
            progressBar.setProgress((int) ((j8 * 100) / j7));
        }
        if (textView == null || j7 <= 0) {
            return;
        }
        textView.setText(((j8 * 100) / j7) + "%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uploadToCloudDisk$96(androidx.appcompat.app.o oVar, File file, String str) {
        oVar.dismiss();
        ToastHelper.show(this, file.getName() + ": " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uploadToCloudDisk$97(androidx.appcompat.app.o oVar, int[] iArr, int i7) {
        oVar.dismiss();
        this.clipboardFiles.clear();
        this.layoutPasteActions.setVisibility(8);
        ToastHelper.show(this, "上传完成: " + iArr[0] + " / " + i7 + " 个文件");
        loadCloudFiles(this.currentCloudParentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$uploadToCloudDisk$98(List list, boolean[] zArr, TextView textView, TextView textView2, int i7, final TextView textView3, final long j7, final ProgressBar progressBar, int[] iArr, boolean z6, androidx.appcompat.app.o oVar) {
        File file;
        TextView textView4 = textView;
        androidx.appcompat.app.o oVar2 = oVar;
        final AtomicLong atomicLong = new AtomicLong(0L);
        char c7 = 0;
        int i8 = 0;
        while (i8 < list.size() && !zArr[c7]) {
            final File file2 = (File) list.get(i8);
            try {
                runOnUiThread(new o4.f(textView, file2, textView2, i8, i7));
                String fileMd5 = Pan123Api.getFileMd5(file2);
                runOnUiThread(new z0(13, textView4, file2));
                Pan123Api.UploadInfo uploadUrl = Pan123Api.getUploadUrl(this.currentCloudCredential, this.currentCloudParentId, file2.getName(), file2.length(), fileMd5);
                if (uploadUrl.reuse) {
                    file2.getName();
                    file = file2;
                } else {
                    runOnUiThread(new w2(textView4, file2, textView3, 11));
                    final int[] iArr2 = {-1};
                    file = file2;
                    if (!Pan123Api.uploadFileData(this.currentCloudCredential, uploadUrl, file, new Pan123Api.UploadCallback() { // from class: com.navi.tool.ui.extra.d3
                        @Override // com.navi.tool.utils.Pan123Api.UploadCallback
                        public final void onProgress(int i9) {
                            FileManagerActivity.this.lambda$uploadToCloudDisk$94(j7, atomicLong, file2, iArr2, progressBar, textView3, i9);
                        }
                    })) {
                        throw new Exception("服务器未确认上传完成");
                    }
                }
            } catch (Exception e7) {
                e = e7;
                file = file2;
            }
            try {
                atomicLong.addAndGet(file.length());
                iArr[0] = iArr[0] + 1;
                runOnUiThread(new Runnable() { // from class: com.navi.tool.ui.extra.e3
                    @Override // java.lang.Runnable
                    public final void run() {
                        FileManagerActivity.lambda$uploadToCloudDisk$95(atomicLong, progressBar, j7, textView3);
                    }
                });
                if (z6) {
                    file.delete();
                }
                i8++;
                oVar2 = oVar;
                c7 = 0;
                textView4 = textView;
            } catch (Exception e8) {
                e = e8;
                runOnUiThread(new f3(this, oVar, file, e.getMessage() != null ? e.getMessage() : "上传失败"));
                return;
            }
        }
        runOnUiThread(new g3(this, oVar2, iArr, i7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadCloudFiles(String str) {
        this.currentCloudParentId = str;
        this.progressScan.setVisibility(0);
        this.recyclerView.setVisibility(8);
        this.textEmpty.setVisibility(8);
        this.executor.execute(new z3(this, str, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFiles(File file) {
        if (this.isCloudDiskMode) {
            this.isCloudDiskMode = false;
            this.currentCloudCredential = null;
            this.currentCloudParentId = "0";
            this.cloudPathStack.clear();
            this.cloudNameStack.clear();
            this.cloudFiles.clear();
        }
        this.isSearchMode = false;
        this.isAppMode = false;
        invalidateOptionsMenu();
        this.currentDir = file;
        updateBreadcrumbs(file);
        switchLayout(this.isGridMode);
        this.progressScan.setVisibility(0);
        this.recyclerView.setVisibility(8);
        this.textEmpty.setVisibility(8);
        int incrementAndGet = this.directoryLoadVersion.incrementAndGet();
        ExecutorService executorService = this.directoryLoadExecutor;
        if (executorService == null || executorService.isShutdown() || this.directoryLoadExecutor.isTerminated()) {
            return;
        }
        try {
            this.directoryLoadExecutor.execute(new g4(this, file, incrementAndGet, 4));
        } catch (RejectedExecutionException unused) {
            this.progressScan.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadZipFolder(String str) {
        this.currentZipPath = str;
        updateBreadcrumbs(null);
        List<FileItem> list = this.zipCache.get(str);
        if (list == null) {
            list = new ArrayList<>();
        }
        sortFileItems(list);
        this.adapter.setItems(list);
        this.textEmpty.setVisibility(list.isEmpty() ? 0 : 8);
        this.recyclerView.setVisibility(list.isEmpty() ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: notifyDirectoryItemCountReady, reason: merged with bridge method [inline-methods] */
    public void lambda$requestDirectoryItemCount$79(String str) {
        File file;
        FileAdapter fileAdapter = this.adapter;
        if (fileAdapter == null) {
            return;
        }
        List<FileItem> items = fileAdapter.getItems();
        for (int i7 = 0; i7 < items.size(); i7++) {
            FileItem fileItem = items.get(i7);
            if (!fileItem.isZipEntry && fileItem.isDirectory && (file = fileItem.file) != null && str.equals(file.getAbsolutePath())) {
                this.adapter.notifyItemChanged(i7);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openFile(File file) {
        String lowerCase = file.getName().toLowerCase();
        if (lowerCase.endsWith(".apk")) {
            installApk(file);
            return;
        }
        if (lowerCase.endsWith(".zip") || lowerCase.endsWith(".rar")) {
            showUnzipDialog(file);
            return;
        }
        if (isImageFileName(lowerCase)) {
            viewImage(file);
            return;
        }
        if (isVideoFileName(lowerCase)) {
            openVideoPreview(file);
            return;
        }
        if (lowerCase.endsWith(".txt") || lowerCase.endsWith(".xml") || lowerCase.endsWith(".json") || lowerCase.endsWith(".log") || lowerCase.endsWith(".gradle") || lowerCase.endsWith(".properties") || lowerCase.endsWith(".md") || lowerCase.endsWith(".java") || lowerCase.endsWith(".conf") || lowerCase.endsWith(".sh") || lowerCase.endsWith(".ini") || lowerCase.endsWith(".yaml") || lowerCase.endsWith(".yml") || lowerCase.endsWith(".cfg") || !lowerCase.contains(".")) {
            openTextEditor(file);
            return;
        }
        if (!lowerCase.endsWith(".mp3") && !lowerCase.endsWith(".wav") && !lowerCase.endsWith(".aac") && !lowerCase.endsWith(".m4a") && !lowerCase.endsWith(".flac") && !lowerCase.endsWith(".wma")) {
            ToastHelper.show(this, "不支持打开此类型文件");
            return;
        }
        AudioPlayerHelper audioPlayerHelper = this.audioPlayerHelper;
        if (audioPlayerHelper != null) {
            audioPlayerHelper.playAudio(file);
        }
    }

    private void openTextEditor(File file) {
        Intent intent = new Intent(this, (Class<?>) TextEditorActivity.class);
        intent.putExtra(TextEditorActivity.EXTRA_FILE_PATH, file.getAbsolutePath());
        startActivity(intent);
    }

    private void openVideoPreview(File file) {
        Intent intent = new Intent(this, (Class<?>) FileVideoPreviewActivity.class);
        intent.putExtra(FileVideoPreviewActivity.EXTRA_VIDEO_PATH, file.getAbsolutePath());
        startActivity(intent);
    }

    private long parseCloudTime(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(str).getTime();
        } catch (Exception unused) {
            return 0L;
        }
    }

    private void parseZip(File file, Charset charset) {
        String str;
        String str2;
        if (charset != null) {
            this.openZipFile = new ZipFile(file, charset);
        } else {
            this.openZipFile = new ZipFile(file);
        }
        this.zipCache.clear();
        this.zipCache.put(s5.h0.FRAGMENT_ENCODE_SET, new ArrayList());
        Enumeration<? extends ZipEntry> entries = this.openZipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            String name = nextElement.getName();
            boolean isDirectory = nextElement.isDirectory();
            boolean z6 = false;
            if (isDirectory && name.endsWith("/")) {
                name = name.substring(0, name.length() - 1);
            }
            String[] split = name.split("/");
            String str3 = s5.h0.FRAGMENT_ENCODE_SET;
            int i7 = 0;
            while (i7 < split.length) {
                String str4 = split[i7];
                boolean z7 = i7 == split.length - 1 ? true : z6;
                if (str3.isEmpty()) {
                    str = str4;
                } else {
                    str = str3 + "/" + str4;
                }
                boolean z8 = (!z7 || isDirectory) ? true : z6;
                List<FileItem> list = this.zipCache.get(str3);
                if (list == null) {
                    list = new ArrayList<>();
                    this.zipCache.put(str3, list);
                }
                List<FileItem> list2 = list;
                if (findZipItem(list2, str4) == null) {
                    str2 = str;
                    list2.add(new FileItem(str4, str, z8, z7 ? nextElement.getSize() : 0L, z7 ? nextElement.getTime() : 0L));
                } else {
                    str2 = str;
                }
                if (z8 && !this.zipCache.containsKey(str2)) {
                    this.zipCache.put(str2, new ArrayList());
                }
                i7++;
                str3 = str2;
                z6 = false;
            }
        }
    }

    private void pasteFiles() {
        if (this.clipboardFiles.isEmpty()) {
            return;
        }
        if (this.isCloudDiskMode) {
            uploadToCloudDisk();
        } else {
            this.globalConflictAction = -1;
            executePaste();
        }
    }

    private int promptForConflict(String str) {
        int[] iArr = {1};
        boolean[] zArr = {false};
        runOnUiThread(new f3(this, str, iArr, zArr));
        synchronized (this) {
            while (!zArr[0] && !this.isPasteCancelled) {
                try {
                    wait();
                } catch (InterruptedException e7) {
                    e7.printStackTrace();
                }
            }
        }
        return iArr[0];
    }

    private List<File> queryMediaStore(Uri uri) {
        return queryMediaStore(uri, null, null);
    }

    private List<File> queryMediaStore(Uri uri, String str, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = getContentResolver().query(uri, new String[]{"_data"}, str, strArr, "date_modified DESC");
            if (query != null) {
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndexOrThrow);
                        if (string != null) {
                            File file = new File(string);
                            if (file.exists()) {
                                arrayList.add(file);
                            }
                        }
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        return arrayList;
    }

    private void refreshCloudBackupAvailability() {
        if (ConfigBackupService.running) {
            invalidateOptionsMenu();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.cloudBackupCheckedAt < 300000) {
            invalidateOptionsMenu();
            return;
        }
        this.cloudBackupCheckedAt = currentTimeMillis;
        List<CloudDiskCredential> loadAll = CloudDiskCredential.loadAll(this);
        int i7 = -1;
        int i8 = 0;
        if (loadAll.isEmpty()) {
            this.cloudBackupAvailable = false;
            this.cloudBackupCredentialIndex = -1;
            invalidateOptionsMenu();
            return;
        }
        if (this.isCloudDiskMode && this.currentCloudCredential != null) {
            while (true) {
                if (i8 < loadAll.size()) {
                    if (loadAll.get(i8).token != null && loadAll.get(i8).token.equals(this.currentCloudCredential.token)) {
                        i7 = i8;
                        break;
                    }
                    i8++;
                } else {
                    break;
                }
            }
        }
        this.metadataExecutor.execute(new g4(this, i7, loadAll, 3));
    }

    private void refreshCloudDisks() {
        LinearLayout linearLayout = this.layoutCloudDisks;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        List<CloudDiskCredential> loadAll = CloudDiskCredential.loadAll(this);
        this.savedCloudCredentials = loadAll;
        if (loadAll.isEmpty()) {
            this.cloudBackupAvailable = false;
            this.cloudBackupCredentialIndex = -1;
            invalidateOptionsMenu();
        }
        for (final int i7 = 0; i7 < this.savedCloudCredentials.size(); i7++) {
            final CloudDiskCredential cloudDiskCredential = this.savedCloudCredentials.get(i7);
            View inflate = LayoutInflater.from(this).inflate(g4.q.item_sidebar_storage, (ViewGroup) this.layoutCloudDisks, false);
            TextView textView = (TextView) inflate.findViewById(g4.p.title);
            ImageView imageView = (ImageView) inflate.findViewById(g4.p.icon);
            String str = cloudDiskCredential.name;
            textView.setText((str == null || str.isEmpty()) ? "123网盘" : cloudDiskCredential.name);
            imageView.setImageResource(g4.o.ic_123pan);
            inflate.setOnClickListener(new d1(4, this, cloudDiskCredential));
            inflate.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.navi.tool.ui.extra.b4
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    boolean lambda$refreshCloudDisks$12;
                    lambda$refreshCloudDisks$12 = this.lambda$refreshCloudDisks$12(cloudDiskCredential, i7, view);
                    return lambda$refreshCloudDisks$12;
                }
            });
            this.layoutCloudDisks.addView(inflate);
        }
        if (this.savedCloudCredentials.isEmpty()) {
            View inflate2 = LayoutInflater.from(this).inflate(g4.q.item_sidebar_storage, (ViewGroup) this.layoutCloudDisks, false);
            TextView textView2 = (TextView) inflate2.findViewById(g4.p.title);
            ImageView imageView2 = (ImageView) inflate2.findViewById(g4.p.icon);
            textView2.setText("添加网盘");
            imageView2.setImageResource(g4.o.ic_action_add);
            inflate2.setOnClickListener(new a4(this, 1));
            this.layoutCloudDisks.addView(inflate2);
        }
        if (this.isCloudDiskMode) {
            for (CloudDiskCredential cloudDiskCredential2 : this.savedCloudCredentials) {
                int i8 = cloudDiskCredential2.type;
                CloudDiskCredential cloudDiskCredential3 = this.currentCloudCredential;
                if (i8 == cloudDiskCredential3.type && cloudDiskCredential2.token == cloudDiskCredential3.token) {
                    return;
                }
            }
            exitCloudDisk();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshNetworkNeighborIndicator() {
        this.networkNeighborHandler.removeCallbacks(this.hideNetworkNeighborRunnable);
        setNetworkNeighborVisible(true);
        this.networkNeighborHandler.postDelayed(this.hideNetworkNeighborRunnable, NETWORK_NEIGHBOR_OFFLINE_TIMEOUT_MS);
    }

    private void refreshStorageVolumes() {
        LinearLayout linearLayout = this.layoutStorageVolumes;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        StorageManager storageManager = (StorageManager) getSystemService("storage");
        if (storageManager == null) {
            return;
        }
        for (StorageVolume storageVolume : storageManager.getStorageVolumes()) {
            File volumePath = getVolumePath(storageVolume);
            if (volumePath != null) {
                String description = storageVolume.getDescription(this);
                if (description == null || description.isEmpty()) {
                    description = "存储设备";
                }
                View inflate = LayoutInflater.from(this).inflate(g4.q.item_sidebar_storage, (ViewGroup) this.layoutStorageVolumes, false);
                TextView textView = (TextView) inflate.findViewById(g4.p.title);
                ImageView imageView = (ImageView) inflate.findViewById(g4.p.icon);
                textView.setText(description);
                imageView.setImageResource(g4.o.ic_folder_yellow);
                inflate.setOnClickListener(new q3(this, volumePath, 0));
                this.layoutStorageVolumes.addView(inflate);
            }
        }
    }

    private void registerCloudBackupReceiver() {
        if (this.cloudBackupReceiverRegistered) {
            return;
        }
        try {
            g0.g.c(this, this.cloudBackupReceiver, new IntentFilter(ConfigBackupService.ACTION_PROGRESS));
            this.cloudBackupReceiverRegistered = true;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestDirectoryItemCount(File file) {
        if (file == null) {
            return;
        }
        String absolutePath = file.getAbsolutePath();
        if (!this.directoryChildCountCache.containsKey(absolutePath) && this.pendingDirectoryCountPaths.add(absolutePath)) {
            ExecutorService executorService = this.metadataExecutor;
            if (executorService == null || executorService.isShutdown() || this.metadataExecutor.isTerminated()) {
                this.pendingDirectoryCountPaths.remove(absolutePath);
                return;
            }
            try {
                this.metadataExecutor.execute(new w2(this, file, absolutePath, 9));
            } catch (RejectedExecutionException unused) {
                this.pendingDirectoryCountPaths.remove(absolutePath);
            }
        }
    }

    private int resolveConflict(File file) {
        return this.globalConflictAction != -1 ? this.globalConflictAction : promptForConflict(file.getName());
    }

    private void scanCategoryFiles(String... strArr) {
        this.isSearchMode = true;
        this.isAppMode = false;
        this.isCloudDiskMode = false;
        invalidateOptionsMenu();
        this.progressScan.setVisibility(0);
        this.recyclerView.setVisibility(8);
        this.textEmpty.setVisibility(8);
        this.breadcrumbContainer.removeAllViews();
        TextView textView = new TextView(this);
        textView.setText("内部存储");
        textView.setPadding(16, 0, 16, 0);
        textView.setMinHeight(48);
        textView.setGravity(16);
        textView.setTextSize(16.0f);
        textView.setTextColor(getBreadcrumbColor());
        textView.setOnClickListener(new a4(this, 23));
        this.breadcrumbContainer.addView(textView);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(g4.o.ic_right_arrow_small);
        imageView.setColorFilter(getBreadcrumbColor());
        TextView textView2 = new TextView(this);
        textView2.setText(">");
        textView2.setTextColor(getBreadcrumbColor());
        this.breadcrumbContainer.addView(textView2);
        TextView textView3 = new TextView(this);
        textView3.setText("分类搜索");
        textView3.setPadding(16, 0, 16, 0);
        textView3.setMinHeight(48);
        textView3.setGravity(16);
        textView3.setTextSize(14.0f);
        textView3.setTextColor(getBreadcrumbColor());
        textView3.setTypeface(null, 1);
        this.breadcrumbContainer.addView(textView3);
        this.mLastScanExtensions = strArr;
        boolean z6 = false;
        boolean z7 = false;
        for (String str : strArr) {
            if (str.contains(".apk")) {
                z6 = true;
            }
            if (str.contains(".zip") || str.contains(".rar") || str.contains(".7z")) {
                z7 = true;
            }
        }
        if (z6 || z7) {
            this.currentCategoryView = z6 ? "apk" : "zip";
        } else {
            this.currentCategoryView = null;
        }
        this.executor.execute(new z0(17, this, strArr));
    }

    private void scanDirRecursive(File file, List<File> list, String[] strArr) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                scanDirRecursive(file2, list, strArr);
            } else {
                String lowerCase = file2.getName().toLowerCase();
                int length = strArr.length;
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        break;
                    }
                    if (lowerCase.endsWith(strArr[i7])) {
                        list.add(file2);
                        break;
                    }
                    i7++;
                }
            }
        }
    }

    private void scanInstalledApps() {
        this.isAppMode = true;
        this.isSearchMode = false;
        invalidateOptionsMenu();
        this.progressScan.setVisibility(0);
        this.recyclerView.setVisibility(8);
        this.textEmpty.setVisibility(8);
        this.breadcrumbContainer.removeAllViews();
        TextView textView = new TextView(this);
        textView.setText("内部存储");
        textView.setPadding(16, 0, 16, 0);
        textView.setMinHeight(48);
        textView.setGravity(16);
        textView.setTextSize(14.0f);
        textView.setTextColor(getBreadcrumbColor());
        textView.setOnClickListener(new a4(this, 22));
        this.breadcrumbContainer.addView(textView);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(g4.o.ic_right_arrow_small);
        imageView.setColorFilter(getBreadcrumbColor());
        this.breadcrumbContainer.addView(imageView);
        TextView textView2 = new TextView(this);
        textView2.setText("已安装应用");
        textView2.setPadding(16, 0, 16, 0);
        textView2.setMinHeight(48);
        textView2.setGravity(16);
        textView2.setTextSize(14.0f);
        textView2.setTextColor(getBreadcrumbColor());
        textView2.setTypeface(null, 1);
        this.breadcrumbContainer.addView(textView2);
        this.executor.execute(new c4(this, 3));
    }

    private void scanRecursiveForCache(File file) {
    }

    private void searchDirRecursive(File file, List<File> list, String str) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                searchDirRecursive(file2, list, str);
            } else if (file2.getName().toLowerCase().contains(str)) {
                list.add(file2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchFiles(String str) {
        this.isSearchMode = true;
        this.isAppMode = false;
        invalidateOptionsMenu();
        this.progressScan.setVisibility(0);
        this.recyclerView.setVisibility(8);
        this.textEmpty.setVisibility(8);
        this.breadcrumbContainer.removeAllViews();
        TextView textView = new TextView(this);
        textView.setText("所有存储");
        textView.setPadding(16, 0, 16, 0);
        textView.setMinHeight(48);
        textView.setGravity(16);
        textView.setTextSize(14.0f);
        textView.setTextColor(getBreadcrumbColor());
        textView.setOnClickListener(new a4(this, 2));
        this.breadcrumbContainer.addView(textView);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(g4.o.ic_right_arrow_small);
        imageView.setColorFilter(getBreadcrumbColor());
        TextView textView2 = new TextView(this);
        textView2.setText(">");
        textView2.setTextColor(getBreadcrumbColor());
        this.breadcrumbContainer.addView(textView2);
        TextView textView3 = new TextView(this);
        textView3.setText("搜索结果: " + str);
        textView3.setPadding(16, 0, 16, 0);
        textView3.setMinHeight(48);
        textView3.setGravity(16);
        textView3.setTextSize(14.0f);
        textView3.setTextColor(getBreadcrumbColor());
        textView3.setTypeface(null, 1);
        this.breadcrumbContainer.addView(textView3);
        this.executor.execute(new z3(this, str, 1));
    }

    private void setNetworkNeighborVisible(boolean z6) {
        this.isNetworkNeighborVisible = z6;
        MenuItem menuItem = this.actionNetworkNeighbor;
        if (menuItem != null) {
            menuItem.setVisible(z6);
        }
    }

    private void setupActions() {
        final int i7 = 0;
        this.layoutSelectionActions.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.extra.c3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i7) {
                    case 0:
                        FileManagerActivity.lambda$setupActions$31(view);
                        break;
                    default:
                        FileManagerActivity.lambda$setupActions$32(view);
                        break;
                }
            }
        });
        final int i8 = 1;
        this.layoutPasteActions.setOnClickListener(new View.OnClickListener() { // from class: com.navi.tool.ui.extra.c3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i8) {
                    case 0:
                        FileManagerActivity.lambda$setupActions$31(view);
                        break;
                    default:
                        FileManagerActivity.lambda$setupActions$32(view);
                        break;
                }
            }
        });
        this.actionCancel.setOnClickListener(new a4(this, 5));
        this.actionSelectAll.setOnClickListener(new a4(this, 6));
        this.actionDelete.setOnClickListener(new a4(this, 7));
        this.actionCopy.setOnClickListener(new a4(this, 8));
        this.actionCut.setOnClickListener(new a4(this, 9));
        this.actionRename.setOnClickListener(new a4(this, 10));
        this.actionCompress.setOnClickListener(new a4(this, 11));
        this.actionInfo.setOnClickListener(new a4(this, 12));
        this.btnCancelPaste.setOnClickListener(new a4(this, 3));
        this.btnPaste.setOnClickListener(new a4(this, 4));
    }

    private void setupCategories() {
        this.layoutCatApps.setOnClickListener(new a4(this, 13));
        this.layoutCatTransfer.setOnClickListener(new a4(this, 14));
        this.layoutCatApk.setOnClickListener(new a4(this, 15));
        this.layoutCatZip.setOnClickListener(new a4(this, 16));
        this.layoutCatImage.setOnClickListener(new a4(this, 17));
        this.layoutCatVideo.setOnClickListener(new a4(this, 18));
        this.layoutCatMusic.setOnClickListener(new a4(this, 19));
    }

    private void setupHttpServer() {
        this.switchHttpServer.setOnCheckedChangeListener(new o3(this, 1));
    }

    private void setupStorageInfo() {
        View findViewById = findViewById(g4.p.layout_storage_info);
        if (findViewById != null) {
            findViewById.setOnClickListener(new a4(this, 0));
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong() * blockSizeLong;
        long availableBlocksLong = blockCountLong - (statFs.getAvailableBlocksLong() * blockSizeLong);
        this.progressStorage.setProgress((int) ((100 * availableBlocksLong) / blockCountLong));
        this.textStorageInfo.setText(String.format("已用 %s / 共 %s", formatSize(availableBlocksLong), formatSize(blockCountLong)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAppActionDialog(PackageInfo packageInfo) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t(packageInfo.applicationInfo.loadLabel(getPackageManager()));
        nVar.m("版本: " + packageInfo.versionName + "\n包名: " + packageInfo.packageName);
        nVar.r("卸载", new c1(6, this, packageInfo));
        nVar.o("取消", null);
        nVar.v();
    }

    private void showCloudBackupDialog() {
        dismissCloudBackupDialog();
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("正在备份配置");
        nVar.u(LayoutInflater.from(this).inflate(g4.q.dialog_progress, (ViewGroup) null));
        nVar.k();
        androidx.appcompat.app.o e7 = nVar.e();
        this.cloudBackupDialog = e7;
        e7.show();
        this.cloudBackupProgressBar = (ProgressBar) this.cloudBackupDialog.findViewById(g4.p.progress_bar);
        this.cloudBackupProgressText = (TextView) this.cloudBackupDialog.findViewById(g4.p.progress_text);
        this.cloudBackupPercentText = (TextView) this.cloudBackupDialog.findViewById(g4.p.progress_percent);
        TextView textView = (TextView) this.cloudBackupDialog.findViewById(g4.p.progress_count);
        if (textView != null) {
            textView.setVisibility(8);
        }
        Button button = (Button) this.cloudBackupDialog.findViewById(g4.p.btn_cancel);
        if (button != null) {
            button.setOnClickListener(new a4(this, 24));
        }
        ProgressBar progressBar = this.cloudBackupProgressBar;
        if (progressBar != null) {
            progressBar.setIndeterminate(true);
        }
        TextView textView2 = this.cloudBackupProgressText;
        if (textView2 != null) {
            textView2.setText("正在准备备份...");
        }
        TextView textView3 = this.cloudBackupPercentText;
        if (textView3 != null) {
            textView3.setText("...");
        }
    }

    private void showCloudDiskLoginDialog() {
        QrCodeLoginDialog qrCodeLoginDialog = new QrCodeLoginDialog(this);
        qrCodeLoginDialog.setOnLoginSuccessListener(new y3(this));
        qrCodeLoginDialog.setOnLoginFailedListener(new y3(this));
        qrCodeLoginDialog.show();
    }

    private void showCreateDialog(boolean z6) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t(z6 ? "新建文件夹" : "新建文件");
        EditText editText = new EditText(this);
        editText.setSingleLine(true);
        editText.setHint("请输入名称");
        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        int i7 = (int) (getResources().getDisplayMetrics().density * 24.0f);
        layoutParams.leftMargin = i7;
        layoutParams.rightMargin = i7;
        editText.setLayoutParams(layoutParams);
        frameLayout.addView(editText);
        nVar.u(frameLayout);
        nVar.r("确定", new t(this, editText, z6, 1));
        nVar.o("取消", null);
        androidx.appcompat.app.o e7 = nVar.e();
        e7.setOnShowListener(new s3(this, editText, 0));
        e7.show();
    }

    private void showCreateOptions() {
        if (this.isCloudDiskMode) {
            showCreateDialog(true);
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("新建");
        nVar.l(new String[]{"新建文件夹", "新建文件"}, new i3(this, 2));
        nVar.v();
    }

    private void showDeleteSelectionDialog() {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("删除文件");
        nVar.m("确定要删除选中的 " + this.selectedFiles.size() + " 个项目吗？");
        nVar.r("删除", new i3(this, 3));
        nVar.o("取消", null);
        nVar.v();
    }

    private void showMultiFileProperties() {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("属性 (" + this.selectedFiles.size() + "项)");
        nVar.m("正在计算...");
        nVar.r("确定", null);
        this.executor.execute(new b3(this, nVar.v(), 1));
    }

    private void showPasteLayout() {
        String str;
        this.layoutPasteActions.setVisibility(0);
        TextView textView = this.textPasteInfo;
        if (this.isCutOperation) {
            str = "已剪切 " + this.clipboardFiles.size() + " 项";
        } else {
            str = "已复制 " + this.clipboardFiles.size() + " 项";
        }
        textView.setText(str);
    }

    private void showPropertiesDialog() {
        if (this.selectedFiles.isEmpty()) {
            return;
        }
        if (this.selectedFiles.size() == 1) {
            showSingleFileProperties(this.selectedFiles.get(0));
        } else {
            showMultiFileProperties();
        }
    }

    private void showQrCodeDialog(String str) {
        try {
            y3.b a7 = new p3.f(20, 0).a(str, w3.a.QR_CODE, 300, 300, null);
            Bitmap createBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.RGB_565);
            for (int i7 = 0; i7 < 300; i7++) {
                for (int i8 = 0; i8 < 300; i8++) {
                    createBitmap.setPixel(i7, i8, a7.a(i7, i8) ? -16777216 : -1);
                }
            }
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            linearLayout.setGravity(17);
            linearLayout.setPadding(16, 16, 16, 4);
            TextView textView = new TextView(this);
            textView.setText("使用手机自带相机扫描二维码\n直接访问http服务上传或下载文件");
            textView.setTextSize(14.0f);
            textView.setTextColor(p0.d1.MEASURED_STATE_MASK);
            textView.setGravity(17);
            textView.setLineSpacing(CropImageView.DEFAULT_ASPECT_RATIO, 1.2f);
            linearLayout.addView(textView);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(createBitmap);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 12, 0, 4);
            linearLayout.addView(imageView, layoutParams);
            androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
            nVar.u(linearLayout);
            nVar.r("关闭", new v3());
            androidx.appcompat.app.o e7 = nVar.e();
            this.qrDialog = e7;
            e7.show();
            Window window = this.qrDialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(-1));
                window.setLayout((int) Math.min(getResources().getDisplayMetrics().widthPixels * 0.6f, getResources().getDisplayMetrics().density * 450.0f), -2);
            }
        } catch (Exception e8) {
            e8.printStackTrace();
            ToastHelper.show(this, "生成二维码失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showReceiveProgressDialog(String str) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        LayoutInflater.from(this).inflate(g4.q.dialog_loading, (ViewGroup) null);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(50, 40, 50, 40);
        TextView textView = new TextView(this);
        this.transferProgressText = textView;
        textView.setText("正在接收: " + str);
        linearLayout.addView(this.transferProgressText);
        ProgressBar progressBar = new ProgressBar(this, null, R.attr.progressBarStyleHorizontal);
        this.transferProgressBar = progressBar;
        progressBar.setMax(100);
        linearLayout.addView(this.transferProgressBar);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("接收文件");
        nVar.u(linearLayout);
        nVar.k();
        nVar.o("后台运行", null);
        nVar.r("完成", new i3(this, 0));
        androidx.appcompat.app.o e7 = nVar.e();
        this.transferProgressDialog = e7;
        e7.show();
    }

    private void showRenameDialog(File file) {
        EditText editText = new EditText(this);
        editText.setText(file.getName());
        editText.setSelectAllOnFocus(true);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(50, 0, 50, 0);
        linearLayout.addView(editText, layoutParams);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("重命名");
        nVar.u(linearLayout);
        nVar.r("确定", new b(this, editText, file, 2));
        nVar.o("取消", null);
        nVar.v();
    }

    private void showSendDialog() {
        View inflate = LayoutInflater.from(this).inflate(g4.q.dialog_transfer_send, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(g4.p.input_text);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.u(inflate);
        nVar.r("关闭", null);
        androidx.appcompat.app.o e7 = nVar.e();
        inflate.findViewById(g4.p.btn_send_text).setOnClickListener(new d1(6, this, editText));
        inflate.findViewById(g4.p.btn_select_file).setOnClickListener(new d1(7, this, e7));
        e7.show();
    }

    private void showSingleFileProperties(File file) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("属性");
        nVar.m("正在计算...");
        nVar.r("确定", null);
        this.executor.execute(new w2(this, file, nVar.v(), 14));
    }

    private void showUnzipDialog(File file) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t(file.getName());
        nVar.l(new String[]{"查看文件", "解压文件"}, new z2(this, file, 1));
        nVar.v();
    }

    private void showUnzipOptionsDialog(File file) {
        File file2 = this.currentDir;
        this.customUnzipTargetDir = file2;
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(50, 30, 50, 0);
        TextView textView = new TextView(this);
        textView.setText("解压位置:");
        linearLayout.addView(textView);
        TextView textView2 = new TextView(this);
        textView2.setText(file2.getAbsolutePath());
        textView2.setPadding(0, 20, 0, 20);
        textView2.setTextSize(14.0f);
        textView2.setTypeface(null, 1);
        linearLayout.addView(textView2);
        Button button = new Button(this);
        button.setText("更改位置");
        linearLayout.addView(button);
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("解压选项");
        nVar.u(linearLayout);
        nVar.r("开始解压", new z2(this, file, 0));
        nVar.o("取消", null);
        androidx.appcompat.app.o e7 = nVar.e();
        button.setOnClickListener(new k4(this, file, e7, 3));
        e7.show();
    }

    private void sortFileItems(List<FileItem> list) {
        Collections.sort(list, new m3(this, 2));
    }

    private void sortFileItemsByName(List<FileItem> list) {
        Collections.sort(list, new m3(this, 1));
    }

    private void sortFiles(List<File> list) {
        Collections.sort(list, new m3(this, 0));
    }

    private void startBackgroundScan() {
    }

    private void startConfigBackup() {
        if (ConfigBackupService.running) {
            ToastHelper.show(this, "备份正在进行");
            return;
        }
        if (!this.cloudBackupAvailable || this.cloudBackupCredentialIndex < 0) {
            ToastHelper.show(this, "123网盘未登录或登录已失效");
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("备份配置");
        nVar.m("将把 navitool 下的配置目录打包成 ZIP，并上传到 123 网盘根目录的“车机助手配置备份”文件夹。\n\n打包和上传过程都可以在通知栏取消，是否继续？");
        nVar.r("开始备份", new i3(this, 1));
        nVar.o("取消", null);
        nVar.v();
    }

    private void startDeleteSelected() {
        if (this.isDeletingSelected) {
            ToastHelper.show(this, "正在删除，请稍候");
            return;
        }
        ArrayList arrayList = new ArrayList(this.selectedFiles);
        if (arrayList.isEmpty()) {
            return;
        }
        this.isDeletingSelected = true;
        exitSelectionMode();
        ToastHelper.show(this, "正在删除 " + arrayList.size() + " 个项目…");
        this.executor.execute(new j3(2, this, arrayList));
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a3, code lost:
    
        r1 = r4.getHostAddress();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void startHttpServer() {
        /*
            Method dump skipped, instructions count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.ui.extra.FileManagerActivity.startHttpServer():void");
    }

    private void stopHttpServer() {
        q4.a aVar = this.httpServer;
        if (aVar != null) {
            try {
                aVar.g();
            } catch (Exception unused) {
            }
            this.httpServer = null;
        }
    }

    private void streamNextBatch(int i7, File file, List<FileItem> list, int i8) {
        this.recyclerView.post(new o4.f(this, i7, file, i8, list, 2));
    }

    private void switchLayout(boolean z6) {
        this.recyclerView.Z(this.listDivider);
        this.recyclerView.Z(this.gridDivider);
        if (z6) {
            this.recyclerView.setLayoutManager(new GridLayoutManager(4));
            this.recyclerView.g(this.gridDivider);
        } else {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(1));
            this.recyclerView.g(this.listDivider);
        }
        if (this.isAppMode) {
            this.recyclerView.setAdapter(this.appAdapter);
        } else {
            this.recyclerView.setAdapter(this.adapter);
        }
    }

    private void toggleSelectAll() {
        File file;
        if (this.adapter.items.isEmpty()) {
            return;
        }
        int i7 = 0;
        for (FileItem fileItem : this.adapter.items) {
            if (!fileItem.isZipEntry && fileItem.file != null) {
                i7++;
            }
        }
        if (i7 == 0) {
            return;
        }
        if (this.selectedFiles.size() == i7) {
            exitSelectionMode();
            return;
        }
        this.selectedFiles.clear();
        for (FileItem fileItem2 : this.adapter.items) {
            if (!fileItem2.isZipEntry && (file = fileItem2.file) != null) {
                this.selectedFiles.add(file);
            }
        }
        FileAdapter fileAdapter = this.adapter;
        fileAdapter.notifyItemRangeChanged(0, fileAdapter.getItemCount(), "selection");
        updateSelectionTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleSelection(int i7) {
        FileItem fileItem;
        File file;
        if (i7 < 0 || i7 >= this.adapter.getItemCount() || (fileItem = this.adapter.getItems().get(i7)) == null || (file = fileItem.file) == null) {
            return;
        }
        if (this.selectedFiles.contains(file)) {
            this.selectedFiles.remove(file);
            if (this.selectedFiles.isEmpty()) {
                exitSelectionMode();
                return;
            }
        } else {
            this.selectedFiles.add(file);
        }
        this.adapter.notifyItemChanged(i7, "selection");
        updateSelectionTitle();
    }

    private String transferOwner() {
        return "file_manager_" + System.identityHashCode(this);
    }

    private void unregisterCloudBackupReceiver() {
        if (this.cloudBackupReceiverRegistered) {
            try {
                unregisterReceiver(this.cloudBackupReceiver);
            } catch (Exception unused) {
            }
            this.cloudBackupReceiverRegistered = false;
        }
    }

    private void updateBreadcrumbs(File file) {
        this.breadcrumbContainer.removeAllViews();
        int i7 = 1;
        if (!this.isZipMode) {
            ArrayList arrayList = new ArrayList();
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            while (file != null) {
                arrayList.add(0, file);
                if (file.equals(externalStorageDirectory)) {
                    break;
                } else {
                    file = file.getParentFile();
                }
            }
            for (int i8 = 0; i8 < arrayList.size(); i8++) {
                File file2 = (File) arrayList.get(i8);
                TextView textView = new TextView(this);
                textView.setText(getFriendlyName(file2));
                textView.setPadding(16, 0, 16, 0);
                textView.setMinHeight(48);
                textView.setGravity(16);
                textView.setTextSize(14.0f);
                if (i8 == arrayList.size() - 1) {
                    textView.setTextColor(getBreadcrumbColor());
                    textView.setTypeface(null, 1);
                } else {
                    textView.setTextColor(getBreadcrumbColor());
                    textView.setOnClickListener(new q3(this, file2, i7));
                }
                this.breadcrumbContainer.addView(textView);
                if (i8 < arrayList.size() - 1) {
                    addBreadcrumbSeparator();
                }
            }
            return;
        }
        TextView textView2 = new TextView(this);
        textView2.setText("返回上级");
        textView2.setPadding(16, 0, 16, 0);
        textView2.setMinHeight(48);
        textView2.setGravity(16);
        textView2.setTextSize(14.0f);
        textView2.setTextColor(getBreadcrumbColor());
        textView2.setOnClickListener(new a4(this, 20));
        this.breadcrumbContainer.addView(textView2);
        addBreadcrumbSeparator();
        TextView textView3 = new TextView(this);
        textView3.setText(this.currentZipFile.getName());
        textView3.setPadding(16, 0, 16, 0);
        textView3.setMinHeight(48);
        textView3.setGravity(16);
        textView3.setTextSize(14.0f);
        if (this.currentZipPath.isEmpty()) {
            int i9 = g4.m.primary;
            Object obj = g0.g.f6423a;
            textView3.setTextColor(g0.d.a(this, i9));
            textView3.setTypeface(null, 1);
        } else {
            textView3.setTextColor(getBreadcrumbColor());
            textView3.setOnClickListener(new a4(this, 21));
        }
        this.breadcrumbContainer.addView(textView3);
        if (this.currentZipPath.isEmpty()) {
            return;
        }
        addBreadcrumbSeparator();
        String[] split = this.currentZipPath.split("/");
        String str = s5.h0.FRAGMENT_ENCODE_SET;
        int i10 = 0;
        while (i10 < split.length) {
            str = str.isEmpty() ? split[i10] : str + "/" + split[i10];
            boolean z6 = i10 == split.length - 1;
            TextView textView4 = new TextView(this);
            textView4.setText(split[i10]);
            textView4.setPadding(16, 0, 16, 0);
            textView4.setMinHeight(48);
            textView4.setGravity(16);
            textView4.setTextSize(14.0f);
            if (z6) {
                int i11 = g4.m.primary;
                Object obj2 = g0.g.f6423a;
                textView4.setTextColor(g0.d.a(this, i11));
                textView4.setTypeface(null, 1);
            } else {
                textView4.setTextColor(getBreadcrumbColor());
                textView4.setOnClickListener(new h3(this, str, i7));
            }
            this.breadcrumbContainer.addView(textView4);
            if (!z6) {
                addBreadcrumbSeparator();
            }
            i10++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCloudBackupProgress(String str, int i7, String str2) {
        String str3;
        androidx.appcompat.app.o oVar = this.cloudBackupDialog;
        if (oVar == null || !oVar.isShowing()) {
            return;
        }
        TextView textView = this.cloudBackupProgressText;
        if (textView != null) {
            if (str2 == null) {
                str2 = s5.h0.FRAGMENT_ENCODE_SET;
            }
            textView.setText(str2);
        }
        boolean equals = ConfigBackupService.STAGE_SCANNING.equals(str);
        ProgressBar progressBar = this.cloudBackupProgressBar;
        if (progressBar != null) {
            progressBar.setIndeterminate(equals);
            if (!equals) {
                this.cloudBackupProgressBar.setProgress(i7);
            }
        }
        TextView textView2 = this.cloudBackupPercentText;
        if (textView2 != null) {
            if (equals) {
                str3 = "...";
            } else {
                str3 = i7 + "%";
            }
            textView2.setText(str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCloudBreadcrumbs() {
        this.breadcrumbContainer.removeAllViews();
        int i7 = 0;
        addCloudBreadcrumbItem("123网盘", "0", 0);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = this.cloudPathStack.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        Iterator<String> it2 = this.cloudNameStack.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next());
        }
        while (i7 < arrayList.size()) {
            String str = i7 < arrayList.size() + (-1) ? (String) arrayList.get(i7 + 1) : this.currentCloudParentId;
            String str2 = (String) arrayList2.get(i7);
            i7++;
            addCloudBreadcrumbItem(str2, str, i7);
        }
    }

    private void updateSelectionTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().t("已选择 " + this.selectedFiles.size() + " 项");
        }
        View view = this.actionSelectAll;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            int i7 = 0;
            for (FileItem fileItem : this.adapter.items) {
                if (!fileItem.isZipEntry && fileItem.file != null) {
                    i7++;
                }
            }
            textView.setText((i7 <= 0 || this.selectedFiles.size() != i7) ? "全选" : "取消全选");
        }
    }

    private void updateSortOrder(boolean z6) {
        if (this.isSortAscending == z6) {
            return;
        }
        this.isSortAscending = z6;
        getSharedPreferences("settings", 0).edit().putBoolean("file_manager_sort_asc", this.isSortAscending).apply();
        invalidateOptionsMenu();
        updateSortType(this.currentSortType);
    }

    private void updateSortType(int i7) {
        this.currentSortType = i7;
        getSharedPreferences("settings", 0).edit().putInt("file_manager_sort_type", this.currentSortType).apply();
        invalidateOptionsMenu();
        if (this.isSearchMode || this.isZipMode) {
            List<FileItem> items = this.adapter.getItems();
            if (items != null) {
                sortFileItems(items);
                this.adapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        if (this.isAppMode || this.currentDir == null) {
            return;
        }
        List<FileItem> items2 = this.adapter.getItems();
        if (items2 == null || items2.isEmpty()) {
            loadFiles(this.currentDir);
        } else {
            sortFileItems(items2);
            this.adapter.notifyDataSetChanged();
        }
    }

    private void uploadToCloudDisk() {
        if (this.currentCloudCredential == null) {
            ToastHelper.show(this, "云盘信息丢失，请重新登录");
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("正在上传到123云盘...");
        nVar.u(LayoutInflater.from(this).inflate(g4.q.dialog_progress, (ViewGroup) null));
        nVar.k();
        final androidx.appcompat.app.o e7 = nVar.e();
        e7.show();
        final ProgressBar progressBar = (ProgressBar) e7.findViewById(g4.p.progress_bar);
        final TextView textView = (TextView) e7.findViewById(g4.p.progress_text);
        final TextView textView2 = (TextView) e7.findViewById(g4.p.progress_count);
        final TextView textView3 = (TextView) e7.findViewById(g4.p.progress_percent);
        Button button = (Button) e7.findViewById(g4.p.btn_cancel);
        final ArrayList arrayList = new ArrayList();
        for (File file : this.clipboardFiles) {
            if (file.isFile()) {
                arrayList.add(file);
            }
        }
        if (arrayList.isEmpty()) {
            e7.dismiss();
            ToastHelper.show(this, "剪贴板中没有文件，不支持上传文件夹");
            return;
        }
        final boolean z6 = this.isCutOperation;
        final int size = arrayList.size();
        final int[] iArr = {0};
        final boolean[] zArr = {false};
        if (button != null) {
            button.setOnClickListener(new k3(this, zArr, e7));
        }
        Iterator it = arrayList.iterator();
        final long j7 = 0;
        while (it.hasNext()) {
            File file2 = (File) it.next();
            if (file2.isFile()) {
                j7 += file2.length();
            }
        }
        if (textView != null) {
            textView.setText("正在上传...");
        }
        if (textView2 != null) {
            textView2.setText("0 / " + size);
        }
        this.downloadExecutor.execute(new Runnable() { // from class: com.navi.tool.ui.extra.l3
            @Override // java.lang.Runnable
            public final void run() {
                FileManagerActivity.this.lambda$uploadToCloudDisk$98(arrayList, zArr, textView, textView2, size, textView3, j7, progressBar, iArr, z6, e7);
            }
        });
    }

    private void viewImage(File file) {
        Intent intent = new Intent(this, (Class<?>) ImageViewerActivity.class);
        ArrayList<String> arrayList = new ArrayList<>();
        FileAdapter fileAdapter = this.adapter;
        int i7 = 0;
        int i8 = 0;
        for (FileItem fileItem : fileAdapter != null ? fileAdapter.getItems() : new ArrayList<>()) {
            if (fileItem != null && !fileItem.isDirectory && !fileItem.isZipEntry && fileItem.file != null && isImageFileName(fileItem.name)) {
                String absolutePath = fileItem.file.getAbsolutePath();
                if (absolutePath.equals(file.getAbsolutePath())) {
                    i8 = arrayList.size();
                }
                arrayList.add(absolutePath);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(file.getAbsolutePath());
        } else {
            i7 = i8;
        }
        intent.putStringArrayListExtra(ImageViewerActivity.EXTRA_IMAGE_PATHS, arrayList);
        intent.putExtra(ImageViewerActivity.EXTRA_START_INDEX, i7);
        try {
            startActivity(intent);
        } catch (Exception unused) {
            ToastHelper.show(this, "无法打开图片");
        }
    }

    private void zipFileRecursive(File file, String str, ZipOutputStream zipOutputStream, byte[] bArr) {
        if (!file.exists()) {
            return;
        }
        String absolutePath = file.getAbsolutePath();
        String substring = absolutePath.equals(str) ? s5.h0.FRAGMENT_ENCODE_SET : absolutePath.startsWith(str) ? absolutePath.substring(str.length() + 1) : file.getName();
        if (file.isDirectory()) {
            if (!substring.isEmpty()) {
                if (!substring.endsWith("/")) {
                    substring = substring.concat("/");
                }
                zipOutputStream.putNextEntry(new ZipEntry(substring));
                zipOutputStream.closeEntry();
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    zipFileRecursive(file2, str, zipOutputStream, bArr);
                }
                return;
            }
            return;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), bArr.length);
        zipOutputStream.putNextEntry(new ZipEntry(substring));
        while (true) {
            int read = bufferedInputStream.read(bArr, 0, bArr.length);
            if (read == -1) {
                bufferedInputStream.close();
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, read);
        }
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, android.app.Activity
    public void onActivityResult(int i7, int i8, Intent intent) {
        Uri data;
        super.onActivityResult(i7, i8, intent);
        if (i7 == REQUEST_CODE_PICK_FOLDER && i8 == -1 && intent != null) {
            Uri data2 = intent.getData();
            if (data2 == null || data2.getPath() == null) {
                return;
            }
            File file = new File(data2.getPath());
            this.currentDir = file;
            if (this.pendingUnzipFile != null) {
                loadFiles(file);
                showUnzipOptionsDialog(this.pendingUnzipFile);
                return;
            }
            return;
        }
        if (i7 != 2001 || i8 != -1 || intent == null || (data = intent.getData()) == null) {
            return;
        }
        String pathFromUri = getPathFromUri(data);
        if (pathFromUri == null) {
            copyAndSend(data);
            return;
        }
        q4.h hVar = this.transferManager;
        File file2 = new File(pathFromUri);
        String str = hVar.f8482r;
        if (str != null) {
            hVar.f8467c.execute(new q4.c(hVar, str, file2, 0));
        }
        ToastHelper.show(this, "开始发送文件...");
    }

    @Override // androidx.activity.o, android.app.Activity
    public void onBackPressed() {
        File parentFile;
        File parentFile2;
        if (this.isCloudDiskMode) {
            if (this.cloudPathStack.isEmpty()) {
                exitCloudDisk();
                return;
            }
            String pop = this.cloudPathStack.pop();
            this.cloudNameStack.pop();
            this.currentCloudParentId = pop;
            loadCloudFiles(pop);
            updateCloudBreadcrumbs();
            return;
        }
        if (this.isPickFolderMode || this.isPickFileMode) {
            File file = this.currentDir;
            if (file == null || file.equals(Environment.getExternalStorageDirectory()) || (parentFile = this.currentDir.getParentFile()) == null || !parentFile.canRead()) {
                super.onBackPressed();
                return;
            } else {
                loadFiles(parentFile);
                return;
            }
        }
        if (this.isSelectionMode) {
            exitSelectionMode();
            return;
        }
        if (this.isZipMode) {
            if (this.currentZipPath.isEmpty()) {
                exitZipMode();
                return;
            }
            int lastIndexOf = this.currentZipPath.lastIndexOf(47);
            if (lastIndexOf > 0) {
                loadZipFolder(this.currentZipPath.substring(0, lastIndexOf));
                return;
            } else {
                loadZipFolder(s5.h0.FRAGMENT_ENCODE_SET);
                return;
            }
        }
        if (this.isAppMode) {
            this.isAppMode = false;
            invalidateOptionsMenu();
            loadFiles(Environment.getExternalStorageDirectory());
            return;
        }
        if (this.isSearchMode) {
            this.isSearchMode = false;
            File file2 = this.currentDir;
            if (file2 == null || !file2.exists()) {
                loadFiles(Environment.getExternalStorageDirectory());
                return;
            } else {
                loadFiles(this.currentDir);
                return;
            }
        }
        File file3 = this.currentDir;
        if (file3 == null || file3.equals(Environment.getExternalStorageDirectory()) || (parentFile2 = this.currentDir.getParentFile()) == null || !parentFile2.canRead()) {
            super.onBackPressed();
        } else {
            loadFiles(parentFile2);
        }
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, androidx.activity.o, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        recreate();
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        setContentView(g4.q.activity_file_manager);
        this.thumbnailCache = new LruCache<String, Bitmap>(((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8) { // from class: com.navi.tool.ui.extra.FileManagerActivity.3
            @Override // android.util.LruCache
            public int sizeOf(String str, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
        this.apkIconCache = new LruCache<>(50);
        Toolbar toolbar = (Toolbar) findViewById(g4.p.toolbar);
        toolbar.setTitle("文件助手");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().n(true);
        }
        if (ACTION_PICK_FOLDER.equals(getIntent().getAction())) {
            this.isPickFolderMode = true;
            if (getSupportActionBar() != null) {
                getSupportActionBar().t("选择解压位置");
            }
        } else if (ACTION_PICK_FILE.equals(getIntent().getAction())) {
            this.isPickFileMode = true;
            this.isMultiSelectAllowed = getIntent().getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false);
            if (getSupportActionBar() != null) {
                getSupportActionBar().t(this.isMultiSelectAllowed ? "选择文件 (可多选)" : "选择文件");
            }
        }
        initViews();
        this.listDivider = new androidx.recyclerview.widget.y(this);
        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(4, 16, true);
        this.gridDivider = gridSpacingItemDecoration;
        this.recyclerView.g(gridSpacingItemDecoration);
        setupStorageInfo();
        refreshCloudDisks();
        setupCategories();
        setupActions();
        setupHttpServer();
        FileAdapter fileAdapter = new FileAdapter();
        this.adapter = fileAdapter;
        this.recyclerView.setAdapter(fileAdapter);
        SharedPreferences sharedPreferences = getSharedPreferences("settings", 0);
        this.isGridMode = sharedPreferences.getBoolean("file_manager_grid_mode", true);
        this.currentSortType = sharedPreferences.getInt("file_manager_sort_type", 0);
        this.isSortAscending = sharedPreferences.getBoolean("file_manager_sort_asc", true);
        switchLayout(this.isGridMode);
        this.currentDir = Environment.getExternalStorageDirectory();
        this.audioPlayerHelper = new AudioPlayerHelper(this);
        loadFiles(this.currentDir);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.isPickFolderMode) {
            menu.add(0, 100, 0, "确定").setShowAsAction(2);
            return true;
        }
        if (this.isPickFileMode) {
            return true;
        }
        getMenuInflater().inflate(g4.r.file_manager_menu, menu);
        this.actionNetworkNeighbor = menu.findItem(g4.p.action_network_neighbor);
        setNetworkNeighborVisible(this.isNetworkNeighborVisible);
        menu.findItem(g4.p.action_layout_toggle);
        final SearchView searchView = (SearchView) menu.findItem(g4.p.action_search).getActionView();
        searchView.setQueryHint("搜索文件...");
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.b3() { // from class: com.navi.tool.ui.extra.FileManagerActivity.5
            @Override // androidx.appcompat.widget.b3
            public boolean onQueryTextChange(String str) {
                return false;
            }

            @Override // androidx.appcompat.widget.b3
            public boolean onQueryTextSubmit(String str) {
                if (str.isEmpty()) {
                    return true;
                }
                FileManagerActivity.this.searchFiles(str);
                searchView.clearFocus();
                return true;
            }
        });
        return true;
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        dismissCloudBackupDialog();
        unregisterCloudBackupReceiver();
        this.networkNeighborHandler.removeCallbacks(this.hideNetworkNeighborRunnable);
        AudioPlayerHelper audioPlayerHelper = this.audioPlayerHelper;
        if (audioPlayerHelper != null) {
            audioPlayerHelper.stopAudio();
        }
        q4.h hVar = this.transferManager;
        if (hVar != null && !this.isPickFolderMode && !this.isPickFileMode) {
            hVar.m(transferOwner());
        }
        stopHttpServer();
        this.executor.shutdown();
        this.directoryLoadExecutor.shutdown();
        this.metadataExecutor.shutdown();
        this.imageExecutor.shutdown();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 100) {
            Intent intent = new Intent();
            intent.setData(Uri.fromFile(this.currentDir));
            setResult(-1, intent);
            finish();
            return true;
        }
        if (menuItem.getItemId() == g4.p.action_network_neighbor) {
            showSendDialog();
            return true;
        }
        if (menuItem.getItemId() == g4.p.action_backup_config) {
            startConfigBackup();
            return true;
        }
        if (menuItem.getItemId() == g4.p.action_123pan) {
            showCloudDiskLoginDialog();
            return true;
        }
        if (menuItem.getItemId() == 16908332) {
            if (this.isSelectionMode) {
                exitSelectionMode();
                return true;
            }
            onBackPressed();
            return true;
        }
        if (menuItem.getItemId() == g4.p.action_create) {
            if (this.isZipMode || this.isAppMode || this.isSearchMode) {
                ToastHelper.show(this, "当前模式下不支持创建文件");
                return true;
            }
            showCreateOptions();
            return true;
        }
        if (menuItem.getItemId() == g4.p.sort_by_name) {
            updateSortType(0);
            return true;
        }
        if (menuItem.getItemId() == g4.p.sort_by_size) {
            updateSortType(1);
            return true;
        }
        if (menuItem.getItemId() == g4.p.sort_by_date) {
            updateSortType(2);
            return true;
        }
        if (menuItem.getItemId() == g4.p.sort_asc) {
            updateSortOrder(true);
            return true;
        }
        if (menuItem.getItemId() == g4.p.sort_desc) {
            updateSortOrder(false);
            return true;
        }
        if (menuItem.getItemId() != g4.p.action_layout_toggle) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.isGridMode = !this.isGridMode;
        getSharedPreferences("settings", 0).edit().putBoolean("file_manager_grid_mode", this.isGridMode).apply();
        switchLayout(this.isGridMode);
        invalidateOptionsMenu();
        return true;
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onPause() {
        dismissCloudBackupDialog();
        unregisterCloudBackupReceiver();
        super.onPause();
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        this.actionNetworkNeighbor = menu.findItem(g4.p.action_network_neighbor);
        setNetworkNeighborVisible(this.isNetworkNeighborVisible);
        if (menu.findItem(g4.p.action_sort) != null) {
            int i7 = this.currentSortType;
            if (i7 == 0) {
                menu.findItem(g4.p.sort_by_name).setChecked(true);
            } else if (i7 == 1) {
                menu.findItem(g4.p.sort_by_size).setChecked(true);
            } else if (i7 == 2) {
                menu.findItem(g4.p.sort_by_date).setChecked(true);
            }
            if (this.isSortAscending) {
                menu.findItem(g4.p.sort_asc).setChecked(true);
            } else {
                menu.findItem(g4.p.sort_desc).setChecked(true);
            }
        }
        MenuItem findItem = menu.findItem(g4.p.action_backup_config);
        if (findItem != null) {
            findItem.setVisible(this.cloudBackupAvailable && !ConfigBackupService.running);
        }
        MenuItem findItem2 = menu.findItem(g4.p.action_123pan);
        if (findItem2 != null) {
            findItem2.setVisible(true ^ this.cloudBackupAvailable);
        }
        MenuItem findItem3 = menu.findItem(g4.p.action_layout_toggle);
        if (findItem3 != null) {
            if (this.isGridMode) {
                findItem3.setIcon(g4.o.ic_menu);
                findItem3.setTitle("列表视图");
            } else {
                findItem3.setIcon(g4.o.ic_grid_view);
                findItem3.setTitle("网格视图");
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onResume() {
        super.onResume();
        registerCloudBackupReceiver();
        refreshStorageVolumes();
        refreshCloudDisks();
        refreshCloudBackupAvailability();
        if (this.isAppMode) {
            scanInstalledApps();
        }
    }
}
