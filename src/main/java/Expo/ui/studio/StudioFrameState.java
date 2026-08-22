package Expo.ui.studio;

import java.util.HashMap;
import java.util.Map;











public class StudioFrameState {
   public float J;
   public boolean S;
   private final Map<String, Boolean> f = new HashMap<>();
   public float h;

   public void W(String var1, boolean var2) {
      if (var1 != null && !var1.isEmpty()) {
         if (var2) {
            this.f.put(var1, true);
         } else {
            this.f.remove(var1);
         }
      }
   }

   public Map<String, Boolean> W() {
      return this.f;
   }

   public StudioFrameState(float var1, float var2, boolean var3) {
      this.J = var1;
      this.h = var2;
      this.S = var3;
   }

   public boolean v(String var1) {
      return this.f.getOrDefault(var1, false);
   }

   public void j() {
      this.f.clear();
   }
}
