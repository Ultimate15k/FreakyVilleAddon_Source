package com.gmail.vacrosdk.modules.Prison.vagt;

import com.gmail.vacrosdk.FreakyvilleAddon;
import com.gmail.vacrosdk.utils.Utils;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;

public class VagtLeaveListener {

  private final FreakyvilleAddon addon;

  public VagtLeaveListener(FreakyvilleAddon addon) {
    this.addon = addon;
  }

  @SuppressWarnings("unused")
  @Subscribe
  public void onGameTick(ServerDisconnectEvent event) {
    if (!addon.isOnlineOnFreakyville()) {
      return;
    }
    if(moduleIsDisabled()) {
      return;
    }
    doCheck();
  }

  private void doCheck() {
    String username = addon.labyAPI().getName();
    if(addon.IsPlayerOnGuard().equals(true)) {
      addon.SetIsPlayerOnGuard(false);
      Utils.createNotification("BetterTimers", "Modul er slået til!", Icon.head(username));
    }
  }

  private boolean moduleIsDisabled() {
    return addon.IsPlayerOnGuard().equals(false) || addon.configuration().enabled().get().equals(false)|| addon.configuration().getPrisonEnabled().get().equals(false);
  }
}
