package org.sog.ui.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("net.artelis.wita.ui")
public class WitaUIProperties {

    private String pmsLinkProjekt;
    private String pmsLinkKunde;

    private String ldapUrl;
    private String ldapManagerDn;
    private String ldapManagerPassword;
    private String ldapRoleHierarchy;

    private String logoutUrl;
    private String currentUrl;

}
