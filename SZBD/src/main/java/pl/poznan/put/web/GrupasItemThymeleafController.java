package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Grupa;
import io.springlets.data.web.validation.GenericValidator;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import io.springlets.web.mvc.util.concurrency.ConcurrencyCallback;
import io.springlets.web.mvc.util.concurrency.ConcurrencyTemplate;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.StoredProcedureQuery;
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
import pl.poznan.put.service.api.GrupaService;

/**
 * = GrupasItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Grupa.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/grupas/{grupa}", name = "GrupasItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class GrupasItemThymeleafController implements ConcurrencyManager<Grupa> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<Grupa> concurrencyTemplate = new ConcurrencyTemplate<Grupa>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<GrupasItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private GrupaService grupaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<GrupasCollectionThymeleafController> collectionLink;


    private EntityManager em;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param grupaService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public GrupasItemThymeleafController(GrupaService grupaService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder, EntityManager em) {
        setGrupaService(grupaService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(GrupasItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(GrupasCollectionThymeleafController.class));
        this.em = em;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return GrupaService
     */
    public GrupaService getGrupaService() {
        return grupaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupaService
     */
    public void setGrupaService(GrupaService grupaService) {
        this.grupaService = grupaService;
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
    public MethodLinkBuilderFactory<GrupasItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<GrupasItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<GrupasCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<GrupasCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Grupa
     */
    @ModelAttribute
    public Grupa getGrupa(@PathVariable("grupa") Long id, Locale locale, HttpMethod method) {
        Grupa grupa = null;
        if (HttpMethod.PUT.equals(method)) {
            grupa = grupaService.findOneForUpdate(id);
        } else {
            grupa = grupaService.findOne(id);
        }
        if (grupa == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Grupa", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return grupa;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Grupa grupa, Model model) {
        model.addAttribute("grupa", grupa);

        Integer result = (Integer)this.em.createNamedStoredProcedureQuery("funkcjaLiczbaOsob")
                .setParameter("id_grupy", grupa.getId())
                .getSingleResult();

        model.addAttribute("ileOsob", result);

        return new ModelAndView("grupas/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Grupa grupa, Model model) {
        model.addAttribute("grupa", grupa);
        return new ModelAndView("grupas/showInline :: inline-content");
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
    public ConcurrencyTemplate<Grupa> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "grupa";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "grupas/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(Grupa record) {
        return getGrupaService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(Grupa entity, Model model) {
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
    @InitBinder("grupa")
    public void initGrupaBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(Grupa.class, getGrupaService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Grupa grupa, Model model) {
        populateForm(model);
        model.addAttribute("grupa", grupa);
        return new ModelAndView("grupas/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Grupa grupa, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        Grupa savedGrupa = getConcurrencyTemplate().execute(grupa, model, new ConcurrencyCallback<Grupa>() {

            @Override
            public Grupa doInConcurrency(Grupa grupa) throws Exception {
                return getGrupaService().save(grupa);
            }
        });
        UriComponents showURI = getItemLink().to(GrupasItemThymeleafLinkFactory.SHOW).with("grupa", savedGrupa.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Grupa grupa) {
        getGrupaService().delete(grupa);
        return ResponseEntity.ok().build();
    }
}
