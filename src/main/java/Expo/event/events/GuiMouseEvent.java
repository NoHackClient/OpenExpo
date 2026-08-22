package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.GuiScreen;











public class GuiMouseEvent extends Event {
   public final GuiScreen j;
   private static final long a = 135486859174404L;
   public final boolean y;
   public final int A;
   public final int I;

   public GuiMouseEvent(GuiScreen var1, boolean var2, int var5, int var6) {
      super();
      this.j = var1;
      this.y = var2;
      this.I = var5;
      this.A = var6;
   }}
