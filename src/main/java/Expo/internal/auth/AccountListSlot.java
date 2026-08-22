package Expo.internal.auth;

import Expo.enums.AccountType;
import Expo.ui.screen.AccountManagerScreen;
import Expo.util.ChatFormatting;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiSlot;
import org.apache.commons.lang3.StringUtils;

public class AccountListSlot extends GuiSlot {
   private static String[] b;
   final AccountManagerScreen u;
   private static long a;
   private static Map d;
   private static long[] h;
   private static String[] c;

   public int getListWidth() {
      return 308;
   }

   protected int getSize() {
      return AltManager.Q.size();
   }

   protected int getContentHeight() {
      return AltManager.Q.size() * 16;
   }

   protected void drawBackground() {
      this.u.drawDefaultBackground();
   }

   protected void elementClicked(int var1, boolean var2, int var3, int var4) {
      AccountManagerScreen.C(this.u, var1);
      this.u.updateScreen();
      if (var2) {
         this.u.actionPerformed(AccountManagerScreen.L(this.u));
      }
   }

   protected void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      FontRenderer var13 = AccountManagerScreen.p(this.u);
      Account var14 = AltManager.Q.get(var1);
      String var15 = var14.h();
      if (StringUtils.isBlank(var15)) {
         var15 = "&7&l?";
      }

      if (SessionAccessor.d() != null) {
         if (var14.v() == AccountType.OFFLINE && var15.equals(SessionAccessor.d().getUsername())) {
            var15 = String.format("&a&l%s", var15);
         } else if (var14.v() == AccountType.MINECRAFT && var14.h().equals(SessionAccessor.d().getUsername())) {
            var15 = String.format("&a&l%s", var15);
         }
      }

      String var16 = var14.v() == AccountType.OFFLINE ? " &7(Offline)" : " &7(Minecraft)";
      String var17 = ChatFormatting.y(String.format("&r%s", var15));
      String var18 = ChatFormatting.y(var16);
      this.u.drawString(var13, var17, var2 + 2, var3 + 2, -1);
      this.u.drawString(var13, var18, var2 + 2 + var13.getStringWidth(var17), var3 + 2, -1);
      long var19 = System.currentTimeMillis();
      long var21 = var14.F();
      String var12;
      if (var21 < 0L) {
         var12 = "&4&l⚠";
      } else if (var21 <= var19) {
         var12 = "&2&l✔";
      } else {
         long var23 = var21 - var19;
         long var25 = var23 / 1000L % 60L;
         long var27 = var23 / 60000L % 60L;
         long var29 = var23 / 3600000L % 24L;
         long var31 = var23 / 86400000L;
         var12 = String.format(
            "%s%s%s%s",
            var31 > 0L ? String.format("%dd", var31) : "",
            var29 > 0L ? String.format(" %dh", var29) : "",
            var27 > 0L ? String.format(" %dm", var27) : "",
            var25 > 0L ? String.format(" %ds", var25) : ""
         );
         var12 = var12.trim();
         var12 = String.format("%s &c&l⚠", var12);
      }

      var12 = ChatFormatting.y(String.format("&r%s&r", var12));
      this.u.drawString(var13, var12, var2 + this.getListWidth() - 5 - var13.getStringWidth(var12), var3 + 2, -1);
   }

   protected boolean isSelected(int var1) {
      return var1 == AccountManagerScreen.W(this.u);
   }

   static {
      a = 66617801163655L;
   }

   protected int getScrollBarX() {
      return (this.width + this.getListWidth()) / 2 + 2;
   }

   public AccountListSlot(long var1, AccountManagerScreen var3, Minecraft var4) {
      super(
         var4,
         var3.width,
         var3.height,
         32,
         var3.height - 64,
         16
      );
      this.u = var3;
   }
}
