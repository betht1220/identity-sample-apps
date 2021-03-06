package org.cloudfoundry.identity.samples.implicit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class Application {

    public static void main(String[] args) {
        if ("true".equals(System.getenv("SKIP_SSL_VALIDATION"))) {
            SSLValidationDisabler.disableSSLValidation();
        }
        SpringApplication.run(Application.class, args);
    }

    @Value("${ssoServiceUrl:http:localhost:8080/uaa}")
    private String ssoServiceUrl;

    @Value("${security.oauth2.client.clientId:client_id_placeholder}")
    private String clientId;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        request.getSession().invalidate();
        populateModelAttributes(request, model);
        return "index";
    }

    @RequestMapping("/implicit.html")
    public String implicit(HttpServletRequest request, Model model) {
        populateModelAttributes(request, model);
        return "index";
    }

    private void populateModelAttributes(HttpServletRequest request, Model model) {
        model.addAttribute("ssoServiceUrl", ssoServiceUrl);
        model.addAttribute("thisUrl", UrlUtils.buildFullRequestUrl(request));
        model.addAttribute("clientId", clientId);
    }

}
