package Expo.event.events;

import Expo.event.Event;
import java.util.Arrays;
import java.util.List;











public class ModuleTagEvent extends Event {
   private static final long a = 71874928198191L;
   private StringBuilder K;


   public void U(String var1) {
      this.K.append(var1).append("\n");
   }

   public void y(String var1) {
      this.K.append(var1);
   }

   public List<String> i() {
      return Arrays.asList(this.K.toString().split("\n"));
   }

   public ModuleTagEvent() {
      super();
      this.K = new StringBuilder();
   }}
