import org.example.CarRental.CarRental;
import org.example.CarRental.Cart;
import org.example.CarRental.CartType;
import org.example.CarRental.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CarRentalTest {

    public CarRental cartRental;


    @BeforeEach
    public void setUpClass() {
        cartRental = new CarRental();
        cartRental.addCart(new Cart(CartType.ONE_SEATER));
        cartRental.addCart(new Cart(CartType.TWO_SEATER));
        cartRental.addCart(new Cart(CartType.THREE_SEATER));
        cartRental.addCart(new Cart(CartType.ONE_SEATER));
        cartRental.addCart(new Cart(CartType.TWO_SEATER));
        cartRental.addCart(new Cart(CartType.THREE_SEATER));
        cartRental.addCart(new Cart(CartType.ONE_SEATER));
        cartRental.addCart(new Cart(CartType.TWO_SEATER));
        cartRental.addCart(new Cart(CartType.THREE_SEATER));
    }

    @Test
    public void whoIsRentingCart() {
        Person renterTest = new Person("Hubert", "Kulka");
        cartRental.rentCart(1, renterTest);
        Person renter = cartRental.getRenter(1);
        Assertions.assertEquals(renter, renterTest);
    }

    @Test
    public void howManyCartsAreRented() {
        cartRental.rentCart(2, new Person("Marzena", "Karp"));
        int rentedCartAmmount = cartRental.getRentedCartsAmmount();
        Assertions.assertEquals(1, rentedCartAmmount);
    }

    @Test
    public void howManyCartsOfGivenType() {
        cartRental.rentCart(1, new Person("Kamil", "Czapla"));
        int rentedCartAmmount = cartRental.getRentedCartsAmmount(CartType.ONE_SEATER);
        Assertions.assertEquals(1, rentedCartAmmount);
    }

    @Test
    public void whoIsRenting() {
        List<Person> peronWhoIsRentingList = List.of(new Person("Stas", "Karpov"), new Person("Stas1", "Karpov2"), new Person("Stas2", "Karpov3"));

        cartRental.rentCart(2, new Person("Stas", "Karpov"));
        cartRental.rentCart(4, new Person("Stas1", "Karpov2"));
        cartRental.rentCart(7, new Person("Stas2", "Karpov3"));
        List<Person> personWhoIsRentingListFromCartRental = cartRental.getListOfRentals();
        Assertions.assertEquals(peronWhoIsRentingList, personWhoIsRentingListFromCartRental);
    }

    @Timeout(value = 1, unit = TimeUnit.MILLISECONDS)
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void getRenter(int cartId) {
        Person[] person = new Person[3];
        person[0] = new Person("Stas", "Karpov");
        person[1] = new Person("Stas1", "Karpov2");
        person[2] = new Person("Stas2", "Karpov3");

        cartRental.rentCart(1, person[0]);
        cartRental.rentCart(2,person[1] );
        cartRental.rentCart(3, person[2]);
        Assertions.assertEquals(cartRental.getRenter(cartId),person[cartId-1]);
    }

    @RepeatedTest(20)
    public void repeating(){
        cartRental.addCart(new Cart(CartType.ONE_SEATER));
    }
}