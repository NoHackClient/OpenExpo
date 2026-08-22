package Expo.ui.screen;

import Expo.enums.AccountType;
import Expo.internal.auth.Account;
import Expo.internal.auth.AccountListSlot;
import Expo.internal.auth.AltManager;
import Expo.internal.auth.AuthService;
import Expo.internal.auth.SessionAccessor;
import Expo.internal.auth.SessionSwapper;
import Expo.internal.auth.TimedStatusMessage;
import Expo.util.ChatFormatting;
import Expo.util.Sneaky;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

public class AccountManagerScreen extends GuiScreen {
   private static String[] c;
   private int G;
   private GuiButton Q;
   private GuiButton i;
   private AccountListSlot P;
   private static Map d;
   public static TimedStatusMessage q;
   private CompletableFuture<Void> I;
   private static Map g;
   private ExecutorService K;
   private static Map k;
   private GuiButton S;
   protected final GuiScreen v;
   private static long a;

   public void actionPerformed(GuiButton var1) {
       try {long var2 = 43158565309945L;

      if (var1 != null) {
         if (var1.enabled) {
            switch (var1.id) {
               case 0:
                  if (this.I == null || this.I.isDone()) {
                     if (this.K == null) {
                        this.K = Executors.newSingleThreadExecutor();
                     }

                     Account var11;
                     String var12 = StringUtils.isBlank((var11 = AltManager.Q.get(this.G)).h()) ? "???" : var11.h();
                     if (var11.v() == AccountType.OFFLINE) {
                        boolean var15 = SessionSwapper.D(var11.h(), 14635617689442L);
                        q = var15
                           ? new TimedStatusMessage(
                              ChatFormatting.y(String.format("&aSuccessful login! (%s)&r", var11.h())),
                              5000L
                           )
                           : new TimedStatusMessage(
                              ChatFormatting.y(String.format("&cFailed to log in! (%s)&r", var11.h())),
                              5000L
                           );
                        return;
                     }

                     q = new TimedStatusMessage(
                        ChatFormatting.y(String.format("&7Fetching your Minecraft profile... (%s)&r", var12)), (-1L)
                     );
                     Account var14 = var11;
                     this.I = AuthService.i(var14.Y(), this.K)
                        .handle(
                           (var3, var4x) -> {
                               try {long var5 = a ^ 87885160721567L;
                              long var7 = var5 ^ 13867146136939L;
                              long var10001x = var5 ^ 30004265269190L;
                              int var9x = (int)((var5 ^ 30004265269190L) >>> 48);
                              int var10x = (int)((var5 ^ 30004265269190L) << 16 >>> 32);
                              int var11x = (int)(var10001x << 48 >>> 48);
                              if (var3 != null) {
                                 var14.J(var3.getUsername());
                                 AltManager.O(var7);
                                 SessionAccessor.k(var3);
                                 q = new TimedStatusMessage(
                                    ChatFormatting.y(String.format("&aSuccessful login! (%s)&r", var14.h())),
                                    5000L
                                 );
                                 return CompletableFuture.completedFuture(null);
                              } else {
                                 q = new TimedStatusMessage(
                                    ChatFormatting.y(String.format("&7Refreshing Microsoft access tokens... (%s)&r", var12)),
                                    -1L
                                 );
                                 return AuthService.A(var14.d(), this.K)
                                    .thenComposeAsync(
                                       var2xx -> {
                                           try {long var3x = a ^ 62198513322221L;
                                          long var10001xx = var3x ^ 118979352941492L;
                                          int var5x = (int)((var3x ^ 118979352941492L) >>> 48);
                                          int var6x = (int)((var3x ^ 118979352941492L) << 16 >>> 32);
                                          int var7x = (int)(var10001xx << 48 >>> 48);
                                          q = new TimedStatusMessage(
                                             ChatFormatting.y(String.format("&7Acquiring Xbox access token... (%s)&r", var12)),
                                             -1L
                                          );
                                          return AuthService.M(var2xx.get("access_token"), this.K);
                                       } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } },
                                       this.K
                                    )
                                    .thenComposeAsync(
                                       var2xx -> {
                                           try {long var3x = a ^ 110260397370429L;
                                          long var10001xx = var3x ^ 53739931760484L;
                                          int var5x = (int)((var3x ^ 53739931760484L) >>> 48);
                                          int var6x = (int)((var3x ^ 53739931760484L) << 16 >>> 32);
                                          int var7x = (int)(var10001xx << 48 >>> 48);
                                          q = new TimedStatusMessage(
                                             ChatFormatting.y(String.format("&7Acquiring Xbox XSTS token... (%s)&r", var12)),
                                             -1L
                                          );
                                          return AuthService.L(var2xx, this.K);
                                       } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } },
                                       this.K
                                    )
                                    .thenComposeAsync(
                                       var2xx -> {
                                           try {long var3x = a ^ 91584954772080L;
                                          long var10001xx = var3x ^ 8712255811881L;
                                          int var5x = (int)((var3x ^ 8712255811881L) >>> 48);
                                          int var6x = (int)((var3x ^ 8712255811881L) << 16 >>> 32);
                                          int var7x = (int)(var10001xx << 48 >>> 48);
                                          q = new TimedStatusMessage(
                                             ChatFormatting.y(String.format("&7Acquiring Minecraft access token... (%s)&r", var12)),
                                             -1L
                                          );
                                          return AuthService.P(
                                             var2xx.get("Token"), var2xx.get("uhs"), this.K
                                          );
                                       } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } },
                                       this.K
                                    )
                                    .thenComposeAsync(
                                       var2xx -> {
                                           try {long var3x = a ^ 132435433508590L;
                                          long var10001xx = var3x ^ 49292075324855L;
                                          int var5x = (int)((var3x ^ 49292075324855L) >>> 48);
                                          int var6x = (int)((var3x ^ 49292075324855L) << 16 >>> 32);
                                          int var7x = (int)(var10001xx << 48 >>> 48);
                                          q = new TimedStatusMessage(
                                             ChatFormatting.y(String.format("&7Fetching your Minecraft profile... (%s)&r", var12)),
                                             -1L
                                          );
                                          return AuthService.i(var2xx, this.K);
                                       } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } },
                                       this.K
                                    )
                                    .thenAccept(
                                       var1xx -> {
                                           try {long var2xx = 6502544405800L;
                                          long var4xx = 101554584226764L;
                                          long var10001xx = var2xx ^ 86181252498505L;
                                          int var6x = (int)((var2xx ^ 86181252498505L) >>> 48);
                                          int var7x = (int)((var2xx ^ 86181252498505L) << 16 >>> 32);
                                          int var8x = (int)(var10001xx << 48 >>> 48);
                                          var14.J(var1xx.getUsername());
                                          AltManager.O(var4xx);
                                          SessionAccessor.k(var1xx);
                                          q = new TimedStatusMessage(
                                             ChatFormatting.y(String.format("&aSuccessful login! (%s)&r", var14.h())),
                                             5000L
                                          );
                                       } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }
                                    );
                              }
                           } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }
                        )
                        .thenComposeAsync(var0 -> (CompletionStage<Void>)var0, this.K)
                        .exceptionally(
                           var1x -> {
                               try {long var2x = 83056452077633L;
                              long var10001x = var2x ^ 9822730528L;
                              int var5 = (int)((var2x ^ 9822730528L) << 16 >>> 32);
                              int var6x = (int)(var10001x << 48 >>> 48);
                              q = new TimedStatusMessage(
                                 ChatFormatting.y(String.format("&c%s (%s)&r", var1x.getMessage(), var12)),
                                 5000L
                              );
                              return null;
                           } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }
                        );
                  }
                  break;
               case 1:
                  this.mc.displayGuiScreen(new AddAccountScreen(this.v));
                  break;
               case 2:
                  if (this.G > -1 && this.G < AltManager.Q.size()) {
                     AltManager.Q.remove(this.G);
                     AltManager.O(101554584226764L);
                     this.G = -1;
                     this.updateScreen();
                  }
                  break;
               case 3:
                  this.mc.displayGuiScreen(Z(this.v));
                  break;
               default:
                  this.P.actionPerformed(var1);
            }
         }
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public static GuiButton L(AccountManagerScreen var0) {
      return var0.i;
   }

   public void drawScreen(int var1, int var2, float var3) {
       try {long var4 = 32992442628228L;
      if (this.P != null) {
         this.P.drawScreen(var1, var2, var3);
      }

      super.drawScreen(var1, var2, var3);
      this.drawCenteredString(
         this.fontRendererObj,
         ChatFormatting.y(String.format("&rLumiere Account Manager &8(&7%s&8)&r", AltManager.Q.size())),
         this.width / 2,
         20,
         -1
      );
      String var9 = ChatFormatting.y(String.format("&7Username: &3%s&r", SessionAccessor.d().getUsername()));
      this.mc.currentScreen.drawString(this.mc.fontRendererObj, var9, 3, 3, -1);
      if (q != null && !q.b()) {
         String var10 = q.o();
         Gui.drawRect(
            this.mc.currentScreen.width / 2 - this.mc.fontRendererObj.getStringWidth(var10) / 2 - 3,
            4,
            this.mc.currentScreen.width / 2 + this.mc.fontRendererObj.getStringWidth(var10) / 2 + 3,
            7 + this.mc.fontRendererObj.FONT_HEIGHT + 2,
            1677721600
         );
         this.mc
            .currentScreen
            .drawCenteredString(
               this.mc.fontRendererObj, q.o(), this.mc.currentScreen.width / 2, 7, -1
            );
      }
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      if (this.I != null && !this.I.isDone()) {
         this.I.cancel(true);
         this.K.shutdownNow();
      }
   }

   public static int W(AccountManagerScreen var0) {
      return var0.G;
   }

   public AccountManagerScreen(long var1, GuiScreen var3, TimedStatusMessage var4) {
      this.i = null;
      this.Q = null;
      this.S = null;
      this.P = null;
      this.G = -1;
      this.K = null;
      this.I = null;
      this.v = var3;
      q = var4;
   }

   public static FontRenderer p(AccountManagerScreen var0) {
      return var0.fontRendererObj;
   }

   public void handleMouseInput() {
      if (this.P != null) {
         this.P.handleMouseInput();
      }

      super.handleMouseInput();
   }

   public AccountManagerScreen(long var1, GuiScreen var3) {
      this.i = null;
      this.Q = null;
      this.S = null;
      this.P = null;
      this.G = -1;
      this.K = null;
      this.I = null;
      this.v = var3;
   }

   public static int C(AccountManagerScreen var0, int var1) {
      return var0.G = var1;
   }

   public void updateScreen() {
      if (this.i != null && this.Q != null) {
         this.Q.enabled = this.G >= 0;
         this.i.enabled = this.Q.enabled;
         if (this.I != null && !this.I.isDone()) {
            this.i.enabled = false;
         }
      }
   }

   private static GuiScreen Z(GuiScreen var0) {
      while (true) {
         if (var0 instanceof AccountManagerScreen) {
            var0 = ((AccountManagerScreen)var0).v;
         } else {
            if (!(var0 instanceof AddAccountScreen)) {
               return var0;
            }

            var0 = ((AddAccountScreen)var0).j();
         }
      }
   }

   public void initGui() {
       try {long var1 = 74020945541135L;

      AltManager.Q(17200, (short)3883, (short)55813);
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.i = new GuiButton(
         0,
         this.width / 2 - 150 - 4,
         this.height - 52,
         150,
         20,
         "Login"
      );
      this.buttonList.add(this.i);
      this.buttonList
         .add(
            new GuiButton(
               1,
               this.width / 2 + 4,
               this.height - 52,
               150,
               20,
               "Add"
            )
         );
      this.Q = new GuiButton(
         2,
         this.width / 2 - 150 - 4,
         this.height - 28,
         150,
         20,
         "Delete"
      );
      this.buttonList.add(this.Q);
      this.S = new GuiButton(
         3,
         this.width / 2 + 4,
         this.height - 28,
         150,
         20,
         "Cancel"
      );
      this.buttonList.add(this.S);
      this.P = new AccountListSlot(58253071927924L, this, this.mc);
      this.P.registerScrollButtons(11, 12);
      this.updateScreen();
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   protected void keyTyped(char var1, int var2) {
      switch (var2) {
         case 1:
            this.actionPerformed(this.S);
            break;
         case 28:
            this.actionPerformed(this.i);
            break;
         case 200:
            if (this.G > 0) {
               this.G--;
               if (isCtrlKeyDown()) {
                  Collections.swap(AltManager.Q, this.G, this.G + 1);
                  AltManager.O(101554584226764L);
               }
            }
            break;
         case 208:
            if (this.G < AltManager.Q.size() - 1) {
               this.G++;
               if (isCtrlKeyDown()) {
                  Collections.swap(AltManager.Q, this.G, this.G - 1);
                  AltManager.O(101554584226764L);
               }
            }
            break;
         case 211:
            this.actionPerformed(this.Q);
      }

      if (isKeyComboCtrlC(var2) && this.G >= 0) {
         setClipboardString(AltManager.Q.get(this.G).h());
      }
   }

   static {
      q = null;
      a = 34290707720760L;
      d = new HashMap(13);
      c = new String[20];
      g = new HashMap(13);
      k = new HashMap(13);
   }
}
