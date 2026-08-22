package Expo.internal.accessor;

import java.io.UnsupportedEncodingException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;











public final class EntityPlayerAccessor {
   private static TypedValueStore Q;
   private static TypedValueStore m;

   public static int J(EntityPlayer var0) {
      return Q.m(var0);
   }



   public static void Z(EntityPlayer var0, ItemStack var1) {
      m.d(var0, var1);
   }

   public static ItemStack o(char var0, EntityPlayer var1, char var2) {
      return (ItemStack)m.v(var1);
   }

   public static void e(long var0, EntityPlayer var2, int var3) {
      Q.T(var2, var3);
   }

   static {
      m = FieldAccessors.X(EntityPlayer.class, "itemInUse", "itemInUse");
      Q = FieldAccessors.X(EntityPlayer.class, "itemInUseCount", "itemInUseCount");
   }

}
