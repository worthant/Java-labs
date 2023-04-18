package requestLogic;

import java.net.InetAddress;
import java.util.Objects;

public class CallerBack {
    private final InetAddress address;
    private final int port;

    public CallerBack(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallerBack that = (CallerBack) o;
        return port == that.port && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, port);
    }

    @Override
    public String toString() {
        return "CallerBack{" +
                "address=" + address +
                ", port=" + port +
                '}';
    }
}
