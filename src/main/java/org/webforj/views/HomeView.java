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
  private final Toast toast;

  public HomeView() {
    this.toast = new Toast();
    this.toast.setText("initial toast triggering").setPlacement(Toast.Placement.TOP_LEFT).open();

    scheduler.schedule(() -> {
      this.toast.setText("toast triggered in another thread").setPlacement(Toast.Placement.TOP_RIGHT).open();
    }, 500, TimeUnit.MILLISECONDS);

    final var future = CompletableFuture.runAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });

    future.thenAccept(__ -> {
      this.toast.setText("toast triggered within an asynchronous block").setPlacement(Toast.Placement.BOTTOM_RIGHT).open();
    });
  }
}
