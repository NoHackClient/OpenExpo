package Expo.module.impl.configuration;

import Expo.module.Category;

import Expo.module.Module;
import Expo.setting.settings.BooleanSetting;
import Expo.setting.settings.NumberSetting;
import Expo.setting.settings.PercentageSetting;
import Expo.util.MathUtil;
import Expo.util.render.CustomFont;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;


public class ScoreBoard extends Module {
   private static Map h;
   public static BooleanSetting textShadow;
   public static NumberSetting offsetX;
   public static BooleanSetting hideScoreboard;
   public static PercentageSetting backgroundOpacity;
   private static long[] d;
   public static NumberSetting scale;
   public static NumberSetting offsetY;
   public static BooleanSetting roundedRectangle;
   private static long a;
   public static BooleanSetting disableScores;
   private static String b;

   static {
      a = 136735959965016L;
      b = ": ";
      h = new HashMap(13);
      d = new long[]{3694946831705732335L, -1892344913047605889L, -6692344859849378204L, -712116554178381252L};
   }

   public ScoreBoard(long var1) {
      super(((a ^ (var1)) ^ 26128142835819L));
      // add code
      this.declare("ScoreBoard", Category.Configuration, "Manage vanilla scoreboard rendering");
      var1 = a ^ var1;
   }

   public static void n(ScoreObjective var0, ScaledResolution var3) {





      if (!hideScoreboard.c()) {
         double var16 = scale.L();
         float var18 = offsetX.L();
         float var19 = offsetY.L();
         boolean var20 = disableScores.c();
         boolean var21 = roundedRectangle.c();
         boolean var22 = textShadow.c();
         Scoreboard var23 = var0.getScoreboard();
         Collection<Score> var24 = var23.getSortedScores(var0);
         List var25 = Lists.newArrayList(Iterables.filter(var24, var0x -> var0x.getPlayerName() != null && !var0x.getPlayerName().startsWith("#")));
         if (var25.size() > 15) {
            var25 = var25.subList(var25.size() - 15, var25.size());
         }

         CustomFont var26 = Font.J();
         GlStateManager.pushMatrix();
         GlStateManager.scale(var16, var16, 1.0);
         int var27 = (int)(var3.getScaledWidth() / var16);
         int var28 = (int)(var3.getScaledHeight() / var16);
         int var29 = (int)Math.ceil(var26.R(var0.getDisplayName(), 52019766876817L));

         for (Score var31 : (Iterable<Score>)(var25)) {
            ScorePlayerTeam var32 = var23.getPlayersTeam(var31.getPlayerName());
            String var33 = ScorePlayerTeam.formatPlayerName(var32, var31.getPlayerName());
            if (!var20) {
               var33 = var33 + b + EnumChatFormatting.RED + var31.getScorePoints();
            }

            var29 = Math.max(var29, (int)Math.ceil(var26.R(var33, 52019766876817L)));
         }

         int var49 = Math.max(1, (int)Math.ceil(var26.o(60714858652844L)));
         int var50 = var25.size() * var49;
         int var51 = var28 / 2 + var50 / 3 + (int)var19;
         byte var52 = 3;
         int var34 = var27 - var29 - var52 + (int)var18;
         int var35 = var27 - var52 + 2 + (int)var18;
         int var36 = (int)(2.55 * backgroundOpacity.k());
         int var37 = new Color(0, 0, 0, var36).getRGB();
         int var38 = new Color(0, 0, 0, (int)MathUtil.R(var36 * 1.5, 0.0, 255.0)).getRGB();
         if (var21) {
            int var39 = var51 - var50;
            int var40 = var39 - var49 - 1;
            Expo.util.render.RenderUtil.j(var34 - 2, var40, var35, var51, 6.0F, 4113131265056L, var37);
            int var41 = 0;

            for (Score var43 : (Iterable<Score>)(var25)) {
               var41++;
               ScorePlayerTeam var44 = var23.getPlayersTeam(var43.getPlayerName());
               String var45 = ScorePlayerTeam.formatPlayerName(var44, var43.getPlayerName());
               String var46 = EnumChatFormatting.RED + "" + var43.getScorePoints();
               int var47 = var51 - var41 * var49;
               var26.v(var45, var34, var47, 16777215, 88827598794260L, var22);
               if (!var20) {
                  var26.v(var46, var35 - var26.R(var46, 52019766876817L), var47, 16777215, 88827598794260L, var22);
               }
            }

            String var56 = var0.getDisplayName();
            var26.v(var56, var34 + var29 / 2.0F - var26.R(var56, 52019766876817L) / 2.0F, var39 - var49, 16777215, 88827598794260L, var22);
         } else {
            int var53 = 0;

            for (Score var55 : (Iterable<Score>)(var25)) {
               var53++;
               ScorePlayerTeam var57 = var23.getPlayersTeam(var55.getPlayerName());
               String var58 = ScorePlayerTeam.formatPlayerName(var57, var55.getPlayerName());
               String var59 = EnumChatFormatting.RED + "" + var55.getScorePoints();
               int var60 = var51 - var53 * var49;
               Expo.util.render.RenderUtil.c(125644905353792L, var34 - 2, var60, var35, var60 + var49, var37);
               var26.v(var58, var34, var60, 16777215, 88827598794260L, var22);
               if (!var20) {
                  var26.v(var59, var35 - var26.R(var59, 52019766876817L), var60, 16777215, 88827598794260L, var22);
               }

               if (var53 == var25.size()) {
                  String var61 = var0.getDisplayName();
                  Expo.util.render.RenderUtil.c(125644905353792L, var34 - 2, var60 - var49 - 1, var35, var60 - 1, var38);
                  Expo.util.render.RenderUtil.c(125644905353792L, var34 - 2, var60 - 1, var35, var60, var37);
                  var26.v(var61, var34 + var29 / 2.0F - var26.R(var61, 52019766876817L) / 2.0F, var60 - var49, 16777215, 88827598794260L, var22);
               }
            }
         }

         GlStateManager.popMatrix();
      }
   }



   static {
      // add code
      backgroundOpacity = new PercentageSetting("Background-opacity", 30);
   }
   static {
      // add code
      hideScoreboard = new BooleanSetting("Hide-scoreboard", false);
      disableScores = new BooleanSetting("Disable-scores", true);
      textShadow = new BooleanSetting("Text-shadow", false);
      roundedRectangle = new BooleanSetting("Rounded-rectangle", false);
   }
   static {
      // add code
      scale = new NumberSetting("Scale", 1.0F, 0.0F, 3.0F, 0.01F);
      offsetX = new NumberSetting("Offset-X", 0.0F, -1000.0F, 1000.0F, 1.0F);
      offsetY = new NumberSetting("Offset-Y", 0.0F, -1000.0F, 1000.0F, 1.0F);
   }
}
