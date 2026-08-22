package Expo.util.render;

import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import Expo.util.Pair;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;











public class FontGlyphPage {
   private FontGlyphPage h;
   private final GlyphTexture x;
   private static String[] f;
   private static String[] b;
   private final GlyphTexture q;
   private static int[] T;
   private static long[] i;
   private final Font S;
   private final Minecraft X;
   private static String d;
   private static final float e = 8.2F;
   private static Map k;
   private static Integer[] j;
   private int c;
   private final List<String> F;
   private static Map g;
   private static long a;

   private float q(byte var1, char var2, int var3, int var4) {
      long var5 = ((long)var1 << 56 | (long)var3 << 32 >>> 8 | (long)var4 << 40 >>> 40) ^ a;
      int var7 = (int)((var5 ^ 71486216368056L) >>> 48);
      int var8 = (int)((var5 ^ 71486216368056L) << 16 >>> 32);
      if (var2 == 167 || var2 == 167) {
         return -1.0F;
      } else if (var2 == 32) {
         return 2.0F;
      } else if (this.Z((short)var7, this.q, var2, var8)) {
         return this.getStringWidth(var2,0L);
      } else {
         int var12 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(var2);
         if (var2 > 0 && var12 != -1) {
            return Glyph.l(GlyphTexture.t(this.q)[var12]) / 2.0F - 4.0F;
         } else if (var2 < GlyphTexture.t(this.q).length && Glyph.l(GlyphTexture.t(this.q)[var2]) / 2.0F - 4.0F != 0.0F) {
            int var13 = (int)(Glyph.l(GlyphTexture.t(this.q)[var2]) / 2.0F - 4.0F) >>> 4;
            int var14 = (int)(Glyph.l(GlyphTexture.t(this.q)[var2]) / 2.0F - 4.0F) & 15;
            var13 &= 15;
            var14++;
            return (var14 - var13) / 2 + 1;
         } else {
            return 0.0F;
         }
      }
   }

   public void o(String var1, long var2, float var4, float var5, int var6) {
      long var9 = var2 ^ 100922889475041L;
      this.x(var1, var4 - this.S(var1,0L) / 2.0F, var9, var5, var6);
   }

   public void F(String var1, double var2, float var4, int var5, long var6) {
      long var8 = var6 ^ 39076840947170L;
      this.N(var1, var2 + 0.5, var4 + 0.5F, var5, true, 8.2F, true, var8);
      this.N(var1, var2, var4, var5, false, 8.2F, true, var8);
   }

   public int e(long var1) {
      return (this.c - 8) / 2;
   }

   public void P(String var1, double var2, float var4, long var5, int var7) {
      long var8 = 92918018324186L;
      this.N(var1, var2, var4, var7, false, 8.2F, true, var8);
   }

   private double t(char var1, double var2, double var4, double var6, int var8, boolean var9, Integer var10, GlyphDrawer var11, GlyphTexture var12, long var13) {
      long var19 = var13 ^ 89627481167212L;
      String var21 = String.valueOf(var1);
      int var22 = this.getStringWidth(var1,0L);
      int var23;
      if (var11 != null) {
         float var24 = (float)((var2 - var6) / 2.0);
         var23 = var11.s(var1, var24, var22, var10);
         if (var9) {
            var23 = (var23 & 16579836) >> 2 | var23 & -16777216;
         }
      } else {
         var23 = var10 == null ? var8 : this.C( var10, var8);
      }

      GlStateManager.pushMatrix();
      GlStateManager.scale(2.0F, 2.0F, 1.0F);
      this.X.fontRendererObj.drawString(var21, (float)(var2 * 0.5 + 1.0), (float)(var4 * 0.5 + 3.0), var23, false);
      GlStateManager.popMatrix();
      GlStateManager.bindTexture(GlyphTexture.x(var12).getGlTextureId());
      if (var11 == null) {
         RenderUtil.R(var23, var19);
      }

      return var22 * 2.0;
   }

   public void r(String var1, long var2, float var4, float var5, Color var6) {
      long var7 = var2 ^ 60359793139674L;
      this.x(var1, var4, var7, var5, var6.getRGB());
   }

