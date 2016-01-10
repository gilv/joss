package org.javaswift.joss.command.shared.identity.access;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EndPoint {

    public String adminURL;

    public String region;

    public String internalURL;

    public String publicURL;

    public String id;

    public String region_id;

    public String url;

    @JsonProperty("interface")
    public String accessInterface;
}
