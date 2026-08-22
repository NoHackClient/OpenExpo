package Expo.internal;

import Expo.enums.DetectedAction;
import Expo.enums.DetectedCheat;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.CheaterDetectorBinder;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.GetDisplayNameEvent;
import Expo.event.events.PlayerGetNameEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.module.Module;
import Expo.util.CheaterRegistry;
import Expo.util.ClientUtil;
import Expo.util.MathUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S22PacketMultiBlockChange.BlockUpdateData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;


public class CheaterDetector extends Module implements EventSubscriber {
   private static long a;
   private static Map o;
   private static Map e;
   private long J;
   private static Integer[] h;
   public static Map<UUID, CheaterRegistry> R;
   public static Map<UUID, EntityPlayer> c;
   private static String[] d;
   private static long[] g;
   private static long[] m;
   private static String[] b;
   private static Object[] p;
   private static String[] r;
   private static Map k;

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {





      int var12 = 0;

      for (int var13 = f.theWorld.playerEntities.size(); var12 < var13; var12++) {
         EntityPlayer var14 = (EntityPlayer)f.theWorld.playerEntities.get(var12);
         UUID var15 = var14.getGameProfile().getId();
         c.putIfAbsent(var15, var14);
         if (!(var14 instanceof EntityPlayerSP)) {
            CheaterRegistry var16 = R.computeIfAbsent(var15, var0 -> {
               long var3x = 139461843194438L;
               return new CheaterRegistry(var3x);
            });
            this.q(var14, var16, 130408358797926L);
            this.V(var14, 91960239010806L, var16);
            this.c(var14, var16);
         }
      }

      for (Entry var21 : R.entrySet()) {
         EntityPlayer var22 = c.get(var21.getKey());
         if (var22 != null) {
            for (DetectedCheat var18 : DetectedCheat.values()) {
               if (((CheaterRegistry)var21.getValue()).g(var18) >= var18.FLAG_VL) {
                  ((CheaterRegistry)var21.getValue()).C(118087748409822L, var18);
                  ClientUtil.t(48081174263320L, var22.getDisplayName().getFormattedText() + "\u00a7r flagged " + var18.colorFormatCode + var18.name());
                  ((CheaterRegistry)var21.getValue()).D(var18, -9999);
               }
            }
         }

         ((CheaterRegistry)var21.getValue()).d.clear();
         ((CheaterRegistry)var21.getValue()).d.putAll(((CheaterRegistry)var21.getValue()).c);
      }
   }

   private boolean K(short var1, int var2, List var3, char var4, long var5, long var7) {
      float[] var10000 = new float[6];
      var10000[0] = 30.0F;
      var10000[1] = 35.0F;
      var10000[2] = 45.0F;
      var10000[3] = 90.0F;
      var10000[4] = 135.0F;
      var10000[5] = 180.0F;
      float[] var11 = var10000;
      int[] var12 = new int[var11.length];
      float[] var13 = new float[var11.length];
      float[] var14 = new float[var11.length];

      for (CheaterDetectionSample var16 : (Iterable<CheaterDetectionSample>)(var3)) {
         if (CheaterDetectionSample.u(var16) >= var5) {
            if (CheaterDetectionSample.u(var16) > var7) {
               break;
            }

            if (CheaterDetectionSample.h(var16) == DetectedAction.ROTATION && !(CheaterDetectionSample.R(var16) < 24.0F)) {
               for (int var17 = 0; var17 < var11.length; var17++) {
                  float var18 = var11[var17] >= 90.0F ? 8.0F : 6.0F;
                  if (this.N(CheaterDetectionSample.R(var16), var11[var17], var18)) {
                     var12[var17]++;
                     var13[var17] += CheaterDetectionSample.R(var16);
                     var14[var17] += CheaterDetectionSample.R(var16) * CheaterDetectionSample.R(var16);
                     break;
                  }
               }
            }
         }
      }

      for (int var19 = 0; var19 < var11.length; var19++) {
         if (var12[var19] >= 2) {
            float var20 = var13[var19] / var12[var19];
            float var21 = var14[var19] / var12[var19] - var20 * var20;
            if (var21 <= 12.0F) {
               return true;
            }
         }
      }

      return false;
   }

   private void E(byte var1, int var2, int var3, S14PacketEntity var4) {
      long var5 = ((long)var1 << 56 | (long)var2 << 32 >>> 8 | (long)var3 << 40 >>> 40) ^ a;
      long var7 = var5 ^ 122990171470973L;
      if (var4.func_149060_h() && f.theWorld != null) {
         Entity var9 = var4.getEntity(f.theWorld);
         if (var9 instanceof EntityPlayer && var9 != f.thePlayer) {
            double var10 = Math.hypot(var4.func_149062_c(), var4.func_149064_e()) / 32.0;
            this.K((EntityPlayer)var9, this.W(var4.func_149066_f()), this.W(var4.func_149063_g()), var4.getOnGround(), var7, var10);
         }
      }
   }

