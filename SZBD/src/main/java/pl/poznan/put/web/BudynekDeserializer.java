package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Budynek;
import pl.poznan.put.service.api.BudynekService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = BudynekDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Budynek.class)
@JsonComponent
public class BudynekDeserializer extends JsonObjectDeserializer<Budynek> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private BudynekService budynekService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param budynekService
     * @param conversionService
     */
    @Autowired
    public BudynekDeserializer(@Lazy BudynekService budynekService, ConversionService conversionService) {
        this.budynekService = budynekService;
        this.conversionService = conversionService;
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
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return Budynek
     */
    public Budynek deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Budynek budynek = budynekService.findOne(id);
        if (budynek == null) {
            throw new NotFoundException("Budynek not found");
        }
        return budynek;
    }
}
