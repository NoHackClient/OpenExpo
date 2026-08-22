package Expo.enums;

public enum DetectedCheat {
   AUTOBLOCK((int)zkm$g0()[0], zkm$g11()[3]),
   SCAFFOLD((int)zkm$g0()[1], zkm$g11()[2]),
   NOSLOW(5, zkm$g11()[0]);

   public final String colorFormatCode;
   private static final DetectedCheat[] L = new DetectedCheat[]{AUTOBLOCK, SCAFFOLD, NOSLOW};
   public final int FLAG_VL;

   DetectedCheat(int var3, String var4) {
      this.FLAG_VL = var3;
      this.colorFormatCode = var4;
   }

   private static long[] zkm$g0() {
      return new long[]{-459581626888749036L, 137313523247087626L};
   }

   private static String[] zkm$g11() {
      return new String[]{"\u00a79\u00a7l", "NOSLOW", "\u00a7b\u00a7l", "\u00a7c\u00a7l", "AUTOBLOCK", "SCAFFOLD"};
   }
}
