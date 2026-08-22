package Expo.module.impl.movement;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.NoJumpDelayBinder;
import Expo.event.events.PreTickEvent;
import Expo.internal.accessor.EntityLivingBaseStateAccessor;
import Expo.module.Module;
import Expo.setting.settings.NumberSetting;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class NoJumpDelay extends Module implements EventSubscriber {
   private static final long a = 43870994421215L;
   public static NumberSetting jumpTicks;

   private static void a() {
   }

   public void onPreTick(PreTickEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      EntityLivingBaseStateAccessor.x(14848, f.thePlayer, Math.min(EntityLivingBaseStateAccessor.C( f.thePlayer), (int)jumpTicks.L() + 1));
   }

   static {
      a();
   }

   public NoJumpDelay(long var1) {
      super(((a ^ (var1)) ^ 128982966657112L));
      this.declare("NoJumpDelay", Category.Movement, "Remove vanilla hold-space jump delay");
      var1 = a ^ var1;
   }

   public final void x(long var1, EventBus var3) {
      NoJumpDelayBinder.T(var3, this);
   }

   public String g(long var1) {
      return String.valueOf((int)jumpTicks.L());
   }
   static {
      jumpTicks = new NumberSetting("Jump-ticks", 0.0F, 0.0F, 10.0F, 1.0F);
   }
}
