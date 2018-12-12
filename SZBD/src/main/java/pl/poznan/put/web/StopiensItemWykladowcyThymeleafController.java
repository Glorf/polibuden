package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Stopien;
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
import pl.poznan.put.service.api.StopienService;
import pl.poznan.put.service.api.WykladowcaService;

/**
 * = StopiensItemWykladowcyThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Stopien.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "wykladowcy", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/stopiens/{stopien}/wykladowcy", name = "StopiensItemWykladowcyThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class StopiensItemWykladowcyThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WykladowcaService wykladowcaService;

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
    private ConversionService conversionService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<StopiensCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param stopienService
     * @param wykladowcaService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public StopiensItemWykladowcyThymeleafController(StopienService stopienService, WykladowcaService wykladowcaService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setStopienService(stopienService);
        setWykladowcaService(wykladowcaService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
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
     * @param stopien
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Wykladowca>> datatables(@ModelAttribute Stopien stopien, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Wykladowca> wykladowcy = getWykladowcaService().findByStopien(stopien, search, pageable);
        long totalWykladowcyCount = getWykladowcaService().countByStopien(stopien);
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
     * @param stopien
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Stopien stopien, Model model) {
        populateForm(model);
        model.addAttribute("wykladowca", new Wykladowca());
        return new ModelAndView("stopiens/wykladowcy/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromWykladowcy", value = "/{wykladowcyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromWykladowcy(@ModelAttribute Stopien stopien, @PathVariable("wykladowcyToRemove") Long wykladowcyToRemove) {
        getStopienService().removeFromWykladowcy(stopien, Collections.singleton(wykladowcyToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromWykladowcyBatch", value = "/batch/{wykladowcyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromWykladowcyBatch(@ModelAttribute Stopien stopien, @PathVariable("wykladowcyToRemove") Collection<Long> wykladowcyToRemove) {
        getStopienService().removeFromWykladowcy(stopien, wykladowcyToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopien
     * @param wykladowcy
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Stopien stopien, @RequestParam(value = "wykladowcyIds", required = false) List<Long> wykladowcy, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (wykladowcy != null) {
            for (Iterator<Long> iterator = wykladowcy.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, stopien.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (wykladowcy != null) {
                stopien.setWykladowcy(new HashSet<Wykladowca>(getWykladowcaService().findAll(wykladowcy)));
            } else {
                stopien.setWykladowcy(new HashSet<Wykladowca>());
            }
            // Reset the version to prevent update
            stopien.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("stopien", stopien);
            model.addAttribute("concurrency", true);
            return new ModelAndView("stopiens/wykladowcy/create");
        } else if (!Objects.equals(version, stopien.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("stopien", stopien);
            model.addAttribute("concurrency", false);
            return new ModelAndView("stopiens/wykladowcy/create");
        }
        getStopienService().setWykladowcy(stopien, wykladowcy);
        return new ModelAndView("redirect:" + getCollectionLink().to(StopiensCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
