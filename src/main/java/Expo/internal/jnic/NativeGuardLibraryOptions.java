package Expo.internal.jnic;

import java.util.HashMap;


final class NativeGuardLibraryOptions extends HashMap<String, Object> {
   private static String b;
   private static long a;



   NativeGuardLibraryOptions(long var1) {
      var1 = a ^ var1;
      this.put(b, NativeGuardLibraryHolder.P);
   }

   static {
      a = 126135751934270L;
   }



}
