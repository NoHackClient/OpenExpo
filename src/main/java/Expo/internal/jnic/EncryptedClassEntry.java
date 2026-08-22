package Expo.internal.jnic;

import Expo.internal.synthetic.EncryptedClassEntryCtorMarker;
import java.util.Set;


final class EncryptedClassEntry {
   private final Set<String> n;
   private final String b;
   private static final long a = 44251458668485L;
   private final boolean Q;
   private final byte[] T;

   private EncryptedClassEntry(String var1, int var2, int var3, byte[] var4, int var5, boolean var6) {
      long var7 = ((long)var2 << 32 | (long)var3 << 48 >>> 32 | (long)var5 << 48 >>> 48) ^ a;
      this.b = var1;
      this.T = var4;
      this.Q = var6;
      this.n = EncryptedClassLoader.s(var4,0L);
   }

   static boolean p(EncryptedClassEntry var0) {
      return var0.Q;
   }

   static Set s(EncryptedClassEntry var0) {
      return var0.n;
   }

   static String v(EncryptedClassEntry var0) {
      return var0.b;
   }

   static byte[] Z(EncryptedClassEntry var0) {
      return var0.T;
   }

   EncryptedClassEntry(String var1, byte[] var2, boolean var3, long var4, EncryptedClassEntryCtorMarker var6) {
      this(
         var1,
         (int)(((a ^ var4) ^ 16325601946567L) >>> 32),
         (int)(((a ^ var4) ^ 16325601946567L) << 32 >>> 48),
         var2,
         (int)(((a ^ var4) ^ 16325601946567L) << 48 >>> 48),
         var3
      );
      var4 = a ^ var4;
   }
}