   private boolean Z(short var1, GlyphTexture var2, char var3, int var4) {
      return this.l(0L, GlyphTexture.t(var2), var3);
   }

   public int l(String var1, float var2, long var3, float var5, int var6) {
      long var7 = var3 ^ 124077717714411L;
      return this.V(var1, var2 - this.S(var1,0L) / 2.0F, var7, var5, var6);
   }

   public float Q(String var1, float var2, long var3, float var5, int var6, float var7, float var8) {
      var3 = a ^ var3;
      long var9 = var3 ^ 15781317412478L;
      long var11 = var3 ^ 106160638319010L;
      this.y(var1, var2, var11, var7);
      float var15 = var5;

      for (String var17 : this.F) {
         RenderUtil.X();
         this.V(var17, var2, var9, var15, var6);
         var15 += this.e(0L) + var8;
      }

      return var15 - var5;
   }

   private int r(int var1, long var2) {
      int var4 = var1 >> 24 & 255;
      return var4 == 0 ? var1 | -16777216 : var1;
   }

   public FontGlyphPage u() {
      return this.h;
   }

   public void O(double var1, double var3, GlyphTexture var5, boolean var6, boolean var9, char var10) {
      GL11.glBegin(4);
      Glyph var13 = GlyphTexture.t(var5)[var10];
      this.z(
         (float)var1,
         (float)var3,
         Glyph.l(var13),
         Glyph.V(var13),
         Glyph.d(var13),
         Glyph.z(var13),
         ((Integer)GlyphTexture.o(var5).a()).intValue(),
         ((Integer)GlyphTexture.o(var5).p()).intValue()
      );
      GL11.glEnd();
      if (var6) {
         this.J(var1, var3 + Glyph.V(var13) / 2, var1 + Glyph.l(var13) - 8.0, var3 + Glyph.V(var13) / 2);
      }

      if (var9) {
         this.J(var1 + 2.5, var3 + Glyph.V(var13) - 1.0, var1 + Glyph.l(var13) - 6.0, var3 + Glyph.V(var13) - 1.0);
      }
   }

   public float N(String var1, double var2, double var4, int var6, boolean var7, float var8, boolean var9, long var10) {
      var10 = a ^ var10;
      int var12 = (int)((var10 ^ 7427245434618L) >>> 48);
      int var13 = (int)((var10 ^ 7427245434618L) << 16 >>> 32);
      int var14 = (int)((var10 ^ 7427245434618L) << 48 >>> 48);
      return this.V(var1, var2, var4, var6, var7, (short)var12, var8, var9, var13, (char)var14, null);
   }

   public void j(String var1, double var2, long var4, float var6, int var7, boolean var8, GlyphDrawer var9) {





      if (var8) {
         this.V(var1, var2 + 0.5, var6 + 0.5F, var7, true, (short)0, 8.2F, true, 1908211717, (char)34487, var9);
      }

      this.V(var1, var2, var6, var7, false, (short)0, 8.2F, true, 1908211717, (char)34487, var9);
   }



   private void v(long var1, GlyphTexture var3, Font var4, Graphics2D var5, boolean var6) {


      int var9 = 0;
      int var10 = 0;
      int var11 = 1;
      int var12 = 0;
      FontMetrics var13 = var5.getFontMetrics();
      if (var6) {
         BufferedImage var22 = new BufferedImage((Integer)GlyphTexture.o(var3).a(), (Integer)GlyphTexture.o(var3).p(), 2);
         Graphics2D var23 = (Graphics2D)var22.getGraphics();
         var23.setFont(var4);
         var23.setColor(new Color(255, 255, 255, 0));
         var23.fillRect(0, 0, (Integer)GlyphTexture.o(var3).a(), (Integer)GlyphTexture.o(var3).p());
         var23.setColor(Color.WHITE);
         var23.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
         var23.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         var23.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

         for (Glyph var19 : GlyphTexture.t(var3)) {
            char var20 = (char)var12;
            var23.drawString(String.valueOf(var20), Glyph.d(var19) + 2, Glyph.z(var19) + var13.getAscent());
            var12++;
         }

         GlyphTexture.d(var3, new DynamicTexture(var22));
      } else {
         while (var12 < GlyphTexture.t(var3).length) {
            char var14 = (char)var12;
            Glyph var15 = new Glyph(null);
            Rectangle2D var16 = var13.getStringBounds(String.valueOf(var14), var5);
            Glyph.d(var15, var16.getBounds().width + 8.2F);
            Glyph.m(var15, var16.getBounds().height);
            if (var10 + Glyph.l(var15) >= ((Integer)GlyphTexture.o(var3).a()).intValue()) {
               var10 = 0;
               var11 += var9;
               var9 = 0;
            }

            if (Glyph.V(var15) > var9) {
               var9 = Glyph.V(var15);
            }

            Glyph.U(var15, var10);
            Glyph.T(var15, var11);
            if (Glyph.V(var15) > this.c) {
               this.c = Glyph.V(var15);
            }

            GlyphTexture.t(var3)[var12] = var15;
            var10 = (int)(var10 + Glyph.l(var15));
            GlyphTexture.x(var3, Pair.p(GlyphTexture.o(var3).a(), var11 + var13.getAscent()));
            var12++;
         }

         this.v(9307747062484L, var3, var4, var5, true);
      }
   }

