package web.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

//полностью соответствует xml конфигурационному файлу

@Configuration // определяет Beanы и их зависимости, альтернатива xml
@EnableWebMvc//для поддержания web функций
@ComponentScan("web") // пакет где лежит контроллер аналогична mvc:annotation-driven из xml
public class WebConfig implements WebMvcConfigurer {
//интерфейс для реализации собственной настройки Spring MVC
// вместо стандартного шаблонизатора используем таймлиф
    //ниже бины отвечающие за конфигурацию таймлиф
    private final ApplicationContext applicationContext;

    //зарегистрировать эту конфигурацию в контексте Spring
    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    //спринг автоматом внедряет ApplicationContext, который реализует IoC (инверсию управления) управляет бинами,
    //зависимостями, жизненым циклом бинов(компонентов). Расширяет интерфейс BeanFactory


    @Bean
    public SpringResourceTemplateResolver templateResolver() {//реализация applicationContext
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/pages/");//задаем папку где лежат наши представления для нахождения по имени
        templateResolver.setSuffix(".html");//задаем расширение представлений
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {//реализация наших представлений
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {//здесь задаем шаблонизатор тайм лиф передаем спрингу
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}