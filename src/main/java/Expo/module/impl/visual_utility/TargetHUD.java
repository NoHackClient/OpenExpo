package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.TargetHUDBinder;
import Expo.event.events.AttackTargetEntityEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.module.impl.combat.KillAura;
import Expo.module.impl.configuration.Font;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.CombatUtil;
import Expo.util.EntityUtil;
import Expo.util.MathUtil;
import Expo.util.RaytraceUtil;
import Expo.util.TimerUtil;
import Expo.util.render.ColorUtil;
import Expo.util.render.CustomFont;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;











public class TargetHUD extends Module implements EventSubscriber {
   public static BooleanSetting bots;
   public static BooleanSetting mobs;
   public static HeaderSetting targetSettings;
   public static BooleanSetting chatPreview;
   public static NumberSetting range;
   private static DecimalFormat S;
   private static String[] k;
   private static DecimalFormat L;
   private EntityLivingBase K;
   public static ModeSetting style;
   public static PercentageSetting backgroundOpacity;
   public static BooleanSetting onlyWhenUsingKillaura;
   public static BooleanSetting enemies;
   private float d;
   public static BooleanSetting players;
   private EntityLivingBase E;
   public static NumberSetting scale;
   private ResourceLocation n;
   public static BooleanSetting animals;
   public static ColorSetting customColor;
   public static NumberSetting x2;
   public static ModeSetting color;
   public static BooleanSetting teammates;
   private float U;
   public static BooleanSetting healthAnimations;
   private static long c;
   public static BooleanSetting friends;
   public static BooleanSetting outline;
   public static NumberSetting y;
   public static BooleanSetting indicators;
   public static BooleanSetting textShadow;
   private float G;
   public static BooleanSetting customHealthColor;
   private final TimerUtil x;
   public static ModeSetting targetMode;
   private final TimerUtil v;
   public static NumberSetting stayTime;
   public static BooleanSetting bosses;





   public void onAttackTargetEntity(long var1, AttackTargetEntityEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (targetMode.R("HIT") && var3.w instanceof EntityLivingBase && this.A(1893608662326L, (EntityLivingBase)var3.w)) {
         this.E = this.K;
         this.K = (EntityLivingBase)var3.w;
         this.v.W();
      }
   }

   public final void x(long var1, EventBus var3) {
      TargetHUDBinder.Y(var3, this);
   }

   private static void a() {
   }

   private void C(float var1, float var2, float var3, float var4, long var5, int var7) {
      long var8 = var5 ^ 67329287032949L;
      if (var7 != 0) {
         Expo.util.render.RenderUtil.l(var7, var8);
         GL11.glBegin(9);
         GL11.glVertex2f(var1, var2);
         GL11.glVertex2f(var1, var4);
         GL11.glVertex2f(var3, var4);
         GL11.glVertex2f(var3, var2);
         GL11.glEnd();
         GlStateManager.resetColor();
      }
   }

   private void q(float var1, float var2, float var3, float var4, int var5, float var6, int var7, long var8) {
      long var10 = ((long)var5 << 32 | var8 << 32 >>> 32) ^ c;
      long var12 = var10 ^ 19002995991916L;
      if (var7 != 0) {
         Expo.util.render.RenderUtil.l(var7, var12);
         GL11.glLineWidth(var6);
         GL11.glEnable(2848);
         GL11.glHint(3154, 4354);
         GL11.glBegin(1);
         GL11.glVertex2f(var1, var2);
         GL11.glVertex2f(var1, var4);
         GL11.glVertex2f(var3, var4);
         GL11.glVertex2f(var3, var2);
         GL11.glVertex2f(var1, var2);
         GL11.glVertex2f(var3, var2);
         GL11.glVertex2f(var1, var4);
         GL11.glVertex2f(var3, var4);
         GL11.glEnd();
         GL11.glDisable(2848);
         GL11.glLineWidth(2.0F);
         GlStateManager.resetColor();
      }
   }

