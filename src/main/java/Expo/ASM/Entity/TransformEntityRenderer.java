package Expo.ASM.Entity;

import Expo.ASM.TransformerBase;
import Expo.ASM.Util.BytecodeHelper;
import Expo.ASM.Util.SrgNames;
import java.util.HashMap;
import java.util.Map;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;

public class TransformEntityRenderer extends TransformerBase {
   private static String[] P;
   private static Object[] O;
   private static Map t;
   private static Map G;
   private static long d;
   private static String[] l;

   static {
      d = 60548101332298L;
      O = new Object[40];
      P = new String[40];
      t = new HashMap(13);
      l = new String[83];
      G = new HashMap(13);
   }

   public boolean s(ClassNode var1) {
      boolean var4 = false;
      var4 |= BytecodeHelper.t(
         var1,
         "(F)V",
         (var1x, var2x) -> {
            boolean var5x = BytecodeHelper.R(
               var2x, BytecodeHelper.s("net/minecraft/entity/Entity", "(F)" + SrgNames.X("net/minecraft/util/Vec3"), "getLook", "getLook"), (var0x, var1xx) -> {
                  long var2xx = d ^ 71255420042271L;
                  BytecodeHelper.Y(var0x, v, "entityRendererGetLook", "(" + SrgNames.X("net/minecraft/entity/Entity") + "F)" + SrgNames.X("net/minecraft/util/Vec3"));
               }
            );
            return var5x
               | BytecodeHelper.H(
                  var2x,
                  BytecodeHelper.W("java/util/List", "size", "()I"),
                  var1xx -> {
                     long var2xx = d ^ 85018624812435L;
                     var1xx.add(new InsnNode(89));
                     BytecodeHelper.k(var1xx);
                     BytecodeHelper.P(
                        var1xx,
                        var1.name,
                        TransformerBase.j(var1, SrgNames.X("net/minecraft/client/Minecraft"), "mc", "mc", "h"),
                        SrgNames.X("net/minecraft/client/Minecraft")
                     );
                     var1xx.add(new InsnNode(95));
                     BytecodeHelper.Y(var1xx, s, "onGetMouseOverList", "(" + SrgNames.X("net/minecraft/client/Minecraft") + "Ljava/util/List;)V");
                  }
               );
         },
         "getMouseOver",
         "getMouseOver"
      );
      var4 |= BytecodeHelper.t(
         var1,
         "(FJ)V",
         (var0, var1x) -> {
            long var2x = d ^ 46779071886968L;
            boolean var4x = TransformerBase.u(var1x, Type.VOID_TYPE, false, false, (var0x, var1xx) -> {
               long var2xx = d ^ 48120974112455L;
               BytecodeHelper.Y(var0x, s, "updateCameraAndRender", "()V");
            });
            boolean var5x = BytecodeHelper.Q(
               var1x, BytecodeHelper.s("net/minecraft/client/gui/GuiIngame", "(F)V", "renderGameOverlay", "renderGameOverlay"), var1xx -> {
                  long var2xx = d ^ 115331598876789L;
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(var1xx, s, "onRender2D", "(F)V");
               }
            );
            if (!var5x) {
               var5x = TransformerBase.M(var1x, Type.VOID_TYPE, (var1xx, var2xx) -> {
                  BytecodeHelper.n(var1xx, var1x, 0);
                  BytecodeHelper.Y(var1xx, s, "onRender2D", "(F)V");
               });
            }

            var4x |= var5x;
            return var4x | TransformerBase.M(var1x, Type.VOID_TYPE, (var0x, var1xx) -> {
               long var2xx = d ^ 27978408483658L;
               BytecodeHelper.Y(var0x, s, "postUpdateCameraAndRender", "()V");
            });
         },
         "updateCameraAndRender",
         "updateCameraAndRender"
      );
      var4 |= BytecodeHelper.t(var1, "(IFJ)V", (var0, var1x) -> {
         long var2x = d ^ 18145945181569L;
         return TransformerBase.M(var1x, Type.VOID_TYPE, (var1xx, var2xx) -> {
            BytecodeHelper.k(var1xx);
            BytecodeHelper.n(var1xx, var1x, 1);
            BytecodeHelper.Y(var1xx, s, "onRender3D", "(" + SrgNames.X("net/minecraft/client/renderer/EntityRenderer") + "F)V");
         });
      }, "renderWorldPass", "renderWorldPass");
      var4 |= BytecodeHelper.t(
         var1,
         "(F)V",
         (var1x, var2x) -> {
            return TransformerBase.u(
               var2x,
               Type.VOID_TYPE,
               false,
               true,
               (var2xx, var3x) -> {
                  long var4x = d ^ 67056483319661L;
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(
                     var2xx,
                     var1.name,
                     TransformerBase.j(var1, SrgNames.X("net/minecraft/client/Minecraft"), "mc", "mc", "h"),
                     SrgNames.X("net/minecraft/client/Minecraft")
                  );
                  BytecodeHelper.n(var2xx, var2x, 0);
                  BytecodeHelper.Y(var2xx, s, "hurtCameraEffect", "(" + SrgNames.X("net/minecraft/client/Minecraft") + "F)Z");
                  LabelNode var6x = new LabelNode();
                  var2xx.add(new JumpInsnNode(153, var6x));
                  BytecodeHelper.I(var2xx, var3x);
                  BytecodeHelper.U(var2xx, BytecodeHelper.P, "cancel", "()V");
                  var2xx.add(var6x);
               }
            );
         },
         "hurtCameraEffect",
         "hurtCameraEffect"
      );
      var4 |= BytecodeHelper.t(
         var1,
         "(F)V",
         (var1x, var2x) -> {
            return BytecodeHelper.H(
               var2x,
               BytecodeHelper.s("net/minecraft/util/Vec3", "(" + SrgNames.X("net/minecraft/util/Vec3") + ")D", "distanceTo", "distanceTo"),
               var2xx -> {
                  int var5x = BytecodeHelper.O(var2x, var2xx);
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(
                     var2xx,
                     var1.name,
                     TransformerBase.j(var1, SrgNames.X("net/minecraft/client/Minecraft"), "mc", "mc", "h"),
                     SrgNames.X("net/minecraft/client/Minecraft")
                  );
                  BytecodeHelper.n(var2xx, var2x, 0);
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(var2xx, var1.name, TransformerBase.j(var1, "F", "thirdPersonDistance", "thirdPersonDistance", "q"), "F");
                  BytecodeHelper.k(var2xx);
                  BytecodeHelper.P(var2xx, var1.name, TransformerBase.j(var1, "F", "thirdPersonDistanceTemp", "thirdPersonDistanceTemp", "r"), "F");
                  BytecodeHelper.I(var2xx, var5x);
                  BytecodeHelper.Y(
                     var2xx,
                     v,
                     "entityRendererOrientCamera",
                     "(" + SrgNames.X("net/minecraft/client/renderer/EntityRenderer") + SrgNames.X("net/minecraft/client/Minecraft") + "FFF" + z + ")V"
                  );
                  BytecodeHelper.E(var2xx, var5x, Type.VOID_TYPE);
               }
            );
         },
         "orientCamera",
         "orientCamera"
      );
      var4 |= BytecodeHelper.t(
         var1,
         "(F)V",
         (var0, var1x) -> {
            long var2x = d ^ 96207212921655L;
            return BytecodeHelper.R(
               var1x,
               BytecodeHelper.s("net/minecraft/entity/EntityLivingBase", "(" + SrgNames.X("net/minecraft/potion/Potion") + ")Z", "isPotionActive", "isPotionActive"),
               (var0x, var1xx) -> {
                  long var2xx = d ^ 125916988456220L;
                  BytecodeHelper.Y(
                     var0x, v, "bypassBlindness", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + SrgNames.X("net/minecraft/potion/Potion") + ")Z"
                  );
               }
            );
         },
         "updateFogColor",
         "updateFogColor"
      );
      var4 |= BytecodeHelper.t(
         var1,
         "(IF)V",
         (var0, var1x) -> {
            long var2x = d ^ 65401012380338L;
            return BytecodeHelper.R(
               var1x,
               BytecodeHelper.s("net/minecraft/entity/EntityLivingBase", "(" + SrgNames.X("net/minecraft/potion/Potion") + ")Z", "isPotionActive", "isPotionActive"),
               (var0x, var1xx) -> {
                  long var2xx = d ^ 40654999085268L;
                  BytecodeHelper.Y(
                     var0x, v, "bypassBlindness", "(" + SrgNames.X("net/minecraft/entity/EntityLivingBase") + SrgNames.X("net/minecraft/potion/Potion") + ")Z"
                  );
               }
            );
         },
         "setupFog",
         "setupFog"
      );
      return var4
         | BytecodeHelper.t(
            var1,
            "(FI)V",
            (var0, var1x) -> {
               long var2x = d ^ 64097867871101L;
               return BytecodeHelper.R(
                  var1x,
                  BytecodeHelper.s(
                     "net/minecraft/client/entity/EntityPlayerSP", "(" + SrgNames.X("net/minecraft/potion/Potion") + ")Z", "isPotionActive", "isPotionActive"
                  ),
                  (var0x, var1xx) -> {
                     long var2xx = d ^ 37056240623474L;
                     BytecodeHelper.Y(
                        var0x, v, "bypassConfusion", "(" + SrgNames.X("net/minecraft/client/entity/EntityPlayerSP") + SrgNames.X("net/minecraft/potion/Potion") + ")Z"
                     );
                  }
               );
            },
            "setupCameraTransform",
            "setupCameraTransform"
         );
   }

