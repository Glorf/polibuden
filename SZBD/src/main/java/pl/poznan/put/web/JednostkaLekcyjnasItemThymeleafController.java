package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.JednostkaLekcyjna;
import io.springlets.data.web.validation.GenericValidator;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import io.springlets.web.mvc.util.concurrency.ConcurrencyCallback;
import io.springlets.web.mvc.util.concurrency.ConcurrencyTemplate;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;

/**
 * = JednostkaLekcyjnasItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = JednostkaLekcyjna.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/jednostkalekcyjnas/{jednostkaLekcyjna}", name = "JednostkaLekcyjnasItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class JednostkaLekcyjnasItemThymeleafController implements ConcurrencyManager<JednostkaLekcyjna> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<JednostkaLekcyjna> concurrencyTemplate = new ConcurrencyTemplate<JednostkaLekcyjna>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<JednostkaLekcyjnasItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<JednostkaLekcyjnasCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param jednostkaLekcyjnaService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public JednostkaLekcyjnasItemThymeleafController(JednostkaLekcyjnaService jednostkaLekcyjnaService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(JednostkaLekcyjnasItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(JednostkaLekcyjnasCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return JednostkaLekcyjnaService
     */
    public JednostkaLekcyjnaService getJednostkaLekcyjnaService() {
        return jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjnaService
     */
    public void setJednostkaLekcyjnaService(JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        this.jednostkaLekcyjnaService = jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<JednostkaLekcyjnasItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<JednostkaLekcyjnasItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<JednostkaLekcyjnasCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<JednostkaLekcyjnasCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return JednostkaLekcyjna
     */
    @ModelAttribute
    public JednostkaLekcyjna getJednostkaLekcyjna(@PathVariable("jednostkaLekcyjna") Long id, Locale locale, HttpMethod method) {
        JednostkaLekcyjna jednostkaLekcyjna = null;
        if (HttpMethod.PUT.equals(method)) {
            jednostkaLekcyjna = jednostkaLekcyjnaService.findOneForUpdate(id);
        } else {
            jednostkaLekcyjna = jednostkaLekcyjnaService.findOne(id);
        }
        if (jednostkaLekcyjna == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "JednostkaLekcyjna", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return jednostkaLekcyjna;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjna
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute JednostkaLekcyjna jednostkaLekcyjna, Model model) {
        model.addAttribute("jednostkaLekcyjna", jednostkaLekcyjna);
        return new ModelAndView("jednostkalekcyjnas/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjna
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute JednostkaLekcyjna jednostkaLekcyjna, Model model) {
        model.addAttribute("jednostkaLekcyjna", jednostkaLekcyjna);
        return new ModelAndView("jednostkalekcyjnas/showInline :: inline-content");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ConcurrencyTemplate
     */
    public ConcurrencyTemplate<JednostkaLekcyjna> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "jednostkaLekcyjna";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "jednostkalekcyjnas/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(JednostkaLekcyjna record) {
        return getJednostkaLekcyjnaService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(JednostkaLekcyjna entity, Model model) {
        // Populate the form with all the necessary elements
        populateForm(model);
        // Add concurrency attribute to the model to show the concurrency form
        // in the current edit view
        model.addAttribute("concurrency", true);
        // Add the new version value to the model.
        model.addAttribute("newVersion", getLastVersion(entity));
        // Add the current pet values to maintain the values introduced by the user
        model.addAttribute(getModelName(), entity);
        // Return the edit view path
        return new org.springframework.web.servlet.ModelAndView(getEditViewPath(), model.asMap());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param binder
     */
    @InitBinder("jednostkaLekcyjna")
    public void initJednostkaLekcyjnaBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(JednostkaLekcyjna.class, getJednostkaLekcyjnaService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjna
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute JednostkaLekcyjna jednostkaLekcyjna, Model model) {
        populateForm(model);
        model.addAttribute("jednostkaLekcyjna", jednostkaLekcyjna);
        return new ModelAndView("jednostkalekcyjnas/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjna
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute JednostkaLekcyjna jednostkaLekcyjna, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        JednostkaLekcyjna savedJednostkaLekcyjna = getConcurrencyTemplate().execute(jednostkaLekcyjna, model, new ConcurrencyCallback<JednostkaLekcyjna>() {

            @Override
            public JednostkaLekcyjna doInConcurrency(JednostkaLekcyjna jednostkaLekcyjna) throws Exception {
                return getJednostkaLekcyjnaService().save(jednostkaLekcyjna);
            }
        });
        UriComponents showURI = getItemLink().to(JednostkaLekcyjnasItemThymeleafLinkFactory.SHOW).with("jednostkaLekcyjna", savedJednostkaLekcyjna.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjna
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute JednostkaLekcyjna jednostkaLekcyjna) {
        getJednostkaLekcyjnaService().delete(jednostkaLekcyjna);
        return ResponseEntity.ok().build();
    }
}
