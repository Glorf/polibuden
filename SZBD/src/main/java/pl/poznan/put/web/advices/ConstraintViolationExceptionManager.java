package pl.poznan.put.web.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ConstraintViolationExceptionManager {
    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView constraintValidationException(final ConstraintViolationException ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("message", "Wprowadzony obiekt już istnieje");
        return mav;
    }

}

