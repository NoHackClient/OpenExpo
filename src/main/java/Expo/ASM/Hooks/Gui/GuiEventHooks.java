package Expo.ASM.Hooks.Gui;

import Expo.ASM.Hooks.CallbackInfo;
import Expo.ExpoClient;
import Expo.event.Event;
import Expo.event.events.ActionPerformedEvent;
import Expo.event.events.DisconnectedInitEvent;
import Expo.event.events.GuiMouseEvent;
import Expo.event.events.InitGuiEvent;
import Expo.event.events.PreDrawScreenEvent;
import Expo.event.events.ServerJoinEvent;
import Expo.internal.auth.AltManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IChatComponent;
import org.lwjgl.input.Mouse;

public class GuiEventHooks {
   private static boolean k;
   private static Field j;
   private static boolean J;
   private static Field G;
   private static final Minecraft n = Minecraft.getMinecraft();
   private static final long a = 84546025458123L;

   private static void ensureInitialized() {
      if (!k) {
         if (ExpoClient.w != null) {
            try {
               AltManager.M(7874752644491L);
               k = true;
            } catch (Throwable var5) {
            }
         }
      }
   }

   private static void post(Event var0) {
      if (ExpoClient.w != null) {
         ExpoClient.w.e(var0, 18670087776179L);
      }
   }

   public static void onDrawScreen(GuiScreen var0) {
      ensureInitialized();
      post(new PreDrawScreenEvent(var0));
   }

   public static void onInitGui(GuiScreen var0) {
      ensureInitialized();
      post(new InitGuiEvent(var0, buttonList(var0)));
   }

   public static void onClientTick() {
      GuiScreen var4 = n.currentScreen;
      boolean var5 = Mouse.isButtonDown(0);
      boolean var6 = var5 && !J;
      J = var5;
      if (var4 != null) {
         ensureInitialized();
         int var7 = Mouse.getX() * var4.width / n.displayWidth;
         int var8 = var4.height - Mouse.getY() * var4.height / n.displayHeight - 1;
         post(new GuiMouseEvent(var4, var6, var7, var8));
      }
   }

   public static void onLoadWorld() {
      ensureInitialized();
      post(new ServerJoinEvent(n.getCurrentServerData(), 31027, (char)3724, (short)54290));
   }

   public static void onActionPerformed(GuiScreen var0, GuiButton var1, CallbackInfo var2) {
      ensureInitialized();
      ActionPerformedEvent var7 = new ActionPerformedEvent(var0, var1);
      post(var7);
      if (var7.a()) {
         var2.cancel();
      }
   }

   private static Field findButtonListField() throws NoSuchFieldException {
      try {
         return findField(GuiScreen.class, "buttonList", "field_146292_n", "n");
      } catch (Throwable var6) {
         for (Class<?> var0 = GuiScreen.class; var0 != null; var0 = var0.getSuperclass()) {
            Field[] var1 = var0.getDeclaredFields();

            for (Field var5 : var1) {
               if (List.class.isAssignableFrom(var5.getType()) || ArrayList.class.isAssignableFrom(var5.getType())) {
                  var5.setAccessible(true);
                  return var5;
               }
            }
         }

         throw new NoSuchFieldException("buttonList");
      }
   }

   private GuiEventHooks() {
   }

   public static List<GuiButton> buttonList(GuiScreen var0) {
      try {
         if (j == null) {
            j = findButtonListField();
         }

         return (List<GuiButton>)j.get(var0);
      } catch (Throwable var2) {
         return null;
      }
   }

   public static void onDisconnectedInit(GuiScreen var0) {
      if (var0 instanceof GuiDisconnected) {
         ensureInitialized();

         try {
            if (G == null) {
               G = findField(GuiDisconnected.class, "message", "field_146304_f", "f");
            }

            post(new DisconnectedInitEvent(var0, (IChatComponent)G.get(var0)));
         } catch (Throwable var6) {
         }
      }
   }

   private static Field findField(Class<?> var0, String... var1) throws NoSuchFieldException {
      for (Class var2 = var0; var2 != null; var2 = var2.getSuperclass()) {
         for (String var6 : var1) {
            try {
               Field var7 = var2.getDeclaredField(var6);
               var7.setAccessible(true);
               return var7;
            } catch (NoSuchFieldException var8) {
            }
         }
      }

      throw new NoSuchFieldException(var0.getName());
   }

   public static void onMouseClicked(GuiScreen var0, int var1, int var2, int var3, CallbackInfo var4) {
      if (var3 == 0) {
         ensureInitialized();
         GuiMouseEvent var9 = new GuiMouseEvent(var0, true, var1, var2);
         post(var9);
         if (var9.a()) {
            var4.cancel();
         }
      }
   }
}
