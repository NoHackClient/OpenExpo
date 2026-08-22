package Expo.internal.restore;

import Expo.module.Module;
import com.google.gson.JsonObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public final class ExpoCtorCache {

   public static final boolean ENABLED = true;

   public static final List<String> LOG = new ArrayList<String>();

   public static int built;

   public static int failed;

   private ExpoCtorCache() {
   }

   static final class Site {
      final int idx;
      final long k0;
      final int value;
      final String field;
      final String desc;

      Site(int var1, long var2, int var4, String var5, String var6) {
         this.idx = var1;
         this.k0 = var2;
         this.value = var4;
         this.field = var5;
         this.desc = var6;
      }
   }

   static final class Plan {
      final Class<? extends Module> cls;
      final String name;
      final String seedField;
      final String cacheField;
      final int k;
      final int cacheLen;
      final String pack;
      final Site[] sites;

      Plan(Class<? extends Module> var1, String var2, String var3, String var4,
           int var5, int var6, String var7, Site[] var8) {
         this.cls = var1;
         this.name = var2;
         this.seedField = var3;
         this.cacheField = var4;
         this.k = var5;
         this.cacheLen = var6;
         this.pack = var7;
         this.sites = var8;
      }
   }

   static Plan[] plans() {
      return new Plan[]{
         new Plan(Expo.module.impl.combat.HitSelect.class, "HitSelect", "a", "h", 21628, 3, "J", new Site[]{
            new Site(32428, 2723576178725505343L, 0, "m", "I"),
            new Site(23622, 7397565292231551958L, 0, "L", "I"),
         }),
         new Plan(Expo.module.impl.combat.AntiFireball.class, "AntiFireball", "a", "h", 14058, 3, "J", new Site[]{
            new Site(30693, 8186777335795973556L, 0, "N", "Z"),
         }),
         new Plan(Expo.module.impl.player.ChestAura.class, "ChestAura", "b", "m", 31935, 4, "J", new Site[]{
            new Site(8286, 6094875200226540527L, 0, "J", "Z"),
            new Site(8286, 6094875200226540527L, 0, "s", "Z"),
            new Site(8286, 6094875200226540527L, 0, "p", "Z"),
         }),
         new Plan(Expo.module.impl.world.BlockIn.class, "BlockIn", "b", "p", 24428, 11, "J", new Site[]{
            new Site(2014, 8012480587103233077L, -1, "O", "I"),
            new Site(2014, 8012480587103233077L, -1, "M", "I"),
         }),
         new Plan(Expo.module.impl.combat.BackTrack.class, "BackTrack", "b", "n", 10833, 3, "J", new Site[]{
            new Site(8330, 5688266278803206197L, 0, "E", "Z"),
         }),
         new Plan(Expo.module.impl.visual_utility.InventoryHUD.class, "InventoryHUD", "a", "d", 12376, 10, "J", new Site[]{
            new Site(26857, 6035176678564753830L, 27, null, null),
         }),
         new Plan(Expo.module.impl.world.AntiVoid.class, "AntiVoid", "a", "g", 5292, 4, "J", new Site[]{
            new Site(29507, 6671796270530158461L, 0, "D", "Z"),
            new Site(29507, 6671796270530158461L, 0, "k", "Z"),
            new Site(29507, 6671796270530158461L, 0, "U", "Z"),
            new Site(29507, 6671796270530158461L, 0, "t", "Z"),
         }),
         new Plan(Expo.module.impl.visual_utility.BedPlates.class, "BedPlates", "b", "n", 578, 7, "J", new Site[]{
            new Site(31885, 7925028623335272997L, 5, "F", "I"),
            new Site(31255, 1736573748167652542L, 2, "J", "I"),
            new Site(31795, 4433329095510667929L, 16, "d", "I"),
         }),
         new Plan(Expo.module.impl.visual_utility.FallIndicator.class, "FallIndicator", "b", "m", 12593, 15, "J", new Site[]{
            new Site(486, 7938159462607728212L, -1, "U", "I"),
            new Site(6189, 913887084950730641L, 0, "s", "Z"),
            new Site(10609, 7294865597832103628L, 0, "L", "Z"),
            new Site(488, 2614953321329405532L, -1, "r", "I"),
         }),
         new Plan(Expo.module.impl.player.FastCraft.class, "FastCraft", "a", "g", 3665, 13, "SSI", new Site[]{
            new Site(2273, 5395706073806586902L, 6, null, null),
            new Site(11225, 5523046453089855271L, 8, null, null),
            new Site(2497, 1480707547944568116L, 6, null, null),
            new Site(17798, 6756993452064115070L, 7, null, null),
            new Site(2497, 1480707547944568116L, 6, null, null),
            new Site(15339, 2212301667659544347L, 8, null, null),
            new Site(22622, 43382244530623663L, 7, null, null),
            new Site(10628, 668920129094903161L, 9, null, null),
            new Site(22622, 43382244530623663L, 7, null, null),
            new Site(2497, 1480707547944568116L, 6, null, null),
            new Site(22622, 43382244530623663L, 7, null, null),
            new Site(2497, 1480707547944568116L, 6, null, null),
            new Site(18084, 168894688206391894L, 9, null, null),
            new Site(2497, 1480707547944568116L, 6, null, null),
            new Site(22622, 43382244530623663L, 7, null, null),
            new Site(18084, 168894688206391894L, 9, null, null),
            new Site(22622, 43382244530623663L, 7, null, null),
            new Site(2497, 1480707547944568116L, 6, null, null),
            new Site(22622, 43382244530623663L, 7, null, null),
            new Site(2497, 1480707547944568116L, 6, null, null),
            new Site(18084, 168894688206391894L, 9, null, null),
            new Site(26130, 3476390729396759265L, 0, "v", "Z"),
            new Site(20867, 2304963614575566199L, 0, "N", "Z"),
         }),
         new Plan(Expo.module.impl.visual.TabGUI.class, "TabGUI", "c", "k", 30925, 15, "J", new Site[]{
            new Site(22082, 2920255095099852831L, 0, "a", "I"),
            new Site(22082, 2920255095099852831L, 0, "o", "Z"),
         }),
         new Plan(Expo.module.impl.combat.LagRange.class, "LagRange", "a", "h", 4476, 3, "J", new Site[]{
            new Site(23590, 5361158691537936663L, 0, "e", "Z"),
         }),
         new Plan(Expo.module.impl.combat.JumpReset.class, "JumpReset", "a", "g", 2033, 5, "J", new Site[]{
            new Site(30237, 6298404055134370110L, 0, "d", "Z"),
            new Site(30237, 6298404055134370110L, 0, "J", "Z"),
            new Site(30237, 6298404055134370110L, 0, "o", "I"),
         }),
         new Plan(Expo.module.impl.movement.Stuck.class, "Stuck", "a", "d", 25525, 5, "JI", new Site[]{
            new Site(4190, 2160737169265335063L, 0, "s", "I"),
            new Site(2598, 9216671297597571438L, 0, "U", "Z"),
         }),
         new Plan(Expo.module.impl.misc.ContainerKeeper.class, "ContainerKeeper", "a", "h", 6163, 4, "J", new Site[]{
            new Site(22855, 3126871733210175939L, 0, "v", "Z"),
            new Site(22855, 3126871733210175939L, 0, "t", "Z"),
            new Site(22855, 3126871733210175939L, 0, "T", "Z"),
         }),
         new Plan(Expo.module.impl.player.NoFall.class, "NoFall", "a", "c", 20542, 2, "BJ", new Site[]{
            new Site(18015, 4503509822438384287L, 0, "r", "Z"),
            new Site(23564, 8963975281788565709L, 0, "K", "Z"),
            new Site(23564, 8963975281788565709L, 0, "C", "Z"),
            new Site(23564, 8963975281788565709L, 0, "n", "Z"),
            new Site(23564, 8963975281788565709L, 0, "L", "Z"),
            new Site(23564, 8963975281788565709L, 0, "x", "Z"),
         }),
         new Plan(Expo.module.impl.combat.SprintReset.class, "SprintReset", "a", "k", 23815, 4, "ICS", new Site[]{
            new Site(32360, 7633384127733083978L, 0, "H", "Z"),
            new Site(32360, 7633384127733083978L, 0, "C", "Z"),
            new Site(32360, 7633384127733083978L, 0, "g", "Z"),
            new Site(32360, 7633384127733083978L, 0, "u", "Z"),
            new Site(32360, 7633384127733083978L, 0, "K", "Z"),
            new Site(32360, 7633384127733083978L, 0, "h", "Z"),
         }),
         new Plan(Expo.module.impl.misc.AutoGG.class, "AutoGG", "a", "g", 5180, 22, "J", new Site[]{
            new Site(17251, 4549106400131373314L, -1, "n", "I"),
         }),
         new Plan(Expo.module.impl.world.Nuker.class, "Nuker", "b", "k", 26103, 7, "IIB", new Site[]{
            new Site(7128, 8992097853669558367L, 0, "g", "Z"),
            new Site(12849, 8469495982199366071L, 0, "K", "Z"),
            new Site(20530, 652371295304713142L, -1, "U", "I"),
         }),
         new Plan(Expo.module.impl.world.BridgeAssist.class, "BridgeAssist", "a", "d", 23392, 7, "J", new Site[]{
            new Site(4398, 8165079706445025135L, -1, "y", "I"),
            new Site(4398, 8165079706445025135L, -1, "Y", "I"),
            new Site(4398, 8165079706445025135L, -1, "n", "I"),
            new Site(4398, 8165079706445025135L, -1, "s", "I"),
            new Site(20380, 9144974035348247003L, 0, "G", "Z"),
         }),
         new Plan(Expo.module.impl.combat.WTap.class, "WTap", "a", "k", 31602, 7, "J", new Site[]{
            new Site(16744, 6377928706375133303L, 0, "T", "I"),
            new Site(4007, 6552793054094354109L, 0, "G", "I"),
            new Site(4007, 6552793054094354109L, 0, "u", "Z"),
            new Site(4007, 6552793054094354109L, 0, "L", "Z"),
            new Site(4007, 6552793054094354109L, 0, "J", "Z"),
            new Site(4007, 6552793054094354109L, 0, "p", "Z"),
         }),
         new Plan(Expo.module.impl.combat.Velocity.class, "Velocity", "c", "bb", 29510, 6, "IBI", new Site[]{
            new Site(23474, 9144873935309433681L, 0, "E", "Z"),
            new Site(865, 8648056866551182214L, 0, "I", "Z"),
            new Site(29844, 1654352439292806256L, 1, "N", "Z"),
            new Site(865, 8648056866551182214L, 0, "a", "Z"),
            new Site(865, 8648056866551182214L, 0, "v", "I"),
            new Site(865, 8648056866551182214L, 0, "k", "Z"),
            new Site(865, 8648056866551182214L, 0, "t", "Z"),
         }),
         new Plan(Expo.module.impl.visual.ArrayList.class, "ArrayList", "a", "p", 11981, 8, "J", new Site[]{
            new Site(12829, 7259488500419800518L, 6, null, null),
         }),
         new Plan(Expo.module.impl.world.AutoDigPlace.class, "AutoDigPlace", "b", "d", 6069, 5, "ICI", new Site[]{
            new Site(2269, 1618887811214563830L, 0, "O", "Z"),
            new Site(2269, 1618887811214563830L, 0, "a", "Z"),
            new Site(2269, 1618887811214563830L, 0, "N", "Z"),
            new Site(2269, 1618887811214563830L, 0, "t", "Z"),
            new Site(2269, 1618887811214563830L, 0, "E", "Z"),
            new Site(2269, 1618887811214563830L, 0, "n", "Z"),
         }),
         new Plan(Expo.module.impl.world.AutoTool.class, "AutoTool", "a", "e", 5724, 6, "J", new Site[]{
            new Site(11767, 6483576504881071035L, 0, "I", "Z"),
            new Site(27119, 6281398260493977509L, -1, "J", "I"),
            new Site(29305, 5329223110410443825L, 0, "S", "Z"),
         }),
         new Plan(Expo.module.impl.misc.Timer.class, "Timer", "b", "e", 12163, 3, "J", new Site[]{
            new Site(15294, 8004595846113005731L, 0, "d", "Z"),
            new Site(1917, 1563814055861136482L, 0, "a", "Z"),
         }),
         new Plan(Expo.module.impl.visual.Chams.class, "Chams", "a", "d", 3424, 4, "J", new Site[]{
            new Site(19410, 4752404691515757807L, 0, "b", "Z"),
            new Site(22209, 4647346560483805695L, 0, "u", "Z"),
            new Site(22209, 4647346560483805695L, 0, "Y", "Z"),
            new Site(22209, 4647346560483805695L, 0, "C", "Z"),
            new Site(22209, 4647346560483805695L, 0, "I", "Z"),
            new Site(22209, 4647346560483805695L, 0, "y", "Z"),
            new Site(22209, 4647346560483805695L, 0, "x", "Z"),
            new Site(22209, 4647346560483805695L, 0, "H", "Z"),
         }),
         new Plan(Expo.module.impl.movement.InvMove.class, "InvMove", "a", "h", 27103, 7, "J", new Site[]{
            new Site(23878, 3883305706901125547L, 0, "c", "Z"),
         }),
         new Plan(Expo.module.impl.player.NoHitDelay.class, "NoHitDelay", "a", "c", 17756, 2, "ICC", new Site[]{
            new Site(26777, 6037919109017145250L, 0, "S", "I"),
         }),
         new Plan(Expo.module.impl.player.InvClicker.class, "InvClicker", "a", "c", 7715, 4, "J", new Site[]{
            new Site(25185, 7121863864638836910L, 0, "p", "I"),
         }),
         new Plan(Expo.module.impl.player.Blink.class, "Blink", "a", "h", 25580, 5, "J", new Site[]{
            new Site(7980, 937514358243253414L, 0, "J", "Z"),
            new Site(18582, 5876241400044096287L, 1, "k", "Z"),
         }),
      };
   }

   static final class SSite {
      final int idx;
      final long k0;
      final String value;
      final String field;
      final String expect;

      SSite(int var1, long var2, String var4, String var5, String var6) {
         this.idx = var1;
         this.k0 = var2;
         this.value = var4;
         this.field = var5;
         this.expect = var6;
      }
   }

   static final class SPlan {
      final Class<? extends Module> cls;
      final String name;
      final String seedField;
      final String cacheField;
      final int k;
      final int cacheLen;
      final String pack;
      final SSite[] sites;

      SPlan(Class<? extends Module> var1, String var2, String var3, String var4,
            int var5, int var6, String var7, SSite[] var8) {
         this.cls = var1;
         this.name = var2;
         this.seedField = var3;
         this.cacheField = var4;
         this.k = var5;
         this.cacheLen = var6;
         this.pack = var7;
         this.sites = var8;
      }
   }

   static SPlan[] splans() {
      return new SPlan[]{
         new SPlan(Expo.module.impl.visual_utility.LeapModeHUD.class, "LeapModeHUD", "a", "c", 23355, 8, "J", new SSite[]{
            new SSite(8284, 5215580133141319025L, "Arrow", "h", "§6Arrow"),
         }),
      };
   }

   public static void publish(JsonObject var0) {
      if (!ENABLED) {
         LOG.add("ExpoCtorCache disabled by ENABLED");
         return;
      }

      boolean var9 = false;

      try {
         var9 = ExpoCtorCacheGate.run();
      } catch (Throwable var8) {
         LOG.add("gate threw: " + var8);
      }

      LOG.addAll(ExpoCtorCacheGate.LOG);

      if (!var9) {
         ExpoModuleRegistry.PENDING.add("ExpoCtorCache  the negative-control gate is not fully"
            + " green (" + ExpoCtorCacheGate.didFail + " of " + ExpoCtorCacheGate.shouldFail
            + " falsified inputs rejected, " + ExpoCtorCacheGate.positivesOk + " of "
            + ExpoCtorCacheGate.positives + " positive controls built, "
            + ExpoCtorCacheGate.strong + " of " + ExpoCtorCacheGate.EXPECT_STRONG
            + " value-level controls still falsifiable); each plan is now gated on its own"
            + " controls instead, so a blinded control costs coverage, not modules");
      }

      Plan[] var1 = plans();

      for (int var2 = 0; var2 < var1.length; var2++) {
         Plan var3 = var1[var2];
         Module var4 = null;

         if (!ExpoCtorCacheGate.planOk(var3.cls)) {
            failed++;
            LOG.add(var3.cls.getName() + "  withheld by its own controls");
            ExpoModuleRegistry.PENDING.add(var3.cls.getName() + "  ctor (" + var3.pack
               + ") withheld -- its positive control did not build, or one of its falsified"
               + " inputs was accepted");
            continue;
         }

         try {
            var4 = build(var3);
         } catch (Throwable var6) {
            LOG.add(var3.cls.getName() + "  build threw: " + var6);
         }

         if (var4 == null) {
            failed++;
            ExpoModuleRegistry.PENDING.add(var3.cls.getName() + "  ctor (" + var3.pack
               + ") cache pre-population failed -- " + lastFor(var3));
            continue;
         }

         built++;
         ExpoModuleRegistry.publishPrepopulated(var4, var3.cls, var3.name, var0);
      }

      SPlan[] var10 = splans();

      for (int var11 = 0; var11 < var10.length; var11++) {
         SPlan var12 = var10[var11];
         Module var13 = null;

         if (!ExpoCtorCacheGate.planOk(var12.cls)) {
            failed++;
            LOG.add(var12.cls.getName() + "  withheld by its own controls");
            ExpoModuleRegistry.PENDING.add(var12.cls.getName() + "  ctor (" + var12.pack
               + ") withheld -- its positive control did not build, or one of its falsified"
               + " inputs was accepted");
            continue;
         }

         try {
            var13 = buildS(var12);
         } catch (Throwable var14) {
            LOG.add(var12.cls.getName() + "  buildS threw: " + var14);
         }

         if (var13 == null) {
            failed++;
            ExpoModuleRegistry.PENDING.add(var12.cls.getName() + "  ctor (" + var12.pack
               + ") String-cache pre-population failed -- " + lastForS(var12));
            continue;
         }

         built++;
         ExpoModuleRegistry.publishPrepopulated(var13, var12.cls, var12.name, var0);
      }
   }

   private static String lastForS(SPlan var0) {
      for (int var1 = LOG.size() - 1; var1 >= 0; var1--) {
         if (LOG.get(var1).startsWith(var0.cls.getName())) {
            return LOG.get(var1);
         }
      }

      return "no reason recorded";
   }

   private static String lastFor(Plan var0) {
      for (int var1 = LOG.size() - 1; var1 >= 0; var1--) {
         if (LOG.get(var1).startsWith(var0.cls.getName())) {
            return LOG.get(var1);
         }
      }

      return "no reason recorded";
   }

   static Field cacheField(Class<?> var0, String var1) {
      try {
         Field var2 = var0.getDeclaredField(var1);
         var2.setAccessible(true);
         return var2;
      } catch (NoSuchFieldException var3) {
         return null;
      }
   }

   static boolean hasCache(Class<?> var0, String var1) {
      return cacheField(var0, var1) != null;
   }

   private static boolean machineryGone(Class<?> var0, Class<?> var1) {
      Field[] var2 = var0.getDeclaredFields();

      for (int var3 = 0; var3 < var2.length; var3++) {
         if (java.lang.reflect.Modifier.isStatic(var2[var3].getModifiers())
             && var2[var3].getType() == var1) {
            return false;
         }
      }

      return true;
   }

   private static Module buildInlined(Plan var0) {
      Module var1 = construct(var0, 0L);

      if (var1 == null) {
         return null;
      }

      String var2 = verify(var0, var1);

      if (var2 != null) {
         LOG.add(var0.cls.getName() + "  post-condition failed: " + var2);
         return null;
      }

      LOG.add(var0.cls.getName() + "  OK inlined -- cache field " + var0.cacheField
         + " no longer exists, every site is a bytecode literal and the post-conditions"
         + " read the constructed object directly");
      return var1;
   }

   private static Module buildSInlined(SPlan var0) {
      Module var1 = constructS(var0, 0L);

      if (var1 == null) {
         return null;
      }

      String var2 = verifyS(var0, var1);

      if (var2 != null) {
         LOG.add(var0.cls.getName() + "  post-condition failed: " + var2);
         return null;
      }

      LOG.add(var0.cls.getName() + "  OK inlined -- cache field " + var0.cacheField
         + " no longer exists");
      return var1;
   }

   private static String verifyS(SPlan var0, Module var1) {
      for (int var2 = 0; var2 < var0.sites.length; var2++) {
         SSite var3 = var0.sites[var2];

         if (var3.field == null || var3.expect == null) {
            continue;
         }

         try {
            Field var4 = var0.cls.getDeclaredField(var3.field);
            var4.setAccessible(true);
            Object var5 = var4.get(var1);

            if (!var3.expect.equals(var5)) {
               return var3.field + " is " + var5 + " but the site's value demands " + var3.expect;
            }
         } catch (Throwable var6) {
            return var3.field + " unreadable: " + var6;
         }
      }

      return null;
   }

   static Module build(Plan var0) throws Exception {
      Class<? extends Module> var1 = var0.cls;
      Field var2 = var1.getDeclaredField(var0.seedField);
      var2.setAccessible(true);

      if (var2.getType() != long.class) {
         LOG.add(var1.getName() + "  seed field " + var0.seedField + " is not long");
         return null;
      }

      long var3 = var2.getLong(null);
      Field var5 = cacheField(var1, var0.cacheField);

      if (var5 == null) {
         if (!machineryGone(var1, Integer[].class)) {
            LOG.add(var1.getName() + "  cache field " + var0.cacheField + " does not exist but"
               + " the class still declares a static Integer[] -- the plan names the wrong field");
            return null;
         }

         return buildInlined(var0);
      }

      Object var6 = var5.get(null);

      if (!(var6 instanceof Integer[])) {
         LOG.add(var1.getName() + "  cache field " + var0.cacheField + " is not Integer[]");
         return null;
      }

      Integer[] var7 = (Integer[])var6;

      if (var7.length != var0.cacheLen) {
         LOG.add(var1.getName() + "  cache length " + var7.length + " != bytecode " + var0.cacheLen);
         return null;
      }

      int var8 = (int)(var3 & 32767L);
      int var9 = -1;
      int[] var10 = null;

      for (int var11 = 0; var11 < 32768; var11++) {
         int[] var12 = new int[var0.sites.length];
         boolean var13 = true;

         for (int var14 = 0; var14 < var0.sites.length; var14++) {
            int var15 = var0.sites[var14].idx ^ ((int)(var0.sites[var14].k0 & 32767L) ^ var11)
               ^ var0.k;

            if (var15 < 0 || var15 >= var7.length || var7[var15] != null) {
               var13 = false;
               break;
            }

            var12[var14] = var15;
         }

         if (var13) {
            for (int var16 = 0; var16 < var12.length && var13; var16++) {
               for (int var17 = var16 + 1; var17 < var12.length && var13; var17++) {
                  if (var12[var16] == var12[var17]
                      && var0.sites[var16].value != var0.sites[var17].value) {
                     var13 = false;
                  }
               }
            }
         }

         if (var13) {
            var9 = var11;
            var10 = var12;
            break;
         }
      }

      if (var9 < 0) {
         LOG.add(var1.getName() + "  no carrier in 0..32767 puts every site on an in-range"
            + " currently-null slot (fail closed)");
         return null;
      }

      long var18 = (long)(var9 ^ var8);
      Module var20 = null;
      Integer[] var24 = new Integer[var7.length];
      System.arraycopy(var7, 0, var24, 0, var7.length);

      try {
         for (int var21 = 0; var21 < var10.length; var21++) {
            var7[var10[var21]] = Integer.valueOf(var0.sites[var21].value);
         }

         var20 = construct(var0, var18);
      } finally {
         System.arraycopy(var24, 0, var7, 0, var7.length);
      }

      if (var20 == null) {
         return null;
      }

      String var23 = verify(var0, var20);

      if (var23 != null) {
         LOG.add(var1.getName() + "  post-condition failed: " + var23);
         return null;
      }

      LOG.add(var1.getName() + "  OK carrier=" + var18 + " slots=" + join(var10));
      return var20;
   }

   static Module buildS(SPlan var0) throws Exception {
      Class<? extends Module> var1 = var0.cls;
      Field var2 = var1.getDeclaredField(var0.seedField);
      var2.setAccessible(true);

      if (var2.getType() != long.class) {
         LOG.add(var1.getName() + "  seed field " + var0.seedField + " is not long");
         return null;
      }

      long var3 = var2.getLong(null);
      Field var5 = cacheField(var1, var0.cacheField);

      if (var5 == null) {
         if (!machineryGone(var1, String[].class)) {
            LOG.add(var1.getName() + "  cache field " + var0.cacheField + " does not exist but"
               + " the class still declares a static String[] -- the plan names the wrong field");
            return null;
         }

         return buildSInlined(var0);
      }

      Object var6 = var5.get(null);

      if (!(var6 instanceof String[])) {
         LOG.add(var1.getName() + "  cache field " + var0.cacheField + " is not String[]");
         return null;
      }

      String[] var7 = (String[])var6;

      if (var7.length != var0.cacheLen) {
         LOG.add(var1.getName() + "  cache length " + var7.length + " != source " + var0.cacheLen);
         return null;
      }

      int var8 = (int)(var3 & 32767L);
      int var9 = -1;
      int[] var10 = null;

      for (int var11 = 0; var11 < 32768; var11++) {
         int[] var12 = new int[var0.sites.length];
         boolean var13 = true;

         for (int var14 = 0; var14 < var0.sites.length; var14++) {
            int var15 = var0.sites[var14].idx ^ ((int)(var0.sites[var14].k0 & 32767L) ^ var11)
               ^ var0.k;

            if (var15 < 0 || var15 >= var7.length || var7[var15] != null) {
               var13 = false;
               break;
            }

            var12[var14] = var15;
         }

         if (var13) {
            for (int var16 = 0; var16 < var12.length && var13; var16++) {
               for (int var17 = var16 + 1; var17 < var12.length && var13; var17++) {
                  if (var12[var16] == var12[var17]
                      && !var0.sites[var16].value.equals(var0.sites[var17].value)) {
                     var13 = false;
                  }
               }
            }
         }

         if (var13) {
            var9 = var11;
            var10 = var12;
            break;
         }
      }

      if (var9 < 0) {
         LOG.add(var1.getName() + "  no carrier in 0..32767 puts every String site on an"
            + " in-range currently-null slot (fail closed)");
         return null;
      }

      long var18 = (long)(var9 ^ var8);
      Module var20 = null;
      String[] var24 = new String[var7.length];
      System.arraycopy(var7, 0, var24, 0, var7.length);

      try {
         for (int var21 = 0; var21 < var10.length; var21++) {
            var7[var10[var21]] = var0.sites[var21].value;
         }

         var20 = constructS(var0, var18);
      } finally {
         System.arraycopy(var24, 0, var7, 0, var7.length);
      }

      if (var20 == null) {
         return null;
      }

      String var22 = verifyS(var0, var20);

      if (var22 != null) {
         LOG.add(var1.getName() + "  post-condition failed: " + var22);
         return null;
      }

      LOG.add(var1.getName() + "  OK carrier=" + var18 + " slots=" + join(var10));
      return var20;
   }

   private static Module constructS(SPlan var0, long var1) {
      try {
         if ("J".equals(var0.pack)) {
            Constructor<?> var3 = var0.cls.getDeclaredConstructor(long.class);
            return (Module)var3.newInstance(Long.valueOf(var1));
         }

         LOG.add(var0.cls.getName() + "  unknown carrier packing " + var0.pack);
         return null;
      } catch (Throwable var4) {
         LOG.add(var0.cls.getName() + "  constructor threw: " + var4);
         return null;
      }
   }

   private static String join(int[] var0) {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < var0.length; var2++) {
         if (var2 > 0) {
            var1.append(',');
         }

         var1.append(var0[var2]);
      }

      return var1.toString();
   }

   private static Module construct(Plan var0, long var1) {
      try {
         Constructor<?> var3;

         if ("J".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(long.class);
            return (Module)var3.newInstance(Long.valueOf(var1));
         } else if ("SSI".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(short.class, short.class, int.class);
            return (Module)var3.newInstance(Short.valueOf((short)0), Short.valueOf((short)0),
               Integer.valueOf((int)var1));
         } else if ("JI".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(long.class, int.class);
            return (Module)var3.newInstance(Long.valueOf(0L), Integer.valueOf((int)var1));
         } else if ("BJ".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(byte.class, long.class);
            return (Module)var3.newInstance(Byte.valueOf((byte)0), Long.valueOf(var1));
         } else if ("ICS".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(int.class, char.class, short.class);
            return (Module)var3.newInstance(Integer.valueOf(0), Character.valueOf('\0'),
               Short.valueOf((short)var1));
         } else if ("IIB".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(int.class, int.class, byte.class);
            return (Module)var3.newInstance(Integer.valueOf(0),
               Integer.valueOf((int)(var1 >>> 8)), Byte.valueOf((byte)var1));
         } else if ("IBI".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(int.class, byte.class, int.class);
            return (Module)var3.newInstance(Integer.valueOf(0), Byte.valueOf((byte)0),
               Integer.valueOf((int)var1));
         } else if ("ICC".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(int.class, char.class, char.class);
            return (Module)var3.newInstance(Integer.valueOf(0), Character.valueOf('\0'),
               Character.valueOf((char)var1));
         } else if ("ICI".equals(var0.pack)) {
            var3 = var0.cls.getDeclaredConstructor(int.class, char.class, int.class);
            return (Module)var3.newInstance(Integer.valueOf(0), Character.valueOf('\0'),
               Integer.valueOf((int)var1));
         }

         LOG.add(var0.cls.getName() + "  unknown carrier packing " + var0.pack);
         return null;
      } catch (Throwable var4) {
         LOG.add(var0.cls.getName() + "  constructor threw: " + var4);
         return null;
      }
   }

   private static String verify(Plan var0, Module var1) {
      for (int var2 = 0; var2 < var0.sites.length; var2++) {
         Site var3 = var0.sites[var2];

         if (var3.field == null || var3.desc == null) {
            continue;
         }

         try {
            Field var4 = var0.cls.getDeclaredField(var3.field);
            var4.setAccessible(true);

            if ("Z".equals(var3.desc)) {
               boolean var5 = var4.getBoolean(var1);

               if (var5 != ((var3.value & 1) != 0)) {
                  return var3.field + ":Z is " + var5 + " but the site's value " + var3.value
                     + " demands " + ((var3.value & 1) != 0);
               }
            } else if ("I".equals(var3.desc)) {
               int var6 = var4.getInt(var1);

               if (var6 != var3.value) {
                  return var3.field + ":I is " + var6 + " but the site's value is " + var3.value;
               }
            } else {
               return var3.field + " has unhandled descriptor " + var3.desc;
            }
         } catch (Throwable var7) {
            return var3.field + " unreadable: " + var7;
         }
      }

      return extra(var0, var1);
   }

   private static String extra(Plan var0, Module var1) {
      try {
         if (var0.cls == Expo.module.impl.player.FastCraft.class) {
            Object var_J = get(var0, var1, "J");
            int[] exp_J = new int[]{1, 2, 3, 4, 6};
            if (var_J == null || Array.getLength(var_J) != exp_J.length) {
               return "J has the wrong length";
            }
            for (int var_i_J = 0; var_i_J < exp_J.length; var_i_J++) {
               if (Array.getInt(var_J, var_i_J) != exp_J[var_i_J]) {
                  return "J[" + var_i_J + "] is " + Array.getInt(var_J, var_i_J)
                     + " but the recovered constant set demands " + exp_J[var_i_J];
               }
            }
            Object var_s = get(var0, var1, "s");
            int[] exp_s = new int[]{1, 3, 4, 5, 6, 7, 8, 9};
            if (var_s == null || Array.getLength(var_s) != exp_s.length) {
               return "s has the wrong length";
            }
            for (int var_i_s = 0; var_i_s < exp_s.length; var_i_s++) {
               if (Array.getInt(var_s, var_i_s) != exp_s[var_i_s]) {
                  return "s[" + var_i_s + "] is " + Array.getInt(var_s, var_i_s)
                     + " but the recovered constant set demands " + exp_s[var_i_s];
               }
            }
            Object var_G = get(var0, var1, "G");
            int[] exp_G = new int[]{1, 2, 3, 4, 6, 7, 9};
            if (var_G == null || Array.getLength(var_G) != exp_G.length) {
               return "G has the wrong length";
            }
            for (int var_i_G = 0; var_i_G < exp_G.length; var_i_G++) {
               if (Array.getInt(var_G, var_i_G) != exp_G[var_i_G]) {
                  return "G[" + var_i_G + "] is " + Array.getInt(var_G, var_i_G)
                     + " but the recovered constant set demands " + exp_G[var_i_G];
               }
            }
            Object var_m = get(var0, var1, "m");
            int[] exp_m = new int[]{4, 6, 7, 9};
            if (var_m == null || Array.getLength(var_m) != exp_m.length) {
               return "m has the wrong length";
            }
            for (int var_i_m = 0; var_i_m < exp_m.length; var_i_m++) {
               if (Array.getInt(var_m, var_i_m) != exp_m[var_i_m]) {
                  return "m[" + var_i_m + "] is " + Array.getInt(var_m, var_i_m)
                     + " but the recovered constant set demands " + exp_m[var_i_m];
               }
            }
            Object var_t = get(var0, var1, "t");
            int[] exp_t = new int[]{1, 3, 4, 5, 6, 7, 9};
            if (var_t == null || Array.getLength(var_t) != exp_t.length) {
               return "t has the wrong length";
            }
            for (int var_i_t = 0; var_i_t < exp_t.length; var_i_t++) {
               if (Array.getInt(var_t, var_i_t) != exp_t[var_i_t]) {
                  return "t[" + var_i_t + "] is " + Array.getInt(var_t, var_i_t)
                     + " but the recovered constant set demands " + exp_t[var_i_t];
               }
            }
         }
         if (var0.cls == Expo.module.impl.visual_utility.InventoryHUD.class) {
            Object var_p = get(var0, var1, "p");
            if (var_p == null || Array.getLength(var_p) != 27) {
               return "p.length != 27";
            }
         }
         if (var0.cls == Expo.module.impl.visual.ArrayList.class) {
            Object var_d = get(var0, var1, "d");
            if (!(var_d instanceof java.util.List)) {
               return "d is not a List";
            }
            if (!((java.util.List)var_d).isEmpty()) {
               return "d is not empty after the constructor";
            }
            Object var_U = get(var0, var1, "U");
            if (!(var_U instanceof java.util.List) || !((java.util.List)var_U).isEmpty()) {
               return "U is not an empty List after the constructor";
            }
         }
      } catch (Throwable var9) {
         return "structural post-condition threw: " + var9;
      }

      return null;
   }

   private static Object get(Plan var0, Module var1, String var2) throws Exception {
      Field var3 = var0.cls.getDeclaredField(var2);
      var3.setAccessible(true);
      return var3.get(var1);
   }
}
