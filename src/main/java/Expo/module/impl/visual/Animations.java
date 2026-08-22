package Expo.module.impl.visual;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AnimationsBinder;
import Expo.event.events.GetArmSwingAnimationEndEvent;
import Expo.event.events.RenderItemInFirstPersonEvent;
import Expo.internal.accessor.ItemRendererAccessor;
import Expo.internal.synthetic.AnimationsSwitchMapEnumAction;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemMap;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;


public class Animations extends Module implements EventSubscriber {
   private static Map n;
   public static HeaderSetting h;
   private static long a;
   public static HeaderSetting c;
   private static Map t;
   private static String[] x;
   public static NumberSetting offsetX;
   public static NumberSetting scaleX;
   public static NumberSetting swingSpeed;
   public static NumberSetting scaleY;
   private static Object[] v;
   public static ModeSetting mode;
   public static NumberSetting rotationZ;
   public static HeaderSetting H;
   public static BooleanSetting noEquipReset;
   public static NumberSetting scaleZ;
   private static String[] d;
   private static Map g;
   public static BooleanSetting noRotationsEffect;
   public static NumberSetting rotationX;
   public static NumberSetting rotationY;
   public static NumberSetting offsetY;
   public static NumberSetting offsetZ;

   public void onGetArmSwingAnimationEnd(GetArmSwingAnimationEndEvent var1) {
      var1.t((int)(var1.N() * (-this.d(swingSpeed.L()) / 100.0F + 1.0F)));
   }

   public static void J() {
      GlStateManager.translate(offsetX.L() / 100.0F, offsetY.L() / 100.0F, offsetZ.L() / 100.0F);
   }

   private float d(float var1) {
      return var1 / 2.0F * 400.0F - 200.0F;
   }


