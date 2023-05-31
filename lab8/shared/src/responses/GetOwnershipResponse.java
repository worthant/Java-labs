package responses;

import java.util.Map;

public class GetOwnershipResponse extends CommandStatusResponse {
    /**
     * Map (city_id, client_name)
     * Used for coloring city by different colors
     */
    private Map<Long, String> response;

    public GetOwnershipResponse(String oldResponse, int statusCode, Map<Long, String> response) {
        super(oldResponse, statusCode);
        this.response = response;
    }

    public static GetOwnershipResponse of(String s, Map<Long, String> ownershipMap) {
        return new GetOwnershipResponse(s, 0, ownershipMap);
    }

    public Map<Long, String> getOwnershipMap() {
        return response;
    }
}
