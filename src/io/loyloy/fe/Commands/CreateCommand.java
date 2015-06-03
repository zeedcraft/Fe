package io.loyloy.fe.Commands;

import io.loyloy.fe.API.Account;
import io.loyloy.fe.API.CommandType;
import io.loyloy.fe.API.SubCommand;
import io.loyloy.fe.Fe;
import io.loyloy.fe.Phrases;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreateCommand extends SubCommand
{
    public CreateCommand( Fe plugin )
    {
        super( plugin, "create", "fe.create", "create [name]", Phrases.COMMAND_CREATE, CommandType.CONSOLE );
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args )
    {
        if( args.length < 1 )
        {
            return false;
        }
        String name = args[0];
        if( plugin.api.accountExists( name ) )
        {
            Phrases.ACCOUNT_EXISTS.sendWithPrefix( sender );
            return true;
        }
        if( name.length() > 16 )
        {
            Phrases.NAME_TOO_LONG.sendWithPrefix( sender );
            return true;
        }
        Account account = plugin.api.createAccount( name );
        Phrases.ACCOUNT_CREATED.sendWithPrefix( sender, Phrases.PRIMARY_COLOR.parse() + account.getName() + Phrases.SECONDARY_COLOR.parse() );
        return true;
    }
}