   private ResourceLocation j(EntityLivingBase var1) {
      if (var1 instanceof EntityPlayer) {
         NetworkPlayerInfo var2 = f.getNetHandler().getPlayerInfo(var1.getName());
         if (var2 != null) {
            return var2.getLocationSkin();
         }
      }

      return null;
   }

   private void W(CustomFont var1, char var2, String var3, float var4, float var5, float var6, int var7, short var8, float var9, float var10, int var11) {
      long var12 = ((long)var2 << 48 | (long)var7 << 32 >>> 16 | (long)var8 << 48 >>> 48) ^ c;
      long var14 = var12 ^ 56484231081322L;
      long var16 = var12 ^ 75068345638606L;
      float var18 = Math.min(var9, var6 / Math.max(var1.R(var3, var16), 1.0F));
      if (var18 < var10) {
         var18 = var10;
         String var19 = "...";

         while (var3.length() > 1 && var1.R(var3 + var19, var16) * var18 > var6) {
            var3 = var3.substring(0, var3.length() - 1);
         }

         var3 = var3 + var19;
      }

      this.O(var1, var3, var4, var5, var18, var11, var14);
   }

   private Color k(Color var1, int var2) {
      return new Color(var1.getRed(), var1.getGreen(), var1.getBlue(), var2);
   }

   private boolean A(long var1, EntityLivingBase var3) {


      return EntityUtil.q(var3, players.c(), mobs.c(), animals.c(), bosses.c(), friends.c(), enemies.c(), teammates.c(), bots.c(), 21816078198602L);
   }

   public TargetHUD(long var1) {
      super(((c ^ (var1)) ^ 135513913802190L));
      // add code
      this.declare("TargetHUD", Category.Visual_utility, "Show basic information about the current attacking target");
      var1 = c ^ var1;
      this.v = new TimerUtil();
      this.x = new TimerUtil();
      this.K = null;
      this.E = null;
      this.n = null;
      this.U = 0.0F;
      this.d = 0.0F;
      this.G = 0.0F;
   }

   private String T(Entity var1) {
      return var1.getDisplayName()
         .getFormattedText()
         .replaceAll("\u00a7\\S$", "")
         .replaceAll("(?i)\u00a7r", "\u00a7f")
         .trim();
   }

   private void L(EntityPlayer var1, float var2, float var3) {
      ArrayList var4 = new ArrayList();

      for (int var5 = 3; var5 >= 0; var5--) {
         ItemStack var6 = var1.inventory.armorInventory[var5];
         if (var6 != null) {
            var4.add(var6);
         }
      }

      ItemStack var7 = var1.getHeldItem();
      if (var7 != null) {
         var4.add(var7);
      }

      GlStateManager.pushMatrix();
      GlStateManager.scale(0.55F, 0.55F, 1.0F);

      for (int var8 = 0; var8 < var4.size(); var8++) {
         Expo.util.render.RenderUtil.m((ItemStack)var4.get(var8), (int)((var2 + var8 * 10.0F) / 0.55F), (int)(var3 / 0.55F));
      }

      GlStateManager.popMatrix();
   }

   private void O(CustomFont var1, String var2, float var3, float var4, float var5, float var6, int var7, long var8) {
      var8 = c ^ var8;
      long var10 = var8 ^ 68706159257845L;
      long var12 = var8 ^ 80700600703825L;
      var6 = Math.min(var6, var5 / Math.max(var1.R(var2, var12), 1.0F));
      float var14 = var1.R(var2, var12) * var6;
      this.O(var1, var2, var3 + (var5 - var14) / 2.0F, var4, var6, var7, var10);
   }

