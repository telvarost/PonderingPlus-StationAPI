package com.github.telvarost.ponderingplus;

import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ModHelper {

    //public static final Namespace NAMESPACE = Namespace.resolve();
    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    public static final int STORY_BOOK_SIZE = 10;

    public static class ModHelperFields {
    }
}
