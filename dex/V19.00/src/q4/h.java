package q4;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.p0;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: C:\Users\Administrator\Documents\Trae-CN\temp_20261118_01\dump_1900\dex_1_3779968.dex */
public final class h {

    /* renamed from: x, reason: collision with root package name */
    public static h f8464x;

    /* renamed from: a, reason: collision with root package name */
    public final Context f8465a;

    /* renamed from: n, reason: collision with root package name */
    public DatagramSocket f8478n;

    /* renamed from: o, reason: collision with root package name */
    public ServerSocket f8479o;

    /* renamed from: q, reason: collision with root package name */
    public g f8481q;

    /* renamed from: r, reason: collision with root package name */
    public String f8482r;

    /* renamed from: s, reason: collision with root package name */
    public Socket f8483s;

    /* renamed from: t, reason: collision with root package name */
    public DataOutputStream f8484t;

    /* renamed from: u, reason: collision with root package name */
    public String f8485u;

    /* renamed from: v, reason: collision with root package name */
    public Socket f8486v;

    /* renamed from: w, reason: collision with root package name */
    public DataOutputStream f8487w;

    /* renamed from: b, reason: collision with root package name */
    public final Handler f8466b = new Handler(Looper.getMainLooper());

    /* renamed from: c, reason: collision with root package name */
    public final ExecutorService f8467c = Executors.newFixedThreadPool(10);

    /* renamed from: d, reason: collision with root package name */
    public final Gson f8468d = new Gson();

    /* renamed from: e, reason: collision with root package name */
    public final CopyOnWriteArraySet f8469e = new CopyOnWriteArraySet();

    /* renamed from: f, reason: collision with root package name */
    public final CopyOnWriteArraySet f8470f = new CopyOnWriteArraySet();

    /* renamed from: g, reason: collision with root package name */
    public final HashSet f8471g = new HashSet();

    /* renamed from: h, reason: collision with root package name */
    public final HashSet f8472h = new HashSet();

    /* renamed from: i, reason: collision with root package name */
    public final HashSet f8473i = new HashSet();

    /* renamed from: j, reason: collision with root package name */
    public final ConcurrentHashMap f8474j = new ConcurrentHashMap();

    /* renamed from: k, reason: collision with root package name */
    public final ConcurrentHashMap f8475k = new ConcurrentHashMap();

    /* renamed from: l, reason: collision with root package name */
    public final Object f8476l = new Object();

    /* renamed from: m, reason: collision with root package name */
    public final Object f8477m = new Object();

    /* renamed from: p, reason: collision with root package name */
    public volatile boolean f8480p = false;

    public h(Context context) {
        this.f8465a = context.getApplicationContext();
    }

