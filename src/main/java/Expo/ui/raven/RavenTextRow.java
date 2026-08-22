package Expo.ui.raven;

import Expo.module.Module;
import Expo.module.impl.configuration.Language;
import Expo.setting.Setting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.TextSetting;
import java.text.MessageFormat;
import org.lwjgl.opengl.GL11;

public class RavenTextRow extends AbstractRavenSettingRow {
   private Module p;
   private static long d;
   private static String c;
   private TextSetting L;
   private HeaderSetting j;

   private void s(HeaderSetting var1, RavenModuleRow var2, int var3) {
      this.j = var1;
      this.p = var2.R;
      this.h = var2.O.X() + var2.O.t();
      this.g = var2.O.T() + var2.L;
      this.y = var3;
   }

   private void V(TextSetting var1, RavenModuleRow var2, int var3) {
      this.L = var1;
      this.p = var2.R;
      this.h = var2.O.X() + var2.O.t();
      this.g = var2.O.T() + var2.L;
      this.y = var3;
   }

   public void i(int var1, int var2, int var3, byte var4) {
      this.y = var1;
   }

   public RavenTextRow(HeaderSetting var1, RavenModuleRow var2, int var3) {
      super(var2);
      this.s(var1, var2, var3);
   }

   static {
      c = "clickgui.description.edit";
      d = -3567882023462699137L;
   }

   public Setting f() {
      return this.j;
   }

   public void U(long var1) {
      long var3 = var1 ^ 132092541766008L;
      long var5 = var1 ^ 68628560147625L;
      int var7 = (int)((var1 ^ 58246301632428L) >>> 56);
      long var8 = (var1 ^ 58246301632428L) << 8 >>> 8;
      long var12 = var1 ^ 39350870069077L;
      GL11.glPushMatrix();
      GL11.glScaled(0.5, 0.5, 0.5);
      int var14 = (int)d;
      String var15;
      if (this.L != null) {
         var15 = MessageFormat.format(Language.z(c,0L), this.L.e((byte)var7, this.p, var8));
      } else {
         var15 = this.j.U(var12, this.p);
      }

      this.C(var5).T(var3, var15, (this.O.O.X() + 4) * 2, (this.O.O.T() + this.y + 4) * 2, var14);
      GL11.glPopMatrix();
   }

   public RavenTextRow(TextSetting var1, RavenModuleRow var2, int var3) {
      super(var2);
      this.V(var1, var2, var3);
   }
}
