package Expo.ASM.Hooks.Player;

import Expo.ASM.Hooks.CallbackInfoReturnable;
import Expo.ExpoClient;
import Expo.event.events.HeldItemChangeEvent;
import Expo.event.events.IsPressedEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.event.events.TickEvent;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;


public class KeyBindingHooks {
   private static Minecraft T;
   private static long a;
   private static long b;

   static {
      a = 9888973805939L;
      T = MinecraftRef.c((byte)0, 0L);
      b = -4381557878251585527L;
   }

   public static void onTick(int var0) {

      ExpoClient.w.e(new TickEvent(var0), 18670087776179L);
   }

   public static void isPressed(CallbackInfoReturnable<Boolean> var0, String var1, int var2) {


      IsPressedEvent var13 = new IsPressedEvent(KeyBindUtil.m(32881896332787L, var2), (Boolean)var0.getReturnValue());
      ExpoClient.w.e(var13, 18670087776179L);
      if (var13.a()) {
         var0.setReturnValue(false);
      } else {
         if ((Boolean)var0.getReturnValue()) {
            for (int var14 = 0; var14 < (int)b; var14++) {
               if (T.gameSettings.keyBindsHotbar[var14].getKeyDescription().equals(var1)) {
                  HeldItemChangeEvent var15 = new HeldItemChangeEvent(var14, 0);
                  ExpoClient.w.e(var15, 18670087776179L);
                  if (var15.a()) {
                     var0.setReturnValue(false);
                  }
               }
            }
         }
      }
   }

   public static void onSetKeyBindState(int var0, boolean var1) {

      if (var1 && T.currentScreen == null) {
         ExpoClient.w.e(new SetKeyBindStateEvent(var0), 18670087776179L);
      }
   }

}
