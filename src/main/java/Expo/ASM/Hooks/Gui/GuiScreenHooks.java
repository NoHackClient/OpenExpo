package Expo.ASM.Hooks.Gui;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ExpoClient;
import Expo.event.events.DrawScreenEvent;
import Expo.event.events.PostDrawScreenEvent;
import Expo.internal.accessor.GuiScreenAccessor;
import Expo.module.Modules;
import Expo.module.impl.configuration.Gadgets;
import Expo.module.impl.misc.InputFix;
import Expo.util.ClientUtil;
import Expo.util.MinecraftRef;
import Expo.util.render.VisualSpoofRenderer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

public class GuiScreenHooks {
   private static long b;
   private static long a;
   private static Minecraft c;

   public static void onPostDrawScreen(GuiScreen var0, int var1, int var2, float var3) throws Throwable {
      if (var0 != null) {
         if (!VisualSpoofRenderer.H()) {
            VisualSpoofRenderer.L(var1, var2, var3, 86835669792802L);
            ScaledResolution var12 = new ScaledResolution(c);
            PostDrawScreenEvent var13 = new PostDrawScreenEvent(var0, var12);
            ExpoClient.w.e(var13, 18670087776179L);
         }
      }
   }

   static {
      a = 140001884061524L;
      c = MinecraftRef.c((byte)0, 0L);
      b = 3298079341916717088L;
   }

   public static void onDrawScreen(GuiScreen var0, CallbackInfo var1) {
      if (var0 != null) {
         if (!VisualSpoofRenderer.H()) {
            DrawScreenEvent var8 = new DrawScreenEvent(var0);
            ExpoClient.w.e(var8, 18670087776179L);
            if (var8.a()) {
               var1.cancel();
            }
         }
      }
   }

   public static void onHandleKeyboardInput(GuiScreen var0, CallbackInfo var1) throws IOException {
      if (Modules.J(InputFix.class).o()) {
         char var4 = Keyboard.getEventCharacter();
         int var5 = Keyboard.getEventKey();
         if (Keyboard.getEventKeyState() || var4 >= (int)b && var5 == 0) {
            GuiScreenAccessor.J(var0, Keyboard.getEventCharacter(), Keyboard.getEventKey());
         }

         c.dispatchKeypresses();
         var1.cancel();
      }
   }

   public static boolean shouldCancel() {
      return ClientUtil.I() && Gadgets.noScreenBackground.c();
   }
}
