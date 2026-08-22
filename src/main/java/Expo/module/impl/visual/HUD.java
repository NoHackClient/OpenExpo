package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.HUDBinder;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.BuildInfo;
import Expo.util.CombatUtil;
import Expo.util.MathUtil;
import Expo.util.MoveUtil;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class HUD extends Module implements EventSubscriber {
   private static String[] k;
   private static String[] c;
   private static ResourceLocation r;
   public static BooleanSetting fps;
   public static BooleanSetting bps;
   public static BooleanSetting release;
   public static NumberSetting scale;
   public static BooleanSetting username;
   private static long a;
   public static BooleanSetting coordinate;
   private static Object[] h;
   private static String[] b;
   public static BooleanSetting time;
   private static Map d;
   public static ModeSetting infoMode;
   private static long g;
   public static BooleanSetting health;
   public static BooleanSetting watermark;

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

   static {
      a = 67018157161661L;
      zkm$clinit();
      r = new ResourceLocation("watermark/logo.png");
   }

   public void onRender2D(Render2DEvent var1, long var2) {
      CustomFont var25 = Font.F(0L);
      ScaledResolution var26 = var1.C;
      String var27 = "";
      if (bps.c()) {
         var27 = var27 + MathUtil.W(MoveUtil.k(32438560973981L)) + " BPS";
      }

      if (coordinate.c()) {
         if (bps.c()) {
            var27 = var27 + " | ";
         }

         var27 = var27 + (int)f.thePlayer.posX + "/" + (int)f.thePlayer.posY + "/" + (int)f.thePlayer.posZ;
      }

      var25.T(37697014677608L, var27, 0.0F, var26.getScaledHeight() - var25.o(60714858652844L), -1);
      if (release.c()) {
         String var28 = "Expo OpenSource";
         var25.T(37697014677608L, var28, var26.getScaledWidth() - var25.R(var28, 52019766876817L), var26.getScaledHeight() - var25.o(60714858652844L), -1);
      }

      if (health.c()) {
         String var43 = CombatUtil.h(CombatUtil.P(f.thePlayer), f.thePlayer.getMaxHealth(), 88877475006969L)
            + MathUtil.W(CombatUtil.h(f.thePlayer) + CombatUtil.D(f.thePlayer))
            + "❤";
         var25.T(37697014677608L, var43, var26.getScaledWidth() / 2.0F - var25.R(var43, 52019766876817L) / 2.0F, var26.getScaledHeight() / 2.0F + var25.o(60714858652844L) + 2.0F, -1);
      }

      float var44 = scale.L();
      switch (infoMode.Y()) {
         case "PLAIN_TEXT":
            if (watermark.c()) {
               GlStateManager.pushMatrix();
               GlStateManager.scale(var44, var44, var44);
               String var47 = BuildInfo.l();
               String var48 = "";
               boolean var50 = false;
               if (username.c()) {
                  var48 = var48 + "§f" + BuildInfo.W;
                  var50 = true;
               }

               if (fps.c()) {
                  if (var50) {
                     var48 = var48 + " §f| ";
                  }

                  var48 = var48 + "§f" + Minecraft.getDebugFPS() + " §fFPS";
               }

               if (!var48.isEmpty()) {
                  var48 = " §f(" + var48 + "§f)";
                  var47 = var47 + var48;
               }

               var25.T(37697014677608L, var47, 2.0F / var44, 2.0F / var44, Theme.S(0.0, 35338930340239L));
               GlStateManager.popMatrix();
            }
            break;
         case "LOGO":
            var44 *= 0.7F;
            if (watermark.c()) {
               Expo.util.render.RenderUtil.g((char)0, (char)25892, r, -336833984, 1.0F, 1.0F, 30.0F, 1.0F / var44, Theme.S(0.0, 35338930340239L));
            }

            float var46 = watermark.c() ? 35.0F * var44 : 1.0F;
            GlStateManager.pushMatrix();
            GlStateManager.scale(var44, var44, var44);
            if (username.c()) {
               var25.T(37697014677608L, "§bUser §7| §r" + BuildInfo.W, 1.0F / var44, var46 / var44, -1);
               var46 += (3.0F + var25.o(60714858652844L)) * var44;
            }

            if (fps.c()) {
               var25.T(37697014677608L, "§bFPS §7| §r" + Minecraft.getDebugFPS(), 1.0F / var44, var46 / var44, -1);
               var46 += (3.0F + var25.o(60714858652844L)) * var44;
            }

            if (time.c()) {
               var25.T(37697014677608L, "§bTime §7| §r" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), 1.0F / var44, var46 / var44, -1);
            }

            GlStateManager.popMatrix();
            break;
         case "INFO":
            if (!watermark.c() && !username.c() && !time.c() && !fps.c()) {
               return;
            }

            StringBuilder var31 = new StringBuilder();
            boolean var32 = false;
            if (watermark.c()) {
               var31.append("Expo");
               var32 = username.c() || time.c() || fps.c();
            }

            if (var32) {
               var31.append(" | ");
            }

            boolean var33 = false;
            if (username.c()) {
               var31.append(BuildInfo.W);
               var33 = true;
            }

            if (fps.c()) {
               if (var33) {
                  var31.append(" ");
               }

               var31.append(Minecraft.getDebugFPS()).append(" FPS");
               var33 = true;
            }

            if (time.c()) {
               if (var33 || var32) {
                  var31.append(" | ");
               }

               var31.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }

            String var34 = var31.toString();
            GlStateManager.pushMatrix();
            GlStateManager.scale(var44, var44, var44);
            float var35 = 2.0F * var44;
            float var36 = var25.R(var34, 52019766876817L) * var44;
            float var37 = var25.o(60714858652844L) * var44;
            float var38 = var36 + 2.0F * var35;
            float var39 = var37 + 2.0F * var35;
            float var40 = 3.0F;
            float var41 = 3.0F;
            Expo.util.render.RenderUtil.c(125644905353792L, var40 / var44, var41 / var44, (var40 + var38) / var44, (var41 + var35) / var44, Theme.S(0.0, 35338930340239L));
            var41 += var35;
            Expo.util.render.RenderUtil.c(125644905353792L, var40 / var44, var41 / var44, (var40 + var38) / var44, (var41 + var39) / var44, new Color(0, 0, 0, (int)g).getRGB());
            var25.T(37697014677608L, var34, (var40 + var35) / var44, (var41 + var35) / var44, -1);
            GlStateManager.popMatrix();
      }
   }

   public final void x(long var1, EventBus var3) {
      HUDBinder.H(var3, this);
   }

   public HUD(long var1) {
      super(((a ^ (var1)) ^ 139999288410231L));
      this.declare("HUD", Category.Visual, "Aka \"Heads up display\"");
      var1 = a ^ var1;
   }

   private static void a() {
      h[0] = "\u0004\u0005bVVJ";
      h[1] = ">`\u0001A\u0007j\tw\u0005KJN\u001e|_W";
      h[2] = long.class;
      k[2] = "java/lang/Long";
      h[3] = "'\u000e\u0007\b\u0018\u0001:";
      h[4] = void.class;
      k[4] = "java/lang/Void";
      h[5] = "\u001ecFd62\u0015lW+W<\u001egSq";
      h[6] = "\u0005vbs\u0018\u000f\u001b0b\u00154vRsa+\u0018\u001a\u001aua+tO\u00142xq\t\u0014\u0016w%\u0015NI[)zm\u0018\u000f\u0014=\u001c/\u0006J\u0014-p-\u000f\u0006\rL";
   }
   private static void zkm$clinit() {
      try {
         long var14 = a ^ 91982637577235L;
         h = new Object[7];
         k = new String[7];
         a();
         d = new HashMap(13);
         Cipher var5;
         byte[] var10003 = new byte[]{(byte)(var14 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var6 = 1; var6 < 8; var6++) {
            var10003[var6] = (byte)(var14 << var6 * 8 >>> 56);
         }

         (var5 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var12 = new String[21];
         int var10 = 0;
         String var9 = ";.óøºEå¥×eOGÝðõ÷\u0010¸\u009dm\u0012¯\"rÆ2\u0012\u0099À\u001dDdï\u0010Õ¸\u009eÐÎôw£.ee¼öÑWÅ\u0010<tAÇ\"÷xP\u000b¯\u0096íáö\u009b\u009e(Òîñ\u009dQ1>®\r³]ì0\u0086\u0000\u008dý(Üý&®.Lå7YvívgÒ²\u0086úÜ]\u001d\u009cÑ åkÞÄÜ²èÂÌ\u001b~ÂU\u0012eÛw¼C\u0005>Ñ´\u000b\u008dÖTÌàÖ\u0001N\u0010\u008c\u0085\u0010p&Í\u009b²f\u0094HÿÆKF\u009d@S\u0003ü\u00adÈ\u001b¯\fäYð\u00820\u0080çCBÅT\u008c\t\u009bõa÷\u0085\u0019¥G^\n;\u009aè CCõ-p\u0088\u0099¨XÒeåöP|øl¦\u0095ÂKyECj\u0003Ñß1\u0010\u009d.ïñJÈò}\u000e\u0096H84\tCÅ\u0010\r)ß\u00ad1ÁàôyzL\u0090\u0097ñçº\u0010\u0014Ö4Øj\u0095{\u0005\u0087âaßaû¶!\u0010æûÄòý\u0081ÕdöT\u0002ïÂ$éÄ0 åÕ^Jh`bÏkÓW\u0005÷ép\u009cÏ\u0010\rØ\u0088ÅÜ\u001c/ÈÄîW¸\u0088\u009ff×:NÒ4+\u0016M\u0085\u0081òhCZ\u0010ÂgO\u0084%Àq\u0017*aa¢Bdßi\u0010\u009bé% ¡U;\u0013\\öÒñ]Â\u0084\u007f\u0018o3ÅåWõ\u000f*\u00ad%\u0014\u001fÞ\u008eÝhºEØé§\u008f,%\u0010Ãç\u0098\u001a»;y ,Â\u009aë\u0085Ã\u00931(Fr\u0099pq2Xáÿq\u0002¯\u0019\u0012×i[õ\"á$â¸\u001c\u0015v*À\u0081¢7w:\u0011Û\u0018\u009fGYu °gb\u0018êM-s5æ\u0090ö\u008d9\n{5.¡¨\\wÙóQcZ;\u0003\u0090ùÛ";
         int var11 = ";.óøºEå¥×eOGÝðõ÷\u0010¸\u009dm\u0012¯\"rÆ2\u0012\u0099À\u001dDdï\u0010Õ¸\u009eÐÎôw£.ee¼öÑWÅ\u0010<tAÇ\"÷xP\u000b¯\u0096íáö\u009b\u009e(Òîñ\u009dQ1>®\r³]ì0\u0086\u0000\u008dý(Üý&®.Lå7YvívgÒ²\u0086úÜ]\u001d\u009cÑ åkÞÄÜ²èÂÌ\u001b~ÂU\u0012eÛw¼C\u0005>Ñ´\u000b\u008dÖTÌàÖ\u0001N\u0010\u008c\u0085\u0010p&Í\u009b²f\u0094HÿÆKF\u009d@S\u0003ü\u00adÈ\u001b¯\fäYð\u00820\u0080çCBÅT\u008c\t\u009bõa÷\u0085\u0019¥G^\n;\u009aè CCõ-p\u0088\u0099¨XÒeåöP|øl¦\u0095ÂKyECj\u0003Ñß1\u0010\u009d.ïñJÈò}\u000e\u0096H84\tCÅ\u0010\r)ß\u00ad1ÁàôyzL\u0090\u0097ñçº\u0010\u0014Ö4Øj\u0095{\u0005\u0087âaßaû¶!\u0010æûÄòý\u0081ÕdöT\u0002ïÂ$éÄ0 åÕ^Jh`bÏkÓW\u0005÷ép\u009cÏ\u0010\rØ\u0088ÅÜ\u001c/ÈÄîW¸\u0088\u009ff×:NÒ4+\u0016M\u0085\u0081òhCZ\u0010ÂgO\u0084%Àq\u0017*aa¢Bdßi\u0010\u009bé% ¡U;\u0013\\öÒñ]Â\u0084\u007f\u0018o3ÅåWõ\u000f*\u00ad%\u0014\u001fÞ\u008eÝhºEØé§\u008f,%\u0010Ãç\u0098\u001a»;y ,Â\u009aë\u0085Ã\u00931(Fr\u0099pq2Xáÿq\u0002¯\u0019\u0012×i[õ\"á$â¸\u001c\u0015v*À\u0081¢7w:\u0011Û\u0018\u009fGYu °gb\u0018êM-s5æ\u0090ö\u008d9\n{5.¡¨\\wÙóQcZ;\u0003\u0090ùÛ"
            .length();
         char var8 = 16;
         int var18 = -1;

         label43:
         while (true) {
            String var19 = var9.substring(++var18, var18 + var8);
            byte var10001 = -1;

            while (true) {
               byte[] var13 = var5.doFinal(var19.getBytes("ISO-8859-1"));
               String var27 = b(var13).intern();
               switch (var10001) {
                  case 0:
                     var12[var10++] = var27;
                     if ((var18 += var8) >= var11) {
                        b = var12;
                        c = new String[21];
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var14 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var14 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));

                        byte[] var4 = var0.doFinal(
                           new byte[]{
                              (byte)131L,
                              (byte)33643L,
                              (byte)8612707L,
                              (byte)2204853194L,
                              (byte)564442417727L,
                              (byte)144497258938157L,
                              (byte)36991298288168305L,
                              (byte)-8976971711938465401L
                           }
                        );
                        long var31 = (var4[0] & 255L) << 56
                           | (var4[1] & 255L) << 48
                           | (var4[2] & 255L) << 40
                           | (var4[3] & 255L) << 32
                           | (var4[4] & 255L) << 24
                           | (var4[5] & 255L) << 16
                           | (var4[6] & 255L) << 8
                           | var4[7] & 255L;
                        var10001 = -1;
                        g = var31;
                        return;
                     }

                     var8 = var9.charAt(var18);
                     break;
                  default:
                     var12[var10++] = var27;
                     if ((var18 += var8) < var11) {
                        var8 = var9.charAt(var18);
                        continue label43;
                     }

                     var9 = "æE\u0012Ã\u009a.\u000bB\u001dä\nøæÒÏý\u0018èVZ)C\u0081YSy\u0005ëìN\u001dQ<æã\u0005´Ãü[\u008e";
                     var11 = "æE\u0012Ã\u009a.\u000bB\u001dä\nøæÒÏý\u0018èVZ)C\u0081YSy\u0005ëìN\u001dQ<æã\u0005´Ãü[\u008e".length();
                     var8 = 16;
                     var18 = -1;
               }

               var19 = var9.substring(++var18, var18 + var8);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var16) {
         throw new RuntimeException(var16);
      }
   }

   static {
      health = new BooleanSetting("Health", false);
      fps = new BooleanSetting("FPS", true);
      scale = new NumberSetting("Scale", 1.0F, 0.25F, 3.0F, 0.01F);
      username = new BooleanSetting("Username", true);
      bps = new BooleanSetting("BPS", false);
      coordinate = new BooleanSetting("Coordinate", false);
      time = new BooleanSetting("Time", false);
      release = new BooleanSetting("Release", true);
      infoMode = new ModeSetting("Info-mode", "PLAIN_TEXT", "INFO", "LOGO");
      watermark = new BooleanSetting("Watermark", true);
   }
}
