package Expo.event.events;

import Expo.event.Event;
import net.minecraft.client.multiplayer.ServerData;











public class ServerJoinEvent extends Event {
   public final ServerData u;
   private static final long a = 78924094934294L;

   public ServerJoinEvent(ServerData var1, int var2, char var3, short var4) {
      super();
      this.u = var1;
   }}
