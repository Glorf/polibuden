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
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;
import pl.poznan.put.service.api.PrzedmiotService;

/**
 * = PrzedmiotsItemJednostkiLekcyjneThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Przedmiot.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "jednostkiLekcyjne", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/przedmiots/{przedmiot}/jednostkiLekcyjne", name = "PrzedmiotsItemJednostkiLekcyjneThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class PrzedmiotsItemJednostkiLekcyjneThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private PrzedmiotService przedmiotService;

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
    private MethodLinkBuilderFactory<PrzedmiotsCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param przedmiotService
     * @param jednostkaLekcyjnaService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public PrzedmiotsItemJednostkiLekcyjneThymeleafController(PrzedmiotService przedmiotService, JednostkaLekcyjnaService jednostkaLekcyjnaService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setPrzedmiotService(przedmiotService);
        setJednostkaLekcyjnaService(jednostkaLekcyjnaService);
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
     * @param idPrzedmiotu
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<JednostkaLekcyjna>> datatables(@ModelAttribute Przedmiot idPrzedmiotu, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<JednostkaLekcyjna> jednostkiLekcyjne = getJednostkaLekcyjnaService().findByIdPrzedmiotu(idPrzedmiotu, search, pageable);
        long totalJednostkiLekcyjneCount = getJednostkaLekcyjnaService().countByIdPrzedmiotu(idPrzedmiotu);
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
     * @param przedmiot
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Przedmiot przedmiot, Model model) {
        populateForm(model);
        model.addAttribute("jednostkaLekcyjna", new JednostkaLekcyjna());
        return new ModelAndView("przedmiots/jednostkiLekcyjne/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjneToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromJednostkiLekcyjne", value = "/{jednostkiLekcyjneToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromJednostkiLekcyjne(@ModelAttribute Przedmiot przedmiot, @PathVariable("jednostkiLekcyjneToRemove") Long jednostkiLekcyjneToRemove) {
        getPrzedmiotService().removeFromJednostkiLekcyjne(przedmiot, Collections.singleton(jednostkiLekcyjneToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjneToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromJednostkiLekcyjneBatch", value = "/batch/{jednostkiLekcyjneToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromJednostkiLekcyjneBatch(@ModelAttribute Przedmiot przedmiot, @PathVariable("jednostkiLekcyjneToRemove") Collection<Long> jednostkiLekcyjneToRemove) {
        getPrzedmiotService().removeFromJednostkiLekcyjne(przedmiot, jednostkiLekcyjneToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiot
     * @param jednostkiLekcyjne
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Przedmiot przedmiot, @RequestParam(value = "jednostkiLekcyjneIds", required = false) List<Long> jednostkiLekcyjne, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (jednostkiLekcyjne != null) {
            for (Iterator<Long> iterator = jednostkiLekcyjne.iterator(); iterator.hasNext(); ) {
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
            if (jednostkiLekcyjne != null) {
                przedmiot.setJednostkiLekcyjne(new HashSet<JednostkaLekcyjna>(getJednostkaLekcyjnaService().findAll(jednostkiLekcyjne)));
            } else {
                przedmiot.setJednostkiLekcyjne(new HashSet<JednostkaLekcyjna>());
            }
            // Reset the version to prevent update
            przedmiot.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("przedmiot", przedmiot);
            model.addAttribute("concurrency", true);
            return new ModelAndView("przedmiots/jednostkiLekcyjne/create");
        } else if (!Objects.equals(version, przedmiot.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("przedmiot", przedmiot);
            model.addAttribute("concurrency", false);
            return new ModelAndView("przedmiots/jednostkiLekcyjne/create");
        }
        getPrzedmiotService().setJednostkiLekcyjne(przedmiot, jednostkiLekcyjne);
        return new ModelAndView("redirect:" + getCollectionLink().to(PrzedmiotsCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