    public static Socket d(String str) {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(str, 34569), 3000);
        socket.setSoTimeout(3000);
        return socket;
    }

    public static ArrayList f() {
        InetAddress inetAddress;
        byte[] address;
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.getName().contains("wlan") && !nextElement.isLoopback() && nextElement.isUp()) {
                    for (InterfaceAddress interfaceAddress : nextElement.getInterfaceAddresses()) {
                        InetAddress broadcast = interfaceAddress.getBroadcast();
                        if ((interfaceAddress.getAddress() instanceof Inet4Address) && broadcast == null) {
                            try {
                                address = interfaceAddress.getAddress().getAddress();
                            } catch (Exception unused) {
                            }
                            if (address.length == 4) {
                                address[3] = -1;
                                inetAddress = InetAddress.getByAddress(address);
                                broadcast = inetAddress;
                            }
                            inetAddress = null;
                            broadcast = inetAddress;
                        }
                        if (broadcast != null) {
                            arrayList.add(broadcast);
                        }
                    }
                }
            }
        } catch (Exception unused2) {
        }
        return arrayList;
    }

    public static synchronized h g(Context context) {
        h hVar;
        synchronized (h.class) {
            try {
                if (f8464x == null) {
                    f8464x = new h(context);
                }
                hVar = f8464x;
            } catch (Throwable th) {
                throw th;
            }
        }
        return hVar;
    }

    public static String n(String str) {
        if ("hud_hello".equals(str) || "hud_connect".equals(str) || "hud_accept".equals(str)) {
            return "hud";
        }
        if ("hello".equals(str) || "connect".equals(str) || "accept".equals(str)) {
            return "general";
        }
        return null;
    }

    public final synchronized void a(String str) {
        try {
            HashSet hashSet = this.f8471g;
            if (str == null) {
                str = "default";
            }
            hashSet.add(str);
            if (!this.f8480p) {
                o();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void b(DatagramSocket datagramSocket, String str) {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("cmd", "hud".equals(str) ? "hud_hello" : "hello");
            if ("hud".equals(str)) {
                jsonObject.addProperty("service", "hud");
            }
            jsonObject.addProperty("name", Build.MODEL);
            byte[] bytes = this.f8468d.toJson((JsonElement) jsonObject).getBytes();
            ArrayList f7 = f();
            f7.isEmpty();
            if ("hud".equals(str)) {
                f7.size();
            }
            Iterator it = f7.iterator();
            while (it.hasNext()) {
                InetAddress inetAddress = (InetAddress) it.next();
                if (datagramSocket.isClosed()) {
                    return;
                }
                try {
                    datagramSocket.send(new DatagramPacket(bytes, bytes.length, inetAddress, 34568));
                } catch (Exception unused) {
                    inetAddress.getHostAddress();
                }
            }
        } catch (Exception unused2) {
        }
    }

    public final void c(String str) {
        if ("hud".equals(str)) {
            DataOutputStream dataOutputStream = this.f8487w;
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException unused) {
                }
            }
            this.f8487w = null;
            Socket socket = this.f8486v;
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException unused2) {
                }
            }
            this.f8486v = null;
            return;
        }
        DataOutputStream dataOutputStream2 = this.f8484t;
        if (dataOutputStream2 != null) {
            try {
                dataOutputStream2.close();
            } catch (IOException unused3) {
            }
        }
        this.f8484t = null;
        Socket socket2 = this.f8483s;
        if (socket2 != null) {
            try {
                socket2.close();
            } catch (IOException unused4) {
            }
        }
        this.f8483s = null;
    }

    public final DataOutputStream e(String str, String str2) {
        if ("hud".equals(str2)) {
            Socket socket = this.f8486v;
            if (socket == null || socket.isClosed()) {
                Socket d7 = d(str);
                this.f8486v = d7;
                d7.setSoTimeout(0);
                this.f8487w = new DataOutputStream(new BufferedOutputStream(this.f8486v.getOutputStream()));
            }
            return this.f8487w;
        }
        Socket socket2 = this.f8483s;
        if (socket2 == null || socket2.isClosed()) {
            Socket d8 = d(str);
            this.f8483s = d8;
            d8.setSoTimeout(0);
            this.f8484t = new DataOutputStream(new BufferedOutputStream(this.f8483s.getOutputStream()));
        }
        return this.f8484t;
    }

    public final void h(String str, String str2) {
        JsonObject asJsonObject;
        String n6;
        boolean add;
        boolean add2;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if ((nextElement instanceof Inet4Address) && nextElement.getHostAddress().equals(str2)) {
                        return;
                    }
                }
            }
        } catch (Exception unused) {
        }
        try {
            asJsonObject = JsonParser.parseString(str).getAsJsonObject();
            n6 = n(asJsonObject.get("cmd").getAsString());
        } catch (Exception unused2) {
        }
        if (n6 == null) {
            return;
        }
        String asString = asJsonObject.has("name") ? asJsonObject.get("name").getAsString() : "Unknown";
        int i7 = 3;
        if ("hud".equals(n6)) {
            this.f8475k.put(str2, Long.valueOf(System.currentTimeMillis()));
            synchronized (this.f8473i) {
                add2 = this.f8473i.add(str2);
            }
            if (add2) {
                this.f8467c.execute(new d(this, str2, "hud", i7));
                return;
            }
            return;
        }
        this.f8474j.put(str2, Long.valueOf(System.currentTimeMillis()));
        this.f8466b.post(new d(this, str2, asString, 1));
        synchronized (this.f8472h) {
            add = this.f8472h.add(str2);
        }
        if (add) {
            this.f8466b.post(new d(this, str2, asString, 0));
            this.f8467c.execute(new d(this, str2, "general", i7));
        }
    }

    public final void i(String str, String str2) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        if ("hud".equals(str2)) {
            if (str.equals(this.f8485u)) {
                this.f8485u = null;
            }
            c("hud");
            synchronized (this.f8473i) {
                this.f8473i.remove(str);
            }
            this.f8475k.remove(str);
            return;
        }
        if (str.equals(this.f8482r)) {
            this.f8482r = null;
        }
        c("general");
        synchronized (this.f8472h) {
            this.f8472h.remove(str);
        }
        this.f8474j.remove(str);
        this.f8466b.post(new d(this, "general", str, 2));
    }

    public final void j(String str) {
        this.f8466b.post(new p0(19, this, str));
    }

    public final void k(long j7, String str) {
        ConcurrentHashMap concurrentHashMap = "hud".equals(str) ? this.f8475k : this.f8474j;
        for (Map.Entry entry : concurrentHashMap.entrySet()) {
            Long l7 = (Long) entry.getValue();
            if (l7 != null && j7 - l7.longValue() > 12000) {
                String str2 = (String) entry.getKey();
                concurrentHashMap.remove(str2);
                int i7 = 2;
                if ("hud".equals(str)) {
                    synchronized (this.f8473i) {
                        this.f8473i.remove(str2);
                    }
                    if (str2.equals(this.f8485u)) {
                        this.f8485u = null;
                        c("hud");
                        this.f8466b.post(new d(this, "hud", str2, i7));
                    }
                } else {
                    synchronized (this.f8472h) {
                        this.f8472h.remove(str2);
                    }
                    if (str2.equals(this.f8482r)) {
                        this.f8482r = null;
                        c("general");
                        this.f8466b.post(new d(this, "general", str2, i7));
                    }
                }
            }
        }
    }

    public final void l(Socket socket, DataInputStream dataInputStream, String str, long j7, String str2) {
        Handler handler;
        int read;
        File file = new File(Environment.getExternalStorageDirectory(), "navitool/filemanager");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                socket.setSoTimeout(0);
                int i7 = 8192;
                byte[] bArr = new byte[8192];
                long j8 = 0;
                while (true) {
                    handler = this.f8466b;
                    if (j8 >= j7 || (read = dataInputStream.read(bArr, 0, (int) Math.min(i7, j7 - j8))) == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    j8 += read;
                    if ("general".equals(str2)) {
                        handler.post(new h1.a(this, str, (int) ((100 * j8) / j7), 5));
                    }
                    i7 = 8192;
                }
                int i8 = 1;
                MediaScannerConnection.scanFile(this.f8465a, new String[]{file2.getAbsolutePath()}, null, new e());
                if ("general".equals(str2)) {
                    handler.post(new c(this, str, file2, i8));
                }
                fileOutputStream.close();
            } finally {
            }
        } catch (IOException e7) {
            if ("general".equals(str2)) {
                j("Receive file error: " + e7.getMessage());
            }
        }
    }

    public final synchronized void m(String str) {
        try {
            HashSet hashSet = this.f8471g;
            if (str == null) {
                str = "default";
            }
            hashSet.remove(str);
            if (this.f8471g.isEmpty() && this.f8480p) {
                p();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void o() {
        this.f8472h.clear();
        this.f8473i.clear();
        this.f8474j.clear();
        this.f8475k.clear();
        this.f8482r = null;
        this.f8485u = null;
        c("general");
        c("hud");
        try {
            final DatagramSocket datagramSocket = new DatagramSocket((SocketAddress) null);
            final int i7 = 1;
            datagramSocket.setReuseAddress(true);
            datagramSocket.bind(new InetSocketAddress(34568));
            datagramSocket.setBroadcast(true);
            try {
                ServerSocket serverSocket = new ServerSocket();
                serverSocket.setReuseAddress(true);
                serverSocket.bind(new InetSocketAddress(34569));
                serverSocket.setSoTimeout(3000);
                this.f8478n = datagramSocket;
                this.f8479o = serverSocket;
                this.f8480p = true;
                final int i8 = 0;
                Runnable runnable = new Runnable(this) { // from class: q4.b

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ h f8454b;

                    {
                        this.f8454b = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i8) {
                            case 0:
                                h hVar = this.f8454b;
                                DatagramSocket datagramSocket2 = datagramSocket;
                                byte[] bArr = new byte[1024];
                                while (hVar.f8480p && !datagramSocket2.isClosed()) {
                                    try {
                                        DatagramPacket datagramPacket = new DatagramPacket(bArr, 1024);
                                        datagramSocket2.receive(datagramPacket);
                                        hVar.h(new String(datagramPacket.getData(), 0, datagramPacket.getLength()), datagramPacket.getAddress().getHostAddress());
                                    } catch (IOException unused) {
                                        if (hVar.f8480p) {
                                            datagramSocket2.isClosed();
                                        }
                                    }
                                }
                            default:
                                h hVar2 = this.f8454b;
                                DatagramSocket datagramSocket3 = datagramSocket;
                                while (hVar2.f8480p && !datagramSocket3.isClosed()) {
                                    hVar2.b(datagramSocket3, "general");
                                    hVar2.b(datagramSocket3, "hud");
                                    long currentTimeMillis = System.currentTimeMillis();
                                    hVar2.k(currentTimeMillis, "general");
                                    hVar2.k(currentTimeMillis, "hud");
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException unused2) {
                                        Thread.currentThread().interrupt();
                                    }
                                }
                        }
                    }
                };
                ExecutorService executorService = this.f8467c;
                executorService.execute(runnable);
                executorService.execute(new Runnable(this) { // from class: q4.b

                    /* renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ h f8454b;

                    {
                        this.f8454b = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i7) {
                            case 0:
                                h hVar = this.f8454b;
                                DatagramSocket datagramSocket2 = datagramSocket;
                                byte[] bArr = new byte[1024];
                                while (hVar.f8480p && !datagramSocket2.isClosed()) {
                                    try {
                                        DatagramPacket datagramPacket = new DatagramPacket(bArr, 1024);
                                        datagramSocket2.receive(datagramPacket);
                                        hVar.h(new String(datagramPacket.getData(), 0, datagramPacket.getLength()), datagramPacket.getAddress().getHostAddress());
                                    } catch (IOException unused) {
                                        if (hVar.f8480p) {
                                            datagramSocket2.isClosed();
                                        }
                                    }
                                }
                            default:
                                h hVar2 = this.f8454b;
                                DatagramSocket datagramSocket3 = datagramSocket;
                                while (hVar2.f8480p && !datagramSocket3.isClosed()) {
                                    hVar2.b(datagramSocket3, "general");
                                    hVar2.b(datagramSocket3, "hud");
                                    long currentTimeMillis = System.currentTimeMillis();
                                    hVar2.k(currentTimeMillis, "general");
                                    hVar2.k(currentTimeMillis, "hud");
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException unused2) {
                                        Thread.currentThread().interrupt();
                                    }
                                }
                        }
                    }
                });
                this.f8467c.execute(new p0(20, this, serverSocket));
            } catch (Exception e7) {
                try {
                    datagramSocket.close();
                } catch (Exception unused) {
                }
                j("TCP Error: " + e7.getMessage());
            }
        } catch (Exception e8) {
            j("UDP Error: " + e8.getMessage());
        }
    }

    public final void p() {
        this.f8480p = false;
        this.f8472h.clear();
        this.f8473i.clear();
        this.f8474j.clear();
        this.f8475k.clear();
        this.f8482r = null;
        this.f8485u = null;
        DatagramSocket datagramSocket = this.f8478n;
        if (datagramSocket != null) {
            datagramSocket.close();
            this.f8478n = null;
        }
        ServerSocket serverSocket = this.f8479o;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException unused) {
            }
            this.f8479o = null;
        }
        c("general");
        c("hud");
    }
}
