package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ContainerKeeperBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.SendPacketEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.movement.InvMove;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.TextSetting;
import Expo.util.KeyBindUtil;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import org.lwjgl.input.Keyboard;











public class ContainerKeeper extends Module implements EventSubscriber {
   private boolean v;
   private boolean t;
   private boolean T;
   public static TextSetting toggleKey;
   private static long a;
   public static BooleanSetting requireShiftToSave;
   private GuiScreen H;

   static {
      a = 89937564371945L;
   }

   private void p() {
      this.v = false;
      this.t = false;
      this.H = null;
      this.T = false;
   }

   public void onPreUpdate(int var1, int var2, byte var3, PreUpdateEvent var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 40 >>> 32 | (long)var3 << 56 >>> 56) ^ a;
      long var7 = var5 ^ 123508576926208L;
      long var11 = var5 ^ 88654231159108L;
      boolean var15 = KeyBindUtil.V(Keyboard.getKeyIndex(toggleKey.X()), var11);
      if (!var15) {
         this.T = false;
      }

      if (this.v) {
         InvMove.Q(0L);
      }

      if (KeyBindUtil.V(1, var11)) {
         this.p();
      } else if (f.currentScreen instanceof GuiInventory) {
         this.p();
      } else {
         this.W(var15, var7);
      }
   }

   public void onSendPacket(SendPacketEvent var1, int var2, short var3, int var4) {
      if (var1.B instanceof C0EPacketClickWindow) {
         this.v = true;
      } else if (var1.B instanceof C0DPacketCloseWindow) {
         this.p();
      }
   }

   public void onReceivePacket(ReceivePacketEvent var1) {
      if (var1.d instanceof S2EPacketCloseWindow) {
         this.p();
      } else if (var1.d instanceof S2DPacketOpenWindow) {
         this.v = false;
      }
   }

   public void onRender2D(long var1, int var3, Render2DEvent var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = (var1 << 32 | (long)var3 << 32 >>> 32) ^ a;
      long var7 = var5 ^ 82025431758030L;
      if (this.t) {
         float var11 = var4.C.getScaledWidth() / 2.0F + 10.0F;
         float var12 = var4.C.getScaledHeight() / 2.0F + 10.0F;
         CustomFont var13 = Font.s(0L);
         var13.T(var7, "Press " + toggleKey.X() + " for container", var11, var12, -1);
      }
   }

   public void A(long var1) {
      this.p();
   }


   private void W(boolean var1, long var2) {
      long var6 = var2 ^ 36240920312644L;
      if (!this.T && var1) {
         if (!this.t && f.currentScreen instanceof GuiContainer && (!requireShiftToSave.c() || KeyBindUtil.V(f.gameSettings.keyBindSneak.getKeyCode(), var6))) {
            this.H = f.currentScreen;
            f.displayGuiScreen(null);
            if (!this.v) {
               InvMove.c(0L);
            }

            f.inGameHasFocus = true;
            this.t = true;
            this.T = true;
         } else if (this.t) {
            f.displayGuiScreen(this.H);
            this.t = false;
            this.T = true;
         }
      }
   }


   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 72387046643234L) >>> 32);
      ContainerKeeperBinder.Q(var3, var4, this);
   }

   public void onWorldLoad(WorldLoadEvent var3) {
      this.p();
   }



   public ContainerKeeper(long var1) {
      super(((a ^ (var1)) ^ 59075679715341L));
      // add code
      this.declare("ContainerKeeper", Category.Misc, "Save a container to open later");
      var1 = a ^ var1;
      this.v = false;
      this.t = false;
      this.H = null;
      this.T = false;
   }

   static {
      // add code
      toggleKey = new TextSetting("Toggle-key", "NONE");
      requireShiftToSave = new BooleanSetting("Require-shift-to-save", false);
   }
}
