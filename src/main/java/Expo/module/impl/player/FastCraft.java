package Expo.module.impl.player;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FastCraftBinder;
import Expo.event.events.PostDrawScreenEvent;
import Expo.event.events.PreUpdateEvent;
import Expo.internal.accessor.GuiContainerAccessor;
import Expo.module.Module;
import Expo.util.ClientUtil;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;


public class FastCraft extends Module implements EventSubscriber {
   private final int[] J;
   private static long[] e;
   private final int[] s;
   private static Map h;
   private boolean v;
   private static String[] o;
   private final int[] G;
   private final int[] t;
   private static Map d;
   private static String[] c;
   private static long a;
   private final int[] m;
   private static Integer[] g;
   private static Object[] n;
   private final List<String> k;
   private boolean N;
   private static String[] b;

   private static void a() {
      n[0] = "G@+Fl\u00054";
      n[1] = "=n9\u000f\fK=n.S\u0000D'%$U\u0004O}B9D\f";
      n[2] = long.class;
      o[2] = "java/lang/Long";
      n[3] = int.class;
      o[3] = "java/lang/Integer";
      n[4] = "\u001e)}HR4\u001e)j\u0014^;\u0004bj\nV8\u001e8'\u000bJ1\u0004%y\n^$\u0015>'6S<\t){%P3\u0004>f\nS8\u0002\u0001Y";
      n[5] = "'/\u0016vD('/\u0001*H'=d\u00076](=3L(E 0/\u0010vl/=#\u0016!y-(3\u0007*";
      n[6] = "#G\u0006n,\u001b#G\u00112 \u00149\f\u001b4$\u001fck\u0006%,!9C\u0011+";
      n[7] = "bI`|\fMbIw \u0000Bx\u0002}<\u0017AbX{ \u0018\nOCz&\u0000MbIf\u0005\u000eVgNq<\u0002L";
      n[8] = "O\u0018";
      n[9] = boolean.class;
      o[9] = "java/lang/Boolean";
      n[10] = "*<T@9t5";
      n[11] = "\t{lHN\">lhB\u0003\u0006)g2^";
      n[12] = void.class;
      o[12] = "java/lang/Void";
      n[13] = "QO}\u00183sZ@lWR}QKh\r";
      n[14] = "\u000fJ!URcP\u000er1F\nP\u001c{\tLpP\u0014b_>3\bC\"\n\u0005n\u0004\u0012\"1\u00005\u000e\bl\t\u000ezU\u001d\u001cM\u0005hRNvK[qmM}MG1\u0007\fwJ\u0005\n";
      n[15] = "\u0013\u0006+0\u000f\u000e\u0011\u001bu,u\u0018,W*j\u001c\u0006L\t'h\u001cd\u0015\u0002*nN_H\u000e{nu\u0006\u0015Z~<\u001cYQ\t\u001a`\u001e\u0001TY`=O\u001f\u0010g";
      n[16] = "g\u0010i/^\u00018T:KMhy\u00126t\u000f\u0002\u007fL/K\u000b\r5\u0017opV\u0001d\u0017Tq\u000f\b:D74W\u0015j)nvRWhJ+.O\u0007\u0005";
      n[17] = "R4\u0019\u0013H=\u0016%\u0016\u001a/5\u00149\u0012<\u0010kVbB<F[Sb\u0019TB8\u0016:\u0004\u0004/aT?F\u0006L$\f\"\u0016k\u0015f\t`\u0014\bP>\u00140yQ\u0012;V2\u001a\u0014J&\u0006_ESU+\u001b FP\u00142ic\u0014\u0004MeP6\u0004\u0017\u0014[";
   }

   private int w(Item var1,int var4) {
      Container var5 = f.thePlayer.openContainer;

      for (int var6 = 10; var6 < var5.inventorySlots.size(); var6++) {
         Slot var7 = (Slot)var5.inventorySlots.get(var6);
         if (var7.getHasStack() && var7.getStack().getItem() == var1 && var7.getStack().stackSize >= var4) {
            return var6;
         }
      }

      return -1;
   }

   private void getRGB(float var1, float var2, Item var3, long var4, Color var6) {
      var4 = a ^ var4;
      long var7 = var4 ^ 72823998263771L;
      long var9 = var4 ^ 117520811710607L;
      Expo.util.render.RenderUtil.c(var9, var1, var2, var1 + 18.0F, var2 + 18.0F, var6.getRGB());
      Expo.util.render.RenderUtil.m(new ItemStack(var3), (int)(var1 + 1.0F), (int)(var2 + 1.0F));
      Expo.util.render.RenderUtil.H(var1, var2, var7, var1 + 18.0F, var2 + 18.0F, Color.BLACK.getRGB());
   }

