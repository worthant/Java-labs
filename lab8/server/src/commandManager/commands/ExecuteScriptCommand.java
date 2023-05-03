package commandManager.commands;

import responses.CommandStatusResponse;

public class ExecuteScriptCommand implements BaseCommand {
    private CommandStatusResponse response;
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescr() {
        return "Reads and executes script from file.";
    }

    @Override
    public String getArgs() {
        return "file_path";
    }

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        response = CommandStatusResponse.ofString("Server is alive and ready for command executing!");
    }

    @Override
    public CommandStatusResponse getResponse() {
        return response;
    }
}
