package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.JednostkaLekcyjna;
import pl.poznan.put.service.api.JednostkaLekcyjnaService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = JednostkaLekcyjnaDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = JednostkaLekcyjna.class)
@JsonComponent
public class JednostkaLekcyjnaDeserializer extends JsonObjectDeserializer<JednostkaLekcyjna> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private JednostkaLekcyjnaService jednostkaLekcyjnaService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param jednostkaLekcyjnaService
     * @param conversionService
     */
    @Autowired
    public JednostkaLekcyjnaDeserializer(@Lazy JednostkaLekcyjnaService jednostkaLekcyjnaService, ConversionService conversionService) {
        this.jednostkaLekcyjnaService = jednostkaLekcyjnaService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return JednostkaLekcyjnaService
     */
    public JednostkaLekcyjnaService getJednostkaLekcyjnaService() {
        return jednostkaLekcyjnaService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jednostkaLekcyjnaService
     */
    public void setJednostkaLekcyjnaService(JednostkaLekcyjnaService jednostkaLekcyjnaService) {
        this.jednostkaLekcyjnaService = jednostkaLekcyjnaService;
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
     * @return JednostkaLekcyjna
     */
    public JednostkaLekcyjna deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        JednostkaLekcyjna jednostkaLekcyjna = jednostkaLekcyjnaService.findOne(id);
        if (jednostkaLekcyjna == null) {
            throw new NotFoundException("JednostkaLekcyjna not found");
        }
        return jednostkaLekcyjna;
    }
}
