package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.TracersBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.event.events.Render3DEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Teams;
import Expo.module.impl.misc.AntiBot;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.util.EntityUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;


public class Tracers extends Module implements EventSubscriber {
   public static ColorSetting animalsColor;
   public static BooleanSetting enemies;
   // update new version
   public static HeaderSetting colorSettings;
   // update new version
   public static HeaderSetting targetSettings2;
   public static BooleanSetting animals;
   public static ColorSetting friendsColor;
   public static ColorSetting playersColor;
   public static ColorSetting teammatesColor;
   public static BooleanSetting bosses;
   private final List<TracersTarget> Y;
   public static BooleanSetting mobs;
   public static BooleanSetting bots;
   public static BooleanSetting teammates;
   public static BooleanSetting friends;
   public static ModeSetting mode;
   public static BooleanSetting players;
   public static ColorSetting mobsColor;
   public static ColorSetting bossesColor;
   public static ModeSetting colorMode;
   public static ColorSetting enemiesColor;
   private static long c;
   public static ColorSetting botsColor;

   private int u(char var1, int var2, short var3, EntityLivingBase var4) {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ c;
      long var7 = var5 ^ 108364494621445L;
      long var11 = var5 ^ 44979509056248L;
      if (Teams.l(var4)) {
         return friendsColor.k(var11);
      } else if (Teams.Y(var4)) {
         return enemiesColor.k(var11);
      } else if (Teams.g(0L, var4)) {
         return botsColor.k(var11);
      } else {
         return this.u(var7, var4) ? teammatesColor.k(var11) : playersColor.k(var11);
      }
   }

   private boolean c(EntityLivingBase var1) {
      return var1 instanceof IBossDisplayData;
   }

   public void A(long var1) {
      this.Y.clear();
   }

   private Vec3 s(float var1) {
      Vec3 var2 = this.q();
      Vec3 var3 = this.G(var2, var1);
      return new Vec3(var3.xCoord, var3.yCoord + f.getRenderViewEntity().getEyeHeight(), var3.zCoord);
   }


   private boolean u(long var1, EntityLivingBase var3) {
      var1 = c ^ var1;
      int var4 = (int)((var1 ^ 47718613103071L) >>> 48);
      return var3 instanceof EntityPlayer && AntiBot.T((short)var4, (EntityPlayer)var3);
   }

   private boolean B(EntityLivingBase var1) {
      return var1 == f.thePlayer || var1 == f.getRenderViewEntity();
   }

   public void onRender3D(Render3DEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (mode.R("LINE")) {
         Expo.util.render.RenderUtil.L();
         Vec3 var8 = this.s(var1.j);

         for (int var9 = 0; var9 < this.Y.size(); var9++) {
            TracersTarget var10 = this.Y.get(var9);
            this.B(var8, 30320126760008L, var10, var1.j);
         }

         Expo.util.render.RenderUtil.w();
      }
   }


   private int B(long var1, EntityLivingBase var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      int var12 = 34903;

      if (this.Z(23952)) {
         return Teams.d((short)0, var3);
      } else if (this.A(var3)) {
         return this.u((char)0, 821190475, (short)var12, var3);
      } else if (this.c(var3)) {
         return bossesColor.k(96531491288662L);
      } else if (this.x(var3)) {
         return mobsColor.k(96531491288662L);
      } else {
         return this.h(var3) ? animalsColor.k(96531491288662L) : -1;
      }
   }


   private boolean h(long var1, EntityLivingBase var3, TracersFilterFlags var4, short var5) {
      long var6 = (5612624347136L | (long)var5 << 48 >>> 48) ^ c;
      long var8 = var6 ^ 97976797106880L;
      if (this.g(var3)) {
         return false;
      } else {
         return this.B(var3)
            ? false
            : EntityUtil.q(var3, TracersFilterFlags.g(var4), TracersFilterFlags.I(var4), TracersFilterFlags.c(var4), TracersFilterFlags.E(var4), TracersFilterFlags.M(var4), TracersFilterFlags.h(var4), TracersFilterFlags.l(var4), TracersFilterFlags.d(var4), var8);
      }
   }

   private boolean h(EntityLivingBase var1) {
      return var1 instanceof IAnimals && !this.c(var1) && !this.x(var1);
   }

   private Vec3 q() {
      return this.F() ? new Vec3(0.0, 0.0, 1.0) : new Vec3(0.0, 0.0, 0.0);
   }

   private boolean M(TracersFilterFlags var1) {
      return TracersFilterFlags.g(var1) && !TracersFilterFlags.I(var1) && !TracersFilterFlags.c(var1) && !TracersFilterFlags.E(var1);
   }

   private double isSneaking(EntityLivingBase var1) {
      return var1.isSneaking() ? 0.125 : 0.0;
   }


   private boolean g(EntityLivingBase var1) {
      return f.getRenderViewEntity().getDistanceToEntity(var1) > 512.0F;
   }

