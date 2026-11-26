package androidx.media;

import java.util.Arrays;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_2_1633180.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {

    /* renamed from: a, reason: collision with root package name */
    public int f2222a = 0;

    /* renamed from: b, reason: collision with root package name */
    public int f2223b = 0;

    /* renamed from: c, reason: collision with root package name */
    public int f2224c = 0;

    /* renamed from: d, reason: collision with root package name */
    public int f2225d = -1;

    public final boolean equals(Object obj) {
        int i7;
        if (!(obj instanceof AudioAttributesImplBase)) {
            return false;
        }
        AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
        if (this.f2223b != audioAttributesImplBase.f2223b) {
            return false;
        }
        int i8 = this.f2224c;
        int i9 = audioAttributesImplBase.f2224c;
        int i10 = audioAttributesImplBase.f2225d;
        if (i10 == -1) {
            int i11 = audioAttributesImplBase.f2222a;
            int i12 = AudioAttributesCompat.CONTENT_TYPE_UNKNOWN;
            if ((i9 & 1) != 1) {
                i7 = 4;
                if ((i9 & 4) != 4) {
                    switch (i11) {
                        case 2:
                            i7 = 0;
                            break;
                        case 3:
                            i7 = 8;
                            break;
                        case 4:
                            break;
                        case 5:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                            i7 = 5;
                            break;
                        case 6:
                            i7 = 2;
                            break;
                        case 11:
                            i7 = 10;
                            break;
                        case 12:
                        default:
                            i7 = 3;
                            break;
                        case 13:
                            i7 = 1;
                            break;
                    }
                } else {
                    i7 = 6;
                }
            } else {
                i7 = 7;
            }
        } else {
            i7 = i10;
        }
        if (i7 == 6) {
            i9 |= 4;
        } else if (i7 == 7) {
            i9 |= 1;
        }
        return i8 == (i9 & 273) && this.f2222a == audioAttributesImplBase.f2222a && this.f2225d == i10;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f2223b), Integer.valueOf(this.f2224c), Integer.valueOf(this.f2222a), Integer.valueOf(this.f2225d)});
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.f2225d != -1) {
            sb.append(" stream=");
            sb.append(this.f2225d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        int i7 = this.f2222a;
        int i8 = AudioAttributesCompat.CONTENT_TYPE_UNKNOWN;
        switch (i7) {
            case 0:
                str = "USAGE_UNKNOWN";
                break;
            case 1:
                str = "USAGE_MEDIA";
                break;
            case 2:
                str = "USAGE_VOICE_COMMUNICATION";
                break;
            case 3:
                str = "USAGE_VOICE_COMMUNICATION_SIGNALLING";
                break;
            case 4:
                str = "USAGE_ALARM";
                break;
            case 5:
                str = "USAGE_NOTIFICATION";
                break;
            case 6:
                str = "USAGE_NOTIFICATION_RINGTONE";
                break;
            case 7:
                str = "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
                break;
            case 8:
                str = "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
                break;
            case 9:
                str = "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
                break;
            case 10:
                str = "USAGE_NOTIFICATION_EVENT";
                break;
            case 11:
                str = "USAGE_ASSISTANCE_ACCESSIBILITY";
                break;
            case 12:
                str = "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
                break;
            case 13:
                str = "USAGE_ASSISTANCE_SONIFICATION";
                break;
            case 14:
                str = "USAGE_GAME";
                break;
            case 15:
            default:
                str = a.a.d("unknown usage ", i7);
                break;
            case 16:
                str = "USAGE_ASSISTANT";
                break;
        }
        sb.append(str);
        sb.append(" content=");
        sb.append(this.f2223b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.f2224c).toUpperCase());
        return sb.toString();
    }
}
