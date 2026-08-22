package loader_forgemod;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class g_2 extends n implements Iterable<H> {
   private final List<String> b;
   private final List<n> c;
   private transient v d;

   public g_2() {
      this.b = new ArrayList<>();
      this.c = new ArrayList<>();
      this.d = new v();
   }

   public g_2(g_2 var1) {
      this(var1, false);
   }

   private g_2(g_2 var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("object is null");
      }

      if (var2) {
         this.b = Collections.unmodifiableList(var1.b);
         this.c = Collections.unmodifiableList(var1.c);
      } else {
         this.b = new ArrayList<>(var1.b);
         this.c = new ArrayList<>(var1.c);
      }

      this.d = new v();
      this.N();
   }

   @Deprecated
   public static native g_2 i(Reader var0) throws IOException;

   @Deprecated
   public static native g_2 j(String var0);

   public static native g_2 k(g_2 var0);

   public native g_2 l(String var1, int var2);

   public native g_2 m(String var1, long var2);

   public native g_2 n(String var1, float var2);

   public native g_2 o(String var1, double var2);

   public native g_2 p(String var1, boolean var2);

   public native g_2 q(String var1, String var2);

   public native g_2 r(String var1, n var2);

   public native g_2 s(String var1, int var2);

   public native g_2 t(String var1, long var2);

   public native g_2 u(String var1, float var2);

   public native g_2 v(String var1, double var2);

   public native g_2 w(String var1, boolean var2);

   public native g_2 x(String var1, String var2);

   public native g_2 y(String var1, n var2);

   public native g_2 z(String var1);

   public native boolean A(String var1);

   public native g_2 B(g_2 var1);

   public native n C(String var1);

   public native int D(String var1, int var2);

   public native long E(String var1, long var2);

   public native float F(String var1, float var2);

   public native double G(String var1, double var2);

   public native boolean H(String var1, boolean var2);

   public native String I(String var1, String var2);

   public native int size();

   public native boolean isEmpty();

   public native List<String> J();

   public native Iterator<H> iterator();

   native void a(J var1) throws IOException;

   public native boolean K();

   public native g_2 L();

   public native int hashCode();

   public native boolean equals(Object var1);

   native int M(String var1);

   private synchronized native void readObject(ObjectInputStream var1) throws IOException, ClassNotFoundException;

   private native void N();
}