   private void a(String var1, float var2) {
      this.F.clear();
      this.F.addAll(Arrays.asList(var1.trim().split("\n")));
   }

   public boolean F() {
      return this.h != null;
   }

   public float V(String var1, double var2, double var4, int var6, boolean var7, short var8, float var9, boolean var10, int var11, char var12, GlyphDrawer var13) {
      long var14 = ((long)var8 << 48 | (long)var11 << 32 >>> 16 | (long)var12 << 48 >>> 48) ^ a;
      long var20 = var14 ^ 104486217488844L;
      long var22 = var14 ^ 58600889761046L;
      if (var1 == null) {
         return 0.0F;
      }

      int var24 = this.r(var6,0L);
      if (var7) {
         var24 = (var24 & 16579836) >> 2 | var24 & -16777216;
      }

      GlStateManager.pushMatrix();
      GlStateManager.scale(0.5, 0.5, 0.5);
      RenderUtil.U(0L);
      RenderUtil.X();
      RenderUtil.R(var24, var22);
      GlStateManager.enableTexture2D();
      GlStateManager.bindTexture(GlyphTexture.x(this.q).getGlTextureId());
      if (var10) {
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
      } else {
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
      }

      float var25 = this.F(var1, var2, var4, var24, var7, var13, var20);
      GL11.glHint(3155, 4352);
      GlStateManager.popMatrix();
      RenderUtil.X();
      RenderUtil.G();
      GlStateManager.bindTexture(0);
      return var25;
   }

   public String G(String var1, int var2, boolean var3, byte var4, long var5) {
      long var7 = ((long)var4 << 56 | var5 << 8 >>> 8) ^ a;
      int var9 = (int)((var7 ^ 9053739995914L) >>> 56);
      int var10 = (int)((var7 ^ 9053739995914L) << 8 >>> 32);
      int var11 = (int)((var7 ^ 9053739995914L) << 40 >>> 40);
      if (var1 == null) {
         return "";
      }

      StringBuilder var12 = new StringBuilder();
      float var13 = 0.0F;
      int var14 = var3 ? var1.length() - 1 : 0;
      int var15 = var3 ? -1 : 1;
      boolean var16 = false;
      boolean var17 = false;

      for (int var18 = var14; var18 >= 0 && var18 < var1.length() && var13 < var2; var18 += var15) {
         char var19 = var1.charAt(var18);
         float var20 = this.q((byte)var9, var19, var10, var11);
         if (var16) {
            if (var19 == 108 || var19 == 76) {
               var17 = true;
            } else if (var19 == 114 || var19 == 82) {
               var17 = false;
            }
         } else if (var20 < 0.0F) {
         } else {
            var13 += var20;
            if (var17) {
               var13++;
            }
         }

         if (var13 > var2) {
            break;
         }

         if (var3) {
            var12.insert(0, var19);
         } else {
            var12.append(var19);
         }
      }

      return var12.toString();
   }

   public float x(float var1, long var2) {
      return var1 / 2.0F - this.e(0L) / 2.0F;
   }

   public List C(String var1, float var2, float var3, float var4, long var5) {
      long var7 = var5 ^ 24565957080472L;
      this.y(var1, var2, var7, var3);
      return this.F;
   }

