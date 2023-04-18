package requestLogic.requestWorkers;

import commandLogic.CommandDescription;
import commandManager.CommandExporter;
import requestLogic.requests.ServerRequest;
import responseLogic.responseSenders.CommandConfigureResponseSender;
import responses.CommandDescriptionsResponse;

import java.util.ArrayList;

public class CommandConfigureRequestWorker implements RequestWorker {
    @Override
    public void workWithRequest(ServerRequest request) {
        ArrayList<CommandDescription> commands = CommandExporter.getCommandsToExport();
        CommandDescriptionsResponse response = new CommandDescriptionsResponse(commands);
        CommandConfigureResponseSender.sendResponse(response, request.getConnection(), request.getFrom());
    }
}
