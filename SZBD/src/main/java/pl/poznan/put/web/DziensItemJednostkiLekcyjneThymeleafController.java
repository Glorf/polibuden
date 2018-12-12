package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Dzien;
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
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.service.api.DzienService;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;

/**
 * = DziensItemJednostkiLekcyjneThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Dzien.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "jednostkiLekcyjne", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/dziens/{dzien}/jednostkiLekcyjne", name = "DziensItemJednostkiLekcyjneThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class DziensItemJednostkiLekcyjneThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private DzienService dzienService;

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
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MethodLinkBuilderFactory<DziensCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param dzienService
     * @param jednostkaLekcyjnaService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public DziensItemJednostkiLekcyjneThymeleafController(DzienService dzienService, JednostkaLekcyjnaService jednostkaLekcyjnaService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setDzienService(dzienService);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setCollectionLink(linkBuilder.of(DziensCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return DzienService
     */
    public DzienService getDzienService() {
        return dzienService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzienService
     */
    public void setDzienService(DzienService dzienService) {
        this.dzienService = dzienService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return JednostkaLekcyjnaService
     */
    public JednostkaLekcyjnaService getJednostkaLekcyjnaService() {
        return jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjnaService
     */
    public void setJednostkaLekcyjnaService(JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        this.jednostkaLekcyjnaService = jednostkaLekcyjnaService;
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
    public MethodLinkBuilderFactory<DziensCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<DziensCollectionThymeleafController> collectionLink) {
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
     * @return Dzien
     */
    @ModelAttribute
    public Dzien getDzien(@PathVariable("dzien") Long id, Locale locale, HttpMethod method) {
        Dzien dzien = null;
        if (HttpMethod.PUT.equals(method)) {
            dzien = dzienService.findOneForUpdate(id);
        } else {
            dzien = dzienService.findOne(id);
        }
        if (dzien == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Dzien", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return dzien;
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
     * @param dzien
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<JednostkaLekcyjna>> datatables(@ModelAttribute Dzien dzien, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findByDzien(dzien, search, pageable);
        long totalJednostkiLekcyjneCount = getJednostkaLekcyjnaService().countByDzien(dzien);
        ConvertedDatatablesData<JednostkaLekcyjna> data = new ConvertedDatatablesData<JednostkaLekcyjna>(jednostkiLekcyjne, totalJednostkiLekcyjneCount, draw, getConversionService(), datatablesColumns);
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
    public ResponseEntity<ConvertedDatatablesData<JednostkaLekcyjna>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findAllByIdsIn(ids, search, pageable);
        long totalJednostkiLekcyjneCount = jednostkiLekcyjne.getTotalElements();
        ConvertedDatatablesData<JednostkaLekcyjna> data = new ConvertedDatatablesData<JednostkaLekcyjna>(jednostkiLekcyjne, totalJednostkiLekcyjneCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Dzien dzien, Model model) {
        populateForm(model);
        model.addAttribute("jednostkaLekcyjna", new JednostkaLekcyjna());
        return new ModelAndView("dziens/jednostkiLekcyjne/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjneToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromJednostkiLekcyjne", value = "/{jednostkiLekcyjneToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromJednostkiLekcyjne(@ModelAttribute Dzien dzien, @PathVariable("jednostkiLekcyjneToRemove") Long jednostkiLekcyjneToRemove) {
        getDzienService().removeFromJednostkiLekcyjne(dzien, Collections.singleton(jednostkiLekcyjneToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjneToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromJednostkiLekcyjneBatch", value = "/batch/{jednostkiLekcyjneToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromJednostkiLekcyjneBatch(@ModelAttribute Dzien dzien, @PathVariable("jednostkiLekcyjneToRemove") Collection<Long> jednostkiLekcyjneToRemove) {
        getDzienService().removeFromJednostkiLekcyjne(dzien, jednostkiLekcyjneToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzien
     * @param jednostkiLekcyjne
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Dzien dzien, @RequestParam(value = "jednostkiLekcyjneIds", required = false) List<Long> jednostkiLekcyjne, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (jednostkiLekcyjne != null) {
            for (Iterator<Long> iterator = jednostkiLekcyjne.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, dzien.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (jednostkiLekcyjne != null) {
                dzien.setJednostkiLekcyjne(new HashSet<JednostkaLekcyjna>(getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne)));
            } else {
                dzien.setJednostkiLekcyjne(new HashSet<JednostkaLekcyjna>());
            }
            // Reset the version to prevent update
            dzien.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("dzien", dzien);
            model.addAttribute("concurrency", true);
            return new ModelAndView("dziens/jednostkiLekcyjne/create");
        } else if (!Objects.equals(version, dzien.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("dzien", dzien);
            model.addAttribute("concurrency", false);
            return new ModelAndView("dziens/jednostkiLekcyjne/create");
        }
        getDzienService().setJednostkiLekcyjne(dzien, jednostkiLekcyjne);
        return new ModelAndView("redirect:" + getCollectionLink().to(DziensCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
