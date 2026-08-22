package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.KillEffectBinder;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.HandleChatEvent;
import Expo.event.events.LivingDeathEvent;
import Expo.event.events.PostRenderEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.util.render.LightningRenderer;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StringUtils;

public class KillEffect extends Module implements EventSubscriber {
   public static BooleanSetting onlyKilledBySelf;
   private static long a;
   private static Object[] g;
   private final Map<String, KillEffectDeathPos> F;
   private static String[] h;
   public static ModeSetting mode;
   private static String[] b;
   private static Map e;
   private static String[] c;

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

   private static void a() {
      g[0] = "ah\u001f\u0000O?I";
      g[1] = "B\n\u001e\u0001\u0003[u\u001d\u001a\u000bN\u007fb\u0016@\u0017";
      g[2] = "\"jf'\u0001%+";
      g[3] = long.class;
      h[3] = "java/lang/Long";
      g[4] = void.class;
      h[4] = "java/lang/Void";
      g[5] = "`F~d'6kIo+F8`Bkq";
      g[6] = "%'\\\u000f\u0003F>+Yt072-[\b\u0005Y&~RtQP*:Z\r\u0004[u!9N\u000e^%x\u0002L\u0018Lr@\u0002M\u0001\r=1@\u000f\u0006LO";
   }

   public void onEntityJoinWorld(EntityJoinWorldEvent var1) {
      if (var1.H.equals(f.thePlayer)) {
         this.F.clear();
      }
   }

   private void R(KillEffectDeathPos var1, long var2) {
      switch (mode.Y()) {
         case "BLOOD":
            LightningRenderer.f(1, 6021109416714L, var1.G, var1.L, var1.S, var1.w);
            break;
         case "LIGHTNING":
            LightningRenderer.f(3, 6021109416714L, var1.G, var1.L, var1.S, var1.w);
            break;
         case "SOUL_BREAK":
            LightningRenderer.f(2, 6021109416714L, var1.G, var1.L, var1.S, var1.w);
      }
   }

   public String g(long var1) {
      return mode.Y();
   }

   public void onLivingDeath(LivingDeathEvent var1, long var2) {
      boolean var6 = !onlyKilledBySelf.c() || var1.M.getEntity() != null && var1.M.getEntity().equals(f.thePlayer);
      if (var6 && var1.p != f.thePlayer) {
         String var7 = var1.p.getName();
         KillEffectDeathPos var8 = this.F.remove(var7);
         if (var8 != null) {
            this.R(var8, 24462074121926L);
         } else {
            EntityLivingBase var9 = var1.p;
            this.R(new KillEffectDeathPos(var9.posX, var9.posY, var9.posZ, var9.getEyeHeight()), 24462074121926L);
         }
      }
   }

   public void onPostRender(PostRenderEvent var1) {
      if (var1.z instanceof EntityPlayer && var1.z != f.thePlayer) {
         EntityLivingBase var2 = var1.z;
         this.F.put(var2.getName(), new KillEffectDeathPos(var2.posX, var2.posY, var2.posZ, var2.getEyeHeight()));
      }
   }

   public void onHandleChat(long var1, HandleChatEvent var3) {
      String var6 = StringUtils.stripControlCodes(var3.A.getUnformattedText());
      if (f.thePlayer != null && !var6.contains(":") && var6.contains("by " + f.thePlayer.getName())) {
         String var7 = var6.trim().split(" ")[0];
         KillEffectDeathPos var8 = this.F.remove(var7);
         if (var8 != null) {
            this.R(var8, 24462074121926L);
         }
      }
   }

   public KillEffect(long var1) {
      super(((a ^ (var1)) ^ 45213747570466L));
      this.declare("KillEffect", Category.Visual, "Play some effects after you killed your enemy");
      var1 = a ^ var1;
      this.F = new ConcurrentHashMap<>();
   }