   private Color D(float var1) {
      if (var1 >= 0.9F) {
         return Color.GREEN;
      } else if (var1 >= 0.55F) {
         return this.n((var1 - 0.55F) / 0.35F, Color.YELLOW, Color.GREEN);
      } else if (var1 >= 0.45F) {
         return Color.YELLOW;
      } else {
         return var1 >= 0.1F ? this.n((var1 - 0.1F) / 0.35F, Color.RED, Color.YELLOW) : Color.RED;
      }
   }

   private void Z(long var1, CustomFont var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var1 = c ^ var1;
      long var5 = var1 ^ 131829585953089L;
      int var7 = (int)((var1 ^ 24198059579309L) >>> 48);
      int var8 = (int)((var1 ^ 24198059579309L) << 16 >>> 32);
      int var9 = (int)((var1 ^ 24198059579309L) << 48 >>> 48);
      long var10 = var1 ^ 94977643226488L;
      long var14 = var1 ^ 130707325117777L;
      long var16 = var1 ^ 54825566322959L;
      long var18 = var1 ^ 85698055113784L;
      long var20 = var1 ^ 29831367414322L;
      TargetHUDSnapshot var22 = this.H(var4, var16);
      float var23 = x2.L() / scale.L();
      float var24 = y.L() / scale.L();
      float var25 = 124.0F;
      float var26 = 48.0F;
      GlStateManager.pushMatrix();
      GlStateManager.scale(scale.L(), scale.L(), 1.0F);
      GlStateManager.translate(var23, var24, -450.0F);
      this.Y(0.0F, 0.0F, var5, var25, var26, var4);
      this.G(7,8, 32);
      this.W(var3, (char)var7, TargetHUDSnapshot.k(var22), 44.0F, 5.0F, 75.0F, var8, (short)var9, 1.25F, 0.78F, -1);
      this.getRGB(44.0F, var14, 21.0F, 74.0F, 8.0F, TargetHUDSnapshot.j(var22), TargetHUDSnapshot.C(var22));
      this.O(var3, this.j(var22), 44.0F, 22.6F, 74.0F, 0.58F, -1, var20);
      this.O(var3, "HTK", 44.0F, 35.0F, 0.68F, -1, var10);
      this.O(
         var3,
         "\u00a7b" + TargetHUDSnapshot.H(var22),
         59.0F,
         35.0F,
         0.68F,
         new Color(0, 210, 220, 230).getRGB(),
         var10
      );
      this.O(
         var3,
         CombatUtil.s(var18, this.K) ? " \u00a7aW" : " \u00a7c\u00a7lL",
         108.0F,
         33.0F,
         1.0F,
         this.k(TargetHUDSnapshot.B(var22), 220).getRGB(),
         var10
      );
      GlStateManager.popMatrix();
   }

   private TargetHUDSnapshot H(int var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var2 = c ^ var2;
      long var4 = var2 ^ 54785271515986L;
      long var10 = var2 ^ 16564796773000L;
      float var12 = f.thePlayer.getHealth() + f.thePlayer.getAbsorptionAmount();
      float var13 = this.K.getAbsorptionAmount();
      float var14 = this.K.getHealth() + var13;
      this.G = Math.max(this.K.getMaxHealth() + var13, 1.0F);
      if (this.K != this.E) {
         this.n = null;
         this.x.W();
         this.U = var14;
         this.d = var14;
      }

      if (!healthAnimations.c() || this.x.Q(150L)) {
         this.U = this.d;
         this.d = var14;
         if (this.U != this.d) {
            this.x.W();
         }
      }

      ResourceLocation var15 = this.j(this.K);
      if (var15 != null) {
         this.n = var15;
      }

      float var16 = (float)Math.min(Math.max(this.x.s(), 0L), 150L);
      float var17 = Math.min(Math.max(MathUtil.k(this.d, this.U, var16 / 150.0F) / this.G, 0.0F), 1.0F);
      double var18 = this.K.getHealth() / this.K.getMaxHealth();
      Color var20 = var18 < 0.3 ? Color.RED : (var18 < 0.5 ? Color.ORANGE : (var18 < 0.7 ? Color.YELLOW : Color.GREEN));
      Color var21 = customHealthColor.c() ? new Color(var1) : var20;
      float var22 = Math.min(Math.max((var12 - var14 + 1.0F) / 2.0F, 0.0F), 1.0F);
      return new TargetHUDSnapshot(
         Expo.enums.MinecraftColor.C(String.format("&r%s&r", this.T(this.K))),
         var14,
         var17,
         var21,
         CombatUtil.s(var10, this.K) ? Color.GREEN : Color.RED,
         CombatUtil.G(var4, this.K),
         Expo.enums.MinecraftColor.C(
            String.format(
               "&r&f%s%s\u2764&r",
               L.format(var14),
               var13 > 0.0F ? "&6" : "&c"
            )),
         null
      );
   }

