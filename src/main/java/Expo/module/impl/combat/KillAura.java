package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.KillAuraBinder;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.Render3DEvent;
import Expo.event.events.SetAnglesEvent;
import Expo.internal.accessor.RenderManagerAccessor;
import Expo.module.Modules;
import Expo.module.PriorityModule;
import Expo.module.impl.configuration.Theme;
import Expo.module.impl.movement.NoSlow;
import Expo.module.impl.visual.Freelook;
import Expo.module.impl.world.BedNuker;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.CombatUtil;
import Expo.util.EntityUtil;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import Expo.util.Pair;
import Expo.util.RaytraceUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import Expo.util.TimerUtil;
import Expo.util.packet.OutgoingPacketState;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class KillAura extends PriorityModule implements EventSubscriber {
   public static ModeSetting mode;
   public static TimerUtil I;
   private long C;
   private static Minecraft e;
   private double O;
   public static EntityLivingBase H6;
   private static Long[] qb;
   public static BooleanSetting showReachRing;
   public static NumberSetting attackRange;
   public static PercentageSetting rotationSmoothing;
   public static BooleanSetting players;
   public static ColorSetting customColor;
   public static BooleanSetting requireSword;
   public static BooleanSetting teammates;
   public static BooleanSetting requireClick;
   public static ModeSetting showTarget;
   private static Map rb;
   private boolean B;
   public static BooleanSetting enemies;
   private Pair<Float, Float> H7;
   public static BooleanSetting silverfishes;
   private static String[] gb;
   private static long bb;
   public static BooleanSetting animals;
   private static long[] pb;
   private static String[] hb;
   public static NumberSetting swingRange;
   public static NumberSetting minAPS;
   public static TimerUtil y;
   private boolean U;
   public static NumberSetting maxAPS;
   public static BooleanSetting throughWall;
   public static BooleanSetting golems;
   public static NumberSetting switchDelay;
   public static BooleanSetting screenCheck;
   private int m;
   public static BooleanSetting bots;
   public static ColorSetting showTargetDamageColor;
   private static Map ib;
   public static HeaderSetting blinkAutoblocksOnlyWorksInHypixel;
   public static PercentageSetting showTargetOpacity;
   public static NumberSetting angleStep;
   public static ModeSetting showTargetColor;
   public static ModeSetting moveFix;
   public static HeaderSetting targetSettings;
   public static ModeSetting sort;
   public static BooleanSetting friends;
   private static Map ob;
   private static long[] mb;
   public static BooleanSetting legit;
   private boolean t;
   public static NumberSetting fov;
   public static boolean x;
   public static boolean a;
   public static long b;
   public static ModeSetting rotation;
   public static BooleanSetting bosses;
   public static BooleanSetting mobs;

   public void onRender3D(Render3DEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var12 = 11654630;
      int var15 = 54127;

      if (showReachRing.c()) {
         Expo.util.render.RenderUtil.s(e.thePlayer, attackRange.L(), 45, 1.5F, -1, 24156, (char)1469, (char)var15);
      }

      if (H6 != null) {
         int var20;
         switch (showTargetColor.Y()) {
            case "THEME":
               var20 = Theme.n(137969810508066L, 0.0, (int)(showTargetOpacity.k() / 100.0F * 255.0F));
               break;
            case "THEME_CUSTOM":
               var20 = Theme.e(0.0, (int)(showTargetOpacity.k() / 100.0F * 255.0F), 87512972878444L);
               break;
            default:
               var20 = customColor.x( (int)(showTargetOpacity.k() / 100.0F * 255.0F));
         }

         switch (showTarget.Y()) {
            case "BOX":
               Expo.util.render.RenderUtil.N(H6, 138251344894190L, var20);
               break;
            case "BOX_WITH_DAMAGE":
               if (H6.hurtTime > 5) {
                  var20 = showTargetDamageColor.x( (int)(showTargetOpacity.k() / 100.0F * 255.0F));
               }

               Expo.util.render.RenderUtil.N(H6, 138251344894190L, var20);
               break;
            case "HEAD_BOX":
               Expo.util.render.RenderUtil.R(H6, 47843098999105L, var20, var1.j);
               break;
            case "HEAD_BOX_WITH_DAMAGE":
               if (H6.hurtTime > 5) {
                  var20 = showTargetDamageColor.x( (int)(showTargetOpacity.k() / 100.0F * 255.0F));
               }

               Expo.util.render.RenderUtil.R(H6, 47843098999105L, var20, var1.j);
               break;
            case "RING":
               this.C(9764, (byte)75, var12, H6, var20, var1.j);
         }
      }
   }

   public String g(long var1) {
      return mode.Y();
   }

   public final void x(long var1, EventBus var3) {
      KillAuraBinder.e(var3, this);
   }

   private float k(Entity var1, int var2) {
      if (var1 == null) {
         return 0.0F;
      }

      long var7 = y.p();
      long var9 = I.p();
      if (I.Q(1000L)) {
         this.t = ((0 & 1) != 0);
      }

      if (!this.t) {
         if (var7 < 200L) {
            return (float)var7 / 200.0F;
         }

         this.t = ((1 & 1) != 0);
         return 1.0F;
      } else {
         return var9 > 800L ? Math.max(0.0F, (float)(1000L - var9) / 200.0F) : 1.0F;
      }
   }

   private void q(EntityLivingBase var1, int var2, int var3) {
      long var4 = ((long)var2 << 32 | (long)var3 << 32 >>> 32) ^ bb;
      long var6 = var4 ^ 84560587536236L;
      int var10 = (int)((var4 ^ 100821469610507L) >>> 32);
      int var11 = (int)((var4 ^ 100821469610507L) << 32 >>> 48);
      int var12 = (int)((var4 ^ 100821469610507L) << 48 >>> 48);
      long var13 = var4 ^ 55425579471998L;
      long var15 = var4 ^ 13657711759795L;
      long var17 = var4 ^ 122091008712958L;
      this.U = this.S(var1, var10, (short)var11, (char)var12);
      if (AutoBlock.t(var6) == 1) {
         AutoBlock.V(0L);
      } else if (b <= 0L) {
         boolean var19;
         if (RaytraceUtil.i(var1, attackRange.L(), var13, !throughWall.c())) {
            var19 = this.Y(var15, var1);
         } else {
            var19 = this.E(var1, var17);
         }

         if (var19) {
            b = b + MathUtil.e(this.N()[0], this.N()[1]);
         }
      }
   }

   private double g(double var1, double var3) {
      double var5 = Math.max(var1, var3);
      return Math.max(var1, var3) > attackRange.L() ? var5 : attackRange.L();
   }

   private void Y(int var1, char var2, int var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ bb;
      long var8 = var4 ^ 90561222524791L;
      this.T(false);
      this.H7 = null;
      this.m = 0;
      this.U = ((0 & 1) != 0);
      b = 0L;
      this.C = 0L;
      a = ((0 & 1) != 0);
      H6 = null;
      x = ((0 & 1) != 0);
      if (this.B) {
         RotationManager.k(0L);
         RotationManager.O(var8);
         this.B = ((0 & 1) != 0);
      }
   }

   private boolean isGetKeyCode(long var1) {
      if (!this.Y()) {
         return false;
      }

      if (requireSword.c() && !ItemUtil.d()) {
         return false;
      }

      if (requireClick.c() && !KeyBindUtil.V(e.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L)) {
         return false;
      }

      if (screenCheck.c() && e.currentScreen != null) {
         return false;
      }

      if (e.thePlayer.isDead) {
         return false;
      }

      EntityLivingBase var7 = this.b(14517823015178L);
      H6 = var7;
      return var7 != null;
   }

   private boolean y(char var1, char var2, int var3, EntityLivingBase var4, boolean var5) {
      long var6 = ((long)var1 << 48 | (long)var2 << 48 >>> 16 | (long)var3 << 32 >>> 32) ^ bb;
      int var8 = (int)((var6 ^ 132025568960591L) >>> 56);
      int var9 = (int)((var6 ^ 132025568960591L) << 8 >>> 32);
      int var10 = (int)((var6 ^ 132025568960591L) << 40 >>> 40);
      int var11 = (int)((var6 ^ 55635610132039L) >>> 32);
      int var12 = (int)((var6 ^ 55635610132039L) << 32 >>> 48);
      int var13 = (int)((var6 ^ 55635610132039L) << 48 >>> 48);
      int var14 = (int)((var6 ^ 123716592291462L) >>> 32);
      long var15 = (var6 ^ 123716592291462L) << 32 >>> 32;
      if (legit.c()) {
         KeyBindUtil.T(var11, (short)var12, e.gameSettings.keyBindAttack.getKeyCode(), (short)var13);
         return true;
      } else if (OutgoingPacketState.E) {
         return true;
      } else if (this.U && (!var5 || !e.thePlayer.isUsingItem() && !OutgoingPacketState.h && !OutgoingPacketState.P)) {
         this.b(false, (byte)var8, var9, var10);
         return CombatUtil.I(var4, var14, var15);
      } else {
         return false;
      }
   }

   private void g(PreMouseInputEvent var1) {
      if (OutgoingPacketState.T) {
         var1.Q(true);
         var1.G(true);
         var1.M(true);
      }
   }

   private EntityLivingBase b(long var1) {
      double var7 = attackRange.L();
      HashSet var9 = new HashSet();
      List var10;
      if (rotation.R("MANUAL")) {
         var10 = RaytraceUtil.j(var7);
      } else {
         var10 = EntityUtil.F(var7, 84864282554303L, fov.L());
      }

      List<EntityLivingBase> var11 = EntityUtil.x(var10, players.c(), mobs.c(), animals.c(), bosses.c(), 50993518959776L, friends.c(), enemies.c(), teammates.c(), bots.c(), silverfishes.c(), golems.c());
      if (!throughWall.c()) {
         var11.removeIf(var3x -> {
            long var4 = bb ^ 23825246438246L;
            long var6 = var4 ^ 47455069962948L;
            if (RaytraceUtil.V(var3x, var6, var7)) {
               var9.add(var3x);
               return true;
            } else {
               return false;
            }
         });
      }

      if (var11.isEmpty()) {
         double var12 = this.w$r1();
         if (rotation.R("MANUAL")) {
            var10 = RaytraceUtil.j(var12);
         } else {
            var10 = EntityUtil.F(var12, 84864282554303L, fov.L());
         }

         var11 = EntityUtil.x(var10, players.c(), mobs.c(), animals.c(), bosses.c(), 50993518959776L, friends.c(), enemies.c(), teammates.c(), bots.c(), silverfishes.c(), golems.c());
         if (!throughWall.c()) {
            var11.removeIf(var3x -> {
               long var4 = bb ^ 49181704316728L;
               long var6 = var4 ^ 20035012437658L;
               return var9.contains(var3x) || RaytraceUtil.V(var3x, var6, var12);
            });
         }
      }

      if (var11.isEmpty()) {
         return null;
      }

      this.V(var11);
      switch (mode.Y()) {
         case "SINGLE":
            return (EntityLivingBase)var11.get(0);
         case "SWITCH":
            this.m = MathUtil.k(this.m, 0, var11.size() - 1);
            if (x && this.C <= 0L) {
               if (this.m + 1 >= var11.size()) {
                  this.m = 0;
               } else {
                  this.m++;
               }

               this.C = this.C + (long)switchDelay.L();
               x = ((0 & 1) != 0);
            }

            return (EntityLivingBase)var11.get(this.m);
         default:
            return (EntityLivingBase)var11.get(0);
      }
   }

   private double w$r1() {
      return this.g(swingRange.L(), this.g(swingRange.L(), attackRange.L()));
   }

   private boolean S(EntityLivingBase var1, int var2, short var3, char var4) {
      long var5 = ((long)var2 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ bb;
      long var7 = var5 ^ 111426794559433L;
      long var9 = var5 ^ 76239945710532L;
      int var15 = (int)((var5 ^ 133601039957796L) >>> 48);
      int var16 = (int)((var5 ^ 133601039957796L) << 16 >>> 48);
      long var18 = var5 ^ 134387840172397L;
      if (BedNuker.y) {
         return false;
      }

      if (!rotation.R("LOCK")) {
         RotationManager.k(0L);
      }

      switch (moveFix.Y()) {
         case "SILENT":
            RotationManager.n(RotationMode.SILENT);
            break;
         case "STRICT":
            RotationManager.n(RotationMode.STRICT);
            break;
         case "NONE":
            RotationManager.n(RotationMode.NONE);
      }

      double var27 = this.r(var9, var1);
      switch (rotation.Y()) {
         case "MANUAL":
            return RaytraceUtil.j(var27).contains(var1);
         case "NONE":
            return true;
         default:
            if (rotation.R("NO_RENDER")) {
               RotationManager.w(true);
            }

            float var24 = angleStep.L();
            float var25 = rotationSmoothing.k() / 100.0F;
            float[] var26 = throughWall.c() ? RotationUtil.J(var1, var18, var27) : RotationUtil.p(var1, var27, var7);
            RotationManager.L( var26[0], var26[1], var24, var25);
            this.H7 = new Pair<>(RotationManager.r, RotationManager.G);
            if (rotation.R("LOCK") && !Freelook.c()) {
               RotationManager.W((short)var15, (short)var16, this.H7.a(), this.H7.p());
            } else {
               RotationManager.k(0L);
            }

            this.B = ((1 & 1) != 0);
            return RaytraceUtil.j(var27).contains(var1);
      }
   }

   private double[] N() {
      return new double[]{MathUtil.q(minAPS.L(), 1.0F, maxAPS.L()), MathUtil.q(maxAPS.L(), 1.0F, maxAPS.L())};
   }

   private boolean Y(long var1, EntityLivingBase var3) {
      var1 = bb ^ var1;
      int var4 = (int)((var1 ^ 91056883982322L) >>> 48);
      int var5 = (int)((var1 ^ 91056883982322L) << 16 >>> 48);
      int var6 = (int)((var1 ^ 91056883982322L) << 32 >>> 32);
      boolean var7 = this.y((char)var4, (char)var5, var6, var3, true);
      if (var7) {
         x = ((1 & 1) != 0);
      }

      return var7;
   }

   private boolean b(boolean var1, byte var2, int var3, int var4) {
      long var5 = ((long)var2 << 56 | (long)var3 << 32 >>> 8 | (long)var4 << 40 >>> 40) ^ bb;
      int var7 = (int)((var5 ^ 13024498382582L) >>> 32);
      int var8 = (int)((var5 ^ 13024498382582L) << 32 >>> 48);
      int var9 = (int)((var5 ^ 13024498382582L) << 48 >>> 48);
      if (!OutgoingPacketState.E && !OutgoingPacketState.T) {
         if (!var1 || !e.thePlayer.isUsingItem() && !OutgoingPacketState.h && !OutgoingPacketState.P) {
            if (!legit.c()) {
               e.thePlayer.swingItem();
            }

            KeyBindUtil.T(var7, (short)var8, e.gameSettings.keyBindAttack.getKeyCode(), (short)var9);
            return true;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   private void C(int var1, byte var2, int var3, Entity var4, int var5, float var6) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var7 = ((long)var1 << 32 | (long)var2 << 56 >>> 32 | (long)var3 << 40 >>> 40) ^ bb;
      long var9 = var7 ^ 1262679441742L;
      int var15 = (int)((var7 ^ 93193192819373L) >>> 32);
      int var18 = (int)((var7 ^ 45337844945094L) >>> 32);
      double var21 = this.O;
      this.O = this.O + 0.8 * Expo.util.ClientUtil.H(var9) * 0.05;
      float var23 = (float)(var4.getEntityBoundingBox().maxY - var4.getEntityBoundingBox().minY);
      double var24 = var21 + (this.O - var21) * var6;
      double var26 = Math.abs(1.0 + Math.sin(var24 - 0.5)) / 2.0;
      double var28 = Math.abs(1.0 + Math.sin(var24)) / 2.0;
      double var30 = var4.lastTickPosX + (var4.posX - var4.lastTickPosX) * var6 - RenderManagerAccessor.k(0L, e.getRenderManager());
      double var32 = var4.lastTickPosY
         + (var4.posY - var4.lastTickPosY) * var6
         - RenderManagerAccessor.y(var15, e.getRenderManager())
         + var26 * var23;
      double var34 = var4.lastTickPosZ + (var4.posZ - var4.lastTickPosZ) * var6 - RenderManagerAccessor.W(0L, e.getRenderManager());
      double var36 = var4.lastTickPosY
         + (var4.posY - var4.lastTickPosY) * var6
         - RenderManagerAccessor.y(var15, e.getRenderManager())
         + var28 * var23;
      GL11.glPushMatrix();
      GL11.glDisable(2884);
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glDisable(2929);
      GL11.glDisable(3008);
      GL11.glShadeModel(7425);
      GL11.glBegin(8);
      Color var39 = new Color(var5);

      for (int var38 = 0; var38 <= 360; var38++) {
         float var40 = this.k(var4, var18);
         GL11.glColor4f(var39.getRed() / 255.0F, var39.getGreen() / 255.0F, var39.getBlue() / 255.0F, 0.6F * var40);
         GL11.glVertex3d(
            var30 + Math.cos(Math.toRadians(var38)) * var4.width * 0.8, var36, var34 + Math.sin(Math.toRadians(var38)) * var4.width * 0.8
         );
         GL11.glColor4f(var39.getRed() / 255.0F, var39.getGreen() / 255.0F, var39.getBlue() / 255.0F, 0.01F * var40);
         GL11.glVertex3d(
            var30 + Math.cos(Math.toRadians(var38)) * var4.width * 0.8, var32, var34 + Math.sin(Math.toRadians(var38)) * var4.width * 0.8
         );
      }

      GL11.glEnd();
      GL11.glEnable(2848);
      GL11.glBegin(2);

      for (int var41 = 0; var41 <= 360; var41++) {
         float var42 = this.k(var4, var18);
         GL11.glColor4f(var39.getRed() / 255.0F, var39.getGreen() / 255.0F, var39.getBlue() / 255.0F, 0.8F * var42);
         GL11.glVertex3d(
            var30 + Math.cos(Math.toRadians(var41)) * var4.width * 0.8, var36, var34 + Math.sin(Math.toRadians(var41)) * var4.width * 0.8
         );
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glEnable(3008);
      GL11.glEnable(2929);
      GL11.glShadeModel(7424);
      GL11.glDisable(3042);
      GL11.glEnable(2884);
      GL11.glPopMatrix();
      GlStateManager.resetColor();
   }

   public void onSetAngles(long var1, SetAnglesEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!a || !rotation.R("LOCK")) {
         this.H7 = null;
      } else if (!Freelook.c() && this.H7 != null) {
         var3.I(21307, 3074332907L);
      }
   }

   private static String b(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 6534;
      if (hb[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])ib.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               ib.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/module/impl/combat/KillAura", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = gb[var5].getBytes("ISO-8859-1");
         hb[var5] = b(((Cipher)var4[0]).doFinal(var9));
      }

      return hb[var5];
   }

   private static String b(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private void K(int var1, int var2) {
      if (b > 0L) {
         b = b - 50L;
      }

      if (this.C > 0L) {
         this.C = this.C - 50L;
      }
   }

   private static boolean Q(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return Modules.J(NoSlow.class).o() && NoSlow.swordMode.R("VANILLA") && NoSlow.slowDown.k() < 100;
   }

   private static boolean u(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var0 = bb ^ var0;
      return Modules.J(KeepSprint.class).o() && KeepSprint.mode.R(b(14809, 3518532176668270889L ^ var0));
   }

   public void onPreMouseInput(long var1, PreMouseInputEvent var3) {
      int var13 = 3014;
      this.K(15041, 61729);
      boolean var14 = this.isGetKeyCode(64011875398272L);
      if (!var14) {
         this.Y(31990, (char)54655, var13);
         this.g(var3);
      } else {
         this.T(true);
         a = ((1 & 1) != 0);
         this.q(H6, 18356, -467341094);
         this.g(var3);
      }
   }

   public KillAura(long var1) {
      super((((bb ^ (var1)) ^ 113695299397976L) >>> 16), (char)((int)(((((bb ^ (var1)) ^ 113695299397976L) << 48) >>> 48))));
      this.declare("KillAura", Category.Combat, "Attack entities in range");
      var1 = bb ^ var1;
      this.B = ((0 & 1) != 0);
      this.U = ((0 & 1) != 0);
      this.C = 0L;
      this.m = 0;
      this.t = ((0 & 1) != 0);
      this.H7 = null;
   }

   static {
      bb = 71823488026878L;
      I = new TimerUtil();
      y = new TimerUtil();
      a = ((0 & 1) != 0);
      x = ((0 & 1) != 0);
      b = 0L;
      e = MinecraftRef.c((byte)0, 0L);
      ib = new HashMap(13);
      gb = new String[]{"\u00d7\u00f2%\u00ca\u00cb*w\u0019", "Ki\u00b9\u00a3_\u00ee?\u0081\u00b9\u00f2\u00f9\u00fb\u00ca\u000e\u008b\u00fd", "B\u0091\u009bEsj\u00dc1", "\u00da\u00c5q\u00f2\u00e0\u00ff\u00c3\u00db", "\u00d1\u0092`\u0017\u00c2\u008dx\u0012", "\u0008\u00df\u00acx\u00eb\u00eeP*", "f\u0090\u0090\u00c0\u00f1\u00ae\u00b1\u00ce\u00ad\u0081\u0097\u0083\u00be\u00ec\u00f2\u00d6\u00daM\u00c2\u0092Zt\u00fc\n", "\u001c\u00bf\u00e6\u00c5\u00cem\u0002\u007fT\\`\u00ab^\r7\u0002", "-\u00b4\u00d3\u0003\u0094\u0086\u00f7@", "L \u00aarP\u001f77", "\u00a5\u00ebN\u0015\u0098\u001cj\u00eai-\u008c/\u00d5m\u00cb\u009c", "\u0085\u001b\u009d\u00b1\u00b3g#\u000f", "\u000c\u00f4\u00fa\u00d2\u00f2\u0097F\u00c4", "1nWI\u00b7\\o\u0005\u001aF\u008e\u00e1\u00b0\u00cc\u00a2F", "\\\u00a5\"\u0017\u00ea2\u0093\u00c7", "\u00de\u0098\u0089\u00e0A\u008b\u00f9\u0090", "\"\u00c0x\u00d8\u009d\u00ea\u00ed3", "\u00e3\u0090\u0011!\u00d1\u00f2\u008f\u00f4\u00ed\u00a8\u00cd\u00a3\u00de\tI\u00de", "\u008d\u0015W\u00b2\u00da\u0013\u00f3F", "\u00fe\u0019\u008fv S\u00fe\u00a8", "\u0015\u00a0\u0001\u0010\u0083\u00cdYIr\u00baJ\u0096d6\u00e2}", "s9\u00d0p\u009d\u0092\u00df ", "\u00d3\u00d8\u00cb\u00e2\u00b9P\u009eK", "\u00e2\u00b3$\u00cc\u00aa+\u00ec\u00a2"};
      hb = new String[24];
      ob = new HashMap(13);
      mb = new long[]{4222651748112354234L, 1358759797698752134L, 6027566444030759557L, 6531994159110063519L, 4352892789842412532L, 4215198685318417151L, 4265623248262754419L, -8484462168072695861L, -4975961977512736667L, 5684919308909557051L, -4714451467247381270L, -7242730324709650935L, -1859447380455794541L, -5814833442236119007L, 8426417971733715765L, -3487626619764442578L, -8826877039400193592L, 2162156720661625334L, 2895595605367363828L, 3335883105485980950L, -1828796396622648081L, 5888032080245728409L, 4083632905029626501L};
      rb = new HashMap(13);
      pb = new long[]{8087929143410548367L, 4563174337710790695L, -8179287428373695482L, -5236949487611815791L, -2593048032935943668L, 7859560633349231800L};
      qb = new Long[6];
   }

   public void L(PreMouseInputEvent var1, long var2) {
      int var4 = (int)((var2 ^ 54577294223149L) >>> 32);
      int var5 = (int)((var2 ^ 54577294223149L) << 32 >>> 48);
      int var6 = (int)((var2 ^ 54577294223149L) << 48 >>> 48);
      this.Y(var4, (char)var5, var6);
   }

   private void V(List<EntityLivingBase> var1) {
      var1.sort(
         Comparator.comparingDouble(
            var0 -> {
               switch (sort.Y()) {
                  case "VIEW":
                     return RotationUtil.g((Entity)var0);
                  case "HEALTH":
                     return var0.getHealth() + var0.getAbsorptionAmount();
                  case "HURT_TIME":
                     return var0.hurtResistantTime;
                  case "ARMOR":
                     return var0.getTotalArmorValue();
                  default:
                     return RaytraceUtil.i(var0);
               }
            }
         )
      );
   }

   public static boolean e(long var0, EntityLivingBase var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return H6 == var2 && !showTarget.R("NONE");
   }

   private boolean E(EntityLivingBase var1, long var2) {
      var2 = bb ^ var2;
      int var4 = (int)((var2 ^ 9829324015118L) >>> 56);
      int var5 = (int)((var2 ^ 9829324015118L) << 8 >>> 32);
      int var6 = (int)((var2 ^ 9829324015118L) << 40 >>> 40);
      long var7 = var2 ^ 30996639530110L;
      boolean var9 = RaytraceUtil.i(var1, swingRange.L(), var7, !throughWall.c());
      boolean var10 = false;
      if (var9) {
         var10 = this.b(true, (byte)var4, var5, var6);
      }

      return var10;
   }

   private double r(long var1, EntityLivingBase var3) {
      long var4 = var1 ^ 120803966592335L;
      return RaytraceUtil.i(var3, attackRange.L(), var4, !throughWall.c()) ? attackRange.L() : this.w$r1();
   }

   static {
      rotationSmoothing = new PercentageSetting("Rotation-smoothing", 0);
      customColor = new ColorSetting("Custom-color", "FFFFFF");
      showTargetDamageColor = new ColorSetting("Show-target-damage-color", "FF0000");
      showTargetOpacity = new PercentageSetting("Show-target-opacity", 25);
   }
   static {
      showReachRing = new BooleanSetting("Show-reach-ring", false);
      // update new version
      blinkAutoblocksOnlyWorksInHypixel = new HeaderSetting("Blink autoblocks only works in Hypixel");
      // update new version
      targetSettings = new HeaderSetting("Target settings");
      legit = new BooleanSetting("Legit", false);
      requireClick = new BooleanSetting("Require-click", false);
      requireSword = new BooleanSetting("Require-Sword", true);
      screenCheck = new BooleanSetting("Screen-check", true);
      throughWall = new BooleanSetting("Through-wall", false);
      players = new BooleanSetting("Players", true);
      mobs = new BooleanSetting("Mobs", false);
      animals = new BooleanSetting("Animals", false);
      bosses = new BooleanSetting("Bosses", false);
      friends = new BooleanSetting("Friends", false);
      enemies = new BooleanSetting("Enemies", true);
      teammates = new BooleanSetting("Teammates", false);
      bots = new BooleanSetting("Bots", false);
      silverfishes = new BooleanSetting("Silverfishes", false);
      golems = new BooleanSetting("Golems", false);
   }
   static {
      fov = new NumberSetting("FOV", 360.0F, 1.0F, 360.0F, 1.0F);
      minAPS = new NumberSetting("Min-APS", 20.0F, 1.0F, 20.0F, 0.1F);
      maxAPS = new NumberSetting("Max-APS", 20.0F, 1.0F, 20.0F, 0.1F);
      attackRange = new NumberSetting("Attack-range", 3.0F, 0.0F, 8.0F, 0.01F);
      swingRange = new NumberSetting("Swing-range", 6.0F, 0.0F, 8.0F, 0.01F);
      switchDelay = new NumberSetting("Switch-delay", 100.0F, 0.0F, 1000.0F, 1.0F);
      angleStep = new NumberSetting("Angle-step", 90.0F, 0.0F, 180.0F, 1.0F);
   }
   static {
      mode = new ModeSetting("Mode", "SINGLE", "SWITCH");
      sort = new ModeSetting("Sort", false, "VIEW", "DISTANCE", "HEALTH", "VIEW", "HURT_TIME", "ARMOR");
      rotation = new ModeSetting("Rotation", "SILENT", "NO_RENDER", "LOCK", "MANUAL", "NONE");
      moveFix = new ModeSetting("Move-fix", "SILENT", "STRICT", "NONE");
      showTarget = new ModeSetting("Show-target", "BOX", "BOX_WITH_DAMAGE", "HEAD_BOX", "HEAD_BOX_WITH_DAMAGE", "RING", "NONE");
      showTargetColor = new ModeSetting("Show-target-color", "THEME", "THEME_CUSTOM", "CUSTOM");
   }
}
