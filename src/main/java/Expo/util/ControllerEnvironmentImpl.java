package Expo.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.util.plugins.Plugins;











public class ControllerEnvironmentImpl extends ControllerEnvironment {
   private final Collection<String> x = new ArrayList<>();
   private ArrayList<Controller> N;
   private static long a;


   private void c(String var1) {
      File var2 = new File(var1);
      if (var2.exists()) {
         try {
            Plugins var3 = new Plugins(var2);
            Class[] var4 = var3.getExtends(ControllerEnvironment.class);

            for (Class var8 : var4) {
               ControllerEnvironment var9 = (ControllerEnvironment)var8.getDeclaredConstructor().newInstance();
               if (var9.isSupported()) {
                  this.k(var9.getControllers());
                  this.x.add(var9.getClass().getName());
               }
            }
         } catch (Exception var10) {
            Expo.internal.restore.ExpoDiag.attribute(var10, "ControllerEnvironmentImpl.c/1#0");
         }
      }
   }

   private void k(Controller[] var1) {
      this.N.addAll(Arrays.asList(var1));
   }

   public void d(long var1) {
      String var3 = System.getProperty("jinput.controllerPluginPath");
      if (var3 == null) {
         var3 = "controller";
      }

      this.c(System.getProperty("java.home") + File.separator + "lib" + File.separator + var3);
      this.c(System.getProperty("user.dir") + File.separator + var3);
   }

   public boolean isSupported() {
      return true;
   }

   static {
      a = 10551167990814L;
   }

   public Controller[] getControllers() {
      if (this.N == null) {
         this.N = new ArrayList<>();
         this.d(0L);
         ArrayList var5 = new ArrayList();
         String var6 = System.getProperty("os.name", "").trim();
         if (var6.equalsIgnoreCase("Linux")) {
            var5.add("net.java.games.input.LinuxEnvironmentPlugin");
         } else if (var6.equalsIgnoreCase("Mac OS X")) {
            var5.add("net.java.games.input.OSXEnvironmentPlugin");
         } else if (var6.contains("Windows")) {
            var5.add("net.java.games.input.DirectAndRawInputEnvironmentPlugin");
         }

         for (String var8 : (Iterable<String>)(var5)) {
            try {
               if (!this.x.contains(var8)) {
                  Class var9 = Class.forName(var8);
                  ControllerEnvironment var10 = (ControllerEnvironment)var9.getDeclaredConstructor().newInstance();
                  this.k(var10.getControllers());
                  this.x.add(var10.getClass().getName());
               }
            } catch (Exception var11) {
               Expo.internal.restore.ExpoDiag.attribute(var11, "ControllerEnvironmentImpl.getControllers/0#0");
            }
         }
      }

      return this.N.toArray(new Controller[0]);
   }

}
