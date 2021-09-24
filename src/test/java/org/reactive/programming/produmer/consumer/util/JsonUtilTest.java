package org.reactive.programming.produmer.consumer.util;

import org.junit.jupiter.api.Test;
import org.reactive.programming.produmer.consumer.domain.Customer;
import org.reactive.programming.produmer.consumer.domain.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JsonUtilTest {

    @Test
    public void can_get_customer_from_json(){
        JsonUtil util = new JsonUtil();
        Customer customer = util.getCustomer("{\n" +
                "          \"id\": 1,\n" +
                "          \"name\": \"Test User\",\n" +
                "          \"registeredDate\": \"01-01-2020\",\n" +
                "          \"attrNumber\": 0,\n" +
                "          \"products\": [\n" +
                "             {\n" +
                "                  \"id\": 1\n" +
                "            },\n" +
                "            {\n" +
                "                    \"id\": 2\n" +
                "             },\n" +
                "            {\n" +
                "                    \"id\": 3\n" +
                "            }\n" +
                "          ]\n" +
                "        }");
        assertEquals(1, customer.getId());
        assertEquals("Test User", customer.getName());
        assertEquals("01-01-2020", customer.getRegisteredDate());
        assertEquals(3, customer.getProducts().size());
    }
    @Test
    public void can_not_get_customer_from_json() {
        JsonUtil util = new JsonUtil();
        Customer customer = util.getCustomer("empty json");
        assertNull(customer);
    }

    @Test
    public void can_get_product_from_json(){
        JsonUtil util = new JsonUtil();
        Product product = util.getProduct("{\n" +
                "          \"id\": 1,\n" +
                "          \"name\": \"saving\",\n" +
                "          \"department\": \"accounts\",\n" +
                "          \"attrNumber\" :2\n" +
                "\n" +
                "      }");
        assertEquals(1, product.getId());
        assertEquals("saving", product.getName());
        assertEquals("accounts", product.getDepartment());
        assertEquals(2, product.getAttrNumber());
    }
    @Test
    public void can_not_get_product_from_json() {
        JsonUtil util = new JsonUtil();
        Product product = util.getProduct("empty json");
        assertNull(product);
    }
}
