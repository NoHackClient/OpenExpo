package Expo.event.events;

import Expo.event.Event;











public class RedirectIsUsingItemEvent extends Event {
   float l;
   private boolean r;
   private static long a;

   public RedirectIsUsingItemEvent(float var1) {
      super();
      this.r = false;
      this.l = var1;
   }

   public void W(float var1) {
      this.l = var1;
   }

   public boolean v() {
      return this.r;
   }

   public void I(int var1, long var2) {
      this.r = true;
   }

   public float q() {
      return this.l;
   }

   static {
      a = 72090766616664L;
   }

}
