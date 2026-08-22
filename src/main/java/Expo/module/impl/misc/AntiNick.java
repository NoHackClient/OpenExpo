package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AntiNickBinder;
import Expo.event.events.PlayerGetNameEvent;
import Expo.module.Module;
import Expo.setting.settings.TextSetting;
import com.mojang.authlib.GameProfile;
import java.util.UUID;

public class AntiNick extends Module implements EventSubscriber {
   private static final long a = 106963681923233L;
   public static TextSetting suffix;

   public final void x(long var1, EventBus var3) {
      AntiNickBinder.I(var3, this);
   }

   static {
      a();
   }

   public void onPlayerGetName(PlayerGetNameEvent var1) {
      GameProfile var2 = var1.u.getGameProfile();
      if (isVersion(var2.getId())) {
         var1.N(" " + suffix.X());
      }
   }

   public AntiNick(long var1) {
      super(((a ^ (var1)) ^ 50917928111327L));
      this.declare("AntiNick", Category.Misc, "Allows you to see if any player is nicked");
      var1 = a ^ var1;
   }

   private static boolean isVersion(UUID var0) {
      return var0.version() == 1;
   }

   private static void a() {
   }
   static {
      suffix = new TextSetting("Suffix", "\u00a7l\u00a7e[\u00a7l\u00a7eNick\u00a7r\u00a7l\u00a7e]\u00a7r");
   }
}
