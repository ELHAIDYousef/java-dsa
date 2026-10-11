package com.shoplite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Order service — advanced Mockito")
class OrderServiceAdvancedTest {
    static final String EMAIL = "yousef@mail.com";
    @Mock
    PaymentGateway payment;
    @Mock
    OrderRepository repository;
    @Mock
    NotificationService notifier;
    @Spy
    PriceCalculator calculator = new PriceCalculator();
    @InjectMocks
    OrderService service;
    @Captor
    ArgumentCaptor<Order> orderCaptor;
    @Captor
    ArgumentCaptor<String> messageCaptor;
    Cart cart;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cart.add(new Product("Mouse", 120));
        cart.add(new Product("Pad", 30)); // 150 → 135
    }

    // ---------- matchers ----------
    @Test
    void approvesAnyAmount_savesSomeOrder() {
        when(payment.charge(anyDouble())).thenReturn(true);
        service.placeOrder(cart, EMAIL);
        verify(repository).save(any(Order.class));
        verify(repository).save(argThat(o -> Math.abs(o.total() - 135.0) < 0.0001));
        verify(payment).charge(doubleThat(d -> Math.abs(d - 135.0) < 0.0001));
    }

    // ---------- captors ----------
    @Test
    void savedOrder_hasCorrectContent() {
        when(payment.charge(135.0)).thenReturn(true);
        service.placeOrder(cart, EMAIL);
        verify(repository).save(orderCaptor.capture());
        Order saved = orderCaptor.getValue();
        assertThat(saved.id()).isNotBlank();
        assertThat(saved.total()).isCloseTo(135.0, within(0.0001));
        assertThat(saved.items()).extracting(Product::name).containsExactly("Mouse", "Pad");
    }

    @Test
    void confirmationEmail_hasCorrectText() {
        when(payment.charge(135.0)).thenReturn(true);
        Order order = service.placeOrder(cart, EMAIL);
        verify(notifier).sendConfirmation(eq(EMAIL), messageCaptor.capture());
        assertThat(messageCaptor.getValue())
                .isEqualTo("Order " + order.id() + " confirmed. Total: 135.0 MAD");
    }

    // ---------- void methods ----------
    @Test
    void emailFails_orderStillSaved() {
        when(payment.charge(135.0)).thenReturn(true);
        doThrow(new RuntimeException("SMTP server down"))
                .when(notifier).sendConfirmation(anyString(), anyString());
        Order order = service.placeOrder(cart, EMAIL);
        assertThat(order).isNotNull();
        verify(repository).save(order);
    }

    // ---------- answers ----------
    @Test
    void overBankLimit_declined() {
        when(payment.charge(anyDouble())).thenAnswer(inv -> inv.<Double>getArgument(0) <= 5000);
        Cart bigCart = new Cart();
        bigCart.add(new Product("Laptop", 9000)); // → 8100
        assertThatIllegalStateException()
                .isThrownBy(() -> service.placeOrder(bigCart, EMAIL))
                .withMessage("Payment declined");
        verifyNoInteractions(repository, notifier);
    }

    // ---------- spy ----------
    @Test
    void discountComputedOnFullCartTotal() {
        when(payment.charge(135.0)).thenReturn(true);
        service.placeOrder(cart, EMAIL);
        verify(calculator).finalPrice(150.0);
    }

    @Test
    void spy_canOverrideOneMethod() {
        doReturn(1.0).when(calculator).finalPrice(150.0); // pretend a huge promo
        when(payment.charge(1.0)).thenReturn(true);
        Order order = service.placeOrder(cart, EMAIL);
        assertThat(order.total()).isEqualTo(1.0);
    }

    // ---------- order of calls ----------
    @Test
    void chargesThenSavesThenEmails() {
        when(payment.charge(135.0)).thenReturn(true);
        service.placeOrder(cart, EMAIL);
        InOrder inOrder = inOrder(payment, repository, notifier);
        inOrder.verify(payment).charge(135.0);
        inOrder.verify(repository).save(any(Order.class));
        inOrder.verify(notifier).sendConfirmation(eq(EMAIL), anyString());
    }

    // ---------- BDD style ----------
    @Test
    void declined_bddStyle() {
        // Given
        given(payment.charge(135.0)).willReturn(false);
        // When
        Throwable thrown = catchThrowable(() -> service.placeOrder(cart, EMAIL));
        // Then
        assertThat(thrown).isInstanceOf(IllegalStateException.class)
                .hasMessage("Payment declined");
        then(repository).shouldHaveNoInteractions();
        then(notifier).shouldHaveNoInteractions();
    }
}