   private boolean isWindowClick(ContainerWorkbench var1, int[] var4, Item var5) {
      int var8 = this.w(var5,var4.length);
      if (var8 == -1) {
         return false;
      }

      f.playerController.windowClick(var1.windowId, var8, 0, 0, f.thePlayer);

      for (int var12 : var4) {
         f.playerController.windowClick(var1.windowId, var12, 1, 0, f.thePlayer);
      }

      f.playerController.windowClick(var1.windowId, var8, 0, 0, f.thePlayer);
      f.playerController.windowClick(var1.windowId, 0, 0, 1, f.thePlayer);
      return true;
   }

   private boolean U(int[] var1, Item var4) {
      int var7 = this.w(var4,var1.length);
      return var7 != -1;
   }

   private static int d(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 3665;
      if (g[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = e[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().getId();
         Object[] var9 = (Object[])h.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               h.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/module/impl/player/FastCraft", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         g[var3] = var15;
      }

      return g[var3];
   }

   public void onPreUpdate(long var1, PreUpdateEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      if (f.currentScreen instanceof GuiCrafting) {
         Container var10 = f.thePlayer.openContainer;
         ContainerWorkbench var11 = (ContainerWorkbench)var10;
         if (!this.k.isEmpty()) {
            this.N = true;
            switch ((String)this.k.get(0)) {
               case "helmet":
                  if (this.isWindowClick(var11, this.J, Items.iron_ingot)) {
                     ClientUtil.t(48081174263320L, "\u00a7a\u00a7lSuccess \u00a7rCrafted an iron helmet");
                  } else {
                     ClientUtil.t(48081174263320L, "\u00a7c\u00a7lFailed \u00a7rto craft iron helmet, not enough resources");
                  }
                  break;
               case "chestplate":
                  if (this.isWindowClick(var11, this.s, Items.iron_ingot)) {
                     ClientUtil.t(48081174263320L, "\u00a7a\u00a7lSuccess \u00a7rCrafted an iron chestplate");
                  } else {
                     ClientUtil.t(48081174263320L, "\u00a7c\u00a7lFailed \u00a7rto craft iron chestplate, not enough resources");
                  }
                  break;
               case "leggings":
                  if (this.isWindowClick(var11, this.G, Items.iron_ingot)) {
                     ClientUtil.t(48081174263320L, "\u00a7a\u00a7lSuccess \u00a7rCrafted an iron leggings");
                  } else {
                     ClientUtil.t(48081174263320L, "\u00a7c\u00a7lFailed \u00a7rto craft iron leggings, not enough resources");
                  }
                  break;
               case "boots":
                  if (this.isWindowClick(var11, this.m, Items.iron_ingot)) {
                     ClientUtil.t(48081174263320L, "\u00a7a\u00a7lSuccess \u00a7rCrafted an iron boots");
                  } else {
                     ClientUtil.t(48081174263320L, "\u00a7c\u00a7lFailed \u00a7rto craft iron boots, not enough resources");
                  }
                  break;
               case "ladders":
                  if (this.isWindowClick(var11, this.t, Items.stick)) {
                     ClientUtil.t(48081174263320L, "\u00a7a\u00a7lSuccess \u00a7rCrafted 3 ladders");
                  } else {
                     ClientUtil.t(48081174263320L, "\u00a7c\u00a7lFailed \u00a7rto craft ladders, not enough resources");
                  }
            }

            this.k.clear();
         } else if (this.N && f.thePlayer.inventory.getItemStack() != null) {
            GuiContainerAccessor.S((GuiCrafting)f.currentScreen, null);
            f.thePlayer.inventory.setItemStack(null);
            f.currentScreen.updateScreen();
            this.N = false;
         }
      } else {
         this.k.clear();
         this.N = false;
      }
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

   public FastCraft(short var1, short var2, int var3) {
      super(((((((long)((var1)) << 48) | (((long)((var2)) << 48) >>> 16)) | (((long)((var3)) << 32) >>> 32)) ^ a) ^ 111658972797502L));
      // add code
      this.declare("FastCraft", Category.Player, "Craft some MegaWalls items faster");
      this.J = new int[]{1, 2, 3, 4, 6};
      int[] var10001 = new int[8];
      var10001[0] = 1;
      var10001[1] = 3;
      var10001[2] = 4;
      var10001[3] = 5;
      var10001[4] = 6;
      var10001[5] = 7;
      var10001[6] = 8;
      var10001[7] = 9;
      this.s = var10001;
      var10001 = new int[7];
      var10001[0] = 1;
      var10001[1] = 2;
      var10001[2] = 3;
      var10001[3] = 4;
      var10001[4] = 6;
      var10001[5] = 7;
      var10001[6] = 9;
      this.G = var10001;
      this.m = new int[]{4, 6, 7, 9};
      var10001 = new int[7];
      var10001[0] = 1;
      var10001[1] = 3;
      var10001[2] = 4;
      var10001[3] = 5;
      var10001[4] = 6;
      var10001[5] = 7;
      var10001[6] = 9;
      this.t = var10001;
      this.k = new CopyOnWriteArrayList<>();
      this.v = false;
      this.N = false;
   }

   public void onPostDrawScreen(PostDrawScreenEvent var1, long var2) {





      boolean var11 = this.v;
      if (var1.C instanceof GuiCrafting) {
         this.v = Mouse.isButtonDown(0);
         boolean var12 = !var11 && this.v;
         GuiCrafting var13 = (GuiCrafting)f.currentScreen;
         int var14 = Mouse.getEventX() * var13.width / f.displayWidth;
         int var15 = var13.height - Mouse.getEventY() * var13.height / f.displayHeight - 1;
         ScaledResolution var16 = var1.s;
         float var17 = var16.getScaledWidth() / 2.0F + 35 * var16.getScaleFactor();
         float var18 = var16.getScaledHeight() / 2.0F - 25.0F * var16.getScaleFactor();
         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.f((byte)0, var17, 4759559, var18, Items.iron_helmet, 3568500);
         float var19 = var18;
         float var20 = var18 + 18.0F;
         var18 += 25.0F;
         this.f((byte)0, var17, 4759559, var18, Items.iron_chestplate, 3568500);
         float var21 = var18;
         float var22 = var18 + 18.0F;
         var18 += 25.0F;
         this.f((byte)0, var17, 4759559, var18, Items.iron_leggings, 3568500);
         float var23 = var18;
         float var24 = var18 + 18.0F;
         var18 += 25.0F;
         this.f((byte)0, var17, 4759559, var18, Items.iron_boots, 3568500);
         float var25 = var18;
         float var26 = var18 + 18.0F;
         var18 += 25.0F;
         this.f((byte)0, var17, 4759559, var18, Item.getItemFromBlock(Blocks.ladder), 3568500);
         float var27 = var18;
         float var28 = var18 + 18.0F;
         float var29 = var17;
         float var30 = var17 + 18.0F;
         GlStateManager.popMatrix();
         if (var14 >= var29 && var14 <= var30) {
            if (var15 >= var19 && var15 <= var20) {
               this.getRGB(var17, var19, Items.iron_helmet, 105412507962817L, this.U(this.J, Items.iron_ingot) ? (this.v ? Color.ORANGE : Color.GREEN) : Color.RED);
               if (var12) {
                  this.k.add("helmet");
               }
            } else if (var15 >= var21 && var15 <= var22) {
               this.getRGB(var17, var21, Items.iron_chestplate, 105412507962817L, this.U(this.s, Items.iron_ingot) ? (this.v ? Color.ORANGE : Color.GREEN) : Color.RED);
               if (var12) {
                  this.k.add("chestplate");
               }
            } else if (var15 >= var23 && var15 <= var24) {
               this.getRGB(var17, var23, Items.iron_leggings, 105412507962817L, this.U(this.G, Items.iron_ingot) ? (this.v ? Color.ORANGE : Color.GREEN) : Color.RED);
               if (var12) {
                  this.k.add("leggings");
               }
            } else if (var15 >= var25 && var15 <= var26) {
               this.getRGB(var17, var25, Items.iron_boots, 105412507962817L, this.U(this.m, Items.iron_ingot) ? (this.v ? Color.ORANGE : Color.GREEN) : Color.RED);
               if (var12) {
                  this.k.add("boots");
               }
            } else if (var15 >= var27 && var15 <= var28) {
               this.getRGB(
                  var17,
                  var27,
                  Item.getItemFromBlock(Blocks.ladder),
                  105412507962817L,
                  this.U(this.t, Items.stick) ? (this.v ? Color.ORANGE : Color.GREEN) : Color.RED
               );
               if (var12) {
                  this.k.add("ladders");
               }
            }
         }
      } else {
         this.v = false;
      }
   }

   public final void x(long var1, EventBus var3) {
      FastCraftBinder.t(var3, this);
   }

   private void f(byte var1, float var2, int var3, float var4, Item var5, int var6) {
      long var7 = ((long)var1 << 56 | (long)var3 << 32 >>> 8 | (long)var6 << 40 >>> 40) ^ a;
      long var9 = var7 ^ 87993393723835L;
      this.getRGB(var2, var4, var5, var9, Color.WHITE);
   }

   public void A(long var1) {
      this.N = false;
      this.k.clear();
   }

   static {
      a = 78584124819214L;
      zkm$clinit();
   }

   private static void zkm$clinit() {
      try {
         n = new Object[18];
         o = new String[18];
         a();
         d = new HashMap(13);
         long var11 = a ^ 10213001497440L;
         Cipher var13;
         byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var14 = 1; var14 < 8; var14++) {
            var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
         }

         (var13 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var20 = new String[20];
         int var18 = 0;
         String var17 = ":NéÑÜ\u008f\u001aÜþ¨XÿÖ\u0013\u009b\r¼\u0083\u0019¥DÌ{©DêÔùë!®Aã\u0098qpÖ\u0098*VÂM\t)Óà\u0013!¡\u007f| °®z=t ÁÌ\u0087ôg\u001b\u0010Â9è¾\u0018\u0083\u0094hPçÄPõÉõ\u001a@GÄ\u001fÄ2\u0093ä\u0082°\u0097RhWô{·Ä\u009dMèé\\E]ª\u0091C\u001cÂnÑJ=°\u0019\u00016èyíìS¦g\u009dóN>H\u0099`iZ.q\r\u0086µèÒ N\u0018\f\u0010XÂW\u0003`\u0012g\u009dâ\t2ÔS}p]\u0010\u0003Ù\u0086\u0087¡Me\u009fÔlEËàÎôu\u0018Îór4î:1%\u00962Å.\u00adzí_è\u0095Ñè^C\u0089kXÑ\u008a¤Â\u007f\u001bâ'¥$gçñt;\u0097(\u0014Àe¾\u0099Y1c×1Øøé¶ÆâÔ\u0012\u0005ù«ªZùhPbõ\u0082C ué¨è\u0091\u0019è.HH\u0093¶\u0084\u0014çTvõyB\u0099Á@×Gø\u0015B\u0086p'\u0015ôÕ¡IG«¥:P\të\u0098hg`i\u0001\u0099lÉÜ,Y»(T\u0012pÙ±\b}¸\u0007n¼Ri«\u0085&\u0002´\u000f½ìwÙ3 Ñ\u0016\u0099\u001faï¾\u0018\u0004,¦|²L5nÊ{8öIÒhE\u00947©õðW¨,¿Ä4!\u001dÏ\u0098h\u008fÊ\u001f<Yl\bÉ\u001cØ^¥D°SÀ1:;âUd\bZÙ\u0006l\u0088iu\u001c&AwI8g@³°\u008dLÉÈ?WG}$ÝÃ\b\u001az\u00ad8!¾qÓûÿD\bÆzüx#úÛ\u0099e)9ÃÐd·BÁ »áø\u0006z¥Û\rÔ\u001f\u0087\u00962\u0086\u0019\u0013â¡-\u0095\u0012\n@©j\u0095\u0006MÈ|\u0096·~(W\u0005ú\u0096\u0006Y\u0092\u0006y×\u0084\u008cå©Þ\u00047\u0086Ã\u0096ÓÁð½\u0011\u0099\tE«Ü#\u0018%Ñç\u000f\u001d²Ûë\u0015\u0013$j¨êãôEb}M«h!÷d)\u0003\u009bä(\u0082\u001dR\u000fÒ¶}\u0006þ6Dh'Z³W%#²hÇl¡¢E\u0004ãóÜýF\u0003\u001by(\u001f\u0095@\f\u0082ÊÔú|¯\u0089\u0011\u0000{Y¦?\u0098¹¾Â\u008f\u0013hw£\u0010 \u0091NÑ²\u0092&\u001c\u0004ÏÕ\u0016»\t \u0004ôâ&XR|\u0016F\u0096\u0084pª\u0090\u0003e>\u001a}h;Xv¯mt\u0000të+^`m\u0098±\b¢Ô\u0005Cs=õ¶\u0098HáîõÎ32ÍârÞö¿\u0015È\nîh\u001fÏoÏ7,,\tãØ\u0019(\u001f\u0086\u0081ss¤ 1i\u009dÕ«Âi~Ä\u00adªxçºIy®¦-\u0089kiÓ¸;Û\u0096\u0017&<Rû\u0001\u0013\u0010\u0012#!ø\u008bÃ\u0003\u0018ôcÅ¨qú\u0004óiÕ\u0099\u0097@\u0080\u0094]\u007fYÍF$\b£\u0004 ä\u0080\u0088±S*\u0098ER\u00138¸vo[m\u0080\u009aÑNGL\u0084Y¸m Wf²Ë@h´§û\r\"}\u0088ÔìN\u008f]\u0019_\u008c\u009böZwr9ø\u0000fªÚÒ\u0099\u0080_õ\u000f\t3D\u0000\u008bwÇC\u0095ÿ\u009exdÎ\u001a´Æ÷w\u008a\u0011M±]6VÓ\u008aäß&Xµj+º©Uî\u0080Á·µè\u0014\u0093fê¤þ\u0005HI¿Ê«µ]2¢Z\u0012\u008379óÿ÷\u0085\u009eo\u0006\u0010\b=Õí\u008d\u0092\u001cõÛF'A_\u0090ÍÇH\u0018\r\u0087Ø: =M\u0005A1&C\u0007!ü¿[\u009eÛácOÓ%\u0099Ð..¡â\u001d\u0094iÛ\u0099m¤\u0013¼}V²\u0081Ùep¨|F\r+|&æ?\u0089¡ú\u0017\u001b_\u000bTÃ\u0085úÍrT÷\u0095\u0010\u0080\u0084dB\u0085\u009bZ?°\u0003XÞ\bE\rN";
         int var19 = ":NéÑÜ\u008f\u001aÜþ¨XÿÖ\u0013\u009b\r¼\u0083\u0019¥DÌ{©DêÔùë!®Aã\u0098qpÖ\u0098*VÂM\t)Óà\u0013!¡\u007f| °®z=t ÁÌ\u0087ôg\u001b\u0010Â9è¾\u0018\u0083\u0094hPçÄPõÉõ\u001a@GÄ\u001fÄ2\u0093ä\u0082°\u0097RhWô{·Ä\u009dMèé\\E]ª\u0091C\u001cÂnÑJ=°\u0019\u00016èyíìS¦g\u009dóN>H\u0099`iZ.q\r\u0086µèÒ N\u0018\f\u0010XÂW\u0003`\u0012g\u009dâ\t2ÔS}p]\u0010\u0003Ù\u0086\u0087¡Me\u009fÔlEËàÎôu\u0018Îór4î:1%\u00962Å.\u00adzí_è\u0095Ñè^C\u0089kXÑ\u008a¤Â\u007f\u001bâ'¥$gçñt;\u0097(\u0014Àe¾\u0099Y1c×1Øøé¶ÆâÔ\u0012\u0005ù«ªZùhPbõ\u0082C ué¨è\u0091\u0019è.HH\u0093¶\u0084\u0014çTvõyB\u0099Á@×Gø\u0015B\u0086p'\u0015ôÕ¡IG«¥:P\të\u0098hg`i\u0001\u0099lÉÜ,Y»(T\u0012pÙ±\b}¸\u0007n¼Ri«\u0085&\u0002´\u000f½ìwÙ3 Ñ\u0016\u0099\u001faï¾\u0018\u0004,¦|²L5nÊ{8öIÒhE\u00947©õðW¨,¿Ä4!\u001dÏ\u0098h\u008fÊ\u001f<Yl\bÉ\u001cØ^¥D°SÀ1:;âUd\bZÙ\u0006l\u0088iu\u001c&AwI8g@³°\u008dLÉÈ?WG}$ÝÃ\b\u001az\u00ad8!¾qÓûÿD\bÆzüx#úÛ\u0099e)9ÃÐd·BÁ »áø\u0006z¥Û\rÔ\u001f\u0087\u00962\u0086\u0019\u0013â¡-\u0095\u0012\n@©j\u0095\u0006MÈ|\u0096·~(W\u0005ú\u0096\u0006Y\u0092\u0006y×\u0084\u008cå©Þ\u00047\u0086Ã\u0096ÓÁð½\u0011\u0099\tE«Ü#\u0018%Ñç\u000f\u001d²Ûë\u0015\u0013$j¨êãôEb}M«h!÷d)\u0003\u009bä(\u0082\u001dR\u000fÒ¶}\u0006þ6Dh'Z³W%#²hÇl¡¢E\u0004ãóÜýF\u0003\u001by(\u001f\u0095@\f\u0082ÊÔú|¯\u0089\u0011\u0000{Y¦?\u0098¹¾Â\u008f\u0013hw£\u0010 \u0091NÑ²\u0092&\u001c\u0004ÏÕ\u0016»\t \u0004ôâ&XR|\u0016F\u0096\u0084pª\u0090\u0003e>\u001a}h;Xv¯mt\u0000të+^`m\u0098±\b¢Ô\u0005Cs=õ¶\u0098HáîõÎ32ÍârÞö¿\u0015È\nîh\u001fÏoÏ7,,\tãØ\u0019(\u001f\u0086\u0081ss¤ 1i\u009dÕ«Âi~Ä\u00adªxçºIy®¦-\u0089kiÓ¸;Û\u0096\u0017&<Rû\u0001\u0013\u0010\u0012#!ø\u008bÃ\u0003\u0018ôcÅ¨qú\u0004óiÕ\u0099\u0097@\u0080\u0094]\u007fYÍF$\b£\u0004 ä\u0080\u0088±S*\u0098ER\u00138¸vo[m\u0080\u009aÑNGL\u0084Y¸m Wf²Ë@h´§û\r\"}\u0088ÔìN\u008f]\u0019_\u008c\u009böZwr9ø\u0000fªÚÒ\u0099\u0080_õ\u000f\t3D\u0000\u008bwÇC\u0095ÿ\u009exdÎ\u001a´Æ÷w\u008a\u0011M±]6VÓ\u008aäß&Xµj+º©Uî\u0080Á·µè\u0014\u0093fê¤þ\u0005HI¿Ê«µ]2¢Z\u0012\u008379óÿ÷\u0085\u009eo\u0006\u0010\b=Õí\u008d\u0092\u001cõÛF'A_\u0090ÍÇH\u0018\r\u0087Ø: =M\u0005A1&C\u0007!ü¿[\u009eÛácOÓ%\u0099Ð..¡â\u001d\u0094iÛ\u0099m¤\u0013¼}V²\u0081Ùep¨|F\r+|&æ?\u0089¡ú\u0017\u001b_\u000bTÃ\u0085úÍrT÷\u0095\u0010\u0080\u0084dB\u0085\u009bZ?°\u0003XÞ\bE\rN"
            .length();
         char var16 = '@';
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
                        b = var20;
                        c = new String[20];
                        h = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[13];
                        int var3 = 0;
                        String var4 = "\u009d$ ×ì\u009f=\u0019ñ »·DÁ 84c;ÚC-9\u0094Ù²5\u0012Ló\u0005±±\u0086½Ø\u0083µÿ\u0000¿ñó°c\u0083\u0090¡\u000b\u0089\u0092l\u0094b$Dö×¶\u001eM\u009dªK\u009d}d?3\u0089Û9\u008e¦{Êøñö©¥Æ±\u0088Húýz";
                        int var5 = "\u009d$ ×ì\u009f=\u0019ñ »·DÁ 84c;ÚC-9\u0094Ù²5\u0012Ló\u0005±±\u0086½Ø\u0083µÿ\u0000¿ñó°c\u0083\u0090¡\u000b\u0089\u0092l\u0094b$Dö×¶\u001eM\u009dªK\u009d}d?3\u0089Û9\u008e¦{Êøñö©¥Æ±\u0088Húýz"
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
                                       e = var6;
                                       g = new Integer[13];
                                       return;
                                    }
                                    break;
                                 default:
                                    var29[var10001] = var46;
                                    if (var2 < var5) {
                                       continue label40;
                                    }

                                    var4 = "\u008b\u001fh¸v¤è\t³K\u001a·Y'\u0087Õ";
                                    var5 = "\u008b\u001fh¸v¤è\t³K\u001a·Y'\u0087Õ".length();
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

                     var17 = "\u009e\rRù~\u001eã\u009c\u000fj\"¹mç\u0006\u008d »yu\u009a4RÈ\b| \fT·¾ì1\u0084åvt\u0081<ö\fB¨Å÷Èa\u000e¸";
                     var19 = "\u009e\rRù~\u001eã\u009c\u000fj\"¹mç\u0006\u008d »yu\u009a4RÈ\b| \fT·¾ì1\u0084åvt\u0081<ö\fB¨Å÷Èa\u000e¸".length();
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
}
