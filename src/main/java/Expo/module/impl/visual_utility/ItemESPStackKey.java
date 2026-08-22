package Expo.module.impl.visual_utility;

import java.util.Objects;

public class ItemESPStackKey {
   public final double l;
   public final double p;
   private final int Q;
   public final double d;
   public final int Z;

   public ItemESPStackKey(int var1, double var2, double var4, double var6) {
      this.Z = var1;
      this.p = var2;
      this.d = var4;
      this.l = var6;
      this.Q = Objects.hash(var1, (int)var2, (int)var4, (int)var6);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ItemESPStackKey var2 = (ItemESPStackKey)var1;
         return this.Z == var2.Z && (int)this.p == (int)var2.p && (int)this.d == (int)var2.d && (int)this.l == (int)var2.l;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.Q;
   }
}
