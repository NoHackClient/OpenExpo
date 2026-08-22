package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ItemTagsBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.module.Module;
import Expo.module.Modules;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.visual.ItemScale;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.ItemUtil;
import Expo.util.LunarClientDetector;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;


public class ItemTags extends Module implements EventSubscriber {
   public static BooleanSetting renderBlocks;
   public static BooleanSetting renderSwordsAndBows;
   public static BooleanSetting nbtOnly;
   public static BooleanSetting megawallsItems;
   public static NumberSetting scale;
   private final List<ItemTagsEntry> S;
   public static BooleanSetting renderALL;
   private static long a;
   public static BooleanSetting renderGoldenApples;
   public static BooleanSetting bedwarsResources;
   public static PercentageSetting backgroundOpacity;

   private String l(String var1, long var2, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      return var1 + " \u00a7fx" + var4;
   }

   private void w(EntityItem var1, String var2, int var3) {
      this.S.add(new ItemTagsEntry(var1, var2, var3, null));
   }

   private void G(EntityItem var1, long var2, Item var4, String var5) {
      if (var4 instanceof ItemSword || var4 instanceof ItemBow) {
         this.w(var1, var5, 16733525);
      }
   }


   private void y(long var1, EntityItem var3, ItemStack var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      Item var22 = var4.getItem();
      String var23 = var4.getDisplayName();
      String var24 = StringUtils.stripControlCodes(var23);
      int var25 = var4.stackSize;
      boolean var26 = var4.hasTagCompound();
      if (!nbtOnly.c() || var26) {
         int var27 = this.L(var4,0L);
         String var28 = this.l(var23,0L, var25);
         if (renderALL.c()) {
            this.P(0L, var3, var28, var26, var27);
         } else {
            if (megawallsItems.c()) {
               this.A( var3, var22, var24, var28, var25, var27);
            }

            if (bedwarsResources.c()) {
               this.W(0L, var3, var22, var28);
            }

            if (renderSwordsAndBows.c()) {
               this.G(var3,0L, var22, var28);
            }

            if (renderBlocks.c()) {
               this.z(var3, var4,0L, var28);
            }

            if (renderGoldenApples.c()) {
               this.g(var3, var22, var28,0L);
            }
         }
      }
   }

   private float W(float var1, float var2, float var3) {
      return (var2 - var1) * var3 + var1;
   }


   private void o(long var1) {
      GL11.glEnable(2929);
      GL11.glDisable(3042);
      GlStateManager.resetColor();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }


   public void A(long var1) {
      this.S.clear();
   }

   private void a(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      this.S.clear();
      List var5 = f.theWorld.loadedEntityList;

      for (int var6 = 0; var6 < var5.size(); var6++) {
         Entity var7 = (Entity)var5.get(var6);
         if (var7 instanceof EntityItem) {
            EntityItem var8 = (EntityItem)var7;
            ItemStack var9 = var8.getEntityItem();
            if (this.I(var9)) {
               this.y(17753419752380L, var8, var9);
            }
         }
      }
   }


   private float F(CustomFont var1, String var2, long var3) {


      return -var1.R(var2, 52019766876817L) / 2.0F - 4.6F;
   }

   private void m(CustomFont var3, String var4, float var5, int var6) {


      float var11 = var3.R(var4, 52019766876817L);
      int var12 = 255 * backgroundOpacity.k() / 100;
      int var13 = new Color(0, 0, 0, var12).getRGB();
      Expo.util.render.RenderUtil.c(125644905353792L, (int)var5 + 2, -14 - var6, var11 / 2.0F, -4 - var6, var13);
   }

   private float m(Minecraft var1, int var2, char var3, char var4) {
      if (LunarClientDetector.q(0L)) {
         return var1.getRenderManager().playerViewX;
      } else {
         return var1.gameSettings.thirdPersonView == 2 ? -var1.getRenderManager().playerViewX : var1.getRenderManager().playerViewX;
      }
   }

