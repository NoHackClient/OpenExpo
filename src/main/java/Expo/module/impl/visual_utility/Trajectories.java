package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.TrajectoriesBinder;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.RenderManagerAccessor;
import Expo.internal.synthetic.TrajectoriesSwitchMapAxis;
import Expo.module.Module;
import Expo.module.impl.configuration.Teams;
import Expo.module.impl.misc.AntiBot;
import Expo.setting.settings.ColorSetting;
import Expo.util.RotationManager;
import Expo.util.Vector3d;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;


public class Trajectories extends Module implements EventSubscriber {
   public static ColorSetting teammatesColor;
   public static ColorSetting baseColor;
   private static Map d;
   private static long a;
   public static ColorSetting friendColor;
   public static ColorSetting botColor;
   public static ColorSetting nonePlayersColor;
   private static Object[] e;
   private static long[] b;
   public static ColorSetting enemyColor;
   private static String[] k;

   private void p(Vector3d var1, double var2, double var4, double var6) {
      Vector3d.d(var1, var2);
      Vector3d.z(var1, var4);
      Vector3d.c(var1, var6);
   }

   private void x(int var1, TrajectorySimulationResult var2, int var3, byte var4) {
      long var5 = ((long)var1 << 32 | (long)var3 << 40 >>> 32 | (long)var4 << 56 >>> 56) ^ a;
      long var10 = var5 ^ 140115040817061L;
      if (TrajectorySimulationResult.R$r1(var2) != null) {
         GlStateManager.pushMatrix();
         GlStateManager.translate(TrajectorySimulationResult.l(var2), TrajectorySimulationResult.R(var2), TrajectorySimulationResult.B(var2));
         this.t(TrajectorySimulationResult.R$r1(var2));
         Expo.util.render.RenderUtil.I( -0.35F, -0.35F, 0.35F, 0.35F, TrajectorySimulationResult.K(var2), 60);
         Expo.util.render.RenderUtil.m(-0.35F, -0.35F, var10, 0.35F, 0.35F, 1.0F, TrajectorySimulationResult.K(var2));
         GlStateManager.popMatrix();
      }
   }

   private double J(TrajectoryStep var1) {
      return TrajectoryStep.x(var1) - TrajectoryStep.P(var1).yCoord;
   }

   private int s(Entity var1, long var2) {
      var2 = a ^ var2;
      int var6 = (int)((var2 ^ 87705994994996L) >>> 48);
      long var9 = var2 ^ 106744149123567L;
      if (var1 instanceof EntityPlayer) {
         EntityPlayer var11 = (EntityPlayer)var1;
         if (Teams.l(var11)) {
            return friendColor.k(var9);
         } else if (Teams.Y(var11)) {
            return enemyColor.k(var9);
         } else if (AntiBot.T((short)var6, var11)) {
            return botColor.k(var9);
         } else {
            return Teams.g(0L, var11) ? teammatesColor.k(var9) : baseColor.k(var9);
         }
      } else {
         return nonePlayersColor.k(var9);
      }
   }


