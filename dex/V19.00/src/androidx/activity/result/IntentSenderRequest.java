package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.l;
import d.j;
import i5.a;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class IntentSenderRequest implements Parcelable {

    /* renamed from: a, reason: collision with root package name */
    public final IntentSender f650a;

    /* renamed from: b, reason: collision with root package name */
    public final Intent f651b;

    /* renamed from: c, reason: collision with root package name */
    public final int f652c;

    /* renamed from: d, reason: collision with root package name */
    public final int f653d;
    public static final j Companion = new j();
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new l(6);

    public IntentSenderRequest(IntentSender intentSender, Intent intent, int i7, int i8) {
        a.g("intentSender", intentSender);
        this.f650a = intentSender;
        this.f651b = intent;
        this.f652c = i7;
        this.f653d = i8;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        a.g("dest", parcel);
        parcel.writeParcelable(this.f650a, i7);
        parcel.writeParcelable(this.f651b, i7);
        parcel.writeInt(this.f652c);
        parcel.writeInt(this.f653d);
    }
}
