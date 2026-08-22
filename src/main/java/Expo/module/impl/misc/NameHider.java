package Expo.module.impl.misc;

import Expo.module.Category;

import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.setting.settings.TextSetting;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;











public class NameHider extends Module {
   private static final long a = 124618384406252L;
   public static TextSetting name;
   private static final Minecraft n;

   public NameHider(long var1) {
      super(((a ^ (var1)) ^ 80695783786002L));
      // add code
      this.declare("NameHider", Category.Misc, "Replace all string that matches your name");
      var1 = a ^ var1;
   }

   public static String U(String var0) {
      if (n.thePlayer == null || ModuleManager.J == null || var0 == null) {
         return var0;
      } else {
         return ModuleManager.J.o() ? var0.replace(n.thePlayer.getName(), name.X()) : var0;
      }
   }

   static {
      int var2 = 0;
      n = MinecraftRef.c((byte)var2,0L);
   }
   static {
      // add code
      name = new TextSetting("Name", "You");
   }
}
