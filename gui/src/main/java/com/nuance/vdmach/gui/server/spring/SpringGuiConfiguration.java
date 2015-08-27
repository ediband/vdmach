package com.nuance.vdmach.gui.server.spring;

import javax.servlet.ServletException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nuance.vdmach.gui.server.atmosphere.GwtRpcAtmosphereHandler;

import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.cpr.AnnotationProcessor;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereInterceptor;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.BroadcasterLifeCyclePolicy.ATMOSPHERE_RESOURCE_POLICY;
import org.atmosphere.spring.bean.AtmosphereSpringContext;
import org.atmosphere.util.VoidAnnotationProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ediband1
 *         date:   8/20/15 9:49 AM
 */
@Configuration
@ComponentScan(basePackages = {"com.nuance.vdmach.gui"})
public class SpringGuiConfiguration {
    @Bean
    public AtmosphereFramework atmosphereFramework() throws ServletException, InstantiationException, IllegalAccessException {
        AtmosphereFramework atmosphereFramework = new AtmosphereFramework(false, false);
        // atmosphereFramework.setBroadcasterCacheClassName(UUIDBroadcasterCache.class.getName());
        atmosphereFramework.init(atmosphereSpringContext(), false);
        List<AtmosphereInterceptor> interceptors = new ArrayList<>();
//        for (Class<? extends AtmosphereInterceptor> a : atmosphereFramework.defaultInterceptors()) {
//            interceptors.add(a.newInstance());
//        }
        atmosphereFramework.addAtmosphereHandler("/vendingmachine/atmosphere", atmosphereHandler(), interceptors);
        return atmosphereFramework;
    }

    @Bean
    public GwtRpcAtmosphereHandler atmosphereHandler() {
        return new GwtRpcAtmosphereHandler();
    }

    @Bean
    public BroadcasterFactory broadcasterFactory() throws ServletException, InstantiationException, IllegalAccessException {
        return atmosphereFramework().getAtmosphereConfig().getBroadcasterFactory();
    }

    @Bean
    public AtmosphereSpringContext atmosphereSpringContext() {
        AtmosphereSpringContext atmosphereSpringContext = new AtmosphereSpringContext();
        Map<String, String> map = new HashMap<>();
        map.put("org.atmosphere.cpr.broadcasterClass", org.atmosphere.cpr.DefaultBroadcaster.class.getName());
        map.put(AtmosphereInterceptor.class.getName(), TrackMessageSizeInterceptor.class.getName());
        map.put(AnnotationProcessor.class.getName(), VoidAnnotationProcessor.class.getName());
        map.put("org.atmosphere.cpr.broadcasterLifeCyclePolicy", ATMOSPHERE_RESOURCE_POLICY.IDLE_DESTROY.toString());
        atmosphereSpringContext.setConfig(map);
        return atmosphereSpringContext;
    }
}
