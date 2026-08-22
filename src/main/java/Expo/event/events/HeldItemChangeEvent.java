package Expo.event.events;

import Expo.event.Event;











public class HeldItemChangeEvent extends Event {
   private final int i;
   private int t;
   private static final long a = 15357759893676L;

   public void A(int var1) {
      this.t = var1;
   }

   public int j() {
      return this.t;
   }

   public HeldItemChangeEvent(int var3, int var4) {
      super();
      this.t = var3;
      this.i = var4;
   }}
