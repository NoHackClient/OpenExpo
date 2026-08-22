package Expo.util;

import Expo.internal.accessor.MinecraftAccessor;
import Expo.module.impl.misc.NameHider;
import Expo.module.impl.misc.NoObfuscation;
import Expo.ui.swing.ConfigManagerWindow;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;

public class ClientUtil {
   private static Map<Integer, Boolean> G;
   private static String[] c;
   private static Minecraft l;
   private static Map d;
   private static long a;
   private static String[] b;

   static {
      a = 131437811654614L;
      G = new HashMap<>();
      l = MinecraftRef.c((byte)0, 0L);
   }

   public static void I(String var0) {
      ConfigManagerWindow.D.add(var0);
   }

   public static void t(long var0, String var2) {
      long var3 = var0 ^ 63955945367536L;
      l.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(BuildInfo.y(var3) + var2));
      ConfigManagerWindow.D.add(BuildInfo.y(var3) + var2);
   }

   public static boolean I(double var0) {
      return var0 == Math.floor(var0);
   }

   public static boolean q() {
      return !I()
         ? false
         : l.theWorld.isAirBlock(new BlockPos(l.thePlayer.posX, l.thePlayer.posY - 1.0, l.thePlayer.posZ));
   }

   public static boolean P() {
      return l.theWorld
         .getCollidingBoundingBoxes(
            l.thePlayer, l.thePlayer.getEntityBoundingBox().offset(l.thePlayer.motionX / 3.0, -1.0, l.thePlayer.motionZ / 3.0)
         )
         .isEmpty();
   }

   public static boolean b(int var0, long var1) {
      int var7 = KeyBindUtil.m(32881896332787L, var0);
      boolean var8 = KeyBindUtil.V(var7, 64165991731362L);
      boolean var9 = var8 && !G.getOrDefault(var7, false);
      G.put(var7, var8);
      return var9;
   }

   public static String replaceString(String var0) {
      if (var0 == null) {
         return var0;
      }

      var0 = NameHider.U(var0);
      return NoObfuscation.f( var0);
   }

   public static void B(String var0) {
      l.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation(var0), 1.0F));
   }

   public static float H(long var0) {
      return MinecraftAccessor.o( l).renderPartialTicks;
   }

   public static void b(String var0) {
      l.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(var0));
      ConfigManagerWindow.D.add(var0);
   }

   public static void e(long var0) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      l.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("------------"));
      ConfigManagerWindow.D.add("------------");
   }

   public static boolean I() {
      return l.thePlayer != null && l.theWorld != null && l.getNetHandler() != null;
   }

   public static boolean d() {
      return l.gameSettings.keyBindJump.isKeyDown();
   }

   public static BlockPos p() {
      return BlockUtil.Z(RaytraceUtil.f());
   }

   public static Timer b(long var0) {
      return MinecraftAccessor.o( l);
   }
}
