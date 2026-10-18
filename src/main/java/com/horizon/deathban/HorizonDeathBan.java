package com.horizon.deathban;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class HorizonDeathBan implements ModInitializer {

    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("horizondeathban.properties");

    private static boolean enabled = true;
    private static String banDuration = "30s";

    @Override
    public void onInitialize() {
        loadConfig();

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (!(entity instanceof ServerPlayer player)) {
                return;
            }

            if (!enabled) {
                return;
            }

            MinecraftServer server = player.level().getServer();

            if (server == null) {
                return;
            }

            String playerName = player.getGameProfile().name();

            String command = "tempban " + playerName
                    + " " + banDuration
                    + " You Died";

            System.out.println("[HorizonDeathBan] Executing: " + command);

            server.getCommands().performPrefixedCommand(
                    server.createCommandSourceStack(),
                    command
            );
        });
    }

    private void loadConfig() {
        Properties properties = new Properties();

        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            if (!Files.exists(CONFIG_PATH)) {
                properties.setProperty("enabled", "true");
                properties.setProperty("ban-duration", "30s");

                try (OutputStream output = Files.newOutputStream(CONFIG_PATH)) {
                    properties.store(output, "HorizonDeathBan configuration");
                }
            }

            try (InputStream input = Files.newInputStream(CONFIG_PATH)) {
                properties.load(input);
            }

            enabled = Boolean.parseBoolean(
                    properties.getProperty("enabled", "true")
            );

            banDuration = properties.getProperty(
                    "ban-duration", "30s"
            ).trim();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load HorizonDeathBan configuration", e
            );
        }
    }
}
