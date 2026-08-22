package Expo.internal;

import Expo.command.impl.StockCommandModuleSetting;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ChatInputHandlerBinder;
import Expo.event.events.GuiChatKeyTypedEvent;
import Expo.event.events.PostTickEvent;
import Expo.internal.jnic.StockCommandRegistry;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.Modules;
import Expo.module.impl.misc.CommandLine;
import Expo.util.ClientUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.MinecraftRef;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;

public class ChatInputHandler implements EventSubscriber {
   private static Map e;
   private static boolean k;
   private static long[] c;
   private static long a;
   private static int A;
   private boolean V;
   private static int C;
   private static String g;
   private static String b;
   private static Minecraft F;
   public static List<String> E;

   public final void x(long var1, EventBus var3) {
      ChatInputHandlerBinder.A(var3, this);
   }

   private void O(char var1, char var2) {
      E.clear();
      A = -1;
      C = 0;
      g = "";
      this.V = false;
      k = false;
   }

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var10001 = 14005570406408L;

      int var6 = (int)(var10001 << 48 >>> 48);

      var10001 = 82618457592067L;

      if (!ClientUtil.I()) {
         this.O((char)0, (char)19236);
      }

      if (this.v(137682475207932L)) {
         if (KeyBindUtil.V(15, 64165991731362L)) {
            if (!this.V) {
               this.V = true;
               String var26 = this.s(3260, (short)60684, (short)var6);
               String var27 = var26.substring(1);
               String[] var28 = var27.split(" ");
               ArrayList<String> var29 = new ArrayList<>();
               Collections.addAll(var29, var28);
               int var30 = this.S( var27);
               Expo.command.Command var31 = var28.length > 0 ? this.V(116613762984180L, var28[0]) : null;
               if (!(var31 instanceof StockCommandModuleSetting) && !var29.isEmpty()) {
                  var29.remove(0);
               }

               String var32 = x(var27);
               if (var30 == A && !k) {
                  C++;
                  if (C >= E.size()) {
                     C = 0;
                  }

                  C = MathUtil.k(C, 0, E.size() - 1);
               } else {
                  g = var32;
                  C = 0;
                  E = var31 != null ? var31.g(var29.toArray(new String[0]), var30, 15052477066368L) : new ArrayList<>();
                  if (E == null) {
                     E = new ArrayList<>();
                  }

                  if (var31 == null) {
                     for (Expo.command.Command var34 : StockCommandRegistry.L) {
                        E.add(var34.e(116557065638625L)[0]);
                     }

                     for (Module var37 : ModuleManager.S) {
                        E.add(var37.b());
                     }
                  }

                  E.removeIf(var0 -> var0 == null || var0.isEmpty() || !var0.toLowerCase().replaceAll("-", "").startsWith(g.toLowerCase().replaceAll("-", "")));
                  if (!CommandLine.autoFillPrompt.c() || E.isEmpty()) {
                     return;
                  }

                  ClientUtil.t(48081174263320L, b + Arrays.toString(E.toArray(new String[0])));
               }

               k = false;
               if (!E.isEmpty()) {
                  this.X(92903460908824L, "." + y(var27, E.get(C)));
               }

               A = var30;
            }
         } else {
            this.V = false;
         }
      } else {
         this.O((char)0, (char)19236);
      }
   }

   private boolean v(long var1) {
      return F.currentScreen instanceof GuiChat && this.s(3260, (short)60684, (short)34824).startsWith(".") && Modules.J(CommandLine.class).o() && CommandLine.autoFill.c();
   }

   static {
      a = 133875353228107L;
      E = new ArrayList<>();
      A = -1;
      C = 0;
      g = "";
      k = false;
      F = MinecraftRef.c((byte)0, 0L);
      b = "\u00a7b\u00a7lAUTOCOMPLETE\u00a7r: ";
      e = new HashMap(13);
      c = new long[]{-2394404294513042523L, 1683432911221615452L, 9045198900964769424L, -8656514580352376674L, -2173930181571335424L, 75138827731708389L, -9019545282253747357L, 5102263365140684918L};
   }

   private void X(long var1, String var3) {
      Expo.internal.accessor.GuiChatAccessor.z((char)0, (char)10358, 245891786, (GuiChat)F.currentScreen).setText(var3);
   }

   public static String y(String var0, String var1) {
      int var2 = var0.lastIndexOf(" ");
      return var2 == -1 ? var1 : var0.substring(0, var2 + 1) + var1;
   }

   public void onGuiChatKeyTyped(GuiChatKeyTypedEvent var1, int var2, long var3) {
      k = true;
   }

   private Expo.command.Command V(long var1, String var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      for (Expo.command.Command var7 : StockCommandRegistry.L) {
         for (String var11 : var7.e(116557065638625L)) {
            if (var11.equalsIgnoreCase(var3)) {
               return var7;
            }
         }
      }

      for (Module var14 : ModuleManager.S) {
         if (var14.b().equalsIgnoreCase(var3)) {
            return StockCommandRegistry.J;
         }
      }

      return null;
   }

   private int S( String var3) {
      int var4 = 0;

      for (int var5 = 0; var5 < var3.length(); var5++) {
         if (var3.charAt(var5) == 32) {
            var4++;
         }
      }

      return var4;
   }

   public ChatInputHandler(long var1) {
      this.V = false;
   }

   public static String x(String var0) {
      int var1 = var0.lastIndexOf(" ");
      return var1 == -1 ? var0 : var0.substring(var1 + 1);
   }

   private String s(int var1, short var2, short var3) {
      long var4 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ a;
      int var6 = (int)((var4 ^ 102291818092937L) >>> 48);
      int var7 = (int)((var4 ^ 102291818092937L) << 16 >>> 48);
      int var8 = (int)((var4 ^ 102291818092937L) << 32 >>> 32);
      return Expo.internal.accessor.GuiChatAccessor.z((char)var6, (char)var7, var8, (GuiChat)F.currentScreen).getText();
   }
}
