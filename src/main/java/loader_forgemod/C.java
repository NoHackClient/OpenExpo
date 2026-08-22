package loader_forgemod;

public class C extends RuntimeException {
   private final o z;

   C(String var1, o var2) {
      super(var1 + " at " + var2);
      this.z = var2;
   }

   public native o bb();

   @Deprecated
   public native int bc();

   @Deprecated
   public native int bd();

   @Deprecated
   public native int be();
}
