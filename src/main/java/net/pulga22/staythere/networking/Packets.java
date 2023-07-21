package net.pulga22.staythere.networking;

import net.minecraft.util.Identifier;

public class Packets {

    public static final Identifier CHANGE_STATE = new Identifier("staythere", "change_state");
    public static final Identifier REQUEST_SYNC_C2S = new Identifier("staythere", "request_sync_cts");
    public static final Identifier SYNC_S2C = new Identifier("staythere", "sync_stc");

}
