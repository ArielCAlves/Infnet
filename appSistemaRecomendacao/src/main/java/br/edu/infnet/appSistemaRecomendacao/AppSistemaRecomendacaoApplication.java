package br.edu.infnet.appSistemaRecomendacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AppSistemaRecomendacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSistemaRecomendacaoApplication.class, args);
	}

}
