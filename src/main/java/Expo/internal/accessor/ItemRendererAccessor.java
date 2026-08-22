package Expo.internal.accessor;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;











public final class ItemRendererAccessor {
   private static Accessor C;
   private static Accessor h;
   private static Accessor u;
   private static Accessor j;
   private static Accessor T;
   private static Accessor L;
   private static Accessor W;
   private static Accessor y;
   private static Accessor O;
   private static Accessor v;



   public static void x(ItemRenderer var0, EntityPlayerSP var1, float var2) {
      Accessor.v(W, new Object[]{var0, var1, var2});
   }

   public static void v(ItemRenderer var0, AbstractClientPlayer var1, float var2) {
      Accessor.v(h, new Object[]{var0, var1, var2});
   }

   public static void Z(ItemRenderer var0, AbstractClientPlayer var1, float var2, float var3, float var4) {
      Accessor.v(O, new Object[]{var0, var1, var2, var3, var4});
   }

   public static void s(ItemRenderer var0, float var1, float var2) {
      Accessor.v(j, new Object[]{var0, var1, var2});
   }

   public static void E(ItemRenderer var0, float var1, AbstractClientPlayer var2) {
      Accessor.v(L, new Object[]{var0, var1, var2});
   }

   public static void q(ItemRenderer var0, AbstractClientPlayer var1, float var2, float var3) {
      Accessor.v(C, new Object[]{var0, var1, var2, var3});
   }

   public static void U(ItemRenderer var0, float var1) {
      Accessor.v(v, new Object[]{var0, var1});
   }

   static {
      T = MethodAccessors.C(ItemRenderer.class, new Class[]{float.class, float.class}, new String[]{"rotateArroundXAndY", "rotateArroundXAndY"});
      u = MethodAccessors.C(ItemRenderer.class, new Class[]{AbstractClientPlayer.class}, new String[]{"setLightMapFromPlayer", "setLightMapFromPlayer"});
      W = MethodAccessors.C(ItemRenderer.class, new Class[]{EntityPlayerSP.class, float.class}, new String[]{"rotateWithPlayerRotations", "rotateWithPlayerRotations"});
      h = MethodAccessors.C(ItemRenderer.class, new Class[]{AbstractClientPlayer.class, float.class}, new String[]{"performDrinking", "performDrinking"});
      v = MethodAccessors.C(ItemRenderer.class, new Class[]{float.class}, new String[]{"doItemUsedTransformations", "doItemUsedTransformations"});
      C = MethodAccessors.C(ItemRenderer.class, new Class[]{AbstractClientPlayer.class, float.class, float.class}, new String[]{"renderPlayerArm", "renderPlayerArm"});
      y = MethodAccessors.C(ItemRenderer.class, new Class[0], new String[]{"doBlockTransformations", "doBlockTransformations"});
      L = MethodAccessors.C(ItemRenderer.class, new Class[]{float.class, AbstractClientPlayer.class}, new String[]{"doBowTransformations", "doBowTransformations"});
      j = MethodAccessors.C(ItemRenderer.class, new Class[]{float.class, float.class}, new String[]{"transformFirstPersonItem", "transformFirstPersonItem"});
      O = MethodAccessors.C( ItemRenderer.class, new Class[]{AbstractClientPlayer.class, float.class, float.class, float.class}, new String[]{"renderItemMap", "renderItemMap"} );
   }

   public static void e(ItemRenderer var0) {
      Accessor.v(y, new Object[]{var0});
   }

   public static void k(ItemRenderer var0, AbstractClientPlayer var1) {
      Accessor.v(u, new Object[]{var0, var1});
   }

   public static void z(ItemRenderer var0, float var1, float var2) {
      Accessor.v(T, new Object[]{var0, var1, var2});
   }

}
