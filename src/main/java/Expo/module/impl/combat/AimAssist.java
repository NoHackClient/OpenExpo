package Expo.module.impl.combat;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.AimAssistBinder;
import Expo.event.events.PostTickEvent;
import Expo.event.events.SetAnglesEvent;
import Expo.module.Module;
import Expo.module.impl.visual.Freelook;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.HeaderSetting;
import Expo.setting.settings.ModeSetting;
import Expo.setting.settings.NumberSetting;
import Expo.util.EntityUtil;
import Expo.util.ItemUtil;
import Expo.util.KeyBindUtil;
import Expo.util.MathUtil;
import Expo.util.Pair;
import Expo.util.RaytraceUtil;
import Expo.util.RotationManager;
import Expo.util.RotationUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Comparator;
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;


public class AimAssist extends Module implements EventSubscriber {
   public static BooleanSetting lock;
   public static NumberSetting horizontalSpeed;
   public static BooleanSetting friends;
   private static String[] m;
   public static NumberSetting verticalSpeed;
   public static BooleanSetting breakBlocks;
   public static BooleanSetting bosses;
   public static BooleanSetting players;
   public static BooleanSetting bots;
   public static ModeSetting sort;
   public static BooleanSetting teammates;
   public static BooleanSetting ignoreBehindWall;
   private static String[] n;
   public static BooleanSetting animals;
   private static long k;
   private Pair<Float, Float> g;
   public static BooleanSetting swordOnly;
   private static Map r;
   public static NumberSetting fov;
   public static BooleanSetting enemies;
   public static NumberSetting range;
   public static HeaderSetting targetSettings;
   private static Object[] s;
   public static BooleanSetting mobs;
   private static String[] t;