   private void p(TrajectoryStep var1, TrajectoryProjectileSpec var2, int var3, TrajectoriesViewerOffset var4, TrajectorySimulationResult var5, char var6, int var7) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var8 = ((long)var3 << 32 | (long)var6 << 48 >>> 32 | (long)var7 << 48 >>> 48) ^ a;
      long var10 = var8 ^ 73990078807519L;
      long var12 = var8 ^ 94334553551053L;
      AxisAlignedBB var14 = this.p(var1, TrajectoryProjectileSpec.b(var2), this.Q(var1), this.J(var1), this.d(var1));
      ArrayList var15 = this.n(var14);
      TrajectoryEntityHit var16 = this.L(var15, TrajectoryStep.P(var1), TrajectoryStep.g(var1), TrajectoryProjectileSpec.b(var2));
      if (var16 != null) {
         TrajectorySimulationResult.g(var5, this.s(TrajectoryEntityHit.d(var16), var10));
         Expo.util.render.RenderUtil.A(TrajectoryEntityHit.d(var16), var12, TrajectorySimulationResult.K(var5), 1.5F, TrajectoryEntityHit.d(var16).getCollisionBorderSize());
         TrajectorySimulationResult.Q(var5, true);
         TrajectorySimulationResult.T(var5, true);
         TrajectorySimulationResult.E(var5, TrajectoryEntityHit.r(var16));
         this.W(TrajectoryEntityHit.r(var16).hitVec, var4, var5);
      }
   }

   private float getItemInUseDuration(float var1) {
      float var2 = f.thePlayer.getItemInUseDuration() + var1;
      float var3 = var2 / 20.0F;
      var3 = (var3 * var3 + var3 * 2.0F) / 3.0F;
      if (var3 > 1.0F) {
         var3 = 1.0F;
      }

      return var3;
   }

   private AxisAlignedBB p(TrajectoryStep var1, float var2, double var3, double var5, double var7) {
      return new AxisAlignedBB(
            TrajectoryStep.P(var1).xCoord - var2,
            TrajectoryStep.P(var1).yCoord - var2,
            TrajectoryStep.P(var1).zCoord - var2,
            TrajectoryStep.P(var1).xCoord + var2,
            TrajectoryStep.P(var1).yCoord + var2,
            TrajectoryStep.P(var1).zCoord + var2
         )
         .addCoord(var3, var5, var7)
         .expand(1.0, 1.0, 1.0);
   }

   private TrajectoryEntityHit L(ArrayList<Entity> var1, Vec3 var2, Vec3 var3, float var4) {
      for (int var5 = 0; var5 < var1.size(); var5++) {
         Entity var6 = (Entity)var1.get(var5);
         if (this.canBeCollidedWith(var6)) {
            AxisAlignedBB var7 = var6.getEntityBoundingBox().expand(var4, var4, var4);
            MovingObjectPosition var8 = var7.calculateIntercept(var2, var3);
            if (var8 != null) {
               return new TrajectoryEntityHit(var6, var8, null);
            }
         }
      }

      return null;
   }

   private boolean canBeCollidedWith(Entity var1) {
      return var1.canBeCollidedWith() && var1 != f.thePlayer;
   }

   private void N(TrajectoryStep var1, TrajectorySimulationResult var2) {
      TrajectorySimulationResult.E(var2, f.theWorld.rayTraceBlocks(TrajectoryStep.P(var1), TrajectoryStep.g(var1), false, true, false));
      if (TrajectorySimulationResult.R$r1(var2) != null) {
         TrajectorySimulationResult.T(var2, true);
         TrajectoryStep.y(var1, TrajectorySimulationResult.R$r1(var2).hitVec);
      }
   }

   private void t(MovingObjectPosition var1) {
      if (var1.sideHit != null) {
         switch (TrajectoriesSwitchMapAxis.t[var1.sideHit.getAxis().ordinal()]) {
            case 1:
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
               break;
            case 2:
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            case 3:
         }
      }
   }

   private static void a() {
      e[0] = "\"Z~IL\u0012)";
      e[1] = float.class;
      k[1] = "java/lang/Float";
      e[2] = "9\u001f98di,";
      e[3] = char.class;
      k[3] = "java/lang/Character";
      e[4] = "GO\fN$\u0015pX\bDi1gSRX";
      e[5] = int.class;
      k[5] = "java/lang/Integer";
      e[6] = "\r\u0010\u000e\u0018Fc&";
      e[7] = void.class;
      k[7] = "java/lang/Void";
      e[8] = "V&R\u00119<])C^X2V\"G\u0004";
      e[9] = "Kk_-TjFo\u000e@^\u0012\u00116_yMnWmT}&";
      e[10] = "\"`C\u0002p\u0013gbN\tI~\u001b9BU-\u001f+\u007fHMy# i\u001eIwQj}\u001c\bI\u0018y<AL+\u0013ih\u00193rA$f[QyQp>$\u000f)]$<DI8E|\u0003\u0019\r-\u001fzl\\\n$J\u001b";
      e[11] = "$N89 3)JiT)K~\u00138m978H3iR";
   }

   public final void x(long var1, EventBus var3) {
      TrajectoriesBinder.U( var3, this);
   }

   public Trajectories(long var1) {
      super(((a ^ (var1)) ^ 136413373644575L));
      // add code
      this.declare("Trajectories", Category.Visual_utility, "Show trajectories of projectiles");
      var1 = a ^ var1;
   }

   private boolean f$r4() {
      return f.thePlayer != null && f.theWorld != null && f.thePlayer.getHeldItem() != null && f.gameSettings.thirdPersonView == 0;
   }

   static {
      a = 44890774506870L;
      e = new Object[12];
      k = new String[12];
      d = new HashMap(13);
      b = new long[]{1885610206048317227L, -6062832488464918740L, 1199991079690601516L, -7825559100591298828L, 5120067646296097688L};
   }

   private double Q(TrajectoryStep var1) {
      return TrajectoryStep.L(var1) - TrajectoryStep.P(var1).xCoord;
   }

   private TrajectoriesViewerOffset P$r1(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      RenderManager var10 = f.getRenderManager();
      return new TrajectoriesViewerOffset(RenderManagerAccessor.k(0L, var10), RenderManagerAccessor.y(13236, var10), RenderManagerAccessor.W(0L, var10), null);
   }

   private void A(TrajectoryStep var1, TrajectoriesViewerOffset var2, TrajectorySimulationResult var3) {
      if (!TrajectorySimulationResult.L(var3) && TrajectorySimulationResult.R$r1(var3) != null) {
         this.W(TrajectorySimulationResult.R$r1(var3).hitVec, var2, var3);
      }
   }

   private TrajectoryProjectileSpec W(Item var1, float var2) {
      if (var1 instanceof ItemBow) {
         float var3 = this.getItemInUseDuration(var2);
         return var3 < 0.1F ? null : new TrajectoryProjectileSpec(true, var3 * 3.0F, 0.99F, 0.05F, 0.3F, null);
      } else if (var1 instanceof ItemFishingRod) {
         return new TrajectoryProjectileSpec(false, 1.5F, 0.92F, 0.04F, 0.25F, null);
      } else {
         return !(var1 instanceof ItemSnowball) && !(var1 instanceof ItemEgg) && !(var1 instanceof ItemEnderPearl)
            ? null
            : new TrajectoryProjectileSpec(false, 1.5F, 0.99F, 0.03F, 0.25F, null);
      }
   }

   private TrajectorySimulationResult getRGB(long var1, Vector3d var3, TrajectoryProjectileSpec var4, TrajectoriesViewerOffset var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {





      TrajectorySimulationResult var9 = new TrajectorySimulationResult(null);
      TrajectorySimulationResult.g(var9, Color.WHITE.getRGB());

      while (!TrajectorySimulationResult.c(var9) && Vector3d.I(var3) > 0.0) {
         TrajectoryStep var10 = this.L(var3);
         this.N(var10, var9);
         this.p(var10, var4, 30101, var5, var9, (char)6773, 27238);
         this.p(var3, TrajectoryStep.L(var10), TrajectoryStep.x(var10), TrajectoryStep.n(var10));
         this.A(var10, var5, var9);
         this.z(var3, TrajectoryProjectileSpec.Z(var4), TrajectoryProjectileSpec.T(var4));
         this.A(var3, var5, var9);
      }

      return var9;
   }

   private TrajectoryStep L(Vector3d var1) {
      double var2 = Vector3d.i(var1) + Vector3d.O(var1);
      double var4 = Vector3d.I(var1) + Vector3d.f(var1);
      double var6 = Vector3d.A(var1) + Vector3d.l(var1);
      Vec3 var8 = new Vec3(Vector3d.i(var1), Vector3d.I(var1), Vector3d.A(var1));
      Vec3 var9 = new Vec3(var2, var4, var6);
      return new TrajectoryStep(var8, var9, var2, var4, var6, null);
   }

   private float Q(float var1) {
      return var1 * (float) (Math.PI / 180.0);
   }

   private Vector3d t(TrajectoryProjectileSpec var1, TrajectoriesViewerOffset var2) {
      float var5 = RotationManager.p();
      float var6 = RotationManager.s();
      float var7 = this.Q(var5);
      float var8 = this.Q(var6);
      float var9 = MathHelper.sin(var7);
      float var10 = MathHelper.cos(var7);
      float var11 = MathHelper.sin(var8);
      float var12 = MathHelper.cos(var8);
      double var13 = TrajectoriesViewerOffset.q(var2) - var10 * 0.16;
      double var15 = TrajectoriesViewerOffset.L(var2) + f.thePlayer.getEyeHeight() - 0.1;
      double var17 = TrajectoriesViewerOffset.p(var2) - var9 * 0.16;
      double var19 = TrajectoryProjectileSpec.A(var1) ? 1.0 : 0.4;
      double var21 = -var9 * var12 * var19;
      double var23 = -var11 * var19;
      double var25 = var10 * var12 * var19;
      double var27 = Math.sqrt(var21 * var21 + var23 * var23 + var25 * var25);
      if (var27 == 0.0) {
         return null;
      }

      var21 = var21 / var27 * TrajectoryProjectileSpec.I(var1);
      var23 = var23 / var27 * TrajectoryProjectileSpec.I(var1);
      var25 = var25 / var27 * TrajectoryProjectileSpec.I(var1);
      return new Vector3d(var13, var15, var17, var21, var23, var25, null);
   }

   private void N(long var1, TrajectorySimulationResult var3) {






      WorldRenderer var11 = Tessellator.getInstance().getWorldRenderer();
      Expo.util.render.RenderUtil.L();
      Expo.util.render.RenderUtil.l(TrajectorySimulationResult.K(var3), 73372009905513L);
      GL11.glLineWidth(1.5F);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      var11.begin(3, DefaultVertexFormats.POSITION);

      for (int var12 = 0; var12 < TrajectorySimulationResult.b(var3).size(); var12++) {
         Vec3 var13 = (Vec3)TrajectorySimulationResult.b(var3).get(var12);
         var11.pos(var13.xCoord, var13.yCoord, var13.zCoord).endVertex();
      }

      Tessellator.getInstance().draw();
      this.x(1175, var3, 8184540, (byte)248);
      GL11.glDisable(2848);
      GL11.glLineWidth(2.0F);
      GlStateManager.resetColor();
      Expo.util.render.RenderUtil.w();
   }

   private double d(TrajectoryStep var1) {
      return TrajectoryStep.n(var1) - TrajectoryStep.P(var1).zCoord;
   }

   private void W(Vec3 var1, TrajectoriesViewerOffset var2, TrajectorySimulationResult var3) {
      TrajectorySimulationResult.M(var3, var1.xCoord - TrajectoriesViewerOffset.q(var2));
      TrajectorySimulationResult.N(var3, var1.yCoord - TrajectoriesViewerOffset.L(var2));
      TrajectorySimulationResult.B(var3, var1.zCoord - TrajectoriesViewerOffset.p(var2));
   }

   private void A(Vector3d var1, TrajectoriesViewerOffset var2, TrajectorySimulationResult var3) {
      TrajectorySimulationResult.b(var3).add(new Vec3(Vector3d.i(var1) - TrajectoriesViewerOffset.q(var2), Vector3d.I(var1) - TrajectoriesViewerOffset.L(var2), Vector3d.A(var1) - TrajectoriesViewerOffset.p(var2)));
   }

   private void z(Vector3d var1, float var2, float var3) {
      if (this.R(var1)) {
         Vector3d var4 = var1;
         Vector3d.B(var4, Vector3d.O(var4) * 0.6);
         var4 = var1;
         Vector3d.P(var4, Vector3d.f(var4) * 0.6);
         var4 = var1;
         Vector3d.U(var4, Vector3d.l(var4) * 0.6);
      } else {
         Vector3d var7 = var1;
         Vector3d.B(var7, Vector3d.O(var7) * var2);
         var7 = var1;
         Vector3d.P(var7, Vector3d.f(var7) * var2);
         var7 = var1;
         Vector3d.U(var7, Vector3d.l(var7) * var2);
      }

      Vector3d var10 = var1;
      Vector3d.P(var10, Vector3d.f(var10) - var3);
   }

   private ArrayList<Entity> n(AxisAlignedBB var1) {
      int var2 = MathHelper.floor_double((var1.minX - 2.0) / 16.0);
      int var3 = MathHelper.floor_double((var1.maxX + 2.0) / 16.0);
      int var4 = MathHelper.floor_double((var1.minZ - 2.0) / 16.0);
      int var5 = MathHelper.floor_double((var1.maxZ + 2.0) / 16.0);
      ArrayList var6 = new ArrayList();

      for (int var7 = var2; var7 <= var3; var7++) {
         for (int var8 = var4; var8 <= var5; var8++) {
            f.theWorld.getChunkFromChunkCoords(var7, var8).getEntitiesWithinAABBForEntity(f.thePlayer, var1, var6, null);
         }
      }

      return var6;
   }

   private boolean R(Vector3d var1) {
      return f.theWorld.getBlockState(new BlockPos(Vector3d.i(var1), Vector3d.I(var1), Vector3d.A(var1))).getBlock().getMaterial() == Material.water;
   }

   public void onRender3D(Render3DEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {



      if (this.f$r4()) {
         Item var12 = f.thePlayer.getHeldItem().getItem();
         TrajectoryProjectileSpec var13 = this.W(var12, var1.j);
         if (var13 != null) {
            TrajectoriesViewerOffset var14 = this.P$r1(53469978203973L);
            Vector3d var15 = this.t(var13, var14);
            if (var15 != null) {
               TrajectorySimulationResult var16 = this.getRGB(78770923882103L, var15, var13, var14);
               if (TrajectorySimulationResult.b(var16).size() > 1) {
                  this.N(12020114583032L, var16);
               }
            }
         }
      }
   }
   static {
      // add code
      nonePlayersColor = new ColorSetting("None-players-color", "FFFFFF");
      botColor = new ColorSetting("Bot-color", "FFFFFF");
      teammatesColor = new ColorSetting("Teammates-color", "FFFFFF");
      enemyColor = new ColorSetting("Enemy-color", "FF0000");
      friendColor = new ColorSetting("Friend-color", "00FF00");
      baseColor = new ColorSetting("Base-color", "FF0000");
   }
}
