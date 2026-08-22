package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.module.Module;











public class InputFix extends Module {
   private static final long a = 116595888514030L;

   public InputFix(short var1, int var2, int var3) {
      super(((((((long)((var1)) << 48) | (((long)((var2)) << 32) >>> 16)) | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 27568344305945L));
      // add code
      this.declare("InputFix", Category.Misc, "Fix some special characters typing");
   }}
