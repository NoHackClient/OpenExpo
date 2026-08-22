package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AutoBlockBinder;
import Expo.event.events.ModuleTagEvent;
import Expo.event.events.PostUpdateEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.RedirectIsUsingItemEvent;
import Expo.event.events.TickEvent;
import Expo.event.events.UpdateCameraAndRenderEvent;
import Expo.module.Module;
import Expo.module.Modules;
import Expo.module.impl.movement.NoSlow;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.CombatUtil;
import Expo.util.EntityUtil;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.Pair;
import Expo.util.RaytraceUtil;
import Expo.util.ScoreboardReader;
import Expo.util.Sneaky;
import Expo.util.packet.OutgoingPacketState;
import Expo.util.packet.PacketManager;
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
import java.util.Comparator;
import java.util.HashMap;
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
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;











public class AutoBlock extends Module implements EventSubscriber {
   public static BooleanSetting mobs;
   private static boolean m;
   public static NumberSetting targetRange;
   private boolean M;
   public static BooleanSetting smartUnblock;
   public static BooleanSetting golems;
   public static BooleanSetting players;
   private static boolean D;
   private static long mb;
   private EntityLivingBase F;
   public static ModeSetting apsMode;
   private static String[] ob;
   public static ModeSetting mode;
   private static String[] S;
   public static BooleanSetting manualLeftClick;
   private long Y;
   private static boolean B;
   private static Map ib;
   private static Integer[] hb;
   public static HeaderSetting targetSettings;
   private static Map bb;
   public static BooleanSetting allowNoSlow;
   public static PercentageSetting smartUnblockChance;
   private static int I;
   public static BooleanSetting friends;
   public static boolean C;
   public static BooleanSetting requireKillAura;
   public static BooleanSetting silverfishes;
   public static NumberSetting smartUnblockTicks;
   private int p;
   private static boolean K;
   private static boolean G;
   public static BooleanSetting bosses;
   private static String[] ab;
   public static BooleanSetting requireRightClick;
   public static BooleanSetting visualBlocking;
   public static BooleanSetting enemies;
   private static long o;
   public static BooleanSetting animals;
   private static long[] gb;
   public static int k;
   public static BooleanSetting teammates;
   public static NumberSetting fov;
   public static BooleanSetting bots;
   private boolean J;
   private static final byte[] KEY_OFFSETS = {
      10, 4, 8, 14, 19, 28, 31, 45, 50, 12, 46, 11, 60, 47, 58, 44,
      53, 5, 7, 23, 55, 9, 52, 16, 24, 2, 1, 35, 17, 6, 51, 3,
      26, 0, 39, 49, 38, 59, 33, 43, 13, 48, 57, 18, 61, 54, 25, 40,
      15, 29, 34, 21, 62, 63, 42, 32, 20, 30, 41, 36, 22, 27, 37, 56
   };
   private static Object[] nb;

   public void onRedirectIsUsingItem(RedirectIsUsingItemEvent var1, long var2) {


      if (m) {
         switch (mode.Y()) {
            case "LAG":
            case "LAG_PRE":
            case "LAG_LEGIT":
            case "LAG_LEGIT_PRE":
            case "LAG_NEW":
            case "LAG_NEW_PRE":
               if (!c()) {
                  var1.I(21307, 3074332907L);
               }
         }
      }
   }

