package loader_forgemod;

import java.io.IOException;
import java.io.Reader;

public class m {
   private static final int a = 1000;
   private static final int b = 10;
   private static final int c = 1024;
   private final D<Object, Object> d;
   private Reader e;
   private char[] f;
   private int g;
   private int h;
   private int i;
   private int j;
   private int k;
   private int l;
   private StringBuilder m;
   private int n;
   private int o;

   public m(D<?, ?> var1) {
      if (var1 == null) {
         throw new NullPointerException("handler is null");
      }

      this.d = (D<Object, Object>)var1;
      var1.b = this;
   }

   public native void a(String var1);

   public native void b(Reader var1) throws IOException;

   public native void c(Reader var1, int var2) throws IOException;

   private native String m() throws IOException;

   native o y();
}
