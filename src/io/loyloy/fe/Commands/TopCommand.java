package io.loyloy.fe.Commands;

import io.loyloy.fe.API.Account;
import io.loyloy.fe.API.CommandType;
import io.loyloy.fe.API.SubCommand;
import io.loyloy.fe.Fe;
import io.loyloy.fe.Phrases;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TopCommand extends SubCommand
{
    public TopCommand( Fe plugin )
    {
        super( plugin, "top", "fe.top", "top", Phrases.COMMAND_TOP, CommandType.CONSOLE );
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String commandLabel, String[] args )
    {
        int topSize = plugin.settings.getShowTop();
        try
        {
            if( args.length > 0 && args[0] != null && !"".equals( args[0] ) )
            {
                int enteredTop = Integer.parseInt( args[0] );
                if( enteredTop > 0 )
                {
                    topSize = enteredTop;
                }
            }
        }
        catch( NumberFormatException e )
        {
        }
        List<Account> topAccounts = plugin.api.getTopAccounts( topSize );
        if( topAccounts.size() < 1 )
        {
            Phrases.NO_ACCOUNTS_EXIST.sendWithPrefix( sender );
            return true;
        }
        sender.sendMessage( plugin.getEqualMessage( Phrases.RICH.parse(), 10 ) );
        for( int i = 0; i < topAccounts.size(); i++ )
        {
            Account account = topAccounts.get( i );
            String two = Phrases.SECONDARY_COLOR.parse();
            sender.sendMessage( two + ( i + 1 ) + ". " + Phrases.PRIMARY_COLOR.parse() + account.getName() + two + " - " + plugin.api.format( account ) );
        }
        sender.sendMessage( plugin.getEndEqualMessage( 28 ) );
        return true;
    }
}
