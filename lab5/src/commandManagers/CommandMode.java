package commandManagers;

/**
 * Contains information about mode of commands behavior
 *
 * @author boris
 * @since 2.0
 */
public enum CommandMode {
    /**
     * Commands will use user-friendly interaction. Envisage that it will be used with System.in scanner
     */
    CLI_UserMode,
    /**
     * Commands will use simple interaction w/o user-orientation. Envisage that it will be used with file streams, e.t.c.
     */
    NonUserMode
}