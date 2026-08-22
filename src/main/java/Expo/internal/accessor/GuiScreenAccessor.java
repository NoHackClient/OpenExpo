package Expo.internal.accessor;

import java.io.IOException;
import net.minecraft.client.gui.GuiScreen;

public final class GuiScreenAccessor {
   private static Accessor V;
   private static Accessor e;

   public static void c(GuiScreen var0, int var1, int var2, int var3) {
      Accessor.v(V, new Object[]{var0, var1, var2, var3});
   }

   public static void J(GuiScreen var0, char var1, int var2) throws IOException {
      try {
         Accessor.v(e, new Object[]{var0, var1, var2});
      } catch (RuntimeException var4) {
         throw MethodAccessors.H(var4);
      }
   }

   static {
      V = MethodAccessors.G(GuiScreen.class, "mouseClicked", "mouseClicked", new Class[]{int.class, int.class, int.class});
      e = MethodAccessors.G(GuiScreen.class, "keyTyped", "keyTyped", new Class[]{char.class, int.class});
   }
}