   public void V(char var1, int var2, String var3, char var4, float var5, float var6, Color var7) {

      this.a(var3, var5, var6, var7.getRGB(), 84933894394513L, false);
   }

   private int C( int var3, int var4) {
      return var4 & -16777216 | var3 & 16777215;
   }

   public void F(String var1, float var2, float var3, Color var4, long var5) {
      long var7 = var5 ^ 69176062704921L;
      this.l(var1, var2, var7, var3, var4.getRGB());
   }

   public int a(String var1, float var2, float var3, int var4, long var5, boolean var7) {
      var5 = a ^ var5;
      long var8 = var5 ^ 41340294649110L;
      long var10 = var5 ^ 95386951378791L;
      return var7 ? this.x(var1, var2, var10, var3, var4) : (int)this.N(var1, var2, var3, var4, false, 8.2F, false, var8);
   }

   private float F(String var1, double var2, double var4, int var7, boolean var8, GlyphDrawer var9, long var10) {
      var10 = a ^ var10;
      int var18 = (int)((var10 ^ 132472550121097L) >>> 48);
      int var19 = (int)((var10 ^ 132472550121097L) << 16 >>> 32);
      long var23 = var10 ^ 26602621970209L;
      long var25 = var10 ^ 81045529064013L;
      var2 = (var2 - 1.0) * 2.0;
      var4 = (var4 - 3.0) * 2.0;
      double var27 = var2;
      GlyphTexture var29 = this.q;
      int var30 = this.r(var7,0L);
      float var31 = (var30 >> 24 & 255) / 255.0F;
      Integer var32 = null;
      boolean var33 = false;
      boolean var34 = false;
      boolean var35 = false;
      boolean var36 = false;

      for (int var37 = 0; var37 < var1.length(); var37++) {
         char var38 = var1.charAt(var37);
         if (var38 == 167) {
            int var39 = 21;

            try {
               if (var37 + 1 < var1.length()) {
                  char var40 = var1.charAt(var37 + 1);
                  var39 = this.Z(var40,0L);
               }
            } catch (Exception var42) {
               Expo.internal.restore.ExpoDiag.attribute(var42, "FontGlyphPage.F/8#0");
            }

            if (var39 >= 0 && var39 <= 15) {
               var33 = false;
               var34 = false;
               var36 = false;
               var35 = false;
               GlStateManager.bindTexture(GlyphTexture.x(this.q).getGlTextureId());
               var29 = this.q;
               if (var8) {
                  var39 += 16;
               }

               var32 = T[var39];
               RenderUtil.O(var32, var31,0L);
            } else {
               switch (var39) {
                  case 17:
                     if (this.F()) {
                        var33 = true;
                        if (var34) {
                           GlStateManager.bindTexture(GlyphTexture.x(this.h.x).getGlTextureId());
                           var29 = this.h.x;
                        } else {
                           GlStateManager.bindTexture(GlyphTexture.x(this.h.q).getGlTextureId());
                           var29 = this.h.q;
                        }
                     }
                     break;
                  case 18:
                     var35 = true;
                     break;
                  case 19:
                     var36 = true;
                     break;
                  case 20:
                     if (var33 && this.F()) {
                        GlStateManager.bindTexture(GlyphTexture.x(this.h.x).getGlTextureId());
                        var29 = this.h.x;
                        break;
                     }

                     GlStateManager.bindTexture(GlyphTexture.x(this.x).getGlTextureId());
                     var29 = this.x;
                     break;
                  case 21:
                  default:
                     var36 = false;
                     var35 = false;
                     var32 = null;
                     RenderUtil.R(this.r(var7,0L), var25);
                     GlStateManager.bindTexture(GlyphTexture.x(this.q).getGlTextureId());
                     var29 = this.q;
               }
            }

            var37++;
         } else if (this.Z((short)var18, var29, var38, var19)) {
            var2 += this.t(var38, var2, var4, var27, var30, var8, var32, var9, var29, var23);
         } else {
            if (var9 != null) {
               float var46 = (float)((var2 - var27) / 2.0);
               float var47 = (float)(MathUtil.x(Glyph.l(GlyphTexture.t(var29)[var38]) - 8.2F) / 2.0);
               int var41 = var9.s(var38, var46, var47, var32);
               RenderUtil.R(var8 ? (var41 & 16579836) >> 2 | var41 & -16777216 : var41, var25);
               GlStateManager.bindTexture(GlyphTexture.x(var29).getGlTextureId());
            }

            this.O(var2, var4, var29, var35, var36, var38);
            var2 += MathUtil.x(Glyph.l(GlyphTexture.t(var29)[var38]) - 8.2F);
         }
      }

      return (float)(var2 / 2.0);
   }



