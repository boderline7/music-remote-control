package com.navi.tool.ui.extra;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.navi.tool.ui.extra.StorageAnalysisActivity;
import com.navi.tool.utils.ToastHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class StorageAnalysisActivity extends androidx.appcompat.app.r {
    private View actionCancel;
    private View actionDelete;
    private View actionSelectAll;
    private LargeFileAdapter adapter;
    private View btnFilter;
    private Button btnScan;
    private View layoutBatchActions;
    private ProgressBar progressScanning;
    private ProgressBar progressTotal;
    private RecyclerView recyclerLargeFiles;
    private TextView textEmptyLargeFiles;
    private TextView textFree;
    private TextView textLargeFileTitle;
    private TextView textUsed;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private List<FileItem> largeFiles = new ArrayList();
    private boolean isSelectionMode = false;
    private List<FileItem> selectedItems = new ArrayList();
    private long largeFileThreshold = 104857600;
    private String largeFileThresholdLabel = "100MB";

    public static class FileItem {
        File file;
        long size;

        public FileItem(File file) {
            this.file = file;
            this.size = file.length();
        }
    }

    public class LargeFileAdapter extends androidx.recyclerview.widget.h1 {
        private Context context;
        private List<FileItem> list;

        public class ViewHolder extends androidx.recyclerview.widget.j2 {
            CheckBox checkbox;
            ImageView delete;
            ImageView icon;
            TextView subtitle;
            TextView title;

            public ViewHolder(View view) {
                super(view);
                this.icon = (ImageView) view.findViewById(R.id.icon);
                this.title = (TextView) view.findViewById(R.id.text1);
                this.subtitle = (TextView) view.findViewById(R.id.text2);
                this.delete = (ImageView) view.findViewById(R.id.button1);
                this.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            }
        }

        public LargeFileAdapter(Context context, List<FileItem> list) {
            this.context = context;
            this.list = list;
        }

        private View createItemView(Context context) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(0);
            int dp2px = dp2px(16.0f);
            int dp2px2 = dp2px(8.0f);
            linearLayout.setPadding(dp2px, dp2px2, dp2px, dp2px2);
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
            linearLayout.setBackgroundResource(typedValue.resourceId);
            linearLayout.setLayoutParams(new androidx.recyclerview.widget.s1(-1, -2));
            linearLayout.setGravity(16);
            View checkBox = new CheckBox(context);
            checkBox.setId(R.id.checkbox);
            checkBox.setVisibility(8);
            linearLayout.addView(checkBox);
            ImageView imageView = new ImageView(context);
            imageView.setId(R.id.icon);
            imageView.setImageResource(g4.o.ic_file_default);
            int dp2px3 = dp2px(40.0f);
            linearLayout.addView(imageView, new LinearLayout.LayoutParams(dp2px3, dp2px3));
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(1);
            linearLayout2.setPadding(dp2px(16.0f), 0, 0, 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
            layoutParams.weight = 1.0f;
            linearLayout.addView(linearLayout2, layoutParams);
            TextView textView = new TextView(context);
            textView.setId(R.id.text1);
            textView.setTextSize(14.0f);
            TypedValue typedValue2 = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.textColorPrimary, typedValue2, true);
            textView.setTextColor(typedValue2.data);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            linearLayout2.addView(textView);
            TextView textView2 = new TextView(context);
            textView2.setId(R.id.text2);
            textView2.setTextSize(12.0f);
            TypedValue typedValue3 = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.textColorSecondary, typedValue3, true);
            textView2.setTextColor(typedValue3.data);
            linearLayout2.addView(textView2);
            ImageView imageView2 = new ImageView(context);
            imageView2.setId(R.id.button1);
            imageView2.setImageResource(R.drawable.ic_menu_delete);
            imageView2.setColorFilter(-7829368);
            int dp2px4 = dp2px(8.0f);
            imageView2.setPadding(dp2px4, dp2px4, dp2px4, dp2px4);
            linearLayout.addView(imageView2);
            return linearLayout;
        }

        private int dp2px(float f7) {
            return (int) TypedValue.applyDimension(1, f7, this.context.getResources().getDisplayMetrics());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$0(FileItem fileItem, View view) {
            StorageAnalysisActivity.this.toggleSelection(fileItem);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$1(FileItem fileItem, View view) {
            StorageAnalysisActivity.this.toggleSelection(fileItem);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$2(FileItem fileItem, ViewHolder viewHolder, View view) {
            StorageAnalysisActivity.this.deleteFile(fileItem, viewHolder.getAdapterPosition());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onBindViewHolder$3(FileItem fileItem, View view) {
            ToastHelper.show(this.context, fileItem.file.getAbsolutePath());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$onBindViewHolder$4(FileItem fileItem, View view) {
            StorageAnalysisActivity.this.enterSelectionMode(fileItem);
            return true;
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return this.list.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(ViewHolder viewHolder, int i7) {
            final FileItem fileItem = this.list.get(i7);
            viewHolder.title.setText(fileItem.file.getName());
            viewHolder.subtitle.setText(Formatter.formatFileSize(this.context, fileItem.size) + "  " + fileItem.file.getParent());
            final int i8 = 1;
            final int i9 = 0;
            if (StorageAnalysisActivity.this.isSelectionMode) {
                viewHolder.checkbox.setVisibility(0);
                viewHolder.checkbox.setChecked(StorageAnalysisActivity.this.selectedItems.contains(fileItem));
                viewHolder.delete.setVisibility(8);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.w5

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ StorageAnalysisActivity.LargeFileAdapter f5547b;

                    {
                        this.f5547b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i10 = i9;
                        StorageAnalysisActivity.FileItem fileItem2 = fileItem;
                        StorageAnalysisActivity.LargeFileAdapter largeFileAdapter = this.f5547b;
                        switch (i10) {
                            case 0:
                                largeFileAdapter.lambda$onBindViewHolder$0(fileItem2, view);
                                break;
                            case 1:
                                largeFileAdapter.lambda$onBindViewHolder$1(fileItem2, view);
                                break;
                            default:
                                largeFileAdapter.lambda$onBindViewHolder$3(fileItem2, view);
                                break;
                        }
                    }
                });
                viewHolder.checkbox.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.w5

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ StorageAnalysisActivity.LargeFileAdapter f5547b;

                    {
                        this.f5547b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i10 = i8;
                        StorageAnalysisActivity.FileItem fileItem2 = fileItem;
                        StorageAnalysisActivity.LargeFileAdapter largeFileAdapter = this.f5547b;
                        switch (i10) {
                            case 0:
                                largeFileAdapter.lambda$onBindViewHolder$0(fileItem2, view);
                                break;
                            case 1:
                                largeFileAdapter.lambda$onBindViewHolder$1(fileItem2, view);
                                break;
                            default:
                                largeFileAdapter.lambda$onBindViewHolder$3(fileItem2, view);
                                break;
                        }
                    }
                });
            } else {
                viewHolder.checkbox.setVisibility(8);
                viewHolder.delete.setVisibility(0);
                viewHolder.delete.setOnClickListener(new k4(this, fileItem, viewHolder, i8));
                final int i10 = 2;
                viewHolder.itemView.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.w5

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ StorageAnalysisActivity.LargeFileAdapter f5547b;

                    {
                        this.f5547b = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        int i102 = i10;
                        StorageAnalysisActivity.FileItem fileItem2 = fileItem;
                        StorageAnalysisActivity.LargeFileAdapter largeFileAdapter = this.f5547b;
                        switch (i102) {
                            case 0:
                                largeFileAdapter.lambda$onBindViewHolder$0(fileItem2, view);
                                break;
                            case 1:
                                largeFileAdapter.lambda$onBindViewHolder$1(fileItem2, view);
                                break;
                            default:
                                largeFileAdapter.lambda$onBindViewHolder$3(fileItem2, view);
                                break;
                        }
                    }
                });
                viewHolder.itemView.setOnLongClickListener(new e1(this, fileItem, i8));
            }
            String lowerCase = fileItem.file.getName().toLowerCase();
            int i11 = g4.o.ic_file_default;
            if (lowerCase.endsWith(".apk")) {
                i11 = g4.o.ic_file_apk;
            } else if (lowerCase.endsWith(".zip") || lowerCase.endsWith(".rar")) {
                i11 = g4.o.ic_file_zip;
            } else if (lowerCase.endsWith(".mp3") || lowerCase.endsWith(".wav")) {
                i11 = g4.o.ic_file_music;
            } else if (lowerCase.endsWith(".mp4") || lowerCase.endsWith(".avi")) {
                i11 = g4.o.ic_file_video;
            } else if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".png")) {
                i11 = g4.o.ic_file_image;
            }
            try {
                viewHolder.icon.setImageResource(i11);
            } catch (Exception unused) {
                viewHolder.icon.setImageResource(g4.o.ic_file_default);
            }
        }

        @Override // androidx.recyclerview.widget.h1
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return new ViewHolder(createItemView(this.context));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteFile(FileItem fileItem, int i7) {
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("确认删除");
        nVar.m("确定要删除文件 " + fileItem.file.getName() + " 吗？");
        nVar.r("删除", new u5(this, fileItem, i7));
        nVar.o("取消", null);
        nVar.v();
    }

    private void deleteSelectedFiles() {
        if (this.selectedItems.isEmpty()) {
            return;
        }
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("确认删除");
        nVar.m("确定要删除选中的 " + this.selectedItems.size() + " 个文件吗？此操作不可恢复。");
        nVar.r("删除", new z4(1, this));
        nVar.o("取消", null);
        nVar.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterSelectionMode(FileItem fileItem) {
        this.isSelectionMode = true;
        this.selectedItems.clear();
        if (fileItem != null) {
            this.selectedItems.add(fileItem);
        }
        this.layoutBatchActions.setVisibility(0);
        this.btnScan.setVisibility(8);
        this.adapter.notifyDataSetChanged();
        updateActionTitle();
    }

    private void exitSelectionMode() {
        this.isSelectionMode = false;
        this.selectedItems.clear();
        this.layoutBatchActions.setVisibility(8);
        this.btnScan.setVisibility(0);
        this.adapter.notifyDataSetChanged();
        getSupportActionBar().t("存储空间分析");
    }

    private void initView() {
        setSupportActionBar((Toolbar) findViewById(g4.p.toolbar));
        final int i7 = 1;
        getSupportActionBar().n(true);
        getSupportActionBar().o();
        this.progressTotal = (ProgressBar) findViewById(g4.p.progress_total);
        this.textUsed = (TextView) findViewById(g4.p.text_used);
        this.textFree = (TextView) findViewById(g4.p.text_free);
        this.textLargeFileTitle = (TextView) findViewById(g4.p.text_large_file_title);
        this.btnFilter = findViewById(g4.p.btn_filter);
        this.progressScanning = (ProgressBar) findViewById(g4.p.progress_scanning);
        this.textEmptyLargeFiles = (TextView) findViewById(g4.p.text_empty_large_files);
        this.recyclerLargeFiles = (RecyclerView) findViewById(g4.p.recycler_large_files);
        this.btnScan = (Button) findViewById(g4.p.btn_scan);
        this.layoutBatchActions = findViewById(g4.p.layout_batch_actions);
        this.actionCancel = findViewById(g4.p.action_cancel);
        this.actionSelectAll = findViewById(g4.p.action_select_all);
        this.actionDelete = findViewById(g4.p.action_delete);
        this.recyclerLargeFiles.setLayoutManager(new LinearLayoutManager(1));
        LargeFileAdapter largeFileAdapter = new LargeFileAdapter(this, this.largeFiles);
        this.adapter = largeFileAdapter;
        this.recyclerLargeFiles.setAdapter(largeFileAdapter);
        final int i8 = 0;
        this.btnScan.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.t5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ StorageAnalysisActivity f5478b;

            {
                this.f5478b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i9 = i8;
                StorageAnalysisActivity storageAnalysisActivity = this.f5478b;
                switch (i9) {
                    case 0:
                        storageAnalysisActivity.lambda$initView$0(view);
                        break;
                    case 1:
                        storageAnalysisActivity.lambda$initView$1(view);
                        break;
                    case 2:
                        storageAnalysisActivity.lambda$initView$2(view);
                        break;
                    case 3:
                        storageAnalysisActivity.lambda$initView$3(view);
                        break;
                    default:
                        storageAnalysisActivity.lambda$initView$4(view);
                        break;
                }
            }
        });
        this.btnFilter.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.t5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ StorageAnalysisActivity f5478b;

            {
                this.f5478b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i9 = i7;
                StorageAnalysisActivity storageAnalysisActivity = this.f5478b;
                switch (i9) {
                    case 0:
                        storageAnalysisActivity.lambda$initView$0(view);
                        break;
                    case 1:
                        storageAnalysisActivity.lambda$initView$1(view);
                        break;
                    case 2:
                        storageAnalysisActivity.lambda$initView$2(view);
                        break;
                    case 3:
                        storageAnalysisActivity.lambda$initView$3(view);
                        break;
                    default:
                        storageAnalysisActivity.lambda$initView$4(view);
                        break;
                }
            }
        });
        final int i9 = 2;
        this.actionCancel.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.t5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ StorageAnalysisActivity f5478b;

            {
                this.f5478b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i92 = i9;
                StorageAnalysisActivity storageAnalysisActivity = this.f5478b;
                switch (i92) {
                    case 0:
                        storageAnalysisActivity.lambda$initView$0(view);
                        break;
                    case 1:
                        storageAnalysisActivity.lambda$initView$1(view);
                        break;
                    case 2:
                        storageAnalysisActivity.lambda$initView$2(view);
                        break;
                    case 3:
                        storageAnalysisActivity.lambda$initView$3(view);
                        break;
                    default:
                        storageAnalysisActivity.lambda$initView$4(view);
                        break;
                }
            }
        });
        final int i10 = 3;
        this.actionSelectAll.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.t5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ StorageAnalysisActivity f5478b;

            {
                this.f5478b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i92 = i10;
                StorageAnalysisActivity storageAnalysisActivity = this.f5478b;
                switch (i92) {
                    case 0:
                        storageAnalysisActivity.lambda$initView$0(view);
                        break;
                    case 1:
                        storageAnalysisActivity.lambda$initView$1(view);
                        break;
                    case 2:
                        storageAnalysisActivity.lambda$initView$2(view);
                        break;
                    case 3:
                        storageAnalysisActivity.lambda$initView$3(view);
                        break;
                    default:
                        storageAnalysisActivity.lambda$initView$4(view);
                        break;
                }
            }
        });
        final int i11 = 4;
        this.actionDelete.setOnClickListener(new View.OnClickListener(this) { // from class: com.navi.tool.ui.extra.t5

            /* renamed from: b, reason: collision with root package name */
            public final /* synthetic */ StorageAnalysisActivity f5478b;

            {
                this.f5478b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i92 = i11;
                StorageAnalysisActivity storageAnalysisActivity = this.f5478b;
                switch (i92) {
                    case 0:
                        storageAnalysisActivity.lambda$initView$0(view);
                        break;
                    case 1:
                        storageAnalysisActivity.lambda$initView$1(view);
                        break;
                    case 2:
                        storageAnalysisActivity.lambda$initView$2(view);
                        break;
                    case 3:
                        storageAnalysisActivity.lambda$initView$3(view);
                        break;
                    default:
                        storageAnalysisActivity.lambda$initView$4(view);
                        break;
                }
            }
        });
        updateThresholdText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteFile$10(FileItem fileItem, int i7, DialogInterface dialogInterface, int i8) {
        if (!fileItem.file.delete()) {
            ToastHelper.show(this, "删除失败");
            return;
        }
        this.largeFiles.remove(i7);
        this.adapter.notifyItemRemoved(i7);
        this.adapter.notifyItemRangeChanged(i7, this.largeFiles.size());
        refreshStorageInfo();
        ToastHelper.show(this, "已删除");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteSelectedFiles$5(DialogInterface dialogInterface, int i7) {
        Iterator it = new ArrayList(this.selectedItems).iterator();
        int i8 = 0;
        while (it.hasNext()) {
            FileItem fileItem = (FileItem) it.next();
            if (fileItem.file.delete()) {
                this.largeFiles.remove(fileItem);
                i8++;
            }
        }
        ToastHelper.show(this, "成功删除 " + i8 + " 个文件");
        exitSelectionMode();
        this.adapter.notifyDataSetChanged();
        refreshStorageInfo();
        if (this.largeFiles.isEmpty()) {
            this.textEmptyLargeFiles.setVisibility(0);
            this.recyclerLargeFiles.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(View view) {
        startScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$1(View view) {
        showFilterDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$2(View view) {
        exitSelectionMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$3(View view) {
        toggleSelectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$4(View view) {
        deleteSelectedFiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showFilterDialog$6(long[] jArr, String[] strArr, DialogInterface dialogInterface, int i7) {
        this.largeFileThreshold = jArr[i7];
        this.largeFileThresholdLabel = strArr[i7];
        updateThresholdText();
        startScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$startScan$7(FileItem fileItem, FileItem fileItem2) {
        return Long.compare(fileItem2.size, fileItem.size);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startScan$8() {
        this.progressScanning.setVisibility(8);
        this.btnScan.setEnabled(true);
        if (this.largeFiles.isEmpty()) {
            this.textEmptyLargeFiles.setVisibility(0);
            this.recyclerLargeFiles.setVisibility(8);
        } else {
            this.textEmptyLargeFiles.setVisibility(8);
            this.recyclerLargeFiles.setVisibility(0);
            this.adapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startScan$9() {
        scanDirectory(Environment.getExternalStorageDirectory());
        Collections.sort(this.largeFiles, new x(3));
        runOnUiThread(new v5(this, 1));
    }

    private void refreshStorageInfo() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            long blockSizeLong = statFs.getBlockSizeLong();
            long blockCountLong = statFs.getBlockCountLong() * blockSizeLong;
            long availableBlocksLong = statFs.getAvailableBlocksLong() * blockSizeLong;
            long j7 = blockCountLong - availableBlocksLong;
            this.progressTotal.setProgress((int) ((100 * j7) / blockCountLong));
            this.textUsed.setText("已用: " + Formatter.formatFileSize(this, j7));
            this.textFree.setText("剩余: " + Formatter.formatFileSize(this, availableBlocksLong));
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    private void scanDirectory(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                if (!file2.getName().startsWith(".")) {
                    scanDirectory(file2);
                }
            } else if (file2.length() > this.largeFileThreshold) {
                this.largeFiles.add(new FileItem(file2));
            }
        }
    }

    private void showFilterDialog() {
        String[] strArr = {"10MB", "50MB", "100MB", "500MB", "1GB"};
        androidx.appcompat.app.n nVar = new androidx.appcompat.app.n(this);
        nVar.t("选择大文件阈值");
        nVar.l(strArr, new b(this, new long[]{10485760, 52428800, 104857600, 524288000, 1073741824}, strArr, 3));
        nVar.v();
    }

    private void startScan() {
        this.btnScan.setEnabled(false);
        this.progressScanning.setVisibility(0);
        this.recyclerLargeFiles.setVisibility(8);
        this.textEmptyLargeFiles.setVisibility(8);
        this.largeFiles.clear();
        this.adapter.notifyDataSetChanged();
        this.executor.execute(new v5(this, 0));
    }

    private void toggleSelectAll() {
        if (this.selectedItems.size() == this.largeFiles.size()) {
            this.selectedItems.clear();
        } else {
            this.selectedItems.clear();
            this.selectedItems.addAll(this.largeFiles);
        }
        this.adapter.notifyDataSetChanged();
        updateActionTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleSelection(FileItem fileItem) {
        if (this.selectedItems.contains(fileItem)) {
            this.selectedItems.remove(fileItem);
        } else {
            this.selectedItems.add(fileItem);
        }
        this.adapter.notifyDataSetChanged();
        updateActionTitle();
        if (this.selectedItems.isEmpty()) {
            exitSelectionMode();
        }
    }

    private void updateActionTitle() {
        getSupportActionBar().t("已选择 " + this.selectedItems.size() + " 项");
    }

    private void updateThresholdText() {
        this.textLargeFileTitle.setText("大文件清理 (>" + this.largeFileThresholdLabel + ")");
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(5894);
        setContentView(g4.q.activity_storage_analysis);
        initView();
        refreshStorageInfo();
        startScan();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
