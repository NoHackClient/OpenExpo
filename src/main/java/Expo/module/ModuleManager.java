package Expo.module;

import Expo.module.impl.combat.HitBox;
import Expo.module.impl.misc.AntiBot;
import Expo.module.impl.misc.AntiNick;
import Expo.module.impl.misc.NameHider;
import Expo.module.impl.misc.NoObfuscation;
import Expo.module.impl.player.ChestStealer;
import Expo.module.impl.player.GhostHand;
import Expo.module.impl.player.NoHitDelay;
import Expo.module.impl.visual.Animations;
import Expo.module.impl.visual.AntiDebuff;
import Expo.module.impl.visual.BarrierVisible;
import Expo.module.impl.visual.CaveXray;
import Expo.module.impl.visual.Chams;
import Expo.module.impl.visual.ItemScale;
import Expo.module.impl.visual.NoHurtCam;
import Expo.module.impl.visual.TeamInvisible;
import Expo.module.impl.visual.ViewClip;
import Expo.module.impl.world.Scaffold;
import Expo.setting.settings.BooleanSetting;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import org.apache.logging.log4j.Logger;


public class ModuleManager {
   public static CaveXray m;
   public static NameHider J;
   public static AntiNick f;
   private static Map j;
   public static ViewClip h;
   public static List<String> n;
   private static long b;
   public static NoHurtCam g;
   public static TeamInvisible y;
   public static ChestStealer q;
   public static NoHitDelay p;
   public static Scaffold I;
   private static Logger z;
   public static GhostHand Q;
   public static BarrierVisible W;
   public static AntiDebuff O;
   public static Chams a;
   public static NoObfuscation k;
   public static Animations d;
   public static HitBox r;
   public static AntiBot c;
   public static ItemScale v;
   public static List<Module> S;
   private static String[] i;
   public static HashMap<Class<? extends Module>, Module> o;
   private static String[] e;
   public static boolean $skidonion$1876635314;

   static {
      // add code
      m = new CaveXray(0L);
      W = new BarrierVisible(0L);
      d = new Animations(0L);
      h = new ViewClip(0L);
      O = new AntiDebuff(0L);
      g = new NoHurtCam(0L);
      Q = new GhostHand(0L);
      q = new ChestStealer(0L);
      r = new HitBox(0L);
      v = new ItemScale(0L);

      if (Scaffold.fakeItem == null) {
         Scaffold.fakeItem = new BooleanSetting("Fake-item", true);
      }

      if (ChestStealer.silent == null) {
         ChestStealer.silent = new BooleanSetting("Silent", false);
      }

      if (ChestStealer.chestIntegrityCheck == null) {
         ChestStealer.chestIntegrityCheck = new BooleanSetting("Chest-integrity-check", true);
      }
   }

   private static native String a(int var0, long var1);

   // add code
   public static List<Module> modules() {
      return S == null ? java.util.Collections.<Module>emptyList() : S;
   }

   // add code
   public static Module byClass(Class<? extends Module> var0) {
      return o == null ? null : o.get(var0);
   }

   // add code
   public static Module byName(String var0) {
      if (var0 != null) {
         for (Module var1 : modules()) {
            if (var0.equalsIgnoreCase(var1.name())) {
               return var1;
            }
         }
      }

      return null;
   }

   // add code
   public static List<Module> inCategory(Category var0) {
      List<Module> var1 = new java.util.ArrayList<Module>();

      for (Module var2 : modules()) {
         if (var2.f() == var0) {
            var1.add(var2);
         }
      }

      return var1;
   }

   // add code
   public static List<String> names() {
      List<String> var0 = new java.util.ArrayList<String>();

      for (Module var1 : modules()) {
         if (var1.name() != null) {
            var0.add(var1.name());
         }
      }

      return var0;
   }

   // add code
   public static String describe(String var0) {
      Module var1 = byName(var0);
      return var1 == null ? null : var1.description();
   }

}
