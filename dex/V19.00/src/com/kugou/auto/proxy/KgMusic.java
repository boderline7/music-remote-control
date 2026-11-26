package com.kugou.auto.proxy;

import a.a;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class KgMusic implements Parcelable {
    public static final Parcelable.Creator<KgMusic> CREATOR = new Parcelable.Creator<KgMusic>() { // from class: com.kugou.auto.proxy.KgMusic.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KgMusic createFromParcel(Parcel parcel) {
            return new KgMusic(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KgMusic[] newArray(int i7) {
            return new KgMusic[i7];
        }
    };
    public static final int imgUrlSize240 = 240;

    @Deprecated
    public final long albumId;
    public String albumImagePath;
    public final String albumName;
    public final long duration;
    public String hashValue;

    @Deprecated
    public final long id;
    public final String musicName;
    public int musicType;
    public final String singerName;
    public final String songAlbumId;
    public final String songId;

    public KgMusic(Parcel parcel) {
        this.id = parcel.readLong();
        this.musicName = parcel.readString();
        this.singerName = parcel.readString();
        this.albumId = parcel.readLong();
        this.albumName = parcel.readString();
        this.albumImagePath = parcel.readString();
        this.duration = parcel.readLong();
        this.hashValue = parcel.readString();
        this.musicType = parcel.readInt();
        this.songId = parcel.readString();
        this.songAlbumId = parcel.readString();
    }

    public KgMusic(String str, String str2, String str3, String str4, String str5, String str6, long j7, String str7, int i7) {
        long j8;
        long j9 = -1;
        if (str == null || str.length() <= 0) {
            j8 = -1;
        } else {
            try {
                j8 = Long.parseLong(str);
            } catch (Exception unused) {
                j8 = System.currentTimeMillis();
            }
        }
        this.id = j8;
        this.songId = str;
        this.musicName = str2;
        this.singerName = str3;
        if (str4 != null && str4.length() > 0) {
            try {
                j9 = Long.parseLong(str4);
            } catch (Exception unused2) {
                j9 = System.currentTimeMillis();
            }
        }
        this.albumId = j9;
        this.songAlbumId = str4;
        this.albumName = str5;
        this.albumImagePath = correctImgUrl(str6, imgUrlSize240);
        this.duration = j7;
        this.hashValue = str7;
        this.musicType = i7;
    }

    public static String correctImgUrl(String str, int i7) {
        return !TextUtils.isEmpty(str) ? str.replace("{size}", String.valueOf(i7)) : str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("KgMusic{id=");
        sb.append(this.id);
        sb.append(", musicName='");
        sb.append(this.musicName);
        sb.append("', singerName='");
        sb.append(this.singerName);
        sb.append("', albumId=");
        sb.append(this.albumId);
        sb.append(", albumName='");
        sb.append(this.albumName);
        sb.append("', albumImagePath='");
        sb.append(this.albumImagePath);
        sb.append("', duration=");
        sb.append(this.duration);
        sb.append(", hashValue='");
        sb.append(this.hashValue);
        sb.append("', musicType=");
        sb.append(this.musicType);
        sb.append(", songId='");
        sb.append(this.songId);
        sb.append("', songAlbumId='");
        return a.i(sb, this.songAlbumId, "'}");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeLong(this.id);
        parcel.writeString(this.musicName);
        parcel.writeString(this.singerName);
        parcel.writeLong(this.albumId);
        parcel.writeString(this.albumName);
        parcel.writeString(this.albumImagePath);
        parcel.writeLong(this.duration);
        parcel.writeString(this.hashValue);
        parcel.writeInt(this.musicType);
        parcel.writeString(this.songId);
        parcel.writeString(this.songAlbumId);
    }
}
