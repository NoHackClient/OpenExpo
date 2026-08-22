package Expo;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ExpoClientBinder;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.PreMouseInputEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.SetKeyBindStateEvent;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.Modules;
import Expo.module.impl.configuration.ClickGUI;
import Expo.module.impl.configuration.VisualSpoof;
import Expo.module.impl.visual.Freelook;
import Expo.module.impl.world.BedNuker;
import Expo.ui.swing.ConfigManagerWindow;
import Expo.util.ClientUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import Expo.util.Sneaky;
import Expo.util.TimerUtil;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class ExpoClient implements EventSubscriber {
   private static Map e;
   private static Object[] l;
   private static String[] m;
   private final TimerUtil B;
   private static String[] d;
   private static Map h;
   private final Minecraft c;
   private static Long[] j;
   public static Map<Integer, String> H;
   private boolean s;
   private static long[] f;
   private static Integer[] g;
   public static Set<BlockPos> G;
   private static String[] b;
   private boolean N;
   public static ConfigManagerWindow T;
   private static long a;
   private static Map k;
   private final ScheduledExecutorService U;
   private static long[] i;
   public static String I;
   private static final byte[] KEY_OFFSETS = {
      39, 57, 59, 32, 29, 12, 48, 9, 40, 35, 20, 47, 44, 1, 25, 42,
      11, 5, 28, 36, 41, 27, 14, 60, 2, 45, 52, 31, 23, 38, 62, 33,
      24, 17, 15, 0, 37, 8, 46, 53, 61, 21, 30, 6, 16, 49, 51, 3,
      55, 18, 50, 34, 63, 22, 10, 58, 56, 26, 54, 19, 4, 13, 43, 7
   };
   public static EventBus w;

   public void onEntityJoinWorld(long var1, EntityJoinWorldEvent var3) {
      if (var3.H instanceof EntityPlayerSP) {
         BedNuker.D.clear();
         BedNuker.B = ((0 & 1) != 0);
      }
   }

   public void onReceivePacket(ReceivePacketEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (var1.d instanceof S02PacketChat) {
         String var4 = ((S02PacketChat)var1.d).getChatComponent().getFormattedText();
         if (var4.contains("§e§lProtect your bed and destroy the enemy bed") || var4.contains("§e§lDestroy the enemy bed and then eliminate them")) {
            BedNuker.B = ((1 & 1) != 0);
         }
      } else if (var1.d instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook var6 = (S08PacketPlayerPosLook)var1.d;
         if (BedNuker.B) {
            BedNuker.B = ((0 & 1) != 0);
            this.U.schedule(() -> {
               int var4x = MathHelper.floor_double(var6.getX());
               int var5x = MathHelper.floor_double(var6.getY());
               int var6x = MathHelper.floor_double(var6.getZ());

               for (int var7 = var4x - 35; var7 <= var4x + 35; var7++) {
                  for (int var8 = var5x - 15; var8 <= var5x + 15; var8++) {
                     for (int var9 = var6x - 35; var9 <= var6x + 35; var9++) {
                        BlockPos var10 = new BlockPos(var7, var8, var9);
                        if (this.c.theWorld.getBlockState(var10).getBlock() == Blocks.bed) {
                           BedNuker.D.add(var10);
                        }
                     }
                  }
               }
            }, 3000L, TimeUnit.MILLISECONDS);
         }
      }
   }

   public void onPreUpdate(long var1, PreUpdateEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      List var8 = ModuleManager.S;
      int var9 = 0;

      for (int var10 = var8.size(); var9 < var10; var9++) {
         Module var11 = (Module)var8.get(var9);
         if (!var11.b().equalsIgnoreCase("Timer")) {
            if (var11.l()) {
               var11.h(122596698849654L);
            } else if (var11.K()) {
               var11.Z(110240354022990L);
            }

            if (!var11.o()) {
               var11.d();
            }
         }
      }
   }

   private static long c(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 32169;
      if (j[var3] == null) {
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
         long var5 = i[var3];
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
            throw new RuntimeException("Expo/ExpoClient", var14);
         }

         long var15 = (var10[0] & 255L) << 56
            | (var10[1] & 255L) << 48
            | (var10[2] & 255L) << 40
            | (var10[3] & 255L) << 32
            | (var10[4] & 255L) << 24
            | (var10[5] & 255L) << 16
            | (var10[6] & 255L) << 8
            | var10[7] & 255L;
         j[var3] = var15;
      }

      return j[var3];
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
      Object var5 = l[var4];
      if (!(var5 instanceof String)) {
         return (Field)var5;
      }

      String var6 = m[var4];
      int var7 = var6.indexOf(8);
      Class var8 = b(Long.parseLong(var6.substring(0, var7), 36), 0L);
      int var9 = var6.indexOf(8, ++var7);
      String var10 = var6.substring(var7, var9);
      Class var11 = b(Long.parseLong(var6.substring(++var9), 36), 0L);
      Class var12 = var8;

      while (true) {
         Field var13 = a(var12, var10, var11);
         if (var13 != null) {
            l[var4] = var13;
            return var13;
         }

         Class[] var14 = var12.getInterfaces();
         if (var14 != null) {
            for (int var15 = 0; var15 < var14.length; var15++) {
               var13 = b(var14[var15], var10, var11);
               if (var13 != null) {
                  l[var4] = var13;
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
            var12 = b(525810144067084L, 0L);
         }
      }
   }

   private static CallSite a(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments(MethodHandles.lookup().findStatic(ExpoClient.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;Ljava/lang/invoke/MethodType;[Ljava/lang/Object;)Ljava/lang/Object;", ExpoClient.class.getClassLoader())).asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1, var2), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("Expo/ExpoClient" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   public void onSetKeyBindState(SetKeyBindStateEvent var1, long var2) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (ClientUtil.I()) {
         List var9 = ModuleManager.S;
         int var10 = 0;

         for (int var11 = var9.size(); var10 < var11; var10++) {
            Module var12 = (Module)var9.get(var10);
            if (!var12.b().equalsIgnoreCase("Timer")
               && var12.h() != 0
               && KeyBindUtil.d(var12.h(), var1.R, 55909487137472L)
               && (!var12.b().equalsIgnoreCase("FREELOOK") || Freelook.mode.R("TOGGLE"))) {
               var12.u((short)0, 139350548161835L);
            }
         }

         for (Entry var18 : H.entrySet()) {
            if (KeyBindUtil.d((Integer)var18.getKey(), var1.R, 55909487137472L)) {
               for (String var15 : ((String)var18.getValue()).split("\\n")) {
                  this.c.thePlayer.sendChatMessage(var15);
               }
            }
         }
      }
   }

   public ExpoClient(int var1, char var2, int var3) {
      this.U = Executors.newScheduledThreadPool(1);
      this.c = MinecraftRef.c((byte)0, 0L);
      this.B = new TimerUtil();
      this.N = false;
      this.s = false;
   }

   private static void a() {
      l[0] = "";
      m[0] = "Expo.event.binder.ExpoClientBinder";
      l[1] = long.class;
      m[1] = "java/lang/Long";
      l[2] = "";
      m[2] = "Expo.event.EventBus";
      l[3] = "";
      m[3] = "Expo.ExpoClient";
      l[4] = void.class;
      m[4] = "java/lang/Void";
      l[5] = "";
      m[5] = "java.util.List";
      l[6] = int.class;
      m[6] = "java/lang/Integer";
      l[7] = "";
      m[7] = "java.lang.Object";
      l[8] = "";
      m[8] = "Expo.module.impl.configuration.VisualSpoof";
      l[9] = boolean.class;
      m[9] = "java/lang/Boolean";
      l[10] = "";
      m[10] = "Expo.module.Modules";
      l[11] = "";
      m[11] = "java.lang.Class";
      l[12] = "";
      m[12] = "Expo.module.Module";
      l[13] = "";
      m[13] = "Expo.setting.settings.DisableRenderVisualSetting";
      l[14] = "";
      m[14] = "Expo.util.KeyBindUtil";
      l[15] = "";
      m[15] = "Expo.setting.settings.ModeSetting";
      l[16] = "";
      m[16] = "java.lang.String";
      l[17] = "";
      m[17] = "Expo.util.ClientUtil";
      l[18] = "";
      m[18] = "java.util.Set";
      l[19] = "";
      m[19] = "Expo.module.impl.configuration.ClickGUI";
      l[20] = char.class;
      m[20] = "java/lang/Character";
      l[21] = short.class;
      m[21] = "java/lang/Short";
      l[22] = "";
      m[22] = "Expo.util.packet.PacketManager";
      l[23] = "";
      m[23] = "Expo.setting.settings.BooleanSetting";
      l[24] = "XUJ6&\u0017F]PyE\u0003B\u0010y9|\u0010K";
      l[25] = "";
      m[25] = "java.lang.Integer";
      l[26] = "~E$T!J`M>\u001bFKqV3A`M";
      l[27] = "";
      m[27] = "java.util.Map";
      l[28] = "";
      m[28] = "net.minecraft.client.entity.EntityPlayerSP";
      l[29] = "vn\u000f\u0011?q\u0003N\u0004\u001e.>~V\u0017\u0019'w\u0016";
      l[30] = "";
      m[30] = "Expo.event.events.PreMouseInputEvent";
      l[31] = "KM\u001da\u0001Z\u0015EZu:D\u001dTe%\\HHH\u0004{TZ\u001d(X'VW\rF\u001fwUPp";
      l[32] = "m;B\n\f-=7L]l)P~JS\u000f!h(\bW\u001cOn \u001fJ\u0012\u007f!(\u0015Ml";
      l[33] = "<kz]v/7f-V\u000b)>fP\u0000zFn$\u007f\u0011v()t|\u0016\u000b";
      l[34] = "kC5.\u0005p0G%(lNT\u001d$\u007fS`2F:%\u0005\fdZ&5]rjD\"6l";
      l[35] = "Q58|Ec_+<\u007ft`a !g\u0014m[|<7\b\u001dXu&m\u001a%\u000e7\"~t'\u001f. 5\u0012v\u00136=\u000e";
      l[36] = "U2c|l\u0012\u000b:$hW\b\n:r~WY\u00104y?1\b\u001c,d\u0004";
      l[37] = "\u0018.N8,$U$Z!K#) Q(6#\u0019oY\"1]\u0010&R&%eFdV5KgW}T~-6[eIE";
      l[38] = "_\u0010\u0015\u001ax(LE\u0011\u0018\u0011\u00116\u0014\u0013\u001e)/WJ\u001b\f|O\u000f\u0010\u0011\u001e\u007fwYR\u0015\r\u0011qQE\b\u0003!>YO\u000f}";
      l[39] = "gsoQ*\nimkR\u001b\u0010W5rNf\ngzzDat";
      l[40] = "Wu\b\r\u007f\u0004Yk\f\u000eN>gn\u000f\u0003?\u0002\u001a0\b\u0016sz^5\u0016\u001c B\bw\u0012\u000fN@\u0019n\u0010D(\u0011\u0015v\r\u007f";
      l[41] = "^}A\u0019\u0012M\t\u007fBXq,7h_\u0013\u001f\bVx[\u0010\u000bv\teTT\u000fFFm^Sq";
      l[42] = "\u0019q?svy\u0017o;pG`)7\"l:y\u0019x*f=\u0007";
      l[43] = "\u0002j\u0000\u000fej\ft\u0004\fTt2.\u001c\u001eltSp\u0014\f9\u0014";
      l[44] = "H\rwmW\u0005F\u0013snf#xKjr\u001b\u0005H\u0004bx\u001c{";
      l[45] = "#MG\u000fA\u0016-SC\fp!\u0013\f\u0005\u0019\u0013\u0006+ZG\u001d\u0000h)K^\u001fK\u000exGF\u0002p";
      l[46] = "\u000b\u0000[R\u0014\u000f\u001a\u0014HPi7a@CG\u0014\bQ\u000fKM\u0013v";
      l[47] = "\u0005\u007fzF;][w=R\u0000W_ho><J]\"b_bBOw\u0002";
      l[48] = "XX\u001e_C!TGUW\"0GP\u00051\u001dg\u0003\bS1N^PPT\b\\?@TW\u001c\"dDU\f]D5HM\u0011f";
      l[49] = "\u0002[^\u0017\u001f\u0014R\u0005\u0001\btE\n\u001dw\u001b\u0004YcXV\u001b\t[S\u0017^\u0011\u000e%";
      l[50] = "%^\u0011\u0003s7+@\u0015\u0000B(\u0015\u001fS\u0015!'-I\u0011\u00112I/X\b\u0013y/~T\u0010\u000eB";
      l[51] = "\u007fZ\u0013I$HqD\u0017J\u0015wO\u001bQ_vXwM\u0013[e6qE\u0004Fk\u0006>M\u000eA\u0015\f1A\u000b\u0000s]=Y\u0016;";
      l[52] = "s\u0004\u0004DE8%V\u0011Fz8cQ\u0002R\u0006>e<\u0019\u0006\u00158t\rIXJ'\u001f";
      l[53] = "\u0011T>sg \u0002\u0001:q\u000e+xP8w6'\u0019\u000e0ecGD\n=,n&\u001a\u0002/y\u000e~@\b=z6(\u0002\f.\u00140 \u0015\u0011 $\u007f(\u001f\u0016^";
      l[54] = "D\u001e\u0015\u00123\b\u001a\u000e\u000e\fJ\u0001}_Z\u00106\u0007\u0013\u0018\n\u00131zDZ\u0000\u001f$B\u0012\u0018\u0004\fJ@\u0003\u0001\u0006G,\u0011\u000f\u0019\u001b|";
      l[55] = "'n-\\D\u0006.s8ffhx5 \u0005CP.w$\u0016-Q=|*\u001f_\u000f-g4f\u0017T3v/\u000f\\\u0012|5D\\S\u000b#6\"\r_\u0013>\r";
      l[56] = "$%Z9E#*;^:t\u0014\u0014cG&\t#$,O,\u000e].#C)O;\u007f/[4t";
      l[57] = "M{^\u001bYP\u001b)K\u0019fZE.K\u001ef\u000b_ @_\u0000ZS8]d";
      l[58] = "7\u001eH\b\u001aNm\u0015\u0006PyE1\u0010'\u0001\u001dY:lE\u000e\u001a\u001c7\r\u001b\u0006\bIW";
      l[59] = "@',HxZN9(KIvpfn^*JH0,Z9$J!5XrB\u001b--EI";
      l[60] = "}UC\u001e'8vX\u0014\u0015Z>\u007fXtG>,\u007f$\u0017\u00106-oJP@5*\u0012";
      l[61] = "ME<,r\"]A?8\f.^^o&w\u0002HEa8a\bNXkBf%\u001dMx#v!\u001eY\u0006|k.Z]63c$]#";
      l[62] = "\u0007k\u000e)X=\u0019j\u000e4%\u0016k1W4\u001d1\no_&HQWkRoE0\tc@:%=\u0019sClU0\f3QW\u001f/\bo\n1N#\u0010r1";
      l[63] = "\"J!xb\u0003<K!e\u001f\u001fN\u0010xe'\u000f/Npwro#OwfoU\u007fR'z\u001f\u0003<Rl=o\u000e)\u0012~\u0006!\b#Q`6n\u0000)V\u001e";
      l[64] = "tMyjj\u0003p\u0014rp\u0013w\u001a\u0014%wnL*[-}i2 T!x(TqX9e\u0013";
      l[65] = "6lW]whf2\bB\u001c?:!D<!g;-ERf78*8";
      l[66] = "\u0014\"kZG\u001f\u001a<oYv\"$dvE\u000b\u001f\u0014+~O\fa";
      l[67] = "C{\rnWxOb\\16qDwFpmq^\u000bP9Jc\u001dj\u0006k_a\"";
      l[68] = "\u0013GJbf\u0015\u001dYNaW\r#\u0001W}*\u0015\u0013N_w-k\u0019ASrl\rHMKoW";
      l[69] = "\u000e\u000b:\u0004\u0019\u000e\u0000\u0015>\u0007((>Jx\u0012K\u001e\u0006\u001c:\u0016Xp\u0004\r#\u0014\u0013\u0016U\u0001;\t(";
      l[70] = "s\u0000F0\u000f\tuWL1~\u001b\u001c\u0003\u0011a\u0003\u000e,L\u0019k\u0004p";
      l[71] = "Z!;QJkT??R{Xjg&N\u0006kZ(.D\u0001\u0015P'\"A@s\u0001+:\\{";
      l[72] = "\u0019\u0017\u007fof\u001a\u0017\t{lW\u0004)V=y4\n\u0011\u0000\u007f}'d\u0013\u0011f\u007fl\u0002B\u001d~bW";
      l[73] = "\b\u0010\u007f l\u0019\u0006\u000e{#]\u000b8R{1?\\^\u0003w)\"g";
      l[74] = "N\u000b(\rnN@\u0015,\u000e_Z~\u00194E1N\u001f\t0F%0";
      l[75] = "\\zu\u0010\t)\u0002jn\u000ep\u0011e;:\u0012\f&\u000b|j\u0011\u000b[_xg\u001cK=\u000et\u007f\u0001p";
      l[76] = "C+\u007fezkS/|q\u0004qQ)$w\u0004`Ow+uepKt?\u000bg7K&tmt;\u0010=E";
   }

   public void onPostTick(PostTickEvent var1, long var2) throws Throwable {
      int var16 = 22243;

      int var21 = 12652;

      if (!ClientUtil.I()) {
         BedNuker.B = ((0 & 1) != 0);
         PacketManager.M(false);
         PacketManager.u.clear();
         PacketManager.v.clear();
         PacketManager.a.clear();
         I = null;
         List var32 = ModuleManager.S;
         int var33 = 0;

         for (int var34 = var32.size(); var33 < var34; var33++) {
            Module var35 = (Module)var32.get(var33);
            if (!var35.b().equalsIgnoreCase("Timer") && var35.P()) {
               w.B(var35);
               var35.A(false);
            }
         }

         this.s = ((0 & 1) != 0);
      } else {
         List var26 = ModuleManager.S;
         int var27 = 0;

         for (int var28 = var26.size(); var27 < var28; var27++) {
            Module var29 = (Module)var26.get(var27);
            if (!var29.b().equalsIgnoreCase("Timer")) {
               if (var29.l()) {
                  var29.i(17998201765264L);
                  var29.n(false);
               } else if (var29.K()) {
                  var29.A(94287625739397L);
                  var29.E(false);
               }

               if (var29.o()) {
                  if (!var29.P()) {
                     w.s(var29, 25046058167973L);
                     var29.A(true);
                  }
               } else {
                  if (var29.P()) {
                     w.B(var29);
                     var29.A(false);
                  }

                  var29.P(11128156246666L);
               }
            }
         }

         if (this.c.currentScreen == null) {
            if (ClickGUI.x(17550, (short)6998, (char)var16)) {
               try {
                  ClickGUI.O(2169, 8663, (char)var21);
               } catch (NullPointerException var30) {
               }
            }

            if (Freelook.mode.R("HOLD") && Modules.J(Freelook.class).h() != 0) {
               Modules.J(Freelook.class).I(20724619369162L, KeyBindUtil.V(Modules.J(Freelook.class).h(), 64165991731362L));
            }
         }

         if (VisualSpoof.n(118536638251483L) && !this.s) {
            VisualSpoof.t.v(!VisualSpoof.t.c(), 64895789836511L);
         }

         this.s = VisualSpoof.n(118536638251483L);
      }
   }

   public void d(long var1, PreUpdateEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (I != null && this.B.L(300L, true)) {
         if (this.N) {
            this.N = ((0 & 1) != 0);
            this.c.thePlayer.sendChatMessage("/p " + I);
         } else {
            this.N = ((1 & 1) != 0);
            this.c.thePlayer.sendChatMessage("/p leave");
         }
      }
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

   public void onPreMouseInput(long var1, PreMouseInputEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      List var6 = ModuleManager.S;
      int var7 = 0;

      for (int var8 = var6.size(); var7 < var8; var7++) {
         Module var9 = (Module)var6.get(var7);
         if (!var9.b().equalsIgnoreCase("Timer") && !var9.o()) {
            var9.L(var3, 85029904657643L);
         }
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

   private static int b(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 12893;
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
         long var5 = f[var3];
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
         Object[] var9 = (Object[])h.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               h.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/ExpoClient", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         g[var3] = var15;
      }

      return g[var3];
   }

   private static String a(int var0, long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 28377;
      if (d[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().getId();
            var4 = (Object[])e.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               e.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("Expo/ExpoClient", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = b[var5].getBytes("ISO-8859-1");
         d[var5] = a(((Cipher)var4[0]).doFinal(var9));
      }

      return d[var5];
   }

   private static int a(long var0, long var2) {
      var0 ^= var2 << 48 | var2;
      int var4 = (int)(var0 >>> 46);
      if (m[var4] != null) {
         return var4;
      }

      Object var5 = l[var4];
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

      m[var4] = new String(var13);
      return var4;
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

   private static Class b(long var0, long var2) {
      Class var5 = null;
      int var4 = a(var0, var2);
      Object var6 = l[var4];
      try {
         if (var6 instanceof String) {
            var5 = Class.forName(Expo.internal.restore.ExpoNameMap.map(m[var4]));
            l[var4] = var5;
            return var5;
         }
      } catch (Exception var8) {
         throw new RuntimeException(var8.toString());
      }

      return (Class)var6;
   }

   public final void x(long var1, EventBus var3) {
      ExpoClientBinder.C(var3, this);
   }

   static {
      a = 55479544243313L;
      zkm$clinit();
      H = new LinkedHashMap<>();
      G = new CopyOnWriteArraySet<>();
      I = null;
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

   private static MethodHandle a(Lookup var0, MutableCallSite var1, String var2, MethodType var3, long var4, long var6) {
      char var8 = var2.charAt(0);
      MethodHandle var9 = null;
      Field var10 = null;
      Method var11 = null;

      try {
         if (var8 != 204 && var8 != 200 && var8 != 'K' && var8 != 219) {
            var11 = d(var4, var6);
            Class var17 = var11.getDeclaringClass();
            String var19 = var11.getName();
            MethodType var20 = MethodType.methodType(var11.getReturnType(), var11.getParameterTypes());
            if (var8 == 244) {
               var9 = var0.findVirtual(var17, var19, var20);
            } else if (var8 == 254) {
               var9 = var0.findStatic(var17, var19, var20);
            } else {
               var9 = var0.findSpecial(var17, var19, var20, var17);
            }
         } else {
            var10 = c(var4, var6);
            Class var12 = var10.getDeclaringClass();
            String var18 = var10.getName();
            Class var14 = var10.getType();
            if (var8 == 204) {
               var9 = var0.findGetter(var12, var18, var14);
            } else if (var8 == 200) {
               var9 = var0.findSetter(var12, var18, var14);
            } else if (var8 == 'K') {
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

   private static Method d(long var0, long var2) {
      int var4 = a(var0, var2);
      Object var5 = l[var4];
      if (!(var5 instanceof String)) {
         return (Method)var5;
      }

      String var6 = m[var4];
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
            l[var4] = var26;
            return var26;
         }

         if (var23.getName().equals("java.lang.Object")) {
            break;
         }

         if ((var23 = var23.getSuperclass()) == null) {
            var23 = b(525810144067084L, 0L);
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
                  l[var4] = var19;
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
            var23 = b(525810144067084L, 0L);
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

   private static boolean zkm$unresolved$0$monomorphic_exactly_one_target_not_statically_decidable_candidates_Expo_iD_l_OR_Expo_iD_K_y_slots_39_49_66_70(Object var0, long var3) {
       try {MethodType var5 = MethodType.fromMethodDescriptorString("(Ljava/lang/Object;JJ)Z", ExpoClient.class.getClassLoader());
      return (boolean)MethodHandles.explicitCastArguments(a(MethodHandles.lookup(), null, "ô", var5, 2266045794134596627L, 9901644652386L), var5)
         .invoke((Object)var0, (long)2266045794134596627L, (long)9901644652386L);
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   private static void zkm$clinit() {
      try {
         l = new Object[77];
         m = new String[77];
         a();
         e = new HashMap(13);
         long var22 = a ^ 20790936441576L;
         Cipher var24;
         byte[] var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var25 = 1; var25 < 8; var25++) {
            var10003[var25] = (byte)(var22 << var25 * 8 >>> 56);
         }

         (var24 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var31 = new String[10];
         int var29 = 0;
         String var28 = " !ðü$ìâ´\u0097¿//ú\u0089sK ßðÀ2\u0019®Ø\u0006\u0080\rn0ð &Hý$\u0097\r\u0019û\u009d\u001f°pÁãÍh¯å\u00107\u0003mÀÎ^³\u001bê`Ö\u000e\u001b\b®óPMv\u008d°&:Ð¤\u0087¿}Ï.Åü/Ý3/\n6MË\u0015¥Ï\fð®=a\u008fËª¤Z^.QDÙá!\u0017°\u0016\u0080Ð ûÐX\nq¼hiû\u0003©Þ/\non\u008c\u0011?®+#C÷D\u0015\u008bW®\u0087?\u0010\u0093§/òì¾¹\u00045â@5É\u0012õ¼\u0010æà8%\u009bãU,h¬%\u0083\u0099Z\u0088¥\u0010ñ¬µ\u0085ö\u008b\u000bðç_º\u0081Û³\u008f8X/Æu\u0087#5\u0000h7dçö°ÈÞÄüeé\u0018¨¹Ï\u0083ÍPò)\b\u0016&\u001fé1ÂàÛ¢/²;G\u0088;\u0011\u0000\u0099 ä\b3ûwà×¾#æ\u0016\u008b\u001a3\u0015!.\\@Å×Þ$é³±\u0094xE\u000bUjÒ\u001a\u000e¶q2KÏ";
         int var30 = " !ðü$ìâ´\u0097¿//ú\u0089sK ßðÀ2\u0019®Ø\u0006\u0080\rn0ð &Hý$\u0097\r\u0019û\u009d\u001f°pÁãÍh¯å\u00107\u0003mÀÎ^³\u001bê`Ö\u000e\u001b\b®óPMv\u008d°&:Ð¤\u0087¿}Ï.Åü/Ý3/\n6MË\u0015¥Ï\fð®=a\u008fËª¤Z^.QDÙá!\u0017°\u0016\u0080Ð ûÐX\nq¼hiû\u0003©Þ/\non\u008c\u0011?®+#C÷D\u0015\u008bW®\u0087?\u0010\u0093§/òì¾¹\u00045â@5É\u0012õ¼\u0010æà8%\u009bãU,h¬%\u0083\u0099Z\u0088¥\u0010ñ¬µ\u0085ö\u008b\u000bðç_º\u0081Û³\u008f8X/Æu\u0087#5\u0000h7dçö°ÈÞÄüeé\u0018¨¹Ï\u0083ÍPò)\b\u0016&\u001fé1ÂàÛ¢/²;G\u0088;\u0011\u0000\u0099 ä\b3ûwà×¾#æ\u0016\u008b\u001a3\u0015!.\\@Å×Þ$é³±\u0094xE\u000bUjÒ\u001a\u000e¶q2KÏ"
            .length();
         char var27 = 16;
         int var36 = -1;

         label77:
         while (true) {
            String var37 = var28.substring(++var36, var36 + var27);
            int var10001 = -1;

            while (true) {
               byte[] var32 = var24.doFinal(var37.getBytes("ISO-8859-1"));
               String var51 = a(var32).intern();
               switch (var10001) {
                  case 0:
                     var31[var29++] = var51;
                     if ((var36 += var27) >= var30) {
                        b = var31;
                        d = new String[10];
                        h = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(var22 << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var17 = new long[8];
                        int var14 = 0;
                        String var15 = "ÜõÍ\u009bzg°FAÖÀ×¡Üóõ\u008a\u001aÐ¥\u008bÛ<HÓÏ¹>ÊÈ\u00adµ\u0002Ô\u0001ÏÄ\bu\u0005+\u001bý\u0092¬\u001b_û";
                        int var16 = "ÜõÍ\u009bzg°FAÖÀ×¡Üóõ\u008a\u001aÐ¥\u008bÛ<HÓÏ¹>ÊÈ\u00adµ\u0002Ô\u0001ÏÄ\bu\u0005+\u001bý\u0092¬\u001b_û".length();
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
                           byte var59 = -1;

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
                                       f = var17;
                                       g = new Integer[8];
                                       k = new HashMap(13);
                                       Cipher var0;
                                       var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                                       for (int var1 = 1; var1 < 8; var1++) {
                                          var10003[var1] = (byte)(var22 << var1 * 8 >>> 56);
                                       }

                                       (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                       long[] var6 = new long[2];
                                       int var3 = 0;
                                       String var4 = "\u0089\u009ejÛéP´~Ë\u000eËgZ±m\u007f";
                                       int var5 = "\u0089\u009ejÛéP´~Ë\u000eËgZ±m\u007f".length();
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

                                       i = var6;
                                       j = new Long[2];
                                       return;
                                    }
                                    break;
                                 default:
                                    var40[var10001] = var63;
                                    if (var13 < var16) {
                                       continue label59;
                                    }

                                    var15 = "&\u000b\r¡(k\u009cJw \ba·÷Åk";
                                    var16 = "&\u000b\r¡(k\u009cJw \ba·÷Åk".length();
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

                     var27 = var28.charAt(var36);
                     break;
                  default:
                     var31[var29++] = var51;
                     if ((var36 += var27) < var30) {
                        var27 = var28.charAt(var36);
                        continue label77;
                     }

                     var28 = "\u0018\u0086Û\u0099G\u008b!þ/\u0015â\u0000\u0010·»\u0080ðÓ\u009dÓ\u0000\u009b(\t.Ã\u0087ÈJbvà\u0010aÌ\u009eú×bÞÕüû\u0084 c3\u0007\u0013";
                     var30 = "\u0018\u0086Û\u0099G\u008b!þ/\u0015â\u0000\u0010·»\u0080ðÓ\u009dÓ\u0000\u009b(\t.Ã\u0087ÈJbvà\u0010aÌ\u009eú×bÞÕüû\u0084 c3\u0007\u0013"
                        .length();
                     var27 = ' ';
                     var36 = -1;
               }

               var37 = var28.substring(++var36, var36 + var27);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var33) {
         throw new RuntimeException(var33);
      }
   }
}
