package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Sala;
import pl.poznan.put.service.api.SalaService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = SalaDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Sala.class)
@JsonComponent
public class SalaDeserializer extends JsonObjectDeserializer<Sala> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private SalaService salaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param salaService
     * @param conversionService
     */
    @Autowired
    public SalaDeserializer(@Lazy SalaService salaService, ConversionService conversionService) {
        this.salaService = salaService;
        this.conversionService = conversionService;
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
     * @return Sala
     */
    public Sala deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Sala sala = salaService.findOne(id);
        if (sala == null) {
            throw new NotFoundException("Sala not found");
        }
        return sala;
    }
}
