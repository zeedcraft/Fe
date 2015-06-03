package io.loyloy.fe.Commands;

import io.loyloy.fe.API.CommandType;
import io.loyloy.fe.API.SubCommand;
import io.loyloy.fe.Fe;
import io.loyloy.fe.Phrases;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DebugCommand extends SubCommand
{
    public DebugCommand( Fe plugin )
    {
        super( plugin, "debug", "fe.debug", "debug", Phrases.COMMAND_DEBUG, CommandType.CONSOLE );
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args )
    {
        final boolean enable = !plugin.settings.debug();
        plugin.settings.debug( enable );
        Phrases.DEBUG_STATUS.sendWithPrefix( sender, enable ? "enabled" : "disabled" );
        return true;
    }
}
