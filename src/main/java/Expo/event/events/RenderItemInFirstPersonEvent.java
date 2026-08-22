package Expo.event.events;

import Expo.event.Event;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;











public class RenderItemInFirstPersonEvent extends Event {
   public final EnumAction d;
   public final float C;
   private static final long a = 140600741017988L;
   public final float J;
   public final ItemStack e;
   public final float U;

   public RenderItemInFirstPersonEvent(EnumAction var1, float var2, int var3, float var4, float var5, char var6, char var7, ItemStack var8) {
      super();
      this.d = var1;
      this.J = var2;
      this.U = var4;
      this.C = var5;
      this.e = var8;
   }}
