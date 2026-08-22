package Expo.ASM.Hooks;

import Expo.ASM.Hooks.Gui.GuiEventHooks;
import Expo.ExpoClient;
import Expo.event.events.ClickMouseEvent;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.PostClickMouseEvent;
import Expo.event.events.PostRightClickEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreTickEvent;
import Expo.event.events.RightClickMouseEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.internal.jnic.GameStartLatch;
import Expo.internal.jnic.StockClientBootstrap;
import Expo.module.impl.configuration.Gadgets;
import Expo.util.MinecraftRef;
import Expo.util.render.VisualSpoofRenderer;
import java.io.File;
import java.io.UnsupportedEncodingException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ScreenShotHelper;

public class MinecraftHooks {
   private static Minecraft P;
   private static long a;
   private static long b;
   private static long v;

   public static void onClickMouse(CallbackInfo var0) {
      if (ExpoClient.w != null) {
         ClickMouseEvent var7 = new ClickMouseEvent();
         ExpoClient.w.e(var7, 18670087776179L);
         if (var7.a()) {
            var0.cancel();
         }
      }
   }

   public static void changeCurrentItem(InventoryPlayer var0, int var1) {
      if (ExpoClient.w == null) {
         var0.changeCurrentItem(var1);
      } else {
         HeldItemChangeEvent var8 = new HeldItemChangeEvent(-1, var1);
         ExpoClient.w.e(var8, 18670087776179L);
         if (!var8.a()) {
            var0.changeCurrentItem(var1);
         }
      }
   }

   public static void onStartGame() {
      GameStartLatch.w.set(true);
      t();
   }

   public static void onPostClickMouse() {
      if (ExpoClient.w != null) {
         PostClickMouseEvent var6 = new PostClickMouseEvent();
         ExpoClient.w.e(var6, 18670087776179L);
      }
   }

   public static void onSetKeyBindState(int var0, boolean var1) {
      KeyBinding.setKeyBindState(var0, var1);
      if (ExpoClient.w != null && var1 && P.currentScreen == null) {
         ExpoClient.w.e(new SetKeyBindStateEvent(var0), 18670087776179L);
      }
   }

   private static void t() {
      if (ExpoClient.w == null) {
         long var4 = System.currentTimeMillis();
         if (var4 - v >= b) {
            v = var4;
            StockClientBootstrap.F();
         }
      }
   }

   public static void onPostRightClick() {
      if (ExpoClient.w != null) {
         ExpoClient.w.e(new PostRightClickEvent(), 18670087776179L);
      }
   }

   static {
      a = 125812666074014L;
      P = MinecraftRef.c((byte)0, 0L);
      b = 1000L;
   }

   public static void onPreTick() {
      t();
      if (ExpoClient.w != null) {
         ExpoClient.w.e(new PreTickEvent(), 18670087776179L);
      }
   }

   public static void onPostTick() {
      GuiEventHooks.onClientTick();
      if (ExpoClient.w != null) {
         ExpoClient.w.e(new PostTickEvent(), 18670087776179L);
      }
   }

   public static boolean notAllowUserInput() {
      boolean var6 = P.currentScreen != null && P.currentScreen.allowUserInput;
      if (!var6 && ExpoClient.w != null) {
         PreMouseInputEvent var7 = new PreMouseInputEvent();
         ExpoClient.w.e(var7, 18670087776179L);
      }

      return var6;
   }

   public static IChatComponent onSaveScreenshot(File var0, int var1, int var2, Framebuffer var3) throws Throwable {
      if (VisualSpoofRenderer.B()) {
         Framebuffer var8 = VisualSpoofRenderer.f(127872219919683L);
         if (var8 != null) {
            return ScreenShotHelper.saveScreenshot(var0, var1, var2, var8);
         }
      }

      return ScreenShotHelper.saveScreenshot(var0, var1, var2, var3);
   }

   public static void onOptimizeWorldSwapping() {
      if (!Gadgets.betterWorldSwapping.c()) {
         System.gc();
      }
   }

   public static boolean[] onPreMouseInput() {
      if (ExpoClient.w == null) {
         return new boolean[]{false, false, false, false};
      }

      PreMouseInputEvent var8 = new PreMouseInputEvent();
      ExpoClient.w.e(var8, 18670087776179L);
      return var8.M();
   }

   public static void onLoadWorld() {
      GuiEventHooks.onLoadWorld();
      if (ExpoClient.w != null) {
         ExpoClient.w.e(new WorldLoadEvent(), 18670087776179L);
      }
   }

   public static void onRightClickMouse(CallbackInfo var0) {
      if (ExpoClient.w != null) {
         RightClickMouseEvent var7 = new RightClickMouseEvent();
         ExpoClient.w.e(var7, 18670087776179L);
         if (var7.a()) {
            var0.cancel();
         }
      }
   }
}
