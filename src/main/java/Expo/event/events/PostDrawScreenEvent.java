package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;











public class PostDrawScreenEvent extends Event {
   private static final long a = 74606617970778L;
   public final ScaledResolution s;
   public final GuiScreen C;

   public PostDrawScreenEvent(GuiScreen var1, ScaledResolution var4) {
      super();
      this.C = var1;
      this.s = var4;
   }}
