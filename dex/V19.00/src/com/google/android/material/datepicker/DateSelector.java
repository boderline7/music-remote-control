package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import java.util.ArrayList;
import p0.m2;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public interface DateSelector<S> extends Parcelable {
    static void j(EditText... editTextArr) {
        if (editTextArr.length == 0) {
            return;
        }
        i iVar = new i(0, editTextArr);
        for (EditText editText : editTextArr) {
            editText.setOnFocusChangeListener(iVar);
        }
        EditText editText2 = editTextArr[0];
        editText2.postDelayed(new m2(2, editText2), 100L);
    }

    Object a();

    String b(Context context);

    String c(Context context);

    int d(Context context);

    ArrayList e();

    View g(LayoutInflater layoutInflater, ViewGroup viewGroup, CalendarConstraints calendarConstraints, w wVar);

    boolean h();

    void i(long j7);

    ArrayList k();
}
