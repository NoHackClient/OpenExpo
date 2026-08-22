package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.GuiScreen;











public class DrawScreenEvent extends Event {
   public final GuiScreen S;
   private static final long a = 72891699105399L;

   public DrawScreenEvent(GuiScreen var1) {
      super();
      this.S = var1;
   }}
