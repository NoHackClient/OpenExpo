package Expo.util.packet;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.IncomingPacketHoldBinder;
import Expo.event.events.ReceivePacketEvent;
import Expo.event.events.SendPacketEvent;
import Expo.util.ClientUtil;
import Expo.util.MinecraftRef;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.util.Vec3;











public class IncomingPacketHold implements EventSubscriber {
   private static Object[] b;
   private static Minecraft h;
   private static CopyOnWriteArrayList<Packet<INetHandlerPlayClient>> U;
   private static boolean g;
   public static Map<Integer, Vec3> i;
   private static String[] c;
   private static long a;

   static {
      a = 16363778365439L;
      // add code
      zkm$clinit();
      h = MinecraftRef.c((byte)0, 0L);
   }

   private static void zkm$clinit() {
      try {
         long var7 = a ^ 55631520864713L;
         b = new Object[7];
         c = new String[7];
         a();
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var7 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var7 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));

         byte[] var6 = var2.doFinal(
            new byte[]{
               (byte)136L,
               (byte)34950L,
               (byte)8947342L,
               (byte)2290519662L,
               (byte)586373033638L,
               (byte)150111496611329L,
               (byte)38428543132500241L,
               (byte)-8609037031789489753L
            }
         );
         long var13 = (var6[0] & 255L) << 56
            | (var6[1] & 255L) << 48
            | (var6[2] & 255L) << 40
            | (var6[3] & 255L) << 32
            | (var6[4] & 255L) << 24
            | (var6[5] & 255L) << 16
            | (var6[6] & 255L) << 8
            | var6[7] & 255L;
         long var0 = var13;
         U = new CopyOnWriteArrayList<>();
         i = new HashMap<>();
         g = (var0) != 0;
      } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var12) {
         throw new RuntimeException(var12);
      }
   }

   public void onReceivePacket(ReceivePacketEvent var1, long var2) {



      if (ClientUtil.I() && !h.isSingleplayer()) {
         if (g && this.K(var1.d)) {
            U.add((Packet<INetHandlerPlayClient>)var1.d);
            var1.I(21307, 3074332907L);
         }
      } else {
         s();
      }
   }

   public static void s() {
      U.clear();
      i.clear();
   }

   public void onSendPacket(SendPacketEvent var1) {
      if (ClientUtil.I() && !h.isSingleplayer()) {
         if (var1.B instanceof C00Handshake
            || var1.B instanceof C00PacketLoginStart
            || var1.B instanceof C00PacketServerQuery
            || var1.B instanceof C01PacketPing
            || var1.B instanceof C01PacketEncryptionResponse) {
            m();
         }
      } else {
         s();
      }
   }

   public static void m() {
      if (ClientUtil.I() && !h.isSingleplayer()) {
         for (Packet var1 : U) {
            PacketManager.M(var1);
            U.remove(var1);
         }

         i.clear();
      } else {
         s();
      }
   }

   public boolean K(Packet<?> var1) {
      if (!g) {
         return false;
      }

      if (var1 instanceof S00PacketKeepAlive) {
         return false;
      }

      if (var1 instanceof S01PacketJoinGame || var1 instanceof S07PacketRespawn) {
         m();
         return false;
      }

      if (!(var1 instanceof S19PacketEntityStatus)) {
         return true;
      }

      S19PacketEntityStatus var2 = (S19PacketEntityStatus)var1;
      Entity var3 = var2.getEntity(h.theWorld);
      return var3 == null || var3.equals(h.thePlayer) && var2.getOpCode() == 2;
   }

   public static CopyOnWriteArrayList<Packet<INetHandlerPlayClient>> p() {
      return U;
   }

   public static boolean r() {
      return g;
   }

   public static void X(boolean var0) {
      g = var0;
   }

   public final void x(long var1, EventBus var3) {
      IncomingPacketHoldBinder.z(var3, this);
   }

   private static void a() {
      b[0] = ".[O03s4";
      b[1] = "i\u0002&]j\u001c^\u0015\"W'8I\u001exK";
      b[2] = long.class;
      c[2] = "java/lang/Long";
      b[3] = "\u0006w\u0017mhK:";
      b[4] = void.class;
      c[4] = "java/lang/Void";
      b[5] = "xDxwm\\sKi8\fRx@mb";
      b[6] = "\\F%\\\u001e\u0016GS~?\u001dh\u0005\u0011wU\u0006\u0001\u0003EhDoQ\\Ks@\u0006W\u0002T~?U\u000e\u0007Sh\u0000\u000f\u0019\u0006W\u0018\u0004\u0001\n\u0001E\"A\u0005\u0014\u0003)";
   }

}
