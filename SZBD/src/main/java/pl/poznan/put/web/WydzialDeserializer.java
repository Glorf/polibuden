package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Wydzial;
import pl.poznan.put.service.api.WydzialService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = WydzialDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Wydzial.class)
@JsonComponent
public class WydzialDeserializer extends JsonObjectDeserializer<Wydzial> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private WydzialService wydzialService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param wydzialService
     * @param conversionService
     */
    @Autowired
    public WydzialDeserializer(@Lazy WydzialService wydzialService, ConversionService conversionService) {
        this.wydzialService = wydzialService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return WydzialService
     */
    public WydzialService getWydzialService() {
        return wydzialService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param wydzialService
     */
    public void setWydzialService(WydzialService wydzialService) {
        this.wydzialService = wydzialService;
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
     * @return Wydzial
     */
    public Wydzial deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Wydzial wydzial = wydzialService.findOne(id);
        if (wydzial == null) {
            throw new NotFoundException("Wydzial not found");
        }
        return wydzial;
    }
}
