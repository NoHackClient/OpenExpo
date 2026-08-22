package Expo.ui.screen;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ReconnectHandlerBinder;
import Expo.event.events.ActionPerformedEvent;
import Expo.event.events.DisconnectedInitEvent;
import Expo.event.events.GuiMouseEvent;
import Expo.event.events.InitGuiEvent;
import Expo.event.events.PreDrawScreenEvent;
import Expo.event.events.ServerJoinEvent;
import Expo.internal.auth.Account;
import Expo.internal.auth.AltManager;
import Expo.internal.auth.SessionAccessor;
import Expo.util.ChatFormatting;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import org.apache.commons.lang3.StringUtils;

public class ReconnectHandler implements EventSubscriber {
   private static Map d;
   private static long[] h;
   private static long a;
   private static String[] b;
   private static Map j;
   private static Map g;
   private static String[] c;
   private static Minecraft T;

   private static GuiButton V(GuiScreen var0) {
      return h(GuiScreenButtonList.J(var0));
   }

   public void onPreDrawScreen(PreDrawScreenEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (c(var1.Q)) {
         String var9 = ChatFormatting.y(String.format("&7Username: &3%s&r", SessionAccessor.d().getUsername()));
         GlStateManager.disableLighting();
         var1.Q.drawString(T.fontRendererObj, var9, 3, 3, -1);
         GlStateManager.enableLighting();
      }
   }

   public void onGuiMouse(GuiMouseEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (c(var1.j)) {
         d(35828833225014L, var1.j, null);
         if (var1.y) {
            GuiButton var15 = V(var1.j);
            if (var15 != null && d(var15, var1.I, var1.A)) {
               T.displayGuiScreen(new AccountManagerScreen(81800336346822L, var1.j));
               var1.I(21307, 3074332907L);
            }
         }
      }
   }

   private static void d(long var0, GuiScreen var2, List var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (c(var2)) {
         List var8 = var3 != null ? var3 : GuiScreenButtonList.J(var2);
         if (var8 != null && h(var8) == null) {
            var8.add(
               new GuiButton(
                  69,
                  var2.width - 106,
                  6,
                  100,
                  20,
                  "Accounts"
               )
            );
         }
      }
   }

   public void onActionPerformed(ActionPerformedEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (c(var1.O) && var1.Q != null && var1.Q.id == 69) {
         T.displayGuiScreen(new AccountManagerScreen(81800336346822L, var1.O));
         var1.I(21307, 3074332907L);
      }
   }

   private static long t(String var0) {
      long var3 = System.currentTimeMillis();

      for (String var8 : var0.split(" ")) {
         if (!var8.isEmpty()) {
            String var9 = var8.substring(var8.length() - 1);
            long var10 = Long.parseLong(var8.substring(0, var8.length() - 1));
            switch (var9) {
               case "d":
                  var3 += var10 * 86400000L;
                  break;
               case "h":
                  var3 += var10 * 3600000L;
                  break;
               case "m":
                  var3 += var10 * 60000L;
                  break;
               case "s":
                  var3 += var10 * 1000L;
            }
         }
      }

      return var3;
   }

   static {
      a = 118119382273412L;
      T = Minecraft.getMinecraft();
   }

   private static void z(long var0, long var2) {
      AltManager.Q(17200, (short)3883, (short)55813);

      for (Account var10 : AltManager.Q) {
         if (SessionAccessor.d().getUsername().equals(var10.h())) {
            var10.G(var0);
         }
      }

      AltManager.O(101554584226764L);
   }

   private static GuiButton h(List var2) {
      if (var2 == null) {
         return null;
      }

      for (GuiButton var4 : (Iterable<GuiButton>)(var2)) {
         if (var4 != null && var4.id == 69) {
            return var4;
         }
      }

      return null;
   }

   public void onDisconnectedInit(long var1, DisconnectedInitEvent var3) {
      if (var3.O instanceof GuiDisconnected && var3.X != null) {
         String var8 = var3.X.getFormattedText().split("\n\n")[0];
         if (!var8.equals("§r§cYou are permanently banned from this server!") && !var8.equals("§r§cYour account has been blocked.")) {
            if (var8.matches("§r§cYou are temporarily banned for §r§f.*§r§c from this server!")
               || var8.matches("§r§cYour account is temporarily blocked for §r§f.*§r§c from this server!")) {
               String var9 = StringUtils.substringBetween(var8, "§r§f", "§r§c");
               if (var9 != null) {
                  z(t(var9), 60323149919382L);
               }
            }
         } else {
            z(-1L, 60323149919382L);
         }
      }
   }

   public void onInitGui(InitGuiEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      d(35828833225014L, var1.A, var1.B);
   }

   public final void x(long var1, EventBus var3) {
      ReconnectHandlerBinder.v(var3, this);
   }

   private static boolean c(GuiScreen var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (var0 == null) {
         return false;
      }

      if (!(var0 instanceof GuiSelectWorld) && !(var0 instanceof GuiMultiplayer)) {
         for (Class var3 = var0.getClass(); var3 != null; var3 = var3.getSuperclass()) {
            String var4 = var3.getName();
            String var5 = var4.toLowerCase();
            if (var4.endsWith(".GuiSelectWorld")
               || var4.endsWith(".GuiMultiplayer")
               || var4.equals("net.minecraft.client.gui.GuiSelectWorld")
               || var4.equals("net.minecraft.client.gui.GuiMultiplayer")
               || var5.contains("multiplayer")
               || var5.contains("selectworld")
               || var5.contains("worldselection")) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private static boolean d(GuiButton var0, int var1, int var2) {
      return var0.enabled
         && var0.visible
         && var1 >= var0.xPosition
         && var2 >= var0.yPosition
         && var1 < var0.xPosition + var0.width
         && var2 < var0.yPosition + var0.height;
   }

   public void onServerJoin(long var1, ServerJoinEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      ServerData var6 = var3.u;
      if (var6 != null) {
         String var7 = var6.serverIP;
         if (var7 != null && (var7.endsWith("hypixel.net") || var7.endsWith("hypixel.io"))) {
            z(0L, 60323149919382L);
         }
      }
   }
}
