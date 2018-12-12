package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Literatura;
import pl.poznan.put.service.api.LiteraturaService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = LiteraturaDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Literatura.class)
@JsonComponent
public class LiteraturaDeserializer extends JsonObjectDeserializer<Literatura> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private LiteraturaService literaturaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param literaturaService
     * @param conversionService
     */
    @Autowired
    public LiteraturaDeserializer(@Lazy LiteraturaService literaturaService, ConversionService conversionService) {
        this.literaturaService = literaturaService;
        this.conversionService = conversionService;
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
     * @return Literatura
     */
    public Literatura deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Literatura literatura = literaturaService.findOne(id);
        if (literatura == null) {
            throw new NotFoundException("Literatura not found");
        }
        return literatura;
    }
}