   private boolean B(List var1, long var2, int var4) {
      var2 = a ^ var2;
      int var5 = Math.min(var1.size(), var4 + d(25319, 1248308785650939903L ^ var2));
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;

      for (int var9 = var4; var9 < var5; var9++) {
         float var10 = this.Z(var1, var9);
         if (this.N(var10, 90.0F, 8.0F)) {
            var6++;
         }

         if (var10 >= 28.0F && var10 <= 40.0F) {
            var7++;
         }

         if (this.N(var10, 135.0F, 10.0F) || this.N(var10, 180.0F, 8.0F)) {
            var8++;
         }
      }

      return var6 >= 2 || var6 >= 1 && var7 >= 2 || var8 >= 1;
   }

   private boolean w(long var1, List var3, int var4) {
      var1 = a ^ var1;
      int var5 = Math.min(var3.size(), var4 + d(27240, 5209944961784744300L ^ var1));
      int var6 = 0;

      for (int var7 = var4; var7 < var5; var7++) {
         if (CheaterDetectionSample.N((CheaterDetectionSample)var3.get(var7)) && this.d(var3, var7, 2)) {
            var6++;
         }
      }

      return var6 >= 1;
   }


   public void A(long var1) {
      c.clear();
   }

   private void P(long var1, S18PacketEntityTeleport var3) {


      if (f.theWorld != null) {
         Entity var6 = f.theWorld.getEntityByID(var3.getEntityId());
         if (var6 instanceof EntityPlayer && var6 != f.thePlayer) {
            this.K((EntityPlayer)var6, this.W(var3.getYaw()), this.W(var3.getPitch()), var3.getOnGround(), 126069368889640L, 0.0);
         }
      }
   }

