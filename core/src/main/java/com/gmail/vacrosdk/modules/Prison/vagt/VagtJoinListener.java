package com.gmail.vacrosdk.modules.Prison.vagt;

import com.gmail.vacrosdk.FreakyvilleAddon;
import com.gmail.vacrosdk.utils.Utils;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerJoinEvent;

public class VagtJoinListener {

  private final FreakyvilleAddon addon;

  public VagtJoinListener(FreakyvilleAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(ServerJoinEvent event) {
    if (!addon.isOnlineOnFreakyville()) {
      return;
    }
    if (moduleIsDisabled()) {
      return;
    }
    doCheck();
  }

  private void doCheck() {
    String username = addon.labyAPI().getName();
    String vagtUsername = addon.configuration().getVagtName().get();
    if (username.equals(vagtUsername)) {
      addon.configuration().getVagtSwitch().set(true);
      Utils.createNotification("BetterTimers", "Modul er slået fra!", Icon.head(vagtUsername));
    }
  }

  private boolean moduleIsDisabled() {
    return addon.configuration().getAutomaticSwitch().get().equals(false) || addon.configuration().enabled().get().equals(false)|| addon.configuration().getPrisonEnabled().get().equals(false);
  }
}
