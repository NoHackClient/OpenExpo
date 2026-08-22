package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.ESPBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render3DEvent;
import Expo.internal.accessor.EntityRendererAccessor;
import Expo.module.Module;
import Expo.module.impl.combat.KillAura;
import Expo.module.impl.configuration.Teams;
import Expo.module.impl.configuration.Theme;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.ColorSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.EntityUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;


public class ESP extends Module implements EventSubscriber {
   private static long a;
   private static Integer[] m;
   public static BooleanSetting friends;
   public static ModeSetting mode;
   public static ModeSetting healthBar;
   private static Map g;
   public static ColorSetting customColor;
   public static BooleanSetting showSelf;
   public static BooleanSetting mobs;
   public static BooleanSetting bots;
   private static Map n;
   public static BooleanSetting teammates;
   public static BooleanSetting bosses;
   public static BooleanSetting players;
   public static HeaderSetting targetSettings;
   public static ModeSetting color;
   private static String[] e;
   private static String[] r;
   private static long[] h;
   private static Object[] o;
   public static BooleanSetting animals;
   private final List<EntityLivingBase> L;
   public static BooleanSetting enemies;
   public static BooleanSetting hideTeammatesHealthBar;
   private static String[] d;
   public static NumberSetting offset;

   public final void x(long var1, EventBus var3) {
      ESPBinder.Y(var3, this);
   }

