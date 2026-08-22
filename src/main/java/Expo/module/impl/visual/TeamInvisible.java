package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.TeamInvisibleBinder;
import Expo.event.events.EntityRenderStateEvent;
import Expo.event.events.PostRenderCapeEvent;
import Expo.event.events.PostRenderModelBipedEvent;
import Expo.event.events.PreRenderCapeEvent;
import Expo.event.events.PreRenderModelBipedEvent;
import Expo.event.events.PreRenderEntityEvent;
import Expo.module.Module;
import Expo.module.impl.configuration.Teams;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.RaytraceUtil;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;


public class TeamInvisible extends Module implements EventSubscriber {
   private static Object[] e;
   private static long[] b;
   private static long a;
   public static PercentageSetting opacity;
   private static String[] g;
   private static Map d;
   public static NumberSetting range;

   public final void x(long var1, EventBus var3) {
      TeamInvisibleBinder.y(var3, this);
   }

   public void onPostRenderModelBiped(PostRenderModelBipedEvent var1, long var2) {

      this.j(var1.v, 41139269005963L);
      var1.G();
   }

   public void onPreRenderModelBiped(long var1, PreRenderModelBipedEvent var3) {

      this.v(var3.O, 111893817914976L);
      var3.G();
   }

   public String g(long var1) {
      return opacity.k() + "%";
   }

   public void v(Entity var1, long var2) {



      if (this.C(1481626796L, (char)46315, var1)) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, opacity.k() / 100.0F);
         GlStateManager.depthMask(false);
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.alphaFunc(516, 0.003921569F);
      }
   }

   public void onPreRenderCape(PreRenderCapeEvent var1, long var2) {

      this.v(var1.F, 111893817914976L);
      var1.G();
   }

   public void j(Entity var1, long var2) {



      if (this.C(1481626796L, (char)46315, var1)) {
         GlStateManager.disableBlend();
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.depthMask(true);
      }
   }


   private boolean C(long var1, char var3, Entity var4) {
      long var5 = (97099893702656L | (long)var3 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 19024618437949L;
      return var4 != f.thePlayer && RaytraceUtil.q(var7, var4, range.L()) && Teams.g(0L, var4);
   }

   public void onEntityRenderState(char var1, int var2, EntityRenderStateEvent var3, int var4) {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 63634681832868L;
      this.j(var3.k, var7);
      var3.G();
   }

   static {
      a = 110532025384406L;
      e = new Object[7];
      g = new String[7];
      d = new HashMap(13);
      b = new long[]{-5350843204115390627L, 3042616221696105688L, 634406712226758148L, -6769754617391115710L};
   }

   public void onPreRenderEntity(PreRenderEntityEvent var1, long var2) {

      this.v(var1.O, 111893817914976L);
      var1.G();
   }

   private static void a() {
      e[0] = "/M\u001bJ\t]";
      e[1] = long.class;
      g[1] = "java/lang/Long";
      e[2] = "{_\u0003{NNLH\u0007q\u0003j[C]m";
      e[3] = "DHg8OHQ";
      e[4] = void.class;
      g[4] = "java/lang/Void";
      e[5] = "@S\u000b1\u007f&K\\\u001a~\u001e(@W\u001e$";
      e[6] = "b\u0006;\taK|V)ji4;P'\rcExI9[\u0018\rx[?\f'Pc\u0003?j\"Sp@-V)\u000bl\u0006GQ#DgT<RaWh9";
   }

   public TeamInvisible(char var1, long var2) {
      super((((((long)((var1)) << 48) | 0L) ^ a) ^ 107406694384265L));
      // add code
      this.declare("TeamInvisible", Category.Visual, "Let your teammates be \"Invisible\"");
   }

   public void onPostRenderCape(long var1, byte var3, PostRenderCapeEvent var4) {
      long var5 = (var1 << 8 | (long)var3 << 56 >>> 56) ^ a;
      long var7 = var5 ^ 77545539425907L;
      this.j(var4.U, var7);
      var4.G();
   }
   static {
      // add code
      range = new NumberSetting("Range", 20.0F, 1.0F, 64.0F, 1.0F);
      opacity = new PercentageSetting("Opacity", 20);
   }
}
