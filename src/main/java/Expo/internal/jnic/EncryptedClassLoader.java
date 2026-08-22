package Expo.internal.jnic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.JarFile;
import org.apache.logging.log4j.Logger;


public class EncryptedClassLoader {
   private static Map g;
   private static Logger n;
   private static Map d;
   private static AtomicBoolean A;
   private static String[] b;
   private static long a;
   private static long[] e;
   private static volatile int p;
   private static String[] c;

   public static native void p(long var0);

   private static native Class r(EncryptedClassEntry var0, Map var1, ClassLoader var2, Set var3, Set var4, Set var5, List var6, long var7);

   private static native boolean p(long var0, String var2);

   private static native String a(int var0, long var1);

   private static native boolean r(long var0, String var2);

   static native Set s(byte[] var0, long var1);
}