   public void l(EntityLivingBase var1, long var2, int var4, double var5, float var7, ScaledResolution var8) {

      if (Expo.util.render.RenderUtil.l(var1)) {
         EntityRendererAccessor.k(f.entityRenderer, var7, 0);
         double var11 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * var7 - f.getRenderManager().viewerPosX;
         double var13 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * var7 - f.getRenderManager().viewerPosY;
         double var15 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * var7 - f.getRenderManager().viewerPosZ;
         AxisAlignedBB var17 = var1.getEntityBoundingBox()
            .expand(0.1 + var5, 0.1 + var5, 0.1 + var5)
            .offset(var11 - var1.posX, var13 - var1.posY, var15 - var1.posZ);
         Vec3[] var10000 = new Vec3[8];
         var10000[0] = new Vec3(var17.minX, var17.minY, var17.minZ);
         var10000[1] = new Vec3(var17.minX, var17.maxY, var17.minZ);
         var10000[2] = new Vec3(var17.maxX, var17.minY, var17.minZ);
         var10000[3] = new Vec3(var17.maxX, var17.maxY, var17.minZ);
         var10000[4] = new Vec3(var17.minX, var17.minY, var17.maxZ);
         var10000[5] = new Vec3(var17.minX, var17.maxY, var17.maxZ);
         var10000[6] = new Vec3(var17.maxX, var17.minY, var17.maxZ);
         var10000[7] = new Vec3(var17.maxX, var17.maxY, var17.maxZ);
         Vec3[] var18 = var10000;
         double var19 = Double.MAX_VALUE;
         double var21 = Double.MAX_VALUE;
         double var23 = -Double.MAX_VALUE;
         double var25 = -Double.MAX_VALUE;
         boolean var27 = false;

         for (Vec3 var31 : var18) {
            Vec3 var32 = Expo.util.render.RenderUtil.I(var8.getScaleFactor(), var31.xCoord, var31.yCoord, var31.zCoord);
            if (var32 != null && !(var32.zCoord <= 0.0) && !(var32.zCoord >= 1.0)) {
               var27 = true;
               var19 = Math.min(var19, var32.xCoord);
               var21 = Math.min(var21, var32.yCoord);
               var23 = Math.max(var23, var32.xCoord);
               var25 = Math.max(var25, var32.yCoord);
            }
         }

         if (var27) {
            f.entityRenderer.setupOverlayRendering();
            var19 = Math.max(0.0, var19);
            var21 = Math.max(0.0, var21);
            var23 = Math.min(var8.getScaledWidth(), var23);
            var25 = Math.min(var8.getScaledHeight(), var25);
            float var38 = (var4 >> 16 & 255) / 255.0F;
            float var39 = (var4 >> 8 & 255) / 255.0F;
            float var40 = (var4 & 255) / 255.0F;
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(2.5F);
            GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.95F);
            GL11.glBegin(2);
            GL11.glVertex2d(var19, var21);
            GL11.glVertex2d(var23, var21);
            GL11.glVertex2d(var23, var25);
            GL11.glVertex2d(var19, var25);
            GL11.glEnd();
            GL11.glLineWidth(1.3F);
            GL11.glColor4f(var38, var39, var40, 1.0F);
            GL11.glBegin(2);
            GL11.glVertex2d(var19, var21);
            GL11.glVertex2d(var23, var21);
            GL11.glVertex2d(var23, var25);
            GL11.glVertex2d(var19, var25);
            GL11.glEnd();
            GL11.glDisable(2848);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
         }
      }
   }

   private void r(EntityLivingBase var1, long var2, ScaledResolution var4, float var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {









      if (!KillAura.e(139955413975329L, var1)) {
         if (Expo.util.render.RenderUtil.l(var1)) {
            int var24;
            switch (color.Y()) {
               case "THEME":
                  var24 = Theme.S(0.0, 35338930340239L);
                  break;
               case "THEME_CUSTOM":
                  var24 = Theme.X(65301174328177L, 0.0);
                  break;
               case "TEAM":
                  var24 = Teams.d((short)0, var1);
                  break;
               default:
                  var24 = customColor.k(96531491288662L);
            }

            switch (mode.Y()) {
               case "BOX":
                  this.l(var1, 138240708914945L, var24, 0.0, var5, var4);
                  break;
               case "2D":
                  Expo.util.render.RenderUtil.N(17533, var1, var24, var5);
                  break;
               case "3D":
                  Expo.util.render.RenderUtil.A(var1, 9401974101981L, var24, 1.5F, 0.0);
            }
         }
      }
   }

   public ESP(long var1) {
      super(((a ^ (var1)) ^ 20870233781589L));
      // add code
      this.declare("ESP", Category.Visual_utility, "Aka \"Extra sensory perception\"");
      var1 = a ^ var1;
      this.L = new ArrayList<>();
   }

   private static void a() {
      o[0] = "?`XF{\u0017\u0003";
      o[1] = long.class;
      r[1] = "java/lang/Long";
      o[2] = boolean.class;
      r[2] = "java/lang/Boolean";
      o[3] = ")g'uil7o=:\u000bp0r";
      o[4] = "A}+{BrA}<'N}[6:;[r[aq\u0010AoFl&";
      o[5] = "O>\u0012K\u0005cO>\u0005\u0017\tlUu\u0003\u000b\u001ccU\"H\u0015\u0004kX>\u0014K-dU2\u0012\u001c8f@\"\u0003\u0017";
      o[6] = "y\u0001\u0003tVVy";
      o[7] = ",\nKUu>\u001b\u001dO_8\u001a\f\u0016\u0015C";
      o[8] = "fuYEV\u0004k";
      o[9] = void.class;
      r[9] = "java/lang/Void";
      o[10] = "o\u001a+R,[d\u0015:\u001dMUo\u001e>G";
      o[11] = "Y\u001d2\\\u000fxBR0=\b\u0012\n\u0012cE\u0001.\u000fMpGc.\u000f]kT\u000f+\u000e\u0011a=ZhASa\u0000\bi]\u001c\n\u0004\u0019`MF7V\u0018|\u0002-3G\u0011lX\u0010aF\r#3\u0014pO\u001dy\u000eFqSR\u0012\nWxC\b/XVd\fc";
      o[12] = "3zQ1p/paJ.L\u001c\u000f>\u0016+s(eyU:+M6<@9.q3cS;Lpv~L83!~>MAr#adB$**hj)";
      o[13] = "Vg1\u0004\u0017\u0011M(3e&{\u0005h`\u001d\u0019G\u00007s\u001f{BF%w\u000eF\u0010G98eA\u0017Z8v\u0019\tGS:\t";
      o[14] = "2py[Ow)?{:Z\u001dcp.F\u0019%:;|[#$\"2?Q\u001ev#.p:\u001ag*>*\u0007Hf6qA\u0003Yo&+|QXsi@x@Qc3}*AM,Xy;H]ve+:T\u0012\u001da:3DH 3;/\u000b#$\"2?Q\u001ev#.p:\u001ag*>*\u0007Hf6qA\u0003\u001ct \"}\u0006Cg\"@x@Qc3}*AM,X";
   }

   public void onPostTick(int var1, char var2, PostTickEvent var3, short var4) {
      long var5 = ((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a;
      long var9 = var5 ^ 69028288505684L;
      long var11 = var5 ^ 53969792808358L;
      this.L.clear();
      boolean var13 = players.c();
      boolean var14 = mobs.c();
      boolean var15 = animals.c();
      boolean var16 = bosses.c();
      boolean var17 = friends.c();
      boolean var18 = enemies.c();
      boolean var19 = teammates.c();
      boolean var20 = bots.c();
      boolean var21 = var13 && !var14 && !var15 && !var16;
      List var22 = EntityUtil.U( var21);
      int var23 = 0;

      for (int var24 = var22.size(); var23 < var24; var23++) {
         EntityLivingBase var25 = (EntityLivingBase)var22.get(var23);
         if (var21
            ? EntityUtil.c(var11, (EntityPlayer)var25, var17, var18, var19, var20)
            : EntityUtil.q(var25, var13, var14, var15, var16, var17, var18, var19, var20, var9)) {
            this.L.add(var25);
         }
      }
   }

   static {
      a = 32365214196100L;
      zkm$clinit();
   }

   public void A(long var1) {
      this.L.clear();
   }


   public String g(long var1) {
      return mode.Y();
   }

   private static String b(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            int var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            int var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private void E(long var1, EntityLivingBase var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (!hideTeammatesHealthBar.c() || !Teams.g(0L, var3)) {
         switch (healthBar.Y()) {
            case "NORMAL":
               Expo.util.render.RenderUtil.a(var3, 106028892044707L, offset.L(), 6.0F, 23);
               break;
            case "THIN":
               Expo.util.render.RenderUtil.a(var3, 106028892044707L, offset.L(), 4.0F, 21);
         }
      }
   }


   public void onRender3D(char var1, int var2, Render3DEvent var3, short var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var5 = ((long)var1 << 48 | (long)var2 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      long var7 = var5 ^ 83461070758662L;
      long var9 = var5 ^ 80498493049726L;
      boolean var11 = mode.R("BOX");
      ArrayList var12 = new ArrayList(this.L.size());
      int var13 = 0;

      for (int var14 = this.L.size(); var13 < var14; var13++) {
         EntityLivingBase var15 = this.L.get(var13);
         if (var11) {
            this.E(var7, var15);
            var12.add(var15);
         } else {
            this.r(var15, var9, var3.O, var3.j);
            this.E(var7, var15);
         }
      }

      if (showSelf.c() && f.gameSettings.thirdPersonView != 0) {
         if (var11) {
            this.E(var7, f.thePlayer);
            var12.add(f.thePlayer);
         } else {
            this.r(f.thePlayer, var9, var3.O, var3.j);
            this.E(var7, f.thePlayer);
         }
      }

      if (var11) {
         var13 = 0;

         for (int var17 = var12.size(); var13 < var17; var13++) {
            EntityLivingBase var18 = (EntityLivingBase)var12.get(var13);
            this.r(var18, var9, var3.O, var3.j);
         }
      }
   }
   private static void zkm$clinit() {
      try {
         o = new Object[15];
         r = new String[15];
         a();
         g = new HashMap(13);
         long var11 = a ^ 50776291618664L;
         Cipher var13;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var14 = 1; var14 < 8; var14++) {
            var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
         }

         (var13 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var20 = new String[9];
         int var18 = 0;
         String var17 = "Õâ\u0089m§SCáe\u0091sÃ¨uÑA\u00102\u00980C¥¯e\u001d[Ô\u008c?\u0000\u009d\u0018!\u0010\u00849ì(\u0003Ø?¿Ø°\u0084%v\u0097ß\u0094\u0010º\u0000l¡Ý;V\u0004Ñ:\u0096©7\u0007ö÷ ù\fÓ6¦¦42ß¥]\u0019\u008dÆù\u008bI}\u0098îçmj\u008fzq3<Ù\u0019\u000f\u008d\u0010hdO\u008fºyJ?ÉÏ£éTñå\u0001\u0010\u0086\u0083kì[å ûÆ\u0094Áî¾Å+W";
         int var19 = "Õâ\u0089m§SCáe\u0091sÃ¨uÑA\u00102\u00980C¥¯e\u001d[Ô\u008c?\u0000\u009d\u0018!\u0010\u00849ì(\u0003Ø?¿Ø°\u0084%v\u0097ß\u0094\u0010º\u0000l¡Ý;V\u0004Ñ:\u0096©7\u0007ö÷ ù\fÓ6¦¦42ß¥]\u0019\u008dÆù\u008bI}\u0098îçmj\u008fzq3<Ù\u0019\u000f\u008d\u0010hdO\u008fºyJ?ÉÏ£éTñå\u0001\u0010\u0086\u0083kì[å ûÆ\u0094Áî¾Å+W"
            .length();
         char var16 = 16;
         int var25 = -1;

         label58:
         while (true) {
            String var26 = var17.substring(++var25, var25 + var16);
            int var10001 = -1;

            while (true) {
               byte[] var21 = var13.doFinal(var26.getBytes("ISO-8859-1"));
               String var37 = b(var21).intern();
               switch (var10001) {
                  case 0:
                     var20[var18++] = var37;
                     if ((var25 += var16) >= var19) {
                        d = var20;
                        e = new String[9];
                        n = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[20];
                        int var3 = 0;
                        String var4 = "\\Ú/\r$\u0018W¼²dÌYß¶ÚÿEKQ\u001e\u001bô\u007f\u001bjÈ\u0000d#>+oò>'÷¦\u00052«\u0011+ÔÆU°\b d\u008e\u009d\b´l\u009fÜ\u0001\u008e\u0013\u009d¹1\t\u000f²fóy\u0083<þ~¦Ã\u001eÔ\u0085¬ùuà\u001dg\u001a\u008d\u009c\u000f\u001dt¨Boõ±\u008b\u008cÆÐäè\u0085\u008d;¶¤K;\u009fvT\u0002iÝÊ\u0098\u001fÀlÉ\u009bZñÈÓj\u0080mÔ\u0010íKlP\u001e[§/*\b.?âL:";
                        int var5 = "\\Ú/\r$\u0018W¼²dÌYß¶ÚÿEKQ\u001e\u001bô\u007f\u001bjÈ\u0000d#>+oò>'÷¦\u00052«\u0011+ÔÆU°\b d\u008e\u009d\b´l\u009fÜ\u0001\u008e\u0013\u009d¹1\t\u000f²fóy\u0083<þ~¦Ã\u001eÔ\u0085¬ùuà\u001dg\u001a\u008d\u009c\u000f\u001dt¨Boõ±\u008b\u008cÆÐäè\u0085\u008d;¶¤K;\u009fvT\u0002iÝÊ\u0098\u001fÀlÉ\u009bZñÈÓj\u0080mÔ\u0010íKlP\u001e[§/*\b.?âL:"
                           .length();
                        int var2 = 0;

                        label40:
                        while (true) {
                           var10001 = var2;
                           var2 += 8;
                           byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                           long[] var29 = var6;
                           var10001 = var3++;
                           long var41 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           int var44 = -1;

                           while (true) {
                              long var8 = var41;
                              byte[] var10 = var0.doFinal(
                                 new byte[]{
                                    (byte)(var8 >>> 56),
                                    (byte)(var8 >>> 48),
                                    (byte)(var8 >>> 40),
                                    (byte)(var8 >>> 32),
                                    (byte)(var8 >>> 24),
                                    (byte)(var8 >>> 16),
                                    (byte)(var8 >>> 8),
                                    (byte)var8
                                 }
                              );
                              long var46 = (var10[0] & 255L) << 56
                                 | (var10[1] & 255L) << 48
                                 | (var10[2] & 255L) << 40
                                 | (var10[3] & 255L) << 32
                                 | (var10[4] & 255L) << 24
                                 | (var10[5] & 255L) << 16
                                 | (var10[6] & 255L) << 8
                                 | var10[7] & 255L;
                              switch (var44) {
                                 case 0:
                                    var29[var10001] = var46;
                                    if (var2 >= var5) {
                                       h = var6;
                                       m = new Integer[20];
                                       return;
                                    }
                                    break;
                                 default:
                                    var29[var10001] = var46;
                                    if (var2 < var5) {
                                       continue label40;
                                    }

                                    var4 = "ø¥,ÿî\b\u0098Ü»kÓ\u008f¡'\u0095ð";
                                    var5 = "ø¥,ÿî\b\u0098Ü»kÓ\u008f¡'\u0095ð".length();
                                    var2 = 0;
                              }

                              int var35 = var2;
                              var2 += 8;
                              var7 = var4.substring(var35, var2).getBytes("ISO-8859-1");
                              var29 = var6;
                              var10001 = var3++;
                              var41 = (var7[0] & 255L) << 56
                                 | (var7[1] & 255L) << 48
                                 | (var7[2] & 255L) << 40
                                 | (var7[3] & 255L) << 32
                                 | (var7[4] & 255L) << 24
                                 | (var7[5] & 255L) << 16
                                 | (var7[6] & 255L) << 8
                                 | var7[7] & 255L;
                              var44 = 0;
                           }
                        }
                     }

                     var16 = var17.charAt(var25);
                     break;
                  default:
                     var20[var18++] = var37;
                     if ((var25 += var16) < var19) {
                        var16 = var17.charAt(var25);
                        continue label58;
                     }

                     var17 = "^%ê\u0007\u0083gfBÕZ\u001féÕ§j\u0019\u0010\u001e\u009f§Ü¼\u007f\u009f¦Q<Y\u0002\u000fq!\u0014";
                     var19 = "^%ê\u0007\u0083gfBÕZ\u001féÕ§j\u0019\u0010\u001e\u009f§Ü¼\u007f\u009f¦Q<Y\u0002\u000fq!\u0014".length();
                     var16 = 16;
                     var25 = -1;
               }

               var26 = var17.substring(++var25, var25 + var16);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var22) {
         throw new RuntimeException(var22);
      }
   }
   static {
      // add code
      customColor = new ColorSetting("Custom-color", "FFFFFF");
   }
   static {
      // add code
      showSelf = new BooleanSetting("Show-self", false);
      hideTeammatesHealthBar = new BooleanSetting("Hide-teammates-health-bar", true);
      players = new BooleanSetting("Players", true);
      mobs = new BooleanSetting("Mobs", false);
      animals = new BooleanSetting("Animals", false);
      bosses = new BooleanSetting("Bosses", false);
      friends = new BooleanSetting("Friends", true);
      enemies = new BooleanSetting("Enemies", true);
      teammates = new BooleanSetting("Teammates", true);
      bots = new BooleanSetting("Bots", false);
   }
   static {
      // add code
      offset = new NumberSetting("Offset", 0.0F, -50.0F, 25.0F, 1.0F);
   }
   static {
      // add code
      mode = new ModeSetting("Mode", "BOX", "2D", "3D", "NONE");
      color = new ModeSetting("Color", "TEAM", "THEME", "THEME_CUSTOM", "CUSTOM");
      healthBar = new ModeSetting("Health-bar", "NORMAL", "THIN", "NONE");
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
