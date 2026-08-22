package Expo.command.impl;

import Expo.command.Command;
import Expo.command.ExpoCommands;
import Expo.internal.restore.ExpoCommandData;
import Expo.module.impl.configuration.Teams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public final class ExpoCommandNames extends Command {
   public static final int ENEMY = 0;
   public static final int FRIEND = 1;

   private final int kind;
   private final String desc;
   private final String[] usage;
   private final String[] aliases;

   public ExpoCommandNames(int var1, String var2, String[] var3, String... var4) {
      this.kind = var1;
      this.desc = var2;
      this.usage = var3;
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

   @Override
   public void h(long var1) {
      ExpoCommands.chat(this.desc);
      ExpoCommands.chat("§7Usage:");

      for (int var3 = 0; var3 < this.usage.length; var3++) {
         ExpoCommands.chat("§f" + this.usage[var3]);
      }

      ExpoCommands.chat("§8Stored in §7" + this.file() + "§8 next to current.json; "
                        + this.set().size() + " name(s) now.");
   }

   @Override
   public void j(String[] var1, long var2) {
      String var4 = var1[0];

      if ("list".equalsIgnoreCase(var4)) {
         this.list();
      } else if ("clear".equalsIgnoreCase(var4)) {
         this.clear();
      } else if ("add".equalsIgnoreCase(var4)) {
         if (var1.length < 2) {
            ExpoCommands.chat("§cUsage: §f." + this.aliases[0] + " add <name...>");
         } else {
            this.add(var1);
         }
      } else if ("remove".equalsIgnoreCase(var4)) {
         if (var1.length < 2) {
            ExpoCommands.chat("§cUsage: §f." + this.aliases[0] + " remove <name...>");
         } else {
            this.remove(var1);
         }
      } else {
         this.h(0L);
      }
   }

   @Override
   public List g(String[] var1, int var2, long var3) {
      List var5 = new ArrayList();

      if (var2 <= 1) {
         var5.addAll(Arrays.asList("add", "remove", "list", "clear"));
      } else if (var2 == 2 && var1.length > 0 && "remove".equalsIgnoreCase(var1[0])) {
         var5.addAll(this.set());
      }

      return var5;
   }

   private Set<String> set() {
      return this.kind == ENEMY ? Teams.B() : Teams.a();
   }

   private Set<String> other() {
      return this.kind == ENEMY ? Teams.a() : Teams.B();
   }

   private String file() {
      return this.kind == ENEMY ? ExpoCommandData.ENEMIES : ExpoCommandData.FRIENDS;
   }

   private boolean save() {
      return this.kind == ENEMY ? ExpoCommandData.saveEnemies() : ExpoCommandData.saveFriends();
   }

   private void add(String[] var1) {
      int var2 = 0;

      for (int var3 = 1; var3 < var1.length; var3++) {
         String var4 = var1[var3].trim();

         if (var4.isEmpty()) {
            continue;
         }

         if (this.other().contains(var4)) {
            ExpoCommands.chat("§c" + var4 + " is already on the other list; the two are "
                              + "mutually exclusive.");
            continue;
         }

         if (this.set().contains(var4)) {
            ExpoCommands.chat("§7" + var4 + " was already there.");
            continue;
         }

         if (this.kind == ENEMY) {
            Teams.C(var4);
         } else {
            Teams.E(var4);
         }

         if (this.set().contains(var4)) {
            ExpoCommands.chat("§a+ §f" + var4);
            var2++;
         } else {
            ExpoCommands.chat("§c" + var4 + " was refused by the product's own writer.");
         }
      }

      this.report(var2);
   }

   private void remove(String[] var1) {
      int var2 = 0;

      for (int var3 = 1; var3 < var1.length; var3++) {
         String var4 = this.match(var1[var3]);

         if (var4 == null) {
            ExpoCommands.chat("§c" + var1[var3] + " is not on the list.");
         } else if (this.set().remove(var4)) {
            ExpoCommands.chat("§c- §f" + var4);
            var2++;
         }
      }

      this.report(var2);
   }

   private String match(String var1) {
      if (this.set().contains(var1)) {
         return var1;
      }

      for (String var3 : this.set()) {
         if (var3 != null && var3.equalsIgnoreCase(var1)) {
            return var3;
         }
      }

      return null;
   }

   private void list() {
      Set<String> var1 = this.set();

      if (var1.isEmpty()) {
         ExpoCommands.chat("§7The list is empty.");
         return;
      }

      StringBuilder var2 = new StringBuilder();

      for (String var4 : var1) {
         if (var2.length() > 0) {
            var2.append("§7, §f");
         }

         var2.append(var4);
      }

      ExpoCommands.chat("§7" + var1.size() + ": §f" + var2);
   }

   private void clear() {
      int var1 = this.set().size();

      if (this.kind == ENEMY) {
         Teams.W();
      } else {
         Teams.r$r1();
      }

      this.report(var1);
   }

   private void report(int var1) {
      if (var1 == 0) {
         ExpoCommands.chat("§7Nothing changed.");
         return;
      }

      boolean var2 = this.save();
      ExpoCommands.chat("§7" + var1 + " change(s); " + this.set().size() + " name(s) now"
                        + (var2 ? ", saved to §f" + this.file()
                                : " §cbut the write to " + this.file() + " FAILED"));
   }
}
