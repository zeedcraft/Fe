package io.loyloy.fe.Commands;

import io.loyloy.fe.API.CommandType;
import io.loyloy.fe.API.SubCommand;
import io.loyloy.fe.Fe;
import io.loyloy.fe.Phrases;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;

public class ReloadCommand extends SubCommand
{
    public ReloadCommand( Fe plugin )
    {
        super( plugin, "reload", "fe.reload", "reload", Phrases.COMMAND_RELOAD, CommandType.CONSOLE );
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args )
    {
        final PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.disablePlugin( plugin );
        pluginManager.enablePlugin( plugin );
        Phrases.CONFIG_RELOADED.sendWithPrefix( sender );
        return true;
    }
}