   public CheaterDetector(int var1, char var2, int var3) {
      super(((((((long)((var1)) << 32) | (((long)((var2)) << 48) >>> 32)) | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 32824759941751L));
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ a;
      this.J = 0L;
   }

   private boolean e(List<CheaterDetectionSample> var1, int var2) {
      for (int var3 = var1.size() - 1; var3 >= 0; var3--) {
         CheaterDetectionSample var4 = (CheaterDetectionSample)var1.get(var3);
         long var5 = this.J - CheaterDetectionSample.u(var4);
         if (var5 > var2) {
            break;
         }

         if (CheaterDetectionSample.h(var4) == DetectedAction.SWING) {
            return true;
         }
      }

      return false;
   }

   public void onPlayerGetName(int var1, PlayerGetNameEvent var2, short var3, int var4) {
      CheaterRegistry var7 = R.get(var2.u.getGameProfile().getId());
      if (var7 != null && var7.M()) {
         var2.d("§b§l⚠§r ");
      }
   }

   private void o(short var1, BlockPos var2, int var3, Block var4, short var5) {
      long var6 = ((long)var1 << 48 | (long)var3 << 32 >>> 16 | (long)var5 << 48 >>> 48) ^ a;
      int var8 = (int)((var6 ^ 50880071261965L) >>> 48);
      if (f.theWorld != null && var4 != Blocks.air) {
         Block var11 = f.theWorld.getBlockState(var2).getBlock();
         if (var11 == Blocks.air || var11.getMaterial().isReplaceable()) {
            EntityPlayer var12 = this.Q(var2, var4, 3.35);
            if (var12 != null) {
               CheaterRegistry var13 = R.computeIfAbsent(var12.getGameProfile().getId(), var0 -> {
                  long var3x = 139461843194438L;
                  return new CheaterRegistry(var3x);
               });
               if (this.A(var13.G, 18)) {
                  this.L(
                     (short)var8

,
                     var13,
                     new CheaterDetectionSample(DetectedAction.PLACE, this.J, 0.0F, 0.0F, Math.abs(var12.rotationPitch), false, var12.onGround, 0.0)
                  );
               }
            }
         }
      }
   }

   private void B(long var1, List var3) {
      long var4 = this.J - 120L;

      while (!var3.isEmpty() && CheaterDetectionSample.u((CheaterDetectionSample)var3.get(0)) < var4) {
         var3.remove(0);
      }

      if (var3.size() > 40) {
         var3.subList(0, var3.size() - 40).clear();
      }
   }


   private boolean Z(long var1, List var3, int var4, DetectedAction var5, int var6) {
      var1 = a ^ var1;
      long var7 = CheaterDetectionSample.u((CheaterDetectionSample)var3.get(var4));

      label45:
      for (int var9 = var4; var9 < var3.size(); var9++) {
         CheaterDetectionSample var10 = (CheaterDetectionSample)var3.get(var9);
         if (CheaterDetectionSample.u(var10) - var7 <= 24) {
            if (CheaterDetectionSample.h(var10) == var5) {
               for (int var11 = var4; var11 < var3.size(); var11++) {
                  CheaterDetectionSample var12 = (CheaterDetectionSample)var3.get(var11);
                  if (CheaterDetectionSample.u(var12) - var7 > 24) {
                     continue label45;
                  }

                  if (CheaterDetectionSample.h(var12) == DetectedAction.ROTATION && Math.abs(CheaterDetectionSample.u(var12) - CheaterDetectionSample.u(var10)) <= var6 && (CheaterDetectionSample.R(var12) >= 28.0F || CheaterDetectionSample.v(var12) >= 55.0F)) {
                     return true;
                  }
               }
            }
            continue;
         }
         break;
      }

      return false;
   }

   private boolean T(List var1, long var2, int var4) {
      var2 = a ^ var2;
      int var5 = Math.min(var1.size(), var4 + d(27590, 1223529746239833037L ^ var2));
      boolean var6 = false;
      boolean var7 = false;
      boolean var8 = false;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      int var12 = 0;
      int var13 = 0;

      for (int var14 = var4; var14 < var5; var14++) {
         float var15 = this.Z(var1, var14);
         CheaterDetectionSample var16 = (CheaterDetectionSample)var1.get(var14);
         float var17 = CheaterDetectionSample.g(var16);
         boolean var18 = CheaterDetectionSample.N(var16);
         if (!CheaterDetectionSample.z(var16)) {
            var12++;
         }

         if (CheaterDetectionSample.E(var16) > 0.05) {
            var13++;
         }

         if (CheaterDetectionSample.v(var16) >= 55.0F) {
            var11++;
         }

         if (var15 <= 10.0F) {
            if (var18 && this.d(var1, var14, 3)) {
               var8 = true;
            }
         } else if (!var6) {
            if (var15 >= 75.0F || this.N(var15, 90.0F, 8.0F)) {
               var6 = true;
               if (var15 >= 110.0F || this.N(var15, 135.0F, 10.0F) || this.N(var15, 180.0F, 8.0F)) {
                  var7 = true;
               }
            }
         } else {
            if (var15 >= 70.0F && var15 <= 135.0F || this.N(var15, 180.0F, 8.0F)) {
               var7 = true;
            }

            if (var15 >= 24.0F && var15 <= 42.0F) {
               var9++;
            }

            if (var15 > 10.0F && var15 < 24.0F) {
               var9++;
            }

            if (var18) {
               var10++;
               if (this.d(var1, var14, 2)) {
                  var8 = true;
               }
            }

            if (var17 >= 20.0F) {
               var11++;
            }
         }
      }

      return var6 && var13 >= 4 && (var7 || var9 >= 2 || var12 >= 2) && (var8 || var10 >= 2) && var11 >= 2;
   }

   public void onReceivePacket(ReceivePacketEvent var1, long var2) {

      int var6 = 6780733;

      int var11 = 53536;

      this.J++;
      if (var1.d instanceof S14PacketEntity) {
         this.E((byte)0, 555061, var6, (S14PacketEntity)var1.d);
      } else if (var1.d instanceof S18PacketEntityTeleport) {
         this.P(94234514260642L, (S18PacketEntityTeleport)var1.d);
      } else if (var1.d instanceof S0BPacketAnimation) {
         this.V((S0BPacketAnimation)var1.d, 112917762571575L);
      } else if (var1.d instanceof S23PacketBlockChange) {
         S23PacketBlockChange var14 = (S23PacketBlockChange)var1.d;
         this.o((short)0, var14.getBlockPosition(), 732582211, var14.getBlockState().getBlock(), (short)var11);
      } else if (var1.d instanceof S22PacketMultiBlockChange) {
         for (BlockUpdateData var17 : ((S22PacketMultiBlockChange)var1.d).getChangedBlocks()) {
            this.o((short)0, var17.getPos(), 732582211, var17.getBlockState().getBlock(), (short)var11);
         }
      }
   }

   private boolean S(List var1, long var2, long var4, long var6) {

      int var8 = 0;

      for (CheaterDetectionSample var10 : (Iterable<CheaterDetectionSample>)(var1)) {
         if (CheaterDetectionSample.u(var10) >= var2) {
            if (CheaterDetectionSample.u(var10) > var4) {
               break;
            }

            if (CheaterDetectionSample.h(var10) == DetectedAction.PLACE) {
               boolean var11 = false;
               boolean var12 = false;
               Iterator var13 = var1.iterator();

               while (true) {
                  if (var13.hasNext()) {
                     CheaterDetectionSample var14 = (CheaterDetectionSample)var13.next();
                     if (CheaterDetectionSample.u(var14) < CheaterDetectionSample.u(var10) - 8L) {
                        continue;
                     }

                     if (CheaterDetectionSample.u(var14) <= CheaterDetectionSample.u(var10) + 8L) {
                        if (CheaterDetectionSample.h(var14) == DetectedAction.SWING) {
                           var11 = true;
                           continue;
                        }

                        if (CheaterDetectionSample.h(var14) == DetectedAction.ROTATION && CheaterDetectionSample.E(var14) > 0.015 && (CheaterDetectionSample.R(var14) >= 24.0F || CheaterDetectionSample.v(var14) >= 70.0F || CheaterDetectionSample.g(var14) >= 18.0F)) {
                           var12 = true;
                        }
                        continue;
                     }
                  }

                  if (var11 && var12) {
                     var8++;
                  }
                  break;
               }
            }
         }
      }

      return var8 >= 2;
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

   private void L(short var1, CheaterRegistry var4, CheaterDetectionSample var5) {
      var4.G.add(var5);
      if (var4.G.size() > 100) {
         var4.G.remove(0);
      }
   }

   private static int d(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 12018;
      if (h[var3] == null) {
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
         long var5 = g[var3];
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
         Object[] var9 = (Object[])k.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               k.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/internal/CheaterDetector", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         h[var3] = var15;
      }

      return h[var3];
   }

   private void K(EntityPlayer var1, float var2, float var3, boolean var4, long var5, double var7) {
      var5 = a ^ var5;
      int var9 = (int)((var5 ^ 131137936699141L) >>> 48);
      CheaterRegistry var12 = R.computeIfAbsent(var1.getGameProfile().getId(), var0 -> {
         long var3x = 139461843194438L;
         return new CheaterRegistry(var3x);
      });
      float var13 = var12.j ? Math.abs(MathUtil.M(var12.p, var2)) : 0.0F;
      float var14 = var12.j ? Math.abs(MathUtil.M(var12.J, var3)) : 0.0F;
      var12.j = ((1 & 1) != 0);
      var12.p = var2;
      var12.J = var3;
      this.L((short)var9, var12, new CheaterDetectionSample(DetectedAction.ROTATION, this.J, var13, var14, Math.abs(var3), false, var4, var7));
   }

   private boolean v(long var1, List var3, int var4) {
      var1 = a ^ var1;
      long var5 = CheaterDetectionSample.u((CheaterDetectionSample)var3.get(var4));
      int var7 = 0;
      int var8 = 0;
      int var9 = 0;
      int var10 = 0;
      int var11 = 0;
      int var12 = 0;
      int var13 = 0;
      int var14 = 0;

      for (int var15 = var4; var15 < var3.size(); var15++) {
         CheaterDetectionSample var16 = (CheaterDetectionSample)var3.get(var15);
         if (CheaterDetectionSample.u(var16) - var5 > 24) {
            break;
         }

         if (CheaterDetectionSample.h(var16) == DetectedAction.PLACE) {
            var10++;
         } else if (CheaterDetectionSample.h(var16) == DetectedAction.SWING) {
            var11++;
         } else if (CheaterDetectionSample.h(var16) == DetectedAction.ROTATION) {
            var7++;
            if (!CheaterDetectionSample.z(var16)) {
               var12++;
            }

            if (CheaterDetectionSample.E(var16) > 0.015) {
               var14++;
            }

            if (CheaterDetectionSample.v(var16) >= 55.0F || CheaterDetectionSample.g(var16) >= 18.0F) {
               var13++;
            }

            if (CheaterDetectionSample.R(var16) >= 75.0F || this.N(CheaterDetectionSample.R(var16), 90.0F, 8.0F) || this.N(CheaterDetectionSample.R(var16), 135.0F, 10.0F) || this.N(CheaterDetectionSample.R(var16), 180.0F, 8.0F)) {
               var8++;
            }

            if (CheaterDetectionSample.R(var16) >= 24.0F && CheaterDetectionSample.R(var16) <= 42.0F) {
               var9++;
            }
         }
      }

      return var7 >= 2 && var10 >= 1 && var11 >= 1 && var13 >= 1 && var14 >= 1 && (var8 >= 1 || var9 >= 2) && (var12 >= 1 || var9 >= 2);
   }

   public void onGetDisplayName(GetDisplayNameEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      CheaterRegistry var4 = R.get(var1.u.getGameProfile().getId());
      if (var4 != null && var4.M()) {
         var1.Q("\u00a7b\u00a7l\u26a0\u00a7r ");
      }
   }

   public void onWorldLoad(WorldLoadEvent var1) {
      c.clear();
      this.J = 0L;
   }

   private boolean A(List<CheaterDetectionSample> var1, int var2) {
      int var3 = var1.size() - 1;

      while (var3 >= 0) {
         CheaterDetectionSample var4 = (CheaterDetectionSample)var1.get(var3);
         long var5 = this.J - CheaterDetectionSample.u(var4);
         if (var5 <= var2) {
            if (CheaterDetectionSample.h(var4) != DetectedAction.ROTATION
               || !(CheaterDetectionSample.E(var4) > 0.015)
               || !(CheaterDetectionSample.v(var4) >= 55.0F) && !(CheaterDetectionSample.g(var4) >= 18.0F)
               || !(CheaterDetectionSample.R(var4) >= 24.0F) && !(CheaterDetectionSample.v(var4) >= 70.0F)) {
               var3--;
               continue;
            }

            return true;
         }
         break;
      }

      return false;
   }

   private int t(List<CheaterDetectionSample> var1, int var2, long var3) {
      while (var2 + 1 < var1.size() && CheaterDetectionSample.u((CheaterDetectionSample)var1.get(var2 + 1)) <= var3) {
         var2++;
      }

      return var2;
   }

   private float Z(List<CheaterDetectionSample> var1, int var2) {
      return CheaterDetectionSample.R((CheaterDetectionSample)var1.get(var2));
   }

   private boolean d(List<CheaterDetectionSample> var1, int var2, int var3) {
      int var4 = Math.max(0, var2 - var3);
      int var5 = Math.min(var1.size() - 1, var2 + var3);

      for (int var6 = var4; var6 <= var5; var6++) {
         float var7 = this.Z(var1, var6);
         CheaterDetectionSample var8 = (CheaterDetectionSample)var1.get(var6);
         if (var7 >= 28.0F && (CheaterDetectionSample.g(var8) >= 8.0F || CheaterDetectionSample.v(var8) >= 55.0F)) {
            return true;
         }
      }

      return false;
   }

   private void V(S0BPacketAnimation var1, long var2) {


      if (f.theWorld != null && var1.getAnimationType() == 0) {
         Entity var7 = f.theWorld.getEntityByID(var1.getEntityID());
         if (var7 instanceof EntityPlayer && var7 != f.thePlayer) {
            CheaterRegistry var8 = R.computeIfAbsent(((EntityPlayer)var7).getGameProfile().getId(), var0 -> {

               return new CheaterRegistry(139461843194438L);
            });
            this.L((short)0, var8, new CheaterDetectionSample(DetectedAction.SWING, this.J, 0.0F, 0.0F, Math.abs(var7.rotationPitch), true, var7.onGround, 0.0));
         }
      }
   }

   private float W(byte var1) {
      return var1 * 360.0F / 256.0F;
   }

   private static void a() {
      p[0] = "_YuB Ms";
      p[1] = "#DQ3\u0003\u001a\u0014SU9N>\u0003X\u000f%";
      p[2] = "\u001f>\u0002ORg\u001d";
      p[3] = long.class;
      r[3] = "java/lang/Long";
      p[4] = void.class;
      r[4] = "java/lang/Void";
      p[5] = "<0\b\u0003eY7?\u0019L\u0004W<4\u001d\u0016";
      p[6] = "#PRc\u0000Z1JF\t!4x\fW3\u001aU+ZGdd\r'\nW9X_q^^\t^U-\fYoUQ,[92_J!\fI7ZU!0";
   }

   public void V(EntityPlayer var1, long var2, CheaterRegistry var4) {







      if (var4.Y > 0L) {
         var4.Y--;
      } else {
         int var12 = var4.g(DetectedCheat.SCAFFOLD);
         List var13 = var4.G;
         long var14 = this.J - 12L;
         if (var14 <= var4.R) {
            var4.D(DetectedCheat.SCAFFOLD, -5);
            this.B(99456092162304L, var13);
            var4.Y = 6L;
         } else {
            for (int var16 = 0; var16 < var13.size(); var16++) {
               CheaterDetectionSample var17 = (CheaterDetectionSample)var13.get(var16);
               if (CheaterDetectionSample.u(var17) > var4.R) {
                  if (CheaterDetectionSample.u(var17) > var14) {
                     break;
                  }

                  if (CheaterDetectionSample.h(var17) == DetectedAction.PLACE) {
                     int var18 = this.Q(var13, var16, 30);
                     if (this.S(var13, var18, CheaterDetectionSample.u(var17), 62175884037630L)) {
                        var4.D(DetectedCheat.SCAFFOLD, 2);
                        if (this.K(
                           (short)0,
                           1948585579,
                           var13,
                           (char)8825,
                           CheaterDetectionSample.u(var17) - 30L,
                           CheaterDetectionSample.u(var17) + 12L
                        )) {
                           var4.D(DetectedCheat.SCAFFOLD, 2);
                        }

                        var16 = this.t(var13, var16, CheaterDetectionSample.u(var17) + 12L);
                     }
                  }
               }
            }

            var4.R = var14;
            if (var12 == var4.g(DetectedCheat.SCAFFOLD)) {
               var4.D(DetectedCheat.SCAFFOLD, -5);
            }

            this.B(99456092162304L, var13);
            var4.Y = 6L;
         }
      }
   }

   public void onEntityJoinWorld(EntityJoinWorldEvent var1) {
      if (var1.H instanceof EntityPlayer) {
         EntityPlayer var2 = (EntityPlayer)var1.H;
         UUID var3 = var2.getGameProfile().getId();
         c.put(var3, var2);
      }
   }

   private int Q(List<CheaterDetectionSample> var1, int var2, int var3) {
      long var4 = CheaterDetectionSample.u((CheaterDetectionSample)var1.get(var2));
      int var6 = var2;

      while (var6 > 0 && var4 - CheaterDetectionSample.u((CheaterDetectionSample)var1.get(var6 - 1)) <= var3) {
         var6--;
      }

      return var6;
   }

   public void c(EntityPlayer var1, CheaterRegistry var2) {
      if (var1.isUsingItem()) {
         if (var1.isSprinting()) {
            var2.D(DetectedCheat.NOSLOW, 1);
         }
      } else {
         var2.D(DetectedCheat.NOSLOW, -1);
      }
   }

   private boolean N(float var1, float var2, float var3) {
      return Math.abs(var1 - var2) <= var3;
   }

   public final void x(long var1, EventBus var3) {
      CheaterDetectorBinder.M(var3, this);
   }

   private boolean S(List var1, int var2, long var3, long var5) {






      long var12 = var3 - 30L;
      long var14 = var3 + 12L;
      int var16 = 0;
      int var17 = 0;
      int var18 = 0;
      int var19 = 0;
      int var20 = 0;

      for (int var21 = var2; var21 < var1.size(); var21++) {
         CheaterDetectionSample var22 = (CheaterDetectionSample)var1.get(var21);
         if (CheaterDetectionSample.u(var22) >= var12) {
            if (CheaterDetectionSample.u(var22) > var14) {
               break;
            }

            if (CheaterDetectionSample.h(var22) == DetectedAction.PLACE) {
               var16++;
            } else if (CheaterDetectionSample.h(var22) == DetectedAction.SWING) {
               var17++;
            } else if (CheaterDetectionSample.h(var22) == DetectedAction.ROTATION) {
               if (CheaterDetectionSample.E(var22) > 0.015) {
                  var18++;
               }

               if (CheaterDetectionSample.v(var22) >= 60.0F || CheaterDetectionSample.g(var22) >= 18.0F) {
                  var19++;
               }

               if (CheaterDetectionSample.R(var22) >= 24.0F) {
                  var20++;
               }
            }
         }
      }

      return var16 >= 2
         && var17 >= 2
         && var18 >= 2
         && var19 >= 2
         && var20 >= 2
         && this.S(var1, var12, var14, 40076018100272L)
         && this.K((short)0, 1948585579, var1, (char)8825, var12, var14);
   }

   private boolean O(long var1, List var3, char var4, int var5) {
      long var6 = (var1 << 16 | (long)var4 << 48 >>> 48) ^ a;
      int var8 = Math.min(var3.size(), var5 + d(25319, 1248260181282185674L ^ var6));
      float var9 = 0.0F;
      int var10 = 0;

      for (int var11 = var5; var11 < var8; var11++) {
         float var12 = this.Z(var3, var11);
         if (var12 >= 24.0F) {
            var9 += var12;
            var10++;
         }
      }

      if (var10 < 3) {
         return false;
      }

      float var17 = var9 / var10;
      float var18 = 0.0F;
      int var13 = 0;

      for (int var14 = var5; var14 < var8; var14++) {
         float var15 = this.Z(var3, var14);
         if (!(var15 < 24.0F)) {
            float var16 = var15 - var17;
            var18 += var16 * var16;
            if (this.N(var15, 30.0F, 7.0F)
               || this.N(var15, 35.0F, 7.0F)
               || this.N(var15, 45.0F, 7.0F)
               || this.N(var15, 90.0F, 8.0F)
               || this.N(var15, 135.0F, 10.0F)
               || this.N(var15, 180.0F, 8.0F)) {
               var13++;
            }
         }
      }

      var18 /= var10;
      return var13 >= 3 && var18 <= 90.0F;
   }

   private boolean m(int var1, List var2, int var3, long var4) {
      long var6 = ((long)var1 << 32 | var4 << 32 >>> 32) ^ a;
      long var8 = var6 ^ 95192963962318L;
      return this.Z(var8, var2, var3, DetectedAction.SWING, 6)
         && this.Z(var8, var2, var3, DetectedAction.PLACE, 10);
   }

   public EntityPlayer Q(BlockPos var1, Block var2, double var3) {
      AxisAlignedBB var5 = new AxisAlignedBB(
         var1.getX() + 0.5 - var3,
         var1.getY() + 0.5 - 3.0,
         var1.getZ() + 0.5 - var3,
         var1.getX() + 0.5 + var3,
         var1.getY() + 0.5 + 3.0,
         var1.getZ() + 0.5 + var3
      );
      List var6 = f.theWorld.getEntitiesWithinAABB(EntityPlayer.class, var5);
      EntityPlayer var7 = null;
      double var8 = Double.MAX_VALUE;

      for (EntityPlayer var11 : (Iterable<EntityPlayer>)(var6)) {
         if (var11 != f.thePlayer) {
            ItemStack var12 = var11.getHeldItem();
            if (var12 != null && var12.getItem() instanceof ItemBlock && ((ItemBlock)var12.getItem()).getBlock() == var2) {
               int var13 = (int)Math.floor(var11.posY);
               if (var1.getY() <= var13 && var1.getY() >= var13 - 2) {
                  double var14 = var11.posX - (var1.getX() + 0.5);
                  double var16 = var11.posZ - (var1.getZ() + 0.5);
                  double var18 = Math.abs(var11.posY - var1.getY());
                  double var20 = var14 * var14 + var16 * var16;
                  if (!(var20 > var3 * var3)) {
                     double var22 = var14 * var14 + var16 * var16 + var18 * 0.35;
                     if (var22 < var8) {
                        var8 = var22;
                        var7 = var11;
                     }
                  }
               }
            }
         }
      }

      return var7;
   }

   public void q(EntityPlayer var1, CheaterRegistry var2, long var3) {


      if (var1.isUsingItem()) {
         var2.f++;
         if (var2.f >= 3 && var1.isSwingInProgress && var1.swingProgressInt == 1) {
            var2.D(DetectedCheat.AUTOBLOCK, 5);
         }
      } else {
         var2.I = ((1 & 1) != 0);
         var2.f = 0;
         var2.D(DetectedCheat.AUTOBLOCK, -1);
      }

      if (var2.g(DetectedCheat.AUTOBLOCK) >= DetectedCheat.AUTOBLOCK.FLAG_VL) {
         var2.C(118087748409822L, DetectedCheat.AUTOBLOCK);
      }
   }

   static {
      a = 23137457498728L;
      zkm$clinit();
      R = new LinkedHashMap<>();
      c = new LinkedHashMap<>();
   }
   private static void zkm$clinit() {
      try {
         p = new Object[7];
         r = new String[7];
         a();
         e = new HashMap(13);
         long var22 = a ^ 78116914972866L;
         Cipher var24;
         byte[] var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var25 = 1; var25 < 8; var25++) {
            var10003[var25] = (byte)(var22 << var25 * 8 >>> 56);
         }

         (var24 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var31 = new String[3];
         int var29 = 0;
         String var28 = "\u0013Jú*R\u009cÊ×; ÐÐ\u0096y\u0018JKï\u0088\u000f:Ô¨Ö #«¨,ë\"\r·6o¥s7¯ez±m\nó>+\u0093\u008fuGw\u0016¦:g)\u0018\u0015_m9\u0017ùÇ¢\u0093kk1+õÄóz\u0086\u001c\u008ei\u008aD=";
         int var30 = "\u0013Jú*R\u009cÊ×; ÐÐ\u0096y\u0018JKï\u0088\u000f:Ô¨Ö #«¨,ë\"\r·6o¥s7¯ez±m\nó>+\u0093\u008fuGw\u0016¦:g)\u0018\u0015_m9\u0017ùÇ¢\u0093kk1+õÄóz\u0086\u001c\u008ei\u008aD="
            .length();
         char var27 = 24;
         int var26 = -1;

         while (true) {
            byte[] var32 = var24.doFinal(var28.substring(++var26, var26 + var27).getBytes("ISO-8859-1"));
            String var48 = b(var32).intern();
            int var10001 = -1;
            var31[var29++] = var48;
            if ((var26 += var27) >= var30) {
               b = var31;
               d = new String[3];
               k = new HashMap(13);
               Cipher var11;
               var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

               for (int var12 = 1; var12 < 8; var12++) {
                  var10003[var12] = (byte)(var22 << var12 * 8 >>> 56);
               }

               (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
               long[] var17 = new long[17];
               int var14 = 0;
               String var15 = "JG+Ö·\u0097qS\u0018-\u0097aû\fa\u0011ÕõG[ËÎöîëk\u0010\u009cêÁE|\u0010g[Óð.Rþ\"éÖñG\b}\u0006®\u001bF\"Ö\u001d¥¥7i\u0084zW\u0093g^H\u009cYHÕ¥NÅIöY?Ökf§xNö\u0011\u0099\u001fÿ\u001f×ßxB\b£ð¼«÷-ì\u0007(ür\u001cL\n\u0006¨U¾¾¸\u0085{xÐ\u001cMª";
               int var16 = "JG+Ö·\u0097qS\u0018-\u0097aû\fa\u0011ÕõG[ËÎöîëk\u0010\u009cêÁE|\u0010g[Óð.Rþ\"éÖñG\b}\u0006®\u001bF\"Ö\u001d¥¥7i\u0084zW\u0093g^H\u009cYHÕ¥NÅIöY?Ökf§xNö\u0011\u0099\u001fÿ\u001f×ßxB\b£ð¼«÷-ì\u0007(ür\u001cL\n\u0006¨U¾¾¸\u0085{xÐ\u001cMª"
                  .length();
               int var13 = 0;

               label63:
               while (true) {
                  var10001 = var13;
                  var13 += 8;
                  byte[] var18 = var15.substring(var10001, var13).getBytes("ISO-8859-1");
                  long[] var38 = var17;
                  var10001 = var14++;
                  long var51 = (var18[0] & 255L) << 56
                     | (var18[1] & 255L) << 48
                     | (var18[2] & 255L) << 40
                     | (var18[3] & 255L) << 32
                     | (var18[4] & 255L) << 24
                     | (var18[5] & 255L) << 16
                     | (var18[6] & 255L) << 8
                     | var18[7] & 255L;
                  int var57 = -1;

                  while (true) {
                     long var19 = var51;
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
                     long var62 = (var21[0] & 255L) << 56
                        | (var21[1] & 255L) << 48
                        | (var21[2] & 255L) << 40
                        | (var21[3] & 255L) << 32
                        | (var21[4] & 255L) << 24
                        | (var21[5] & 255L) << 16
                        | (var21[6] & 255L) << 8
                        | var21[7] & 255L;
                     switch (var57) {
                        case 0:
                           var38[var10001] = var62;
                           if (var13 >= var16) {
                              g = var17;
                              h = new Integer[17];
                              o = new HashMap(13);
                              Cipher var0;
                              var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                              for (int var1 = 1; var1 < 8; var1++) {
                                 var10003[var1] = (byte)(var22 << var1 * 8 >>> 56);
                              }

                              (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                              long[] var6 = new long[11];
                              int var3 = 0;
                              String var4 = "H\u000b¥\b\u0081«ñ´\u009fã\u0081£ò·\u0083¹Ee½%µKëä±êÀ\u001eL|9í¶l³\u009aâ\u0011Tã\u0000\u008b\r8÷\u0099ôê3t\u0013\u0004\u0088¢\u0098ò\u0095á¬Ãí¶ 6ÂÇ\u008c\"g\u00adr\u001b";
                              int var5 = "H\u000b¥\b\u0081«ñ´\u009fã\u0081£ò·\u0083¹Ee½%µKëä±êÀ\u001eL|9í¶l³\u009aâ\u0011Tã\u0000\u008b\r8÷\u0099ôê3t\u0013\u0004\u0088¢\u0098ò\u0095á¬Ãí¶ 6ÂÇ\u008c\"g\u00adr\u001b"
                                 .length();
                              int var2 = 0;

                              label47:
                              while (true) {
                                 int var45 = var2;
                                 var2 += 8;
                                 byte[] var7 = var4.substring(var45, var2).getBytes("ISO-8859-1");
                                 long[] var40 = var6;
                                 var45 = var3++;
                                 long var54 = (var7[0] & 255L) << 56
                                    | (var7[1] & 255L) << 48
                                    | (var7[2] & 255L) << 40
                                    | (var7[3] & 255L) << 32
                                    | (var7[4] & 255L) << 24
                                    | (var7[5] & 255L) << 16
                                    | (var7[6] & 255L) << 8
                                    | var7[7] & 255L;
                                 int var60 = -1;

                                 while (true) {
                                    long var8 = var54;
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
                                    var62 = (var10[0] & 255L) << 56
                                       | (var10[1] & 255L) << 48
                                       | (var10[2] & 255L) << 40
                                       | (var10[3] & 255L) << 32
                                       | (var10[4] & 255L) << 24
                                       | (var10[5] & 255L) << 16
                                       | (var10[6] & 255L) << 8
                                       | var10[7] & 255L;
                                    switch (var60) {
                                       case 0:
                                          var40[var45] = var62;
                                          if (var2 >= var5) {
                                             m = var6;
                                             return;
                                          }
                                          break;
                                       default:
                                          var40[var45] = var62;
                                          if (var2 < var5) {
                                             continue label47;
                                          }

                                          var4 = "\f[°~\u00995Ûn\n\u0082¥+x\u0007\u007f½";
                                          var5 = "\f[°~\u00995Ûn\n\u0082¥+x\u0007\u007f½".length();
                                          var2 = 0;
                                    }

                                    int var47 = var2;
                                    var2 += 8;
                                    var7 = var4.substring(var47, var2).getBytes("ISO-8859-1");
                                    var40 = var6;
                                    var45 = var3++;
                                    var54 = (var7[0] & 255L) << 56
                                       | (var7[1] & 255L) << 48
                                       | (var7[2] & 255L) << 40
                                       | (var7[3] & 255L) << 32
                                       | (var7[4] & 255L) << 24
                                       | (var7[5] & 255L) << 16
                                       | (var7[6] & 255L) << 8
                                       | var7[7] & 255L;
                                    var60 = 0;
                                 }
                              }
                           }
                           break;
                        default:
                           var38[var10001] = var62;
                           if (var13 < var16) {
                              continue label63;
                           }

                           var15 = "Ù<l\u0015@\u008bQyuA«\u0017k't§";
                           var16 = "Ù<l\u0015@\u008bQyuA«\u0017k't§".length();
                           var13 = 0;
                     }

                     int var44 = var13;
                     var13 += 8;
                     var18 = var15.substring(var44, var13).getBytes("ISO-8859-1");
                     var38 = var17;
                     var10001 = var14++;
                     var51 = (var18[0] & 255L) << 56
                        | (var18[1] & 255L) << 48
                        | (var18[2] & 255L) << 40
                        | (var18[3] & 255L) << 32
                        | (var18[4] & 255L) << 24
                        | (var18[5] & 255L) << 16
                        | (var18[6] & 255L) << 8
                        | var18[7] & 255L;
                     var57 = 0;
                  }
               }
            }

            var27 = var28.charAt(var26);
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var33) {
         throw new RuntimeException(var33);
      }
   }
}
