package Expo.ui.raven;

import Expo.enums.BlurDirection;
import Expo.internal.synthetic.RavenFramebufferSwitchMapBlurDirection;
import Expo.util.MinecraftRef;
import Expo.util.render.RenderUtil;
import java.awt.Color;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;


public class RavenFramebuffer {
   private int e;
   private int N;
   private int r;
   private boolean Z;
   private int z;
   private int f;
   private static long[] g;
   private int U;
   private int[] u;
   private int a;
   private int T;
   private int i;
   private int h;
   private final BlurDirection B;
   private int c;
   private static Minecraft j;
   private int w;
   private static Map l;
   private int L;
   private int m;
   private int D;
   private int H;
   private static long d;
   private int b;
   private int S;

   public void c(int var1, int var2, int var3, int var4, long var5) {


      this.u = this.C(var1, var2, var3, var4, 29209995737999L);
      int var11 = this.u[0];
      int var12 = this.u[var3 - 1];
      int var13 = this.u[(var4 - 1) * var3 - 1];
      int var14 = this.u[var4 * var3 - 1];
      Color var15;
      Color var16;
      switch (RavenFramebufferSwitchMapBlurDirection.O[this.B.ordinal()]) {
         case 1:
            var15 = RenderUtil.n(var11, var13,0L);
            var16 = RenderUtil.n(var12, var14,0L);
            break;
         case 2:
         default:
            var15 = RenderUtil.n(var11, var12,0L);
            var16 = RenderUtil.n(var13, var14,0L);
      }

      this.z = var15.getRed();
      this.D = var15.getGreen();
      this.r = var15.getBlue();
      this.w = var16.getRed();
      this.h = var16.getGreen();
      this.b = var16.getBlue();
      if (!this.Z) {
         this.a = this.z;
         this.i = this.r;
         this.L = this.D;
         this.m = this.w;
         this.f = this.b;
         this.U = this.h;
         this.Z = true;
      }

      this.e = this.a;
      this.N = this.i;
      this.T = this.L;
      this.H = this.m;
      this.c = this.f;
      this.S = this.U;
      this.a = this.P(this.a, this.z);
      this.i = this.P(this.i, this.D);
      this.L = this.P(this.L, this.r);
      this.m = this.P(this.m, this.w);
      this.f = this.P(this.f, this.h);
      this.U = this.P(this.U, this.b);
      this.a = Math.min(this.a, 255);
      this.i = Math.min(this.i, 255);
      this.L = Math.min(this.L, 255);
      this.a = Math.max(this.a, 0);
      this.i = Math.max(this.i, 0);
      this.L = Math.max(this.L, 0);
      this.m = Math.min(this.m, 255);
      this.f = Math.min(this.f, 255);
      this.U = Math.min(this.U, 255);
      this.m = Math.max(this.m, 0);
      this.f = Math.max(this.f, 0);
      this.U = Math.max(this.U, 0);
   }

   public void a(long var1, float var3, float var4, float var5, float var6, float var7, float var8) {



      Color var12 = new Color(this.B(this.a, this.e, var7), this.B(this.i, this.N, var7), this.B(this.L, this.T, var7));
      Color var13 = new Color(this.B(this.m, this.H, var7), this.B(this.f, this.c, var7), this.B(this.U, this.S, var7));
      var12 = RenderUtil.R(var12, var8);
      var13 = RenderUtil.R(var13, var8);
      switch (RavenFramebufferSwitchMapBlurDirection.O[this.B.ordinal()]) {
         case 1:
         case 2:
            RenderUtil.d(362153198233L, var3, var4, var3 + var5, var4 + var6, var12.getRGB(), var13.getRGB(), (byte)220);
      }
   }

   public int t() {
      return this.f;
   }

   public int z() {
      return this.H;
   }

   public int d() {
      return this.r;
   }

   public int E() {
      return this.c;
   }

   public int[] O() {
      return this.u;
   }

   public int C() {
      return this.z;
   }

   public int V() {
      return this.D;
   }

   private int B(double var1, double var3, float var5) {
      return (int)((1.0F - var5) * var3 + var5 * var1);
   }

   public int p() {
      return this.N;
   }

   public int i() {
      return this.a;
   }

   public int c() {
      return this.T;
   }

   public boolean D() {
      return this.Z;
   }

   public void C(long var1, float var3, float var4, float var5, float var6) {
      long var7 = var1 ^ 118768600163166L;
      this.c((int)var3, (int)var4, (int)var5, (int)var6, var7);
   }


   public int u() {
      return this.S;
   }

   public int b() {
      return this.i;
   }

   public int Q() {
      return this.U;
   }

   private int P(double var1, double var3) {
      return (int)(var1 + (var3 - var1) / 10.0);
   }

   public int w() {
      return this.e;
   }

   public RavenFramebuffer(long var1, BlurDirection var3) {
      var1 = d ^ var1;
      this.Z = false;
      this.B = var3;
   }

   public int Y() {
      return this.m;
   }


   private int[] C(int var1, int var2, int var3, int var4, long var5) {
      int var8 = var3 * var4;
      IntBuffer var9 = (IntBuffer)((Buffer)BufferUtils.createIntBuffer(var8)).clear();
      int[] var7 = new int[var8];
      GL11.glPixelStorei(240, 1);
      GL11.glPixelStorei(320, 1);
      int var10 = 1;
      int var11 = j.gameSettings.guiScale;
      if (var11 == 0) {
         var11 = 255;
      }

      while (
         var10 < var11
            && j.displayWidth / (var10 + 1) >= 3317
            && j.displayHeight / (var10 + 1) >= 3333
      ) {
         var10++;
      }

      GL11.glReadPixels(
         var1 * var10,
         j.displayHeight - (var2 + 1) * var10,
         var3,
         var4,
         0,
         255,
         var9
      );
      var9.get(var7);
      TextureUtil.processPixelValues(var7, var3, var4);
      return var7;
   }

   public int S() {
      return this.L;
   }

   static {
      j = MinecraftRef.c((byte)0, 0L);
      d = 111385426594014L;
      l = new HashMap(13);
      g = new long[]{-634123079903718284L, -449954504354636044L, -5448260739223382033L, 2332538861600869028L, -5339800790329183928L, 7122341011747045817L, 775259053441777127L, -997511244557585255L, 6140130801574930532L, 524137214210370194L, -2300091152783046003L, -5054703763100788379L};
   }

}
