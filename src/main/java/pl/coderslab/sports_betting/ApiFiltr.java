//package pl.coderslab.sports_betting;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//import pl.coderslab.sports_betting.Service.ApiService;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/api/**")
//public class ApiFiltr implements Filter {
//
//    @Autowired
//    ApiService apiService;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        //forces injection of autowired service
//        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
//
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse res = (HttpServletResponse) servletResponse;
//
//        String key = req.getParameter("apiKey");
//        boolean check = this.apiService.checkKey(key);
//
//        if (check = false) {
//            res.sendError(400, "Please check you APIkey");
//        } else {
//            res.sendRedirect(req.getRequestURI());
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//}