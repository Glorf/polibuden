package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Kierunek;
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
import pl.poznan.put.service.api.KierunekService;

/**
 * = KieruneksItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Kierunek.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/kieruneks/{kierunek}", name = "KieruneksItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class KieruneksItemThymeleafController implements ConcurrencyManager<Kierunek> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private KierunekService kierunekService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<Kierunek> concurrencyTemplate = new ConcurrencyTemplate<Kierunek>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<KieruneksItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<KieruneksCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param kierunekService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public KieruneksItemThymeleafController(KierunekService kierunekService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setKierunekService(kierunekService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(KieruneksItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(KieruneksCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return KierunekService
     */
    public KierunekService getKierunekService() {
        return kierunekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunekService
     */
    public void setKierunekService(KierunekService kierunekService) {
        this.kierunekService = kierunekService;
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
    public MethodLinkBuilderFactory<KieruneksItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<KieruneksItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<KieruneksCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<KieruneksCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Kierunek
     */
    @ModelAttribute
    public Kierunek getKierunek(@PathVariable("kierunek") Long id, Locale locale, HttpMethod method) {
        Kierunek kierunek = null;
        if (HttpMethod.PUT.equals(method)) {
            kierunek = kierunekService.findOneForUpdate(id);
        } else {
            kierunek = kierunekService.findOne(id);
        }
        if (kierunek == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Kierunek", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return kierunek;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Kierunek kierunek, Model model) {
        model.addAttribute("kierunek", kierunek);
        return new ModelAndView("kieruneks/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Kierunek kierunek, Model model) {
        model.addAttribute("kierunek", kierunek);
        return new ModelAndView("kieruneks/showInline :: inline-content");
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
    public ConcurrencyTemplate<Kierunek> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "kierunek";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "kieruneks/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(Kierunek record) {
        return getKierunekService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(Kierunek entity, Model model) {
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
    @InitBinder("kierunek")
    public void initKierunekBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(Kierunek.class, getKierunekService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Kierunek kierunek, Model model) {
        populateForm(model);
        model.addAttribute("kierunek", kierunek);
        return new ModelAndView("kieruneks/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Kierunek kierunek, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        Kierunek savedKierunek = getConcurrencyTemplate().execute(kierunek, model, new ConcurrencyCallback<Kierunek>() {

            @Override
            public Kierunek doInConcurrency(Kierunek kierunek) throws Exception {
                return getKierunekService().save(kierunek);
            }
        });
        UriComponents showURI = getItemLink().to(KieruneksItemThymeleafLinkFactory.SHOW).with("kierunek", savedKierunek.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Kierunek kierunek) {
        getKierunekService().delete(kierunek);
        return ResponseEntity.ok().build();
    }
}