   private void M(int var1, int var2, int var3) {
      GlStateManager.enableDepth();
      GuiInventory.drawEntityOnScreen(var1, var2, var3, 18.0F, 4.0F, this.K);
      GlStateManager.disableDepth();
   }

   private Color n(float var1, Color var2, Color var3) {
      var1 = Math.min(Math.max(var1, 0.0F), 1.0F);
      return new Color(
         (int)(var2.getRed() + var1 * (var3.getRed() - var2.getRed())),
         (int)(var2.getGreen() + var1 * (var3.getGreen() - var2.getGreen())),
         (int)(var2.getBlue() + var1 * (var3.getBlue() - var2.getBlue()))
      );
   }

   public void onRender2D(long var1, Render2DEvent var3, short var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = (var1 << 16 | (long)var4 << 48 >>> 48) ^ c;
      long var7 = var5 ^ 33042073593378L;
      int var11 = (int)((var5 ^ 120994541113228L) >>> 32);
      long var12 = (var5 ^ 120994541113228L) << 32 >>> 32;
      long var16 = var5 ^ 6879085728477L;
      long var18 = var5 ^ 54167840056618L;
      long var20 = var5 ^ 66524874562166L;
      long var22 = var5 ^ 56964375878596L;
      long var24 = var5 ^ 14305828767811L;
      long var28 = var5 ^ 121938417813945L;
      long var34 = var5 ^ 107250564600487L;
      long var36 = var5 ^ 129333833978695L;
      long var38 = var5 ^ 27554384232032L;
      CustomFont var40 = Font.F(0L);
      if (this.K != null) {
         int var41;
         switch (color.Y()) {
            case "THEME":
               var41 = Theme.S(0.0, var28);
               break;
            case "THEME_CUSTOM":
               var41 = Theme.X(var36, 0.0);
               break;
            default:
               var41 = customColor.k(var38);
         }

         switch (style.Y()) {
            case "MODEL":
               this.E(var16, var40, var41);
               break;
            case "HEAD":
               this.Z(var22, var40, var41);
               break;
            case "VANILLA":
               float var44 = f.thePlayer.getHealth() + f.thePlayer.getAbsorptionAmount();
               float var45 = this.K.getAbsorptionAmount();
               float var46 = this.K.getHealth() + var45;
               this.G = Math.max(this.K.getMaxHealth() + var45, 1.0F);
               if (this.K != this.E) {
                  this.n = null;
                  this.x.W();
                  this.U = var46;
                  this.d = var46;
               }

               if (!healthAnimations.c() || this.x.Q(150L)) {
                  this.U = this.d;
                  this.d = var46;
                  if (this.U != this.d) {
                     this.x.W();
                  }
               }

               ResourceLocation var47 = this.j(this.K);
               if (var47 != null) {
                  this.n = var47;
               }

               float var48 = (float)Math.min(Math.max(this.x.s(), 0L), 150L);
               float var49 = Math.min(Math.max(MathUtil.k(this.d, this.U, var48 / 150.0F) / this.G, 0.0F), 1.0F);
               double var50 = this.K.getHealth() / this.K.getMaxHealth();
               Color var52 = var50 < 0.3 ? Color.RED : (var50 < 0.5 ? Color.ORANGE : (var50 < 0.7 ? Color.YELLOW : Color.GREEN));
               Color var53 = customHealthColor.c() ? new Color(var41) : var52;
               float var54 = Math.min(Math.max((var44 - var46 + 1.0F) / 2.0F, 0.0F), 1.0F);
               Color var55 = this.D(var54);
               String var56 = Expo.enums.MinecraftColor.C(String.format("&r%s&r", this.T(this.K)));
               int var57 = (int)var40.R(var56, var34);
               String var58 = Expo.enums.MinecraftColor.C(
                  String.format(
                     "&r&f%s%s\u2764&r",
                     L.format(var46),
                     var45 > 0.0F ? "&6" : "&c"
                  ));
               int var59 = (int)var40.R(var58, var34);
               String var60 = CombatUtil.s(var24, this.K) ? " \u00a7aW" : " \u00a7c\u00a7lL";
               int var61 = (int)var40.R(var60, var34);
               String var62 = Expo.enums.MinecraftColor.C(
                  String.format("&r%s&r", var46 == var44 ? "0.0" : S.format(var44 - var46)));
               int var63 = (int)var40.R(var62, var34);
               float var64 = Math.max(var57 + (indicators.c() ? 2.0F + var61 + 2.0F : 0.0F), var59 + (indicators.c() ? 2.0F + var63 + 2.0F : 0.0F));
               float var65 = this.n != null ? 25.0F : 0.0F;
               float var66 = Math.max(var65 + 70.0F, var65 + 2.0F + var64 + 2.0F);
               float var67 = x2.L() / scale.L();
               float var68 = y.L() / scale.L();
               GlStateManager.pushMatrix();
               GlStateManager.scale(scale.L(), scale.L(), 0.0F);
               GlStateManager.translate(var67, var68, -450.0F);
               Expo.util.render.RenderUtil.L();
               int var69 = new Color(0.0F, 0.0F, 0.0F, 0.01F * backgroundOpacity.k()).getRGB();
               int var70 = outline.c() ? var41 : new Color(0, 0, 0, 0).getRGB();
               this.q(0.0F, 0.0F, var66, 27.0F, var11, 1.5F, var70, var12);
               this.C(0.0F, 0.0F, var66, 27.0F, var18, var69);
               Expo.util.render.RenderUtil.c(var20, var65 + 2.0F, 22.0, var66 - 2.0F, 25.0, ColorUtil.Z(0L, var53, 0.2F).getRGB());
               Expo.util.render.RenderUtil.c(var20, var65 + 2.0F, 22.0, var65 + 2.0F + var49 * (var66 - 2.0F - var65 - 2.0F), 25.0, var53.getRGB());
               Expo.util.render.RenderUtil.w();
               GlStateManager.disableDepth();
               GlStateManager.enableBlend();
               GlStateManager.blendFunc(770, 771);
               var40.v(var56, var65 + 2.0F, 2.0F, -1, var7, textShadow.c());
               var40.v(var58, var65 + 2.0F, 12.0F, -1, var7, textShadow.c());
               if (indicators.c()) {
                  var40.v(var60, var66 - 2.0F - var61, 2.0F, var55.getRGB(), var7, textShadow.c());
                  var40.v(var62, var66 - 2.0F - var63, 12.0F, ColorUtil.Z(0L, var55, 0.8F).getRGB(), var7, textShadow.c());
               }

               if (this.n != null) {
                  GlStateManager.color(1.0F, 1.0F, 1.0F);
                  f.getTextureManager().bindTexture(this.n);
                  Gui.drawScaledCustomSizeModalRect(
                     2,
                     2,
                     8.0F,
                     8.0F,
                     8,
                     8,
                     23,
                     23,
                     64.0F,
                     64.0F
                  );
                  Gui.drawScaledCustomSizeModalRect(
                     2,
                     2,
                     40.0F,
                     8.0F,
                     8,
                     8,
                     23,
                     23,
                     64.0F,
                     64.0F
                  );
                  GlStateManager.color(1.0F, 1.0F, 1.0F);
               }

               GlStateManager.disableBlend();
               GlStateManager.enableDepth();
               GlStateManager.popMatrix();
         }
      }
   }

