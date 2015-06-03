package io.loyloy.fe.Databases;

import io.loyloy.fe.API.Account;
import io.loyloy.fe.Fe;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDB extends DatabaseSQL
{
    public SQLiteDB( Fe plugin )
    {
        super( plugin, false );
    }

    @Override
    public String getName()
    {
        return "SQLite";
    }

    @Override
    public boolean isAsync()
    {
        return false;
    }

    @Override
    public Connection getNewConnection()
    {
        try
        {
            Class.forName( "org.sqlite.JDBC" );
            return DriverManager.getConnection(
                    "jdbc:sqlite:" + new File( plugin.getDataFolder(), "database.db" ).getAbsolutePath() );
        }
        catch( ClassNotFoundException | SQLException e )
        {
            return null;
        }
    }

    @Override
    public void getConfigDefaults( ConfigurationSection section )
    {
    }

    @Override
    protected String getSaveAccountQuery( Account account )
    {
        final String strName = account.getName();
        final String strBlnc = String.valueOf( account.getMoney() );
        if( account.getUUID() != null )
        {
            final String struuid = account.getUUID().toString();
            return "INSERT OR REPLACE INTO `" + tableAccounts + "` (`" + columnAccountsUser + "`, `" + columnAccountsUUID + "`, `" + columnAccountsMoney + "`) "
                    + "VALUES ('" + strName + "', '" + struuid + "', '" + strBlnc + "');";
        }
        return "INSERT OR REPLACE INTO `" + tableAccounts + "` (`" + columnAccountsUser + "`, `" + columnAccountsMoney + "`) "
                + "VALUES ('" + strName + "', '" + strBlnc + "');";
    }
}
