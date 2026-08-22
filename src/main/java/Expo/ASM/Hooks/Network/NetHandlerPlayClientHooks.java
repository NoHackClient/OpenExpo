package Expo.ASM.Hooks.Network;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ExpoClient;
import Expo.event.events.HandleChatEvent;
import Expo.event.events.KnockbackEvent;
import Expo.event.events.PostKnockbackEvent;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S12PacketEntityVelocity;











public class NetHandlerPlayClientHooks {
   private static final long a = 114020735289940L;
   private static final Minecraft g;

   public static void onHandleEntityVelocity(NetHandlerPlayClient var0, S12PacketEntityVelocity var1, CallbackInfo var2) {




      WorldClient var12 = g.theWorld;
      PacketThreadUtil.checkThreadAndEnqueue(var1, var0, g);
      Entity var13 = var12.getEntityByID(var1.getEntityID());
      if (var13.getEntityId() == g.thePlayer.getEntityId()) {
         KnockbackEvent var14 = new KnockbackEvent(var1.getMotionX(), var1.getMotionY(), var1.getMotionZ());
         ExpoClient.w.e(var14, 18670087776179L);
         if (var14.a()) {
            var2.cancel();
            return;
         }

         var13.setVelocity(var14.S() / 8000.0, var14.f() / 8000.0, var14.R() / 8000.0);
         ExpoClient.w.e(new PostKnockbackEvent((char)0, 446144442, 25937), 18670087776179L);
      } else {
         var13.setVelocity(var1.getMotionX() / 8000.0, var1.getMotionY() / 8000.0, var1.getMotionZ() / 8000.0);
      }

      var2.cancel();
   }

   static {
      int var2 = 0;
      g = MinecraftRef.c((byte)var2,0L);
   }

   public static void onProcessEntityVelocity(INetHandlerPlayClient var0, S12PacketEntityVelocity var1, CallbackInfo var2) {
      if (var0 instanceof NetHandlerPlayClient) {
         onHandleEntityVelocity((NetHandlerPlayClient)var0, var1, var2);
      }
   }

   public static void handleChat(S02PacketChat var0) {

      ExpoClient.w.e(new HandleChatEvent(var0.getChatComponent()), 18670087776179L);
   }
}
