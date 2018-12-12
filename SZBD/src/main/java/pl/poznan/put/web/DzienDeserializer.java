package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Dzien;
import pl.poznan.put.service.api.DzienService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = DzienDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Dzien.class)
@JsonComponent
public class DzienDeserializer extends JsonObjectDeserializer<Dzien> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private DzienService dzienService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param dzienService
     * @param conversionService
     */
    @Autowired
    public DzienDeserializer(@Lazy DzienService dzienService, ConversionService conversionService) {
        this.dzienService = dzienService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return DzienService
     */
    public DzienService getDzienService() {
        return dzienService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param dzienService
     */
    public void setDzienService(DzienService dzienService) {
        this.dzienService = dzienService;
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
     * @return Dzien
     */
    public Dzien deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Dzien dzien = dzienService.findOne(id);
        if (dzien == null) {
            throw new NotFoundException("Dzien not found");
        }
        return dzien;
    }
}