   private void getRGB(float var1, long var2, float var4, float var5, float var6, Color var7, float var8) {
      long var11 = var2 ^ 11623722859356L;
      Expo.util.render.RenderUtil.c(var11, var1, var4, var1 + var5, var4 + var6, ColorUtil.Z(0L, var7, 0.2F).getRGB());
      Expo.util.render.RenderUtil.c(var11, var1, var4, var1 + var5 * var8, var4 + var6, var7.getRGB());
   }

   private void G(int var1,int var4, int var5) {
      if (this.n != null) {
         GlStateManager.color(1.0F, 1.0F, 1.0F);
         f.getTextureManager().bindTexture(this.n);
         Gui.drawScaledCustomSizeModalRect(var1, var4, 8.0F, 8.0F, 8, 8, var5, var5, 64.0F, 64.0F);
         Gui.drawScaledCustomSizeModalRect(var1, var4, 40.0F, 8.0F, 8, 8, var5, var5, 64.0F, 64.0F);
         GlStateManager.color(1.0F, 1.0F, 1.0F);
      }
   }

   private void Y(float var1, float var2, long var3, float var5, float var6, int var7) {
      var3 = c ^ var3;
      int var10 = 255 * backgroundOpacity.k() / 100;
      Color var11 = new Color(var7);
      int var12 = new Color(
            8,
            10,
            12,
            Math.max(var10, 70)
         )
         .getRGB();
      int var13 = outline.c()
         ? new Color(var11.getRed(), var11.getGreen(), var11.getBlue(), 145).getRGB()
         : new Color(
               255,
               255,
               255,
               45
            )
            .getRGB();
      Expo.util.render.RenderUtil.J( var1, var2, var1 + var5, var2 + var6, 6.0F, var12, var13, var13);
   }