   public String g(long var1) {
      return String.valueOf(horizontalSpeed.L());
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
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   public void A(long var1) {
      this.g = null;
   }

   private void J(long var1, EntityLivingBase var3) {






      float[] var13;
      if (ignoreBehindWall.c()) {
         var13 = RotationUtil.p(var3, range.L(), 133389424731416L);
      } else {
         var13 = RotationUtil.J(var3, 112421519553468L, range.L());
      }

      float var14 = var13[0];
      float var15 = var13[1];
      float var16 = MathUtil.M(RotationManager.p(), var14) / (20.0F / (lock.c() ? 20.0F : horizontalSpeed.L()));
      float var17 = MathUtil.M(RotationManager.s(), var15) / (20.0F / (lock.c() ? 20.0F : verticalSpeed.L()));
      float var18 = RotationManager.s() + var17;
      if (var18 > 90.0F) {
         var17 = 90.0F - RotationManager.s();
      } else if (var18 < -90.0F) {
         var17 = -90.0F - RotationManager.s();
      }

      RotationManager.V(31564L, -1928233425, RotationManager.p() + var16);
      RotationManager.v(74908232914960L, RotationManager.s() + var17);
      this.g = new Pair<>(RotationManager.p(), RotationManager.s());
   }

   static {
      k = 103167649702968L;
      zkm$clinit();
   }

   public void onSetAngles(SetAnglesEvent var1, long var2) {
      if (lock.c() && !Freelook.c() && this.g != null) {
         var1.t(this.g.a());
         var1.m(this.g.p());
      }
   }

   public void onPostTick(long var1, PostTickEvent var3) {




      if (f.currentScreen != null) {
         this.g = null;
      } else if (!KeyBindUtil.V(f.gameSettings.keyBindAttack.getKeyCode(), 64165991731362L)) {
         this.g = null;
      } else if (breakBlocks.c() && f.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
         this.g = null;
      } else if (swordOnly.c() && !ItemUtil.d()) {
         this.g = null;
      } else {
         EntityLivingBase var12 = this.L(94518397476333L);
         if (var12 == null) {
            this.g = null;
         } else if (!RotationUtil.b(126426268413036L, var12, fov.L())) {
            this.g = null;
         } else {
            this.J(92947820359428L, var12);
         }
      }
   }

   public final void x(long var1, EventBus var3) {
      AimAssistBinder.u(var3, this);
   }

   private EntityLivingBase L(long var1) {



      float var7 = range.L();
      float var8 = fov.L();
      boolean var9 = players.c();
      boolean var10 = mobs.c();
      boolean var11 = animals.c();
      boolean var12 = bosses.c();
      boolean var13 = friends.c();
      boolean var14 = enemies.c();
      boolean var15 = teammates.c();
      boolean var16 = bots.c();
      List<EntityLivingBase> var17 = EntityUtil.K(EntityUtil.F(var7 > 3.0F ? 3.0 : var7, 84864282554303L, var8), var9, 127230230889546L, var10, var11, var12, var13, var14, var15, var16);
      if (var7 > 3.0F && var17.isEmpty()) {
         var17 = EntityUtil.K(EntityUtil.F(var7, 84864282554303L, var8), var9, 127230230889546L, var10, var11, var12, var13, var14, var15, var16);
      }

      if (ignoreBehindWall.c()) {
         var17.removeIf(var1x -> {

            return RaytraceUtil.V(var1x, 140537582766428L, var7);
         });
      }

      if (var17.isEmpty()) {
         return null;
      }

      switch (sort.Y()) {
         case "HEALTH":
            var17.sort(Comparator.comparingDouble(EntityLivingBase::getHealth));
            break;
         case "DISTANCE":
            var17.sort(Comparator.comparingDouble(f.thePlayer::getDistanceToEntity));
            break;
         case "VIEW":
            var17.sort(Comparator.comparingDouble(RotationUtil::g));
            break;
         case "HURT_TIME":
            var17.sort(Comparator.comparingInt(var0 -> var0.hurtTime));
            break;
         case "ARMOR":
            var17.sort(Comparator.comparingInt(EntityLivingBase::getTotalArmorValue));
      }

      return (EntityLivingBase)var17.get(0);
   }

   private static void a() {
      s[0] = "^'\u0014\u000b^\u001cP";
      s[1] = float.class;
      t[1] = "java/lang/Float";
      s[2] = "9W]\r\u007f$%";
      s[3] = boolean.class;
      t[3] = "java/lang/Boolean";
      s[4] = "HK\u000e{3c{";
      s[5] = int.class;
      t[5] = "java/lang/Integer";
      s[6] = long.class;
      t[6] = "java/lang/Long";
      s[7] = "]R\u0010@9g]R\u0007\u001c5hG\u0019\u0007\u0002=k]CJ\u001d1zG^\n\t' xR\u001d,=`W^\n\t";
      s[8] = "6GKW]'%";
      s[9] = "X^r\u000f1yX^eS=vB\u0015cO(yBB(d2d_O\u007fm5f_Uac=cS";
      s[10] = void.class;
      t[10] = "java/lang/Void";
      s[11] = "\u001e[\u0007\u0017M\t\b";
      s[12] = "2\u0014i>\u0001g";
      s[13] = "GI\u0016en1GI\u00019b>]\u0002\u0007%w1]UL\u000em,@X\u001b";
      s[14] = double.class;
      t[14] = "java/lang/Double";
      s[15] = "/\u0002K\u0012\u0013\u001d\u0013";
      s[16] = "pQy\u0000Y\u001enYcO;\u0002iD";
      s[17] = "\u000b+W@Y\u0000\b";
      s[18] = "\u0012\u0002&\u0016\u0010~%\u0015\"\u001c]Z2\u001ex\u0000";
      s[19] = "Q*bS\u0012VZ";
      s[20] = "\u0006c";
      s[21] = "\r'";
      s[22] = "\u0014_d+^T\u001fPud?Z\u0014[q>";
      s[23] = "T\bs!\"a\u0005S\u0001\u0018Y!^Zlec)QW8\\";
      s[24] = "\u0010\u000ec<\u001d\u0002\nG|7%\u001bz\u0017y!J[\u0007\u0002$#\u001efFL}#_\u0002\b\u0015!(%X@\u0019d;O\u0003\u0010\u001arX\u001a\u0005\u0016@x#W\u0006\u001f\u0019\u001e";
      s[25] = "\u0000a|\u0018Mq\\kj\u000e/rl8\u007f\u001cB3V0p\u0011\u0016\n";
      s[26] = "$E|d\b2rF}\u007fs\u001aMJk.\u0001/(A|)JTq\u0013dn\t0?J8es=!\u0018un\u001666\u001f>\u0015\u0019i*Nzo\u0001)-\\\u0007";
      s[27] = "\u0007\u0000 \u0019\u0012\u001dE\flKl\u0000>K0\u001d\u000e\u0005T\u0013m[Sl";
      s[28] = "#\u0004,e\u001bXxByd\"|\u0018\u0005-'L]%Z+~\u0019\"$\tvgXFjP*l\"\u0018u\u0005wuH@(C*\u001c";
      s[29] = "xH^#^;$BH5<;\u0014\u0011]'Qy.\u0019R*\u0005@";
      s[30] = "@Yzp9:N\u0003m:@ p\u0002z0z`\u0014\u000fa`/X\u0019_*r;=\u0012H-9@d@Pjz$*\u0019\fa\u0000-%@\r.fx%\u000fW\u0011";
      s[31] = "A>\"0\u001cT\u00164 4n\u0005\u00012\"[WVEhw79\n|hq7\u0000\u0014A7wnUk";
      s[32] = "x\u001b3nAa.\u00182u:D\u0011\u0017uxWzk\u000f5\u007fE\u0007+\u0010t}SmsM2 :;!\u001e3e^uxB8\u001f\u0000j-\u001f!uX7kBH%W;s\u0014\"}\n}.}rr\u0006ex\u0017*/@8\u0011G%#Xn{\u001fxe\u0005\u0007+\u0010t}SmsM2 :=|A*vPe!\u0007w\u001f\u0000j-\u001f!uX7kBHu\u0007`|\u00002mGgn}";
      s[33] = "%{g\u000e<O~+d\u0018_g\u001b}2\u0017$_\u007f3kK/%%+l\u00182N|?\u007f\u0005_\u001ax->\u0012$W{$gt";
      s[34] = "'x-Nz\u0010{r;X\u0018\u0015K$tCc\u0011/j-\u001fhkrr.M!Qz}#\u0019\u0018T(txFc\u0019+}! ";
      s[35] = "l+N`*F57Z|\u0012SShF!pQ90\u001bg-8";
      s[36] = "\u00195J[\bB\u0017o]\u0011qb)nJ\u001bK\u0018McQK\u001e \u0015oBP\u000bD[6\u001e[qIEdSP\u0014BRc\u0018+\u001c]\u0019a\u001eMI]V;!";
      s[37] = "^Kc\u0004}f\u0005\u001b`\u0012\u001eH`M6\u001dev\u0004\u0003oAn\f^\u001bh\u0012sg\u0007\u000f{\u000f\u001e";
      s[38] = "S7i~:4]m~4C<ca2m8,\u0007/k13VR624{2_-baC?\u000ffpu&4\u0018a;\u000ey;_?kd!f\u0019b\u0002";
      s[39] = "\u0006U&G\u0014lZ_0QvIj\t\u007fJ\rm\u000eG&\u0016\u0006\u0017V\rtG\t*\t\u000b-\u0012v.\u0000_\"\u0010L&\u000fRv)It\u0006\t)R\u0004w\u000fPO";
   }

   public AimAssist(long var1) {
      super(((k ^ (var1)) ^ 101115674713218L));
      // add code
      this.declare("AimAssist", Category.Combat, "Help you aim BETTER when you click");
      var1 = k ^ var1;
      this.g = null;
   }
   private static void zkm$clinit() {
      try {
         s = new Object[40];
         t = new String[40];
         a();
         r = new HashMap(13);
         long var0 = k ^ 130649753332591L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var9 = new String[5];
         int var7 = 0;
         String var6 = "=ê\u0016à\u0013\u0082:\u0081ÛO\u0081\u008a\u0010;gÃ\u001däH¤×\u0018\b;Þ×\f¿snÀd\u0010ÿW\u0094g'\u008f@\u0080\u0098éï\b`À[ù\u0010R¬QLÖa\u0002Á«\u000fóC\u0091×-2";
         int var8 = "=ê\u0016à\u0013\u0082:\u0081ÛO\u0081\u008a\u0010;gÃ\u001däH¤×\u0018\b;Þ×\f¿snÀd\u0010ÿW\u0094g'\u008f@\u0080\u0098éï\b`À[ù\u0010R¬QLÖa\u0002Á«\u000fóC\u0091×-2"
            .length();
         char var5 = ' ';
         int var13 = -1;

         label31:
         while (true) {
            String var14 = var6.substring(++var13, var13 + var5);
            byte var10001 = -1;

            while (true) {
               byte[] var10 = var2.doFinal(var14.getBytes("ISO-8859-1"));
               String var20 = b(var10).intern();
               switch (var10001) {
                  case 0:
                     var9[var7++] = var20;
                     if ((var13 += var5) >= var8) {
                        m = var9;
                        n = new String[5];
                        return;
                     }

                     var5 = var6.charAt(var13);
                     break;
                  default:
                     var9[var7++] = var20;
                     if ((var13 += var5) < var8) {
                        var5 = var6.charAt(var13);
                        continue label31;
                     }

                     var6 = "m\u009a×U\u008cã©61\u0012«³\u007f\"äQ \u009b0(\u007fìÖ\u007ftt¸\to\u0091Ï'\u0085Ûçm\u0092\t\u0081\u009a)ïrG-{ÇÄ\u0091";
                     var8 = "m\u009a×U\u008cã©61\u0012«³\u007f\"äQ \u009b0(\u007fìÖ\u007ftt¸\to\u0091Ï'\u0085Ûçm\u0092\t\u0081\u009a)ïrG-{ÇÄ\u0091".length();
                     var5 = 16;
                     var13 = -1;
               }

               var14 = var6.substring(++var13, var13 + var5);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var11) {
         throw new RuntimeException(var11);
      }
   }
   static {
      // add code
      lock = new BooleanSetting("Lock", false);
      breakBlocks = new BooleanSetting("Break-blocks", true);
      ignoreBehindWall = new BooleanSetting("Ignore-behind-wall", true);
      swordOnly = new BooleanSetting("Sword-only", false);
      players = new BooleanSetting("Players", true);
      mobs = new BooleanSetting("Mobs", false);
      animals = new BooleanSetting("Animals", false);
      bosses = new BooleanSetting("Bosses", false);
      friends = new BooleanSetting("Friends", false);
      enemies = new BooleanSetting("Enemies", true);
      teammates = new BooleanSetting("Teammates", false);
      bots = new BooleanSetting("Bots", false);
   }
   static {
      // add code
      range = new NumberSetting("Range", 6.0F, 0.1F, 10.0F, 0.1F);
      fov = new NumberSetting("FOV", 180.0F, 1.0F, 360.0F, 1.0F);
      horizontalSpeed = new NumberSetting("Horizontal-speed", 15.0F, 1.0F, 20.0F, 0.1F);
      verticalSpeed = new NumberSetting("Vertical-speed", 5.0F, 1.0F, 20.0F, 0.1F);
   }
   static {
      // add code
      sort = new ModeSetting("Sort", "DISTANCE", "HEALTH", "VIEW", "HURT_TIME", "ARMOR");
   }
   static {
      // add code
      targetSettings = new HeaderSetting("Target settings");
   }
}
