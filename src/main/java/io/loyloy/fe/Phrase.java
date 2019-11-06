package io.loyloy.fe;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public enum Phrase
{
    DATABASE_TYPE_DOES_NOT_EXIST( "Ese tipo de base de datos no existe." ),
    DATABASE_FAILURE_DISABLE( "La inicialización de la base de datos ha fallado, deshabilitando Fe." ),
    COMMAND_NEEDS_ARGUMENTS( "Ese comando necesita argumentos." ),
    COMMAND_NOT_CONSOLE( "El comando '$ 1' no se puede usar en la consola." ),
    NO_PERMISSION_FOR_COMMAND( "Lo sentimos, no tienes permiso para usar ese comando." ),
    ACCOUNT_HAS( "$1 tiene $2" ),
    YOU_HAVE( "Tu tienes $1" ),
    HELP( "Fe ayuda" ),
    HELP_ARGUMENTS( "$1 Necesario, $2 Opcional" ),
    RICH( "Lista de Millonarios" ),
    STARTING_UUID_CONVERSION( "Inicio de conversión de UUID." ),
    UUID_CONVERSION_FAILED( "La conversión de UUID falló, deshabilitando Fe!" ),
    UUID_CONVERSION_SUCCEEDED( "¡La conversión de UUID ha tenido éxito!" ),
    CONFIG_RELOADED( "La configuración ha sido recargada." ),
    NOT_ENOUGH_MONEY( "No tienes suficiente dinero." ),
    ACCOUNT_DOES_NOT_EXIST( "Lo sentimos, esa cuenta no existe." ),
    YOUR_ACCOUNT_DOES_NOT_EXIST( "No tienes una cuenta." ),
    ACCOUNT_EXISTS( "Esa cuenta ya existe." ),
    ACCOUNT_CREATED( "Se ha creado una cuenta por $1." ),
    ACCOUNT_REMOVED( "Se eliminó una cuenta por $1." ),
    MONEY_RECEIVE( "Has recibido $1 de $2." ),
    MONEY_SENT( "Has enviado $1 a $2" ),
    MAX_BALANCE_REACHED( "$1 ha alcanzado el saldo máximo." ),
    PLAYER_SET_MONEY( "Has establecido el saldo de $1 a $2." ),
    PLAYER_GRANT_MONEY( "Has otorgado $1 a $2." ),
    PLAYER_GRANTED_MONEY( "$2 le otorgaron $1." ),
    PLAYER_DEDUCT_MONEY( "Has deducido $1 de $2." ),
    PLAYER_DEDUCTED_MONEY( "$2 deducidos $1 de su cuenta." ),
    ACCOUNT_CREATED_GRANT( "Creó una cuenta por $1 y le otorgó $2" ),
    NO_ACCOUNTS_EXIST( "No existen cuentas." ),
    NAME_TOO_LONG( "Lo siento, ese nombre es demasiado largo." ),
    ACCOUNT_CLEANED( "Se han eliminado todas las cuentas con el saldo predeterminado." ),
    TRY_COMMAND( "Tratar $1" ),
    PRIMARY_COLOR( ChatColor.GOLD.toString() ),
    SECONDARY_COLOR( ChatColor.GRAY.toString() ),
    TERTIARY_COLOR( ChatColor.DARK_GRAY.toString() ),
    ARGUMENT_COLOR( ChatColor.YELLOW.toString() ),
    COMMAND_BALANCE( "Checks your balance", true ),
    COMMAND_SEND( "Sends another player money", true ),
    COMMAND_TOP( "Checks the top 5 richest players", true ),
    COMMAND_HELP( "Gives you help", true ),
    COMMAND_CREATE( "Creates an account", true ),
    COMMAND_REMOVE( "Removes an account", true ),
    COMMAND_SET( "Set a player's balance", true ),
    COMMAND_GRANT( "Grants a player money", true ),
    COMMAND_DEDUCT( "Deducts money from a player", true ),
    COMMAND_CLEAN( "Cleans the accounts with default balance", true ),
    COMMAND_RELOAD( "Reloads the config", true );

    private static Fe plugin;

    private final String defaultMessage;

    private final boolean categorized;

    private String message;

    private Phrase( String defaultMessage )
    {
        this( defaultMessage, false );
    }

    private Phrase( String defaultMessage, boolean categorized )
    {
        this.defaultMessage = defaultMessage;

        this.categorized = categorized;

        message = defaultMessage + "";
    }

    public static void init( Fe instance )
    {
        plugin = instance;
    }

    private String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public void reset()
    {
        message = defaultMessage + "";
    }

    public String getConfigName()
    {
        String name = name();

        if( categorized )
        {
            name = name.replaceFirst( "_", "." );
        }

        return name;
    }

    public String parse( String... params )
    {
        String parsedMessage = getMessage();

        if( params != null )
        {
            for( int i = 0; i < params.length; i++ )
            {
                parsedMessage = parsedMessage.replace( "$" + ( i + 1 ), params[i] );
            }
        }

        return parsedMessage;
    }

    public String parseWithoutSpaces( String... params )
    {
        return parse( params ).replace( " ", "" );
    }

    private String parseWithPrefix( String... params )
    {
        return plugin.getMessagePrefix().replace( "$1", plugin.getConfig().getString( "prefix" ) ) + parse( params );
    }

    public void send( CommandSender sender, String... params )
    {
        sender.sendMessage( parse( params ) );
    }

    public void sendWithPrefix( CommandSender sender, String... params )
    {
        sender.sendMessage( parseWithPrefix( params ) );
    }
}
