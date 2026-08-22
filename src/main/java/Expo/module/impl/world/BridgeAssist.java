package Expo.module.impl.world;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BridgeAssistBinder;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.SendPacketEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.BlockUtil;
import Expo.util.KeyBindUtil;
import Expo.util.RotationManager;
import Expo.util.Sneaky;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;

public class BridgeAssist extends Module implements EventSubscriber {
   private int s;
   private boolean G;
   public static NumberSetting sneakOnJumpTime;
   private static long a;
   public static BooleanSetting notMovingForward;
   private boolean H;
   private int n;
   private static String[] h;
   private static Map e;
   private boolean R;
   private static Object[] g;
   public static NumberSetting unsneakDelay;
   private boolean T;
   public static NumberSetting edgeOffset;
   private static long[] b;
   private int Y;
   private static Integer[] d;
   private static EnumFacing[] c;
   private int y;
   public static BooleanSetting requireSneak;
   public static BooleanSetting requireLookingDown;
   public static BooleanSetting silentRotation;
   private static final byte[] KEY_OFFSETS = {
      23, 0, 59, 41, 45, 56, 43, 44, 36, 11, 17, 26, 9, 4, 61, 5,
      10, 50, 1, 14, 40, 8, 35, 29, 34, 16, 63, 49, 58, 3, 30, 15,
      25, 7, 39, 18, 31, 47, 32, 52, 46, 28, 33, 51, 62, 24, 12, 53,
      20, 19, 2, 54, 48, 22, 37, 21, 6, 27, 38, 13, 55, 60, 42, 57
   };
   public static BooleanSetting requireHoldingBlocks;

   public String g(long var1) {
      double var3 = edgeOffset.L();
      return var3 == Math.rint(var3) ? Integer.toString((int)var3) : Double.toString(Math.round(var3 * 100.0) / 100.0);
   }

   private void P( MoveInputEvent var3, boolean var4) {
      var3.x(true);
      this.T = true;
      if (var4) {
         this.s = -1;
      }

      this.m(var3,0L);
   }

   private void O(long var1, MoveInputEvent var3, boolean var4) {
      long var5 = var1 ^ 136071802527362L;
      if (!requireSneak.c()) {
         var3.x(false);
      } else if (!this.T || !this.isGetKeyCode(var5) || !this.H && f.thePlayer.onGround) {
         if (this.R) {
            var3.x(false);
         }
      } else {
         KeyBinding.setKeyBindState(f.gameSettings.keyBindSneak.getKeyCode(), false);
         var3.x(false);
         this.R = true;
      }

      this.T = false;
      this.H = false;
      if (var4) {
         this.p(0L);
      }
   }

