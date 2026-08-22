package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.BreakProgressBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.PlayerControllerStateAccessor;
import Expo.module.Module;
import Expo.module.impl.configuration.Font;
import Expo.util.LunarClientDetector;
import Expo.util.render.CustomFont;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;











public class BreakProgress extends Module implements EventSubscriber {
   private BlockPos p;
   private String g;
   private static final long a = 8924778945689L;
   private double B;

   public void onRender3D(Render3DEvent var1) {

      if (this.B != 0.0 && this.p != null) {
         double var10 = this.p.getX() + 0.5 - f.getRenderManager().viewerPosX;
         double var12 = this.p.getY() + 0.5 - f.getRenderManager().viewerPosY;
         double var14 = this.p.getZ() + 0.5 - f.getRenderManager().viewerPosZ;
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)var10, (float)var12, (float)var14);
         GlStateManager.rotate(-f.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(f.getRenderManager().playerViewX, LunarClientDetector.q(0L) ? 1.0F : this.g(), 0.0F, 0.0F);
         GlStateManager.scale(-0.02266667F, -0.02266667F, -0.02266667F);
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         CustomFont var16 = Font.s(0L);
         var16.v(this.g, -f.fontRendererObj.getStringWidth(this.g) / 2, -3.0F, -1, 88827598794260L, true);
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   public void A(long var1) {
      this.B();
   }

   public BreakProgress(long var1) {
      super(((a ^ (var1)) ^ 96405790857882L));
      // add code
      this.declare("BreakProgress", Category.Visual, "Display the current breaking percentage right on the current breaking block");
      var1 = a ^ var1;
   }

   private void B() {
      this.B = 0.0;
      this.p = null;
      this.g = "";
   }

   private void w$r4() {
      this.g = (int)(100.0 * this.B) + "%";
   }

   public void onPostTick(int var1, PostTickEvent var2, short var3, short var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!f.thePlayer.capabilities.isCreativeMode && f.thePlayer.capabilities.allowEdit) {
         this.B = PlayerControllerStateAccessor.s(0L, f.playerController);
         if (this.B == 0.0) {
            this.B();
         } else {
            this.p = PlayerControllerStateAccessor.Z(f.playerController);
            this.w$r4();
         }
      } else {
         this.B();
      }
   }

   public final void x(long var1, EventBus var3) {
      BreakProgressBinder.Z(var3, this);
   }

   private float g() {
      return f.gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F;
   }

   static {
      a();
   }

   private static void a() {
   }}
