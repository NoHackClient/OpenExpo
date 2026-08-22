package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.enums.RotationMode;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.JumpResetBinder;
import Expo.event.events.KnockbackEvent;
import Expo.event.events.MoveInputEvent;
import Expo.event.events.PostUpdateEvent;
import Expo.event.events.WorldLoadEvent;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.EntityUtil;
import Expo.util.MathUtil;
import Expo.util.MoveUtil;
import Expo.util.RotationManager;
import Expo.util.Sneaky;
import Expo.util.TimerUtil;
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
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;











public class JumpReset extends Module implements EventSubscriber {
   public static PercentageSetting chance;
   public static BooleanSetting enemies;
   private static long a;
   private static Map m;
   private boolean J;
   private boolean d;
   public static BooleanSetting bots;
   public static BooleanSetting bosses;
   public static BooleanSetting animals;
   private static String[] u;
   public static BooleanSetting requireMoving;
   private static Object[] s;
   public static BooleanSetting teammates;
   private final TimerUtil t;
   public static BooleanSetting mobs;
   public static BooleanSetting reduce;
   public static HeaderSetting targetSettings;
   public static NumberSetting fov;
   public static NumberSetting range;
   public static BooleanSetting friends;
   private static Integer[] g;
   private static long[] c;
   private int o;
   public static BooleanSetting players;
   private static final byte[] KEY_OFFSETS = {
      28, 45, 41, 18, 59, 53, 5, 24, 39, 54, 15, 33, 7, 40, 60, 52,
      30, 55, 58, 11, 43, 16, 34, 20, 2, 49, 26, 56, 61, 63, 6, 23,
      37, 29, 44, 47, 8, 32, 51, 46, 14, 62, 50, 57, 17, 10, 19, 4,
      9, 0, 36, 27, 48, 3, 12, 21, 13, 42, 38, 31, 22, 1, 35, 25
   };
   private static long n;

   public final void x(long var1, EventBus var3) {
      JumpResetBinder.K(var3, this);
   }

   private double atan2(double var1, double var3) {
      double var5 = Math.toDegrees(Math.atan2(-var1, var3));
      return MathHelper.wrapAngleTo180_double(var5 - 180.0);
   }

   private static int b(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 2033;
      if (g[var3] == null) {
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
         long var5 = c[var3];
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
         Object[] var9 = (Object[])m.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               m.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/module/impl/combat/JumpReset", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         g[var3] = var15;
      }

      return g[var3];
   }

   private static boolean d(long var0) {
      var0 = a ^ var0;
      long var2 = var0 ^ 35521623559911L;
      long var4 = var0 ^ 33907090973970L;
      List var6 = EntityUtil.K(EntityUtil.F(range.L(), var2, fov.L()), players.c(), var4, mobs.c(), animals.c(), bosses.c(), friends.c(), enemies.c(), teammates.c(), bots.c());
      return !var6.isEmpty();
   }

   private static int a(long var0, long var2) {
      var0 ^= var2 << 48 | var2;
      int var4 = (int)(var0 >>> 46);
      if (u[var4] != null) {
         return var4;
      }

      Object var5 = s[var4];
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

      u[var4] = new String(var13);
      return var4;
   }

   static {
      a = 81398827166909L;
      zkm$clinit();
   }

   public static boolean C(long var0) {
      long var2 = var0 ^ 79556925850426L;
      return (!requireMoving.c() || MoveUtil.o())
         && MathUtil.h(0.0F, 99.0F) < chance.k()
         && !f.thePlayer.isPotionActive(Potion.jump)
         && d(var2)
         && f.thePlayer.isSprinting();
   }

   public void onWorldLoad(long var1, WorldLoadEvent var3) {
      this.q(0L);
   }

   private static Field a(Class var0, String var1, Class var2) {
      for (Field var6 : var0.getDeclaredFields()) {
         if (var6.getName().equals(var1) && var6.getType() == var2) {
            return var6;
         }
      }

      return null;
   }

