package Expo.module.impl.visual_utility;

import Expo.module.Category;

import Expo.event.EventBus;
import Expo.event.EventSubscriber;
import Expo.event.binder.FKCounterBinder;
import Expo.event.events.HandleChatEvent;
import Expo.event.events.PostTickEvent;
import Expo.event.events.Render2DEvent;
import Expo.module.Module;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.HypixelGameState;
import Expo.util.ScoreboardUtil;
import Expo.util.TeamPrefixUtil;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;

public class FKCounter extends Module implements EventSubscriber {
   private static Map t;
   private final List<Map<String, Integer>> N;
   private static String[] c;
   private String B;
   private static int x;
   private static Pattern[] I;
   private int n;
   private static long d;
   private final Map<String, Integer> g;
   private final String[] J;
   public static PercentageSetting backgroundOpacity;
   private boolean Y;
   private final int[] D;
   private static long[] r;
   private static Map o;
   public static NumberSetting scale;
   private static String[] v;
   private static String[] R;
   private String p;
   private static Object[] u;
   private static String[] E;
   public static NumberSetting offsetX;
   public static NumberSetting offsetY;
   private final List<FKCounterTeamEntry> e;
   private static String[] k;
   private static String[] m;
   private final Set<String> y;

   private boolean H(int var1) {
      return var1 < 0 || var1 >= 4;
   }

   public String g(long var1) {
      return TeamPrefixUtil.z() ? "DM" : "WAIT";
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

   private String m(int var1) {
      return "§" + this.J[var1];
   }

   private void merge(String var1, String var2) {
      int var3 = this.equalsIgnoreCase(var2);
      if (!this.H(var3)) {
         if (!this.y.contains(var1)) {
            this.N.get(var3).merge(var1, 1, Integer::sum);
            this.g.merge(var1, 1, Integer::sum);
            this.D[var3]++;
         }
      }
   }

   private boolean v() {
      return this.E();
   }

   private boolean E() {
      return this.p != null && TeamPrefixUtil.i() && TeamPrefixUtil.z();
   }

   private static void a() {
      u[0] = "\u001c\u0000;\u001atj>";
      u[1] = "\u000e\u0001\u0002+\u0001\r9\u0016\u0006!L).\u001d\\=";
      u[2] = "\u0005V7P]R\t";
      u[3] = long.class;
      v[3] = "java/lang/Long";
      u[4] = void.class;
      v[4] = "java/lang/Void";
      u[5] = "(\u001bBXo\u0003#\u0014S\u0017\u000e\r(\u001fWM";
      u[6] = "\nUCE|\u0002PVB%g9DEYGaB\u000b\\A%%\u0001E\bL\u001cqTUG(\u001f\u007f\bS\\R^.^\t8\u0012W#\u0002\t\u0001KXcK4";
   }

   public void onHandleChat(HandleChatEvent var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (this.E()) {
         String var8 = var1.A.getUnformattedText();
         if (var8 != null && !var8.isEmpty()) {
            String var9 = var1.A.getFormattedText();

            for (int var10 = 0; var10 < I.length; var10++) {
               Matcher var11 = I[var10].matcher(var8);
               if (var11.matches()) {
                  if (var11.groupCount() == 2) {
                     this.b(var9, 2946736501650L, var11.group(1), var11.group(2));
                     return;
                  }

                  if (var11.groupCount() == 1) {
                     this.T(21221622971157L, var9, var11.group(1));
                     return;
                  }
               }
            }
         }
      }
   }

   static {
      d = 28874700161329L;
      zkm$clinit();
   }

   public final void x(long var1, EventBus var3) {
      FKCounterBinder.p(var3, this);
   }

   private void T(long var1, String var3, String var4) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      String var10 = ScoreboardUtil.Z( (short)35653, var3, var4);
      if (!var10.isEmpty()) {
         if (this.i(var4, var10) != -1) {
            this.v(0L);
         }
      }
   }

   public FKCounter(long var1) {
      super(((d ^ (var1)) ^ 99314281517800L));
      this.declare("FKCounter", Category.Visual_utility, "Show MegaWalls deathmatch final kills per team");
      var1 = d ^ var1;
      this.D = new int[4];
      this.J = Arrays.copyOf(c, c.length);
      this.y = new HashSet<>();
      this.e = new ArrayList<>(4);
      this.g = new HashMap<>();
      this.N = Arrays.asList(new HashMap(), new HashMap(), new HashMap(), new HashMap());
      this.B = "";
   }

   private void M(long var1) {
      List var8 = ScoreboardUtil.b(0L);

      for (int var9 = 0; var9 < var8.size(); var9++) {
         String var10 = (String)var8.get(var9);

         for (int var11 = 0; var11 < 4; var11++) {
            if (var10.contains(R[var11])) {
               String var12 = ScoreboardUtil.Z( (short)35653, var10, R[var11]);
               if (!var12.isEmpty()) {
                  this.J[var11] = var12;
               }
            }
         }
      }
   }

   private int equalsIgnoreCase(String var1) {
      for (int var2 = 0; var2 < 4; var2++) {
         if (this.J[var2].equalsIgnoreCase(var1)) {
            return var2;
         }
      }

      return -1;
   }

