package Expo.ASM.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;


public class AsmUtil {
   private static volatile MappingKind i;
   private static String[] e;
   private static Map<MappingKind, Map<FieldRef, FieldMapping>> P;
   private static Map j;
   private static String[] d;
   private static String c;
   private static Map<MappingKind, Map<FieldRef, FieldMapping>> B;
   private static Map<MappingKind, Map<MethodRef, MethodMapping>> Z;
   private static volatile boolean K;
   private static Map<MappingKind, Map<String, String>> N;
   private static Map<MappingKind, Map<String, String>> Y;
   private static Logger I;
   private static long[] g;
   private static long a;
   private static Map f;
   private static String C;
   private static ThreadLocal<MappingKind> r;
   private static Map<MappingKind, Map<MethodRef, MethodMapping>> b;
   private static ThreadLocal<MappingKind> v;
   private static Integer[] h;

   public static void M(List<MappingKind> var0, MappingKind var1) {
      if (var1 != null && !var0.contains(var1)) {
         var0.add(var1);
      }
   }

   public static MappingKind o(String var0) {
      if (var0 == null) {
         return N();
      }

      for (MappingKind var6 : MappingKind.values()) {
         if (var6.name().equalsIgnoreCase(var0)) {
            return var6;
         }
      }

      return N();
   }

   public static void E(Map<String, Class<?>> var0) {
      Set var3 = var0.keySet();
      boolean var4 = var3.contains("net.minecraft.client.Minecraft");
      boolean var5 = var3.contains("ave");
      MappingKind var6 = Z();
      i = var6 == null ? j(var0, var4, var5) : var6;
      ((Logger)I).info((String)"ASM injection namespace selected: {} (minecraftClassLoaded={}, notchMinecraftClassLoaded={}, reason={})", (Object[])new Object[]{N().name(), var4, var5, var6 == null ? d(var0, var4, var5) : "build-profile"});
   }

   public static void U(Set<String> var0, String var1, String var2, boolean var3) {
      boolean var6 = false;

      for (MappingKind var10 : MappingKind.values()) {
         String var11 = b(var10, var1);
         FieldMapping var12 = B.get(var10).get(new FieldRef(var11, var2));
         if (var12 != null) {
            var0.add(FieldMapping.U(var12));
            var6 = true;
         }
      }

      if (!var6 && var3 && H(var2)) {
         for (MappingKind var16 : MappingKind.values()) {
            for (Entry var18 : B.get(var16).entrySet()) {
               if (FieldRef.Z((FieldRef)var18.getKey()).equals(var2)) {
                  var0.add(FieldMapping.U((FieldMapping)var18.getValue()));
               }
            }
         }
      }
   }

   public static boolean v(FieldInsnNode var0, String var1, String var2, String... var3) {
      String var6 = b(var1);
      String var7 = n(var2);
      return var0.owner.equals(b(c(), var6)) && var0.desc.equals(Y(c(), var7)) && j(var0.name, G(var6, var3));
   }

   public static void O() {
      r.remove();
   }


   public static void a() {
      v.remove();
   }

   public static boolean H(ClassNode var0) {

      for (Object var4 : var0.methods) {
         MethodNode var5 = (MethodNode)var4;
         if (var5.name.startsWith("func_")) {
            return true;
         }
      }

      for (Object var7 : var0.fields) {
         FieldNode var8 = (FieldNode)var7;
         if (var8.name.startsWith("field_")) {
            return true;
         }
      }

      return false;
   }

   public static void h(MappingKind var0, String var1, String var2) {
      Y.get(var0).put(var1, var2);
      N.get(var0).put(var2, var1);
   }

