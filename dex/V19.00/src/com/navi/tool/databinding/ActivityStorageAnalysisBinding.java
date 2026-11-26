package com.navi.tool.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import g4.p;
import g4.q;
import n1.z;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class ActivityStorageAnalysisBinding {
    public static ActivityStorageAnalysisBinding bind(View view) {
        int i7 = p.action_cancel;
        if (((TextView) z.c0(i7, view)) != null) {
            i7 = p.action_delete;
            if (((TextView) z.c0(i7, view)) != null) {
                i7 = p.action_select_all;
                if (((TextView) z.c0(i7, view)) != null) {
                    i7 = p.btn_filter;
                    if (((ImageView) z.c0(i7, view)) != null) {
                        i7 = p.btn_scan;
                        if (((Button) z.c0(i7, view)) != null) {
                            i7 = p.layout_batch_actions;
                            if (((LinearLayout) z.c0(i7, view)) != null) {
                                i7 = p.progress_scanning;
                                if (((ProgressBar) z.c0(i7, view)) != null) {
                                    i7 = p.progress_total;
                                    if (((ProgressBar) z.c0(i7, view)) != null) {
                                        i7 = p.recycler_large_files;
                                        if (((RecyclerView) z.c0(i7, view)) != null) {
                                            i7 = p.text_empty_large_files;
                                            if (((TextView) z.c0(i7, view)) != null) {
                                                i7 = p.text_free;
                                                if (((TextView) z.c0(i7, view)) != null) {
                                                    i7 = p.text_large_file_title;
                                                    if (((TextView) z.c0(i7, view)) != null) {
                                                        i7 = p.text_used;
                                                        if (((TextView) z.c0(i7, view)) != null) {
                                                            i7 = p.toolbar;
                                                            if (((Toolbar) z.c0(i7, view)) != null) {
                                                                return new ActivityStorageAnalysisBinding();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i7)));
    }

    public static ActivityStorageAnalysisBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityStorageAnalysisBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z6) {
        View inflate = layoutInflater.inflate(q.activity_storage_analysis, viewGroup, false);
        if (z6) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
}
