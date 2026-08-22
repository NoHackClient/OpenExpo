package Expo.event.events;

import Expo.event.Event;

public class SetAnglesEvent extends Event {
   private float s;
   private boolean F;
   private float Y;
   private static long a;

   public boolean l() {
      return this.F;
   }

   public float s() {
      return this.Y;
   }

   public void t(float var3) {
      this.F = true;
      this.s = var3;
   }

   public float x() {
      return this.s;
   }

   public SetAnglesEvent(float var3, float var4) {
      super();
      this.F = false;
      this.s = var3;
      this.Y = var4;
   }

   static {
      a = 10061019518693L;
   }

   public void m(float var3) {
      this.F = true;
      this.Y = var3;
   }
}
