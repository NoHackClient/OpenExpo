package Expo.ASM.Hooks;

import Expo.ASM.Hooks.Entity.EntityHookDispatch;
import Expo.ASM.Hooks.Entity.EntityRendererHooks;
import Expo.ASM.Hooks.Gui.GuiMainMenuHooks;
import Expo.ASM.Hooks.Render.ItemRendererHooks;
import Expo.internal.accessor.MethodAccessors;
import Expo.ui.screen.MainMenuTheme;
import Expo.util.MinecraftRef;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;


public class MiscHooks {
   private static boolean U;
   private static boolean O;
   private static boolean W;
   private static boolean Z;
   private static long a;

   public static Vec3 entityRendererGetLook(Entity var0, float var1) {
      return EntityRendererHooks.redirectGetLook(var1);
   }

   public static void minecraftClickMouseHead(CallbackInfo var0) {
      if (Z) {
         var0.cancel();
      } else {
         HookDispatch.Minecraft$onClickMouse(var0);
      }
   }

   private static Object readField(Object var0, Class<?> var1, String... var2) throws Exception, IllegalAccessException {
      Field var3 = findField(var1, var2);
      return var3.get(var0);
   }

   public static void minecraftPreMouseInput() {
      boolean[] var0 = HookDispatch.Minecraft$onPreMouseInput();
      Z = var0.length > 0 && var0[0];
      U = var0.length > 1 && var0[1];
      W = var0.length > 2 && var0[2];
      O = var0.length > 3 && var0[3];
   }

   private static List<GuiButton> buttonList(GuiScreen var0) throws Exception, IllegalAccessException {
      return (List<GuiButton>)readField(var0, GuiScreen.class, "buttonList", "buttonList", "n");
   }

   public static boolean entityPlayerSPIsUsingItem(EntityPlayerSP var0) {
      if (var0.isUsingItem() && !var0.isRiding()) {
         EntityHookDispatch.EntityPlayerSP$redirectIsUsingItem(var0);
         return false;
      } else {
         return var0.isUsingItem();
      }
   }

   public static void guiMainMenuInit(GuiMainMenu var0, CallbackInfo var1) {





      try {
         List var12 = buttonList(var0);
         GuiMainMenuHooks.onInitGUI(var12, var0.width, var0.height);
         if (!MainMenuTheme.o(0L)) {
            return;
         }

         Minecraft var13 = MinecraftRef.c((byte)0,0L);
         DynamicTexture var14 = new DynamicTexture(256, 256);
         ResourceLocation var15 = var13.getTextureManager().getDynamicTextureLocation("background", var14);
         writeField(var0, GuiMainMenu.class, var14, "viewportTexture", "viewportTexture", "u");
         writeField(var0, GuiMainMenu.class, var15, "backgroundTexture", "backgroundTexture", "J");
         Calendar var16 = Calendar.getInstance();
         var16.setTime(new Date());
         if (var16.get(2) + 1 == 12 && var16.get(5) == 24) {
            writeField(var0, GuiMainMenu.class, "Merry X-mas!", "splashText", "splashText", "r");
         } else if (var16.get(2) + 1 == 1 && var16.get(5) == 1) {
            writeField(var0, GuiMainMenu.class, "Happy new year!", "splashText", "splashText", "r");
         } else if (var16.get(2) + 1 == 10 && var16.get(5) == 31) {
            writeField(var0, GuiMainMenu.class, "OOoooOOOoooo! Spooky!", "splashText", "splashText", "r");
         }

         MainMenuTheme.r(var0.width, var0.height, 3666, (byte)89, var12, 4438180);
         synchronized (readField(var0, GuiMainMenu.class, "threadLock", "threadLock", "w")) {
            String var19 = (String)readField(var0, GuiMainMenu.class, "openGLWarning1", "openGLWarning1", "x");
            String var20 = (String)readField(var0, GuiMainMenu.class, "openGLWarning2", "openGLWarning2", "y");
            int var21 = var13.fontRendererObj.getStringWidth(var19);
            int var22 = var13.fontRendererObj.getStringWidth(var20);
            int var23 = Math.max(var21, var22);
            writeField(var0, GuiMainMenu.class, var21, "field_92023_s", "E");
            writeField(var0, GuiMainMenu.class, var22, "field_92024_r", "D");
            writeField(var0, GuiMainMenu.class, (var0.width - var23) / 2, "field_92022_t", "F");
            writeField(var0, GuiMainMenu.class, ((GuiButton)var12.get(0)).yPosition - 24, "field_92021_u", "G");
            writeField(var0, GuiMainMenu.class, (var0.width - var23) / 2 + var23, "field_92020_v", "H");
            writeField(var0, GuiMainMenu.class, ((GuiButton)var12.get(0)).yPosition, "field_92019_w", "I");
         }

         var13.setConnectedToRealms(false);
         boolean var18 = (Boolean)readField(var0, GuiMainMenu.class, "field_183502_L", "L");
         if (var13.gameSettings.getOptionOrdinalValue(Options.REALMS_NOTIFICATIONS) && !var18) {
            RealmsBridge var27 = new RealmsBridge();
            GuiScreenRealmsProxy var29 = var27.getNotificationScreen(var0);
            writeField(var0, GuiMainMenu.class, var29, "field_183503_M", "M");
            writeField(var0, GuiMainMenu.class, true, "field_183502_L", "L");
         }

         if (guiMainMenuHasRealms(var0)) {
            GuiScreen var28 = (GuiScreen)readField(var0, GuiMainMenu.class, "field_183503_M", "M");
            var28.setGuiSize(var0.width, var0.height);
            var28.initGui();
         }

         var1.cancel();
      } catch (Throwable var26) {
      }
   }

