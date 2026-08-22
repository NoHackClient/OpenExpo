package Expo.enums;

import java.awt.Color;











public enum GradientPalette {
   RAINBOW(
      new Color(255, 0, 0),
      new Color(0, 255, 0),
      new Color(0, 0, 255)
   ),
   SUNSET(
      new Color(255, 94, 77),
      new Color(255, 149, 128),
      new Color(255, 195, 113)
   ),
   OCEAN(
      new Color(0, 105, 148),
      new Color(0, 168, 232),
      new Color(144, 224, 239)
   ),
   FIRE(
      new Color(255, 69, 0),
      new Color(255, 140, 0),
      new Color(255, 215, 0)
   ),
   ICE(
      new Color(180, 225, 255),
      new Color(120, 200, 255),
      new Color(70, 150, 255)
   ),
   PURPLE_DREAM(
      new Color(128, 0, 255),
      new Color(186, 85, 211),
      new Color(221, 160, 221)
   ),
   FOREST(
      new Color(34, 139, 34),
      new Color(60, 179, 113),
      new Color(144, 238, 144)
   ),
   CYBER(
      new Color(0, 255, 255),
      new Color(0, 150, 255),
      new Color(138, 43, 226)
   ),
   ROSE(
      new Color(255, 105, 180),
      new Color(255, 182, 193),
      new Color(255, 228, 225)
   ),
   GOLD(
      new Color(255, 215, 0),
      new Color(255, 193, 7),
      new Color(255, 160, 0)
   ),
   LAVA(
      new Color(128, 0, 0),
      new Color(220, 20, 60),
      new Color(255, 99, 71)
   ),
   NIGHT(
      new Color(25, 25, 112),
      new Color(72, 61, 139),
      new Color(138, 43, 226)
   ),
   AQUA(
      new Color(0, 255, 200),
      new Color(0, 180, 255),
      new Color(0, 100, 255)
   ),
   MAGMA(
      new Color(255, 50, 50),
      new Color(255, 120, 0),
      new Color(255, 200, 0)
   ),
   TOXIC(
      new Color(50, 255, 50),
      new Color(180, 255, 0),
      new Color(255, 255, 0)
   ),
   NEON_PINK(
      new Color(255, 40, 160),
      new Color(255, 90, 200),
      new Color(255, 160, 220)
   ),
   CYBER_BLUE(
      new Color(0, 255, 255),
      new Color(0, 170, 255),
      new Color(100, 0, 255)
   ),
   MATRIX(
      new Color(0, 255, 0),
      new Color(0, 180, 80),
      new Color(0, 120, 60)
   ),
   LASER(
      new Color(255, 0, 255),
      new Color(255, 0, 140),
      new Color(255, 80, 80)
   ),
   ULTRAVIOLET(
      new Color(120, 0, 255),
      new Color(180, 0, 255),
      new Color(255, 0, 200)
   ),
   PEACH(
      new Color(255, 180, 140),
      new Color(255, 210, 180),
      new Color(255, 235, 210)
   ),
   LAVENDER(
      new Color(180, 160, 255),
      new Color(200, 180, 255),
      new Color(230, 210, 255)
   ),
   MINT(
      new Color(120, 255, 200),
      new Color(170, 255, 220),
      new Color(210, 255, 240)
   ),
   SAND(
      new Color(210, 180, 140),
      new Color(230, 200, 160),
      new Color(255, 235, 190)
   ),
   OBSIDIAN(
      new Color(30, 30, 35),
      new Color(60, 60, 80),
      new Color(120, 80, 160)
   ),
   MIDNIGHT(
      new Color(10, 10, 30),
      new Color(30, 30, 90),
      new Color(80, 60, 180)
   ),
   CARBON(
      new Color(25, 25, 25),
      new Color(50, 50, 50),
      new Color(90, 90, 90)
   ),
   DEEP_SEA(
      new Color(0, 40, 60),
      new Color(0, 80, 120),
      new Color(0, 140, 200)
   ),
   BLOOD(
      new Color(120, 0, 0),
      new Color(180, 0, 0),
      new Color(255, 40, 40)
   ),
   ELECTRIC(
      new Color(0, 200, 255),
      new Color(0, 255, 255),
      new Color(180, 255, 255)
   ),
   SOLAR(
      new Color(255, 120, 0),
      new Color(255, 180, 0),
      new Color(255, 255, 120)
   );

