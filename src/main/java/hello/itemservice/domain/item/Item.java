package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data //Data에 Getter, Setter, ToString 등이 있다. 원하지 않은 동작을 할 수 있기 때문에 권장하지는 않는다. DTO때만 괜찮을듯?
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; //price가 안들어갈 때도 있으니까 null을 받을 수 있는 Integer로 했다. int로 하면 0으로라도 넣어줘야한다.
    private Integer quantity; //수량

    public Item() { //기본 생성자 만들어준다.
    }

    public Item(String itemName, Integer price, Integer quantity) { //id를 제외한 생성자 만들어준다.
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
