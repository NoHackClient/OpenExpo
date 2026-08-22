package Expo.internal.restore;

import Expo.ExpoClient;
import Expo.command.ExpoCommands;
import Expo.event.EventBus;
import Expo.internal.jnic.StockCommandRegistry;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.ui.swing.ConfigManagerWindow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public final class ExpoBootstrap {

   public static final long REGISTRATION_CARRIER = 0L;





   public static final List<String> SUBSCRIBED = new ArrayList<String>();

   public static final List<String> PENDING = new ArrayList<String>();

   private ExpoBootstrap() {
   }

   public static void initClient() {
      // add code
      ExpoTableDump.install();
      if (ExpoClient.w != null) {
         return;
      }

      SUBSCRIBED.clear();
      PENDING.clear();

      EventBus var2 = new EventBus();

      if (ModuleManager.S == null) {
         ModuleManager.S = new ArrayList<Module>();
      }

      if (ModuleManager.o == null) {
         ModuleManager.o = new HashMap<Class<? extends Module>, Module>();
      }

      if (ConfigManagerWindow.D == null) {
         ConfigManagerWindow.D = new ArrayList<String>();
      }

      sub(var2, "Expo.internal.MiningEngine", Expo.internal.MiningEngine.uq);
      sub(var2, "Expo.util.AutoToolService", Expo.util.AutoToolService.K);
      sub(var2, "Expo.internal.BrokenBlockTracker", Expo.internal.BrokenBlockTracker.m);
      sub(var2, "Expo.internal.MiningRenderSubscriber", new Expo.internal.MiningRenderSubscriber());

      sub(var2, "Expo.util.packet.IncomingPacketHold", new Expo.util.packet.IncomingPacketHold());
      sub(var2, "Expo.util.AttackTracker", new Expo.util.AttackTracker());
      sub(var2, "Expo.ui.ModuleTagRenderer", new Expo.ui.ModuleTagRenderer());
      sub(var2, "Expo.util.RotationManager", new Expo.util.RotationManager());
      sub(var2, "Expo.util.HypixelGameState", new Expo.util.HypixelGameState());
      sub(var2, "Expo.util.packet.PacketManager", new Expo.util.packet.PacketManager());

      ExpoModuleRegistry.publish();

      ExpoAzPump.install(var2, REGISTRATION_CARRIER, SUBSCRIBED, PENDING);
      PENDING.addAll(ExpoModuleRegistry.PENDING);
      PENDING.add("Expo.internal.ChatInputHandler    ctor (J)V   carrier live: Expo/yT.a(IJ)I @19");
      PENDING.add("Expo.ui.screen.MainMenuTheme  ctor (J)V   carrier live: Expo/on_2.a(IJ)String @19 @34 @49 @64");
      ExpoSettingStatics.apply(PENDING);
      ExpoModuleSettings.apply(PENDING);
      ExpoTruthNames.apply(PENDING);
      runOrphanedStaticInit();
      ExpoClickGui.install(PENDING);
      ExpoCommands.install(PENDING);

      ExpoClient.w = var2;
      if (ExpoModuleRegistry.PLAIN_LISTENER != null) {
         PENDING.add("Expo.internal.CheaterDetector held out of tD.S and left unsubscribed -- /cheaters reads its "
                     + "R/c maps and they stay empty until a world-gated subscription exists");
      }
      ExpoConfig.apply(PENDING);

      PENDING.add("Expo.config boot snapshot = " + ExpoConfig.snapshotBoot()
                  + " setting value(s); a later save preserves the file's value for any of "
                  + "them the load did not actually apply, instead of overwriting it");

      try {
         ExpoGuiWindow.install(PENDING);
      } catch (Throwable var3) {
         PENDING.add("ExpoGuiWindow.install threw: " + ExpoDiag.describe(var3));
      }


      diag$dump();
      ExpoSelfTest.install();
   }

   private static String census() {
      StringBuilder t = new StringBuilder();
      java.util.Map<String, Integer> perCat = new java.util.TreeMap<String, Integer>();
      int named = 0;
      for (int i = 0; i < ModuleManager.S.size(); i++) {
         Module m = ModuleManager.S.get(i);
         if (m == null) { continue; }
         String n, c;
         int ns;
         try { n = m.b(); } catch (Throwable x) { n = "<name threw>"; }
         try { c = m.f() == null ? "<null>" : m.f().name(); } catch (Throwable x) { c = "<cat threw>"; }
         try { ns = m.w() == null ? -1 : m.w().size(); } catch (Throwable x) { ns = -1; }
         if (n != null && n.length() > 2 && Character.isUpperCase(n.charAt(0))) { named++; }
         Integer p = perCat.get(c);
         perCat.put(c, Integer.valueOf(p == null ? 1 : p.intValue() + 1));
         t.append(m.getClass().getName()).append('\t').append(n).append('\t')
          .append(c).append('\t').append(ns).append('\n');
      }
      try {
         java.io.File f = new java.io.File("expo-census.tsv");
         java.io.Writer w = new java.io.OutputStreamWriter(
               new java.io.FileOutputStream(f), "UTF-8");
         try { w.write(t.toString()); } finally { w.close(); }
      } catch (Throwable x) {
      }
      StringBuilder b = new StringBuilder();
      b.append("[EXPODIAG] census names       = ").append(named)
       .append(" of ").append(ModuleManager.S.size()).append(" (truth 112)\n");
      b.append("[EXPODIAG] census per-cat     = ").append(perCat).append('\n');
      return b.toString();
   }

   private static void diag$dump() {
      try {
         StringBuilder b = new StringBuilder("\n[EXPODIAG] ==== bootstrap outcome ====\n");
         b.append("[EXPODIAG] AZ.w             = ").append(Expo.ExpoClient.w == null ? "null" : "live").append('\n');
         b.append("[EXPODIAG] subscribed       = ").append(SUBSCRIBED.size()).append(' ').append(SUBSCRIBED).append('\n');
         b.append("[EXPODIAG] pending          = ").append(PENDING.size()).append('\n');
         b.append("[EXPODIAG] tD.S (list)      = ")
          .append(ModuleManager.S == null ? "null" : String.valueOf(ModuleManager.S.size())).append('\n');
         b.append("[EXPODIAG] tD.o (byClass)   = ")
          .append(ModuleManager.o == null ? "null" : String.valueOf(ModuleManager.o.size())).append('\n');
         b.append("[EXPODIAG] zu_3.B VESTIGE   = ").append(Expo.module.impl.configuration.ClickGUI.B == null ? "null" : "live").append('\n');
         b.append("[EXPODIAG] zu_3.Y STUDIO    = ").append(Expo.module.impl.configuration.ClickGUI.Y == null ? "null" : "live").append('\n');
         b.append("[EXPODIAG] zu_3.F RAVEN     = ").append(Expo.module.impl.configuration.ClickGUI.F == null ? "null" : "live").append('\n');
         b.append("[EXPODIAG] zu_3.U Mode      = ")
          .append(Expo.module.impl.configuration.ClickGUI.mode == null ? "null" : Expo.module.impl.configuration.ClickGUI.mode.Y()).append('\n');
         b.append("[EXPODIAG] zu_3.K Keybind   = ")
          .append(Expo.module.impl.configuration.ClickGUI.keybind == null ? "null" : Expo.module.impl.configuration.ClickGUI.keybind.X()).append('\n');
         b.append("[EXPODIAG] t6.L (commands)  = ")
          .append(StockCommandRegistry.L == null ? "null" : String.valueOf(StockCommandRegistry.L.size())).append('\n');
         b.append("[EXPODIAG] config writable   = ").append(ExpoModuleRegistry.writableNote()).append('\n');
         b.append("[EXPODIAG] ctorcache        = built ").append(ExpoCtorCache.built)
          .append(" failed ").append(ExpoCtorCache.failed).append('\n');
         b.append("[EXPODIAG] module count     = ")
          .append(ModuleManager.S == null ? -1 : ModuleManager.S.size()).append(" of ")
          .append(ExpoModuleRegistry.ORIGINAL_MODULE_COUNT).append(' ')
          .append(ExpoModuleRegistry.countGateGreen ? "OK" : "REGRESSION")
          .append(ExpoModuleRegistry.MISSING.isEmpty()
                  ? "" : " missing " + ExpoModuleRegistry.MISSING).append('\n');
         for (String c : ExpoCtorCache.LOG) {
            b.append("[EXPODIAG]   ctorcache> ").append(c).append('\n');
         }
         if (ModuleManager.S != null) {
            int on = 0;
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < ModuleManager.S.size(); i++) {
               Module m = ModuleManager.S.get(i);
               if (m == null) { continue; }
               if (m.o()) { on++; }
               if (i < 12) {
                  names.append(i == 0 ? "" : ", ").append(m.b()).append(m.o() ? "*" : "");
               }
            }
            b.append("[EXPODIAG] modules enabled   = ").append(on).append('\n');
            b.append("[EXPODIAG] first 12          = ").append(names).append('\n');
            b.append(census());
         }
         for (String p : PENDING) {
            if (p.contains("ClickGui") || p.contains("Ts_2") || p.contains("Ad_2")
                || p.contains("REFUSED") || p.contains("threw")) {
               b.append("[EXPODIAG]   pending> ").append(p).append('\n');
            }
         }

         for (String d : ExpoClickGui.degraded()) {
            b.append("[EXPODIAG]   degraded> ").append(d).append('\n');
         }

         b.append("[EXPODIAG] ==== end ====");
      } catch (Throwable t) {
      }
   }

   private static void sub(EventBus var0, String var1, Object var2) {
      if (var2 == null) {
         throw new IllegalStateException("ExpoBootstrap: null listener " + var1);
      }

      var0.s(var2, REGISTRATION_CARRIER);
      SUBSCRIBED.add(var1);
   }

   private static void runOrphanedStaticInit() {
      try {
         Expo.module.impl.visual.KeyStrokes.T();
         SUBSCRIBED.add("za_4.T (KeyStrokes last-press map seeded)");
      } catch (Throwable var0) {
         PENDING.add("za_4.T threw: " + ExpoDiag.describe(var0));
      }

      try {
         String[] capes = {
         "VALENTINE", "valentine", "MJ_STUDIOS", "mojang_studios", "SCROLLS", "scrolls",
         "2012", "2012", "2011", "2011", "OXEYE", "oxeye",
         "MJ_CLASSIC", "mojang_classic", "MOJIRA", "mojira", "SPADE", "spade",
         "2016", "2016", "2015", "2015", "SNOWMAN", "snowman",
         "2013", "2013", "PRISMARINE", "prismarine", "TURTLE", "turtle",
         "REALMS", "realms", "MJ", "mojang", "TRANSLATOR", "translator",
         "BIRTHDAY", "birthday", "MILLIONTH", "millionth", "DB", "db",
         "COBALT", "cobalt", "SIZE_M", "size-m"
         };

         if (Expo.module.impl.configuration.CustomCape.O != null) {
            for (int i = 0; i + 1 < capes.length; i += 2) {
               Expo.module.impl.configuration.CustomCape.O.put(capes[i], capes[i + 1]);
            }

            SUBSCRIBED.add("zH_3.O seeded with " + Expo.module.impl.configuration.CustomCape.O.size() + " cape(s)");
         } else {
            PENDING.add("zH_3.O is null -- CustomCape cannot resolve any texture");
         }
      } catch (Throwable var1) {
         PENDING.add("zH_3.O seeding threw: " + ExpoDiag.describe(var1));
      }

      try {
         String user = ExpoGuiData.sessionName();

         if (user != null && user.length() > 0) {
            Expo.util.BuildInfo.W = user;
            SUBSCRIBED.add("yg_2.W set to the Minecraft session name");
         } else {
            PENDING.add("yg_2.W left empty -- no Minecraft session name available");
         }
      } catch (Throwable var2) {
         PENDING.add("yg_2.W seeding threw: " + ExpoDiag.describe(var2));
      }
   }

}
