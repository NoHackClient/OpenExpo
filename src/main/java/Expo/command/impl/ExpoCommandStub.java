package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import java.util.ArrayList;
import java.util.List;











// add code
public final class ExpoCommandStub extends Command {

   private final String[] aliases;
   private final String description;
   private final String usageHeader;
   private final String[] usage;

   private static final String[] NONE = new String[0];

   ExpoCommandStub(String var1, String var2, String[] var3, String... var4) {
      if (var4 == null || var4.length == 0) {
         throw new IllegalArgumentException("ExpoCommandStub: no name");
      }

      this.description = var1;
      this.usageHeader = var2;
      this.usage = var3 == null ? NONE : var3;
      this.aliases = var4;
   }

   @Override
   public boolean J() {
      return false;
   }

   @Override
   public String[] e(long var1) {
      return this.aliases;
   }


   public boolean hasText() {
      return this.description != null || this.usage.length > 0;
   }

   String description() {
      return this.description;
   }

   String aliasLine() {
      StringBuilder var1 = new StringBuilder();

      for (int var2 = 0; var2 < this.aliases.length; var2++) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var1.append(ExpoCommands.PREFIX).append(this.aliases[var2]);
      }

      return var1.toString();
   }

   @Override
   public void h(long var1) {
      ExpoCommands.chat("§7" + this.aliasLine());

      if (this.description != null) {
         ExpoCommands.chat(this.description);
      }

      if (this.usage.length > 0) {
         ExpoCommands.chat("§7" + (this.usageHeader == null ? "Usage:" : this.usageHeader));

         for (int var3 = 0; var3 < this.usage.length; var3++) {
            ExpoCommands.chat("§f" + this.usage[var3]);
         }
      }

      ExpoCommands.chat("§8Text above is the stock client's own. The body is native and "
                        + "was NOT recovered -- typing this command does nothing.");
   }

   @Override
   public void j(String[] var1, long var2) {
      this.h(0L);
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      return new ArrayList();
   }
}
