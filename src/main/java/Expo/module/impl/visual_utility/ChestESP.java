package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ChestESPBinder;
import Expo.event.events.EntityJoinWorldEvent;
import Expo.event.events.PlayerRightClickEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.RenderManagerAccessor;
import Expo.internal.synthetic.ChestESPSwitchMapEnumFacing;
import Expo.module.Module;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.BlockUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;











public class ChestESP extends Module implements EventSubscriber {
   public static ModeSetting color;
   public static BooleanSetting showTargetShade;
   private static long a;
   public static BooleanSetting ignoreOpened;
   public static PercentageSetting opacity;
   public static BooleanSetting showTargetOutline;
   private final List<AxisAlignedBB> I;
   private final Set<BlockPos> v;
   public static ColorSetting customColor;

   private void B(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      this.I.clear();
      List var6 = f.theWorld.loadedTileEntityList;
      boolean var7 = ignoreOpened.c();
      int var8 = 0;

      for (int var9 = var6.size(); var8 < var9; var8++) {
         TileEntity var10 = (TileEntity)var6.get(var8);
         if (var10 instanceof TileEntityChest) {
            BlockPos var11 = var10.getPos();
            if (!this.v.contains(var11) && (((TileEntityChest)var10).numPlayersUsing > 0 || BlockUtil.Y((byte)0, var11, 8170486))) {
               this.v.add(var11);
            }

            if (!this.v.contains(var11) || !var7) {
               Block var12 = f.theWorld.getBlockState(var11).getBlock();
               double var13 = 0.0625;
               double var15 = 0.0625;
               double var17 = 0.9375;
               double var19 = 0.9375;
               if (var12 instanceof BlockChest) {
                  EnumFacing var21 = (EnumFacing)f.theWorld.getBlockState(var11).getValue(BlockChest.FACING);
                  switch (ChestESPSwitchMapEnumFacing.V[var21.ordinal()]) {
                     case 1:
                        if (f.theWorld.getBlockState(var11.east()).getBlock() == var12) {
                           continue;
                        }

                        if (f.theWorld.getBlockState(var11.west()).getBlock() == var12) {
                           var13--;
                        }
                        break;
                     case 2:
                        if (f.theWorld.getBlockState(var11.west()).getBlock() == var12) {
                           continue;
                        }

                        if (f.theWorld.getBlockState(var11.east()).getBlock() == var12) {
                           var17++;
                        }
                        break;
                     case 3:
                        if (f.theWorld.getBlockState(var11.north()).getBlock() == var12) {
                           continue;
                        }

                        if (f.theWorld.getBlockState(var11.south()).getBlock() == var12) {
                           var19++;
                        }
                        break;
                     case 4:
                        if (f.theWorld.getBlockState(var11.south()).getBlock() != var12) {
                           if (f.theWorld.getBlockState(var11.north()).getBlock() == var12) {
                              var15--;
                           }
                           break;
                        }
                     default:
                        continue;
                  }
               }

               this.I
                  .add(
                     new AxisAlignedBB(
                        var11.getX() + var13,
                        var11.getY(),
                        var11.getZ() + var15,
                        var11.getX() + var17,
                        var11.getY() + 0.875,
                        var11.getZ() + var19
                     )
                  );
            }
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      ChestESPBinder.N(var3, this);
   }

   public void onRender3D(long var1, Render3DEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {





      int var19;
      switch (color.Y()) {
         case "THEME":
            var19 = Theme.S(0.0, 35338930340239L);
            break;
         case "THEME_CUSTOM":
            var19 = Theme.X(65301174328177L, 0.0);
            break;
         default:
            var19 = customColor.k(96531491288662L);
      }

      double var30 = RenderManagerAccessor.k(0L, f.getRenderManager());
      double var22 = RenderManagerAccessor.y(13236, f.getRenderManager());
      double var24 = RenderManagerAccessor.W(0L, f.getRenderManager());
      int var26 = (int)(2.55 * opacity.k());

      for (int var27 = 0; var27 < this.I.size(); var27++) {
         AxisAlignedBB var28 = this.I.get(var27).offset(-var30, -var22, -var24);
         Expo.util.render.RenderUtil.W(var28, 48544574689857L, var19, var26, showTargetOutline.c(), showTargetShade.c());
      }
   }

   static {
      a = 17077916200986L;
   }

   public void onEntityJoinWorld(EntityJoinWorldEvent var1) {
      if (var1.H instanceof EntityPlayerSP) {
         this.v.clear();
         this.I.clear();
      }
   }


   public void onPostTick(PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      this.B(129551973060899L);
   }

   public ChestESP(long var1) {
      super(((a ^ (var1)) ^ 98396349626058L));
      // add code
      this.declare("ChestESP", Category.Visual_utility, "ESP for chests");
      var1 = a ^ var1;
      this.v = new HashSet<>();
      this.I = new ArrayList<>();
   }


   public void A(long var1) {
      this.v.clear();
      this.I.clear();
   }

   public String g(long var1) {
      return opacity.k() + "%";
   }

   public void onPlayerRightClick(PlayerRightClickEvent var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      BlockPos var6 = var1.a$r2();
      if (f.theWorld.getBlockState(var6).getBlock() == Blocks.chest) {
         this.v.add(var6);
         this.B(129551973060899L);
      }
   }


   static {
      // add code
      opacity = new PercentageSetting("Opacity", 25);
      ignoreOpened = new BooleanSetting("Ignore-opened", false);
      showTargetOutline = new BooleanSetting("Show-target-outline", true);
      showTargetShade = new BooleanSetting("Show-target-shade", false);
      color = new ModeSetting("Color", "THEME", "THEME_CUSTOM", "CUSTOM");
      customColor = new ColorSetting("Custom-color", "FFFFFF");
   }
}
