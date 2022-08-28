package com.kamileq_14.voidworld.particle;

import com.kamileq_14.voidworld.VoidWorld;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, VoidWorld.MOD_ID);

    public static final RegistryObject<SimpleParticleType> VOID_PORTAL_PARTICLES =
            PARTICLE_TYPES.register("void_portal_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}