package org.eatzav.plugin.food;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.eatzav.plugin.EatZavPlugin;


import javax.annotation.Nonnull;

public class EatHUDSystem extends EntityTickingSystem<EntityStore> {

    public EatHUDSystem() {

    }


    @Override
    public void tick(float v, int i, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        EatComponent eatComponent = store.getComponent(ref, EatZavPlugin.get().getEatComponentType());
        if(eatComponent == null) return;

        Player player = store.getComponent(ref, Player.getComponentType());
        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
        float b =  (float) eatComponent.getEat() / 100;
        player.getHudManager().setCustomHud(playerRef,  new EatHud(playerRef, "Zav_Eat.ui", b));
    }

    @NullableDecl
    @Override
    public Query<EntityStore> getQuery() {
        return PlayerRef.getComponentType();
    }
}
