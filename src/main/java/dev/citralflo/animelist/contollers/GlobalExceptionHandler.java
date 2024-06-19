package dev.citralflo.animelist.contollers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception ex, Model model) {
        // Get error status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = status != null ? Integer.valueOf(status.toString()) : HttpStatus.INTERNAL_SERVER_ERROR.value();

        // Default error details
        String errorCode = "Error " + statusCode;
        String errorMessage = "An unexpected error occurred.";

        // Customize message for specific status codes
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            errorCode = "404 Not Found";
            errorMessage = "The resource you are looking for could not be found.";
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            errorCode = "500 Internal Server Error";
            errorMessage = "There was an internal server error.";
        }

        // Add error details to the model
        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("exception", ex.getMessage());

        // Return the error page template
        return "error";
    }
}
