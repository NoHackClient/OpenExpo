package Expo.ASM.Hooks.Gui;

import Expo.ExpoClient;
import Expo.event.events.GuiChatKeyTypedEvent;

public class GuiTextFieldHooks {
   private static final long a = 75756383394213L;

   public static void onGuiChatKeyTyped() {
      ExpoClient.w.e(new GuiChatKeyTypedEvent(), 18670087776179L);
   }
}
