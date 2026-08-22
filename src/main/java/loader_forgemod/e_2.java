package loader_forgemod;

import java.io.IOException;











class e_2 extends n {
   private final String a;

   e_2(String var1) {
      if (var1 == null) {
         throw new NullPointerException("string is null");
      }

      this.a = var1;
   }

   native void a(J var1) throws IOException;

   public native boolean b();

   public native String c();

   public native int hashCode();

   public native boolean equals(Object var1);
}
