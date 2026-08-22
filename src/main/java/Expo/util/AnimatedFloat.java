package Expo.util;

import Expo.enums.AnimationDirection;








public class AnimatedFloat {
   private EasedAnimation w = new SmoothStepAnimation(0, 0.0, AnimationDirection.BACKWARDS);
   private float Y;
   private float j;

   public boolean E() {
      return this.j == this.Y || this.w.W();
   }

   public EasedAnimation S() {
      return this.w;
   }

   public void j(float var1, int var2) {
      this.j = (float)(this.Y - this.w.z());
      this.Y = var1;
      if (this.j != this.Y - var1) {
         this.w = new SmoothStepAnimation(var2, this.Y - this.j, AnimationDirection.BACKWARDS);
      }
   }

   public float H() {
      this.j = (float)(this.Y - this.w.z());
      return this.j;
   }
}
