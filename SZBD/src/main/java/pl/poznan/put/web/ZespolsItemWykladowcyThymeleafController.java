package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Zespol;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.web.datatables.ConvertedDatatablesData;
import io.springlets.data.web.datatables.Datatables;
import io.springlets.data.web.datatables.DatatablesColumns;
import io.springlets.data.web.datatables.DatatablesPageable;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pl.poznan.put.model.Wykladowca;
import pl.poznan.put.service.api.WykladowcaService;
import pl.poznan.put.service.api.ZespolService;

/**
 * = ZespolsItemWykladowcyThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Zespol.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "wykladowcy", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/zespols/{zespol}/wykladowcy", name = "ZespolsItemWykladowcyThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class ZespolsItemWykladowcyThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WykladowcaService wykladowcaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessageSource messageSource;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ZespolService zespolService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<ZespolsCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param zespolService
     * @param wykladowcaService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public ZespolsItemWykladowcyThymeleafController(ZespolService zespolService, WykladowcaService wykladowcaService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setZespolService(zespolService);
        setWykladowcaService(wykladowcaService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setCollectionLink(linkBuilder.of(ZespolsCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ZespolService
     */
    public ZespolService getZespolService() {
        return zespolService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespolService
     */
    public void setZespolService(ZespolService zespolService) {
        this.zespolService = zespolService;
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
    public MethodLinkBuilderFactory<ZespolsCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<ZespolsCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @param locale
     * @param method
     * @return Zespol
     */
    @ModelAttribute
    public Zespol getZespol(@PathVariable("zespol") Long id, Locale locale, HttpMethod method) {
        Zespol zespol = null;
        if (HttpMethod.PUT.equals(method)) {
            zespol = zespolService.findOneForUpdate(id);
        } else {
            zespol = zespolService.findOne(id);
        }
        if (zespol == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Zespol", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return zespol;
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
     * @param zespol
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Wykladowca>> datatables(@ModelAttribute Zespol zespol, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Wykladowca> wykladowcy = getWykladowcaService().findByZespol(zespol, search, pageable);
        long totalWykladowcyCount = getWykladowcaService().countByZespol(zespol);
        ConvertedDatatablesData<Wykladowca> data = new ConvertedDatatablesData<Wykladowca>(wykladowcy, totalWykladowcyCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatablesByIdsIn", produces = Datatables.MEDIA_TYPE, value = "/dtByIdsIn")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Wykladowca>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Wykladowca> wykladowcy = getWykladowcaService().findAllByIdsIn(ids, search, pageable);
        long totalWykladowcyCount = wykladowcy.getTotalElements();
        ConvertedDatatablesData<Wykladowca> data = new ConvertedDatatablesData<Wykladowca>(wykladowcy, totalWykladowcyCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Zespol zespol, Model model) {
        populateForm(model);
        model.addAttribute("wykladowca", new Wykladowca());
        return new ModelAndView("zespols/wykladowcy/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromWykladowcy", value = "/{wykladowcyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromWykladowcy(@ModelAttribute Zespol zespol, @PathVariable("wykladowcyToRemove") Long wykladowcyToRemove) {
        getZespolService().removeFromWykladowcy(zespol, Collections.singleton(wykladowcyToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromWykladowcyBatch", value = "/batch/{wykladowcyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromWykladowcyBatch(@ModelAttribute Zespol zespol, @PathVariable("wykladowcyToRemove") Collection<Long> wykladowcyToRemove) {
        getZespolService().removeFromWykladowcy(zespol, wykladowcyToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespol
     * @param wykladowcy
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Zespol zespol, @RequestParam(value = "wykladowcyIds", required = false) List<Long> wykladowcy, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (wykladowcy != null) {
            for (Iterator<Long> iterator = wykladowcy.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, zespol.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (wykladowcy != null) {
                zespol.setWykladowcy(new HashSet<Wykladowca>(getWykladowcaService().findAll(wykladowcy)));
            } else {
                zespol.setWykladowcy(new HashSet<Wykladowca>());
            }
            // Reset the version to prevent update
            zespol.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("zespol", zespol);
            model.addAttribute("concurrency", true);
            return new ModelAndView("zespols/wykladowcy/create");
        } else if (!Objects.equals(version, zespol.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("zespol", zespol);
            model.addAttribute("concurrency", false);
            return new ModelAndView("zespols/wykladowcy/create");
        }
        getZespolService().setWykladowcy(zespol, wykladowcy);
        return new ModelAndView("redirect:" + getCollectionLink().to(ZespolsCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
