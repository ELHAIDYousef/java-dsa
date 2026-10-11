package com.shoplite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
@DisplayName("Shopping cart (AssertJ)")
class CartAssertJTest {
    private Cart cart;
    private final Product mouse = new Product("Mouse", 120);
    private final Product pad = new Product("Pad", 30);
    @BeforeEach
    void createEmptyCart() {
        cart = new Cart();
    }
    @Test
    void newCart_isEmpty() {
        assertThat(cart.isEmpty()).isTrue();
        assertThat(cart.items()).isEmpty();
        assertThat(cart.total()).isZero();
    }
    @Test
    void items_keepInsertionOrder() {
        cart.add(mouse);
        cart.add(pad);
        assertThat(cart.items()).hasSize(2).containsExactly(mouse, pad);
    }
    @Test
    void items_namesAndPrices() {
        cart.add(mouse);
        cart.add(pad);
        assertThat(cart.items())
                .extracting(Product::name, Product::price)
                .containsExactly(tuple("Mouse", 120.0), tuple("Pad", 30.0));
    }
    @Test
    void items_allHavePositivePrices() {
        cart.add(mouse);
        cart.add(pad);
        assertThat(cart.items()).allMatch(p -> p.price() > 0);
    }
    @Test
    void items_filterExpensiveOnes() {
        cart.add(mouse);
        cart.add(pad);
        assertThat(cart.items()).filteredOn(p -> p.price() > 100).containsExactly(mouse);
    }
    @Test
    void total_ofSmallPrices_isCloseTo() {
        cart.add(new Product("Sticker", 0.1));
        cart.add(new Product("Pin", 0.2));
        assertThat(cart.total()).isCloseTo(0.3, within(0.0001));
    }
    @Test
    void findByName_returnsSameObject() {
        cart.add(mouse);
        assertThat(cart.findByName("Mouse")).isSameAs(mouse);
        assertThat(cart.findByName("Keyboard")).isNull();
    }
    @Test
    void add_null_throws() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> cart.add(null))
                .withMessage("Product cannot be null");
    }
    @Test
    void items_returnedList_cannotBeModified() {
        cart.add(mouse);
        assertThatThrownBy(() -> cart.items().add(pad))
                .isInstanceOf(UnsupportedOperationException.class);
    }
    @Test
    void add_validProduct_doesNotThrow() {
        assertThatCode(() -> cart.add(mouse)).doesNotThrowAnyException();
    }
    @Test
    void negativePrice_messageIsClear() {
        PriceCalculator calculator = new PriceCalculator();
        assertThatThrownBy(() -> calculator.finalPrice(-50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("Amount cannot be negative")
                .hasMessageContaining("-50.0");
    }
    @Test
    void cartState_softAssertions() {
        cart.add(mouse);
        cart.add(pad);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(cart.itemCount()).as("count").isEqualTo(2);
            softly.assertThat(cart.isEmpty()).as("empty?").isFalse();
            softly.assertThat(cart.total()).as("total").isCloseTo(150.0, within(0.0001));
        });
    }
}