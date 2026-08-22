package Expo.ui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;











public class GuiTextWidget extends Gui {
   private String E;
   private final int S;
   private int r;
   private final int q;
   private boolean s;
   private int F;
   private static long a;
   private final int b;
   private final int x;
   private int o;
   private int M;
   private final FontRenderer B;

   public void V(int var1) {
      this.r = var1;
      if (this.E.length() > var1) {
         this.E = this.E.substring(0, var1);
         this.F = Math.min(this.F, this.E.length());
      }
   }

   public void W(long var1, char var3, int var4) {
      if (this.s) {
         if (GuiScreen.isKeyComboCtrlX(var4)) {
            this.F = this.E.length();
         } else if (GuiScreen.isKeyComboCtrlC(var4)) {
            GuiScreen.setClipboardString(this.E);
         } else if (GuiScreen.isKeyComboCtrlV(var4)) {
            this.J(GuiScreen.getClipboardString());
         } else if (GuiScreen.isKeyComboCtrlA(var4)) {
            GuiScreen.setClipboardString(this.E);
            this.E = "";
            this.F = 0;
         } else {
            switch (var4) {
               case 14:
                  if (!this.E.isEmpty() && this.F > 0) {
                     this.E = this.E.substring(0, this.F - 1) + this.E.substring(this.F);
                     this.F--;
                  }
                  break;
               case 28:
                  this.J("\n");
                  break;
               case 199:
                  this.F = 0;
                  break;
               case 200:
                  if (this.M > 0) {
                     this.M--;
                  }
                  break;
               case 203:
                  if (this.F > 0) {
                     this.F--;
                  }
                  break;
               case 205:
                  if (this.F < this.E.length()) {
                     this.F++;
                  }
                  break;
               case 207:
                  this.F = this.E.length();
                  break;
               case 208:
                  String[] var9 = this.E.split("\\r?\\n", -1);
                  int var10 = this.B.FONT_HEIGHT + 2;
                  int var11 = (this.b - 4) / var10;
                  if (this.M < Math.max(0, var9.length - var11)) {
                     this.M++;
                  }
                  break;
               case 211:
                  if (!this.E.isEmpty() && this.F < this.E.length()) {
                     this.E = this.E.substring(0, this.F) + this.E.substring(this.F + 1);
                  }
                  break;
               default:
                  if (TextInputCharFilter.R(var3)) {
                     this.J(Character.toString(var3));
                  }
            }
         }
      }
   }

   public void z(String var1) {
      this.E = var1.length() > this.r ? var1.substring(0, this.r) : var1;
      this.F = this.E.length();
   }

   public void D(int var1, int var2, int var3) {
      boolean var4 = var1 >= this.S && var1 < this.S + this.x && var2 >= this.q && var2 < this.q + this.b;
      this.r(var4);
      if (var4 && var3 == 0) {
         this.F = this.E.length();
      }
   }

   public String z() {
      return this.E;
   }

   public void h() {
      drawRect(this.S - 1, this.q - 1, this.S + this.x + 1, this.q + this.b + 1, -6250336);
      drawRect(this.S, this.q, this.S + this.x, this.q + this.b, -16777216);
      String[] var3 = this.E.split("\\r?\\n", -1);
      int var4 = this.B.FONT_HEIGHT + 2;
      int var5 = (this.b - 4) / var4;
      int var6 = var3.length;
      if (this.M > Math.max(0, var6 - var5)) {
         this.M = Math.max(0, var6 - var5);
      }

      int var7 = (this.x - 8) / (this.B.FONT_HEIGHT / 2);
      int var8 = 0;

      for (int var9 = this.M; var9 < var3.length && var8 < var5; var9++) {   // add code
         String var10 = var3[var9];


         while (0 < var10.length() && var8 < var5) {
            int var12 = Math.min(0 + var7, var10.length());
            String var13 = var10.substring(0, var12);

            while (this.B.getStringWidth(var13) > this.x - 8 && var13.length() > 0) {
               var13 = var13.substring(0, var13.length() - 1);
            }

            this.B.drawString(var13, this.S + 4, this.q + 4 + var8 * var4, 14737632);
            var8++;
         }

         if (var10.isEmpty()) {   // add code
            var8++;
         }
      }

      if (this.s && this.o / 6 % 2 == 0) {
         String var16 = this.E.substring(0, Math.min(this.F, this.E.length()));
         String[] var17 = var16.split("\\r?\\n", -1);
         int var18 = var17.length - 1;
         String var19 = var17[var17.length - 1];
         int var20 = this.S + 4 + this.B.getStringWidth(var19);
         int var14 = this.q + 4 + (var18 - this.M) * var4;
         if (var18 >= this.M && var18 < this.M + var5) {
            drawRect(var20, var14 - 1, var20 + 1, var14 + this.B.FONT_HEIGHT, -3092272);
         }
      }
   }

   public GuiTextWidget(int var1, FontRenderer var2, int var5, int var6, int var7, int var8) {
      this.E = "";
      this.r = 32;
      this.F = 0;
      this.M = 0;
      this.B = var2;
      this.S = var5;
      this.q = var6;
      this.x = var7;
      this.b = var8;
   }

   public void r(boolean var1) {
      this.s = var1;
   }


   private void J(String var1) {
      StringBuilder var6 = new StringBuilder();

      for (char var10 : var1.toCharArray()) {
         if (var10 == 10 || var10 == 13 || TextInputCharFilter.R(var10)) {
            var6.append(var10);
         }
      }

      String var12 = var6.toString();
      int var13 = this.r - this.E.length();
      if (var13 > 0) {
         if (var12.length() > var13) {
            var12 = var12.substring(0, var13);
         }

         this.E = this.E.substring(0, this.F) + var12 + this.E.substring(this.F);
         this.F = this.F + var12.length();
      }
   }

   public void o() {
      this.o++;
   }

   static {
      a = 89951620145125L;
   }

}
