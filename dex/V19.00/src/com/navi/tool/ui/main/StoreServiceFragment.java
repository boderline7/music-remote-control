package com.navi.tool.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.p0;
import androidx.fragment.app.z;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.navi.tool.model.StoreConfig;
import com.navi.tool.ui.main.StoreConfigAdapter;
import com.navi.tool.utils.NativeBridge;
import com.navi.tool.utils.SharedPreferencesUtils;
import com.navi.tool.utils.StoreDownloadManager;
import com.navi.tool.utils.ToastHelper;
import g4.p;
import g4.q;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import s5.f;
import s5.g;
import s5.h0;
import s5.m0;
import s5.o0;
import s5.r0;
import s5.x0;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public class StoreServiceFragment extends z {
    private StoreConfigAdapter adapter;
    private o0 client;
    private List<StoreConfig> configList = new ArrayList();
    private Handler mainHandler;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    /* renamed from: com.navi.tool.ui.main.StoreServiceFragment$1, reason: invalid class name */
    public class AnonymousClass1 implements StoreConfigAdapter.OnConfigActionListener {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onDownload$0() {
            StoreServiceFragment.this.adapter.notifyDataSetChanged();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onDownloadAndExtract$1() {
            StoreServiceFragment.this.adapter.notifyDataSetChanged();
        }

        @Override // com.navi.tool.ui.main.StoreConfigAdapter.OnConfigActionListener
        public void onDownload(StoreConfig storeConfig) {
            StoreDownloadManager.handleAction(StoreServiceFragment.this.getContext(), storeConfig, 1, new c(this, 0));
        }

        @Override // com.navi.tool.ui.main.StoreConfigAdapter.OnConfigActionListener
        public void onDownloadAndExtract(StoreConfig storeConfig) {
            StoreDownloadManager.handleAction(StoreServiceFragment.this.getContext(), storeConfig, 3, new c(this, 1));
        }

        @Override // com.navi.tool.ui.main.StoreConfigAdapter.OnConfigActionListener
        public void onExtract(StoreConfig storeConfig) {
            StoreDownloadManager.handleAction(StoreServiceFragment.this.getContext(), storeConfig, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchJsonData(String str) {
        r0 r0Var = new r0();
        r0Var.f(str);
        this.client.a(r0Var.a()).e(new g() { // from class: com.navi.tool.ui.main.StoreServiceFragment.3
            @Override // s5.g
            public void onFailure(f fVar, IOException iOException) {
                StoreServiceFragment storeServiceFragment = StoreServiceFragment.this;
                storeServiceFragment.handleError(storeServiceFragment.networkErrorText(iOException, "获取商店数据失败"));
            }

            @Override // s5.g
            public void onResponse(f fVar, x0 x0Var) {
                if (x0Var.B()) {
                    StoreServiceFragment.this.parseAndDisplayJson(x0Var.f8919g.A());
                } else {
                    StoreServiceFragment.this.handleError("获取商店数据失败，状态码: " + x0Var.f8916d);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchStoreConfigs() {
        String storeUrl = NativeBridge.getStoreUrl();
        if (TextUtils.isEmpty(storeUrl)) {
            handleError("商城地址不可用");
            return;
        }
        Context context = getContext();
        String licenseUid = context == null ? null : SharedPreferencesUtils.getLicenseUid(context);
        String licenseToken = context != null ? SharedPreferencesUtils.getLicenseToken(context) : null;
        if (storeUrl == null) {
            storeUrl = h0.FRAGMENT_ENCODE_SET;
        }
        StringBuilder sb = new StringBuilder(storeUrl);
        char c7 = '&';
        char c8 = sb.indexOf("?") >= 0 ? '&' : '?';
        if (TextUtils.isEmpty(licenseUid)) {
            c7 = c8;
        } else {
            sb.append(c8);
            sb.append("uid=");
            sb.append(Uri.encode(licenseUid));
        }
        if (!TextUtils.isEmpty(licenseToken)) {
            sb.append(c7);
            sb.append("tk=");
            sb.append(Uri.encode(licenseToken));
        }
        r0 r0Var = new r0();
        r0Var.f(sb.toString());
        this.client.a(r0Var.a()).e(new g() { // from class: com.navi.tool.ui.main.StoreServiceFragment.2
            @Override // s5.g
            public void onFailure(f fVar, IOException iOException) {
                StoreServiceFragment storeServiceFragment = StoreServiceFragment.this;
                storeServiceFragment.handleError(storeServiceFragment.networkErrorText(iOException, "获取商店配置地址失败"));
            }

            @Override // s5.g
            public void onResponse(f fVar, x0 x0Var) {
                if (!x0Var.B()) {
                    StoreServiceFragment.this.handleError("获取商店配置地址失败，状态码: " + x0Var.f8916d);
                    return;
                }
                String trim = x0Var.f8919g.A().trim();
                if (trim.isEmpty() || trim.equals("false")) {
                    StoreServiceFragment.this.handleError("商城未授权（需 VIP），或后台尚未配置商店地址");
                } else {
                    StoreServiceFragment.this.fetchJsonData(trim);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleError(String str) {
        this.mainHandler.post(new p0(23, this, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleError$1(String str) {
        this.swipeRefreshLayout.setRefreshing(false);
        if (getContext() != null) {
            ToastHelper.showLong(getContext(), str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$parseAndDisplayJson$0(List list) {
        this.configList.clear();
        this.configList.addAll(list);
        this.adapter.notifyDataSetChanged();
        this.swipeRefreshLayout.setRefreshing(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String networkErrorText(IOException iOException, String str) {
        if ((iOException instanceof UnknownHostException) || (iOException instanceof ConnectException) || (iOException instanceof SocketTimeoutException)) {
            return "当前网络异常，商店暂不可用，请检查网络";
        }
        return str + ": " + iOException.getMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseAndDisplayJson(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList();
            for (int i7 = 0; i7 < jSONArray.length(); i7++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i7);
                String optString = jSONObject.optString("name", h0.FRAGMENT_ENCODE_SET);
                String optString2 = jSONObject.optString("time", h0.FRAGMENT_ENCODE_SET);
                String optString3 = jSONObject.optString("description", h0.FRAGMENT_ENCODE_SET);
                String optString4 = jSONObject.optString("download_url", h0.FRAGMENT_ENCODE_SET);
                ArrayList arrayList2 = new ArrayList();
                JSONArray optJSONArray = jSONObject.optJSONArray("tags");
                if (optJSONArray != null) {
                    for (int i8 = 0; i8 < optJSONArray.length(); i8++) {
                        arrayList2.add(optJSONArray.getString(i8));
                    }
                }
                arrayList.add(new StoreConfig(optString, optString2, optString3, arrayList2, optString4));
            }
            this.mainHandler.post(new p0(22, this, arrayList));
        } catch (Exception e7) {
            handleError("解析商店数据失败: " + e7.getMessage());
        }
    }

    private void setupRecyclerView() {
        getContext();
        this.recyclerView.setLayoutManager(new GridLayoutManager(3));
        StoreConfigAdapter storeConfigAdapter = new StoreConfigAdapter(getContext(), this.configList, new AnonymousClass1());
        this.adapter = storeConfigAdapter;
        this.recyclerView.setAdapter(storeConfigAdapter);
    }

    @Override // androidx.lifecycle.k
    public e1.c getDefaultViewModelCreationExtras() {
        return e1.a.INSTANCE;
    }

    @Override // androidx.fragment.app.z
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(q.fragment_store_service, viewGroup, false);
        if ((NativeBridge.getCachedLicenseMask(requireContext()) & 2048) == 0) {
            inflate.setVisibility(8);
            return inflate;
        }
        this.swipeRefreshLayout = (SwipeRefreshLayout) inflate.findViewById(p.swipe_refresh);
        this.recyclerView = (RecyclerView) inflate.findViewById(p.recycler_view);
        this.mainHandler = new Handler(Looper.getMainLooper());
        m0 m0Var = new m0();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        m0Var.a(10L, timeUnit);
        m0Var.b(10L, timeUnit);
        this.client = new o0(m0Var);
        setupRecyclerView();
        this.swipeRefreshLayout.setOnRefreshListener(new b(this));
        this.swipeRefreshLayout.setRefreshing(true);
        fetchStoreConfigs();
        return inflate;
    }
}
