package util;

import com.google.gson.InstanceCreator;
import model.Address;
import model.User;

import java.lang.reflect.Type;

/**
 * Created by yoges on 7/9/2016.
 */
public class AddressInstanceCreator implements InstanceCreator<Address> {
    private final User user;

    public AddressInstanceCreator(User user){
        this.user = user;
    }

    @Override
    public Address createInstance(Type type) {
        return new Address();
    }
}