   private void y(String var1, float var2, long var3, float var5) {
      var3 = a ^ var3;
      this.F.clear();
      String[] var8 = var1.trim().split(" ");
      StringBuilder var9 = new StringBuilder();

      for (String var13 : var8) {
         float var14 = this.S(var9 + " " + var13,0L);
         if (var2 + var14 >= var2 + var5) {
            this.F.add(var9.toString());
            var9 = new StringBuilder(var13).append(" ");
         } else {
            var9.append(var13).append(" ");
         }
      }

      this.F.add(var9.toString());
   }

   private void G(long var1, GlyphTexture var3) {





      BufferedImage var7 = new BufferedImage(1, 1, 2);
      Graphics2D var8 = (Graphics2D)var7.getGraphics();
      Font var9 = GlyphTexture.k(var3) == 0 ? this.S : this.S.deriveFont(GlyphTexture.k(var3));
      var8.setFont(var9);
      this.y((short)0, 1452133362, var3, (char)42389, var9, var8);
   }

   public void P(FontGlyphPage var1) {
      this.h = var1;
   }

   public String U(String var1, int var2, long var3) {
      var3 = a ^ var3;
      int var5 = (int)((var3 ^ 121613094644513L) >>> 56);
      long var6 = (var3 ^ 121613094644513L) << 8 >>> 8;
      return this.G(var1, var2, false, (byte)var5, var6);
   }

   public int V(String var1, float var2, long var3, float var5, int var6) {
      long var7 = var3 ^ 1600447750306L;
      return this.a(var1, var2, var5, var6, var7, false);
   }

   private void y(short var1, int var2, GlyphTexture var3, char var4, Font var5, Graphics2D var6) {
      long var7 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      long var9 = var7 ^ 137458417721814L;
      this.v(var9, var3, var5, var6, false);
   }

   private void J(double var1, double var5, double var7, double var9) {
      GL11.glDisable(3553);
      GL11.glLineWidth(1.0F);
      GL11.glBegin(1);
      GL11.glVertex2d(var1, var5);
      GL11.glVertex2d(var7, var9);
      GL11.glEnd();
      GL11.glEnable(3553);
   }

   private int Z(char var1, long var2) {
      switch (Character.toLowerCase(var1)) {
         case '0':
            return 0;
         case '1':
            return 1;
         case '2':
            return 2;
         case '3':
            return 3;
         case '4':
            return 4;
         case '5':
            return 5;
         case '6':
            return 6;
         case '7':
            return 7;
         case '8':
            return 8;
         case '9':
            return 9;
         case ':':
         case ';':
         case '<':
         case '=':
         case '>':
         case '?':
         case '@':
         case 'A':
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'g':
         case 'h':
         case 'i':
         case 'j':
         case 'p':
         case 'q':
         default:
            return 21;
         case 'a':
            return 10;
         case 'b':
            return 11;
         case 'c':
            return 12;
         case 'd':
            return 13;
         case 'e':
            return 14;
         case 'f':
            return 15;
         case 'k':
            return 16;
         case 'l':
            return 17;
         case 'm':
            return 18;
         case 'n':
            return 19;
         case 'o':
            return 20;
         case 'r':
            return 21;
      }
   }

   private static void v(short var0, int var1, short var2) {
      if (T == null) {
         T = new int[32];

         for (int var5 = 0; var5 < 32; var5++) {
            int var6 = (var5 >> 3 & 1) * 85;
            int var7 = (var5 >> 2 & 1) * 170 + var6;
            int var8 = (var5 >> 1 & 1) * 170 + var6;
            int var9 = (var5 & 1) * 170 + var6;
            if (var5 == 6) {
               var7 += 85;
            }

            if (var5 >= 16) {
               var7 /= 4;
               var8 /= 4;
               var9 /= 4;
            }

            T[var5] = (var7 & 255) << 16
               | (var8 & 255) << 8
               | var9 & 255;
         }
      }
   }

