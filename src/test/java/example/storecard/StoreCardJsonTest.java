package example.storecard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class StoreCardJsonTest {

//    @Autowired
//    private JacksonTester<StoreCard> json;
//
//    @Test
//    public void storeCardSerializationTest() throws IOException {
//        StoreCard storeCard = new StoreCard(99L, 123.45);
//        assertThat(json.write(storeCard)).isStrictlyEqualToJson("expected.json");
//        assertThat(json.write(storeCard)).hasJsonPathNumberValue("@.id");
//        assertThat(json.write(storeCard)).extractingJsonPathNumberValue("@.id")
//                .isEqualTo(99);
//        assertThat(json.write(storeCard)).hasJsonPathNumberValue("@.amount");
//        assertThat(json.write(storeCard)).extractingJsonPathNumberValue("@.amount")
//                .isEqualTo(123.45);
//    }
//
//    @Test
//    public void storeCardDeserializationTest() throws IOException{
//        String expected = """
//                {
//                    "id": 99,
//                    "amount": 123.45
//                }
//                """;
//        assertThat(json.parse(expected)).isEqualTo(new StoreCard(99L, 123.45));
////        assertThat(json.parseObject(expected).id()).isEqualTo(99);
////        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
//    }

}
