package com.cyber.crm.config;

import com.cyber.crm.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    // Khai báo thông tin cấu hình cho filter
    @Bean
    public FilterRegistrationBean<AutoLoginFilter> autoLoginFilterConfig() {
        FilterRegistrationBean<AutoLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AutoLoginFilter());
        registrationBean.addUrlPatterns("/login");
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean FilterRegistrationBean<AuthenFilter> authenFilterConfig() {
        FilterRegistrationBean<AuthenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenFilter());
        registrationBean.addUrlPatterns("/dashboard");
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.addUrlPatterns("/task/*");
        registrationBean.addUrlPatterns("/job/*");
        registrationBean.addUrlPatterns("/role/*");
        registrationBean.setOrder(2);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminAndManagerFilter> adminAndManagerFilterConfig() {
        FilterRegistrationBean<AdminAndManagerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminAndManagerFilter());
        registrationBean.addUrlPatterns("/user");
        registrationBean.addUrlPatterns("/user/add/*");
        registrationBean.addUrlPatterns("/user/update/*");
        registrationBean.addUrlPatterns("/user/delete/*");
        registrationBean.addUrlPatterns("/job/*");
        registrationBean.setOrder(3);

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilterConfig() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/role/*");
        registrationBean.setOrder(4);

        return registrationBean;
    }


    /**
     * 1) Nếu như đăng nhập rồi thì không cần đăng nhập và chuyển về trang chủ
     *  B1: Khi đăng nhập thành công thì phải lưu lại thông tin user đã đăng nhập (Session/Cookie/Database)
     *  B2: Khi người dùng vào lại trang login thì kiểm tra xem Session/Cookie lưu trữ thông tin có tồn tại không.
     *  B3: Nếu tồn tại, chuyển hướng qua trang chủ
     *      Nếu không tồn tại thì vào trang login
     *
     * 2) Hãy làm tính năng phân quyền cho hệ thống CRM
     * - ADMIN : thêm, xóa, sửa role
     * - NHANVIEN : Xem được thông tin nhân viên.
     */

}
