package Expo.ui.raven;

import Expo.enums.Easing;
import Expo.module.Category;
import Expo.module.Module;
import Expo.module.ModuleManager;
import Expo.module.impl.configuration.ClickGUI;
import Expo.module.impl.configuration.Theme;
import Expo.util.Animator;
import Expo.util.MinecraftRef;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;


public class RavenCategoryPanel {
   private boolean x;
   private static long b;
   private static int a;
   private Animator H;
   private static Integer[] f;
   private static int c;
   private float z;
   private int U;
   public boolean V;
   private int q;
   private static int Q;
   private static int C;
   private int P;
   public boolean s;
   public String Z;
   private static long[] d;
   private static long i;
   public int N;
   public int l;
   private int e;
   public Category w;
   private RavenAnimation E;
   private static Map h;
   public boolean g;
   public int u;
   public boolean D;
   public List<RavenModuleRow> R;

   public void v(int var1) {
      this.e = var1;
   }

   public void T(CustomFont var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {







      int var17 = 86;

      int var22 = 48816;

      int var27 = 47391;
      double var32 = ClickGUI.scale.L();
      this.U = 92;
      int var34 = 0;
      if (!this.R.isEmpty() && this.x) {
         for (RavenElement var35 : this.R) {
            var34 += var35.E(101577281637281L);
         }

         this.z = var34;
      }

      this.H.i(24948514690095L, this.x ? 1.0 : 0.0);
      float var40 = (float)this.H.Z();
      float var41 = this.P + this.q + 4 + var34 * var40;
      if (!this.x) {
         if (this.E == null) {
            var41 = this.P + this.q + var34 * var40 + 4.0F;
         } else {
            float var37 = this.E.m(0.0F, this.z, 1);
            var41 = this.P + this.q + 4 + this.z - var37;
         }
      }

      GL11.glPushMatrix();
      Expo.util.render.RenderUtil.N(1334551664L, (char)21658);
      Expo.util.render.RenderUtil.P((int)(0.0 * var32), (int)((this.P - 2) * var32), (int)((this.e + this.U + 4) * var32), (int)((var41 - this.P + 4.0F) * var32));
      Expo.util.render.RenderUtil.m(
         this.e - 2,
         this.P,
         this.e + this.U + 2,
         var41,
         10.0F,
         Q,
         (Integer)Theme.k(22853, 17908, (short)var22).get(0),
         45584246178720L,
         (Integer)Theme.k(22853, 17908, (short)var22).get(1),
         (Integer)Theme.k(22853, 17908, (short)var22).get(2)
      );
      boolean var28 = this.x || this.g;
      int var29 = (this.P + 4);
      int var30 = (this.e + 1);
      Category var31 = this.w;
      this.O(18070, 13384080, var31, (byte)var17, var30, var29, var28);
      var1.v(this.D ? this.Z : this.w.x(12139, 2577, (short)var27), this.e + 12, this.P + 4, a, 88827598794260L, false);
      if (!this.D) {
         GL11.glPushMatrix();
         var1.v(this.x ? "-" : "+", this.e + 80, (float)(this.P + 4.5), this.x ? C : c, 88827598794260L, false);
         GL11.glPopMatrix();
         if (this.x && !this.R.isEmpty()) {
            for (RavenModuleRow var38 : this.R) {
               var38.U(99412188383504L);
            }
         }
      }

      Expo.util.render.RenderUtil.q(112619748911265L);
      GL11.glPopMatrix();
   }

   public void S(boolean var1) {
      this.s = var1;
   }

   static {
      b = 7843458566225L;
      h = new HashMap(13);
      d = new long[]{4761757691821300281L, 5113570216143711950L, 7076288492692668227L, -8175806172379427661L, 8756823661108571545L, 6603669316710787097L, -7818434524661274496L, -3788492994295951298L, 8437643454212387449L, -7487685381110389523L, 3649149712541518431L, 5248284696307956101L, 1213003007859160371L, 695343510205831688L, 5850844154461767343L, -2718821263593996205L, -1754807722676416291L, -3652659546346572275L, -5500673475836309612L, 5904796349296154618L, -427002400845779974L};
      f = new Integer[21];
      i = 600L;
   }

   public void y(int var1) {
      this.e = var1;
   }

   public void h(int var1, int var2) {
      if (this.V) {
         this.y(var1 - this.u);
         this.k(var2 - this.N);
      }

      this.g = this.j(var1, var2);
   }

   public boolean h() {
      return this.x;
   }

   public int X() {
      return this.e;
   }

   public int t() {
      return this.U;
   }

   public void M(long var1, char var3) {
      long var4 = (var1 << 16 | (long)var3 << 48 >>> 48) ^ b;
      long var6 = var4 ^ 41262889057000L;
      int var8 = (int)((var4 ^ 50675654604693L) >>> 32);
      int var9 = (int)((var4 ^ 50675654604693L) << 32 >>> 40);
      int var10 = (int)((var4 ^ 50675654604693L) << 56 >>> 56);
      int var11 = this.q + 3;

      for (RavenElement var12 : this.R) {
         var12.i(var11, var8, var9, (byte)var10);
         var11 += var12.E(var6);
      }
   }

   public void m(boolean var1) {
      this.V = var1;
   }

   public void p(int var1) {
      this.P = var1;
   }

   public RavenCategoryPanel(int var1, Category var2, char var3, int var4) {
      long var5 = ((long)var1 << 32 | (long)var3 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ b;
      long var7 = var5 ^ 68863845366281L;
      this.R = new CopyOnWriteArrayList<>();
      this.D = false;
      this.s = false;
      this.g = false;
      this.K(var7, var2);
   }

   // add code
   public RavenCategoryPanel(Category var1, int var2) {
      Q = new Color(0, 0, 0, 110).getRGB();
      a = new Color(220, 220, 220).getRGB();
      C = new Color(250, 95, 85).getRGB();
      c = new Color(135, 238, 144).getRGB();
      this.R = new CopyOnWriteArrayList<>();
      this.D = false;
      this.s = false;
      this.g = false;
      this.w = var1;
      this.Z = null;
      this.U = 92;
      this.e = 5;
      this.P = var2;
      this.q = 13;
      this.E = null;
      this.u = 0;
      this.N = 0;
      this.z = 0.0F;
      this.x = false;
      this.V = false;
      this.l = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();
      this.H = new Animator(Easing.EASE_OUT_QUART, i);
   }

   // add code
   public int ravenWidth() {
      return this.U;
   }

   // add code
   public int ravenHeader() {
      return this.q;
   }

   // add code
   public static long ravenAnimationMillis() {
      return i;
   }

   public boolean p(int var1, int var2, long var3) {
      return var1 >= this.e + 77
         && var1 <= this.e + this.U - 6
         && var2 >= this.P + 2.0F
         && var2 <= this.P + this.q + 1;
   }

   private void K(long var1, Category var3) {






      Q = new Color(0, 0, 0, 110).getRGB();
      a = new Color(220, 220, 220).getRGB();
      C = new Color(250, 95, 85).getRGB();
      c = new Color(135, 238, 144).getRGB();
      this.w = var3;
      this.U = 92;
      this.e = 5;
      this.P = 5;
      this.q = 13;
      this.E = null;
      this.u = 0;
      this.x = false;
      this.V = false;
      int var10 = this.q + 3;
      this.l = new ScaledResolution(MinecraftRef.c((byte)0,0L)).getScaleFactor();
      this.H = new Animator(Easing.EASE_OUT_QUART, i);
      ArrayList var11 = new ArrayList();
      if (var3 == Category.Macro) {
         ArrayList<Module> var12 = new ArrayList<>();
         List var13 = ModuleManager.S;
         int var14 = 0;

         for (int var15 = var13.size(); var14 < var15; var14++) {
            Module var16 = (Module)var13.get(var14);
            if (var16.f().equals(var3)) {
               var12.add(var16);
            }
         }

         var12.sort(Comparator.comparing(var0 -> var0.b().toLowerCase().charAt(5)));
         var11.addAll(var12);
      } else {
         ArrayList var18 = new ArrayList();
         List var20 = ModuleManager.S;
         int var22 = 0;

         for (int var24 = var20.size(); var22 < var24; var22++) {
            Module var25 = (Module)var20.get(var22);
            if (var25.f().equals(this.w)) {
               var18.add(var25);
            }
         }

         var11.addAll(var18);
      }

      for (Module var21 : (Iterable<Module>)(var11)) {
         RavenModuleRow var23 = new RavenModuleRow(29128, var21, this, 19320, (short)30520, var10);
         this.R.add(var23);
         var10 += 16;
      }
   }

   public boolean w(int var1, int var2) {
      return var1 >= this.e && var1 <= this.e + this.U && var2 >= this.P && var2 <= this.P + this.q;
   }

   private void O(int var1, int var2, Category var3, byte var4, int var5, int var6, boolean var7) {
      long var8 = ((long)var1 << 32 | (long)var2 << 40 >>> 32 | (long)var4 << 56 >>> 56) ^ b;
      int var10 = (int)((var8 ^ 9643769532875L) >>> 56);
      RenderItem var13 = MinecraftRef.c((byte)var10,0L).getRenderItem();
      double var14 = 0.55;
      GlStateManager.pushMatrix();
      GlStateManager.scale(var14, var14, var14);
      ItemStack var16 = null;
      if (var3.equals(Category.Combat)) {
         var16 = new ItemStack(Items.diamond_sword);
      } else if (var3.equals(Category.Movement)) {
         var16 = new ItemStack(Items.feather);
      } else if (var3.equals(Category.Player)) {
         var16 = new ItemStack(Items.skull, 1, 3);
      } else if (var3.equals(Category.World)) {
         var16 = new ItemStack(Item.getItemFromBlock(Blocks.grass));
      } else if (var3.equals(Category.Visual)) {
         var16 = new ItemStack(Items.ender_pearl);
      } else if (var3.equals(Category.Misc)) {
         var16 = new ItemStack(Items.gunpowder);
      } else if (var3.equals(Category.Configuration)) {
         var16 = new ItemStack(Items.iron_ingot);
      } else if (var3.equals(Category.Macro)) {
         var16 = new ItemStack(Item.getItemFromBlock(Blocks.dispenser));
      } else if (var3.equals(Category.Visual_utility)) {
         var16 = new ItemStack(Items.ender_eye);
      }

      if (var16 != null) {
         if (var7 && var3 != Category.Player) {
            var16.addEnchantment(Enchantment.unbreaking, 2);
         }

         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.disableBlend();
         var13.renderItemAndEffectIntoGUI(var16, (int)(var5 / var14), (int)(var6 / var14));
         GlStateManager.enableBlend();
         RenderHelper.disableStandardItemLighting();
      }

      GlStateManager.disableBlend();
      GlStateManager.scale(1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public boolean j(int var1, int var2) {
      return var1 >= this.e - 2 && var1 <= this.e + this.U + 2 && var2 >= this.P + 2.0F && var2 <= this.P + this.q + 1;
   }

   public boolean g(long var1, int var3, int var4) {
      return var3 >= this.e + 92 - 13
         && var3 <= this.e + this.U
         && var4 >= this.P + 2.0F
         && var4 <= this.P + this.q + 1;
   }

   public int T() {
      return this.P;
   }

   public void U(boolean var1, char var2, int var3, short var4) {
      long var5 = ((long)var2 << 48 | (long)var3 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ b;
      long var7 = var5 ^ 56329268528884L;
      this.x = var1;
      (this.E = new RavenAnimation(600.0F)).y();
      this.H.O(var7);
      this.H.C(var1 ? 1.0 : 0.0);
   }

   public void l(boolean var1) {
      this.x = var1;
   }

   public void k(int var1) {
      this.P = var1;
   }


   public boolean g() {
      return this.s;
   }

   public List<RavenModuleRow> s() {
      return this.R;
   }

   public boolean D() {
      return this.x;
   }

}
