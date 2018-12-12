package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Zespol;
import pl.poznan.put.service.api.ZespolService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = ZespolDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Zespol.class)
@JsonComponent
public class ZespolDeserializer extends JsonObjectDeserializer<Zespol> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ZespolService zespolService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param zespolService
     * @param conversionService
     */
    @Autowired
    public ZespolDeserializer(@Lazy ZespolService zespolService, ConversionService conversionService) {
        this.zespolService = zespolService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ZespolService
     */
    public ZespolService getZespolService() {
        return zespolService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param zespolService
     */
    public void setZespolService(ZespolService zespolService) {
        this.zespolService = zespolService;
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
     * @return Zespol
     */
    public Zespol deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Zespol zespol = zespolService.findOne(id);
        if (zespol == null) {
            throw new NotFoundException("Zespol not found");
        }
        return zespol;
    }
}
