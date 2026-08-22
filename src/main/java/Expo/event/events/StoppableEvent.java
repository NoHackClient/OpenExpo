package Expo.event.events;

import Expo.event.Event;











public class StoppableEvent extends Event {
   private static long b;
   private boolean Z;

   public boolean p() {
      return this.Z;
   }

   static {
      b = 13431490024297L;
   }

   public StoppableEvent() {
      super();
      this.Z = false;
   }

   public void G() {
      this.Z = true;
   }

}
