package Expo.util.render;

import Expo.util.ClientUtil;
import Expo.util.MinecraftRef;
import java.awt.Font;
import java.io.InputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;


public class CustomFont {
   public final boolean b;
   private final Minecraft H;
   private static long a;
   private final FontGlyphPage G;


   private static Font a(ResourceLocation var0, long var1) {



      try {
         InputStream var6 = MinecraftRef.c((byte)0,0L).getResourceManager().getResource(var0).getInputStream();
         return Font.createFont(0, var6).deriveFont(20.0F);
      } catch (Exception var7) {
         Expo.internal.restore.ExpoDiag.attribute(var7, "CustomFont.a/2#0");
         return new Font("default", 0, 20);
      }
   }

   static {

      a = 123238688271397L;
   }

   private Integer G(long var1, char var3) {
      switch (Character.toLowerCase(var3)) {
         case '0':
            return -16777216;
         case '1':
            return -16777046;
         case '2':
            return -16733696;
         case '3':
            return -16733526;
         case '4':
            return -5636096;
         case '5':
            return -5635926;
         case '6':
            return -22016;
         case '7':
            return -5592406;
         case '8':
            return -11184811;
         case '9':
            return -11184641;
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
         default:
            return null;
         case 'a':
            return -11141291;
         case 'b':
            return -11141121;
         case 'c':
            return -43691;
         case 'd':
            return -43521;
         case 'e':
            return -171;
         case 'f':
            return -1;
      }
   }

   public void X(String var1, float var2, long var3, float var5, int var6) {


      this.v(var1, var2, var5, var6, 88827598794260L, false);
   }

   private void B(String var1, float var2, float var3, int var4, boolean var5, GlyphDrawer var8) {
      if (var1 != null) {
         float var11 = var2;
         float var12 = 0.0F;
         Integer var13 = null;

         for (int var14 = 0; var14 < var1.length(); var14++) {
            char var15 = var1.charAt(var14);
            if (var15 == 167 && var14 + 1 < var1.length()) {
               Integer var20 = this.G(0L, var1.charAt(var14 + 1));
               if (var20 != null) {
                  var13 = var20;
               } else if (Character.toLowerCase(var1.charAt(var14 + 1)) == 114) {
                  var13 = null;
               }

               var14++;
            } else {
               String var16 = String.valueOf(var15);
               int var17 = this.H.fontRendererObj.getStringWidth(var16);
               int var18 = var8 == null ? var4 : var8.s(var15, var12, var17, var13);
               if (var5) {
                  this.H.fontRendererObj.drawStringWithShadow(var16, var11, var3, var18);
               } else {
                  this.H.fontRendererObj.drawString(var16, (int)var11, (int)var3, var18, false);
               }

               var11 += var17;
               var12 += var17;
            }
         }
      }
   }

   public float o(long var1) {


      return this.b ? this.H.fontRendererObj.FONT_HEIGHT : this.G.e(25129460711095L);
   }

   public void p(String var1, float var2, float var3, long var4, int var6) {



      this.v(var1, var2 - this.R(var1, 52019766876817L) / 2.0F, var3, var6, 88827598794260L, false);
   }

   public void T(long var1, String var3, float var4, float var5, int var6) {


      this.v(var3, var4, var5, var6, 88827598794260L, true);
   }

   public void A(String var1, float var2, float var3, int var4, long var5, boolean var7, GlyphDrawer var8) {


      var1 = ClientUtil.replaceString(var1);
      if (this.b) {
         this.B(var1, var2, var3, var4, var7, var8);
      } else {
         this.G.j(var1, var2, 94109581654416L, var3, var4, var7, var8);
      }
   }


   public void v(String var1, float var2, float var3, int var4, long var5, boolean var7) {



      var1 = ClientUtil.replaceString(var1);
      if (this.b) {
         if (var7) {
            this.H.fontRendererObj.drawStringWithShadow(var1, var2, var3, var4);
         } else {
            this.H.fontRendererObj.drawString(var1, (int)var2, (int)var3, var4, false);
         }
      } else {
         if (var7) {
            this.G.F(var1, var2, var3, var4, 130877858286392L);
         } else {
            this.G.P(var1, var2, var3, 73237590039151L, var4);
         }
      }
   }

   public float R(String var1, long var2) {


      var1 = ClientUtil.replaceString(var1);
      return this.b ? this.H.fontRendererObj.getStringWidth(var1) : this.G.S(var1, 2692364323580L);
   }

   public CustomFont(long var1, String var3) {
      var1 = a ^ var1;
      long var4 = var1 ^ 47724778262642L;
      long var6 = var1 ^ 68153505398850L;
      int var8 = (int)((var1 ^ 128048231670000L) >>> 56);
      long var9 = (var1 ^ 128048231670000L) << 8 >>> 8;
      this.H = MinecraftRef.c((byte)var8,0L);
      this.b = var3.equalsIgnoreCase("NONE");
      this.G = this.b ? null : new FontGlyphPage(a(new ResourceLocation("font/" + var3 + ".ttf"), var6), var4);
   }

}
