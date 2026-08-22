package Expo.internal.restore;

import Expo.ExpoClient;
import Expo.event.EventBus;
import java.util.ArrayList;
import java.util.List;


public final class ExpoAzPump {

   private ExpoAzPump() {
   }

   public static ExpoClient INSTANCE;

   public static final List<String> DEGRADED = new ArrayList<String>();

   public static ExpoClient install(EventBus var0, long var1, List<String> var2, List<String> var3) {
      if (var0 == null) {
         throw new IllegalStateException("ExpoAzPump: null bus");
      }

      if (INSTANCE != null) {
         throw new IllegalStateException("ExpoAzPump: already installed");
      }

      ExpoClient var4 = new ExpoClient(0, (char)0, 0);
      var0.s(var4, 0L);
      INSTANCE = var4;

      if (var2 != null) {
         var2.add("Expo.ExpoClient");
      }

      DEGRADED.clear();
      DEGRADED.add("Expo/zT_3.F : LExpo/ModeSetting; -- 3 getstatic / 0 putstatic; "
                   + "read unguarded at AZ.java:476 (AZ.q@150, AZ.r@534)");
      DEGRADED.add("Expo/zo_4.t : LExpo/qD; and Expo/zo_4.x : LExpo/qk; -- 0 putstatic; "
                   + "read at AZ.java:482 behind zo_4.n(long)");

      if (var3 != null) {
         var3.add("Expo.ExpoClient    SUBSCRIBED (module pump live). DEGRADED: "
                  + "zT_3.F and zo_4.t/.x are native-written statics that are still null "
                  + "(stage1 item 8), so AZ.r throws a caught, rate-limited NPE after the "
                  + "module loop; the pump itself completes. Fill them to clear this.");
      }

      return var4;
   }
}
