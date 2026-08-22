package Expo.event.events;

import Expo.event.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;











public class GetDisplayNameEvent extends Event {
   private static final long a = 77882100772787L;
   public final EntityPlayer u;
   private String K;
   private String T;
   private IChatComponent R;

   public void O(String var1) {
      this.T = this.T + var1;
   }

   public IChatComponent S() {
      return this.R;
   }

   public GetDisplayNameEvent(EntityPlayer var3, IChatComponent var4) {
      super();
      this.K = "";
      this.T = "";
      this.u = var3;
      this.R = var4;
   }

   public void n(IChatComponent var1) {
      this.R = var1;
   }

   public IChatComponent c() {
      ChatComponentText var1 = new ChatComponentText(this.K);
      ChatComponentText var2 = new ChatComponentText(this.T);
      return var1.appendSibling(this.R).appendSibling(var2);
   }

   public void Q(String var1) {
      this.K = var1 + this.K;
   }}
