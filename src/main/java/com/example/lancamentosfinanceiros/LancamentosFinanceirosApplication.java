package com.example.lancamentosfinanceiros;

import com.example.lancamentosfinanceiros.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class LancamentosFinanceirosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LancamentosFinanceirosApplication.class, args);
	}

}
