package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BlockHitBinder;
import Expo.event.events.PostUpdateEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.UpdateCameraAndRenderEvent;
import Expo.module.Module;
import Expo.module.Modules;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.CombatUtil;
import Expo.util.EntityUtil;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.Sneaky;
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
import java.util.HashMap;
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
import net.minecraft.network.play.server.S19PacketEntityStatus;











public class BlockHit extends Module implements EventSubscriber {
   public static boolean N;
   private long h;
   public static NumberSetting predictBlockTicks;
   public static BooleanSetting enemies;
   public static HeaderSetting targetSettings;
   private boolean v;
   private static long[] bb;
   public static NumberSetting fov;
   public static NumberSetting spamBPS;
   public static BooleanSetting mobs;
   public static BooleanSetting friends;
   private int y;
   private static String[] pb;
   private static long[] ib;
   private static Map ab;
   private static Map nb;
   public static NumberSetting predictEarlyTicks;
   private static long D;
   public static BooleanSetting players;
   private static Integer[] gb;
   private boolean O;
   private long t;
   private static String[] L;
   public static BooleanSetting animals;
   public static ModeSetting lagAfterBlockMode;
   public static NumberSetting predictRandomEarlyTicks;
   public static BooleanSetting requireLeftClick;
   public static NumberSetting predictHurtResistTicks;
   private boolean x;
   public static ModeSetting mode;
   private boolean E;
   public static BooleanSetting onlyAutoClicker;
   // update new version
   public static BooleanSetting allowNoSlow;
   // update new version
   public static BooleanSetting visualBlocking;
   private long H;
   public static BooleanSetting teammates;
   public static BooleanSetting requireRightClick;
   public static BooleanSetting bosses;
   private boolean k;
   public static NumberSetting predictMaxPingCompTicks;
   public static NumberSetting range;
   private static Map hb;
   private static Object[] ob;
   public static NumberSetting lagAfterBlockTime;
   public static NumberSetting spamBlockTime;
   private static String[] J;
   public static BooleanSetting bots;
   private static final byte[] KEY_OFFSETS = {
      49, 50, 33, 43, 17, 44, 2, 27, 39, 0, 40, 8, 21, 10, 31, 15,
      5, 16, 45, 59, 4, 1, 60, 3, 61, 38, 41, 63, 52, 57, 6, 42,
      12, 37, 23, 36, 7, 9, 47, 25, 51, 29, 58, 62, 13, 26, 28, 48,
      34, 18, 24, 46, 22, 20, 56, 19, 35, 30, 11, 53, 14, 32, 55, 54
   };
   private int Y;

   private void isUsingItem(long var1) {
      var1 = D ^ var1;
      long var3 = var1 ^ 4104836459092L;
      int var5 = (int)((var1 ^ 52374169736260L) >>> 32);
      int var6 = (int)((var1 ^ 52374169736260L) << 32 >>> 48);
      int var7 = (int)((var1 ^ 52374169736260L) << 48 >>> 48);
      zkm$unresolved$0$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_ia_n_OR_Expo_ia_U_OR_Expo_ia_G_OR_Expo_ia_X_y_slots_25_35_42_45(this, var3, var1);
      this.H = 0L;
      this.t = 0L;
      this.v = false;
      N = false;
      if (this.O) {
         PacketManager.j();
         PacketManager.M(false);
         this.O = false;
      }

      if (this.k) {
         this.J(var5, (char)var6, (short)var7);
      }

      f.thePlayer.isUsingItem();
   }

