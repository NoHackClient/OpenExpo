package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ChamsBinder;
import Expo.event.events.PostRenderEvent;
import Expo.event.events.PreRenderEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.util.EntityUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;











public class Chams extends Module implements EventSubscriber {
   private boolean I;
   public static BooleanSetting bosses;
   public static BooleanSetting bots;
   public static BooleanSetting mobs;
   public static BooleanSetting friends;
   public static BooleanSetting animals;
   private boolean x;
   private boolean C;
   public static BooleanSetting teammates;
   private boolean u;
   public static HeaderSetting targetSettings;
   public static BooleanSetting enemies;
   private boolean y;
   private boolean b;
   private static long a;
   public static BooleanSetting players;
   private boolean Y;
   private boolean H;

   public Chams(long var1) {
      super(((a ^ (var1)) ^ 81600828246724L));
      // add code
      this.declare("Chams", Category.Visual, "Allows you to see entities through blocks");
      var1 = a ^ var1;
      this.b = false;
      this.u = false;
      this.Y = false;
      this.C = false;
      this.I = false;
      this.y = false;
      this.x = false;
      this.H = false;
   }


   public final void x(long var1, EventBus var3) {
      ChamsBinder.I(var3, this);
   }

   public void onPreRender(PreRenderEvent var1) {

      this.b = players.c();
      this.u = mobs.c();
      this.Y = animals.c();
      this.C = bosses.c();
      this.I = friends.c();
      this.y = enemies.c();
      this.x = teammates.c();
      this.H = bots.c();
      if (var1.B instanceof EntityPlayerSP || L(var1.B, this.b, this.u, this.Y, this.C, 51651182070909L, this.I, this.y, this.x, this.H)) {
         GL11.glEnable(32823);
         GL11.glPolygonOffset(1.0F, -2500000.0F);
      }
   }


   public static boolean L(
      EntityLivingBase var0, boolean var1, boolean var2, boolean var3, boolean var4, long var5, boolean var7, boolean var8, boolean var9, boolean var10
   ) {


      return EntityUtil.q(var0, var1, var2, var3, var4, var7, var8, var9, var10, 21816078198602L);
   }

   public void onPostRender(PostRenderEvent var3) {

      if (var3.z instanceof EntityPlayerSP || L(var3.z, this.b, this.u, this.Y, this.C, 51651182070909L, this.I, this.y, this.x, this.H)) {
         GL11.glDisable(32823);
         GL11.glPolygonOffset(1.0F, 2500000.0F);
      }
   }

   static {
      a = 4220396465040L;
   }

   static {
      // add code
      teammates = new BooleanSetting("Teammates", true);
      enemies = new BooleanSetting("Enemies", true);
      bosses = new BooleanSetting("Bosses", false);
      animals = new BooleanSetting("Animals", false);
      bots = new BooleanSetting("Bots", false);
      mobs = new BooleanSetting("Mobs", false);
      players = new BooleanSetting("Players", true);
      friends = new BooleanSetting("Friends", true);
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
