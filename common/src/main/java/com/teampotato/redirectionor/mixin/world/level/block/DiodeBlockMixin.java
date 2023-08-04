package com.teampotato.redirectionor.mixin.world.level.block;

import com.teampotato.redirectionor.references.DirectionReferences;
import com.teampotato.redirectionor.references.TickPriorityReferences;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.ticks.TickPriority;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DiodeBlock.class)
public abstract class DiodeBlockMixin {
    @Redirect(method = "neighborChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Direction;values()[Lnet/minecraft/core/Direction;"))
    private Direction[] redirectDirectionValues() {
        return DirectionReferences.DIRECTIONS;
    }

    @Redirect(method = "checkTickOnNeighbor", at = @At(value = "FIELD", target = "Lnet/minecraft/world/ticks/TickPriority;HIGH:Lnet/minecraft/world/ticks/TickPriority;"))
    private TickPriority redirectTickPriorityHIGH() {
        return TickPriorityReferences.HIGH;
    }

    @Redirect(method = "checkTickOnNeighbor", at = @At(value = "FIELD", target = "Lnet/minecraft/world/ticks/TickPriority;EXTREMELY_HIGH:Lnet/minecraft/world/ticks/TickPriority;"))
    private TickPriority redirectTickPriorityEXTREMELY_HIGH() {
        return TickPriorityReferences.EXTREMELY_HIGH;
    }

    @Redirect(method = {"tick", "checkTickOnNeighbor"}, at = @At(value = "FIELD", target = "Lnet/minecraft/world/ticks/TickPriority;VERY_HIGH:Lnet/minecraft/world/ticks/TickPriority;"))
    private TickPriority redirectTickPriorityVERY_HIGH() {
        return TickPriorityReferences.VERY_HIGH;
    }
}
