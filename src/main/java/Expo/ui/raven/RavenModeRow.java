package Expo.ui.raven;

import Expo.module.Module;
import Expo.setting.Setting;
import Expo.setting.settings.ModeSetting;
import Expo.util.KeyBindUtil;
import Expo.util.MinecraftRef;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;











public class RavenModeRow extends AbstractRavenSettingRow {
   private static String d;
   private static Minecraft R;
   private ModeSetting q;
   private static long c;
   private Module z;

   private void q(int var1, boolean var2, long var3) {
      long var5 = var3 ^ 76954889919131L;
      boolean var7;
      switch (var1) {
         case 0:
            var7 = true;
            break;
         case 1:
            var7 = false;
            break;
         default:
            return;
      }

      if (var2) {
         var7 = !var7;
      }

      if (var7) {
         this.q.w(var5);
      } else {
         this.q.X();
      }
   }

   private void O(ModeSetting var1, RavenModuleRow var2, int var3) {
      this.q = var1;
      this.z = var2.R;
      this.h = var2.O.X() + var2.O.t();
      this.g = var2.O.T() + var2.L;
      this.y = var3;
   }

   public void V(long var1, int var3, int var4) {
      this.g = this.O.O.T() + this.y;
      this.h = this.O.O.X();
   }



   public void U(long var1) {
      long var3 = var1 ^ 11699446097668L;
      int var5 = (int)((var1 ^ 67731632783900L) >>> 48);
      int var6 = (int)((var1 ^ 67731632783900L) << 16 >>> 48);
      int var7 = (int)((var1 ^ 67731632783900L) << 32 >>> 32);
      long var8 = var1 ^ 68628560147625L;
      int var10 = (int)((var1 ^ 58246301632428L) >>> 56);
      long var11 = (var1 ^ 58246301632428L) << 8 >>> 8;
      GL11.glPushMatrix();
      GL11.glScaled(0.5, 0.5, 0.5);
      String var13 = this.q.a((char)var5, (short)var6, this.z, var7);
      this.C(var8)
         .v(
            this.q.e((byte)var10, this.z, var11).replaceAll("-", " ") + d + var13,
            (int)((this.O.O.X() + 4) * 2.0F),
            (int)((this.O.O.T() + this.y + 3) * 2.0F),
            this.P,
            var3,
            true
         );
      GL11.glPopMatrix();
   }

   public RavenModeRow(ModeSetting var1, RavenModuleRow var2, int var3) {
      super(var2);
      this.O(var1, var2, var3);
   }

   static {
      c = 69705471025448L;
      // add code
      R = MinecraftRef.c((byte)0, 0L);
      d = ": ";
   }

   public void r(char var1, int var2, int var3, int var4, long var5) {
      long var7 = (long)var1 << 48 | var5 << 16 >>> 16;
      long var9 = (var7 ^ 94618552824683L) >>> 16;
      int var11 = (int)((var7 ^ 94618552824683L) << 48 >>> 48);
      long var12 = var7 ^ 102627593736782L;
      long var14 = var7 ^ 134744598610607L;
      int var16 = (int)((var7 ^ 107445156594623L) >>> 56);
      long var19 = var7 ^ 20422209034961L;
      if (this.D(var14, var2, var3) && this.O.k) {
         this.q(var4, KeyBindUtil.V(MinecraftRef.c((byte)var16,0L).gameSettings.keyBindSneak.getKeyCode(), var19), var12);
         this.O.O.M(var9, (char)var11);
      }
   }

   public void i(int var1, int var2, int var3, byte var4) {
      this.y = var1;
   }

   public Setting f() {
      return this.q;
   }

}
