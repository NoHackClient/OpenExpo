package Expo.util.render;

import Expo.util.MinecraftRef;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;











public class LightningRenderer {
   private static Minecraft E;
   private static String[] c;
   private static String[] b;
   private static long a;



   private static void l(Entity var0) {
      E.theWorld.addWeatherEffect(new EntityLightningBolt(E.theWorld, var0.posX, var0.posY, var0.posZ));
      E.theWorld
         .playSoundAtPos(new BlockPos(var0.posX, var0.posY, var0.posZ), "ambient.weather.thunder", 5.0F, 1.0F, false);
   }

   private static void emitParticleAtEntity(Entity var0, EnumParticleTypes var1) {
      E.effectRenderer.emitParticleAtEntity(var0, var1);
   }



   private static void K(Entity var0, int var1) {
      BlockPos var4 = new BlockPos(var0.posX, var0.posY + var0.getEyeHeight(), var0.posZ);
      E.renderGlobal.playAuxSFX(null, 2001, var4, var1);
      E.renderGlobal.playAuxSFX(null, 2001, var4, var1);
      E.renderGlobal.playAuxSFX(null, 2001, var4, var1);
   }



   private static void f(double var0, double var2, long var4, double var6, double var8, int var10) {
      BlockPos var11 = new BlockPos(var0, var2 + var8, var6);
      E.renderGlobal.playAuxSFX(null, 2001, var11, var10);
      E.renderGlobal.playAuxSFX(null, 2001, var11, var10);
      E.renderGlobal.playAuxSFX(null, 2001, var11, var10);
   }

   private static void playAuxSFX(World var0, double var1, double var5, double var7, double var9) {
      BlockPos var11 = new BlockPos(var1, var5 + var9, var7);
      var0.playAuxSFX(2003, var11, 0);
      var0.playAuxSFX(2003, var11, 0);
      var0.playAuxSFX(2003, var11, 0);
   }

   public static void f(int var0, long var1, double var3, double var5, double var7, double var9) {


      switch (var0) {
         case 0:
         default:
            break;
         case 1:
            f(var3, var5, 119683151908764L, var7, var9, 152);
            break;
         case 2:
            playAuxSFX(E.theWorld, var3, var5, var7, var9);
            break;
         case 3:
            N(var3, var5, var7);
      }
   }

   private static void N(double var0, double var2, double var7) {
      E.theWorld.addWeatherEffect(new EntityLightningBolt(E.theWorld, var0, var2, var7));
      E.theWorld.playSoundAtPos(new BlockPos(var0, var2, var7), "ambient.weather.thunder", 5.0F, 1.0F, false);
   }

   private static void F(Entity var0) {
      BlockPos var3 = new BlockPos(var0.posX, var0.posY + var0.getEyeHeight(), var0.posZ);
      var0.worldObj.playAuxSFX(2003, var3, 0);
      var0.worldObj.playAuxSFX(2003, var3, 0);
      var0.worldObj.playAuxSFX(2003, var3, 0);
   }

   static {
      a = 133900489297638L;
      E = MinecraftRef.c((byte)0, 0L);
   }

   private static void z(Entity var0, EnumParticleTypes var1) {
      var0.getEntityWorld().spawnParticle(var1, var0.posX, var0.posY, var0.posZ, 0.0, 0.0, 0.0, new int[0]);
   }

   public static void E(short var0, Entity var1, int var2, short var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      switch (var2) {
         case 0:
         default:
            break;
         case 1:
            K(var1, 152);
            break;
         case 2:
            F(var1);
            break;
         case 3:
            l(var1);
      }
   }


}
