package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.InvClickerBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;


public class InvClicker extends Module implements EventSubscriber {
   private static long a;
   private static Map d;
   private int p;
   private static Object[] e;
   private static String[] g;
   private static long[] b;
   public static NumberSetting cps;
   public static BooleanSetting alwaysClick;

   private static void a() {
      e[0] = "\"g'B@&";
      e[1] = long.class;
      g[1] = "java/lang/Long";
      e[2] = "Q^LA\t\u0018fIHKD<qB\u0012W";
      e[3] = "\u0010f\u0017g\u0010S\u001e";
      e[4] = void.class;
      g[4] = "java/lang/Void";
      e[5] = "IQ\u0012^/@B^\u0003\u0011NNIU\u0007K";
      e[6] = "[Hh\t(\u0013U\beqJo\u0007Le\nj\u0015\u0002\u001dwA\u0016V^\u0019dH+\u0015X\byq,\u0004P\u001dc\u001fq\u0015N\u0016\u0019J'V_\u000f&J|\u0015Yt";
   }

   public void onPreUpdate(long var1, PreUpdateEvent var3) {

      if (this.p > 0) {
         this.p = this.p - 50;
      }

      if (KeyBindUtil.V(f.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L)
         && KeyBindUtil.V(42, 64165991731362L)
         && (f.currentScreen instanceof GuiInventory || f.currentScreen instanceof GuiContainer)
         && !this.isGetItemStack()) {
         if (alwaysClick.c()) {
            ItemUtil.e(f.currentScreen);
         } else if (this.p <= 0) {
            ItemUtil.e(f.currentScreen);
            this.p = (int)(1000.0 / cps.L());
         }
      }
   }

   private boolean isGetItemStack() {
      return f.thePlayer.inventory.getItemStack() != null;
   }

   static {
      a = 104942853302490L;
      e = new Object[7];
      g = new String[7];
      d = new HashMap(13);
      b = new long[]{-7855264762936904475L, 2721434635923828421L, 695722135645467035L, -8001476799126727203L};
   }

   public final void x(long var1, EventBus var3) {
      InvClickerBinder.T(var3, this);
   }

   public InvClicker(long var1) {
      super(((a ^ (var1)) ^ 31981580547189L));
      // add code
      this.declare("InvClicker", Category.Player, "Automatically click in inventory when you press shift");
      var1 = a ^ var1;
      this.p = 0;
   }


   public void A(long var1) {
      this.p = 0;
   }
   static {
      // add code
      alwaysClick = new BooleanSetting("Always-click", true);
      cps = new NumberSetting("CPS", 10.0F, 0.0F, 20.0F, 1.0F);
   }
}
