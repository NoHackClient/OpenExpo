package Expo.enums;

import java.io.UnsupportedEncodingException;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;











public enum Easing {
   LINEAR(var0 -> var0),
   EASE_IN_QUAD(var0 -> var0 * var0),
   EASE_OUT_QUAD(var0 -> var0 * (2.0 - var0)),
   EASE_IN_OUT_QUAD(var0 -> var0 < 0.5 ? 2.0 * var0 * var0 : -1.0 + (4.0 - 2.0 * var0) * var0),
   EASE_IN_CUBIC(var0 -> var0 * var0 * var0),
   EASE_OUT_CUBIC(var0 -> (var0 = var0 - 1.0) * var0 * var0 + 1.0),
   EASE_IN_OUT_CUBIC(var0 -> var0 < 0.5 ? 4.0 * var0 * var0 * var0 : (var0 - 1.0) * (2.0 * var0 - 2.0) * (2.0 * var0 - 2.0) + 1.0),
   EASE_IN_QUART(var0 -> var0 * var0 * var0 * var0),
   EASE_OUT_QUART(var0 -> 1.0 - (var0 = var0 - 1.0) * var0 * var0 * var0),
   EASE_IN_OUT_QUART(var0 -> var0 < 0.5 ? 8.0 * var0 * var0 * var0 * var0 : 1.0 - 8.0 * (var0 = var0 - 1.0) * var0 * var0 * var0),
   EASE_IN_QUINT(var0 -> var0 * var0 * var0 * var0 * var0),
   EASE_OUT_QUINT(var0 -> 1.0 + (var0 = var0 - 1.0) * var0 * var0 * var0 * var0),
   EASE_IN_OUT_QUINT(var0 -> var0 < 0.5 ? 16.0 * var0 * var0 * var0 * var0 * var0 : 1.0 + 16.0 * (var0 = var0 - 1.0) * var0 * var0 * var0 * var0),
   EASE_IN_SINE(var0 -> 1.0 - Math.cos(var0 * Math.PI / 2.0)),
   EASE_OUT_SINE(var0 -> Math.sin(var0 * Math.PI / 2.0)),
   EASE_IN_OUT_SINE(var0 -> 1.0 - Math.cos(Math.PI * var0 / 2.0)),
   EASE_IN_EXPO(var0 -> var0 == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * var0 - 10.0)),
   EASE_OUT_EXPO(var0 -> var0 == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * var0)),
   EASE_IN_OUT_EXPO(
      var0 -> var0 == 0.0
         ? 0.0
         : (var0 == 1.0 ? 1.0 : (var0 < 0.5 ? Math.pow(2.0, 20.0 * var0 - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * var0 + 10.0)) / 2.0))
   ),
   EASE_IN_CIRC(var0 -> 1.0 - Math.sqrt(1.0 - var0 * var0)),
   EASE_OUT_CIRC(var0 -> Math.sqrt(1.0 - (var0 = var0 - 1.0) * var0)),
   EASE_IN_OUT_CIRC(var0 -> var0 < 0.5 ? (1.0 - Math.sqrt(1.0 - 4.0 * var0 * var0)) / 2.0 : (Math.sqrt(1.0 - 4.0 * (var0 - 1.0) * var0) + 1.0) / 2.0),
   SIGMOID(var0 -> 1.0 / (1.0 + Math.exp(-var0))),
   EASE_OUT_ELASTIC(
      var0 -> var0 == 0.0 ? 0.0 : (var0 == 1.0 ? 1.0 : Math.pow(2.0, -10.0 * var0) * Math.sin((var0 * 10.0 - 0.75) * (Math.PI * 2.0 / 3.0)) * 0.5 + 1.0)
   ),
   EASE_IN_BACK(var0 -> 2.70158 * var0 * var0 * var0 - 1.70158 * var0 * var0),
   DECELERATE(var0 -> 1.0 - (var0 - 1.0) * (var0 - 1.0));

   private final Function<Double, Double> b;
   private static boolean zkm$done;
   private static long[] zkm$v0;
   private static String[] zkm$v11;

   public String toString() {
      return StringUtils.capitalize(this.name().toLowerCase().replace("_", " "));
   }

   public Function<Double, Double> C() {
      return this.b;
   }

   static {
      Easing[] var10000 = new Easing[(int)zkm$g0()[7]];
      var10000[0] = LINEAR;
      var10000[1] = EASE_IN_QUAD;
      var10000[2] = EASE_OUT_QUAD;
      var10000[3] = EASE_IN_OUT_QUAD;
      var10000[4] = EASE_IN_CUBIC;
      var10000[5] = EASE_OUT_CUBIC;
      var10000[(int)zkm$g0()[8]] = EASE_IN_OUT_CUBIC;
      var10000[(int)zkm$g0()[17]] = EASE_IN_QUART;
      var10000[(int)zkm$g0()[10]] = EASE_OUT_QUART;
      var10000[(int)zkm$g0()[14]] = EASE_IN_OUT_QUART;
      var10000[(int)zkm$g0()[15]] = EASE_IN_QUINT;
      var10000[(int)zkm$g0()[3]] = EASE_OUT_QUINT;
      var10000[(int)zkm$g0()[33]] = EASE_IN_OUT_QUINT;
      var10000[(int)zkm$g0()[18]] = EASE_IN_SINE;
      var10000[(int)zkm$g0()[38]] = EASE_OUT_SINE;
      var10000[(int)zkm$g0()[26]] = EASE_IN_OUT_SINE;
      var10000[(int)zkm$g0()[32]] = EASE_IN_EXPO;
      var10000[(int)zkm$g0()[1]] = EASE_OUT_EXPO;
      var10000[(int)zkm$g0()[35]] = EASE_IN_OUT_EXPO;
      var10000[(int)zkm$g0()[31]] = EASE_IN_CIRC;
      var10000[(int)zkm$g0()[9]] = EASE_OUT_CIRC;
      var10000[(int)zkm$g0()[40]] = EASE_IN_OUT_CIRC;
      var10000[(int)zkm$g0()[34]] = SIGMOID;
      var10000[(int)zkm$g0()[11]] = EASE_OUT_ELASTIC;
      var10000[(int)zkm$g0()[29]] = EASE_IN_BACK;
      var10000[(int)zkm$g0()[6]] = DECELERATE;
   }

   Easing(Function<Double, Double> var3) {
      this.b = var3;
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

   private static void zkm$pre() {
      zkm$v11 = new String[]{"EASE_IN_BACK", "EASE_IN_QUART", "EASE_IN_OUT_SINE", "SIGMOID", "EASE_IN_CIRC", "EASE_OUT_QUINT", "EASE_IN_OUT_QUAD", "EASE_IN_OUT_CIRC", "EASE_OUT_CUBIC", "EASE_OUT_QUAD", "EASE_IN_QUINT", "EASE_OUT_CIRC", "EASE_IN_EXPO", "EASE_IN_CUBIC", "EASE_IN_OUT_QUART", "EASE_IN_OUT_CUBIC", "EASE_IN_OUT_EXPO", "LINEAR", "EASE_IN_OUT_QUINT", "EASE_OUT_QUART", "EASE_OUT_EXPO", "EASE_IN_SINE", "EASE_OUT_SINE", "EASE_OUT_ELASTIC", "DECELERATE", "EASE_IN_QUAD"};
      zkm$v0 = new long[]{-4498073971555565544L, -4922700783767519215L, 9187938526598004761L, 8964489606028328971L, -6863759601918017513L, -1903910228668186604L, 2415805970266456089L, -3209800438085844966L, -3758188936250261498L, -1614335222810148844L, 5493553485603405832L, -6208827202956427241L, 8745819966151852053L, -879099957754200046L, -6931985359278440439L, 1499549369966264330L, 8284773085400268817L, -6096960832657162233L, 8878542122114875405L, 3902166268760817680L, -3659392499325075450L, 1056636304994861075L, 6771224020424589323L, -3547059704167399410L, -5018844275228540915L, -8996775832970067953L, -3283323530799742961L, -7247874521657507830L, -2374763473239277556L, 2420774620362702872L, 3989115549501095945L, 4635766491306786835L, 1270031514578452496L, 6590793621140668428L, -237348598675668970L, 2162914864199106578L, 5598437506094727175L, 1398262736772661256L, 7667346995157139470L, 9161453069601341462L, 5102281359347417109L};
   }

   private static long[] zkm$g0() {
      if (!zkm$done) {
         zkm$done = true;
         zkm$pre();
      }

      return zkm$v0;
   }

}