   private static void a() {
      s[0] = "";
      u[0] = "Expo.event.events.MoveInputEvent";
      s[1] = float.class;
      u[1] = "java/lang/Float";
      s[2] = void.class;
      u[2] = "java/lang/Void";
      s[3] = boolean.class;
      u[3] = "java/lang/Boolean";
      s[4] = "";
      u[4] = "Expo.event.events.KnockbackEvent";
      s[5] = double.class;
      u[5] = "java/lang/Double";
      s[6] = "";
      u[6] = "Expo.module.impl.combat.JumpReset";
      s[7] = long.class;
      u[7] = "java/lang/Long";
      s[8] = "";
      u[8] = "Expo.util.RotationManager";
      s[9] = "";
      u[9] = "Expo.util.EntityUtil";
      s[10] = "";
      u[10] = "java.util.List";
      s[11] = "";
      u[11] = "Expo.enums.RotationMode";
      s[12] = "";
      u[12] = "Expo.event.binder.JumpResetBinder";
      s[13] = "";
      u[13] = "Expo.event.EventBus";
      s[14] = "";
      u[14] = "java.lang.Object";
      s[15] = "j\u0010\u001f\u001do\u0011<\u0004]\u001f\u000f-PU\u001c\u00181\u001a=\u001e]\u001bjw";
      s[16] = "\u0002\u001azx{\u0015M\u0011c)E6<Jl?{\u0015Q\u0001-< x\u0001\u0013-,/@P\u001f,}ECA\u000e/-(\b\u0000\rt@zBA\u001d+|;\u0019V\u0001\u0011";
      s[17] = "]\f\u0011r\u001c3\u000b\u0018\u00106\"\u0015m_\u000fvN<U\u000e\u0003w\u001fV\\\b\u000e2]&\t\u001f\u0000r\"j\u0011\u0004\u0014uG/Q\u001f\bJ\u001b2\u0003\u000bSzC(\n\u0019m";
      s[18] = "V\f`\r1;\u0013L{\u0011\u000e\u0015jMdHb4R\u001chI3^POa\u0005`&\u0005\n}L\u000e";
      s[19] = "1fuTu\u0010<dgG\u0012j\f/&Zw\u001bpdp_r+1t&Yx\u0013`x'\b\u0012\u0012hxs\u000b\"Jrqa5";
      s[20] = "~\u0017)=j~(\u0003k?\nCDR*84u)\u0019k;o\u0018";
      s[21] = "Q'\u001225i\u001en\u001cRm\u0015UjC7<i\u001e<F2\f,\b8El<t\u00121WR";
      s[22] = "";
      u[22] = "4tnq7mq4um\u0008q\u00083uw6eex4tm\u00085j4db0df55\u00083uw6eex4tm\u00081lfa68ivos\u0008";
      s[23] = "m~`yRc`|rj5\u001fP3m$Y2hba%\bXijaq\u000bh1phc5";
      s[24] = "";
      u[24] = "6cc06eyhza\u0008K\u000872ud24vibx\u000827oyfpgrs0\u00085j4db0df55\u000827oyfpgrs0\u000827oyfpgrs0\u000827oyfpgrs0\u000827oyfpgrs0\u000827oyfpgrs0\u000827oyfpgrs0\u000827oyfpgrs0\u000872ud24vibx\u0008";
      s[25] = "NY 1A#C[2\"&~s\u0019u/Li\u001f\u0019*nM\u0018JM!9\u0018(\u0012W(+&";
      s[26] = "$G$D\u000eRk\u000e*$p.#\t}UYVvLa\u001c7\u0017}Xs\u001a\u0007OgQa$";
      s[27] = "W]\tE\"'\u0001IKGB/m\u0018\n@|,\u0000SKC'A";
   }

   public String g(long var1) {
      return chance.k() + "%";
   }