   public final void x(long var1, EventBus var3) {
      KillEffectBinder.P(var3, this);
   }

   static {
      a = 117327217342098L;
      zkm$clinit();
   }

   public void t(LivingDeathEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (var1.p != null) {
         boolean var7 = var1.M.getEntity() != null && var1.M.getEntity().equals(f.thePlayer);
         if (!onlyKilledBySelf.c() || var7) {
            switch (mode.Y()) {
               case "BLOOD":
                  LightningRenderer.E((short)0, var1.p, 1, (short)31551, 0);
                  break;
               case "LIGHTNING":
                  LightningRenderer.E((short)0, var1.p, 3, (short)31551, 0);
                  break;
               case "SOUL_BREAK":
                  LightningRenderer.E((short)0, var1.p, 2, (short)31551, 0);
            }
         }
      }
   }
   private static void zkm$clinit() {
      try {
         g = new Object[7];
         h = new String[7];
         a();
         e = new HashMap(13);
         long var0 = a ^ 75590332247018L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var9 = new String[7];
         int var7 = 0;
         String var6 = "uïK×:\u0007¥p·\u0091e2\u0097JVí\u0018Òôâ·¢i\u008b[* nË¬MçÚ³¬òIvKZI\u0010\u000fAEî'-PÚÛÕÀà¶\u009bÏ\u0001 \u0096\u0017\rÍñ\u0005\u0083Ý\u0083\u008d]Ì\u0014+òþiqóÓ*ø¤\u0012|\u008b5È$ÎdÂ\u0018[¸°z\u009b¢ê\u008cÆrbtô \u0080!\"\u008aÇaÐc¹\u0002";
         int var8 = "uïK×:\u0007¥p·\u0091e2\u0097JVí\u0018Òôâ·¢i\u008b[* nË¬MçÚ³¬òIvKZI\u0010\u000fAEî'-PÚÛÕÀà¶\u009bÏ\u0001 \u0096\u0017\rÍñ\u0005\u0083Ý\u0083\u008d]Ì\u0014+òþiqóÓ*ø¤\u0012|\u008b5È$ÎdÂ\u0018[¸°z\u009b¢ê\u008cÆrbtô \u0080!\"\u008aÇaÐc¹\u0002"
            .length();
         char var5 = 16;
         int var13 = -1;

         label31:
         while (true) {
            String var14 = var6.substring(++var13, var13 + var5);
            byte var10001 = -1;

            while (true) {
               byte[] var10 = var2.doFinal(var14.getBytes("ISO-8859-1"));
               String var20 = b(var10).intern();
               switch (var10001) {
                  case 0:
                     var9[var7++] = var20;
                     if ((var13 += var5) >= var8) {
                        b = var9;
                        c = new String[7];
                        return;
                     }

                     var5 = var6.charAt(var13);
                     break;
                  default:
                     var9[var7++] = var20;
                     if ((var13 += var5) < var8) {
                        var5 = var6.charAt(var13);
                        continue label31;
                     }

                     var6 = "à\u009d;tÿr0Eõ\u0087_fFé\u001e¿{.yÁ\u0006Ò\u008eé«\u0002:^^Ä)\u0013\u0010®ETy&\u001ecò\u009f«EHW=óM";
                     var8 = "à\u009d;tÿr0Eõ\u0087_fFé\u001e¿{.yÁ\u0006Ò\u008eé«\u0002:^^Ä)\u0013\u0010®ETy&\u001ecò\u009f«EHW=óM".length();
                     var5 = ' ';
                     var13 = -1;
               }

               var14 = var6.substring(++var13, var13 + var5);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var11) {
         throw new RuntimeException(var11);
      }
   }

   static {
      mode = new ModeSetting("Mode", false, "BLOOD", "NONE", "BLOOD", "LIGHTNING", "SOUL_BREAK");
      onlyKilledBySelf = new BooleanSetting("Only-killed-by-self", false);
   }
}
