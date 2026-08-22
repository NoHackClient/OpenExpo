package Expo.module.impl.visual;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.TabGUIBinder;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.Sneaky;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.renderer.GlStateManager;











public class TabGUI extends Module implements EventSubscriber {
   public static HashMap<Category, Integer> E;
   public static ModeSetting color;
   private static long c;
   private boolean o;
   public static ColorSetting customColor;
   private Module s;
   private Category M;
   private int a;
   private final float t;
   public static NumberSetting offsetX;
   public static NumberSetting offsetY;
   public static BooleanSetting disableTabKey;

   public TabGUI(long var1) {
      super(((c ^ (var1)) ^ 138011780300290L));
      // add code
      this.declare("TabGUI", Category.Visual, "Use tab and arrow keys to toggle modules");
      var1 = c ^ var1;
      this.t = 0.5F;
      this.M = Category.Combat;
      this.s = null;
      this.a = 0;
      this.o = false;
   }

   private int j() {
      return E.get(this.M);
   }

   private Module r$r2() {
      ArrayList var1 = new ArrayList();
      List var2 = ModuleManager.S;
      int var3 = 0;

      for (int var4 = var2.size(); var3 < var4; var3++) {
         Module var5 = (Module)var2.get(var3);
         if (var5.f().equals(this.M)) {
            var1.add(var5);
         }
      }

      return (Module)var1.get(this.j());
   }

   static {
      c = 49436393407904L;
      E = new HashMap<>();
   }



   public final void x(long var1, EventBus var3) {
      TabGUIBinder.z(var3, this);
   }

   public void onRender2D(long var1, Render2DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {














      GlStateManager.pushMatrix();
      CustomFont var35 = Font.F(0L);
      float var36 = offsetX.L();
      float var37 = offsetY.L();
      boolean var38 = false;
      Category var39 = null;
      Comparator<Category> var40 = Comparator.comparingDouble(var2 -> {
         long var3x = 56334937510878L;
         long var5 = 52019766876817L;
         long var10001x = 52136776939807L;
         int var7 = 12139;
         int var8x = (int)((var3x ^ 31161965472449L) << 32 >>> 48);
         int var9 = (int)(var10001x << 48 >>> 48);
         return var35.R(var2.x(var7, var8x, (short)var9), var5) * 0.5F;
      });
      List var41 = Category.j();
      int var42 = 0;

      for (int var43 = var41.size(); var42 < var43; var42++) {
         Category var44 = (Category)var41.get(var42);
         if (!var38 || var40.compare(var44, var39) > 0) {
            var38 = true;
            var39 = var44;
         }
      }

      float var63 = var38 ? var35.R(var39.x(12139, 2577, (short)47391), 52019766876817L) : 0.0F;
      float var64 = var63 * 0.5F;
      float var65 = 0.0F;
      if (this.o) {
         boolean var67 = false;
         Module var45 = null;
         Comparator<Module> var46 = Comparator.comparingDouble(var2 -> {
             try {long var3x = c ^ 20731982445531L;
            long var5 = 37791158269748L;
            long var10001x = 124297285854588L;
            int var7 = 28940;
            int var8x = (int)((var3x ^ 72775140226722L) << 32 >>> 48);
            int var9 = (int)(var10001x << 48 >>> 48);
            return var35.R(var2.Q(var7, (char)var8x, (char)var9), var5) * 0.5F;
         } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } });
         List var47 = ModuleManager.S;
         int var48 = 0;

         for (int var49 = var47.size(); var48 < var49; var48++) {
            Module var50 = (Module)var47.get(var48);
            if (var50.f().equals(this.M) && (!var67 || var46.compare(var50, var45) > 0)) {
               var67 = true;
               var45 = var50;
            }
         }

         if (var67) {
            var65 = var35.R(var45.Q(31773, (char)3256, (char)47391), 52019766876817L);
         }
      }

      var65 *= 0.5F;
      double var68 = 0.0;

      for (Category var70 : Category.j()) {
         String var71 = var70.x(12139, 2577, (short)47391);
         int var72;
         switch (color.Y()) {
            case "THEME":
               var72 = Theme.S(var68, 35338930340239L);
               break;
            case "THEME_CUSTOM":
               var72 = Theme.X(65301174328177L, var68);
               break;
            default:
               var72 = customColor.k(96531491288662L);
         }

         var68 += Theme.offset.L();
         double var81 = var37 / 0.5F;
         int var26 = this.M == var70 ? var72 : new Color(0, 0, 0, 150).getRGB();
         double var27 = ((var37 + var35.o(60714858652844L) * 0.5F + 2.0F) / 0.5F);
         double var29 = ((var36 + var64 + 2.0F) / 0.5F);
         double var31 = var81;
         double var33 = (var36 / 0.5F);
         Expo.util.render.RenderUtil.c(125644905353792L, var33, var31, var29, var27, var26);
         var35.T(37697014677608L, var71, (var36 + 1.0F) / 0.5F, (var37 + 1.0F) / 0.5F, 16777215);
         if (this.o && this.M == var70) {
            float var74 = var36 + var64 + 2.0F;
            float var75 = var37;
            ArrayList var52 = new ArrayList();
            List var53 = ModuleManager.S;
            int var54 = 0;

            for (int var55 = var53.size(); var54 < var55; var54++) {
               Module var56 = (Module)var53.get(var54);
               if (var56.f().equals(this.M)) {
                  var52.add(var56);
               }
            }

            var54 = 0;

            for (int var77 = var52.size(); var54 < var77; var54++) {
               Module var78 = (Module)var52.get(var54);
               double var82 = var75 / 0.5F;
               var26 = this.s == var78
                  ? (var78.o() ? Color.GREEN.getRGB() : Color.RED.getRGB())
                  : new Color(
                        30,
                        30,
                        30,
                        150
                     )
                     .getRGB();
               var27 = ((var75 + var35.o(60714858652844L) * 0.5F + 1.0F) / 0.5F);
               var29 = ((var74 + var65 + 1.0F) / 0.5F);
               var31 = var82;
               var33 = (var74 / 0.5F);
               Expo.util.render.RenderUtil.c(125644905353792L, var33, var31, var29, var27, var26);
               var35.T(37697014677608L, var78.Q(31773, (char)3256, (char)47391), (var74 + 0.5F) / 0.5F, (var75 + 0.5F) / 0.5F, 16777215);
               var75 += var35.o(60714858652844L) * 0.5F + 1.0F;
            }
         }

         var37 += var35.o(60714858652844L) * 0.5F + 2.0F;
      }

      GlStateManager.popMatrix();
   }

