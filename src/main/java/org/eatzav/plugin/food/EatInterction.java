package org.eatzav.plugin.food;

import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.eatzav.plugin.EatZavPlugin;
import org.eatzav.plugin.config.EatConfig;


import javax.annotation.Nonnull;

public class EatInterction extends SimpleInstantInteraction {

    private EatConfig config = EatZavPlugin.CONFIG.get();

    @Override
    protected void firstRun(@Nonnull InteractionType interactionType, @Nonnull InteractionContext context, @Nonnull CooldownHandler cooldownHandler) {
        Ref<EntityStore> entity = context.getEntity();
        EatComponent eatComponent  = entity.getStore().getComponent(entity, EatZavPlugin.get().getEatComponentType());
        eatComponent.addEat(this.config.getFoodValue());

    }


    public static final BuilderCodec<EatInterction> CODEC = BuilderCodec.builder(EatInterction.class, EatInterction::new, SimpleInstantInteraction.CODEC)
            .documentation("Yaps.")
            .appendInherited(
                    new KeyedCodec<>("FoodValue", EatConfig.CODEC),
                    (o, i) -> o.config = i, (o) -> o.config,
                    (o, p) -> o.config = p.config)
            .addValidator(Validators.nonNull())
            .add()
            .build();

}
