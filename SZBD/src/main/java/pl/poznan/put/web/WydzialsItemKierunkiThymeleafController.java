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
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.service.api.KierunekService;
import pl.poznan.put.service.api.WydzialService;

/**
 * = WydzialsItemKierunkiThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Wydzial.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "kierunki", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/wydzials/{wydzial}/kierunki", name = "WydzialsItemKierunkiThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class WydzialsItemKierunkiThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private KierunekService kierunekService;

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
    private MethodLinkBuilderFactory<WydzialsCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param wydzialService
     * @param kierunekService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public WydzialsItemKierunkiThymeleafController(WydzialService wydzialService, KierunekService kierunekService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setWydzialService(wydzialService);
        setKierunekService(kierunekService);
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
     * @return KierunekService
     */
    public KierunekService getKierunekService() {
        return kierunekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunekService
     */
    public void setKierunekService(KierunekService kierunekService) {
        this.kierunekService = kierunekService;
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
    public ResponseEntity<ConvertedDatatablesData<Kierunek>> datatables(@ModelAttribute Wydzial wydzial, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Kierunek> kierunki = getKierunekService().findByWydzial(wydzial, search, pageable);
        long totalKierunkiCount = getKierunekService().countByWydzial(wydzial);
        ConvertedDatatablesData<Kierunek> data = new ConvertedDatatablesData<Kierunek>(kierunki, totalKierunkiCount, draw, getConversionService(), datatablesColumns);
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
    public ResponseEntity<ConvertedDatatablesData<Kierunek>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Kierunek> kierunki = getKierunekService().findAllByIdsIn(ids, search, pageable);
        long totalKierunkiCount = kierunki.getTotalElements();
        ConvertedDatatablesData<Kierunek> data = new ConvertedDatatablesData<Kierunek>(kierunki, totalKierunkiCount, draw, getConversionService(), datatablesColumns);
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
        model.addAttribute("kierunek", new Kierunek());
        return new ModelAndView("wydzials/kierunki/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunkiToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromKierunki", value = "/{kierunkiToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromKierunki(@ModelAttribute Wydzial wydzial, @PathVariable("kierunkiToRemove") Long kierunkiToRemove) {
        getWydzialService().removeFromKierunki(wydzial, Collections.singleton(kierunkiToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunkiToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromKierunkiBatch", value = "/batch/{kierunkiToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromKierunkiBatch(@ModelAttribute Wydzial wydzial, @PathVariable("kierunkiToRemove") Collection<Long> kierunkiToRemove) {
        getWydzialService().removeFromKierunki(wydzial, kierunkiToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzial
     * @param kierunki
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Wydzial wydzial, @RequestParam(value = "kierunkiIds", required = false) List<Long> kierunki, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (kierunki != null) {
            for (Iterator<Long> iterator = kierunki.iterator(); iterator.hasNext(); ) {
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
            if (kierunki != null) {
                wydzial.setKierunki(new HashSet<Kierunek>(getKierunekService().findAll(kierunki)));
            } else {
                wydzial.setKierunki(new HashSet<Kierunek>());
            }
            // Reset the version to prevent update
            wydzial.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("wydzial", wydzial);
            model.addAttribute("concurrency", true);
            return new ModelAndView("wydzials/kierunki/create");
        } else if (!Objects.equals(version, wydzial.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("wydzial", wydzial);
            model.addAttribute("concurrency", false);
            return new ModelAndView("wydzials/kierunki/create");
        }
        getWydzialService().setKierunki(wydzial, kierunki);
        return new ModelAndView("redirect:" + getCollectionLink().to(WydzialsCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
