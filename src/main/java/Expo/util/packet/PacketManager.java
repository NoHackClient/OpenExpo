package Expo.util.packet;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.PacketManagerBinder;
import Expo.event.events.SendPacketEvent;
import Expo.util.ClientUtil;
import Expo.util.MinecraftRef;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayServer;


public class PacketManager implements EventSubscriber {
   public static Set<Packet<INetHandlerPlayServer>> a;
   private static Minecraft T;
   public static Set<Packet<INetHandlerPlayServer>> v;
   public static boolean Z;
   private static long b;
   public static List<Packet<?>> u;

   public static void M(Packet<INetHandlerPlayClient> var0) {
      try {
         if (!ClientUtil.I() || T.isSingleplayer()) {
            return;
         }

         a.add(s(var0));
         var0.processPacket(T.getNetHandler());
      } catch (Throwable var2) {
         Expo.internal.restore.ExpoDiag.attribute(var2, "PacketManager.M/1#0");
      }
   }


   public final void x(long var1, EventBus var3) {
      PacketManagerBinder.N(var3, this);
   }


   public static void M(boolean var0) {
      Z = var0;
   }

   public static void X(Packet<?> var0) {
      try {
         v.add(s(var0));
         T.getNetHandler().addToSendQueue(var0);
      } catch (Throwable var2) {
         Expo.internal.restore.ExpoDiag.attribute(var2, "PacketManager.X/1#0");
      }
   }


   public static void k(Packet<INetHandlerPlayClient> var0) {
      try {
         if (!ClientUtil.I() || T.isSingleplayer()) {
            return;
         }

         var0.processPacket(T.getNetHandler());
      } catch (Throwable var2) {
         Expo.internal.restore.ExpoDiag.attribute(var2, "PacketManager.k/1#0");
      }
   }

   public void onSendPacket(long var1, SendPacketEvent var3) {



      if (ClientUtil.I() && !T.isSingleplayer()) {
         if (Z) {
            OutgoingPacketState.D(0L, var3.B);
            u.add(var3.B);
            var3.I(21307, 3074332907L);
         }
      } else {
         j();
         v.clear();
         a.clear();
      }
   }

   public static void b(Packet<?> var0) {
      try {
         T.getNetHandler().addToSendQueue(var0);
      } catch (Throwable var2) {
         Expo.internal.restore.ExpoDiag.attribute(var2, "PacketManager.b/1#0");
      }
   }

   public static boolean e() {
      return Z;
   }

   public static void j() {
      try {
         if (T.isSingleplayer()) {
            u.clear();
         }

         for (Packet var1 : u) {
            X(var1);
            u.remove(var1);
         }
      } catch (Throwable var2) {
         Expo.internal.restore.ExpoDiag.attribute(var2, "PacketManager.j/0#0");
      }
   }


   public static <H extends INetHandler> Packet<H> s(Packet<?> var0) {
      return (Packet<H>)var0;
   }


   static void $jnicClinit() throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException {
      b = 116394823986266L;
      long var7 = b ^ 109979955980684L;
      Cipher var2;
      byte[] var10003 = new byte[]{(byte)(var7 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var3 = 1; var3 < 8; var3++) {
         var10003[var3] = (byte)(var7 << var3 * 8 >>> 56);
      }

      (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));

      byte[] var6 = var2.doFinal(
         new byte[]{
            (byte)92L,
            (byte)23701L,
            (byte)6067664L,
            (byte)1553322043L,
            (byte)397650443111L,
            (byte)101798513436510L,
            (byte)26060419439746668L,
            (byte)6671467376575147137L
         }
      );
      long var12 = (var6[0] & 255L) << 56
         | (var6[1] & 255L) << 48
         | (var6[2] & 255L) << 40
         | (var6[3] & 255L) << 32
         | (var6[4] & 255L) << 24
         | (var6[5] & 255L) << 16
         | (var6[6] & 255L) << 8
         | var6[7] & 255L;
      long var0 = var12;
      Z = (var0) != 0;
   }

   static {
      try {
         $jnicClinit();
         u = new CopyOnWriteArrayList<>();
         v = new HashSet<>();
         a = new HashSet<>();
      } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var0) {
         throw new RuntimeException(var0);
      }
      T = MinecraftRef.c((byte)0, 0L);
   }
}
