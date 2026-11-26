package com.navi.tool.baidu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemClock;
import android.util.Base64;
import com.baidu.naviauto.imaplbs.IMapAutoAPIService;
import com.navi.tool.service.PhoneNotificationRelayService;
import com.yalantis.ucrop.view.CropImageView;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import n1.z;
import org.json.JSONArray;
import org.json.JSONObject;
import s5.h0;
import y0.n;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class e {

    /* renamed from: p, reason: collision with root package name */
    public static volatile e f4632p;

    /* renamed from: q, reason: collision with root package name */
    public static final HashMap f4633q = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    public final Context f4634a;

    /* renamed from: b, reason: collision with root package name */
    public HandlerThread f4635b;

    /* renamed from: c, reason: collision with root package name */
    public Handler f4636c;

    /* renamed from: d, reason: collision with root package name */
    public IMapAutoAPIService f4637d;

    /* renamed from: e, reason: collision with root package name */
    public boolean f4638e;

    /* renamed from: f, reason: collision with root package name */
    public boolean f4639f;

    /* renamed from: g, reason: collision with root package name */
    public String f4640g = h0.FRAGMENT_ENCODE_SET;

    /* renamed from: h, reason: collision with root package name */
    public boolean f4641h = false;

    /* renamed from: i, reason: collision with root package name */
    public long f4642i = 0;

    /* renamed from: j, reason: collision with root package name */
    public final a f4643j = new a(this);

    /* renamed from: k, reason: collision with root package name */
    public final b f4644k = new b(this, 0);

    /* renamed from: l, reason: collision with root package name */
    public final b f4645l = new b(this, 1);

    /* renamed from: m, reason: collision with root package name */
    public final c f4646m = new c(this);

    /* renamed from: n, reason: collision with root package name */
    public final b f4647n = new b(this, 2);

    /* renamed from: o, reason: collision with root package name */
    public final b f4648o = new b(this, 3);

    static {
        q("0+1", "ba_ic_lane_straight");
        q("0+3", "ba_ic_lane_straight_gray");
        q("9+1", "ba_ic_lane_turn_left_90");
        q("9+3", "ba_ic_lane_turn_left_90_gray");
        q("3+1", "ba_ic_lane_turn_right_90");
        q("3+3", "ba_ic_lane_turn_right_90_gray");
        q("6+1", "ba_ic_lane_turn_around_left");
        q("6+3", "ba_ic_lane_turn_around_left_gray");
        q("5+1", "ba_ic_lane_left_front_bright");
        q("5+3", "ba_ic_lane_left_front_gray");
        q("4+1", "ba_ic_lane_right_front_bright");
        q("4+3", "ba_ic_lane_right_front_gray");
        q("7+1", "ba_ic_lane_around_right_bright");
        q("7+3", "ba_ic_lane_around_right_gray");
        q("09+11", "ba_ic_lane_2cross_left_straight_bright");
        q("09+13", "ba_ic_lane_2cross_left_straight");
        q("09+33", "ba_ic_lane_2cross_left_straight");
        q("09+31", "ba_ic_lane_2cross_turn_left");
        q("03+11", "ba_ic_lane_2cross_right_straight_bright");
        q("03+13", "ba_ic_lane_2cross_right_straight");
        q("03+33", "ba_ic_lane_2cross_right_straight");
        q("03+31", "ba_ic_lane_2cross_turn_right");
        q("69+11", "ba_ic_lane_around_and_left_bright");
        q("69+13", "ba_ic_lane_around_and_left");
        q("69+33", "ba_ic_lane_around_and_left");
        q("69+31", "ba_ic_lane_left_and_around");
        q("36+11", "ba_ic_lane_around_and_right_bright");
        q("36+13", "ba_ic_lane_right_and_around");
        q("36+33", "ba_ic_lane_around_and_right");
        q("36+31", "ba_ic_lane_around_and_right");
        q("06+11", "ba_ic_lane_around_and_straight_bright");
        q("06+13", "ba_ic_lane_straight_and_around");
        q("06+33", "ba_ic_lane_around_and_straight");
        q("06+31", "ba_ic_lane_around_and_straight");
        q("39+11", "ba_ic_lane_left_and_right_bright");
        q("39+13", "ba_ic_lane_right_and_left");
        q("39+33", "ba_ic_lane_left_and_right");
        q("39+31", "ba_ic_lane_left_and_right");
        q("07+11", "ba_ic_lane_straight_and_around_right_bright");
        q("07+13", "ba_ic_lane_straight_and_around_right");
        q("07+33", "ba_ic_lane_straight_and_around_right_gray");
        q("07+31", "ba_ic_lane_around_right_and_straight");
        q("04+11", "ba_ic_lane_straight_and_right_front_bright");
        q("04+13", "ba_ic_lane_straight_and_right_front");
        q("04+33", "ba_ic_lane_straight_and_right_front_gray");
        q("04+31", "ba_ic_lane_right_front_and_straight");
        q("05+11", "ba_ic_lane_left_front_and_straight_bright");
        q("05+13", "ba_ic_lane_straight_and_left_front");
        q("05+33", "ba_ic_lane_left_front_and_straight_gray");
        q("05+31", "ba_ic_lane_left_front_and_straight");
        q("039+111", "ba_ic_lane_3cross_bright");
        q("039+133", "ba_ic_lane_3cross_straight");
        q("039+331", "ba_ic_lane_3cross_turn_left");
        q("039+313", "ba_ic_lane_3cross_turn_right");
        q("039+333", "ba_ic_lane_3cross_straight");
        q("369+111", "ba_ic_lane_around_left_right_bright");
        q("369+133", "ba_ic_lane_right_around_left");
        q("369+331", "ba_ic_lane_left_around_right");
        q("369+313", "ba_ic_lane_around_left_right");
        q("369+333", "ba_ic_lane_around_left_right");
        q("069+111", "ba_ic_lane_around_left_straight_bright");
        q("069+133", "ba_ic_lane_straight_left_around");
        q("069+331", "ba_ic_lane_left_around_straight");
        q("069+313", "ba_ic_lane_around_left_straight");
        q("069+333", "ba_ic_lane_around_left_straight");
        q("036+111", "ba_ic_lane_around_right_straight_bright");
        q("036+133", "ba_ic_lane_straight_right_around");
        q("036+331", "ba_ic_lane_around_right_straight");
        q("036+313", "ba_ic_lane_right_around_straight");
        q("036+333", "ba_ic_lane_around_right_straight");
        q("0369+1111", "ba_ic_lane_around_left_right_straight_bright");
        q("0369+1333", "ba_ic_lane_straight_around_left_right");
        q("0369+3133", "ba_ic_lane_right_around_left_straight");
        q("0369+3313", "ba_ic_lane_around_left_right_straight");
        q("0369+3333", "ba_ic_lane_around_left_right_straight");
        q("46+11", "ba_ic_lane_around_left_and_right_front_bright");
        q("46+13", "ba_ic_lane_right_front_and_around_left");
        q("46+31", "ba_ic_lane_around_left_and_right_front");
        q("46+33", "ba_ic_lane_around_left_and_right_front_gray");
        q("37+11", "ba_ic_lane_right_and_around_right_bright");
        q("37+13", "ba_ic_lane_right_and_around_right");
        q("37+31", "ba_ic_lane_around_right_and_right");
        q("37+33", "ba_ic_lane_right_and_around_right_gray");
        q("49+11", "ba_ic_lane_left_and_right_front_bright");
        q("49+13", "ba_ic_lane_right_front_and_left");
        q("49+31", "ba_ic_lane_left_and_right_front");
        q("49+33", "ba_ic_lane_left_and_right_front_gray");
        q("35+11", "ba_ic_lane_left_front_and_right_bright");
        q("35+13", "ba_ic_lane_right_and_left_front");
        q("35+31", "ba_ic_lane_left_front_and_right");
        q("35+33", "ba_ic_lane_left_front_and_right_gray");
        q("56+11", "ba_ic_lane_around_left_and_left_front_bright");
        q("56+13", "ba_ic_lane_left_front_and_around_left");
        q("56+31", "ba_ic_lane_around_left_and_left_front");
        q("56+33", "ba_ic_lane_around_left_and_left_front_gray");
        q("34+11", "ba_ic_lane_right_front_and_right_bright");
        q("34+13", "ba_ic_lane_right_and_right_front");
        q("34+31", "ba_ic_lane_right_front_and_right");
        q("34+33", "ba_ic_lane_right_front_and_right_gray");
        q("59+11", "ba_ic_lane_left_and_left_front_bright");
        q("59+13", "ba_ic_lane_left_front_and_left");
        q("59+31", "ba_ic_lane_left_and_left_front");
        q("59+33", "ba_ic_lane_left_and_left_front_gray");
        q("79+11", "ba_ic_lane_left_and_around_right_bright");
        q("79+13", "ba_ic_lane_around_right_and_left");
        q("79+31", "ba_ic_lane_left_and_around_right");
        q("79+33", "ba_ic_lane_left_and_around_right_gray");
        q("47+11", "ba_ic_lane_right_front_and_around_right_bright");
        q("47+13", "ba_ic_lane_right_front_and_around_right");
        q("47+31", "ba_ic_lane_around_right_and_right_front");
        q("47+33", "ba_ic_lane_right_front_and_around_right_gray");
        q("57+11", "ba_ic_lane_left_front_and_around_right_bright");
        q("57+13", "ba_ic_lane_left_front_and_around_right");
        q("57+31", "ba_ic_lane_around_right_and_left_front");
        q("57+33", "ba_ic_lane_left_front_and_around_right_gray");
        q("037+111", "ba_ic_lane_straight_and_right_and_around_right_bright");
        q("037+133", "ba_ic_lane_straight_and_right_and_around_right");
        q("037+313", "ba_ic_lane_right_and_straight_and_around_right");
        q("037+331", "ba_ic_lane_around_right_and_straight_and_right");
        q("037+333", "ba_ic_lane_straight_and_right_and_around_right_gray");
        q("079+111", "ba_ic_lane_left_and_straight_and_around_right_bright");
        q("079+133", "ba_ic_lane_straight_and_left_and_around_right");
        q("079+331", "ba_ic_lane_left_and_straight_and_around_right");
        q("079+313", "ba_ic_lane_around_right_and_left_and_straight");
        q("079+333", "ba_ic_lane_left_and_straight_and_around_right_gray");
        q("379+111", "ba_ic_lane_left_and_right_and_around_right_bright");
        q("379+133", "ba_ic_lane_right_and_left_and_around_right");
        q("379+331", "ba_ic_lane_left_and_right_and_around_right");
        q("379+313", "ba_ic_lane_around_right_and_left_and_right");
        q("379+333", "ba_ic_lane_left_and_right_and_around_right_gray");
        q("0+bus+1", "ba_ic_lane_bus_front");
        q("0+bus+3", "ba_ic_lane_bus_straight_gray");
        q("3+bus+1", "ba_ic_lane_bus_right");
        q("3+bus+3", "ba_ic_lane_bus_turn_right_90_gray");
        q("6+bus+1", "ba_ic_lane_bus_back");
        q("6+bus+3", "ba_ic_lane_bus_turn_around_left_gray");
        q("9+bus+1", "ba_ic_lane_bus_left");
        q("9+bus+3", "ba_ic_lane_bus_turn_left_90_gray");
        q("039+bus+333", "ba_ic_lane_bus_3cross_gray");
        q("69+bus+33", "ba_ic_lane_bus_around_and_left_gray");
        q("36+bus+33", "ba_ic_lane_bus_around_and_right_gray");
        q("06+bus+33", "ba_ic_lane_bus_around_and_straight_gray");
        q("369+bus+333", "ba_ic_lane_bus_around_left_right_gray");
        q("036+bus+333", "ba_ic_lane_bus_around_left_straight_gray");
        q("069+bus+333", "ba_ic_lane_bus_around_right_straight_gray");
        q("39+bus+33", "ba_ic_lane_bus_left_and_right_gray");
        q("03+bus+33", "ba_ic_lane_bus_right_straight_gray");
        q("03+bus+13", "ba_ic_lane_bus_2cross_right_straight_bright");
        q("1", "ba_ic_lane_bus");
        q("3", "ba_ic_lane_hov");
        q("4", "ba_ic_lane_tidal_text");
        q("5", "ba_ic_lane_tidal_front");
        q("6", "ba_ic_lane_tidal_available");
        q("7", "ba_ic_lane_bus_text");
        q("8", "ba_ic_lane_hov_bright");
        q("9", "ba_ic_lane_tidal_text_bright");
        q("10", "ba_ic_lane_tidal_front_bright");
        q("11", "ba_ic_lane_tidal_available_bright");
        q("12", "ba_ic_lane_variable");
    }

    public e(Context context) {
        this.f4634a = context.getApplicationContext();
        HandlerThread handlerThread = new HandlerThread("baidu-navi-poll");
        this.f4635b = handlerThread;
        handlerThread.start();
        this.f4636c = new Handler(this.f4635b.getLooper());
    }

    public static void a(e eVar, String str) {
        eVar.getClass();
        if (str != null) {
            try {
                if (str.isEmpty()) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(str);
                float optDouble = (float) jSONObject.optDouble("bearing", -1.0d);
                h hVar = h.B;
                if (optDouble >= CropImageView.DEFAULT_ASPECT_RATIO) {
                    hVar.getClass();
                    System.currentTimeMillis();
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("point");
                if (optJSONObject != null) {
                    double optDouble2 = optJSONObject.optDouble("lat", Double.NaN);
                    double optDouble3 = optJSONObject.optDouble("lng", Double.NaN);
                    if (Double.isNaN(optDouble2) || Double.isNaN(optDouble3)) {
                        return;
                    }
                    x(hVar, optDouble2, optDouble3);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void b(e eVar, String str) {
        JSONArray optJSONArray;
        JSONObject optJSONObject;
        double d7;
        double d8;
        eVar.getClass();
        try {
            h hVar = h.B;
            if (str != null && !str.isEmpty()) {
                String trim = str.trim();
                if (trim.startsWith("[")) {
                    optJSONArray = new JSONArray(trim);
                } else {
                    JSONObject jSONObject = new JSONObject(trim);
                    JSONArray optJSONArray2 = jSONObject.optJSONArray("list");
                    optJSONArray = optJSONArray2 == null ? jSONObject.optJSONArray("highWaySubscribeInfoList") : optJSONArray2;
                }
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    ArrayList arrayList = new ArrayList();
                    int i7 = 0;
                    while (true) {
                        int length = optJSONArray.length();
                        String str2 = h0.FRAGMENT_ENCODE_SET;
                        if (i7 >= length) {
                            break;
                        }
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i7);
                        if (optJSONObject2 != null && optJSONObject2.optInt("type", -1) == 4 && (optJSONObject = optJSONObject2.optJSONObject("serviceAreaInfo")) != null) {
                            String optString = optJSONObject.optString("name", h0.FRAGMENT_ENCODE_SET);
                            if (optString != null) {
                                str2 = optString.trim();
                                int indexOf = str2.indexOf(65288);
                                if (indexOf < 0) {
                                    indexOf = str2.indexOf(40);
                                }
                                if (indexOf > 0) {
                                    str2 = str2.substring(0, indexOf).trim();
                                }
                            }
                            String str3 = str2;
                            int optInt = optJSONObject.optInt("distance", -1);
                            if (!str3.isEmpty() && optInt >= 0) {
                                JSONObject optJSONObject3 = optJSONObject.optJSONObject("position");
                                if (optJSONObject3 != null) {
                                    d7 = optJSONObject3.optDouble("lat", Double.NaN);
                                    d8 = optJSONObject3.optDouble("lng", Double.NaN);
                                } else {
                                    d7 = Double.NaN;
                                    d8 = Double.NaN;
                                }
                                arrayList.add(new d(str3, optInt, d7, d8));
                            }
                        }
                        i7++;
                    }
                    if (arrayList.isEmpty()) {
                        j(hVar);
                        return;
                    }
                    Collections.sort(arrayList, new m0.a(1));
                    d dVar = (d) arrayList.get(0);
                    hVar.f4654c = dVar.f4628a;
                    hVar.f4655d = dVar.f4629b;
                    hVar.f4656e = dVar.f4630c;
                    hVar.f4657f = dVar.f4631d;
                    if (arrayList.size() > 1) {
                        d dVar2 = (d) arrayList.get(1);
                        hVar.f4658g = dVar2.f4628a;
                        hVar.f4659h = dVar2.f4629b;
                        hVar.f4660i = dVar2.f4630c;
                        hVar.f4661j = dVar2.f4631d;
                    } else {
                        hVar.f4658g = h0.FRAGMENT_ENCODE_SET;
                        hVar.f4659h = -1;
                        hVar.f4660i = Double.NaN;
                        hVar.f4661j = Double.NaN;
                    }
                    o();
                    return;
                }
                j(hVar);
                return;
            }
            j(hVar);
        } catch (Exception unused) {
        }
    }

    public static void c(e eVar) {
        eVar.getClass();
        h hVar = h.B;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j7 = eVar.f4642i;
        if (j7 <= 0 || elapsedRealtime - j7 <= PhoneNotificationRelayService.CONNECTION_MODE_TRANSITION_GRACE_MS) {
            r();
            o();
            return;
        }
        hVar.getClass();
        hVar.f4652a = false;
        hVar.f4653b = h0.FRAGMENT_ENCODE_SET;
        hVar.f4654c = h0.FRAGMENT_ENCODE_SET;
        hVar.f4655d = -1;
        hVar.f4656e = Double.NaN;
        hVar.f4657f = Double.NaN;
        hVar.f4658g = h0.FRAGMENT_ENCODE_SET;
        hVar.f4659h = -1;
        hVar.f4660i = Double.NaN;
        hVar.f4661j = Double.NaN;
        hVar.f4662k = h0.FRAGMENT_ENCODE_SET;
        hVar.f4663l = -1;
        hVar.f4664m = -1;
        hVar.f4665n = h0.FRAGMENT_ENCODE_SET;
        hVar.f4666o = h0.FRAGMENT_ENCODE_SET;
        hVar.f4668q = h0.FRAGMENT_ENCODE_SET;
        hVar.f4669r = new ArrayList();
        hVar.f4670s = -1;
        hVar.f4671t = 0L;
        hVar.f4672u = new JSONArray();
        hVar.f4673v = false;
        hVar.f4674w = 0;
        hVar.f4675x = new JSONArray();
        hVar.f4676y = false;
        hVar.f4677z = h0.FRAGMENT_ENCODE_SET;
        hVar.A = 0L;
        eVar.f4642i = 0L;
    }

    public static void d(e eVar, String str) {
        JSONObject optJSONObject;
        boolean z6;
        int i7;
        boolean z7;
        int i8;
        eVar.getClass();
        if (str != null) {
            try {
                if (!str.isEmpty() && (optJSONObject = new JSONObject(str).optJSONObject("laneInfo")) != null) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("itemList");
                    if (optJSONArray != null && optJSONArray.length() != 0) {
                        JSONArray jSONArray = new JSONArray();
                        for (int i9 = 0; i9 < optJSONArray.length(); i9++) {
                            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i9);
                            if (optJSONObject2 != null) {
                                JSONObject optJSONObject3 = optJSONObject2.optJSONObject("laneTypeInfo");
                                int i10 = -1;
                                if (optJSONObject3 != null) {
                                    i7 = optJSONObject3.optInt("laneType", -1);
                                    z6 = optJSONObject3.optBoolean("isBright", false);
                                } else {
                                    z6 = false;
                                    i7 = -1;
                                }
                                ArrayList arrayList = new ArrayList();
                                JSONArray optJSONArray2 = optJSONObject2.optJSONArray("directionList");
                                if (optJSONArray2 != null) {
                                    int i11 = 0;
                                    z7 = false;
                                    while (i11 < optJSONArray2.length()) {
                                        JSONObject optJSONObject4 = optJSONArray2.optJSONObject(i11);
                                        if (optJSONObject4 != null) {
                                            int optInt = optJSONObject4.optInt("direction", i10);
                                            int optInt2 = optJSONObject4.optInt("validType", 1);
                                            switch (optInt) {
                                                case 1:
                                                    i8 = 0;
                                                    break;
                                                case 2:
                                                    i8 = 9;
                                                    break;
                                                case 3:
                                                    i8 = 3;
                                                    break;
                                                case 4:
                                                    i8 = 6;
                                                    break;
                                                case 5:
                                                    i8 = 5;
                                                    break;
                                                case 6:
                                                    i8 = 4;
                                                    break;
                                                case 7:
                                                    i8 = 7;
                                                    break;
                                                default:
                                                    i8 = i10;
                                                    break;
                                            }
                                            if (i8 >= 0) {
                                                int[] iArr = new int[2];
                                                iArr[0] = i8;
                                                if (optInt2 == 2) {
                                                    optInt2 = 3;
                                                }
                                                iArr[1] = optInt2;
                                                arrayList.add(iArr);
                                            }
                                            if (optJSONObject4.optBoolean("isRecommend", false)) {
                                                z7 = true;
                                            }
                                        }
                                        i11++;
                                        i10 = -1;
                                    }
                                } else {
                                    z7 = false;
                                }
                                String u6 = u(i7, arrayList, z6);
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("iconName", u6);
                                    jSONObject.put("trafficLaneAdvised", z7);
                                    jSONObject.put("trafficLaneType", i7);
                                    jSONObject.put("lane_id", i9);
                                    jSONArray.put(jSONObject);
                                } catch (Exception unused) {
                                }
                            }
                        }
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("drive_way_enabled", true);
                            jSONObject2.put("drive_way_info", jSONArray);
                            h.B.f4668q = jSONObject2.toString();
                        } catch (Exception unused2) {
                        }
                        String str2 = h.B.f4668q;
                        return;
                    }
                    h.B.f4668q = h0.FRAGMENT_ENCODE_SET;
                }
            } catch (Exception unused3) {
            }
        }
    }

    public static void e(e eVar, String str) {
        JSONObject jSONObject;
        eVar.getClass();
        if (str != null) {
            try {
                if (str.isEmpty()) {
                    return;
                }
                h hVar = h.B;
                String trim = str.trim();
                if (trim.startsWith("[")) {
                    JSONArray jSONArray = new JSONArray(trim);
                    if (jSONArray.length() == 0) {
                        return;
                    }
                    jSONObject = jSONArray.optJSONObject(0);
                    if (jSONObject == null) {
                        return;
                    }
                } else {
                    jSONObject = new JSONObject(trim);
                }
                hVar.f4664m = jSONObject.optInt("turnKind", -1);
                hVar.f4665n = jSONObject.optString("nextRoadName", h0.FRAGMENT_ENCODE_SET);
                hVar.f4666o = jSONObject.optString("roadName", h0.FRAGMENT_ENCODE_SET);
                hVar.f4667p = jSONObject.optInt("remainDistance", -1);
            } catch (Exception unused) {
            }
        }
    }

    public static void f(e eVar, String str) {
        eVar.getClass();
        if (str != null) {
            try {
                if (str.isEmpty()) {
                    return;
                }
                String trim = str.trim();
                JSONArray jSONArray = trim.startsWith("[") ? new JSONArray(trim) : new JSONObject(trim).optJSONArray("roadConditionList");
                if (jSONArray == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                int i7 = 0;
                while (true) {
                    int i8 = 1;
                    if (i7 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject optJSONObject = jSONArray.optJSONObject(i7);
                    if (optJSONObject != null) {
                        int optInt = optJSONObject.optInt("roadConditionType", 0);
                        int optInt2 = optJSONObject.optInt("addDist", 0);
                        int optInt3 = optJSONObject.optInt("distance", 0);
                        if (optInt > 0 && optInt3 > 0) {
                            int[] iArr = new int[3];
                            iArr[0] = optInt2;
                            iArr[1] = optInt3;
                            if (optInt != 1) {
                                if (optInt == 2) {
                                    i8 = 2;
                                } else if (optInt != 3) {
                                    i8 = 4;
                                    if (optInt != 4) {
                                        i8 = 0;
                                    }
                                } else {
                                    i8 = 3;
                                }
                            }
                            iArr[2] = i8;
                            arrayList.add(iArr);
                        }
                    }
                    i7++;
                }
                h hVar = h.B;
                JSONArray jSONArray2 = new JSONArray();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    int[] iArr2 = (int[]) it.next();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("start", iArr2[0]);
                    jSONObject.put("len", iArr2[1]);
                    jSONObject.put("color", iArr2[2]);
                    jSONArray2.put(jSONObject);
                }
                hVar.f4675x = jSONArray2;
                r();
                jSONArray2.toString();
                Objects.toString(hVar.f4672u);
                o();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004c A[Catch: Exception -> 0x00b2, TryCatch #0 {Exception -> 0x00b2, blocks: (B:4:0x0005, B:9:0x000d, B:12:0x002a, B:16:0x0038, B:18:0x0040, B:20:0x004c, B:23:0x005a, B:25:0x0060, B:27:0x0068, B:30:0x0073, B:33:0x009e, B:35:0x00a2, B:39:0x007c, B:41:0x0080, B:45:0x0089), top: B:3:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void g(com.navi.tool.baidu.e r7, java.lang.String r8) {
        /*
            r7.getClass()
            if (r8 == 0) goto Lb2
            boolean r0 = r8.isEmpty()     // Catch: java.lang.Exception -> Lb2
            if (r0 == 0) goto Ld
            goto Lb2
        Ld:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lb2
            r0.<init>(r8)     // Catch: java.lang.Exception -> Lb2
            java.lang.String r8 = "status"
            r1 = -1
            int r8 = r0.optInt(r8, r1)     // Catch: java.lang.Exception -> Lb2
            java.lang.String r2 = "type"
            r0.optInt(r2, r1)     // Catch: java.lang.Exception -> Lb2
            java.lang.String r1 = "junctionViewInfo"
            org.json.JSONObject r1 = r0.optJSONObject(r1)     // Catch: java.lang.Exception -> Lb2
            java.lang.String r2 = "imageBytes"
            java.lang.String r3 = ""
            if (r1 == 0) goto L35
            java.lang.String r1 = r1.optString(r2, r3)     // Catch: java.lang.Exception -> Lb2
            boolean r4 = r1.isEmpty()     // Catch: java.lang.Exception -> Lb2
            if (r4 != 0) goto L35
            goto L36
        L35:
            r1 = 0
        L36:
            if (r1 != 0) goto L5e
            java.lang.String r4 = "ilsImageViewInfo"
            org.json.JSONObject r4 = r0.optJSONObject(r4)     // Catch: java.lang.Exception -> Lb2
            if (r4 == 0) goto L5e
            java.lang.String r5 = "backgroundMapBytes"
            java.lang.String r5 = r4.optString(r5, r3)     // Catch: java.lang.Exception -> Lb2
            boolean r6 = r5.isEmpty()     // Catch: java.lang.Exception -> Lb2
            if (r6 != 0) goto L5e
            java.lang.String r1 = "arrowMapBytes"
            java.lang.String r1 = r4.optString(r1, r3)     // Catch: java.lang.Exception -> Lb2
            boolean r4 = r1.isEmpty()     // Catch: java.lang.Exception -> Lb2
            if (r4 == 0) goto L5a
            r1 = r5
            goto L5e
        L5a:
            java.lang.String r1 = k(r5, r1)     // Catch: java.lang.Exception -> Lb2
        L5e:
            if (r1 != 0) goto L73
            java.lang.String r4 = "destStreetInfo"
            org.json.JSONObject r0 = r0.optJSONObject(r4)     // Catch: java.lang.Exception -> Lb2
            if (r0 == 0) goto L73
            java.lang.String r0 = r0.optString(r2, r3)     // Catch: java.lang.Exception -> Lb2
            boolean r2 = r0.isEmpty()     // Catch: java.lang.Exception -> Lb2
            if (r2 != 0) goto L73
            r1 = r0
        L73:
            com.navi.tool.baidu.h r0 = com.navi.tool.baidu.h.B     // Catch: java.lang.Exception -> Lb2
            r2 = 1
            if (r8 == 0) goto L7a
            if (r8 != r2) goto L9e
        L7a:
            if (r1 == 0) goto L9e
            boolean r8 = r0.f4676y     // Catch: java.lang.Exception -> Lb2
            if (r8 == 0) goto L89
            java.lang.String r8 = r0.f4677z     // Catch: java.lang.Exception -> Lb2
            boolean r8 = r1.equals(r8)     // Catch: java.lang.Exception -> Lb2
            if (r8 == 0) goto L89
            goto Lb2
        L89:
            android.os.Handler r8 = r7.f4636c     // Catch: java.lang.Exception -> Lb2
            com.navi.tool.baidu.b r7 = r7.f4644k     // Catch: java.lang.Exception -> Lb2
            r8.removeCallbacks(r7)     // Catch: java.lang.Exception -> Lb2
            long r7 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> Lb2
            r0.f4676y = r2     // Catch: java.lang.Exception -> Lb2
            r0.f4677z = r1     // Catch: java.lang.Exception -> Lb2
            r0.A = r7     // Catch: java.lang.Exception -> Lb2
            o()     // Catch: java.lang.Exception -> Lb2
            goto Lb2
        L9e:
            boolean r8 = r0.f4676y     // Catch: java.lang.Exception -> Lb2
            if (r8 == 0) goto Lb2
            android.os.Handler r8 = r7.f4636c     // Catch: java.lang.Exception -> Lb2
            com.navi.tool.baidu.b r0 = r7.f4644k     // Catch: java.lang.Exception -> Lb2
            r8.removeCallbacks(r0)     // Catch: java.lang.Exception -> Lb2
            android.os.Handler r8 = r7.f4636c     // Catch: java.lang.Exception -> Lb2
            com.navi.tool.baidu.b r7 = r7.f4644k     // Catch: java.lang.Exception -> Lb2
            r0 = 3000(0xbb8, double:1.482E-320)
            r8.postDelayed(r7, r0)     // Catch: java.lang.Exception -> Lb2
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.baidu.e.g(com.navi.tool.baidu.e, java.lang.String):void");
    }

    public static String i(int i7, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("function", str);
            if (str2 != null) {
                jSONObject.put("data", str2);
            }
            jSONObject.put("requestId", (int) (System.currentTimeMillis() & 2147483647L));
            jSONObject.put("flag", i7);
            jSONObject.put("pid", String.valueOf(Process.myPid()));
            return jSONObject.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static void j(h hVar) {
        hVar.f4654c = h0.FRAGMENT_ENCODE_SET;
        hVar.f4655d = -1;
        hVar.f4656e = Double.NaN;
        hVar.f4657f = Double.NaN;
        hVar.f4658g = h0.FRAGMENT_ENCODE_SET;
        hVar.f4659h = -1;
        hVar.f4660i = Double.NaN;
        hVar.f4661j = Double.NaN;
    }

    public static String k(String str, String str2) {
        try {
            byte[] decode = Base64.decode(str, 0);
            byte[] decode2 = Base64.decode(str2, 0);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            Bitmap decodeByteArray2 = BitmapFactory.decodeByteArray(decode2, 0, decode2.length);
            if (decodeByteArray == null) {
                return str;
            }
            if (decodeByteArray.getWidth() > 960) {
                int max = Math.max(1, Math.round(decodeByteArray.getHeight() * (960.0f / decodeByteArray.getWidth())));
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeByteArray, 960, max, true);
                decodeByteArray.recycle();
                if (decodeByteArray2 != null) {
                    decodeByteArray2 = Bitmap.createScaledBitmap(decodeByteArray2, 960, max, true);
                }
                decodeByteArray = createScaledBitmap;
            }
            Bitmap copy = decodeByteArray.copy(Bitmap.Config.ARGB_8888, true);
            if (decodeByteArray2 != null) {
                new Canvas(copy).drawBitmap(decodeByteArray2, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            copy.compress(Bitmap.CompressFormat.JPEG, 85, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Throwable unused) {
            return str;
        }
    }

    public static e l(Context context) {
        if (f4632p == null) {
            synchronized (e.class) {
                try {
                    if (f4632p == null) {
                        f4632p = new e(context);
                    }
                } finally {
                }
            }
        }
        return f4632p;
    }

    public static double m(double d7, double d8, double d9, double d10) {
        double radians = Math.toRadians(d9 - d7);
        double radians2 = Math.toRadians(d10 - d8);
        double d11 = radians / 2.0d;
        double d12 = radians2 / 2.0d;
        double sin = (Math.sin(d12) * Math.sin(d12) * Math.cos(Math.toRadians(d9)) * Math.cos(Math.toRadians(d7))) + (Math.sin(d11) * Math.sin(d11));
        return Math.atan2(Math.sqrt(sin), Math.sqrt(1.0d - sin)) * 2.0d * 6371000.0d;
    }

    public static void o() {
        try {
            i6.e.b().f(new f());
        } catch (Exception unused) {
        }
    }

    public static void p(String str) {
        int i7;
        if (str == null) {
            return;
        }
        try {
            String trim = str.trim();
            if (trim.isEmpty()) {
                return;
            }
            boolean z6 = true;
            try {
                i7 = Integer.parseInt(trim);
            } catch (NumberFormatException unused) {
                if (!"true".equalsIgnoreCase(trim) && !"1".equals(trim)) {
                    i7 = 0;
                }
                i7 = 1;
            }
            h hVar = h.B;
            if (i7 == 0) {
                z6 = false;
            }
            if (z6 == hVar.f4652a) {
                return;
            }
            if (z6) {
                hVar.f4672u = new JSONArray();
                hVar.f4675x = new JSONArray();
                hVar.f4673v = false;
                hVar.f4674w = 0;
            } else {
                hVar.f4663l = -1;
                hVar.f4662k = h0.FRAGMENT_ENCODE_SET;
                hVar.f4664m = -1;
                hVar.f4665n = h0.FRAGMENT_ENCODE_SET;
                hVar.f4666o = h0.FRAGMENT_ENCODE_SET;
                hVar.f4667p = -1;
                hVar.f4653b = h0.FRAGMENT_ENCODE_SET;
                hVar.f4672u = new JSONArray();
                hVar.f4675x = new JSONArray();
                hVar.f4673v = false;
                hVar.f4674w = 0;
            }
            hVar.f4652a = z6;
        } catch (Exception unused2) {
        }
    }

    public static void q(String str, String str2) {
        f4633q.put(str, str2);
    }

    public static void r() {
        try {
            h hVar = h.B;
            JSONArray jSONArray = hVar.f4675x;
            int i7 = 0;
            if (jSONArray != null && jSONArray.length() != 0) {
                int i8 = 0;
                for (int i9 = 0; i9 < jSONArray.length(); i9++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i9);
                    if (optJSONObject != null) {
                        i8 = Math.max(i8, optJSONObject.optInt("start", 0) + optJSONObject.optInt("len", 0));
                    }
                }
                String str = hVar.f4662k;
                int i10 = -1;
                if (str != null && !str.isEmpty()) {
                    try {
                        i10 = (int) Math.round(Double.parseDouble(str.trim()));
                    } catch (Exception unused) {
                    }
                }
                int i11 = i10 >= 0 ? i8 - i10 : 0;
                JSONArray jSONArray2 = new JSONArray();
                int i12 = 0;
                while (i12 < jSONArray.length()) {
                    JSONObject optJSONObject2 = jSONArray.optJSONObject(i12);
                    if (optJSONObject2 != null) {
                        int optInt = optJSONObject2.optInt("start", i7);
                        int optInt2 = optJSONObject2.optInt("len", i7) + optInt;
                        int optInt3 = optJSONObject2.optInt("color", i7);
                        if (i11 <= 0) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("weight", optJSONObject2.optInt("len", i7));
                            jSONObject.put("status", optInt3);
                            jSONArray2.put(jSONObject);
                        } else if (optInt2 <= i11) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("weight", optJSONObject2.optInt("len", i7));
                            jSONObject2.put("status", 10);
                            jSONArray2.put(jSONObject2);
                        } else if (optInt >= i11) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("weight", optJSONObject2.optInt("len", i7));
                            jSONObject3.put("status", optInt3);
                            jSONArray2.put(jSONObject3);
                        } else {
                            while (optInt < i11) {
                                int min = Math.min(100, i11 - optInt);
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("weight", min);
                                jSONObject4.put("status", 10);
                                jSONArray2.put(jSONObject4);
                                optInt += 100;
                            }
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("weight", optInt2 - i11);
                            jSONObject5.put("status", optInt3);
                            jSONArray2.put(jSONObject5);
                        }
                    }
                    i12++;
                    i7 = 0;
                }
                hVar.f4672u = jSONArray2;
                hVar.f4673v = jSONArray2.length() > 0;
                hVar.f4674w = (i8 <= 0 || i11 <= 0) ? 0 : Math.min(100, Math.round((i11 * 100.0f) / i8));
                return;
            }
            hVar.f4672u = new JSONArray();
            hVar.f4673v = false;
            hVar.f4674w = 0;
        } catch (Exception unused2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x0099, code lost:
    
        if (r0.equals("0") == false) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String u(int r11, java.util.ArrayList r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.navi.tool.baidu.e.u(int, java.util.ArrayList, boolean):java.lang.String");
    }

    public static void x(h hVar, double d7, double d8) {
        boolean z6;
        int round;
        int round2;
        if (Double.isNaN(hVar.f4656e) || Double.isNaN(hVar.f4657f) || (round2 = (int) Math.round(m(d7, d8, hVar.f4656e, hVar.f4657f))) == hVar.f4655d) {
            z6 = false;
        } else {
            hVar.f4655d = round2;
            z6 = true;
        }
        if (!Double.isNaN(hVar.f4660i) && !Double.isNaN(hVar.f4661j) && (round = (int) Math.round(m(d7, d8, hVar.f4660i, hVar.f4661j))) != hVar.f4659h) {
            hVar.f4659h = round;
        } else if (!z6) {
            return;
        }
        o();
    }

    public final void h() {
        b bVar = this.f4647n;
        Context context = this.f4634a;
        this.f4638e = true;
        try {
            Intent intent = new Intent("com.baidu.naviauto.imaplbs.lbsservice");
            intent.setPackage("com.baidu.naviauto");
            intent.putExtra("package", context.getPackageName());
            if (context.bindService(intent, this.f4646m, 1)) {
                this.f4636c.postDelayed(this.f4648o, 10000L);
            } else {
                this.f4638e = false;
                if (this.f4639f) {
                    this.f4636c.postDelayed(bVar, 5000L);
                }
            }
        } catch (Exception unused) {
            this.f4638e = false;
            if (this.f4639f) {
                this.f4636c.postDelayed(bVar, 5000L);
            }
        }
    }

    public final boolean n() {
        return this.f4637d != null;
    }

    public final void s(String str, String str2) {
        try {
            String i7 = i(1, str, str2);
            String str3 = this.f4640g;
            if (str3 != null && !str3.isEmpty()) {
                i7 = z.a0(this.f4640g, i7);
            }
            String send = this.f4637d.send(i7);
            if (send != null && !send.isEmpty()) {
                String str4 = this.f4640g;
                if (str4 != null && !str4.isEmpty()) {
                    send = z.P(this.f4640g, send);
                }
                if (send.contains("\"errorCode\":0")) {
                    return;
                }
                v(str, str2);
                return;
            }
            v(str, str2);
        } catch (Exception unused) {
            v(str, str2);
        }
    }

    public final JSONObject t() {
        if (!n()) {
            return null;
        }
        try {
            String i7 = i(0, "is_in_navi", null);
            String str = this.f4640g;
            if (str != null && !str.isEmpty()) {
                i7 = z.a0(this.f4640g, i7);
            }
            String send = this.f4637d.send(i7);
            if (send != null && !send.isEmpty()) {
                String str2 = this.f4640g;
                if (str2 != null && !str2.isEmpty()) {
                    send = z.P(this.f4640g, send);
                }
                return new JSONObject(send);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final void v(String str, String str2) {
        if (this.f4639f && n()) {
            this.f4636c.postDelayed(new n(this, str, str2, 4), 5000L);
        }
    }

    public final void w() {
        this.f4639f = false;
        this.f4636c.removeCallbacks(this.f4645l);
        this.f4636c.removeCallbacks(this.f4647n);
        this.f4636c.removeCallbacks(this.f4648o);
        this.f4636c.removeCallbacks(this.f4644k);
        try {
            this.f4635b.quitSafely();
        } catch (Exception unused) {
        }
        if (n()) {
            try {
                if (this.f4641h) {
                    this.f4637d.removeSDKEventListener(String.valueOf(Process.myPid()));
                }
            } catch (Exception unused2) {
            }
            try {
                this.f4634a.unbindService(this.f4646m);
            } catch (Exception unused3) {
            }
        }
        this.f4637d = null;
        this.f4638e = false;
        this.f4641h = false;
        o4.c.c().f7937e.i(Boolean.FALSE);
    }
}
