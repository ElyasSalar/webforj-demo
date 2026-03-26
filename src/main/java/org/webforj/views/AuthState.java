package org.webforj.views;

import com.webforj.webstorage.CookieStorage;

/**
 * Per-session authentication state for the WebAuthn demo. Uses browser cookies via webforj's
 * {@link CookieStorage}, so state survives page refreshes and persists for 1 hour.
 *
 * <p>In production, use a proper session/security framework.
 */
public final class AuthState {

  private static final String KEY_AUTHENTICATED = "webauthn.demo.authenticated";
  private static final String KEY_USER = "webauthn.demo.user";
  private static final String COOKIE_ATTRS = "Max-Age=3600; SameSite=Strict; Path=/";

  private AuthState() {}

  public static boolean isAuthenticated() {
    return "true".equals(CookieStorage.getCurrent().get(KEY_AUTHENTICATED));
  }

  public static String getAuthenticatedUser() {
    return CookieStorage.getCurrent().get(KEY_USER);
  }

  public static void login(String username) {
    CookieStorage.getCurrent().add(KEY_AUTHENTICATED, "true", COOKIE_ATTRS);
    CookieStorage.getCurrent().add(KEY_USER, username, COOKIE_ATTRS);
  }

  public static void logout() {
    CookieStorage.getCurrent().remove(KEY_AUTHENTICATED);
    CookieStorage.getCurrent().remove(KEY_USER);
  }
}
