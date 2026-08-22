package Expo.ASM;

import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

public class CoreModCallHook implements IFMLCallHook {
   @Override
   public Void call() {
      TransformerOrdering.E();
      TransformerOrdering.Q();
      return null;
   }

   public void injectData(Map<String, Object> var1) {
   }
}
