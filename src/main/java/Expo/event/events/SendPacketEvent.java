package Expo.event.events;

import Expo.event.Event;
import net.minecraft.network.Packet;











public class SendPacketEvent extends Event {
   private static final long a = 100084488601101L;
   public final Packet<?> B;

   public SendPacketEvent(Packet var1) {
      super();
      this.B = var1;
   }}
