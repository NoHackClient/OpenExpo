package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.enums.MinecraftColor;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.NameTagsBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.RenderManagerAccessor;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Teams;
import Expo.module.impl.player.AutoWeapon;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.CombatUtil;
import Expo.util.EnchantmentAbbreviation;
import Expo.util.EnchantmentAbbreviations;
import Expo.util.EntityUtil;
import Expo.util.LunarClientDetector;
import Expo.util.MathUtil;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


public class NameTags extends Module implements EventSubscriber {
   public static BooleanSetting onlyName;
   private static String[] E;
   private static long[] s;
   public static PercentageSetting backgroundOpacity;
   // update new version
   public static NumberSetting backgroundSpacing;
   public static Set<EntityLivingBase> x;
   private static Object[] y;
   public static BooleanSetting textShadow;
   public static BooleanSetting enemies;
   public static BooleanSetting autoScale;
   public static BooleanSetting bots;
   public static BooleanSetting friends;
   public static BooleanSetting players;
   private static long a;
   public static NumberSetting scale;
   public static BooleanSetting showHealth;
   public static BooleanSetting showEffects;
   private static String[] k;
   private static Map<Integer, EnchantmentAbbreviation> c;
   public static BooleanSetting showHitsToKill;
   // update new version
   public static ModeSetting armorMode;
   // update new version
   public static BooleanSetting enchant;
   public static BooleanSetting animals;
   public static BooleanSetting teammates;
   private static Map m;
   public static BooleanSetting showSelf;
   private final List<EntityLivingBase> Y;
   public static BooleanSetting mobs;
   public static BooleanSetting showDistance;
   public static BooleanSetting bosses;
   public static BooleanSetting showIndicator;
   public static HeaderSetting targetSettings;
   private static Map v;

   private void replaceAll(String var1, long var2, float var4, float var5) {
      long var6 = var2 ^ 30936216594894L;
      CustomFont var10 = Font.s(0L);
      String var11 = var1.replaceAll("(?i)\u00a7[\\da-f]", "");
      var10.v(var11, var4 + 1.0F, var5, 0, var6, false);
      var10.v(var11, var4 - 1.0F, var5, 0, var6, false);
      var10.v(var11, var4, var5 + 1.0F, 0, var6, false);
      var10.v(var11, var4, var5 - 1.0F, 0, var6, false);
      var10.v(var1, var4, var5, -1, var6, false);
   }

   private void c(EntityLivingBase var1, CustomFont var2, long var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (var1 instanceof EntityPlayer) {
         float var11 = var2.o(60714858652844L) + 2.0F;
         // update new version
         if (!armorMode.R("NONE")) {
            ArrayList var12 = new ArrayList();

            for (int var13 = 4; var13 >= 0; var13--) {
               ItemStack var14;
               if (var13 == 0) {
                  var14 = var1.getHeldItem();
               } else {
                  var14 = ((EntityPlayer)var1).inventory.armorInventory[var13 - 1];
               }

               if (var14 != null) {
                  var12.add(var14);
               }
            }

            if (!var12.isEmpty()) {
               // update new version
               String var24 = armorMode.Y();
               boolean var25 = var1 == f.thePlayer;
               if (var24.equals("LEFT") || var25 && var24.equals("SELF_LEFT")) {
                  this.armorColumn(var12, -26, 114286643707769L);
               } else if (var24.equals("RIGHT") || var25 && var24.equals("SELF_RIGHT")) {
                  this.armorColumn(var12, 10, 114286643707769L);
               } else {
                  int var18 = var12.size() * -8;

                  for (int var21 = 0; var21 < var12.size(); var21++) {
                     this.h((ItemStack)var12.get(var21), var18 + var21 * 16, 114286643707769L, (int)(-var11 - 16.0F));
                  }

                  var11 += 16.0F;
               }
            }
         }

         if (showEffects.c()) {
            ArrayList var17 = new ArrayList();

            for (PotionEffect var22 : var1.getActivePotionEffects()) {
               Potion var15 = Potion.potionTypes[var22.getPotionID()];
               if (var15 != null && var15.hasStatusIcon()) {
                  var17.add(var22);
               }
            }

            if (!var17.isEmpty()) {
               GlStateManager.pushMatrix();
               GlStateManager.scale(0.5F, 0.5F, 1.0F);
               int var20 = var17.size() * -9;

               for (int var23 = 0; var23 < var17.size(); var23++) {
                  this.F((PotionEffect)var17.get(var23), var20 + var23 * 18, (int)(-(var11 * 2.0F) - 18.0F));
               }

               GlStateManager.popMatrix();
            }
         }
      }
   }

