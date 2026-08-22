package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.InventoryHUDBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.ItemStack;


public class InventoryHUD extends Module implements EventSubscriber {
   public static PercentageSetting backgroundOpacity;
   private static long a;
   private static long[] c;
   private static Object[] h;
   private final ItemStack[] p;
   public static NumberSetting offsetX;
   private static String[] k;
   public static NumberSetting offsetY;
   private static Map g;

   static {
      a = 132766341431106L;
      h = new Object[8];
      k = new String[8];
      g = new HashMap(13);
      c = new long[]{-437371915069317021L, 2620004582826395637L, -3763553000221625768L, 4610870018804750723L, -7897035737980205386L, 7164162007031990288L, -2322289815908258466L, 932703178022838397L, 8605211776989967091L, 1116916509488641341L};
   }

   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 93804618286204L) >>> 56);
      int var5 = (int)((var1 ^ 93804618286204L) << 8 >>> 32);
      int var6 = (int)((var1 ^ 93804618286204L) << 40 >>> 40);
      InventoryHUDBinder.j(var3, this, (byte)var4, var5, var6);
   }

   public InventoryHUD(long var1) {
      super(((a ^ (var1)) ^ 19548772938122L));
      // add code
      this.declare("InventoryHUD", Category.Visual_utility, "Show your inventory contents on screen");
      var1 = a ^ var1;
      this.p = new ItemStack[27];
   }

   public void A(long var1) {
      for (int var3 = 0; var3 < this.p.length; var3++) {
         this.p[var3] = null;
      }
   }

   public void onRender2D(Render2DEvent var1, char var2, int var3, int var4) {
      long var5 = ((long)var2 << 48 | (long)var3 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 60788501648431L;
      int var9 = (int)offsetX.L();
      int var10 = (int)offsetY.L();
      Expo.util.render.RenderUtil.c(
         var7,
         var9 - 2,
         var10 - 2,
         var9 + 162 + 2,
         var10 + 54 + 2,
         new Color(0, 0, 0, 255 * backgroundOpacity.k() / 100).getRGB()
      );

      for (int var11 = 0; var11 < this.p.length; var11++) {
         ItemStack var12 = this.p[var11];
         if (var12 != null) {
            if (var12.stackSize <= 1) {
               Expo.util.render.RenderUtil.m(var12, var9, var10);
            } else {
               Expo.util.render.RenderUtil.q(var12, var9, var10, String.valueOf(var12.stackSize));
            }
         }

         var9 += 18;
         if (var11 == 8 || var11 == 17) {
            var9 = (int)offsetX.L();
            var10 += 18;
         }
      }
   }


   public void onPostTick(long var1, PostTickEvent var3) {

      for (int var4 = 9; var4 < 36; var4++) {
         ItemStack var5 = f.thePlayer == null ? null : f.thePlayer.inventory.mainInventory[var4];
         this.p[var4 - 9] = var5 == null ? null : var5.copy();
      }
   }

   private static void a() {
      h[0] = "v\u0003`t%I\u0007";
      h[1] = "m'+(A\u001aZ0/\"\f>M;u>";
      h[2] = "Ifs\u001a\tg<";
      h[3] = byte.class;
      k[3] = "java/lang/Byte";
      h[4] = int.class;
      k[4] = "java/lang/Integer";
      h[5] = void.class;
      k[5] = "java/lang/Void";
      h[6] = "O&\u001dUo\u0004D)\f\u001a\u000e\nO\"\b@";
      h[7] = "\f\u0005\u0007Sx\u000e\u000b\u001d\u001a#+\u007f\\[\u001b\u001fx\u000f\u001e\u0019B\u001bIF\u0014\u001a\u0017C.\u001d\n\n\u001b#sN\u0005]\u0002I;\u0011\u0005\u001d{\u00195NZ[\u0005Gs\u001f\u0007cA_x@]\u001d\u001f\u0019)\u001deX\u0001\u001a-\u0002\u0001_\u0016\u0013;\u007f";
   }
   static {
      // add code
      offsetX = new NumberSetting("Offset-X", 5.0F, 0.0F, 1000.0F, 1.0F);
      backgroundOpacity = new PercentageSetting("Background-opacity", 50);
      offsetY = new NumberSetting("Offset-Y", 30.0F, 0.0F, 1000.0F, 1.0F);
   }
}
