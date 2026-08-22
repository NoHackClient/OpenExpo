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
