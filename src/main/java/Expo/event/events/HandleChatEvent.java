package Expo.event.events;

import Expo.event.Event;
import net.minecraft.util.IChatComponent;

public class HandleChatEvent extends Event {
   private static final long a = 96033516377139L;
   public final IChatComponent A;

   public HandleChatEvent(IChatComponent var1) {
      super();
      this.A = var1;
   }}
