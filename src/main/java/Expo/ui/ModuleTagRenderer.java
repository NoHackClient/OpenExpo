package Expo.ui;

import Expo.ExpoClient;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ModuleTagRendererBinder;
import Expo.event.events.ModuleTagEvent;
import Expo.event.events.Render2DEvent;
import Expo.util.MinecraftRef;
import java.util.List;
import net.minecraft.client.Minecraft;

public class ModuleTagRenderer implements EventSubscriber {
   private static String[] g;
   public static boolean X;
   private static long a;
   private static Object[] e;
   private static Minecraft f;

   static {
      a = 57609484080751L;

      int var13 = 0;

      e = new Object[8];

      g = new String[8];

      f = MinecraftRef.c((byte)var13,0L);

      X = false;
   }

   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 100278348539550L) >>> 32);
      ModuleTagRendererBinder.Q(var4, var3, this);
   }

   public void onRender2D(long var1, Render2DEvent var3) {
      if (X) {
         ModuleTagEvent var8 = new ModuleTagEvent();
         ExpoClient.w.e(var8, 18670087776179L);
         if (var8.a()) {
            return;
         }

         List var9 = var8.i();

         for (int var10 = 0; var10 < var9.size(); var10++) {
            f.fontRendererObj.drawStringWithShadow((String)var9.get(var10), 20.0F, 20 + var10 * f.fontRendererObj.FONT_HEIGHT, -1);
         }
      }
   }
}
