package Expo.internal.auth;

import Expo.enums.AccountType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Optional;

public class Account {
   private String S;
   private String O;
   private String l;
   private long U;
   private AccountType G;
   private static long b;
   private String a;

   static {
      b = 81987605227339L;
   }

   public static Account k(JsonObject var0, long var1) {
      return new Account(
         Optional.ofNullable(var0.get("refreshToken")).<String>map(JsonElement::getAsString).orElse(""),
         Optional.ofNullable(var0.get("accessToken")).<String>map(JsonElement::getAsString).orElse(""),
         Optional.ofNullable(var0.get("username")).<String>map(JsonElement::getAsString).orElse(""),
         Optional.ofNullable(var0.get("uuid")).<String>map(JsonElement::getAsString).orElse(""),
         Optional.ofNullable(var0.get("unban")).<Long>map(JsonElement::getAsLong).orElse(0L),
         Optional.ofNullable(var0.get("type")).<String>map(JsonElement::getAsString).map(AccountType::E).orElse(AccountType.MINECRAFT)
      );
   }

   public AccountType v() {
      return this.G;
   }

   public JsonObject F(long var1) {
      JsonObject var3 = new JsonObject();
      var3.addProperty("refreshToken", this.l);
      var3.addProperty("accessToken", this.a);
      var3.addProperty("username", this.S);
      var3.addProperty("uuid", this.O);
      var3.addProperty("unban", this.U);
      var3.addProperty("type", this.G.toString());
      return var3;
   }

   public String h() {
      return this.S;
   }

   public void H(String var1) {
      this.l = var1;
   }

   public String f() {
      return this.O;
   }

   public Account(String var1, String var2, String var3, String var4, long var5, AccountType var7) {
      this.l = var1;
      this.a = var2;
      this.S = var3;
      this.O = var4;
      this.U = var5;
      this.G = var7;
   }

   public void j(String var1) {
      this.O = var1;
   }

   public void J(String var1) {
      this.S = var1;
   }

   public String Y() {
      return this.a;
   }

   public void G(long var1) {
      this.U = var1;
   }

   public String toString() {
      return "Account{refreshToken='"
         + this.l
         + 39
         + ", accessToken='"
         + this.a
         + 39
         + ", username='"
         + this.S
         + 39
         + ", uuid='"
         + this.O
         + 39
         + ", unban="
         + this.U
         + ", type="
         + this.G
         + 125;
   }

   public String d() {
      return this.l;
   }

   public Account(String var1, String var2, String var3, String var4) {
      this(var1, var2, var3, var4, 0L, AccountType.MINECRAFT);
   }

   public Account(String var1, String var2, String var3) {
      this("", var2, var1, var3, 0L, AccountType.MINECRAFT);
   }

   public long F() {
      return this.U;
   }

   public void g(AccountType var1) {
      this.G = var1;
   }

   public Account(String var1, String var2, String var3, String var4, long var5) {
      this(var1, var2, var3, var4, var5, AccountType.MINECRAFT);
   }

   public void r(String var1) {
      this.a = var1;
   }
}
