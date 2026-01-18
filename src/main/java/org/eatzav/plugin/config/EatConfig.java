package org.eatzav.plugin.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class EatConfig {


    public static final BuilderCodec<EatConfig> CODEC = BuilderCodec.builder(EatConfig.class, EatConfig::new)
            .append(new KeyedCodec<Integer>("FoodValue", Codec.INTEGER),
                    (exConfig, aInt, extraInfo) -> exConfig.FoodValue = aInt, // Setter
                    (exConfig, extraInfo) -> exConfig.FoodValue)                    // Getter
            .add()
            .build();



    // 2. Configuration variable with default value
    private int FoodValue = 2;

    public EatConfig() {

    }


    public int getFoodValue() {
        return FoodValue;
    }
}
