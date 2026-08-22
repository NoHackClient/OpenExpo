package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ItemESPBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.RenderManagerAccessor;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.LunarClientDetector;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;


public class ItemESP extends Module implements EventSubscriber {
   public static NumberSetting scale;
   public static BooleanSetting emeralds;
   public static BooleanSetting golds;
   private static long b;
   public static PercentageSetting opacity;
   public static BooleanSetting irons;
   private final List<ItemESPEntry> t;
   public static BooleanSetting outline;
   public static BooleanSetting diamonds;


   private boolean Q(int var1) {
      Item var2 = Item.getItemById(var1);
      Block var3 = Block.getBlockFromItem(var2);
      return var2 == Items.iron_ingot || var3 == Blocks.iron_block || var3 == Blocks.iron_ore;
   }


   private void W(AxisAlignedBB var1, Color var2, char var3) {
      Expo.util.render.RenderUtil.L();
      if (opacity.k() > 0) {
         Expo.util.render.RenderUtil.l(var1, var2.getRed(), var2.getGreen(), var2.getBlue(), (int)(2.55 * opacity.k()));
      }

      if (outline.c()) {
         Expo.util.render.RenderUtil.X(var1, var2.getRed(), var2.getGreen(), var2.getBlue(), 255, 1.5F);
      }

      GlStateManager.resetColor();
      Expo.util.render.RenderUtil.w();
   }

   private double h(double var1) {
      return (Math.max(6.0, var1) - 6.0) / 28.0;
   }

   private double q(double var1) {
      return (0.5 + 0.375 * var1) * scale.L();
   }


   private float q() {
      return f.gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F;
   }

   private Color f(int var3) {
      if (this.i(var3)) {
         return new Color(-11141291);
      } else if (this.Y(var3)) {
         return new Color(-11141121);
      } else if (this.J(var3)) {
         return new Color(-171);
      } else {
         return this.Q(var3) ? new Color(-1) : new Color(-5592406);
      }
   }

   public void onPostTick(PostTickEvent var1) {
      this.t.clear();
      List var2 = f.theWorld.loadedEntityList;

      for (int var3 = 0; var3 < var2.size(); var3++) {
         Entity var4 = (Entity)var2.get(var3);
         if (this.D(var4)) {
            EntityItem var5 = (EntityItem)var4;
            ItemStack var6 = var5.getEntityItem();
            if (this.b(var6)) {
               int var7 = Item.getIdFromItem(var6.getItem());
               if (this.q(var7)) {
                  this.t.add(new ItemESPEntry(var5, var7, null));
               }
            }
         }
      }
   }

   private void s(CustomFont var1, String var2, Color var3, double var4, double var6, double var8, double var10, double var12, long var14, float var16, float var17) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var14 = b ^ var14;
      long var20 = var14 ^ 84925837445787L;
      long var22 = var14 ^ 118731946975631L;
      long var24 = var14 ^ 93779980940454L;
      GlStateManager.pushMatrix();
      GlStateManager.translate(var4, var6 + var10 * 0.5, var8);
      GlStateManager.rotate(-var16, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(var17, LunarClientDetector.q(0L) ? 1.0F : this.q(), 0.0F, 0.0F);
      GlStateManager.scale(var12, var12, 1.0);
      GlStateManager.disableDepth();
      float var26 = -(var1.R(var2, var24) / 2.0F) + 0.5F;
      float var27 = -(var1.o(var20) / 2.0F) + 0.5F;
      Expo.util.render.RenderUtil.K(var1, var2, var22, var26, var27, var3.getRGB());
      GlStateManager.enableDepth();
      GlStateManager.resetColor();
      GlStateManager.popMatrix();
   }


   private void q(Map<ItemESPStackKey, Integer> var1, ItemESPStackKey var2, int var3) {
      var1.merge(var2, var3, Integer::sum);
   }

   private boolean D(Entity var1) {
      return var1 instanceof EntityItem && var1.ticksExisted >= 3;
   }

   private boolean Y(int var1) {
      Item var2 = Item.getItemById(var1);
      Block var3 = Block.getBlockFromItem(var2);
      return var2 == Items.diamond
         || var2 == Items.diamond_sword
         || var2 == Items.diamond_pickaxe
         || var2 == Items.diamond_shovel
         || var2 == Items.diamond_axe
         || var2 == Items.diamond_hoe
         || var2 == Items.diamond_helmet
         || var2 == Items.diamond_chestplate
         || var2 == Items.diamond_leggings
         || var2 == Items.diamond_boots
         || var3 == Blocks.diamond_block
         || var3 == Blocks.diamond_ore;
   }

   public ItemESP(short var1, char var2, int var3) {
      super(((((((long)((var1)) << 48) | (((long)((var2)) << 48) >>> 16)) | (((long)((var3)) << 32) >>> 32)) ^ b) ^ 112538991030944L));
      // add code
      this.declare("ItemESP", Category.Visual_utility, "Render a box on items");
      this.t = new ArrayList<>();
   }


   static void $jnicClinit() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {

      b = 28379158381100L;
   }

   private boolean q(int var1) {
      return emeralds.c() && this.i(var1) || diamonds.c() && this.Y(var1) || golds.c() && this.J(var1) || irons.c() && this.Q(var1);
   }