   public static void guiMainMenuDraw(GuiMainMenu var0, int var1, int var2, float var3, CallbackInfo var4) {




      try {
         if (!MainMenuTheme.X(0L)) {
            return;
         }

         Minecraft var17 = MinecraftRef.c((byte)0,0L);
         List var18 = buttonList(var0);
         MainMenuTheme.S(var0.width, var0.height);
         MainMenuTheme.F(var0.width, 16138, 3806336253L, var0.height, var18);

         for (GuiButton var20 : (Iterable<GuiButton>)(var18)) {
            var20.drawButton(var17, var1, var2);
         }

         if (guiMainMenuHasRealms(var0)) {
            GuiScreen var22 = (GuiScreen)readField(var0, GuiMainMenu.class, "field_183503_M", "M");
            var22.drawScreen(var1, var2, var3);
         }

         var4.cancel();
      } catch (Throwable var21) {
      }
   }

   public static void guiMainMenuAction(GuiButton var0, CallbackInfo var1) {
      GuiMainMenuHooks.onActionPerformed(var0, var1);
   }

   private MiscHooks() {
   }

   private static void writeField(Object var0, Class<?> var1, Object var2, String... var3) throws Exception, IllegalAccessException {
      Field var4 = findField(var1, var3);
      var4.set(var0, var2);
   }


   public static void minecraftSendClickBlockHead(CallbackInfo var0) {
      if (O) {
         var0.cancel();
      }
   }


