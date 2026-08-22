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
      // add code

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


   private static void a() {
      e[0] = "\u00024-ql.s";
      e[1] = int.class;
      g[1] = "java/lang/Integer";
      e[2] = "DP1[L=sG5Q\u0001\u0019dLoM";
      e[3] = "CD-W\u00076o";
      e[4] = byte.class;
      g[4] = "java/lang/Byte";
      e[5] = void.class;
      g[5] = "java/lang/Void";
      e[6] = "]M\u001dK^;VB\f\u0004?5]I\b^";
      e[7] = "z$\u0019h\u0001h}1U\u0002c\u0003\"c\u0014<]~}o\\x::j3\u001a|F2'$H\u0002\u0000i >N`[e$5$8Hc%5TmYzx_\u001d>\n=|\"B2By\u001bd]y\u0002b}.\u001aiC\u0003";
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
