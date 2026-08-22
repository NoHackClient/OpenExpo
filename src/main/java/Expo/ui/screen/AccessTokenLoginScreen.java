package Expo.ui.screen;

import Expo.internal.auth.Account;
import Expo.internal.auth.AltManager;
import Expo.internal.auth.AuthService;
import Expo.internal.auth.TimedStatusMessage;
import Expo.ui.GuiTextWidget;
import Expo.util.ChatFormatting;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.Session;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;


public class AccessTokenLoginScreen extends GuiScreen {
   private static long j;
   private static String[] c;
   private static Pattern R;
   private static Map h;
   private ExecutorService T;
   private static Pattern i;
   private static Pattern X;
   private static Pattern Z;
   private GuiButton C;
   private static Pattern V;
   private static String[] b;
   private String D;
   private final GuiScreen W;
   private static long[] e;
   private GuiButton E;
   private GuiTextWidget K;
   private static long a;
   private CompletableFuture<Void> f;
   private static Map d;

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      if (this.f != null && !this.f.isDone()) {
         this.f.cancel(true);
         if (this.T != null && !this.T.isShutdown()) {
            this.T.shutdownNow();
         }
      }
   }

   private void W(List<CompletableFuture<Void>> var1, List<String> var2, List<String> var3) {
      this.f = CompletableFuture.allOf(var1.toArray(new CompletableFuture[0]))
         .thenRunAsync(
            () -> {

               AltManager.O(101554584226764L);
               this.mc
                  .addScheduledTask(
                     () -> {
                        long var5x = 106134966044692L;
                        String var10 = !var3.isEmpty() && var2.isEmpty()
                           ? String.format("§aSuccessfully logged in %d account(s)!§r", var3.size())
                           : (
                              var3.isEmpty() && !var2.isEmpty()
                                 ? String.format("§cFailed to log in %d account(s).§r", var2.size())
                                 : String.format("§aLogged in %d, §cfailed %d account(s).§r", var3.size(), var2.size())
                           );
                        this.mc.displayGuiScreen(new AccountManagerScreen(var5x, this.W, new TimedStatusMessage(ChatFormatting.y(var10), j)));
                        if (!var2.isEmpty()) {
                           var2.forEach(System.err::println);
                        }
                     }
                  );
            },
            this.T
         )
         .exceptionally(var1x -> {
            this.mc.addScheduledTask(() -> {
               this.D = "§cAn unexpected error occurred during batch processing.§r";
               this.E.enabled = true;
            });
            return null;
         });
   }

   static {
      a = 110395747778664L;
      i = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
      Z = Pattern.compile(
      "(?:(?:.*?[:|\\s])?(?:Accesstoken|accesstoken|Mctoken|mctoken):([a-zA-Z0-9\\-_\\.]+))|([a-zA-Z0-9\\-_\\.]+)(?:\\s*\\|McName:([a-zA-Z0-9_]+))?(?:\\s*\\|([a-zA-Z0-9_]+))?(?:\\s*\\|([0-9a-fA-F-]{36}))?"
      );
      X = Pattern.compile(
      "(?:.*?)?(?:Accesstoken|accesstoken|Mctoken|mctoken):([a-zA-Z0-9\\-_\\.]+)(?:\\s*\\|McName:([a-zA-Z0-9_]+))?(?:\\s*\\|([a-zA-Z0-9_]+))?(?:\\s*\\|([0-9a-fA-F-]{36}))?|([a-zA-Z0-9\\-_\\.]+)\\|([a-zA-Z0-9_]+)\\|?([0-9a-fA-F-]{36})?",
      32
      );
      R = Pattern.compile("eyJ[a-zA-Z0-9_-]*\\.eyJ[a-zA-Z0-9_-]*\\.[a-zA-Z0-9_-]*");
      V = Pattern.compile("[a-zA-Z0-9\\-_]{20,}(?:\\.[a-zA-Z0-9\\-_]+){2,}|[a-zA-Z0-9\\-_]{100,}");
      d = new HashMap(13);
      b = new String[]{"\u000c\u008d\u00eb\rZt)\u00fb:*\u00ab\u001a\u00f0u0\u00e9", "\u009f[m\u0084\u00dc\u00f9(\u00a0", "\u00be~\u0088\u009c\u0014\u00c3\u00ea\u00af", "\u00d7U\u00f2\u00adM\u00a3\u00a2\u00adNm}\u00f4\u00fd\u00b2W\u00a2Ha1\u00131\u00bbm\u00fd\u001f\u00a4nQ<\u0088\u00eb\u00a7", "9\u00ef-W\u0005\u00a4\u0087\u008c\u00e1}\u008f\u00e8\u00a8wB\u0087ES\u00fb\u00fd\u00ce\u007f+\u00e9", "\u00ab\u00df,Y\u0015N\u00fbY\u00e6\u00da\u00b1\u00a2\u00cf#\u0081f\u00d9,D\u00f1\u001d7V\u00d1}1\u00f2\u00ea\u00a4\u00fe\u0007\u00dc\u0084m\u00a8\u00c2\u00ca\u00b2\u009ea\u0093L\u00d2\u00bfD\u00de\u00d8\u0003", "\u0015C\u009d\u00ac+u\u00a7F", "\u00b0\t\u00fd\u00a2\u008f\u00fbm6", "\u00cf,\u00fe\u0081\u0089\u00ee4\u008d", "R>\u00117WP\u0088|", "5\u007f\u00ba\u00d9j\u0011\u00a6\u00b3#\u00f9\u0005\u00d0\u00a3X*\u00fc", "\u008c\u00e1\u0002\u0083\u009cT:\u00ef", "\u000eA\u00cc\u0008\u000e\u0094f\u001f\u001e*\\\u00f4\u00e5\u00847I\u00b3,y\u00f1\u0086\u00fd\u00b2\tn\u0084\u00ab\u009c\u00e3\u0096\u00b4\u00ec\u0084<\u00f3\u00db\u00c9\u00ab\u00ae+\u00f1\\a\u00b3\u008bR\u0001\u0082\u0095\u00b6\u0014\t\u00b6(\u0011\u0014\u00e9\u00de\u00ad\u009c\t[f!\u00ac\u0094\u00c3\u0091\u00be%W\u0017", "~\u008d\u0013\u0005! \u001b\u00a5\u008d{\u00e2\u00a6\u00e5\u0001\u0093$\u00b9\u0091\u00be\u00edM\u00c2\u00d3LR/A'\u0004J\u00b6\u00e0kIJ\u000em\u009b\u001d\n\u001e\u00c7#]+\u00cf\u00ab\u001am\u0014\u00de\u00da\u00e0\u00f3\u00a8|", "B\u0001\u000c\u00fe\u00b6\u00cf\"\u00c0>f\u0084\r{p\u00e2\u00cd", "\u0017\u00c9\u00cd\u00be\u0015\u00e1\u00ecHwc,\u00f78\u0018\"\u00ce\u00cdm\u0094\u00e3\u00ac\u00c2\u008aI \u00ab\u00f8-SJ\u00fd{\u001b\u00e1\u0007\u00c9\u00d9\u00fcr\u008fU\u00ea\u0015S\u00db\u00c78\u00bf\nJ;\u00be\u001b\u00b4\u00b2\u0016\u00d9\u0087<J\u001d\u00a0e\u00c2\u0014\u00bf\u00ee\u0014\u00bb\u00c9\u00dck@\u00f0\u009b\u00ccg\u0000\u00f1Z\u000c\u0019\u00e6\u00ae\u00a5\u00ef?&4\u00db\u00967\u0097%.\u00ee\u00b5\u0095\u00d1\u00f9\u009ev\u00cb\u00d5\u008dNX\u00e2\u0086\r\u00ea|\u00c1E\u00cc|\u00a6\u0090~\u00cbQ\u00cd\u0013\t\u00a6p\u00e8\u0018\u0094\u00a9]\u00e35\u00b6\u009c\u00dd8\u00c8u\u00a4\u00de\u00f2\u0007\u0080\u000e\u00deef#\u0081\u00e8q\u001d\u0080\u00c9\u00f7YDCy\u00ea\u00a8\u00ac\u00c0\u001f\u00edcW\u00behk\u00a82\u00ec\u00b3\u00fe\u00d8[\\\u00ee\u00cf\u00a8*\u0014\u00f47L\u00f4\u00de%\u001bn", "\u00ef9\u00c9\u00a9\u00cc\u00a2\u0080\u0097\u0097s]\u00fdX\u0092XE,\u00ba\u00ee\\\u00bc\u00cb1\u0002\u000c\u00a4\u00b8\u00db\u0004Y\u0006\u00c7\u00eb\u00aa`\u00cb\u0018:\u00aa\u00f8\u00e7.\u00ae\u0093e\u00a0\u00a5\u001f", "\u00d2\u00e6\u000eA\u00c3t\u00a3F\u0005\u00f2\u00d1f\u0089\u00a5I\u00b0", "\u00f0\u00c4z\u00f2_P\u00b8\u00cc\u008c\u0083\u00ec\u009a\u00d5\u00d5i\u00da?F\u00ffP\u0010\u00038\u00a8\u00beU\u00bbh\u00ea\u00bf\u00ea`1J\u0004\u00f9\u00ab\u00f8\u00a1\u00f5\t\u00ca}\u00b3(\u001f\u00cd\u0091", "\u00e6p\u0084\u00a02 \u0098\u00edN\u00a1\u00f3.]\u0008U\u00cd<\u0085k\u008c#k\u00b6\u0016\u0005y\u00a9\u00e9\u00c8\u00a2\u00fc\u0007\u00830\u0092@\u0085i\u000ft.\u00c2\u00c0i\u0014\u00ac\u00f52\u00ae\u0087iT\u007f\u00cb\u00b3i\u0012\u00b3Df\u0018\u00f3\u00b8\u0003", "U\u00ab(\u00c0\u008a\u0081\u001c\u000e", "n\u00a8\u000b\u00c6\u00ed\u00de\u0019-3\u00cb\u0088\u00e0]\u008b\u00d2I", "\u00c9\u0016\u00a0d\u00c7\u00fc\u00ba{", "}\u00fcU\u00ee\u00f6\u00a9\u0088lY1M\u00ebLi6%\u00c1O4W\u000b=\u00de\u0001\u00ef)\u00b7y\u00e9\u0083\u00c6\u00a4", "0$\u0014\u0087\u0092\u00ba\u00cda;\u00df\u00b0\u00ae\u0008\u000bi\u009dw\u0017|\u00fcC\u000b\u00aa\u00e1\u0094\u00f5W`\u0090\u00c9o\u00df", "\u00a5\u0081\u000c\u0019\u0016\u0012\u00d6\u00a0_\u000c\t\u00aa\u00ff\u0097\u00bd\u00d2\u00c9\u00a5N\u00f6L\u00cc\u0099q\u00000\u000b\u0016u\u00ec*\u0003B\u00db\u00c1\u00f1\u0097b\u00e5H\u00f2\u00fbY\u00ea\u000b\u0098\u0081\u00f8", "\u0089lB\u0006\u0097\u000b\u00cb\u00c0", "k\u00c2\u0011L\u0008X\u009fW\u007f\u00b0\u00fc\u0008\u009e_\u00b0\u000e", "\u00bb\u001b\u00bc\u0096g\u0013t\u00a4vts\u00d9Q1n\u007f", "~k\u00b6\u0085\u00b9\u0089_\u00e4}6\u00b9]\u00e0M\u0013x\u00a9?$\u00ea\u00eab\u001e\u00cen\u00e2\u0099\u00fbj\u00c3&\u0008\u00fefZ\u0096('\u00f4\u0081\u00bb2\u00feU7\u00e2)\u00d2\u000b\u009d+\tD<\u0083\u00a5\u00f5\u0011.\u00d6\u0098\u008aP^L\u0002\u0019\u00afq\u00f3s\u0087@\u00d5\u0092\u00aft3N ", "\u009e\u00a8*\u009b\u00e1p\u00831", "\u00c9\u00fb\u0008\u00d8\u00d3l\r\u00dc\u00ab\u00fe`\u008dC<h9", "\u0015\u00f7F\u00c8\u00fa\u0080\u00d3\u00fd\u0005\u00f9\u0080RE\u0088\u00a1\u009fL\u00ed\u00e2\u0004\u0002\u00ed\u009ekl\u00f1\r\u00ee[\u0084\u008d!", "\u001f\u0081\u0011EBC\u0096\u00d7\u00eb\u0089\u00a0\u00bd\u0095\u0093}?", "<\u000b\u00d3?\u001ch\u00b0DN5\u00b3s\u0083/\u00de\u00c5\u00b6\u0004 e7\u00c2\u00e1\u00a4\u00d8\u00ce\u00c3\u00f9\u00b6LQY", "m\u00ab)\u00c0a\u00c33\u009e\u001aJ\u00a5}\u00be\u001bt\u0004\u0003]O\u00cf\u00d0\u00ed\u00b2\u008a\u00f0=\u00ad=\u00c5\u009fXJ\u00fcg\u0098{\u008d\u0019\"^\u00a1K\u00ba9\u00bd/\u00bcW", "q\u0080\u008e\u00d5\u0013^S\u00dc", "\u0084\u00b2\u00dd\u00d1\u0088{\u001c$\"\r/\u00e8\u00c9\u00d2\u0002\u00d4W\u0014\u00880\u009f\u00c7`\u00c9", "\u00ae+4r'd\u00cb\r\u001e\u001a\u00e8y\u00da{%<\u00b3zn\u001dN\u00fa\u009a^\u00a1\u00eb=\u0087\u009d\u0093\u001cZz2*\u00b7\u00fb\u00a3F\u00c8", "\u00aap\n6\u0096\u0017D\u008e\u00b7of0\u00efS\u00ad\u001bF\u00a1DH\u00b0\u00a6\u00d4\u00aa\u0088\u00c8\u009aM\u0080\u00d9\u00a3\u0013v\u0000\u00aa\u0013F.\u0007\u0018G\u0004&\u00de\u00fc?l\u00f3z.=\u00ef\u00c2\u00e5\u00c7\u000b\u00a13!>p\u0089:\u00d7\u0004-\u00f9UO\u00cf\u00ebu&\u00df^\u008b\u00e8+\r7\u007f\u00c2\u00dcg\u0082\u00b4\u0096\\\u00f8T\u0092\u00e6\u00ef\u00f1\u00b4\u0011j\u00c2\u009c\u008e\u00bd\u00f55\u00be\u00fd*\u00135\u00f4\u00ceMp\r\u00b4/E\u00f4\u008d\\\u00bb]%\u00b2J^\u0096\u00f8\u0088\u0011N\u00a7z\u008dc\u0012\u00a4\u00fe\u009a\u00ce\u008feC\u00d6\u00a2\u00f2\u00b0\u0001\u0099}B\u00c9$\u00f98\u00dc\u00a9\u0094'\u00f0\u00feH\u0093\u0011\u008by\u0082\u00d2\u00a9\u00a03B\u0096]\u00f9u\u00fa\u00eb\u00d0b\u00175\u0015\u0093@[\u00bd9;^, /\u00fa\u00c1\u00e5\\\u00f5\u00e0\u00f0\u000bHd\u00e90\u00dc\u0081\u00ab\u00fa\u00f3\u0093\u009e\u0093mL\u00ce."};
      c = new String[40];
      h = new HashMap(13);
      e = new long[]{-6179178860364192126L, -4115508186449270523L, -8344684637995672757L, -3102215640195162597L, 3437161096464223226L, 4087559869772570548L, -6742336212447138074L, -2954776816750629725L, 8086583128158658561L, 620766257088394773L, -7612866976290492898L, 8239844019441514443L, -2341318659144379299L, -1168087349002653694L, 148759182871054105L, -1412249561140564675L, -514634316045331755L, 2284500278605410940L, 3447497351858921391L, 1928326603251773153L};
      j = 5000L;
   }


   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.E = new GuiButton(
         0,
         this.width / 2 - 100,
         this.height / 2 + 30,
         200,
         20,
         "Login Account(s)"
      );
      this.buttonList.add(this.E);
      this.C = new GuiButton(
         1,
         this.width / 2 - 100,
         this.height / 2 + 55,
         200,
         20,
         "Cancel"
      );
      this.buttonList.add(this.C);
      this.K = new GuiTextWidget(
         2,
         this.fontRendererObj,
         this.width / 2 - 100,
         this.height / 2 - 60,
         200,
         80
      );
      this.K.V(50000);
      this.K.r(true);
   }

   public void updateScreen() {
      this.K.o();
   }

   public AccessTokenLoginScreen(GuiScreen var1, long var2) {
      this.D = "\u00a77Enter your Minecraft Access Token(s)\u00a7r";
      this.W = var1;
   }

   private void q(String var1, List var2,List var5, List var6) {
      if (!(var1 = var1.trim()).isEmpty() && var1.length() >= 20) {
         String var7 = var1;
         CompletableFuture var8 = AuthService.i(var7, this.T).thenAcceptAsync(var2x -> {
            String var3x = var2x.getUsername();
            String var4 = var2x.getPlayerID();
            Optional var5x = AltManager.Q.stream().filter(var1xx -> var1xx.Y().equals(var7)).findFirst();
            if (var5x.isPresent()) {
               Account var6x = (Account)var5x.get();
               var6x.J(var3x);
               var6x.j(var4);
            } else {
               AltManager.Q.add(new Account(var3x, var7, var4));
            }

            var6.add(var3x);
         }, this.T).exceptionally(var2x -> {
            long var3x = a ^ 14084689864637L;
            String var5x = "Login failed!";
            if (var2x != null) {
               Throwable var6x = var2x.getCause();
               var5x = var6x != null ? var6x.getMessage() : var2x.getMessage();
            }

            String var7x = var7.length() > 30 ? var7.substring(0, 30) + "..." : var7;
            var5.add("§cFailed (" + var5x + ") for token: " + var7x + "§r");
            return null;
         });
         var2.add(var8);
      }
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.K.D(var1, var2, var3);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(
         this.fontRendererObj,
         "§fLogin with Access Token(s)",
         this.width / 2,
         this.height / 2 - 90,
         16777215
      );
      this.drawCenteredString(
         this.fontRendererObj,
         this.D,
         this.width / 2,
         this.height / 2 - 75,
         11184810
      );
      this.K.h();
      super.drawScreen(var1, var2, var3);
   }

   private void N(String var1,List var4, List var5, List var6) {
      if (!var1.isEmpty()) {
         String var7 = null;
         String var8 = null;
         String var9 = null;
         Matcher var10 = R.matcher(var1);
         if (var10.find()) {
            var7 = var10.group();
         }

         if (var7 == null) {
            Matcher var11 = Z.matcher(var1);

            while (var11.find()) {
               if (var11.group(1) != null) {
                  var7 = var11.group(1);
               }

               if (var7 == null && var11.group(2) != null) {
                  var7 = var11.group(2);
               }

               if (var11.group(3) != null) {
                  var8 = var11.group(3);
               } else if (var11.group(4) != null) {
                  var8 = var11.group(4);
               }

               String var12;
               if (var11.group(5) != null && (var12 = var11.group(5)) != null && i.matcher(var12).matches()) {
                  var9 = var12;
               }

               if (var7 == null) {
               }
            }
         }

         if (var7 != null && var7.length() < 20 && !var7.contains(".")) {
            var7 = null;
         }

         if (var7 != null && !var7.isEmpty()) {
            String var17 = var7;
            String var18 = var8;
            String var13 = var9;
            CompletableFuture<Session> var14 = !StringUtils.isBlank(var18) && !StringUtils.isBlank(var13) ? AuthService.y(var17, var18, var13, this.T) : AuthService.i(var17, this.T);
            CompletableFuture var15 = var14.thenAcceptAsync(var2x -> {
               String var3 = var2x.getUsername();
               String var4x = var2x.getPlayerID();
               Optional var5x = AltManager.Q.stream().filter(var1xx -> var1xx.Y().equals(var17)).findFirst();
               if (var5x.isPresent()) {
                  Account var6x = (Account)var5x.get();
                  var6x.J(var3);
                  var6x.j(var4x);
               } else {
                  AltManager.Q.add(new Account(var3, var17, var4x));
               }

               var6.add(var3);
            }, this.T).exceptionally(var3 -> {
               long var4x = a ^ 100364979922129L;
               String var6x = "Login failed!";
               if (var3 != null) {
                  Throwable var7x = var3.getCause();
                  var6x = var7x != null ? var7x.getMessage() : var3.getMessage();
               }

               var5.add("§cFailed (" + var6x + ") for: " + (var18 != null ? var18 : "Unknown Username/Invalid Token") + "§r");
               return null;
            });
            var4.add(var15);
         } else {
            var5.add(
               "§cInvalid format or missing token for: "
                  + (var1.length() > 50 ? var1.substring(0, 50) + "..." : var1)
                  + "§r"
            );
         }
      }
   }

   protected void actionPerformed(GuiButton var1) {
      if (var1.enabled) {
         switch (var1.id) {
            case 0:
               String var6 = this.K.z().trim();
               if (!var6.isEmpty()) {
                  this.r( var6);
               } else {
                  this.D = "§cPlease enter at least one account.§r";
               }
               break;
            case 1:
               this.mc.displayGuiScreen(this.W);
         }
      }
   }



   protected void keyTyped(char var1, int var2) {

      if (var2 == 1) {
         this.actionPerformed(this.C);
      } else {
         this.K.W(131295994842818L, var1, var2);
         if (var2 == 28 && isCtrlKeyDown() && !this.K.z().trim().isEmpty()) {
            this.actionPerformed(this.E);
         }
      }
   }

   private void r( String var3) {
      if (this.T == null || this.T.isShutdown()) {
         this.T = Executors.newFixedThreadPool(5);
      }

      this.D = "§7Processing accounts...§r";
      this.E.enabled = false;
      ArrayList var8 = new ArrayList();
      ArrayList var9 = new ArrayList();
      ArrayList var10 = new ArrayList();
      ArrayList var11 = new ArrayList();
      Matcher var12 = R.matcher(var3);

      while (var12.find()) {
         String var13 = var12.group();
         if (!var11.contains(var13)) {
            var11.add(var13);
            this.q(var13, var8,var9, var10);
         }
      }

      if (!var11.isEmpty()) {
         this.W(var8, var9, var10);
      } else {
         ArrayList var29 = new ArrayList();
         Matcher var14 = X.matcher(var3);

         while (var14.find()) {
            if (var14.group(1) != null || var14.group(5) != null) {
               var29.add(var14.group(0));
            }
         }

         String[] var15 = var3.split("[\\r\\n]+");

         for (String var19 : var15) {
            if (!(var19 = var19.trim()).isEmpty()) {
               Matcher var21 = R.matcher(var19);

               while (var21.find()) {
                  String var22 = var21.group();
                  if (!var11.contains(var22)) {
                     var11.add(var22);
                     this.q(var22, var8,var9, var10);
                  }
               }

               if (var21.find()) {
                  var21.reset();
               } else {
                  boolean var20 = var19.contains("McName:")
                     || var19.contains("Accesstoken:")
                     || var19.contains("accesstoken:")
                     || var19.contains("Mctoken:")
                     || var19.contains("mctoken:");
                  if (var20) {
                     this.N(var19,var8, var9, var10);
                  } else if (var19.contains("|")) {
                     for (String var27 : var19.split("\\|")) {
                        if (!(var27 = var27.trim()).isEmpty()) {
                           if (R.matcher(var27).matches()) {
                              if (!var11.contains(var27)) {
                                 var11.add(var27);
                                 this.q(var27, var8,var9, var10);
                              }
                           } else if (V.matcher(var27).matches() && !var11.contains(var27)) {
                              var11.add(var27);
                              this.q(var27, var8,var9, var10);
                           }
                        }
                     }
                  } else if (V.matcher(var19).matches() && !var11.contains(var19)) {
                     var11.add(var19);
                     this.q(var19, var8,var9, var10);
                  }
               }
            }
         }

         for (String var32 : (Iterable<String>)(var29)) {
            this.N(var32.trim(),var8, var9, var10);
         }

         if (var8.isEmpty() && !var3.trim().isEmpty()) {
            String var31 = var3.trim().replaceAll("[\\r\\n]+", "");
            if (V.matcher(var31).matches()) {
               this.q(var31, var8,var9, var10);
            } else {
               this.N(var3.trim(),var8, var9, var10);
            }
         }

         this.W(var8, var9, var10);
      }
   }

}