   private void E(long var1, CustomFont var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      var1 = c ^ var1;
      long var5 = var1 ^ 73028733093976L;
      int var7 = (int)((var1 ^ 39087032196788L) >>> 48);
      int var8 = (int)((var1 ^ 39087032196788L) << 16 >>> 32);
      int var9 = (int)((var1 ^ 39087032196788L) << 48 >>> 48);
      long var10 = var1 ^ 74139989404744L;
      long var12 = var1 ^ 4757589739542L;
      long var14 = var1 ^ 51329357273899L;
      TargetHUDSnapshot var16 = this.H(var4, var12);
      float var17 = x2.L() / scale.L();
      float var18 = y.L() / scale.L();
      float var19 = 122.0F;
      float var20 = 46.0F;
      GlStateManager.pushMatrix();
      GlStateManager.scale(scale.L(), scale.L(), 1.0F);
      GlStateManager.translate(var17, var18, -450.0F);
      this.Y(0.0F, 0.0F, var5, var19, var20, var4);
      this.W(var3, (char)var7, TargetHUDSnapshot.k(var16), 43.0F, 6.0F, 74.0F, var8, (short)var9, 1.25F, 0.78F, -1);
      this.getRGB(43.0F, var10, 33.0F, 74.0F, 8.0F, TargetHUDSnapshot.j(var16), TargetHUDSnapshot.C(var16));
      this.O(var3, this.j(var16), 43.0F, 34.6F, 74.0F, 0.58F, -1, var14);
      if (this.K instanceof EntityPlayer) {
         this.L((EntityPlayer)this.K, 44.0F, 19.0F);
      }

      this.M(22, 39, 18);
      GlStateManager.popMatrix();
   }