   private void o$r2() {
      PacketManager.j();
      this.Y = System.currentTimeMillis();
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

   private static boolean s(int var0, int var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return allowNoSlow.c() && Modules.J(NoSlow.class).o() && NoSlow.swordMode.R("VANILLA") && NoSlow.slowDown.k() < 100;
   }

   public static boolean f$r2() {
      return D;
   }

   private Pair n(boolean var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (var1) {
         I = 0;
      }

      this.M = I > 0;

      this.F = null;
      m = false;
      G = false;
      return this.B(22228944377438L);
   }

   private void f(long var1) {
      K = false;
      D = false;
   }

   private static boolean T(long var0) {
      switch (mode.Y()) {
         case "LAG":
         case "LAG_PRE":
         case "LAG_LEGIT":
         case "LAG_LEGIT_PRE":
         case "LAG_NEW":
         case "LAG_NEW_PRE":
            return ScoreboardReader.v(0L);
         default:
            return true;
      }
   }

   private Pair B(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      B = false;
      Pair var16 = new Pair<>(false, true);
      Pair var17 = new Pair<>(false, false);
      Pair var18 = new Pair<>(true, true);
      Pair var19 = new Pair<>(true, false);
      k = 0;
      if (this.J) {
         this.o$r2();
         this.n();
         this.J = false;
      }

      if (!ItemUtil.d()) {
         if (OutgoingPacketState.O) {
            C = false;
         }

         this.f(0L);
         return var16;
      } else if (C) {
         this.f(0L);
         if (this.y(0L)) {
            OutgoingPacketState.P = true;
            OutgoingPacketState.J(0L);
            return var18;
         } else {
            return var17;
         }
      } else {
         Pair var20 = var16;
         if (f$r2()
            && c()) {
            if (s(26815, -704018324)) {
               if (!this.K(0L, true)) {
                  return var17;
               }

               this.e(0L, false);
               f.thePlayer.stopUsingItem();
               var20 = var19;
            } else {
               if (!this.K(0L, true)) {
                  return var17;
               }

               f.thePlayer.stopUsingItem();
               var20 = var18;
            }
         }

         this.f(0L);
         return var20;
      }
   }

   private static Field c(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = nb[var4];
      if (!(var5 instanceof String)) {
         return (Field)var5;
      }

      String var6 = ob[var4];
      int var7 = var6.indexOf(8);
      Class var8 = b(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      Class var11 = b(Long.parseLong(var6.substring(++var9), 36), 0L);
      Class var12 = var8;

      while (true) {
         Field var13 = a(var12, var10, var11);
         if (var13 != null) {
            nb[var4] = var13;
            return var13;
         }

         Class[] var14 = var12.getInterfaces();
         if (var14 != null) {
            for (int var15 = 0; var15 < var14.length; var15++) {
               var13 = b(var14[var15], var10, var11);
               if (var13 != null) {
                  nb[var4] = var13;
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
            var12 = b(1523196619010493L, 0L);
         }
      }
   }

   public boolean Q(boolean var1, long var2, boolean var4, EntityLivingBase var5) {
      var2 = o ^ var2;
      if (zkm$unresolved$1$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_iT_c_OR_Expo_yO_Y_y_slots_41_67(var2)) {
         return true;
      }

      ItemStack var6 = f.thePlayer.getHeldItem();
      if (ItemUtil.d() && (!var1 || OutgoingPacketState.Y())) {
         if (var4 && var5 != null) {
            MovingObjectPosition var7 = RaytraceUtil.k(RaytraceUtil.S(var5), 8.0);
            if (var7 != null) {
               PacketManager.b(
                  new C02PacketUseEntity(
                     var5,
                     new Vec3(
                        var7.hitVec.xCoord - var5.posX,
                        var7.hitVec.yCoord - var5.posY,
                        var7.hitVec.zCoord - var5.posZ
                     )
                  )
               );
               PacketManager.b(new C02PacketUseEntity(var5, Action.INTERACT));
            }
         }

         PacketManager.b(new C08PacketPlayerBlockPlacement(var6));
         f.thePlayer.setItemInUse(var6, var6.getMaxItemUseDuration());
         this.k(true);
         return true;
      } else {
         return false;
      }
   }

   public void L(PreMouseInputEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.n(true,0L);
   }

   private static Class b(long var0, long var2) {
      Class var5 = null;
      int var4 = a(var0, var2);
      Object var6 = nb[var4];
      try {
         if (var6 instanceof String) {
            var5 = Class.forName(ob[var4]);
            nb[var4] = var5;
            return var5;
         }
      } catch (Exception var8) {
         throw new RuntimeException(var8.toString());
      }

      return (Class)var6;
   }

   public static boolean c() {
      return f$r2() ? K : f.thePlayer.isUsingItem();
   }

   private static Object a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, Object[] var4) throws Throwable {
      int var5 = var4.length - 2;
      long var6 = (Long)var4[var5];
      long var9 = (Long)var4[++var5];
      MethodHandle var8 = a(var0, var1, var2, var3, var6, var9);
      var1.setTarget(MethodHandles.explicitCastArguments(var8, var3));
      return (Object)var8.asSpreader(Object[].class, var4.length).invoke(var4);
   }

   private boolean M(int var1, char var2, short var3, EntityLivingBase var4, boolean var5) {
      long var6 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ o;
      int var8 = (int)((var6 ^ 20804175781509L) >>> 32);
      long var9 = (var6 ^ 20804175781509L) << 32 >>> 32;
      int var11 = (int)((var6 ^ 78766441816911L) >>> 48);
      long var12 = (var6 ^ 78766441816911L) << 16 >>> 16;
      if (var5
         && !zkm$unresolved$2$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_yO_Y_OR_Expo_yO_f_y_slots_67_99(var6)) {
         return false;
      }

      if (OutgoingPacketState.T) {
         return true;
      }

      this.T((char)var11, var12, false);
      return CombatUtil.I(var4, var8, var9);
   }

   private void k(boolean var1) {
      K = var1;
   }

   public String g(long var1) {
      return mode.Y();
   }

   private static void a() {
      nb[0] = "";
      ob[0] = "Expo.module.impl.combat.AutoBlock";
      nb[1] = boolean.class;
      ob[1] = "java/lang/Boolean";
      nb[2] = "";
      ob[2] = "Expo.util.packet.PacketManager";
      nb[3] = "";
      ob[3] = "net.minecraft.network.Packet";
      nb[4] = void.class;
      ob[4] = "java/lang/Void";
      nb[5] = "";
      ob[5] = "Expo.util.ItemUtil";
      nb[6] = "";
      ob[6] = "Expo.util.EntityUtil";
      nb[7] = double.class;
      ob[7] = "java/lang/Double";
      nb[8] = long.class;
      ob[8] = "java/lang/Long";
      nb[9] = "";
      ob[9] = "java.util.List";
      nb[10] = char.class;
      ob[10] = "java/lang/Character";
      nb[11] = "";
      ob[11] = "Expo.util.CombatUtil";
      nb[12] = "";
      ob[12] = "net.minecraft.entity.Entity";
      nb[13] = int.class;
      ob[13] = "java/lang/Integer";
      nb[14] = "";
      ob[14] = "Expo.util.packet.OutgoingPacketState";
      nb[15] = "";
      ob[15] = "Expo.util.RaytraceUtil";
      nb[16] = "";
      ob[16] = "net.minecraft.util.AxisAlignedBB";
      nb[17] = "";
      ob[17] = "net.minecraft.util.MovingObjectPosition";
      nb[18] = "";
      ob[18] = "net.minecraft.client.entity.EntityPlayerSP";
      nb[19] = "";
      ob[19] = "net.minecraft.item.ItemStack";
      nb[20] = "";
      ob[20] = "java.lang.Boolean";
      nb[21] = "";
      ob[21] = "java.lang.Object";
      nb[22] = "3\u0011id<5-\u0019s+t57\u0013kl}.w m`v):\u0011k`";
      nb[23] = "";
      ob[23] = "Expo.setting.settings.BooleanSetting";
      nb[24] = "\\}`%XnBuzj5t[lw6\u0017oYn";
      nb[25] = "%gK6D\u000e;oQy\f\u000e!eI>\u0005\u0015aRR\u0013\u0005\u000e-jX\u0011\u001f\u0015,rT8\u0004";
      nb[26] = "";
      ob[26] = "Expo.util.Pair";
      nb[27] = "";
      ob[27] = "Expo.setting.settings.ModeSetting";
      nb[28] = "";
      ob[28] = "java.lang.String";
      nb[29] = "";
      ob[29] = "Expo.util.ScoreboardReader";
      nb[30] = short.class;
      ob[30] = "java/lang/Short";
      nb[31] = "*W$+3/!X5d^+!D\u0001/l6%X1/";
      nb[32] = "";
      ob[32] = "Expo.event.binder.AutoBlockBinder";
      nb[33] = "";
      ob[33] = "Expo.event.EventBus";
      nb[34] = byte.class;
      ob[34] = "java/lang/Byte";
      nb[35] = "";
      ob[35] = "Expo.setting.settings.NumberSetting";
      nb[36] = float.class;
      ob[36] = "java/lang/Float";
      nb[37] = "";
      ob[37] = "Expo.event.events.PreMouseInputEvent";
      nb[38] = "";
      ob[38] = "java.lang.System";
      nb[39] = "";
      ob[39] = "net.minecraft.entity.EntityLivingBase";
      nb[40] = "p4^d\u0006++-^g:\u001f\u00175\u001c2\u00065v&\u0018lF[";
      nb[41] = "3>9!%#1?pOpF+&zt|\"en*O";
      nb[42] = "tn~\u001f\u007fWy+qDFUbv\u007fsy\u0003!!(s,^\u001f|uH|Gfzu\u001b!;";
      nb[43] = "\u007f.ZqrV}/\u0013\u001f*3-}Eu6Pd:Q#L";
      nb[44] = "";
      ob[44] = "4coainygpp\u0008x\u00086haxeci0ip\u0008yz23ol72b\u0008yz23ol72b\u0008yz23ol72b\u0008yz23ol72b\u00085jx5xk0c48\u0008yz23ol72b\u0008yz23ol72b\u0008yz23ol72b\u0008yz23ol72b\u0008yz23ol72b\u0008yz23ol72b\u00086haxeci0ip\u0008";
      nb[45] = "R2h\u001d*\u001e_wgF\u0013\u001cD*iq,K\u0001w2qrr\u0002udLi\u0011K2p\u001a\u0013";
      nb[46] = "]#8J!X_\"q$\\=\t+1\u0019o^\f\"}\u0014\u001f\f\u00050;\u0018yR\f#z$nO\u000er&@ \u0007^I0V%\u0006S-~\u001eu=";
      nb[47] = "6\u0019m8F04\u0018$V\u0004Ub\u0011dk\b6g\u0018(fxdn\nnj\u001e:g\u0019/V";
      nb[48] = "DX-uL.K\u0004p,)<^\u001c&(RQJ\u0013?!Wn\u0019\u001bt<) U[t+Mn\u001d\u000bO";
      nb[49] = "jtf@U\u0010>h#\u0007:\u0005obeU}\u0015\u0006wj\u0002\u0001\u001cb9\"R:\u0017tx`WQCh='8";
      nb[50] = "B%O\u001d3I\u00169\nZ\\HI0U\b5Dp>U\u00181\"_%\u000b^;F\u0011m[e";
      nb[51] = "O\u00191@e\u0006M\u0018x.\u0011c\u001b\u00118\u0013+\u0000\u001e\u0018t\u001e[\u0002\u001bHpP2\u0019@\u00190.";
      nb[52] = "7\u0014u<s\b8H(e\u0016\u0017=VwNq\u001b9-&4o\rhKx=|LT";
      nb[53] = "QyKu%]\u001c}TdDum/\u001dc9Z\f*\u001d#:;Pp\\!4XUy\u0010,D\u0006\\mQ}%\u0003\\-R\u001cz[\u0004bAw%\u0003\fj,";
      nb[54] = "V;\u0013mDX[~\u001c6}Z@#\u0012\u0001B\r\u0005~E\u0001\u0017E=~@0\u0017N^7\u0007$A4";
      nb[55] = "i\u007fpgrgo\u007f#:\u000epxxw\n1!4.,\nk\u001e4/e'2xj&vf\u000e";
      nb[56] = "";
      ob[56] = "abqf6mcc8\u0008m\u000899qr4ng0b3\u0008nqwnn214or\u000899qr4ng0b3\u000831nbrkzvz4\u0008";
      nb[57] = "<RdG%?>S-)KZhZm\u0014k9mS!\u0019\u001b9*StWt0j\\c)j(o\u0003zM$`?8";
      nb[58] = "\u0013DT&%\u0003\u000bQGw]\u001bz\u001d\u001b4 \u0018\u001b\u0018\u001bt#yD@C;0\u0012\u001b\u0018K3]";
      nb[59] = "ww(w=tuva\031d=d'Q\":wtg2k}c\"\035";
      nb[60] = "HD%`zh\u0017\u001c-h\u0017y\u001bA+nzB\u0018$\"\u007fufH\u0015.bpfvU>*,d\u0012\u001bvz\u0017";
      nb[61] = "1!t@>g)4g\u0011FFXuxA?$d,1\u001c~\u001d2<i\u0012/#; tJF";
      nb[62] = "}\u0015'\u0007w\u0012\u007f\u0014ni\u0015w)\u001d.T9\u0014,\u0014bYI\u0006fEe\u000e-H.\u0015^";
      nb[63] = "\u001c;&qh\u0013\u001e:o\u001f0v\u0004#e$1\u0012Jk5\u001fk\u0014\u0005l/|n\u001dIa_~kMM/6e0\u001c\rQ";
      nb[64] = "";
      ob[64] = "abqf6mcc8\u0008M\u000899qr4ng0b3\u00087j2mq3aqnq\u0008kwcavgb7lv\u0008rjyxslz8nh\u0008yz23ol72b\u0008yz23ol72b\u0008";
      nb[65] = "\u0018_M!,M\u0000J^pTNq\u0006Q<iG\u0012\u0003Xpd7AI_5m\u000b\u0018\u0000\u0002tT\n@DN-5\u000f@\u0004ML%EK\u0000T(k\r\u001b;";
      nb[66] = "";
      ob[66] = "80n9rw5gu1\u0008I\u00088zdq14a390\u000899qr4ng0b3\u00085jx5xk0c48\u0008yz23ol72b\u0008";
      nb[67] = "AX\rq\u001d\b\u000e^Uq|4pT\u0018:G\u0002\u0014\u001aPj|";
      nb[68] = "V~sV\u0017LT\u007f:8D)\u0002vz\u0005YJ\u0007\u007f6\b)XM.1_M\u0016\u0005~\nI[\u0013\u0004sn\u0007\u0013C?";
      nb[69] = "";
      ob[69] = "alvepryye4\u0008i\u00088zdq14a390\u000859wuii097v\u00085jx5xk0c48\u0008yz23ol72b\u0008yz23ol72b\u0008";
      nb[70] = ">\u0000eD\u0004I<\u0001,*@,j\bl\u0017JOo\u0001 \u001a:\u0017n\fvPY^)\u0018 *";
      nb[71] = "sD1\u0011=`,\u001c9\u0019Pd XXPar7\u0018>\u000ehav$5\u0013 f3\u001bf\u001bk{M";
      nb[72] = ".!\tn\u0011\u000f37Wwr'O=\f~K\u001302P#\u0012v";
      nb[73] = "\u0015\u0011\u0001{1!\u000eJP;O!tAH3\"6K\u0012@x?H";
      nb[74] = "9K\u0012m[9\"\u0010C-%(X\u001b[%H.gHSnUP";
      nb[75] = "\u001b}Q\u000bd\u001e\u0003hBZ\u001c\u0007rsV\u0005#\rLzJ\u0018{dO(P\u001b}\u0005J(\u0010\u0018\u001c\u000fO Q]b\u0002\u0013y^f";
      nb[76] = "{_1~\u0004EcJ\"/|a\u0012\u000b=\u007f\u0005\u0006.Rt\"D?/Y?.\f\\*Ps#|\u0002#D2r\u001d\u0007#\u00041\u0013\rM(\u0000(wC\u0005x;";
      nb[77] = "\u001f\u000be\u0015B\n\u0016\u001dy\u0017|_&L|\u0011\r\u000e@\u0012~\u001d\u00065\u001dO~\u0005\u0006VT\bjS|";
      nb[78] = "\u00148\u000b\u0014j4Tn\u0018\u000e\u0004e(q\u0013^?nL?[\u000e\u0004";
      nb[79] = "7,\t\u001e\u0010G:i\u0006E)E!4\br\u0016\u0012djSr@+03\u000f\u001fUR63\\B)\u001am+\u0019\u0019ODd8X%\u0012\u0012:8\u0019F[U.nc";
      nb[80] = "NFEUg8LG\f;:]V^\u0006\u0000>9\u0018\u0016V;bdAFFX+#U\u0010<";
      nb[81] = "/W#9}\n=\u0011r-\f\u001aW\u001c}%v_1Bt67cf\u001c5&0\u00058\u0015&g\f\\5\u0017)%7\n.K5\\7Z1G6?~\u001d%\u0011L";
      nb[82] = "a@KO6D.F\u0013OWkP\u0000NNjY3\u0005G\u0002g)k\u0004JT-J\"C^\u0002W";
      nb[83] = "#\u0001l\u007fF\n!\u0000%\u00113ow\te,\b\fr\u0000)!xTs\r\u007fk\u001b\u001d4\u0019)\u0011";
      nb[84] = "\u0011%\fPOU\u0013$E>.0E-\u0005\u0003\u0001S@$I\u000eq\u000bA)\u001fD\u0012B\u0006=I>";
      nb[85] = "nh]@S[-wQY=\u000bj\u007f\u001a][\u001cKd\u0005]x\u0001sa\u0001K=]uu]@^X|9P0";
      nb[86] = "}sBl:q\u007fr\u000b\u0002J\u0014){K?tw,r\u00072\u0004/-\u007fQxgfjk\u0007\u0002";
      nb[87] = "x5\u001bO4|`#\u0003FK\u0013\u001dkM\u000e1}{5D\u001dpA{5\u0017\u0013u'}'\u0018\u0012K'd%\u001a\u0011qx!=\u0006w\"+d4B\u0012 *-ZMF2;!<\u0013O!z\u001daE\u0011!;~(\u0002\u0005wA";
      nb[88] = "\u0005xj\u0002 c\u0007y#lt\u0006QpcQneTy/\\\u001e=Uty\u0016}t\u0012`/l";
      nb[89] = "H!4FhkJ }(\u001a\u000e\u001cz2U7o\u0019zrVVhX4+Nl7\u001d,7(g?X1qN96KpMY$4\u001a,)\u0017ld!z|Q,2G$uBm\u000e[)<X-jS{+HV";
      nb[90] = "[Z-!\u0013+Y[dOCN\u000fR$r]-\n[h\u007f-u\u000bV>5N<LBhO";
      nb[91] = "?\n\n1o\u0011=\u000bC_ tk\u0002\u0003b!\u0017n\u000bOoQ\u0005$ZH85Kl\ns";
      nb[92] = "8/*s\u001607sw*s$<p4#\u0012) \u0016,+L=em-'\u001f2[g:pH(?)r s";
      nb[93] = "\"X`;0{ Y)U`21\010\031$|$pU}j4tK";
      nb[94] = "Z?FPW\u0013F|BWj\\9z[\u001eWRZ\u007fRRZ\"H5\u0003U\rF\u0006}Sn";
      nb[95] = "\t`zNy<\u000ba3 \u001eY\u0011x9\u001b =_0i z;\u00107sC\u007f2\\:\u0003Q5c[mg\u001f}3`paQ7\"\u0004x3F'Y\u0011x9\u001b =_0i ";
      nb[96] = "^t\\\\\bk\\u\u00152j\u000e\b|\u001fWO5^gCK63Un\u0018BU6\\\"\u00152G|\r%BV\t4]\u001eT@\f5Pz\u001a\b\\\u000e";
      nb[97] = "$:lQ3K&;%?v.|alE1H\"h\u007f\u0004\r\u001f|)o\u0003kAu:.?|\\wkr[2\u0014'P";
      nb[98] = "xwCmCD,\u007f[a;@\u0016tN>\u0000Lr:\u0006n;";
      nb[99] = "\u0002\u00069@D/M\u0000a@%,3\n,\u000b\u001e%WDd[%";
      nb[100] = "49\u0000Vzzf*@E\u0002|<-\u0002Cxv='6M\u007f}?%zJbh?9J\u0016~f\"H\u0015[xk#%GH8x[";
      nb[101] = "[5b^\u000f\u001b\u0004mjVb\u000b\u0002/w.\r\u0001\u001f)sC_\u0012_:\u000b\u0015[\u0016\u000f/h\\\u001c\u0002YU";
      nb[102] = "<9=yUWca5q8]y\u00141qDM\u0002(&3\u0003[ffnc8";
   }

   public void onPreMouseInput(PreMouseInputEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      long var10001 = 104267827882366L;


      int var8 = (int)(var10001 << 40 >>> 40);
      var10001 = 91503647250490L;


      int var11 = (int)(var10001 << 40 >>> 40);


      var10001 = 42176070331598L;





      int var32 = 6007;



      B = false;
      if (I > 0) {
         I--;
         this.M = true;
      } else {
         this.M = false;
      }

      int var37 = t(81424435728200L);
      if (var37 != 0 && var37 != 2) {
         if (!f$r2()) {
            K = c();
            k = c() ? 0 : 1;
            m(21304, (byte)218, var11);
            f.thePlayer.setItemInUse(f.thePlayer.getHeldItem(), f.thePlayer.getHeldItem().getMaxItemUseDuration());
         }

         float var47 = requireKillAura.c() ? KillAura.attackRange.L() : 3.0F;
         boolean var39 = !requireKillAura.c() || !KillAura.throughWall.c();
         this.F = this.D(var47, (byte)0, 6214846, var39, var8);
         m = true;
         boolean var40 = true;
         String var41 = mode.Y();
         int var42 = -1;
         switch (var41.hashCode()) {
            case -1228695924:
               if (var41.equals("LAG_LEGIT")) {
                  var42 = 2;
               }
               break;
            case -420882864:
               if (var41.equals("LAG_LEGIT_PRE")) {
                  var42 = 3;
               }
               break;
            case 75122:
               if (var41.equals("LAG")) {
                  var42 = 0;
               }
               break;
            case 72313753:
               if (var41.equals("LEGIT")) {
                  var42 = 7;
               }
               break;
            case 660175155:
               if (var41.equals("LAG_NEW")) {
                  var42 = 4;
               }
               break;
            case 660177462:
               if (var41.equals("LAG_PRE")) {
                  var42 = 1;
               }
               break;
            case 951084891:
               if (var41.equals("VANILLA")) {
                  var42 = 6;
               }
               break;
            case 2129661303:
               if (var41.equals("LAG_NEW_PRE")) {
                  var42 = 5;
               }
         }

         switch (var42) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
               this.F(19386209426888L);
               break;
            case 6:
               var40 = false;
               if (c() && (!manualLeftClick.c() || G)) {
                  this.e(0L, var40);
                  this.y(0L);
               }

               k = 2;
               break;
            case 7:
               this.b(62049884109922L);
         }

         boolean var48 = mode.Y().contains("LAG") && System.currentTimeMillis() - this.Y >= mb;
         if (k == 12) {
            k = 7;
         } else if (k == 13) {
            k = 8;
         } else if (k == 9) {
            k = 0;
         } else if (k == 10) {
            k = 11;
         } else if (var48 || k == 2 || k == 5 || k == 6) {
            boolean var49 = false;
            boolean var43 = false;
            if (!manualLeftClick.c() || G) {
               if (this.F != null) {
                  if (this.M(14410, (char)33447, (short)var32, this.F, var40)) {
                     KillAura.x = true;
                     G = false;
                     var49 = true;
                     var43 = true;
                     B = true;
                  }
               } else if (this.T((char)0, 138059789010217L, var40)) {
                  KillAura.x = true;
                  G = false;
                  var49 = true;
               }
            }

            if (k != 5 && k != 6 && (var49 || var48) && (mode.Y().contains("LAG_NEW") || this.Q(var40, 73338735518706L, var43, this.F))) {
               switch (mode.Y()) {
                  case "LAG":
                  case "LAG_LEGIT":
                     k = 4;
                     break;
                  case "LAG_PRE":
                     this.o$r2();
                     this.W(0L);
                     this.J = true;
                     k = 0;
                     break;
                  case "LAG_LEGIT_PRE":
                     this.W(0L);
                     this.J = true;
                     k = 0;
                     break;
                  case "LAG_NEW":
                     if (s(26815, -704018324)) {
                        k = 12;
                     } else {
                        k = 7;
                     }
                     break;
                  case "LAG_NEW_PRE":
                     if (s(26815, -704018324)) {
                        k = 13;
                     } else {
                        k = 8;
                     }
                     break;
                  default:
                     k = 0;
               }
            }
         }

         var1.q(9819, 57776);
      } else {
         Pair var38 = this.n(var37 != 2,0L);
         if (var37 == 2
            || (Boolean)var38.a()
            || !(Boolean)var38.p()) {
            var1.q(9819, 57776);
         }
      }
   }

   private static MethodHandle a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, long var4, long var6) {
      char var8 = var2.charAt(0);
      MethodHandle var9 = null;
      Field var10 = null;
      Method var11 = null;

      try {
         if (var8 != 'w' && var8 != 'm' && var8 != 238 && var8 != 'T') {
            var11 = d(var4, var6);
            Expo.internal.restore.ExpoHandleProbe.log("Expo/module/impl/combat/AutoBlock.java", var8, var4, var6, var11); // add code
            Class var17 = var11.getDeclaringClass();
            String var19 = var11.getName();
            MethodType var20 = MethodType.methodType(var11.getReturnType(), var11.getParameterTypes());
            if (var8 == 'A') {
               var9 = var0.findVirtual(var17, var19, var20);
            } else if (var8 == 'q') {
               var9 = var0.findStatic(var17, var19, var20);
            } else {
               var9 = var0.findSpecial(var17, var19, var20, var17);
            }
         } else {
            var10 = c(var4, var6);
            Expo.internal.restore.ExpoHandleProbe.log("Expo/module/impl/combat/AutoBlock.java", var8, var4, var6, var10); // add code
            Class var12 = var10.getDeclaringClass();
            String var18 = var10.getName();
            Class var14 = var10.getType();
            if (var8 == 'w') {
               var9 = var0.findGetter(var12, var18, var14);
            } else if (var8 == 'm') {
               var9 = var0.findSetter(var12, var18, var14);
            } else if (var8 == 238) {
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

   private boolean y(long var1) {
      if (C) {
         PacketManager.b(new C09PacketHeldItemChange(f.thePlayer.inventory.currentItem));
         C = false;
         this.k(false);
         return true;
      } else {
         return true;
      }
   }

   public static boolean G(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (m && s(26815, -704018324)) {
         String var4 = mode.Y();
         int var5 = -1;
         switch (var4.hashCode()) {
            case -1228695924:
               if (var4.equals("LAG_LEGIT")) {
                  var5 = 2;
               }
               break;
            case -420882864:
               if (var4.equals("LAG_LEGIT_PRE")) {
                  var5 = 3;
               }
               break;
            case 75122:
               if (var4.equals("LAG")) {
                  var5 = 0;
               }
               break;
            case 660175155:
               if (var4.equals("LAG_NEW")) {
                  var5 = 4;
               }
               break;
            case 660177462:
               if (var4.equals("LAG_PRE")) {
                  var5 = 1;
               }
               break;
            case 951084891:
               if (var4.equals("VANILLA")) {
                  var5 = 6;
               }
               break;
            case 2129661303:
               if (var4.equals("LAG_NEW_PRE")) {
                  var5 = 5;
               }
         }

         switch (var5) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
               return true;
            default:
               return false;
         }
      } else {
         return false;
      }
   }

   private boolean e(long var1, boolean var3) {
      if (!var3 || !OutgoingPacketState.h && !OutgoingPacketState.P && !OutgoingPacketState.E) {
         int var4 = f.thePlayer.inventory.currentItem;
         int var5 = this.p + 1 >= 8 ? (var4 == 0 ? var4 + 1 : 0) : (this.p + 1 == var4 ? this.p + 2 : this.p + 1);
         this.p = var5;
         PacketManager.b(new C09PacketHeldItemChange(var5));
         C = true;
         this.k(false);
         return true;
      } else {
         return false;
      }
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

   private static void m(int var0, byte var1, int var2) {
      D = true;
   }

   private void F(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {





      String var14 = mode.Y();
      if (k != 0 && k != 3 && k != 5) {
         if (k == 1 || k == 6) {
            k = 2;
            if (X(0L, (short)55086) && this.F != null && RaytraceUtil.q(50051018191872L, this.F, 3.5)) {
               KeepSprint.t = 2;
               KeepSprint.a = 0;
            }
         }
      } else {
         this.W(0L);
         if (var14.contains("LAG_NEW")) {
            this.C(10860524904695L);
         } else {
            this.r(38310564483000L);
         }

         if (k == 1 || k == 6) {
            if (var14.equals("LAG_LEGIT") || var14.equals("LAG_LEGIT_PRE")) {
               this.o$r2();
               this.n();
               f.thePlayer.stopUsingItem();
               this.J = false;
            }

            if (X(0L, (short)55086) && this.F != null && RaytraceUtil.q(50051018191872L, this.F, 3.5)) {
               KeepSprint.t = 1;
               KeepSprint.a = 0;
            }
         }
      }
   }

   private static int d(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 719;
      if (hb[var3] == null) {
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
         long var5 = gb[var3];
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
         Object[] var9 = (Object[])ib.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               ib.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/module/impl/combat/AutoBlock", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         hb[var3] = var15;
      }

      return hb[var3];
   }

   private static Field a(Class var0, String var1, Class var2) {
      for (Field var6 : var0.getDeclaredFields()) {
         if (var6.getName().equals(var1) && var6.getType() == var2) {
            return var6;
         }
      }

      return null;
   }

   public void onUpdateCameraAndRender(long var1, UpdateCameraAndRenderEvent var3) {


      if ((m || this.M) && visualBlocking.c()) {
         var3.W(17984, 996510524L);
      }
   }

   public void onTick(TickEvent var1, long var2) {
      if (var1.v == f.gameSettings.keyBindAttack.getKeyCode()) {
         V(0L);
      }
   }

   private static boolean g$r1(long var0) {
      var0 = o ^ var0;
      long var2 = var0 ^ 131540701814873L;
      long var4 = var0 ^ 23010005291846L;
      List var6 = EntityUtil.x(EntityUtil.F(targetRange.L(), var4, fov.L()), players.c(), mobs.c(), animals.c(), bosses.c(), var2, friends.c(), enemies.c(), teammates.c(), bots.c(), silverfishes.c(), golems.c());
      return !var6.isEmpty();
   }

   private void W(long var1) {
      PacketManager.M(true);
      this.J = true;
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

   private static CallSite a(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments(MethodHandles.lookup().findStatic(AutoBlock.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/Object;", AutoBlock.class.getClassLoader())).asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1, var2), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("Expo/module/impl/combat/AutoBlock" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private void C(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (s(26815, -704018324)) {
         if (k == 0) {
            if (this.K(0L, true)) {
               this.e(0L, false);
               k = 3;
            }
         } else {
            switch (apsMode.Y()) {
               case "3APS":
               case "5APS":
                  if (k == 3) {
                     OutgoingPacketState.P = true;
                     if (this.y(0L)) {
                        k = 5;
                     }
                  } else if (k == 5) {
                     OutgoingPacketState.P = true;
                     k = 1;
                  }
                  break;
               case "7APS":
                  if (k == 3 && this.y(0L)) {
                     k = 2;
                  }
                  break;
               case "10APS":
               case "14APS":
                  if (k == 3) {
                     if (this.y(0L)) {
                        k = 6;
                     }
                  } else {
                     k = 1;
                  }
            }
         }
      } else if (k == 0) {
         if (this.K(0L, true)) {
            k = 3;
         }
      } else {
         switch (apsMode.Y()) {
            case "3APS":
               if (k == 3) {
                  OutgoingPacketState.P = true;
                  k = 5;
               } else if (k == 5) {
                  OutgoingPacketState.P = true;
                  k = 1;
               }
               break;
            case "5APS":
               if (k == 3) {
                  OutgoingPacketState.P = true;
                  k = 1;
               }
               break;
            case "7APS":
               if (k == 3) {
                  k = 2;
               }
               break;
            case "10APS":
            case "14APS":
               if (k == 3) {
                  k = 6;
               } else {
                  k = 1;
               }
         }
      }
   }

   public void onModuleTag(ModuleTagEvent var1, long var2) {
      var1.U("autoblockStage: " + k);
      var1.U("isBlocking: " + c());
   }

   private void r(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (s(26815, -704018324)) {
         if (k == 0) {
            if (this.K(0L, true)) {
               this.e(0L, false);
               k = 3;
            }
         } else {
            switch (apsMode.Y()) {
               case "3APS":
               case "5APS":
                  if (k == 3) {
                     OutgoingPacketState.P = true;
                     k = 5;
                  } else if (k == 5) {
                     OutgoingPacketState.P = true;
                     if (this.y(0L)) {
                        k = 1;
                     }
                  }
                  break;
               case "7APS":
                  if (k == 3) {
                     OutgoingPacketState.P = true;
                     if (this.y(0L)) {
                        k = 1;
                     }
                  }
                  break;
               case "10APS":
                  if (k == 3) {
                     OutgoingPacketState.P = true;
                     if (this.y(0L)) {
                        k = 5;
                     }
                  } else if (k == 5) {
                     k = 1;
                  }
                  break;
               case "14APS":
                  if (k == 3 && this.y(0L)) {
                     k = 6;
                  }
            }
         }
      } else if (k == 0) {
         if (this.K(0L, true)) {
            k = 3;
         }
      } else {
         switch (apsMode.Y()) {
            case "3APS":
            case "5APS":
               if (k == 3) {
                  OutgoingPacketState.P = true;
                  k = 5;
               } else if (k == 5) {
                  OutgoingPacketState.P = true;
                  k = 1;
               }
               break;
            case "7APS":
               if (k == 3) {
                  OutgoingPacketState.P = true;
                  k = 1;
               }
               break;
            case "10APS":
               if (k == 3) {
                  OutgoingPacketState.P = true;
                  k = 5;
               } else {
                  k = 6;
               }
               break;
            case "14APS":
               if (k == 3) {
                  k = 6;
               }
         }
      }
   }

   private static Method d(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = nb[var4];
      if (!(var5 instanceof String)) {
         return (Method)var5;
      }

      String var6 = ob[var4];
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
            nb[var4] = var26;
            return var26;
         }

         if (var23.getName().equals("java.lang.Object")) {
            break;
         }

         if ((var23 = var23.getSuperclass()) == null) {
            var23 = b(1523196619010493L, 0L);
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
                  nb[var4] = var19;
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
            var23 = b(1523196619010493L, 0L);
         }
      }
   }

   public static void V(long var0) {
      G = true;
   }

   private void n() {
      PacketManager.M(false);
   }

   public void onReceivePacket(ReceivePacketEvent var1, long var2) {
      if (smartUnblock.c()) {
         if (var1.d instanceof S19PacketEntityStatus) {
            S19PacketEntityStatus var6 = (S19PacketEntityStatus)var1.d;
            if (var6.getEntity(f.theWorld) instanceof EntityPlayerSP && var6.getOpCode() == 2 && MathUtil.Q(smartUnblockChance.k(),0L)) {
               I = (int)smartUnblockTicks.L();
            }
         }
      }
   }

   private void b(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (k == 1) {
         k = 2;
         if (X(0L, (short)55086) && this.F != null && RaytraceUtil.q(50051018191872L, this.F, 3.5)) {
            KeepSprint.t = 2;
            KeepSprint.a = 0;
         }
      } else if (k == 0 || k == 3 || k == 5) {
         if (k == 0) {
            switch (apsMode.Y()) {
               case "10APS":
                  if (this.K(0L, true)) {
                     f.thePlayer.stopUsingItem();
                     k = 7;
                  }
                  break;
               case "14APS":
                  if (this.K(0L, true)) {
                     f.thePlayer.stopUsingItem();
                     k = 3;
                  }
                  break;
               default:
                  k = 3;
            }
         } else {
            switch (apsMode.Y()) {
               case "3APS":
               case "5APS":
                  if (k == 3) {
                     OutgoingPacketState.P = true;
                     k = 5;
                  } else if (this.K(0L, true)) {
                     f.thePlayer.stopUsingItem();
                     k = 7;
                  }
                  break;
               case "7APS":
                  if (this.K(0L, true)) {
                     f.thePlayer.stopUsingItem();
                     k = 7;
                  }
                  break;
               case "14APS":
                  if (k == 3) {
                     k = 7;
                  }
            }
         }

         if (k == 7) {
            k = 1;
            if (X(0L, (short)55086) && this.F != null && RaytraceUtil.q(50051018191872L, this.F, 3.5)) {
               KeepSprint.t = 1;
               KeepSprint.a = 0;
            }
         }
      }
   }

   static {
      o = 2719582613777L;
      zkm$clinit();
      k = 0;
      C = false;
      m = false;
      I = 0;
      G = false;
      D = false;
      K = false;
      B = false;
   }

   public static int t(long var0) {
      var0 = o ^ var0;
      long var2 = var0 ^ 21768853664689L;
      long var6 = var0 ^ 125537170550523L;
      if (!Modules.J(AutoBlock.class).o()) {
         return 0;
      } else if (mode.R("NONE")) {
         return 0;
      } else if (!ItemUtil.d()) {
         return 0;
      } else if (requireKillAura.c() && !KillAura.a) {
         return 0;
      } else if (requireRightClick.c() && !KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), var6)) {
         return 0;
      } else if (!T(0L)) {
         return 0;
      } else if (I > 0) {
         return 2;
      } else {
         return g$r1(var2) ? 1 : 0;
      }
   }

   public void onPostUpdate(long var1, short var3, PostUpdateEvent var4) {
      long var5 = (var1 << 16 | (long)var3 << 48 >>> 48) ^ o;
      long var9 = var5 ^ 50155738398037L;
      switch (k) {
         case 4:
            switch (mode.Y()) {
               case "LAG":
                  this.o$r2();
                  this.W(0L);
                  k = 0;
                  break;
               case "LAG_LEGIT":
                  this.W(0L);
                  k = 0;
            }
         case 5:
         case 6:
         case 9:
         case 10:
         default:
            break;
         case 7:
            this.Q(false, var9, B, this.F);
            k = 10;
            break;
         case 8:
            this.Q(false, var9, B, this.F);
            this.o$r2();
            this.W(0L);
            k = 9;
            break;
         case 11:
            this.o$r2();
            this.W(0L);
            k = 0;
      }
   }

   private static String b(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 8642;
      if (ab[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])bb.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               bb.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/module/impl/combat/AutoBlock", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = S[var5].getBytes("ISO-8859-1");
         ab[var5] = b(((Cipher)var4[0]).doFinal(var9));
      }

      return ab[var5];
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

   private boolean K(long var1, boolean var4) {
      if (!c()) {
         return true;
      } else if (ItemUtil.d() && (!var4 || !OutgoingPacketState.h && !OutgoingPacketState.P && !OutgoingPacketState.E)) {
         PacketManager.b(
            new C07PacketPlayerDigging(
               net.minecraft.network.play.client.C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN
            )
         );
         this.k(false);
         return true;
      } else {
         return false;
      }
   }

   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 129665493400693L) >>> 32);
      int var5 = (int)((var1 ^ 129665493400693L) << 32 >>> 56);
      AutoBlockBinder.Z(var4, var3, (byte)var5, this);
   }

   private EntityLivingBase D(double var1, byte var3, int var4, boolean var5, int var6) {
      long var7 = ((long)var3 << 56 | (long)var4 << 32 >>> 8 | (long)var6 << 40 >>> 40) ^ o;
      long var9 = var7 ^ 39019671195955L;
      long var11 = var7 ^ 126226262099151L;
      long var13 = var7 ^ 114499964531765L;
      EntityLivingBase var15 = null;
      if (KillAura.a) {
         var15 = KillAura.H6;
         if (!KillAura.throughWall.c() && RaytraceUtil.V(var15, var9, var1)) {
            return null;
         }

         if (!RaytraceUtil.i(var15, var1, var13, var5)) {
            return null;
         }
      } else {
         List<EntityLivingBase> var16 = EntityUtil.x(RaytraceUtil.j(var1), players.c(), mobs.c(), animals.c(), bosses.c(), var11, friends.c(), enemies.c(), teammates.c(), bots.c(), silverfishes.c(), golems.c());
         var16.removeIf(var2 -> {
            long var3x = 20827164685641L;
            long var5x = 140537582766428L;
            return RaytraceUtil.V(var2, var5x, var1);
         });
         var16.removeIf(var3x -> {
            long var4x = o ^ 68060580273161L;
            long var6x = var4x ^ 12168478327106L;
            return !RaytraceUtil.i(var3x, var1, var6x, var5);
         });
         var16.sort(Comparator.comparingDouble(var3x -> {
            long var4x = o ^ 39422669808605L;
            long var6x = var4x ^ 29001535206636L;
            return RaytraceUtil.M(var6x, var3x, var1, var5);
         }));
         if (!var16.isEmpty()) {
            var15 = (EntityLivingBase)var16.get(0);
         }
      }

      return var15;
   }

   private boolean T(char var1, long var2, boolean var4) {
      long var5 = ((long)var1 << 48 | var2 << 16 >>> 16) ^ o;
      if (var4
         && !zkm$unresolved$6$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_iT_c_OR_Expo_yO_f_y_slots_41_99(var5)) {
         return false;
      }

      if (OutgoingPacketState.T) {
         return true;
      }

      f.thePlayer.swingItem();
      return true;
   }

   public AutoBlock(byte var1, long var2) {
      super((((((long)((var1)) << 56) | 43254310455398L) ^ o) ^ 53883575149036L));
      // add code
      this.declare("AutoBlock", Category.Combat, "Allows you to combat while sword blocked");
      this.p = 0;
      this.J = false;
      this.F = null;
      this.Y = System.currentTimeMillis();
      this.M = false;
   }

   private static boolean X(long var0, short var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return Modules.J(KeepSprint.class).o() && KeepSprint.mode.R("RESTRICT");
   }

   private static int a(long var0, long var2) {
      var0 ^= var2 << 48 | var2;
      int var4 = (int)(var0 >>> 46);
      if (ob[var4] != null) {
         return var4;
      }

      Object var5 = nb[var4];
      if (!(var5 instanceof String)) {
         return var4;
      }

      int var6 = KEY_OFFSETS[(int)(var0 >>> 42 & 63L)];
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

      ob[var4] = new String(var13);
      return var4;
   }


   private static boolean zkm$unresolved$1$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_iT_c_OR_Expo_yO_Y_y_slots_41_67(long var2) {
       try {MethodType var4 = MethodType.fromMethodDescriptorString("(JJ)Z", AutoBlock.class.getClassLoader());
      return (boolean)MethodHandles.explicitCastArguments(a(MethodHandles.lookup(), null, "q", var4, 1362679790029856311L, var2), var4).invoke((long)1362679790029856311L, (long)var2);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private static boolean zkm$unresolved$2$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_yO_Y_OR_Expo_yO_f_y_slots_67_99(long var2) {
       try {MethodType var4 = MethodType.fromMethodDescriptorString("(JJ)Z", AutoBlock.class.getClassLoader());
      return (boolean)MethodHandles.explicitCastArguments(a(MethodHandles.lookup(), null, "q", var4, 7385552860468072201L, var2), var4).invoke((long)7385552860468072201L, (long)var2);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }




   private static boolean zkm$unresolved$6$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_iT_c_OR_Expo_yO_f_y_slots_41_99(long var2) {
       try {MethodType var4 = MethodType.fromMethodDescriptorString("(JJ)Z", AutoBlock.class.getClassLoader());
      return (boolean)MethodHandles.explicitCastArguments(a(MethodHandles.lookup(), null, "q", var4, -855537796426212009L, var2), var4).invoke((long)-855537796426212009L, (long)var2);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }
   private static void zkm$clinit() {
      try {

         nb = new Object[103];
         ob = new String[103];
         a();
         bb = new HashMap(13);
         Cipher var16;
         byte[] var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

         for (int var17 = 1; var17 < 8; var17++) {
            var10003[var17] = (byte)(56695932197746L << var17 * 8 >>> 56);
         }

         (var16 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var23 = new String[29];
         int var21 = 0;
         String var20 = "P)Ç=@x\u0019È¶\b\b\u008eÊçÑ\u0016B.QW/ïæ*\u0010+,'\rÄ\u008c×4ÊüéB\u0019ùª\r\u0018\u0094´¬ñ\u009a05åîb_2Rxë§Y!O²¨Sá\u0013\u0010§Ük\u0010SÂI\u008bsÀ?\u0080\u001eÅ\u008a\u008c\u0010Ú3Î:ÐÞwÎÌ³OèF\u008bÏº\u0010f(§\u0083ü8»·1\u0080¡$\u0017*\tÞ\u0018\u0010\u0094õ\u0086K\u0086tûQqL\u001fº9èV:\u0007ßOHý\u0006à\u0010½\u0099 ;d\u001fÚ\u008cº\u007fÀÉ$Ë\bU\u0010Uú\u0083\u008a\u0000e\n\u008bµ\u008e,6»µ<ü\u0010®\u007fÇ\u0083\u007f!\u0011½-yz\u0007\u0093+\u0099ô ï)ôTóà\u0010æi\tc\u007f\u0089\u001d\u0018Öd\u0096§\u001cç\u000eq\u00adá¨EÚì\u0095?8\u0010ÿ$j¦ä\u0087{\u008eÙ\u0094 \u008fw\u0002±^\u0010ª\u0010\u0088¡\u00adèôeòn'jÜ@MË b\u000e\u009e½¨vÕ\u008e\"¢\u0081\u0004\u0005öF·\u009aj\u0011«\u0016\u0006}¹\u001eD®Q©\u008b\u009cw\u0010<ü\u008b\u001c°0óöÍ\u009cðsÐ\u0096ÉÀ\u0010¿\u0094Ç\u0001\u0012\u0080\u0091\u0088#\u001fz\u009b\u0080\u0090Ù? \u0088[\u0096\u00023Zq\u0090áS<\u001bZ\u0015ñÛV\u009e~\r\u0091\u0098~¯¢\u0018öhvG\u0086S\u0010×H\u0017Äû;B$\u0083\u0011,Ùz\u008eº \u0010§BP|\u001b>\u0016R-Wé\u008e,£Îñ ÉYIq¨Òè-l\u008aÁÉzA´ó3fÒ9=\u0086Ò39N/\u008d¦¯3Û0_«É&ò'g\u0093¸\u0090³ù÷d\u0002cÂÊI\u0001\u009d±¿_\u009cf\u0007,Ç=}\u0000\u001d:b\u0017À_ËyëÜ\u0099Yë;\u008a?\u0010-\u001e=`r«\u0090ÉªÉ¤ª÷£\u008bÃ\u0010<\r]ow1]\u0085ç¶[ö\u0000!R¡ I=È\u009f%(±1ë\u001e¶qnVV\u000f½½\u0013:ÚUZc\u0088àI\u0004A/K\u0096\u0010\u009eÃû\u0094ó<\u0094\b\u0010\u0088¡\u001cûw\u0012^\u0010p¾õ\u0084°Ê¸¤#øBOõoSk\u0010U)\u0002¦\u0016È´3pI\n£r¡LÇ";
         int var22 = "P)Ç=@x\u0019È¶\b\b\u008eÊçÑ\u0016B.QW/ïæ*\u0010+,'\rÄ\u008c×4ÊüéB\u0019ùª\r\u0018\u0094´¬ñ\u009a05åîb_2Rxë§Y!O²¨Sá\u0013\u0010§Ük\u0010SÂI\u008bsÀ?\u0080\u001eÅ\u008a\u008c\u0010Ú3Î:ÐÞwÎÌ³OèF\u008bÏº\u0010f(§\u0083ü8»·1\u0080¡$\u0017*\tÞ\u0018\u0010\u0094õ\u0086K\u0086tûQqL\u001fº9èV:\u0007ßOHý\u0006à\u0010½\u0099 ;d\u001fÚ\u008cº\u007fÀÉ$Ë\bU\u0010Uú\u0083\u008a\u0000e\n\u008bµ\u008e,6»µ<ü\u0010®\u007fÇ\u0083\u007f!\u0011½-yz\u0007\u0093+\u0099ô ï)ôTóà\u0010æi\tc\u007f\u0089\u001d\u0018Öd\u0096§\u001cç\u000eq\u00adá¨EÚì\u0095?8\u0010ÿ$j¦ä\u0087{\u008eÙ\u0094 \u008fw\u0002±^\u0010ª\u0010\u0088¡\u00adèôeòn'jÜ@MË b\u000e\u009e½¨vÕ\u008e\"¢\u0081\u0004\u0005öF·\u009aj\u0011«\u0016\u0006}¹\u001eD®Q©\u008b\u009cw\u0010<ü\u008b\u001c°0óöÍ\u009cðsÐ\u0096ÉÀ\u0010¿\u0094Ç\u0001\u0012\u0080\u0091\u0088#\u001fz\u009b\u0080\u0090Ù? \u0088[\u0096\u00023Zq\u0090áS<\u001bZ\u0015ñÛV\u009e~\r\u0091\u0098~¯¢\u0018öhvG\u0086S\u0010×H\u0017Äû;B$\u0083\u0011,Ùz\u008eº \u0010§BP|\u001b>\u0016R-Wé\u008e,£Îñ ÉYIq¨Òè-l\u008aÁÉzA´ó3fÒ9=\u0086Ò39N/\u008d¦¯3Û0_«É&ò'g\u0093¸\u0090³ù÷d\u0002cÂÊI\u0001\u009d±¿_\u009cf\u0007,Ç=}\u0000\u001d:b\u0017À_ËyëÜ\u0099Yë;\u008a?\u0010-\u001e=`r«\u0090ÉªÉ¤ª÷£\u008bÃ\u0010<\r]ow1]\u0085ç¶[ö\u0000!R¡ I=È\u009f%(±1ë\u001e¶qnVV\u000f½½\u0013:ÚUZc\u0088àI\u0004A/K\u0096\u0010\u009eÃû\u0094ó<\u0094\b\u0010\u0088¡\u001cûw\u0012^\u0010p¾õ\u0084°Ê¸¤#øBOõoSk\u0010U)\u0002¦\u0016È´3pI\n£r¡LÇ"
            .length();
         char var19 = 24;
         int var30 = -1;

         label70:
         while (true) {
            String var31 = var20.substring(++var30, var30 + var19);
            int var10001 = -1;

            while (true) {
               byte[] var24 = var16.doFinal(var31.getBytes("ISO-8859-1"));
               String var44 = b(var24).intern();
               switch (var10001) {
                  case 0:
                     var23[var21++] = var44;
                     if ((var30 += var19) >= var22) {
                        S = var23;
                        ab = new String[29];
                        ib = new HashMap(13);
                        Cipher var5;
                        var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

                        for (int var6 = 1; var6 < 8; var6++) {
                           var10003[var6] = (byte)(56695932197746L << var6 * 8 >>> 56);
                        }

                        (var5 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var11 = new long[27];
                        int var8 = 0;
                        String var9 = "\u0006\u007f6\u0006q\u008d\u0018\u001b)§<½`gÏî{\u0087ËiÐ>\u0001ÏIíocüÝÄÆXÿ\u009dáf\u0096®s¾bXrdi\u0016\u0010+h§q¹¿\u008d\u0017\u0004\u0081µ\u0018Ô\u000fÃ\u0012\u000eÃ\u0098ðÇèó.d±\u0006Ç\u001bja«\u0007ñ\u0007ï\u0083\u0011hóÍ^\u0087\u0000Þ}I¶ \u008b\u009dF÷$\u00922oþ:èlXn\u0082Ï²yKÖ6h\u001aöxX\u00100\u0004\u0094ßÿãè·ý§tñS\u0080\u001e~µ>\u001b\u0018L\u009cîÕ¿é\u0093\u0018q\u0098\u0000\u0092`ñ\u0092&\u0097f7¿\u008bÓkmVý\r\u0002i;BÅá\u0017\u0006\u0015\u0005$s\u0082ûÌ\u0017Q¾ø<²~\u0095Î(Å½Ûy";
                        int var10 = "\u0006\u007f6\u0006q\u008d\u0018\u001b)§<½`gÏî{\u0087ËiÐ>\u0001ÏIíocüÝÄÆXÿ\u009dáf\u0096®s¾bXrdi\u0016\u0010+h§q¹¿\u008d\u0017\u0004\u0081µ\u0018Ô\u000fÃ\u0012\u000eÃ\u0098ðÇèó.d±\u0006Ç\u001bja«\u0007ñ\u0007ï\u0083\u0011hóÍ^\u0087\u0000Þ}I¶ \u008b\u009dF÷$\u00922oþ:èlXn\u0082Ï²yKÖ6h\u001aöxX\u00100\u0004\u0094ßÿãè·ý§tñS\u0080\u001e~µ>\u001b\u0018L\u009cîÕ¿é\u0093\u0018q\u0098\u0000\u0092`ñ\u0092&\u0097f7¿\u008bÓkmVý\r\u0002i;BÅá\u0017\u0006\u0015\u0005$s\u0082ûÌ\u0017Q¾ø<²~\u0095Î(Å½Ûy"
                           .length();
                        int var7 = 0;

                        label52:
                        while (true) {
                           var10001 = var7;
                           var7 += 8;
                           byte[] var12 = var9.substring(var10001, var7).getBytes("ISO-8859-1");
                           long[] var34 = var11;
                           var10001 = var8++;
                           long var48 = (var12[0] & 255L) << 56
                              | (var12[1] & 255L) << 48
                              | (var12[2] & 255L) << 40
                              | (var12[3] & 255L) << 32
                              | (var12[4] & 255L) << 24
                              | (var12[5] & 255L) << 16
                              | (var12[6] & 255L) << 8
                              | var12[7] & 255L;
                           int var53 = -1;

                           while (true) {
                              long var13 = var48;
                              byte[] var15 = var5.doFinal(
                                 new byte[]{
                                    (byte)(var13 >>> 56),
                                    (byte)(var13 >>> 48),
                                    (byte)(var13 >>> 40),
                                    (byte)(var13 >>> 32),
                                    (byte)(var13 >>> 24),
                                    (byte)(var13 >>> 16),
                                    (byte)(var13 >>> 8),
                                    (byte)var13
                                 }
                              );
                              long var56 = (var15[0] & 255L) << 56
                                 | (var15[1] & 255L) << 48
                                 | (var15[2] & 255L) << 40
                                 | (var15[3] & 255L) << 32
                                 | (var15[4] & 255L) << 24
                                 | (var15[5] & 255L) << 16
                                 | (var15[6] & 255L) << 8
                                 | var15[7] & 255L;
                              switch (var53) {
                                 case 0:
                                    var34[var10001] = var56;
                                    if (var7 >= var10) {
                                       gb = var11;
                                       hb = new Integer[27];
                                       Cipher var0;
                                       var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

                                       for (int var1 = 1; var1 < 8; var1++) {
                                          var10003[var1] = (byte)(56695932197746L << var1 * 8 >>> 56);
                                       }

                                       (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));

                                       byte[] var4 = var0.doFinal(
                                          new byte[]{
                                             (byte)11L,
                                             (byte)2995L,
                                             (byte)766887L,
                                             (byte)196323130L,
                                             (byte)50258721344L,
                                             (byte)12866232664304L,
                                             (byte)3293755562061999L,
                                             (byte)843201423887871795L
                                          }
                                       );
                                       long var51 = (var4[0] & 255L) << 56
                                          | (var4[1] & 255L) << 48
                                          | (var4[2] & 255L) << 40
                                          | (var4[3] & 255L) << 32
                                          | (var4[4] & 255L) << 24
                                          | (var4[5] & 255L) << 16
                                          | (var4[6] & 255L) << 8
                                          | var4[7] & 255L;
                                       mb = var51;
                                       return;
                                    }
                                    break;
                                 default:
                                    var34[var10001] = var56;
                                    if (var7 < var10) {
                                       continue label52;
                                    }

                                    var9 = "^)·2\u0015aÕ\u009d©\u0011ð}\u009dF\u000fÄ";
                                    var10 = "^)·2\u0015aÕ\u009d©\u0011ð}\u009dF\u000fÄ".length();
                                    var7 = 0;
                              }

                              int var41 = var7;
                              var7 += 8;
                              var12 = var9.substring(var41, var7).getBytes("ISO-8859-1");
                              var34 = var11;
                              var10001 = var8++;
                              var48 = (var12[0] & 255L) << 56
                                 | (var12[1] & 255L) << 48
                                 | (var12[2] & 255L) << 40
                                 | (var12[3] & 255L) << 32
                                 | (var12[4] & 255L) << 24
                                 | (var12[5] & 255L) << 16
                                 | (var12[6] & 255L) << 8
                                 | var12[7] & 255L;
                              var53 = 0;
                           }
                        }
                     }

                     var19 = var20.charAt(var30);
                     break;
                  default:
                     var23[var21++] = var44;
                     if ((var30 += var19) < var22) {
                        var19 = var20.charAt(var30);
                        continue label70;
                     }

                     var20 = "\u0095¨Àhô§k;u\u001ax\u0085xj\u0081è\u0010ábp\u0013\u0014.m_êø\u0014\u0095\u009c®\u001dU";
                     var22 = "\u0095¨Àhô§k;u\u001ax\u0085xj\u0081è\u0010ábp\u0013\u0014.m_êø\u0014\u0095\u009c®\u001dU".length();
                     var19 = 16;
                     var30 = -1;
               }

               var31 = var20.substring(++var30, var30 + var19);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var27) {
         throw new RuntimeException(var27);
      }
   }

   static {
      // add code
      smartUnblockChance = new PercentageSetting("Smart-unblock-chance", 100);
   }
   static {
      // add code
      manualLeftClick = new BooleanSetting("Manual-left-click", false);
      requireRightClick = new BooleanSetting("Require-right-click", false);
      requireKillAura = new BooleanSetting("Require-KillAura", true);
      smartUnblock = new BooleanSetting("Smart-unblock", false);
      allowNoSlow = new BooleanSetting("Allow-no-slow", true);
      visualBlocking = new BooleanSetting("Visual-blocking", true);
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
      // add code
      fov = new NumberSetting("FOV", 360.0F, 1.0F, 360.0F, 1.0F);
      targetRange = new NumberSetting("Target-range", 5.0F, 1.0F, 8.0F, 0.01F);
      smartUnblockTicks = new NumberSetting("Smart-unblock-ticks", 8.0F, 0.0F, 15.0F, 1.0F);
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "LAG_NEW", "LAG_NEW_PRE", "LAG", "LAG_PRE", "LAG_LEGIT", "LAG_LEGIT_PRE", "LEGIT", "VANILLA", "NONE");
      apsMode = new ModeSetting("APS-mode", "3APS", "5APS", "7APS", "10APS", "14APS");
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
