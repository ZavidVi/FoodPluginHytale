package org.eatzav.plugin;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.console.ConsoleSender;
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import org.eatzav.plugin.config.EatConfig;
import org.eatzav.plugin.event.PlayerFoodEvent;
import org.eatzav.plugin.food.EatComponent;
import org.eatzav.plugin.food.EatHUDSystem;
import org.eatzav.plugin.food.EatInterction;
import org.eatzav.plugin.food.EatSystem;


import javax.annotation.Nonnull;

/**
 * This class serves as the entrypoint for your plugin. Use the setup method to register into game registries or add
 * event listeners.
 */
public class EatZavPlugin extends JavaPlugin {

    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    public static Config<EatConfig> CONFIG;
    private ComponentType<EntityStore, EatComponent> eatComponent;
    private static EatZavPlugin instance;

    public EatZavPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        CONFIG = this.withConfig("EatZavPlugin", EatConfig.CODEC);
        instance = this;
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.getName());
        this.getCodecRegistry(Interaction.CODEC).register("EatInterction", EatInterction.class, EatInterction.CODEC);
        this.eatComponent = this.getEntityStoreRegistry().registerComponent(EatComponent.class, "EatComponent", EatComponent.CODEC);
        this.getEventRegistry().register(PlayerConnectEvent.class, PlayerFoodEvent::addComponentFoodPlayer);
        this.getEntityStoreRegistry().registerSystem(new EatSystem(this.eatComponent));
        this.getEntityStoreRegistry().registerSystem(new EatHUDSystem());
    }

    @Override
    protected void start() {

    }

    public ComponentType<EntityStore, EatComponent> getEatComponentType() {
        return eatComponent;
    }


    public static EatZavPlugin get() {
        return instance;
    }
}