   private final Color c;
   private final Color y;
   private final Color H;
   private static long a;

   public Color s() {
      return this.H;
   }

   public int A(double var1, double var3, long var5) {
      return J( var1, var3, this.H, this.y, this.c);
   }

   private static int T(Color var0, double var1, double var5) {
      long var7 = System.currentTimeMillis();
      double var9 = 2000.0 / var5;
      double var11 = (Math.sin((var7 + var1 * 50.0) / var9 * Math.PI * 2.0) + 1.0) / 2.0;
      double var13 = 0.75 + var11 * 0.25;
      int var15 = (int)Math.min(255.0, var0.getRed() * var13);
      int var16 = (int)Math.min(255.0, var0.getGreen() * var13);
      int var17 = (int)Math.min(255.0, var0.getBlue() * var13);
      return var15 << 16 | var16 << 8 | var17 | -16777216;
   }

   public String O() {
      return this.name();
   }


   public Color a() {
      return this.c;
   }

   static {
      GradientPalette[] var10000 = new GradientPalette[31];
      var10000[0] = RAINBOW;
      var10000[1] = SUNSET;
      var10000[2] = OCEAN;
      var10000[3] = FIRE;
      var10000[4] = ICE;
      var10000[5] = PURPLE_DREAM;
      var10000[6] = FOREST;
      var10000[7] = CYBER;
      var10000[8] = ROSE;
      var10000[9] = GOLD;
      var10000[10] = LAVA;
      var10000[11] = NIGHT;
      var10000[12] = AQUA;
      var10000[13] = MAGMA;
      var10000[14] = TOXIC;
      var10000[15] = NEON_PINK;
      var10000[16] = CYBER_BLUE;
      var10000[17] = MATRIX;
      var10000[18] = LASER;
      var10000[19] = ULTRAVIOLET;
      var10000[20] = PEACH;
      var10000[21] = LAVENDER;
      var10000[22] = MINT;
      var10000[23] = SAND;
      var10000[24] = OBSIDIAN;
      var10000[25] = MIDNIGHT;
      var10000[26] = CARBON;
      var10000[27] = DEEP_SEA;
      var10000[28] = BLOOD;
      var10000[29] = ELECTRIC;
      var10000[30] = SOLAR;
      a = 0L;
   }

   GradientPalette(Color var3, Color var4, Color var5) {
      this.H = var3;
      this.y = var4;
      this.c = var5;
   }

   public static int J( double var2, double var4, Color var6, Color var7, Color var8) {
      long var9 = System.currentTimeMillis();
      long var11 = (long)(2000.0 / var4);
      long var13 = (long)(var9 + var2 * 50.0);
      float var15 = (float)((double)(var13 % var11) / var11);
      Color var16;
      Color var17;
      float var18;
      if (var15 < 0.33333334F) {
         var16 = var6;
         var17 = var7;
         var18 = var15 * 3.0F;
      } else if (var15 < 0.6666667F) {
         var16 = var7;
         var17 = var8;
         var18 = (var15 - 0.33333334F) * 3.0F;
      } else {
         var16 = var8;
         var17 = var6;
         var18 = (var15 - 0.6666667F) * 3.0F;
      }

      int var19 = (int)(var16.getRed() + (var17.getRed() - var16.getRed()) * var18);
      int var20 = (int)(var16.getGreen() + (var17.getGreen() - var16.getGreen()) * var18);
      int var21 = (int)(var16.getBlue() + (var17.getBlue() - var16.getBlue()) * var18);
      return var19 << 16 | var20 << 8 | var21 | -16777216;
   }

   public int H(double var1, double var3, long var5) {
      return T(this.c, var1, var3);
   }


   public int M(double var1, long var3, double var5) {
      return T(this.y, var1, var5);
   }

   public static int R(double var0, double var2) {
      long var4 = System.currentTimeMillis();
      long var6 = (long)(2000.0 / var2);
      long var8 = (long)(var4 + var0 * 50.0);
      float var10 = (float)((double)(var8 % var6) / var6);
      return Color.HSBtoRGB(var10, 1.0F, 1.0F);
   }

   public int m(long var1, byte var3, double var4, double var6) {
      return T(this.H, var4, var6);
   }

   public Color p() {
      return this.y;
   }

   public static String[] N() {
      GradientPalette[] var0 = values();
      String[] var1 = new String[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2].name();
      }

      return var1;
   }

}
