package Expo.ui.raven;

import Expo.setting.Setting;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface RavenElement {
   long e = 66652673112161L;

   default void V(long var1, int var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
   }

   default void f(int var1, int var2, int var3, int var4, short var5, int var6) {
   }

   default void i(int var1, int var2, int var3, byte var4) {
   }

   default void c(char var1, int var2, long var3) {
   }

   default Setting f() {
      return null;
   }

   default CustomFont C(long var1) {
      return RavenClickGuiScreen.t();
   }

   default void r(char var1, int var2, int var3, int var4, long var5) throws UnsupportedEncodingException, Throwable, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
   }

   default void c(int var1, long var2, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.V(0L, var1, var4);
   }

   default void W(long var1) {
   }

   default void U(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
   }

   RavenModuleRow C();

   default int E(long var1) {
      return 0;
   }
}
