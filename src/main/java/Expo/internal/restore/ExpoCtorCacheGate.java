package Expo.internal.restore;

import Expo.module.Module;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class ExpoCtorCacheGate {

   public static final List<String> LOG = new ArrayList<String>();

   public static final int EXPECT_STRONG = 29;

   public static int shouldFail;

   public static int didFail;

   public static int positives;

   public static int positivesOk;

   public static int strong;

   public static int inert;

   private static final Map<Class<?>, Boolean> OK = new HashMap<Class<?>, Boolean>();

   private static final Set<Class<?>> RAN = new HashSet<Class<?>>();

   private ExpoCtorCacheGate() {
   }

   public static boolean planOk(Class<?> var0) {
      if (!RAN.contains(var0)) {
         return false;
      }

      Boolean var1 = OK.get(var0);
      return var1 != null && var1.booleanValue();
   }

   public static boolean run() {
      LOG.clear();
      OK.clear();
      RAN.clear();
      shouldFail = 0;
      didFail = 0;
      positives = 0;
      positivesOk = 0;
      strong = 0;
      inert = 0;

      ExpoCtorCache.Plan[] var0 = ExpoCtorCache.plans();

      for (int var1 = 0; var1 < var0.length; var1++) {
         try {
            runPlan(var0[var1]);
         } catch (Throwable var6) {
            OK.put(var0[var1].cls, Boolean.FALSE);
            LOG.add("GATE " + var0[var1].name + ": the control sweep itself threw " + var6);
         }
      }

      ExpoCtorCache.SPlan[] var2 = ExpoCtorCache.splans();

      for (int var3 = 0; var3 < var2.length; var3++) {
         try {
            runSPlan(var2[var3]);
         } catch (Throwable var5) {
            OK.put(var2[var3].cls, Boolean.FALSE);
            LOG.add("GATE " + var2[var3].name + ": the control sweep itself threw " + var5);
         }
      }

      boolean var4 = positivesOk == positives && shouldFail == didFail
                     && strong >= EXPECT_STRONG;

      LOG.add("GATE positive controls " + positivesOk + "/" + positives
              + ", falsified inputs rejected " + didFail + "/" + shouldFail
              + ", plans with a value-level control " + strong + "/" + EXPECT_STRONG
              + ", inert sites " + inert + " -- " + (var4 ? "GREEN" : "RED"));
      return var4;
   }

   private static void runPlan(ExpoCtorCache.Plan var0) {
      RAN.add(var0.cls);
      boolean var1 = true;
      positives++;

      if (tryBuild(var0) == null) {
         var1 = false;
         LOG.add("GATE " + var0.name + ": POSITIVE CONTROL FAILED -- the un-falsified plan does"
                 + " not build");
      } else {
         positivesOk++;
      }

      boolean var2 = ExpoCtorCache.hasCache(var0.cls, var0.cacheField);

      if (var2) {
         var1 &= must(var0.name + " declared cache length",
                      with(var0, var0.seedField, var0.cacheField, var0.cacheLen + 1,
                           copy(var0.sites)));
         var1 &= must(var0.name + " cache field name",
                      with(var0, var0.seedField, var0.cacheField + "$nope", var0.cacheLen,
                           copy(var0.sites)));
      }

      var1 &= must(var0.name + " seed field name",
                   with(var0, var0.seedField + "$nope", var0.cacheField, var0.cacheLen,
                        copy(var0.sites)));

      boolean var3 = false;

      for (int var4 = 0; var4 < var0.sites.length; var4++) {
         if (var0.sites[var4].field == null || var0.sites[var4].desc == null) {
            continue;
         }

         var1 &= must(var0.name + " post-condition field name of site " + var4,
                      fieldName(var0, var4));

         if (reject(value(var0, var4)) || reject(slot(var0, var4))) {
            var3 = true;
            continue;
         }

         inert++;
         LOG.add("GATE " + var0.name + ": site " + var4 + " (" + var0.sites[var4].field
                 + ") is INERT -- neither its value nor its slot changes the built module");
      }

      if (var3) {
         strong++;
      } else {
         LOG.add("GATE " + var0.name + ": no value-level control is falsifiable any more");
      }

      OK.put(var0.cls, Boolean.valueOf(var1));
   }

   private static void runSPlan(ExpoCtorCache.SPlan var0) {
      RAN.add(var0.cls);
      boolean var1 = true;
      positives++;

      if (tryBuildS(var0) == null) {
         var1 = false;
         LOG.add("GATE " + var0.name + ": POSITIVE CONTROL FAILED -- the un-falsified plan does"
                 + " not build");
      } else {
         positivesOk++;
      }

      boolean var2 = ExpoCtorCache.hasCache(var0.cls, var0.cacheField);

      if (var2) {
         var1 &= mustS(var0.name + " declared String cache length",
                       sWith(var0, var0.seedField, var0.cacheField, var0.cacheLen + 1,
                             sCopy(var0.sites)));
         var1 &= mustS(var0.name + " String cache field name",
                       sWith(var0, var0.seedField, var0.cacheField + "$nope", var0.cacheLen,
                             sCopy(var0.sites)));
      }

      var1 &= mustS(var0.name + " String seed field name",
                    sWith(var0, var0.seedField + "$nope", var0.cacheField, var0.cacheLen,
                          sCopy(var0.sites)));

      boolean var3 = false;

      for (int var4 = 0; var4 < var0.sites.length; var4++) {
         if (var0.sites[var4].field == null || var0.sites[var4].expect == null) {
            continue;
         }

         var1 &= mustS(var0.name + " String post-condition of site " + var4,
                       sExpect(var0, var4));

         if (rejectS(sValue(var0, var4)) || rejectS(sSlot(var0, var4))) {
            var3 = true;
            continue;
         }

         inert++;
         LOG.add("GATE " + var0.name + ": String site " + var4 + " (" + var0.sites[var4].field
                 + ") is INERT");
      }

      if (var3) {
         strong++;
      } else {
         LOG.add("GATE " + var0.name + ": no String value-level control is falsifiable any more");
      }

      OK.put(var0.cls, Boolean.valueOf(var1));
   }

   private static Module tryBuild(ExpoCtorCache.Plan var0) {
      try {
         return ExpoCtorCache.build(var0);
      } catch (Throwable var2) {
         return null;
      }
   }

   private static Module tryBuildS(ExpoCtorCache.SPlan var0) {
      try {
         return ExpoCtorCache.buildS(var0);
      } catch (Throwable var2) {
         return null;
      }
   }

   private static boolean reject(ExpoCtorCache.Plan var0) {
      return tryBuild(var0) == null;
   }

   private static boolean rejectS(ExpoCtorCache.SPlan var0) {
      return tryBuildS(var0) == null;
   }

   private static boolean must(String var0, ExpoCtorCache.Plan var1) {
      shouldFail++;

      if (tryBuild(var1) == null) {
         didFail++;
         return true;
      }

      LOG.add("GATE " + var0 + ": ACCEPTED -- THE GATE IS BROKEN");
      return false;
   }

   private static boolean mustS(String var0, ExpoCtorCache.SPlan var1) {
      shouldFail++;

      if (tryBuildS(var1) == null) {
         didFail++;
         return true;
      }

      LOG.add("GATE " + var0 + ": ACCEPTED -- THE GATE IS BROKEN");
      return false;
   }

   private static ExpoCtorCache.Site[] copy(ExpoCtorCache.Site[] var0) {
      ExpoCtorCache.Site[] var1 = new ExpoCtorCache.Site[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = new ExpoCtorCache.Site(var0[var2].idx, var0[var2].k0, var0[var2].value,
            var0[var2].field, var0[var2].desc);
      }

      return var1;
   }

   private static ExpoCtorCache.SSite[] sCopy(ExpoCtorCache.SSite[] var0) {
      ExpoCtorCache.SSite[] var1 = new ExpoCtorCache.SSite[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = new ExpoCtorCache.SSite(var0[var2].idx, var0[var2].k0, var0[var2].value,
            var0[var2].field, var0[var2].expect);
      }

      return var1;
   }

   private static ExpoCtorCache.Plan with(ExpoCtorCache.Plan var0, String var1, String var2,
                                          int var3, ExpoCtorCache.Site[] var4) {
      return new ExpoCtorCache.Plan(var0.cls, var0.name, var1, var2, var0.k, var3, var0.pack, var4);
   }

   private static ExpoCtorCache.SPlan sWith(ExpoCtorCache.SPlan var0, String var1, String var2,
                                            int var3, ExpoCtorCache.SSite[] var4) {
      return new ExpoCtorCache.SPlan(var0.cls, var0.name, var1, var2, var0.k, var3, var0.pack,
         var4);
   }

   private static ExpoCtorCache.Plan value(ExpoCtorCache.Plan var0, int var1) {
      ExpoCtorCache.Site[] var2 = copy(var0.sites);
      var2[var1] = new ExpoCtorCache.Site(var2[var1].idx, var2[var1].k0, var2[var1].value + 1,
         var2[var1].field, var2[var1].desc);
      return with(var0, var0.seedField, var0.cacheField, var0.cacheLen, var2);
   }

   private static ExpoCtorCache.Plan slot(ExpoCtorCache.Plan var0, int var1) {
      ExpoCtorCache.Site[] var2 = copy(var0.sites);
      var2[var1] = new ExpoCtorCache.Site(var2[var1].idx ^ 1, var2[var1].k0, var2[var1].value,
         var2[var1].field, var2[var1].desc);
      return with(var0, var0.seedField, var0.cacheField, var0.cacheLen, var2);
   }

   private static ExpoCtorCache.Plan fieldName(ExpoCtorCache.Plan var0, int var1) {
      ExpoCtorCache.Site[] var2 = copy(var0.sites);
      var2[var1] = new ExpoCtorCache.Site(var2[var1].idx, var2[var1].k0, var2[var1].value,
         var2[var1].field + "$nope", var2[var1].desc);
      return with(var0, var0.seedField, var0.cacheField, var0.cacheLen, var2);
   }

   private static ExpoCtorCache.SPlan sValue(ExpoCtorCache.SPlan var0, int var1) {
      ExpoCtorCache.SSite[] var2 = sCopy(var0.sites);
      var2[var1] = new ExpoCtorCache.SSite(var2[var1].idx, var2[var1].k0,
         var2[var1].value + "$nope", var2[var1].field, var2[var1].expect);
      return sWith(var0, var0.seedField, var0.cacheField, var0.cacheLen, var2);
   }

   private static ExpoCtorCache.SPlan sSlot(ExpoCtorCache.SPlan var0, int var1) {
      ExpoCtorCache.SSite[] var2 = sCopy(var0.sites);
      var2[var1] = new ExpoCtorCache.SSite(var2[var1].idx ^ 1, var2[var1].k0, var2[var1].value,
         var2[var1].field, var2[var1].expect);
      return sWith(var0, var0.seedField, var0.cacheField, var0.cacheLen, var2);
   }

   private static ExpoCtorCache.SPlan sExpect(ExpoCtorCache.SPlan var0, int var1) {
      ExpoCtorCache.SSite[] var2 = sCopy(var0.sites);
      var2[var1] = new ExpoCtorCache.SSite(var2[var1].idx, var2[var1].k0, var2[var1].value,
         var2[var1].field, var2[var1].expect + "$nope");
      return sWith(var0, var0.seedField, var0.cacheField, var0.cacheLen, var2);
   }
}