   // update new version
   private void armorColumn(java.util.List var1, int var2, long var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      GlStateManager.pushMatrix();

      for (int var5 = 0; var5 < var1.size(); var5++) {
         this.h((ItemStack)var1.get(var5), var2, var3, -8 + var5 * 16);
      }

      GlStateManager.popMatrix();
   }

   private void F(PotionEffect var1, int var2, int var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var6 = Potion.potionTypes[var1.getPotionID()].getStatusIconIndex();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.pushMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.clear(256);
      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0F, 1.0F, -0.01F);
      f.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
      Gui.drawModalRectWithCustomSizedTexture(
         var2,
         var3,
         var6 % 8 * 18,
         198 + var6 / 8 * 18,
         18,
         18,
         256.0F,
         256.0F
      );
      GlStateManager.popMatrix();
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.popMatrix();
   }

   public void A(long var1) {
      this.Y.clear();
      x.clear();
   }

   private static void a() {
      y[0] = "\u0001yM(tH=";
      y[1] = long.class;
      E[1] = "java/lang/Long";
      y[2] = boolean.class;
      E[2] = "java/lang/Boolean";
      y[3] = "o%_\u001e\fdq-EQnxv0";
      y[4] = "9B\u0014yD\u00049B\u0003%H\u000b#\t\u00059]\u0004#^N\u0012G\u0019>S\u0019";
      y[5] = "v\fv\u0016\u0000zv\faJ\fulGgV\u0019zl\u0010,H\u0001ra\fp\u0016(}l\u0000vA=\u007fy\u0010gJ";
      y[6] = "a@j\u007f\u001d\u007fG";
      y[7] = ":`r\u001cZp\rwv\u0016\u0017T\u001a|,\n";
      y[8] = "da\u0018hY$f";
      y[9] = void.class;
      E[9] = "java/lang/Void";
      y[10] = "\u001c&a\u0018~F\u0017)pW\u001fH\u001c\"t\r";
      y[11] = "m,Hp'^?#N\u0015'\"$+_wsB5tT\u0015w@n>Cl']i}//v\u001ej ItrXlL\u0015/p\u001c8*N+6\u001aTv\u0015)rN2-\u0011ot\"nv\u0013+ D5rU-L\u0018np\u0011y*Cj6\u0017\u0015";
      y[12] = "\u0017|\u0018M;|IjJ\u0004\u0005J+8\u0012\u0019;eR?\u0005\u00128\u0003\u0015:\u0014IhrG9EJ\u0005sLu\u0019Keb\u0013~{J}x\u00154A\u0016:bO\u0005";
      y[13] = "c\u001a\u001bFXK1\u0015\u001d#n7*\u001d\fA\fW;B\u0007#\t\rfD\u0010ER\t B|\u0019^[aG\u001b\u0018^Y`z";
      y[14] = "r\u0012){,` \u001d/\u001e>\u001cq\r'e.n7\t1/G&qNpr!}u\bv\u001e}&wL\"x&\"1JN$} u\u001e(\u007fyfsrt${\"'\u0014/ =$KHt\"yp-\u0013pd\u007f\u001cqHr +z*L4&G&qNpr!}u\bv\u001e}&wL\"x&\"1JNn l)M.\u007f\u007fgKHt\"yp-\u0013pd\u007f\u001c";
   }

   public void onRender3D(Render3DEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      CustomFont var9 = Font.s(0L);
      float var10 = var1.j;
      x.clear();

      for (int var11 = 0; var11 < this.Y.size(); var11++) {
         EntityLivingBase var12 = this.Y.get(var11);
         if (Expo.util.render.RenderUtil.l(var12)) {
            this.A(2009537843L, var12, var10, (char)57256, var9);
         }
      }

      if (showSelf.c() && f.gameSettings.thirdPersonView != 0) {
         this.A(2009537843L, f.thePlayer, var10, (char)57256, var9);
      }
   }

   private float y() {
      return f.gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F;
   }



   private void O(EntityLivingBase var1, double var2, double var4, double var8, double var10) {
      GlStateManager.translate(var2, var4 + (var1.isSneaking() ? 0.225 : 0.4), var8);
      GlStateManager.rotate(f.getRenderManager().playerViewY * -1.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f.getRenderManager().playerViewX, LunarClientDetector.q(0L) ? 1.0F : this.y(), 0.0F, 0.0F);
      double var14 = Math.pow(Math.min(Math.max(autoScale.c() ? var10 : 0.0, 6.0), 128.0), 0.75) * 0.0075;
      GlStateManager.scale(-var14 * scale.L(), -var14 * scale.L(), 1.0);
   }

   private String u(int var1, byte var2, int var3, EntityLivingBase var4, double var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var7 = ((long)var1 << 32 | (long)var2 << 56 >>> 32 | (long)var3 << 40 >>> 40) ^ a;
      long var9 = var7 ^ 101699053036785L;
      long var11 = var7 ^ 97055390054510L;
      long var13 = var7 ^ 51555224747637L;
      long var15 = var7 ^ 87333464305021L;
      StringBuilder var17 = new StringBuilder();
      if (onlyName.c()) {
         var17.append(var4.getName());
      } else {
         var17.append(var4.getDisplayName().getFormattedText());
      }

      if (showHealth.c()) {
         float var18 = CombatUtil.h(var4);
         float var19 = var4.getMaxHealth();
         var17.append(" ").append(CombatUtil.h(var18, var19, var9)).append(MathUtil.W(var18));
         float var20 = CombatUtil.D(var4);
         if (var20 != 0.0F) {
            var17.append(" \u00a76").append(MathUtil.W(var20));
         }
      }

      if (showHitsToKill.c()) {
         var17.append(" ").append(CombatUtil.h(var4, var13, f.thePlayer.inventory.getStackInSlot(AutoWeapon.M(var11))));
      }

      if (showDistance.c() && var4 != f.thePlayer) {
         int var21 = (int)MathUtil.W(var5);
         String var22 = "";
         if (var5 <= 8.0) {
            var22 = "\u00a7c";
         } else if (var5 <= 15.0) {
            var22 = "\u00a76";
         } else if (var5 <= 25.0) {
            var22 = "\u00a7e";
         }

         var17.insert(0, "\u00a7b[\u00a7r" + var22 + var21 + "\u00a7b] \u00a7r");
      }

      if (showIndicator.c() && var4 != f.thePlayer) {
         var17.append(" ").append(CombatUtil.s(var15, var4) ? "\u00a76[\u00a7a+\u00a76]\u00a7r" : "\u00a76[\u00a7c-\u00a76]\u00a7r");
      }

      return var17.toString();
   }

   private void h(ItemStack var1, int var2, long var3, int var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var6 = 64928077817138L;
      GlStateManager.pushMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.clear(256);
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glDisable(2896);
      GlStateManager.pushMatrix();
      GlStateManager.scale(1.0F, 1.0F, -0.01F);
      f.getRenderItem().zLevel = -150.0F;
      f.getRenderItem().renderItemAndEffectIntoGUI(var1, var2, var5);
      f.getRenderItem().renderItemOverlays(f.fontRendererObj, var1, var2, var5);
      f.getRenderItem().zLevel = 0.0F;
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.popMatrix();
      GlStateManager.pushMatrix();
      GlStateManager.scale(0.5F, 0.5F, 0.5F);
      GlStateManager.disableDepth();
      // update new version
      if (enchant.c()) {
         this.W(var6, var1, var2, var5, 0.5F);
      }

      GlStateManager.enableDepth();
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      GlStateManager.popMatrix();
   }

   private void l(long var1, EntityLivingBase var3, CustomFont var4, String var5) {






      float var18 = var4.R(var5, 52019766876817L);
      Color var19 = !var3.isSneaking() && !var3.isInvisible()
         ? new Color(0.0F, 0.0F, 0.0F, backgroundOpacity.k() / 100.0F)
         : new Color(0.33F, 0.0F, 0.33F, backgroundOpacity.k() / 100.0F);
      Expo.util.render.RenderUtil.L();
      // update new version
      float var24 = backgroundSpacing.L();
      float var20 = -var18 / 2.0F - var24;
      float var21 = -var4.o(60714858652844L) - var24;
      float var22 = var18 / 2.0F + (textShadow.c() ? var24 : 0.0F);
      float var23 = textShadow.c() ? 0.0F : -var24;
      Expo.util.render.RenderUtil.c(125644905353792L, var20, var21, var22, var23, var19.getRGB());
      if (Teams.l(var3)) {
         Expo.util.render.RenderUtil.m(var20, var21, 91446790430251L, var22, var23, 1.0F, Color.GREEN.getRGB());
      } else if (Teams.Y(var3)) {
         Expo.util.render.RenderUtil.m(var20, var21, 91446790430251L, var22, var23, 1.0F, Color.RED.getRGB());
      }

      Expo.util.render.RenderUtil.w();
      GlStateManager.disableDepth();
      var4.v(var5, -var18 / 2.0F, -var4.o(60714858652844L), -1, 88827598794260L, textShadow.c());
      GlStateManager.enableDepth();
   }

   private void A(long var1, EntityLivingBase var3, float var4, char var5, CustomFont var6) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var7 = (131697072078848L | (long)var5 << 48 >>> 48) ^ a;
      long var10001 = var7 ^ 135707487007392L;
      int var11 = (int)((var7 ^ 135707487007392L) >>> 32);
      int var12 = (int)((var7 ^ 135707487007392L) << 32 >>> 56);
      int var13 = (int)(var10001 << 40 >>> 40);
      var10001 = var7 ^ 35185164424738L;
      int var18 = (int)((var7 ^ 35185164424738L) >>> 32);
      long var21 = var7 ^ 45524526076454L;
      long var23 = var7 ^ 29991463200841L;
      x.add(var3);
      RenderManager var25 = f.getRenderManager();
      double var26 = RenderManagerAccessor.k(0L, var25);
      double var28 = RenderManagerAccessor.y(var18, var25);
      double var30 = RenderManagerAccessor.W(0L, var25);
      double var32 = (var3.posX - var3.lastTickPosX) * var4 + var3.lastTickPosX - var26;
      double var34 = (var3.posY - var3.lastTickPosY) * var4 + var3.lastTickPosY - var28 + var3.getEyeHeight();
      double var36 = (var3.posZ - var3.lastTickPosZ) * var4 + var3.lastTickPosZ - var30;
      EntityPlayerSP var38 = f.thePlayer;
      double var39 = var38 == null ? 0.0 : var38.getDistanceToEntity(var3);
      GlStateManager.pushMatrix();
      this.O(var3, var32, var34, var36, var39);
      String var41 = this.u(var11, (byte)var12, var13, var3, var39);
      this.l(var21, var3, var6, var41);
      this.c(var3, var6, var23);
      GlStateManager.popMatrix();
   }


   public NameTags(long var1) {
      super(((a ^ (var1)) ^ 119992795285380L));
      // add code
      this.declare("NameTags", Category.Visual_utility, "Modify nametags rendering");
      var1 = a ^ var1;
      this.Y = new ArrayList<>();
   }

   private static MinecraftColor y(int var0, int var1) {
      if (var0 > var1) {
         return Expo.enums.MinecraftColor.LIGHT_PURPLE;
      }

      if (var0 == var1) {
         return Expo.enums.MinecraftColor.RED;
      }

      switch (var0) {
         case 1:
            return Expo.enums.MinecraftColor.AQUA;
         case 2:
            return Expo.enums.MinecraftColor.GREEN;
         case 3:
            return Expo.enums.MinecraftColor.YELLOW;
         case 4:
            return Expo.enums.MinecraftColor.GOLD;
         default:
            return Expo.enums.MinecraftColor.GRAY;
      }
   }

   public final void x(long var1, EventBus var3) {
      NameTagsBinder.A(var3, this);
   }

   public String g(long var1) {
      return String.valueOf(scale.L());
   }

   public void onPostTick(PostTickEvent var1, long var2) {


      this.Y.clear();
      boolean var10 = players.c();
      boolean var11 = mobs.c();
      boolean var12 = animals.c();
      boolean var13 = bosses.c();
      boolean var14 = friends.c();
      boolean var15 = enemies.c();
      boolean var16 = teammates.c();
      boolean var17 = bots.c();
      boolean var18 = var10 && !var11 && !var12 && !var13;
      List var19 = EntityUtil.U( var18);

      for (int var20 = 0; var20 < var19.size(); var20++) {
         EntityLivingBase var21 = (EntityLivingBase)var19.get(var20);
         if (!(var21 instanceof EntityPlayerSP)
            && var21.getDisplayName() != null
            && (
               var18
                  ? EntityUtil.c(30808997819832L, (EntityPlayer)var21, var14, var15, var16, var17)
                  : EntityUtil.q(var21, var10, var11, var12, var13, var14, var15, var16, var17, 21816078198602L)
            )) {
            this.Y.add(var21);
         }
      }
   }

   static {
      a = 110449242857023L;
      x = new HashSet<>();
      c = new EnchantmentAbbreviations();
      y = new Object[15];
      E = new String[15];
      m = new HashMap(13);
      k = new String[13];
      v = new HashMap(13);
      s = new long[]{8114464844023189536L, -8498047997027657721L, -6848780433799349009L, 1123611790016166617L, -8478601633323954937L, -1822533454991580431L, 4543667561951989338L, 366751151073540261L, -7937407761630176002L, 4904965540425861536L, -5136359554768556648L};
   }


   private void W(long var1, ItemStack var3, float var4, float var5, float var6) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var9 = var1 ^ 131829999346408L;
      NBTTagList var11 = var3.getItem() == Items.enchanted_book ? Items.enchanted_book.getEnchantments(var3) : var3.getEnchantmentTagList();
      if (var11 != null) {
         for (int var12 = 0; var12 < var11.tagCount(); var12++) {
            EnchantmentAbbreviation var13 = c.get(var11.getCompoundTagAt(var12).getInteger("id"));
            if (var13 != null) {
               int var14 = var11.getCompoundTagAt(var12).getShort("lvl");
               MinecraftColor var15 = y(var14, var13.L);
               this.replaceAll(
                  Expo.enums.MinecraftColor.C(String.format("&r%s%s%d&r", var13.S, var15, Integer.valueOf(var14))),
                  var9,
                  var4 * (1.0F / var6),
                  (var5 + var12 * 4.0F) * (1.0F / var6)
               );
            }
         }
      }
   }
   static {
      // add code
      teammates = new BooleanSetting("Teammates", true);
      bots = new BooleanSetting("Bots", false);
      backgroundOpacity = new PercentageSetting("Background-opacity", 30);
      showHealth = new BooleanSetting("Show-health", true);
      players = new BooleanSetting("Players", true);
      showDistance = new BooleanSetting("Show-distance", false);
      autoScale = new BooleanSetting("Auto-scale", true);
      showHitsToKill = new BooleanSetting("Show-hits-to-kill", false);
      textShadow = new BooleanSetting("Text-shadow", false);
      mobs = new BooleanSetting("Mobs", false);
      showSelf = new BooleanSetting("Show-self", true);
      // update new version
      enchant = new BooleanSetting("Enchant", true);
      animals = new BooleanSetting("Animals", false);
      bosses = new BooleanSetting("Bosses", false);
      friends = new BooleanSetting("Friends", true);
      showIndicator = new BooleanSetting("Show-indicator", false);
      onlyName = new BooleanSetting("Only-name", false);
      enemies = new BooleanSetting("Enemies", true);
      scale = new NumberSetting("Scale", 0.8F, 0.1F, 3.0F, 0.01F);
      // update new version
      backgroundSpacing = new NumberSetting("Background-spacing", 1.0F, 0.0F, 5.0F, 0.1F);
      // update new version
      armorMode = new ModeSetting("Armor-mode", "NONE", "TOP", "LEFT", "RIGHT", "SELF_LEFT", "SELF_RIGHT");
      showEffects = new BooleanSetting("Show-effects", false);
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
