package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;











public class ActionPerformedEvent extends Event {
   public final GuiScreen O;
   public final GuiButton Q;
   private static final long a = 105165164361147L;

   public ActionPerformedEvent(GuiScreen var1, GuiButton var4) {
      super();
      this.O = var1;
      this.Q = var4;
   }}
