package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.enums.MegaWallsClass;
import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.MegaWallsDetectorBinder;
import Expo.event.events.PlayerGetNameEvent;
import Expo.event.events.PostTickEvent;
import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.util.ClientUtil;
import Expo.util.HypixelGameState;
import Expo.util.ScoreboardUtil;
import Expo.util.TeamPrefixUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.IScoreObjectiveCriteria.EnumRenderType;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSettings.GameType;


public class MegaWallsDetector extends Module implements EventSubscriber {
   private static Object[] t;
   private final Set<String> E;
   public static BooleanSetting phoenixDetector;
   public static BooleanSetting phoenixChatNotify;
   public static BooleanSetting phoenixIconsInTab;
   private final Set<String> Y;
   private static int p;
   private static long b;
   private final Map<String, Integer> F;
   public static BooleanSetting potionDetector;
   private final Map<String, Integer> h;
   private final Map<String, Integer> o;
   private static int B;
   public static BooleanSetting potionCountsInTab;
   private static String[] u;
   public static BooleanSetting potionChatNotify;
   private static String[] e;
   private static int a;
   private static Map s;
   private static Map g;
   private final Map<String, Integer> d;

   private String n(String var1, long var2) {
      var2 = b ^ var2;
      int var6 = (int)((var2 ^ 81918644165420L) << 48 >>> 48);
      if (f != null && f.getNetHandler() != null) {
         NetworkPlayerInfo var9 = f.getNetHandler().getPlayerInfo(var1);
         if (var9 == null) {
            return "";
         }

         ScorePlayerTeam var10 = var9.getPlayerTeam();
         if (var10 instanceof ScorePlayerTeam) {
            String var11 = var10.getColorPrefix();
            String var12 = ScoreboardUtil.h(var11,0L);
            if (!var12.isEmpty()) {
               return var12;
            }
         }

         return ScoreboardUtil.Z( (short)var6, ScorePlayerTeam.formatPlayerName(var10, var9.getGameProfile().getName()), var9.getGameProfile().getName());
      } else {
         return "";
      }
   }

   private void w$r2() {
      this.F.clear();
      this.o.clear();
   }

   public void i(long var1) {
      this.S$r1();
   }

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {


      if (!TeamPrefixUtil.i()) {
         this.S$r1();
      } else {
         if (potionDetector != null && potionDetector.c()) {
            this.s((char)0, 89532643044307L);
         } else {
            this.w$r2();
         }

         if (phoenixDetector != null && phoenixDetector.c()) {
            this.c();
         } else {
            this.D$r2();
         }
      }
   }

   private int t(String var1) {
      EntityPlayer var2 = f.theWorld.getPlayerEntityByName(var1);
      return var2 != null && !var2.isDead ? Math.round(var2.getHealth()) : this.V(var1);
   }

   private void S$r1() {
      this.w$r2();
      this.D$r2();
   }

   private boolean z(int var1) {
      return var1 == 44
         || var1 == 40
         || var1 == 32
         || var1 == 24;
   }

   public final void x(long var1, EventBus var3) {
      int var4 = (int)((var1 ^ 26104167962768L) >>> 32);
      MegaWallsDetectorBinder.X(var3, var4, this);
   }



   private boolean m(long var1, String var3) {


      MegaWallsClass var6 = TeamPrefixUtil.F(22611545248530L, var3);
      if (var6 == MegaWallsClass.PHOENIX) {
         this.E.add(var3);
         return true;
      } else {
         return this.E.contains(var3);
      }
   }

   private void clear() {
      this.d.clear();
      this.h.clear();
      this.E.clear();
      this.Y.clear();
   }

   public MegaWallsDetector(long var1) {
      super(((b ^ (var1)) ^ 54051925699248L));
      // add code
      this.declare("MegaWallsDetector", Category.Visual_utility, "Detect potion heals and phoenix resurrection from tab health");
      var1 = b ^ var1;
      this.F = new HashMap<>();
      this.o = new HashMap<>();
      this.d = new HashMap<>();
      this.h = new HashMap<>();
      this.E = new HashSet<>();
      this.Y = new HashSet<>();
   }

   private void remove(String var1) {
      this.d.remove(var1);
      this.h.remove(var1);
      this.E.remove(var1);
      this.Y.remove(var1);
   }

   public void A(long var1) {
      this.S$r1();
   }

   private void c() {



      this.B();

      for (NetworkPlayerInfo var12 : f.getNetHandler().getPlayerInfoMap()) {
         if (var12 != null && var12.getGameProfile() != null && var12.getGameType() != GameType.SPECTATOR) {
            String var13 = var12.getGameProfile().getName();
            if (!this.c(var13, 62621913985836L)) {
               this.remove(var13);
            } else {
               int var14 = this.t(var13);
               if (var14 <= 0) {
                  this.remove(var13);
               } else if (!this.Y.contains(var13) && this.m(139346002047723L, var13)) {
                  Integer var15 = this.d.get(var13);
                  boolean var16 = this.h.getOrDefault(var13, 0) > 0;
                  if (var15 != null && var16 && var14 > var15 && this.z(var14)) {
                     this.Y.add(var13);
                     if (phoenixChatNotify != null && phoenixChatNotify.c()) {
                        ClientUtil.t(48081174263320L, TeamPrefixUtil.n(var13) + " §eresurrected");
                        f.thePlayer.playSound("note.pling", 1.0F, 2.0F);
                     }
                  }

                  if (!this.d.containsKey(var13)) {
                     this.d.put(var13, var14);
                     this.h.remove(var13);
                  } else {
                     if (var14 <= 6) {
                        this.h.put(var13, 12);
                     }

                     this.d.put(var13, var14);
                  }
               }
            }
         }
      }
   }

