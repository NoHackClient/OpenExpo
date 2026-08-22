package Expo.util.packet;

import Expo.module.impl.combat.AutoBlock;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C0APacketAnimation;











public class OutgoingPacketState {
   public static boolean C;
   private static Integer[] c;
   private static Minecraft l;
   public static boolean d;
   public static boolean O;
   public static boolean E;
   public static boolean P;
   private static long a;
   public static boolean h;
   public static boolean R;
   public static boolean T;

   public static boolean f() {
      return !w() && !T && !P && !h;
   }

   public static boolean w() {
      return AutoBlock.c();
   }

   public static void D(long var0, Packet var2) {
      if (var2 instanceof C02PacketUseEntity) {
         E = true;
         if (((C02PacketUseEntity)var2).getAction() == Action.INTERACT) {
            h = true;
         } else if (((C02PacketUseEntity)var2).getHitVec() != null) {
            h = true;
         } else if (((C02PacketUseEntity)var2).getAction() == Action.INTERACT_AT) {
            h = true;
         }
      }

      if (var2 instanceof C07PacketPlayerDigging) {
         P = true;
         R = true;
         if (((C07PacketPlayerDigging)var2).getStatus() == net.minecraft.network.play.client.C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
            C = true;
         }
      }

      if (var2 instanceof C08PacketPlayerBlockPlacement) {
         h = true;
      }

      if (var2 instanceof C09PacketHeldItemChange) {
         if (((C09PacketHeldItemChange)var2).getSlotId() != l.thePlayer.inventory.currentItem) {
            P = true;
         }

         O = true;
      }

      if (var2 instanceof C0APacketAnimation) {
         T = true;
      }

      if (var2 instanceof C03PacketPlayer) {
         E = false;
         if (d) {
            P = true;
            d = false;
         } else {
            P = false;
         }

         h = false;
         T = false;
         C = false;
         O = false;
      }
   }

   public static boolean Y() {
      return !w() && !P && !h;
   }

   static {
      a = 74046264607936L;
      E = false;
      P = false;
      h = false;
      T = false;
      C = false;
      R = false;
      O = false;
      d = false;
      l = MinecraftRef.c((byte)0, 0L);
   }

   public static void J(long var0) {
      d = true;
   }


}
