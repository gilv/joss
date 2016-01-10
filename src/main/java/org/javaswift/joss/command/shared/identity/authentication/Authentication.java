package org.javaswift.joss.command.shared.identity.authentication;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonRootName(value="auth")
public class Authentication {
    private PasswordCredentials passwordCredentials;
    private String tenantName;
    private String tenantId;
    private Identity identity;

    public Authentication(final String tenantName, String tenantId, final String username, final String password) {
        this.passwordCredentials = new PasswordCredentials(username, password);
        this.tenantName = tenantName;
        this.tenantId = tenantId;
    }

    public Authentication(final String userId, final String password, final String projectId) {
        this.identity = new Identity("password", userId, password, projectId);
    }

    @JsonSerialize(include=Inclusion.NON_NULL)
    public String getTenantId() {
        return this.tenantId;
    }

    @JsonSerialize(include=Inclusion.NON_NULL)
    public String getTenantName() {
        return this.tenantName;
    }

    @JsonSerialize(include=Inclusion.NON_NULL)
    public Identity getIdentity() {
        return this.identity;
    }

    @JsonSerialize(include=Inclusion.NON_NULL)
    public PasswordCredentials getPasswordCredentials() {
        return this.passwordCredentials;
    }
}
