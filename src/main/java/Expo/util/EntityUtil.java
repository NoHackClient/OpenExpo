package Expo.util;

import Expo.enums.MegaWallsClass;
import Expo.module.impl.configuration.Teams;
import Expo.module.impl.misc.AntiBot;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;


public class EntityUtil {
   private static String b;
   private static long a;
   private static Minecraft z;



   public static List x(
      List var0,
      boolean var1,
      boolean var2,
      boolean var3,
      boolean var4,
      long var5,
      boolean var7,
      boolean var8,
      boolean var9,
      boolean var10,
      boolean var11,
      boolean var12
   ) {
      long var13 = var5 ^ 67859023805418L;
      ArrayList var15 = new ArrayList(var0.size());
      int var16 = 0;

      for (int var17 = var0.size(); var16 < var17; var16++) {
         EntityLivingBase var18 = (EntityLivingBase)var0.get(var16);
         if (var11 && var18 instanceof EntitySilverfish) {
            var15.add(var18);
         } else if (var12 && var18 instanceof EntityGolem) {
            var15.add(var18);
         } else if (q(var18, var1, var2, var3, var4, var7, var8, var9, var10, var13)) {
            var15.add(var18);
         }
      }

      return var15;
   }


   public static boolean c(long var0, EntityPlayer var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      long var7 = var0 ^ 98569467475870L;
      return !EntityUtil.K( (Entity)var2)
         ? false
         : g(var2, var3, var4, var5, var6, var7);
   }

   public static List J(List var0, boolean var1, boolean var2, boolean var3, int var4, int var5, boolean var6, byte var7) {
      long var8 = ((long)var4 << 32 | (long)var5 << 40 >>> 32 | (long)var7 << 56 >>> 56) ^ a;
      long var10 = var8 ^ 34731660136459L;
      ArrayList var12 = new ArrayList(var0.size());
      int var13 = 0;

      for (int var14 = var0.size(); var13 < var14; var13++) {
         EntityPlayer var15 = (EntityPlayer)var0.get(var13);
         if (c(var10, var15, var1, var2, var3, var6)) {
            var12.add(var15);
         }
      }

      return var12;
   }

   private static char I(Entity var0, long var1) {
      var1 = a ^ var1;
      int var5 = (int)((var1 ^ 3227074822378L) << 48 >>> 48);
      String var6 = ScoreboardUtil.Z( (short)var5, var0.getName(), b);
      return var6.isEmpty() ? '\u0000' : Character.toLowerCase(var6.charAt(0));
   }


   public static List F(double var0, long var2, double var4) {
      var2 = a ^ var2;
      long var6 = var2 ^ 125673106620142L;
      long var10 = var2 ^ 49692991415938L;
      List var12 = z.theWorld.loadedEntityList;
      ArrayList var13 = new ArrayList(var12.size());
      int var14 = 0;

      for (int var15 = var12.size(); var14 < var15; var14++) {
         Entity var16 = (Entity)var12.get(var14);
         if (B(0L, var16) && RaytraceUtil.q(var6, var16, var0) && RotationUtil.b(var10, var16, var4)) {
            var13.add((EntityLivingBase)var16);
         }
      }

      return var13;
   }

   public static boolean B(long var0, Entity var2) {
      return var2 instanceof EntityLivingBase && G((EntityLivingBase)var2);
   }


   public static List p(double var0, double var2, long var4) {
      var4 = a ^ var4;
      long var6 = var4 ^ 126070933336181L;
      long var8 = var4 ^ 50404340262937L;
      List var12 = z.theWorld.playerEntities;
      ArrayList var13 = new ArrayList(var12.size());
      int var14 = 0;

      for (int var15 = var12.size(); var14 < var15; var14++) {
         EntityPlayer var16 = (EntityPlayer)var12.get(var14);
         if (K( var16) && RaytraceUtil.q(var6, var16, var0) && RotationUtil.b(var8, var16, var2)) {
            var13.add(var16);
         }
      }

      return var13;
   }


   public static boolean g(Entity var0, boolean var1, boolean var2, boolean var3, boolean var4, long var5) {



      if (var0 instanceof EntityPlayerSP) {
         return false;
      }

      boolean var14 = Teams.l(var0);
      boolean var15 = Teams.Y(var0);
      boolean var16 = Teams.g(0L, var0) || G(44418141303531L, var0);
      boolean var17 = var0 instanceof EntityPlayer && AntiBot.T((short)0, (EntityPlayer)var0);
      return (!var2 || !var15) && (!var4 || !var17) && (!var1 || !var14) && (!var3 || !var16)
         ? (var4 || !var17) && (var1 || !var14) && (var3 || !var16) && (var2 || !var15)
         : true;
   }

   private static char W(EntityPlayer var0) {
      ScorePlayerTeam var5 = z.theWorld.getScoreboard().getPlayersTeam(var0.getName());
      if (var5 == null) {
         return '\u0000';
      }

      String var6 = ScoreboardUtil.h(var5.getColorPrefix(),0L);
      return var6.isEmpty() ? '\u0000' : Character.toLowerCase(var6.charAt(0));
   }

   public static List M(long var0) {
      var0 = a ^ var0;
      return U( false);
   }

