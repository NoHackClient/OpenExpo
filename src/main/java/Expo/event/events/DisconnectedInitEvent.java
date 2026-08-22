package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IChatComponent;











public class DisconnectedInitEvent extends Event {
   private static final long a = 82080178024315L;
   public final IChatComponent X;
   public final GuiScreen O;

   public DisconnectedInitEvent(GuiScreen var3, IChatComponent var4) {
      super();
      this.O = var3;
      this.X = var4;
   }}
