package Expo.util;

import Expo.internal.synthetic.OggStreamPlayerCtorMarker;
import com.jcraft.jogg.Packet;
import com.jcraft.jogg.Page;
import com.jcraft.jogg.StreamState;
import com.jcraft.jogg.SyncState;
import com.jcraft.jorbis.Block;
import com.jcraft.jorbis.Comment;
import com.jcraft.jorbis.DspState;
import com.jcraft.jorbis.Info;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl.Type;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

class OggStreamPlayer implements Runnable {
   private volatile boolean c;
   private static String b;
   private volatile boolean o;
   private Thread O;
   private static long[] d;
   private SoundCallback W;
   private final InputStream J;
   private static Map f;
   private static long a;
   private final float p;
   private static long g;

   public void L() {
      this.o = true;
   }

   private void r(InputStream var1,float var4) {
      SyncState var5 = null;
      StreamState var6 = null;
      Page var7 = null;
      Packet var8 = null;
      Info var9 = null;
      Comment var10 = null;
      DspState var11 = null;
      Block var12 = null;
      SourceDataLine var13 = null;

      try {
         var5 = new SyncState();
         var6 = new StreamState();
         var7 = new Page();
         var8 = new Packet();
         var9 = new Info();
         var10 = new Comment();
         var11 = new DspState();
         var12 = new Block(var11);
         var5.init();
         int var14 = var5.buffer(4096);
         byte[] var15 = var5.data;
         int var16 = var1.read(var15, var14, 4096);
         if (var16 > 0) {
            var5.wrote(var16);
            if (var5.pageout(var7) == 1) {
               var6.init(var7.serialno());
               var6.pagein(var7);
               if (var6.packetout(var8) == 1) {
                  var9.init();
                  var10.init();
                  if (var9.synthesis_headerin(var10, var8) >= 0) {
                     int var17 = 0;

                     while (var17 < 2) {
                        int var18 = var5.pageout(var7);
                        if (var18 == 0) {
                           var14 = var5.buffer(4096);
                           var15 = var5.data;
                           var16 = var1.read(var15, var14, 4096);
                           if (var16 <= 0) {
                              return;
                           }

                           var5.wrote(var16);
                        } else if (var18 == 1) {
                           var6.pagein(var7);

                           while (var17 < 2 && var6.packetout(var8) == 1) {
                              if (var9.synthesis_headerin(var10, var8) != 0) {
                                 return;
                              }

                              var17++;
                           }
                        }
                     }

                     var11.synthesis_init(var9);
                     var12.init(var11);
                     AudioFormat var61 = new AudioFormat(var9.rate, 16, var9.channels, true, false);
                     if (!AudioSystem.isLineSupported(new javax.sound.sampled.DataLine.Info(SourceDataLine.class, var61))) {
                        AudioFormat var19 = new AudioFormat(
                           Math.min(var9.rate, 44100), 16, var9.channels, true, false
                        );
                        var13 = (SourceDataLine)AudioSystem.getLine(new javax.sound.sampled.DataLine.Info(SourceDataLine.class, var19));
                        var13.open(var19);
                     } else {
                        var13 = (SourceDataLine)AudioSystem.getLine(new javax.sound.sampled.DataLine.Info(SourceDataLine.class, var61));
                        var13.open(var61);
                     }

                     var13.start();
                     l(var13, var4);
                     float[][][] var62 = new float[1][][];
                     int[] var20 = new int[var9.channels];
                     boolean var21 = false;

                     while (!var21 && !this.c) {
                        if (Thread.currentThread().isInterrupted()) {
                           this.c = true;
                           return;
                        }

                        while (this.o && !this.c) {
                           try {
                              Thread.sleep(g);
                           } catch (InterruptedException var48) {
                              this.c = true;
                              Thread.currentThread().interrupt();
                              return;
                           }

                           if (Thread.currentThread().isInterrupted()) {
                              this.c = true;
                              return;
                           }
                        }

                        if (this.c) {
                           return;
                        }

                        int var22 = var5.pageout(var7);
                        if (var22 == 0) {
                           var14 = var5.buffer(4096);
                           var15 = var5.data;
                           var16 = var1.read(var15, var14, 4096);
                           if (var16 <= 0) {
                           }

                           var5.wrote(var16);
                        } else if (var22 == 1) {
                           var6.pagein(var7);

                           while (var6.packetout(var8) == 1) {
                              if (var12.synthesis(var8) == 0) {
                                 var11.synthesis_blockin(var12);
                              }

                              int var23;
                              while ((var23 = var11.synthesis_pcmout(var62, var20)) > 0 && !this.c) {
                                 float[][] var24 = var62[0];
                                 int var25 = Math.min(var23, 1024);
                                 byte[] var26 = new byte[var25 * var9.channels * 2];
                                 int var27 = 0;

                                 for (int var28 = 0; var28 < var25; var28++) {
                                    for (int var29 = 0; var29 < var9.channels; var29++) {
                                       int var30 = (int)(var24[var29][var20[var29] + var28] * 32767.0F);
                                       if (var30 > 32767) {
                                          var30 = 32767;
                                       }

                                       if (var30 < -32768) {
                                          var30 = -32768;
                                       }

                                       var26[var27++] = (byte)(var30 & 255);
                                       var26[var27++] = (byte)(var30 >>> 8 & 255);
                                    }
                                 }

                                 if (!this.c && !this.o) {
                                    var13.write(var26, 0, var26.length);
                                 }

                                 var11.synthesis_read(var25);
                              }
                           }

                           if (var7.eos() != 0) {
                           }
                        }
                     }
                  }
               }
            }
         }
      } catch (Throwable var49) {
      } finally {
         if (var13 != null) {
            var13.drain();
            g(var13);
         }

         if (var6 != null) {
            var6.clear();
         }

         if (var12 != null) {
            var12.clear();
         }

         if (var11 != null) {
            var11.clear();
         }

         if (var9 != null) {
            var9.clear();
         }

         if (var5 != null) {
            var5.clear();
         }

         try {
            if (var1 != null) {
               var1.close();
            }
         } catch (IOException var47) {
         }
      }
   }

