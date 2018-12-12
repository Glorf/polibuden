package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Kierunek;
import pl.poznan.put.service.api.KierunekService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = KierunekDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Kierunek.class)
@JsonComponent
public class KierunekDeserializer extends JsonObjectDeserializer<Kierunek> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private KierunekService kierunekService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param kierunekService
     * @param conversionService
     */
    @Autowired
    public KierunekDeserializer(@Lazy KierunekService kierunekService, ConversionService conversionService) {
        this.kierunekService = kierunekService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return KierunekService
     */
    public KierunekService getKierunekService() {
        return kierunekService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param kierunekService
     */
    public void setKierunekService(KierunekService kierunekService) {
        this.kierunekService = kierunekService;
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
     * @return Kierunek
     */
    public Kierunek deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Kierunek kierunek = kierunekService.findOne(id);
        if (kierunek == null) {
            throw new NotFoundException("Kierunek not found");
        }
        return kierunek;
    }
}
