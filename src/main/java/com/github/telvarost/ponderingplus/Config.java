package com.github.telvarost.ponderingplus;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;

public class Config {

    @ConfigRoot(value = "config", visibleName = "PonderingPlus")
    public static ConfigFields config = new ConfigFields();

    public static class ConfigFields {

        @ConfigEntry(
                name = "Use Book As Seat",
                description = "Book is still needed even when false",
                multiplayerSynced = true
        )
        public Boolean useBookAsSeat = true;
    }
}
