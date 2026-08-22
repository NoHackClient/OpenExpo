package Expo.ASM.Hooks.Network;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ExpoClient;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.SendPacketEvent;
import Expo.internal.accessor.PacketAccessor;
import Expo.internal.jnic.StockCommandRegistry;
import Expo.module.Modules;
import Expo.module.impl.misc.CommandLine;
import Expo.util.packet.OutgoingPacketState;
import Expo.util.packet.PacketManager;
import java.util.Set;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class NetworkManagerHooks {
   private static final long a = 10022116776078L;

   public static void onSendPacket(Packet<?> var0, CallbackInfo var1) {
      if (var0 != null) {
         if (isKnownPacket(PacketAccessor.U, var0)) {
            if (var0 instanceof C01PacketChatMessage && Modules.J(CommandLine.class).o() && ((C01PacketChatMessage)var0).getMessage().startsWith(".")) {
               StockCommandRegistry.E(27284, (char)12607, (char)37714, ((C01PacketChatMessage)var0).getMessage());
               var1.cancel();
            }

            if (PacketManager.v.contains(var0)) {
               PacketManager.v.remove(var0);
            } else {
               SendPacketEvent var13 = new SendPacketEvent(var0);
               ExpoClient.w.e(var13, 18670087776179L);
               if (var13.a()) {
                  var1.cancel();
               } else {
                  OutgoingPacketState.D(0L, var0);
               }
            }
         }
      }
   }

   public static void onReceivePacket(Packet<?> var0, CallbackInfo var1) {
      if (var0 != null) {
         if (isKnownPacket(PacketAccessor.m, var0)) {
            if (PacketManager.a.contains(var0)) {
               PacketManager.a.remove(var0);
            } else {
               ReceivePacketEvent var8 = new ReceivePacketEvent(var0);
               ExpoClient.w.e(var8, 18670087776179L);
               if (var8.a()) {
                  var1.cancel();
               }
            }
         }
      }
   }

   private static boolean isKnownPacket(Set<Class<?>> var0, Packet<?> var1) {
      Class var2 = var1.getClass();

      for (Class var4 : var0) {
         if (var4.isAssignableFrom(var2)) {
            return true;
         }
      }

      return false;
   }
}
