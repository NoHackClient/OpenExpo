package Expo.util;

import Expo.enums.DetectedCheat;
import Expo.internal.CheaterDetectionSample;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheaterRegistry {
   public List<CheaterDetectionSample> G;
   public final Map<DetectedCheat, Boolean> e;
   private static long a;
   public boolean I;
   public float p;
   public long R;
   private boolean m;
   public final Map<DetectedCheat, Integer> c;
   private static long i;
   public final Map<DetectedCheat, Integer> d;
   public long Y;
   public float J;
   public int f;
   public boolean j;

   public void C(long var1, DetectedCheat var3) {
      this.e.put(var3, true);
      this.m = true;
   }

   public boolean M() {
      return this.m;
   }

   public int g(DetectedCheat var1) {
      return this.c.get(var1);
   }

   public CheaterRegistry(long var1) {
      var1 = a ^ var1;
      this.c = new LinkedHashMap<>();
      this.d = new LinkedHashMap<>();
      this.e = new HashMap<>();
      this.I = false;
      this.f = 0;
      this.G = new ArrayList<>();
      this.Y = i;
      this.R = 0L;
      this.j = false;
      this.p = 0.0F;
      this.J = 0.0F;
      this.m = false;
      DetectedCheat[] var3 = DetectedCheat.values();

      for (DetectedCheat var7 : var3) {
         this.c.put(var7, 0);
         this.e.put(var7, false);
      }
   }

   static {
      a = 85887610707744L;
   }

   public boolean V(DetectedCheat var1) {
      return this.e.get(var1);
   }

   public void D(DetectedCheat var1, int var2) {
      this.c.put(var1, Math.max(this.c.get(var1) + var2, 0));
   }
}
