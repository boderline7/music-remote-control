package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public final class b implements Parcelable.Creator {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1921a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f1921a) {
            case 0:
                return new BackStackState(parcel);
            case 1:
                FragmentManager$LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo = new FragmentManager$LaunchedFragmentInfo();
                fragmentManager$LaunchedFragmentInfo.f1881a = parcel.readString();
                fragmentManager$LaunchedFragmentInfo.f1882b = parcel.readInt();
                return fragmentManager$LaunchedFragmentInfo;
            case 2:
                FragmentManagerState fragmentManagerState = new FragmentManagerState();
                fragmentManagerState.f1887e = null;
                fragmentManagerState.f1888f = new ArrayList();
                fragmentManagerState.f1889g = new ArrayList();
                fragmentManagerState.f1883a = parcel.createTypedArrayList(FragmentState.CREATOR);
                fragmentManagerState.f1884b = parcel.createStringArrayList();
                fragmentManagerState.f1885c = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
                fragmentManagerState.f1886d = parcel.readInt();
                fragmentManagerState.f1887e = parcel.readString();
                fragmentManagerState.f1888f = parcel.createStringArrayList();
                fragmentManagerState.f1889g = parcel.createTypedArrayList(Bundle.CREATOR);
                fragmentManagerState.f1890h = parcel.createTypedArrayList(FragmentManager$LaunchedFragmentInfo.CREATOR);
                return fragmentManagerState;
            default:
                return new FragmentState(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i7) {
        switch (this.f1921a) {
            case 0:
                return new BackStackState[i7];
            case 1:
                return new FragmentManager$LaunchedFragmentInfo[i7];
            case 2:
                return new FragmentManagerState[i7];
            default:
                return new FragmentState[i7];
        }
    }
}
