package pl.coderslab.sports_betting.Controller.ErrorController;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "redirect:/403";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirect:/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "redirect:/500";
            }
        }
        return "error";

                }

    @GetMapping("/403")
    public String error403(){
        return "errorPages/403";
    }

    @GetMapping("/404")
    public String error404(){
        return "errorPages/404";
    }

    @GetMapping("/500")
    public String error500(){
        return "errorPages/500";
    }

    @Override
    public String getErrorPath() {
            return "/error";
        }
}
