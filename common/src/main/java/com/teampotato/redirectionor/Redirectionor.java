package com.teampotato.redirectionor;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.FrameType;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction8;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.horse.Markings;
import net.minecraft.world.entity.animal.horse.Variant;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.TickPriority;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.chunk.storage.IOWorker;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

public class Redirectionor {
    public static final ChatFormatting[] CHAT_FORMATTINGS = ChatFormatting.values();
    public static final FrameType[] FRAME_TYPES = FrameType.values();
    public static final InteractionHand[] INTERACTION_HANDS = InteractionHand.values();
    public static final SoundSource[] SOUND_SOURCES = SoundSource.values();
    public static final PlayerModelPart[] PLAYER_MODEL_PARTS = PlayerModelPart.values();
    public static final Heightmap.Types[] HEIGHTMAP_TYPES = Heightmap.Types.values();
    public static final MobCategory[] MOB_CATEGORIES = MobCategory.values();
    public static final Difficulty[] DIFFICULTIES = Difficulty.values();
    public static final JigsawBlockEntity.JointType[] JIGSAW_BLOCK_ENTITY_JOINT_TYPES = JigsawBlockEntity.JointType.values();
    public static final Direction[] DIRECTIONS = Direction.values();
    public static final DyeColor[] DYE_COLORS = DyeColor.values();
    public static final GameType[] GAME_TYPES = GameType.values();
    public static final ClientboundPlayerPositionPacket.RelativeArgument[] CLIENTBOUND_PLAYER_POSITION_PACKET_RELATIVE_ARGUMENTS = ClientboundPlayerPositionPacket.RelativeArgument.values();
    public static final ObjectiveCriteria.RenderType[] OBJECTIVE_CRITERIA_RENDER_TYPES = ObjectiveCriteria.RenderType.values();
    public static final EquipmentSlot[] EQUIPMENT_SLOTS = EquipmentSlot.values();
    public static final BossEvent.BossBarColor[] BOSS_EVENT_BOSS_BAR_COLORS = BossEvent.BossBarColor.values();
    public static final BossEvent.BossBarOverlay[] BOSS_EVENT_BOSS_BAR_OVERLAYS = BossEvent.BossBarOverlay.values();
    public static final Panda.Gene[] PANDA_GENES = Panda.Gene.values();
    public static final Variant[] VARIANTS = Variant.values();
    public static final Markings[] MARKINGS = Markings.values();
    public static final SpellcasterIllager.IllagerSpell[] ILLAGER_SPELLS = SpellcasterIllager.IllagerSpell.values();
    public static final AbstractArrow.Pickup[] PICKUPS = AbstractArrow.Pickup.values();
    public static final Rotation[] ROTATIONS = Rotation.values();
    public static final Direction.Axis[] AXES = Direction.Axis.values();
    public static final SupportType[] SUPPORT_TYPES = SupportType.values();
    public static final Direction8[] DIRECTION_8_S = Direction8.values();
    public static final GenerationStep.Carving[] GENERATION_STEP_CARVINGS = GenerationStep.Carving.values();
    public static final IOWorker.Priority[] PRIORITIES = IOWorker.Priority.values();
    public static final Half[] HALVES = Half.values();
    public static final BlockPathTypes[] BLOCK_PATH_TYPES = BlockPathTypes.values();
    public static final MapDecoration.Type[] MAP_DECORARION_TYPE = MapDecoration.Type.values();
    public static final LootContext.EntityTarget[] LOOT_CONTEXT_ENTITY_TARGETS = LootContext.EntityTarget.values();
    public static final CopyNameFunction.NameSource[] COPY_NAME_FUNCTION_NAME_SOURCES = CopyNameFunction.NameSource.values();
    public static final CopyNbtFunction.MergeStrategy[] COPY_NAME_FUNCTION_MERGE_STRATEGIES = CopyNbtFunction.MergeStrategy.values();
    public static final TickPriority[] TICK_PRIORITIES = TickPriority.values();
}
