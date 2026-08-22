package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.module.Module;

public class BarrierVisible extends Module {
   private static final long a = 54421956347826L;

   public void i(long var1) {
      f.renderGlobal.loadRenderers();
   }

   public BarrierVisible(long var1) {
      super(((a ^ (var1)) ^ 34943837037887L));
      this.declare("BarrierVisible", Category.Visual, "Render barriers as glasses");
      var1 = a ^ var1;
   }

   public void A(long var1) {
      f.renderGlobal.loadRenderers();
   }}
