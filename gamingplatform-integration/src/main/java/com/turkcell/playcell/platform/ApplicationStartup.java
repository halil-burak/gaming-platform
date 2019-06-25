package com.turkcell.playcell.platform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.turkcell.playcell.platform.controller.AccessTokenController;
import com.turkcell.playcell.platform.entity.TurkcellServiceConfig;
import com.turkcell.playcell.platform.repository.TurkcellServiceRepository;
import com.turkcell.playcell.platform.security.JwtProvider;
import com.turkcell.playcell.platform.token.MobileConnectAuthKey;

@Component
public class ApplicationStartup
implements ApplicationListener<ApplicationReadyEvent>	{

        @Autowired
        public TurkcellServiceRepository repo;

        @Override
        public void onApplicationEvent(final ApplicationReadyEvent event) {

            // Read Key Values only once at startup
            Optional<TurkcellServiceConfig> authkey = repo.findKey("AUTH_KEY");
            AccessTokenController.authKeyValue = authkey.get().getKeyValue();

            Optional<TurkcellServiceConfig> mobconkey = repo.findKey("MOB_CON_KEY");
            MobileConnectAuthKey.mobconKey = mobconkey.get().getKeyValue();

            Optional<TurkcellServiceConfig> permTokenkey = repo.findKey("PERM_TOKEN");
            JwtProvider.permTokenKey = permTokenkey.get().getKeyValue();

            Optional<TurkcellServiceConfig> tempTokenkey = repo.findKey("TEMP_TOKEN");
            JwtProvider.tempTokenKey = tempTokenkey.get().getKeyValue();

            return;
        }
}
