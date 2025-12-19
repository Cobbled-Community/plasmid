package xyz.nucleoid.plasmid.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundFromEntityS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class PlayerUtil {
    private PlayerUtil() {}

    public static void playSoundToPlayer(PlayerEntity player, SoundEvent soundEvent, SoundCategory category, float volume, float pitch) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.networkHandler.sendPacket(new PlaySoundFromEntityS2CPacket(Registries.SOUND_EVENT.getEntry(soundEvent), category, player, volume, pitch, player.getRandom().nextLong()));
        }
    }
}
