package org.webforj.views;

import com.webforj.component.Composite;
import com.webforj.component.html.elements.Div;
import com.webforj.component.toast.Toast;
import com.webforj.router.annotation.Route;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Route("/")
public class HomeView extends Composite<Div> {

  private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

  public HomeView() {
    Toast.show("initial toast triggering", Toast.Placement.TOP_LEFT);

    scheduler.schedule(() -> {
      Toast.show("toast triggered in another thread", Toast.Placement.TOP_RIGHT);
    }, 500, TimeUnit.MILLISECONDS);

    final var future = CompletableFuture.runAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });

    future.thenAccept(__ -> {
      Toast.show("toast triggered within an asynchronous block", Toast.Placement.BOTTOM_RIGHT);
    });
  }
}
