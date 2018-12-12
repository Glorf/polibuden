package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Budynek;
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
import pl.poznan.put.service.api.BudynekService;

/**
 * = BudyneksItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Budynek.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/budyneks/{budynek}", name = "BudyneksItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class BudyneksItemThymeleafController implements ConcurrencyManager<Budynek> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<Budynek> concurrencyTemplate = new ConcurrencyTemplate<Budynek>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<BudyneksItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private BudynekService budynekService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<BudyneksCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param budynekService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public BudyneksItemThymeleafController(BudynekService budynekService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setBudynekService(budynekService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(BudyneksItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(BudyneksCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return BudynekService
     */
    public BudynekService getBudynekService() {
        return budynekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynekService
     */
    public void setBudynekService(BudynekService budynekService) {
        this.budynekService = budynekService;
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
    public MethodLinkBuilderFactory<BudyneksItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<BudyneksItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<BudyneksCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<BudyneksCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Budynek
     */
    @ModelAttribute
    public Budynek getBudynek(@PathVariable("budynek") Long id, Locale locale, HttpMethod method) {
        Budynek budynek = null;
        if (HttpMethod.PUT.equals(method)) {
            budynek = budynekService.findOneForUpdate(id);
        } else {
            budynek = budynekService.findOne(id);
        }
        if (budynek == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Budynek", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return budynek;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Budynek budynek, Model model) {
        model.addAttribute("budynek", budynek);
        return new ModelAndView("budyneks/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Budynek budynek, Model model) {
        model.addAttribute("budynek", budynek);
        return new ModelAndView("budyneks/showInline :: inline-content");
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
    public ConcurrencyTemplate<Budynek> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "budynek";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "budyneks/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(Budynek record) {
        return getBudynekService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(Budynek entity, Model model) {
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
    @InitBinder("budynek")
    public void initBudynekBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(Budynek.class, getBudynekService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Budynek budynek, Model model) {
        populateForm(model);
        model.addAttribute("budynek", budynek);
        return new ModelAndView("budyneks/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Budynek budynek, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        Budynek savedBudynek = getConcurrencyTemplate().execute(budynek, model, new ConcurrencyCallback<Budynek>() {

            @Override
            public Budynek doInConcurrency(Budynek budynek) throws Exception {
                return getBudynekService().save(budynek);
            }
        });
        UriComponents showURI = getItemLink().to(BudyneksItemThymeleafLinkFactory.SHOW).with("budynek", savedBudynek.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Budynek budynek) {
        getBudynekService().delete(budynek);
        return ResponseEntity.ok().build();
    }
}
