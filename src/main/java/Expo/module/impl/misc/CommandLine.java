package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.CommandLineBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.util.ClientUtil;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
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
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;











public class CommandLine extends Module implements EventSubscriber {
   private static long[] b;
   private static long a;
   public static BooleanSetting autoFillPrompt;
   private static Map d;
   private static Object[] e;
   public static BooleanSetting autoFill;
   private static Integer[] c;
   private static String[] g;

   static {
      a = 54022204389669L;
      // add code
      zkm$clinit();
   }

   private static void zkm$clinit() {
      try {
         e = new Object[7];
         g = new String[7];
         a();
         d = new HashMap(13);
         long var0 = a ^ 46196709686825L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         long[] var8 = new long[3];
         int var5 = 0;
         String var6 = "ø\u0002&e«Ë\u0017\u001fX\u0098i*¦\u0084¼3G\u0097\u000fõ]ÌNç";
         int var7 = "ø\u0002&e«Ë\u0017\u001fX\u0098i*¦\u0084¼3G\u0097\u000fõ]ÌNç".length();
         int var4 = 0;

         do {
            int var10001 = var4;
            var4 += 8;
            byte[] var9 = var6.substring(var10001, var4).getBytes("ISO-8859-1");
            var10001 = var5++;
            long var10 = (var9[0] & 255L) << 56
               | (var9[1] & 255L) << 48
               | (var9[2] & 255L) << 40
               | (var9[3] & 255L) << 32
               | (var9[4] & 255L) << 24
               | (var9[5] & 255L) << 16
               | (var9[6] & 255L) << 8
               | var9[7] & 255L;
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
            long var10004 = (var12[0] & 255L) << 56
               | (var12[1] & 255L) << 48
               | (var12[2] & 255L) << 40
               | (var12[3] & 255L) << 32
               | (var12[4] & 255L) << 24
               | (var12[5] & 255L) << 16
               | (var12[6] & 255L) << 8
               | var12[7] & 255L;
            var8[var10001] = var10004;
         } while (var4 < var7);

         b = var8;
         c = new Integer[3];
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var13) {
         throw new RuntimeException(var13);
      }
   }

   public void onRender2D(int var1, int var2, Render2DEvent var3, int var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 99233232358707L;
      long var10001 = var5 ^ 59846985663092L;
      int var9 = (int)((var5 ^ 59846985663092L) >>> 48);
      int var10 = (int)((var5 ^ 59846985663092L) << 16 >>> 48);
      int var12 = (int)((var5 ^ 90252217372278L) >>> 48);
      int var13 = (int)((var5 ^ 90252217372278L) << 16 >>> 48);
      int var14 = (int)((var5 ^ 90252217372278L) << 32 >>> 32);
      if (f.currentScreen instanceof GuiChat) {
         GuiTextField var17 = Expo.internal.accessor.GuiChatAccessor.z((char)var12, (char)var13, var14, (GuiChat)f.currentScreen);
         if (var17 == null) {
            return;
         }

         String var18 = var17.getText();
         if (!var18.isEmpty() && var18.charAt(0) == 46) {
            float var20 = f.currentScreen.height - 14;
            Color var15 = new Color(Theme.S(Theme.offset.L(), var7));
            float var16 = (f.currentScreen.height - 2);
            Expo.util.render.RenderUtil.G(2.0F, var20, (f.currentScreen.width - 2), (char)var9, var16, (char)var10, var15);
         }
      }
   }

   public CommandLine(long var1) {
      super(((a ^ (var1)) ^ 41236048353694L));
      // add code
      this.declare("CommandLine", Category.Misc, "Configure the client setting by typing command in chat");
      var1 = a ^ var1;
   }

   private static void a() {
      e[0] = "\u0018=q~^\u00111";
      e[1] = "\u0018Pu.Z+/Gq$\u0017\u000f8L+8";
      e[2] = long.class;
      g[2] = "java/lang/Long";
      e[3] = "b9^gv&I";
      e[4] = void.class;
      g[4] = "java/lang/Void";
      e[5] = "[\u0019|\u0014{xP\u0016m[\u001av[\u001di\u0001";
      e[6] = "E\\H`\\2WCL\u000b\u001fB\u0017\u0019\bl\u0002zIMCydx\u0016D\u001cm\u0016:TI\u0002\u000b^.GG\u0012nX9_\u0019r1\u001bzU\u001c\u0010z^,V&";
   }

   public void onPreUpdate(PreUpdateEvent var1, long var2) {

      if (f.currentScreen == null) {
         boolean var6 = f.gameSettings.chatVisibility != EnumChatVisibility.HIDDEN;
         if (ClientUtil.b(52, 35207672374243L) && var6) {
            f.displayGuiScreen(new GuiChat("."));
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      CommandLineBinder.s(var3, this);
   }

   static {
      // add code
      autoFill = new BooleanSetting("Auto-fill", true);
      autoFillPrompt = new BooleanSetting("Auto-fill-prompt", true);
   }
}
