package Expo.internal.jnic;

import com.sun.jna.Library;
import com.sun.jna.WString;











interface NativeGuardLibrary extends Library {
   int z(long var1, WString var3, WString var4, int var5);
}
