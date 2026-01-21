package com.ids.consulta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // <- indicamos que es una app spring
@EnableFeignClients // <- importante, habilita clientes Feign (requisito para ms2) *
public class ConsultaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultaServiceApplication.class, args);
    }
}