   private void b(String var1, long var2, String var4, String var5) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      String var11 = ScoreboardUtil.Z( (short)35653, var1, var4);
      String var12 = ScoreboardUtil.Z( (short)35653, var1.replaceFirst(var4, ""), var5);
      if (!var11.isEmpty() && !var12.isEmpty()) {
         if (this.i(var4, var11) != -1) {
            this.merge(var5, var12);
            this.v(0L);
         }
      }
   }

   public void onPostTick(long var1, PostTickEvent var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      if (!TeamPrefixUtil.i()) {
         this.g((byte)0, 66507525071674L);
      } else {
         String var15 = HypixelGameState.L().c();
         if (var15 == null) {
            this.g((byte)0, 66507525071674L);
         } else {
            if (!var15.equals(this.p)) {
               this.arraycopy( var15);
            }

            this.M(106623492124055L);
            boolean var16 = TeamPrefixUtil.z();
            if (var16 && !this.Y) {
               this.clear(0L);
            } else if (!var16 && this.Y) {
               this.clear(0L);
            }

            this.Y = var16;
            this.v(0L);
         }
      }
   }

   private int i(String var1, String var2) {
      int var3 = this.equalsIgnoreCase(var2);
      if (this.H(var3)) {
         return -1;
      }

      if (this.y.contains(var1)) {
         return -1;
      }

      Integer var4 = this.N.get(var3).remove(var1);
      int var5 = var4 == null ? 0 : var4;
      if (var5 > 0) {
         this.D[var3] = Math.max(0, this.D[var3] - var5);
      }

      this.g.remove(var1);
      this.y.add(var1);
      return var5;
   }

   private void arraycopy( String var3) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.p = var3;
      Arrays.fill(this.J, null);
      System.arraycopy(c, 0, this.J, 0, c.length);
      this.clear(0L);
      this.Y = TeamPrefixUtil.z();
   }

   private void v(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.e.clear();

      for (int var3 = 0; var3 < 4; var3++) {
         this.e.add(new FKCounterTeamEntry(var3, this.D[var3], null));
      }

      this.e.sort(Comparator.<FKCounterTeamEntry>comparingInt(var0 -> FKCounterTeamEntry.h(var0)).reversed().thenComparingInt(var0 -> FKCounterTeamEntry.f(var0)));
      StringBuilder var7 = new StringBuilder();

      for (int var4 = 0; var4 < this.e.size(); var4++) {
         FKCounterTeamEntry var5 = this.e.get(var4);
         if (var4 > 0) {
            var7.append(EnumChatFormatting.GRAY).append(" / ");
         }

         var7.append(this.m(FKCounterTeamEntry.p(var5))).append(FKCounterTeamEntry.O(var5));
      }

      this.B = var7.toString();
      this.n = f.fontRendererObj == null ? 0 : f.fontRendererObj.getStringWidth(this.B);
   }

   public void A(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var3 = (int)((var1 ^ 116264514650047L) >>> 56);
      long var4 = (var1 ^ 116264514650047L) << 8 >>> 8;
      this.g((byte)var3, var4);
   }

   public void i(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      int var3 = (int)((var1 ^ 48526503714474L) >>> 56);
      long var4 = (var1 ^ 48526503714474L) << 8 >>> 8;
      this.g((byte)var3, var4);
   }

   private void g(byte var1, long var2) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      this.p = null;
      this.Y = false;
      Arrays.fill(this.J, null);
      System.arraycopy(c, 0, this.J, 0, c.length);
      this.clear(0L);
      this.B = "";
      this.n = 0;
   }

   private void clear(long var1) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
      Arrays.fill(this.D, 0);
      this.g.clear();

      for (int var5 = 0; var5 < 4; var5++) {
         this.N.get(var5).clear();
      }

      this.y.clear();
      this.v(0L);
   }

   public void onRender2D(Render2DEvent var1, long var2) {
      if (this.v() && !this.B.isEmpty()) {
         float var4 = scale.L();
         float var5 = offsetX.L();
         float var6 = offsetY.L();
         int var7 = backgroundOpacity.k() * 255 / 100;
         int var8 = f.fontRendererObj.FONT_HEIGHT;
         GlStateManager.pushMatrix();
         GlStateManager.translate(var5, var6, 0.0F);
         GlStateManager.scale(var4, var4, 1.0F);
         if (var7 > 0) {
            Gui.drawRect(
               -2, -2, this.n + 2, var8 + 1, new Color(0, 0, 0, var7).getRGB()
            );
         }

         f.fontRendererObj.drawStringWithShadow(this.B, 0.0F, 0.0F, 16777215);
         GlStateManager.popMatrix();
      }
   }
   private static void zkm$clinit() {
      try {
         long var20 = d ^ 132487852058117L;
         u = new Object[7];
         v = new String[7];
         a();
         o = new HashMap(13);
         Cipher var11;
         byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var12 = 1; var12 < 8; var12++) {
            var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
         }

         (var11 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var18 = new String[89];
         int var16 = 0;
         String var15 = "Éä¯ð\u009f\u0095\u0000o/JëV7ÌäJEWÓïN(\u0088\nBIäþ\u001cÖÃh\u0004òCý.\u00829J\u0089\u0088F^\u009d=×\u008e\u0099v¾ý\u0083ÑKú\u0084H\u008f\u000e\u000f\u008eÚ²\btÎ\u009a\u009d\u001eÕ=chßP\u007fÖ\u0098÷\bæo\u009e5¸d!&\u0011ò°¾ËÜËhÀÛe!4\u0098fÖ½x\u001a\u008c6\u009cb@\u0003P¦æßf\u008a\u00983\u0017\u0019ÖÃ»\u000e\u0000\u0010Ý\u0093JòÁ½x5O8D³¶\u0010ÒF\u009e¨Ù{ æF¶\u0096òRùÕðlÍ\u001f\u0016Ï~)$ô7\u0085\u0080Â§¼Õ\u0091Ôí\u0088ë ¹\faµdäJi\u0005¨:\u009d×\u0018\u001dìâdì0\u008a\u0082W½\u0092cBÂOØB%\u009e3[\u0004-XEyø\u0000u\u009bk \u0081\u0093P2×*\u007ftÙÔRy\u008eáìQ 7#)\u0000JX¼HR\u001b\u0090\u001cpÎ¤9È\u001b\u008b\u0010\tbSëË\u000bäè3»L\u000e.\u0000ó+\u0084ÞM\u009b\u0095Ç\u0096´Dè\u001bØ'\u00ad*\u0014\u0098zSÙ\u0010Ö[a\u008cÒÙ\u0080)Ý£[\u009d¤Nüïî½n\u0093\u001abRúú\u009eÖåDJ\u008c²X,¬EkHÙbVqj\u0091ª\u000e\u0088¯×à/\u0001¹ÔP3O\u0014\u008eÇ\u0092Ñr\u0090\u001dtj>b¯\u009f¨ë7\u001b\u0004\u009e_\u0099åUuF÷u\u009cx¢¬\u008a¸â\u0014Lk;E\u0002×ÉÔ%Üç>\u009dlz/\u0000\u0010E\u0086_ßÔ\u0080b\u0093Z\u0093\u009c\u0002ïc\u0013\u0017\u0010*úÀ\\h\u000f%\u0082&\u0012Ø8-ûÇ\u0019P°|R\u0085\u001eî\u009c\u0003>\u0019:µ\u009b?\u008cª\u0016xì\u0016¥Yµ\f¶gb¼å\nã/}\u009a´e\u008b\u0017\u0094\u0011ZÚ\br\u009e\u0089nÒ.\u001f\u0089\u0082\u009c\u0006U'ÓÀ%õP\u0091\u0015\u001cvä«¹è\u0095cÀ\u00ad²£\u0011Íd\u0089Lx\fpmQ@\u008e3?Pg½û\u0089¥\tN\u001eB\u0082Fwj¿zí\u000f>NTÒä¶¥<(b\u0090f\u0093:\u001c\u0011R÷¦NTt9\u00989¸©\\Zbë\u0006ÞÁ\u0090\u001c@raÁ]ÿ\u0000^ì\u008b]¶\"\u0094ÑævÖÜ\u0003õ\u0019\u0098+9[p\u0080dUÅèêÆ¾©A^\u009f\u001e\u00182¨\u0088Ý\b\u0004¦*k¥\u0080E[NÐº\u0093@\u0082\u0098ªýÙp\u008c¨1ý¸îÎé|_[F¿\u0014\u0086i\u0007ÉGWÜm2\u0081¶ï\u0002Ì¦¡Þ«\bØ=¯ª\u008bX?Ù\u0080ß¢Ø\u0097ËÓ\u001c¹\u0096*f\u0014Ùç\u0094¥PpÝ\u009f\u001auµ\u0095>ÿ_½\u0019\u009eÎ¡Øi\u0099¦ï*-¯ \u000eÎÃ2]øØÝíÝ3\u0086\"ÙÉà§ã£kÖ¡ø®ª±çG¿\u0013¼wz\u0085L\u0010\f \u001f=¥\u001b\u008dP\u0082µûÔ\u0083t\b~\tåÎ\u008fH£]§ÂÇú\nØt\u001câñFÊü\u000eb\u001f\u0010$\u0096Â\u008f1Ø]=\u000fýÅÀ\f\u0002p\u0097\u0089\u0001\n£*ì\u00adzd \u0087Å\u008bÅ_Ú\tD\tR\u0094ãÇ÷É§ìÅÒ\u0017ñpMx \u0002ÜHw¢ õ\u0094j.ôu¤\u0010v×U¬^\u0003\u0084Ì\u0018!ÊçÉxG\u009bþdTú¿³\u0080:Q\u0086`>\u001dãÞ£nôë-5¨Ú\u0093;d\u0085iTtË)²_)THX\u0012ìC\u0098|wmX\u009e\u0089(UýqÑÖ§\nw{d\u0019þ7\u0091\u009c\\°àÜ^èè\u0085C\"m´Ø\u0007\fon\u0007RÛø\u0092\u00929I¸>¯#G}ÎÔqH\u0097¶\u009fº©èÏxz Þø\u001eçæ©X\\\u0095ø\u0007¢³\u0085åz\u008dt\u0004Q\u0018ïÂ\u0015eHõ!¹0d\u0014Òô\u0099÷³YNl\u000esÃ\u009c¤lR¡\u001egüûL\u0002\u0082\u0084IÊ\u0088òZ$\u009aiW\u000eàì¦V¥t°uö\u0007Vq]\u0002d§\u0012ý\u0082b÷t\u0091!|o³^£\u009f?\u0096XÇá\u00938\u0014ÖÃ³Ö\u008c1\u0012?_û\u009aà/Üþ°i\u009aSìR*\u00042ÖL\u0081Ð\u0095raü`f¦;ÜyÆ,Ç3¢ ù \u0090ÞãBù1ù©·Æ\u009d&XÒ|«F\u0003Êô\u008fr®\u008f\u009c(÷X7{Î\fÐyÃ\u001cN`\u0003ÁLtÚÐ\u001eLy7&\u0015\u0006>\u008a\u001fÒ=ñ\"eW7â\u009a©L3Å\\'¾ÄÆËÜ\u0088§ê\\/Î`»ÞøÿA\u008dQzl\u0006\u009esE«»\u001d\u0098íº\u0005t\u0098ÒF]£ -\"Ûp_\u0004ìcôE¯UT*ÇÍ!ËyÞ\"W3\u0007øÎXöíân¹â\u0013³¢ËmZ~×5Ïy`/¬Ç}.\u009fg÷ã+\"\rÁ\u0081q\u001aibüL¨ f\u0012\u009eY»d¸\u0095\u0095×¢¦~ÑuÒ9\u0003ûÆ=9Wå\u00056ÖRq¦î\u0086\u009cÔ¯\u0091c^c`Î¬ß\u009dpâw,XPÑx¹<\u0015\u0017\u001fî-hÆöOÂ¨\u009f¾¯\u001c²ä¨\nx\u0001É¬¤´l\u0088ºá}_\u0082É;½Óùü\u001f½ì\u0085nç\u00156¼4\u0093:HªåM\u001f;jÏ£\u008a]øÏÞWae'ù»á!D\u009d6Â±\u0092¥\u009e\u00ad\u008b\u0092XÃ\u007f\u0083¡Wöï¬¾5ûÅ\u008eÛ¹\u0018bHè±êø<\u000bÒ¾P½Â¢J6Év\"\u000bè\u0090\\ÐÃw\u0005\u0018Sêº\u0000su\u0016^\u0097½%\u009e\u0000çî\u0004-r0ãð\u0082ð¼YZà\u0082«4GÑÐ\u0096íj/Ï¡õ&¦çºH\u0087A´\u001d\u0001\u0017%×\u0010Sã\u0011ä\u0019!µ\u0089 ÙRNË7\u001erÅ$mò»\u0087ù{R±\u0087\böll\u0089×ÿÁ\u0096Ôñ§Ø\u0016îÄ\u001f9\u00ad.J2\u001f'±\u008dÐÖ\u008dm\u001b+;a,óHÀ=\u000b\u008cUwá\u009eÒR\u009e<\r5WIïFèÂâe2H\u00ad\f\u008bw°AÕY`6\\bÓ\u001b´\u000e±-p0ÒÄÖão\u0091\u0082ÝÂ¥\t\u0017\u0098¤Ít\u0086NNÔV×£\u0003nÔ]ÂXÐfÓ·\u0098\u0000k¦Å¢2ÕÍ\u001e4ñ¨Ù\u009b\\¨<ú\u001fÐ\u0013\u0002mð\u0081ÿ\b¯!¼½Ëæ\u0087|Ò8\u0081{ç´\u0013\u00adH\u0085¥æ¡é\u0000ÿ\u001fB\u00906Ð\u001dÂO\u0014jþÚ\u008bÚ\u009eð\u008eµk\u0001á\u0096\u001cn{\u0093Ó{>pm\u009aX-JC\u0092Ø\u009câ¶×Ñ\u0093\u009eò×\u0087Ì\"Û\u00ad5~r®csr2\u0087éè\u0083hYV5QÎüÕgE\u009e\u0018ñóh\u0088{±Zùb6\"\"ðq&\n\u0097ûi¼ù%\u0081ý¤Ï\u009f\u0013¹Üº\u0084÷\n1§ºl\u0005\u001d\u0012\u001cÍI·\u0010ÉÜ=(\u009aÌdPd\u000b \u001aîÈÍ1PFÿfQEP\u008a\u001eé+<À\f6Ç¸S:g\u0091\u000fO\u0000\u000fÌt÷»\u0099\u0016oAo\u0090ºS¾ü3î\r,ï2t\u0012^:¯æs\rÂ§l\u008cÛóÆ¹gÛá`\u0004&é©\u0007y\r\u001b6.<çÁ°\u008b\u00138=Ù¦M\u0087\u008a\f\u0092MÆ]'\u008fó¿\u0012óV;\u00adc\u009f2*ýü¶x\u00863ò\u0001ÕtÔ*\u0092\u0097¨½J&Æ¼9h\u0093\u0082êÍ&ùG\"^\u008f(\u001eØ½a:\u0013\u007fºø\u0081\u0000¿ø\u0005M¾_Á®Ï[áÒkfª{\u0016\u001e0ä\u0081²Ú\u00ad\u008e½W\f\u0092PçÏS¯5Ji\u0013ÝZU$O\\ÙY2Au\u008eh\f&r²\u009f$\u001b\rMë\u0087ÌïE`P\u0018+K9gkÈ\u009c\u008d\u0011\u0003\u0007Û_\u008e\u00adüó\u0007\u008b\t¨õV$¬è{Ý\u0099cÊ\u0013/v\fÎ¬´VaÍzH<¿GdzçÜ@Ó'º\nî¶©Øåùg\u0004ëçÀöEw½\u0083\u001aHg\u0091VÁ·²\u0019RÄ_ kÇÆ.íÃ\u0087C\u009c\u0081Ób\u0093ûN²Ò\u0001ØH\u0081\u0002(\rF\u0013\u0094\u0092¬¯\u0007@í\u001e¿¦ìãÕXpoîü°\u0013\u0096`Öo\u0000;ñagS5³c\u0099lh\u0002MAaG¦\f\u0006õu\u00ad \u008cB¤|ÿY}åÎ\u0011°\u0088Lü<ÂcÌÀÄ0chiVBï\u009d2\b7À¤Âx[¾\u0000?{%¸$\u0088\u0082^·\u001eBD\u0019¾\u007fK\beêégY»\u008d_\u0002oÉ\u009c×Èv«\u009b«\u008c\u0096\u0097\u00ad}lÏÉ`7]\u0003nNë\u0097\u001cZ\u0095Õàï²Î\u0085Ç5\u0019v¾(ÁÜ-\u0092¶\u0019\u008d\u009f\u001e»\u0015\u0098B -ÎbAóA\u001eÌþPb2p,ÜeR\u0002âäU\u001c\u0015 Íá\u0088\u0014ÑV¯ªv³°\u008c*Âÿ½\u0094ÏéPs8&ä4Û¯Ò\\í3\u0080ÔÒ\u008eã\u0003\u0081&2S\\\u0089½\u009f\u0010'ðc¸¯\u0097ouÁ®ÑEx\fÌ&²æ\u009aq8sovaõÝ\u007f\u008bÕ\u001a=\u00adE·4`ÌÙ@\u0096<ÆìT¥×»[(zØë§mÇa\u0083&óh'zeð[m¿Ç|E\u0001±ÂWLÃ(=-þWvuB¥æajf\u0089çp¼ÂüÍß\u0005ª>\u0012P\u001f\u0017P*\u0090ñ\u0081äöÙ;\\ÞÃ\u00928©è\u0088\u0012Ìñ\u0002Ç,4\u0014õ\u009a>1MPdÒ\u0094¤JSÛ¹\u0090±¾\f\u0080³Ü[¨W\u008b\u0004©\u0099;\u001e«\u000be\u0000±1X\u0012ÿ\u0006âS#»B@ê\u008dï\u000fXùgR\u008e\u001c©oZy^:ôØWõU`\u0015YC\u0002q\u0087ZÐ§«\u0090íØÆ\u0092»Y\u001c\u008eóÉ5ï@Mx\u0005e2ÐØ\u009fÀ\u001auòyøKÊ\u001a\u0014@¤¤o)H«Í\u001aÙüá99¾CzI\u0005bÎè\u0017ä®\u009fQ×qè\u001cO¾9¦\u009el\u008b$\u0006\u001f>\u0000é\u001eÚ®0ÆB¬¥Pé+!ªÞzö\f\u0005þ\tøP\u0095©X9%õqN,/@,{òÚ¬ËÊ9 Ê¦h\u009fÒjpÔjq¼í¾ßI²\u008bºÒl\u0091\u001d»\u0085*\u0007\u009b¾¬×\u0094ì;-\\nâ0ÔÇ\u009fÑ}k\u0018\bì\u0018yIAp\u0091ÈÔ\u0004Ò\u000eL\u008a\u0010i·öÇü\u008b\u0006-Ò\u0013{!\u0007u¾ÄXó¹ÏÄ\u0006Z(%rø\n\\\u0099®\u0093\u000f\u0001Ôè\u0012Î|{¬ y)/\u0003$\u007f: \u009e\u0094\u0098\u0006ì\u000ea8¾¥\u000fÙQÀ¢\u0000\u001bëïæ|ës¸á=z\u001a©\u009a8\u0011n\u001dÂ\u008f8\u0093'+Á}©m\u0000w7\u0092ù¦Ë ¦º~Pä»yâíÚÖ\u0088X\u008f\u009dMÙ\u0000ÃÚB\t\u001b\u0012NL®>\u0012¬´\u0085ø-ÆÏ\u009as\u0081²©@se\b\u0088B\u001e[òóûÀ\u001f¥u\u001cU\u008d\u0006F\u0007´e+Bø0\u009aSÓ*W\u0092íîõÜÿ7~ÇW\tPÙ2\u000e\u0019A=¥4Üd½Ép ûaMÃ\u0086`\u0093z=\u0003ù7QÞ\u0011«CY?b|\\òFËP¨\u0005\u0083\u0005R£\u001cqô¡\u0085{Ú*2\u0097\u0082æw?\u009a/\u0089Ã\u0001\u0018SJ\u009d[õ\u0090TN±,7ì\"°@£Fó38ÒÉFm\u008eóK\u0001\u008d\u001e\u0093\u00835g\\Å\u001bÎæîâ,©y©å\u007f\u0094p\u0012®ÊDî\u0091\u0091§±aº¾\u0019mçæ\u0007ÚKÌéÐ²à\u00960\u009eÛùAh·\r!f\u001a\u0087Ip\u0087S\u00923V\u0006\u0081Hq¾\"\u0012q¼MAHít[u\u009a§Vï\u001e´Ó|\u0093\u0019\u009fo\u0087ÀÉ\u0007D4\u0090ê¼)¾\u008e\u008a¶\u008fb\u009dá«g\u0019æîoæÀ¤w8jQ+)\u001d9S\u000e¤eõ\u0015\u009f-ùÕk;v»|Dª7\u0086\\É9\u0016ÞÎ^ót@R\u008bsþ¯\u001d?Y,¯ÑeE\u001aÊÇ\u0013\u009c§í*ÖüÍ\u0080\u001a\u0091h|!£²g\u000f\u0019DÕÜ\u0013Y\u0085\u0080øFHé\u0007@Æ\u001ewÆWÛNpÌru¯\u0094Gÿ\u001bXüµ\u0005µ\u0090Ý\u0004\u008e«\u009c«e_\"sb\u00915\u0001Îº\u008d\u0088Y\u0084\u0006\u0000D§ª2ëh\u0014è\u00129á#2¿A\u0087Q{\u0080è)c\u0094¥=à\"}Â~ªr\u007f\u0000òa°m\u0097-\u0011\u0091Æîù¨\u0099V|U*\u0001ñ¡ \u00189²Ô\u009d\u008dHA@æ\u001e±vÏ\u008cã®\u0001ª\u0093\u0083õÙ\n}X\u0001YÞ3\u0013\u0089\u008d¿£Bçõ\u009aÛR\n«åø¦ùtâ÷ø!{\u00000VsÑ\u0012\u0095\t¤D\u0005¨F\"ÒÁÉð[ÔÛ>2´\u0011[H\nH\u009fc©Ó)þ°0õ66Çm©C\u0083C\u0017TTp¦r\u0081¼\u0006t¶c\b°À\u0014yëÛI}\u009e\u0082\n\u0085Ñ\u001f\u009d¿\u008b_ç]\u000ftð\u001bÖlý\u0001a\u001d\u000fÒ3õ½\u0015\u00033\u001cT\u0010öI\u0086ÄÃºQ\u0085QvR¡á\u0091\b\u001bXuÅÌ\u001bR\u0085©\u0099AÜhÝá\u0087\u0011SÁ¨~ñhpR\u001e\"\u009fæÐ=\\óÌ\u001e>pÿ?Ýã¹ÂmüqûJÒ/1óp³*\u0000ò´\u008c\u0095¬òðne@ýKïÐáÄ\u0016«ª0¬MY®ã»\"\u008cõÎA¢)\u009f`pßôI\fç\u00816\u001b:¾+¬MI3¹ÇÜ×HiÓ¯\u0093\u0093ðx©\u008dÐ\u008bö\u0091·ííkÅå\u0090·(;¹\u0002\u0081\u001cVúÊ\u008bû\u0016\u000f\u001a¹b\u0085\u001eØÎguñH·á\u008a|llï-nÅ\u007f\u0006\u000bÆuk½Ã\u0097\u0005R\u0094K\u009e]3åÛdb@g>¥\u0018ÖEÆIAÅmàß\u0016Í¨ö\u009cuLLæ\u009f¹å\u001e\u0097÷ìf\u001eÙ#\u009a\u0017~\u0086æbÏOá¾0Ç\räõN²\u008dÎÀi¶8a\u008cÇ\u009cÎ?È\u000bHÝÖ\"\u0081\u009e\u009a`î4¨Ë\r\bäÖ\u0092¾´ÖÙV\u0010¼@\u0083<æl)ÒI÷çº{>°?\u00197\u0005eÓSs\u001e4\u000b~fì²|`Ë#|í\rö6.ã£ÜÂp@\u00ad\u00976ìX_Ý\u0088âÕº \t)=Íº#Ù©[ô×6y\u007f\u000e0Î*ým\u00961Kïý¼\u0092âF\u0012°i\u0014\u0093ªò0½à{½H\u0082ðt¹ä\u008bÇò,©U$Ç \u0087W}ñ1h#nPÜ`B@\u001eLÝ3ÑS_§ÝbÞ¥@\u0082ÙXá>\u009a\u0083Ä\u0002îQæ\u0094\u0012eM´²\u007fFö©º¨iáeÂp\u009d\u008b\u009d=åå\u0011Ð\t\u000e\u0081\u000e\u000fÿK[\u0092¨Ûzÿ\u0006Æâ\u0099&>V9£K\u001c4:\u0095`MR\u0015\u008aÜÚþxj\rçá{\u0005·u\u0088\u000b®¥'<AO\u0016p^6b\u008d¡ü\u0091*ß\u0082\u0007Ò ÿï\u0001ò©Bç\bçK\u0080\u0000-Â\u0005bVÂ\u0093k\u007fýnJÃ\u0081hZ¨nr\u0012h²`\\\u009d\u0088¢W\u009bZl\u008e`\u009aË{ÂÄ\u0014\t±IëfàHé½\u0004µFø\u0088\u00ad\u0007(@\u00815C\u001e\u0098ÜÝ¿Ñ»\u0085N®xà2Ð\u0097\"M\u0005WW\u00804}Edá!\u009dá¯\u0092f 7Ë\u0002ì:hÌ]ùª\u008a¾3\u008fýP\u001f\u0088Õ\u0099mbOß|@à0¯\u000b\u008eÑõä\u0083\u008fÊRì\u009fá\u0081D/l:¬ea¨p\u009e\u0092¡P±~ñ\u008b\u0091iY¤×h)ÏãJ\u0013éë1N^/X«ó·CWÎÕ\u001e\u0000\u0016KÃ\u0012@ø(\u001d«1\u0080Ò3Ú¦¯¥ÞâÎ\u0081+«ã\u009e\u009b\u0019ð\u0090?ÂmHöRZ,\u001dúëü'\u009eRþüµy'úXg -_\u001dÐ'®§TìfÄóä\u009b`òXÎh\u0007ô·\u008aQï\u008f\u001cQ\u008b\u000b]\u0093\u007f®mÖ1í\u001bñ3\u0095î£,bÌ\u0001l\u0098ûB9LD\u0087¤_Rwà«©¡ºÍPØâ\t\u001eëÜ\u008b\u008cèá\u0093w,\u0083C¤üeÙ²ÉÞCoð£,MVä¥ÆâÓk2?K(ÝíÂÛ\u0091÷i¿\b\u0093lÇåH\u00049\u0080Gø\u009b¿úòÒñ¸\u0080\u008e\u0016¯gÚ\u001dÝ¬·B\u0087iÙP ²©Ý\u0011åÀÆ©\u009f\u008c¼\u0014\u0095-ôA\u007fdÞ\u00859Rì\u0006\u0016\rü6\rêÁ/\r¤ÄrWF\u0010féýa\u0099qÔE\u0003\u009c°qB\\\u0015z'7¶q¢\u008c`³È\u009f?þ\u00880)Þ¾\u0019mä=®G\u0088`\u0081ª \u000fa\u0002M=fÕ¬<ë\u0014\u0082TÚç;³eb¸¶\u001b\u0087l\u001c<Ab ^\u001fÄ7\u00ad\u000b\u0087\u0089\u009aË{í\u0082t~ÑA\u009fÞÌ¬ùpvMw\u0005Ð\u0085´µÎ*\u001f¾»\u008b\u009d¬ÞÂÔeM×L°]\u0083 4\u0007Î\u008fJ;\u0087\u009d\u0019±\u009euªnPN\u001bÕnÚÍRÕ9B\u0019c£\u0088àËðÚk3üi6Pi®\u0007\u0083AlÞg_6æh5\u0088û\u0018\u001f.¾\u0010~Ùbà¯Ý\u0088¥ ü9ï H5þ\u008c¦%ñá±ö¸+bP\u001bç\u0006\u009aç\u001dÀÿ[P\u009c«\nÞkxñKúÅ´)¿ÂZ\u001b®=ßÄòF$ØYÙ^fõm\u0089ó]\u0016U]Ê\u0092?.¶\u0017\\á\rW¾\bâgíx`\u007fhu\u0096\fUvr}T\u007fð\u000f\fi\bUn\u009f¤\u0082¤%\u007fòd¾0Ú\u0083ð@^Q\u001el+¿\u0097²\u0003v.óBÃøGKMÕq\u000eYó\u0017l¡/øô´ãÍ¾?Ü&\u0098´\u0007g\u0007([¼Pz´¯ÍÛ÷Î`\u001eÝ\u0080\u0004ÌPÌ`Æ\u0089Ú\u0085]\u0012\"F}Sí.&Ì!\u001f\u0099>L\u0089bÌ¤g\u009e×|Óde0j`òû6Õb\u0000n%²\u0086Bb\u0003F\u0085!MïPÉS\u0017ÂJp[f}} àX¤Ç_\u009c½\u0080e8\u000e\u000f\u001eÍôÎÚl\u0005\u0015S\u0003e¬-|k|\u000b\u0001GJÔÔbÍæ\ná\u008d\r¼IN(§i\u0015V\u001d¬W\"0É¿nô«µ°\bÈå\u001b(\u001c4\u0081\u008eTu4å\u0003Ö¦\bx\u001cD¸C\\WW`[°$H@ð²Sz¹±Î\u001fv\u001a³Z8\u0083Sl\u0093xÜ¼Ä\u0084Y\u000eØ\u0000.\u0092ùÚsç+£;yãR\bS\u008dT»\u008c\u0019g\u0001Å\u0084LÕ4o\u0019\u0014j\u000b\u0006x\u008a] º¥±\u0084\r\u0085Jß@XaÐWÖ7¼¥\u000f¾¢C\u001be\u000e6Ä\u009d+¬\u0014\u0088&í\u0085\u0080ÑYÞòÊ©\u0085\u009d8'\u0088üÂqCôp;ö\u0098\u0088J\u000b\u001dN\u000e\u001bu!C\u009fjQ-[LM\u0004ùr|2ú\u0015B\u0081°@ÇÄ\u0017\u0084Dv\u009efÈBH\rdù(h\u00140!s£Ê¯±O\\\u001d\u007f¦÷åª+Äë\u001f\u00019JóÜ¤íô.Ùd;±ç®\u0002 8òJ\u008c\u0098¹8ß\u007fÍdÝ;mä%ì\u0094í|\u0017\u0084Äm¿W\u0093:7ü\u0014\u0007\u008cáÓÌ\t°\u001fð2ó\u00004ºñ\b¥8cj3f\u0081¸l\u0081\u00ad®@\u001f°\u009a\u008aÝ\u008c±H32kU^\u0091{°HCèëwÕoÞÇp\u0015\u0002ö_,Sá\u001eRÅqç)#ëØ\u009eC\u00adÕ¨Î\u009d\u0083®î\u0095\u009a¸\u0006t²\u0091p-1|l\u008bpÂ¶)_\u0005Óã26\u008d¢©&3`Hç\u00917´âÎél|(þülè¤É\u0093Òà\u0089äµÀ RÀP\u0086|² W\u0016,ß\u0099\u009f\u0084Â|gÎìw\u0087?rrÂ%Ç\u0003NhÕÞõJ\u001csz¶éæD\u0019\n\u0000\u00adx±\u0017*tì\u009dÂ\u0011³?3¢`Ö%'ª¯\râªèÒ*BPi\fmçé\u00872Só\u001eP¿\u0083þ\u0007G§j\u0089Y\u0082\u001eë¶b~Â\bÞUÅå Xw-w\u001fïLI/U\u0089o°\u0081þ\u0006\u0089»u5)¾¼bW\u0094¯K:ªCo\u009a¸\u0011Ê\u0007ÞÀ\u009bã×Ä\\¾AyhVp\rÅÑ\u0084¥!¿W)¹~\u0097\u0099v\u0019H\u0092yêø\u009b\u000el6\u000eobÄ®UTv´Å~«\u008c\u0014ì\u009b2Ô\u009e~\u0094¯L\u0005\\\u0090³\u0012<\u0016\u0082\u008dd·ª&+\n\u0015C\u0096Õ\u0090kÁ\u001fd\u0019\u0014k\u0091!ÅÐBs\u001c¾PÖûõ8Q\u0091°ëÍ\u0086Þïó¹\u0096Ñ\u008d\f\u001a\u0010³¦Õ\u0091\n¸ÿtíº\u008e'B\u0001q\u001cXLÑw(°àê\u001czµ1<Ó\u0090\u0011o\u0006S:]XÕ$®÷b0Z1\u0004xTg\u001aéD\u00046ÂÀÆî\t¯Ý¡]÷¾ù\u0088áÎdÎ×¾\u009bØc|\u009bábHkJ\u009aî\u0090Ø\u0094\u000fR\u009d0à¨\u009e\u0097Ú\u0000òÖÜØâ\u0091H~\u0084È\u0092XEdÕ\u008a\u0014ö\u0092\u0087G\u0080U\u0098\u001d\u008e\u0019j\u008a0ÓàIË!%\u0087\u0083Y\u0087\u001bhú¿\u0085É%?,Ç\u008eÆÊÁýY²\u000eâ\u0082Æ\u001eäY%e¾`\u0004å^W\u008bÊ6ë¾[wX=¢\u0002\u0097H³M\u0082Ý\u008dÎ\u0080\f®ù'âæ(\b\u0085Ìr((0:\u0018\u001a\u008e+ñÉ\u0099\u0006\u009aP*lý¼b>ÊußB\u0097¯ü\u0093\u000f3\u0084Ø.\u0096I\u0001*\u000b{?¸\u008bý½\u0091\u008f\nw½æP\u00ad¨5D~^,dÂ°ÎQáÅ(3\u000f\u0080ö \u0081øz\u001e)qJl\u0019^\u0083\u0083ÈÆ\u009cèÙ\u007f[¡Â\u008eØ\u0084!\u0094QøEÚ¿+Ø\u0096\u008f8e\u0083\u0087\u0016a\u000e<vÕéµD\u0090\u008fÊà¡\n\u0095Q®±Q\u0000×Ú\fõ]ïjôÑð\u009eúSV\u0011*}\u00adSöæ\u000fë\u00870SPAÿ\u008e\u009cåH\u009ffkOQ-\u008f\u0013B\u00064l\u0010c<Èû~ D\u0010áÅÑjFK\u009d\rã\r7ìepH\u008c\u0019\u0088ðé}(Ó«\u0095\u001b\tÿM×\u00ad\u008e\u0093I¨ã\u00019º©Ï\u0010¢\u0099EN[\u001e\u009f\u009fÑX(®\u0000\"¨p\u0001Yø0iaåÏ¯ÂL÷î$\u0016M\u000bU ÃÜ\u0000â\u009a\u0001\u0019m\u0080½óq\u0095öÊ\u009b`\u0018Õ\u009b±Í\u0014ü)\u001bBÞ7¨ÁMy\u0087\u001e#\u008f\u0000Ï\u009dåNås\u0085\u0086_Þ¿£YÚ\u0014ã¡Uqè\u0084#ý¢Ì\u0010h¼Xc+e\u0080i6e\fiÌ^\u001f\u0096X\u001f¸\u008c\u009a\u0001\u0097¤iøÈÍ\u0002íö\u0092¦Mc\u0019;\u009bÚê\u001bí\u00ad¦mµ¤áÞ\rè\u0017G\u009f<Ô²ÏØlHlF\"b\u0092\u0082ß«Á\u0015\u0011\u000bn%ÚLGåÞ\u0095Ð\u000bhH\u001cÚ;EuA\u0099j\u0095\u0082T(\ræïìÇç\u001b\u0092`ø|ÇaÆù\u009eOr¸\u009c\u001bä\u009eÍF\u0087s°*ã\t]{jt~òði<ë\u009co@ì>×{\b\u0011cÄÓ\u008cÊ\u0092^²'¬$N¹¼-)E\u001f\u001b¬0.]\u0083E\u0010ÎÎ4\u0001«Y\u00919rA./N\u009b\u009dTb?G\u0081÷-/ÚÊ\u0001£ôJ";
         int var17 = "Éä¯ð\u009f\u0095\u0000o/JëV7ÌäJEWÓïN(\u0088\nBIäþ\u001cÖÃh\u0004òCý.\u00829J\u0089\u0088F^\u009d=×\u008e\u0099v¾ý\u0083ÑKú\u0084H\u008f\u000e\u000f\u008eÚ²\btÎ\u009a\u009d\u001eÕ=chßP\u007fÖ\u0098÷\bæo\u009e5¸d!&\u0011ò°¾ËÜËhÀÛe!4\u0098fÖ½x\u001a\u008c6\u009cb@\u0003P¦æßf\u008a\u00983\u0017\u0019ÖÃ»\u000e\u0000\u0010Ý\u0093JòÁ½x5O8D³¶\u0010ÒF\u009e¨Ù{ æF¶\u0096òRùÕðlÍ\u001f\u0016Ï~)$ô7\u0085\u0080Â§¼Õ\u0091Ôí\u0088ë ¹\faµdäJi\u0005¨:\u009d×\u0018\u001dìâdì0\u008a\u0082W½\u0092cBÂOØB%\u009e3[\u0004-XEyø\u0000u\u009bk \u0081\u0093P2×*\u007ftÙÔRy\u008eáìQ 7#)\u0000JX¼HR\u001b\u0090\u001cpÎ¤9È\u001b\u008b\u0010\tbSëË\u000bäè3»L\u000e.\u0000ó+\u0084ÞM\u009b\u0095Ç\u0096´Dè\u001bØ'\u00ad*\u0014\u0098zSÙ\u0010Ö[a\u008cÒÙ\u0080)Ý£[\u009d¤Nüïî½n\u0093\u001abRúú\u009eÖåDJ\u008c²X,¬EkHÙbVqj\u0091ª\u000e\u0088¯×à/\u0001¹ÔP3O\u0014\u008eÇ\u0092Ñr\u0090\u001dtj>b¯\u009f¨ë7\u001b\u0004\u009e_\u0099åUuF÷u\u009cx¢¬\u008a¸â\u0014Lk;E\u0002×ÉÔ%Üç>\u009dlz/\u0000\u0010E\u0086_ßÔ\u0080b\u0093Z\u0093\u009c\u0002ïc\u0013\u0017\u0010*úÀ\\h\u000f%\u0082&\u0012Ø8-ûÇ\u0019P°|R\u0085\u001eî\u009c\u0003>\u0019:µ\u009b?\u008cª\u0016xì\u0016¥Yµ\f¶gb¼å\nã/}\u009a´e\u008b\u0017\u0094\u0011ZÚ\br\u009e\u0089nÒ.\u001f\u0089\u0082\u009c\u0006U'ÓÀ%õP\u0091\u0015\u001cvä«¹è\u0095cÀ\u00ad²£\u0011Íd\u0089Lx\fpmQ@\u008e3?Pg½û\u0089¥\tN\u001eB\u0082Fwj¿zí\u000f>NTÒä¶¥<(b\u0090f\u0093:\u001c\u0011R÷¦NTt9\u00989¸©\\Zbë\u0006ÞÁ\u0090\u001c@raÁ]ÿ\u0000^ì\u008b]¶\"\u0094ÑævÖÜ\u0003õ\u0019\u0098+9[p\u0080dUÅèêÆ¾©A^\u009f\u001e\u00182¨\u0088Ý\b\u0004¦*k¥\u0080E[NÐº\u0093@\u0082\u0098ªýÙp\u008c¨1ý¸îÎé|_[F¿\u0014\u0086i\u0007ÉGWÜm2\u0081¶ï\u0002Ì¦¡Þ«\bØ=¯ª\u008bX?Ù\u0080ß¢Ø\u0097ËÓ\u001c¹\u0096*f\u0014Ùç\u0094¥PpÝ\u009f\u001auµ\u0095>ÿ_½\u0019\u009eÎ¡Øi\u0099¦ï*-¯ \u000eÎÃ2]øØÝíÝ3\u0086\"ÙÉà§ã£kÖ¡ø®ª±çG¿\u0013¼wz\u0085L\u0010\f \u001f=¥\u001b\u008dP\u0082µûÔ\u0083t\b~\tåÎ\u008fH£]§ÂÇú\nØt\u001câñFÊü\u000eb\u001f\u0010$\u0096Â\u008f1Ø]=\u000fýÅÀ\f\u0002p\u0097\u0089\u0001\n£*ì\u00adzd \u0087Å\u008bÅ_Ú\tD\tR\u0094ãÇ÷É§ìÅÒ\u0017ñpMx \u0002ÜHw¢ õ\u0094j.ôu¤\u0010v×U¬^\u0003\u0084Ì\u0018!ÊçÉxG\u009bþdTú¿³\u0080:Q\u0086`>\u001dãÞ£nôë-5¨Ú\u0093;d\u0085iTtË)²_)THX\u0012ìC\u0098|wmX\u009e\u0089(UýqÑÖ§\nw{d\u0019þ7\u0091\u009c\\°àÜ^èè\u0085C\"m´Ø\u0007\fon\u0007RÛø\u0092\u00929I¸>¯#G}ÎÔqH\u0097¶\u009fº©èÏxz Þø\u001eçæ©X\\\u0095ø\u0007¢³\u0085åz\u008dt\u0004Q\u0018ïÂ\u0015eHõ!¹0d\u0014Òô\u0099÷³YNl\u000esÃ\u009c¤lR¡\u001egüûL\u0002\u0082\u0084IÊ\u0088òZ$\u009aiW\u000eàì¦V¥t°uö\u0007Vq]\u0002d§\u0012ý\u0082b÷t\u0091!|o³^£\u009f?\u0096XÇá\u00938\u0014ÖÃ³Ö\u008c1\u0012?_û\u009aà/Üþ°i\u009aSìR*\u00042ÖL\u0081Ð\u0095raü`f¦;ÜyÆ,Ç3¢ ù \u0090ÞãBù1ù©·Æ\u009d&XÒ|«F\u0003Êô\u008fr®\u008f\u009c(÷X7{Î\fÐyÃ\u001cN`\u0003ÁLtÚÐ\u001eLy7&\u0015\u0006>\u008a\u001fÒ=ñ\"eW7â\u009a©L3Å\\'¾ÄÆËÜ\u0088§ê\\/Î`»ÞøÿA\u008dQzl\u0006\u009esE«»\u001d\u0098íº\u0005t\u0098ÒF]£ -\"Ûp_\u0004ìcôE¯UT*ÇÍ!ËyÞ\"W3\u0007øÎXöíân¹â\u0013³¢ËmZ~×5Ïy`/¬Ç}.\u009fg÷ã+\"\rÁ\u0081q\u001aibüL¨ f\u0012\u009eY»d¸\u0095\u0095×¢¦~ÑuÒ9\u0003ûÆ=9Wå\u00056ÖRq¦î\u0086\u009cÔ¯\u0091c^c`Î¬ß\u009dpâw,XPÑx¹<\u0015\u0017\u001fî-hÆöOÂ¨\u009f¾¯\u001c²ä¨\nx\u0001É¬¤´l\u0088ºá}_\u0082É;½Óùü\u001f½ì\u0085nç\u00156¼4\u0093:HªåM\u001f;jÏ£\u008a]øÏÞWae'ù»á!D\u009d6Â±\u0092¥\u009e\u00ad\u008b\u0092XÃ\u007f\u0083¡Wöï¬¾5ûÅ\u008eÛ¹\u0018bHè±êø<\u000bÒ¾P½Â¢J6Év\"\u000bè\u0090\\ÐÃw\u0005\u0018Sêº\u0000su\u0016^\u0097½%\u009e\u0000çî\u0004-r0ãð\u0082ð¼YZà\u0082«4GÑÐ\u0096íj/Ï¡õ&¦çºH\u0087A´\u001d\u0001\u0017%×\u0010Sã\u0011ä\u0019!µ\u0089 ÙRNË7\u001erÅ$mò»\u0087ù{R±\u0087\böll\u0089×ÿÁ\u0096Ôñ§Ø\u0016îÄ\u001f9\u00ad.J2\u001f'±\u008dÐÖ\u008dm\u001b+;a,óHÀ=\u000b\u008cUwá\u009eÒR\u009e<\r5WIïFèÂâe2H\u00ad\f\u008bw°AÕY`6\\bÓ\u001b´\u000e±-p0ÒÄÖão\u0091\u0082ÝÂ¥\t\u0017\u0098¤Ít\u0086NNÔV×£\u0003nÔ]ÂXÐfÓ·\u0098\u0000k¦Å¢2ÕÍ\u001e4ñ¨Ù\u009b\\¨<ú\u001fÐ\u0013\u0002mð\u0081ÿ\b¯!¼½Ëæ\u0087|Ò8\u0081{ç´\u0013\u00adH\u0085¥æ¡é\u0000ÿ\u001fB\u00906Ð\u001dÂO\u0014jþÚ\u008bÚ\u009eð\u008eµk\u0001á\u0096\u001cn{\u0093Ó{>pm\u009aX-JC\u0092Ø\u009câ¶×Ñ\u0093\u009eò×\u0087Ì\"Û\u00ad5~r®csr2\u0087éè\u0083hYV5QÎüÕgE\u009e\u0018ñóh\u0088{±Zùb6\"\"ðq&\n\u0097ûi¼ù%\u0081ý¤Ï\u009f\u0013¹Üº\u0084÷\n1§ºl\u0005\u001d\u0012\u001cÍI·\u0010ÉÜ=(\u009aÌdPd\u000b \u001aîÈÍ1PFÿfQEP\u008a\u001eé+<À\f6Ç¸S:g\u0091\u000fO\u0000\u000fÌt÷»\u0099\u0016oAo\u0090ºS¾ü3î\r,ï2t\u0012^:¯æs\rÂ§l\u008cÛóÆ¹gÛá`\u0004&é©\u0007y\r\u001b6.<çÁ°\u008b\u00138=Ù¦M\u0087\u008a\f\u0092MÆ]'\u008fó¿\u0012óV;\u00adc\u009f2*ýü¶x\u00863ò\u0001ÕtÔ*\u0092\u0097¨½J&Æ¼9h\u0093\u0082êÍ&ùG\"^\u008f(\u001eØ½a:\u0013\u007fºø\u0081\u0000¿ø\u0005M¾_Á®Ï[áÒkfª{\u0016\u001e0ä\u0081²Ú\u00ad\u008e½W\f\u0092PçÏS¯5Ji\u0013ÝZU$O\\ÙY2Au\u008eh\f&r²\u009f$\u001b\rMë\u0087ÌïE`P\u0018+K9gkÈ\u009c\u008d\u0011\u0003\u0007Û_\u008e\u00adüó\u0007\u008b\t¨õV$¬è{Ý\u0099cÊ\u0013/v\fÎ¬´VaÍzH<¿GdzçÜ@Ó'º\nî¶©Øåùg\u0004ëçÀöEw½\u0083\u001aHg\u0091VÁ·²\u0019RÄ_ kÇÆ.íÃ\u0087C\u009c\u0081Ób\u0093ûN²Ò\u0001ØH\u0081\u0002(\rF\u0013\u0094\u0092¬¯\u0007@í\u001e¿¦ìãÕXpoîü°\u0013\u0096`Öo\u0000;ñagS5³c\u0099lh\u0002MAaG¦\f\u0006õu\u00ad \u008cB¤|ÿY}åÎ\u0011°\u0088Lü<ÂcÌÀÄ0chiVBï\u009d2\b7À¤Âx[¾\u0000?{%¸$\u0088\u0082^·\u001eBD\u0019¾\u007fK\beêégY»\u008d_\u0002oÉ\u009c×Èv«\u009b«\u008c\u0096\u0097\u00ad}lÏÉ`7]\u0003nNë\u0097\u001cZ\u0095Õàï²Î\u0085Ç5\u0019v¾(ÁÜ-\u0092¶\u0019\u008d\u009f\u001e»\u0015\u0098B -ÎbAóA\u001eÌþPb2p,ÜeR\u0002âäU\u001c\u0015 Íá\u0088\u0014ÑV¯ªv³°\u008c*Âÿ½\u0094ÏéPs8&ä4Û¯Ò\\í3\u0080ÔÒ\u008eã\u0003\u0081&2S\\\u0089½\u009f\u0010'ðc¸¯\u0097ouÁ®ÑEx\fÌ&²æ\u009aq8sovaõÝ\u007f\u008bÕ\u001a=\u00adE·4`ÌÙ@\u0096<ÆìT¥×»[(zØë§mÇa\u0083&óh'zeð[m¿Ç|E\u0001±ÂWLÃ(=-þWvuB¥æajf\u0089çp¼ÂüÍß\u0005ª>\u0012P\u001f\u0017P*\u0090ñ\u0081äöÙ;\\ÞÃ\u00928©è\u0088\u0012Ìñ\u0002Ç,4\u0014õ\u009a>1MPdÒ\u0094¤JSÛ¹\u0090±¾\f\u0080³Ü[¨W\u008b\u0004©\u0099;\u001e«\u000be\u0000±1X\u0012ÿ\u0006âS#»B@ê\u008dï\u000fXùgR\u008e\u001c©oZy^:ôØWõU`\u0015YC\u0002q\u0087ZÐ§«\u0090íØÆ\u0092»Y\u001c\u008eóÉ5ï@Mx\u0005e2ÐØ\u009fÀ\u001auòyøKÊ\u001a\u0014@¤¤o)H«Í\u001aÙüá99¾CzI\u0005bÎè\u0017ä®\u009fQ×qè\u001cO¾9¦\u009el\u008b$\u0006\u001f>\u0000é\u001eÚ®0ÆB¬¥Pé+!ªÞzö\f\u0005þ\tøP\u0095©X9%õqN,/@,{òÚ¬ËÊ9 Ê¦h\u009fÒjpÔjq¼í¾ßI²\u008bºÒl\u0091\u001d»\u0085*\u0007\u009b¾¬×\u0094ì;-\\nâ0ÔÇ\u009fÑ}k\u0018\bì\u0018yIAp\u0091ÈÔ\u0004Ò\u000eL\u008a\u0010i·öÇü\u008b\u0006-Ò\u0013{!\u0007u¾ÄXó¹ÏÄ\u0006Z(%rø\n\\\u0099®\u0093\u000f\u0001Ôè\u0012Î|{¬ y)/\u0003$\u007f: \u009e\u0094\u0098\u0006ì\u000ea8¾¥\u000fÙQÀ¢\u0000\u001bëïæ|ës¸á=z\u001a©\u009a8\u0011n\u001dÂ\u008f8\u0093'+Á}©m\u0000w7\u0092ù¦Ë ¦º~Pä»yâíÚÖ\u0088X\u008f\u009dMÙ\u0000ÃÚB\t\u001b\u0012NL®>\u0012¬´\u0085ø-ÆÏ\u009as\u0081²©@se\b\u0088B\u001e[òóûÀ\u001f¥u\u001cU\u008d\u0006F\u0007´e+Bø0\u009aSÓ*W\u0092íîõÜÿ7~ÇW\tPÙ2\u000e\u0019A=¥4Üd½Ép ûaMÃ\u0086`\u0093z=\u0003ù7QÞ\u0011«CY?b|\\òFËP¨\u0005\u0083\u0005R£\u001cqô¡\u0085{Ú*2\u0097\u0082æw?\u009a/\u0089Ã\u0001\u0018SJ\u009d[õ\u0090TN±,7ì\"°@£Fó38ÒÉFm\u008eóK\u0001\u008d\u001e\u0093\u00835g\\Å\u001bÎæîâ,©y©å\u007f\u0094p\u0012®ÊDî\u0091\u0091§±aº¾\u0019mçæ\u0007ÚKÌéÐ²à\u00960\u009eÛùAh·\r!f\u001a\u0087Ip\u0087S\u00923V\u0006\u0081Hq¾\"\u0012q¼MAHít[u\u009a§Vï\u001e´Ó|\u0093\u0019\u009fo\u0087ÀÉ\u0007D4\u0090ê¼)¾\u008e\u008a¶\u008fb\u009dá«g\u0019æîoæÀ¤w8jQ+)\u001d9S\u000e¤eõ\u0015\u009f-ùÕk;v»|Dª7\u0086\\É9\u0016ÞÎ^ót@R\u008bsþ¯\u001d?Y,¯ÑeE\u001aÊÇ\u0013\u009c§í*ÖüÍ\u0080\u001a\u0091h|!£²g\u000f\u0019DÕÜ\u0013Y\u0085\u0080øFHé\u0007@Æ\u001ewÆWÛNpÌru¯\u0094Gÿ\u001bXüµ\u0005µ\u0090Ý\u0004\u008e«\u009c«e_\"sb\u00915\u0001Îº\u008d\u0088Y\u0084\u0006\u0000D§ª2ëh\u0014è\u00129á#2¿A\u0087Q{\u0080è)c\u0094¥=à\"}Â~ªr\u007f\u0000òa°m\u0097-\u0011\u0091Æîù¨\u0099V|U*\u0001ñ¡ \u00189²Ô\u009d\u008dHA@æ\u001e±vÏ\u008cã®\u0001ª\u0093\u0083õÙ\n}X\u0001YÞ3\u0013\u0089\u008d¿£Bçõ\u009aÛR\n«åø¦ùtâ÷ø!{\u00000VsÑ\u0012\u0095\t¤D\u0005¨F\"ÒÁÉð[ÔÛ>2´\u0011[H\nH\u009fc©Ó)þ°0õ66Çm©C\u0083C\u0017TTp¦r\u0081¼\u0006t¶c\b°À\u0014yëÛI}\u009e\u0082\n\u0085Ñ\u001f\u009d¿\u008b_ç]\u000ftð\u001bÖlý\u0001a\u001d\u000fÒ3õ½\u0015\u00033\u001cT\u0010öI\u0086ÄÃºQ\u0085QvR¡á\u0091\b\u001bXuÅÌ\u001bR\u0085©\u0099AÜhÝá\u0087\u0011SÁ¨~ñhpR\u001e\"\u009fæÐ=\\óÌ\u001e>pÿ?Ýã¹ÂmüqûJÒ/1óp³*\u0000ò´\u008c\u0095¬òðne@ýKïÐáÄ\u0016«ª0¬MY®ã»\"\u008cõÎA¢)\u009f`pßôI\fç\u00816\u001b:¾+¬MI3¹ÇÜ×HiÓ¯\u0093\u0093ðx©\u008dÐ\u008bö\u0091·ííkÅå\u0090·(;¹\u0002\u0081\u001cVúÊ\u008bû\u0016\u000f\u001a¹b\u0085\u001eØÎguñH·á\u008a|llï-nÅ\u007f\u0006\u000bÆuk½Ã\u0097\u0005R\u0094K\u009e]3åÛdb@g>¥\u0018ÖEÆIAÅmàß\u0016Í¨ö\u009cuLLæ\u009f¹å\u001e\u0097÷ìf\u001eÙ#\u009a\u0017~\u0086æbÏOá¾0Ç\räõN²\u008dÎÀi¶8a\u008cÇ\u009cÎ?È\u000bHÝÖ\"\u0081\u009e\u009a`î4¨Ë\r\bäÖ\u0092¾´ÖÙV\u0010¼@\u0083<æl)ÒI÷çº{>°?\u00197\u0005eÓSs\u001e4\u000b~fì²|`Ë#|í\rö6.ã£ÜÂp@\u00ad\u00976ìX_Ý\u0088âÕº \t)=Íº#Ù©[ô×6y\u007f\u000e0Î*ým\u00961Kïý¼\u0092âF\u0012°i\u0014\u0093ªò0½à{½H\u0082ðt¹ä\u008bÇò,©U$Ç \u0087W}ñ1h#nPÜ`B@\u001eLÝ3ÑS_§ÝbÞ¥@\u0082ÙXá>\u009a\u0083Ä\u0002îQæ\u0094\u0012eM´²\u007fFö©º¨iáeÂp\u009d\u008b\u009d=åå\u0011Ð\t\u000e\u0081\u000e\u000fÿK[\u0092¨Ûzÿ\u0006Æâ\u0099&>V9£K\u001c4:\u0095`MR\u0015\u008aÜÚþxj\rçá{\u0005·u\u0088\u000b®¥'<AO\u0016p^6b\u008d¡ü\u0091*ß\u0082\u0007Ò ÿï\u0001ò©Bç\bçK\u0080\u0000-Â\u0005bVÂ\u0093k\u007fýnJÃ\u0081hZ¨nr\u0012h²`\\\u009d\u0088¢W\u009bZl\u008e`\u009aË{ÂÄ\u0014\t±IëfàHé½\u0004µFø\u0088\u00ad\u0007(@\u00815C\u001e\u0098ÜÝ¿Ñ»\u0085N®xà2Ð\u0097\"M\u0005WW\u00804}Edá!\u009dá¯\u0092f 7Ë\u0002ì:hÌ]ùª\u008a¾3\u008fýP\u001f\u0088Õ\u0099mbOß|@à0¯\u000b\u008eÑõä\u0083\u008fÊRì\u009fá\u0081D/l:¬ea¨p\u009e\u0092¡P±~ñ\u008b\u0091iY¤×h)ÏãJ\u0013éë1N^/X«ó·CWÎÕ\u001e\u0000\u0016KÃ\u0012@ø(\u001d«1\u0080Ò3Ú¦¯¥ÞâÎ\u0081+«ã\u009e\u009b\u0019ð\u0090?ÂmHöRZ,\u001dúëü'\u009eRþüµy'úXg -_\u001dÐ'®§TìfÄóä\u009b`òXÎh\u0007ô·\u008aQï\u008f\u001cQ\u008b\u000b]\u0093\u007f®mÖ1í\u001bñ3\u0095î£,bÌ\u0001l\u0098ûB9LD\u0087¤_Rwà«©¡ºÍPØâ\t\u001eëÜ\u008b\u008cèá\u0093w,\u0083C¤üeÙ²ÉÞCoð£,MVä¥ÆâÓk2?K(ÝíÂÛ\u0091÷i¿\b\u0093lÇåH\u00049\u0080Gø\u009b¿úòÒñ¸\u0080\u008e\u0016¯gÚ\u001dÝ¬·B\u0087iÙP ²©Ý\u0011åÀÆ©\u009f\u008c¼\u0014\u0095-ôA\u007fdÞ\u00859Rì\u0006\u0016\rü6\rêÁ/\r¤ÄrWF\u0010féýa\u0099qÔE\u0003\u009c°qB\\\u0015z'7¶q¢\u008c`³È\u009f?þ\u00880)Þ¾\u0019mä=®G\u0088`\u0081ª \u000fa\u0002M=fÕ¬<ë\u0014\u0082TÚç;³eb¸¶\u001b\u0087l\u001c<Ab ^\u001fÄ7\u00ad\u000b\u0087\u0089\u009aË{í\u0082t~ÑA\u009fÞÌ¬ùpvMw\u0005Ð\u0085´µÎ*\u001f¾»\u008b\u009d¬ÞÂÔeM×L°]\u0083 4\u0007Î\u008fJ;\u0087\u009d\u0019±\u009euªnPN\u001bÕnÚÍRÕ9B\u0019c£\u0088àËðÚk3üi6Pi®\u0007\u0083AlÞg_6æh5\u0088û\u0018\u001f.¾\u0010~Ùbà¯Ý\u0088¥ ü9ï H5þ\u008c¦%ñá±ö¸+bP\u001bç\u0006\u009aç\u001dÀÿ[P\u009c«\nÞkxñKúÅ´)¿ÂZ\u001b®=ßÄòF$ØYÙ^fõm\u0089ó]\u0016U]Ê\u0092?.¶\u0017\\á\rW¾\bâgíx`\u007fhu\u0096\fUvr}T\u007fð\u000f\fi\bUn\u009f¤\u0082¤%\u007fòd¾0Ú\u0083ð@^Q\u001el+¿\u0097²\u0003v.óBÃøGKMÕq\u000eYó\u0017l¡/øô´ãÍ¾?Ü&\u0098´\u0007g\u0007([¼Pz´¯ÍÛ÷Î`\u001eÝ\u0080\u0004ÌPÌ`Æ\u0089Ú\u0085]\u0012\"F}Sí.&Ì!\u001f\u0099>L\u0089bÌ¤g\u009e×|Óde0j`òû6Õb\u0000n%²\u0086Bb\u0003F\u0085!MïPÉS\u0017ÂJp[f}} àX¤Ç_\u009c½\u0080e8\u000e\u000f\u001eÍôÎÚl\u0005\u0015S\u0003e¬-|k|\u000b\u0001GJÔÔbÍæ\ná\u008d\r¼IN(§i\u0015V\u001d¬W\"0É¿nô«µ°\bÈå\u001b(\u001c4\u0081\u008eTu4å\u0003Ö¦\bx\u001cD¸C\\WW`[°$H@ð²Sz¹±Î\u001fv\u001a³Z8\u0083Sl\u0093xÜ¼Ä\u0084Y\u000eØ\u0000.\u0092ùÚsç+£;yãR\bS\u008dT»\u008c\u0019g\u0001Å\u0084LÕ4o\u0019\u0014j\u000b\u0006x\u008a] º¥±\u0084\r\u0085Jß@XaÐWÖ7¼¥\u000f¾¢C\u001be\u000e6Ä\u009d+¬\u0014\u0088&í\u0085\u0080ÑYÞòÊ©\u0085\u009d8'\u0088üÂqCôp;ö\u0098\u0088J\u000b\u001dN\u000e\u001bu!C\u009fjQ-[LM\u0004ùr|2ú\u0015B\u0081°@ÇÄ\u0017\u0084Dv\u009efÈBH\rdù(h\u00140!s£Ê¯±O\\\u001d\u007f¦÷åª+Äë\u001f\u00019JóÜ¤íô.Ùd;±ç®\u0002 8òJ\u008c\u0098¹8ß\u007fÍdÝ;mä%ì\u0094í|\u0017\u0084Äm¿W\u0093:7ü\u0014\u0007\u008cáÓÌ\t°\u001fð2ó\u00004ºñ\b¥8cj3f\u0081¸l\u0081\u00ad®@\u001f°\u009a\u008aÝ\u008c±H32kU^\u0091{°HCèëwÕoÞÇp\u0015\u0002ö_,Sá\u001eRÅqç)#ëØ\u009eC\u00adÕ¨Î\u009d\u0083®î\u0095\u009a¸\u0006t²\u0091p-1|l\u008bpÂ¶)_\u0005Óã26\u008d¢©&3`Hç\u00917´âÎél|(þülè¤É\u0093Òà\u0089äµÀ RÀP\u0086|² W\u0016,ß\u0099\u009f\u0084Â|gÎìw\u0087?rrÂ%Ç\u0003NhÕÞõJ\u001csz¶éæD\u0019\n\u0000\u00adx±\u0017*tì\u009dÂ\u0011³?3¢`Ö%'ª¯\râªèÒ*BPi\fmçé\u00872Só\u001eP¿\u0083þ\u0007G§j\u0089Y\u0082\u001eë¶b~Â\bÞUÅå Xw-w\u001fïLI/U\u0089o°\u0081þ\u0006\u0089»u5)¾¼bW\u0094¯K:ªCo\u009a¸\u0011Ê\u0007ÞÀ\u009bã×Ä\\¾AyhVp\rÅÑ\u0084¥!¿W)¹~\u0097\u0099v\u0019H\u0092yêø\u009b\u000el6\u000eobÄ®UTv´Å~«\u008c\u0014ì\u009b2Ô\u009e~\u0094¯L\u0005\\\u0090³\u0012<\u0016\u0082\u008dd·ª&+\n\u0015C\u0096Õ\u0090kÁ\u001fd\u0019\u0014k\u0091!ÅÐBs\u001c¾PÖûõ8Q\u0091°ëÍ\u0086Þïó¹\u0096Ñ\u008d\f\u001a\u0010³¦Õ\u0091\n¸ÿtíº\u008e'B\u0001q\u001cXLÑw(°àê\u001czµ1<Ó\u0090\u0011o\u0006S:]XÕ$®÷b0Z1\u0004xTg\u001aéD\u00046ÂÀÆî\t¯Ý¡]÷¾ù\u0088áÎdÎ×¾\u009bØc|\u009bábHkJ\u009aî\u0090Ø\u0094\u000fR\u009d0à¨\u009e\u0097Ú\u0000òÖÜØâ\u0091H~\u0084È\u0092XEdÕ\u008a\u0014ö\u0092\u0087G\u0080U\u0098\u001d\u008e\u0019j\u008a0ÓàIË!%\u0087\u0083Y\u0087\u001bhú¿\u0085É%?,Ç\u008eÆÊÁýY²\u000eâ\u0082Æ\u001eäY%e¾`\u0004å^W\u008bÊ6ë¾[wX=¢\u0002\u0097H³M\u0082Ý\u008dÎ\u0080\f®ù'âæ(\b\u0085Ìr((0:\u0018\u001a\u008e+ñÉ\u0099\u0006\u009aP*lý¼b>ÊußB\u0097¯ü\u0093\u000f3\u0084Ø.\u0096I\u0001*\u000b{?¸\u008bý½\u0091\u008f\nw½æP\u00ad¨5D~^,dÂ°ÎQáÅ(3\u000f\u0080ö \u0081øz\u001e)qJl\u0019^\u0083\u0083ÈÆ\u009cèÙ\u007f[¡Â\u008eØ\u0084!\u0094QøEÚ¿+Ø\u0096\u008f8e\u0083\u0087\u0016a\u000e<vÕéµD\u0090\u008fÊà¡\n\u0095Q®±Q\u0000×Ú\fõ]ïjôÑð\u009eúSV\u0011*}\u00adSöæ\u000fë\u00870SPAÿ\u008e\u009cåH\u009ffkOQ-\u008f\u0013B\u00064l\u0010c<Èû~ D\u0010áÅÑjFK\u009d\rã\r7ìepH\u008c\u0019\u0088ðé}(Ó«\u0095\u001b\tÿM×\u00ad\u008e\u0093I¨ã\u00019º©Ï\u0010¢\u0099EN[\u001e\u009f\u009fÑX(®\u0000\"¨p\u0001Yø0iaåÏ¯ÂL÷î$\u0016M\u000bU ÃÜ\u0000â\u009a\u0001\u0019m\u0080½óq\u0095öÊ\u009b`\u0018Õ\u009b±Í\u0014ü)\u001bBÞ7¨ÁMy\u0087\u001e#\u008f\u0000Ï\u009dåNås\u0085\u0086_Þ¿£YÚ\u0014ã¡Uqè\u0084#ý¢Ì\u0010h¼Xc+e\u0080i6e\fiÌ^\u001f\u0096X\u001f¸\u008c\u009a\u0001\u0097¤iøÈÍ\u0002íö\u0092¦Mc\u0019;\u009bÚê\u001bí\u00ad¦mµ¤áÞ\rè\u0017G\u009f<Ô²ÏØlHlF\"b\u0092\u0082ß«Á\u0015\u0011\u000bn%ÚLGåÞ\u0095Ð\u000bhH\u001cÚ;EuA\u0099j\u0095\u0082T(\ræïìÇç\u001b\u0092`ø|ÇaÆù\u009eOr¸\u009c\u001bä\u009eÍF\u0087s°*ã\t]{jt~òði<ë\u009co@ì>×{\b\u0011cÄÓ\u008cÊ\u0092^²'¬$N¹¼-)E\u001f\u001b¬0.]\u0083E\u0010ÎÎ4\u0001«Y\u00919rA./N\u009b\u009dTb?G\u0081÷-/ÚÊ\u0001£ôJ"
            .length();
         char var14 = '`';
         int var26 = -1;

         label67:
         while (true) {
            String var27 = var15.substring(++var26, var26 + var14);
            int var10001 = -1;

            while (true) {
               byte[] var19 = var11.doFinal(var27.getBytes("ISO-8859-1"));
               String var39 = b(var19).intern();
               switch (var10001) {
                  case 0:
                     var18[var16++] = var39;
                     if ((var26 += var14) >= var17) {
                        k = var18;
                        m = new String[89];
                        t = new HashMap(13);
                        Cipher var0;
                        var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var1 = 1; var1 < 8; var1++) {
                           var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                        }

                        (var0 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var6 = new long[85];
                        int var3 = 0;
                        String var4 = "\u0082Ï»D\u0087\u001a¥±~v\u00938\u0018Ë£:ØP\u008f\u0017Ê¤¬áAXbìÅê\u008eÁ\u008e>b\u0089¹\u0001®ãG\u0097\u0097\u001bÝÍ\u008d%_Â\u0098<(\u001e¢1\\\u007fD\u0095\u0091¤¢5c[\\\u008eëÉRõN6[øÈ ^hã^úô®ã¢&CM\u009dDé6gE c\u000eÑrV\u0005¤<~\u00ad\u0084ÉwU\u0093Üc\u0013vßÊ\u008cµÊ\u001a½Ô\u0005\u0083¼>¼û2¥De\u0092\u0019\u0097P\u0081/¨\u008e¯r.[¹Sñ\\.äæ6@µe_²MôÁ\u008b\u001d\u0089_\u0085Am\u008fc¾°\u0083\u0084ßujZ\u0092³\u0004:\u0019YRwã\u008b\u009a\u001fØ*\u0099\u0088ºí\u0089Ð\u000bî½_ë\u001c\u000b\u009fÅ\u000b\u0002)w\u008f¸ó\u0087\u0091÷Ç8\u000e\u001bíÜTÃÈ`Áæñýy\u0099\u0091/pòÕ\u0084Ò\u0019À\u0082>\u0094·ÕûÚ @k\f\u007fÒÍî]×C*'j\u001fâ\fï«=Ñ@ag\u0088\u0090=à\b)¶\u00ad\u0092ó´w´pÂ°^¸¤¨\u0004ë\u0095ZT\u0014\u0099Ó²\u0019cYe\u0010:ºÇ`ÿÓ\u008eít½\u0019 pHM)kÛÙ\u0082ÚY\u0083Õcõ\fu{b@Ð°è\"[rWÍWwn-\b|Q^\u0084Ì=\u008a\r\u001d@Ðß\u0093¾\u0005\u0090&\u0083:A\f\u0087t\u0083\u001d\u0089\u0086\u001aûÇåx]\u0093æRºÿc.lÝ\u0010\u0087\u0013W?ì÷$:µH\u009eZûxBWP\u0007\\I\u0080³\bÿÈ\"\u000fâ¸\u009fÖzrÁÑMÈ_\u009e}Ý\u001f¿å\nz¸ì\u0097¤)\u0015\u0099\fÆü2öîËB\u0019ÆÕgÿ\u0089\u009d\u0083Ã\u001dàøÞ\u000eÉ\u0090\t´µ¡£\u009a)BÂ´ßBVëèC\u001dù³|ôÖ\r\u009b26r´·.\"\u000b*\u0095`ÝêT2Î\rÅ$\u0088ÆNk\u001c¾¥~\u009d\u0013 5ñô´\u008d\u0005\u0099Íj\u0099¨.\\GÒ6üÍVTMð¾¿5\u009fCÅa\u007fµá!tò\u00adû§\"¼Ü£>í\u000eVõ,Dñ\u0005á\u00884¬ÎË\u0010·m»\u008d\u008aÕf\u0019ËÅ J\u0099\u0081/RW8ñ\u0091\u0097Z\tT7ð\u000fït\u001b\u008f\u00adh¿[\u0087Ñ~Bgf\u0099 éqrB\tðÚëñ0\u0013?\\ò¯ü\u0099ë\u0092´BË\u0010\réÅ.Iú\u000b\u008d\u0018¿¸L79";
                        int var5 = "\u0082Ï»D\u0087\u001a¥±~v\u00938\u0018Ë£:ØP\u008f\u0017Ê¤¬áAXbìÅê\u008eÁ\u008e>b\u0089¹\u0001®ãG\u0097\u0097\u001bÝÍ\u008d%_Â\u0098<(\u001e¢1\\\u007fD\u0095\u0091¤¢5c[\\\u008eëÉRõN6[øÈ ^hã^úô®ã¢&CM\u009dDé6gE c\u000eÑrV\u0005¤<~\u00ad\u0084ÉwU\u0093Üc\u0013vßÊ\u008cµÊ\u001a½Ô\u0005\u0083¼>¼û2¥De\u0092\u0019\u0097P\u0081/¨\u008e¯r.[¹Sñ\\.äæ6@µe_²MôÁ\u008b\u001d\u0089_\u0085Am\u008fc¾°\u0083\u0084ßujZ\u0092³\u0004:\u0019YRwã\u008b\u009a\u001fØ*\u0099\u0088ºí\u0089Ð\u000bî½_ë\u001c\u000b\u009fÅ\u000b\u0002)w\u008f¸ó\u0087\u0091÷Ç8\u000e\u001bíÜTÃÈ`Áæñýy\u0099\u0091/pòÕ\u0084Ò\u0019À\u0082>\u0094·ÕûÚ @k\f\u007fÒÍî]×C*'j\u001fâ\fï«=Ñ@ag\u0088\u0090=à\b)¶\u00ad\u0092ó´w´pÂ°^¸¤¨\u0004ë\u0095ZT\u0014\u0099Ó²\u0019cYe\u0010:ºÇ`ÿÓ\u008eít½\u0019 pHM)kÛÙ\u0082ÚY\u0083Õcõ\fu{b@Ð°è\"[rWÍWwn-\b|Q^\u0084Ì=\u008a\r\u001d@Ðß\u0093¾\u0005\u0090&\u0083:A\f\u0087t\u0083\u001d\u0089\u0086\u001aûÇåx]\u0093æRºÿc.lÝ\u0010\u0087\u0013W?ì÷$:µH\u009eZûxBWP\u0007\\I\u0080³\bÿÈ\"\u000fâ¸\u009fÖzrÁÑMÈ_\u009e}Ý\u001f¿å\nz¸ì\u0097¤)\u0015\u0099\fÆü2öîËB\u0019ÆÕgÿ\u0089\u009d\u0083Ã\u001dàøÞ\u000eÉ\u0090\t´µ¡£\u009a)BÂ´ßBVëèC\u001dù³|ôÖ\r\u009b26r´·.\"\u000b*\u0095`ÝêT2Î\rÅ$\u0088ÆNk\u001c¾¥~\u009d\u0013 5ñô´\u008d\u0005\u0099Íj\u0099¨.\\GÒ6üÍVTMð¾¿5\u009fCÅa\u007fµá!tò\u00adû§\"¼Ü£>í\u000eVõ,Dñ\u0005á\u00884¬ÎË\u0010·m»\u008d\u008aÕf\u0019ËÅ J\u0099\u0081/RW8ñ\u0091\u0097Z\tT7ð\u000fït\u001b\u008f\u00adh¿[\u0087Ñ~Bgf\u0099 éqrB\tðÚëñ0\u0013?\\ò¯ü\u0099ë\u0092´BË\u0010\réÅ.Iú\u000b\u008d\u0018¿¸L79"
                           .length();
                        int var2 = 0;

                        label49:
                        while (true) {
                           var10001 = var2;
                           var2 += 8;
                           byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                           long[] var30 = var6;
                           var10001 = var3++;
                           long var43 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           int var46 = -1;

                           while (true) {
                              long var8 = var43;
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
                              long var48 = (var10[0] & 255L) << 56
                                 | (var10[1] & 255L) << 48
                                 | (var10[2] & 255L) << 40
                                 | (var10[3] & 255L) << 32
                                 | (var10[4] & 255L) << 24
                                 | (var10[5] & 255L) << 16
                                 | (var10[6] & 255L) << 8
                                 | var10[7] & 255L;
                              switch (var46) {
                                 case 0:
                                    var30[var10001] = var48;
                                    if (var2 >= var5) {
                                       r = var6;
                                       x = 4;
                                       R = new String[]{"[R]", "[G]", "[Y]", "[B]"};
                                       c = new String[]{"c", "a", "e", "9"};
                                       String[] var31 = new String[82];
                                       var31[0] = "(\\w{1,16}) got banana pistol'd by (\\w{1,16}).*";
                                       var31[1] = "(\\w{1,16}) was peeled by (\\w{1,16}).*";
                                       var31[2] = "(\\w{1,16}) was mushed by (\\w{1,16}).*";
                                       var31[3] = "(\\w{1,16}) was hit by a banana split from (\\w{1,16}).*";
                                       var31[4] = "(\\w{1,16}) was killed by an explosive banana from (\\w{1,16}).*";
                                       var31[5] = "(\\w{1,16}) was killed by a magic banana from (\\w{1,16}).*";
                                       var31[6] = "(\\w{1,16}) was turned into mush by (\\w{1,16}).*";
                                       var31[7] = "(\\w{1,16}) was shot and killed by (\\w{1,16}).*";
                                       var31[8] = "(\\w{1,16}) was snowballed to death by (\\w{1,16}).*";
                                       var31[9] = "(\\w{1,16}) was killed by (\\w{1,16}).*";
                                       var31[10] = "(\\w{1,16}) was killed with a potion by (\\w{1,16}).*";
                                       var31[11] = "(\\w{1,16}) was killed with an explosion by (\\w{1,16}).*";
                                       var31[12] = "(\\w{1,16}) was killed with magic by (\\w{1,16}).*";
                                       var31[13] = "(\\w{1,16}) was blocked by (\\w{1,16}).*";
                                       var31[14] = "(\\w{1,16}) was put into cold storage by (\\w{1,16}).*";
                                       var31[15] = "(\\w{1,16}) was deleted by (\\w{1,16}).*";
                                       var31[16] = "(\\w{1,16}) was purged by an antivirus owned by (\\w{1,16}).*";
                                       var31[17] = "(\\w{1,16}) accidentally closed the game while fighting (\\w{1,16}).*";
                                       var31[18] = "(\\w{1,16}) had their computer switched off by (\\w{1,16}).*";
                                       var31[19] = "(\\w{1,16}) had their computer fried by (\\w{1,16}).*";
                                       var31[20] = "(\\w{1,16}) was filled full of lead by (\\w{1,16}).*";
                                       var31[21] = "(\\w{1,16}) was iced by (\\w{1,16}).*";
                                       var31[22] = "(\\w{1,16}) met their end by (\\w{1,16}).*";
                                       var31[23] = "(\\w{1,16}) lost a drinking contest with (\\w{1,16}).*";
                                       var31[24] = "(\\w{1,16}) was killed with dynamite by (\\w{1,16}).*";
                                       var31[25] = "(\\w{1,16}) lost the draw to (\\w{1,16}).*";
                                       var31[26] = "(\\w{1,16}) was struck down by (\\w{1,16}).*";
                                       var31[27] = "(\\w{1,16}) was turned to dust by (\\w{1,16}).*";
                                       var31[28] = "(\\w{1,16}) was turned to ash by (\\w{1,16}).*";
                                       var31[29] = "(\\w{1,16}) was melted by (\\w{1,16}).*";
                                       var31[30] = "(\\w{1,16}) was incinerated by (\\w{1,16}).*";
                                       var31[31] = "(\\w{1,16}) was vaporized by (\\w{1,16}).*";
                                       var31[32] = "(\\w{1,16}) was struck with Cupid's arrow by (\\w{1,16}).*";
                                       var31[33] = "(\\w{1,16}) was given the cold shoulder by (\\w{1,16}).*";
                                       var31[34] = "(\\w{1,16}) was hugged too hard by (\\w{1,16}).*";
                                       var31[35] = "(\\w{1,16}) drank a love potion from (\\w{1,16}).*";
                                       var31[36] = "(\\w{1,16}) was hit by a love bomb from (\\w{1,16}).*";
                                       var31[37] = "(\\w{1,16}) was no match for (\\w{1,16}).*";
                                       var31[38] = "(\\w{1,16}) was smote from afar by (\\w{1,16}).*";
                                       var31[39] = "(\\w{1,16}) was justly ended by (\\w{1,16}).*";
                                       var31[40] = "(\\w{1,16}) was purified by (\\w{1,16}).*";
                                       var31[41] = "(\\w{1,16}) was killed with holy water by (\\w{1,16}).*";
                                       var31[42] = "(\\w{1,16}) was dealt vengeful justice by (\\w{1,16}).*";
                                       var31[43] = "(\\w{1,16}) was returned to dust by (\\w{1,16}).*";
                                       var31[44] = "(\\w{1,16}) be shot and killed by (\\w{1,16}).*";
                                       var31[45] = "(\\w{1,16}) be snowballed to death by (\\w{1,16}).*";
                                       var31[46] = "(\\w{1,16}) be sent to Davy Jones' locker by (\\w{1,16}).*";
                                       var31[47] = "(\\w{1,16}) be killed with rum by (\\w{1,16}).*";
                                       var31[48] = "(\\w{1,16}) be shot with cannon by (\\w{1,16}).*";
                                       var31[49] = "(\\w{1,16}) be killed with magic by (\\w{1,16}).*";
                                       var31[50] = "(\\w{1,16}) was glazed in BBQ sauce by (\\w{1,16}).*";
                                       var31[51] = "(\\w{1,16}) was sprinkled with chilli powder by (\\w{1,16}).*";
                                       var31[52] = "(\\w{1,16}) was sliced up by (\\w{1,16}).*";
                                       var31[53] = "(\\w{1,16}) was overcooked by (\\w{1,16}).*";
                                       var31[54] = "(\\w{1,16}) was deep fried by (\\w{1,16}).*";
                                       var31[55] = "(\\w{1,16}) was boiled by (\\w{1,16}).*";
                                       var31[56] = "(\\w{1,16}) was squeaked from a distance by (\\w{1,16}).*";
                                       var31[57] = "(\\w{1,16}) was hit by frozen cheese from (\\w{1,16}).*";
                                       var31[58] = "(\\w{1,16}) was chewed up by (\\w{1,16}).*";
                                       var31[59] = "(\\w{1,16}) was chemically cheesed by (\\w{1,16}).*";
                                       var31[60] = "(\\w{1,16}) was turned into cheese whiz by (\\w{1,16}).*";
                                       var31[61] = "(\\w{1,16}) was magically squeaked by (\\w{1,16}).*";
                                       var31[62] = "(\\w{1,16}) was hit by a flying bunny by (\\w{1,16}).*";
                                       var31[63] = "(\\w{1,16}) was hit by a bunny thrown by (\\w{1,16}).*";
                                       var31[64] = "(\\w{1,16}) was turned into a carrot by (\\w{1,16}).*";
                                       var31[65] = "(\\w{1,16}) was hit by a carrot from (\\w{1,16}).*";
                                       var31[66] = "(\\w{1,16}) was bitten by a bunny from (\\w{1,16}).*";
                                       var31[67] = "(\\w{1,16}) was magically turned into a bunny by (\\w{1,16}).*";
                                       var31[68] = "(\\w{1,16}) was fed to a bunny by (\\w{1,16}).*";
                                       var31[69] = "(\\w{1,16}) starved to death\\.";
                                       var31[70] = "(\\w{1,16}) hit the ground too hard\\.";
                                       var31[71] = "(\\w{1,16}) blew up\\.";
                                       var31[72] = "(\\w{1,16}) exploded\\.";
                                       var31[73] = "(\\w{1,16}) tried to swim in lava\\.";
                                       var31[74] = "(\\w{1,16}) went up in flames\\.";
                                       var31[75] = "(\\w{1,16}) burned to death\\.";
                                       var31[76] = "(\\w{1,16}) suffocated in a wall\\.";
                                       var31[77] = "(\\w{1,16}) suffocated\\.";
                                       var31[78] = "(\\w{1,16}) fell out of the world\\.";
                                       var31[79] = "(\\w{1,16}) had a block fall on them\\.";
                                       var31[80] = "(\\w{1,16}) drowned\\.";
                                       var31[81] = "(\\w{1,16}) died from a cactus\\.";
                                       E = var31;
                                       I = new Pattern[E.length];

                                       for (int var22 = 0; var22 < E.length; var22++) {
                                          I[var22] = Pattern.compile(E[var22]);
                                       }

                                       return;
                                    }
                                    break;
                                 default:
                                    var30[var10001] = var48;
                                    if (var2 < var5) {
                                       continue label49;
                                    }

                                    var4 = "RièÝV·Å\u0011`©\u00119Ý d\u0001";
                                    var5 = "RièÝV·Å\u0011`©\u00119Ý d\u0001".length();
                                    var2 = 0;
                              }

                              int var37 = var2;
                              var2 += 8;
                              var7 = var4.substring(var37, var2).getBytes("ISO-8859-1");
                              var30 = var6;
                              var10001 = var3++;
                              var43 = (var7[0] & 255L) << 56
                                 | (var7[1] & 255L) << 48
                                 | (var7[2] & 255L) << 40
                                 | (var7[3] & 255L) << 32
                                 | (var7[4] & 255L) << 24
                                 | (var7[5] & 255L) << 16
                                 | (var7[6] & 255L) << 8
                                 | var7[7] & 255L;
                              var46 = 0;
                           }
                        }
                     }

                     var14 = var15.charAt(var26);
                     break;
                  default:
                     var18[var16++] = var39;
                     if ((var26 += var14) < var17) {
                        var14 = var15.charAt(var26);
                        continue label67;
                     }

                     var15 = "c\u009f)j'\u008e\u001e\u0089\u009dJ\u0011c¢\u009a¡OU±/\u0005þÖ\u001dÙém\u001cöáò÷ù^ÅD\u0092ÑbÒ;iØ7a:ÜoëP\u009ei×\u008eo\u0001Cúv\u0095{\\Þ\b\u0089íÅ\u0088;æ;\u0013BV\u0081\u0085kû\u007fÚwbzöQ§ù\nm\u0088Ïz>ãÎÔ!¸¼H$[\\A\u008fuAítÜmå`\u0096Ò<\u009eØ\u000eA®\u008bC×<L|\u00adÚZ";
                     var17 = "c\u009f)j'\u008e\u001e\u0089\u009dJ\u0011c¢\u009a¡OU±/\u0005þÖ\u001dÙém\u001cöáò÷ù^ÅD\u0092ÑbÒ;iØ7a:ÜoëP\u009ei×\u008eo\u0001Cúv\u0095{\\Þ\b\u0089íÅ\u0088;æ;\u0013BV\u0081\u0085kû\u007fÚwbzöQ§ù\nm\u0088Ïz>ãÎÔ!¸¼H$[\\A\u008fuAítÜmå`\u0096Ò<\u009eØ\u000eA®\u008bC×<L|\u00adÚZ"
                        .length();
                     var14 = '0';
                     var26 = -1;
               }

               var27 = var15.substring(++var26, var26 + var14);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var23) {
         throw new RuntimeException(var23);
      }
   }

   static {
      offsetX = new NumberSetting("Offset-X", 4.0F, 0.0F, 1000.0F, 1.0F);
      scale = new NumberSetting("Scale", 1.0F, 0.5F, 3.0F, 0.01F);
      backgroundOpacity = new PercentageSetting("Background-opacity", 40);
      offsetY = new NumberSetting("Offset-Y", 104.0F, 0.0F, 1000.0F, 1.0F);
   }
}
