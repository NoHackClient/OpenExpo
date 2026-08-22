package Expo.module.impl.world;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AutoToolBinder;
import Expo.event.events.PreMouseInputEvent;
import Expo.module.PriorityModule;
import Expo.module.impl.player.AutoWeapon;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.OutgoingPacketState;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSword;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class AutoTool extends PriorityModule implements EventSubscriber {
   private int J;
   private boolean I;
   private static Map g;
   private static Object[] h;
   public static BooleanSetting switchBackToSword;
   private static String[] k;
   public static BooleanSetting disableWhenHoldingSword;
   private static long[] d;
   public static BooleanSetting switchBack;
   public static NumberSetting delay;
   private final TimerUtil t;
   public static BooleanSetting requireSneak;
   private boolean S;
   private static long a;

   public void P(long var1) {
      this.J = f.thePlayer.inventory.currentItem;
   }

   public AutoTool(long var1) {
      super((((a ^ (var1)) ^ 66187273744195L) >>> 16), (char)((int)(((((a ^ (var1)) ^ 66187273744195L) << 48) >>> 48))));
      this.declare("AutoTool", Category.World, "Switch to the right tools when you are mining");
      var1 = a ^ var1;
      this.t = new TimerUtil();
      this.I = false;
      this.J = -1;
      this.S = false;
   }

   public String g(long var1) {
      return String.valueOf((int)delay.L());
   }

   static {
      a = 65895564979047L;
      h = new Object[10];
      k = new String[10];
      g = new HashMap(13);
      d = new long[]{6204913656527195237L, -3535756269773128318L, -8377926795496370906L, -3172277969201009195L, 8119325576134456104L, 4460089624490024534L};
   }

   public final void x(long var1, EventBus var3) {
      AutoToolBinder.v(var3, this);
   }

   public void onPreMouseInput(long var1, PreMouseInputEvent var3) {
      if (!this.I) {
         this.J = f.thePlayer.inventory.currentItem;
      }

      if (!disableWhenHoldingSword.c() || f.thePlayer.getHeldItem() == null || !(f.thePlayer.getHeldItem().getItem() instanceof ItemSword)) {
         if (!requireSneak.c() || f.thePlayer.isSneaking()) {
            if (this.Y()) {
               if (!OutgoingPacketState.P && !OutgoingPacketState.h) {
                  if (f.currentScreen == null && KeyBindUtil.V(f.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L)) {
                     if (f.objectMouseOver != null && f.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && !this.S) {
                        this.t.W();
                        this.S = true;
                     }

                     if (this.t.L((long)delay.L(), true)) {
                        BlockPos var12 = f.objectMouseOver.getBlockPos();
                        if (var12 == null) {
                           return;
                        }

                        Block var13 = f.theWorld.getBlockState(var12).getBlock();
                        if (var13 == null || var13 == Blocks.air) {
                           return;
                        }

                        if (ItemUtil.e(0L, var13) == -1) {
                           return;
                        }

                        this.I = true;
                        ItemUtil.P( ItemUtil.e(0L, var13));
                        this.S = false;
                     }
                  } else if (switchBackToSword.c() && this.I) {
                     if (AutoWeapon.M(93384294372710L) != -1) {
                        ItemUtil.P( AutoWeapon.M(93384294372710L));
                     } else if (this.J != -1) {
                        ItemUtil.P( this.J);
                     }

                     this.I = false;
                     this.J = -1;
                  } else if (switchBack.c() && this.I && this.J != -1) {
                     ItemUtil.P( this.J);
                     this.I = false;
                     this.J = -1;
                  }
               }
            }
         }
      }
   }
   static {
      disableWhenHoldingSword = new BooleanSetting("Disable-when-holding-sword", true);
      delay = new NumberSetting("Delay", 0.0F, 0.0F, 1000.0F, 1.0F);
      switchBackToSword = new BooleanSetting("Switch-back-to-sword", false);
      switchBack = new BooleanSetting("Switch-back", true);
      requireSneak = new BooleanSetting("Require-sneak", false);
   }
}
