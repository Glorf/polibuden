package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooDetail;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooThymeleaf;
import pl.poznan.put.model.Grupa;
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
import pl.poznan.put.model.Student;
import pl.poznan.put.service.api.GrupaService;
import pl.poznan.put.service.api.StudentService;

/**
 * = GrupasItemStudenciThymeleafController
 *
 * TODO Auto-generated class documentation
 *
 */
@RooController(entity = Grupa.class, type = ControllerType.DETAIL)
@RooDetail(relationField = "studenci", views = { "show" })
@RooThymeleaf
@Controller
@RequestMapping(value = "/grupas/{grupa}/studenci", name = "GrupasItemStudenciThymeleafController", produces = MediaType.TEXT_HTML_VALUE)
public class GrupasItemStudenciThymeleafController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private StudentService studentService;

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
    private MethodLinkBuilderFactory<GrupasCollectionThymeleafController> collectionLink;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param grupaService
     * @param studentService
     * @param conversionService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public GrupasItemStudenciThymeleafController(GrupaService grupaService, StudentService studentService, ConversionService conversionService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setGrupaService(grupaService);
        setStudentService(studentService);
        setConversionService(conversionService);
        setMessageSource(messageSource);
        setCollectionLink(linkBuilder.of(GrupasCollectionThymeleafController.class));
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
     * @return StudentService
     */
    public StudentService getStudentService() {
        return studentService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param studentService
     */
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
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
    public MethodLinkBuilderFactory<GrupasCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<GrupasCollectionThymeleafController> collectionLink) {
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
     * @return Grupa
     */
    @ModelAttribute
    public Grupa getGrupa(@PathVariable("grupa") Long id, Locale locale, HttpMethod method) {
        Grupa grupa = null;
        if (HttpMethod.PUT.equals(method)) {
            grupa = grupaService.findOneForUpdate(id);
        } else {
            grupa = grupaService.findOne(id);
        }
        if (grupa == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] { "Grupa", id }, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return grupa;
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
     * @param podgrupa
     * @param datatablesColumns
     * @param search
     * @param pageable
     * @param draw
     * @return ResponseEntity
     */
    @GetMapping(name = "datatables", produces = Datatables.MEDIA_TYPE, value = "/dt")
    @ResponseBody
    public ResponseEntity<ConvertedDatatablesData<Student>> datatables(@ModelAttribute Grupa podgrupa, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Student> studenci = getStudentService().findByPodgrupa(podgrupa, search, pageable);
        long totalStudenciCount = getStudentService().countByPodgrupa(podgrupa);
        ConvertedDatatablesData<Student> data = new ConvertedDatatablesData<Student>(studenci, totalStudenciCount, draw, getConversionService(), datatablesColumns);
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
    public ResponseEntity<ConvertedDatatablesData<Student>> datatablesByIdsIn(@RequestParam("ids") List<Long> ids, DatatablesColumns datatablesColumns, GlobalSearch search, DatatablesPageable pageable, @RequestParam("draw") Integer draw) {
        Page<Student> studenci = getStudentService().findAllByIdsIn(ids, search, pageable);
        long totalStudenciCount = studenci.getTotalElements();
        ConvertedDatatablesData<Student> data = new ConvertedDatatablesData<Student>(studenci, totalStudenciCount, draw, getConversionService(), datatablesColumns);
        return ResponseEntity.ok(data);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/create-form", name = "createForm")
    public ModelAndView createForm(@ModelAttribute Grupa grupa, Model model) {
        populateForm(model);
        model.addAttribute("student", new Student());
        return new ModelAndView("grupas/studenci/create");
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenciToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromStudenci", value = "/{studenciToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromStudenci(@ModelAttribute Grupa grupa, @PathVariable("studenciToRemove") Long studenciToRemove) {
        getGrupaService().removeFromStudenci(grupa, Collections.singleton(studenciToRemove));
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenciToRemove
     * @return ResponseEntity
     */
    @DeleteMapping(name = "removeFromStudenciBatch", value = "/batch/{studenciToRemove}")
    @ResponseBody
    public ResponseEntity<?> removeFromStudenciBatch(@ModelAttribute Grupa grupa, @PathVariable("studenciToRemove") Collection<Long> studenciToRemove) {
        getGrupaService().removeFromStudenci(grupa, studenciToRemove);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grupa
     * @param studenci
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PostMapping(name = "create")
    public ModelAndView create(@ModelAttribute Grupa grupa, @RequestParam(value = "studenciIds", required = false) List<Long> studenci, @RequestParam("parentVersion") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Remove empty values
        if (studenci != null) {
            for (Iterator<Long> iterator = studenci.iterator(); iterator.hasNext(); ) {
                if (iterator.next() == null) {
                    iterator.remove();
                }
            }
        }
        // Concurrency control
        if (!Objects.equals(version, grupa.getVersion()) && StringUtils.isEmpty(concurrencyControl)) {
            populateForm(model);
            // Obtain the selected books and include them in the author that will be
            // included in the view
            if (studenci != null) {
                grupa.setStudenci(new HashSet<Student>(getStudentService().findAll(studenci)));
            } else {
                grupa.setStudenci(new HashSet<Student>());
            }
            // Reset the version to prevent update
            grupa.setVersion(version);
            // Include the updated element in the model
            model.addAttribute("grupa", grupa);
            model.addAttribute("concurrency", true);
            return new ModelAndView("grupas/studenci/create");
        } else if (!Objects.equals(version, grupa.getVersion()) && "discard".equals(concurrencyControl)) {
            populateForm(model);
            // Provide the original element from the Database
            model.addAttribute("grupa", grupa);
            model.addAttribute("concurrency", false);
            return new ModelAndView("grupas/studenci/create");
        }
        getGrupaService().setStudenci(grupa, studenci);
        return new ModelAndView("redirect:" + getCollectionLink().to(GrupasCollectionThymeleafLinkFactory.LIST).toUriString());
    }
}
