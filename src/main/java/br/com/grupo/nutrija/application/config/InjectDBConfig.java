package br.com.grupo.nutrija.application.config;

import br.com.grupo.nutrija.application.service.InjectDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectDBConfig {

    private final InjectDBService injectDB;

    @Autowired
    public InjectDBConfig(InjectDBService injectDB) {
        this.injectDB = injectDB;
    }

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean injectDbInfos(){
        if(value.equals("create")){
            this.injectDB.injectDB();
        }
        return false;
    }
}
