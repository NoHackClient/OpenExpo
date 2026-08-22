package Expo.internal;

import Expo.enums.MiningRegionState;
import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.MiningEngineBinder;
import Expo.event.events.PreTickEvent;
import Expo.module.Modules;
import Expo.module.impl.world.AutoTunnel;
import Expo.util.AutoToolService;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import Expo.util.MiningConstants;
import Expo.util.RotationManager;
import Expo.util.Sneaky;
import Expo.util.render.BoxRenderer;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class MiningEngine implements EventSubscriber {
   private static Map gb;
   private final MiningState K;
   private boolean j;
   private double Q;
   private final HashSet<BlockPos> P;
   private static long[] eb;
   private boolean W;
   private boolean p;
   private static long[] hb;
   private int H;
   private int n;
   private static String[] bb;
   private boolean w;
   private long z;
   private static long T;
   private long S;
   private float v;
   private boolean r;
   private long e;
   private long R;
   private boolean f;
   private int U;
   private boolean u;
   private boolean N;
   private static Long[] ib;
   private BlockPos L;
   private static final double x = 1.0;
   private float uM;
   private long X;
   private static long i;
   private long l;
   private static long Y;
   private final List<BlockPos> I;
   private static long o;
   private boolean D;
   private static Map jb;
   private boolean m;
   private Boolean d;
   private boolean J;
   private long u7;
   private static Random uA;
   private static Map db;
   private long k;
   private boolean q;
   private boolean A;
   private float B;
   private static long ab;
   private final List<MinedBlockTimestamp> c;
   private long O;
   private double C;
   private Float b;
   private static long g;
   private boolean V;
   private static final float y = 70.0F;
   private final List<BlockPos> E;
   private static long a;
   private static String[] cb;
   private float h;
   public static MiningEngine uq;
   private long s;
   private static long t;
   private long G;
   private static Minecraft F;
   private static Integer[] fb;
   private BlockPos M;
   private boolean Z;

   public void b(long var1) {
      var1 = ab ^ var1;
      long var3 = var1 ^ 111700754433030L;
      long var5 = (var1 ^ 75425271482067L) >>> 32;
      int var7 = (int)((var1 ^ 75425271482067L) << 32 >>> 32);
      if (this.D || F.thePlayer != null && F.theWorld != null) {
         if (this.D) {
            this.B(var5, var7);
         } else {
            this.a(var3);
         }
      }
   }

   private void W(List<BlockPos> var1, HashSet<BlockPos> var2, BlockPos var3) {
      if (var2.add(var3)) {
         var1.add(var3);
      }
   }

   private Vec3[] a(long var1, BlockPos var3) {
      Vec3[] var10000 = new Vec3[6];
      var10000[0] = new Vec3(var3.getX() + 0.5, var3.getY() + 0.5, var3.getZ() + 0.5);
      var10000[1] = new Vec3(var3.getX() + 0.5, var3.getY() + 0.15, var3.getZ() + 0.5);
      var10000[2] = new Vec3(var3.getX() + 0.25, var3.getY() + 0.5, var3.getZ() + 0.5);
      var10000[3] = new Vec3(var3.getX() + 0.75, var3.getY() + 0.5, var3.getZ() + 0.5);
      var10000[4] = new Vec3(var3.getX() + 0.5, var3.getY() + 0.5, var3.getZ() + 0.25);
      var10000[5] = new Vec3(var3.getX() + 0.5, var3.getY() + 0.5, var3.getZ() + 0.75);
      return var10000;
   }

   public void z(long var1) {
      long var3 = var1 ^ 114002527847062L;
      if (this.D && F.thePlayer != null) {
         this.W(var3);
      }
   }

   private static void a() {
   }

   private boolean S(long var1, BlockPos var3) {
      var1 = ab ^ var1;
      long var4 = var1 ^ 64188168623084L;
      long var6 = var1 ^ 61350408649907L;
      return this.f() ? this.t(var6, var3) : this.i(var3, var4);
   }

   private boolean z(long var1, long var3) {
      if (!this.w) {
         return false;
      }

      if (var1 >= this.s) {
         if (this.U >= 3) {
            this.u(101811667684420L);
            this.N = true;
            return false;
         }

         this.U++;
         this.s = var1 + 300L;
      }

      this.c(41798187465823L);
      return true;
   }

   private boolean m(BlockPos var1, long var2) {
      long var4 = var2 ^ 14891818128972L;
      EntityPlayerSP var6 = F.thePlayer;
      Block var7 = F.theWorld.getBlockState(var1).getBlock();
      if (var7 == Blocks.air) {
         return false;
      } else if (BrokenBlockTracker.m.k(var1)) {
         return false;
      } else {
         return (var7 == Blocks.chest || var7 == Blocks.trapped_chest) && !this.Q(var1)
            ? false
            : var7.getBlockHardness(F.theWorld, var1) >= 0.0F && this.N(var4, F, var6, var1);
      }
   }

   private boolean c(EntityPlayerSP var1) {
      for (BlockPos var3 : this.u(var1)) {
         if (F.theWorld.getBlockState(var3).getBlock() != Blocks.air) {
            return false;
         }
      }

      return true;
   }

   private MiningEngine(int var1, int var2, int var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ ab;
      long var6 = var4 ^ 98829638223922L;
      this.K = new MiningState(var6);
      this.I = new ArrayList<>();
      this.c = new ArrayList<>();
      this.E = new ArrayList<>();
      this.P = new HashSet<>();
      this.D = false;
      this.v = 0.0F;
      this.uM = 0.0F;
      this.u7 = 0L;
      this.O = 0L;
      this.e = 0L;
      this.V = false;
      this.C = 0.0;
      this.Q = 0.0;
      this.S = 0L;
      this.J = false;
      this.B = 0.0F;
      this.k = 0L;
      this.R = 0L;
      this.n = -1;
      this.d = null;
      this.h = Float.NaN;
      this.m = false;
      this.l = 0L;
      this.p = false;
      this.b = null;
      this.f = false;
      this.r = false;
      this.z = 0L;
      this.q = false;
      this.A = false;
      this.H = 0;
      this.j = false;
      this.W = false;
      this.X = 0L;
      this.Z = false;
      this.u = false;
      this.G = 0L;
      this.w = false;
      this.N = false;
      this.U = 0;
      this.s = 0L;
   }

   public void v(long var1) {
      int var26 = 2524;
      if (this.D) {
         this.K.V(94183755216258L);
         this.K.s(28894398477326L);
         this.K.c(88214989872647L);
         this.K.w(108613680588488L);
         this.k((byte)0, 7372158);
         this.J();
         BrokenBlockTracker.m.e(98246303673997L);
         MiningBlockScanner.j(53803850989232L);
         MiningBlockScanner.h(61947740700751L);
         MiningBlockScanner.l(14863, (short)29585, (char)var26);
         this.I(32278145520763L);
      }
   }

   private boolean X(int var1, char var2, int var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ ab;
      long var6 = var4 ^ 67332649843161L;
      long var8 = var4 ^ 16300157774262L;
      int var10 = (int)((var4 ^ 107714347851340L) >>> 32);
      int var11 = (int)((var4 ^ 107714347851340L) << 32 >>> 48);
      int var12 = (int)((var4 ^ 107714347851340L) << 48 >>> 48);
      long var13 = var4 ^ 117953977387019L;
      long var15 = var4 ^ 30233662041066L;
      long var17 = System.currentTimeMillis();
      if (var17 - this.k < 1000L) {
         return false;
      }

      float var19 = this.w(RotationManager.r);
      double var20 = Math.toRadians(var19);
      double var22 = -Math.sin(var20);
      double var24 = Math.cos(var20);
      double var26 = F.thePlayer.posX + var22 * 1.2;
      double var28 = F.thePlayer.posZ + var24 * 1.2;
      int var30 = (int)Math.floor(var26);
      int var31 = (int)F.thePlayer.posY;
      int var32 = (int)Math.floor(var28);
      BlockPos var33 = new BlockPos(var30, var31, var32);
      BlockPos var34 = new BlockPos(var30, var31 + 1, var32);
      if (!this.X(var33) && !this.X(var34)) {
         return false;
      }

      if (!MiningConstants.A) {
         this.L(var6, "dead end detected and auto turn is disabled");
         return true;
      }

      double var35 = Math.sqrt(
         Math.pow(var33.getX() + 0.5 - F.thePlayer.posX, 2.0)
            + Math.pow(var33.getZ() + 0.5 - F.thePlayer.posZ, 2.0)
      );
      if (var35 > 1.0) {
         KeyBindUtil.A(var13, F.gameSettings.keyBindForward.getKeyCode(), true);
         KeyBindUtil.A(var13, F.gameSettings.keyBindBack.getKeyCode(), false);
         KeyBindUtil.A(var13, F.gameSettings.keyBindSneak.getKeyCode(), false);
         return false;
      }

      this.k = var17;
      double var37 = Math.toRadians((var19 - 90.0F) % 360.0F);
      double var39 = Math.toRadians((var19 + 90.0F) % 360.0F);
      BlockPos var41 = new BlockPos(
         (int)Math.floor(F.thePlayer.posX - Math.sin(var37) * 1.2), var31, (int)Math.floor(F.thePlayer.posZ + Math.cos(var37) * 1.2)
      );
      BlockPos var42 = new BlockPos(
         (int)Math.floor(F.thePlayer.posX - Math.sin(var39) * 1.2), var31, (int)Math.floor(F.thePlayer.posZ + Math.cos(var39) * 1.2)
      );
      boolean var43 = this.X(var41);
      boolean var44 = this.X(var42);
      if (!var43 && !var44) {
         MiningAxisScanResult var46 = MiningBlockScanner.l(var8, 50);
         int var47 = var46.Z;
         int var48 = var46.t;
         int var49 = var46.c;
         int var50 = var46.S;

         boolean var54;
         if (var47 > var48 + 3) {
            var54 = true;
         } else if (var48 > var47 + 3) {
            var54 = false;
         } else if (var49 != -1 && var50 != -1) {
            if (var49 > var50) {
               var54 = true;
            } else if (var50 > var49) {
               var54 = false;
            } else {
               var54 = uA.nextBoolean();
            }
         } else if (var49 != -1) {
            var54 = false;
         } else if (var50 != -1) {
            var54 = true;
         } else {
            var54 = uA.nextBoolean();
         }

         MiningRegionScanResult var52 = MiningBlockScanner.b(var15, 4);
         if (var52.L == MiningRegionState.EMPTY && var52.j == MiningRegionState.EMPTY) {
            this.L(var6, "dead end detected with no viable tunnel on either side");
            return true;
         } else if ((var52.L == MiningRegionState.EMPTY || var52.j == MiningRegionState.EMPTY) && var52.N == MiningRegionState.EMPTY) {
            this.L(var6, "dead end detected with no side path and no back path");
            return true;
         } else if ((!var54 || var52.L != MiningRegionState.EMPTY) && (var54 || var52.j != MiningRegionState.EMPTY)) {
            this.o(var54 ? -90.0F : 90.0F, var10, var11, (char)var12);
            return true;
         } else {
            this.o(180.0F, var10, var11, (char)var12);
            return true;
         }
      } else if (!var43) {
         MiningRegionScanResult var53 = MiningBlockScanner.b(var15, 4);
         if (var53.L == MiningRegionState.EMPTY) {
            this.o(180.0F, var10, var11, (char)var12);
            return true;
         } else {
            this.o(-90.0F, var10, var11, (char)var12);
            return true;
         }
      } else if (!var44) {
         MiningRegionScanResult var45 = MiningBlockScanner.b(var15, 4);
         if (var45.j == MiningRegionState.EMPTY) {
            this.o(180.0F, var10, var11, (char)var12);
            return true;
         } else {
            this.o(90.0F, var10, var11, (char)var12);
            return true;
         }
      } else {
         this.L(var6, "dead end detected with no viable turn");
         return true;
      }
   }

   private static String a(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 2559;
      if (cb[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])db.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               db.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/internal/MiningEngine", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = bb[var5].getBytes("ISO-8859-1");
         cb[var5] = a(((Cipher)var4[0]).doFinal(var9));
      }

      return cb[var5];
   }

   public boolean h() {
      return this.D;
   }

   private float m(EntityPlayerSP var1) {
      return !this.D && !this.J ? var1.rotationYaw : RotationManager.r;
   }

   private boolean x(EntityPlayerSP var1, long var2) {
      if (!this.f() || this.j || this.M == null) {
         return false;
      } else if (this.t(116121547939723L, this.M) && this.R(F, var1, 113596981294470L, this.M)) {
         double var8 = Math.sqrt(
            Math.pow(this.M.getX() + 0.5 - var1.posX, 2.0) + Math.pow(this.M.getZ() + 0.5 - var1.posZ, 2.0)
         );
         float var10 = this.f(var1, this.M, this.w(this.m(var1)));
         return var8 > 2.5 && var10 > 40.0F ? true : var8 > 1.5 && var10 > 25.0F && this.K.I();
      } else {
         return true;
      }
   }

   private void f(int var1, char var2, short var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ ab;
      long var6 = var4 ^ 325597178373L;
      this.u(var6);
      this.N = false;
   }

   public MiningState s() {
      return this.K;
   }

   private void A(BlockPos var1, long var2) {
      long var4 = var2 ^ 5445298567679L;
      Vec3 var6 = F.thePlayer.getPositionEyes(1.0F);
      Vec3 var7 = new Vec3(var1.getX() + 0.5, var1.getY() + 0.5, var1.getZ() + 0.5);
      Vec3 var8 = var7.subtract(var6);
      double var9 = var8.xCoord;
      double var11 = var8.yCoord;
      double var13 = var8.zCoord;
      double var15 = Math.sqrt(var9 * var9 + var13 * var13);
      float var17 = (float)(-Math.toDegrees(Math.atan2(var11, var15)));
      float var18 = (float)Math.toDegrees(Math.atan2(var13, var9)) - 90.0F;

      while (var18 > 180.0F) {
         var18 -= 360.0F;
      }

      while (var18 < -180.0F) {
         var18 += 360.0F;
      }

      float var19 = var17 - this.uM;
      float var20 = var18 - this.v;

      while (var20 > 180.0F) {
         var20 -= 360.0F;
      }

      while (var20 < -180.0F) {
         var20 += 360.0F;
      }

      this.v += var20 / 4.0F;
      this.uM += var19 / 4.0F;
      this.uM = Math.max(-90.0F, Math.min(90.0F, this.uM));
      this.W(var4);
   }

   private float O(float var1) {
      while (var1 > 180.0F) {
         var1 -= 360.0F;
      }

      while (var1 < -180.0F) {
         var1 += 360.0F;
      }

      return var1;
   }

   private boolean S(char var1, long var2) {
      long var4 = ((long)var1 << 48 | var2 << 16 >>> 16) ^ ab;
      int var6 = (int)((var4 ^ 52625895914735L) >>> 32);
      int var7 = (int)((var4 ^ 52625895914735L) << 32 >>> 48);
      int var8 = (int)((var4 ^ 52625895914735L) << 48 >>> 48);
      if (MiningConstants.A && !this.J) {
         if (!this.A) {
            this.A = true;
            this.H = 4;
         }

         if (this.H <= 0) {
            return false;
         }

         this.H--;
         this.M = null;
         this.o(90.0F, var6, var7, (char)var8);
         return true;
      } else {
         return false;
      }
   }

   private BlockPos E(long var1, Minecraft var3, EntityPlayerSP var4) {
      long var5 = var1 ^ 60907744104040L;
      if (this.M == null) {
         return null;
      }

      Vec3 var7 = var4.getPositionEyes(1.0F);
      Vec3 var8 = new Vec3(this.M.getX() + 0.5, this.M.getY() + 0.5, this.M.getZ() + 0.5);
      Vec3 var9 = var8.subtract(var7);
      double var10 = var9.lengthVector();
      if (var10 <= 0.001) {
         return null;
      }

      Vec3 var12 = new Vec3(var9.xCoord / var10, var9.yCoord / var10, var9.zCoord / var10);
      double var13 = 0.1;
      int var15 = (int)Math.ceil(var10 / var13);

      for (int var16 = 1; var16 < var15 - 1; var16++) {
         double var17 = var13 * var16;
         Vec3 var19 = new Vec3(
            var7.xCoord + var12.xCoord * var17,
            var7.yCoord + var12.yCoord * var17,
            var7.zCoord + var12.zCoord * var17
         );
         BlockPos var20 = new BlockPos(var19.xCoord, var19.yCoord, var19.zCoord);
         if (!var20.equals(this.M) && this.T(var3, var5, var4, var20)) {
            return var20;
         }
      }

      return null;
   }

   private BlockPos j(EntityPlayerSP var1) {
      return this.R(var1).down();
   }

   private String k(long var1) {
      return "player stuck in same position for " + MiningConstants.X + " seconds";
   }

   private BlockPos u$r2(EntityPlayerSP var1) {
      BlockPos var2 = this.R(var1);
      return new BlockPos(var2.getX(), var2.getY() + 1, var2.getZ());
   }

   private boolean G() {
      return MiningConstants.J == 2 || this.Z;
   }

   private boolean D() {
      return MiningConstants.J == 3;
   }

   private boolean T(Minecraft var1, long var2, EntityPlayerSP var4, BlockPos var5) {
      var2 = ab ^ var2;
      long var6 = var2 ^ 120300567359737L;
      long var8 = var2 ^ 81371055893030L;
      if (var5 != null && var1.theWorld != null) {
         for (BlockPos var13 : this.K.M()) {
            if (var13 != null && var13.equals(var5)) {
               return false;
            }
         }

         Block var15 = var1.theWorld.getBlockState(var5).getBlock();
         if (var15 == Blocks.air || BrokenBlockTracker.m.k(var5)) {
            return false;
         } else {
            return this.t(var8, var5) ? false : var15.getBlockHardness(var1.theWorld, var5) >= 0.0F && this.N(var6, var1, var4, var5);
         }
      } else {
         return false;
      }
   }

   private boolean z(BlockPos var1) {
      return this.P.contains(var1);
   }

   private void T(String var1, long var2) {
       try {var2 = ab ^ var2;
      long var4 = var2 ^ 113896711444114L;
      int var6 = (int)((var2 ^ 9262046309426L) >>> 32);
      int var7 = (int)((var2 ^ 9262046309426L) << 32 >>> 48);
      int var8 = (int)((var2 ^ 9262046309426L) << 48 >>> 48);
      int var9 = (int)((var2 ^ 67560786616803L) >>> 48);
      long var10 = (var2 ^ 67560786616803L) << 16 >>> 16;
      int var12 = (int)((var2 ^ 50855157172334L) >>> 32);
      int var13 = (int)((var2 ^ 50855157172334L) << 32 >>> 48);
      int var14 = (int)((var2 ^ 50855157172334L) << 48 >>> 48);
      int var15 = (int)((var2 ^ 111988944921308L) >>> 32);
      int var16 = (int)((var2 ^ 111988944921308L) << 32 >>> 48);
      int var17 = (int)((var2 ^ 111988944921308L) << 48 >>> 48);
      long var18 = var2 ^ 114855714239184L;
      long var20 = var2 ^ 48611081517191L;
      boolean var26 = this.D;
      this.D = false;
      this.C(var15, (char)var16, (char)var17);
      this.M = null;
      this.J = false;
      this.h = Float.NaN;
      RotationManager.O(var20);
      this.j(var4);
      this.U();
      this.o(0L);
      this.f(var12, (char)var13, (short)var14);
      BrokenBlockTracker.m.N(false);
      AutoToolService.K.p(var6, (char)var7, (char)var8);
      this.I.clear();
      this.E.clear();
      this.P.clear();
      this.L = null;
      this.V = false;
      this.r = false;
      this.f = false;
      if (this.n != -1) {
         MiningConstants.w = this.n;
         this.n = -1;
      }

      if (this.d != null) {
         MiningConstants.v = this.d;
         this.d = null;
      }

      if (var26) {
         if (var1 == null) {
            Expo.util.ClientUtil.t(var18, "AutoTunnel closed");
         } else {
            Expo.util.ClientUtil.t(var18, "AutoTunnel closed unexpectedly: " + var1);
            if (Modules.J(AutoTunnel.class).o()) {
               Modules.J(AutoTunnel.class).u((short)var9, var10);
            }
         }
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private void c(long var1) {
      this.C(9749, (char)24068, (char)9748);
      switch (this.U) {
         case 0:
            KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindForward.getKeyCode(), true);
            break;
         case 1:
            KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindBack.getKeyCode(), true);
            break;
         case 2:
            KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindLeft.getKeyCode(), true);
            break;
         case 3:
            KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindRight.getKeyCode(), true);
      }
   }

   public void a(long var1) {
      var1 = ab ^ var1;
      long var3 = var1 ^ 109089883132357L;
      long var5 = var1 ^ 132928916585055L;
      int var7 = (int)((var1 ^ 14077531761509L) >>> 32);
      int var8 = (int)((var1 ^ 14077531761509L) << 32 >>> 48);
      int var9 = (int)((var1 ^ 14077531761509L) << 48 >>> 48);
      int var10 = (int)((var1 ^ 47147754540857L) >>> 32);
      int var11 = (int)((var1 ^ 47147754540857L) << 32 >>> 48);
      int var12 = (int)((var1 ^ 47147754540857L) << 48 >>> 48);
      long var13 = var1 ^ 119658807129479L;
      long var15 = var1 ^ 138266274291867L;
      if (MiningConstants.J == 0) {
         MiningRegionScanResult var21 = MiningBlockScanner.b(var15, 4);
         if (var21.Z == MiningRegionState.EMPTY) {
            Expo.util.ClientUtil.t(var13, "AutoTunnel failed to start: front area is fully empty");
            return;
         }
      }

      this.D = true;
      this.M = null;
      long var24 = System.currentTimeMillis();
      this.u7 = var24;
      this.O = var24;
      this.J = false;
      this.k = 0L;
      this.j(var3);
      this.U();
      this.o(0L);
      this.f(var10, (char)var11, (short)var12);
      RotationManager.n(RotationMode.STRICT);
      BrokenBlockTracker.m.N(true);
      if (MiningConstants.r) {
         AutoToolService.K.I(var5);
      } else {
         AutoToolService.K.p(var7, (char)var8, (char)var9);
      }

      if (F.thePlayer != null) {
         this.C = F.thePlayer.posX;
         this.Q = F.thePlayer.posZ;
         this.v = RotationManager.r;
         this.uM = RotationManager.G;
         this.h = this.w(RotationManager.r);
      }

      this.S = System.currentTimeMillis();
      if (this.f()) {
         if (this.d == null) {
            this.d = MiningConstants.v;
         }

         MiningConstants.v = false;
      }

      if (MiningConstants.v) {
         this.n = MiningConstants.w;
         MiningConstants.w = 2;
         if (this.d == null) {
            this.d = Boolean.TRUE;
         }
      } else if (this.d == null) {
         this.d = Boolean.FALSE;
      }

      this.m = true;
   }

   private boolean Q(BlockPos var1) {
      Block var2 = F.theWorld.getBlockState(var1).getBlock();
      return var2 != Blocks.chest && var2 != Blocks.trapped_chest ? false : !MiningConstants.k || this.K.g().contains(var1);
   }

   private boolean f(int var1, short var2, EntityPlayerSP var3, long var4, char var6) {
      long var7 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var6 << 48 >>> 48) ^ ab;
      int var11 = (int)((var7 ^ 134308865199986L) >>> 32);
      long var12 = (var7 ^ 134308865199986L) << 32 >>> 32;
      long var14 = var7 ^ 70634309982809L;
      long var16 = var7 ^ 50093163134501L;
      long var18 = var7 ^ 117504215602890L;
      long var20 = var7 ^ 10658483957709L;
      long var22 = var7 ^ 111656236503513L;
      long var24 = var7 ^ 81176845781412L;
      if (this.f() && !this.j) {
         boolean var26 = var3.isCollidedHorizontally || this.K.I();
         if (!var26) {
            return false;
         }

         BlockPos var27 = this.R(var3);
         BlockPos var28 = this.u$r2(var3);
         Block var29 = F.theWorld.getBlockState(var27).getBlock();
         if (var29 == Blocks.air) {
            return false;
         }

         if (!this.t(var18, var27) && !this.t(var18, var28)) {
            if (this.K(F, var3, var4, this.D(0L), var22)) {
               return true;
            } else if (!this.u(var11, var12, var27) && !this.u(var11, var12, var28)) {
               return false;
            } else {
               BlockPos var30 = this.I(var16, var3);
               if (var30 != null) {
                  this.l(var20, var3, var30, var4);
                  this.S = var4;
                  return true;
               } else if (this.c(var3)) {
                  KeyBindUtil.A(var24, F.gameSettings.keyBindBack.getKeyCode(), false);
                  KeyBindUtil.A(var24, F.gameSettings.keyBindSneak.getKeyCode(), false);
                  KeyBindUtil.A(var24, F.gameSettings.keyBindForward.getKeyCode(), true);
                  KeyBindUtil.A(var24, F.gameSettings.keyBindJump.getKeyCode(), true);
                  this.S = var4;
                  return true;
               } else if (this.m(var28, var14)) {
                  this.l(var20, var3, var28, var4);
                  this.S = var4;
                  return true;
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private BlockPos I(long var1, EntityPlayerSP var3) {
      long var4 = var1 ^ 120615260726396L;

      for (BlockPos var7 : this.u(var3)) {
         if (this.m(var7, var4)) {
            return var7;
         }
      }

      return null;
   }

   private boolean a(char var1, long var2, short var4, int var5) {
      if (!this.w && !this.N) {
         this.w = true;
         this.U = 0;
         this.s = var2 + 300L;
         return true;
      } else {
         return false;
      }
   }

   private List<BlockPos> u(EntityPlayerSP var1) {
      ArrayList var2 = new ArrayList();
      HashSet var3 = new HashSet();
      BlockPos var4 = this.t(var1);
      BlockPos var5 = this.R(var1);
      int var6 = var5.getX() - var4.getX();
      int var7 = var5.getZ() - var4.getZ();
      int var8 = var7;
      int var9 = -var6;
      this.W(var2, var3, new BlockPos(var4.getX(), var4.getY() + 2, var4.getZ()));
      this.W(var2, var3, new BlockPos(var4.getX() + var6, var4.getY() + 2, var4.getZ() + var7));
      this.W(var2, var3, new BlockPos(var4.getX() + var8, var4.getY() + 2, var4.getZ() + var9));
      this.W(var2, var3, new BlockPos(var4.getX() - var8, var4.getY() + 2, var4.getZ() - var9));
      this.W(var2, var3, this.u$r2(var1));
      this.W(var2, var3, new BlockPos(var5.getX(), var5.getY() + 2, var5.getZ()));
      this.W(var2, var3, new BlockPos(var5.getX() + var8, var5.getY() + 2, var5.getZ() + var9));
      this.W(var2, var3, new BlockPos(var5.getX() - var8, var5.getY() + 2, var5.getZ() - var9));
      return var2;
   }

   private boolean b(long var1, long var3) {
      long var5 = var3 ^ 49874899658299L;
      if (this.D() && !this.Z && !this.u && this.O != 0L) {
         if (var1 - this.O < 3000L) {
            return false;
         }

         this.Z = true;
         this.u = true;
         this.G = var1 + 2000L;
         this.M = null;
         this.J = false;
         this.j(var5);
         this.u7 = var1;
         return true;
      } else {
         return false;
      }
   }

   private List D(long var1) {
      MiningBlockScanner.r( this.K.g(), this.G(), this.k());
      return MiningBlockScanner.W();
   }

   private boolean k() {
      return MiningConstants.J == 2;
   }

   private void U() {
      this.j = false;
      this.W = false;
      this.X = 0L;
   }

   private boolean X(BlockPos var1) {
      Block var2 = F.theWorld.getBlockState(var1).getBlock();
      return var2 != Blocks.air && var2.getBlockHardness(F.theWorld, var1) < 0.0F || BrokenBlockTracker.m.k(var1);
   }

   private BlockPos t(EntityPlayerSP var1) {
      return new BlockPos(Math.floor(var1.posX), Math.floor(var1.posY), Math.floor(var1.posZ));
   }

   private void u$r3(EntityPlayerSP var1) {
      this.E.clear();
      this.P.clear();
      this.L = null;
      BlockPos var2 = this.j(var1);
      boolean var3 = F.theWorld.getBlockState(var2).getBlock() == Blocks.air;
      if (var3) {
         this.L = var2;
         int var4 = var2.getX();
         int var5 = var2.getY() + 2;
         int var6 = var2.getZ();
         double var7 = Math.toRadians(this.m(var1));
         int var9 = (int)Math.round(-Math.sin(var7));
         int var10 = (int)Math.round(Math.cos(var7));
         int var11 = var10;
         int var12 = -var9;

         for (int var13 = 0; var13 < 3; var13++) {
            int var14 = var4 + var9 * var13;
            int var15 = var6 + var10 * var13;

            for (int var16 = 0; var16 < 2; var16++) {
               for (int var17 = -1; var17 <= 1; var17++) {
                  BlockPos var18 = new BlockPos(var14 + var11 * var17, var5 + var16, var15 + var12 * var17);
                  this.E.add(var18);
                  this.P.add(var18);
               }
            }
         }
      }
   }

   private static long c(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 9202;
      if (ib[var3] == null) {
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
         long var5 = hb[var3];
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
         Object[] var9 = (Object[])jb.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               jb.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/internal/MiningEngine", var14);
         }

         long var15 = (var10[0] & 255L) << 56
            | (var10[1] & 255L) << 48
            | (var10[2] & 255L) << 40
            | (var10[3] & 255L) << 32
            | (var10[4] & 255L) << 24
            | (var10[5] & 255L) << 16
            | (var10[6] & 255L) << 8
            | var10[7] & 255L;
         ib[var3] = var15;
      }

      return ib[var3];
   }

   private float w(float var1) {
      var1 %= 360.0F;
      if (var1 < 0.0F) {
         var1 += 360.0F;
      }

      return var1;
   }

   private float f(EntityPlayerSP var1, BlockPos var2, float var3) {
      double var4 = var2.getX() + 0.5 - var1.posX;
      double var6 = var2.getZ() + 0.5 - var1.posZ;
      float var8 = (float)Math.toDegrees(Math.atan2(var6, var4)) - 90.0F;
      return Math.abs(this.O(var8 - var3));
   }

   private boolean f() {
      return MiningConstants.J == 2 || MiningConstants.J == 3;
   }

   public void k(byte var1, int var2) {
      if (this.M != null && this.D) {
         BoxRenderer.I( this.M, new Color(0, 255, 0, 180));
      }
   }

   public void J() {
      long var5 = System.currentTimeMillis();
      this.c.removeIf(var2 -> {
         return var5 - var2.g > 10000L;
      });

      for (MinedBlockTimestamp var8 : this.c) {
         BoxRenderer.I( var8.Y, new Color(255, 0, 0, 180));
      }
   }

   private void L(long var1, String var3) {
      long var4 = var1 ^ 121494375227160L;
      this.T(var3, var4);
   }

   private boolean B(Block var1) {
      return var1 == Blocks.iron_ore || var1 == Blocks.coal_ore;
   }

   private void n(long var1) {
      if (F.thePlayer != null) {
         float var7 = this.B - this.v;

         while (var7 > 180.0F) {
            var7 -= 360.0F;
         }

         while (var7 < -180.0F) {
            var7 += 360.0F;
         }

         float var8 = MiningConstants.C;
         if (Math.abs(var7) < var8) {
            this.v = this.B;
            RotationManager.N(71285564916286L, this.B, this.uM);
            this.v = RotationManager.r;
            this.uM = MathUtil.q(RotationManager.G, -90.0F, 90.0F);
            this.J = false;
            this.M = null;
            this.m = true;
         } else {
            RotationManager.S(this.B, this.uM, var8, 23926534103447L);
            this.v = RotationManager.r;
            this.uM = MathUtil.q(RotationManager.G, -90.0F, 90.0F);
         }
      }
   }

   private BlockPos C(Minecraft var1, EntityPlayerSP var2, int var3, List var4, short var5, float var6, float var7, char var8) {
      long var9 = ((long)var3 << 32 | (long)var5 << 48 >>> 32 | (long)var8 << 48 >>> 48) ^ ab;
      long var11 = var9 ^ 81303667785638L;
      long var13 = var9 ^ 78329467540907L;
      BlockPos var15 = null;
      boolean var16 = false;
      float var17 = Float.MAX_VALUE;
      double var18 = Double.MAX_VALUE;

      for (BlockPos var21 : (Iterable<BlockPos>)(var4)) {
         if (this.t(var13, var21) && this.R(var1, var2, var11, var21)) {
            float var22 = this.f(var2, var21, var6);
            if (!(var22 > var7)) {
               boolean var23 = this.Q(var21);
               double var24 = var21.distanceSq(var2.posX, var2.posY, var2.posZ);
               if (var15 == null
                  || var23 && !var16
                  || var23 == var16 && var22 < var17 - 0.001F
                  || var23 == var16 && Math.abs(var22 - var17) < 0.001F && var24 < var18) {
                  var15 = var21;
                  var16 = var23;
                  var17 = var22;
                  var18 = var24;
               }
            }
         }
      }

      return var15;
   }

   private Float g(Minecraft var1, EntityPlayerSP var2, List var3, long var4) {
      var4 = ab ^ var4;
      int var6 = (int)((var4 ^ 75739334580263L) >>> 32);
      int var7 = (int)((var4 ^ 75739334580263L) << 32 >>> 48);
      int var8 = (int)((var4 ^ 75739334580263L) << 48 >>> 48);
      float var9 = this.w(this.m(var2));
      float var10 = this.w(var9 + 180.0F);
      float var11 = this.w(var9 - 90.0F);
      float var12 = this.w(var9 + 90.0F);
      BlockPos var13 = this.C(var1, var2, var6, var3, (short)var7, var10, 70.0F, (char)var8);
      if (var13 != null) {
         return 180.0F;
      } else {
         BlockPos var14 = this.C(var1, var2, var6, var3, (short)var7, var11, 70.0F, (char)var8);
         BlockPos var15 = this.C(var1, var2, var6, var3, (short)var7, var12, 70.0F, (char)var8);
         if (var14 == null && var15 == null) {
            return null;
         } else if (var14 != null && var15 == null) {
            return -90.0F;
         } else if (var14 == null) {
            return 90.0F;
         } else {
            return this.D(var2, var14, var15) ? -90.0F : 90.0F;
         }
      }
   }

   private boolean i(BlockPos var1, long var2) {
      long var4 = var2 ^ 111115377937706L;

      for (BlockPos var11 : this.K.M()) {
         if (var11 != null && var11.equals(var1)) {
            return false;
         }
      }

      Block var13 = F.theWorld.getBlockState(var1).getBlock();
      if (var13 == Blocks.air && var1.equals(this.M)) {
         long var14 = System.currentTimeMillis();
         this.u7 = var14;
         this.O = var14;
         this.o(0L);
         if (MiningConstants.w == 1 && uA.nextFloat() < MiningConstants.Q / 100.0F) {
            this.V = true;
            this.e = var14;
         }

         this.M = null;
         if (this.b != null) {
            this.v = this.b;
            this.W(var4);
            this.b = null;
         }
      }

      boolean var15 = var13 != Blocks.air && var13.getBlockHardness(F.theWorld, var1) >= 0.0F && !BrokenBlockTracker.m.k(var1);
      if (var15 && !var1.equals(this.M)) {
         this.u7 = System.currentTimeMillis();
      }

      return var15;
   }

   private boolean K(Minecraft var1, EntityPlayerSP var2, long var3, List var5, long var6) {
      var6 = ab ^ var6;
      long var8 = var6 ^ 127319558266917L;
      int var10 = (int)((var6 ^ 26063258658013L) >>> 32);
      int var11 = (int)((var6 ^ 26063258658013L) << 32 >>> 48);
      int var12 = (int)((var6 ^ 26063258658013L) << 48 >>> 48);
      long var13 = var6 ^ 67968879239288L;
      if (MiningConstants.A && !this.J) {
         Float var15 = this.g(var1, var2, var5, var13);
         if (var15 == null) {
            return false;
         }

         this.j(var8);
         this.M = null;
         this.k = var3;
         this.o(var15, var10, var11, (char)var12);
         return true;
      } else {
         return false;
      }
   }

   private void C(int var1, char var2, char var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ ab;
      long var6 = var4 ^ 103215548124182L;
      KeyBindUtil.A(var6, F.gameSettings.keyBindForward.getKeyCode(), false);
      KeyBindUtil.A(var6, F.gameSettings.keyBindJump.getKeyCode(), false);
      KeyBindUtil.A(var6, F.gameSettings.keyBindBack.getKeyCode(), false);
      KeyBindUtil.A(var6, F.gameSettings.keyBindLeft.getKeyCode(), false);
      KeyBindUtil.A(var6, F.gameSettings.keyBindRight.getKeyCode(), false);
      KeyBindUtil.A(var6, F.gameSettings.keyBindAttack.getKeyCode(), false);
      KeyBindUtil.A(var6, F.gameSettings.keyBindSneak.getKeyCode(), false);
   }

   private void W(long var1) {
      long var3 = var1 ^ 72475297152960L;
      RotationManager.N(var3, this.v, this.uM);
      this.v = RotationManager.r;
      this.uM = MathUtil.q(RotationManager.G, -90.0F, 90.0F);
   }

   private boolean D(EntityPlayerSP var1, BlockPos var2, BlockPos var3) {
      boolean var4 = this.Q(var2);
      boolean var5 = this.Q(var3);
      if (var4 != var5) {
         return var4;
      }

      double var6 = var2.distanceSq(var1.posX, var1.posY, var1.posZ);
      double var8 = var3.distanceSq(var1.posX, var1.posY, var1.posZ);
      return Math.abs(var6 - var8) > 0.001 ? var6 < var8 : uA.nextBoolean();
   }

   private boolean u(int var1, long var2, BlockPos var4) {
      long var5 = ((long)var1 << 32 | var2 << 32 >>> 32) ^ ab;
      long var7 = var5 ^ 36992732672863L;
      Block var9 = F.theWorld.getBlockState(var4).getBlock();
      return var9 != Blocks.air && !this.t(var7, var4);
   }

   private void o(long var1) {
      this.H();
      this.u = false;
   }

   private BlockPos R(EntityPlayerSP var1) {
      double var2 = Math.toRadians(this.m(var1));
      int var4 = (int)Math.round(-Math.sin(var2));
      int var5 = (int)Math.round(Math.cos(var2));
      int var6 = (int)Math.floor(var1.posX) + var4;
      int var7 = (int)Math.floor(var1.posY);
      int var8 = (int)Math.floor(var1.posZ) + var5;
      return new BlockPos(var6, var7, var8);
   }

   private BlockPos i(short var1, int var2, int var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ ab;
      long var6 = var4 ^ 138104599058938L;
      int var10 = (int)((var4 ^ 2315358876204L) >>> 32);
      int var11 = (int)((var4 ^ 2315358876204L) << 32 >>> 48);
      int var12 = (int)((var4 ^ 2315358876204L) << 48 >>> 48);
      if (this.M == null) {
         return null;
      }

      Vec3 var13 = F.thePlayer.getPositionEyes(1.0F);
      Vec3 var14 = new Vec3(this.M.getX() + 0.5, this.M.getY() + 0.5, this.M.getZ() + 0.5);
      Vec3 var15 = var14.subtract(var13);
      double var16 = var15.lengthVector();
      Vec3 var18 = new Vec3(var15.xCoord / var16, var15.yCoord / var16, var15.zCoord / var16);
      double var19 = 0.1;
      int var21 = (int)Math.ceil(var16 / var19);

      for (int var22 = 1; var22 < var21 - 1; var22++) {
         double var23 = var19 * var22;
         Vec3 var25 = new Vec3(
            var13.xCoord + var18.xCoord * var23,
            var13.yCoord + var18.yCoord * var23,
            var13.zCoord + var18.zCoord * var23
         );
         BlockPos var26 = new BlockPos(var25.xCoord, var25.yCoord, var25.zCoord);
         Block var27 = F.theWorld.getBlockState(var26).getBlock();
         if (var27 != Blocks.air) {
            if (!var26.equals(this.M) && this.X(var26)) {
               BoxRenderer.I( var26, new Color(0, 0, 255, 180));
               this.X(var10, (char)var11, var12);
               this.M = null;
               return var26;
            }

            if (!var26.equals(this.M) && this.S(var6, var26)) {
               return var26;
            }

            if (this.f() && !var26.equals(this.M)) {
               return var26;
            }
         }
      }

      return null;
   }

   private boolean G(Minecraft var1, long var2, EntityPlayerSP var4, long var5) {
      var2 = ab ^ var2;
      int var7 = (int)((var2 ^ 88367466194687L) >>> 32);
      int var8 = (int)((var2 ^ 88367466194687L) << 32 >>> 48);
      int var9 = (int)((var2 ^ 88367466194687L) << 48 >>> 48);
      long var10 = var2 ^ 58818843149764L;
      if (this.f() && this.M != null && !this.R(var1, var4, var10, this.M)) {
         if (!this.n(var7, var1, var4, var8, (char)var9, var5)) {
            return false;
         }

         this.v = RotationManager.r;
         this.uM = RotationManager.G;
         this.h = this.w(RotationManager.r);
         return true;
      } else {
         return false;
      }
   }

   private void l(long var1, EntityPlayerSP var3, BlockPos var4, long var5) {
      var1 = ab ^ var1;
      long var7 = var1 ^ 68598441642090L;
      long var9 = var1 ^ 124490930244750L;
      Block var13 = F.theWorld.getBlockState(var4).getBlock();
      this.M = var4;
      this.A(this.M, var7);
      KeyBindUtil.A(var9, F.gameSettings.keyBindForward.getKeyCode(), false);
      KeyBindUtil.A(var9, F.gameSettings.keyBindBack.getKeyCode(), false);
      KeyBindUtil.A(var9, F.gameSettings.keyBindSneak.getKeyCode(), false);
      KeyBindUtil.A(var9, F.gameSettings.keyBindJump.getKeyCode(), false);
      if (MiningConstants.r) {
         int var14 = this.U(var3, var13);
         if (var14 != -1 && var14 != var3.inventory.currentItem) {
            var3.inventory.currentItem = var14;
         }
      }

      this.S = var5;
   }

   private int U(EntityPlayerSP var3, Block var4) {
      float var5 = 1.0F;
      int var6 = -1;

      for (int var7 = 0; var7 < 9; var7++) {
         ItemStack var8 = var3.inventory.getStackInSlot(var7);
         if (var8 != null) {
            float var9 = var8.getStrVsBlock(var4);
            if (var8.getItem() instanceof ItemTool) {
               if (var9 > var5) {
                  var5 = var9;
                  var6 = var7;
               }
            } else if (var9 > var5) {
               var6 = var7;
            }
         }
      }

      return var6;
   }

   private BlockPos W(long var1, Minecraft var3, EntityPlayerSP var4) {
      var1 = ab ^ var1;
      long var5 = var1 ^ 82618915614421L;
      long var7 = var1 ^ 136618739392701L;
      MiningProgress var9 = this.K.d();
      if (var9 != null && this.T(var3, var7, var4, var9.i)) {
         return var9.i;
      }

      BlockPos var10 = this.E(var5, var3, var4);
      if (var10 != null) {
         return var10;
      }

      BlockPos var11 = this.K.U();
      if (this.T(var3, var7, var4, var11)) {
         return var11;
      }

      for (BlockPos var15 : new BlockPos[]{this.R(var4), this.u$r2(var4)}) {
         if (this.T(var3, var7, var4, var15)) {
            return var15;
         }
      }

      for (BlockPos var18 : this.u(var4)) {
         if (this.T(var3, var7, var4, var18)) {
            return var18;
         }
      }

      return null;
   }

   private boolean C(long var1, EntityPlayerSP var3, long var4) {
      if (this.f() && !this.J) {
         List var15 = this.D(0L);
         BlockPos var16 = this.C(F, var3, 8189, var15, (short)32783, this.w(this.m(var3)), 70.0F, (char)8391);
         if (var16 != null && !var16.equals(this.M)) {
            this.M = var16;
            this.A(this.M, 6377414525953L);
            this.S = var4;
            return true;
         } else {
            return this.K(F, var3, var4, var15, 113038385092760L);
         }
      } else {
         return false;
      }
   }

   private void H() {
      this.Z = false;
      this.G = 0L;
   }

   public void I(long var1) {
      long var3 = 99005023413082L;
      if (MiningConstants.o) {
         if (this.L != null) {
            BoxRenderer.p(
               this.L, var3, new Color(0, 255, 255, 20)
            );
         }

         for (BlockPos var6 : this.E) {
            BoxRenderer.p(
               var6,
               var3,
               new Color(
                  180,
                  180,
                  180,
                  40
               )
            );
         }
      }
   }

   public void B(long var1, int var3) {
      long var4 = (var1 << 32 | (long)var3 << 32 >>> 32) ^ ab;
      long var6 = var4 ^ 23036030938981L;
      this.T(null, var6);
   }

   private BlockPos d(byte var1, int var2, int var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 56 | (long)var2 << 32 >>> 8 | (long)var3 << 40 >>> 40) ^ ab;
      long var6 = var4 ^ 54597762249479L;
      long var10 = var4 ^ 10022703091818L;
      int var12 = (int)((var4 ^ 55772732089079L) >>> 32);
      int var13 = (int)((var4 ^ 55772732089079L) << 32 >>> 48);
      int var14 = (int)((var4 ^ 55772732089079L) << 48 >>> 48);
      int var15 = (int)((var4 ^ 127416340415130L) >>> 48);
      long var16 = (var4 ^ 127416340415130L) << 16 >>> 16;
      long var18 = var4 ^ 80762697020132L;
      int var20 = (int)((var4 ^ 67936535552057L) >>> 32);
      int var21 = (int)((var4 ^ 67936535552057L) << 32 >>> 48);
      int var22 = (int)((var4 ^ 67936535552057L) << 48 >>> 48);
      if (this.f()) {
         EntityPlayerSP var55 = F.thePlayer;
         List var57 = this.D(0L);
         BlockPos var59 = this.C(F, var55, var12, var57, (short)var13, this.w(this.m(var55)), 70.0F, (char)var14);
         if (var59 != null) {
            this.j(var10);
            return var59;
         }

         if (this.S((char)var15, var16)) {
            return null;
         }

         this.j(var10);
         this.L(var6, "no directly reachable gapalt targets found in forward, side, or reverse search directions");
         return null;
      } else {
         this.I.clear();
         if (MiningConstants.v && F.thePlayer != null && F.theWorld != null) {
            EntityPlayerSP var23 = F.thePlayer;
            BlockPos var24 = this.R(var23);
            BlockPos var25 = this.u$r2(var23);
            BlockPos var26 = this.j(var23);
            BlockPos var27 = null;
            if (this.m && this.i(var25, var18)) {
               this.I.add(var25);
               var27 = var25;
            }

            if (var27 == null && this.i(var24, var18)) {
               this.I.add(var24);
               var27 = var24;
            }

            if (var27 == null && this.i(var26, var18)) {
               this.I.add(var26);
               var27 = var26;
            }

            this.u$r3(var23);
            if (!this.I.isEmpty()) {
               BlockPos var28 = this.K.U();
               if (var28 != null && this.I.contains(var28)) {
                  return var28;
               }

               this.I.removeIf(this::z);
               return var27;
            }

            if (this.m) {
               return null;
            }
         }

         EntityPlayerSP var54 = F.thePlayer;
         float var56 = this.w(this.m(var54));
         double var58 = Math.toRadians(var56);
         double var60 = -Math.sin(var58);
         double var29 = Math.cos(var58);

         for (double var31 = 0.2; var31 < 1.2; var31 += 0.2) {
            int var33 = (int)Math.floor(var54.posX + var60 * var31);
            int var34 = (int)var54.posY;
            int var35 = (int)Math.floor(var54.posZ + var29 * var31);
            BlockPos var36 = new BlockPos(var33, var34, var35);
            BlockPos var37 = new BlockPos(var33, var34 + 1, var35);
            if (this.i(var36, var18)) {
               return var36;
            }

            if (this.i(var37, var18)) {
               return var37;
            }
         }

         for (double var61 = 1.2; var61 <= 2.2; var61++) {
            int var64 = (int)Math.floor(var54.posX + var60 * var61);
            int var66 = (int)var54.posY;
            int var68 = (int)Math.floor(var54.posZ + var29 * var61);
            BlockPos var69 = new BlockPos(var64, var66, var68);
            if (this.X(var69)) {
               this.c.add(new MinedBlockTimestamp(var69, System.currentTimeMillis()));
               this.X(var20, (char)var21, var22);
               return null;
            }
         }

         BlockPos var62 = null;

         for (double var32 = 1.2; var32 <= 4.2; var32++) {
            double var67 = var54.posX + var60 * var32;
            double var70 = var54.posZ + var29 * var32;
            int var38 = (int)Math.floor(var67);
            int var39 = (int)var54.posY;
            int var40 = (int)Math.floor(var70);
            BlockPos var41 = new BlockPos(var38, var39, var40);
            BlockPos var42 = new BlockPos(var38, var39 + 1, var40);
            if (this.i(var41, var18)) {
               if (var62 == null) {
                  var62 = var41;
               }

               this.I.add(var41);
            }

            if (this.i(var42, var18)) {
               if (var62 == null) {
                  var62 = var42;
               }

               this.I.add(var42);
            }

            if (MiningConstants.q) {
               for (double var43 = -0.3; var43 <= 0.3; var43 += 0.3) {
                  if (var43 != 0.0) {
                     double var45 = var67 + var29 * var43;
                     double var47 = var70 - var60 * var43;
                     int var49 = (int)Math.floor(var45);
                     int var50 = (int)var54.posY;
                     int var51 = (int)Math.floor(var47);
                     BlockPos var52 = new BlockPos(var49, var50, var51);
                     BlockPos var53 = new BlockPos(var49, var50 + 1, var51);
                     if (this.i(var52, var18)) {
                        if (var62 == null) {
                           var62 = var52;
                        }

                        this.I.add(var52);
                     }

                     if (this.i(var53, var18)) {
                        if (var62 == null) {
                           var62 = var53;
                        }

                        this.I.add(var53);
                     }
                  }
               }
            }
         }

         BlockPos var63 = this.K.U();
         if (var63 != null && this.I.contains(var63)) {
            int var65 = (int)var54.posY;
            if (var63.getY() > var65) {
               return var63;
            }
         }

         if (var62 == null) {
            if (this.l == 0L) {
               this.l = System.currentTimeMillis();
            }

            if ((float)(System.currentTimeMillis() - this.l) > MiningConstants.c * 1000.0F) {
               this.L(var6, "no blocks found for too long");
            }
         } else {
            this.l = 0L;
         }

         return var62;
      }
   }

   private boolean N(long var1, Minecraft var3, EntityPlayerSP var4, BlockPos var5) {
      long var6 = var1 ^ 128627607276251L;
      if (var4.getDistanceSq(var5.getX() + 0.5, var5.getY() + 0.5, var5.getZ() + 0.5) > 25.0) {
         return false;
      }

      Vec3 var8 = var4.getPositionEyes(1.0F);

      for (Vec3 var12 : this.a(var6, var5)) {
         MovingObjectPosition var13 = var3.theWorld.rayTraceBlocks(var8, var12, false, true, false);
         if (var13 != null && var13.typeOfHit == MovingObjectType.BLOCK && var5.equals(var13.getBlockPos())) {
            return true;
         }
      }

      return false;
   }

   private void j(long var1) {
      this.A = false;
      this.H = 0;
   }

   private void o(float var1, int var2, int var3, char var4) {
      long var5 = ((long)var2 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ ab;
      long var7 = var5 ^ 65888423836832L;
      this.J = true;
      this.B = RotationManager.r + var1;

      while (this.B > 180.0F) {
         this.B -= 360.0F;
      }

      while (this.B < -180.0F) {
         this.B += 360.0F;
      }

      KeyBindUtil.A(var7, F.gameSettings.keyBindForward.getKeyCode(), false);
      this.h = this.w(this.B);
   }

   public final void x(long var1, EventBus var3) {
      MiningEngineBinder.t(var3, this);
   }

   private boolean n(int var1, Minecraft var2, EntityPlayerSP var3, int var4, char var5, long var6) {
      long var8 = ((long)var1 << 32 | (long)var4 << 48 >>> 32 | (long)var5 << 48 >>> 48) ^ ab;
      long var10 = var8 ^ 119778099325258L;
      if (this.f() && !this.j && !this.W) {
         BlockPos var12 = this.W(var10, var2, var3);
         if (var12 == null) {
            return false;
         }

         this.j = true;
         this.W = true;
         this.X = var6 + 1200L;
         this.M = var12;
         this.u7 = var6;
         return true;
      } else {
         return false;
      }
   }

   public void onPreTick(PreTickEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var10001 = 120463243594918L;

      int var8 = (int)(var10001 << 48 >>> 48);

      var10001 = 39350982965954L;

      int var13 = (int)(var10001 << 48 >>> 48);

      var10001 = 2682888230589L;

      int var24 = (int)(var10001 << 48 >>> 48);
      var10001 = 18200678687241L;

      int var29 = (int)(var10001 << 48 >>> 48);
      var10001 = 55106932204454L;

      int var34 = (int)(var10001 << 48 >>> 48);
      var10001 = 67791441774702L;

      int var37 = (int)(var10001 << 32 >>> 32);

      var10001 = 30892599371991L;

      int var52 = (int)(var10001 << 40 >>> 40);

      if (this.D && !this.p) {
         KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindAttack.getKeyCode(), true);
         this.K.b();
         this.K.T(17790601399577L);
         this.K.v(14218255969322L);
         this.K.C();
         EntityPlayerSP var61 = F.thePlayer;
         long var62 = System.currentTimeMillis();
         if (MiningConstants.v) {
            BlockPos var64 = new BlockPos(Math.floor(var61.posX), Math.floor(var61.posY) - 1.0, Math.floor(var61.posZ));
            if (F.theWorld.getBlockState(var64).getBlock() == Blocks.bedrock) {
               if (this.d == null) {
                  this.d = Boolean.TRUE;
               }

               MiningConstants.v = false;
               if (this.n != -1) {
                  MiningConstants.w = this.n;
                  this.n = -1;
               }
            }
         }

         boolean var78 = this.K.G();
         if (var78) {
            for (BlockPos var68 : this.K.M()) {
               Block var69 = F.theWorld.getBlockState(var68).getBlock();
               if (var69 == Blocks.chest || var69 == Blocks.trapped_chest) {
                  this.M = var68;
                  if (this.b == null) {
                     this.b = RotationManager.r;
                  }

                  this.A(this.M, 6377414525953L);
                  if (!this.q && !this.f) {
                     this.f = true;
                     this.r = true;
                     this.z = System.currentTimeMillis() + 700L;
                  }
                  break;
               }
            }
         }

         this.q = var78;
         double var79 = var61.posX;
         double var80 = var61.posZ;
         double var81 = Math.sqrt(Math.pow(var79 - this.C, 2.0) + Math.pow(var80 - this.Q, 2.0));
         if (var81 > 1.0) {
            this.C = var79;
            this.Q = var80;
            this.S = var62;
            this.f(28047, (char)35031, (short)var8);
            this.U();
            this.o(0L);
         } else if (this.w) {
            if (this.z(var62, 50763164016555L)) {
               return;
            }
         } else if (this.j) {
            if (var62 > this.X) {
               this.U();
               this.L(31800236978487L, this.k(0L));
               return;
            }
         } else if ((float)(var62 - this.S) > MiningConstants.X * 1000.0F) {
            if (this.a((char)0, var62, (short)15783, var37)) {
               return;
            }

            if (this.x(var61, 18422345256146L) && this.C(41474706476091L, var61, var62)) {
               return;
            }

            if (this.n(624, F, var61, 43161, (char)var24, var62)) {
               return;
            }

            this.L(31800236978487L, this.k(0L));
            return;
         }

         if (this.Z && var62 > this.G) {
            this.H();
         }

         if (!this.j && !this.Z && (float)(var62 - this.u7) > MiningConstants.s * 1000.0F && this.u7 != 0L) {
            this.L(31800236978487L, "no block broken for " + (var62 - this.u7) / 1000L + " seconds");
         } else if (this.J) {
            this.n(3902860684954L);
         } else {
            KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindJump.getKeyCode(), false);
            boolean var71 = MiningConstants.w != 2;
            if (this.V) {
               if ((float)(System.currentTimeMillis() - this.e) > MiningConstants.e) {
                  this.V = false;
               } else {
                  var71 = false;
               }
            }

            if (!this.f(12830, (short)38174, var61, var62, (char)var34)) {
               if (MiningConstants.v && this.M == null) {
                  BlockPos var72 = this.R(var61);
                  BlockPos var73 = this.j(var61);
                  if (F.theWorld.getBlockState(var72).getBlock() == Blocks.air
                     && F.theWorld.getBlockState(var73).getBlock() == Blocks.air) {
                     KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindForward.getKeyCode(), true);
                  }
               }

               if (this.M != null && this.z(this.M)) {
                  this.M = null;
               }

               if (this.M != null) {
                  if (this.x(var61, 18422345256146L)) {
                     this.M = null;
                     return;
                  }

                  BlockPos[] var10000 = new BlockPos[6];
                  var10000[0] = this.M.north();
                  var10000[1] = this.M.south();
                  var10000[2] = this.M.east();
                  var10000[3] = this.M.west();
                  var10000[4] = this.M.up();
                  var10000[5] = this.M.down();

                  for (BlockPos var75 : var10000) {
                     Block var76 = F.theWorld.getBlockState(var75).getBlock();
                     if ((var76 == Blocks.chest || var76 == Blocks.trapped_chest) && this.Q(var75)) {
                        BoxRenderer.I( var75, new Color(0, 0, 255, 180));
                        this.M = var75;
                        break;
                     }
                  }

                  if (var62 - this.R > 10L) {
                     this.R = var62;
                     BlockPos var83 = this.i((short)0, 600448348, var13);
                     if (var83 != null) {
                        if (this.f() && !this.t(116121547939723L, var83)) {
                           this.M = null;
                           return;
                        }

                        if (MiningConstants.v) {
                           BlockPos var87 = this.R(var61);
                           BlockPos var89 = this.j(var61);
                           if (var83.equals(var87) || var83.equals(var89)) {
                              this.M = var83;
                              this.A(this.M, 6377414525953L);
                           }
                        } else {
                           this.M = var83;
                           this.A(this.M, 6377414525953L);
                        }
                     }

                     if (this.M == null) {
                        return;
                     }
                  }

                  double var84 = Math.sqrt(
                     Math.pow(this.M.getX() + 0.5 - var61.posX, 2.0) + Math.pow(this.M.getZ() + 0.5 - var61.posZ, 2.0)
                  );
                  KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindForward.getKeyCode(), var84 > 1.5);
                  KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindBack.getKeyCode(), false);
                  KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindSneak.getKeyCode(), var71);
                  if (this.i(this.M, 110157827715284L)) {
                     this.A(this.M, 6377414525953L);
                     if (MiningConstants.r) {
                        Block var90 = F.theWorld.getBlockState(this.M).getBlock();
                        int var91 = this.U(var61, var90);
                        if (var91 != -1 && var91 != var61.inventory.currentItem) {
                           var61.inventory.currentItem = var91;
                        }
                     }

                     if (this.m) {
                        this.m = false;
                     }
                  } else {
                     this.M = null;
                  }

                  if (this.b != null) {
                     this.v = this.b;
                     this.W(1370338967038L);
                  }
               } else {
                  this.M = this.d((byte)0, 1841342, var52);
                  if (this.M != null) {
                     this.A(this.M, 6377414525953L);
                     return;
                  }

                  if (this.J) {
                     return;
                  }

                  if (this.X(4237, (char)44284, var29)) {
                     return;
                  }

                  if (this.J || !this.D) {
                     return;
                  }

                  KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindForward.getKeyCode(), true);
                  KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindSneak.getKeyCode(), var71);
               }

               if (!this.f() && !this.J && !Float.isNaN(this.h)) {
                  float var85 = this.w(RotationManager.r);
                  float var88 = Math.abs(var85 - this.h);
                  if (var88 > 180.0F) {
                     var88 = 360.0F - var88;
                  }

                  if (var88 > 45.0F) {
                     this.L(31800236978487L, "unexpected facing direction detected");
                     return;
                  }
               }

               if (MiningConstants.x && !this.p && this.K.I()) {
                  this.p = true;
                  new Thread(() -> {
                     try {
                        KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindForward.getKeyCode(), false);
                        KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindBack.getKeyCode(), true);
                        Thread.sleep(700L);
                        KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindBack.getKeyCode(), false);
                     } catch (Exception var6x) {
                     }

                     F.addScheduledTask(() -> {
                        long var3x = 129958705539448L;

                        int var7x = 1429416877;
                        this.B(22332L, var7x);
                        this.a(var3x);
                        this.p = false;
                     });
                  }).start();
               } else {
                  if (this.r) {
                     KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindBack.getKeyCode(), true);
                     KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindSneak.getKeyCode(), true);
                     if (System.currentTimeMillis() >= this.z) {
                        KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindBack.getKeyCode(), false);
                        KeyBindUtil.A(82009306480869L, F.gameSettings.keyBindSneak.getKeyCode(), false);
                        this.r = false;
                        this.f = false;
                     }
                  }
               }
            }
         }
      }
   }

   private boolean x() {
      if (this.j) {
         return false;
      }

      if (this.J) {
         return false;
      }

      float var1 = Math.abs(this.O(RotationManager.r - this.v));
      float var2 = Math.abs(RotationManager.G - this.uM);
      return var1 > 15.0F || var2 > 15.0F;
   }

   private static String a(byte[] var0) {
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

   private boolean t(long var1, BlockPos var3) {
      long var4 = var1 ^ 15035500670815L;
      Block var6 = F.theWorld.getBlockState(var3).getBlock();
      // update new version
      boolean var7 = var6 == Blocks.stone || this.Q(var3) || !MiningConstants.gapAltOnlyStone && this.G() && this.B(var6);
      return var7 ? this.i(var3, var4) : false;
   }

   static {
      ab = 54274017209063L;
      zkm$clinit();
      uA = new Random();
      F = MinecraftRef.c((byte)0, 0L);
   }

   private void u(long var1) {
      var1 = ab ^ var1;
      int var3 = (int)((var1 ^ 83361452867767L) >>> 32);
      int var4 = (int)((var1 ^ 83361452867767L) << 32 >>> 48);
      int var5 = (int)((var1 ^ 83361452867767L) << 48 >>> 48);
      if (this.w) {
         this.C(var3, (char)var4, (char)var5);
      }

      this.w = false;
      this.U = 0;
      this.s = 0L;
   }

   private boolean R(Minecraft var1, EntityPlayerSP var2, long var3, BlockPos var5) {
      long var6 = var3 ^ 45786370863314L;
      return this.N(var6, var1, var2, var5);
   }

   private static void zkm$clinit() {
      try {
         long var31 = ab ^ 49477109757273L;
         int var33 = (int)((var31 ^ 31653288287663L) >>> 32);
         int var34 = (int)((var31 ^ 31653288287663L) << 32 >>> 48);
         int var35 = (int)((var31 ^ 31653288287663L) << 48 >>> 48);
         a();
         db = new HashMap(13);
         Cipher var22;
         byte[] var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var23 = 1; var23 < 8; var23++) {
            var10003[var23] = (byte)(var31 << var23 * 8 >>> 56);
         }

         (var22 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var29 = new String[14];
         int var27 = 0;
         String var26 = "ôº:Ân\u0004°îq\u0098\u009a\u001f\u009e\u001bÀ\u0005L\\ÍÝº\u0093\u009bº\u001ec¹@zÑ\u0085 p\u0088ë[0Ù[\u0004\u008eA^VcV8\u0096àþéíöÏªÚ\\(G,IÜg\u00adXß\néy´é¡oÉÒà:<\u0005Ìj;®/+Y»ì\u0016¢ÊhÝ `[õt×\u0086´\u009bâËnkw\u0094bP\r\u0086\u009d\u0097Ï\u0013Âo \u0010\u009d\u0083b}p\u0015_Ý\u0090*E\u0096K\u0087\u0094vvvØ {4ó\u0090QºïÁ]Ô\u000bæÑ\u0018\u0084æ\r>YQ\u008fA5©¡\fÑ?\u00049Sb7«Õ\u009fË\nPB:Ç2$\u009a¢\u009c\rÁ\u0003E\f\u0016ôc4Ò1í\u0001\u001ePp\u0002~È\u0087£û\u007f¼lÜ;\u0092bJR\f@ZCÙk´\u001aC\u0080\u008a\u009cò¹ºL0\u009d\u0080Î\u009bM\u0017È\\\u0002\u0001,5^\raMKÚÏF~±\u0094ð@dÝE\u0089QA\u0088ßäF\u0089Ö>q¬HZà°¦k\u0097ä\u008eXRÂ\u0016s\u0098\u0017R\n&\nE\täªt:¡ÝòG\u0089 [+DöÔo@\u0088ô\u0000Å\u0082!^\u0002=Ç8?FIG\u0093å\u0088éä\u007fm\u0083aÎ\u001aáýÇ2\\\u0087u§h\u0090`#uá\u0092Ò\n\u0010rVÁ¤psº½\u0018î\u0084¶¬fN\u00141\u001bñ@i{\u008a@Ò7^£\u001b\u0083Ërúæ¿@nÖ>)È£\u0018°ºÔ.ËE*çý\u0014Y¢ò\u0011&V|ÚÀÃ+*MØõ»hâD³¤â\t\u001fà\b7{¬Ô0R\u009d+ÁP4(H\u008cò¿bMí!s\u001fý;s\\S\u0085M((*\u0014Äm\u009e¬\u0088Ò\u000f\u0091t¥b\u001f\u0083\\Tv\u0095õtU°ðD\\o<M¤ÊB!p\u000b¡«\u001a©\u0092±0\u0083°í{\u0087)N8¨Ã¸Ê\u0004êdE\u0087@X\u009f\u009aÜñ\u008e«lo¯òc$Ôª<\u0006´\u0082$Ç/á\u00ad«êÇ\u0006±\u001eæÕ9BÅ \u008e\u0083\u000b\u0084\u0083´Éö¢å#\u0010ô!ýÏ+èsÄIQ:\u001e\u0082¢Ì(\u0018ó»äÈq\u0086k\u0017MV_¹\u0091¨6\u009af\u0001/Snìßq`5Èx®¬Õc¾ÿö\u009a¶ô Kx\u0095ìåÑ\u007fÓ\u009c¶\u00adKÜ\u0092\u0094ô\u008a\u0019Ü^\u001bN\u008d,\u000f\u009d¡\u001e9b°\u0019b4\u0012\u0012µ;Ì\u008aÉÄ\u0012¾ÒsM\u0087f÷1;kÎ´;´¦\u0080³\u0095ó¿\u0016\u0015\u0095\u0002\u0089ßp>0f\u00ad5`]\f7o\u001cÎ0é¬ñ®ió²ð\rç»¤\u0088\u0016,\u0087\u0013\u009c(ÿ7 µc}(X\u0000ò&¯|\u0012$\u0097\r\u009aã\u009cK'¯Äû\u00adÓ¹Ö";
         int var28 = "ôº:Ân\u0004°îq\u0098\u009a\u001f\u009e\u001bÀ\u0005L\\ÍÝº\u0093\u009bº\u001ec¹@zÑ\u0085 p\u0088ë[0Ù[\u0004\u008eA^VcV8\u0096àþéíöÏªÚ\\(G,IÜg\u00adXß\néy´é¡oÉÒà:<\u0005Ìj;®/+Y»ì\u0016¢ÊhÝ `[õt×\u0086´\u009bâËnkw\u0094bP\r\u0086\u009d\u0097Ï\u0013Âo \u0010\u009d\u0083b}p\u0015_Ý\u0090*E\u0096K\u0087\u0094vvvØ {4ó\u0090QºïÁ]Ô\u000bæÑ\u0018\u0084æ\r>YQ\u008fA5©¡\fÑ?\u00049Sb7«Õ\u009fË\nPB:Ç2$\u009a¢\u009c\rÁ\u0003E\f\u0016ôc4Ò1í\u0001\u001ePp\u0002~È\u0087£û\u007f¼lÜ;\u0092bJR\f@ZCÙk´\u001aC\u0080\u008a\u009cò¹ºL0\u009d\u0080Î\u009bM\u0017È\\\u0002\u0001,5^\raMKÚÏF~±\u0094ð@dÝE\u0089QA\u0088ßäF\u0089Ö>q¬HZà°¦k\u0097ä\u008eXRÂ\u0016s\u0098\u0017R\n&\nE\täªt:¡ÝòG\u0089 [+DöÔo@\u0088ô\u0000Å\u0082!^\u0002=Ç8?FIG\u0093å\u0088éä\u007fm\u0083aÎ\u001aáýÇ2\\\u0087u§h\u0090`#uá\u0092Ò\n\u0010rVÁ¤psº½\u0018î\u0084¶¬fN\u00141\u001bñ@i{\u008a@Ò7^£\u001b\u0083Ërúæ¿@nÖ>)È£\u0018°ºÔ.ËE*çý\u0014Y¢ò\u0011&V|ÚÀÃ+*MØõ»hâD³¤â\t\u001fà\b7{¬Ô0R\u009d+ÁP4(H\u008cò¿bMí!s\u001fý;s\\S\u0085M((*\u0014Äm\u009e¬\u0088Ò\u000f\u0091t¥b\u001f\u0083\\Tv\u0095õtU°ðD\\o<M¤ÊB!p\u000b¡«\u001a©\u0092±0\u0083°í{\u0087)N8¨Ã¸Ê\u0004êdE\u0087@X\u009f\u009aÜñ\u008e«lo¯òc$Ôª<\u0006´\u0082$Ç/á\u00ad«êÇ\u0006±\u001eæÕ9BÅ \u008e\u0083\u000b\u0084\u0083´Éö¢å#\u0010ô!ýÏ+èsÄIQ:\u001e\u0082¢Ì(\u0018ó»äÈq\u0086k\u0017MV_¹\u0091¨6\u009af\u0001/Snìßq`5Èx®¬Õc¾ÿö\u009a¶ô Kx\u0095ìåÑ\u007fÓ\u009c¶\u00adKÜ\u0092\u0094ô\u008a\u0019Ü^\u001bN\u008d,\u000f\u009d¡\u001e9b°\u0019b4\u0012\u0012µ;Ì\u008aÉÄ\u0012¾ÒsM\u0087f÷1;kÎ´;´¦\u0080³\u0095ó¿\u0016\u0015\u0095\u0002\u0089ßp>0f\u00ad5`]\f7o\u001cÎ0é¬ñ®ió²ð\rç»¤\u0088\u0016,\u0087\u0013\u009c(ÿ7 µc}(X\u0000ò&¯|\u0012$\u0097\r\u009aã\u009cK'¯Äû\u00adÓ¹Ö"
            .length();
         char var25 = '@';
         int var43 = -1;

         label85:
         while (true) {
            String var44 = var26.substring(++var43, var43 + var25);
            int var50 = -1;

            while (true) {
               byte[] var30 = var22.doFinal(var44.getBytes("ISO-8859-1"));
               String var61 = a(var30).intern();
               switch (var50) {
                  case 0:
                     var29[var27++] = var61;
                     if ((var43 += var25) >= var28) {
                        bb = var29;
                        cb = new String[14];
                        gb = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(var31 << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var17 = new long[18];
                        int var14 = 0;
                        String var15 = "Ô\u0006\u0090\u0091ø\u00adu\u008d&çÿ»_ÆYbýBU\u0006\u001fü\f}êC\u000e¾Ü;Dc\u000b\u009cÐl\u0089¿\u0087åà\u0086ª\u001f9\u0012ûò\u0016Iú\u0006\u001e\"\u0082¡HS¢sË\u001e3ò\u000b\u009cÆàc\u0098;ò\u0087M6\u0099\u001aõWûo@6(pµ#b°P\u008aü(\u0098ÈG\u0096\tã?R=\u0011\u0002l\u0000e\u001dþ2ÓæVtºÿ\u001b\u0011\u0083æ\u0080ªQã7§`\u000b";
                        int var16 = "Ô\u0006\u0090\u0091ø\u00adu\u008d&çÿ»_ÆYbýBU\u0006\u001fü\f}êC\u000e¾Ü;Dc\u000b\u009cÐl\u0089¿\u0087åà\u0086ª\u001f9\u0012ûò\u0016Iú\u0006\u001e\"\u0082¡HS¢sË\u001e3ò\u000b\u009cÆàc\u0098;ò\u0087M6\u0099\u001aõWûo@6(pµ#b°P\u008aü(\u0098ÈG\u0096\tã?R=\u0011\u0002l\u0000e\u001dþ2ÓæVtºÿ\u001b\u0011\u0083æ\u0080ªQã7§`\u000b"
                           .length();
                        int var13 = 0;

                        label67:
                        while (true) {
                           int var54 = var13;
                           var13 += 8;
                           byte[] var18 = var15.substring(var54, var13).getBytes("ISO-8859-1");
                           long[] var47 = var17;
                           int var55 = var14++;
                           long var65 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           int var71 = -1;

                           while (true) {
                              long var19 = var65;
                              byte[] var21 = var11.doFinal(
                                 new byte[]{
                                    (byte)(var19 >>> 56),
                                    (byte)(var19 >>> 48),
                                    (byte)(var19 >>> 40),
                                    (byte)(var19 >>> 32),
                                    (byte)(var19 >>> 24),
                                    (byte)(var19 >>> 16),
                                    (byte)(var19 >>> 8),
                                    (byte)var19
                                 }
                              );
                              long var76 = (var21[0] & 255L) << 56
                                 | (var21[1] & 255L) << 48
                                 | (var21[2] & 255L) << 40
                                 | (var21[3] & 255L) << 32
                                 | (var21[4] & 255L) << 24
                                 | (var21[5] & 255L) << 16
                                 | (var21[6] & 255L) << 8
                                 | var21[7] & 255L;
                              switch (var71) {
                                 case 0:
                                    var47[var55] = var76;
                                    if (var13 >= var16) {
                                       eb = var17;
                                       fb = new Integer[18];
                                       jb = new HashMap(13);
                                       Cipher var0;
                                       var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                                       for (int var1 = 1; var1 < 8; var1++) {
                                          var10003[var1] = (byte)(var31 << var1 * 8 >>> 56);
                                       }

                                       (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                       long[] var6 = new long[18];
                                       int var3 = 0;
                                       String var4 = "³¶<\u008e¢×\u0089\u0091\u0089sïwã¤\rÄ\u0019%ýiMÓìÚ\u0091Ýÿaþ\u0087ðþÃÃeÍ\u0082)]\\tÇK¿\u0097vu}OÁßd\u009d9*\u0016ÿ&\u008cô\u001eð\u001d\u001a\u001cåAÄ&A*\u0002íª¨\u001aûv}\u0092J§¹ñö°[\u0007\u0006H\u0013êË¿\u008e_9³Õåð\rs\u0019#2mòìi£X3\u008f(/_Uê¼-ßÓl\u0081\u0011tá";
                                       int var5 = "³¶<\u008e¢×\u0089\u0091\u0089sïwã¤\rÄ\u0019%ýiMÓìÚ\u0091Ýÿaþ\u0087ðþÃÃeÍ\u0082)]\\tÇK¿\u0097vu}OÁßd\u009d9*\u0016ÿ&\u008cô\u001eð\u001d\u001a\u001cåAÄ&A*\u0002íª¨\u001aûv}\u0092J§¹ñö°[\u0007\u0006H\u0013êË¿\u008e_9³Õåð\rs\u0019#2mòìi£X3\u008f(/_Uê¼-ßÓl\u0081\u0011tá"
                                          .length();
                                       int var2 = 0;

                                       label51:
                                       while (true) {
                                          int var57 = var2;
                                          var2 += 8;
                                          byte[] var7 = var4.substring(var57, var2).getBytes("ISO-8859-1");
                                          long[] var49 = var6;
                                          int var58 = var3++;
                                          long var68 = (var7[0] & 255L) << 56
                                             | (var7[1] & 255L) << 48
                                             | (var7[2] & 255L) << 40
                                             | (var7[3] & 255L) << 32
                                             | (var7[4] & 255L) << 24
                                             | (var7[5] & 255L) << 16
                                             | (var7[6] & 255L) << 8
                                             | var7[7] & 255L;
                                          int var74 = -1;

                                          while (true) {
                                             long var8 = var68;
                                             byte[] var10 = var0.doFinal(
                                                new byte[]{
                                                   (byte)(var8 >>> 56),
                                                   (byte)(var8 >>> 48),
                                                   (byte)(var8 >>> 40),
                                                   (byte)(var8 >>> 32),
                                                   (byte)(var8 >>> 24),
                                                   (byte)(var8 >>> 16),
                                                   (byte)(var8 >>> 8),
                                                   (byte)var8
                                                }
                                             );
                                             var76 = (var10[0] & 255L) << 56
                                                | (var10[1] & 255L) << 48
                                                | (var10[2] & 255L) << 40
                                                | (var10[3] & 255L) << 32
                                                | (var10[4] & 255L) << 24
                                                | (var10[5] & 255L) << 16
                                                | (var10[6] & 255L) << 8
                                                | var10[7] & 255L;
                                             switch (var74) {
                                                case 0:
                                                   var49[var58] = var76;
                                                   if (var2 >= var5) {
                                                      hb = var6;
                                                      ib = new Long[18];
                                                      i = 10L;
                                                      Y = 3000L;
                                                      g = 1000L;
                                                      o = 1200L;
                                                      t = 10000L;
                                                      a = 2000L;
                                                      T = 300L;
                                                      uq = new MiningEngine(var33, var34, var35);
                                                      return;
                                                   }
                                                   break;
                                                default:
                                                   var49[var58] = var76;
                                                   if (var2 < var5) {
                                                      continue label51;
                                                   }

                                                   var4 = "$\u0083:\u008c\u0093HEÿh t\u0019ÏK\u0004ì";
                                                   var5 = "$\u0083:\u008c\u0093HEÿh t\u0019ÏK\u0004ì".length();
                                                   var2 = 0;
                                             }

                                             int var59 = var2;
                                             var2 += 8;
                                             var7 = var4.substring(var59, var2).getBytes("ISO-8859-1");
                                             var49 = var6;
                                             var58 = var3++;
                                             var68 = (var7[0] & 255L) << 56
                                                | (var7[1] & 255L) << 48
                                                | (var7[2] & 255L) << 40
                                                | (var7[3] & 255L) << 32
                                                | (var7[4] & 255L) << 24
                                                | (var7[5] & 255L) << 16
                                                | (var7[6] & 255L) << 8
                                                | var7[7] & 255L;
                                             var74 = 0;
                                          }
                                       }
                                    }
                                    break;
                                 default:
                                    var47[var55] = var76;
                                    if (var13 < var16) {
                                       continue label67;
                                    }

                                    var15 = "v\u000fj\u0013'ÒåàÎsÿc(³\u0084\u0013";
                                    var16 = "v\u000fj\u0013'ÒåàÎsÿc(³\u0084\u0013".length();
                                    var13 = 0;
                              }

                              int var56 = var13;
                              var13 += 8;
                              var18 = var15.substring(var56, var13).getBytes("ISO-8859-1");
                              var47 = var17;
                              var55 = var14++;
                              var65 = (var18[0] & 255L) << 56
                                 | (var18[1] & 255L) << 48
                                 | (var18[2] & 255L) << 40
                                 | (var18[3] & 255L) << 32
                                 | (var18[4] & 255L) << 24
                                 | (var18[5] & 255L) << 16
                                 | (var18[6] & 255L) << 8
                                 | var18[7] & 255L;
                              var71 = 0;
                           }
                        }
                     }

                     var25 = var26.charAt(var43);
                     break;
                  default:
                     var29[var27++] = var61;
                     if ((var43 += var25) < var28) {
                        var25 = var26.charAt(var43);
                        continue label85;
                     }

                     var26 = "ó7örb%\u008d·äÖiA'Ù6+,þ^\u0001\u0013\u0084J@Ë\\\u008c\u0087ÙAÕÔd&8\u0091\u0018ËS\u001a\u0011\u009fj~D\u0089¨A\u0000T\u009fÐN:6ÔÃ«\u0017\u0084\fè\\AÀ\tÃ>×ýÿb\u0090ÿ\u0017\u001bè/1°ü«c?\n:\u0006ÕN\u0085@ÌÀ«\u0095Ø3<\u001a¢\u0007q2\u0099tMÊ\u0011·lóG$Ö\u0094\u009b\u0016\"\u0011þ\u0006ÖÃI\u0000½\u0015U|çU`Ò3 \bÐt-\u0090üÌì\u0005(®)\u0002ÂS\u008e\u0013\u0012IVEPLH\u0087-\u000btÑÜ\u0083\u008a\u0002ú¸\u008e\u0010Ð·hÖ;\u0080\u008cð{\u008bÐ¾ø";
                     var28 = "ó7örb%\u008d·äÖiA'Ù6+,þ^\u0001\u0013\u0084J@Ë\\\u008c\u0087ÙAÕÔd&8\u0091\u0018ËS\u001a\u0011\u009fj~D\u0089¨A\u0000T\u009fÐN:6ÔÃ«\u0017\u0084\fè\\AÀ\tÃ>×ýÿb\u0090ÿ\u0017\u001bè/1°ü«c?\n:\u0006ÕN\u0085@ÌÀ«\u0095Ø3<\u001a¢\u0007q2\u0099tMÊ\u0011·lóG$Ö\u0094\u009b\u0016\"\u0011þ\u0006ÖÃI\u0000½\u0015U|çU`Ò3 \bÐt-\u0090üÌì\u0005(®)\u0002ÂS\u008e\u0013\u0012IVEPLH\u0087-\u000btÑÜ\u0083\u008a\u0002ú¸\u008e\u0010Ð·hÖ;\u0080\u008cð{\u008bÐ¾ø"
                        .length();
                     var25 = 144;
                     var43 = -1;
               }

               var44 = var26.substring(++var43, var43 + var25);
               var50 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var39) {
         throw new RuntimeException(var39);
      }
   }
}