   public TransformEntityRenderer() {
      super("net/minecraft/client/renderer/EntityRenderer");
   }

   private static void b() {
      O[0] = "\u001ftG3oM\tA\u0019\u000ex";
      O[1] = "\u00059 }(L\u000e612UT\u001d18{";
      O[2] = "\u0003D+@\u001fMvd O\u000e\u0002\u000b|3H\u0007Kc";
      O[3] = "\u0017}7\u0010\u00159\u0001Hi-B";
      O[4] = "L`\u0005x/,ZU[E";
      O[5] = "r\u001b)M~\u0016w\f-\u0017f\u0011\u007fG/\u0010|Zi\u001b+\u0006?9x\u001d&\fu:r\r+";
      O[6] = "\u001b\u001asS\u000f\u0007\u001e\rw\t\u0017\u0000\u0016Fu\u000e\rK \u0011d\u0018";
      O[7] = boolean.class;
      P[7] = "java/lang/Boolean";
      O[8] = "\u000b*~L{\t\u001d\u001f q\u0004";
      O[9] = "v>D\u001eM\u007f`\u000b\u001a8";
      O[10] = "\u0018_Z[6)\u000ej\u0004fA";
      O[11] = "Zu\u000f?\u0001__b\u000be\u0019XW)\tb\u0003\u0013Au\rt@t[t\u0006]\u0007NA";
      O[12] = void.class;
      P[12] = "java/lang/Void";
      O[13] = "7mw\u000bz'2zsQb :1qVxk,mu@;\u0004:ldWt&,V~V{\u000b7{u";
      O[14] = "<\u001e*\u0018e 9\t.B}'1B,Egl'\u001e(S$\u0001?\r>ED-7\t";
      O[15] = "m\b9k\u001er\u0018(2d\u000f=e0!c\u0006t\r";
      O[16] = int.class;
      P[16] = "java/lang/Integer";
      O[17] = "\u0013{Nc\u001a\u0012\u0005N\u0010^\u0002";
      O[18] = "A7/$P\u0014W\u0002q\u001f";
      O[19] = "\f2q+\u001d`y\u0012z$\f/\u0004\ni#\u0005fl";
      O[20] = "E\u001e69#2N\u0011'vB<E\u001a#,";
      O[21] = "\u0000%b\r\u0006O\u001eltfy*_(dY\u0005\u001a\u001bl|\u00029\u0010\bduZEV\u001e&gf\u0007GT1r\t\u0002U\u0001=\u0018[\u0005\u0010\r7d^TRYT";
      O[22] = "\u00184@L,F\u0006}V'^#C:THzDE=DC\u0013I\u0016\u007fXJ(B\u0004'P'/JC,UA/[\u0010|:\u0017uR\u0004}\u0004Nk]DE";
      O[23] = "Uk/\n\u001d]K\"9aY8\bpj[PAJc%\u000e\"\u0001[%o\u0013[CHj:aNTXs:\u001fIC\bkU[N\b\\&)\u001dXJN\u001a";
      O[24] = "\u0005\u0001X\u0019\u007fx\u0007\u0002CFBh?DO\u0007})\u000f\u0000\u000b\u001f&\u0015\u0003\u0016\f\u0012-s\u0003\u0007_BB(\u0003EZ\u0018>-R\u0007\u000e{\u007f)\u0005\u0016P\u0007zxGB3F!g_\u0012C\u00079uU\u007f\u000eGx|\\\u0003\u000b\u0016:(?";
      O[25] = "Ui[rA,\b.^gq\u0019jiV3K;\u0013+E|\u001eIS:\u00036\u00030\u0011)Lcq";
      O[26] = "q\u0001$]\u001f'oH26xB*\u000f0YI%,\b R {\u007fOdDY9l\u000016\u0019(*J,O[;e\u001f^\u000fJ}/\u0002'MY2zpnPQ:-N7N^z\u0015";
      O[27] = "?~I\u0010YP!7_{<5`sODZ\u0005$7W\u001ff\u000f7?^G\u001aI!}L{\r\u000f\"lP\u0012]\u0005ke3FZ\u000f2lOC\u000bMf\u000f";
      O[28] = "\u0004K[\u0004>K\u001a\u0002Mo@._EO\u0000hIYB_\u000b\u0001D\n\u0000C\u0002:O\u0018XKo1H\u0011B\u0019QhV\u001e\u0002!";
      O[29] = "x\u0015O$\u001f\u000bf\\YO\\nuXKsO\fc[\rs Wv[\u000f=Y\u0015e\u0014ZOLRn\u0003Yp@\u0005g\u00065#L\u0007u\u000bK$[Wmd\bs\u001a\u0007\u007f\u0018\r\"XS\u001c";
      O[30] = "\ffS@}d\u0012/E+\u0013\u0001WhGD+fQoWOB8\u0002(\u0013Y;z\u0011gF+{kW-[R9x\u0018x)\u0012(>RePP;q\u0007\u0017\u0019M3yP)@S<9h";
      O[31] = "\u000e'uL0\u0001\u0010nc'RdU)aHf\u0003S.qC\u000f]\u0000i5Uv\u001f\u0013&`'6\u000eUl}^t\u001d\u001a9\u000f\u001ee[P$v\\v\u0014\u0005V?A~\u001cRhf_q\\j";
      O[32] = "ej\f5\\\u007f{#\u001a^\u0005\u001a>d\u00181\n}8c\b:c!}gIbSe9\u007f\u0012^\tp;y\u001be\u0002bcqvn\u0005ky#H7\u001bd9\u001b";
      O[33] = "\f\u0002A*Y0\n\u0005Q!0>_\u0011/t\bfMM\u0017tO'\u0002}\u001f#A/\u000bCF=No3";
      O[34] = "{c\u001e\u001d6\u000ey`\u0005B\u000b&A&\t\u00034_qbM\u001boc}tJ\u0016d\u0005}e\u0019F\u000b^\"o\u0015\u0012{\u001f:}\u001f\u007f6_{t\u0016\u00033\u000e9 u";
      O[35] = ">DQ\u001d*z \rGvv\u001feJE\u0019|xcMU\u0012\u0015/<DSN+v\"K\u0013v";
      O[36] = "\u0007'.,M!\u0005$5sp.=0y0L#_&zvLL\u00043zt\u00025F 5!p Q0,!\u000e'F`4NI&\u0002c77\u000b5M6E";
      O[37] = "(\u0007*G4R6N<,T7u\u001co\u0016yN7\u000f C\u000b\u000e&Ij^rL5\u0006?,2]sL\"UpN<\u0019P\u0016g\u0007!J,PqE3v";
      O[38] = "\u0001$Jq^\u001e\u001fm\\\u001a&{^)L%]K\u001amT~aD\u001a;_s\u0006B\u001d+T\u001a\u000b\u0011_7]!\u0000\u0003\u0007?0";
      O[39] = "K*;F@\u0019Uc--&|\u0014'=\u0012CLPc%I\u007fFCk,\u0011\u0003\u0000U)>-A\u0011\u001f>+BD\u0003J2A\u0010CFF8=\u0015\u0012\u0004\u0012[";
   }
}