   public void onPlayerGetName(PlayerGetNameEvent var1, long var2, short var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      String var7 = var1.u.getGameProfile().getName();
      if (var7 != null && !var7.isEmpty()) {
         if (phoenixIconsInTab != null && phoenixIconsInTab.c() && this.Y.contains(var7)) {
            var1.d("\u00a7b\u00a7l\u2726\u00a7r");
         }

         if (potionCountsInTab != null && potionCountsInTab.c()) {
            int var8 = this.o.getOrDefault(var7, 0);
            if (var8 > 0) {
               var1.N(" \u00a77(\u00a7d" + var8 + "\u00a77)");
            }
         }
      }
   }

   private boolean c(String var1, long var2) {
      long var4 = var2 ^ 139268599442625L;
      String var6 = this.n(var1, var4);
      return !var6.isEmpty() && !HypixelGameState.L().s(var6);
   }

   private int V(String var1) {
      try {
         Scoreboard var2 = f.theWorld.getScoreboard();
         ScoreObjective var3 = var2.getObjectiveInDisplaySlot(0);
         if (var3 != null && var3.getRenderType() != EnumRenderType.HEARTS) {
            return var2.getValueFromObjective(var1, var3).getScorePoints();
         }
      } catch (Exception var4) {
         Expo.internal.restore.ExpoDiag.attribute(var4, "MegaWallsDetector.V/1#0");
      }

      return 0;
   }


   private void s(char var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      long var4 = ((long)var1 << 48 | 89532643044307L) ^ b;
      long var8 = var4 ^ 138615916042107L;
      long var10 = var4 ^ 57293966623245L;

      for (NetworkPlayerInfo var13 : f.getNetHandler().getPlayerInfoMap()) {
         if (var13 != null && var13.getGameProfile() != null && var13.getGameType() != GameType.SPECTATOR) {
            String var14 = var13.getGameProfile().getName();
            if (!this.c(var14, var8)) {
               this.F.remove(var14);
            } else {
               int var15 = this.t(var14);
               if (var15 <= 0) {
                  this.F.remove(var14);
               } else {
                  Integer var16 = this.F.get(var14);
                  MegaWallsClass var17 = MegaWallsClass.s(var14, var10);
                  if (var16 != null && var17 != null && var17.U()) {
                     if (var16 <= 0) {
                        this.F.put(var14, var15);
                        continue;
                     }

                     int var18 = var15 - var16;
                     int var19 = var17.healthPotionAmount * 2 - 2;
                     int var20 = var17.healthPotionAmount * 2 + 2;
                     if (var18 >= var19 && var18 <= var20) {
                        this.w(var14, var17.healthPotionAmount);
                     }
                  }

                  this.F.put(var14, var15);
               }
            }
         }
      }
   }

   private void w(String var3, int var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {

      this.o.put(var3, this.o.getOrDefault(var3, 0) + 1);
      if (potionChatNotify != null && potionChatNotify.c()) {
         EnumChatFormatting var7 = var4 == 10 ? EnumChatFormatting.LIGHT_PURPLE : EnumChatFormatting.AQUA;
         ClientUtil.t(
            48081174263320L,
            "\u00a7f" + TeamPrefixUtil.n(var3) + " \u00a7edrank a " + var7 + var4 + "\u2764\u00a7epotion"
         );
      }
   }

   static {
      b = 25888821202820L;
      t = new Object[8];
      u = new String[8];
      g = new HashMap(13);
      e = new String[8];
      s = new HashMap(13);
      a = 12;
      p = 6;
      B = 60;
   }


   private void B() {
      if (!this.h.isEmpty()) {
         ArrayList var1 = new ArrayList();

         for (Entry var3 : this.h.entrySet()) {
            int var4 = (Integer)var3.getValue() - 1;
            if (var4 <= 0) {
               var1.add(var3.getKey());
            } else {
               var3.setValue(var4);
            }
         }

         for (String var6 : (Iterable<String>)(var1)) {
            this.h.remove(var6);
         }
      }
   }

   private void D$r2() {
      this.clear();
   }

   private static void a() {
      t[0] = "\u000f\u0018E\b\u0001\u0007!";
      t[1] = "{$d-\u0018 L3`'U\u0004[8:;";
      t[2] = int.class;
      u[2] = "java/lang/Integer";
      t[3] = short.class;
      u[3] = "java/lang/Short";
      t[4] = " bkC[\u0003\f";
      t[5] = void.class;
      u[5] = "java/lang/Void";
      t[6] = "\u0010]5\u001b X\u001bR$TAV\u0010Y \u000e";
      t[7] = "xi6$\u000b\u0018+yhVjvx<4oT\u000b+hng:O&<i7G\u001fy6hV\u0000\u001d>?7:\n\u001f*zPo]Lxg-?\u0002Fy\u0006j)\u0004\u000e9\u007f(:\u000b\u0004A= 9\u0004L}g :Jv";
   }
   static {
      // add code
      potionCountsInTab = new BooleanSetting("Potion-counts-in-tab", true);
      phoenixChatNotify = new BooleanSetting("Phoenix-chat-notify", true);
      potionDetector = new BooleanSetting("Potion-detector", true);
      potionChatNotify = new BooleanSetting("Potion-chat-notify", true);
      phoenixIconsInTab = new BooleanSetting("Phoenix-icons-in-tab", true);
      phoenixDetector = new BooleanSetting("Phoenix-detector", true);
   }
}
