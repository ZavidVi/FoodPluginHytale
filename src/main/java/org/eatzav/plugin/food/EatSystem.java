package org.eatzav.plugin.food;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.movement.MovementStatesComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageCause;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageSystems;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatsModule;
import com.hypixel.hytale.server.core.modules.entitystats.asset.EntityStatType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EatSystem extends EntityTickingSystem<EntityStore> {

    private final ComponentType<EntityStore, EatComponent> eatComponentType;

    public EatSystem(ComponentType<EntityStore, EatComponent> eatComponentType) {
        this.eatComponentType = eatComponentType;
    }

    @Override
    public void tick(float v, int i, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        Player player = store.getComponent(ref, Player.getComponentType());
        MovementStatesComponent movementStatesComponent = store.getComponent(ref, MovementStatesComponent.getComponentType());
        EntityStatMap entityStatMap = store.getComponent(ref, EntityStatsModule.get().getEntityStatMapComponentType());

        EatComponent eatComponent = archetypeChunk.getComponent(i, eatComponentType);
        if(player.getGameMode() != GameMode.Creative){
            if(movementStatesComponent.getMovementStates().sprinting){
                eatComponent.addEatValue(0.05F);
                if (eatComponent.getEatValue() >= 1 && eatComponent.getEat() > 0){
                    eatComponent.decreaseEat(1);
                    eatComponent.removeEatValue();
                }
            }
            if(eatComponent.getEat() > 50 && entityStatMap.get(EntityStatType.getAssetMap().getIndex("Health")).get() != 100){
                eatComponent.decreaseEat(5);
                entityStatMap.addStatValue(EntityStatType.getAssetMap().getIndex("Health"), 1.0f);
            } else if (eatComponent.getEat() <= 0) {
                DamageSystems.executeDamage(ref, commandBuffer, new Damage(Damage.NULL_SOURCE, DamageCause.OUT_OF_WORLD, 0.1f));
            }
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(this.eatComponentType);
    }
}
