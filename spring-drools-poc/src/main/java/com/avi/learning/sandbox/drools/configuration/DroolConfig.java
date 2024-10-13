package com.avi.learning.sandbox.drools.configuration;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
@Configuration
public class DroolConfig {

    private static final Logger log = LoggerFactory.getLogger(DroolConfig.class);
    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieContainer getKieContainer() throws IOException {
        log.info("Container created ....");
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession getKieSession() throws IOException {
        return getKieContainer().newKieSession();
    }

    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/offer.drl"));
        return kieFileSystem;
    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

//    public Order getDiscountForOrderV2(Order order) throws FileNotFoundException{
//        List<Rule> ruleAttributes = new ArrayList<>();
//        rulesRepo.findAll().forEach(ruleAttributes::add);
//
//        ObjectDataCompiler compiler = new ObjectDataCompiler();
//        String generatedDRL = compiler.compile(ruleAttributes, Thread.currentThread().getContextClassLoader().getResourceAsStream(DroolConfig.RULES_TEMPLATE_FILE));
//        KieServices kieServices = KieServices.Factory.get();
//
//        KieHelper kieHelper = new KieHelper();
//
//        //multiple such resoures/rules can be added
//        byte[] b1 = generatedDRL.getBytes();
//        Resource resource1 = kieServices.getResources().newByteArrayResource(b1);
//        kieHelper.addResource(resource1, ResourceType.DRL);
//
//        KieBase kieBase = kieHelper.build();
//
//        KieSession kieSession = kieBase.newKieSession();
//        kieSession.insert(order);
//        int numberOfRulesFired = kieSession.fireAllRules();
//        kieSession.dispose();
//
//        return order;
//    }
}
