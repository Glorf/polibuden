package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.TypZajec;
import pl.poznan.put.service.api.TypZajecService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = TypZajecDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = TypZajec.class)
@JsonComponent
public class TypZajecDeserializer extends JsonObjectDeserializer<TypZajec> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private TypZajecService typZajecService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param typZajecService
     * @param conversionService
     */
    @Autowired
    public TypZajecDeserializer(@Lazy TypZajecService typZajecService, ConversionService conversionService) {
        this.typZajecService = typZajecService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return TypZajecService
     */
    public TypZajecService getTypZajecService() {
        return typZajecService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param typZajecService
     */
    public void setTypZajecService(TypZajecService typZajecService) {
        this.typZajecService = typZajecService;
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
     * @return TypZajec
     */
    public TypZajec deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        TypZajec typZajec = typZajecService.findOne(id);
        if (typZajec == null) {
            throw new NotFoundException("TypZajec not found");
        }
        return typZajec;
    }
}
