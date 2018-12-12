package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Grupa;
import pl.poznan.put.service.api.GrupaService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = GrupaDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Grupa.class)
@JsonComponent
public class GrupaDeserializer extends JsonObjectDeserializer<Grupa> {

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
     * TODO Auto-generated constructor documentation
     *
     * @param grupaService
     * @param conversionService
     */
    @Autowired
    public GrupaDeserializer(@Lazy GrupaService grupaService, ConversionService conversionService) {
        this.grupaService = grupaService;
        this.conversionService = conversionService;
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
     * @return Grupa
     */
    public Grupa deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Grupa grupa = grupaService.findOne(id);
        if (grupa == null) {
            throw new NotFoundException("Grupa not found");
        }
        return grupa;
    }
}
