package Expo.util;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class KeyBindUtil {
   private static long a;

   public static int m(long var0, int var2) {



      return var2 >= 1000 ? w((char)0, var2 - 1000, 132797583844084L) : var2;
   }


   public static void T(int var0, short var1, int var2, short var3) {
      long var4 = ((long)var0 << 32 | (long)var1 << 48 >>> 32 | (long)var3 << 48 >>> 48) ^ a;
      long var6 = var4 ^ 30548274499651L;
      KeyBinding.onTick(m(var6, var2));
   }

   private static boolean isDigit(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         for (int var1 = 0; var1 < var0.length(); var1++) {
            if (!Character.isDigit(var0.charAt(var1))) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean V(int var0, long var1) {


      int var5 = m(32881896332787L, var0);
      if (var5 == 0) {
         return false;
      } else {
         return var5 < 0 ? Mouse.isButtonDown(var5 + 100) : Keyboard.isKeyDown(var5);
      }
   }

   public static int a(long var0, String var2) {



      if (var2 == null) {
         return -2147483648;
      }

      String var6 = var2.trim();
      if (var6.isEmpty()) {
         return -2147483648;
      }

      String var7 = var6.toUpperCase().replace(" ", "").replace("-", "").replace("_", "");
      String var8 = var7;
      int var9 = -1;
      switch (var8.hashCode()) {
         case -1717291789:
            if (var8.equals("MIDDLECLICK")) {
               var9 = 10;
            }
            break;
         case -1707955184:
            if (var8.equals("MIDDLEMOUSE")) {
               var9 = 11;
            }
            break;
         case -899096340:
            if (var8.equals("RIGHTCLICK")) {
               var9 = 7;
            }
            break;
         case -889759735:
            if (var8.equals("RIGHTMOUSE")) {
               var9 = 8;
            }
            break;
         case -292177945:
            if (var8.equals("XBUTTON1")) {
               var9 = 14;
            }
            break;
         case -292177944:
            if (var8.equals("XBUTTON2")) {
               var9 = 17;
            }
            break;
         case 2777:
            if (var8.equals("X1")) {
               var9 = 13;
            }
            break;
         case 2778:
            if (var8.equals("X2")) {
               var9 = 16;
            }
            break;
         case 75489:
            if (var8.equals("LMB")) {
               var9 = 3;
            }
            break;
         case 76450:
            if (var8.equals("MMB")) {
               var9 = 9;
            }
            break;
         case 81255:
            if (var8.equals("RMB")) {
               var9 = 6;
            }
            break;
         case 2402104:
            if (var8.equals("NONE")) {
               var9 = 0;
            }
            break;
         case 64208429:
            if (var8.equals("CLEAR")) {
               var9 = 2;
            }
            break;
         case 78894522:
            if (var8.equals("SIDE1")) {
               var9 = 12;
            }
            break;
         case 78894523:
            if (var8.equals("SIDE2")) {
               var9 = 15;
            }
            break;
         case 237052353:
            if (var8.equals("LEFTCLICK")) {
               var9 = 4;
            }
            break;
         case 246388958:
            if (var8.equals("LEFTMOUSE")) {
               var9 = 5;
            }
            break;
         case 424865381:
            if (var8.equals("UNBOUND")) {
               var9 = 1;
            }
      }

      switch (var9) {
         case 0:
         case 1:
         case 2:
            return 0;
         case 3:
         case 4:
         case 5:
            return w((char)0, 0, 132797583844084L);
         case 6:
         case 7:
         case 8:
            return w((char)0, 1, 132797583844084L);
         case 9:
         case 10:
         case 11:
            return w((char)0, 2, 132797583844084L);
         case 12:
         case 13:
         case 14:
            return w((char)0, 3, 132797583844084L);
         case 15:
         case 16:
         case 17:
            return w((char)0, 4, 132797583844084L);
         default:
            int var11 = Keyboard.getKeyIndex(var6.toUpperCase());
            if (var11 != 0) {
               return var11;
            } else {
               int var12 = Keyboard.getKeyIndex(var7);
               if (var12 != 0) {
                  return var12;
               } else if (var7.startsWith("MOUSE") && isDigit(var7.substring(5))) {
                  var9 = Integer.parseInt(var7.substring(5)) - 1;
                  return var9 >= 0 ? w((char)0, var9, 132797583844084L) : -2147483648;
               } else if (var7.startsWith("MB") && isDigit(var7.substring(2))) {
                  var9 = Integer.parseInt(var7.substring(2)) - 1;
                  return var9 >= 0 ? w((char)0, var9, 132797583844084L) : -2147483648;
               } else if (var7.startsWith("BUTTON") && isDigit(var7.substring(6))) {
                  var9 = Integer.parseInt(var7.substring(6));
                  return var9 >= 0 ? w((char)0, var9, 132797583844084L) : -2147483648;
               } else {
                  return -2147483648;
               }
            }
      }
   }

   public static void h(long var0) {
      var0 = a ^ var0;
      int var2 = (int)((var0 ^ 51524578116599L) >>> 32);
      int var3 = (int)((var0 ^ 51524578116599L) << 32 >>> 48);
      int var4 = (int)((var0 ^ 51524578116599L) << 48 >>> 48);
      int var5 = (int)((var0 ^ 14748452643353L) >>> 56);
      T(var2, (short)var3, MinecraftRef.c((byte)var5,0L).gameSettings.keyBindUseItem.getKeyCode(), (short)var4);
   }

   static {

      a = 119759750653330L;
   }

   public static int w(char var0, int var1, long var2) {
      return -100 + var1;
   }


   public static int x(int var0, long var1) {
      long var3 = var1 ^ 69080664825661L;
      int var5 = m(var3, var0);
      return var5 < 0 ? var5 + 100 : -1;
   }

   public static boolean t(int var0, long var1) {
      long var3 = var1 ^ 54027585699662L;
      return m(var3, var0) < 0;
   }

   public static void A(long var0, int var2, boolean var3) {


      KeyBinding.setKeyBindState(m(32881896332787L, var2), var3);
   }

   public static void o(long var0, int var2) {
      var0 = a ^ var0;
      long var3 = var0 ^ 136490486805416L;
      long var5 = var0 ^ 47633413435070L;
      int var7 = m(var5, var2);
      A(var3, var7, var7 < 0 ? Mouse.isButtonDown(var7 + 100) : Keyboard.isKeyDown(var7));
   }

   public static boolean d(int var0, int var1, long var2) {


      return m(32881896332787L, var0) == m(32881896332787L, var1);
   }

   public static String p(long var0, char var2, int var3) {
      long var4 = (var0 << 16 | (long)var2 << 48 >>> 48) ^ a;
      long var6 = var4 ^ 36269996137671L;
      long var8 = var4 ^ 33160842740730L;
      int var10 = m(var8, var3);
      if (var10 == 0) {
         return "NONE";
      } else if (var10 < 0) {
         int var12 = x(var10, var6);
         return var12 < 0 ? "UNKNOWN" : "MOUSE" + (var12 + 1);
      } else {
         String var11 = Keyboard.getKeyName(var10);
         return var11 != null && !var11.trim().isEmpty() ? var11 : "UNKNOWN";
      }
   }

}
