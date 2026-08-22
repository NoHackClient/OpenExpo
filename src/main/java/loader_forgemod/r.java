package loader_forgemod;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class r extends n implements Iterable<n> {
   private final List<n> c;

   public r() {
      this.c = new ArrayList<>();
   }

   public r(r var1) {
      this(var1, false);
   }

   private r(r var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("array is null");
      }

      if (var2) {
         this.c = Collections.unmodifiableList(var1.c);
      } else {
         this.c = new ArrayList<>(var1.c);
      }
   }

   @Deprecated
   public static native r ag(Reader var0) throws IOException;

   @Deprecated
   public static native r ah(String var0);

   public static native r ai(r var0);

   public native r aj(int var1);

   public native r ak(long var1);

   public native r al(float var1);

   public native r am(double var1);

   public native r an(boolean var1);

   public native r ao(String var1);

   public native r ap(n var1);

   public native r aq(int var1, int var2);

   public native r ar(int var1, long var2);

   public native r as(int var1, float var2);

   public native r at(int var1, double var2);

   public native r au(int var1, boolean var2);

   public native r av(int var1, String var2);

   public native r aw(int var1, n var2);

   public native r ax(int var1);

   public native int size();

   public native boolean isEmpty();

   public native n ay(int var1);

   public native List<n> az();

   public native Iterator<n> iterator();

   native void a(J var1) throws IOException;

   public native boolean W();

   public native r ab();

   public native int hashCode();

   public native boolean equals(Object var1);
}
