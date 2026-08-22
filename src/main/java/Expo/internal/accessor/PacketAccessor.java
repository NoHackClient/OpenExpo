package Expo.internal.accessor;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.minecraft.network.Packet;

public class PacketAccessor {
   private static long[] b;
   public static Set<Class<?>> m;
   private static Map d;
   private static long a;
   public static Set<Class<?>> U;

   static {
      U = Collections.<Class<?>>singleton(Packet.class);
      m = Collections.<Class<?>>singleton(Packet.class);
   }

   public static native void K(long var0);
}