   public void F(long var1) {
      this.o = false;
   }

   public void run() {
      try {
         this.r(this.J,this.p);
      } catch (Throwable var6) {
         SoundEngine.l().remove(this);
         if (!this.c && this.W != null) {
            this.W.s();
         }

         return;
      }

      SoundEngine.l().remove(this);
      if (!this.c && this.W != null) {
         this.W.s();
      }
   }

   public void G(SoundCallback var1) {
      this.W = var1;
   }

   private OggStreamPlayer(long var1, InputStream var3, float var4) {
      this.o = false;
      this.c = false;
      this.J = var3;
      this.p = var4;
   }

   OggStreamPlayer(int var1, short var2, InputStream var3, char var4, float var5, OggStreamPlayerCtorMarker var6) {
      this((((long)var1 << 32 | (long)var2 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a) ^ 121138181207423L, var3, var5);
   }

   private static void g(SourceDataLine var0) {
      try {
         var0.stop();
      } catch (Throwable var3) {
      }

      try {
         var0.close();
      } catch (Throwable var2) {
      }
   }

   public void b(long var1) {
      this.c = true;
      this.o = false;
      if (this.O != null) {
         this.O.interrupt();
      }
   }

   public void t(char var1, char var2) {
      this.O = new Thread(this, b);
      this.O.setDaemon(true);
      this.O.start();
   }

   private static void l(SourceDataLine var0, float var1) {
      if (var1 != 0.0F) {
         try {
            if (var0.isControlSupported(Type.MASTER_GAIN)) {
               FloatControl var2 = (FloatControl)var0.getControl(Type.MASTER_GAIN);
               float var3 = N(var1, var2.getMinimum(), var2.getMaximum());
               var2.setValue(var3);
            }
         } catch (Throwable var4) {
         }
      }
   }

   public boolean E() {
      return this.c;
   }

   public boolean F() {
      return this.o;
   }

   private static float N(float var0, float var1, float var2) {
      return Math.max(var1, Math.min(var2, var0));
   }

   static {
      a = 110268557049308L;
      b = "OggPlayer-Thread";
      f = new HashMap(13);
      d = new long[]{4083265199998620853L, -7804156243392218070L, -5450520617468154535L, -3206907126933529369L, -2007018012284532477L, -5091787317120875423L, -5512749978260152769L, 2390692812596265243L, -7667186462599823788L, 9069488050007130747L, 5934094339055260779L, -983851958378323050L, 7096782722566740143L, 7599962049540685148L, 2899210336350044887L, -6552084433110130406L, -4140279938191877032L};
      g = 10L;
   }
}
