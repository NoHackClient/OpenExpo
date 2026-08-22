package Expo.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;











public class ClipPlayer {
   public static void x(String var0) {
      try (InputStream var1 = ClipPlayer.class.getResourceAsStream(var0)) {
         if (var1 != null) {
            BufferedInputStream var3 = new BufferedInputStream(var1);
            AudioInputStream var4 = AudioSystem.getAudioInputStream(var3);
            Clip var5 = AudioSystem.getClip();
            var5.open(var4);
            var5.start();
         }
      } catch (Exception var17) {
         Expo.internal.restore.ExpoDiag.attribute(var17, "ClipPlayer.x/1#0");
      }
   }
}