   private Vec3 G(Vec3 var1, float var2) {
      float var3 = this.getRenderViewEntity(var2);
      float var4 = this.B(var2);
      return var1.rotatePitch((float)(-Math.toRadians(var3))).rotateYaw((float)(-Math.toRadians(var4)));
   }


   private TracersFilterFlags s() {
      return new TracersFilterFlags(players.c(), mobs.c(), animals.c(), bosses.c(), friends.c(), enemies.c(), teammates.c(), bots.c(), null);
   }

   static void $jnicClinit() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {

      c = 87762184973561L;
   }

   private boolean A(EntityLivingBase var1) {
      return var1 instanceof EntityPlayer;
   }

   private float g(float var1, float var2, float var3) {
      return (var2 - var1) * var3 + var1;
   }

   private double D(double var1, double var3, float var5) {
      return var5 * (var3 - var1) + var1;
   }

   private boolean x(EntityLivingBase var1) {
      return var1 instanceof EntityMob && !this.c(var1);
   }

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {




      this.Y.clear();
      TracersFilterFlags var11 = this.s();
      List var12 = EntityUtil.U( this.M(var11));

      for (int var13 = 0; var13 < var12.size(); var13++) {
         EntityLivingBase var14 = (EntityLivingBase)var12.get(var13);
         if (this.h(85641851L, var14, var11, (short)22899)) {
            this.Y.add(new TracersTarget(var14, this.B(21752513251269L, var14), null));
         }
      }
   }

   public Tracers(long var1) {
      super(((c ^ (var1)) ^ 92928119894841L));
      // add code
      this.declare("Tracers", Category.Visual_utility, "Draw lines which traced to players");
      var1 = c ^ var1;
      this.Y = new ArrayList<>();
   }

   private float B(float var1) {
      return this.F()
         ? this.g(f.getRenderViewEntity().prevRotationYaw, f.getRenderViewEntity().rotationYaw, var1)
         : this.g(f.thePlayer.prevCameraYaw, f.thePlayer.cameraYaw, var1);
   }

   private boolean Z(int var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return "TEAM".equals(colorMode.Y());
   }

   private float getRenderViewEntity(float var1) {
      return this.F()
         ? this.g(f.getRenderViewEntity().prevRotationPitch, f.getRenderViewEntity().rotationPitch, var1)
         : this.g(f.thePlayer.prevCameraPitch, f.thePlayer.cameraPitch, var1);
   }

   public final void x(long var1, EventBus var3) {
      TracersBinder.Z(var3, this);
   }

   private void B(Vec3 var1, long var2, TracersTarget var4, float var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      EntityLivingBase var8 = Expo.module.impl.visual_utility.TracersTarget.L(var4);
      double var9 = this.D(var8.lastTickPosX, var8.posX, var5);
      double var11 = this.D(var8.lastTickPosY, var8.posY, var5) - this.isSneaking(var8);
      double var13 = this.D(var8.lastTickPosZ, var8.posZ, var5);
      Expo.util.render.RenderUtil.J(var1, var9, var11 + var8.getEyeHeight(), var13, Expo.module.impl.visual_utility.TracersTarget.Y(var4), 1.5F, 133584403222966L);
   }


   public void onRender2D(long var1, Render2DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (mode.R("ARROW") && f.currentScreen == null) {
         for (int var6 = 0; var6 < this.Y.size(); var6++) {
            TracersTarget var7 = this.Y.get(var6);
            Indicators.F(Expo.module.impl.visual_utility.TracersTarget.L(var7), Expo.module.impl.visual_utility.TracersTarget.Y(var7), var3.r, 50.0, true);
         }
      }
   }

   private boolean F() {
      return f.gameSettings.thirdPersonView == 0;
   }


   static {
      try {
         $jnicClinit();
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var0) {
         throw new RuntimeException(var0);
      }
   }
   static {
      // add code
      mobsColor = new ColorSetting("Mobs-color", "FFFFFF");
      enemiesColor = new ColorSetting("Enemies-color", "FF0000");
      botsColor = new ColorSetting("Bots-color", "FFFFFF");
      animals = new BooleanSetting("Animals", false);
      // update new version
      colorSettings = new HeaderSetting("Color settings");
      // update new version
      targetSettings2 = new HeaderSetting("Target settings");
      mobs = new BooleanSetting("Mobs", false);
      players = new BooleanSetting("Players", true);
      enemies = new BooleanSetting("Enemies", true);
      friendsColor = new ColorSetting("Friends-color", "00FF00");
      friends = new BooleanSetting("Friends", true);
      playersColor = new ColorSetting("Players-color", "FFFFFF");
      animalsColor = new ColorSetting("Animals-color", "FFFFFF");
      bossesColor = new ColorSetting("Bosses-color", "B22222");
      colorMode = new ModeSetting("Color-mode", "TEAM", "CUSTOM");
      teammatesColor = new ColorSetting("Teammates-color", "00FFFF");
      mode = new ModeSetting("Mode", "LINE", "ARROW");
      bosses = new BooleanSetting("Bosses", false);
      teammates = new BooleanSetting("Teammates", true);
      bots = new BooleanSetting("Bots", false);
   }
}
