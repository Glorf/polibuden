package pl.poznan.put.web;
import org.springframework.roo.addon.web.mvc.thymeleaf.annotations.RooLinkFactory;
import io.springlets.web.mvc.util.MethodLinkFactory;
import io.springlets.web.mvc.util.SpringletsMvcUriComponentsBuilder;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

/**
 * = DziensItemJednostkiLekcyjneThymeleafLinkFactory
 *
 * TODO Auto-generated class documentation
 *
 */
@RooLinkFactory(controller = DziensItemJednostkiLekcyjneThymeleafController.class)
@Component
public class DziensItemJednostkiLekcyjneThymeleafLinkFactory implements MethodLinkFactory<DziensItemJednostkiLekcyjneThymeleafController> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DATATABLESBYIDSIN = "datatablesByIdsIn";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String REMOVEFROMJEDNOSTKILEKCYJNEBATCH = "removeFromJednostkiLekcyjneBatch";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CREATE = "create";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String REMOVEFROMJEDNOSTKILEKCYJNE = "removeFromJednostkiLekcyjne";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DATATABLES = "datatables";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CREATEFORM = "createForm";

    /**
     * TODO Auto-generated method documentation
     *
     * @return Class
     */
    public Class<DziensItemJednostkiLekcyjneThymeleafController> getControllerClass() {
        return DziensItemJednostkiLekcyjneThymeleafController.class;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param methodName
     * @param parameters
     * @param pathVariables
     * @return UriComponents
     */
    public UriComponents toUri(String methodName, Object[] parameters, Map<String, Object> pathVariables) {
        if (methodName.equals(DATATABLES)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).datatables(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(DATATABLESBYIDSIN)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).datatablesByIdsIn(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(CREATEFORM)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).createForm(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(REMOVEFROMJEDNOSTKILEKCYJNE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).removeFromJednostkiLekcyjne(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(REMOVEFROMJEDNOSTKILEKCYJNEBATCH)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).removeFromJednostkiLekcyjneBatch(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(CREATE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).create(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        throw new IllegalArgumentException("Invalid method name: " + methodName);
    }
}
