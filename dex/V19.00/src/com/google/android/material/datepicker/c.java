package com.google.android.material.datepicker;

import com.google.android.material.datepicker.CalendarConstraints;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class c implements f {
    @Override // com.google.android.material.datepicker.f
    public final boolean a(List list, long j7) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CalendarConstraints.DateValidator dateValidator = (CalendarConstraints.DateValidator) it.next();
            if (dateValidator != null && dateValidator.f(j7)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.material.datepicker.f
    public final int getId() {
        return 1;
    }
}
