package Expo.util;

// add code
/**
 * Rethrows a checked exception without declaring it.
 *
 * ZKM emitted a private copy of this into every class that needed one -- 26
 * identical helpers, each called zkm$sneaky. The try/catch around each use has to
 * stay: the guarded body throws a checked exception and the catch(Throwable) is
 * what lets it compile, so removing the wrapper only produces "unreported
 * exception java.lang.Throwable". The duplication is what can go.
 */
public final class Sneaky {

   private Sneaky() {
   }

   @SuppressWarnings("unchecked")
   public static <T extends Throwable> RuntimeException rethrow(Throwable t) throws T {
      throw (T)t;
   }
}