   public int x(String var1, float var2, long var3, float var5, int var6) {
      long var7 = var3 ^ 126823015494257L;
      this.N(var1, var2 + 0.5F, var5 + 0.5F, var6, true, 8.2F, false, var7);
      return (int)this.N(var1, var2, var5, var6, false, 8.2F, false, var7);
   }

   private boolean l(long var1, Glyph[] var3, char var4) {
      return var4 == 167
         ? false
         : var4 >= 127 || var4 >= var3.length || var3[var4] == null || Glyph.l(var3[var4]) <= 0.0F;
   }

   private int getStringWidth(char var1, long var2) {
      return var1 == 167 ? 0 : this.X.fontRendererObj.getStringWidth(String.valueOf(var1));
   }

   protected void z(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = var5 / var7;
      float var10 = var6 / var8;
      float var11 = var3 / var7;
      float var12 = var4 / var8;
      GL11.glTexCoord2f(var9 + var11, var10);
      GL11.glVertex2d(var1 + var3, var2);
      GL11.glTexCoord2f(var9, var10);
      GL11.glVertex2d(var1, var2);
      GL11.glTexCoord2f(var9, var10 + var12);
      GL11.glVertex2d(var1, var2 + var4);
      GL11.glTexCoord2f(var9, var10 + var12);
      GL11.glVertex2d(var1, var2 + var4);
      GL11.glTexCoord2f(var9 + var11, var10 + var12);
      GL11.glVertex2d(var1 + var3, var2 + var4);
      GL11.glTexCoord2f(var9 + var11, var10);
      GL11.glVertex2d(var1 + var3, var2);
   }

   public double B( String var3, float var4) {
      if (var3 == null) {
         return 0.0;
      }

      float var9 = 0.0F;
      Glyph[] var10 = GlyphTexture.t(this.q);

      for (int var11 = 0; var11 < var3.length(); var11++) {
         char var12 = var3.charAt(var11);
         if (var12 == 167) {
            int var13 = "0123456789abcdefklmnor".indexOf(var3.charAt(var11 + 1));
            switch (var13) {
               case 17:
                  if (this.F()) {
                     var10 = GlyphTexture.t(this.h.q);
                  }
                  break;
               case 20:
                  var10 = GlyphTexture.t(this.q);
                  break;
               default:
                  var10 = GlyphTexture.t(this.q);
            }

            var11++;
         } else if (this.l(0L, var10, var12)) {
            var9 += this.getStringWidth(var12,0L) * 2.0F;
         } else {
            var9 += Glyph.l(var10[var12]) - var4;
         }
      }

      return var9 / 2.0F;
   }

   public Pair e(String var1, long var2, float var4, float var5, int var6, float var7) {
      long var8 = var2 ^ 71593604397779L;
      this.a(var1, var4);
      String var14 = "";
      float var15 = var5;

      for (String var17 : this.F) {
         if (this.S(var17,0L) > this.S(var14,0L)) {
            var14 = var17;
         }

         RenderUtil.X();
         this.V(var17, var4, var8, var15, var6);
         var15 += this.e(0L) + var7;
      }

      return Pair.p(this.S(var14,0L), var15 - var5);
   }

   public float S(String var1, long var2) {
      return (float)this.B( var1, 8.2F);
   }

   public FontGlyphPage(Font var1, long var2) {
      var2 = a ^ var2;
      long var4 = var2 ^ 3820351293046L;
      long var6 = var2 ^ 68568143026748L;
      int var8 = (int)((var2 ^ 137353658574357L) >>> 56);
      long var9 = (var2 ^ 137353658574357L) << 8 >>> 8;
      this.X = MinecraftRef.c((byte)var8,0L);
      this.q = new GlyphTexture(0, var6);
      this.x = new GlyphTexture(2, var6);
      this.F = new ArrayList<>();
      this.S = var1;
      this.G(var4, this.q);
      this.G(var4, this.x);
   }

   static {
      a = 39575492712087L;
   }


}
