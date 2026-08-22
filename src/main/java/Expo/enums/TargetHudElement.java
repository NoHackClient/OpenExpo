package Expo.enums;

import java.io.UnsupportedEncodingException;











public enum TargetHudElement {
   HEAD,
   NAME,
   HP,
   HEIGHT,
   ARROW,
   TEAM,
   DIST;

   private static final TargetHudElement[] s;
   private static boolean zkm$done;
   private static long[] zkm$v0;
   private static String[] zkm$v11;

   private static String a(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   static {
      TargetHudElement[] var10000 = new TargetHudElement[(int)zkm$g0()[1]];
      var10000[0] = HEAD;
      var10000[1] = NAME;
      var10000[2] = HP;
      var10000[3] = HEIGHT;
      var10000[4] = ARROW;
      var10000[5] = TEAM;
      var10000[(int)zkm$g0()[0]] = DIST;
      s = var10000;
   }

   private static void zkm$pre() {
      zkm$v11 = new String[]{"HEAD", "NAME", "HP", "ARROW", "TEAM", "DIST", "HEIGHT"};
      zkm$v0 = new long[]{-686763436679364602L, 2585077889896546311L, -4467426618234634234L};
   }

   private static long[] zkm$g0() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return zkm$v0;
   }

}
