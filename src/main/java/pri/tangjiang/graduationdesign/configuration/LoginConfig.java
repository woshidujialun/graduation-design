package pri.tangjiang.graduationdesign.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pri.tangjiang.graduationdesign.configuration.interceptor.LoginInterceptor;

@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration chain = registry.addInterceptor(new LoginInterceptor());
        chain.addPathPatterns("/**");           // 所有路径都被拦截
        chain.excludePathPatterns("/tj/login",  // 登录不拦截
                "/**/*.html",                   // html静态资源
                "/**/*.js",                     // js静态资源
                "/**/*.css",                    // css静态资源
                "/**/*.woff",
                "/**/*.ttf");
        chain.order(1);
    }

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 拦截所有的请求
                .allowedOriginPatterns("*")  // 可跨域的域名，可以为 *
                .allowCredentials(true)
                .allowedMethods("*")   // 允许跨域的方法，可以单独配置
                .allowedHeaders("*");  // 允许跨域的请求头，可以单独配置
    }*/
}
