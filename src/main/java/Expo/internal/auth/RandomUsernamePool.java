package Expo.internal.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;











public class RandomUsernamePool {
   private static Random r;
   private static long a;
   private static String[] b;

   private static String z(String var2, String var3) {
      int var4 = r.nextInt(4);
      switch (var4) {
         case 0:
            return var2 + "_" + var3;
         case 1:
            String var10 = var3.length() >= 2 ? var3.substring(0, 2) : var3;
            return var2 + var10 + r.nextInt(100);
         case 2:
            int var9 = r.nextInt(Math.min(var2.length(), var3.length()) + 1);
            return var2.substring(0, var9) + "_" + var3.substring(var9);
         case 3:
            StringBuilder var6 = new StringBuilder(var2).append(var3);
            if (var6.length() == 0) {
               return "";
            }

            int var7 = r.nextInt(var6.length() + 1);
            int var5;
            if (var7 < (var5 = r.nextInt(var6.length() + 1))) {
               var6.insert(var5, (int)r.nextInt(100));
               var6.insert(var7, "_");
            } else {
               var6.insert(var7, "_");
               var6.insert(var5, (int)r.nextInt(100));
            }

            return var6.toString();
         default:
            return var2 + var3;
      }
   }

   public static String[] z(int var0, long var1) {
      String[] var9 = Q();
      if (var9 != null && var9.length != 0) {
         List var10 = Arrays.stream(var9).filter(var0x -> {
            return var0x.length() >= 3 && var0x.length() <= 6;
         }).collect(Collectors.toList());
         if (var10.isEmpty()) {
            System.err.println("Warning: No words matching the length criteria (3-6 characters) found in the local username file.");
            return null;
         }

         String[] var11 = new String[var0];

         for (int var12 = 0; var12 < var0; var12++) {
            String var13 = (String)var10.get(r.nextInt(var10.size()));
            String var14 = (String)var10.get(r.nextInt(var10.size()));
            String var15 = z(var13, var14);
            if ((var15 = x(0L, var15)).length() > 16) {
               var15 = var15.substring(0, 16);
            }

            var11[var12] = var15;
         }

         return var11;
      } else {
         return null;
      }
   }



   private static String x(long var0, String var2) {
      if (var2 != null && !var2.isEmpty()) {
         double var5 = 0.125;
         double var7 = 0.25;
         char[] var9 = var2.toCharArray();

         for (int var10 = 0; var10 < var9.length; var10++) {
            char var11 = var9[var10];
            if (Character.isLetter(var11)
               && (var10 == 0 || var9[var10 - 1] == 95 || Character.isDigit(var9[var10 - 1]))
               && r.nextDouble() < var7) {
               var9[var10] = Character.toUpperCase(var11);
            } else {
               char var12 = Character.toLowerCase(var11);
               char var13 = L(var12);
               if (var13 != var12 && r.nextDouble() < var5) {
                  var9[var10] = var13;
                  var5 *= 0.5;
               }
            }
         }

         return new String(var9);
      } else {
         return var2;
      }
   }



   static {
      a = 44449083093767L;
      r = new Random();
   }

   public static String[] Q() {

      try {
         InputStream var3 = RandomUsernamePool.class.getResourceAsStream("/usernames.txt");
         if (var3 == null) {
            System.err.println("Error: Local username file not found! Please ensure /usernames.txt exists in the resources directory.");
            return null;
         }

         BufferedReader var4 = new BufferedReader(new InputStreamReader(var3));
         StringBuilder var5 = new StringBuilder();

         String var2;
         while ((var2 = var4.readLine()) != null) {
            if (!var2.trim().isEmpty()) {
               var5.append(var2.trim()).append(System.lineSeparator());
            }
         }

         var4.close();
         if (var5.length() == 0) {
            System.err.println("Error: Local username file /usernames.txt is empty or contains no valid content.");
            return null;
         } else {
            return var5.toString().split(System.lineSeparator());
         }
      } catch (IOException var6) {
         System.err.println("Error reading local username file: " + var6.getMessage());
         var6.printStackTrace();
         return null;
      }
   }

   private static char L(char var2) {
      if (var2 == 97) {
         return (char)52;
      } else if (var2 == 101) {
         return (char)51;
      } else if (var2 == 105) {
         return (char)49;
      } else if (var2 == 111) {
         return (char)48;
      } else if (var2 == 116) {
         return (char)55;
      } else {
         return (char)(var2 == 115 ? 53 : var2);
      }
   }



   public static String x(long var0) {


      String[] var4 = z(1, 67153725001959L);
      return var4 != null && var4.length != 0 ? var4[0] : null;
   }


}