   private static CallSite a(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments(MethodHandles.lookup().findStatic(BridgeAssist.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/Object;", BridgeAssist.class.getClassLoader())).asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1, var2), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("Expo/module/impl/world/BridgeAssist" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private void C(short var1, char var2, MoveInputEvent var3, int var4, boolean var5) {
      long var6 = ((long)var1 << 48 | (long)var2 << 48 >>> 16 | (long)var4 << 32 >>> 32) ^ a;
      long var8 = var6 ^ 101678966382285L;
      int var12 = f.thePlayer.ticksExisted;
      if (this.s == -1 && this.Y == -1) {
         this.s = var12;
         this.n = this.q(Math.max(0.0F, unsneakDelay.L() - 50.0F));
      }

      if (this.Y != -1 && var12 - this.Y < this.y) {
         this.P( var3, false);
      } else if (this.s != -1 && var12 - this.s < this.n) {
         this.P( var3, false);
      } else {
         this.O(var8, var3, var5);
      }
   }

   private static Field a(Class var0, String var1, Class var2) {
      for (Field var6 : var0.getDeclaredFields()) {
         if (var6.getName().equals(var1) && var6.getType() == var2) {
            return var6;
         }
      }

      return null;
   }

   private double b(AxisAlignedBB var1) {
      AxisAlignedBB var2 = new AxisAlignedBB(
         var1.minX, var1.minY - 0.01, var1.minZ, var1.maxX, var1.minY, var1.maxZ
      );
      List var3 = f.theWorld.getCollidingBoundingBoxes(f.thePlayer, var2);
      if (var3.isEmpty()) {
         return Double.NaN;
      }

      double var4 = (var1.minX + var1.maxX) / 2.0;
      double var6 = (var1.minZ + var1.maxZ) / 2.0;
      double var8 = Double.MAX_VALUE;

      for (AxisAlignedBB var11 : (Iterable<AxisAlignedBB>)(var3)) {
         double var12 = Math.max(var11.minX, Math.min(var4, var11.maxX));
         double var14 = Math.max(var11.minZ, Math.min(var6, var11.maxZ));
         double var16 = Math.abs(var4 - var12);
         double var18 = Math.abs(var6 - var14);
         var8 = Math.min(var8, Math.max(var16, var18));
      }

      return var8;
   }

   private float[] Y(float var1, float var2, float var3, float var4, float var5) {
      var5 = MathHelper.clamp_float(var5, 1.0F, var5 * 2.0F);
      float var6 = MathHelper.wrapAngleTo180_float(var3 - var1);
      float var7 = var4 - var2;
      float var8 = var1 + MathHelper.clamp_float(var6, -var5, var5);
      float var9 = MathHelper.clamp_float(var2 + MathHelper.clamp_float(var7, -var5, var5), -90.0F, 90.0F);
      return new float[]{var8, var9};
   }

   public final void x(long var1, EventBus var3) {
      BridgeAssistBinder.v(var3, this);
   }

   private void K(MoveInputEvent var1, long var2) {
      this.T = false;
      this.p(0L);
      if (requireSneak.c()) {
         this.m(var1,0L);
      }
   }

   private void p(long var1) {
      this.s = -1;
      this.Y = -1;
      this.y = -1;
      this.n = -1;
   }

   private static Field b(Class var0, String var1, Class var2) {
      Field var3 = a(var0, var1, var2);
      if (var3 != null) {
         return var3;
      }

      Class[] var4 = var0.getInterfaces();
      if (var4 != null) {
         for (int var5 = 0; var5 < var4.length; var5++) {
            var3 = b(var4[var5], var1, var2);
            if (var3 != null) {
               return var3;
            }
         }
      }

      return null;
   }

   private static Object a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, Object[] var4) throws Throwable {
      int var5 = var4.length - 2;
      long var6 = (Long)var4[var5];
      long var9 = (Long)var4[++var5];
      MethodHandle var8 = a(var0, var1, var2, var3, var6, var9);
      var1.setTarget(MethodHandles.explicitCastArguments(var8, var3));
      return (Object)var8.asSpreader(Object[].class, var4.length).invoke(var4);
   }

   public void onPreMouseInput(int var1, int var2, PreMouseInputEvent var3, char var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 109549414838327L;
      int var9 = (int)((var5 ^ 38384389184814L) >>> 32);
      int var10 = (int)((var5 ^ 38384389184814L) << 32 >>> 48);
      int var11 = (int)((var5 ^ 38384389184814L) << 48 >>> 48);
      long var14 = var5 ^ 47481477799226L;
      if (silentRotation.c() && f.currentScreen == null && !f.thePlayer.capabilities.isFlying) {
         ItemStack var16 = f.thePlayer.getHeldItem();
         if (var16 != null && var16.getItem() instanceof ItemBlock) {
            if (requireLookingDown.c() && RotationManager.s() < 70.0F) {
               this.o(var7);
            } else if (notMovingForward.c() && f.thePlayer.movementInput.moveForward > 0.0F) {
               this.o(var7);
            } else {
               BridgeAssistRotation var17 = this.s(var9, RotationManager.G, (short)var10, var11, f.playerController.getBlockReachDistance());
               if (var17 == null) {
                  this.o(var7);
               } else {
                  float[] var18 = this.Y(RotationManager.r, RotationManager.G, var17.r, var17.l, 15.0F);
                  RotationManager.I(var18[0],0L);
                  RotationManager.A(var14, var18[1]);
                  this.G = true;
               }
            }
         } else {
            this.o(var7);
         }
      } else {
         this.o(var7);
      }
   }

   private static void a() {
      g[0] = "";
      h[0] = "Expo.util.RotationManager";
      g[1] = float.class;
      h[1] = "java/lang/Float";
      g[2] = long.class;
      h[2] = "java/lang/Long";
      g[3] = void.class;
      h[3] = "java/lang/Void";
      g[4] = "";
      h[4] = "Expo.event.binder.BridgeAssistBinder";
      g[5] = "";
      h[5] = "Expo.event.EventBus";
      g[6] = "";
      h[6] = "Expo.module.impl.world.BridgeAssist";
      g[7] = "";
      h[7] = "java.lang.Object";
      g[8] = "Rm5\u0003XUAl.c.5\b*=\u0000\u0017[W#4\rgLUcqZ\u001aGW4MY\u0003_H#*\u0011WITR";
      g[9] = "\u000eJmK\n\u0010\u001dKv+Mp\u0014\u0011$\u0017\f\r\u001f\u0013s+";
      g[10] = "R\u0005\u001fq\u001efA\u0004\u0004\u0011f\u0006\bB\u0017rQhWK\u001e\u007f!<UP\u001e`Ft\u0001F\u0002\u0011";
      g[11] = "!P\u0019L\u000ff!\bEHd(\u001aU\u000f\u000e\u00199b\u001f\u000e]\u0019V#\u0016\u0005S\u00148|\u001f\f^dj|\r\u0018T\u00073|\u0010\u000e0^2p\u0017\u0004W\u0016ff\u000bu";
      g[12] = "6\rbtU\f%\fy\u0014\u0011l,V+(S\u0011'T|\u0014";
      g[13] = "\"!\u00067\f\u00181 \u001dWrx8zOk\n\u00053x\u0018W\n\u00001}\u000e9U\t8p~mW\u00128o\u0019%\u0003\u0004$\u001e";
   }

   private boolean isGetKeyCode(long var1) {
      long var3 = var1 ^ 99538230915781L;
      return KeyBindUtil.V(f.gameSettings.keyBindSneak.getKeyCode(), var3);
   }

   private static Class b(long var0, long var2) {
      Class var5 = null;
      int var4 = a(var0, var2);
      Object var6 = g[var4];
      try {
         if (var6 instanceof String) {
            var5 = Class.forName(h[var4]);
            g[var4] = var5;
            return var5;
         }
      } catch (Exception var8) {
         throw new RuntimeException(var8.toString());
      }

      return (Class)var6;
   }

   static {
      a = 117632760870111L;
      zkm$clinit();
   }

   private int q(double var1) {
      double var3 = var1 / 50.0;
      int var5 = (int)var3;
      return var5 + (Math.random() < var3 - var5 ? 1 : 0);
   }

   private BridgeAssistRotation s(int var1, float var2, short var3, int var4, double var5) {
      float var9 = RotationManager.p();
      AxisAlignedBB var10 = f.thePlayer.getEntityBoundingBox();
      int var11 = MathHelper.floor_double(var10.minY) - 1;
      int var12 = MathHelper.floor_double(var10.minX);
      int var13 = MathHelper.floor_double(var10.maxX);
      int var14 = MathHelper.floor_double(var10.minZ);
      int var15 = MathHelper.floor_double(var10.maxZ);
      ArrayList var16 = new ArrayList();

      for (int var17 = var12; var17 <= var13; var17++) {
         for (int var18 = var14; var18 <= var15; var18++) {
            BlockPos var19 = new BlockPos(var17, var11, var18);
            if (!BlockUtil.a$r1(var19)) {
               for (EnumFacing var23 : c) {
                  BlockPos var24 = var19.offset(var23);
                  if (BlockUtil.a$r1(var24)) {
                     var16.add(new BridgeAssistPlacement(var19, var23));
                  }
               }
            }
         }
      }

      if (var16.isEmpty()) {
         return null;
      }

      float var31 = Float.MAX_VALUE;
      float var32 = Float.NaN;
      BlockPos var33 = null;
      EnumFacing var34 = null;
      float var35 = 0.2F;
      float var36 = 60.0F;

      while (var36 <= 90.0F) {
         float var37 = 1.0F + (float)(Math.random() * 2.0 - 1.0) * (0.3F + var35 * 0.4F);
         var37 = MathHelper.clamp_float(var37, 0.4F, 1.8F);
         var36 += var37;
         float var39 = Math.min(var36, 90.0F);
         MovingObjectPosition var25 = BlockUtil.F(new float[]{var9, var39}, var5);
         if (var25 != null && var25.typeOfHit == MovingObjectType.BLOCK) {
            EnumFacing var26 = var25.sideHit;
            if (var26 != EnumFacing.UP && var26 != EnumFacing.DOWN) {
               BlockPos var27 = var25.getBlockPos();
               Iterator var28 = var16.iterator();

               while (true) {
                  if (var28.hasNext()) {
                     BridgeAssistPlacement var29 = (BridgeAssistPlacement)var28.next();
                     if (!var27.equals(var29.Z) || var26 != var29.U) {
                        continue;
                     }

                     float var30 = Math.abs(var39 - var2);
                     if (var30 < var31) {
                        var31 = var30;
                        var32 = var39;
                        var33 = var29.Z;
                        var34 = var29.U;
                     }
                  }

                  if (var36 >= 90.0F) {
                     return var33 != null && var34 != null && !Float.isNaN(var32) ? new BridgeAssistRotation(var9, var32) : null;
                  }
                  break;
               }
            }
         }
      }

      return var33 != null && var34 != null && !Float.isNaN(var32) ? new BridgeAssistRotation(var9, var32) : null;
   }

   public void onSendPacket(long var1, SendPacketEvent var3) {
      if (var3.B instanceof C08PacketPlayerBlockPlacement) {
         C08PacketPlayerBlockPlacement var4 = (C08PacketPlayerBlockPlacement)var3.B;
         if (var4.getPlacedBlockDirection() != 255 && this.T && requireSneak.c()) {
            this.H = true;
         }
      }
   }

   private static MethodHandle a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, long var4, long var6) {
      char var8 = var2.charAt(0);
      MethodHandle var9 = null;
      Field var10 = null;
      Method var11 = null;

      try {
         if (var8 != 'Y' && var8 != 226 && var8 != 'F' && var8 != 255) {
            var11 = d(var4, var6);
            Class var17 = var11.getDeclaringClass();
            String var19 = var11.getName();
            MethodType var20 = MethodType.methodType(var11.getReturnType(), var11.getParameterTypes());
            if (var8 == 'C') {
               var9 = var0.findVirtual(var17, var19, var20);
            } else if (var8 == 'O') {
               var9 = var0.findStatic(var17, var19, var20);
            } else {
               var9 = var0.findSpecial(var17, var19, var20, var17);
            }
         } else {
            var10 = c(var4, var6);
            Class var12 = var10.getDeclaringClass();
            String var18 = var10.getName();
            Class var14 = var10.getType();
            if (var8 == 'Y') {
               var9 = var0.findGetter(var12, var18, var14);
            } else if (var8 == 226) {
               var9 = var0.findSetter(var12, var18, var14);
            } else if (var8 == 'F') {
               var9 = var0.findStaticGetter(var12, var18, var14);
            } else {
               var9 = var0.findStaticSetter(var12, var18, var14);
            }
         }

         return MethodHandles.dropArguments(var9, var3.parameterCount() - 2, long.class, long.class);
      } catch (Exception var15) {
         StringBuilder var13 = new StringBuilder();
         var13.append(var15.getClass().getName())
            .append(" : ")
            .append(var10 != null ? var10.toString() : (var11 != null ? var11.toString() : " null "))
            .append(" : ")
            .append(var15.toString());
         throw new RuntimeException(var13.toString());
      }
   }

