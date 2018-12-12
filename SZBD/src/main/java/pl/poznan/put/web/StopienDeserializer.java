package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Stopien;
import pl.poznan.put.service.api.StopienService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = StopienDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Stopien.class)
@JsonComponent
public class StopienDeserializer extends JsonObjectDeserializer<Stopien> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private StopienService stopienService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param stopienService
     * @param conversionService
     */
    @Autowired
    public StopienDeserializer(@Lazy StopienService stopienService, ConversionService conversionService) {
        this.stopienService = stopienService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return StopienService
     */
    public StopienService getStopienService() {
        return stopienService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param stopienService
     */
    public void setStopienService(StopienService stopienService) {
        this.stopienService = stopienService;
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
     * @return Stopien
     */
    public Stopien deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Stopien stopien = stopienService.findOne(id);
        if (stopien == null) {
            throw new NotFoundException("Stopien not found");
        }
        return stopien;
    }
}
