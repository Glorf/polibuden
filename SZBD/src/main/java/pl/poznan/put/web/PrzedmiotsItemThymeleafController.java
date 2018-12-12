package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Przedmiot;
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
import pl.poznan.put.service.api.PrzedmiotService;

/**
 * = PrzedmiotsItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Przedmiot.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/przedmiots/{przedmiot}", name = "PrzedmiotsItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class PrzedmiotsItemThymeleafController implements ConcurrencyManager<Przedmiot> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private PrzedmiotService przedmiotService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<Przedmiot> concurrencyTemplate = new ConcurrencyTemplate<Przedmiot>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<PrzedmiotsItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<PrzedmiotsCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param przedmiotService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public PrzedmiotsItemThymeleafController(PrzedmiotService przedmiotService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setPrzedmiotService(przedmiotService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(PrzedmiotsItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(PrzedmiotsCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return PrzedmiotService
     */
    public PrzedmiotService getPrzedmiotService() {
        return przedmiotService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiotService
     */
    public void setPrzedmiotService(PrzedmiotService przedmiotService) {
        this.przedmiotService = przedmiotService;
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
    public MethodLinkBuilderFactory<PrzedmiotsItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<PrzedmiotsItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<PrzedmiotsCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<PrzedmiotsCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Przedmiot
     */
    @ModelAttribute
    public Przedmiot getPrzedmiot(@PathVariable("przedmiot") Long id, Locale locale, HttpMethod method) {
        Przedmiot przedmiot = null;
        if (HttpMethod.PUT.equals(method)) {
            przedmiot = przedmiotService.findOneForUpdate(id);
        } else {
            przedmiot = przedmiotService.findOne(id);
        }
        if (przedmiot == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Przedmiot", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return przedmiot;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Przedmiot przedmiot, Model model) {
        model.addAttribute("przedmiot", przedmiot);
        return new ModelAndView("przedmiots/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Przedmiot przedmiot, Model model) {
        model.addAttribute("przedmiot", przedmiot);
        return new ModelAndView("przedmiots/showInline :: inline-content");
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
    public ConcurrencyTemplate<Przedmiot> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "przedmiot";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "przedmiots/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(Przedmiot record) {
        return getPrzedmiotService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(Przedmiot entity, Model model) {
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
    @InitBinder("przedmiot")
    public void initPrzedmiotBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(Przedmiot.class, getPrzedmiotService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Przedmiot przedmiot, Model model) {
        populateForm(model);
        model.addAttribute("przedmiot", przedmiot);
        return new ModelAndView("przedmiots/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Przedmiot przedmiot, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        Przedmiot savedPrzedmiot = getConcurrencyTemplate().execute(przedmiot, model, new ConcurrencyCallback<Przedmiot>() {

            @Override
            public Przedmiot doInConcurrency(Przedmiot przedmiot) throws Exception {
                return getPrzedmiotService().save(przedmiot);
            }
        });
        UriComponents showURI = getItemLink().to(PrzedmiotsItemThymeleafLinkFactory.SHOW).with("przedmiot", savedPrzedmiot.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Przedmiot przedmiot) {
        getPrzedmiotService().delete(przedmiot);
        return ResponseEntity.ok().build();
    }
}
