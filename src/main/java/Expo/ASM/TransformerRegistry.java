package Expo.ASM;

import Expo.ASM.Block.TransformBlock;
import Expo.ASM.Block.TransformBlockBarrier;
import Expo.ASM.Block.TransformBlockBush;
import Expo.ASM.Block.TransformBlockGrass;
import Expo.ASM.Block.TransformBlockLeaves;
import Expo.ASM.Block.TransformBlockModelRenderer;
import Expo.ASM.Block.TransformBlockModelShapes;
import Expo.ASM.Block.TransformBlockRendererDispatcher;
import Expo.ASM.Block.TransformBlockStateMapper;
import Expo.ASM.Entity.TransformEntity;
import Expo.ASM.Entity.TransformEntityItem;
import Expo.ASM.Entity.TransformEntityLivingBase;
import Expo.ASM.Entity.TransformEntityPlayer;
import Expo.ASM.Entity.TransformEntityPlayerSP;
import Expo.ASM.Entity.TransformEntityRenderer;
import Expo.ASM.Entity.TransformRenderEntityItem;
import Expo.ASM.Entity.TransformRendererLivingEntity;
import Expo.ASM.Gui.TransformGuiChat;
import Expo.ASM.Gui.TransformGuiContainer;
import Expo.ASM.Gui.TransformGuiDisconnected;
import Expo.ASM.Gui.TransformGuiIngame;
import Expo.ASM.Gui.TransformGuiMainMenu;
import Expo.ASM.Gui.TransformGuiPlayerTabOverlay;
import Expo.ASM.Gui.TransformGuiScreen;
import Expo.ASM.Gui.TransformGuiTextField;
import Expo.ASM.Network.TransformNetHandlerPlayClient;
import Expo.ASM.Network.TransformNetworkManager;
import Expo.ASM.Network.TransformNetworkPlayerInfo;
import Expo.ASM.Network.TransformS12PacketEntityVelocity;
import Expo.ASM.Player.TransformItemInWorldManager;
import Expo.ASM.Player.TransformKeyBinding;
import Expo.ASM.Player.TransformMovementInputFromOptions;
import Expo.ASM.Player.TransformPlayerControllerMP;
import Expo.ASM.Render.TransformEffectRenderer;
import Expo.ASM.Render.TransformFontRenderer;
import Expo.ASM.Render.TransformItemRenderer;
import Expo.ASM.Render.TransformLoadingScreenRenderer;
import Expo.ASM.Render.TransformModelBiped;
import Expo.ASM.Render.TransformModelPlayer;
import Expo.ASM.Render.TransformWorldRenderer;
import Expo.ASM.World.TransformWorld;
import Expo.ASM.World.TransformWorldClient;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TransformerRegistry {
   private static long[] e;
   private static String[] c;
   private static String[] i;
   private static Object[] h;
   private static Map d;
   private static long a;
   private static String[] b;
   private static Map g;
   public static boolean $skidonion$1232505612;

   public static final String[] Z_TARGETS = {
      "net/minecraft/client/gui/GuiSelectWorld",
      "net/minecraft/client/gui/GuiMultiplayer",
   };

   private static volatile Map<String, ClassTransform> stage1$registry;
   private static volatile List<ClassTransform> stage1$all;

   public static ClassTransform k(String var0) {
      if (var0 == null) {
         return null;
      }

      return H().get(var0.replace('.', '/'));
   }

   public static List<ClassTransform> q() {
      H();
      return stage1$all;
   }

   public static Map<String, ClassTransform> H() {
      Map<String, ClassTransform> var0 = stage1$registry;
      if (var0 != null) {
         return var0;
      }

      synchronized (TransformerRegistry.class) {
         var0 = stage1$registry;
         if (var0 != null) {
            return var0;
         }

         Map<String, ClassTransform> var1 = new LinkedHashMap<String, ClassTransform>();
         List<ClassTransform> var2 = new ArrayList<ClassTransform>();
         stage1$build(var1, var2);
         stage1$all = Collections.unmodifiableList(var2);
         var0 = Collections.unmodifiableMap(var1);
         stage1$registry = var0;
         return var0;
      }
   }

   private TransformerRegistry() {
   }

   public static List<ClassTransform> R() {
      return q();
   }

   private static void r(Map<String, ClassTransform> var0, ClassTransform var1, String var2) {
      var0.put(var2.replace('.', '/'), var1);
   }

   private static void stage1$build(Map<String, ClassTransform> var0, List<ClassTransform> var1) {
      r(var0, new TransformBlock(),                    "net/minecraft/block/Block");
      r(var0, new TransformBlockBarrier(),             "net/minecraft/block/BlockBarrier");
      r(var0, new TransformBlockBush(),                "net/minecraft/block/BlockBush");
      r(var0, new TransformBlockGrass(),               "net/minecraft/block/BlockGrass");
      r(var0, new TransformBlockLeaves(),              "net/minecraft/block/BlockLeaves");
      r(var0, new TransformBlockModelRenderer(),       "net/minecraft/client/renderer/BlockModelRenderer");
      r(var0, new TransformBlockModelShapes(),         "net/minecraft/client/renderer/BlockModelShapes");
      r(var0, new TransformBlockRendererDispatcher(),  "net/minecraft/client/renderer/BlockRendererDispatcher");
      r(var0, new TransformBlockStateMapper(),         "net/minecraft/client/renderer/block/statemap/BlockStateMapper");
      r(var0, new TransformEffectRenderer(),           "net/minecraft/client/particle/EffectRenderer");
      r(var0, new TransformEntity(),                   "net/minecraft/entity/Entity");
      r(var0, new TransformEntityItem(),               "net/minecraft/entity/item/EntityItem");
      r(var0, new TransformEntityLivingBase(),         "net/minecraft/entity/EntityLivingBase");
      r(var0, new TransformEntityPlayer(),             "net/minecraft/entity/player/EntityPlayer");
      r(var0, new TransformEntityPlayerSP(),           "net/minecraft/client/entity/EntityPlayerSP");
      r(var0, new TransformEntityRenderer(),           "net/minecraft/client/renderer/EntityRenderer");
      r(var0, new TransformFontRenderer(),             "net/minecraft/client/gui/FontRenderer");
      r(var0, new TransformGuiChat(),                  "net/minecraft/client/gui/GuiChat");
      r(var0, new TransformGuiContainer(),             "net/minecraft/client/gui/inventory/GuiContainer");
      r(var0, new TransformGuiDisconnected(),          "net/minecraft/client/gui/GuiDisconnected");
      r(var0, new TransformGuiIngame(),                "net/minecraft/client/gui/GuiIngame");
      r(var0, new TransformGuiMainMenu(),              "net/minecraft/client/gui/GuiMainMenu");
      r(var0, new TransformGuiPlayerTabOverlay(),      "net/minecraft/client/gui/GuiPlayerTabOverlay");
      r(var0, new TransformGuiScreen(),                "net/minecraft/client/gui/GuiScreen");
      r(var0, new TransformGuiTextField(),             "net/minecraft/client/gui/GuiTextField");
      r(var0, new TransformItemInWorldManager(),       "net/minecraft/server/management/ItemInWorldManager");
      r(var0, new TransformItemRenderer(),             "net/minecraft/client/renderer/ItemRenderer");
      r(var0, new TransformKeyBinding(),               "net/minecraft/client/settings/KeyBinding");
      r(var0, new TransformLayerCape(),                "net/minecraft/client/renderer/entity/layers/LayerCape");
      r(var0, new TransformLoadingScreenRenderer(),    "net/minecraft/client/LoadingScreenRenderer");
      r(var0, new TransformMinecraft(),                "net/minecraft/client/Minecraft");
      r(var0, new TransformModelBiped(),               "net/minecraft/client/model/ModelBiped");
      r(var0, new TransformModelPlayer(),              "net/minecraft/client/model/ModelPlayer");
      r(var0, new TransformMovementInputFromOptions(), "net/minecraft/util/MovementInputFromOptions");
      r(var0, new TransformNetHandlerPlayClient(),     "net/minecraft/client/network/NetHandlerPlayClient");
      r(var0, new TransformNetworkManager(),           "net/minecraft/network/NetworkManager");
      r(var0, new TransformNetworkPlayerInfo(),        "net/minecraft/client/network/NetworkPlayerInfo");
      r(var0, new TransformPlayerControllerMP(),       "net/minecraft/client/multiplayer/PlayerControllerMP");
      r(var0, new TransformRenderEntityItem(),         "net/minecraft/client/renderer/entity/RenderEntityItem");
      r(var0, new TransformRendererLivingEntity(),     "net/minecraft/client/renderer/entity/RendererLivingEntity");
      r(var0, new TransformS12PacketEntityVelocity(),  "net/minecraft/network/play/server/S12PacketEntityVelocity");
      r(var0, new TransformVisGraph(),                 "net/minecraft/client/renderer/chunk/VisGraph");
      r(var0, new TransformWorld(),                    "net/minecraft/world/World");
      r(var0, new TransformWorldClient(),              "net/minecraft/client/multiplayer/WorldClient");
      r(var0, new TransformWorldRenderer(),            "net/minecraft/client/renderer/WorldRenderer");

      for (int var2 = 0; var2 < Z_TARGETS.length; var2++) {
         r(var0, new GenericTransformer(Z_TARGETS[var2]), Z_TARGETS[var2]);
      }

      var1.addAll(var0.values());
   }
}
