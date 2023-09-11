package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository //들어가보면 @Component가 있다. ComponentScan의 대상이 된다.
public class ItemRepository { //item 저장소

    //key를 Long으로 한 이유는 id가 Long 타입이기 때문에 맞춰준거다.
    //static으로 선언해준것도 주의해서 봐줘야한다. 다른곳에서 new로 생성해서 사용하는걸 막기 위함.(공유되어야 할 데이터들이다.)
    //HashMap으로 만들었는데, 현재 싱글톤이므로 동시에 여러 쓰레드가 store에 접근할 수 있다. 실제 운영에서는 ConcurrentHashMap<>() 를 사용해야한다.
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L; //static //실제 운영에서는 long을 사용하면 안된다. 값이 꼬일 수 있다. atomiclong 사용하면된다.

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() { //import는 java.util
        return new ArrayList<>(store.values()); //한번 ArrayList로 감싸서 반환한다. store 보호 및 타입변환문제 해결
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
