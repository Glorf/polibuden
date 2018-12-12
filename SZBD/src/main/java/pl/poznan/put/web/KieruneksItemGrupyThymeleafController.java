package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Kierunek;
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
import pl.poznan.put.model.Grupa;
import pl.poznan.put.service.api.GrupaService;
import pl.poznan.put.service.api.KierunekService;

/**
 * = KieruneksItemGrupyThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Kierunek.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "grupy", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/kieruneks/{kierunek}/grupy", name = "KieruneksItemGrupyThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class KieruneksItemGrupyThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private KierunekService kierunekService;

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
    private ConversionService conversionService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<KieruneksCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param kierunekService
     * @param grupaService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public KieruneksItemGrupyThymeleafController(KierunekService kierunekService, GrupaService grupaService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setKierunekService(kierunekService);
        setGrupaService(grupaService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setCollectionLink(linkBuilder.of(KieruneksCollectionThymeleafController.class));
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
    public MethodLinkBuilderFactory<KieruneksCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<KieruneksCollectionThymeleafController> collectionLink) {
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
     * @return Kierunek
     */
    @ModelAttribute
    public Kierunek getKierunek(@PathVariable("kierunek") Long id, Locale locale, HttpMethod method) {
        Kierunek kierunek = null;
        if (HttpMethod.PUT.equals(method)) {
            kierunek = kierunekService.findOneForUpdate(id);
        } else {
            kierunek = kierunekService.findOne(id);
        }
        if (kierunek == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Kierunek", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return kierunek;
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
     * @param kierunek
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Grupa>> datatables(@ModelAttribute Kierunek kierunek, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Grupa> grupy = getGrupaService().findByKierunek(kierunek, search, pageable);
        long totalGrupyCount = getGrupaService().countByKierunek(kierunek);
        ConvertedDatatablesData<Grupa> data = new ConvertedDatatablesData<Grupa>(grupy, totalGrupyCount, draw, getConversionService(), datatablesColumns);
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
    public ResponseEntity<ConvertedDatatablesData<Grupa>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Grupa> grupy = getGrupaService().findAllByIdsIn(ids, search, pageable);
        long totalGrupyCount = grupy.getTotalElements();
        ConvertedDatatablesData<Grupa> data = new ConvertedDatatablesData<Grupa>(grupy, totalGrupyCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Kierunek kierunek, Model model) {
        populateForm(model);
        model.addAttribute("grupa", new Grupa());
        return new ModelAndView("kieruneks/grupy/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromGrupy", value = "/{grupyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromGrupy(@ModelAttribute Kierunek kierunek, @PathVariable("grupyToRemove") Long grupyToRemove) {
        getKierunekService().removeFromGrupy(kierunek, Collections.singleton(grupyToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupyToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromGrupyBatch", value = "/batch/{grupyToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromGrupyBatch(@ModelAttribute Kierunek kierunek, @PathVariable("grupyToRemove") Collection<Long> grupyToRemove) {
        getKierunekService().removeFromGrupy(kierunek, grupyToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunek
     * @param grupy
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Kierunek kierunek, @RequestParam(value = "grupyIds", required = false) List<Long> grupy, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (grupy != null) {
            for (Iterator<Long> iterator = grupy.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, kierunek.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (grupy != null) {
                kierunek.setGrupy(new HashSet<Grupa>(getGrupaService().findAll(grupy)));
            } else {
                kierunek.setGrupy(new HashSet<Grupa>());
            }
            // Reset the version to prevent update
            kierunek.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("kierunek", kierunek);
            model.addAttribute("concurrency", true);
            return new ModelAndView("kieruneks/grupy/create");
        } else if (!Objects.equals(version, kierunek.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("kierunek", kierunek);
            model.addAttribute("concurrency", false);
            return new ModelAndView("kieruneks/grupy/create");
        }
        getKierunekService().setGrupy(kierunek, grupy);
        return new ModelAndView("redirect:" + getCollectionLink().to(KieruneksCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
