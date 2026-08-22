package Expo.ASM.Render;

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
import org.objectweb.asm.tree.VarInsnNode;

public class TransformItemRenderer extends TransformerBase {
   private static long d;
   private static Map G;
   private static String[] l;
   private static String[] P;
   private static long[] w;
   private static Object[] O;
   private static String[] i;
   private static Map t;

   private static void b() {
      O[0] = "\u0011H30ZD\u0007}m\r-";
      O[1] = "f4%$M3m;4k0+~<=\"";
      O[2] = "J=\u001f\u000e[8\\\bA3L";
      O[3] = "\u0007=$P\u000fo\u0002* \n\u0017h\na\"\r\r#\u001c=&\u001bNN\u0004.0\r.b\f*";
      O[4] = "\u0018!tS{P\u000e\u0014*h";
      O[5] = "JlH\t\u001bP?LC\u0006\n\u001fBTP\u0001\u0003V*";
      O[6] = boolean.class;
      P[6] = "java/lang/Boolean";
      O[7] = "Y\u0015\u0011XG\tO Oe";
      O[8] = "\u0010#\n\u0004?P\u00154\u000e^'W\u001d\u007f\fY=\u001c\u000b#\bO~\u007f\u001a%\u0005E4|\u00105\b";
      O[9] = "=u\u001b\"Zz8b\u001fxB}0)\u001d\u007fX6\u0006~\fi";
      O[10] = "Rk'QY D^yl&";
      O[11] = "\u0015By>x\u0000\u0010U}d`\u0007\u0018\u001e\u007fczL\u000eB{u9+\u0014Cp\\~\u0011\u000e";
      O[12] = void.class;
      P[12] = "java/lang/Void";
      O[13] = int.class;
      P[13] = "java/lang/Integer";
      O[14] = "^\r{\u0010($+-p\u001f9kV5c\u00180\">";
      O[15] = "N~\u000e? mXKP\u0002w";
      O[16] = "Gj\u0006kL\u0011Q_XM";
      O[17] = "|-_5\u0010v\t\rT:\u00019t\u0015G=\bp\u001c";
      O[18] = "-O5Q\u000ei(X1\u000b\u0016n \u00133\f\f%6O7\u001aOJ N&\r\u0000h6t<\f\u000fE-Y7";
      O[19] = "\u001d_PK\u0017K\u0016PA\u0004vE\u001d[E^";
      O[20] = "\r7\tW]1\u000b0\u001dV;\u00154k\u0001_@'M7E]\u0000U],J\u001cZ$\u0004hE];?U$\u0015KW-\t:A&\u00077V)\nBC/X0{";
      O[21] = "%.=R\r[jt?OiM\u0018*&U\u0012BavbWR0&}9H\u0018\fepm\u0017i\fzu#]\rHb{:,URzh-H\u0011Jtq\\\u0013Y@bl0N\u0000]i\u0017`N\u000bOis$V\u0005V\u0018";
      O[22] = "Ch\u0010[Z\u0002Eo\u0004Z<\u001dz0YV@\u0000\u001e`\u0013N\u0001fC2\u001eVZ\u0002\u0013x\u0006\u0017<\f\u0001v\u0004\u0017VZ\u0007o\u0018*U\u001cK3\u0003[\fXDrb";
      O[23] = "uQ\u0004\u001dk:0Q\u0018e]YuS\u0006\u0019k=%\u0019\u001eX\r`w\u0014\u0006\u0003i0=\fGe";
      O[24] = "X\u000bG=aj^\fS<\u0007VaUH4>l\u0004\u0016\u000569\u000eXQI0aj\b\u001bQq\u00077Z\u0016I*cg\u0010\u000e\bL>5\u001d\u0016S(n\u007f\u0005W5|kd\\U\\3wj\u0019j";
      O[25] = "m\u0011`) ck\u0016t(FFTOo \u007fe1\f\"\"x\u0007e\u001ek8/;(\u0016obF78\u001a/g/x$\u0014jX";
      O[26] = "d8\f`V\u0019b?\u0018a0\u0001]c\u001fqH\u001f8\"\u0015/Q}db\u0002mV\u00194(\u001a,0F40OnN\u0016`c\u0014\u0011Z\u0006\"?C{\f\u0000;#~-R\u001f\"(\u001aiJ\u0011;Y";
      O[27] = "~\u0012)\u0001i\u0010x\u0015=\u0000\u000f%GL&\b6\u0016\"\u000fk\n1t~H'\fi\u0010.\u0002?M\u000fM|\u000f'\u0016k\u001d6\u0017fp6O;\u000f=\u0014f\u0005#N[@c\u001ezL2\u000f\u007f\u0010?s";
      O[28] = "M\t9tKwK\u000e-u-utW6}\u0014q\u0011\u0014{\u007f\u0013\u0013I\u00122~_j\u0015V0>-\"\u001a\u0011+l\u0011o\u0012\u0015q\u0005\u001d\u007f\u001eUtlRc\u0010\u0010K";
      O[29] = "\u0012P\"\u0012 u\u0014W6\u0013Fm+\f*\u001a=cRPn\u0018}\u0011\u0015[5\u00077-VVaXF EH0\nzmMLjc";
      O[30] = "\u001bJ\u0019\u00033WX\u0007\u001b\u0004Q[H[aVk\r\u001f\u0007X[8^U7QV;\u000f\u001b^\u001eJ5J$";
      O[31] = "4fQ.vI2aE/\u0010N\r8^')Oh{\u0013%.-=kIb/DrwG'\u0010";
      O[32] = "=\u0003\u000bi4\u0004rY\ttP\r\u0000\u0000\u000bw(\reA\u0001)1o9\u0001\u0016k6\u000biK\u000e*P\u0005{E\f*:S}\\\u0010\u0017iT|F\fs9\u001ed\u0007j";
   }

