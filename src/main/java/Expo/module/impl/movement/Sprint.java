package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.SprintBinder;
import Expo.event.events.PreUpdateEvent;
import Expo.module.Module;
import Expo.module.Modules;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;











public class Sprint extends Module implements EventSubscriber {
   private static String[] d;
   private static long b;
   private static Object[] c;
   private static Minecraft H;
   private static long a;

   public static boolean U(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return H.thePlayer.moveForward > 0.0F
         && (Modules.J(NoSlow.class).o() && NoSlow.c(0L) || !H.thePlayer.isUsingItem())
         && !H.thePlayer.isSneaking()
         && !H.thePlayer.isCollidedHorizontally
         && H.thePlayer.getFoodStats().getFoodLevel() > (int)b;
   }

   public Sprint(short var1, int var2, short var3) {
      super(((((((long)((var1)) << 48) | (((long)((var2)) << 32) >>> 16)) | (((long)((var3)) << 48) >>> 48)) ^ a) ^ 84177168357013L));
      // add code
      this.declare("Sprint", Category.Movement, "Automatically sprint");
   }

   static {
      a = 130957744460578L;
      // add code
      H = MinecraftRef.c((byte)0, 0L);
      c = new Object[7];
      d = new String[7];
      b = 3706994347716116486L;
   }

   public final void x(long var1, EventBus var3) {
      SprintBinder.J(var3, this);
   }

   private static void a() {
      c[0] = "<c{{J&\u0017";
      c[1] = long.class;
      d[1] = "java/lang/Long";
      c[2] = "\u001c\u0002?#$U+\u0015;)iq<\u001ea5";
      c[3] = "Y(\"\u0004C\u001ax";
      c[4] = void.class;
      d[4] = "java/lang/Void";
      c[5] = "i c\u001c``b/rS\u0001ni$v\t";
      c[6] = "A1\u0015\u0007Y\bM}|#)Q\u0012?\u0017PK\u0004\u0017k\u0001a\u0010\fPbAZ[XW?|[KVU9E\u001e\u0014XM\u0000G\fH\tR?C\u0011PU,";
   }

   public void onPreUpdate(short var1, PreUpdateEvent var2, char var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = ((long)var1 << 48 | (long)var3 << 48 >>> 16 | (long)var4 << 32 >>> 32) ^ a;
      long var9 = var5 ^ 137397600751571L;
      if (H.thePlayer.isUsingItem()) {
         if (U(0L)) {
            H.thePlayer.setSprinting(true);
         }
      } else {
         KeyBindUtil.A(var9, H.gameSettings.keyBindSprint.getKeyCode(), true);
      }
   }

   public void A(long var1) {
      long var3 = var1 ^ 34459795889248L;
      KeyBindUtil.A(var3, H.gameSettings.keyBindSprint.getKeyCode(), H.gameSettings.keyBindSprint.isPressed());
   }

}