   private String j(TargetHUDSnapshot var1) {
      return L.format(TargetHUDSnapshot.m(var1)) + "/" + L.format(this.G);
   }

   public String g(long var1) {
      return targetMode.Y();
   }



   public void onPreUpdate(PreUpdateEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (KillAura.H6 != null) {
         if (this.A(1893608662326L, KillAura.H6)) {
            this.E = this.K;
            this.K = KillAura.H6;
            this.v.W();
         }
      } else {
         if (chatPreview.c() && f.currentScreen instanceof GuiChat) {
            this.E = this.K;
            this.K = f.thePlayer;
            return;
         }

         if (onlyWhenUsingKillaura.c() && !KillAura.a && this.v.A(stayTime.L() * 1000.0F)) {
            this.E = this.K;
            this.K = null;
            return;
         }

         if (targetMode.R("AIM")) {
            EntityLivingBase var6 = RaytraceUtil.Z((int)range.L());
            if (var6 != null && this.A(1893608662326L, var6)) {
               this.E = this.K;
               this.K = var6;
               this.v.W();
            } else if (this.v.A(stayTime.L() * 1000.0F)) {
               this.E = this.K;
               this.K = null;
            }
         } else if (targetMode.R("HIT") && this.v.A(stayTime.L() * 1000.0F)) {
            this.E = this.K;
            this.K = null;
         }
      }
   }



   static {
      c = 126705739374527L;
      L = new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
      S = new DecimalFormat("+0.0;-0.0", new DecimalFormatSymbols(Locale.US));
   }

   private void O(CustomFont var1, String var2, float var3, float var4, float var5, int var6, long var7) {
      long var9 = var7 ^ 9480978106145L;
      GlStateManager.pushMatrix();
      GlStateManager.scale(var5, var5, 1.0F);
      var1.v(var2, var3 / var5, var4 / var5, var6, var9, textShadow.c());
      GlStateManager.popMatrix();
   }

   public void A(long var1) {
      this.E = null;
      this.K = null;
   }


   static {
      // add code
      style = new ModeSetting("Style", "HEAD", "VANILLA", "MODEL");
      backgroundOpacity = new PercentageSetting("Background-opacity", 50);
      enemies = new BooleanSetting("Enemies", true);
      players = new BooleanSetting("Players", true);
      animals = new BooleanSetting("Animals", false);
      targetMode = new ModeSetting("Target-mode", false, "AIM", "HIT", "AIM");
      teammates = new BooleanSetting("Teammates", false);
      textShadow = new BooleanSetting("Text-shadow", true);
      bosses = new BooleanSetting("Bosses", false);
      onlyWhenUsingKillaura = new BooleanSetting("Only-when-using-killaura", false);
      stayTime = new NumberSetting("Stay-time", 2.0F, 0.1F, 20.0F, 0.1F);
      customHealthColor = new BooleanSetting("Custom-health-color", false);
      x2 = new NumberSetting("X", 350.0F, 0.0F, 800.0F, 1.0F);
      customColor = new ColorSetting("Custom-color", "FFFFFF");
      range = new NumberSetting("Range", 10.0F, 0.0F, 30.0F, 1.0F);
      scale = new NumberSetting("Scale", 1.0F, 0.5F, 3.0F, 0.01F);
      indicators = new BooleanSetting("Indicators", true);
      friends = new BooleanSetting("Friends", false);
      outline = new BooleanSetting("Outline", true);
      color = new ModeSetting("Color", "THEME", "THEME_CUSTOM", "CUSTOM");
      y = new NumberSetting("Y", 150.0F, 0.0F, 500.0F, 1.0F);
      healthAnimations = new BooleanSetting("Health-Animations", false);
      bots = new BooleanSetting("Bots", false);
      chatPreview = new BooleanSetting("Chat-preview", true);
      mobs = new BooleanSetting("Mobs", false);
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