   public static boolean E(Class<?> var0, String var1) {

      for (Field var7 : var0.getDeclaredFields()) {
         if (var7.getName().equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public static Set<String> i(String var0, String... var1) {
      String var4 = b(var0);
      LinkedHashSet var5 = new LinkedHashSet();

      for (String var9 : var1) {
         var5.add(var9);
         U(var5, var4, var9, true);
      }

      LinkedHashSet var13 = new LinkedHashSet();

      for (String var15 : (Iterable<String>)(var5)) {
         var13.add(var15);

         for (MappingKind var12 : MappingKind.values()) {
            o(var13, var12, var4, var15, true);
         }
      }

      return var13;
   }

   public static Set<String> D(String var0, String var1, String var2) {
      LinkedHashSet var5 = new LinkedHashSet();
      var5.add(q(var0, var1, var2));
      if (H(var2)) {
         for (MappingKind var9 : MappingKind.values()) {
            String var10 = Y(var9, var1);

            for (Entry var12 : b.get(var9).entrySet()) {
               MethodRef var13 = (MethodRef)var12.getKey();
               if (MethodRef.Q(var13).equals(var2) && MethodRef.V(var13).equals(var10)) {
                  var5.add(MethodMapping.Z((MethodMapping)var12.getValue()));
               }
            }
         }
      }

      return var5;
   }


   public static void E(Set<String> var0, String var1, String var2) {
      var0.add(var2);
      FieldMapping var5 = P.get(c()).get(new FieldRef(var1, var2));
      if (var5 != null) {
         var0.add(FieldMapping.U(var5));
      }

      for (Entry var7 : P.get(c()).entrySet()) {
         if (FieldRef.Z((FieldRef)var7.getKey()).equals(var2)) {
            var0.add(FieldMapping.U((FieldMapping)var7.getValue()));
         }
      }
   }

   public static Type l(MappingKind var0, Type var1) {
      switch (var1.getSort()) {
         case 9:
            Type var4 = l(var0, var1.getElementType());
            StringBuilder var5 = new StringBuilder();

            for (int var6 = 0; var6 < var1.getDimensions(); var6++) {
               var5.append((char)91);
            }

            var5.append(var4.getDescriptor());
            return Type.getType(var5.toString());
         case 10:
            return Type.getObjectType(b(var0, b(var1.getInternalName())));
         default:
            return var1;
      }
   }

   public static void U(Set<String> var0, String var1, String var2, String var3) {
      var0.add(var2);
      MethodMapping var6 = Z.get(c()).get(new MethodRef(var1, var2, var3));
      if (var6 != null) {
         var0.add(MethodMapping.Z(var6));
      }

      for (Entry var8 : Z.get(c()).entrySet()) {
         MethodRef var9 = (MethodRef)var8.getKey();
         if (MethodRef.Q(var9).equals(var2) && MethodRef.V(var9).equals(var3)) {
            var0.add(MethodMapping.Z((MethodMapping)var8.getValue()));
         }
      }
   }


   public static MappingKind Z() {
      try {
         return null;
      } catch (Throwable var1) {
         return null;
      }
   }

   public static void o(Set<String> var0, MappingKind var1, String var2, String var3, boolean var4) {
      boolean var7 = false;
      FieldMapping var8 = P.get(var1).get(new FieldRef(var2, var3));
      if (var8 != null) {
         var0.add(FieldMapping.U(var8));
         var7 = true;
      }

      if (!var7 && var4) {
         for (Entry var10 : P.get(var1).entrySet()) {
            if (FieldRef.Z((FieldRef)var10.getKey()).equals(var3)) {
               var0.add(FieldMapping.U((FieldMapping)var10.getValue()));
            }
         }
      }
   }

   public static String d(Map<String, Class<?>> var0, boolean var1, boolean var2) {
      Class var5 = (Class)var0.get("net.minecraft.client.Minecraft");
      if (var5 != null) {
         if (O(var5, "getMinecraft")) {
            return "Minecraft.getMinecraft present";
         } else if (E(var5, "thePlayer")) {
            return "Minecraft.thePlayer present";
         } else if (O(var5, "getMinecraft")) {
            return "Minecraft.getMinecraft present";
         } else {
            return E(var5, "thePlayer") ? "Minecraft.thePlayer present" : "net.minecraft class names without known singleton members";
         }
      } else if (!var0.containsKey("ave") && !var2) {
         return var1 ? "net.minecraft class names" : "fallback";
      } else {
         return "notch Minecraft class present";
      }
   }

   public static BufferedReader F(String var0) throws IOException {
      InputStream var3 = AsmUtil.class.getResourceAsStream(var0);
      if (var3 == null) {
         throw new IOException("missing resource " + var0);
      } else {
         return new BufferedReader(new InputStreamReader(var3, StandardCharsets.UTF_8));
      }
   }


   public static String j(String var0) {
      return var0 == null ? null : var0.replace((char)46, (char)47);
   }

   public static boolean B(String var0, MethodNode var1, String var2, String... var3) {
      String var6 = b(var0);
      String var7 = n(var2);
      return var1.desc.equals(Y(c(), var7)) && j(var1.name, q(var6, var7, var3));
   }

   public static void l(MappingKind var0, String var1, String var2, String var3, String var4, String var5, String var6) {
      Z.get(var0).put(new MethodRef(var1, var2, var3), new MethodMapping(var4, var5, var6));
      b.get(var0).put(new MethodRef(var4, var5, var6), new MethodMapping(var1, var2, var3));
   }

   public static String q(String var0, String var1) {
      String var4 = b(var0);
      String var5 = J(var4, var1);
      FieldMapping var6 = P.get(c()).get(new FieldRef(var4, var5));
      return var6 == null ? var5 : FieldMapping.U(var6);
   }


   public static void L(ClassNode var0) {
      MappingKind var3 = r.get();
      v.set(var3 == null ? Q(var0) : var3);
   }


   public static MappingKind Q(ClassNode var0) {
      if (var0 != null && var0.name != null) {
         String var3 = j(var0.name);
         if (N.get(MappingKind.NOTCH).containsKey(var3)) {
            return MappingKind.NOTCH;
         } else if (H(var0)) {
            return MappingKind.SRG;
         } else {
            return var3.startsWith("net/minecraft/") ? MappingKind.MCP : N();
         }
      } else {
         return N();
      }
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

   public static void M(Set<String> var0, String var1, String var2, String var3, boolean var4) {
      boolean var7 = false;

      for (MappingKind var11 : MappingKind.values()) {
         String var12 = b(var11, var1);
         String var13 = E(var11, var2);

         for (Entry var15 : b.get(var11).entrySet()) {
            MethodRef var16 = (MethodRef)var15.getKey();
            if (MethodRef.O(var16).equals(var12) && MethodRef.Q(var16).equals(var3) && L(MethodRef.V(var16)).equals(var13)) {
               var0.add(MethodMapping.Z((MethodMapping)var15.getValue()));
               var7 = true;
            }
         }
      }

      if (!var7 && var4 && H(var3)) {
         for (MappingKind var20 : MappingKind.values()) {
            String var21 = E(var20, var2);

            for (Entry var23 : b.get(var20).entrySet()) {
               MethodRef var24 = (MethodRef)var23.getKey();
               if (MethodRef.Q(var24).equals(var3) && L(MethodRef.V(var24)).equals(var21)) {
                  var0.add(MethodMapping.Z((MethodMapping)var23.getValue()));
               }
            }
         }
      }
   }

   public static void O(boolean var0) {
      i = null;
      K = var0;
      I.info("ASM runtime namespace: {}", new Object[]{N().name()});
   }

   public static Set<String> W(String var0, String var1) {
      LinkedHashSet var4 = new LinkedHashSet();
      var4.add(J(var0, var1));
      if (H(var1)) {
         for (MappingKind var8 : MappingKind.values()) {
            for (Entry var10 : B.get(var8).entrySet()) {
               if (FieldRef.Z((FieldRef)var10.getKey()).equals(var1)) {
                  var4.add(FieldMapping.U((FieldMapping)var10.getValue()));
               }
            }
         }
      }

      return var4;
   }

   private AsmUtil() {
   }

   public static String b(MappingKind var0, String var1) {
      String var4 = j(var1);
      String var5 = Y.get(var0).get(var4);
      return var5 == null ? var4 : var5;
   }


   public static OwnerNamePair K(String var0) {
      int var3 = var0.lastIndexOf(47);
      return new OwnerNamePair(var0.substring(0, var3), var0.substring(var3 + 1));
   }

   public static String D(String var0) {
      return b(c(), b(var0));
   }

   public static String Y(MappingKind var0, String var1) {
      if (var1 != null && var1.indexOf(76) >= 0) {
         if (var1.charAt(0) != 40) {
            return l(var0, Type.getType(var1)).getDescriptor();
         }

         Type[] var4 = Type.getArgumentTypes(var1);

         for (int var5 = 0; var5 < var4.length; var5++) {
            var4[var5] = l(var0, var4[var5]);
         }

         return Type.getMethodDescriptor(l(var0, Type.getReturnType(var1)), var4);
      } else {
         return var1;
      }
   }


   public static String q(String var0, String var1, String var2) {

      for (MappingKind var8 : MappingKind.values()) {
         String var9 = b(var8, var0);
         String var10 = Y(var8, var1);
         MethodMapping var11 = b.get(var8).get(new MethodRef(var9, var2, var10));
         if (var11 != null) {
            return MethodMapping.Z(var11);
         }
      }

      return var2;
   }

   public static void z(Set<String> var0, MappingKind var1, String var2, String var3, String var4, boolean var5) {
      boolean var8 = false;

      for (Entry var10 : Z.get(var1).entrySet()) {
         MethodRef var11 = (MethodRef)var10.getKey();
         if (MethodRef.O(var11).equals(var2) && MethodRef.Q(var11).equals(var4) && L(MethodRef.V(var11)).equals(var3)) {
            var0.add(MethodMapping.Z((MethodMapping)var10.getValue()));
            var8 = true;
         }
      }

      if (!var8 && var5) {
         for (Entry var13 : Z.get(var1).entrySet()) {
            MethodRef var14 = (MethodRef)var13.getKey();
            if (MethodRef.Q(var14).equals(var4) && L(MethodRef.V(var14)).equals(var3)) {
               var0.add(MethodMapping.Z((MethodMapping)var13.getValue()));
            }
         }
      }
   }

   public static String v(String var0) {
      return Y(c(), n(var0));
   }

   public static String R(String var0) {
      return v(var0);
   }

   public static boolean y$r1(String var0) {
      String var3 = j(var0);
      if (var3 == null) {
         return false;
      } else {
         return var3.startsWith("net/minecraft/") ? true : N.get(MappingKind.SRG).containsKey(var3) || N.get(MappingKind.NOTCH).containsKey(var3);
      }
   }

   public static String E(MappingKind var0, String var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var4 = Y(var0, var1 + "V");
         return L(var4);
      } else {
         return "";
      }
   }

   public static String w(String var0, String var1) {
      return q(var0, var1);
   }


   public static String J(String var0, String var1) {

      for (MappingKind var7 : MappingKind.values()) {
         String var8 = b(var7, var0);
         FieldMapping var9 = B.get(var7).get(new FieldRef(var8, var1));
         if (var9 != null) {
            return FieldMapping.U(var9);
         }
      }

      return var1;
   }

   public static void H(String var0, MappingKind var1) throws Throwable {

      String var6;
      try (BufferedReader var4 = F(var0)) {
         while ((var6 = var4.readLine()) != null) {
            String var7 = var6.trim();
            if (!var7.isEmpty() && !var7.startsWith("#")) {
               String[] var8 = var7.split("\\s+");
               if (var8.length == 3 && "CL:".equals(var8[0])) {
                  h(var1, var8[1], var8[2]);
               } else if (var8.length == 5 && "MD:".equals(var8[0])) {
                  OwnerNamePair var22 = K(var8[1]);
                  OwnerNamePair var23 = K(var8[3]);
                  l(var1, OwnerNamePair.U(var22), OwnerNamePair.A(var22), var8[2], OwnerNamePair.U(var23), OwnerNamePair.A(var23), var8[4]);
               } else if (var8.length == 3 && "FD:".equals(var8[0])) {
                  OwnerNamePair var9 = K(var8[1]);
                  OwnerNamePair var10 = K(var8[2]);
                  o(var1, OwnerNamePair.U(var9), OwnerNamePair.A(var9), OwnerNamePair.U(var10), OwnerNamePair.A(var10));
               }
            }
         }
      } catch (IOException var21) {
         I.warn("Cannot load {} mappings from {}: {}", new Object[]{var1.name(), var0, var21.getMessage()});
      }
   }

   public static MappingKind c() {
      MappingKind var2 = v.get();
      return var2 == null ? N() : var2;
   }

   public static boolean t(String var0, MethodNode var1, String... var2) {
      String var5 = b(var0);
      String var6 = n(var1.desc);
      return j(var1.name, q(var5, var6, var2));
   }

   public static String X(String var0) {
      return D(var0);
   }

   public static Set<String> q(String var0, String var1, String... var2) {
      LinkedHashSet var5 = new LinkedHashSet();

      for (String var9 : var2) {
         for (String var12 : D(var0, var1, var9)) {
            U(var5, var0, var12, var1);
         }
      }

      return var5;
   }

   public static void X(MappingKind var0) {
      Y.put(var0, new LinkedHashMap<>());
      N.put(var0, new LinkedHashMap<>());
      Z.put(var0, new LinkedHashMap<>());
      b.put(var0, new LinkedHashMap<>());
      P.put(var0, new LinkedHashMap<>());
      B.put(var0, new LinkedHashMap<>());
   }


   public static void r(String var0) {
      r.set(o(var0));
   }


   public static String b(String var0) {
      String var3 = j(var0);
      if (var3 == null) {
         return null;
      }

      for (MappingKind var7 : MappingKind.values()) {
         String var8 = N.get(var7).get(var3);
         if (var8 != null) {
            return var8;
         }
      }

      return var3;
   }

   public static boolean O(Class<?> var0, String var1) {

      for (Method var7 : var0.getDeclaredMethods()) {
         if (var7.getName().equals(var1)) {
            return true;
         }
      }

      return false;
   }

   public static Set<String> G(String var0, String... var1) {
      LinkedHashSet var4 = new LinkedHashSet();

      for (String var8 : var1) {
         for (String var11 : W(var0, var8)) {
            E(var4, var0, var11);
         }
      }

      return var4;
   }

   public static boolean b(MethodInsnNode var0, String var1, String var2, String... var3) {
      String var6 = b(var1);
      String var7 = n(var2);
      return var0.owner.equals(b(c(), var6)) && var0.desc.equals(Y(c(), var7)) && j(var0.name, q(var6, var7, var3));
   }

   public static String S(String var0, String var1, String var2) {
      return O(var0, var1, var2);
   }

   static {
      a = 90580821667740L;
      zkm$clinit();
   }

   public static boolean H(String var0) {
      return var0 != null && (var0.startsWith("func_") || var0.startsWith("field_") || var0.length() > 2);
   }


   public static Set<String> Q(String var0, String var1, String... var2) {
      String var5 = b(var0);
      String var6 = L(n(var1));
      LinkedHashSet var7 = new LinkedHashSet();

      for (String var11 : var2) {
         var7.add(var11);
         M(var7, var5, var6, var11, true);
      }

      LinkedHashSet var15 = new LinkedHashSet();

      for (String var17 : (Iterable<String>)(var7)) {
         var15.add(var17);

         for (MappingKind var14 : MappingKind.values()) {
            z(var15, var14, var5, var6, var17, true);
         }
      }

      return var15;
   }

   private static int b(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 25087;
      if (h[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = g[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().getId();
         Object[] var9 = (Object[])j.get(var8);

         byte[] var13;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               j.put(var8, var9);
            }

            DESKeySpec var10 = new DESKeySpec(var4);
            SecretKey var11 = ((SecretKeyFactory)var9[1]).generateSecret(var10);
            Cipher var12 = (Cipher)var9[0];
            var12.init(2, var11, (IvParameterSpec)var9[2]);
            var13 = var12.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("Expo/ASM/Util/AsmUtil", var14);
         }

         int var15 = (var13[4] & 255) << 24 | (var13[5] & 255) << 16 | (var13[6] & 255) << 8 | var13[7] & 255;
         h[var3] = var15;
      }

      return h[var3];
   }

   public static MappingKind N() {
      if (i != null) {
         return i;
      } else {
         return K ? MappingKind.SRG : MappingKind.MCP;
      }
   }

   public static String y(String var0) {
      return D(var0).replace((char)47, (char)46);
   }

   public static String L(String var0) {
      if (var0 != null && !var0.isEmpty() && var0.charAt(0) == 40) {
         int var3 = var0.indexOf(41);
         return var3 < 0 ? var0 : var0.substring(0, var3 + 1);
      } else {
         return "";
      }
   }

   public static MappingKind j(Map<String, Class<?>> var0, boolean var1, boolean var2) {
      Class var5 = (Class)var0.get("net.minecraft.client.Minecraft");
      if (var5 != null) {
         if (O(var5, "getMinecraft") || E(var5, "thePlayer")) {
            return MappingKind.MCP;
         }

         if (O(var5, "getMinecraft") || E(var5, "thePlayer")) {
            return MappingKind.SRG;
         }
      }

      Class var6 = (Class)var0.get("ave");
      if (var6 == null && !var2) {
         return var1 ? MappingKind.MCP : MappingKind.SRG;
      } else {
         return MappingKind.NOTCH;
      }
   }

   public static boolean j(String var0, Set<String> var1) {
      for (String var3 : var1) {
         if (var0.equals(var3)) {
            return true;
         }
      }

      return false;
   }

   public static String[] z(byte[] var0) {
      ClassNode var3 = new ClassNode();
      new ClassReader(var0).accept(var3, 7);
      ArrayList var4 = new ArrayList();
      M(var4, Q(var3));
      M(var4, N());
      M(var4, MappingKind.MCP);
      M(var4, MappingKind.SRG);
      M(var4, MappingKind.NOTCH);
      String[] var5 = new String[var4.size()];

      for (int var6 = 0; var6 < var4.size(); var6++) {
         var5[var6] = ((MappingKind)var4.get(var6)).name();
      }

      return var5;
   }

   public static void o(MappingKind var0, String var1, String var2, String var3, String var4) {
      P.get(var0).put(new FieldRef(var1, var2), new FieldMapping(var3, var4));
      B.get(var0).put(new FieldRef(var3, var4), new FieldMapping(var1, var2));
   }

   public static String O(String var0, String var1, String var2) {
      String var5 = b(var0);
      String var6 = n(var2);
      String var7 = q(var5, var6, var1);
      MethodMapping var8 = Z.get(c()).get(new MethodRef(var5, var7, var6));
      return var8 == null ? var7 : MethodMapping.Z(var8);
   }

   public static String n(String var0) {
      return Y(MappingKind.MCP, var0);
   }

   private static void zkm$clinit() {
      try {
         long var0 = a ^ 83507682439898L;
         f = new HashMap(13);
         Cipher var2;
         byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

         for (int var3 = 1; var3 < 8; var3++) {
            var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
         }

         (var2 = Cipher.getInstance("DES/CBC/PKCS5Padding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
         String[] var4 = new String[41];
         int var5 = 0;
         String var6 = "\u0010\u0010ìëqR¼ú¿T*-Nm\bR\u0011\u001a°ø{½Pj\u0085Áyé\"öFÞf\u0088FÃ\u001cn¤»C7l¿¾Ì@¯rÏ®UØC]Ê8\u0013úÒiý·Û6+\u0005\u0004ªÕç|Ë½@\u0097å\u0001Á3pÍ-Ø\u0089;Ö\u0091þFãAiøýk\u0082\u001cù\t\u008bj^B\u000e\u009aM\u00adTt2w¿\u0010e\u0002'Ü·q\u0091\u0019\u0003°¢¡è¥\u007fê\u0018s»0zCG\u00035\u0086+\u009ad\u0089åakÉ\u001144Õ\u0016\u0001\u008c0þ¹Úý\u0095«¢¦}\u0018sí\u0081\u001bU\u0099îÜUÉ\u001e\u008eÈ½\u007f\u0000\u0081Ner¾±êZ6¹\u00195T¾<DBôUjB\u0015 w\u009c\u0097íÖZé+\nníÉ»\u0014oë\u0085\u00956Q\u0098\u001b|Ñîñ°;¸È\u0090\u0019\u0010\u0088r/\b\u001c¾ãÔl4ç\u000fNÆÉv\u0010M?Ò\u008e¾¨¤\u0016 P\n¹\u0090ÐhV L^µú\u0005 Ä1qoìV°çs8;ÞEùR\u0014\u0091\u0018Pô\u001bÜµ\u0092ºë8]\u008dúWwk3RÇ|qÇ.ïø&ì\u0010î8Åã!\u0010©¢¤¿ã\u0007°®ùôåÍNéñÏ2ýø\fÓãÒi\u0000qõ«(²f_\u0018A\u008b®Õ\r\u000f¹\u000b³8Âñ\\fÅ¯\u0091ÎoÐà\u0088æ6\u0010\u009a\u0090\u000eqe\u0019½\u001c³'=TÅ<Þõ8÷ôu/z\u001aé±ÝÚh*5®uKû¿²ÊÐä2rÃ*ý\u0000ö\u0092ÖÏ¥i+-*\u0007\u001b\u0083Þ·.M¤eP¤¯ô\u0010\u0085>cÔ6 ×\u009d\u001fY[GÐî`Ø`\u0092ß?r\u0089*I`Rÿ¢¹À\u0016'?B¶åìöh\u0002låkX*\u00876ÁÉ,7\u008dÃU\u001c2^A°<u\u0010ø9\u0081¤j\u009a\u0012¡Õ\u0082\u0093\u0091Òû\bHpØÚ|\u0001u\u0083Gâ\u0015\u0089Ñ¾8j\u008e wÀFÂªoao¦4;\u001bwpO\u001ed\u0019¡u[\u00991ö¯ÎÁ)s\u0004Qaj¬¤´ß]7\u0004P+T·\u009a\u000fÜâ 2}Û\u0004\u001f\u009aÇµ\u0013\"1[\"à4ï|èw\u0005\u009d¥\u009d'\"\u0018Îôw/Æ\u00988ý zíÌud0ý¡ª\u0086\u008c7?¬°2\u009dlÐ²\b\u0099å[+NËBsÏ.É&\u0019ã£3ðä¡X!¡ÂÚØÇWHÜ\u001e\u001b\u009dç0ê\\\u0012¼uý\u0013cÛ\fÿV\u0080m\u0019O¦æNÃê!\u0080ü,\u0081\u0094Õ\u009e\u00ad1N\u0099\u007fÉìg|ÂêêH\u0086·v\u008d\u001aº\u0010¥;î\u0099Ê\u0086ÛÂ©6\u001a®\u0097ó\u0000( 6·Äã\"¼1\u0015òt6\u0081wÝ\u0016Ù ?t©À\u0090vzg|\u0085h ³ä\u0097~{r)½rAÏ\u0090b~Õ¯Ù\u008d}\u0086¨Ò`\u0083m¯½`©£Gàø\u008b\u001c\u00ad\u0086\u0015º¿ön¿q\u0093\u0018\u0087k±¦ñ¬ÍÌ_ã\"\u00ad«çQ\rf3G±«L\u008agSX\u0082QsV±\u009c[Q\u0003ßýhe\u0098½¦%\u0005ýª\u0018è]\u0015+4ôÏ¤Ø°Pê\u001b\r(§Õaza¼nª\n\u0090\u008fPéÔI7JñÌ\u0093å\u0001í\u0010º)ìV(@Aô\u0091¨=;tí(³ \u0011\u0080¯í-\u0007þ ©x\u0002¾¢¦\u0087ã\u007f\u009a0©ëÉ\u0080-\u0018q t\u008ba%\u0096\u0010W`\u0096s\u008cÜ+\"Õöûê¶cO°\u0018å¸\u0088f\u009fb4ôÜ\u0089\u008fÈSRïx¾`g\u0080M©mJ8\u0014ï\u0018ø\u0015ï§q¥}BNÙ\u0089\u000ey\u0086\u0019[ÝªÉ\u0084Åý\u0005.A\u001fýx\u0010¯xfû\u0019¯/ù?=â¸Fns\u000f¥}%U´V\u0014\u0089@\u009fò\u0081\u001e8¿\u0011\u0090:ù\b8\u009dI\u0096æîm²~~\u0004é-òxð\u0084\fT¿xÜ®1\u009b\u007fV~\u0096\u001fµ\u008eÙÕë_o?t}\u0098YTÑÿô´ÌIá$3ð8ewÛy\u008exÛ;!\u009f\u0098õ\u007f;æ»,hÙæpOâ\u0013¯PÁÌ\u0095£¾\n<Õ\u008d¬eZ\u0095þíµ-Ìµóü¦ \u0095ÇÃå-b}\u0018ÇaPêëOI¯\u0003\u0017º¿7\u0003L\u001e×o|\u0013º3û.@\u00ad4õÀÒð\u0005\u009eu¦ý¢\u001c{\u0085HãX\u001aØO,\u001c©jsÄWq\tY\u009b+\u001f\u0013ÿ\b ç\u0099¹Ûgª\u0011¦ûà\u009c\u0083X\u0013ë\u0096æhÌõÇ\u0007(\u009b£I òÇùr\u0084p¾ÀÒ,ëAsÏ`¼þ\u0004l\u0085\u0019\"Á¤}¨µµ\u0006â]ûg\u008e\u0087SëÝ]\"ºL\u008dE\u0085»î\u008aý\u0090T\u0094ïBÂê½o\u0090Á\u009añ{7±+ðx\u0090nM34S\u0095±Á\u0013¢»¶¬Âa.o \u0094=\u0098ê\bÖÄ²ýí\u0003Ôw\u0088ªÖ\u0098y\u0082.\u0015\u00947µ\u0004=qè2f\u0094\u0010\u0013y\fþjõ¹Å\u009d\u0095d\u0004N¤\u0014ëñ\u009bF\u00adVpDmY\u001dÍu@3éAtvN·Bº(`\u009a\u0010K=îú8á¤¯I9ìÔ4-ÉÖ(»Ü¤½9}<³ßØ\u009f\u0018öP\u0083\u0084²mËÕÌ¹\u008e\u0018´õ\u000e÷,\bq\u0085AJÅzÛ<²Y\u0010+þÃÍíPjT\u0016#\u001eÛ`¹¤i(Õæå=\u0005kÿ\u0011K\u0003\u009c`\"ïs\u000b²HÿÛE\u0018R ! \u008d\u001b\u0099á:ã\n½w\r²r®\u000b q««\u001cÃ¬¯1_ªÌýpÂN_â\u0019?\u0090tÛËgÙ\u0094WàùYç\t #©ÚK±új©NOb½\u009aÞ^¥o?µ¼§\u0099\u0019E\"£\ré»&P-0Qfç\"%»'\u00946F§¹\u008c\u0006ÌéêÉ\u0081±\u0086ØQm^*¶,ØºÊ\u0014Ä¤¥ÅZ\u001ci\u001f=ÛM(5Tþð wA·>\u0004\u00ad¤u¦Å×çßûL\u0006ÿ£!8VË\u009bAT\u0090b>¯±\n\u009b0·×\u0019ãôX¡h}5b¯äÛI<HÔ:Õ&`¯\u000f]\nz\u0001Qe\u0000$\u0090hÄ\u009cV\u0098ëÌ\u007f^)¶Gý\u0080\u000f";
         int var7 = "\u0010\u0010ìëqR¼ú¿T*-Nm\bR\u0011\u001a°ø{½Pj\u0085Áyé\"öFÞf\u0088FÃ\u001cn¤»C7l¿¾Ì@¯rÏ®UØC]Ê8\u0013úÒiý·Û6+\u0005\u0004ªÕç|Ë½@\u0097å\u0001Á3pÍ-Ø\u0089;Ö\u0091þFãAiøýk\u0082\u001cù\t\u008bj^B\u000e\u009aM\u00adTt2w¿\u0010e\u0002'Ü·q\u0091\u0019\u0003°¢¡è¥\u007fê\u0018s»0zCG\u00035\u0086+\u009ad\u0089åakÉ\u001144Õ\u0016\u0001\u008c0þ¹Úý\u0095«¢¦}\u0018sí\u0081\u001bU\u0099îÜUÉ\u001e\u008eÈ½\u007f\u0000\u0081Ner¾±êZ6¹\u00195T¾<DBôUjB\u0015 w\u009c\u0097íÖZé+\nníÉ»\u0014oë\u0085\u00956Q\u0098\u001b|Ñîñ°;¸È\u0090\u0019\u0010\u0088r/\b\u001c¾ãÔl4ç\u000fNÆÉv\u0010M?Ò\u008e¾¨¤\u0016 P\n¹\u0090ÐhV L^µú\u0005 Ä1qoìV°çs8;ÞEùR\u0014\u0091\u0018Pô\u001bÜµ\u0092ºë8]\u008dúWwk3RÇ|qÇ.ïø&ì\u0010î8Åã!\u0010©¢¤¿ã\u0007°®ùôåÍNéñÏ2ýø\fÓãÒi\u0000qõ«(²f_\u0018A\u008b®Õ\r\u000f¹\u000b³8Âñ\\fÅ¯\u0091ÎoÐà\u0088æ6\u0010\u009a\u0090\u000eqe\u0019½\u001c³'=TÅ<Þõ8÷ôu/z\u001aé±ÝÚh*5®uKû¿²ÊÐä2rÃ*ý\u0000ö\u0092ÖÏ¥i+-*\u0007\u001b\u0083Þ·.M¤eP¤¯ô\u0010\u0085>cÔ6 ×\u009d\u001fY[GÐî`Ø`\u0092ß?r\u0089*I`Rÿ¢¹À\u0016'?B¶åìöh\u0002låkX*\u00876ÁÉ,7\u008dÃU\u001c2^A°<u\u0010ø9\u0081¤j\u009a\u0012¡Õ\u0082\u0093\u0091Òû\bHpØÚ|\u0001u\u0083Gâ\u0015\u0089Ñ¾8j\u008e wÀFÂªoao¦4;\u001bwpO\u001ed\u0019¡u[\u00991ö¯ÎÁ)s\u0004Qaj¬¤´ß]7\u0004P+T·\u009a\u000fÜâ 2}Û\u0004\u001f\u009aÇµ\u0013\"1[\"à4ï|èw\u0005\u009d¥\u009d'\"\u0018Îôw/Æ\u00988ý zíÌud0ý¡ª\u0086\u008c7?¬°2\u009dlÐ²\b\u0099å[+NËBsÏ.É&\u0019ã£3ðä¡X!¡ÂÚØÇWHÜ\u001e\u001b\u009dç0ê\\\u0012¼uý\u0013cÛ\fÿV\u0080m\u0019O¦æNÃê!\u0080ü,\u0081\u0094Õ\u009e\u00ad1N\u0099\u007fÉìg|ÂêêH\u0086·v\u008d\u001aº\u0010¥;î\u0099Ê\u0086ÛÂ©6\u001a®\u0097ó\u0000( 6·Äã\"¼1\u0015òt6\u0081wÝ\u0016Ù ?t©À\u0090vzg|\u0085h ³ä\u0097~{r)½rAÏ\u0090b~Õ¯Ù\u008d}\u0086¨Ò`\u0083m¯½`©£Gàø\u008b\u001c\u00ad\u0086\u0015º¿ön¿q\u0093\u0018\u0087k±¦ñ¬ÍÌ_ã\"\u00ad«çQ\rf3G±«L\u008agSX\u0082QsV±\u009c[Q\u0003ßýhe\u0098½¦%\u0005ýª\u0018è]\u0015+4ôÏ¤Ø°Pê\u001b\r(§Õaza¼nª\n\u0090\u008fPéÔI7JñÌ\u0093å\u0001í\u0010º)ìV(@Aô\u0091¨=;tí(³ \u0011\u0080¯í-\u0007þ ©x\u0002¾¢¦\u0087ã\u007f\u009a0©ëÉ\u0080-\u0018q t\u008ba%\u0096\u0010W`\u0096s\u008cÜ+\"Õöûê¶cO°\u0018å¸\u0088f\u009fb4ôÜ\u0089\u008fÈSRïx¾`g\u0080M©mJ8\u0014ï\u0018ø\u0015ï§q¥}BNÙ\u0089\u000ey\u0086\u0019[ÝªÉ\u0084Åý\u0005.A\u001fýx\u0010¯xfû\u0019¯/ù?=â¸Fns\u000f¥}%U´V\u0014\u0089@\u009fò\u0081\u001e8¿\u0011\u0090:ù\b8\u009dI\u0096æîm²~~\u0004é-òxð\u0084\fT¿xÜ®1\u009b\u007fV~\u0096\u001fµ\u008eÙÕë_o?t}\u0098YTÑÿô´ÌIá$3ð8ewÛy\u008exÛ;!\u009f\u0098õ\u007f;æ»,hÙæpOâ\u0013¯PÁÌ\u0095£¾\n<Õ\u008d¬eZ\u0095þíµ-Ìµóü¦ \u0095ÇÃå-b}\u0018ÇaPêëOI¯\u0003\u0017º¿7\u0003L\u001e×o|\u0013º3û.@\u00ad4õÀÒð\u0005\u009eu¦ý¢\u001c{\u0085HãX\u001aØO,\u001c©jsÄWq\tY\u009b+\u001f\u0013ÿ\b ç\u0099¹Ûgª\u0011¦ûà\u009c\u0083X\u0013ë\u0096æhÌõÇ\u0007(\u009b£I òÇùr\u0084p¾ÀÒ,ëAsÏ`¼þ\u0004l\u0085\u0019\"Á¤}¨µµ\u0006â]ûg\u008e\u0087SëÝ]\"ºL\u008dE\u0085»î\u008aý\u0090T\u0094ïBÂê½o\u0090Á\u009añ{7±+ðx\u0090nM34S\u0095±Á\u0013¢»¶¬Âa.o \u0094=\u0098ê\bÖÄ²ýí\u0003Ôw\u0088ªÖ\u0098y\u0082.\u0015\u00947µ\u0004=qè2f\u0094\u0010\u0013y\fþjõ¹Å\u009d\u0095d\u0004N¤\u0014ëñ\u009bF\u00adVpDmY\u001dÍu@3éAtvN·Bº(`\u009a\u0010K=îú8á¤¯I9ìÔ4-ÉÖ(»Ü¤½9}<³ßØ\u009f\u0018öP\u0083\u0084²mËÕÌ¹\u008e\u0018´õ\u000e÷,\bq\u0085AJÅzÛ<²Y\u0010+þÃÍíPjT\u0016#\u001eÛ`¹¤i(Õæå=\u0005kÿ\u0011K\u0003\u009c`\"ïs\u000b²HÿÛE\u0018R ! \u008d\u001b\u0099á:ã\n½w\r²r®\u000b q««\u001cÃ¬¯1_ªÌýpÂN_â\u0019?\u0090tÛËgÙ\u0094WàùYç\t #©ÚK±új©NOb½\u009aÞ^¥o?µ¼§\u0099\u0019E\"£\ré»&P-0Qfç\"%»'\u00946F§¹\u008c\u0006ÌéêÉ\u0081±\u0086ØQm^*¶,ØºÊ\u0014Ä¤¥ÅZ\u001ci\u001f=ÛM(5Tþð wA·>\u0004\u00ad¤u¦Å×çßûL\u0006ÿ£!8VË\u009bAT\u0090b>¯±\n\u009b0·×\u0019ãôX¡h}5b¯äÛI<HÔ:Õ&`¯\u000f]\nz\u0001Qe\u0000$\u0090hÄ\u009cV\u0098ëÌ\u007f^)¶Gý\u0080\u000f"
            .length();
         char var8 = '8';
         int var24 = -1;

         label58:
         while (true) {
            String var26 = var6.substring(++var24, var24 + var8);
            int var10001 = -1;

            while (true) {
               byte[] var10 = var2.doFinal(var26.getBytes("ISO-8859-1"));
               String var37 = a(var10).intern();
               switch (var10001) {
                  case 0:
                     var4[var5++] = var37;
                     if ((var24 += var8) >= var7) {
                        d = var4;
                        e = new String[41];
                        C = "/assets/expo/asm/mcp-srg.srg";
                        c = "/assets/expo/asm/mcp-notch.srg";
                        j = new HashMap(13);
                        Cipher var11;
                        var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                        for (int var12 = 1; var12 < 8; var12++) {
                           var10003[var12] = (byte)(var0 << var12 * 8 >>> 56);
                        }

                        (var11 = Cipher.getInstance("DES/CBC/NoPadding")).init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                        long[] var13 = new long[10];
                        int var14 = 0;
                        String var15 = "®\u007f\u009d´\u001dW\u009dy¹n[vÓÕ×ô§\u008fÁü*\u0007ïø¤*bÈ\u0098I>M\u0007\u008aïo®4\u008aæ\u0085û°IÎ\u008dnÆ÷ar¹`\u001b®Â\u008d©_Ã´µ\u008c®";
                        int var16 = "®\u007f\u009d´\u001dW\u009dy¹n[vÓÕ×ô§\u008fÁü*\u0007ïø¤*bÈ\u0098I>M\u0007\u008aïo®4\u008aæ\u0085û°IÎ\u008dnÆ÷ar¹`\u001b®Â\u008d©_Ã´µ\u008c®"
                           .length();
                        int var17 = 0;

                        label40:
                        while (true) {
                           var10001 = var17;
                           var17 += 8;
                           byte[] var18 = var15.substring(var10001, var17).getBytes("ISO-8859-1");
                           long[] var29 = var13;
                           var10001 = var14++;
                           long var41 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           int var44 = -1;

                           while (true) {
                              long var19 = var41;
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
                              long var46 = (var21[0] & 255L) << 56
                                 | (var21[1] & 255L) << 48
                                 | (var21[2] & 255L) << 40
                                 | (var21[3] & 255L) << 32
                                 | (var21[4] & 255L) << 24
                                 | (var21[5] & 255L) << 16
                                 | (var21[6] & 255L) << 8
                                 | var21[7] & 255L;
                              switch (var44) {
                                 case 0:
                                    var29[var10001] = var46;
                                    if (var17 >= var16) {
                                       g = var13;
                                       h = new Integer[10];
                                       I = LogManager.getLogger("Expo ASM Mappings");
                                       v = new ThreadLocal<>();
                                       Y = new EnumMap<>(MappingKind.class);
                                       N = new EnumMap<>(MappingKind.class);
                                       Z = new EnumMap<>(MappingKind.class);
                                       b = new EnumMap<>(MappingKind.class);
                                       P = new EnumMap<>(MappingKind.class);
                                       B = new EnumMap<>(MappingKind.class);
                                       r = new ThreadLocal<>();
                                       X(MappingKind.MCP);
                                       X(MappingKind.SRG);
                                       X(MappingKind.NOTCH);
                                       H("/assets/expo/asm/mcp-srg.srg", MappingKind.SRG);
                                       H("/assets/expo/asm/mcp-notch.srg", MappingKind.NOTCH);
                                       I.info(
                                          "Loaded ASM mappings: srgMethods={}, srgFields={}, notchClasses={}, notchMethods={}, notchFields={}",
                                          new Object[]{
                                             Z.get(MappingKind.SRG).size(), P.get(MappingKind.SRG).size(), Y.get(MappingKind.NOTCH).size(), Z.get(MappingKind.NOTCH).size(), P.get(MappingKind.NOTCH).size()
                                          }
                                       );
                                       return;
                                    }
                                    break;
                                 default:
                                    var29[var10001] = var46;
                                    if (var17 < var16) {
                                       continue label40;
                                    }

                                    var15 = "ÅrÄ\u0084LF¹\u007f\u008b¬ëe\u0001°ì8";
                                    var16 = "ÅrÄ\u0084LF¹\u007f\u008b¬ëe\u0001°ì8".length();
                                    var17 = 0;
                              }

                              int var35 = var17;
                              var17 += 8;
                              var18 = var15.substring(var35, var17).getBytes("ISO-8859-1");
                              var29 = var13;
                              var10001 = var14++;
                              var41 = (var18[0] & 255L) << 56
                                 | (var18[1] & 255L) << 48
                                 | (var18[2] & 255L) << 40
                                 | (var18[3] & 255L) << 32
                                 | (var18[4] & 255L) << 24
                                 | (var18[5] & 255L) << 16
                                 | (var18[6] & 255L) << 8
                                 | var18[7] & 255L;
                              var44 = 0;
                           }
                        }
                     }

                     var8 = var6.charAt(var24);
                     break;
                  default:
                     var4[var5++] = var37;
                     if ((var24 += var8) < var7) {
                        var8 = var6.charAt(var24);
                        continue label58;
                     }

                     var6 = "ª\u0095iö\u0006JQæ¼mÜ¾eÎ.20m\u0082\u0082|zÚR&P\u0004¹ÚC9ê/ÙKâu±0¾\f¿\u001a\u008a\u0084\u0010å@bÑ'\u0081µ\u0015Pý\u008b¦½\u0094=\u0012m{\n";
                     var7 = "ª\u0095iö\u0006JQæ¼mÜ¾eÎ.20m\u0082\u0082|zÚR&P\u0004¹ÚC9ê/ÙKâu±0¾\f¿\u001a\u008a\u0084\u0010å@bÑ'\u0081µ\u0015Pý\u008b¦½\u0094=\u0012m{\n"
                        .length();
                     var8 = 16;
                     var24 = -1;
               }

               var26 = var6.substring(++var24, var24 + var8);
               var10001 = 0;
            }
         }
      } catch (Throwable var22) {
         throw new RuntimeException(var22);
      }
   }

}
