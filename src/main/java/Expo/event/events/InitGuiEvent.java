package Expo.event.events;

import Expo.event.Event;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class InitGuiEvent extends Event {
   public final GuiScreen A;
   private static final long a = 137383723411876L;
   public final List<GuiButton> B;

   public InitGuiEvent(GuiScreen var3, List var4) {
      super();
      this.A = var3;
      this.B = var4;
   }}
