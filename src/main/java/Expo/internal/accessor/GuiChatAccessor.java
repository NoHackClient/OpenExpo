package Expo.internal.accessor;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;











public final class GuiChatAccessor {
   private static TypedValueStore T;

   static {
      T = FieldAccessors.X(GuiChat.class, "inputField", "inputField");
   }



   public static GuiTextField z(char var0, char var1, int var2, GuiChat var3) {
      return (GuiTextField)T.v(var3);
   }

}
