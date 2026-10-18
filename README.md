HorizonDeathBan

A server-side Fabric mod for Minecraft that automatically temporarily bans players when they die, using the BanHammer mod.

Developed for the Horizon SMP project.

Features

Automatically detects player deaths.

Executes a BanHammer temporary-ban command when a player dies.

Supports configurable ban durations.

Includes a configuration file that is generated automatically if missing.

Applies to all players, including server administrators, unless otherwise configured.

Requirements

Minecraft Java Edition 26.2

Fabric Loader

BanHammer (banhammer-0.18.0+26.2.jar)

Installation

Install Fabric Loader on your Minecraft server.

Install BanHammer and place its .jar file in the server's mods folder.

Download HorizonDeathBan from the Releases page.

Place the HorizonDeathBan .jar file in the server's mods folder.

Start the server to generate the configuration file.

Configure the ban duration to your preferred settings.

Restart the server if necessary.

Configuration

HorizonDeathBan uses a configuration file to control the temporary-ban duration.

Edit the generated configuration file to change the duration to suit your server.

Ensure the configured duration uses the format expected by the mod.

Dependencies

BanHammer is required to handle temporary bans. HorizonDeathBan relies on BanHammer's command functionality rather than implementing its own ban system.

Important Notes

Players may be banned when they die, including administrators, depending on the server configuration.

Ensure you have a way to unban players, such as the appropriate BanHammer pardon command.

Test the configuration on a test server before deploying it to a live world.

License

See the LICENSE file for the terms under which this project may be used, modified, and redistributed.

Author

Part of the Horizon SMP project.