package fr.anthonyquere.teashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



class TeaTest {
    @Test
    void should_create_tea_with_correct_properties() {
        Tea tea = new Tea("Green Tea", 120, 80, true);
        assertThat(tea.getName()).isEqualTo("Green Tea");
        assertThat(tea.getSteepingTimeSeconds()).isEqualTo(120);
        assertThat(tea.getIdealTemperatureCelsius()).isEqualTo(80);
        assertThat(tea.isLoose()).isTrue();
    }
}

class TeaCupTest {
    private TeaCup teaCup;
    private Tea tea;

    private TeaShop teaShop;

    @BeforeEach
    void setUp() {
        teaCup = new TeaCup();
        tea = new Tea("Black Tea", 180, 85, false);
        teaShop = new TeaShop(90);
    }

    @Test
    void shouldThrowExceptionForNonExistingTea() {
        assertThatThrownBy(() -> teaShop.prepareTea("Earl Grey"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Tea not available");
    }


    @Test
    void should_allow_adding_water() {
        teaCup.addWater(90);
        assertThat(teaCup.isReadyToDrink()).isFalse();
    }
}

class TeaShopTest {
    private TeaShop teaShop;
    private Tea tea;

    @BeforeEach
    void setUp() {
        teaShop = new TeaShop(90);
        tea = new Tea("Oolong", 150, 85, true);
    }

    @Test
    void should_add_tea_to_shop() {
        teaShop.addTea(tea);
        TeaCup cup = teaShop.prepareTea("Oolong");
        assertThat(cup).isNotNull();
    }

    @Test
    void shouldThrowExceptionForNonExistingTea() {
        assertThatThrownBy(() -> teaShop.prepareTea("Earl Grey"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Tea not available");
    }

    @Test
    void should_not_allow_invalid_water_temperature() {
        assertThatThrownBy(() -> teaShop.setWaterTemperature(110))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Water temperature must be between 0 and 100");

        assertThatThrownBy(() -> teaShop.setWaterTemperature(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Water temperature must be between 0 and 100");
    }
}