   private static Field c(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = s[var4];
      if (!(var5 instanceof String)) {
         return (Field)var5;
      }

      String var6 = u[var4];
      int var7 = var6.indexOf(8);
      Class var8 = b(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      Class var11 = b(Long.parseLong(var6.substring(++var9), 36), 0L);
      Class var12 = var8;

      while (true) {
         Field var13 = a(var12, var10, var11);
         if (var13 != null) {
            s[var4] = var13;
            return var13;
         }

         Class[] var14 = var12.getInterfaces();
         if (var14 != null) {
            for (int var15 = 0; var15 < var14.length; var15++) {
               var13 = b(var14[var15], var10, var11);
               if (var13 != null) {
                  s[var4] = var13;
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
            var12 = b(1039626631182229L, 0L);
         }
      }
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

   public JumpReset(long var1) {
      super(((a ^ (var1)) ^ 70001787441225L));
      // add code
      this.declare("JumpReset", Category.Combat, "JumpReset and reduce knockback in combat");
      var1 = a ^ var1;
      this.t = new TimerUtil();
      this.d = false;
      this.J = false;
      this.o = 0;
   }

   private void q(long var1) {

      this.d = false;
      this.o = 0;
      if (this.J) {
         RotationManager.O(123115463851087L);
         this.J = false;
      }
   }

   private void q(double var1, long var3, double var5) {

      this.d = true;
      this.o = 3;
      if (reduce.c() && !RotationManager.X) {
         RotationManager.n(RotationMode.SILENT);
         RotationManager.I((float)this.atan2(var1, var5),0L);
         this.J = true;
         this.t.W();
      }
   }

   private static Object a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, Object[] var4) throws Throwable {
      int var5 = var4.length - 2;
      long var6 = (Long)var4[var5];
      long var9 = (Long)var4[++var5];
      MethodHandle var8 = a(var0, var1, var2, var3, var6, var9);
      var1.setTarget(MethodHandles.explicitCastArguments(var8, var3));
      return (Object)var8.asSpreader(Object[].class, var4.length).invoke(var4);
   }

   public void A(long var1) {
      this.q(0L);
   }

   public void onPostUpdate(long var1, PostUpdateEvent var3) {

      if (this.o > 0) {
         this.o--;
      } else {
         if (this.t.Q(n) && this.J) {
            if (!ModuleManager.I.o()) {
               RotationManager.O(123115463851087L);
            }

            this.J = false;
         }
      }
   }

   private static Method d(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = s[var4];
      if (!(var5 instanceof String)) {
         return (Method)var5;
      }

      String var6 = u[var4];
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
            s[var4] = var26;
            return var26;
         }

         if (var23.getName().equals("java.lang.Object")) {
            break;
         }

         if ((var23 = var23.getSuperclass()) == null) {
            var23 = b(1039626631182229L, 0L);
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
                  s[var4] = var19;
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
            var23 = b(1039626631182229L, 0L);
         }
      }
   }

   public void onKnockback(KnockbackEvent var1, long var2) {



      if (var1.f() > 0.0 && C(122264478076639L)) {
         this.q(
            var1.S(),
            28209960205980L,
            var1.R()
         );
      }
   }

   private static MethodHandle a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, long var4, long var6) {
      char var8 = var2.charAt(0);
      MethodHandle var9 = null;
      Field var10 = null;
      Method var11 = null;

      try {
         if (var8 != 233 && var8 != 'c' && var8 != 255 && var8 != 'Q') {
            var11 = d(var4, var6);
            Expo.internal.restore.ExpoHandleProbe.log("Expo/module/impl/combat/JumpReset.java", var8, var4, var6, var11); // add code
            Class var17 = var11.getDeclaringClass();
            String var19 = var11.getName();
            MethodType var20 = MethodType.methodType(var11.getReturnType(), var11.getParameterTypes());
            if (var8 == 't') {
               var9 = var0.findVirtual(var17, var19, var20);
            } else if (var8 == 199) {
               var9 = var0.findStatic(var17, var19, var20);
            } else {
               var9 = var0.findSpecial(var17, var19, var20, var17);
            }
         } else {
            var10 = c(var4, var6);
            Expo.internal.restore.ExpoHandleProbe.log("Expo/module/impl/combat/JumpReset.java", var8, var4, var6, var10); // add code
            Class var12 = var10.getDeclaringClass();
            String var18 = var10.getName();
            Class var14 = var10.getType();
            if (var8 == 233) {
               var9 = var0.findGetter(var12, var18, var14);
            } else if (var8 == 'c') {
               var9 = var0.findSetter(var12, var18, var14);
            } else if (var8 == 255) {
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

   public void onMoveInput(long var1, MoveInputEvent var3) {
      if (this.d) {
         var3.i(1.0F);
         var3.O(true);
         this.d = false;
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

   private static CallSite a(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments(MethodHandles.lookup().findStatic(JumpReset.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/Object;", JumpReset.class.getClassLoader())).asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1, var2), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("Expo/module/impl/combat/JumpReset" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static Class b(long var0, long var2) {
      Class var5 = null;
      int var4 = a(var0, var2);
      Object var6 = s[var4];
      try {
         if (var6 instanceof String) {
            var5 = Class.forName(u[var4]);
            s[var4] = var5;
            return var5;
         }
      } catch (Exception var8) {
         throw new RuntimeException(var8.toString());
      }

      return (Class)var6;
   }

   private static void zkm$clinit() {
      try {
         s = new Object[28];
         u = new String[28];
         a();
         m = new HashMap(13);
         long var5 = a ^ 95034870281618L;
         Cipher var7;
         byte[] var10003 = new byte[]{(byte)(var5 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var8 = 1; var8 < 8; var8++) {
            var10003[var8] = (byte)(var5 << var8 * 8 >>> 56);
         }

         (var7 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var13 = new long[5];
         int var10 = 0;
         String var11 = "1\u001d³Ww« ©\u0098`ÈÓñe«¥1\u001d?ðU\u0004ä\u009f";
         int var12 = "1\u001d³Ww« ©\u0098`ÈÓñe«¥1\u001d?ðU\u0004ä\u009f".length();
         int var9 = 0;

         label39:
         while (true) {
            int var10001 = var9;
            var9 += 8;
            byte[] var14 = var11.substring(var10001, var9).getBytes("ISO-8859-1");
            long[] var20 = var13;
            var10001 = var10++;
            long var25 = (var14[0] & 255L) << 56
               | (var14[1] & 255L) << 48
               | (var14[2] & 255L) << 40
               | (var14[3] & 255L) << 32
               | (var14[4] & 255L) << 24
               | (var14[5] & 255L) << 16
               | (var14[6] & 255L) << 8
               | var14[7] & 255L;
            int var29 = -1;

            while (true) {
               long var15 = var25;
               byte[] var17 = var7.doFinal(
                  new byte[]{
                     (byte)(var15 >>> 56),
                     (byte)(var15 >>> 48),
                     (byte)(var15 >>> 40),
                     (byte)(var15 >>> 32),
                     (byte)(var15 >>> 24),
                     (byte)(var15 >>> 16),
                     (byte)(var15 >>> 8),
                     (byte)var15
                  }
               );
               long var32 = (var17[0] & 255L) << 56
                  | (var17[1] & 255L) << 48
                  | (var17[2] & 255L) << 40
                  | (var17[3] & 255L) << 32
                  | (var17[4] & 255L) << 24
                  | (var17[5] & 255L) << 16
                  | (var17[6] & 255L) << 8
                  | var17[7] & 255L;
               switch (var29) {
                  case 0:
                     var20[var10001] = var32;
                     if (var9 >= var12) {
                        c = var13;
                        g = new Integer[5];
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var5 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var5 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));

                        byte[] var4 = var0.doFinal(
                           new byte[]{
                              (byte)171L,
                              (byte)43925L,
                              (byte)11244903L,
                              (byte)2878695332L,
                              (byte)736946004995L,
                              (byte)188658177278913L,
                              (byte)48296493383401734L,
                              (byte)-6082841767558707463L
                           }
                        );
                        long var28 = (var4[0] & 255L) << 56
                           | (var4[1] & 255L) << 48
                           | (var4[2] & 255L) << 40
                           | (var4[3] & 255L) << 32
                           | (var4[4] & 255L) << 24
                           | (var4[5] & 255L) << 16
                           | (var4[6] & 255L) << 8
                           | var4[7] & 255L;
                        n = var28;
                        return;
                     }
                     break;
                  default:
                     var20[var10001] = var32;
                     if (var9 < var12) {
                        continue label39;
                     }

                     var11 = "Ôä\t£»,ËWÅ[sa\u009b\u0087ªý";
                     var12 = "Ôä\t£»,ËWÅ[sa\u009b\u0087ªý".length();
                     var9 = 0;
               }

               int var23 = var9;
               var9 += 8;
               var14 = var11.substring(var23, var9).getBytes("ISO-8859-1");
               var20 = var13;
               var10001 = var10++;
               var25 = (var14[0] & 255L) << 56
                  | (var14[1] & 255L) << 48
                  | (var14[2] & 255L) << 40
                  | (var14[3] & 255L) << 32
                  | (var14[4] & 255L) << 24
                  | (var14[5] & 255L) << 16
                  | (var14[6] & 255L) << 8
                  | var14[7] & 255L;
               var29 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var18) {
         throw new RuntimeException(var18);
      }
   }

   static {
      // add code
      chance = new PercentageSetting("Chance", 100);
   }
   static {
      // add code
      requireMoving = new BooleanSetting("Require-moving", true);
      reduce = new BooleanSetting("Reduce", false);
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
      fov = new NumberSetting("FOV", 180.0F, 0.0F, 360.0F, 1.0F);
      range = new NumberSetting("Range", 5.0F, 0.0F, 10.0F, 0.1F);
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