   private void T(long var1) {
      var1 = D ^ var1;
      int var3 = (int)((var1 ^ 77543621352471L) >>> 32);
      int var4 = (int)((var1 ^ 77543621352471L) << 32 >>> 48);
      int var5 = (int)((var1 ^ 77543621352471L) << 48 >>> 48);
      this.x = false;
      this.Y = 0;
      this.J(var3, (char)var4, (short)var5);
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

   private int Y(short var1, char var2, int var3) {
      int var6 = Math.round(predictHurtResistTicks.L());
      int var7 = Math.round(predictEarlyTicks.L());
      int var8 = Math.round(predictRandomEarlyTicks.L());
      int var9 = var8 > 0 ? this.d(0, var8) : 0;
      // update new version
      int var10 = var6 - var7 - var9;
      return Math.max(0, var10);
   }

   private static CallSite a(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments(MethodHandles.lookup().findStatic(BlockHit.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/Object;", BlockHit.class.getClassLoader())).asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1, var2), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("Expo/module/impl/combat/BlockHit" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private int a() {
      int var1 = CombatUtil.q();
      if (var1 <= 0) {
         return 0;
      }

      int var2 = (int)Math.ceil(var1 / 50.0);
      int var3 = Math.round(predictMaxPingCompTicks.L());
      return MathUtil.k(var2, 0, var3);
   }

   private boolean Y(short var1, int var2, char var3) {
      long var4 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var3 << 48 >>> 48) ^ D;
      long var6 = var4 ^ 129672406886982L;
      long var8 = var4 ^ 82977365295539L;
      return !EntityUtil.K(EntityUtil.F(range.L(), var6, fov.L()), players.c(), var8, mobs.c(), animals.c(), bosses.c(), friends.c(), enemies.c(), teammates.c(), bots.c()).isEmpty();
   }

   public void onPreUpdate(long var1, PreUpdateEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (lagAfterBlockMode.R("PRE")) {
         this.q(8138133633306L);
      }
   }

   private static void c() {
      ob[0] = "";
      pb[0] = "Expo.module.impl.combat.BlockHit";
      ob[1] = long.class;
      pb[1] = "java/lang/Long";
      ob[2] = void.class;
      pb[2] = "java/lang/Void";
      ob[3] = int.class;
      pb[3] = "java/lang/Integer";
      ob[4] = char.class;
      pb[4] = "java/lang/Character";
      ob[5] = short.class;
      pb[5] = "java/lang/Short";
      ob[6] = "";
      pb[6] = "Expo.util.EntityUtil";
      ob[7] = double.class;
      pb[7] = "java/lang/Double";
      ob[8] = "";
      pb[8] = "java.util.List";
      ob[9] = boolean.class;
      pb[9] = "java/lang/Boolean";
      ob[10] = "";
      pb[10] = "Expo.util.ItemUtil";
      ob[11] = "";
      pb[11] = "Expo.util.KeyBindUtil";
      ob[12] = "";
      pb[12] = "net.minecraft.client.settings.KeyBinding";
      ob[13] = "";
      pb[13] = "Expo.setting.settings.BooleanSetting";
      ob[14] = "";
      pb[14] = "Expo.setting.settings.NumberSetting";
      ob[15] = float.class;
      pb[15] = "java/lang/Float";
      ob[16] = "";
      pb[16] = "Expo.setting.settings.ModeSetting";
      ob[17] = "";
      pb[17] = "java.lang.String";
      ob[18] = "";
      pb[18] = "Expo.util.packet.PacketManager";
      ob[19] = "";
      pb[19] = "java.lang.Object";
      ob[20] = "";
      pb[20] = "Expo.event.binder.BlockHitBinder";
      ob[21] = "";
      pb[21] = "Expo.event.EventBus";
      ob[22] = "qj l2Cw)`>\\:\u0018?p38\u0014w0z*c~";
      ob[23] = "QC~cjN\u0007D|\u0018K?\u0005H`sw\u0000\u0000F\u007f\u007f\u000b\u0004^Twd4\u0001PK{\u00181ZZUehkD\u000f\u0012\u001c&dZ\u0006H~}{F\u0002(";
      ob[24] = "L\u000bE9o_I\u000b^<\u0005t&\u0003]m?H\u001e\u0002\u0013xy%";
      ob[25] = "e87\u0019R\u001c3?5bUm3?/\u0007B\u000e`cd\f3Tz1/XYRa#/b";
      ob[26] = "";
      pb[26] = "e1ba8i0xnj\u0008s\u00081drmykb89f\u0008ewvuvz58sk\u0008gcjsiy1dh\u00081xjr2b7cxr\u0008";
      ob[27] = "0l\u0004rO812\u0014su4\u000f3\u0017|M8mh\b`IX";
      ob[28] = "ltnl\u0018\u0001<{quq2S?eo\f\u0015#e{:Kljizn\u0000\u000f951eqR<`8k\u0013\t#|<\u000b";
      ob[29] = "I?.@\u0015\u0012\u001f8,;6c\u001c1(F\r\u0013F/}\u0001tXHmtF\u001e\u0011F$.;O\u0003Z?0\u0004J\rE3L\u0002\u0004\u0001\\n&\u0004\u001f\u0013\\T";
      ob[30] = "..\nBY,+.\u0011G3\fD&\u0012\u0016\t;|'\\\u0003OVz#\bAS4!<\u0014E3";
      ob[31] = "\u000b6I`\u001el]1K\u001b.\u001d_=Wp\u0003\"Z3H|\u007f'\u00019Vb\u000f}\u001fl\u0011\u001bDs]eVq\r}\u0014?+%\u0010x\\=I~\u000fdX]";
      ob[32] = "";
      pb[32] = "45tvb4jx8i\u0008K\u000867ya891d7i\u00086gm0hjmxq4\u00081drmykb89f\u00086gm0hjmxq4\u00086gm0hjmxq4\u00086gm0hjmxq4\u00086gm0hjmxq4\u00086gm0hjmxq4\u00086gm0hjmxq4\u00086gm0hjmxq4\u000867ya891d7i\u0008";
      ob[33] = "\bX[\u000foc^_Ytw\u0012^_C\u0011\u007fq\r\u0003\b\u001a\u000e+\u0017QCNd-\fCCt";
      ob[34] = "\u001d*\u001d\u0012dtK-\u001fiT\u0005I!\u0003\u0002y:L/\u001c\u000e\u0005>\u001cxG\u0014ow\u00121\u001di?`\u0016<\u0006\u0019e~C{\u007fS`a\u000f8\u000f\t~4HA";
      ob[35] = "8$\u0016\"nhn#\u0014YR\u0019n#\u000e<~z=\u007fE7\u000f '-\u000ece&<?\u000eY";
      ob[36] = ",O-\u001aQ\bzH/alyzH5\u0004A\u001a)\u0014~\u000f0@3F5[ZF(T5a";
      ob[37] = "\u0013>l\\\"YE9n'\u001d(E9tB2K\u0016e?IC\u0011\f7t\u001d)\u0017\u0017%t'";
      ob[38] = "hH9${Ei\u0006,b\u0016\u0010{J?zm}nU;{\u007f\u0007>\u0006i}\u0016CmRn~t\u0018rNj\u001e";
      ob[39] = "[`J1\u0006~Z._wk&XdE@\f*\\\u001f\u001fn\u000f;HoEpZ|1";
      ob[40] = "\b\u0017\naZ\u001c^\u0010\b\u001a\u007fm^\u0010\u0012\u007fJ\u000e\rLYt;T\u0017\u001e\u0012 QR\f\f\u0012\u001a";
      ob[41] = "B:[2y}\u001cw\u0017-\u0013\u000f~:\u0016=nxEj\u0016#)AGk])b\"\u00147\u0016\"\u0013|OvZu(,Oh\u001dL-~\u000fn\u0017}*-Af'";
      ob[42] = ")=Ymm\u0015\u007f:[\u0016Cd\u007f:As}\u0007,f\nx\f]64A,f[-&A\u0016";
      ob[43] = "43\u000b\u001c\u000eZb4\tg\u0017+b4\u0013\u0002\u001eH1hX\to\u0012+:\u0013]\u0005\u00140(\u0013g";
      ob[44] = "`p \u0002\rE0<5\u001ahl\fr0\u0001PIn)/\u001dT)5<=\u001eRC3'/\u001eh";
      ob[45] = "<\u000b}\u0011\u001f]j\f\u007fj.,j\fe\u000f\u000fO9P.\u0004~\u0015#\u0002eP\u0014\u00138\u0010ej";
      ob[46] = "\u0007M\u0017h-eQX\u0014qKrJP\rXr!\u000e\nX4\u001c}7\f\u0003k6eGV\u001d>q\u001c";
      ob[47] = "L\u0016(t\u0013_EDt\"l\b}Cy{T\u0003\u001f\u0018fgPc";
      ob[48] = "y0\n\u0019\rf/7\bb\u0005\u0017,>\f\u001f\u0015gv YXl";
   }

   private static MethodHandle a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, long var4, long var6) {
      char var8 = var2.charAt(0);
      MethodHandle var9 = null;
      Field var10 = null;
      Method var11 = null;

      try {
         if (var8 != 194 && var8 != 'x' && var8 != 'b' && var8 != 223) {
            var11 = d(var4, var6);
            Expo.internal.restore.ExpoHandleProbe.log("Expo/module/impl/combat/BlockHit.java", var8, var4, var6, var11); // add code
            Class var17 = var11.getDeclaringClass();
            String var19 = var11.getName();
            MethodType var20 = MethodType.methodType(var11.getReturnType(), var11.getParameterTypes());
            if (var8 == 'F') {
               var9 = var0.findVirtual(var17, var19, var20);
            } else if (var8 == 196) {
               var9 = var0.findStatic(var17, var19, var20);
            } else {
               var9 = var0.findSpecial(var17, var19, var20, var17);
            }
         } else {
            var10 = c(var4, var6);
            Expo.internal.restore.ExpoHandleProbe.log("Expo/module/impl/combat/BlockHit.java", var8, var4, var6, var10); // add code
            Class var12 = var10.getDeclaringClass();
            String var18 = var10.getName();
            Class var14 = var10.getType();
            if (var8 == 194) {
               var9 = var0.findGetter(var12, var18, var14);
            } else if (var8 == 'x') {
               var9 = var0.findSetter(var12, var18, var14);
            } else if (var8 == 'b') {
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

   private int d(int var1, int var2) {
      return Math.round(MathUtil.h(var1, var2));
   }

   private void X(long var1) {
      this.y = 0;
      this.Y = 0;
      this.x = false;
      this.E = false;
   }

   static {
      D = 58710388792180L;
      zkm$clinit();
      N = false;
   }

   public void onReceivePacket(ReceivePacketEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {




      if (mode.R("PREDICT") && f.theWorld != null && var1.d instanceof S19PacketEntityStatus) {
         S19PacketEntityStatus var7 = (S19PacketEntityStatus)var1.d;
         if (var7.getEntity(f.theWorld) instanceof EntityPlayerSP && var7.getOpCode() == 2) {
            this.x = false;
            this.Y = 0;
            this.y = this.Y((short)0, (char)98, -1348816909);
            this.E = true;
         }
      }
   }

   private void n(long var1) {
      var1 = D ^ var1;
      int var3 = (int)((var1 ^ 91052879835688L) >>> 32);
      int var4 = (int)((var1 ^ 91052879835688L) << 32 >>> 48);
      int var5 = (int)((var1 ^ 91052879835688L) << 48 >>> 48);
      long var6 = var1 ^ 105226836753708L;
      if (this.v) {
         this.H = this.H + 1000L / (long)spamBPS.L();
         if (this.t <= 0L) {
            this.v = false;
            this.J(var3, (char)var4, (short)var5);
         }
      } else {
         if (this.H <= 0L) {
            this.t = this.t + (long)spamBlockTime.L();
            this.v = true;
            this.V(var6);
         } else {
            this.V(var6);
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      BlockHitBinder.s(var3, this);
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

   public void onPostUpdate(long var1, PostUpdateEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (lagAfterBlockMode.R("POST")) {
         this.q(8138133633306L);
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

   public void Z(long var1) {
      long var3 = var1 ^ 85749904770628L;
      zkm$unresolved$3$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_ia_p_OR_Expo_ia_X_y_slots_43_45(this, var3, var1);
   }

   private void V(long var1) {
      long var3 = var1 ^ 128179348746459L;
      this.k = true;
      KeyBindUtil.A(var3, f.gameSettings.keyBindUseItem.getKeyCode(), true);
   }

   private static Class b(long var0, long var2) {
      Class var5 = null;
      int var4 = a(var0, var2);
      Object var6 = ob[var4];
      try {
         if (var6 instanceof String) {
            var5 = Class.forName(pb[var4]);
            ob[var4] = var5;
            return var5;
         }
      } catch (Exception var8) {
         throw new RuntimeException(var8.toString());
      }

      return (Class)var6;
   }

   private int f$r3() {
      // update new version
      return Math.max(1, Math.round(predictBlockTicks.L()) + this.blockHoldLatencyTicks());
   }

   // update new version
   private int blockHoldLatencyTicks() {
      int var1 = CombatUtil.q();
      return var1 <= 0 ? 0 : MathUtil.k((int)Math.ceil(var1 / 50.0), 0, 2);
   }

   private void L(long var1) {
      long var3 = var1 ^ 110535147776327L;
      this.x = true;
      this.E = false;
      this.Y = this.f$r3();
      this.y = 0;
      this.V(var3);
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
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private void J(int var1, char var2, short var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ D;
      long var6 = var4 ^ 84157365855403L;
      this.k = false;
      KeyBindUtil.A(var6, f.gameSettings.keyBindUseItem.getKeyCode(), false);
   }

   private void G(long var1) {
      var1 = D ^ var1;
      long var3 = var1 ^ 64748412186009L;
      int var5 = (int)((var1 ^ 91394699627482L) >>> 32);
      int var6 = (int)((var1 ^ 91394699627482L) << 32 >>> 48);
      int var7 = (int)((var1 ^ 91394699627482L) << 48 >>> 48);
      long var8 = var1 ^ 36268436360377L;
      long var10 = var1 ^ 103786042115294L;
      if (this.x) {
         this.V(var10);
         this.Y--;
         if (this.Y <= 0) {
            this.T(var8);
         }
      } else {
         this.J(var5, (char)var6, (short)var7);
         if (this.E) {
            if (this.y > 0) {
               this.y--;
            }

            if (this.y <= 0) {
               this.L(var3);
            }
         }
      }
   }

   private static Method d(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = ob[var4];
      if (!(var5 instanceof String)) {
         return (Method)var5;
      }

      String var6 = pb[var4];
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
            ob[var4] = var26;
            return var26;
         }

         if (var23.getName().equals("java.lang.Object")) {
            break;
         }

         if ((var23 = var23.getSuperclass()) == null) {
            var23 = b(1375026162649760L, 0L);
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
                  ob[var4] = var19;
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
            var23 = b(1375026162649760L, 0L);
         }
      }
   }

   private static int a(long var0, long var2) {
      var0 ^= var2 << 48 | var2;
      int var4 = (int)(var0 >>> 46);
      if (pb[var4] != null) {
         return var4;
      }

      Object var5 = ob[var4];
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

      pb[var4] = new String(var13);
      return var4;
   }

   private static Object a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, Object[] var4) throws Throwable {
      int var5 = var4.length - 2;
      long var6 = (Long)var4[var5];
      long var9 = (Long)var4[++var5];
      MethodHandle var8 = a(var0, var1, var2, var3, var6, var9);
      var1.setTarget(MethodHandles.explicitCastArguments(var8, var3));
      return (Object)var8.asSpreader(Object[].class, var4.length).invoke(var4);
   }

   private static Field c(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = ob[var4];
      if (!(var5 instanceof String)) {
         return (Field)var5;
      }

      String var6 = pb[var4];
      int var7 = var6.indexOf(8);
      Class var8 = b(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      Class var11 = b(Long.parseLong(var6.substring(++var9), 36), 0L);
      Class var12 = var8;

      while (true) {
         Field var13 = a(var12, var10, var11);
         if (var13 != null) {
            ob[var4] = var13;
            return var13;
         }

         Class[] var14 = var12.getInterfaces();
         if (var14 != null) {
            for (int var15 = 0; var15 < var14.length; var15++) {
               var13 = b(var14[var15], var10, var11);
               if (var13 != null) {
                  ob[var4] = var13;
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
            var12 = b(1375026162649760L, 0L);
         }
      }
   }

   private void U(long var1) {
      if (this.H > 0L) {
         this.H = this.H - 50L;
      }

      if (this.t > 0L) {
         this.t = this.t - 50L;
      }

      if (this.h > 0L) {
         this.h = this.h - 50L;
      }
   }

   public BlockHit(long var1) {
      super(((D ^ (var1)) ^ 25070132217130L));
      // add code
      this.declare("BlockHit", Category.Combat, "Block the sword when needed to decrease damage received");
      var1 = D ^ var1;
      this.H = 0L;
      this.t = 0L;
      this.h = 0L;
      this.y = 0;
      this.Y = 0;
      this.x = false;
      this.E = false;
      this.k = false;
      this.v = false;
      this.O = false;
   }

   private boolean isGetKeyCode(short var1, short var2, int var3) {
      long var4 = ((long)var1 << 48 | (long)var2 << 48 >>> 16 | (long)var3 << 32 >>> 32) ^ D;
      int var6 = (int)((var4 ^ 30815205746720L) >>> 48);
      int var7 = (int)((var4 ^ 30815205746720L) << 16 >>> 32);
      int var8 = (int)((var4 ^ 30815205746720L) << 48 >>> 48);
      long var9 = var4 ^ 48345515779599L;
      if (!ItemUtil.d()) {
         return false;
      } else if (!this.Y((short)var6, var7, (char)var8)) {
         return false;
      } else if (requireLeftClick.c() && !KeyBindUtil.V(f.gameSettings.keyBindAttack.getKeyCode(), var9)) {
         return false;
      } else {
         return requireRightClick.c() && !KeyBindUtil.V(f.gameSettings.keyBindUseItem.getKeyCode(), var9) ? false : !onlyAutoClicker.c() || AutoClicker.I;
      }
   }

   public String g(long var1) {
      return mode.Y();
   }

   // update new version
   public static boolean noSlowLive() {
      BlockHit var0 = Modules.J(BlockHit.class);
      return allowNoSlow.c() && var0 != null && var0.o() && var0.k;
   }

   // update new version
   public void onUpdateCameraAndRender(long var1, UpdateCameraAndRenderEvent var3) {
      if (this.k && visualBlocking.c()) {
         var3.W(17984, 996510524L);
      }
   }

   private void q(long var1) {
      if (this.O && this.h <= 0L) {
         PacketManager.j();
         PacketManager.M(false);
         N = false;
         this.O = false;
      }
   }

   public void onPreMouseInput(PreMouseInputEvent var1, long var2, short var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = (var2 << 16 | (long)var4 << 48 >>> 48) ^ D;
      int var7 = (int)((var5 ^ 101937483733454L) >>> 48);
      int var8 = (int)((var5 ^ 101937483733454L) << 16 >>> 48);
      int var9 = (int)((var5 ^ 101937483733454L) << 32 >>> 32);
      long var10 = var5 ^ 49973904956291L;
      long var12 = var5 ^ 49082597876337L;
      long var16 = var5 ^ 89946405274653L;
      boolean var18 = this.k;
      this.U(0L);
      if (!this.isGetKeyCode((short)var7, (short)var8, var9)) {
         this.isUsingItem(var16);
      } else {
         this.k = true;
         switch (mode.Y()) {
            case "PREDICT":
               this.G(var10);
               break;
            case "SPAM":
               this.n(var12);
         }

         switch (lagAfterBlockMode.Y()) {
            case "PRE":
            case "POST":
               if (var18 && !this.k && !this.O) {
                  this.h = (long)lagAfterBlockTime.L();
                  PacketManager.M(true);
                  N = true;
                  this.O = true;
               }
         }
      }
   }

   private static String b(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 23016;
      if (L[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])ab.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               ab.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/module/impl/combat/BlockHit", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = J[var5].getBytes("ISO-8859-1");
         L[var5] = b(((Cipher)var4[0]).doFinal(var9));
      }

      return L[var5];
   }

   private static int d(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 14523;
      if (gb[var3] == null) {
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
         long var5 = bb[var3];
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
         Object[] var9 = (Object[])hb.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               hb.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/module/impl/combat/BlockHit", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         gb[var3] = var15;
      }

      return gb[var3];
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

   private static void zkm$unresolved$0$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_ia_n_OR_Expo_ia_U_OR_Expo_ia_G_OR_Expo_ia_X_y_slots_25_35_42_45(Object var0, long var1, long var5) {
       try {MethodType var7 = MethodType.fromMethodDescriptorString("(Ljava/lang/Object;JJJ)V", BlockHit.class.getClassLoader());
      MethodHandles.explicitCastArguments(a(MethodHandles.lookup(), null, "ö", var7, 7743209299158848852L, var5), var7)
         .invoke((Object)var0, (long)var1, (long)7743209299158848852L, (long)var5);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }



   private static void zkm$unresolved$3$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_ia_p_OR_Expo_ia_X_y_slots_43_45(Object var0, long var1, long var5) {
       try {MethodType var7 = MethodType.fromMethodDescriptorString("(Ljava/lang/Object;JJJ)V", BlockHit.class.getClassLoader());
      MethodHandles.explicitCastArguments(a(MethodHandles.lookup(), null, "ö", var7, 3910435423868124319L, var5), var7)
         .invoke((Object)var0, (long)var1, (long)3910435423868124319L, (long)var5);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }
   private static void zkm$clinit() {
      try {

         ob = new Object[49];
         pb = new String[49];
         c();
         ab = new HashMap(13);
         Cipher var22;
         byte[] var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

         for (int var23 = 1; var23 < 8; var23++) {
            var10003[var23] = (byte)(12610113794430L << var23 * 8 >>> 56);
         }

         (var22 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var29 = new String[7];
         int var27 = 0;
         String var26 = "<8iC%\u001bqÔX«x\u0097©ç\u0087É\u0010ª©Abi\u009cÇaQ¯0Z\r\u0006\u0095\u0093\u0010^§\u008f\u0089\u0018tNG\u009c7µ\u0094\u000e\"b\u0014\u0010õü\u00ad#\u0012\u008e\u000f@\u0001?±¿âÔHô\u0010ÈÐ\u0091½Lt1?\u008bDëË z\b?";
         int var28 = "<8iC%\u001bqÔX«x\u0097©ç\u0087É\u0010ª©Abi\u009cÇaQ¯0Z\r\u0006\u0095\u0093\u0010^§\u008f\u0089\u0018tNG\u009c7µ\u0094\u000e\"b\u0014\u0010õü\u00ad#\u0012\u008e\u000f@\u0001?±¿âÔHô\u0010ÈÐ\u0091½Lt1?\u008bDëË z\b?"
            .length();
         char var25 = 16;
         int var36 = -1;

         label77:
         while (true) {
            String var37 = var26.substring(++var36, var36 + var25);
            int var10001 = -1;

            while (true) {
               byte[] var30 = var22.doFinal(var37.getBytes("ISO-8859-1"));
               String var51 = b(var30).intern();
               switch (var10001) {
                  case 0:
                     var29[var27++] = var51;
                     if ((var36 += var25) >= var28) {
                        J = var29;
                        L = new String[7];
                        hb = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(12610113794430L << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var17 = new long[4];
                        int var14 = 0;
                        String var15 = "\tN9ÁÀ\f\u0016D©\u0012æËQþ\u0081´";
                        int var16 = "\tN9ÁÀ\f\u0016D©\u0012æËQþ\u0081´".length();
                        int var13 = 0;

                        label59:
                        while (true) {
                           var10001 = var13;
                           var13 += 8;
                           byte[] var18 = var15.substring(var10001, var13).getBytes("ISO-8859-1");
                           long[] var40 = var17;
                           var10001 = var14++;
                           long var55 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           int var59 = -1;

                           while (true) {
                              long var19 = var55;
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
                              long var63 = (var21[0] & 255L) << 56
                                 | (var21[1] & 255L) << 48
                                 | (var21[2] & 255L) << 40
                                 | (var21[3] & 255L) << 32
                                 | (var21[4] & 255L) << 24
                                 | (var21[5] & 255L) << 16
                                 | (var21[6] & 255L) << 8
                                 | var21[7] & 255L;
                              switch (var59) {
                                 case 0:
                                    var40[var10001] = var63;
                                    if (var13 >= var16) {
                                       bb = var17;
                                       gb = new Integer[4];
                                       nb = new HashMap(13);
                                       Cipher var0;
                                       var10003 = new byte[]{(byte)0L, 0, 0, 0, 0, 0, 0, 0};

                                       for (int var1 = 1; var1 < 8; var1++) {
                                          var10003[var1] = (byte)(12610113794430L << var1 * 8 >>> 56);
                                       }

                                       (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                       long[] var6 = new long[3];
                                       int var3 = 0;
                                       String var4 = "úî\u00adj\u001f×\u0011« \u0085ìW\u009c\u0086kÕS:n mßL}";
                                       int var5 = "úî\u00adj\u001f×\u0011« \u0085ìW\u009c\u0086kÕS:n mßL}".length();
                                       int var2 = 0;

                                       do {
                                          int var48 = var2;
                                          var2 += 8;
                                          byte[] var7 = var4.substring(var48, var2).getBytes("ISO-8859-1");
                                          var48 = var3++;
                                          long var8 = (var7[0] & 255L) << 56
                                             | (var7[1] & 255L) << 48
                                             | (var7[2] & 255L) << 40
                                             | (var7[3] & 255L) << 32
                                             | (var7[4] & 255L) << 24
                                             | (var7[5] & 255L) << 16
                                             | (var7[6] & 255L) << 8
                                             | var7[7] & 255L;
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
                                          var63 = (var10[0] & 255L) << 56
                                             | (var10[1] & 255L) << 48
                                             | (var10[2] & 255L) << 40
                                             | (var10[3] & 255L) << 32
                                             | (var10[4] & 255L) << 24
                                             | (var10[5] & 255L) << 16
                                             | (var10[6] & 255L) << 8
                                             | var10[7] & 255L;
                                          var6[var48] = var63;
                                       } while (var2 < var5);

                                       ib = var6;
                                       return;
                                    }
                                    break;
                                 default:
                                    var40[var10001] = var63;
                                    if (var13 < var16) {
                                       continue label59;
                                    }

                                    var15 = "båPÏ\u001fJ\u001eö\u0003ú\u0088Ë\u0018zd\u0004";
                                    var16 = "båPÏ\u001fJ\u001eö\u0003ú\u0088Ë\u0018zd\u0004".length();
                                    var13 = 0;
                              }

                              int var47 = var13;
                              var13 += 8;
                              var18 = var15.substring(var47, var13).getBytes("ISO-8859-1");
                              var40 = var17;
                              var10001 = var14++;
                              var55 = (var18[0] & 255L) << 56
                                 | (var18[1] & 255L) << 48
                                 | (var18[2] & 255L) << 40
                                 | (var18[3] & 255L) << 32
                                 | (var18[4] & 255L) << 24
                                 | (var18[5] & 255L) << 16
                                 | (var18[6] & 255L) << 8
                                 | var18[7] & 255L;
                              var59 = 0;
                           }
                        }
                     }

                     var25 = var26.charAt(var36);
                     break;
                  default:
                     var29[var27++] = var51;
                     if ((var36 += var25) < var28) {
                        var25 = var26.charAt(var36);
                        continue label77;
                     }

                     var26 = ":<£6EW\u0092¦<Ø@\u0089\u0096\u0013áI\u0010Î\u009d³*\u008e\u0080â\u007f*G<Õ\u0000Os\u009f";
                     var28 = ":<£6EW\u0092¦<Ø@\u0089\u0096\u0013áI\u0010Î\u009d³*\u008e\u0080â\u007f*G<Õ\u0000Os\u009f".length();
                     var25 = 16;
                     var36 = -1;
               }

               var37 = var26.substring(++var36, var36 + var25);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var33) {
         throw new RuntimeException(var33);
      }
   }

   static {
      // add code
      // update new version
      allowNoSlow = new BooleanSetting("Allow-NoSlow", true);
      // update new version
      visualBlocking = new BooleanSetting("Visual-blocking", true);
      requireLeftClick = new BooleanSetting("Require-left-click", true);
      requireRightClick = new BooleanSetting("Require-right-click", false);
      onlyAutoClicker = new BooleanSetting("Only-AutoClicker", true);
      players = new BooleanSetting("Players", true);
      mobs = new BooleanSetting("Mobs", false);
      animals = new BooleanSetting("Animals", false);
      bosses = new BooleanSetting("Bosses", false);
      friends = new BooleanSetting("Friends", false);
      enemies = new BooleanSetting("Enemies", true);
      teammates = new BooleanSetting("Teammates", false);
      bots = new BooleanSetting("Bots", false);
   }
   static {
      // add code
      range = new NumberSetting("Range", 6.0F, 0.0F, 10.0F, 0.1F);
      fov = new NumberSetting("FOV", 180.0F, 0.0F, 360.0F, 1.0F);
      predictHurtResistTicks = new NumberSetting("Predict-hurt-resist-ticks", 10.0F, 1.0F, 20.0F, 1.0F);
      predictEarlyTicks = new NumberSetting("Predict-early-ticks", 3.0F, 0.0F, 8.0F, 1.0F);
      predictBlockTicks = new NumberSetting("Predict-block-ticks", 5.0F, 1.0F, 12.0F, 1.0F);
      predictRandomEarlyTicks = new NumberSetting("Predict-random-early-ticks", 1.0F, 0.0F, 4.0F, 1.0F);
      predictMaxPingCompTicks = new NumberSetting("Predict-max-ping-comp-ticks", 4.0F, 0.0F, 8.0F, 1.0F);
      spamBPS = new NumberSetting("Spam-BPS", 10.0F, 0.0F, 20.0F, 0.1F);
      spamBlockTime = new NumberSetting("Spam-block-time", 100.0F, 0.0F, 500.0F, 1.0F);
      lagAfterBlockTime = new NumberSetting("Lag-after-block-time", 100.0F, 0.0F, 1000.0F, 1.0F);
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "PREDICT", "SPAM");
      lagAfterBlockMode = new ModeSetting("Lag-after-block-mode", "NONE", "POST", "PRE");
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
