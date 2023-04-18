package serverLogic;

import java.io.IOException;

public class UdpConnectionBlockDecorator extends UdpServerConnection {
    private final UdpServerConnection baseConnection;
    private final boolean configureBlock;

    public UdpConnectionBlockDecorator(UdpServerConnection baseConnection, boolean configureBlock) throws IOException {
        super(baseConnection.channel, baseConnection.address);
        this.baseConnection = baseConnection;
        this.configureBlock = configureBlock;
        baseConnection.channel.configureBlocking(configureBlock);
    }

    public boolean getLockState() {
        return configureBlock;
    }
}