   private static int b(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 23392;
      if (d[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = b[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().getId();
         Object[] var9 = (Object[])e.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               e.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/module/impl/world/BridgeAssist", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         d[var3] = var15;
      }

      return d[var3];
   }

   private void o(long var1) {
      long var3 = var1 ^ 59427172654482L;
      if (this.G) {
         RotationManager.O(var3);
         this.G = false;
      }
   }

   private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
      Method var5 = a(var0, var1, var2, var3, var4);
      if (var5 != null) {
         return var5;
      }

      Class[] var6 = var0.getInterfaces();
      if (var6 != null) {
         for (int var7 = 0; var7 < var6.length; var7++) {
            var5 = b(var6[var7], var1, var2, var3, var4);
            if (var5 != null) {
               return var5;
            }
         }
      }

      return null;
   }

   private static int a(long var0, long var2) {
      var0 ^= var2 << 48 | var2;
      int var4 = (int)(var0 >>> 46);
      if (h[var4] != null) {
         return var4;
      }

      Object var5 = g[var4];
      if (!(var5 instanceof String)) {
         return var4;
      }

      byte var6 = KEY_OFFSETS[(int)(var0 >>> 42 & 63L)];
      int[] var7 = new int[6];

      for (int var8 = 0; var8 < 6; var8++) {
         int var9 = 7 * (5 - var8);
         int var10 = (int)(var0 >>> var9 & 127L);
         var10 -= var6;
         if (var10 < 0) {
            var10 += 128;
         }

         var7[var8] = var10;
      }

      char[] var13 = ((String)var5).toCharArray();

      for (int var14 = 0; var14 < var13.length; var14++) {
         int var16 = var7[var14 % var7.length];
         if (var16 == 0) {
            break;
         }

         var13[var14] = (char)(var13[var14] ^ var16);
      }

      h[var4] = new String(var13);
      return var4;
   }

   private static Method d(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = g[var4];
      if (!(var5 instanceof String)) {
         return (Method)var5;
      }

      String var6 = h[var4];
      int var7 = var6.indexOf(8);
      Class var8 = b(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      int var11 = -1;
      int var12 = var9;

      do {
         var11++;
         var12++;
      } while ((var12 = var6.indexOf(8, var12)) > -1);

      int var13;
      Class[] var14 = new Class[var13 = var11 - 1];
      Class var15 = null;
      var12 = var9 + 1;

      for (int var16 = 0; var16 < var11; var16++) {
         int var17 = var6.indexOf(8, var12);
         var15 = b(Long.parseLong(var6.substring(var12, var17), 36), 0L);
         if (var16 < var13) {
            var14[var16] = var15;
         }
      }

      Class var23 = var8;

      while (true) {
         Method var26 = a(var23, var10, var15, var13, var14);
         if (var26 != null) {
            g[var4] = var26;
            return var26;
         }

         if (var23.getName().equals("java.lang.Object")) {
            break;
         }

         if ((var23 = var23.getSuperclass()) == null) {
            var23 = b(497560263219879L, 0L);
            break;
         }
      }

      var23 = var8;

      while (true) {
         Class[] var27;
         if ((var27 = var23.getInterfaces()) != null) {
            for (int var18 = 0; var18 < var27.length; var18++) {
               Method var19 = b(var27[var18], var10, var15, var13, var14);
               if (var19 != null) {
                  g[var4] = var19;
                  return var19;
               }
            }
         }

         if (var23.getName().equals("java.lang.Object")) {
            StringBuffer var28 = new StringBuffer();
            var28.append("NoSuchMethodException in ").append(var8.getName()).append(' ').append(var15.getName()).append(' ').append(var10).append('(');
            int var29 = 0;

            while (var29 < var13) {
               var28.append(var14[var29].getName());
               if (++var29 < var13) {
                  var28.append(", ");
               }
            }

            var28.append(')');
            throw new RuntimeException(var28.toString());
         }

         if ((var23 = var23.getSuperclass()) == null) {
            var23 = b(497560263219879L, 0L);
         }
      }
   }

   private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
      label33:
      for (Method var8 : var0.getDeclaredMethods()) {
         if (var8.getName().equals(var1) && var8.getReturnType() == var2) {
            Class[] var9 = var8.getParameterTypes();
            if (var9.length == var3) {
               for (int var10 = 0; var10 < var3; var10++) {
                  if (var9[var10] != var4[var10]) {
                     continue label33;
                  }
               }

               return var8;
            }
         }
      }

      return null;
   }

   private static Field c(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = g[var4];
      if (!(var5 instanceof String)) {
         return (Field)var5;
      }

      String var6 = h[var4];
      int var7 = var6.indexOf(8);
      Class var8 = b(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      Class var11 = b(Long.parseLong(var6.substring(++var9), 36), 0L);
      Class var12 = var8;

      while (true) {
         Field var13 = a(var12, var10, var11);
         if (var13 != null) {
            g[var4] = var13;
            return var13;
         }

         Class[] var14 = var12.getInterfaces();
         if (var14 != null) {
            for (int var15 = 0; var15 < var14.length; var15++) {
               var13 = b(var14[var15], var10, var11);
               if (var13 != null) {
                  g[var4] = var13;
                  return var13;
               }
            }
         }

         if (var12.getName().equals("java.lang.Object")) {
            StringBuffer var19 = new StringBuffer();
            var19.append("NoSuchFieldException in ").append(var8.getName()).append(' ').append(var11.getName()).append(' ').append(var10);
            throw new RuntimeException(var19.toString());
         }

         var12 = var12.getSuperclass();
         if (var12 == null) {
            var12 = b(497560263219879L, 0L);
         }
      }
   }

   public BridgeAssist(long var1) {
      super(((a ^ (var1)) ^ 8068768226746L));
      this.declare("BridgeAssist", Category.World, "Sneak when you get close to the edge of the blocks");
      var1 = a ^ var1;
      this.y = -1;
      this.Y = -1;
      this.n = -1;
      this.s = -1;
      this.G = false;
   }

   private AxisAlignedBB Y(float var1, float var4) {
      AxisAlignedBB var5 = f.thePlayer.getEntityBoundingBox();
      if (var1 == 0.0F && var4 == 0.0F) {
         return var5.offset(f.thePlayer.motionX, 0.0, f.thePlayer.motionZ);
      }

      float var6 = MathHelper.sqrt_float(var1 * var1 + var4 * var4);
      if (var6 < 1.0F) {
         var6 = 1.0F;
      }

      var1 /= var6;
      var4 /= var6;
      double var7 = f.thePlayer.isSprinting() ? 0.2873 : 0.221;
      float var9 = RotationManager.p();
      float var10 = MathHelper.sin(var9 * (float) Math.PI / 180.0F);
      float var11 = MathHelper.cos(var9 * (float) Math.PI / 180.0F);
      double var12 = (var4 * var11 - var1 * var10) * var7;
      double var14 = (var1 * var11 + var4 * var10) * var7;
      return var5.offset(var12, 0.0, var14);
   }

   private void m(MoveInputEvent var1, long var2) {
      if (this.R && this.isGetKeyCode(106499145851495L)) {
         KeyBinding.setKeyBindState(f.gameSettings.keyBindSneak.getKeyCode(), true);
         var1.x(true);
      }

      this.R = false;
   }

   public void onMoveInput(MoveInputEvent var1, long var2) {
      if (f.currentScreen == null && !f.thePlayer.capabilities.isFlying) {
         boolean var19 = this.isGetKeyCode(106499145851495L);
         boolean var20 = requireSneak.c();
         if (var19 && !var20) {
            this.p(0L);
         } else if (!var20 || var19 && (var1.t() != 0.0F || var1.R() != 0.0F)) {
            if (notMovingForward.c() && var1.t() > 0.0F) {
               this.K(var1,0L);
            } else if (requireLookingDown.c()
               && RotationManager.s()
                  < 70.0F) {
               this.K(var1,0L);
            } else {
               if (requireHoldingBlocks.c()) {
                  ItemStack var21 = f.thePlayer.getHeldItem();
                  if (var21 == null || !(var21.getItem() instanceof ItemBlock)) {
                     this.K(var1,0L);
                     return;
                  }
               }

               if (!var1.d() || !f.thePlayer.onGround || var1.t() == 0.0F && var1.R() == 0.0F || !(sneakOnJumpTime.L() > 0.0F) || var20 && !this.R) {
                  AxisAlignedBB var25 = this.Y(var1.t(), var1.R());
                  double var22 = this.b(var25);
                  if (!Double.isNaN(var22)) {
                     if (var22 > edgeOffset.L()) {
                        this.P( var1, true);
                     } else if (this.T) {
                        this.C((short)0, (char)11672, var1, -1795877129, true);
                     }
                  } else {
                     if (var1.d() && (sneakOnJumpTime.L() <= 0.0F || var1.t() == 0.0F && var1.R() == 0.0F)) {
                        if (this.T) {
                           this.C((short)0, (char)11672, var1, -1795877129, true);
                        }
                     } else if (f.thePlayer.onGround) {
                        this.P( var1, true);
                     } else if (this.T) {
                        this.C((short)0, (char)11672, var1, -1795877129, true);
                     }
                  }
               } else {
                  this.Y = f.thePlayer.ticksExisted;
                  this.y = this.q(sneakOnJumpTime.L());
                  this.P( var1, true);
               }
            }
         } else {
            if (!var19) {
               this.p(0L);
            }

            this.m(var1,0L);
         }
      }
   }

   public void A(long var1) {
      long var3 = var1 ^ 13419466554712L;
      long var7 = var1 ^ 17179273251418L;
      this.T = false;
      this.p(0L);
      this.o(var3);
      KeyBindUtil.o(var7, f.gameSettings.keyBindSneak.getKeyCode());
   }

   private static void zkm$clinit() {
      try {
         g = new Object[14];
         h = new String[14];
         a();
         e = new HashMap(13);
         long var0 = a ^ 54503866275638L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var8 = new long[7];
         int var5 = 0;
         String var6 = "ò\\\u00854\u0003ÐÌ§è\u0019\u008dÕG\u008es`?{éè\bV\nwáð'\u0011\u001e\u0001*w\u0012\u009c'\u009f!ºpó";
         int var7 = "ò\\\u00854\u0003ÐÌ§è\u0019\u008dÕG\u008es`?{éè\bV\nwáð'\u0011\u001e\u0001*w\u0012\u009c'\u009f!ºpó".length();
         int var4 = 0;

         label27:
         while (true) {
            int var10001 = var4;
            var4 += 8;
            byte[] var9 = var6.substring(var10001, var4).getBytes("ISO-8859-1");
            long[] var15 = var8;
            var10001 = var5++;
            long var18 = (var9[0] & 255L) << 56
               | (var9[1] & 255L) << 48
               | (var9[2] & 255L) << 40
               | (var9[3] & 255L) << 32
               | (var9[4] & 255L) << 24
               | (var9[5] & 255L) << 16
               | (var9[6] & 255L) << 8
               | var9[7] & 255L;
            int var20 = -1;

            while (true) {
               long var10 = var18;
               byte[] var12 = var2.doFinal(
                  new byte[]{
                     (byte)(var10 >>> 56),
                     (byte)(var10 >>> 48),
                     (byte)(var10 >>> 40),
                     (byte)(var10 >>> 32),
                     (byte)(var10 >>> 24),
                     (byte)(var10 >>> 16),
                     (byte)(var10 >>> 8),
                     (byte)var10
                  }
               );
               long var22 = (var12[0] & 255L) << 56
                  | (var12[1] & 255L) << 48
                  | (var12[2] & 255L) << 40
                  | (var12[3] & 255L) << 32
                  | (var12[4] & 255L) << 24
                  | (var12[5] & 255L) << 16
                  | (var12[6] & 255L) << 8
                  | var12[7] & 255L;
               switch (var20) {
                  case 0:
                     var15[var10001] = var22;
                     if (var4 >= var7) {
                        b = var8;
                        d = new Integer[7];
                        c = new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST};
                        return;
                     }
                     break;
                  default:
                     var15[var10001] = var22;
                     if (var4 < var7) {
                        continue label27;
                     }

                     var6 = "I\u009e'±\u0088î¹OsÉè\u0019=´æ8";
                     var7 = "I\u009e'±\u0088î¹OsÉè\u0019=´æ8".length();
                     var4 = 0;
               }

               int var17 = var4;
               var4 += 8;
               var9 = var6.substring(var17, var4).getBytes("ISO-8859-1");
               var15 = var8;
               var10001 = var5++;
               var18 = (var9[0] & 255L) << 56
                  | (var9[1] & 255L) << 48
                  | (var9[2] & 255L) << 40
                  | (var9[3] & 255L) << 32
                  | (var9[4] & 255L) << 24
                  | (var9[5] & 255L) << 16
                  | (var9[6] & 255L) << 8
                  | var9[7] & 255L;
               var20 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var13) {
         throw new RuntimeException(var13);
      }
   }

   static {
      silentRotation = new BooleanSetting("Silent-rotation", false);
      requireSneak = new BooleanSetting("Require-sneak", false);
      requireHoldingBlocks = new BooleanSetting("Require-holding-blocks", false);
      requireLookingDown = new BooleanSetting("Require-looking-down", false);
      notMovingForward = new BooleanSetting("Not-moving-forward", false);
   }
   static {
      edgeOffset = new NumberSetting("Edge-offset", 0.0F, 0.0F, 0.3F, 0.01F);
      unsneakDelay = new NumberSetting("Unsneak-delay", 50.0F, 50.0F, 300.0F, 5.0F);
      sneakOnJumpTime = new NumberSetting("Sneak-on-jump-time", 0.0F, 0.0F, 500.0F, 5.0F);
   }
}
