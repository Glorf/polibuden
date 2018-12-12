package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Przedmiot;
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
import pl.poznan.put.model.Literatura;
import pl.poznan.put.service.api.LiteraturaService;
import pl.poznan.put.service.api.PrzedmiotService;

/**
 * = PrzedmiotsItemLiteraturaThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Przedmiot.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "literatura", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/przedmiots/{przedmiot}/literatura", name = "PrzedmiotsItemLiteraturaThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class PrzedmiotsItemLiteraturaThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private PrzedmiotService przedmiotService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private LiteraturaService literaturaService;

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
    private MethodLinkBuilderFactory<PrzedmiotsCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param przedmiotService
     * @param literaturaService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public PrzedmiotsItemLiteraturaThymeleafController(PrzedmiotService przedmiotService, LiteraturaService literaturaService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setPrzedmiotService(przedmiotService);
        setLiteraturaService(literaturaService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
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
     * @param przedmioty
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Literatura>> datatables(@ModelAttribute Przedmiot przedmioty, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Literatura> literatura = getLiteraturaService().findByPrzedmiotyContains(przedmioty, search, pageable);
        long totalLiteraturaCount = getLiteraturaService().countByPrzedmiotyContains(przedmioty);
        ConvertedDatatablesData<Literatura> data = new ConvertedDatatablesData<Literatura>(literatura, totalLiteraturaCount, draw, getConversionService(), datatablesColumns);
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
    public ResponseEntity<ConvertedDatatablesData<Literatura>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Literatura> literatura = getLiteraturaService().findAllByIdsIn(ids, search, pageable);
        long totalLiteraturaCount = literatura.getTotalElements();
        ConvertedDatatablesData<Literatura> data = new ConvertedDatatablesData<Literatura>(literatura, totalLiteraturaCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Przedmiot przedmiot, Model model) {
        populateForm(model);
        model.addAttribute("literatura", new Literatura());
        return new ModelAndView("przedmiots/literatura/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literaturaToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromLiteratura", value = "/{literaturaToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromLiteratura(@ModelAttribute Przedmiot przedmiot, @PathVariable("literaturaToRemove") Long literaturaToRemove) {
        getPrzedmiotService().removeFromLiteratura(przedmiot, Collections.singleton(literaturaToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literaturaToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromLiteraturaBatch", value = "/batch/{literaturaToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromLiteraturaBatch(@ModelAttribute Przedmiot przedmiot, @PathVariable("literaturaToRemove") Collection<Long> literaturaToRemove) {
        getPrzedmiotService().removeFromLiteratura(przedmiot, literaturaToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param literatura
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Przedmiot przedmiot, @RequestParam(value = "literaturaIds", required = false) List<Long> literatura, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (literatura != null) {
            for (Iterator<Long> iterator = literatura.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, przedmiot.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (literatura != null) {
                przedmiot.setLiteratura(new HashSet<Literatura>(getLiteraturaService().findAll(literatura)));
            } else {
                przedmiot.setLiteratura(new HashSet<Literatura>());
            }
            // Reset the version to prevent update
            przedmiot.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("przedmiot", przedmiot);
            model.addAttribute("concurrency", true);
            return new ModelAndView("przedmiots/literatura/create");
        } else if (!Objects.equals(version, przedmiot.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("przedmiot", przedmiot);
            model.addAttribute("concurrency", false);
            return new ModelAndView("przedmiots/literatura/create");
        }
        getPrzedmiotService().setLiteratura(przedmiot, literatura);
        return new ModelAndView("redirect:" + getCollectionLink().to(PrzedmiotsCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
