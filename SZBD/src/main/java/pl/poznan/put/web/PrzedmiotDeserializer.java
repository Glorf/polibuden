package pl.poznan.put.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import pl.poznan.put.model.Przedmiot;
import pl.poznan.put.service.api.PrzedmiotService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = PrzedmiotDeserializer
 *
 * TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Przedmiot.class)
@JsonComponent
public class PrzedmiotDeserializer extends JsonObjectDeserializer<Przedmiot> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private PrzedmiotService przedmiotService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param przedmiotService
     * @param conversionService
     */
    @Autowired
    public PrzedmiotDeserializer(@Lazy PrzedmiotService przedmiotService, ConversionService conversionService) {
        this.przedmiotService = przedmiotService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return PrzedmiotService
     */
    public PrzedmiotService getPrzedmiotService() {
        return przedmiotService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param przedmiotService
     */
    public void setPrzedmiotService(PrzedmiotService przedmiotService) {
        this.przedmiotService = przedmiotService;
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
     * @return Przedmiot
     */
    public Przedmiot deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Przedmiot przedmiot = przedmiotService.findOne(id);
        if (przedmiot == null) {
            throw new NotFoundException("Przedmiot not found");
        }
        return przedmiot;
    }
}
