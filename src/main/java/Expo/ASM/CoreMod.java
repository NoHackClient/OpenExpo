package Expo.ASM;

import Expo.ASM.Util.AsmUtil;
import Expo.util.Sneaky;
import java.util.Map;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;











@MCVersion("1.8.9")
@TransformerExclusions("Expo.ASM")
@SortingIndex(Integer.MAX_VALUE)
public class CoreMod implements IFMLLoadingPlugin {
   public String getSetupClass() {
      return CoreModCallHook.class.getName();
   }

   public void injectData(Map<String, Object> var1) {
      Object var2 = var1.get("runtimeDeobfuscationEnabled");
      AsmUtil.O(var2 instanceof Boolean && (Boolean)var2);
   }

   public String getModContainerClass() {
      return null;
   }

   public String[] getASMTransformerClass() {
       try {TransformerOrdering.L();
      return new String[]{EncryptedClassMarkerHook.class.getName(), ClassNameFilterTransformer.class.getName()};
   } catch (Throwable zkm$t) { throw Sneaky.rethrow(zkm$t); } }

   public String getAccessTransformerClass() {
      return null;
   }

}
