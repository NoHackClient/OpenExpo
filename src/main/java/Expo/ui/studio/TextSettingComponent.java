package Expo.ui.studio;

import Expo.setting.settings.TextSetting;
import Expo.util.Animation;
import Expo.util.render.CustomFont;
import Expo.util.render.FontUtil;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;

public class TextSettingComponent extends AbstractSettingComponent<TextSetting> {
   private final Animation s;
   private boolean m;
   private String K;
   private static long a;

   public boolean V(long var1, float var3, float var4, int var5) {
      long var6 = var1 ^ 44349559506038L;
      float var8 = this.n + 7.0F;
      float var9 = this.J + 11.2F;
      float var10 = this.C - 14.0F;
      if (var5 == 0 && this.G(var3, var4, var8, var9, var10, 6.6F)) {
         this.M.J(var6, this);
         return true;
      } else {
         return false;
      }
   }

   public TextSettingComponent(StudioClickGuiScreen var1, StudioModuleFrame var2, long var3, TextSetting var5) {
      super((var1), (var2), (var5), ((a ^ (var3)) ^ 48917550974598L));
      var3 = a ^ var3;
      this.s = new Animation(0.0F);
      this.K = "";
   }

   public void h(int var1, char var2, int var3, char var4, short var5) {
      long var6 = ((long)var1 << 32 | (long)var4 << 48 >>> 32 | (long)var5 << 48 >>> 48) ^ a;
      long var10 = var6 ^ 5752799222225L;
      if (this.m) {
         if (var3 == 1) {
            this.U(0L, true);
            this.M.n(this, var10);
         } else if (var3 == 28 || var3 == 156) {
            this.U(0L, true);
            this.M.n(this, var10);
         } else if (var3 == 14) {
            if (!this.K.isEmpty()) {
               this.K = this.K.substring(0, this.K.length() - 1);
            }
         } else if (GuiScreen.isCtrlKeyDown() && var3 == 47) {
            String var12 = GuiScreen.getClipboardString();
            if (var12 != null) {
               this.K = this.K + var12;
            }
         } else {
            if (ChatAllowedCharacters.isAllowedCharacter(var2)) {
               this.K = this.K + var2;
            }
         }
      }
   }

   public boolean V() {
      return this.m;
   }

   public float O() {
      return 20.8F;
   }

   public void k(long var1) {
      this.U(0L, true);
   }

   public void c(long var1) {
      this.m = true;
      this.K = this.O.X();
   }

   public void U(long var1, boolean var3) {
      if (this.m) {
         if (var3) {
            this.O.O(this.K);
         }

         this.m = false;
         this.K = "";
      }
   }

   public void Z(int var1, float var2, char var3, float var4, float var5, float var6, short var7, CategoryPalette var8) {
      long var9 = (long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var7 << 48 >>> 48;
      long var10001 = var9 ^ 8261096794773L;
      int var11 = (int)((var9 ^ 8261096794773L) >>> 32);
      int var12 = (int)((var9 ^ 8261096794773L) << 32 >>> 48);
      int var13 = (int)(var10001 << 48 >>> 48);
      long var14 = var9 ^ 100216898476969L;
      var10001 = var9 ^ 42529401121054L;
      int var16 = (int)((var9 ^ 42529401121054L) >>> 48);
      int var17 = (int)((var9 ^ 42529401121054L) << 16 >>> 48);
      int var18 = (int)(var10001 << 32 >>> 32);
      int var19 = (int)((var9 ^ 28952866874662L) >>> 56);
      long var20 = (var9 ^ 28952866874662L) << 8 >>> 8;
      long var22 = (var9 ^ 24899146723189L) >>> 32;
      int var24 = (int)((var9 ^ 24899146723189L) << 32 >>> 32);
      long var25 = var9 ^ 131370279024570L;
      var10001 = var9 ^ 101287543088699L;
      int var27 = (int)((var9 ^ 101287543088699L) >>> 32);
      int var28 = (int)((var9 ^ 101287543088699L) << 32 >>> 48);
      CustomFont var32 = FontUtil.n(var22, var24);
      this.s.d(this.I(var2, var4) ? 1.0F : 0.0F);
      this.s.y(0.28F, this.M.y());
      RenderUtil.j(this.n, this.J, this.n + this.C, this.J + this.O(), 3.0F, var25, this.F(var8, (char)var16, (short)var17, this.s.b(var5), var6, var18));
      String var10002 = FontUtil.Q(var11, var32, this.O.e((byte)var19, this.S, var20), (short)var12, (char)var13, this.C - 12.0F, 0.66F);
      float var10003 = this.n + 6.0F;
      float var10004 = this.J + 2.8F;
      Color var10006 = new Color(239, 244, 251);
      float var30 = var6;
      Color var31 = var10006;
      FontUtil.N(var32, var14, var10002, var10003, var10004, 0.66F, FontUtil.a(var27, var28, var31, var30));
      float var33 = this.n + 7.0F;
      float var34 = this.J + 11.2F;
      float var35 = this.C - 14.0F;
      float var36 = 6.6F;
      float var44 = var33 + var35;
      var10003 = var34 + var36;
      Color var10005 = new Color(22, 28, 39);
      var30 = var6;
      var31 = var10005;
      RenderUtil.j(var33, var34, var44, var10003, 2.3F, var25, FontUtil.a(var27, var28, var31, var30));
      String var37 = this.m
         ? this.K + (System.currentTimeMillis() / 420L % 2L == 0L ? "_" : "")
         : this.O.X();
      var10002 = FontUtil.Q(var11, var32, var37, (short)var12, (char)var13, var35 - 6.0F, 0.66F);
      var10003 = var33 + 3.0F;
      var10004 = var34 + 1.15F;
      var10006 = new Color(205, 215, 235);
      var30 = var6;
      var31 = var10006;
      FontUtil.N(var32, var14, var10002, var10003, var10004, 0.66F, FontUtil.a(var27, var28, var31, var30));
   }

   static {
      a = 106220699075492L;
   }   }
