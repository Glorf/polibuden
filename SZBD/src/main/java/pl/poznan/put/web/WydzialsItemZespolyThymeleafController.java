package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Wydzial;
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
import pl.poznan.put.model.Zespol;
import pl.poznan.put.service.api.WydzialService;
import pl.poznan.put.service.api.ZespolService;

/**
 * = WydzialsItemZespolyThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Wydzial.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "zespoly", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/wydzials/{wydzial}/zespoly", name = "WydzialsItemZespolyThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class WydzialsItemZespolyThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WydzialService wydzialService;

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
    private MethodLinkBuilderFactory<WydzialsCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param wydzialService
     * @param zespolService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public WydzialsItemZespolyThymeleafController(WydzialService wydzialService, ZespolService zespolService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setWydzialService(wydzialService);
        setZespolService(zespolService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setCollectionLink(linkBuilder.of(WydzialsCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return WydzialService
     */
    public WydzialService getWydzialService() {
        return wydzialService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzialService
     */
    public void setWydzialService(WydzialService wydzialService) {
        this.wydzialService = wydzialService;
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
    public MethodLinkBuilderFactory<WydzialsCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<WydzialsCollectionThymeleafController> collectionLink) {
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
     * @return Wydzial
     */
    @ModelAttribute
    public Wydzial getWydzial(@PathVariable("wydzial") Long id, Locale locale, HttpMethod method) {
        Wydzial wydzial = null;
        if (HttpMethod.PUT.equals(method)) {
            wydzial = wydzialService.findOneForUpdate(id);
        } else {
            wydzial = wydzialService.findOne(id);
        }
        if (wydzial == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Wydzial", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return wydzial;
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
     * @param wydzial
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Zespol>> datatables(@ModelAttribute Wydzial wydzial, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Zespol> zespoly = getZespolService().findByWydzial(wydzial, search, pageable);
        long totalZespolyCount = getZespolService().countByWydzial(wydzial);
        ConvertedDatatablesData<Zespol> data = new ConvertedDatatablesData<Zespol>(zespoly, totalZespolyCount, draw, getConversionService(), datatablesColumns);
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
    public ResponseEntity<ConvertedDatatablesData<Zespol>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Zespol> zespoly = getZespolService().findAllByIdsIn(ids, search, pageable);
        long totalZespolyCount = zespoly.getTotalElements();
        ConvertedDatatablesData<Zespol> data = new ConvertedDatatablesData<Zespol>(zespoly, totalZespolyCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Wydzial wydzial, Model model) {
        populateForm(model);
        model.addAttribute("zespol", new Zespol());
        return new ModelAndView("wydzials/zespoly/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespolyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromZespoly", value = "/{zespolyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromZespoly(@ModelAttribute Wydzial wydzial, @PathVariable("zespolyToRemove") Long zespolyToRemove) {
        getWydzialService().removeFromZespoly(wydzial, Collections.singleton(zespolyToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespolyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromZespolyBatch", value = "/batch/{zespolyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromZespolyBatch(@ModelAttribute Wydzial wydzial, @PathVariable("zespolyToRemove") Collection<Long> zespolyToRemove) {
        getWydzialService().removeFromZespoly(wydzial, zespolyToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param zespoly
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Wydzial wydzial, @RequestParam(value = "zespolyIds", required = false) List<Long> zespoly, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (zespoly != null) {
            for (Iterator<Long> iterator = zespoly.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, wydzial.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (zespoly != null) {
                wydzial.setZespoly(new HashSet<Zespol>(getZespolService().findAll(zespoly)));
            } else {
                wydzial.setZespoly(new HashSet<Zespol>());
            }
            // Reset the version to prevent update
            wydzial.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("wydzial", wydzial);
            model.addAttribute("concurrency", true);
            return new ModelAndView("wydzials/zespoly/create");
        } else if (!Objects.equals(version, wydzial.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("wydzial", wydzial);
            model.addAttribute("concurrency", false);
            return new ModelAndView("wydzials/zespoly/create");
        }
        getWydzialService().setZespoly(wydzial, zespoly);
        return new ModelAndView("redirect:" + getCollectionLink().to(WydzialsCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
