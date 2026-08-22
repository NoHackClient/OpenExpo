package Expo.ASM.Hooks.Block;

import java.util.Set;
import net.minecraft.init.Blocks;

public class BlockStateMapperHooks {
   public static boolean getRenderType(Set var0, Object var1) {
      return var1 != Blocks.barrier && var0.contains(var1);
   }
}