   public static void itemRendererUpdateEquippedItem(ItemRenderer var0, CallbackInfo var1) {

      try {
         if (!ItemRendererHooks.onUpdateEquippedItemLastPartAllowed()) {
            return;
         }

         Minecraft var4 = (Minecraft)readField(var0, ItemRenderer.class, "mc", "mc", "c");
         float var5 = (Float)readField(var0, ItemRenderer.class, "equippedProgress", "equippedProgress", "e");
         ItemStack var6 = (ItemStack)readField(var0, ItemRenderer.class, "itemToRender", "itemToRender", "d");
         int var7 = (Integer)readField(var0, ItemRenderer.class, "equippedItemSlot", "equippedItemSlot", "i");
         writeField(var0, ItemRenderer.class, var5, "prevEquippedProgress", "prevEquippedProgress", "f");
         EntityPlayerSP var8 = var4.thePlayer;
         ItemStack var9 = ItemRendererHooks.spoofScaffoldItemStack(var4, var8.inventory.getCurrentItem());
         int var10 = ItemRendererHooks.spoofScaffoldItemSlot(var4, var8.inventory.currentItem);
         boolean var11 = false;
         if (var6 != null && var9 != null) {
            if (!var6.getIsItemStackEqual(var9)) {
               if (!MethodAccessors.n(var6.getItem(), var6, var9, var7 != var10)) {
                  writeField(var0, ItemRenderer.class, var9, "itemToRender", "itemToRender", "d");
                  writeField(var0, ItemRenderer.class, var10, "equippedItemSlot", "equippedItemSlot", "i");
                  var1.cancel();
                  return;
               }

               var11 = true;
            }
         } else {
            var11 = var6 != null || var9 != null;
         }

         float var12 = 0.4F;
         float var13 = var11 ? 0.0F : 1.0F;
         float var14 = MathHelper.clamp_float(var13 - var5, -var12, var12);
         var5 += var14;
         writeField(var0, ItemRenderer.class, var5, "equippedProgress", "equippedProgress", "e");
         if (ItemRendererHooks.onUpdateEquippedItemLastPart(var5)) {
            writeField(var0, ItemRenderer.class, var9, "itemToRender", "itemToRender", "d");
            writeField(var0, ItemRenderer.class, var10, "equippedItemSlot", "equippedItemSlot", "i");
         }

         var1.cancel();
      } catch (Throwable var15) {
      }
   }

   public static void entityRendererOrientCamera(EntityRenderer var0, Minecraft var1, float var2, float var3, float var4, CallbackInfo var5) {
      Boolean var8 = EntityRendererHooks.orientCamera(var1, var2, var3, var4);
      if (var8 != null) {
         try {
            writeField(var0, EntityRenderer.class, var8, "cloudFog", "cloudFog", "B");
         } catch (Throwable var10) {
         }

         var5.cancel();
      }
   }

   public static void minecraftRightClickMouseHead(CallbackInfo var0) {
      if (U) {
         var0.cancel();
      } else {
         HookDispatch.Minecraft$onRightClickMouse(var0);
      }
   }

   public static boolean bypassConfusion(EntityPlayerSP var0, Potion var1) {
      return EntityRendererHooks.bypassConfusionIfNeeded(var1, var0);
   }

   public static boolean minecraftShouldCancelStoppedUsingItem(PlayerControllerMP var0, EntityPlayer var1) {
      return W;
   }


   private static Method findMethod(Class<?> var0, String... var1) throws Exception {
      for (Class var2 = var0; var2 != null; var2 = var2.getSuperclass()) {
         for (String var6 : var1) {
            try {
               Method var7 = var2.getDeclaredMethod(var6);
               var7.setAccessible(true);
               return var7;
            } catch (NoSuchMethodException var8) {
            }
         }
      }

      throw new NoSuchMethodException(String.join("/", var1));
   }

   private static boolean guiMainMenuHasRealms(GuiMainMenu var0) throws Exception, IllegalAccessException, InvocationTargetException {
      Method var3 = findMethod(GuiMainMenu.class, "func_183501_a", "a");
      return (Boolean)var3.invoke(var0);
   }

   public static Vec3 entityRayTraceGetLook(Entity var0, float var1) {
      return EntityHookDispatch.Entity$onGetLook(var1);
   }

   public static void minecraftRunTickHead() {
      HookDispatch.Minecraft$onPreTick();
      minecraftPreMouseInput();
   }

   public static boolean minecraftShouldCancelStoppedUsingItem() {
      return W;
   }

   public static boolean bypassBlindness(EntityLivingBase var0, Potion var1) {
      return EntityRendererHooks.bypassBlindnessIfNeeded(var1, var0);
   }

   static {
      a = 50580487233910L;
   }

   private static Field findField(Class<?> var0, String... var1) throws Exception {
      for (Class var2 = var0; var2 != null; var2 = var2.getSuperclass()) {
         for (String var6 : var1) {
            try {
               Field var7 = var2.getDeclaredField(var6);
               var7.setAccessible(true);
               return var7;
            } catch (NoSuchFieldException var8) {
            }
         }
      }

      throw new NoSuchFieldException(String.join("/", var1));
   }

}
