package api.representations;

import domain.sessions.PrioSession;

import java.util.List;

public class PrioSessionDto {
    private String id;
    private List<PrioItemDto> items;



    public static PrioSessionDto fromSession(PrioSession entity) {
        PrioSessionDto s = new PrioSessionDto();

        s.setId(entity.getId());
        s.setItems(PrioItemDto.fromItems(entity.getItems()));

        return s;
    }


    public static PrioSessionDto withKeyAndOptions(String key, List<PrioItemDto> prioItemDtos) {
        PrioSessionDto s = new PrioSessionDto();

        s.setId(key);
        s.setItems(prioItemDtos);

        return s;
    }






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItems(List<PrioItemDto> items) {
        this.items = items;
    }

    public List<PrioItemDto> getItems() {
        return items;
    }

}
