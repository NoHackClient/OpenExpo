package Expo.setting.settings;

import Expo.setting.Setting;
import java.awt.Color;











public class ColorSetting extends Setting {
   private String Q;
   private static long a;

   public int x( int var3) {
      Color var4 = new Color(Integer.parseInt(this.Q, 16));
      return new Color(var4.getRed(), var4.getGreen(), var4.getBlue(), var3).getRGB();
   }

   public void e(String var1) {
      this.Q = var1;
   }


   public ColorSetting(String var1, String var2) {
      this.q = var1;
      this.Q = var2;
   }

   static {
      // add code

      a = 73137888266447L;
   }

   public String Q() {
      return this.Q;
   }

   public int k(long var1) {
      Color var3 = new Color(Integer.parseInt(this.Q, 16));
      return new Color(var3.getRed(), var3.getGreen(), var3.getBlue(), 255).getRGB();
   }

}
