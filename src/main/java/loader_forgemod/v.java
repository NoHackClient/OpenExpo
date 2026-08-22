package loader_forgemod;

class v {
   private final byte[] a = new byte[32];

   v() {
   }

   v(v var1) {
      System.arraycopy(var1.a, 0, this.a, 0, this.a.length);
   }

   native void a(String var1, int var2);

   native void b(int var1);

   native int c(Object var1);
}
