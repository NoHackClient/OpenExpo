package Expo.event.events;

import Expo.event.Event;
import net.minecraft.network.Packet;

public class ReceivePacketEvent extends Event {
   public final Packet<?> d;
   private static final long a = 37004646980861L;

   public ReceivePacketEvent(Packet var1) {
      super();
      this.d = var1;
   }}
