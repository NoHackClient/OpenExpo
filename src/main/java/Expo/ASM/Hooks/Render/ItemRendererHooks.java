package Expo.ASM.Hooks.Render;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ExpoClient;
import Expo.event.events.RenderItemInFirstPersonEvent;
import Expo.internal.accessor.ItemRendererAccessor;
import Expo.module.ModuleManager;
import Expo.module.Modules;
import Expo.module.impl.combat.BlockHit;
import Expo.module.impl.combat.KillAura;
import Expo.module.impl.visual.Animations;
import Expo.module.impl.world.Scaffold;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;


public class ItemRendererHooks {
   private static Map e;
   private static Integer[] d;
   private static String b;
   private static long a;
   private static long[] c;

   public static void onFunc_178110_a(CallbackInfo var0) {
      if (Animations.noRotationsEffect.c() && ModuleManager.d.o()) {
         GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
         var0.cancel();
      }
   }


   private static boolean shouldSpoofScaffoldItem() {
      Scaffold var0 = Modules.J(Scaffold.class);
      return var0 != null && var0.o() && Scaffold.fakeItem != null && Scaffold.fakeItem.c();
   }



   public static boolean onUpdateEquippedItemLastPart(float var0) {
      return shouldSpoofScaffoldItem()
         || Animations.noEquipReset.c() && ModuleManager.d.o() && (KillAura.a || BlockHit.N || ModuleManager.I != null && ModuleManager.I.o())
         || var0 < 0.1F;
   }

   public static float transformFirstPersonItemEquipProgress(float var0) {
      return !Animations.noEquipReset.c() || !ModuleManager.d.o() || !KillAura.a && !BlockHit.N && !(ModuleManager.I != null && ModuleManager.I.o()) ? var0 : 0.0F;
   }

   public static ItemStack spoofScaffoldItemStack(Minecraft var0, ItemStack var1) {
      if (shouldSpoofScaffoldItem() && var0 != null && var0.thePlayer != null) {
         int var4 = Modules.J(Scaffold.class).q();
         return var4 >= 0 && var4 < 9 ? var0.thePlayer.inventory.getStackInSlot(var4) : var1;
      } else {
         return var1;
      }
   }

   static {
      a = 40650873301534L;
      b = "NONE";
      e = new HashMap(13);
      c = new long[]{2015442937976616169L, -1488955058342560494L};
      d = new Integer[2];
   }

   public static void renderItemInFirstPerson(ItemRenderer var0, Minecraft var1, ItemStack var2, float var3, float var4, float var5, CallbackInfo var6) {




      if (ModuleManager.d.o()) {
         Animations.C();
         Animations.U();
         Animations.J();
      }

      if (ModuleManager.d.o() && !Animations.mode.R(b)) {
         float var14 = 1.0F - (var3 + (var4 - var3) * var5);
         EntityPlayerSP var15 = var1.thePlayer;
         float var16 = var15.getSwingProgress(var5);
         float var17 = var15.prevRotationPitch + (var15.rotationPitch - var15.prevRotationPitch) * var5;
         float var18 = var15.prevRotationYaw + (var15.rotationYaw - var15.prevRotationYaw) * var5;
         ItemRendererAccessor.z(var0, var17, var18);
         ItemRendererAccessor.k(var0, var15);
         ItemRendererAccessor.x(var0, var15, var5);
         GlStateManager.enableRescaleNormal();
         GlStateManager.pushMatrix();
         if (var2 == null) {
            if (!var15.isInvisible()) {
               ItemRendererAccessor.q(var0, var15, var14, var16);
            }
         } else {
            if (var2.getItem() instanceof ItemMap) {
               ItemRendererAccessor.Z(var0, var15, var17, var14, var16);
            } else if (var15.getItemInUseCount() > 0) {
               EnumAction var19 = var2.getItemUseAction();
               RenderItemInFirstPersonEvent var20 = new RenderItemInFirstPersonEvent(var19, var14, 7675, var5, var16, (char)19417, (char)46780, var2);
               ExpoClient.w.e(var20, 18670087776179L);
               if (!var20.a()) {
                  if (var19 == EnumAction.NONE) {
                     ItemRendererAccessor.s(var0, var14, 0.0F);
                  } else if (var19 == EnumAction.EAT || var19 == EnumAction.DRINK) {
                     ItemRendererAccessor.v(var0, var15, var5);
                     ItemRendererAccessor.s(var0, 0.2F, var16);
                     GlStateManager.translate(0.0F, 0.3F, 0.0F);
                  } else if (var19 == EnumAction.BLOCK) {
                     GlStateManager.translate(0.56F, -0.52F, -0.72F);
                     GlStateManager.translate(0.0F, -0.05F, 0.0F);
                     GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
                     float var21 = MathHelper.sin(var16 * var16 * (float) Math.PI);
                     float var22 = MathHelper.sin(MathHelper.sqrt_float(var16) * (float) Math.PI);
                     GlStateManager.rotate(var21 * -20.0F, 0.0F, 1.0F, 0.0F);
                     GlStateManager.rotate(var22 * -20.0F, 0.0F, 0.0F, 1.0F);
                     GlStateManager.rotate(var22 * -80.0F, 1.0F, 0.0F, 0.0F);
                     GlStateManager.scale(0.35F, 0.35F, 0.35F);
                     ItemRendererAccessor.e(var0);
                     GlStateManager.translate(-0.5F, 0.2F, 0.0F);
                  } else if (var19 == EnumAction.BOW) {
                     ItemRendererAccessor.s(var0, 0.2F, var16);
                     ItemRendererAccessor.E(var0, var5, var15);
                     GlStateManager.translate(0.0F, 0.2F, 0.0F);
                  }
               }
            } else {
               ItemRendererAccessor.U(var0, var16);
               ItemRendererAccessor.s(var0, var14, var16);
            }

            Animations.U();
            var0.renderItem(var15, var2, TransformType.FIRST_PERSON);
         }

         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
         RenderHelper.disableStandardItemLighting();
         var6.cancel();
      }
   }

   public static boolean onUpdateEquippedItemLastPartAllowed() {
      return shouldSpoofScaffoldItem()
         || Animations.noEquipReset.c() && ModuleManager.d.o() && (KillAura.a || BlockHit.N || ModuleManager.I != null && ModuleManager.I.o());
   }

   public static int spoofScaffoldItemSlot(Minecraft var0, int var1) {
      if (shouldSpoofScaffoldItem() && var0 != null && var0.thePlayer != null) {
         int var4 = Modules.J(Scaffold.class).q();
         return var4 >= 0 && var4 < 9 ? var4 : var1;
      } else {
         return var1;
      }
   }

}
