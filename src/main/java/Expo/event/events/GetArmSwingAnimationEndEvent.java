package Expo.event.events;

import Expo.event.Event;

public class GetArmSwingAnimationEndEvent extends Event {
   private int N;
   private static final long a = 53536427542161L;

   public GetArmSwingAnimationEndEvent(int var1, int var4) {
      super();
      this.N = var4;
   }

   public int N() {
      return this.N;
   }

   public void t(int var1) {
      this.N = var1;
   }}
