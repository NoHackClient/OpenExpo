package Expo.util.render;

import Expo.util.Pair;
import java.util.Map;
import net.minecraft.client.renderer.texture.DynamicTexture;











public class GlyphTexture {
   private static Map d;
   private static long a;
   private final int y;
   private Pair<Integer, Integer> g;
   private final Glyph[] F;
   private DynamicTexture x;

   public static int k(GlyphTexture var0) {
      return var0.y;
   }

   public static Pair x(GlyphTexture var0, Pair var1) {
      return var0.g = var1;
   }

   public static Pair o(GlyphTexture var0) {
      return var0.g;
   }

   public static DynamicTexture x(GlyphTexture var0) {
      return var0.x;
   }

   static {
      a = 137121052021233L;
      // add code
   }



   public static DynamicTexture d(GlyphTexture var0, DynamicTexture var1) {
      return var0.x = var1;
   }

   public static Glyph[] t(GlyphTexture var0) {
      return var0.F;
   }

   public GlyphTexture(int var1, long var2) {
      var2 = a ^ var2;
      this.F = new Glyph[256];
      this.g = Pair.p(512, 0);
      this.y = var1;
   }



}
