package Expo.event.events;

import Expo.event.Event;











public class MoveFlyingEvent extends Event {
   private float U;
   private float c;
   private float r;
   private static final long a = 39007452797302L;

   public void a(float var1) {
      this.c = var1;
   }

   public void T(float var1) {
      this.r = var1;
   }

   public float a$r3() {
      return this.r;
   }

   public void H(float var1) {
      this.U = var1;
   }

   public float p() {
      return this.U;
   }

   public float b() {
      return this.c;
   }

   public MoveFlyingEvent(float var1, float var2, float var3) {
      super();
      this.r = var1;
      this.c = var2;
      this.U = var3;
   }}
