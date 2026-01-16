package org.eatzav.plugin.food;

import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class EatHud extends CustomUIHud {


    private final String file;
    private final float eatValue;

    public EatHud(@NonNullDecl PlayerRef playerRef, String file, float eatValue) {
        super(playerRef);
        this.file = file;
        this.eatValue = eatValue;
    }

    protected void build(@NonNullDecl UICommandBuilder uiCommandBuilder) {

        uiCommandBuilder.append("Pages/" + this.file);
        uiCommandBuilder.set("#EatBarProgress.Value", eatValue);

    }
}