   private void P(int var1) {
      E.put(this.M, var1);
   }

   private int L() {
      long var1 = 0L;
      List var3 = ModuleManager.S;
      int var4 = 0;

      for (int var5 = var3.size(); var4 < var5; var4++) {
         Module var6 = (Module)var3.get(var4);
         if (var6.f().equals(this.M)) {
            var1++;
         }
      }

      return (int)var1;
   }





   public void onSetKeyBindState(SetKeyBindStateEvent var1, long var2) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (f.currentScreen == null) {
         if (var1.R == 15 && !disableTabKey.c()) {
            this.a++;
            if (this.a >= Category.j().size()) {
               this.a = 0;
            }

            this.M = Category.j().get(this.a);
            this.s = this.r$r2();
         } else if (var1.R == 205) {
            this.o = true;
            this.s = this.r$r2();
         } else if (var1.R == 203) {
            this.o = false;
            this.s = this.r$r2();
         } else if (var1.R == 200) {
            if (this.o) {
               this.P(this.j() - 1);
               if (this.j() < 0) {
                  this.P(this.L() - 1);
               }

               this.s = this.r$r2();
            } else {
               this.a--;
               if (this.a < 0) {
                  this.a = Category.j().size() - 1;
               }

               this.M = Category.j().get(this.a);
               this.s = this.r$r2();
            }
         } else if (var1.R == 208) {
            if (this.o) {
               this.P(this.j() + 1);
               if (this.j() >= this.L()) {
                  this.P(0);
               }

               this.s = this.r$r2();
            } else {
               this.a++;
               if (this.a >= Category.j().size()) {
                  this.a = 0;
               }

               this.M = Category.j().get(this.a);
               this.s = this.r$r2();
            }
         } else if (this.o && var1.R == 28) {
            this.r$r2().u((short)0, 139350548161835L);
         }
      }
   }


   static {
      // add code
      customColor = new ColorSetting("Custom-color", "FFFFFF");
   }
   static {
      // add code
      disableTabKey = new BooleanSetting("Disable-tab-key", true);
   }
   static {
      // add code
      offsetX = new NumberSetting("Offset-X", 3.0F, 0.0F, 1000.0F, 1.0F);
      offsetY = new NumberSetting("Offset-Y", 60.0F, 0.0F, 1000.0F, 1.0F);
   }
   static {
      // add code
      color = new ModeSetting("Color", "THEME", "THEME_CUSTOM", "CUSTOM");
   }
}
