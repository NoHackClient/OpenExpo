package Expo.util.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class ShaderProgram {
   private static long a;
   public final int S;

   public void P() {
      GL20.glUseProgram(0);
   }

   private static int T( String var2, int var3) {
      int var4 = GL20.glCreateShader(var3);
      GL20.glShaderSource(var4, var2);
      GL20.glCompileShader(var4);
      if (GL20.glGetShaderi(var4, 35713) == 0) {
         throw new IllegalStateException("Shader failed to compile: " + GL20.glGetShaderInfoLog(var4, 4096));
      } else {
         return var4;
      }
   }

   public ShaderProgram(long var1, String var3) {
      var1 = a ^ var1;
      int var6 = GL20.glCreateProgram();
      int var7 = T( var3, 35633);
      int var8 = T(
         "#version 120\nvoid main() {\n    gl_TexCoord[0] = gl_MultiTexCoord0;\n    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}\n",
         35632
      );
      GL20.glAttachShader(var6, var7);
      GL20.glAttachShader(var6, var8);
      GL20.glLinkProgram(var6);
      if (GL20.glGetProgrami(var6, 35713) == 0) {
         throw new IllegalStateException("Shader failed to link: " + GL20.glGetProgramInfoLog(var6, 7));
      }

      this.S = var6;
   }

   public void O(String var1, float... var2) {
      int var3 = GL20.glGetUniformLocation(this.S, var1);
      if (var3 != -1) {
         switch (var2.length) {
            case 1:
               GL20.glUniform1f(var3, var2[0]);
               break;
            case 2:
               GL20.glUniform2f(var3, var2[0], var2[1]);
               break;
            case 3:
               GL20.glUniform3f(var3, var2[0], var2[1], var2[2]);
               break;
            case 4:
               GL20.glUniform4f(var3, var2[0], var2[1], var2[2], var2[3]);
         }
      }
   }

   public static void p( float var2, float var3, float var4, float var5) {
      GL11.glBegin(7);
      GL11.glTexCoord2f(0.0F, 0.0F);
      GL11.glVertex2f(var2, var3);
      GL11.glTexCoord2f(0.0F, 1.0F);
      GL11.glVertex2f(var2, var3 + var5);
      GL11.glTexCoord2f(1.0F, 1.0F);
      GL11.glVertex2f(var2 + var4, var3 + var5);
      GL11.glTexCoord2f(1.0F, 0.0F);
      GL11.glVertex2f(var2 + var4, var3);
      GL11.glEnd();
   }

   static {
      a = 13990146093428L;
   }

   public void r() {
      GL20.glUseProgram(this.S);
   }
}
