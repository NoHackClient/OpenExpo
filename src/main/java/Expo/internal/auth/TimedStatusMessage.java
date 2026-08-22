package Expo.internal.auth;

public class TimedStatusMessage {
   private final long C;
   private final String g;
   private final long t;

   public boolean b() {
      return this.t >= 0L && this.t < System.currentTimeMillis() - this.C;
   }

   public String o() {
      return this.g;
   }

   public TimedStatusMessage(String var1, long var2) {
      this.g = var1;
      this.t = var2;
      this.C = System.currentTimeMillis();
   }
}