   static {
      d = 140663902171892L;
      zkm$clinit();
   }

   public TransformItemRenderer() {
      super("net/minecraft/client/renderer/ItemRenderer");
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

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(
         var1,
         "(F)V",
         (var1x, var2x) -> {
            boolean var5x = TransformerBase.u(
               var2x,
               Type.VOID_TYPE,
               false,
               true,
               (var2xx, var3x) -> {
                  long var4x = d ^ 13421063264248L;
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(
                     var2xx,
                     var1.name,
                     TransformerBase.j(var1, SrgNames.X("net/minecraft/client/Minecraft"), "mc", "mc", "c"),
                     SrgNames.X("net/minecraft/client/Minecraft")
                  );
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(
                     var2xx,
                     var1.name,
                     TransformerBase.j(var1, SrgNames.X("net/minecraft/item/ItemStack"), "itemToRender", "itemToRender", "d"),
                     SrgNames.X("net/minecraft/item/ItemStack")
                  );
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(var2xx, var1.name, TransformerBase.j(var1, "F", "prevEquippedProgress", "prevEquippedProgress", "f"), "F");
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(var2xx, var1.name, TransformerBase.j(var1, "F", "equippedProgress", "equippedProgress", "e"), "F");
                  BytecodeHelper.n(var2xx, var2x, 0);
                  BytecodeHelper.I(var2xx, var3x);
                  BytecodeHelper.Y(
                     var2xx,
                     D,
                     "renderItemInFirstPerson",
                     "("
                        + SrgNames.X("net/minecraft/client/renderer/ItemRenderer")
                        + SrgNames.X("net/minecraft/client/Minecraft")
                        + SrgNames.X("net/minecraft/item/ItemStack")
                        + "FFF"
                        + z
                        + ")V"
                  );
               }
            );
            return var5x
               | BytecodeHelper.H(
                  var2x, BytecodeHelper.s("net/minecraft/client/renderer/ItemRenderer", "(FF)V", "transformFirstPersonItem", "transformFirstPersonItem"), var1xx -> {
                     long var2xx = d ^ 38314189942546L;
                     int var4x = BytecodeHelper.t(var2x, Type.FLOAT_TYPE);
                     var1xx.add(new VarInsnNode(56, var4x));
                     BytecodeHelper.Y(var1xx, D, "transformFirstPersonItemEquipProgress", "(F)F");
                     var1xx.add(new VarInsnNode(23, var4x));
                  }
               );
         },
         "renderItemInFirstPerson",
         "renderItemInFirstPerson"
      );
      var4 |= BytecodeHelper.t(var1, "(" + SrgNames.X("net/minecraft/client/entity/EntityPlayerSP") + "F)V", (var0, var1x) -> {
         long var2x = d ^ 45182611574439L;
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 123701976078168L;
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, D, "onFunc_178110_a", "(" + z + ")V");
         });
      }, "rotateWithPlayerRotations");
      return var4 | BytecodeHelper.t(var1, "()V", (var0, var1x) -> {
         long var2x = d ^ 111130480456961L;
         return TransformerBase.u(var1x, Type.VOID_TYPE, false, true, (var0x, var1xx) -> {
            long var2xx = d ^ 133182079305734L;
            BytecodeHelper.k(var0x);
            BytecodeHelper.I(var0x, var1xx);
            BytecodeHelper.Y(var0x, v, "itemRendererUpdateEquippedItem", "(" + SrgNames.X("net/minecraft/client/renderer/ItemRenderer") + z + ")V");
         });
      }, "updateEquippedItem", "updateEquippedItem");
   }
   private static void zkm$clinit() {
      try {
         O = new Object[33];
         P = new String[33];
         b();
         t = new HashMap(13);
         long var0 = d ^ 132929608921282L;
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var4 = new String[34];
         int var5 = 0;
         String var6 = "\u0089\u009eï°Û÷%=Û÷¦ùI:\u0018~0s\r1¤\u0099\u00050ê\u007f¨Ôj$ÂÃ½éV`\u0010bpåã\u0099Á»/kv\\\f\u008fU=È×Rºo\u0018µ³l<Höv\u0010òÝ\u000fj\u008b\u0097Q¿¼µÎG´ñÇ\u0099((\u0012Â\u0089\u0090¨;¡W\u001dµ¨Ei&\u0011|\u0002ße¨\u0016U.áE\u009cç2è8\u008f£õÕw\u0088\u0086¼! ì+ª3\u009f\u0098\u0088¬\u000fÈS_Õ\u001ehìg\u0000Äq¬ÁKC¤(.Ú\u0019·Óg\u0010\u009b\u001f\u0081WÆ\u009b\u0087\u0001\u0011\u0003\u0082¼#6\nö@D,Æ½Üºm\u001cæiDÓ²*ÓõáÙ\u001f\u0014\u009b\u0001#Ì?²\u0086¯]ò\u0019\u009b\fÅòz\rMK\u001b¥¡\u000b\u0013òäZÁ\u001b\u0089_\u0014(\u0082²\u00adÈôòaÀ¦\u009f~\u0018j«|\u008e\u000f\u009eFÑ6Pw=k¤)¼6¾á\u009bûÓ\u009aÕ\u0010\u009a\tÔZå¶\u0093ÇnÂ\u0006ØXÞ\u0014<P øÌ)²Î÷\u009emfR9îoM\u0000Á\u0083\u000b\u008d¤\u0081\u0098^YË\u0084&Ò\u000ffäX\u0098x\u0015\u0082*\bN¬x?³Æ/\u0096!á#Þû\u009bJbïn\u0007'ãQ\u009d©é@âD1é\u0080=\u0091l®3V\u000f½·\n\u0010±Î¿]\u009ab\"ju¬!\u001eð½\u0094È\u0010c\u0090\u0011H}T§\u008d«ëé\u008d¹\u0002\u0012>8ö@Îø?\u001fªµ¡ù\ng\u0094\ni\u009dAe\u0091ª2±1°\u008fcMè!]Âª\u009bö\u0082\u009a}ÏÉ-K=\u008b4ºn\u009d\u0082£\u0004þürË\u0003\u0002\u0018\u0091ºén\u0082f\u0018\b=\u009a.í\u0089%á\u0097\u0018\u009d¤\u0090\u007fÇ6ï \u007f£J\"4s.eà\u008am\u0004Ì\u0080\u00adEñ\u0001!±ú\u0017ÁYö\u0014¢\bCÿÀò(ÍT\u0089ê=ÑZªM\u0086\u0011Ús\u0094Ùoôæ\u009dÓù p\u001bP¶ÎrÖr\u0016ÌÄÜ¼±f¤VD8\u0085TLÌp\u0019çYàéÁ\u009cç!\tA\u0018\u001aÚ\u0013zPú<$\u001a@Í/¾L6\u008b\u0082·içqu\u001a\u0090+a\r\u0015Ìzgo?é\u0007µ Ã,P¼óyªýK\u0004DJsW\u0085³ÁÎ1ù\u008cûök\u001cÒ5íÑEWçÇé¨8\u0011#À¶&\u0096\bw¯ÈVlhëâ\u001e\u009b\u0011åT\u0004¸b5Íá\u0019\th¨\u000fÚ)C~ø\u0096\u001ei__C\u009a\u0092\fÝ\r(áKx\u000e\u001d!\u000e\u0000\u0091ÞtR\u0090V\bÂ1\u0099\\âÐ·yî¾þ_\b\u0094\u0080Ã\u0013\r0\u0012T\u0087]÷È £\u0014\u0085\\hÆÓ »\u0006\u0019\u0019¡Úð%h\u0090^\u0019ÓÒ?\u0000\u0000Êâ%çßs\u0015\u0010:½÷ó°c\u0003\u0004¦îe\u0092\u0086N\u0003(0´\u0094\u009e\u0016\u0088Å¿ÔÌ\u0018×}è>Ã\\²Zo\u0011\nÖ¢ßÂÒ\u0087 ÿH2\u0089\u009d>\u008d\u0088q13\u009eN¸ á\u0097nó[(%Â\u001fÒK¿±sè¾\u001aa\u008a=\u0018ã²qÜ\u0086áôE\u001e|·ù/t4\u009c\u0010Öi1ºc\u0085µ\u009d(ÂÛ\u0083R\u0099XÕ\u0081ó1é\u0017\u008aY\u0094ô\u0019;Ç|ç4ö¯x2ÏSÆ\u009eàBsÛ\fÃ¯î\u008dË\u0010\"´/Äk\u0086 ¹\u008d&\b\\\u0013!¡RPáí\u0097éa\u009d¯\u0080\u001döUÁ©\u0014|\u0094»àÒS\u0015\u0091\u0000MòÃçsÌÚ\u0006Ô$p\u009cGñub¬7Wî\u0094\u0083ÈER*a@Ç=\u009c¢g<Bït\u0012ç99i\u0014¾,×\u009cÀ)\u009fDfdé%K\u00020AÀGØGeÚ(\u0096î\u000b«¬Ê\u0081$\u009cSµ\u0003\u0098svmýÆ\u0005\u008b\u0007¥\u009b®°lÕÍîzïø6\u0016)g\u008dA\u0093$\u0010±«\u001bIJdïü½C[Ö/¦(ö \u0010\u009d_Ub[×éÁW´!\u0081\u0082ï8u5Y·C;f\nn\b\\¯¿ÏåÌ\u0018Fd)\u001e8Öëµî\u0096ÞN\u0013Ïµ[\u0081M7Ã\u0004&\u001c¼ \u0002\u0011¾ílÕpÝy\u0084`ÔwÌKk[m:{ \u0080¨Ø¨&\u0010äÞvØ# Að\u0013µ,\u0096\rvï:z;!?\u0090U£b(qªË\b\u0018I\u000eëF\u0091Î1\u0084";
         int var7 = "\u0089\u009eï°Û÷%=Û÷¦ùI:\u0018~0s\r1¤\u0099\u00050ê\u007f¨Ôj$ÂÃ½éV`\u0010bpåã\u0099Á»/kv\\\f\u008fU=È×Rºo\u0018µ³l<Höv\u0010òÝ\u000fj\u008b\u0097Q¿¼µÎG´ñÇ\u0099((\u0012Â\u0089\u0090¨;¡W\u001dµ¨Ei&\u0011|\u0002ße¨\u0016U.áE\u009cç2è8\u008f£õÕw\u0088\u0086¼! ì+ª3\u009f\u0098\u0088¬\u000fÈS_Õ\u001ehìg\u0000Äq¬ÁKC¤(.Ú\u0019·Óg\u0010\u009b\u001f\u0081WÆ\u009b\u0087\u0001\u0011\u0003\u0082¼#6\nö@D,Æ½Üºm\u001cæiDÓ²*ÓõáÙ\u001f\u0014\u009b\u0001#Ì?²\u0086¯]ò\u0019\u009b\fÅòz\rMK\u001b¥¡\u000b\u0013òäZÁ\u001b\u0089_\u0014(\u0082²\u00adÈôòaÀ¦\u009f~\u0018j«|\u008e\u000f\u009eFÑ6Pw=k¤)¼6¾á\u009bûÓ\u009aÕ\u0010\u009a\tÔZå¶\u0093ÇnÂ\u0006ØXÞ\u0014<P øÌ)²Î÷\u009emfR9îoM\u0000Á\u0083\u000b\u008d¤\u0081\u0098^YË\u0084&Ò\u000ffäX\u0098x\u0015\u0082*\bN¬x?³Æ/\u0096!á#Þû\u009bJbïn\u0007'ãQ\u009d©é@âD1é\u0080=\u0091l®3V\u000f½·\n\u0010±Î¿]\u009ab\"ju¬!\u001eð½\u0094È\u0010c\u0090\u0011H}T§\u008d«ëé\u008d¹\u0002\u0012>8ö@Îø?\u001fªµ¡ù\ng\u0094\ni\u009dAe\u0091ª2±1°\u008fcMè!]Âª\u009bö\u0082\u009a}ÏÉ-K=\u008b4ºn\u009d\u0082£\u0004þürË\u0003\u0002\u0018\u0091ºén\u0082f\u0018\b=\u009a.í\u0089%á\u0097\u0018\u009d¤\u0090\u007fÇ6ï \u007f£J\"4s.eà\u008am\u0004Ì\u0080\u00adEñ\u0001!±ú\u0017ÁYö\u0014¢\bCÿÀò(ÍT\u0089ê=ÑZªM\u0086\u0011Ús\u0094Ùoôæ\u009dÓù p\u001bP¶ÎrÖr\u0016ÌÄÜ¼±f¤VD8\u0085TLÌp\u0019çYàéÁ\u009cç!\tA\u0018\u001aÚ\u0013zPú<$\u001a@Í/¾L6\u008b\u0082·içqu\u001a\u0090+a\r\u0015Ìzgo?é\u0007µ Ã,P¼óyªýK\u0004DJsW\u0085³ÁÎ1ù\u008cûök\u001cÒ5íÑEWçÇé¨8\u0011#À¶&\u0096\bw¯ÈVlhëâ\u001e\u009b\u0011åT\u0004¸b5Íá\u0019\th¨\u000fÚ)C~ø\u0096\u001ei__C\u009a\u0092\fÝ\r(áKx\u000e\u001d!\u000e\u0000\u0091ÞtR\u0090V\bÂ1\u0099\\âÐ·yî¾þ_\b\u0094\u0080Ã\u0013\r0\u0012T\u0087]÷È £\u0014\u0085\\hÆÓ »\u0006\u0019\u0019¡Úð%h\u0090^\u0019ÓÒ?\u0000\u0000Êâ%çßs\u0015\u0010:½÷ó°c\u0003\u0004¦îe\u0092\u0086N\u0003(0´\u0094\u009e\u0016\u0088Å¿ÔÌ\u0018×}è>Ã\\²Zo\u0011\nÖ¢ßÂÒ\u0087 ÿH2\u0089\u009d>\u008d\u0088q13\u009eN¸ á\u0097nó[(%Â\u001fÒK¿±sè¾\u001aa\u008a=\u0018ã²qÜ\u0086áôE\u001e|·ù/t4\u009c\u0010Öi1ºc\u0085µ\u009d(ÂÛ\u0083R\u0099XÕ\u0081ó1é\u0017\u008aY\u0094ô\u0019;Ç|ç4ö¯x2ÏSÆ\u009eàBsÛ\fÃ¯î\u008dË\u0010\"´/Äk\u0086 ¹\u008d&\b\\\u0013!¡RPáí\u0097éa\u009d¯\u0080\u001döUÁ©\u0014|\u0094»àÒS\u0015\u0091\u0000MòÃçsÌÚ\u0006Ô$p\u009cGñub¬7Wî\u0094\u0083ÈER*a@Ç=\u009c¢g<Bït\u0012ç99i\u0014¾,×\u009cÀ)\u009fDfdé%K\u00020AÀGØGeÚ(\u0096î\u000b«¬Ê\u0081$\u009cSµ\u0003\u0098svmýÆ\u0005\u008b\u0007¥\u009b®°lÕÍîzïø6\u0016)g\u008dA\u0093$\u0010±«\u001bIJdïü½C[Ö/¦(ö \u0010\u009d_Ub[×éÁW´!\u0081\u0082ï8u5Y·C;f\nn\b\\¯¿ÏåÌ\u0018Fd)\u001e8Öëµî\u0096ÞN\u0013Ïµ[\u0081M7Ã\u0004&\u001c¼ \u0002\u0011¾ílÕpÝy\u0084`ÔwÌKk[m:{ \u0080¨Ø¨&\u0010äÞvØ# Að\u0013µ,\u0096\rvï:z;!?\u0090U£b(qªË\b\u0018I\u000eëF\u0091Î1\u0084"
            .length();
         char var8 = 16;
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
                        l = new String[34];
                        G = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(var0 << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var13 = new long[2];
                        int var14 = 0;
                        String var15 = "\u0082ÜaDß¸®\u008cd+£²'\u001c\u0080s";
                        int var16 = "\u0082ÜaDß¸®\u008cd+£²'\u001c\u0080s".length();
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

                     var6 = "\u0013Àä\u0092º#vu¼âgàB\u0089\u000fòD$£\u009879\u001cù)\u0000:æ\u008c©©\u009d8\u009f8.a\u007fí\u0016ûhUpÅ.0k\u00adqd\\\u0012\u0090R\u0083çP^é\u0018_§¹O\f\u0016·Ì\u009dUö\u0080|÷!\u000fÈÖ3ÕÓµ8iXæÍ\u0093";
                     var7 = "\u0013Àä\u0092º#vu¼âgàB\u0089\u000fòD$£\u009879\u001cù)\u0000:æ\u008c©©\u009d8\u009f8.a\u007fí\u0016ûhUpÅ.0k\u00adqd\\\u0012\u0090R\u0083çP^é\u0018_§¹O\f\u0016·Ì\u009dUö\u0080|÷!\u000fÈÖ3ÕÓµ8iXæÍ\u0093"
                        .length();
                     var8 = ' ';
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