   private void x(CustomFont var1, Entry var2, int var3, double var4, char var6, double var7, double var9, float var11, float var12, int var13) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var14 = ((long)var3 << 32 | (long)var6 << 48 >>> 32 | (long)var13 << 48 >>> 48) ^ b;
      long var16 = var14 ^ 55632197036009L;
      int var20 = (int)((var14 ^ 65880163195850L) >>> 48);
      ItemESPStackKey var23 = (ItemESPStackKey)var2.getKey();
      int var24 = (Integer)var2.getValue();
      Color var25 = this.f(var23.Z);
      double var26 = var23.p - var4;
      double var28 = var23.d - var7;
      double var30 = var23.l - var9;
      double var32 = f.getRenderViewEntity().getDistance(var23.p, var23.d, var23.l);
      double var34 = this.h(var32);
      double var36 = this.q(var34);
      double var38 = this.Y(var34);
      AxisAlignedBB var40 = this.M(var26, var28, var30, var36);
      this.W(var40, var25, (char)var20);
      this.s(var1, Integer.toString(var24), var25, var26, var28, var30, var36, var38, var16, var11, var12);
   }

   public final void x(long var1, EventBus var3) {
      ItemESPBinder.v(var3, this);
   }

   public void onRender3D(Render3DEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {




      CustomFont var16 = Font.s(0L);
      LinkedHashMap var17 = this.q(var1.j);
      ArrayList var18 = new ArrayList(var17.entrySet());
      this.G(var18);

      for (int var19 = 0; var19 < var18.size(); var19++) {
         this.x(
            var16,
            (Entry)var18.get(var19),
            18589,
            RenderManagerAccessor.k(0L, f.getRenderManager()),
            (char)61597,
            RenderManagerAccessor.y(13236, f.getRenderManager()),
            RenderManagerAccessor.W(0L, f.getRenderManager()),
            f.getRenderManager().playerViewY,
            f.getRenderManager().playerViewX,
            6069
         );
      }
   }


   private LinkedHashMap<ItemESPStackKey, Integer> q(float var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (int var3 = 0; var3 < this.t.size(); var3++) {
         ItemESPEntry var4 = this.t.get(var3);
         EntityItem var5 = ItemESPEntry.w(var4);
         if (this.isGetEntityBoundingBox(var5)) {
            ItemStack var6 = var5.getEntityItem();
            if (this.b(var6)) {
               double var7 = this.m(var5.lastTickPosX, var5.posX, var1);
               double var9 = this.m(var5.lastTickPosY, var5.posY, var1);
               double var11 = this.m(var5.lastTickPosZ, var5.posZ, var1);
               ItemESPStackKey var13 = new ItemESPStackKey(ItemESPEntry.r(var4), var7, var9, var11);
               this.q(var2, var13, var6.stackSize);
            }
         }
      }

      return var2;
   }

   private double Y(double var1) {
      return (-0.04375 - 0.0328125 * var1) * scale.L();
   }


   private void G(List<Entry<ItemESPStackKey, Integer>> var1) {
      var1.sort((var1x, var2) -> Integer.compare(this.D(((ItemESPStackKey)var1x.getKey()).Z), this.D(((ItemESPStackKey)var2.getKey()).Z)));
   }

   private boolean isGetEntityBoundingBox(EntityItem var1) {
      return var1 != null && !var1.isDead && Expo.util.render.RenderUtil.p(var1.getEntityBoundingBox(), 0.125);
   }

   private boolean i(int var1) {
      Item var2 = Item.getItemById(var1);
      Block var3 = Block.getBlockFromItem(var2);
      return var2 == Items.emerald || var3 == Blocks.emerald_block || var3 == Blocks.emerald_ore;
   }



   public void A(long var1) {
      this.t.clear();
   }

   private AxisAlignedBB M(double var1, double var3, double var5, double var7) {
      double var9 = var7 * 0.5;
      return new AxisAlignedBB(var1 - var9, var3, var5 - var9, var1 + var9, var3 + var7, var5 + var9);
   }


   private boolean b(ItemStack var1) {
      return var1 != null && var1.stackSize > 0;
   }


   private int D(int var1) {
      if (this.i(var1)) {
         return 4;
      } else if (this.Y(var1)) {
         return 3;
      } else if (this.J(var1)) {
         return 2;
      } else {
         return this.Q(var1) ? 1 : 0;
      }
   }

   private double m(double var1, double var3, float var5) {
      return var5 * (var3 - var1) + var1;
   }

   private boolean J(int var1) {
      Item var2 = Item.getItemById(var1);
      Block var3 = Block.getBlockFromItem(var2);
      return var2 == Items.gold_ingot
         || var2 == Items.gold_nugget
         || var2 == Items.golden_apple
         || var3 == Blocks.gold_block
         || var3 == Blocks.gold_ore;
   }

   static {
      try {
         $jnicClinit();
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var0) {
         throw new RuntimeException(var0);
      }
   }
   static {
      // add code
      scale = new NumberSetting("Scale", 0.8F, 0.01F, 5.0F, 0.01F);
      golds = new BooleanSetting("Golds", true);
      irons = new BooleanSetting("Irons", true);
      emeralds = new BooleanSetting("Emeralds", true);
      diamonds = new BooleanSetting("Diamonds", true);
      opacity = new PercentageSetting("Opacity", 60);
      outline = new BooleanSetting("Outline", true);
   }
}
