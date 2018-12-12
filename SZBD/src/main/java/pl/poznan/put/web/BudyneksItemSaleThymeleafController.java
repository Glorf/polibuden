package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Budynek;
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
import pl.poznan.put.model.Sala;
import pl.poznan.put.service.api.BudynekService;
import pl.poznan.put.service.api.SalaService;

/**
 * = BudyneksItemSaleThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Budynek.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "sale", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/budyneks/{budynek}/sale", name = "BudyneksItemSaleThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class BudyneksItemSaleThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private BudynekService budynekService;

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
    private MethodLinkBuilderFactory<BudyneksCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SalaService salaService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param budynekService
     * @param salaService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public BudyneksItemSaleThymeleafController(BudynekService budynekService, SalaService salaService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setBudynekService(budynekService);
        setSalaService(salaService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setCollectionLink(linkBuilder.of(BudyneksCollectionThymeleafController.class));
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return BudynekService
     */
    public BudynekService getBudynekService() {
        return budynekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynekService
     */
    public void setBudynekService(BudynekService budynekService) {
        this.budynekService = budynekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return SalaService
     */
    public SalaService getSalaService() {
        return salaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param salaService
     */
    public void setSalaService(SalaService salaService) {
        this.salaService = salaService;
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
    public MethodLinkBuilderFactory<BudyneksCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<BudyneksCollectionThymeleafController> collectionLink) {
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
     * @return Budynek
     */
    @ModelAttribute
    public Budynek getBudynek(@PathVariable("budynek") Long id, Locale locale, HttpMethod method) {
        Budynek budynek = null;
        if (HttpMethod.PUT.equals(method)) {
            budynek = budynekService.findOneForUpdate(id);
        } else {
            budynek = budynekService.findOne(id);
        }
        if (budynek == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Budynek", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return budynek;
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
     * @param budynek
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Sala>> datatables(@ModelAttribute Budynek budynek, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Sala> sale = getSalaService().findByBudynek(budynek, search, pageable);
        long totalSaleCount = getSalaService().countByBudynek(budynek);
        ConvertedDatatablesData<Sala> data = new ConvertedDatatablesData<Sala>(sale, totalSaleCount, draw, getConversionService(), datatablesColumns);
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
    public ResponseEntity<ConvertedDatatablesData<Sala>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Sala> sale = getSalaService().findAllByIdsIn(ids, search, pageable);
        long totalSaleCount = sale.getTotalElements();
        ConvertedDatatablesData<Sala> data = new ConvertedDatatablesData<Sala>(sale, totalSaleCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Budynek budynek, Model model) {
        populateForm(model);
        model.addAttribute("sala", new Sala());
        return new ModelAndView("budyneks/sale/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param saleToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromSale", value = "/{saleToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromSale(@ModelAttribute Budynek budynek, @PathVariable("saleToRemove") Long saleToRemove) {
        getBudynekService().removeFromSale(budynek, Collections.singleton(saleToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param saleToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromSaleBatch", value = "/batch/{saleToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromSaleBatch(@ModelAttribute Budynek budynek, @PathVariable("saleToRemove") Collection<Long> saleToRemove) {
        getBudynekService().removeFromSale(budynek, saleToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param budynek
     * @param sale
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Budynek budynek, @RequestParam(value = "saleIds", required = false) List<Long> sale, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (sale != null) {
            for (Iterator<Long> iterator = sale.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, budynek.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (sale != null) {
                budynek.setSale(new HashSet<Sala>(getSalaService().findAll(sale)));
            } else {
                budynek.setSale(new HashSet<Sala>());
            }
            // Reset the version to prevent update
            budynek.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("budynek", budynek);
            model.addAttribute("concurrency", true);
            return new ModelAndView("budyneks/sale/create");
        } else if (!Objects.equals(version, budynek.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("budynek", budynek);
            model.addAttribute("concurrency", false);
            return new ModelAndView("budyneks/sale/create");
        }
        getBudynekService().setSale(budynek, sale);
        return new ModelAndView("redirect:" + getCollectionLink().to(BudyneksCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
