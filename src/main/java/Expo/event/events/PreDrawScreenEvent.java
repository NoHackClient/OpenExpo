package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.GuiScreen;

public class PreDrawScreenEvent extends Event {
   public final GuiScreen Q;
   private static final long a = 58989482971666L;

   public PreDrawScreenEvent(GuiScreen var3) {
      super();
      this.Q = var3;
   }}