   private void W(long var1, EntityItem var3, Item var4, String var5) {
      if (var4 == Items.diamond) {
         this.w(var3, var5, 5636095);
      }

      if (var4 == Items.iron_ingot) {
         this.w(var3, var5, 11184810);
      }

      if (var4 == Items.gold_ingot) {
         this.w(var3, var5, 16777045);
      }

      if (var4 == Items.emerald) {
         this.w(var3, var5, 5635925);
      }
   }

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      this.a(32528519039452L);
   }


   private ItemTagsRenderPos getRenderManager(Entity var1, float var2) {
      float var3 = this.W((float)var1.lastTickPosX, (float)var1.posX, var2) - (float)f.getRenderManager().viewerPosX;
      float var4 = this.W((float)var1.lastTickPosY, (float)var1.posY, var2) - (float)f.getRenderManager().viewerPosY;
      float var5 = this.W((float)var1.lastTickPosZ, (float)var1.posZ, var2) - (float)f.getRenderManager().viewerPosZ;
      return new ItemTagsRenderPos(var3, var4, var5, null);
   }


   private void P(long var1, EntityItem var3, String var4, boolean var5, int var6) {
      this.w(var3, var4, var5 ? var6 : 16777215);
   }

   static void $jnicClinit() throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {

      a = 67299170408461L;
   }


   private int A(long var1, EntityItem var3) {
      if (!this.M()) {
         return 0;
      } else {
         return !ItemScale.c(var3.getEntityItem()) ? 0 : (int)((ItemScale.scale.L() - 1.0F) * 14.0F);
      }
   }


   public void onRender3D(long var1, Render3DEvent var3) {

      CustomFont var8 = Font.s(0L);

      for (int var9 = 0; var9 < this.S.size(); var9++) {
         ItemTagsEntry var10 = this.S.get(var9);
         this.S(var8, var10, var3.j, scale.L(), 63742102300376L);
      }
   }

   public ItemTags(long var1) {
      super(((a ^ (var1)) ^ 98074705143150L));
      // add code
      this.declare("ItemTags", Category.Visual_utility, "Render text bar on dropped items");
      var1 = a ^ var1;
      this.S = new ArrayList<>();
   }

   private void b(CustomFont var1, String var2, int var3, float var4, long var5, int var7) {


      var1.v(var2, var4 + 4.0F, -12.5F - var7, var3, 88827598794260L, false);
   }


   public String g(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (nbtOnly.c()) {
         return "NBT";
      } else if (renderALL.c()) {
         return "ALL";
      } else if (megawallsItems.c()) {
         return "MEGAWALLS";
      } else if (bedwarsResources.c()) {
         return "BEDWARS";
      } else if (renderGoldenApples.c()) {
         return "GAPPLES";
      } else {
         return renderSwordsAndBows.c() ? "WEAPONS" : "NONE";
      }
   }

   private int L(ItemStack var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!var1.hasTagCompound()) {
         return -1;
      }

      NBTTagCompound var4 = var1.getTagCompound();
      if (!var4.hasKey("display")) {
         return -1;
      }

      NBTTagCompound var5 = var4.getCompoundTag("display");
      return !var5.hasKey("color") ? -1 : var5.getInteger("color");
   }

   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 94918877103430L) >>> 48);
      int var5 = (int)((var1 ^ 94918877103430L) << 16 >>> 48);
      ItemTagsBinder.b(var3, (char)var4, (short)var5, this);
   }


   private float getDistanceToEntity(Entity var1, float var2) {
      float var3 = var2 / 3.0F;
      float var4 = f.thePlayer.getDistanceToEntity(var1) / 10.0F;
      if (var4 < 1.1F) {
         var4 = 1.1F;
      }

      float var5 = var4 * 1.8F;
      var5 /= 100.0F;
      return var5 + var3 / 50.0F;
   }

   private void S(CustomFont var1, ItemTagsEntry var2, float var3, float var4, long var5) {




      float var19 = this.getDistanceToEntity(ItemTagsEntry.H(var2), var4);
      ItemTagsRenderPos var20 = this.getRenderManager(ItemTagsEntry.H(var2), var3);
      int var21 = this.A(0L, ItemTagsEntry.H(var2));
      float var22 = this.F(var1, ItemTagsEntry.T(var2), 51511525423037L);
      this.y(var20, var19, 37666237105538L);
      this.m(var1, ItemTagsEntry.T(var2), var22, var21);
      this.b(var1, ItemTagsEntry.T(var2), ItemTagsEntry.z(var2), var22, 20402189618877L, var21);
      this.o(0L);
   }


   private boolean M() {
      return Modules.J(ItemScale.class).o();
   }


   private boolean c(Item var1) {
      return var1 == Items.diamond_boots || var1 == Items.diamond_leggings || var1 == Items.diamond_helmet || var1 == Items.diamond_chestplate;
   }


   private void z(EntityItem var1, ItemStack var2, long var3, String var5) {
      if (ItemUtil.u(var2)) {
         this.w(var1, var5, 16777215);
      }
   }

   private boolean I(ItemStack var1) {
      return var1 != null && var1.stackSize > 0;
   }

   private void y(ItemTagsRenderPos var1, float var2, long var3) {





      GL11.glPushMatrix();
      GL11.glTranslatef(ItemTagsRenderPos.B(var1), ItemTagsRenderPos.Z(var1) + 0.5F, ItemTagsRenderPos.M(var1));
      GL11.glRotatef(-f.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(this.m(f, 25825, (char)55138, (char)8140), 1.0F, 0.0F, 0.0F);
      GL11.glScalef(-var2, -var2, var2);
      GL11.glDisable(2929);
      GL11.glEnable(3042);
   }

   private void g(EntityItem var1, Item var2, String var3, long var4) {
      if (var2 == Items.golden_apple) {
         this.w(var1, var3, 16755200);
      }
   }

   private void A( EntityItem var2, Item var3, String var4, String var5, int var6, int var7) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (var4.startsWith("Phoenix's Tears of Regen")) {
         this.w(var2, "\u00a76Phoenix's Tears of Regen \u00a7fx" + var6, var7);
      }

      if (var4.startsWith("Squid's Absorption")) {
         this.w(var2, "\u00a79Squid's Absorption \u00a7fx" + var6, var7);
      }

      if (var4.startsWith("Matey")) {
         this.w(var2, var5, var7);
      }

      if (var4.startsWith("Regen-Ade")) {
         this.w(var2, "\u00a7bRegen-ades \u00a7fx" + var6, var7);
      }

      if (var4.startsWith("Ultra Pasteurized Milk Bucket")) {
         this.w(var2, "\u00a7fMilk Bucket \u00a7fx" + var6, var7);
      }

      if (var4.startsWith("Junk Apple")) {
         this.w(var2, var5, var7);
      }

      if (var3 == Items.pumpkin_pie) {
         this.w(var2, var5, 16711610);
      }

      if (var3 == Items.golden_apple) {
         this.w(var2, var5, 16755200);
      }

      if (var3 == Items.diamond) {
         this.w(var2, var5, 5636095);
      }

      if (var3 == Items.diamond_sword) {
         this.w(var2, var5, 5636095);
      }

      if (this.c(var3)) {
         this.w(var2, var5, 5636095);
      }
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
      renderSwordsAndBows = new BooleanSetting("Render-swords-and-bows", false);
      backgroundOpacity = new PercentageSetting("Background-opacity", 20);
      nbtOnly = new BooleanSetting("NBT-only", false);
      scale = new NumberSetting("Scale", 1.0F, 0.01F, 5.0F, 0.01F);
      renderALL = new BooleanSetting("Render-ALL", false);
      megawallsItems = new BooleanSetting("Megawalls-items", true);
      renderGoldenApples = new BooleanSetting("Render-golden-apples", false);
      bedwarsResources = new BooleanSetting("Bedwars-resources", false);
      renderBlocks = new BooleanSetting("Render-blocks", false);
   }
}
