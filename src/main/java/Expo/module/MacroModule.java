package Expo.module;

import Expo.internal.accessor.MethodAccessors;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import java.util.Objects;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class MacroModule extends Module {
   private static long k;
   private boolean Y;
   private boolean J;
   private int U;
   private boolean M;
   private int g;
   private int D;

   private void O(char var1, ModeSetting var2, BooleanSetting var3, int var4, short var5) {
      long var6 = ((long)var1 << 48 | (long)var4 << 32 >>> 16 | (long)var5 << 48 >>> 48) ^ k;
      long var8 = var6 ^ 128021940213233L;
      if (var2.R("ROD")) {
         KeyBindUtil.h(var8);
      }

      if (var3.c()) {
         ItemUtil.P( this.g);
      }
   }

   public MacroModule(long var1) {
      super(((k ^ (var1)) ^ 68044894831310L));
      this.g = 0;
      this.D = 0;
      this.U = -1;
      this.M = false;
      this.J = false;
      this.Y = false;
   }

   private boolean h(long var1, ModeSetting var3, NumberSetting var4) {
      int var11 = 61899;
      int var14 = 16552;

      this.U = -1;
      String var21 = var3.Y();
      int var22 = -1;
      switch (var21.hashCode()) {
         case -2137934807:
            if (var21.equals("LAVA_BUCKET")) {
               var22 = 6;
            }
            break;
         case -419874254:
            if (var21.equals("WATER_BUCKET")) {
               var22 = 5;
            }
            break;
         case 79413:
            if (var21.equals("POT")) {
               var22 = 2;
            }
            break;
         case 81319:
            if (var21.equals("ROD")) {
               var22 = 1;
            }
            break;
         case 76002342:
            if (var21.equals("PEARL")) {
               var22 = 4;
            }
            break;
         case 381473622:
            if (var21.equals("GOLDEN_HEAD")) {
               var22 = 3;
            }
            break;
         case 1213728650:
            if (var21.equals("PROJECTILES")) {
               var22 = 0;
            }
      }

      switch (var22) {
         case 0:
            this.U = ItemUtil.j(30888, 38028, (char)var11, false);
            break;
         case 1:
            this.U = ItemUtil.j(30888, 38028, (char)var11, true);
            break;
         case 2:
            this.U = ItemUtil.M(69180515578808L);
            if (this.U != -1) {
               ItemStack var23 = f.thePlayer.inventory.mainInventory[this.U];
               if (var23 != null
                  && var23.getItem() instanceof ItemPotion
                  && ItemPotion.isSplash(MethodAccessors.f(var23.getItem(), var23))
                  && ItemUtil.y(var23)
                  && f.thePlayer.getHealth() <= var4.L()) {
                  return false;
               }
            }
            break;
         case 3:
            this.U = ItemUtil.w(17215, (char)35254, (short)var14);
            break;
         case 4:
            this.U = ItemUtil.N(0L);
            break;
         case 5:
            this.U = ItemUtil.l(2486174265250L);
            break;
         case 6:
            this.U = ItemUtil.b(81324674286434L);
            break;
         default:
            return false;
      }

      if (this.U == -1) {
         return false;
      }

      ItemUtil.P( this.U);
      return true;
   }

   private int p(ModeSetting var1, NumberSetting var2) {
      switch (var1.Y()) {
         case "PROJECTILES":
         case "ROD":
            return Math.max(1, (int)var2.L());
         default:
            return 1;
      }
   }

   public void y(ModeSetting var1, NumberSetting var2, long var3, NumberSetting var5, BooleanSetting var6, TextSetting var7) {
      if (var1.R("CHAT")) {
         this.I(20724619369162L, false);
         if (!Objects.equals(var7.X(), "")) {
            f.thePlayer.sendChatMessage(var7.X());
         }
      } else if (!this.M) {
         this.g = f.thePlayer.inventory.currentItem;
         if (!this.h(122956549676365L, var1, var5)) {
            this.N(false);
            this.I(20724619369162L, false);
         } else {
            this.M = true;
            this.J = false;
            this.D = 0;
         }
      } else {
         this.D++;
         if (!this.J) {
            KeyBindUtil.h(45028351266375L);
            this.J = true;
            this.D = 0;
         } else {
            int var23 = this.p(var1, var2);
            if (this.D >= var23) {
               this.O((char)0, var1, var6, 107308396, (short)41315);
               if (this.Y) {
                  this.Y = false;
                  this.M = false;
                  this.J = false;
                  this.D = 0;
                  this.y(var1, var2, 40065435448518L, var5, var6, var7);
                  return;
               }

               this.N(true);
               this.I(20724619369162L, false);
            }
         }
      }
   }

   static {
      k = 100029549804245L;
   }

   private void N(boolean var3) {
      this.M = false;
      this.J = false;
      this.Y = false;
      this.D = 0;
      if (var3) {
         this.U = -1;
      }
   }
}
