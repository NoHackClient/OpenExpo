package Expo.ASM.Hooks;

public class CallbackInfoReturnable<T> extends CallbackInfo {
   private T b;

   public CallbackInfoReturnable() {
   }

   public CallbackInfoReturnable(T var1) {
      this.b = (T)var1;
   }

   public void setReturnValue(T var1) {
      this.b = (T)var1;
      this.cancel();
   }

   public T getReturnValue() {
      return this.b;
   }
}
