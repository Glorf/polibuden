package pl.poznan.put.web;
import io.springlets.web.mvc.util.concurrency.ConcurrencyManager;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.model.Wykladowca;
import io.springlets.data.web.validation.GenericValidator;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import io.springlets.web.mvc.util.concurrency.ConcurrencyCallback;
import io.springlets.web.mvc.util.concurrency.ConcurrencyTemplate;
import java.util.Locale;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import pl.poznan.put.service.api.WykladowcaService;

/**
 * = WykladowcasItemThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Wykladowca.class, type = ControllerType.ITEM)
@RooThymeleaf
@Controller
@RequestMapping(value = "/wykladowcas/{wykladowca}", name = "WykladowcasItemThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class WykladowcasItemThymeleafController implements ConcurrencyManager<Wykladowca> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WykladowcaService wykladowcaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private final ConcurrencyTemplate<Wykladowca> concurrencyTemplate = new ConcurrencyTemplate<Wykladowca>(this);

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<WykladowcasItemThymeleafController> itemLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<WykladowcasCollectionThymeleafController> collectionLink;

    private EntityManager em;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param wykladowcaService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public WykladowcasItemThymeleafController(WykladowcaService wykladowcaService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder, EntityManager em) {
        setWykladowcaService(wykladowcaService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(WykladowcasItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(WykladowcasCollectionThymeleafController.class));
        this.em = em;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return WykladowcaService
     */
    public WykladowcaService getWykladowcaService() {
        return wykladowcaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowcaService
     */
    public void setWykladowcaService(WykladowcaService wykladowcaService) {
        this.wykladowcaService = wykladowcaService;
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
    public MethodLinkBuilderFactory<WykladowcasItemThymeleafController> getItemLink() {
        return itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<WykladowcasItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<WykladowcasCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<WykladowcasCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Wykladowca
     */
    @ModelAttribute
    public Wykladowca getWykladowca(@PathVariable("wykladowca") Long id, Locale locale, HttpMethod method) {
        Wykladowca wykladowca = null;
        if (HttpMethod.PUT.equals(method)) {
            wykladowca = wykladowcaService.findOneForUpdate(id);
        } else {
            wykladowca = wykladowcaService.findOne(id);
        }
        if (wykladowca == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Wykladowca", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return wykladowca;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Wykladowca wykladowca, Model model) {
        model.addAttribute("wykladowca", wykladowca);

        StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("funkcjaStaz");
        query.setParameter("id_prac", wykladowca.getId());

        model.addAttribute("staz", query.getSingleResult());

        return new ModelAndView("wykladowcas/show");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Wykladowca wykladowca, Model model) {
        model.addAttribute("wykladowca", wykladowca);
        return new ModelAndView("wykladowcas/showInline :: inline-content");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("zatrudniony_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
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
    public ConcurrencyTemplate<Wykladowca> getConcurrencyTemplate() {
        return concurrencyTemplate;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getModelName() {
        return "wykladowca";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return String
     */
    public String getEditViewPath() {
        return "wykladowcas/edit";
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param record
     * @return Integer
     */
    public Integer getLastVersion(Wykladowca record) {
        return getWykladowcaService().findOne(record.getId()).getVersion();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param entity
     * @param model
     * @return ModelAndView
     */
    public ModelAndView populateAndGetFormView(Wykladowca entity, Model model) {
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
    @InitBinder("wykladowca")
    public void initWykladowcaBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
        // Register validators
        GenericValidator validator = new GenericValidator(Wykladowca.class, getWykladowcaService());
        binder.addValidators(validator);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Wykladowca wykladowca, Model model) {
        populateForm(model);
        model.addAttribute("wykladowca", wykladowca);
        return new ModelAndView("wykladowcas/edit");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Wykladowca wykladowca, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            return new ModelAndView(getEditViewPath());
        }
        // Create Concurrency Spring Template to ensure that the following code will manage the
        // possible concurrency exceptions that appears and execute the provided coded inside the Spring template.
        // If some concurrency exception appears the template will manage it.
        Wykladowca savedWykladowca = getConcurrencyTemplate().execute(wykladowca, model, new ConcurrencyCallback<Wykladowca>() {

            @Override
            public Wykladowca doInConcurrency(Wykladowca wykladowca) throws Exception {
                return getWykladowcaService().save(wykladowca);
            }
        });
        UriComponents showURI = getItemLink().to(WykladowcasItemThymeleafLinkFactory.SHOW).with("wykladowca", savedWykladowca.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wykladowca
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Wykladowca wykladowca) {
        getWykladowcaService().delete(wykladowca);
        return ResponseEntity.ok().build();
    }
}
