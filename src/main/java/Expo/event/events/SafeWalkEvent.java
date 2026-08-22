package Expo.event.events;

import Expo.event.Event;

public class SafeWalkEvent extends Event {
   private static long b;
   private boolean S;

   static {}

   public SafeWalkEvent(long var1) {
      super();
      this.S = (b) != 0;
   }

   public void z(boolean var1) {
      this.S = var1;
   }

   public boolean O() {
      return this.S;
   }
}
