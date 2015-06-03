package io.loyloy.fe.Commands;

import io.loyloy.fe.API.CommandType;
import io.loyloy.fe.API.SubCommand;
import io.loyloy.fe.Fe;
import io.loyloy.fe.FeCommands;
import io.loyloy.fe.Phrases;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand
{
    private final FeCommands command;

    public HelpCommand( Fe plugin, FeCommands command )
    {
        super( plugin, "help,?", "fe.?", "help", Phrases.COMMAND_HELP, CommandType.CONSOLE );
        this.command = command;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args )
    {
        sender.sendMessage( plugin.getEqualMessage( Phrases.HELP.parse(), 10 ) );
        String operatorColor = Phrases.TERTIARY_COLOR.parse();
        String textColor = Phrases.SECONDARY_COLOR.parse();
        sender.sendMessage( textColor + Phrases.HELP_ARGUMENTS.parse( operatorColor + "[]" + textColor, operatorColor + "()" + textColor ) );
        for( SubCommand subcmd : this.command.getCommands() )
        {
            if( subcmd.getName().equalsIgnoreCase( getName() ) )
            {
                continue;
            }
            if( !sender.hasPermission( subcmd.getPermission() ) )
            {
                continue;
            }
            if( !( sender instanceof Player ) && subcmd.getCommandType() == CommandType.PLAYER )
            {
                continue;
            }
            sender.sendMessage( this.command.parse( commandLabel, subcmd ) + textColor + " - " + subcmd.getDescription().parse() );
        }
        sender.sendMessage( plugin.getEndEqualMessage( 27 ) );
        return true;
    }
}
