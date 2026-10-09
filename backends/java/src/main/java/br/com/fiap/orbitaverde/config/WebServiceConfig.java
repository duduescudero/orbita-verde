package br.com.fiap.orbitaverde.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Configuracao do Web Service SOAP.
 *
 * - Registra o servlet SOAP em /ws/*
 * - Gera automaticamente o WSDL a partir do XSD
 * - WSDL acessivel em: http://localhost:8085/ws/satelite.wsdl
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * Registra o MessageDispatcherServlet para processar requisicoes SOAP.
     * Mapeado para /ws/*
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Gera automaticamente o WSDL a partir do XSD.
     * Disponivel em: http://localhost:8085/ws/satelite.wsdl
     */
    @Bean(name = "satelite")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema sateliteSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SatelitePort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://fiap.com.br/orbitaverde/soap");
        wsdl11Definition.setSchema(sateliteSchema);
        return wsdl11Definition;
    }

    /**
     * Carrega o schema XSD do satelite.
     */
    @Bean
    public XsdSchema sateliteSchema() {
        return new SimpleXsdSchema(new ClassPathResource("wsdl/satelite.xsd"));
    }
}
