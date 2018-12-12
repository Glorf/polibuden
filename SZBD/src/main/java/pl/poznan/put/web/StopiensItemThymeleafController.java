package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Stopien;
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
import pl.poznan.put.service.api.StopienService;

/**
 * = StopiensItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Stopien.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/stopiens/{stopien}", name = "StopiensItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class StopiensItemThymeleafController implements ConcurrencyManager<Stopien> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<Stopien> concurrencyTemplate = new ConcurrencyTemplate<Stopien>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<StopiensItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private StopienService stopienService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<StopiensCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param stopienService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public StopiensItemThymeleafController(StopienService stopienService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setStopienService(stopienService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(StopiensItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(StopiensCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return StopienService
     */
    public StopienService getStopienService() {
        return stopienService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopienService
     */
    public void setStopienService(StopienService stopienService) {
        this.stopienService = stopienService;
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
    public MethodLinkBuilderFactory<StopiensItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<StopiensItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<StopiensCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<StopiensCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Stopien
     */
    @ModelAttribute
    public Stopien getStopien(@PathVariable("stopien") Long id, Locale locale, HttpMethod method) {
        Stopien stopien = null;
        if (HttpMethod.PUT.equals(method)) {
            stopien = stopienService.findOneForUpdate(id);
        } else {
            stopien = stopienService.findOne(id);
        }
        if (stopien == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Stopien", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return stopien;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Stopien stopien, Model model) {
        model.addAttribute("stopien", stopien);
        return new ModelAndView("stopiens/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Stopien stopien, Model model) {
        model.addAttribute("stopien", stopien);
        return new ModelAndView("stopiens/showInline :: inline-content");
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
    public ConcurrencyTemplate<Stopien> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "stopien";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "stopiens/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(Stopien record) {
        return getStopienService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(Stopien entity, Model model) {
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
    @InitBinder("stopien")
    public void initStopienBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(Stopien.class, getStopienService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Stopien stopien, Model model) {
        populateForm(model);
        model.addAttribute("stopien", stopien);
        return new ModelAndView("stopiens/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Stopien stopien, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        Stopien savedStopien = getConcurrencyTemplate().execute(stopien, model, new ConcurrencyCallback<Stopien>() {

            @Override
            public Stopien doInConcurrency(Stopien stopien) throws Exception {
                return getStopienService().save(stopien);
            }
        });
        UriComponents showURI = getItemLink().to(StopiensItemThymeleafLinkFactory.SHOW).with("stopien", savedStopien.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Stopien stopien) {
        getStopienService().delete(stopien);
        return ResponseEntity.ok().build();
    }
}
