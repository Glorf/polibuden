package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Literatura;
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
import pl.poznan.put.service.api.LiteraturaService;

/**
 * = LiteraturasItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Literatura.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/literaturas/{literatura}", name = "LiteraturasItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class LiteraturasItemThymeleafController implements ConcurrencyManager<Literatura> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<Literatura> concurrencyTemplate = new ConcurrencyTemplate<Literatura>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private LiteraturaService literaturaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<LiteraturasItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<LiteraturasCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param literaturaService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public LiteraturasItemThymeleafController(LiteraturaService literaturaService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setLiteraturaService(literaturaService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(LiteraturasItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(LiteraturasCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return LiteraturaService
     */
    public LiteraturaService getLiteraturaService() {
        return literaturaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literaturaService
     */
    public void setLiteraturaService(LiteraturaService literaturaService) {
        this.literaturaService = literaturaService;
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
    public MethodLinkBuilderFactory<LiteraturasItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<LiteraturasItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<LiteraturasCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<LiteraturasCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Literatura
     */
    @ModelAttribute
    public Literatura getLiteratura(@PathVariable("literatura") Long id, Locale locale, HttpMethod method) {
        Literatura literatura = null;
        if (HttpMethod.PUT.equals(method)) {
            literatura = literaturaService.findOneForUpdate(id);
        } else {
            literatura = literaturaService.findOne(id);
        }
        if (literatura == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Literatura", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return literatura;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Literatura literatura, Model model) {
        model.addAttribute("literatura", literatura);
        return new ModelAndView("literaturas/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Literatura literatura, Model model) {
        model.addAttribute("literatura", literatura);
        return new ModelAndView("literaturas/showInline :: inline-content");
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
    public ConcurrencyTemplate<Literatura> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "literatura";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "literaturas/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(Literatura record) {
        return getLiteraturaService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(Literatura entity, Model model) {
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
    @InitBinder("literatura")
    public void initLiteraturaBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(Literatura.class, getLiteraturaService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Literatura literatura, Model model) {
        populateForm(model);
        model.addAttribute("literatura", literatura);
        return new ModelAndView("literaturas/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Literatura literatura, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        Literatura savedLiteratura = getConcurrencyTemplate().execute(literatura, model, new ConcurrencyCallback<Literatura>() {

            @Override
            public Literatura doInConcurrency(Literatura literatura) throws Exception {
                return getLiteraturaService().save(literatura);
            }
        });
        UriComponents showURI = getItemLink().to(LiteraturasItemThymeleafLinkFactory.SHOW).with("literatura", savedLiteratura.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param literatura
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Literatura literatura) {
        getLiteraturaService().delete(literatura);
        return ResponseEntity.ok().build();
    }
}
