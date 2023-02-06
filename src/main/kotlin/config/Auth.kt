package config

import jmccc.microsoft.entity.MicrosoftSession
import org.to2mbn.jmccc.auth.OfflineAuthenticator

data class Auth(
    val online: List<MicrosoftSession>,
    val offline: List<OfflineAuthenticator>
)