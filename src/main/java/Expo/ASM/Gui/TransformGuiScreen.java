package Expo.ASM.Gui;

import Expo.ASM.Hooks.Gui.GuiEventHooks;
import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;


public class TransformGuiScreen extends TransformerBase {
   private static Object[] P;
   private static String[] l;
   private static String[] i;
   private static Map O;
   private static Map t;
   private static long[] w;
   private static long d;
   private static String[] Z;
   private static String G;

   private static void b() {
      P[0] = "$\\h\u0004i\u00162i69~";
      P[1] = "\u0012*?]<p\u0017=;\u0007$w\u001fv9\u0000><\t*=\u0016}[\u0013+6?:a\t";
      P[2] = int.class;
      Z[2] = "java/lang/Integer";
      P[3] = void.class;
      Z[3] = "java/lang/Void";
      P[4] = ")$$|QY\"+53,A1,<z";
      P[5] = "yG\u0007:e\u000e|P\u0003`}\tt\u001b\u0001ggBbG\u0005q$-tF\u0014fk\u000fb|\u000egd\"yQ\u0005";
      P[6] = "M@R=\u000f\n[u\f\u0000";
      P[7] = "E6\u000b9\u0014\u001f@!\u000fc\f\u0018Hj\rd\u0016S^6\trU0O0\u0004x\u001f3E \t";
      P[8] = "~iG\u001d\u007f){~CGg.s5A@}eEbPV";
      P[9] = boolean.class;
      Z[9] = "java/lang/Boolean";
      P[10] = "n\u000eP&\u000e\u0005x;\u000e\u001bq";
      P[11] = "H\u0015j2Q\u001cC\u001a{}<\u001cC\u0007o";
      P[12] = "_O2_5}IzlbB";
      P[13] = "\u0006T]\u001aEl\u0003CY@]k\u000b\b[GG \u001dT_Q\u0004M\u0005GIGda\rC";
      P[14] = "\u0010Hw#o<\u0006})\u0018";
      P[15] = "4I\u000e\u001d\\1Ai\u0005\u0012M~<q\u0016\u0015D7T";
      P[16] = "G\u0017LXO/L\u0018]\u0017.!G\u0013YM";
      P[17] = "v\r{PCH\u007fW:i}'\"\f-Y]VbO\"\u0019 \u001c I{QF\u001agY*i\u001b\u001c`\tx\u000f\u001d[pX@R\u001b\\ \n&T\\Lq2zTBBdK+RD\\\u001b";
      P[18] = ",\u0018\\}\u0017<rF\u0000ce-\u0010D\u0017h\u0004`}\u0004Zs\u001cP.B\u00023\u0003n*\t\u0004=enzF\u001b`\u0006.+\u001b\u0017\f[:.\u0005\no\u001bks\tf3\u0005`k@\u001dc\u00062/xXf[-|\u001b\u00187\u0006!\u0010";
      P[19] = "-?>Eg\u0015`mn\u001c\u000e$\u001dje\u00025L{l\"\u0012dt&j%B6\u0012 -5\u0013\u000e";
      P[20] = "\u000f[&\u0000\u0003*\u0006\u0001g9!E[Zp\t\u001d4\u001b\u0019\u007fI`|\u001b\u001abD\u0001(\u0005\u0003f9Zx\u0000\u0001b@\u000b~\u0006\u001f\u001d";
      P[21] = "@l\u001bG:\u0016\u0000/\u0014\u0007G\u000e\u0015>vL'\n\u0001=N\t.\r\bRLJ%\u0002\u0006+\u001dL#\u001cy";
      P[22] = "\u0005C d8G[\u001d|zJn9\u001fkq+\u001bT_&j3+\u0007\u0019~*,\u0015\u0003Rx$J\u0014Y\u0013a-1DZA%\u0015tA\u0007^vv4\u0010ZR\u001a";
      P[23] = "NH yU`G\u0012a@U\u000f\u001aIvpK~Z\ny065\u001e\u0015~?Od\u0018\u0013`@";
      P[24] = "\u0010r\u0002vDFN,^h6G,-]vOEFoF\u007f_*\u0017)C<\u000eL\u0011nSm6";
      P[25] = "*R0\u001cn\u001b#\bq%Ot~Sf\u0015p\u0005>\u0010iU\rH6\tj\u0015`\b{\u0012r%7I%\bt\\fO#\u0016\u000b";
      P[26] = "r24qsr{huHl\u001d.62/u'thmv\u0010&$v4pv cfeH!cst\u007fxps!t\u000f!~zqpt7+-r\r1\".`snqssl\u001f";
      P[27] = "\u000f\u0002(>\r&\u0006Xi\u0007?I[\u0003~7\u00138\u001b@qwnrYF(?\bt\u001eVy\u0007Ur\u0019\u0006+aS5\tW\u0013<U2Y\u0005u:\u0012\"\b=):\f,\u001dDx<\n2b";
   }

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/client/Minecraft") + "II)V", (var0, var1x) -> {
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
            long var2xx = d ^ 105888018897685L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.Y(var0x, G, "onInitGui", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + ")V");
         });
      }, "setWorldAndResolution", "setWorldAndResolution");
      var4 |= BytecodeHelper.t(var1, "(III)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var1xx, var2xx) -> {
            BytecodeHelper.k(var1xx);
            BytecodeHelper.J(var1xx, var1x);
            BytecodeHelper.I(var1xx, var2xx);
            BytecodeHelper.Y(var1xx, G, "onMouseClicked", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + "III" + z + ")V");
         });
      }, "mouseClicked", "mouseClicked");
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 16482177604609L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, f, "onHandleKeyboardInput", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + z + ")V");
         });
      }, "handleKeyboardInput", "handleKeyboardInput");
      var4 |= BytecodeHelper.t(var1, "(IIF)V", (var0, var1x) -> {
         boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 34863182981569L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, f, "onDrawScreen", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + z + ")V");
         });
         return var4x | TransformerBase.M(var1x, Type.VOID_TYPE, (var1xx, var2xx) -> {
            BytecodeHelper.k(var1xx);
            BytecodeHelper.J(var1xx, var1x);
            BytecodeHelper.Y(var1xx, f, "onPostDrawScreen", "(" + SrgNames.X("net/minecraft/client/gui/GuiScreen") + "IIF)V");
         });
      }, "drawScreen", "drawScreen");
      var4 |= BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 82714709907185L;
            BytecodeHelper.Y(var0x, f, "shouldCancel", "()Z");
            LabelNode var4x = new LabelNode();
            var0x.add(new JumpInsnNode(153, var4x));
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.U(var0x, BytecodeHelper.P, "cancel", "()V");
            var0x.add(var4x);
         });
      }, "drawDefaultBackground", "drawDefaultBackground");
      return var4 | BytecodeHelper.t(var1, "(I)V", (var0, var1x) -> {
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 52849497794798L;
            BytecodeHelper.Y(var0x, f, "shouldCancel", "()Z");
            LabelNode var4x = new LabelNode();
            var0x.add(new JumpInsnNode(153, var4x));
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.U(var0x, BytecodeHelper.P, "cancel", "()V");
            var0x.add(var4x);
         });
      }, "drawBackground", "drawBackground");
   }

   public TransformGuiScreen() {
      super("net/minecraft/client/gui/GuiScreen");
   }


   static {
      d = 122206050968871L;
      zkm$clinit();
      G = TransformerBase.e(GuiEventHooks.class);
   }

   private static String a(byte[] var0) {
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

   private static void zkm$clinit() {
      try {
         long var0 = d ^ 51472229688949L;
         P = new Object[28];
         Z = new String[28];
         b();
         t = new HashMap(13);
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var4 = new String[36];
         int var5 = 0;
         String var6 = "\u0099\u0000ÈaÍí·§dLªáð©LØ\u001ax)(Ý\u0001º\u0000\u0018ð~ÿ²\u0002`~¬¨\u007f¡\u0001^sÒÞùax\u0001ü\u0095Tõ\u0010\u0002µ¿\u0005\u0081\u0087\u0090Ô\u009ezTË\u0085\u001f\u0099\f(º9£°\u0090`pÅ\u0080\u0096\u009c/X©,©\b¢«ÿò:Ç³Ðzº\\Q[?ÏVl[\u0001¨Ã¢)\u0018´Vü¤k#\u009føäØ\u00adªÎç\u009cmvk\rÒ#$\u008bÙ\u0018¸þÅ²\u000fõ\u0086m\u0089\u0010ûÞs?vÃìÄêv\u0098ß»\u0011\u0010K\u0097N+~\u0018\u0018Æ}P\u0015§\u009fÁÔ3\u0018¯\u009aL;\u00881æàÌ¾qüÚf{#ÓôW÷òÅ\u0013º\u0010ëãÇÒ;f j`äwÉÑØ\u0011u ?Y9\u0099\u00033µÈwî\u00ad\u0007´ØQ\u0019\u008e\nl\u0085à\u0097\u0019K´OS·:J´\u0093@ê\u000bÔqÈÏöG\u00020cÅñóÓ\u009djMÞc\u008a\\E\u00884òTÓm|\u0098\u0006´p\u008fFW2ª´=¯·k8!óÎ§¡F1áP\u009b©Î\u0001ÓçÈ\"\u0016)\u0018^\u0018Ú&#ýÌ\u00ad\u008d\u008bO]\u008b?õø+\u0007\u008fv\rY\u001cn(]OÝ]\u0085J«\u0019\u008f,AI_EË\u009fÂ\u009b´]$ÿÜn T-æ¥â}Ä®?\u0086Ô{×o%\u0010\"\u0012½ÑcÄ/È\u009e\u0003´ÑÁÑÀÀ ß#\u00895qR\u0005F\u0095Ü\u001fÆÌ\u009bÑ\u0098úGx\u0082\u0010\u001d-í\u0007pôÙ\u0098n\u0010\u001a BnæS¯\u0098\ri-M÷\u0095U\u009cÛ*\u009aò\u007fC\u0099Ï\u0081ßÂ/\u0086ù£8¹S\u0010kÌO@ã%\u0006]GdÊ×ýÓN{\u0010\u0091¾N^»\u0087(Fü\u00148àÑyId(\u0007G=}ç*|\u0097§\u001b\u0094ÜÒ\u0088¿\u0088¡È#µ\u00adS\u00ad\"\u00847ñtý»\u0082\r\u009c¶ï\u001a\u0083\u0082Ãl\u0010Ñ\u000fL.f¦\u0096aÅ»\u0080`sÕ\fË '\u001d\u000b¶\f:Oª¸¯^\u0015aq/ÉîïóT \u009cpùe\f\u009e?Þ\u0019e° ×E\u001bpVí\u0016Î\u0010°í51y:\u0084p A\u009fN¿\u0018Êû±%ä8å¼ÿ\u0010nßwü39\f,´Ë5\u0000åcÌZ0¤'Ó\u0014V]\u0006æ²ú$Q\u000b=íÉ{³x\u0098z\u0004ª÷øæ\u0001ÇÍ\u008e_¡³¦\nÁ»\u0094\u00905.t\u0089âÖ·BÉ\u0010\u0094\u008f\u0005\u0007`Ì§\u0083\u001c<»\u009cº\u009f°\u0083\u0018_cõ\u0097é´¹\rÏTtH×íÆÈ_¤D£SÖq\u000b\u0018oo|}Õ}\n Ö\u00892\u0013BÉ\r\u0005{\u0086Ò\u0099ñ\u0082?Á\u0010\u001a\u0091ÍÕ\u0084VÁIG{\u0092Þ×\u0011\u0091'(\u007fñ/Ö¡òª\u0015sd$¤µ=\\®èÑf\u001dÊÑî\u0011¦`xeJ6}\u008d×büêít8\u0080\u0010nwNµô²Rà×\rq\u008a»ð\u001c\u000e\u0010¹Ùü\u0006\u0005µ\u0097±y\u001a´öaH\u0099ø\u0010\rU}¯éwtÜè\u0081\u0002zEG\u0092\u0004\u0018Ygõ\u0010I~P'\r\u0091\u008bG\u0098÷ûG\"®ê÷p\u001b-Â8<cÙàs\u000e\u009e]\u0000æùQ,ÂøÕå^\fê\u009bÿ~æB\u0080\u001eè\u0092\u009fN5wÏ³2\u008a°r\u0098(ÙGI\u008f>\u0095\"\u00ad\u0017&4j%hù";
         int var7 = "\u0099\u0000ÈaÍí·§dLªáð©LØ\u001ax)(Ý\u0001º\u0000\u0018ð~ÿ²\u0002`~¬¨\u007f¡\u0001^sÒÞùax\u0001ü\u0095Tõ\u0010\u0002µ¿\u0005\u0081\u0087\u0090Ô\u009ezTË\u0085\u001f\u0099\f(º9£°\u0090`pÅ\u0080\u0096\u009c/X©,©\b¢«ÿò:Ç³Ðzº\\Q[?ÏVl[\u0001¨Ã¢)\u0018´Vü¤k#\u009føäØ\u00adªÎç\u009cmvk\rÒ#$\u008bÙ\u0018¸þÅ²\u000fõ\u0086m\u0089\u0010ûÞs?vÃìÄêv\u0098ß»\u0011\u0010K\u0097N+~\u0018\u0018Æ}P\u0015§\u009fÁÔ3\u0018¯\u009aL;\u00881æàÌ¾qüÚf{#ÓôW÷òÅ\u0013º\u0010ëãÇÒ;f j`äwÉÑØ\u0011u ?Y9\u0099\u00033µÈwî\u00ad\u0007´ØQ\u0019\u008e\nl\u0085à\u0097\u0019K´OS·:J´\u0093@ê\u000bÔqÈÏöG\u00020cÅñóÓ\u009djMÞc\u008a\\E\u00884òTÓm|\u0098\u0006´p\u008fFW2ª´=¯·k8!óÎ§¡F1áP\u009b©Î\u0001ÓçÈ\"\u0016)\u0018^\u0018Ú&#ýÌ\u00ad\u008d\u008bO]\u008b?õø+\u0007\u008fv\rY\u001cn(]OÝ]\u0085J«\u0019\u008f,AI_EË\u009fÂ\u009b´]$ÿÜn T-æ¥â}Ä®?\u0086Ô{×o%\u0010\"\u0012½ÑcÄ/È\u009e\u0003´ÑÁÑÀÀ ß#\u00895qR\u0005F\u0095Ü\u001fÆÌ\u009bÑ\u0098úGx\u0082\u0010\u001d-í\u0007pôÙ\u0098n\u0010\u001a BnæS¯\u0098\ri-M÷\u0095U\u009cÛ*\u009aò\u007fC\u0099Ï\u0081ßÂ/\u0086ù£8¹S\u0010kÌO@ã%\u0006]GdÊ×ýÓN{\u0010\u0091¾N^»\u0087(Fü\u00148àÑyId(\u0007G=}ç*|\u0097§\u001b\u0094ÜÒ\u0088¿\u0088¡È#µ\u00adS\u00ad\"\u00847ñtý»\u0082\r\u009c¶ï\u001a\u0083\u0082Ãl\u0010Ñ\u000fL.f¦\u0096aÅ»\u0080`sÕ\fË '\u001d\u000b¶\f:Oª¸¯^\u0015aq/ÉîïóT \u009cpùe\f\u009e?Þ\u0019e° ×E\u001bpVí\u0016Î\u0010°í51y:\u0084p A\u009fN¿\u0018Êû±%ä8å¼ÿ\u0010nßwü39\f,´Ë5\u0000åcÌZ0¤'Ó\u0014V]\u0006æ²ú$Q\u000b=íÉ{³x\u0098z\u0004ª÷øæ\u0001ÇÍ\u008e_¡³¦\nÁ»\u0094\u00905.t\u0089âÖ·BÉ\u0010\u0094\u008f\u0005\u0007`Ì§\u0083\u001c<»\u009cº\u009f°\u0083\u0018_cõ\u0097é´¹\rÏTtH×íÆÈ_¤D£SÖq\u000b\u0018oo|}Õ}\n Ö\u00892\u0013BÉ\r\u0005{\u0086Ò\u0099ñ\u0082?Á\u0010\u001a\u0091ÍÕ\u0084VÁIG{\u0092Þ×\u0011\u0091'(\u007fñ/Ö¡òª\u0015sd$¤µ=\\®èÑf\u001dÊÑî\u0011¦`xeJ6}\u008d×büêít8\u0080\u0010nwNµô²Rà×\rq\u008a»ð\u001c\u000e\u0010¹Ùü\u0006\u0005µ\u0097±y\u001a´öaH\u0099ø\u0010\rU}¯éwtÜè\u0081\u0002zEG\u0092\u0004\u0018Ygõ\u0010I~P'\r\u0091\u008bG\u0098÷ûG\"®ê÷p\u001b-Â8<cÙàs\u000e\u009e]\u0000æùQ,ÂøÕå^\fê\u009bÿ~æB\u0080\u001eè\u0092\u009fN5wÏ³2\u008a°r\u0098(ÙGI\u008f>\u0095\"\u00ad\u0017&4j%hù"
            .length();
         char var8 = 24;
         int var24 = -1;

         label50:
         while (true) {
            String var25 = var6.substring(++var24, var24 + var8);
            int var10001 = -1;

            while (true) {
               byte[] var10 = var2.doFinal(var25.getBytes("ISO-8859-1"));
               String var34 = a(var10).intern();
               switch (var10001) {
                  case 0:
                     var4[var5++] = var34;
                     if ((var24 += var8) >= var7) {
                        i = var4;
                        l = new String[36];
                        O = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(var0 << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var13 = new long[2];
                        int var14 = 0;
                        String var15 = "Uào)ëê\t!õÜÙã5ÑÑ\u0012";
                        int var16 = "Uào)ëê\t!õÜÙã5ÑÑ\u0012".length();
                        int var17 = 0;

                        do {
                           var10001 = var17;
                           var17 += 8;
                           byte[] var18 = var15.substring(var10001, var17).getBytes("ISO-8859-1");
                           long var19 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           byte[] var21 = var11.doFinal(
                              new byte[]{
                                 (byte)(var19 >>> 56),
                                 (byte)(var19 >>> 48),
                                 (byte)(var19 >>> 40),
                                 (byte)(var19 >>> 32),
                                 (byte)(var19 >>> 24),
                                 (byte)(var19 >>> 16),
                                 (byte)(var19 >>> 8),
                                 (byte)var19
                              }
                           );
                           long var10004 = (var21[0] & 255L) << 56
                              | (var21[1] & 255L) << 48
                              | (var21[2] & 255L) << 40
                              | (var21[3] & 255L) << 32
                              | (var21[4] & 255L) << 24
                              | (var21[5] & 255L) << 16
                              | (var21[6] & 255L) << 8
                              | var21[7] & 255L;
                           var13[(var14++)] = var10004;
                        } while (var17 < var16);

                        w = var13;
                        return;
                     }

                     var8 = var6.charAt(var24);
                     break;
                  default:
                     var4[var5++] = var34;
                     if ((var24 += var8) < var7) {
                        var8 = var6.charAt(var24);
                        continue label50;
                     }

                     var6 = "\u008fÆ$\bO\u001f\u0000E¢ÛêW©M\u0093WýÓ\u0013vþÆ\u008a¶D\u007fb\u009f\u0090ËÓÙ\u0013b\u0012\u0010Xá\u0000û\u0014\u0000õy4×\u00145·Ñ\u0085\u0080MGyháïþ\u0016¬¦òØ\u0010÷}#Knÿ»\u009c\u0019\u0082UU\u0092üCÌ";
                     var7 = "\u008fÆ$\bO\u001f\u0000E¢ÛêW©M\u0093WýÓ\u0013vþÆ\u008a¶D\u007fb\u009f\u0090ËÓÙ\u0013b\u0012\u0010Xá\u0000û\u0014\u0000õy4×\u00145·Ñ\u0085\u0080MGyháïþ\u0016¬¦òØ\u0010÷}#Knÿ»\u009c\u0019\u0082UU\u0092üCÌ"
                        .length();
                     var8 = '@';
                     var24 = -1;
               }

               var25 = var6.substring(++var24, var24 + var8);
               var10001 = 0;
            }
         }
      } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException var22) {
         throw new RuntimeException(var22);
      }
   }
}