   public static boolean G(Entity var0, boolean var1, boolean var2, boolean var3, boolean var4) {
      if (var0 instanceof EntityPlayerSP) {
         return false;
      } else if (!var1 && !var2 && !var3 && !var4) {
         return false;
      } else {
         boolean var5 = var0 instanceof EntityPlayer;
         boolean var6 = var0 instanceof IBossDisplayData;
         boolean var7 = var0 instanceof EntityMob && !(var0 instanceof IBossDisplayData);
         boolean var8 = var0 instanceof IAnimals && !(var0 instanceof IBossDisplayData) && !(var0 instanceof EntityMob);
         if ((!var1 || !var5) && (!var4 || !var6) && (!var2 || !var7) && (!var3 || !var8)) {
            return (var1 || !var5) && (var4 || !var6) && (var2 || !var7) && (var3 || !var8) ? false : false;
         } else {
            return true;
         }
      }
   }


   public static boolean q(
      Entity var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8, long var9
   ) {


      if (!B(0L, var0)) {
         return false;
      } else {
         return !G(var0, var1, var2, var3, var4) ? false : g(var0, var5, var6, var7, var8, 76556605506086L);
      }
   }

   public static List K(List var0, boolean var1, long var2, boolean var4, boolean var5, boolean var6, boolean var7, boolean var8, boolean var9, boolean var10) {
      long var11 = var2 ^ 105967230444288L;
      ArrayList var13 = new ArrayList(var0.size());
      int var14 = 0;

      for (int var15 = var0.size(); var14 < var15; var14++) {
         EntityLivingBase var16 = (EntityLivingBase)var0.get(var14);
         if (q(var16, var1, var4, var5, var6, var7, var8, var9, var10, var11)) {
            var13.add(var16);
         }
      }

      return var13;
   }


   public static boolean K( Entity var2) {
      return var2 instanceof EntityPlayer && B(0L, var2);
   }

   public static List o(int var0, char var1, short var2, double var3) {
      long var5 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var2 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 136996340050728L;
      List var11 = z.theWorld.playerEntities;
      ArrayList var12 = new ArrayList(var11.size());
      int var13 = 0;

      for (int var14 = var11.size(); var13 < var14; var13++) {
         EntityPlayer var15 = (EntityPlayer)var11.get(var13);
         if (K( var15) && RaytraceUtil.q(var7, var15, var3)) {
            var12.add(var15);
         }
      }

      return var12;
   }


   public static List U( boolean var2) {
      List var7 = z.theWorld.playerEntities;
      ArrayList var8 = new ArrayList(var7.size());
      if (var2) {
         int var14 = 0;

         for (int var15 = var7.size(); var14 < var15; var14++) {
            EntityLivingBase var16 = (EntityLivingBase)var7.get(var14);
            if (K( var16)) {
               var8.add(var16);
            }
         }

         return var8;
      } else {
         List var9 = z.theWorld.loadedEntityList;
         int var10 = 0;

         for (int var11 = var9.size(); var10 < var11; var10++) {
            Entity var12 = (Entity)var9.get(var10);
            if (B(0L, var12)) {
               var8.add((EntityLivingBase)var12);
            }
         }

         return var8;
      }
   }


   static {
      a = 20782887609681L;
      // add code
      z = MinecraftRef.c((byte)0, 0L);
      b = "Wither";
   }

   public static List h(double var0, long var2) {
      long var4 = var2 ^ 32732719327013L;
      List var8 = z.theWorld.loadedEntityList;
      ArrayList var9 = new ArrayList(var8.size());
      int var10 = 0;

      for (int var11 = var8.size(); var10 < var11; var10++) {
         Entity var12 = (Entity)var8.get(var10);
         if (B(0L, var12) && RaytraceUtil.q(var4, var12, var0)) {
            var9.add((EntityLivingBase)var12);
         }
      }

      return var9;
   }

   private static boolean G(EntityLivingBase var0) {
      return var0 != z.thePlayer && !var0.isDead && var0.deathTime <= 0 && var0.getHealth() > 0.0F;
   }


   private static boolean G(long var0, Entity var2) {



      if (z.thePlayer == null || z.theWorld == null || var2 == null) {
         return false;
      }

      if (!HypixelGameState.p() && !HypixelGameState.d()) {
         return false;
      }

      char var9 = W(z.thePlayer);
      if (var9 == 0) {
         return false;
      }

      if (var2 instanceof IBossDisplayData) {
         char var13 = I(var2, 12500867460862L);
         return var13 != 0 && var13 == var9;
      }

      if (var2 instanceof EntityPlayer) {
         MegaWallsClass var10 = MegaWallsClass.s(var2.getName(), 126433336288858L);
         if (var10 != MegaWallsClass.SHEEP && var10 != MegaWallsClass.ANGEL) {
            return false;
         }

         char var11 = W((EntityPlayer)var2);
         return var11 != 0 && var11 == var9;
      } else {
         return false;
      }
   }

   public static List u(int var0, int var1, char var2) {
      List var7 = z.theWorld.playerEntities;
      ArrayList var8 = new ArrayList(var7.size());
      int var9 = 0;

      for (int var10 = var7.size(); var9 < var10; var9++) {
         EntityPlayer var11 = (EntityPlayer)var7.get(var9);
         if (K( var11)) {
            var8.add(var11);
         }
      }

      return var8;
   }

}
