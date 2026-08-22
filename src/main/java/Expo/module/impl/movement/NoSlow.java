package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.NoSlowBinder;
import Expo.event.events.RedirectIsUsingItemEvent;
import Expo.module.Module;
import Expo.module.impl.combat.AutoBlock;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.MinecraftRef;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;











public class NoSlow extends Module implements EventSubscriber {
   private static Map k;
   public static BooleanSetting onlyEnableWhenAutoblock;
   private static long[] g;
   private static String[] c;
   private static Minecraft R;
   private static String[] b;
   private static long a;
   public static ModeSetting swordMode;
   public static PercentageSetting slowDown;
   public static ModeSetting otherMode;

   static {
      a = 44677372933818L;
      R = MinecraftRef.c((byte)0, 0L);
   }

   public static boolean c(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      ItemStack var4 = R.thePlayer.getHeldItem();
      if (var4 == null) {
         return false;
      }

      Item var5 = var4.getItem();
      if (var5 instanceof ItemSword) {
         if (swordMode.R("VANILLA")) {
            float var6 = 0.2F + (100 - slowDown.k()) / 100.0F * 0.8F;
            float var7 = onlyEnableWhenAutoblock.c() ? (AutoBlock.G(51927146516111L) ? var6 : 0.2F) : var6;
            return var7 > 0.2F;
         } else {
            return false;
         }
      } else {
         return var4.getMaxItemUseDuration() > 0 ? otherMode.R("VANILLA") : true;
      }
   }

   public NoSlow(long var1) {
      super(((a ^ (var1)) ^ 85255024010748L));
      // add code
      this.declare("NoSlow", Category.Movement, "Change the slowdown when blocking sword, eating and pulling bow");
      var1 = a ^ var1;
   }





   public final void x(long var1, EventBus var3) {
      NoSlowBinder.G(var3, this);
   }

   public String g(long var1) {
      return slowDown.k() + "%";
   }

   public void onRedirectIsUsingItem(byte var1, int var2, int var3, RedirectIsUsingItemEvent var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = ((long)var1 << 56 | (long)var2 << 32 >>> 8 | (long)var3 << 40 >>> 40) ^ a;
      long var7 = var5 ^ 33644944796332L;
      if (R.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
         float var9 = 0.2F + (100 - slowDown.k()) / 100.0F * 0.8F;
         float var10 = onlyEnableWhenAutoblock.c() ? (AutoBlock.G(var7) ? var9 : 0.2F) : var9;
         switch (swordMode.Y()) {
            case "VANILLA":
               var4.W(var10);
               break;
            default:
               var4.W(0.2F);
         }
      } else {
         switch (otherMode.Y()) {
            case "VANILLA":
               var4.W(0.2F + (100 - slowDown.k()) / 100.0F * 0.8F);
               break;
            default:
               var4.W(0.2F);
         }
      }
   }

   private static void a() {
   }




   static {
      // add code
      slowDown = new PercentageSetting("Slow-down", 0);
      otherMode = new ModeSetting("Other-mode", "NONE", "VANILLA");
      swordMode = new ModeSetting("Sword-mode", "VANILLA", "NONE");
      onlyEnableWhenAutoblock = new BooleanSetting("Only-enable-when-autoblock", true);
   }
}
