package Expo.event;









public final class ListenerBinding<E> {
   private final int Q;
   private final EventInvoker<E> p;
   private volatile boolean z;

   public static int k(ListenerBinding var0) {
      return var0.Q;
   }

   public static EventInvoker d(ListenerBinding var0) {
      return var0.p;
   }

   public static boolean o(ListenerBinding var0) {
      return var0.z;
   }

   public ListenerBinding(EventInvoker<E> var1, int var2) {
      this.p = var1;
      this.Q = var2;
   }

   public static boolean S(ListenerBinding var0, boolean var1) {
      return var0.z = var1;
   }
}
