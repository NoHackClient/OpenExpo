package Expo.module.impl.player;

import Expo.module.Category;

import Expo.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;











public class FreeCam extends Module {
   private EntityOtherPlayerMP r;
   private static final long a = 60812830000416L;
   private static double x;
   private float B;
   private boolean s;
   private float R;
   private static double n;
   private static double T;

   public FreeCam(long var1) {
      super(((a ^ (var1)) ^ 123297006585977L));
      // add code
      this.declare("FreeCam", Category.Player, "This module is currently disabled");
      var1 = a ^ var1;
   }}
