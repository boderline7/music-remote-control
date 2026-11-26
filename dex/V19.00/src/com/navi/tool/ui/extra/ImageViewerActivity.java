package com.navi.tool.ui.extra;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.navi.tool.view.ZoomableImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class ImageViewerActivity extends androidx.appcompat.app.r {
    public static final String EXTRA_IMAGE_PATHS = "image_paths";
    public static final String EXTRA_START_INDEX = "start_index";
    private LruCache<String, Bitmap> bitmapCache;
    private final ExecutorService decodeExecutor = Executors.newFixedThreadPool(2);
    private List<String> imagePaths = new ArrayList();
    private TextView textCounter;
    private ViewPager2 viewPager;

    public class ImagePagerAdapter extends androidx.recyclerview.widget.h1 {

        public class ImageHolder extends androidx.recyclerview.widget.j2 {
            private String boundPath;
            private final ZoomableImageView imageView;
            private final TextView textHint;

            public ImageHolder(View view) {
                super(view);
                this.imageView = (ZoomableImageView) view.findViewById(g4.p.image_viewer_content);
                this.textHint = (TextView) view.findViewById(g4.p.text_image_error);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void lambda$bind$0(String str, Bitmap bitmap) {
                if (str.equals(this.boundPath)) {
                    if (bitmap == null) {
                        this.imageView.setImageDrawable(null);
                        this.textHint.setVisibility(0);
                        return;
                    }
                    ImageViewerActivity.this.bitmapCache.put(str, bitmap);
                    this.imageView.setImageBitmap(bitmap);
                    ZoomableImageView zoomableImageView = this.imageView;
                    zoomableImageView.f6097l = zoomableImageView.f6098m;
                    zoomableImageView.c();
                    this.textHint.setVisibility(8);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$bind$1(String str) {
                ImageViewerActivity.this.runOnUiThread(new w2(this, str, ImageViewerActivity.this.decodeBitmap(str), 3));
            }

            public void bind(String str) {
                this.boundPath = str;
                Bitmap bitmap = (Bitmap) ImageViewerActivity.this.bitmapCache.get(str);
                if (bitmap == null) {
                    this.imageView.setImageDrawable(null);
                    this.textHint.setVisibility(8);
                    ImageViewerActivity.this.decodeExecutor.execute(new z0(3, (Object) this, (Object) str));
                } else {
                    this.imageView.setImageBitmap(bitmap);
                    ZoomableImageView zoomableImageView = this.imageView;
                    zoomableImageView.f6097l = zoomableImageView.f6098m;
                    zoomableImageView.c();
                    this.textHint.setVisibility(8);
                }
            }
        }

        private ImagePagerAdapter() {
        }

        @Override // androidx.recyclerview.widget.h1
        public int getItemCount() {
            return ImageViewerActivity.this.imagePaths.size();
        }

        @Override // androidx.recyclerview.widget.h1
        public void onBindViewHolder(ImageHolder imageHolder, int i7) {
            imageHolder.bind((String) ImageViewerActivity.this.imagePaths.get(i7));
        }

        @Override // androidx.recyclerview.widget.h1
        public ImageHolder onCreateViewHolder(ViewGroup viewGroup, int i7) {
            return new ImageHolder(LayoutInflater.from(viewGroup.getContext()).inflate(g4.q.item_image_viewer_page, viewGroup, false));
        }
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int i7, int i8) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap decodeBitmap(String str) {
        try {
            if (!new File(str).exists()) {
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inSampleSize = calculateInSampleSize(options, Math.max(getResources().getDisplayMetrics().widthPixels, 1080), Math.max(getResources().getDisplayMetrics().heightPixels, 720));
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception unused) {
            return null;
        }
    }

    private void hideSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCounter(int i7) {
        this.textCounter.setText((i7 + 1) + " / " + this.imagePaths.size());
    }

    @Override // androidx.fragment.app.d0, androidx.activity.o, e0.n, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(g4.q.activity_image_viewer);
        hideSystemUi();
        this.bitmapCache = new LruCache<String, Bitmap>(((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 12) { // from class: com.navi.tool.ui.extra.ImageViewerActivity.1
            @Override // android.util.LruCache
            public int sizeOf(String str, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra(EXTRA_IMAGE_PATHS);
        if (stringArrayListExtra != null) {
            this.imagePaths = stringArrayListExtra;
        }
        if (this.imagePaths.isEmpty()) {
            finish();
            return;
        }
        int i7 = 0;
        int intExtra = getIntent().getIntExtra(EXTRA_START_INDEX, 0);
        if (intExtra < 0) {
            intExtra = 0;
        }
        if (intExtra >= this.imagePaths.size()) {
            intExtra = this.imagePaths.size() - 1;
        }
        this.viewPager = (ViewPager2) findViewById(g4.p.view_pager_images);
        this.textCounter = (TextView) findViewById(g4.p.text_image_counter);
        ((ImageButton) findViewById(g4.p.btn_close_image_viewer)).setOnClickListener(new r4(this, i7));
        this.viewPager.setAdapter(new ImagePagerAdapter());
        this.viewPager.setCurrentItem(intExtra, false);
        updateCounter(intExtra);
        ((List) this.viewPager.f2870c.f2859b).add(new androidx.viewpager2.widget.j() { // from class: com.navi.tool.ui.extra.ImageViewerActivity.2
            @Override // androidx.viewpager2.widget.j
            public void onPageSelected(int i8) {
                ImageViewerActivity.this.updateCounter(i8);
            }
        });
    }

    @Override // androidx.appcompat.app.r, androidx.fragment.app.d0, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.decodeExecutor.shutdown();
        LruCache<String, Bitmap> lruCache = this.bitmapCache;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }

    @Override // androidx.fragment.app.d0, android.app.Activity
    public void onResume() {
        super.onResume();
        hideSystemUi();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z6) {
        super.onWindowFocusChanged(z6);
        if (z6) {
            hideSystemUi();
        }
    }
}
