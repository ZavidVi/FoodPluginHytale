package org.eatzav.plugin.food;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.validation.Validators;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nullable;

public class EatComponent implements Component<EntityStore> {


    private float eatValue;
    private int eat;

    public EatComponent(float eatValue, int eat) {
        this.eatValue = eatValue;
        this.eat = eat;
    }

    public EatComponent(EatComponent eatComponent) {
        this.eatValue = eatComponent.eatValue;
        this.eat = eatComponent.eat;
    }

    public EatComponent() {
        this(0, 100);
    }

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new EatComponent(this);
    }

    public int getEat() {
        return eat;
    }

    public float getEatValue(){
        return eatValue;
    }

    public void addEat(int eat){
        this.eat += eat;
    }

    public void decreaseEat(int eat){
        this.eat -= eat;
    }

    public void addEatValue(float eatValue){
        this.eatValue += eatValue;
    }

    public void removeEatValue(){
        this.eatValue = 0.0F;
    }

    public static final BuilderCodec<EatComponent> CODEC = BuilderCodec.builder(EatComponent.class, EatComponent::new)
            .documentation("Yaps.")
            .appendInherited(
                    new KeyedCodec<>("EatValue", Codec.INTEGER),
                    (o, i) -> o.eat = i, // set
                    (o) -> o.eat, //get
                    (o, p) -> o.eat = p.eat)
            .addValidator(Validators.nonNull())
            .add()
            .build();
}