   public static void C() {
      GlStateManager.rotate(rotationX.L(), 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(rotationY.L(), 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(rotationZ.L(), 0.0F, 0.0F, 1.0F);
   }



   public static void U() {
      GlStateManager.scale(scaleX.L(), scaleY.L(), scaleZ.L());
   }

   public final void x(long var1, EventBus var3) {
      AnimationsBinder.A(var3, this);
   }

   private static void a() {
      v[0] = "\u0016I\u0002l\u000e\u00120";
      v[1] = "\u001b-\u0000I}\u001a,:\u0004C0>;1^_";
      v[2] = long.class;
      x[2] = "java/lang/Long";
      v[3] = "0\u0011\u0006 6n$";
      v[4] = void.class;
      x[4] = "java/lang/Void";
      v[5] = "g[P\u001ee4lTAQ\u0004:g_E\u000b";
      v[6] = "/61klT+71\u0004L)sx/y{\u0012t;1c\u0005\u0010:-wdxH),6\u0004?N1\u007f'|wU2$I>{J49'hw\u0016{G";
   }

   public void onRenderItemInFirstPerson(long var1, RenderItemInFirstPersonEvent var3) {


      if (!(var3.e.getItem() instanceof ItemMap)) {
         if (var3.d.equals(EnumAction.BLOCK)) {
            if (!mode.R("1.7")) {
               EnumAction var7 = var3.d;
               ItemRenderer var8 = f.getItemRenderer();
               float var9 = var3.J;
               float var10 = var3.C;
               float var11 = MathHelper.sin(MathHelper.sqrt_float(var10) * (float) Math.PI);
               switch (AnimationsSwitchMapEnumAction.j[var7.ordinal()]) {
                  case 1:
                     String var12 = mode.Y();
                     int var13 = -1;
                     switch (var12.hashCode()) {
                        case -1860632113:
                           if (var12.equals("EXHIBITION")) {
                              var13 = 1;
                           }
                           break;
                        case -1845204562:
                           if (var12.equals("SMOOTH")) {
                              var13 = 0;
                           }
                           break;
                        case 2514896:
                           if (var12.equals("RHYS")) {
                              var13 = 9;
                           }
                           break;
                        case 2551874:
                           if (var12.equals("SPIN")) {
                              var13 = 3;
                           }
                           break;
                        case 2555458:
                           if (var12.equals("STAB")) {
                              var13 = 2;
                           }
                           break;
                        case 2670253:
                           if (var12.equals("WOOD")) {
                              var13 = 5;
                           }
                           break;
                        case 62368104:
                           if (var12.equals("ALLAH")) {
                              var13 = 10;
                           }
                           break;
                        case 64093444:
                           if (var12.equals("CHILL")) {
                              var13 = 7;
                           }
                           break;
                        case 78897669:
                           if (var12.equals("SIGMA")) {
                              var13 = 4;
                           }
                           break;
                        case 79322468:
                           if (var12.equals("SWONG")) {
                              var13 = 6;
                           }
                           break;
                        case 1240017088:
                           if (var12.equals("KOMOREBI")) {
                              var13 = 8;
                           }
                     }

                     switch (var13) {
                        case 0:
                           ItemRendererAccessor.s(var8, var9, 0.0F);
                           float var14 = -var11 * 2.0F;
                           f(0.0, var14 / 10.0F + 0.1F, 0.0);
                           GlStateManager.rotate(var14 * 10.0F, 0.0F, 1.0F, 0.0F);
                           GlStateManager.rotate(250.0F, 0.2F, 1.0F, -0.6F);
                           GlStateManager.rotate(-10.0F, 1.0F, 0.5F, 1.0F);
                           GlStateManager.rotate(-var14 * 20.0F, 1.0F, 0.5F, 1.0F);
                           break;
                        case 1:
                           ItemRendererAccessor.s(var8, var9 / 2.0F, 0.0F);
                           f(0.0, 0.3F, -0.0);
                           GlStateManager.rotate(-var11 * 31.0F, 1.0F, 0.0F, 2.0F);
                           GlStateManager.rotate(-var11 * 33.0F, 1.5F, var11 / 1.1F, 0.0F);
                           ItemRendererAccessor.e(var8);
                           break;
                        case 2:
                           float var15 = MathHelper.sin(MathHelper.sqrt_float(var10) * (float) Math.PI);
                           f(0.6F, 0.3F, -0.6F + -var15 * 0.7);
                           GlStateManager.rotate(6090.0F, 0.0F, 0.0F, 0.1F);
                           GlStateManager.rotate(6085.0F, 0.0F, 0.1F, 0.0F);
                           GlStateManager.rotate(6110.0F, 0.1F, 0.0F, 0.0F);
                           ItemRendererAccessor.s(var8, 0.0F, 0.0F);
                           ItemRendererAccessor.e(var8);
                           break;
                        case 3:
                           ItemRendererAccessor.s(var8, var9, 0.0F);
                           f(0.0, 0.2F, -1.0);
                           GlStateManager.rotate(-59.0F, -1.0F, 0.0F, 3.0F);
                           GlStateManager.rotate(
                              (float)(-(System.currentTimeMillis() / 2L % 360L)),
                              1.0F,
                              0.0F,
                              0.0F
                           );
                           GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
                           break;
                        case 4:
                           ItemRendererAccessor.s(var8, var9, 0.0F);
                           f(0.0, 0.1F, 0.0);
                           ItemRendererAccessor.e(var8);
                           GlStateManager.rotate(var11 * 35.0F / 2.0F, 0.0F, 1.0F, 1.5F);
                           GlStateManager.rotate(-var11 * 135.0F / 4.0F, 1.0F, 1.0F, 0.0F);
                           break;
                        case 5:
                           ItemRendererAccessor.s(var8, var9 / 2.0F, 0.0F);
                           f(0.0, 0.3F, -0.0);
                           GlStateManager.rotate(-var11 * 30.0F, 1.0F, 0.0F, 2.0F);
                           GlStateManager.rotate(-var11 * 44.0F, 1.5F, var11 / 1.2F, 0.0F);
                           ItemRendererAccessor.e(var8);
                           break;
                        case 6:
                           ItemRendererAccessor.s(var8, var9 / 2.0F, var10);
                           GlStateManager.rotate(var11 * 30.0F / 2.0F, -var11, -0.0F, 9.0F);
                           GlStateManager.rotate(var11 * 40.0F, 1.0F, -var11 / 2.0F, -0.0F);
                           f(0.0, 0.2F, 0.0);
                           ItemRendererAccessor.e(var8);
                           break;
                        case 7:
                           ItemRendererAccessor.s(var8, -0.25F, 1.0F + var11 / 10.0F);
                           GL11.glRotated(-var11 * 25.0F, 1.0, 0.0, 0.0);
                           ItemRendererAccessor.e(var8);
                           break;
                        case 8:
                           f(0.41F, -0.25, -0.5555557F);
                           f(0.0, 0.0, 0.0);
                           GlStateManager.rotate(35.0F, 0.0F, 1.5F, 0.0F);
                           float var16 = MathHelper.sin(var10 * var10 / 64.0F * (float) Math.PI);
                           GlStateManager.rotate(var16 * -5.0F, 0.0F, 0.0F, 0.0F);
                           GlStateManager.rotate(var11 * -12.0F, 0.0F, 0.0F, 1.0F);
                           GlStateManager.rotate(var11 * -65.0F, 1.0F, 0.0F, 0.0F);
                           ItemRendererAccessor.e(var8);
                           break;
                        case 9:
                           ItemRendererAccessor.s(var8, var9, var10);
                           ItemRendererAccessor.e(var8);
                           f(-0.3F, -0.1F, -0.0);
                           break;
                        case 10:
                           ItemRendererAccessor.s(var8, var9, 0.0F);
                           ItemRendererAccessor.e(var8);
                     }
                  default:
                     U();
                     var3.I(21307, 3074332907L);
               }
            }
         }
      }
   }

   public Animations(long var1) {
      super(((a ^ (var1)) ^ 29828256606173L));
      // add code
      this.declare("Animations", Category.Visual, "Some 1.7 item using animations");
      var1 = a ^ var1;
   }

   public static void f(double var0, double var2, double var4) {
      GlStateManager.translate(var0 + offsetX.L() / 100.0F, var2 + offsetY.L() / 100.0F, var4 + offsetZ.L() / 100.0F);
   }

   static {
      a = 90915734307650L;
      v = new Object[7];
      x = new String[7];
      g = new HashMap(13);
      d = new String[12];
      n = new HashMap(13);
      t = new HashMap(13);
   }

   static {
      // add code
      noRotationsEffect = new BooleanSetting("No-rotations-effect", true);
      // update new version
      h = new HeaderSetting("Offset settings");
      // update new version
      c = new HeaderSetting("Scale settings");
      // update new version
      H = new HeaderSetting("Rotation settings");
      noEquipReset = new BooleanSetting("No-equip-reset", true);
      mode = new ModeSetting(
         "Mode", true, "NONE",
         "NONE", "1.7", "SMOOTH", "SPIN", "STAB", "SWONG", "WOOD",
         "SIGMA", "RHYS", "CHILL", "EXHIBITION", "KOMOREBI", "ALLAH"
      );
   }


   public String g(long var1) {
      return mode.Y();
   }
   static {
      // add code
      rotationY = new NumberSetting("Rotation-Y", 0.0F, -180.0F, 180.0F, 1.0F);
      scaleZ = new NumberSetting("Scale-Z", 1.0F, 0.0F, 2.0F, 0.01F);
      scaleY = new NumberSetting("Scale-Y", 1.0F, 0.0F, 2.0F, 0.01F);
      rotationX = new NumberSetting("Rotation-X", 0.0F, -180.0F, 180.0F, 1.0F);
      scaleX = new NumberSetting("Scale-X", 1.0F, 0.0F, 2.0F, 0.01F);
      rotationZ = new NumberSetting("Rotation-Z", 0.0F, -180.0F, 180.0F, 1.0F);
      offsetZ = new NumberSetting("Offset-Z", 0.0F, -500.0F, 500.0F, 1.0F);
      swingSpeed = new NumberSetting("Swing-speed", 1.0F, 0.0F, 1.33F, 0.01F);
      offsetX = new NumberSetting("Offset-X", 0.0F, -500.0F, 500.0F, 1.0F);
      offsetY = new NumberSetting("Offset-Y", 0.0F, -500.0F, 500.0F, 1.0F);
   }
}
