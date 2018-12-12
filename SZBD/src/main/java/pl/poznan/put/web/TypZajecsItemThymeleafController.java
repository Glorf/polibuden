package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.TypZajec;
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
import pl.poznan.put.service.api.TypZajecService;

/**
 * = TypZajecsItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = TypZajec.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/typzajecs/{typZajec}", name = "TypZajecsItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class TypZajecsItemThymeleafController implements ConcurrencyManager<TypZajec> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<TypZajec> concurrencyTemplate = new ConcurrencyTemplate<TypZajec>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<TypZajecsItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private TypZajecService typZajecService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<TypZajecsCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param typZajecService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public TypZajecsItemThymeleafController(TypZajecService typZajecService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setTypZajecService(typZajecService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(TypZajecsItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(TypZajecsCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TypZajecService
     */
    public TypZajecService getTypZajecService() {
        return typZajecService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajecService
     */
    public void setTypZajecService(TypZajecService typZajecService) {
        this.typZajecService = typZajecService;
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
    public MethodLinkBuilderFactory<TypZajecsItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<TypZajecsItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<TypZajecsCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<TypZajecsCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return TypZajec
     */
    @ModelAttribute
    public TypZajec getTypZajec(@PathVariable("typZajec") Long id, Locale locale, HttpMethod method) {
        TypZajec typZajec = null;
        if (HttpMethod.PUT.equals(method)) {
            typZajec = typZajecService.findOneForUpdate(id);
        } else {
            typZajec = typZajecService.findOne(id);
        }
        if (typZajec == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "TypZajec", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return typZajec;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute TypZajec typZajec, Model model) {
        model.addAttribute("typZajec", typZajec);
        return new ModelAndView("typzajecs/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute TypZajec typZajec, Model model) {
        model.addAttribute("typZajec", typZajec);
        return new ModelAndView("typzajecs/showInline :: inline-content");
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
    public ConcurrencyTemplate<TypZajec> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "typZajec";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "typzajecs/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(TypZajec record) {
        return getTypZajecService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(TypZajec entity, Model model) {
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
    @InitBinder("typZajec")
    public void initTypZajecBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(TypZajec.class, getTypZajecService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute TypZajec typZajec, Model model) {
        populateForm(model);
        model.addAttribute("typZajec", typZajec);
        return new ModelAndView("typzajecs/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute TypZajec typZajec, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        TypZajec savedTypZajec = getConcurrencyTemplate().execute(typZajec, model, new ConcurrencyCallback<TypZajec>() {

            @Override
            public TypZajec doInConcurrency(TypZajec typZajec) throws Exception {
                return getTypZajecService().save(typZajec);
            }
        });
        UriComponents showURI = getItemLink().to(TypZajecsItemThymeleafLinkFactory.SHOW).with("typZajec", savedTypZajec.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajec
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute TypZajec typZajec) {
        getTypZajecService().delete(typZajec);
        return ResponseEntity.ok().build();
    }
}
