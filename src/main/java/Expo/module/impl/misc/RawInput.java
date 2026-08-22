package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.module.Module;
import Expo.util.SmoothMouseHelper;
import net.minecraft.util.MouseHelper;

public class RawInput extends Module {
   public static MouseHelper T;
   private SmoothMouseHelper M;
   private static final long a = 55524373878459L;

   public void A(long var1) {
      this.M.M();
      this.M = null;
      MouseHelper var3 = T;
      f.mouseHelper = var3 != null ? var3 : new MouseHelper();
   }

   public void i(long var1) {
      long var3 = var1 ^ 2275773467482L;
      long var5 = var1 ^ 9176379383215L;
      MouseHelper var7 = f.mouseHelper;
      if (!(var7 instanceof SmoothMouseHelper)) {
         T = var7;
      }
      this.M = new SmoothMouseHelper(var3);
      this.M.f(var5);
   }

   public RawInput(long var1) {
      super(((a ^ (var1)) ^ 82691098210477L));
      this.declare("RawInput", Category.Misc, "Fix your mouse input");
      var1 = a ^ var1;
   }}
