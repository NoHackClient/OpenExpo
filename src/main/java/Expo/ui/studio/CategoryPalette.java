package Expo.ui.studio;

import Expo.internal.synthetic.CategoryPaletteSwitchMapCategory;
import Expo.module.Category;
import java.awt.Color;

public class CategoryPalette {
   public final Color i;
   private static long a;
   public final Color D;
   public final Color g;

   public CategoryPalette(Color var1, Color var2, Color var3) {
      this.i = var1;
      this.g = var2;
      this.D = var3;
   }

   static {
      a = 96952875612767L;
   }

   public static CategoryPalette o(Category var2) {
      switch (CategoryPaletteSwitchMapCategory.S[var2.ordinal()]) {
         case 1:
            return new CategoryPalette(
               new Color(255, 98, 118),
               new Color(255, 170, 120),
               new Color(94, 49, 61)
            );
         case 2:
            return new CategoryPalette(
               new Color(73, 220, 201),
               new Color(90, 155, 255),
               new Color(34, 68, 80)
            );
         case 3:
            return new CategoryPalette(
               new Color(110, 214, 126),
               new Color(183, 245, 132),
               new Color(40, 72, 49)
            );
         case 4:
            return new CategoryPalette(
               new Color(255, 193, 88),
               new Color(255, 133, 100),
               new Color(79, 58, 36)
            );
         case 5:
            return new CategoryPalette(
               new Color(116, 200, 255),
               new Color(132, 152, 255),
               new Color(44, 59, 86)
            );
         case 6:
            return new CategoryPalette(
               new Color(150, 133, 255),
               new Color(255, 126, 223),
               new Color(61, 48, 85)
            );
         case 7:
            return new CategoryPalette(
               new Color(255, 139, 140),
               new Color(255, 196, 115),
               new Color(84, 56, 52)
            );
         case 8:
            return new CategoryPalette(
               new Color(182, 191, 214),
               new Color(131, 141, 163),
               new Color(58, 63, 76)
            );
         default:
            return new CategoryPalette(
               new Color(208, 162, 94),
               new Color(255, 209, 130),
               new Color(79, 63, 33)
            );
      }
   }
}
