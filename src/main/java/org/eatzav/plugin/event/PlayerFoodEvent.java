package org.eatzav.plugin.event;

import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import org.eatzav.plugin.EatZavPlugin;
import org.eatzav.plugin.food.EatComponent;

public class PlayerFoodEvent {

    public static void addComponentFoodPlayer(PlayerConnectEvent event) {
        if(event.getHolder().getComponent(EatZavPlugin.get().getEatComponentType()) == null){
            event.getHolder().addComponent(EatZavPlugin.get().getEatComponentType(), new EatComponent(0, 100));
        }
    }
}
